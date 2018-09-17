package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.db.models.PromotionService;
import com.macys.sdt.shared.actions.MEW.pages.ProductDisplay;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductDisplayPage extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ProductDisplayPage.class);
    public static String mew_promotional_prod_id = null;
    public static String mew_promotion_code = null;

    /**
     * Adds a product with the given attributes to bag and selects checkout if elected
     * <p>
     * <b>Example: </b> I add an "orderable and available and bops_available" product to my bag using mobile website that is not "click_to_call" and checkout<br>
     * this would add a bops product that does not have the "click_to_call" attribute (of course those two wouldn't overlap anyway, but that's not the point)
     * </p>
     *
     * @param productTrue  Properties expected to be true separated by either a space or the word "and"
     * @param productFalse Properties expected to be false separated by either a space or the word "and"
     * @param checkout     present if you want to end up on shopping bag page
     * @throws Throwable if any exception occurs or product cannot be found
     */
    @When("^I add an? \"(.*?)\" product to my bag using mobile website(?: that is not(?: an?)? \"(.*?)\")?(?: and \"?(.*?)\"? ?checkout)?$")
    public void I_add_a_product_to_my_bag_using_mobile_website(String productTrue, String productFalse, String checkout) throws Throwable {
        if (prodEnv()) {
            CommonUtils.navigateToRandomProduct(productTrue, productFalse);
        } else {
            //All the none production related promotional products need to be get from siteDB with their respective attributes
            switch (productTrue) {
                case "orderable and promo_code_eligible":
                    String[] productsWithPromoCode = new PromotionService().getPromoCodeForPromotionalProducts();
                    if (productsWithPromoCode == null) {
                        Assert.fail("ERROR - DATA : Promo-Code eligible promotions are not available in database!!");
                    }
                    mew_promotional_prod_id = productsWithPromoCode[1];
                    mew_promotion_code = productsWithPromoCode[0];
                    CommonUtils.navigateDirectlyToProduct(mew_promotional_prod_id);
                    break;
                case "predefined_promo_code_eligible":
                    //TODO: Remove this case once "promotional_products_with_promo_code" SQL query is Fixed
                    mew_promotional_prod_id = macys() ? "1310" : "78350";
                    mew_promotion_code = macys() ? "valpak10" : "X09520_0007500";
                    CommonUtils.navigateDirectlyToProduct(mew_promotional_prod_id);
                    break;
                case "orderable and pwp":
                    //TODO
                    break;
                default:
                    CommonUtils.navigateToRandomProduct(productTrue, productFalse);
            }
        }
        Clicks.clickIfPresent("product_display.no_thanks");
        I_add_product_to_my_bag_from_standard_PDP_Page_using_mobile_site();
        if (checkout != null) {
            Wait.untilElementPresent("add_to_bag.checkout");
            if (onPage("add_to_bag")) {
                Clicks.click("add_to_bag.checkout");
                shouldBeOnPage("shopping_bag");
                ShoppingBag.removeBonusItemsFromBag();
            }
        }
    }

    /**
     * Adds "Lenox Vintage Jewel 5 Piece Place Setting" (ID 86800) product to bag
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I add a predefined orderable product to my bag using mobile website$")
    public void I_select_a_predefined_orderable_random_member_product_using_mobile_website() throws Throwable {
        /*
              86800: Lenox Vintage Jewel 5 Piece Place Setting
         */
        String[] predefined_product_ids = {"86800"};

        Random rn = new Random();
        CommonUtils.navigateDirectlyToProduct(predefined_product_ids[rn.nextInt(predefined_product_ids.length)]);

        Clicks.clickIfPresent("product_display.no_thanks");
        I_add_product_to_my_bag_from_standard_PDP_Page_using_mobile_site();
        if (onPage("add_to_bag")) {
            Clicks.clickWhenPresent("add_to_bag.checkout");
            shouldBeOnPage("shopping_bag");
        }
    }

    /**
     * Adds product to bag from current PDP
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add product to my bag from standard PDP Page using mobile site$")
    public void I_add_product_to_my_bag_from_standard_PDP_Page_using_mobile_site() throws Throwable {
        Clicks.clickIfPresent("product_display.close_app_banner");
        pausePageHangWatchDog();
        for (int count = 0; count < 5; count++) {
            try {
                if (bloomingdales() && ProductDisplay.isMasterMemberPage()) {
                    Clicks.clickRandomElement("product_display_master.choose_member_items", WebElement::isDisplayed);
                }
                // scrollToLazyLoadElement("product_display.add_to_bag_button");
                com.macys.sdt.shared.actions.MEW.pages.ProductDisplay.selectRandomColor();
                com.macys.sdt.shared.actions.MEW.pages.ProductDisplay.selectRandomSize();
                // scrollToLazyLoadElement("product_display.add_to_bag_button");
                if (ProductDisplay.isMasterMemberPage()) {
                    Clicks.clickRandomElement("product_display.add_to_bag_button", WebElement::isDisplayed);
                } else {
                    //There are two element with same id. One for tablet and another for mobile. so had to filter out the displayed element and click in below step
                    Elements.elementInView("product_display.add_to_bag_button");
                    Clicks.javascriptClick("product_display.add_to_bag_button");
                    Wait.secondsUntilElementNotPresent("product_display.add_to_bag_button", 5);
                    if (Elements.findElements("product_display.error_tooltip", WebElement::isDisplayed).size() > 0){
                        Navigate.browserRefresh();
                        continue;
                    }
                    if(macys() && !Wait.until(() -> onPage("add_to_bag"))){
                        Wait.untilElementPresent("home.my_bag");
                        Clicks.click("home.my_bag");
                        break;
                    }
                }
                Clicks.clickIfPresent("product_display.technical_error");

                if (isErrorPaneVisible()) {
                    Clicks.click("home.popup_close");
                }
                Wait.untilElementPresent("add_to_bag.checkout");
                if (ProductDisplay.addedToBag()) {
                    break;
                }
            } catch (IllegalArgumentException | NoSuchElementException e) {
                if (RunConfig.debugMode) {
                    e.printStackTrace();
                }
                logger.info("Failed to add product to bag, trying again. " + (4 - count) + " tries remaining.");
            } catch (Exception e) {
                if (RunConfig.debugMode) {
                    e.printStackTrace();
                }
            }
            Navigate.browserRefresh();
            closePopup();
        }
        if (!ProductDisplay.addedToBag()) {
            Assert.fail("Unable to add product to bag");
        }
        resumePageHangWatchDog();
    }

    /**
     * Clicks on share button on PDP
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select share button on pdp page using mobile website$")
    public void I_select_share_button_on_pdp_page_using_mobile_website() throws Throwable {
        Assert.assertTrue("ERROR-ENV: Share button is not visible", Wait.untilElementPresent("product_display.share_button"));
        Clicks.click("product_display.share_button");
    }

    /**
     * Selects the given social icon on PDP
     *
     * @param social_icon "facebook", "twitter", "pinterest", "email" or "google_plus"
     * @throws Throwable if any exception occurs
     */
    @And("^I select \"([^\"]*)\" social icon on PDP Page using mobile website$")
    public void I_select_social_icon_on_PDP_Page_using_mobile_website(String social_icon) throws Throwable {
        if (Elements.elementPresent("product_display.share_button")) {
            Elements.elementInView("product_display.share_button");
            Clicks.click("product_display.share_button");
            Wait.forPageReady();
        }
        switch (social_icon) {
            case "facebook":
                if (!Clicks.clickIfPresent("product_display.social_icon_facebook")) {
                    Assert.fail(social_icon + " is not Available");
                }
                break;
            case "twitter":
                if (!Clicks.clickIfPresent("product_display.social_icon_twitter")) {
                    Assert.fail(social_icon + " is not Available");
                }
                break;
            case "pinterest":
                Elements.elementInView("product_display.social_icon_pinterest");
                if (!Clicks.clickIfPresent("product_display.social_icon_pinterest")) {
                    Assert.fail(social_icon + " is not Available");
                }
                break;
            case "email":
                if (!Clicks.clickIfPresent("product_display.social_icon_email")) {
                    Assert.fail(social_icon + " is not Available");
                }
                break;
            case "google_plus":
                if (!Clicks.clickIfPresent("product_display.social_icon_google_plus")) {
                    Assert.fail(social_icon + " is not Available");
                }
        }

        if (ie() && WebDriverManager.getWebDriver().getTitle().contains("Certificate Error:")) {
            Set<String> windowSet = WebDriverManager.getWebDriver().getWindowHandles();
            Assert.assertTrue("The popup window has not opened.", windowSet.size() > 1);
            Navigate.switchWindow(1);
            Navigate.execJavascript("document.getElementById('overridelink').click();");
        }
        Clicks.clickIfPresent("product_display.social_close");
    }

    /**
     * Verifies the expected title of a popup window for social icons
     *
     * @param social_icon_pop_up "Facebook", "twitter", "pinterest", "emailemail" or "google_plus"
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see \"([^\"]*)\" title in the popup window using mobile website$")
    public void I_should_see_title_in_the_popup_window_using_mobile_website(String social_icon_pop_up) throws Throwable {
        int window_index = 0;
        switch (social_icon_pop_up) {
            case "Facebook":
                window_index = Navigate.findIndexOfWindow(social_icon_pop_up);
                break;
            case "twitter":
                window_index = Navigate.findIndexOfWindow("Share a link on Twitter");
                break;
            case "pinterest":
                window_index = Navigate.findIndexOfWindow("Pinterest");
                break;
            case "email":
                window_index = Elements.elementPresent("product_display.email_panel") ? 1 : 0;
                break;
            case "google_plus":
                window_index = Navigate.findIndexOfWindow("Google+");
                break;
        }
        if (Arrays.asList("Facebook", "twitter", "pinterest", "google_plus").contains(social_icon_pop_up)){
            Navigate.switchWindow(1);
            Navigate.switchWindowClose();
        }
        if (window_index <= 0) {
            // email may come up in desktop client, which we can't detect automatically.
            if (social_icon_pop_up.equals("email")) {
                logger.info("email is not displayed. Check manually for desktop client.");
            } else {
                Assert.fail(social_icon_pop_up + " is not displayed");
            }
        }
    }

    /**
     * Searches nearby stores for stock of the current product
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I search for a product in a nearby store using mobile website$")
    public void I_search_for_a_product_in_a_nearby_store_using_mobile_website() throws Throwable {
        Assert.assertTrue("ERROR-ENV: Unable to locate product in store", Elements.elementPresent("product_display.find_store_link"));
        Clicks.click("product_display.find_store_link");
        Assert.assertTrue("ERROR-ENV: Find in store overlay is not visible ", Elements.elementPresent("product_display.find_in_store_overlay"));
        if (macys()) {
            DropDowns.selectByText("change_pickup_store.search_distance", "Show stores within 100 miles");
        }

        TextBoxes.typeTextbox("change_pickup_store.address_zip_code", "22102");
        Clicks.click("change_pickup_store.search_button");
    }

    /**
     * Verifies the member products are displayed on master PDP
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I should see member products listed in mobile website$")
    public void I_should_see_member_products_listed_in_mobile_website() throws Throwable {
        Assert.assertTrue("Member items not visible", Elements.anyPresent("product_display_master.member_prod_list"));
    }

    /**
     * Adds random member product to bag from master PDP and selects the opted button
     *
     * @param option "checkout" or "continue shopping"
     * @throws Throwable if any exception occurs
     */
    @And("^I add member product from PDP and select \"([^\"]*)\" using mobile website$")
    public void I_add_member_product_from_PDP_and_select_using_mobile_website(String option) throws Throwable {
        new ProductDisplay().addRandomMemberProductOnMasterPDP();
        Wait.secondsUntilElementPresent("add_to_bag.checkout", 25);
        if (option.equalsIgnoreCase("checkout")) {
            Clicks.click("add_to_bag.checkout");
            shouldBeOnPage("shopping_bag");
        } else if (option.equalsIgnoreCase("continue shopping")) {
            Clicks.click("add_to_bag.continue");
            shouldBeOnPage("product_display_master");
        }
    }

    /**
     * Selects random alternative image on PDP
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select a random alternative image using mobile website$")
    public void I_select_a_random_alternative_image_using_mobile_website() throws Throwable {
        Assert.assertTrue("ERROR-DATA: Alternative images are not visible under the selected product", Wait.untilElementPresent("product_display.alt_images"));
        Clicks.clickRandomElement("product_display.alt_images"); //Should checkout selected alternate product
    }

    /**
     * Clicks on "Add to Wish List button" on PDP and waits for wish list overlay
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click Add to Wish List button on PDP using mobile website$")
    public void I_click_add_to_Wish_List_button_on_PDP_using_mobile_website() throws Throwable {
        Wait.forPageReady();
        Elements.elementInView("product_display.add_to_wishlist");
        //Clicks.clickWhenPresent("product_display.add_to_wishlist");
        Clicks.javascriptClick("product_display.add_to_wishlist");
        Wait.secondsUntilElementPresent("product_display.wish_list_overlay", 20);
        Elements.elementShouldBePresent("product_display.wish_list_overlay");
    }

    /**
     * Clicks on "view list" on Add to Wish List overlay
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click on view list in ATW overlay from PDP using mobile website$")
    public void I_click_on_view_list_in_ATW_overlay_from_PDP_using_mobile_website() throws Throwable {
        Wait.secondsUntilElementPresent("product_display.view_list", 10);
        Clicks.click("product_display.view_list");
        if (onPage("wish_list_home")){
            Clicks.click("wish_list_home.goto_list");
            Wait.forPageReady();
        }
        shouldBeOnPage("wish_list");
    }

    /**
     * Selects size and color on current PDP page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select product related attributes from PDP using mobile website$")
    public void I_select_product_related_attributes_from_PDP_using_mobile_website() throws Throwable {
        com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay.selectRandomColor();
        com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay.selectRandomSize();
    }

    /**
     * Adds a product with the given attributes to bag from BVR page and selects checkout if elected
     *
     * @param productTrue  Properties expected to be true separated by either a space or the word "and"
     * @param productFalse Properties expected to be false separated by either a space or the word "and"
     * @param checkout     present if you want to end up on shopping bag page
     * @throws Throwable if any exception occurs
     */
    @When("^^I add \"([^\"]*)\" product to my bag(?: that is not(?: an?)? \"(.*?)\")? from BVR page using mobile website(?: and \"?(.*?)\"? ?checkout)?$")
    public void I_add_a_product_to_registry_using_mobile_website(String productTrue, String productFalse, String checkout) throws Throwable {
        if (prodEnv()) {
            CommonUtils.navigateToRandomProduct(productTrue, productFalse);
        } else {
            //All the none production related promotional products need to be get from siteDB with their respective attributes
            switch (productTrue) {
                case "orderable and promo_code_eligible":
                    String[] productsWithPromoCode = new PromotionService().getPromoCodeForPromotionalProducts();
                    if (productsWithPromoCode == null) {
                        Assert.fail("ERROR-DATA: Promo-Code eligible promotions are not available in database!!");
                    }
                    mew_promotional_prod_id = productsWithPromoCode[1];
                    mew_promotion_code = productsWithPromoCode[0];
                    CommonUtils.navigateDirectlyToProduct(mew_promotional_prod_id);
                    break;
                case "orderable and pwp":
                    //TODO
                    break;
                default:
                    CommonUtils.navigateToRandomProduct(productTrue, productFalse);
            }
        }
        I_add_product_to_my_registry_from_standard_PDP_Page_using_mobile_site();
//        Assert.assertTrue("ERROR - ENV: Add to registry overlay not displayed", Wait.untilElementPresent("add_to_registry_overlay.add_to_registry_overlay"));
//        Clicks.click("add_to_registry_overlay.view_registry");
//        if (!Wait.until(()->onPage("registry_bvr")))
//            Navigate.browserRefresh();
        shouldBeOnPage("registry_bvr");
        if (bloomingdales()) {
//            Clicks.click("registry_bvr.category_header");
            Wait.untilElementPresentWithRefresh(Elements.element("registry_bvr.quantity"));
            DropDowns.selectByValue("registry_bvr.quantity", "1");
            Wait.untilElementPresentWithRefresh(Elements.element("registry_bvr.add_to_bag_btn"));
        }
        Wait.secondsUntilElementPresentAndClick("registry_bvr.add_to_bag_btn", 10);
        if (checkout != null) {
            Wait.secondsUntilElementPresent("registry_add_to_bag.registry_chkout_button", 10);
            Clicks.click("registry_add_to_bag.registry_chkout_button");
            shouldBeOnPage("shopping_bag");

        }
    }

    /**
     * Adds a product to registry from current PDP
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add product to my registry from standard PDP Page using mobile site$")
    public void I_add_product_to_my_registry_from_standard_PDP_Page_using_mobile_site() throws Throwable {
        try {
            //            for (int count = 0; count < 5; count++) {
            ProductDisplay.selectRandomColor();
            ProductDisplay.selectRandomSize();
            if (ProductDisplay.isMasterMemberPage()) {
                Clicks.clickRandomElement("product_display.add_to_registry");
            } else {
                Clicks.click("product_display.add_to_registry");
            }
            Clicks.clickIfPresent("product_display.technical_error");
            if (isErrorPaneVisible()) {
                Clicks.click("home.popup_close");
            }
            Wait.secondsUntilElementPresentAndClick("add_to_registry_overlay.view_registry", 10);
            //            }
            Wait.forPageReady();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            Assert.fail("Unable to add product to registry.");
        }
    }

    /**
     * Clicks on "continue shopping" button on add to bag page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I click on the continue shopping button from ATB page using mobile website$")
    public void iClickOnTheContinueShoppingButtonFromATBPageUsingMobileWebsite() throws Throwable {
        shouldBeOnPage("add_to_bag");
        Wait.secondsUntilElementPresent("add_to_bag.continue_shopping", 25);
        Clicks.click("add_to_bag.continue_shopping");
    }

    /**
     * Navigates directly to given product PDP from current PDP
     *
     * @param productID target product ID
     * @throws Throwable if any exception occurs
     */
    @When("^I replace product ID with available \"([^\"]*)\" product ID using mobile website$")
    public void iReplaceProductIDWithAvailableProductID(int productID) throws Throwable {
        shouldBeOnPage("product_display");
        try {
            String url = WebDriverManager.getCurrentUrl();
            String pattern = "ID=(.*)&";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(url);
            m.find();
            String new_url = url.replace(m.group(), "ID=" + productID + "&");
            Navigate.visit(new_url);
        } catch (Exception e) {
            Assert.fail("ERROR - DATA: Unable to append predefined prodID to URL" + e);
        }
    }

    @And("^I enter gift card amount and recipient email on PDP page$")
    public void iEnterGiftCardAmountAndRecipientEmailOnPDPPage() throws Throwable {
        shouldBeOnPage("product_display");
        TextBoxes.typeTextbox("product_display.gift_card_amount", "10.00");
        TextBoxes.typeTextbox("product_display.gift_card_recipient_email", TestUsers.generateRandomEmail(10));
    }

    @And("^I verify image can be zoomed on PDP using mobile website$")
    public void iVerifyImageCanBeZoomedOnPDPUsingMobileWebsite() throws Throwable {
        Clicks.clickIfPresent("home.close_app_banner");
        if (iOS()) {
            Clicks.click("product_display.product_image");
        } else {
            // For Android
            Clicks.javascriptClick("product_display.product_image");
        }
        Elements.elementShouldBePresent("product_display.zoomed_image");
        AppiumDriver appiumDriver = WebDriverManager.getAppiumDriver();
        // Double Tap not working in iOS with current version of Appium
        if (appiumDriver != null && !iOS()) {
            String cssValueBeforeTap = Elements.findElement("product_display.zoomed_image").getCssValue("transform");
            String originalContext = appiumDriver.getContext();
            appiumDriver.context("NATIVE_APP");
            int x = appiumDriver.manage().window().getSize().getWidth()/3;
            int y = appiumDriver.manage().window().getSize().getHeight()/3;
            new TouchAction(appiumDriver).tap(x, y).tap(x, y).perform();
            appiumDriver.context(originalContext);
            Assert.assertNotEquals("Product image is not zoomed on PDP page after double tap", cssValueBeforeTap, Elements.findElement("product_display.zoomed_image").getCssValue("transform"));
        }
    }

    @And("^I should be on PDP page$")
    public void iShouldBeOnPDPPage() throws Throwable {
        shouldBeOnPage("product_display");
        logger.info("Verified PDP page initialization");
    }

    @And("^I should be on registry PDP page$")
    public void iShouldBeOnRegistryPDPPage() throws Throwable {
        shouldBeOnPage("registry_pdp");
        logger.info("Verified PDP page initialization");
    }

    /**
     * Method to navigate on first product
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate PDP of the first product$")
    public void iNavigatePDPOfTheFirstProduct() throws Throwable {
        Wait.untilElementPresent("search_result.first_product");
        Clicks.click("search_result.first_product");
        logger.info("Verified that the user clicks on first product");
    }
}
