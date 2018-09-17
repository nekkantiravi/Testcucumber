package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoginCredentials;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.MEW.pages.CreateRegistry;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registry extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);
    private UserProfile regUser;
    private String promoCode;
    private boolean newRegistryCreated = false;

    /**
     * Adds the current product to registry
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add the product to a registry using mobile website$")
    public void I_add_the_product_to_a_registry_using_mobile_website() throws Throwable {
        // There are two element with same id. One for tablet and another for mobile.
        // so had to filter out the displayed element and click in below step
        Clicks.clickIfPresent("home.close_app_banner");
        scrollToLazyLoadElement("product_display.add_to_registry");
        Clicks.click("product_display.add_to_registry");
        Assert.assertTrue("ERROR - DATA : Unable to add product in registry", Wait.secondsUntilElementPresent
                ("add_to_registry_overlay.add_to_registry_overlay", 50));
    }

    /**
     * Visits the web site then creates or logs into a registry
     *
     * @throws Throwable if any exception occurs
     */
    @Given("^I visit the mobile web site as a registry user$")
    public void I_visit_the_mobile_web_site_as_a_registry_user() throws Throwable {
        regUser = TestUsers.getNewRegistryUser();
        sign_in_or_create_registry(regUser);
    }

    /**
     * Creates or logs into a registry
     *
     * @param user_details profile to create
     * @throws Throwable if any exception occurs
     */
    public void sign_in_or_create_registry(UserProfile user_details) throws Throwable {
        Navigate.visit("home");
        closeMewTutorial();
        closeChatAlert();
        ProfileAddress address = regUser.getUser().getProfileAddress();
        LoginCredentials credentials = regUser.getUser().getLoginCredentials();
        if (bloomingdales()) {
            new PageNavigation().I_navigate_the_global_navigation_menu_as_follows(Arrays.asList("The Registry", "Create or Create Registry"));
            pausePageHangWatchDog();
            Wait.secondsUntilElementPresent("registry_capture_email_page.email", 5);
            TextBoxes.typeTextbox("registry_capture_email_page.email", address.getEmail());
            TextBoxes.typeTextbox("registry_capture_email_page.password", credentials.getPassword());
            Clicks.click("registry_capture_email_page.continue_button");
            Wait.forPageReady();
            if (Cookies.getCookieValue("SMISCGCs").contains("regId") && !Cookies.getCookieValue("SMISCGCs").contains("regId1_92_null")) {
                Matcher m = Pattern.compile("regId1_92_(\\d+)").matcher(Cookies.getCookieValue("SMISCGCs"));
                regUser.getUser().setId(m.find() ? m.group(1) : null);
            }
            if (regUser.getUser().getId() != null) {
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
                    Clicks.click("registry_welcome.manage_registry_button");
                }
                Assert.assertTrue(onPage("registry_bvr"));
            }

        } else {
            if (firefox()) {
                Navigate.visit(RunConfig.url + "/wgl/registry/signin?cm_sp=reg_homepage-_-row1-_-create");
                logger.info(RunConfig.url);
            } else {
                GlobalNav.openGlobalNav();
                GlobalNav.navigateOnGnByName("Registry or Wedding Registry");
                GlobalNav.closeGlobalNav();
                //Clicks.click("registry_home.goto_create_registry");
                Clicks.javascriptClick("registry_home.goto_create_registry");
            }
            pausePageHangWatchDog();
            Wait.secondsUntilElementPresent("registry_sign_in.existing_user_email", 5);
            boolean newRegistryEnabled = onPage("new_registry_sign_in");
            if (newRegistryEnabled) {
                Wait.secondsUntilElementPresent("new_registry_sign_in.email", 10);
                TextBoxes.typeTextbox("new_registry_sign_in.email", address.getEmail());
                TextBoxes.typeTextbox("new_registry_sign_in.password", credentials.getPassword());
                Clicks.javascriptClick("new_registry_sign_in.sign_in_button");
            } else {
                TextBoxes.typeTextbox("registry_sign_in.existing_user_email", address.getEmail());
                TextBoxes.typeTextbox("registry_sign_in.existing_user_password", credentials.getPassword());
                Clicks.click("registry_sign_in.existing_user_continue_button");
            }
            CreateProfile.closeSecurityAlertPopUp();
            if (newRegistryEnabled) {
                Wait.untilElementPresent("new_registry_sign_in.error_message");
                if (onPage("new_registry_sign_in") && Elements.elementPresent("new_registry_sign_in.error_message")) {
                    if (prodEnv()) {
                        throw new ProductionException("Cannot create accounts on prod!");
                    }
                    Clicks.javascriptClick("new_registry_sign_in.create_registry_button");
                    Wait.forPageReady();
                    CreateRegistry.createRegistryUser(regUser);
                    Assert.assertFalse("ERROR - ENV : Unable to create registry (Registry Services are down)!!",
                            Elements.getText("new_create_registry.error_message").contains("technical error"));
                    Wait.untilElementPresent("registry_welcome.verify_page");
                    Assert.assertTrue("Registry is not created", Wait.until(() -> onPage("registry_welcome")));
                } else if (onPage("new_create_registry")) {
                    CreateRegistry.createRegistryUserForExistingUser(regUser);
                    Assert.assertFalse("ERROR - ENV : Unable to create registry (Registry Services are down)!!",
                            Elements.getText("new_create_registry.error_message").contains("technical error"));
                    Assert.assertTrue("Registry is not created for existing user", Wait.until(() -> onPage("registry_welcome")));
                } else if (!onPage("registry_manager")) {
                    Assert.assertTrue("Unable to sign in or create registry", Wait.until(() -> onPage("registry_manager")));
                }
            } else {
                if (onPage("registry_sign_in") && Elements.elementPresent("registry_sign_in.error_message")) {
                    if (prodEnv()) {
                        throw new ProductionException("Cannot create accounts on prod!");
                    }
                    TextBoxes.typeTextbox("registry_sign_in.new_user_email", address.getEmail());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_email_verify", address.getEmail());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_password", credentials.getPassword());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_password_verify", credentials.getPassword());
                    Clicks.click("registry_sign_in.new_user_continue_button");
                    CreateRegistry.fillRegistryUserDetails(user_details);
                    if (bloomingdales()) {
                        I_should_be_navigated_to_the_mobile_registry_manager_page("bvr");
                    }
                } else if (onPage("create_registry")) {
                    CreateRegistry.fillRegistryUserDetailsForExistingUser(user_details);
                }
            }
            if (safari()) {
                com.macys.sdt.shared.steps.website.ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
                try {
                    shopAndBrowse.i_remove_all_items_from_the_shopping_bag();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        resumePageHangWatchDog();
        Navigate.visit("home");
        Wait.forPageReady("home");
        Assert.assertTrue("ERROR - ENV : Home page not loaded after sign_in_or_create_registry", onPage("home"));
    }

    /**
     * Begins the process of creating a registry starting on the registry sign in page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I start to create a new registry from mobile registry capture email page$")
    public void I_start_to_create_a_new_registry_from_mobile_registry_capture_email_page() throws Throwable {
        TestUsers.clearCustomer();
        UserProfile regUser = TestUsers.getNewRegistryUser();
        if (bloomingdales()){
            Assert.assertTrue(onPage("registry_capture_email_page"));
            if (prodEnv()) {
                TextBoxes.typeTextbox("registry_capture_email_page.email", regUser.getUser().getProfileAddress().getEmail());
                TextBoxes.typeTextbox("registry_capture_email_page.password", regUser.getUser().getLoginCredentials().getPassword());
                Clicks.click("registry_capture_email_page.continue_button");
                Wait.forPageReady();
                shouldBeOnPage("registry_bvr");
            }
            else{
                Clicks.javascriptClick("registry_capture_email_page.create_registry_button");
                shouldBeOnPage("new_create_registry");
            }

        }
        else {
            if (prodEnv()) {
                TextBoxes.typeTextbox("registry_sign_in.existing_user_email", regUser.getUser().getProfileAddress().getEmail());
                TextBoxes.typeTextbox("registry_sign_in.existing_user_password", regUser.getUser().getLoginCredentials().getPassword());
                Clicks.click("registry_sign_in.existing_user_continue_button");
                shouldBeOnPage("registry_manager");
            } else {
                if (onPage("registry_sign_in")) {
                    TextBoxes.typeTextbox("registry_sign_in.new_user_email", regUser.getUser().getProfileAddress().getEmail());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_email_verify", regUser.getUser().getProfileAddress().getEmail());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_password", regUser.getUser().getLoginCredentials().getPassword());
                    TextBoxes.typeTextbox("registry_sign_in.new_user_password_verify", regUser.getUser().getLoginCredentials().getPassword());
                    Clicks.javascriptClick("registry_sign_in.new_user_continue_button");
                    shouldBeOnPage("create_registry");
                } else if (onPage("new_registry_sign_in")) {
                    TextBoxes.typeTextbox("new_registry_sign_in.email", regUser.getUser().getProfileAddress().getEmail());
                    TextBoxes.typeTextbox("new_registry_sign_in.password", regUser.getUser().getLoginCredentials().getPassword());
                    Clicks.click("new_registry_sign_in.sign_in_button");
                    if (onPage("new_registry_sign_in")) {
                        Clicks.click("new_registry_sign_in.create_registry_button");
                    }
                } else {
                    Assert.fail("User is currently not in Registry Capture Email Page");
                }
            }
        }
    }

    /**
     * Creates a registry. Assumes browser is on create registry page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I create a new registry using mobile website$")
    public void I_create_a_new_registry_using_mobile_website() throws Throwable {
        if (prodEnv()) {
            logger.info("In Production we cannot create new Registries");
        } else {
            Wait.until(()->onPage("new_create_registry"));
            if (onPage("create_registry")) {
                CreateRegistry.fillRegistryUserDetails(TestUsers.getNewRegistryUser());
            } else if (onPage("new_create_registry")) {
                CreateRegistry.createRegistryUser(TestUsers.getNewRegistryUser());
            } else {
                Assert.fail("User is currently not in Create Registry Page");
            }
        }
    }

    /**
     * Verifies registry header is displayed on registry manager page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to the mobile registry (manager|BVR) page$")
    public void I_should_be_navigated_to_the_mobile_registry_manager_page(String pageType) throws Throwable {
        Wait.secondsUntilElementPresent("registry_manager.registry_header", RunConfig.timeout);
        Assert.assertFalse("ERROR - APP : Registry services are down!!", bloomingdales() && Elements.elementPresent("registry_welcome.batch_mode_message"));
        if (onPage("registry_welcome")) {
            if (pageType.equalsIgnoreCase("bvr")) {
                Clicks.click("registry_welcome.manage_registry_button");
                shouldBeOnPage("registry_bvr");
            } else {
                Navigate.visit("registry_home");
                shouldBeOnPage("registry_manager");
            }
        }
        logger.info("User successfully navigated to Registry Manager Page");
    }

    /**
     * Verifies browser is on registry welcome page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to the mobile registry welcome page$")
    public void I_should_be_navigated_to_the_mobile_registry_welcome_page() throws Throwable {
        Wait.secondsUntilElementPresent("registry_welcome.verify_page", 10);
        Assert.assertFalse("ERROR - ENV : Unable to create registry (Registry Services are down)!!",
                Elements.getText("new_create_registry.error_message").contains("technical error"));
        if (onPage("registry_welcome")) {
            logger.info("User successfully navigated to Registry welcome Page");
        } else {
            Assert.fail("User is currently not in Registry welcome Page");
        }
    }

    /**
     * Signs in as existing user from registry sign in page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I sign in with existing profile on mobile capture email page$")
    public void I_sign_in_with_existing_profile_on_mobile_capture_email_page() throws Throwable {
        TextBoxes.typeTextbox("registry_sign_in.existing_user_email", TestUsers.currentEmail);
        TextBoxes.typeTextbox("registry_sign_in.existing_user_password", TestUsers.currentPassword);
        Clicks.click("registry_sign_in.existing_user_continue_button");
        Wait.secondsUntilElementPresent("new_create_registry.continue_button", 10);
        shouldBeOnPage("new_create_registry", "create_registry");
    }

    /**
     * Creates a registry. Assumes browser is on create registry page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I continue creating registry from mobile create registry page$")
    public void I_continue_creating_registry_from_mobile_create_registry_page() throws Throwable {
        if (onPage("create_registry")) {
            CreateRegistry.fillRegistryUserDetailsForExistingUser(TestUsers.getExistingRegistryUser());
        } else if (onPage("new_create_registry")) {
            CreateRegistry.createRegistryUserForExistingUser(TestUsers.getExistingRegistryUser());
        } else {
            Assert.fail("User is currently not in Create Registry Page");
        }
        //There is no welcome page for bcom mew
    }

    /**
     * Navigates to registry home page using Global Nav
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I navigate to wedding registry page$")
    public void I_navigate_to_wedding_registry_page() throws Throwable {
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName("Registry or Wedding Registry");
        GlobalNav.closeGlobalNav();
        //  Wait.secondsUntilElementPresent(Elements.element("registry_home.create_registry"), MainRunner.timeout);
        Assert.assertTrue("ERROR-ENV: Unable to navigate wedding registry page", Elements.elementPresent(Elements.element("registry_home.goto_create_registry")));
    }

    /**
     * Creates registry with given time to even and event type
     *
     * @param event_time "less than" or "more than"
     * @param event_type "WEDDING" or "COMMITMENT" or "ANNIVERSARY"
     * @throws Throwable if any exception occurs
     */
    @And("^I create a new wedding registry with event date as past date which is (less than|more than) 185 days and event type as \"(WEDDING|COMMITMENT||ANNIVERSARY)\" option on mobile site$")
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
        int dayInt = cal.get(Calendar.DAY_OF_MONTH);
        String day = (dayInt < 10) ? ("0" + Integer.toString(dayInt)) : Integer.toString(dayInt);
        String month_name = WordUtils.capitalizeFully(Month.of(month + 1).name());
        TestUsers.clearCustomer();
        TestUsers.clearRegistry();
        regUser = TestUsers.getNewRegistryUser();
        com.macys.sdt.framework.model.registry.Registry registry = regUser.getRegistry();
        registry.setEventType(event_type);
        registry.setEventMonth(month_name);
        registry.setEventDay(day);
        registry.setEventYear(year);
        sign_in_or_create_registry(regUser);
        Wait.forPageReady();
    }

    /**
     * Selects "Registry" or "Wedding Registry" from Global Nav
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to the mobile registry manager page$")
    public void I_navigate_to_mobile_registry_manager_page() throws Throwable {
        GlobalNav.openGlobalNav();
        GlobalNav.navigateOnGnByName("Registry or Wedding Registry");
        shouldBeOnPage("registry_manager");
    }

    /**
     * Saves the promo code from the registry manager page to promoCode variable
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I save promocode displayed on mobile registry (manager|BVR) page$")
    public void I_save_promocode_displayed_on_registry_manager_page(String pageType) throws Throwable {
        if(pageType.equalsIgnoreCase("bvr")){
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
            shouldBeOnPage("registry_manager");
            Assert.assertTrue("Online promo code does not displayed on registry manager page", Wait.untilElementPresent("registry_manager.registry_promocode"));
            promoCode = Elements.getText("registry_manager.registry_promocode").replace("Online Promo code:\n", "");
        }
    }

    /**
     * Uses the promo code saved in promoCode variable on shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I apply registry promo code on mobile shopping bag page$")
    public void I_apply_registry_promo_code_on_the_shopping_bag_page() throws Throwable {
        Assert.assertTrue("ERROR: promo code field is not present", Wait.untilElementPresent(Elements.element("shopping_bag.promocode_area")));
        Clicks.click(Elements.element("shopping_bag.promocode_area"));
        TextBoxes.typeTextbox(Elements.element("shopping_bag.text_promocode"), promoCode);
        Clicks.click(Elements.element("shopping_bag.btn_promocode_apply"));
    }

    /**
     * Verifies that the registry promo text is displayed on shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see registry promocode is applied on mobile shopping bag page$")
    public void I_should_see_registry_promocode_applied_on_mobile_shopping_bag_page() throws Throwable {
        Wait.untilElementPresent("shopping_bag.promo_text");
        String promoText = (macys() ? "Save 20% on most remaining registry gifts!" :
                "Completion Promotion - 10% off registry completion purchases");
        Assert.assertTrue("Registry Promo Code is not applied on shopping bag page!!",
                (bloomingdales() ? Elements.elementPresent("shopping_bag.promo_text") : Elements.getText("shopping_bag.promo_text").contentEquals(promoText)));
    }

    /**
     * Verifies that the names of registrant and co registrant are correct on shopping bag page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify the registrant & co registrant name on mobile shopping bag page$")
    public void I_verify_the_registrant_co_registrant_name_on_mobile_shopping_bag_page() throws Throwable {
        String firstName = regUser.getUser().getProfileAddress().getFirstName();
        String lastName = regUser.getUser().getProfileAddress().getLastName();
        String coRegistrantFirstName = regUser.getRegistry().getCoRegistrantFirstName();
        String coRegistrantLastName = regUser.getRegistry().getCoRegistrantLastName();
        String expected = (firstName + " " + lastName + " & " + coRegistrantFirstName + " " + coRegistrantLastName).toUpperCase();
        Wait.untilElementPresent("shopping_bag.registrant_name_details");
        String actual = Elements.getText("shopping_bag.registrant_name_details").toUpperCase();
        Assert.assertTrue("ERROR - APP: Registrant & Co-registrant name incorrect on shopping bag page. Expected: " + expected +  " Actual: " + actual, actual.contains(expected));
    }

    /**
     * Searches for an existing registry using currently saved registry data
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I search for the existing couple's registry using mobile site$")
    public void i_search_for_the_existing_couple_s_registry() throws Throwable {
        String capturedFirstName = regUser.getUser().getProfileAddress().getFirstName();
        String capturedLastName = regUser.getUser().getProfileAddress().getLastName();
        String page = macys() ? "registry_search" : (onPage("registry_home") ? "registry_home" : "registry_search");
        TextBoxes.typeTextbox(page + ".search_first_name", capturedFirstName);
        TextBoxes.typeTextbox(page + ".search_last_name", capturedLastName);
        DropDowns.selectByText(page + ".event_month", regUser.getRegistry().getEvent().getEventMonthText());
        DropDowns.selectByValue(page + ".event_year", regUser.getRegistry().getEvent().getEventYear());
        if (RunConfig.useAppium && !iOS()) {
            // for Android
            Clicks.javascriptClick(page + ".search_registry_button");
        } else {
            Clicks.click(page + ".search_registry_button");
        }
    }

    /**
     * Verifies that the correct registry has been found
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should find the couple's registry using mobile site$")
    public void i_should_find_the_couple_s_registry() throws Throwable {
        shouldBeOnPage("registry_search");
        pausePageHangWatchDog();
        Wait.untilElementPresent("registry_search.search_results_items");
        Boolean found = false;
        String registrant = regUser.getUser().getProfileAddress().getFirstName() + " " +
                regUser.getUser().getProfileAddress().getLastName();
        String coRegistrant = regUser.getRegistry().getCoRegistrantFirstName() + " " +
                regUser.getRegistry().getCoRegistrantLastName();
        if (macys()) {
            registrant = "Registrant: " + registrant;
            coRegistrant = "Partner: " + coRegistrant;
        }
        Clicks.clickIfPresent("registry_search.show_all_results");
        List<WebElement> searchResults = Elements.findElements("registry_search.search_results_items");
        for (WebElement searchResult : searchResults) {
            if (searchResult.findElement(Elements.element("registry_search.registrant_name")).getText().toLowerCase().contains(registrant.toLowerCase()) &&
                    searchResult.findElement(Elements.element("registry_search.coregistrant_name")).getText().toLowerCase().contains(coRegistrant.toLowerCase())) {
                found = true;
                break;
            }
        }
        Assert.assertTrue("Error: " + registrant + " " + coRegistrant + " registry not found.", found);
        resumePageHangWatchDog();
    }

    /**
     * Click on the edit profile button on the registry manager page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click on edit profile link on mobile registry (BVR|manager) page$")
    public void I_click_on_edit_profile_link_on_mobile_registry_manager_page(String pageType) throws Throwable {
        if (pageType.equalsIgnoreCase("bvr")){
            shouldBeOnPage("registry_bvr");
            Clicks.click("registry_bvr.edit_registry");
        }
        else {
            shouldBeOnPage("registry_manager");
            Clicks.click("registry_manager.edit_our_registry");
        }
    }

    /**
     * Updates the co registrant first name in edit registry page
     *
     * @param page just for step name, unused
     * @throws Throwable if any exception occurs
     */
    @When("^I update co_registrant_first_name in mobile (update|edit) registry page$")
    public void I_update_co_registrant_first_name_in_mobile_update_registry_page(String page) throws Throwable {
        if(bloomingdales()){
            shouldBeOnPage("edit_registry_redesign");
        }
        else{
            shouldBeOnPage("edit_registry");
        }

        String updatedFirstName = TestUsers.generateRandomFirstName();
        if (regUser != null && regUser.getRegistry() != null) {
            regUser.getRegistry().setCoRegistrantFirstName(updatedFirstName);
        }
        if (macys()) {
            Clicks.click("edit_registry.edit_personal_info");
        }
        Wait.untilElementPresent("edit_registry.co_registrant_first_name");
        TextBoxes.typeTextbox("edit_registry.co_registrant_first_name", updatedFirstName);
        if (macys()) {
            Clicks.click("edit_registry.save_personal_info");
            Assert.assertFalse("Unable to update registry. " + Elements.getText("edit_registry.error_msg"), Wait.untilElementPresent("edit_registry.error_msg"));
        } else {
            Navigate.scrollPage(0, 2000);
            Clicks.click("edit_registry_redesign.save_button");
        }
    }

    /**
     * Verifies that co registrant first name is updated on registry manager page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see updated co_registrant_first_name in mobile registry manager page$")
    public void I_should_see_updated_co_registrant_first_name_in_mobile_registry_manager_page() throws Throwable {
        String expectedRegistryHeader;
        String actualRegistryHeader;
        if (macys()){
        Wait.untilElementPresent("registry_manager.registry_header");
        shouldBeOnPage("registry_manager");
        actualRegistryHeader = Elements.getText("registry_manager.registry_header");

            expectedRegistryHeader = "Welcome to your registry manager " +
                    regUser.getUser().getProfileAddress().getFirstName() + " & " +
                    regUser.getRegistry().getCoRegistrantFirstName() + "!";
        } else {
            Wait.untilElementPresent("registry_bvr.header");
            shouldBeOnPage("registry_bvr");
            Wait.secondsUntilElementPresent("registry_bvr.header", 5);
            expectedRegistryHeader = String.format("%s %s and %s %s", regUser.getUser().getProfileAddress().getFirstName(), regUser.getUser().getProfileAddress().getLastName()
                    , regUser.getRegistry().getCoRegistrantFirstName(), regUser.getRegistry().getCoRegistrantLastName()).toLowerCase();
            actualRegistryHeader = Elements.getText("registry_bvr.header_names").trim();
        }
        Assert.assertTrue("Registry title is not updated properly(actual: (" + actualRegistryHeader + ") :: expected: (" + expectedRegistryHeader + "))!!", actualRegistryHeader.equalsIgnoreCase(expectedRegistryHeader));
    }

    /**
     * Adds product to bag from GVR page of currently saved registry and selects checkout button
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add product to bag from GVR page using mobile website and select checkout$")
    public void iAddProductToBagFromGVRPageUsingMobileWebsite() throws Throwable {
        if (macys()) {
            I_navigate_to_wedding_registry_page();
            Clicks.click("registry_home.find_registry_button");
        } else {
            new PageNavigation().I_navigate_the_global_navigation_menu_as_follows(Arrays.asList("The Registry", "Find or Find Registry"));
        }

        i_search_for_the_existing_couple_s_registry();
        i_select_to_view_registry_on_registry_search_results_page();

        Wait.untilElementPresent("registry_gvr.verify_page");
        shouldBeOnPage("registry_gvr");

        if (bloomingdales() && !Elements.elementPresent("registry_gvr.add_to_bag")) {
            Clicks.click("registry_gvr.category_header");
            Wait.untilElementPresent("registry_gvr.quantity");
            DropDowns.selectByText("registry_gvr.quantity", "1");
        }
        Wait.secondsUntilElementPresentAndClick("registry_gvr.add_to_bag", 10);
        Wait.untilElementPresent("registry_gvr.atb_checkout");
        Clicks.click("registry_gvr.atb_checkout");
    }

    @And("^I select to view the registry on registry search results page$")
    public void i_select_to_view_registry_on_registry_search_results_page() {
        shouldBeOnPage("registry_search");
        Wait.untilElementPresent("registry_search.search_results_items");
        Boolean found = false;
        String registrant = regUser.getUser().getProfileAddress().getFirstName() + " " +
                regUser.getUser().getProfileAddress().getLastName();
        String coRegistrant = regUser.getRegistry().getCoRegistrantFirstName() + " " +
                regUser.getRegistry().getCoRegistrantLastName();
        if (macys()) {
            registrant = "Registrant: " + registrant;
            coRegistrant = "Partner: " + coRegistrant;
        }
        List<WebElement> searchResults = Elements.findElements("registry_search.search_results_items");
        for (WebElement searchResult : searchResults) {
            if (searchResult.findElement(Elements.element("registry_search.registrant_name")).getText().equalsIgnoreCase(registrant) &&
                    searchResult.findElement(Elements.element("registry_search.coregistrant_name")).getText().equalsIgnoreCase(coRegistrant)) {
                found = true;
                Clicks.click(searchResult.findElement(Elements.element("registry_search.view_registry_button")));
                break;
            }
        }
        Assert.assertTrue("Error: " + registrant + " " + coRegistrant + " registry not found.", found);
    }

    @And("^I select find registry on registry home page$")
    public void iSelectFindRegistryOnRegistryHomePage() throws Throwable {
        shouldBeOnPage("registry_home");
        Clicks.click("registry_home.find_registry_button");
    }

}
