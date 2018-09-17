package com.macys.sdt.shared.steps.website;


import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoginCredentials;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.actions.website.mcom.pages.registry.CreateRegistry;
import com.macys.sdt.shared.actions.website.mcom.panels.Registry.RegistryFlexibleTemplate;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Registry extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);
    private UserProfile regUser;
    private String promoCode;
    private Double beforePrice;
    private boolean newRegistryCreated = false;
    public static boolean registryMode = false;

    /**
     * Visits the web site then creates or logs into a registry
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the web site as a registry user$")
    public void I_visit_the_web_site_as_a_registry_user() throws Throwable {
        regUser = TestUsers.getNewRegistryUser();
        // Get new co_registrant_fname & co_registrant_lname values if these names are matching with registrant first_name and last_name
        while (regUser.getRegistry().getCoRegistrantFirstName().equals(regUser.getUser().getProfileAddress().getFirstName()) && regUser.getRegistry().getCoRegistrantLastName().equals(regUser.getUser().getProfileAddress().getLastName())) {
            regUser.getRegistry().setCoRegistrantFirstName(TestUsers.generateRandomFirstName());
            regUser.getRegistry().setCoRegistrantLastName(TestUsers.generateRandomLastName());
        }
        pausePageHangWatchDog();
        sign_in_or_create_registry(regUser);
        resumePageHangWatchDog();
        new CheckoutSteps().iRemoveAllItemsInShoppingBag();
        if (newRegistryCreated) {
            new MyAccountSteps().iAddCheckoutEligibleAddressOnMyAddressBookPage();
        }

        if (macys()) {
            Navigate.visit("registry_home");
            Wait.forPageReady();
            Clicks.click("registry_home.goto_registry_manager");
        } else {
            Navigate.visit("registry_bvr");
        }
        Wait.forPageReady();
    }

    /**
     * Creates or logs into a registry
     *
     * @param user_details profile to create
     * @throws Throwable if any exception occurs
     */
    public void sign_in_or_create_registry(UserProfile user_details) throws Throwable {
        User registryUser = regUser.getUser();
        ProfileAddress userAddress = regUser.getUser().getProfileAddress();
        if (!prodEnv()) {
            userAddress.setEmail(TestUsers.generateRandomEmail(16));
            regUser.getUser().setProfileAddress(userAddress);
            regUser.setUser(registryUser);
        }
        LoginCredentials credentials = regUser.getUser().getLoginCredentials();
        com.macys.sdt.framework.model.registry.Registry userRegistry = regUser.getRegistry();

        new PageNavigation().I_visit_the_web_site_as_a_guest_user();
        Clicks.click("home.goto_wedding_registry");
        CommonUtils.closeIECertError();
        Wait.forPageReady();
        if (safari()) { //slow safari hence the wait
            Wait.secondsUntilElementPresent("registry_home.goto_create_registry", 15);
        }

        new PageNavigation().iSelectCreateRegistry();
        //pausePageHangWatchDog();
        if( macys()){
            CreateRegistry.createRegistryUser(regUser);
            Wait.forPageReady();
            // Cannot create Registry user
            if (safari()) {
                ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
                try {
                    shopAndBrowse.i_remove_all_items_from_the_shopping_bag();
                } catch (Exception ignored) {
                }
            }
            if (!onPage("registry_manager")) {
                Navigate.visit("registry_manager");
                Wait.forPageReady();
            }
            Assert.assertFalse("ERROR - ENV : Registry Services are in Batch Mode!!", (macys() ? Elements.getText(By.id("generalErrorMsg")).contains("Your registry is not available online") : Elements.elementPresent(By.className("registry-batch-mode"))));
            Assert.assertFalse("ERROR - ENV : Registry Flex Template services are down!!", title().contains(macys() ? "Site Unavailable" : "oops"));
            shouldBeOnPage("registry_manager");
        }
        else if (bloomingdales()) {
            Wait.secondsUntilElementPresent("registry_capture_email_page.email", 10);
            if (safari() && Elements.findElement("registry_capture_email_page.continue_button").isDisplayed()) {
                CommonUtils.retryAction(() -> {
                    Navigate.browserRefresh();
                    Wait.forPageReady();
                    return Elements.findElement("registry_capture_email_page.continue_button").isEnabled();
                }, 3, "ERROR - APP : continue button on registry sign in page is not enabled!!");
            }

            TextBoxes.typeTextbox("registry_capture_email_page.email", userAddress.getEmail());
            TextBoxes.typeTextbox("registry_capture_email_page.password", credentials.getPassword());
            Clicks.click("registry_capture_email_page.continue_button");
            Wait.forPageReady();
            if (Cookies.getCookieValue("SMISCGCs").contains("regId") && !Cookies.getCookieValue("SMISCGCs").contains("regId1_92_null")) {
                Matcher m = Pattern.compile("regId1_92_(\\d+)").matcher(Cookies.getCookieValue("SMISCGCs"));
                userRegistry.setId(m.find() ? m.group(1) : null);
            }
            if (userRegistry.getId() != null) {
                logger.info("User is already logged into registry");
            } else {
                Wait.forPageReady();
                CreateProfile.closeSecurityAlertPopUp();
                Wait.secondsUntilElementPresent("registry_capture_email_page.error_message", 10);
                if (Elements.elementPresent("registry_capture_email_page.error_message")) {
                    if (prodEnv()) {
                        throw new ProductionException("Cannot create accounts on prod!");
                    }
                    Wait.secondsUntilElementPresent("registry_capture_email_page.create_registry_button", 5);

                    Elements.findElement("registry_capture_email_page.create_registry_button").click();
                    Wait.forPageReady();
                    CreateRegistry.createRegistryUser(regUser);
                    Wait.forPageReady();
                    if (safari()) {
                        Wait.secondsUntilElementPresent("registry_welcome.verify_page", 20);
                    }
                }
                if (!onPage("registry_bvr")) {
                    Wait.secondsUntilElementPresent("registry_welcome.verify_page", (safari() ? 15 : 5));
                    shouldBeOnPage("registry_welcome");
                    newRegistryCreated = true;
                }
            }


            if (safari()) {
                ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
                try {
                    shopAndBrowse.i_remove_all_items_from_the_shopping_bag();
                } catch (Exception ignored) {
                }
            }
            if (!onPage("registry_bvr")) {
                Navigate.visit("registry_bvr");
                Wait.forPageReady();
            }
            Assert.assertFalse("ERROR - ENV : Registry Services are in Batch Mode!!", (macys() ? Elements.getText(By.id("generalErrorMsg")).contains("Your registry is not available online") : Elements.elementPresent(By.className("registry-batch-mode"))));
            Assert.assertFalse("ERROR - ENV : Registry Flex Template services are down!!", title().contains(macys() ? "Site Unavailable" : "oops"));
            shouldBeOnPage("registry_bvr");
        }
        if (!onPage("registry_manager")) {
            Navigate.visit("registry_manager");
            Wait.forPageReady();
        }
        Assert.assertFalse("ERROR - ENV : Registry Services are in Batch Mode!!", (macys() ? Elements.getText(By.id("generalErrorMsg")).contains("Your registry is not available online") : Elements.elementPresent(By.className("registry-batch-mode"))));
        Assert.assertFalse("ERROR - ENV : Registry Flex Template services are down!!", title().contains(macys() ? "Site Unavailable" : "oops"));
        onPage("registry_manager");
        resumePageHangWatchDog();
    }

    /**
     * Navigates to registry sign in page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to registry sign in page$")
    public void I_navigate_to_registry_sign_in_page() throws Throwable {
        Clicks.javascriptClick("registry_home.goto_create_registry");
        shouldBeOnPage("registry_sign_in", "new_registry_sign_in");
    }

    /**
     * Creates a registry. Assumes browser is on create registry page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I continue creating registry from create registry page$")
    public void I_continue_creating_registry_from_create_registry_page() throws Throwable {
        if (macys()) {
            CreateRegistry.createRegistryUserForExistingUser(TestUsers.getExistingRegistryUser());
        } else {
            CreateRegistry.fillRegistryUserDetailsForExistingUser(TestUsers.getExistingRegistryUser());
        }
    }

    /**
     * Navigates the browser back from login page to registry home page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate back to registry home page from capture email page$")
    public void I_navigate_back_to_registry_home_page_from_capture_email_page() throws Throwable {
        Navigate.browserBack();
    }

    /**
     * Adds the current product to registry
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add the product to a registry$")
    public void I_add_the_product_to_a_registry() throws Throwable {
        Clicks.click("product_display.add_to_registry");
        Wait.secondsUntilElementPresent("add_to_registry_dialog.add_to_registry_dialog", 5);
        Assert.assertTrue("ERROR-ENV: Add to Registry Dialog is not present", Elements.elementPresent("add_to_registry_dialog.add_to_registry_dialog"));
    }

    /**
     * Verifies that the browser is on the registry manager page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to the registry manager page$")
    public void I_should_navigate_to_registry_manager_page() throws Throwable {
        if (onPage("registry_welcome"))
            Navigate.visit("registry_manager");
        Wait.forPageReady();
        shouldBeOnPage("registry_manager", "registry_welcome");
    }

    /**
     * Signs into existing user from sign in page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I sign in with existing profile on capture email page$")
    public void I_sign_in_with_existing_profile_and_from_capture_email_page() throws Throwable {
        TextBoxes.typeTextbox("registry_sign_in.existing_user_email", TestUsers.currentEmail);
        TextBoxes.typeTextbox("registry_sign_in.existing_user_password", TestUsers.currentPassword);
        Clicks.click("registry_sign_in.existing_user_continue_button");
        shouldBeOnPage("create_registry", "new_create_registry");
    }

    /**
     * Navigates to the registry home page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to registry home page|I visit the registry home page$")
    public void I_navigate_to_registry_home_page() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("header.goto_wedding_registry", 15);
        Clicks.click("header.goto_wedding_registry");
        Wait.forPageReady();
        if (safari()) {
            Wait.secondsUntilElementPresent("registry_home.goto_find_registry", 30);
        }
        if(!onPage("registry_home")){
            Clicks.javascriptClick("header.goto_wedding_registry");
        }
        CommonUtils.closeIECertError();
        shouldBeOnPage("registry_home");
        registryMode = true;
    }

    /**
     * Verifies that the browser is on a registry PDP
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be redirected to registry PDP(?: page)?$")
    public void I_should_be_redirected_to_registry_PDP_page() throws Throwable {
        shouldBeOnPage("registry_pdp");
    }

    /**
     * Begins the process of creating a registry starting on the registry sign in page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I start to create a new registry from registry sign in page$")
    public void I_start_to_create_a_new_registry_from_registry_capture_email_page() throws Throwable {
        TestUsers.clearCustomer();
        regUser = TestUsers.getNewRegistryUser();
        if (bloomingdales()) {
            Wait.secondsUntilElementPresent("registry_capture_email_page.email", 10);
            if (safari() && Elements.findElement("registry_capture_email_page.continue_button").isDisplayed()) {
                CommonUtils.retryAction(() -> {
                    Navigate.browserRefresh();
                    Wait.forPageReady();
                    return Elements.findElement("registry_capture_email_page.continue_button").isEnabled();
                }, 3, "ERROR - APP : continue button on registry sign in page is not enabled!!");
            }
            TextBoxes.typeTextbox("registry_capture_email_page.email", regUser.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox("registry_capture_email_page.password", regUser.getUser().getLoginCredentials().getPassword());
            Clicks.click("registry_capture_email_page.continue_button");
            Wait.forPageReady();
            CreateProfile.closeSecurityAlertPopUp();
            Wait.secondsUntilElementPresent("registry_capture_email_page.error_message", 10);
            if (Elements.elementPresent("registry_capture_email_page.error_message")) {
                if (prodEnv()) {
                    throw new ProductionException("Cannot create accounts on prod!");
                }
                Wait.secondsUntilElementPresent("registry_capture_email_page.create_registry_button", 5);

                Elements.findElement("registry_capture_email_page.create_registry_button").click();
                Wait.forPageReady();
            }
        }
        else{
            Wait.secondsUntilElementPresent("registry_sign_in.existing_user_email", 10);
            if (prodEnv()) {
                TextBoxes.typeTextbox("registry_sign_in.existing_user_email", regUser.getUser().getProfileAddress().getEmail());
                TextBoxes.typeTextbox("registry_sign_in.existing_user_password", regUser.getUser().getLoginCredentials().getPassword());
                Clicks.click("registry_sign_in.existing_user_continue_button");
                shouldBeOnPage("registry_manager");
            } else if (onPage("registry_capture_email_page")) {
                TextBoxes.typeTextbox("new_registry_sign_in.email", regUser.getUser().getProfileAddress().getEmail());
                TextBoxes.typeTextbox("new_registry_sign_in.password", regUser.getUser().getLoginCredentials().getPassword());
                Clicks.click("new_registry_sign_in.sign_in_button");
            } else {
                Assert.fail("User is currently not in Registry sign in Page");
            }
        }
    }

    /**
     * Verifies that the registry has been created successfully
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see the registry created successfully$")
    public void I_should_see_the_registry_created_successfully() throws Throwable {
//        Wait.untilElementPresent("create_registry.create_message");
//        Wait.untilElementNotPresent("create_registry.create_message");
        Wait.untilElementPresent("registry_welcome.verify_page");
        if (onPage("registry_welcome")) {
            Navigate.visit("registry_home");
        }
        Wait.forPageReady();
        if (safari()) {
            Wait.secondsUntilElementPresent("registry_manager.registry_id", 15);
        }
        Assert.assertFalse("ERROR - ENV : Registry Flex Template services are down!!", title().contains(macys() ? "Site Unavailable" : "oops"));
        shouldBeOnPage("registry_manager");
        CreateRegistry.verifyRegistryIsCreatedInDB(Elements.findElement("registry_manager.registry_id").getText());
    }

    /**
     * Click on the edit profile button on the registry manager page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click on edit profile link on registry (BVR|manager) page$")
    public void I_click_on_edit_profile_link_on_registry_manager_page(String pageType) throws Throwable {
        if(pageType.equalsIgnoreCase("bvr")) {
            if (safari()) {
                Wait.untilElementPresent("registry_bvr.edit_registry");
            }
            if (!chrome() && macys()) {
                Clicks.javascriptClick("registry_bvr.edit_registry");
            } else {
                Clicks.click("registry_bvr.edit_registry");
            }
        }
        else{
            if (safari()) {
                Wait.untilElementPresent("registry_manager.edit_profile_button");
            }
            if (!chrome() && macys()) {
                Clicks.javascriptClick("registry_manager.edit_profile_button");
            } else {
                Clicks.click("registry_manager.edit_profile_button");
            }
        }
    }

    /**
     * Verifies that the browser is on the update registry page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see update registry page$")
    public void I_should_see_update_registry_page() throws Throwable {
        if (macys()) {
            if (safari()) {
                Wait.untilElementPresent("edit_registry.verify_page");
            }
            Wait.forPageReady();
            shouldBeOnPage("edit_registry");
        }
        else{
            if (safari()) {
                Wait.untilElementPresent("edit_registry_redesign.verify_page");
            }
            Wait.forPageReady();
            shouldBeOnPage("edit_registry_redesign");
        }
    }

    @When("^I update the event location to \"([^\"]*)\" in update registry page$")
    public void I_update_the_event_location_in_update_registry_page(String location) throws Throwable {
        Wait.untilElementPresent("edit_registry.event_location");
        DropDowns.selectByText("edit_registry.event_location", location);
        Clicks.click("edit_registry.update_registry_button");
    }

    /**
     * Updates the co registrant first name in registry manager page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I update \"co_registrant_first_name\" in update registry page$")
    public void I_update_in_update_registry_page() throws Throwable {
        String updatedFirstName = TestUsers.generateRandomFirstName();
        if (regUser != null && regUser.getRegistry() != null) {
            regUser.getRegistry().setCoRegistrantFirstName(updatedFirstName);
        }
        if (bloomingdales()){
            Wait.untilElementPresent("edit_registry_redesign.co_registrant_first_name");
            TextBoxes.typeTextbox("edit_registry_redesign.co_registrant_first_name", updatedFirstName);
            Clicks.click("edit_registry_redesign.save_button");
            Wait.untilElementPresent("edit_registry_redesign.save_confirmation_overlay");
            Assert.assertFalse("ERROR - APP : Unable to update registry information!!", Wait.untilElementPresent("edit_registry_redesign.error_message"));
            Wait.untilElementNotPresent("edit_registry_redesign.loader_overlay");
            Wait.untilElementNotPresent("edit_registry_redesign.save_confirmation_overlay");
        }
        else {
            Wait.untilElementPresent("edit_registry.co_registrant_first_name");
            TextBoxes.typeTextbox("edit_registry.co_registrant_first_name", updatedFirstName);
            Clicks.click("edit_registry.update_registry_button");
            Wait.untilElementPresent("create_registry.close_overlay_chat");
            Assert.assertFalse("ERROR - APP : Unable to update registry information!!", Wait.untilElementPresent("edit_registry.error_message"));
            if (Elements.elementPresent("create_registry.close_overlay_chat")) {
                Clicks.click("create_registry.close_overlay_chat");
            }
        }
        Wait.forPageReady();
    }

    /**
     * Verifies that co registrant first name field has been updated
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see updated \"co_registrant_first_name\" in registry manager page$")
    public void I_should_see_updated_data_in_registry_manager_page() throws Throwable {
        String actualRegistryTitle;
        String expectedRegistryTitle;
        if(macys()) {
            Wait.secondsUntilElementPresent("registry_manager.registry_title", 5);
            actualRegistryTitle = Elements.getText("registry_manager.registry_title").trim();
            expectedRegistryTitle = regUser.getUser().getProfileAddress().getFirstName() + " & " + regUser.getRegistry().getCoRegistrantFirstName() + "'S " + regUser.getRegistry().getEventType();
        }
        else{
            Wait.secondsUntilElementPresent("registry_bvr.header", 5);
            expectedRegistryTitle = String.format("%s %s and %s %s", regUser.getUser().getProfileAddress().getFirstName(), regUser.getUser().getProfileAddress().getLastName()
                                            , regUser.getRegistry().getCoRegistrantFirstName(), regUser.getRegistry().getCoRegistrantLastName()).toLowerCase();
            actualRegistryTitle = Elements.getText("registry_bvr.header_names").trim();
        }
        Assert.assertTrue("Registry title is not updated properly(actual: (" + actualRegistryTitle + ") :: expected: (" + expectedRegistryTitle + "))!!", actualRegistryTitle.equalsIgnoreCase(expectedRegistryTitle));
    }

    /**
     * Searches for an existing registry using currently saved registry data
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I search for the existing couple's registry$")
    public void i_search_for_the_existing_couple_s_registry() throws Throwable {
        String capturedFirstName = regUser.getUser().getProfileAddress().getFirstName();
        String capturedLastName = regUser.getUser().getProfileAddress().getLastName();

        TextBoxes.typeTextbox("find_registry.first_name", capturedFirstName);
        TextBoxes.typeTextbox("find_registry.last_name", capturedLastName);
        Clicks.click("find_registry.find_registry_button");
    }

    /**
     * Verifies that the correct registry has been found
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should find the couple's registry$")
    public void i_should_find_the_couple_s_registry() throws Throwable {
        String capturedFirstName = regUser.getUser().getProfileAddress().getFirstName();
        String capturedLastName = regUser.getUser().getProfileAddress().getLastName();
        String capturedName = capturedFirstName + " " + capturedLastName;
        Boolean found = false;
        Wait.forPageReady();
        if (safari()) {
            Wait.secondsUntilElementPresent("find_registry.find_registry_results", 10);
        }
        List<WebElement> elist = Elements.findElements("find_registry.find_registry_results");
        for (WebElement we : elist) {
            String str = we.getText();
            if (str.equalsIgnoreCase(capturedName)) {
                found = true;
                break;
            }
        }
        if (!found) {
            Assert.fail("Registry not found");
        }
    }

    /**
     * Verifies that the names on the registry are correct
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the registrant & co registrant name details$")
    public void i_verify_the_registrant_co_registrant_name_details() throws Throwable {
        String first_name = regUser.getUser().getProfileAddress().getFirstName();
        String last_name = regUser.getUser().getProfileAddress().getLastName();
        String co_registrant_fn = regUser.getRegistry().getCoRegistrantFirstName();
        String co_registrant_ln = regUser.getRegistry().getCoRegistrantLastName();

        Wait.untilElementPresent("shopping_bag.registrant_name_details");
        String registrant_details = Elements.getText("shopping_bag.registrant_name_details").trim();

        try {
            if (macys()) {
                String registrant_co_registrant_name = first_name + " " + last_name + " & " + co_registrant_fn + " " + co_registrant_ln;
                Assert.assertTrue("Error: registrant and co-registrant name incorrect(Actual: " + registrant_details + " || Expected: " + registrant_co_registrant_name + ")!!", registrant_details.toUpperCase().contains(registrant_co_registrant_name));
            } else {
                String[] split_name = registrant_details.split(" & ");

                String registrant_name = first_name + " " + last_name;
                String co_registrant_name = co_registrant_fn + " " + co_registrant_ln;

                Assert.assertTrue("Error: registrant name incorrect(Actual: " + split_name[0].trim() + " || Expected: " + registrant_name + ")!!", split_name[0].trim().equalsIgnoreCase(registrant_name));
                Assert.assertTrue("Error: Co-registrant name incorrect(Actual: " + split_name[1].trim() + " || Expected: " + co_registrant_name + ")!!", split_name[1].trim().equalsIgnoreCase(co_registrant_name));
            }
        } catch (Exception e) {
            Assert.fail("Registrant and Co Registrant name on shopping bag are not matching with Registrant data" + e);
        }
    }

    /**
     * Creates registry with given time to even and event type
     *
     * @param event_time "less than" or "more than"
     * @param event_type "WEDDING" or "COMMITMENT" or "ANNIVERSARY"
     * @throws Throwable if any exception occurs
     */
    @And("^I create a new wedding registry with event date as past date which is (less than|more than) 185 days and event type as \"(WEDDING|COMMITMENT||ANNIVERSARY)\" option$")
    public void i_create_a_new_wedding_registry_with_event_date_as_past_date_which_is_less_than_185_days_and_event_type_as_wedding_option(String event_time, String event_type) throws Throwable {
        Calendar cal = Calendar.getInstance();
        Date modified_date;
        Random random = new Random();
        if (event_time.equals("less than")) {
            modified_date = DateUtils.addDays(new Date(), -(random.nextInt((185 - 1) + 1) + 1));
        } else {
            modified_date = DateUtils.addDays(new Date(), random.nextInt((185 - 1) + 1) + 1);
        }
        cal.setTime(modified_date);
        String year = Integer.toString(cal.get(Calendar.YEAR));
        int month = cal.get(Calendar.MONTH);
        String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
        String month_name = WordUtils.capitalizeFully(Month.of(month + 1).name());
        regUser = TestUsers.getNewRegistryUser();
        regUser.getRegistry().setEventType(event_type);
        regUser.getRegistry().setEventMonth(month_name);
        regUser.getRegistry().setEventDay(day);
        regUser.getRegistry().setEventYear(year);
        sign_in_or_create_registry(regUser);
        // update the event date if the event date is not updated in update registry page.
        if(bloomingdales()) {
            I_click_on_edit_profile_link_on_registry_manager_page("bvr");
        }
        else {
            I_click_on_edit_profile_link_on_registry_manager_page("manager");
        }
        Wait.forPageReady();
        shouldBeOnPage("edit_registry");
        Wait.untilElementPresent("edit_registry.event_month");
        CreateRegistry.selectDropDownText("edit_registry.event_month", month_name);
        CreateRegistry.selectDropDownText("edit_registry.event_day", day);
        CreateRegistry.selectDropDownText("edit_registry.event_year", year);
        Clicks.click("edit_registry.update_registry_button");
        Assert.assertFalse("ERROR - APP : Unable to update registry !!", Wait.secondsUntilElementPresent("edit_registry.error_message", 5));
        Wait.untilElementPresent("create_registry.close_overlay_chat");
        Clicks.clickIfPresent("create_registry.close_overlay_chat");
        Wait.forPageReady();
    }

    /**
     * Saves the promo code on the registry manager page to a local variable
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I save promocode displayed on registry (manager|BVR) page$")
    public void I_save_promocode_displayed_on_registry_manager_page(String pageType) throws Throwable {
        //String promoBannerName = macys() ? "completion_banner" : "get_offer_now";
        if (pageType.equalsIgnoreCase("bvr")){
            Wait.untilElementPresent("registry_bvr.completion_banner");
            Clicks.click("registry_bvr.get_offer_link");
            if (Elements.elementPresent("registry_bvr.offer_overlay")){
                promoCode = Elements.findElement("registry_bvr.online_promo_code_no").getText();
                logger.info("Promo code is " + promoCode);
            }
            else {
                Assert.fail("Online promo code is not displayed");
            }
        }
        else {
            Wait.untilElementPresent("registry_manager.completion_banner");
            if (Elements.elementPresent("registry_manager.completion_banner")) {
                promoCode = Elements.findElement("registry_manager.online_promo_code_no").getAttribute("innerHTML");
            } else {
                Assert.fail("Online promo code is not displayed");
            }
        }
    }

    /**
     * Uses the saved promo code on the shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I apply registry promo code on the shopping bag page$")
    public void I_apply_registry_promo_code_on_the_shopping_bag_page() throws Throwable {
        beforePrice = new Double(macys() ? Elements.getText("shopping_bag.subtotal_price").split("\\$")[1] : Elements.getText("shopping_bag.order_total"));
        TextBoxes.typeTextbox("shopping_bag.promo_code", promoCode);
        Clicks.click("shopping_bag.apply_promo_code_button");
        Wait.untilElementNotPresent("shopping_bag.apply_promo_code_button");
        logger.info("Applied registry promo code on shopping bag page!!");
    }

    /**
     * Verifies that order total is updated on the shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see updated order total on the shopping bag page$")
    public void I_should_see_I_should_see_updated_order_total_on_the_shopping_bag_page() throws Throwable {
        pausePageHangWatchDog();
        Wait.secondsUntilElementPresent("shopping_bag.promo_text", 5);
        List<WebElement> discountTexts = Elements.findElements("shopping_bag.promo_text");
        Double discount = 0.0;
        if (discountTexts.stream().anyMatch(element -> element.getText().toLowerCase().contains("registry"))) {
            discount = discountTexts.stream()
                    .filter(element -> element.getText().toLowerCase().contains("registry"))
                    .map(element -> {
                        String text = element.findElement(By.xpath(edge() ? "..//.." : "..")).findElement(Elements.element("shopping_bag.promo_discount")).getText();
                        return macys() ? text.split("\\$")[1] : text;
                    }).mapToDouble(Double::parseDouble).sum();
        }
        Assert.assertFalse("Registry Promo Code is not applied on shopping bag page!!", discount == 0.0);
        Double after_price = new Double(macys() ? Elements.getText("shopping_bag.subtotal_price").split("\\$")[1] : Elements.getText("shopping_bag.order_total"));
        if (after_price <= (beforePrice - discount)) {
            logger.info("Registry Promotion is applied successfully");
        } else {
            Assert.fail("ERROR - APP : Applied registry promo code is not reflected in order summary!!");
        }
        resumePageHangWatchDog();
    }

    @Then("^I should be navigated to registry \"([^\"]*)\" page$")
    public void iShouldBeNavigatedToRegistryPage(String page) throws Throwable {
        shouldBeOnPage("registry_" + page.replace(" ", "_").toLowerCase());
    }

    @And("^I should see expected media types on registry home page$")
    public void iShouldSeeExpectedMediaTypesOnRegistryHomePage(DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        for (String rowNumber : values.keySet()) {
            if (rowNumber.equalsIgnoreCase("row_type")) {
                continue;
            }
            String selector = "flexible_template.row_one_point_" + convertRowNumber(rowNumber);
            WebElement row = Elements.findElement(selector);
            Assert.assertNotNull(row);
            List<String> actualMediaTypes = RegistryFlexibleTemplate.getMediaTypeInRow(row);
            List<String> mediaTypes = new ArrayList<>();
            mediaTypes.addAll(Arrays.asList(values.get(rowNumber).split(", ")));
            mediaTypes = mediaTypes.stream().map(String::toLowerCase).collect(Collectors.toList());
            for (String mediaType : actualMediaTypes) {
                if (!mediaTypes.contains(mediaType.toLowerCase())) {
                    throw new EnvException("Expected media type " + mediaType + " to be present on the page.");
                }
            }
        }
    }

    @And("^I should see and able to click view ads in \"([^\"]*)\" row on registry home page$")
    public void iShouldSeeAndAbleToClickViewAdsInRowOnRegistryHomePage(String rowId, DataTable table) throws Throwable {
        Map<String, String> values = table.asMap(String.class, String.class);
        for (String key : values.keySet()) {
            if (key.equalsIgnoreCase("column_id")) {
                continue;
            }
            WebElement link = Elements.findElement(By.id(rowId));
            if (link != null) {
                Clicks.click(link.findElement(By.id(key)));
            }
            String expectedUrl = values.get(key);
            if (!url().contains(expectedUrl)) {
                throw new EnvException("URL does not match. \nExpected: " + expectedUrl + "\nfound: " + url());
            }
            Navigate.browserBack();
        }
    }

    private static String convertRowNumber(String rowNumber) {
        String conversion;
        if (rowNumber.endsWith("1")) {
            conversion = "one";
        } else if (rowNumber.endsWith("2")) {
            conversion = "two";
        } else if (rowNumber.endsWith("3")) {
            conversion = "three";
        } else if (rowNumber.endsWith("4")) {
            conversion = "four";
        } else if (rowNumber.endsWith("5")) {
            conversion = "five";
        } else {
            conversion = "six";
        }
        return conversion;
    }


    @When("^I navigate to bvr page$")
    public void iNavigateToBvrPage() throws Throwable {
        Wait.forPageReady();
        if (Elements.findElements(By.className("container-close"), WebElement::isDisplayed).size() > 0) {
            Clicks.click(Elements.findElements(By.className("container-close"), WebElement::isDisplayed).get(0));
        }
        if (onPage("registry_manager")) {
            Clicks.click("registry_manager.view_my_item");
        }
        else {
            Navigate.visit("registry_bvr");
        }
        Wait.forPageReady();
        Assert.assertTrue("Not on Registry BVR page", onPage("registry_bvr"));
    }



    @Then("^I should see the registry ID is present in the header$")
    public void iShouldSeeTheRegistryIDIsPresentInTheHeader() throws Throwable {
        regUser = TestUsers.getCustomerInformation();
        if (onPage("registry_bvr")) {
            logger.info(Elements.getText("registry_bvr.registry_id"));
            logger.info(regUser.getRegistry().getId() + " user ID");
            Assert.assertEquals("Registry ID displayed on the BVR header does not match the Registry ID of the registry data", regUser.getRegistry().getId(), Elements.getText("registry_bvr.registry_id"));
        }
        else if (onPage("registry_gvr")){
            logger.info(Elements.getText("registry_gvr.registry_id"));
            logger.info(regUser.getRegistry().getId() + " registry ID");
            Assert.assertEquals("Registry ID displayed on the GVR header does not match the Registry ID of the registry data", regUser.getRegistry().getId(), Elements.getText("registry_gvr.registry_id"));
        }
        else {
            Assert.fail("User is currently not in Registry BVR/GVR Page");
        }
    }


    @And("^I navigate to registry manager page$")
    public void iNavigateToRegistryManagerPage() throws Throwable {
        Wait.forPageReady();
        if (onPage("home")){
            Clicks.click("home.goto_wedding_registry");
        }
        if (!onPage("registry_manager")) {
            Navigate.visit("registry_manager");
        }
        else{
            logger.info("Already on registry Manager Page");
            Assert.assertTrue("Not on Registry Manager Page", onPage("registry_manager"));
        }

    }

    @Then("^I should land on (BVR|GVR)$")
    public void iShouldLandOnBVR(String bvrGvr){
        if (bvrGvr.equalsIgnoreCase("bvr")){
            Wait.forPageReady("registry_bvr");
            if (isBVRWelcomeOverlay()){
                closeBVRWelcomeOverlay();
            }
            Wait.untilElementNotPresent("bvr_welcome_overlay.yes_button");
            Assert.assertTrue("Not on Registry BVR Page", onPage("registry_bvr"));

        } else {
            Assert.assertTrue("Not on Registry GVR Page", onPage("registry_gvr"));
        }
    }

    @Then("^I should land on Registry Manager$")
    public void iShouldLandOnRegistryManager() throws Throwable {
        Assert.assertTrue("Not on Registry Manager Page", onPage("registry_manager"));
    }

//  This method is used only in registry BOPS. When user opens BOP overlay with no prior stores selected and saved in local storage
//  BOPS Add to Bag button should be disabled
    @And("^I navigate to bvr page with local storage clear$")
    public void iNavigateToBvrPageWithLocalStorageClear() throws Throwable {
        Wait.forPageReady();
        if (Elements.findElements(By.className("container-close"), WebElement::isDisplayed).size() > 0) {
            Clicks.click(Elements.findElements(By.className("container-close"), WebElement::isDisplayed).get(0));
        }
        if (onPage("registry_manager")) {
            Clicks.click("registry_manager.view_my_item");
        }
        else {
            Navigate.visit("registry_bvr");
        }
        Navigate.execJavascript("localStorage.clear()");
        Navigate.execJavascript("sessionStorage.clear()");
        Navigate.browserRefresh();
        Assert.assertTrue("Not on Registry BVR page", onPage("registry_bvr"));
    }

    @And("^I create a new registry for user with existing bcom profile$")
    public void iCreateRegistryForExistingUser() throws Throwable{
        CreateRegistry.createRegistryUserForExistingUser(TestUsers.getExistingRegistryUser());
    }

    @And("^I navigate to registry capture email page$")
    public void iNavigateToRegistryCaptureEmailPage(){
        Navigate.visit("registry_capture_email_page");
    }


    @Given("^I visit the web site as a (registry|gvr) user without checkout$")
    public void iVisitTheWebSiteAsARegistryUserWithoutCheckout(String userType) throws Throwable {
        regUser = TestUsers.getNewRegistryUser();
        // Get new co_registrant_fname & co_registrant_lname values if these names are matching with registrant first_name and last_name
        while (regUser.getRegistry().getCoRegistrantFirstName().equals(regUser.getUser().getProfileAddress().getFirstName()) && regUser.getRegistry().getCoRegistrantLastName().equals(regUser.getUser().getProfileAddress().getLastName())) {
            regUser.getRegistry().setCoRegistrantFirstName(TestUsers.generateRandomFirstName());
            regUser.getRegistry().setCoRegistrantLastName(TestUsers.generateRandomLastName());
        }
        pausePageHangWatchDog();
        sign_in_or_create_registry(regUser);
        resumePageHangWatchDog();
        new CheckoutSteps().iRemoveAllItemsInShoppingBag();

        if (macys()) {
            Navigate.visit("registry_home");
            Wait.forPageReady();
            Clicks.click("registry_home.goto_registry_manager");
        } else {
            Navigate.visit("registry_bvr");
        }
        Wait.forPageReady();
        if (userType.equalsIgnoreCase("gvr")){
            registrySignOut();
        }
    }

    public void registrySignOut(){
        Wait.secondsUntilElementPresent("registry_bvr.goto_my_account_link", 20);
        Clicks.hoverForSelection("registry_bvr.goto_my_account_link");
        Wait.secondsUntilElementPresent("registry_bvr.goto_sign_out_link", 20);
        Clicks.click("registry_bvr.goto_sign_out_link");
        Wait.forPageReady();
        Assert.assertTrue(onPage("home"));
    }


    public boolean isBVRWelcomeOverlay(){
        return Elements.elementInView("registry_bvr.bvr_welcome_overlay_div");
    }

    public void closeBVRWelcomeOverlay(){
        logger.info("The Welcome overlay on BVR page is - " + Elements.anyPresent("registry_bvr.no_button"));
        Elements.findElement("registry_bvr.no_button").click();
    }


}