package com.macys.sdt.projects.Architecture.MSH.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.akamai.AkamaiUtils;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.steps.website.*;
import com.macys.sdt.shared.utils.CommonUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.runner.RunConfig;
import com.thoughtworks.selenium.webdriven.commands.Click;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;
import java.lang.reflect.Method;
import static com.sun.jna.platform.win32.Netapi32Util.getDomainName;
import java.lang.reflect.*;
import static com.macys.sdt.framework.interactions.Elements.getText;

public class MSHGslb extends StepUtils {


    private static JSONObject akamaiJSON;
    private static String envName;
    private PageNavigation pageNavigation = new PageNavigation();
    private Registry registry = new Registry();
    private List<String> productIds;
    public static int productCount;
    private String debugValue = LocalTime.now().toString().replace(":","").replace(".","");
    private String userEmail = "mshgslb@test.com";
    private String userPassword = "Msh@1234";
    public static String facetName;
    public static String selectedFacetItem;
    public static WebElement facetItem;
    private static final Logger logger = LoggerFactory.getLogger(MSHGslb.class);

    @Then("^I verify that the X-Cache key value as \"([^\"]*)\" for \"([^\"]*)\" request$")
    public void iVerifyThatTheXCacheKeyValueAsForRequest(String tcp_value, String path) throws Throwable {

        List<HashMap<String, String>> allHeaders = new ArrayList<>();
        AkamaiUtils ret = new AkamaiUtils();
        Class<?> clazz = ret.getClass();
        Method method = clazz.getDeclaredMethod("getHeaders");
        method.setAccessible(true);
        allHeaders= (List<HashMap<String, String>>)method.invoke(ret);
        String xcache= allHeaders.stream().filter(e -> e.get("url").contains(path)).findFirst().get().get("cache_state");
        if (tcp_value.equals("TCP_MISS")){
            Assert.assertTrue("xache value is NOT TCP_MISS ",xcache.equals(tcp_value));
        }
        else if (tcp_value.equals("TCP_HIT")){
            Assert.assertTrue("xache value is NOT TCP_HIT ",(xcache.equals(tcp_value))||(xcache.equals("TCP_MEM_HIT")));
        }
    }

    @And("^I select \"([^\"]*)\" in header$")
    public void iSelectInHeader(String link) throws Throwable {

       switch (link) {
            case "My Account":
                Clicks.click("home.goto_guest_my_account");
                break;
            case "My Lists":
                Clicks.click("home.lists_header");
                break;
            case "My Order History":
               Clicks.click("footer.goto_order_status");
               break;
            case "My Macy's Credit Card":
                Clicks.click("footer.goto_credit_services");
               break;
           case "registry_PDP":
               Home.selectRandomCategory();
               CommonUtils.navigateToRandomProduct();
               break;
           case "registry_catsplash":
               Home.selectRandomCategory();
               break;
        }
    }

    @And("^I update dca cookie value to (DAL|RTP)")
    public void setDcaValueToRTP(String dcaValue) throws JSONException, IOException {
        String cookieValue = Cookies.getCookieValue("dca");
        if(cookieValue.equals("")) {
            Cookies.addCookie("dca", dcaValue);
        }
        else if (!cookieValue.equals(dcaValue)) {
            Cookies.editCookie("dca", cookieValue, dcaValue);
        }
    }
    @And("^I verify the page served from \"([^\"]*)\" data center$")
    public void iVerifyThePageServedFromDataCenter(String dcaValue) throws Throwable {
        Wait.isPageLoaded();
        String cookieValue = Cookies.getCookieValue("dca");
        if (dcaValue.equals("DAL")) {
            Assert.assertTrue("ERROR - APP: Page is not served from DAL DC", cookieValue.equals("DAL"));
        } else {
            Assert.assertTrue("ERROR - APP: Page is not served from RTP DC", cookieValue.equals("RTP"));
        }
    }

    @When("^I navigate to Personalized Gift Cards splash page$")
    public void iNavigateToPersonalizedGiftCardsSplashPage() throws Throwable {
        if (macys() && Elements.elementPresent("header.gift_dropdown"))
            Clicks.hoverForSelection("header.gift_dropdown");
        Clicks.click("header.goto_gift_cards");
        Clicks.click("category_splash.shop_by_cat_gift_card_personalize");
    }
	@When("^I click on estimated Shipping link from the Order Summary section of shopping bag page$")
    public void iClickOnEstimatedShippingLinkOnShoppingBagPage() throws Throwable {
        scrollToLazyLoadElement("shopping_bag.estimated_shipping_link");
        Clicks.clickWhenPresent("shopping_bag.estimated_shipping_link");
        Utils.threadSleep(5000, null);
        Navigate.switchWindow(1);
        Clicks.click("estimation_shipping.close_window");
    }
    @Then("^I should see quick bag overlay$")
    public void i_should_see_quick_bag_overlay() {
        Wait.untilElementPresent("home.quickbag_overlay");
        Assert.assertTrue(Elements.elementInView("home.quickbag_overlay"));
    }

    @Then("^I should see \"([^\"]*)\" and X button is displayed in quick bag$")
    public void i_should_see_quickbag_contents(String bagText) {
        Assert.assertTrue(Elements.findElement("header.empty_quickbag").isDisplayed());
        Assert.assertEquals(bagText, Elements.findElement("header.empty_quickbag").getText());
        Assert.assertTrue(Elements.findElement(By.className("container-close")).isDisplayed());
    }
    @When("^I navigate Beauty catsplash page and click on freeshipping image$")
    public void iNavigateToFreeShippingImageOnBeautyCatsplashPage() throws Throwable {
        Navigate.visit(RunConfig.url + "/shop/popup?");
        logger.info("navigate Beauty catsplash page and click on freeshipping image");
    }
    @When("^I navigate shopping bag page page and click on bonus offer product$")
    public void iNavigateToShoppingBagPageAndClickOnBonusOfferProduct() throws Throwable {
        Navigate.visit(RunConfig.url + "/shop/catalog/product/gift");
        logger.info("Navigated to registry claim page");
    }
    @When("^I navigate PDP page and click on email icon$")
    public void iNavigateToPDPPageAndClickOnEmailIcon() throws Throwable {
        Navigate.visit(RunConfig.url + "/shop/catalog/product/email");
        logger.info("Navigated to registry claim page");
    }
    @When("^I navigate recently viewed section$")
    public void iNavigateToRecentlyViewedSection() throws Throwable {
        Navigate.visit(RunConfig.url + "/shop/catalog/product/recentlyViewed");
        logger.info("Navigated to registry claim page");
    }
    @Then("^click on save changes button$")
    public void clickOnSaveChangesButton() throws Throwable {
        Clicks.click("had.save_changes");

    }

    @Then("^verify that Bloomingdales logo takes to Home Page$")
    public void verifyThatBloomingdalesLogoTakesToHomePage() throws Throwable {
        if (WebDriverManager.getCurrentUrl().contains("registry"))
            Clicks.click("header.goto_back_to_bloomingdales");
        else {
            Elements.elementShouldBePresent("home.verify_page");
            Clicks.click("home.verify_page");
        }
        Assert.assertTrue(WebDriverManager.getCurrentTitle().equals("Bloomingdale's Official Site - Shop For Designer Clothing & Accessories"));
    }
    @And("^I update email with already exist mail Id$")
    public void iUpdateEmailWithAlreadyExistMailId(Map<String, String> error) throws Throwable {
        String expectedError = error.get("error_email");
        UserProfile user = new UserProfile();
        user = UserProfile.getDefaultProfile();
        UserProfileService.createUserProfile(user);
        TextBoxes.typeTextbox("responsive_checkout.create_profile_email", user.getUser().getProfileAddress().getEmail());
        if(Elements.elementPresent("responsive_checkout.create_profile_checkbox")) {
            Clicks.selectCheckbox(Elements.element("responsive_checkout.create_profile_checkbox"));
            Wait.secondsUntilElementPresent("responsive_checkout.create_profile_email_exist_error", 20);
            Assert.assertTrue("Failed to validate email already exist inline error in guest checkout page", Elements.elementPresent("responsive_checkout.create_profile_email_exist_error"));
            String strPwdError = getText("responsive_checkout.create_profile_email_exist_error");
            Assert.assertEquals("Failed to validate email already exist inline error msg in guest checkout page", strPwdError, expectedError);
        }else
            logger.info("Failed to find create profile checkbox: responsive_checkout.create_profile_checkbox");
    }
	@When("^I navigate directly to /catalog/product/email/index.ognc URI$")
    public void iNavigateToDirectlyEmailIndexURI() throws Throwable {
        String value = WebDriverManager.getCurrentUrl().contains("?") ?  "&debug=" : "?&debug=";
        Navigate.visit(RunConfig.url + "/catalog/product/email/index.ognc" + value +debugValue);
        logger.info("Navigated to directly to email index.ognc");
    }
    @When("^I navigate directly to Price Data URI$")
    public void iNavigateToDirectlyPriceDataURI() throws Throwable {
        String value = WebDriverManager.getCurrentUrl().contains("?") ?  "&debug=" : "?&debug=";
        Navigate.visit(RunConfig.url + "/shop/international/priceData.js" + value +debugValue);
        logger.info("Navigated to directly to Price data URL");
    }
}