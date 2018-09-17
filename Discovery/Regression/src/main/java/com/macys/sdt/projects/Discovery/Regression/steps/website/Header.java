package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.HeaderActions;
import com.macys.sdt.shared.steps.website.Registry;
import com.macys.sdt.projects.Discovery.Regression.utils.config.website.GeneralUtils;
import com.macys.sdt.projects.Discovery.Regression.utils.config.website.QuickbagProduct;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.registry.CreateRegistry;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategorySplash;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.*;
import static com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper.logInfo;
import static com.macys.sdt.shared.actions.website.mcom.pages.home.Home.selectRandomCategory;
import static com.macys.sdt.shared.actions.website.mcom.pages.home.Home.selectRandomSubCategory;

public class Header {

    ListMultimap<String, String> product_details = ArrayListMultimap.create();
    String[] media_name = new String[5];
    String[] width = new String[5];
    String[] height = new String[5];
    String[] file_name = new String[5];
    PageNavigation page = new PageNavigation();
    List<QuickbagProduct> quickbagProducts = new ArrayList<>();
    Map<String, List<String>> fobDetails = new HashMap();
    Map<String, List<String>> subNavHeaders = new HashMap();
    int gna_home_count;
    private CategorySplash categorySplash = new CategorySplash();
    public static Product recentProduct;
    public static String sitemode;
    private static String searchKeyword;
    private static String shoppingMode;
    private String ISHIPCountry = "Canada";
    private String ISHIPCountryCode = "CA";
    private String randomFob;
    private String selectedBrandChr;
    String url = "";
    String title = "";
    Map<Integer, Map<String, String>> bagContents = new HashedMap();
    Map<String, String> productDetails = new HashedMap();
    private static final Logger log = LoggerFactory.getLogger(Header.class);
    private List<String> gnaMediaLinks = new ArrayList();

    @Given("^I visit the web site as a \"([^\"]*)\" user in \"([^\"]*)\" mode$")
    public void i_visit_the_web_site_as_a_user_in_mode(String user_type, String mode) throws Throwable {
        Registry registryPage = new Registry();
        switch (user_type) {
            case "guest":
                switch (mode) {
                    case "site":
                        page.I_visit_the_web_site_as_a_guest_user();
                        break;
                    case "iship":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        break;
                    case "registry":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        break;
                }
                break;
            case "Registered":
            case "registered":
                switch (mode) {
                    case "Domestic":
                    case "site":
                        page.I_visit_the_web_site_as_a_registered_user();
                        break;
                    case "registry":
                        registryPage.I_visit_the_web_site_as_a_registry_user();
                        break;
                }
        }
        product_details.put("mode", mode);
    }


    @Then("^I compare the details of \"([^\"]*)\" in UI with response$")
    public void i_compare_the_details_of_in_UI_with_response(String subNavPoolName) throws Throwable {
        String ipAddress = EnvironmentDetails.otherApp("f5_vip").ipAddress;
        String SubNavResponse = GeneralUtils.getHeaderResponse(ipAddress, subNavPoolName);
        JSONObject jsonSubNavResponse = new JSONObject(SubNavResponse);
        JSONObject subNavContentDetails = jsonSubNavResponse.getJSONObject("globalPools").getJSONArray("globalPool").getJSONObject(0).getJSONArray("contents").getJSONObject(0);
        if (subNavPoolName.equalsIgnoreCase("Seasonal_Promotion")) {
            subNavContentDetails.get("text").equals(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[2]/a")).findElement(By.tagName("img")).getAttribute("alt"));
            subNavContentDetails.get("width").toString().equals(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[2]/a")).findElement(By.tagName("img")).getAttribute("width"));
            subNavContentDetails.get("height").toString().equals(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[2]/a")).findElement(By.tagName("img")).getAttribute("height"));
            subNavContentDetails.get("fileName").equals(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[2]/a")).findElement(By.tagName("img")).getAttribute("src").split("/site_ads/")[1]);
        } else {
            subNavContentDetails.get("text").equals(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[3]/a")).findElement(By.tagName("img")).getAttribute("alt"));
            subNavContentDetails.get("width").toString().equals(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[3]/a")).findElement(By.tagName("img")).getAttribute("width"));
            subNavContentDetails.get("height").toString().equals(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[3]/a")).findElement(By.tagName("img")).getAttribute("height"));
            subNavContentDetails.get("fileName").equals(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[3]/a")).findElement(By.tagName("img")).getAttribute("src").split("/site_ads/")[1]);
        }
    }

    @Then("^I should not see seasonal promotion and gift guide in registry$")
    public void i_should_not_see_seasonal_promotion_and_gift_guide_in_registry() throws Throwable {
        Assert.assertSame(Elements.findElement("header.seasonal_promotion"), null);
        Assert.assertSame(Elements.findElement("header.gift_guide"), null);
    }

    @Then("^I navigate to random browse page$")
    public void i_navigate_to_random_browse_page() throws Throwable {
        Home.selectRandomSubCategory();
        System.out.println("Naviagted to random category page");
    }

    @When("^I click on global media link from top nav$")
    public void i_click_on_global_media_link_from_top_nav() throws Throwable {
        if (macys()) {
            List<WebElement> gnaMediaList = Elements.findElement("header.header_pool_media_section").findElements(By.tagName("a"));
            for (int i = 0; i < gnaMediaList.size(); i++) {
                String gnaLinkURL = gnaMediaList.get(i).getAttribute("href");
                if (gnaLinkURL.contains("javascript:pop")) {
                    gnaLinkURL = gnaLinkURL.split("pop")[1].split("','")[0].replace("('", "");
                }
                gnaMediaLinks.add(gnaLinkURL);
            }
            int index = gnaMediaList.size() == 1 ? 0 : new Random().nextInt(gnaMediaList.size() - 1);
            Clicks.click(gnaMediaList.get(index));
            Thread.sleep(1000);
        } else {
            width[0] = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("width");
            height[0] = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("height");
            file_name[0] = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("src").split("_pools/")[1];
            Thread.sleep(1000);
            Elements.findElement("header.nav_banner").findElement(By.tagName("img")).click();
        }
    }

    @Then("^I should be navigated to media page in \"([^\"]*)\" mode$")
    public void i_should_be_navigated_to_media_page_in_mode(String mode) throws Throwable {
        if (macys()) {
            for (String winHandle : WebDriverManager.getWebDriver().getWindowHandles()) {
                WebDriverManager.getWebDriver().switchTo().window(winHandle);
            }
            Assert.assertFalse("ERROR - APP: GNA media selection is not navigated to expected URL's" + gnaMediaLinks,
                    gnaMediaLinks.stream().filter(f -> WebDriverManager.getCurrentUrl().contains(f)).collect(Collectors.toList()).isEmpty());
        }
    }

    @Then("^I compare the details of global media in UI with response in \"([^\"]*)\" mode$")
    public void i_compare_the_details_of_global_media_in_UI_with_response_in_mode(String mode) throws Throwable {
        String ipAddress = EnvironmentDetails.otherApp("f5_vip").ipAddress;
        String GlobalMediaResponse;
        if (mode.equalsIgnoreCase("iship") && macys()) {
            GlobalMediaResponse = GeneralUtils.getHeaderResponse(ipAddress, "INTL_GNA");
        } else if (mode.equalsIgnoreCase("iship") && bloomingdales()) {
            GlobalMediaResponse = GeneralUtils.getHeaderResponse(ipAddress, "INTL_HEADER_GNA");
        } else if (mode.equalsIgnoreCase("site")) {
            GlobalMediaResponse = GeneralUtils.getHeaderResponse(ipAddress, "ABOVE_NAV_POOL");
        } else {
            GlobalMediaResponse = GeneralUtils.getHeaderResponse(ipAddress, "REG_W_ABOVE_NAV_POOL");
        }
        JSONObject jsonGlobalMedia = new JSONObject(GlobalMediaResponse);
        JSONArray contents = jsonGlobalMedia.getJSONObject("globalPools").getJSONArray("globalPool").getJSONObject(0).getJSONArray("contents");
        if (macys()) {
            for (int i = 0; i < (Elements.findElements(By.className("macysGlobalNavAdLink")).size()); i++) {
                media_name[i] = Elements.findElements(By.className("macysGlobalNavAdLink")).get(i).findElement(By.tagName("img")).getAttribute("alt");
                width[i] = Elements.findElements(By.className("macysGlobalNavAdLink")).get(i).findElement(By.tagName("img")).getAttribute("width");
                height[i] = Elements.findElements(By.className("macysGlobalNavAdLink")).get(i).findElement(By.tagName("img")).getAttribute("height");
                file_name[i] = Elements.findElements(By.className("macysGlobalNavAdLink")).get(i).findElement(By.tagName("img")).getAttribute("src").split("site_ads/")[1];
                Assert.assertSame(contents.getJSONObject(i).get("fileName"), file_name[i]);
                Assert.assertSame(contents.getJSONObject(i).get("width"), width[i]);
                Assert.assertSame(contents.getJSONObject(i).get("height"), height[i]);
                Assert.assertSame(contents.getJSONObject(i).get("text"), media_name[i]);
            }
        } else {
            String width = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("width");
            String height = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("height");
            String file_name = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("src").split("_pools/")[1];
            Assert.assertSame(contents.getJSONObject(0).get("fileName"), file_name);
            Assert.assertSame(contents.getJSONObject(0).get("width"), width);
            Assert.assertSame(contents.getJSONObject(0).get("height"), height);
        }
    }

    @Then("^I verify the TopNav elements are present in the UI for \"([^\"]*)\" user in \"([^\"]*)\" mode$")
    public void i_verify_the_TopNav_elements_are_present_in_the_UI_for_user_in_mode(String user_type, String mode) throws Throwable {
        switch (user_type) {
            case "guest":
                switch (mode) {
                    case "site":
                    case "registry":
                        Elements.elementShouldBePresent("header.goto_sign_in_link");
                        Elements.elementShouldBePresent("header.goto_my_account_link");
                        Elements.elementShouldBePresent("header.goto_stores");
                        break;
                    case "iship":
                        Elements.elementShouldBePresent("header.goto_stores");
                        Elements.elementShouldBePresent("header.goto_customer_service");
                        Elements.elementShouldBePresent("header.shipping_to_link");
                        break;
                }
                break;
            case "registered":
                switch (mode) {
                    case "site":
                    case "registry":
                        Elements.elementShouldBePresent("header.goto_sign_out_link");
                        Elements.elementShouldBePresent("header.goto_my_account_link");
                        Elements.elementShouldBePresent("header.goto_stores");
                        break;
                }
        }
    }

    @When("^I hover over the myaccount link from topNav header$")
    public void i_hover_over_the_myaccount_link_from_topNav_header() throws Throwable {
        if (product_details.get("mode").contains("iship")) {
            Assert.assertNull(Elements.findElement("header.goto_my_account_link"));
        } else {
            GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
        }
    }

    @Then("^I verify the myaccount sublinks from topNav header$")
    public void i_verify_the_myaccount_sublinks_from_topNav_header() throws Throwable {
        if (!product_details.get("mode").contains("iship"))
            Elements.elementShouldBePresent("navigation.goto_my_credit_card");
        Elements.elementShouldBePresent("navigation.goto_order_status");
        Elements.elementShouldBePresent("navigation.goto_my_profile");
        Elements.elementShouldBePresent("navigation.goto_my_wallet_link");
        Elements.elementShouldBePresent("navigation.goto_my_plenti");
        Elements.elementShouldBePresent("navigation.goto_my_wish_list");
    }

    @Given("^I navigate to random sublinks from my account as a \"([^\"]*)\" user in \"([^\"]*)\" mode$")
    public void i_navigate_to_random_sublinks_from_my_account_as_a_user_in_mode(String user_type, String mode) throws Throwable {
        if (!product_details.get("mode").contains("iship"))
            GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
        int random_sublink = (int) (Math.random() * Elements.findElement("header.my_account_menu").findElements(By.tagName("a")).size());
        if (user_type.equalsIgnoreCase("guest")) {
            String subNav_link = Elements.findElement("header.my_account_menu").findElements(By.tagName("a")).get(random_sublink).getText();
            if (subNav_link.equalsIgnoreCase("My Plenti")) {
                i_visit_the_web_site_as_a_user_in_mode("registered", "site");
            } else {
                Clicks.click(Elements.findElement("header.my_account_menu").findElements(By.tagName("a")).get(random_sublink));
            }
        } else {
            Clicks.click(Elements.findElement("header.my_account_menu").findElements(By.tagName("a")).get(random_sublink));
        }
    }

    @When("^I click \"([^\"]*)\" from topnav elements$")
    public void i_click_from_topnav_elements(String top_nav_element) throws Throwable {
        if (top_nav_element.equalsIgnoreCase("stores")) {
            Clicks.click("header.goto_stores");
        } else if (top_nav_element.equalsIgnoreCase("shipping country")) {
            Clicks.click("header.shipping_to_link");
        } else {
            Clicks.click(By.linkText("CUSTOMER SERVICE"));
        }
    }

    @When("^I navigate to home page$")
    public void i_navigate_to_home_page() throws Throwable {
        Navigate.visit("home");
    }

    @Then("^I verify the FORWARDPAGE_KEY is generated in the cookie$")
    public void i_verify_the_FORWARDPAGE_KEY_is_generated_in_the_cookie() throws Throwable {
        String forward_key = Cookies.getCookieValue("FORWARDPAGE_KEY");
        Assert.assertTrue(forward_key.contains(WebDriverManager.getCurrentUrl()));
        Assert.assertFalse(forward_key.isEmpty());
    }

    @Then("^I should be navigated back to my account page$")
    public void i_should_be_naviagted_back_to_my_account_page() throws Throwable {
        shouldBeOnPage("my_account");
    }

    @Given("^I verify the logo available in website$")
    public void i_verify_the_logo_available_in_website() throws Throwable {
        Assert.assertTrue(Elements.findElement("home.verify_page").isEnabled());
    }

    @Given("^I click on logo in \"([^\"]*)\" mode$")
    public void i_click_on_logo_in_mode(String arg1) throws Throwable {
        try {
            Clicks.click("header.logo");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Unable to click on Macy's home page logo");
        }
    }

    @When("^I enter \"([^\"]*)\" keyword in global search field$")
    public void i_enter_keyword_in_global_search_field(String search_keyword) throws Throwable {
        TextBoxes.typeTextbox("home.search_field", search_keyword);
    }

    @Then("^I should see autocomplete suggestions list is populated$")
    public void i_should_see_autocomplete_suggestions_list_is_populated() throws Throwable {
        Assert.assertFalse(Elements.findElement(By.id("ui-menu-item")).getText().isEmpty());
    }

    @Then("^I \"([^\"]*)\" autocomplete suggestions list$")
    public void i_autocomplete_suggestions_list(String action) throws Throwable {
        if (action.equalsIgnoreCase("should see"))
            Assert.assertNotNull(Elements.findElement("header.suggestions_list"));
        else
            Assert.assertNull(Elements.findElement("header.suggestions"));
    }

    @Given("^I \"([^\"]*)\" the default text displayed for \"([^\"]*)\" in global search field$")
    public void i_the_default_text_displayed_for_in_global_search_field(String choice, String user_type) throws Throwable {
        switch (user_type) {
            case "guest":
                if (choice.equalsIgnoreCase("should see")) {
                    Assert.assertTrue(Elements.findElement("header_and_footer.search_field").getAttribute("placeholder").equalsIgnoreCase("Search or enter web ID"));
                } else {
                    Assert.assertFalse(Elements.findElement("header_and_footer.search_field").getAttribute("value").equalsIgnoreCase("Search or enter web ID"));
                }
                break;
            case "registered":
                if (choice.equalsIgnoreCase("should see")) {
                    String user_name = Elements.findElement("header.user_first_name").getText();
                    if (user_name.toLowerCase().contains("welcome"))
                        user_name = user_name.split("Welcome, ")[1];
                    Assert.assertTrue(Elements.findElement("header_and_footer.search_field").getAttribute("placeholder").equalsIgnoreCase(user_name + "," + " Search or enter web ID"));
                } else {
                    Assert.assertFalse(Elements.findElement("header_and_footer.search_field").getAttribute("value").equalsIgnoreCase("Search or enter web ID"));
                }
        }
    }

    @Given("^I should see clear link in the global search field$")
    public void i_should_see_clear_link_in_the_global_search_field() throws Throwable {
        Elements.elementShouldBePresent(Elements.findElement(By.className("clearlink")));
    }

    @When("^I click any clear link icon$")
    public void i_click_any_clear_link_icon() throws Throwable {
        Clicks.click(By.className("clearlink"));
    }

    @Then("^I should see the search field is empty$")
    public void i_should_see_the_search_field_is_empty() throws Throwable {
        Assert.assertEquals(Elements.findElement("header_and_footer.search_field").getAttribute("value"), "");
    }

    @Given("^I navigate to search result page through \"([^\"]*)\" keyword$")
    public void i_navigate_to_search_result_page_through_keyword(String search_keyword) throws Throwable {
        ShopAndBrowse.I_search_for(search_keyword);
        onPage("search_result");
    }

    @Then("^I click on \"([^\"]*)\" link from subNav")
    public void i_click_on_link_from_subNav(String subNavPoolName) throws Throwable {
        WebDriverManager.getWebDriver().navigate().refresh();
        switch (subNavPoolName.toLowerCase()) {
            case "seasonal promotion":
                Elements.findElement("header.seasonal_promotion").click();
                break;
            case "gift guide":
                if (macys() && Elements.elementPresent("header.gift_dropdown"))
                    Clicks.hoverForSelection("header.gift_dropdown");
                Wait.untilElementPresent("header.goto_gift_cards");
                Elements.findElement("header.gift_guide").click();
                break;
            case "wishlist":
                Wait.untilElementPresent("header.goto_wishlist");
                Elements.findElement("header.goto_wishlist").click();
                break;
            case "deals & promotions":
                Elements.findElement("header.goto_deals_promotions").click();
                break;
            case "gift cards":
                if (macys() && Elements.elementPresent("header.gift_dropdown"))
                    Clicks.hoverForSelection("header.gift_dropdown");
                Wait.untilElementPresent("header.goto_gift_cards");
                Elements.findElement("header.goto_gift_cards").click();
                break;
            case "wedding registry":
                Wait.untilElementPresent("header.goto_wedding_registry");
                Elements.findElement("header.goto_wedding_registry").click();
                break;
            case "registry checklist":
                if (macys() && Elements.elementPresent("header.manage_registry_carat_symbol"))
                    Clicks.hoverForSelection("header.manage_registry_carat_symbol");
                Wait.untilElementPresent("header.goto_registry_checklist");
                Elements.findElement("header.goto_registry_checklist").click();
                break;
            case "view registry":
                if (macys() && Elements.elementPresent("header.manage_registry_carat_symbol"))
                    Clicks.hoverForSelection("header.manage_registry_carat_symbol");
                Wait.untilElementPresent("header.goto_view_registry");
                Elements.findElement("header.goto_view_registry").click();
                break;
            case "registry manager":
                if (macys() && Elements.elementPresent("header.manage_registry_carat_symbol"))
                    Clicks.hoverForSelection("header.manage_registry_carat_symbol");
                Wait.untilElementPresent("header.goto_registry_manager");
                Elements.findElement("header.goto_registry_manager").click();
                break;
            case "rewards program":
                GeneralUtils.getAllSubNavHeaders("registry").get(2).click();
                break;
            case "help":
                Wait.untilElementPresent("header.goto_help");
                Elements.findElement("header.goto_help").click();
                break;
            case "back to macys":
                GeneralUtils.moveMousePosition();
                GeneralUtils.getAllSubNavHeaders("registry").get(0).click();
                break;
            case "benefits":
                if (macys() && Elements.elementPresent("header.manage_registry_carat_symbol"))
                    Clicks.hoverForSelection("header.manage_registry_carat_symbol");
                Wait.untilElementPresent("header.goto_registry_benifits");
                Elements.findElement("header.goto_registry_benifits").click();
                break;
            case "profile":
                if (macys() && Elements.elementPresent("header.goto_my_account_link"))
                    Clicks.hoverForSelection(Elements.findElement("header.goto_my_account_link"));
                Wait.untilElementPresent("header.goto_myprofile");
                Elements.findElement("header.goto_myprofile").click();
                break;
        }
    }

    @Then("^I \"([^\"]*)\" see below subnav headers in \"([^\"]*)\" mode$")
    public void I_should_see_below_subnav_headers(String displayType, String mode, List<String> expHeaders) {
        List<WebElement> subNavHeaderLinks = GeneralUtils.getAllSubNavHeaders(mode);
        if (displayType.equals("should")) {
            for (int i = 0; i < subNavHeaderLinks.size(); i++) {
                String expectedHeaderName = expHeaders.get(i);
                String actualHeaderName = subNavHeaderLinks.get(i).getText();
                if (actualHeaderName.equals(""))
                    Assert.assertTrue(subNavHeaderLinks.get(i).isDisplayed());
                else
                    Assert.assertTrue(actualHeaderName.equalsIgnoreCase(expectedHeaderName));
            }
        } else if (displayType.equals("should not")) {
            String uiHeaderName;
            for (String header : expHeaders) {
                for (int i = 0; i < subNavHeaderLinks.size(); i++) {
                    uiHeaderName = subNavHeaderLinks.get(i).getText();
                    Assert.assertTrue(!header.equals(uiHeaderName));
                }
            }

        }
    }

    @When("^I navigate to any random subnav headers page in \"([^\"]*)\" mode$")
    public void i_navigate_to_any_random_subnav_headers_page(String mode) {
        WebElement randomElement;
        GeneralUtils.moveMousePosition();
        try {
            WebDriverManager.getWebDriver().navigate().refresh();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }
        List<WebElement> subNavHeaderLinks = GeneralUtils.getAllSubNavHeaders(mode);
        String[] removeSubNav = {"Back To Macys.com", "Registry Checklist", "Wedding Registry", "View Registry"};
        Random rmd = new Random();
        randomElement = subNavHeaderLinks.get(rmd.nextInt(subNavHeaderLinks.size()));
        while (randomElement.getText().equalsIgnoreCase(removeSubNav[0]) || randomElement.getText().equalsIgnoreCase(removeSubNav[1]) || randomElement.getText().equals(removeSubNav[2]) || randomElement.getText().equalsIgnoreCase(removeSubNav[3])) {
            randomElement = subNavHeaderLinks.get(rmd.nextInt(subNavHeaderLinks.size()));
        }
        randomElement.click();
    }

    @Given("^I should be navigated to \"([^\"]*)\" page$")
    public void i_should_be_navigated_to_page(String navigationPage) throws Throwable {
        String seasonalPromotionURL = "/ce/splash/october-trend-report/index?&cm_sp=navigation-_-top_nav-_-trend-report";
        String giftGuideURL = "/social/gift-guide";
        String topNavWishListURL = "/wishlist/mylist";
        String giftCards = "/shop/gift-cards";
        //String checklist = "cm_sp=global_nav_reg-_-registry_checklist-_-n";

        switch (navigationPage) {
            case "seasonal promotion":
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(seasonalPromotionURL));
                break;
            case "gift guide":
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(giftGuideURL));
                break;
            case "wishlist":
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(topNavWishListURL));
                break;
            case "deals & promotions":
                Assert.assertTrue(WebDriverManager.getWebDriver().getTitle().contains("Macy's Coupons, Deals and Promotions"));
                break;
            case "gift cards":
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(giftCards));
                break;
            case "Gift Registry":
                Assert.assertTrue(WebDriverManager.getWebDriver().getTitle().contains("Gift Registry"));
                break;
            case "stores":
                Assert.assertTrue(WebDriverManager.getWebDriver().getTitle().contains("Our Stores"));
                break;
            case "international context":
                onPage("change_country");
                break;
            case "customer service":
                Elements.elementShouldBePresent("header.goto_stores");
                Elements.elementShouldBePresent("header.goto_customer_service");
                break;
            case "view registry":
                Assert.assertTrue(WebDriverManager.getWebDriver().getTitle().contains("Macy's registry"));
                break;
            case "registry manager":
                Assert.assertTrue(WebDriverManager.getWebDriver().getTitle().contains("Registry Manager"));
                break;
            case "registry checklist":
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("checklist"));
                break;
            case "rewards program":
                Assert.assertTrue(WebDriverManager.getWebDriver().getTitle().contains("Rewards Home Page"));
                break;
            case "help":
                Thread.sleep(15000);
                Assert.assertTrue(Elements.findElement(By.id("helpOverlayContainer")).getText().contains("Need help"));
                break;
            case "homepage":
                Assert.assertTrue(onPage("home"));
                break;
            case "shopping bag":
                Assert.assertTrue(onPage("shopping_bag"));
                break;
            case "catsplash":
                Assert.assertTrue(onPage("category_splash"));
                break;
            case "category browse":
                Assert.assertTrue(onPage("category_browse"));
                break;
            case "category sub splash":
                Assert.assertTrue(onPage("category_sub_splash"));
                break;
            case "add to bag":
                Assert.assertTrue(onPage("add_to_bag"));
                break;
            case "search result":
                Assert.assertTrue(onPage("search_result"));
                break;
        }
        WebDriverManager.getWebDriver().navigate().back();
    }

    @And("^I navigate back to registry home page$")
    public void i_navigate_back_to_registry_home_page() throws Throwable {
        List<WebElement> registryFob = new ArrayList<WebElement>();
        registryFob = GeneralUtils.getFOBFromRegistryHomePage();
        registryFob.get(0).click();
    }

    @When("^I add the product to a registry and continue shopping$")
    public void i_add_the_product_to_a_registry_and_continue_shopping() throws Throwable {
        String mode = product_details.get("mode").get(0);
        GeneralUtils.selectRandomColor(mode);
        GeneralUtils.selectRandomSize(mode);
        Clicks.click(By.className("addToRegistryButton"));
        Clicks.click(By.className("continueShopping"));
    }

    @When("^I add a registry product to the shopping bag$")
    public void i_add_a_registry_product_to_the_shopping_bag() throws Throwable {
        String mode = product_details.get("mode").get(0);
        String color = GeneralUtils.selectRandomColor(mode);
        String size = GeneralUtils.selectRandomSize(mode);
        product_details.put("product_size", size);
        product_details.put("product_color", color);
        Clicks.click(By.className("atb"));
        Clicks.click(By.className("checkout"));
    }

    @When("^I navigate to find registry page$")
    public void i_navigate_to_find_registry_page() throws Throwable {
        Clicks.click("header.goto_wedding_registry");
        Elements.findElement("registry_home.goto_find_registry").click();
    }

    @Then("^I click view registry in GVR page$")
    public void i_click_view_registry_in_GVR_page() throws Throwable {
        Clicks.click("find_registry.find_registry_results");
    }

    @When("^I navigate to PDP page of an \"([^\"]*)\" product$")
    public void i_navigate_to_PDP_page_of_an_product(String product_type) throws Throwable {
        switch (product_type) {
            case "normal":
            case "REGISTRY":
            case "GWP":
                if (product_type.equalsIgnoreCase("GWP")) {
                    page.I_navigate_to_category_page("BEAUTY");
                    new Home().selectSubCategory("Gifts With Purchase");
                } else {
                    Home.selectRandomSubCategory();
                }
                CommonUtils.selectRandomProduct(false, false);
                if (CommonUtils.isMasterProduct()) {
                    int mem_prod_count = Elements.findElements(By.className("memberUrlUpdate")).size();
                    int mem_prod_number = (int) (Math.random() * mem_prod_count);
                    Clicks.click(Elements.findElements(By.className("memberUrlUpdate")).get(mem_prod_number).findElement(By.tagName("img")));
                    Clicks.click("quick_view.goto_product_page");
                }
                break;
            case "VGC":
                if (macys() && Elements.elementPresent("header.gift_dropdown"))
                    Clicks.hoverForSelection("header.gift_dropdown");
                Wait.untilElementPresent("header.goto_gift_cards");
                Clicks.click("home.goto_gift_cards");
                String currentUrl = WebDriverManager.getWebDriver().getCurrentUrl();
                if(!prodEnv() && currentUrl.contains("macys.com")){
                    Navigate.visit(RunConfig.url + currentUrl.split("macys.com")[1]);
                }
                Wait.forPageReady();
                int vgc_count = Elements.findElements("category_splash.gift_card_thumbnails").size();
                int prod_number = vgc_count <= 1 ? 0 : new Random().nextInt(3);
                while (!Elements.findElements("category_splash.gift_card_thumbnails").get(prod_number).isDisplayed()) {
                    Elements.findElement("recently_viewed_items.scroll_right").click();
                    Wait.ajaxDone();
                }
                Clicks.click(Elements.findElements("category_splash.gift_card_thumbnails").get(prod_number).findElement(By.tagName("a")));
                break;
            case "EGC":
                if (macys() && Elements.elementPresent("header.gift_dropdown"))
                    Clicks.hoverForSelection("header.gift_dropdown");
                Wait.untilElementPresent("header.goto_gift_cards");
                Clicks.click("home.goto_gift_cards");
                List<WebElement> leftNavLinks = Elements.findElements(By.className("nav_cat_sub_3"));
                if (leftNavLinks.isEmpty())
                    leftNavLinks = Elements.findElements(By.className("header"));
                Clicks.click(leftNavLinks.get(2).findElement(By.tagName("a")));
                WebElement element = Elements.findElements(By.tagName("iframe")).get(1);
                WebDriverManager.getWebDriver().switchTo().frame(element);
                Thread.sleep(10000);
                List<WebElement> rowElement = Elements.findElement(By.id("MainBodyTable")).findElements(By.tagName("tr"));
                rowElement.get(8).findElements(By.tagName("td")).get(3).click();
                Thread.sleep(20000);
        }
    }


    @Given("^I should see empty quickbag message$")
    public void i_should_see_empty_quickbag_message() throws Throwable {
        Navigate.scrollPage(0, 100);
        Navigate.scrollPage(0, -100);
        GeneralUtils.hoverSelection(Elements.findElement("header.goto_shopping_bag"));
        Elements.elementShouldBePresent("header.empty_quickbag");
        Assert.assertTrue(Elements.findElement("header.empty_quickbag").getText().equalsIgnoreCase("0 items in your bag. Shop great deals now!"));
    }

    @When("^I add the product to the bag$")
    public void i_add_the_product_to_the_bag() throws Throwable {
        if (product_details.get("prod_type").contains("EGC")) {
            product_details.put("product_size", "No Size");
            product_details.put("product_color", "No Color");
            Clicks.click("product_display.egc_add_to_bag");
        } else {
            String mode = product_details.get("mode").get(0);
            String color = GeneralUtils.selectRandomColor(mode);
            String size = GeneralUtils.selectRandomSize(mode);
            product_details.put("product_size", size);
            product_details.put("product_color", color);
            try {
                GeneralUtils.clickAddToBagButtonInPDPPage();
            } catch (Exception e) {
                System.out.println("Unable to click on 'Add To Bag' button");
                e.printStackTrace();
            }
        }
    }

    @Given("^I \"([^\"]*)\" see the quickbag overlay$")
    public void i_see_the_quickbag_overlay(String action) throws Throwable {
        Navigate.scrollPage(0, 100);
        Navigate.scrollPage(0, -100);
        if (action.equalsIgnoreCase("should")) {
            GeneralUtils.hoverSelection(Elements.findElement("header.goto_shopping_bag"));
            Wait.untilElementPresent(By.xpath("//*[@id='qbFlyout']//div[contains(@class, 'description')]"));
            Assert.assertNotNull(Elements.findElement(By.xpath("//*[@id='qbFlyout']//div[contains(@class, 'description')]")));
        } else {
            GeneralUtils.hoverSelection(Elements.findElement("header.shopping_bag_item_count"));
            i_should_see_empty_quickbag_message();
        }
    }

    @Then("^I verify the product details of an \"([^\"]*)\" product in PDP page$")
    public void i_verify_the_product_details_of_an_product_in_PDP_page(String product_type) throws Throwable {
        if (product_type.equalsIgnoreCase("VGC")) {
            String amount = String.valueOf(10 + (int) (Math.random() * 1000));
            String email = amount.concat("@gmail.com");
            TextBoxes.typeTextbox("product_display.vgc_amount", amount);
            TextBoxes.typeTextbox("product_display.vgc_email", email);
            product_details.put("product_price", amount);
            product_details.put("product_email", email);
            product_details.put("product_name", Elements.findElement(By.className("productTitle")).getText().replaceAll("\n", " "));
        } else if (product_type.equalsIgnoreCase("EGC")) {
            String amount = String.valueOf(10 + (int) (Math.random() * 1000));
            product_details.put("product_price", amount);
            Elements.findElement("product_display.gift_card_amount").sendKeys(amount);
            product_details.put("product_name", Elements.findElement(By.xpath("id('MainBodyTable')//tr[2]//td[3]//td")).getText());
        } else {
            page.I_should_be_redirected_to_PDP_page();
            product_details.put("product_name", Elements.findElement(By.className("productTitle")).getText().replaceAll("\n", " "));
            String product_price;
            Assert.assertTrue(Elements.findElement("product_display.add_to_bag_button").isEnabled());
            Assert.assertTrue(Elements.findElement("product_display.product_id_div").isDisplayed());
            if (product_details.get("mode").contains("iship") || product_details.get("mode").contains("registry")) {
                Assert.assertTrue(Elements.findElement("product_display.product_title").isDisplayed());
                product_price = Elements.findElement("product_display.price").getText();
                if ((product_price.contains("Sale")) || product_price.contains("Now")) {
                    product_details.put("product_price", Elements.findElement("product_display.price_box").findElement(By.className("priceSale")).getText().replaceAll("[a-zA-Z ]", ""));
                } else {
                    product_details.put("product_price", Elements.findElement("product_display.price_box").getText().replaceAll("[a-zA-Z ]", ""));
                }
            } else {
                Assert.assertTrue(Elements.findElement("product_display.product_title_pdp").isDisplayed());
                product_price = Elements.findElement("product_display.pdp_price").getText();
                if (product_price.contains("Sale")) {
                    Elements.findElement("product_display.pdp_sale_price").getText();
                    product_details.put("product_price", Elements.findElement("product_display.pdp_sale_price").getText().split("Sale ")[1]);
                } else {
                    product_details.put("product_price", product_price);
                }
            }
            String product_id = Elements.findElement("product_display.product_id_div").getText().replaceAll("[a-zA-Z: ]", "");
            product_details.put("productId", product_id);
        }
        product_details.put("prod_type", product_type);
        product_details.put("quantity", "1");
    }

    @Given("^I verify the product details in quickbag$")
    public void i_verify_the_product_details_in_quickbag() throws Throwable {
        GeneralUtils.hoverSelection(Elements.findElement("header.goto_shopping_bag"));
        quickbagProducts = GeneralUtils.getAllQuickbagProductDetails();
        for (int i = 0; i < quickbagProducts.size(); i++) {
            Assert.assertSame(quickbagProducts.get(i).quantity, Integer.parseInt(product_details.get("quantity").get(i)));
            Assert.assertNotNull(quickbagProducts.get(i).bagTotal);
            if (product_details.get("prod_type").contains("VGC")) {
                Assert.assertTrue(quickbagProducts.get(i).giftCardEmail.contains(product_details.get("product_email").get(i)));
                Assert.assertTrue(product_details.get("product_price").get(i).concat(".0").contains(String.valueOf(quickbagProducts.get(i).retail_price)));
            } else {
                if (quickbagProducts.get(i).color_size.contains("Size:"))
                    Assert.assertTrue(quickbagProducts.get(i).color_size.contains(product_details.get("product_size").get(i)));
                if (quickbagProducts.get(i).gift_retail_price == null || !quickbagProducts.get(i).gift_retail_price.contains("FREE")) {
                    String product_price = product_details.get("product_price").get(i);
                    if (product_price.contains("Sale") || product_price.contains("Now") || product_price.contains("Orig") || product_price.contains("Reg")) {
                        product_price = product_details.get("product_price").get(i).split(" ")[0];
                    }
                    Assert.assertTrue("ERROR - APP: Product price in PDP and Quick Bag is not matching",
                            Double.parseDouble(product_price.replaceAll("[a-zA-Z$: ]", "").replaceAll(",", "").replace("$", "")) == Double.parseDouble(String.valueOf(quickbagProducts.get(i).retail_price).replaceAll("[a-zA-Z$: ]", "").replaceAll(",", "").replace("$", "")));
                }
            }
            if (quickbagProducts.get(i).badges.contains("null"))
                Assert.assertTrue(product_details.get("product_name").get(i).toLowerCase().contains(quickbagProducts.get(i).name.toLowerCase()));
            Assert.assertTrue(quickbagProducts.get(i).color_size.contains(product_details.get("product_color").get(i)));
        }
    }

    @Given("^I should see remove button in Item container of quickbag overlay for the product$")
    public void i_should_see_remove_button_in_Item_container_of_quickbag_overlay_for_the_product() throws Throwable {
        GeneralUtils.hoverSelection(Elements.findElement("header.shopping_bag_item_count"));
        Wait.isPageLoaded();
        GeneralUtils.hoverSelection(Elements.findElement(By.className("itemAction")).findElement(By.tagName("button")));
        Elements.elementShouldBePresent(Elements.findElement(By.className("itemAction")).findElement(By.tagName("button")));
    }

    @When("^I click on \"([^\"]*)\" button for the product in Item container$")
    public void i_click_on_button_for_the_product_in_Item_container(String action) throws Throwable {
        GeneralUtils.hoverSelection(Elements.findElement("header.goto_shopping_bag"));
        if (action.equalsIgnoreCase("remove")) {
            GeneralUtils.hoverSelection(Elements.findElements(By.className("itemAction")).get(quickbagProducts.size() - 1).findElement(By.tagName("button")));
            Elements.elementShouldBePresent(Elements.findElements(By.className("itemAction")).get(quickbagProducts.size() - 1).findElement(By.tagName("button")));
            Elements.findElements(By.className("itemAction")).get(quickbagProducts.size() - 1).findElement(By.tagName("button")).click();
        } else
            Elements.findElement(By.linkText("CHECKOUT")).click();
    }

    @Then("^I should see the item removed from bag$")
    public void i_should_see_the_item_removed_from_bag() throws Throwable {
        if (product_details.get("prod_type").contains("GWP")) {
            GeneralUtils.hoverSelection(Elements.findElement("header.shopping_bag_item_count"));
            Assert.assertFalse(Elements.findElements(By.className("errorMessage")).isEmpty());
        } else {
            i_should_see_empty_quickbag_message();
        }
    }

    @When("^I click quickbag container$")
    public void i_click_quickbag_container() throws Throwable {
        Clicks.click("header.shopping_bag_item_count");
    }

    @When("^I click product thumbnail from quickbag$")
    public void i_click_product_thumbnail_from_quickbag() throws Throwable {
        try {
            Clicks.click("quick_bag.product_title");
        } catch (Exception e) {
            System.out.println("Unable to click product thumbnail from quickbag");
            e.printStackTrace();
        }
    }

    @Then("^I verify below subnav headers in \"([^\"]*)\"$")
    public void i_verify_subnav_headers_in(String mode, DataTable expectedSubNavHeaders) throws Throwable {
        List<String> expSubNav = expectedSubNavHeaders.asList(String.class);
        subNavHeaders.put(mode, expSubNav);
        if (mode.equalsIgnoreCase("site")) {
            Elements.elementShouldBePresent(Elements.findElement("header.seasonal_promotion").findElement(By.tagName("img")));
            Elements.elementShouldBePresent(Elements.findElement("header.goto_wishlist"));
            Elements.elementShouldBePresent(Elements.findElement("header.goto_deals_promotions"));
            if (macys() && Elements.elementPresent("header.gift_dropdown"))
                Clicks.hoverForSelection("header.gift_dropdown");
            Wait.untilElementPresent("header.goto_gift_cards");
            Elements.elementShouldBePresent(Elements.findElement("header.goto_gift_cards"));
            Elements.elementShouldBePresent(Elements.findElement("header.gift_guide"));
            Elements.elementShouldBePresent(Elements.findElement("header.goto_wedding_registry"));
        } else if (mode.equalsIgnoreCase("registry")) {
            List<WebElement> actualSubNav = GeneralUtils.getAllSubNavHeaders(mode);
            for (int i = 0; i < expSubNav.size(); i++) {
                Assert.assertTrue(expSubNav.get(i).equalsIgnoreCase(actualSubNav.get(i).getText()));
            }
        } else if (mode.equalsIgnoreCase("iship")) {
            Elements.elementShouldBePresent(Elements.findElement("header.goto_wedding_registry"));
            Elements.elementShouldBePresent(Elements.findElement("header.gift_guide").findElement(By.tagName("img")));
            Elements.elementShouldBePresent(Elements.findElement("header.seasonal_promotion").findElement(By.tagName("img")));
        }
    }

    @Then("^I verify all the subnav headers in \"([^\"]*)\"$")
    public void i_verify_all_the_subnav_headers_in(String mode) throws Throwable {
        List<String> expSubNav = subNavHeaders.get(mode);
        if (mode.equalsIgnoreCase("site")) {
            Elements.elementShouldBePresent(Elements.findElement("header.goto_wishlist"));
            Elements.elementShouldBePresent(Elements.findElement("header.goto_deals_promotions"));
            if (macys() && Elements.elementPresent("header.gift_dropdown"))
                Clicks.hoverForSelection("header.gift_dropdown");
            Wait.untilElementPresent("header.goto_gift_cards");
            Elements.elementShouldBePresent(Elements.findElement("header.goto_gift_cards"));
            Elements.elementShouldBePresent(Elements.findElement("header.gift_guide"));
            Elements.elementShouldBePresent(Elements.findElement("header.goto_wedding_registry"));
        } else if (mode.equalsIgnoreCase("registry")) {
            List<WebElement> actualSubNav = GeneralUtils.getAllSubNavHeaders(mode);
            for (int i = 0; i < expSubNav.size(); i++) {
                Assert.assertTrue(expSubNav.get(i).equalsIgnoreCase(actualSubNav.get(i).getText()));
            }
        } else if (mode.equalsIgnoreCase("iship")) {
            Elements.elementShouldBePresent(Elements.findElement("header.goto_wedding_registry"));
            Elements.elementShouldBePresent(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[3]/a")).findElement(By.tagName("img")));
            Elements.elementShouldBePresent(Elements.findElement(By.xpath("//*[@id='subnavContainer']/li[2]/a")).findElement(By.tagName("img")));
        }
    }

    @When("^I click on any random FOB$")
    public void i_click_on_any_random_FOB() {
        Random rmd = new Random();
        List<WebElement> fobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.className("fob"));
        if (fobList.size() == 0) {
            List<WebElement> regFobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.tagName("li"));
            for (WebElement element : regFobList) {
                if (element.getAttribute("innerHTML").contains("flyout"))
                    fobList.add(element);
            }
        }
        WebElement eleClick = fobList.get(rmd.nextInt(fobList.size()));
        while (eleClick.getText().equals("WEDDING REGISTRY") || eleClick.getText().equals("ACTIVE") || eleClick.getText().equals("BRANDS")) {
            eleClick = fobList.get(rmd.nextInt(fobList.size()));
        }
        eleClick.click();
    }

    @When("^I hover over any random category and verify links present in subcategory$")
    public void i_hover_over_any_random_category_and_on_subcategory() {
        List<WebElement> fobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.className("fob"));
        if (fobList.size() == 0) {
            List<WebElement> regFobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.tagName("li"));
            for (WebElement element : regFobList) {
                if (element.getAttribute("innerHTML").contains("flyout"))
                    fobList.add(element);
            }
        }
        int elementHover = new Random().nextInt(fobList.size() - 1);
        try {
            Clicks.hoverForSelection(fobList.get(elementHover));
        } catch (Exception e) {
            log.info("Unable to hover over FOB menu" + e.getMessage());
        }
        List<WebElement> subCategoryList = Elements.findElements("header.open_flyout").stream().filter(f -> f.isDisplayed()).collect(Collectors.toList()).get(0).findElements(By.tagName("a"));
        Assert.assertFalse("ERROR - APP: Sub categories on flyout are not displaying.", subCategoryList.isEmpty());
    }

    @When("^I hover over any random category$")
    public void i_hover_over_any_random_category() {
        List<WebElement> fobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.className("fob"));
        if (fobList.size() == 0)
            fobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.cssSelector("li[class='']"));
        int elementHover = new Random().nextInt(fobList.size() - 1);
        try {
            Clicks.hoverForSelection(fobList.get(elementHover));
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Then("^I should see the flyout menu$")
    public void i_should_see_flyout_menu() {
        List<WebElement> fobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.className("fob"));
        if (fobList.size() == 0)
            fobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.cssSelector("li[class='']"));
        int elementHover = new Random().nextInt(fobList.size() - 1);
        try {
            Clicks.hoverForSelection(fobList.get(elementHover));
        } catch (Exception e) {
            log.info("Unable to hover over FOB menu" + e.getMessage());
        }
        Assert.assertFalse("Flyout menu is not displayed",
                Elements.findElements("header.open_flyout").stream().filter(f -> f.isDisplayed()).collect(Collectors.toList()).isEmpty());
    }

    @Then("^I should see below FOB's in \"([^\"]*)\"$")
    public void i_should_see_below_FOBs(String mode, DataTable fobList) {
        List expFob = fobList.asList(String.class);
        ArrayList expFobList = new ArrayList();
        expFobList.addAll(expFob);
        if (mode.equals("iship")) {
            expFobList.remove("ACTIVE");
            expFobList.remove("HANDBAGS");
            expFobList.remove("BEAUTY");
            expFobList.remove("GIFTS");
            expFobList.add(7, "HANDBAGS & ACCESSORIES");
        }
        fobDetails.put(mode, expFobList);
        List<WebElement> actualFobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.className("fob"));
        for (int i = 0; i < actualFobList.size(); i++) {
            Assert.assertEquals(expFobList.get(i), actualFobList.get(i).getText());
        }
    }

    @Then("^I should see all the FOBs in \"([^\"]*)\" mode$")
    public void i_should_see_all_the_FOBs_in_mode(String mode) throws Throwable {
        List expFOBList = fobDetails.get(mode);
        List<WebElement> actualFobList = Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.className("fob"));
        for (int i = 0; i < actualFobList.size(); i++) {
            Assert.assertEquals(expFOBList.get(i), actualFobList.get(i).getText());
        }
    }

    @When("^I navigate to browse page in \"([^\"]*)\"$")
    public void iNavigateToBrowsePage(String mode) throws Throwable {
        categorySplash.navigateToRandomCategoryBrowsePage(8);
        if (mode.equals("registry")) {
            while (!WebDriverManager.getWebDriver().getCurrentUrl().contains("wedding-registry")) {
                Navigate.visit("registry_home");
                categorySplash.navigateToRandomCategoryBrowsePage(8);
            }
        }
    }

    @When("^I navigate to \"([^\"]*)\" page in \"([^\"]*)\" mode$")
    public void I_navigate_to_category_page(String category, String mode) throws Throwable {
        switch (mode) {
            case "site":
                if (category.contentEquals("Browse")) {
                    categorySplash.navigateToRandomCategoryBrowsePage(8);
                }
                if (category.contentEquals("Sub Splash")) {
                    Home.selectRandomSubCategory();
                    Wait.forPageReady();
                }
                break;
            case "iship":
                new Home().selectMainCategory(category);
                break;
            case "registry":
                Elements.findElement(By.xpath(macys() ? "//*[@id=\"flexid_7495\"]/a" : "//*[@id=\"flexLabel_1001498\"]/a")).click();
                if (category.contentEquals("Browse")) {
                    categorySplash.navigateToRandomCategoryBrowsePage(8);
                }
                if (category.contentEquals("Sub Splash")) {
                    Home.selectRandomSubCategory();
                    Wait.forPageReady();
                }
        }
    }

    @And("^I navigate to DLP page in \"([^\"]*)\" mode$")
    public void i_navigate_to_DLP_page_in_mode(String mode) {
        WebElement selectedBrandElement = null;
        switch (mode) {
            case "site":
                selectedBrandElement = Elements.findElement((By.xpath("//*[@id=\"brandIndex\"]/div[4]/ul[2]/li[5]/a")));
                selectedBrandElement.click();
                break;
            case "iship":
                selectedBrandElement = Elements.findElement((By.xpath("//*[@id=\"brandIndex\"]/div[4]/ul[2]/li[4]/a")));
                selectedBrandElement.click();
                break;
            case "registry":
                String url = Elements.findElement(By.xpath("//*[@id=\"seoTagCloud\"]/div[2]/a[1]")).getAttribute("href") + "?mode=wedding";
                try {
                    WebDriverManager.getWebDriver().get(url);
                } catch (DriverNotInitializedException e) {
                    e.printStackTrace();
                }
        }
    }

    @Then("^I verify the display and functionality of new Header Footer$")
    public void i_verify_the_display_and_functionality_of_new_header_footer() throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("header.goto_customer_service");
    }

    @When("^I navigate to registry (BVR|browse|GVR) page$")
    public void i_navigate_to_registry_page(String pagetype) throws Throwable {
        switch (pagetype) {
            case "BVR":
                Wait.forPageReady();
                Clicks.click("registry_home.goto_create_registry");
                Wait.forPageReady();
                Clicks.click("registry_home.verify_page");
                if (macys()) {
                    CreateRegistry.createRegistryUserForExistingUser(TestUsers.getExistingRegistryUser());
                } else {
                    CreateRegistry.fillRegistryUserDetailsForExistingUser(TestUsers.getExistingRegistryUser());
                }
                Wait.forPageReady();
                GeneralUtils.getAllSubNavHeaders("registry").get(5).click();
                Wait.forPageReady();
                Assert.assertTrue("Error-APP - BVR page is not loaded:", title().contains("Macy's Registry of "));
                break;

            case "browse":
                Wait.forPageReady();
                Home.selectRandomSubCategory();
                System.out.println("Naviagted to random category page");
                break;

            case "GVR":
                Wait.forPageReady();
                Clicks.click(Elements.element("my_account.goto_sign_out_link"));
                Wait.untilElementNotPresent("my_account.goto_sign_out_link");
                // sign out doesn't like to stick
                if (Elements.elementPresent("my_account.goto_sign_out_link")) {
                    Clicks.click(Elements.element("my_account.goto_sign_out_link"));
                    Assert.assertTrue("ERROR-ENV: sign out link is still visible", Elements.elementPresent(Elements.element("home.goto_sign_in_link")));
                }
                Clicks.click("home.goto_wedding_registry");
                Wait.forPageReady();
                i_navigate_to_find_registry_page();
                i_click_view_registry_in_GVR_page();
                break;

            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid page");
        }
    }

    @Then("^I verify that Bloomingdales logo takes to Home Page$")
    public void i_verify_that_Bloomingdales_logo_takes_to_Home_Page() throws Throwable {
        if (WebDriverManager.getCurrentUrl().contains("registry"))
            Clicks.click("header.goto_back_to_bloomingdales");
        else {
            Elements.elementShouldBePresent("home.verify_page");
            Clicks.click("home.verify_page");
        }
        Assert.assertTrue(WebDriverManager.getCurrentTitle().equals("Bloomingdale's Official Site - Shop For Designer Clothing & Accessories"));
    }

    @Then("^I navigate directly to \"([^\"]*)\" page$")
    public void i_navigate_directly_to_page(String keyword) throws Throwable {
        if (keyword.equalsIgnoreCase("CUTLERY")) {
            Clicks.hoverForSelection(Elements.findElements("category_menu.category").stream().filter(ele -> ele.getText().equalsIgnoreCase("KITCHEN")).collect(Collectors.toList()).get(0));
            Elements.findElement("category_menu.flyout_menu").findElement(By.linkText("Bread Knives")).click();
        } else {
            Clicks.hoverForSelection(Elements.findElements("category_menu.category").stream().filter(ele -> ele.getText().equalsIgnoreCase("WOMEN")).collect(Collectors.toList()).get(0));
            Elements.findElement("category_menu.flyout_menu").findElement(By.linkText("Dresses")).click();
        }
    }

//    This step is dependent step for below basic attributes validation step so don't move it
    @When("^I Navigate to \"([^\"]*)\" media footer link$")
    public void I_Navigate_to_media_footer_link(String link) {
        List<WebElement> linkList = Elements.findElement(By.className("socialLinks")).findElements(By.tagName("a"));
        scrollToLazyLoadElement("home.social_media_container");
        switch (link) {
            case "Mobile":
                linkList.get(0).click();
                break;
            case "Instagram":
                linkList.get(1).click();
                break;
            case "Snapchat":
                linkList.get(2).click();
                break;
            case "Pinterest":
                linkList.get(3).click();
                break;
            case "Facebook":
                linkList.get(4).click();
                break;
            case "Twitter":
                linkList.get(5).click();
                break;
            case "Tumblr":
                linkList.get(6).click();
                break;
        }
        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
            Set<String> allWindows = driver.getWindowHandles();
            for (String curWindow : allWindows) {
                driver.switchTo().window(curWindow);
            }
            url = WebDriverManager.getCurrentUrl();
            title = WebDriverManager.getWebDriver().getTitle();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }
    }

    @And("^I verify the basic attributes \"([^\"]*)\" page$")
    public void i_verify_basic_attributes(String name) throws Throwable {
        String pageUrl = url.equals("") ? WebDriverManager.getCurrentUrl() : url;
        String pageTitle = title.equals("") ? WebDriverManager.getWebDriver().getTitle() : title;
        String removeSplChar = name.replace(" & ", "_").replace(" ", "_").replace(".", "_");
        String actualUrlUpperCase = String.join("-_-", "BOTTOM_NAV", removeSplChar.toUpperCase());
        String actualUrlLowseCase = String.join("-_-", "BOTTOM_NAV", removeSplChar.toLowerCase());
        switch (name) {
            case "CUSTOMER SERVICE":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                break;
            case "Domestic":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("Support Home Page"));
                break;
            case "Shipping Policy":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-SHIPPING"));
                Assert.assertTrue(pageTitle.equals("What is the shipping policy?") || pageTitle.equals("Bloomingdale's International") || pageTitle.equals("International Shipping"));
                break;
            case "Returns & Exchanges":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("What is the return and exchange policy?") || pageTitle.equals("Bloomingdale's International") || pageTitle.equals("International Returns"));
                break;
            case "International":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("Find Answers") || pageTitle.equals("Bloomingdale's International"));
                break;
            case "MY CREDIT CARD":
            case "Cardholder Benefits":
                if (bloomingdales() && pageUrl.contains(actualUrlUpperCase)) {
                    Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                    Assert.assertTrue(pageTitle.equals("Bloomingdale's"));
                } else {
                    Assert.assertTrue(pageTitle.equals("Credit Gateway"));
                }
                break;
            case "Pay Bill":
                Assert.assertTrue(pageUrl.contains(macys()?actualUrlLowseCase : actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("Sign In - My Account - Bloomingdale's"));
                break;
            case "Learn More & Apply":
                Assert.assertTrue(pageUrl.contains("FOOTER-_-BOTTOM_NAV-_-APPLY_LEARN"));
                Assert.assertTrue(pageTitle.equals("Bloomingdale's"));
                break;
            case "MY ACCOUNT":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("Sign In - My Account - Bloomingdale's"));
                break;
            case "YOUR ORDER":
            case "FAQs":
            case "Order Status":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("Check My Order Status & Order History - Bloomingdale's") || pageTitle.equals("Bloomingdale's International") || pageTitle.contains("order"));
                break;
            case "My Loyallist":
                Assert.assertTrue(pageUrl.contains("loyaltySignIn"));
                Assert.assertTrue(pageTitle.equals("Loyallist - Become A Loyallist | Bloomingdale's"));
                break;
            case "My Profile":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("Create Profile - My Account - Bloomingdale's"));
                break;
            case "COMPANY":
            case "About Us":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("About Us"));
                break;
            case "b.cause":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.contains("Bloomingdale's Loves a Good Cause"));
                break;
            case "Careers":
                WebDriverManager.getWebDriver().get(Elements.findElement(By.linkText("Careers")).getAttribute("href").split("'")[1]);
                String url = WebDriverManager.getCurrentUrl();
                String title = WebDriverManager.getWebDriver().getTitle();
                Assert.assertTrue(url.contains("bloomingdalesjobs"));
                Assert.assertTrue(title.contains("Bloomingdale’s Jobs"));
                break;
            case "WAYS TO SHOP":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.equals("Discover New Events & Ways to Shop at Bloomingdale’s!"));
                break;
            case "Online & Mobile":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-MOBILE"));
                Assert.assertTrue(pageTitle.contains("Experience New Ways to Shop Online at Bloomingdale’s!"));
                break;
            case "Stores":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.contains("Find a Nearby Bloomingdale's"));
                break;
            case "Pick Up In Store":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-BOPS"));
                Assert.assertTrue(pageTitle.contains("Buy Online Pickup In Store Today"));
                break;
            case "Mobile":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-APP"));
                Assert.assertTrue(pageTitle.contains("Experience New Ways to Shop Online"));
                break;
            case "Instagram":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-INSTAGRAM"));
                Assert.assertTrue(pageTitle.contains("Instagram photos and videos"));
                break;
            case "Pinterest":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-PINTEREST"));
                Assert.assertTrue(pageTitle.contains("on Pinterest"));
                break;
            case "Facebook":
                if(bloomingdales()) {
                    Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-FACEBOOK"));
                    Assert.assertTrue(pageTitle.toLowerCase().contains("facebook"));
                }
                break;
            case "Twitter":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-TWITTER"));
                Assert.assertTrue(pageTitle.contains("Twitter"));
                break;
            case "Visitor Services":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                Assert.assertTrue(pageTitle.contains(name));
                break;
            case "Snapchat":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-SNAPCHAT"));
                Assert.assertTrue(pageTitle.contains("Add me on Snapchat"));
                break;
            case "Tumblr":
                Assert.assertTrue(pageUrl.contains(actualUrlUpperCase));
                break;
            case "Outlets":
                Assert.assertTrue(pageUrl.contains("BOTTOM_NAV-_-OUTLETS") || pageUrl.contains("outlet-store"));
                Assert.assertTrue(pageTitle.equals("Designer The Outlet Store - Bloomingdale's") || pageTitle.contains("Find an Outlet Store Near You - Bloomingdale's"));
                break;
            default:
                System.out.println("Argument doesn't match!!!");
        }
        url = "";
        title = "";
    }

    @Then("^I verify the legal notice pages are rendered properly in \"([^\"]*)\" mode$")
    public void i_verify_legal_notice_pages(String mode, DataTable pageTable) throws DriverNotInitializedException {
        String cmTag = mode.equals("iship") ? String.join("-_-", "FOOTER_INTL", "BOTTOM_NAV") : String.join("-_-", "FOOTER", "BOTTOM_NAV");
        String pageURL = WebDriverManager.getCurrentUrl();
        List<String> legalPageList = pageTable.asList(String.class);
        for (String page : legalPageList) {
            switch (page) {
                case "Terms of use":
                    Clicks.click("home.goto_terms_of_use");
                    Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(cmTag + "-_-TERMS_OF_USE"));
                    break;
                case "Privacy":
                    Clicks.click("home.goto_privacy");
                    Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(cmTag + "-_-PRIVACY_PRACTICES"));
                    break;
                case "CA Privacy Rights":
                    Clicks.click("home.goto_ca_privacy_rights");
                    Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(cmTag + "-_-CA_PRIVACY_RIGHTS"));
                    break;
                case "Customers Bill of Rights":
                    WebDriverManager.getWebDriver().get(Elements.findElement(By.linkText("Customers' Bill of Rights")).getAttribute("href"));
                    Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(cmTag + "-_-CUSTOMERS_BILL_OF_RIGHTS"));
                    break;
                case "CA Transparency Supply Chain Act":
                    WebDriverManager.getWebDriver().get(Elements.findElement(By.linkText("CA Transparency in Supply Chains Act")).getAttribute("href"));
                    Assert.assertTrue(WebDriverManager.getCurrentUrl().contains(cmTag + "-_-CA_SUPPLY_CHAINS"));
                    break;
            }
            WebDriverManager.getWebDriver().get(pageURL);
            Wait.untilElementPresent("header.logo");
        }
    }

    @Then("^I verify \"([^\"]*)\" image displayed below footer$")
    public void i_verify_image_displayed_in_footer(String expectedImage) {
        String actualImage = expectedImage.contains("Customer Service") ? Elements.findElement(By.id("text")).getText() : Elements.findElement(By.className("likeNoOtherStore")).findElement(By.tagName("img")).getAttribute("alt");
        Assert.assertTrue(expectedImage.equals(actualImage));
    }

    @When("^I should not see below links on international Page$")
    public void i_should_not_see_links_in_intl_page(DataTable links) {
        List<String> illegalLinksList = links.asList(String.class);
        for (String link : illegalLinksList) {
            switch (link) {
                case "My Loyallist":
                    Assert.assertFalse(Elements.findElement("home.goto_loyallist").getText().equals("My Loyalist"));
                    break;
                case "My Account":
                    Assert.assertNull(Elements.findElement("home.goto_my_account"));
                    break;
                case "Pay Bill":
                    Assert.assertNull(Elements.findElement("home.goto_credit_pay_bill_online"));
                    break;
                case "Cardholder Benefits":
                    Assert.assertNull(Elements.findElement("home.goto_credit_cardholder_benefits"));
                    break;
                case "Apply & Lean More":
                    Assert.assertNull(Elements.findElement("home.goto_credit_apply_now"));
            }
        }
    }

    @Then("^I verify header global banner appears without errors and contains the text \"([^\"]*)\"$")
    public void i_verify_header_global_banner_appears_without_errors_and_contains_the_text(String banner) throws Throwable {
        Elements.findElement("header.nav_banner").click();
        Set<String> allHandles = WebDriverManager.getWebDriver().getWindowHandles();
        allHandles.remove(allHandles.iterator().next());
        String lastHandle = allHandles.iterator().next();
        WebDriverManager.getWebDriver().switchTo().window(lastHandle);
        Elements.findElement(By.className("bl_pop_header")).getText().contains(banner);
    }

    @Then("^I should not see the following \"([^\"]*)\" FOBs$")
    public void i_should_not_see_the_following_FOBs(String category) throws Throwable {
        Assert.assertEquals(0, Elements.findElements("category_menu.category").stream().filter(e -> e.getText().contains(category)).collect(Collectors.toList()).size());
        log.info("category " + category + " is not available in ISHIP mode");
    }

    @Then("^the search field should have a label with the text \"([^\"]*)\"$")
    public void the_search_field_should_have_a_label_with_the_text(String search_field) throws Throwable {
        Assert.assertTrue(Elements.findElement(By.className(macys()?"globalSearchInputField":"aboveNavSearchBox")).getAttribute("placeholder").contains(search_field));
    }

    @Given("^I am on SubSplashPage in \"([^\"]*)\" mode$")
    public void i_am_on_SubSplashPage_in_mode(String mode) throws Throwable {
        if (mode.equalsIgnoreCase("Domestic") || mode.equalsIgnoreCase("Iship")) {
            Home homePage = new Home();
            List<String> mainCategorySite = homePage.getAllMainCategoryNames();
            String mainSubCategory = mainCategorySite.get(2);
            homePage.selectMainCategory(mainSubCategory);
            List<String> subCategorySite = homePage.getAllSubCategoryNames();
            String subSplashCategory = subCategorySite.get(2);
            homePage.selectSubCategory(subSplashCategory);
        } else {
            Home regHome = new Home();
            List<String> mainCategory = regHome.getAllMainCategoryNames();
            String subSplashMainCat = mainCategory.get(1);
            regHome.selectMainCategory(subSplashMainCat);
            List<String> subCategory = regHome.getAllSubCategoryNames();
            String subSplashsubCat = subCategory.get(25);
            regHome.selectSubCategory(subSplashsubCat);
        }
    }

    @Then("^I verify below topnav elements are displayed and return a (\\d+) OK$")
    public void i_verify_topnav_elements_are_displayed(int expectedStatusCode, DataTable expectedTopNav) {
        List<String> expTopNavList = expectedTopNav.asList(String.class);
        String url = null;
        for (String topNav : expTopNavList) {
            expectedStatusCode = topNav.equals("MY ACCOUNT") || topNav.equals("WISH LIST") ? 302
                    : (topNav.equals("STORES & EVENTS") ? 301 : 200);
            switch (topNav) {

                case "STORES & EVENTS":
                    url = Elements.findElement("home.goto_stores").getAttribute("href");
                    break;
                case "COUNTRY FLAG":
                    url = Elements.findElement(By.id("iShip_flag")).findElement(By.tagName("a")).getAttribute("href");
                    break;
                case "CURRENCY":
                    url = Elements.findElement(By.id("bl_nav_account_flag")).findElements(By.tagName("a")).get(1).getAttribute("href");
                    break;
                case "MY ACCOUNT":
                    url = Elements.findElement("home.goto_my_account_link").getAttribute("href");
                    break;
                case "WISH LIST":
                    url = Elements.findElement("home.goto_wishlist").getAttribute("href");
                    break;
                case "BROWN BAG:(0)":
                    url = Elements.findElement("home.goto_shopping_bag").getAttribute("href");
                    break;
                case "SHOPPING BAG ICON":
                    url = Elements.findElement(By.className("topNavBag")).findElement(By.tagName("a")).getAttribute("href");
            }
            try {
                Assert.assertEquals(expectedStatusCode, GeneralUtils.getResponseCode(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Then("^I verify below topnav elements are not displayed$")
    public void i_verify_below_topnav_elements_are_not_displayed(DataTable expectedTopNav) {
        List<String> expTopNavList = expectedTopNav.asList(String.class);
        for (String topNav : expTopNavList) {
            switch (topNav) {
                case "STORES & EVENTS":
                    Assert.assertNull(Elements.findElement("home.goto_stores"));
                    break;
                case "MY ACCOUNT":
                    Assert.assertNull(Elements.findElement("home.goto_my_account_link"));
                    break;
                case "WISH LIST":
                    Assert.assertNull(Elements.findElement("home.goto_wishlist"));
            }
        }
    }

    @Then("^I verify Seasonal Action wrapper (is|is not) displayed$")
    public void i_verify_seasonal_action_wrapper(String action) {
        if (action.equals("is"))
            Assert.assertTrue(Elements.findElement(By.id("seasonalActionOn")).getAttribute("style").equals("display: block;"));
        else {
            Assert.assertNull(Elements.findElement(By.id("seasonalActionOff")));
            Assert.assertNull(Elements.findElement(By.id("seasonalActionOn")));
        }
    }

    @Then("^I click on \"([^\"]*)\" link$")
    public void i_click_on_link(String link) {
        String seasonalActionOffStyle = Elements.findElement(By.id("seasonalActionOff")).getAttribute("style");
        switch (link) {
            case "Seasonal Action wrapper":
                if (seasonalActionOffStyle.equals("display: block;") || seasonalActionOffStyle.equals(""))
                    Clicks.click(Elements.findElement(By.id("seasonalActionOff")));
                else
                    Clicks.click(Elements.findElement(By.id("seasonalActionOn")));
                break;
            case "back to bloomingdales":
                Clicks.click(Elements.findElement(By.id("backtoBloomiesLink")));
        }
    }

    @Then("^I verify that Seasonal Action wrapper is closed$")
    public void i_verify_that_Seasonal_Action_wrapper_is_closed() {
        Assert.assertTrue(Elements.findElement(By.id("seasonalActionOn")).getAttribute("style").equals("display: none;"));
    }

    @When("^I verify links appear in the flyouts$")
    public void verify_links_appear_in_mode() {
        List<WebElement> fobList = Elements.findElement(By.id("globalNavigation")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        fobList.remove(fobList.size() - 1);
        for (WebElement fob : fobList) {
            Assert.assertTrue(fob.findElement(By.tagName("a")).isDisplayed());
        }
    }

    @When("^I should see grid layout in the flyout menu$")
    public void i_should_see_grid_layout_in_the_flyout_menu() {
        Assert.assertTrue(Elements.findElements(By.className(" gridLayout")).size() == 27);
    }

    @When("^I select any character from grid layout$")
    public void i_select_any_character_from_grid_layout() throws DriverNotInitializedException {
        String brandFOB = WebDriverManager.getCurrentUrl().contains("registry") ? "BRANDS" : "DESIGNERS";
        try {
            Clicks.hoverForSelection(Elements.findElement(By.linkText(brandFOB)));
        } catch (Exception e) {
            System.out.println("Unable to hover over quickbag");
            e.printStackTrace();
        }
        List<WebElement> gridList = Elements.findElements(By.className(" gridLayout"));
        Random rmd = new Random();
        try {
            selectedBrandChr = gridList.get(rmd.nextInt(gridList.size())).getText();
        } catch (Exception ee) {
            Actions actions = new Actions(WebDriverManager.getWebDriver());
            actions.moveToElement(Elements.findElement(By.linkText(brandFOB))).perform();
            selectedBrandChr = gridList.get(rmd.nextInt(gridList.size())).getText();
        }
    }

    @And("^I mouse over on the \"([^\"]*)\" fob$")
    public void iMouseOverOnTheBelowFobS(String fob) throws Throwable {
        if (fob.equals("random")) {
            List<WebElement> elements = Elements.findElements(Elements.element("home.category"))
                    .stream()
                    .filter(e -> !e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
            if(bloomingdales()){
                if (HeaderActions.getSiteMode().equalsIgnoreCase("iship")){
                    for(WebElement element : elements){
                        if(element.getText().equalsIgnoreCase("THE REGISTRY")){
                            elements.remove(element);
                            break;
                        }
                    }
                }else if(HeaderActions.getSiteMode().equalsIgnoreCase("registry")){
                    for(WebElement element : elements){
                        if(element.getText().equalsIgnoreCase("SALE")){
                            elements.remove(element);
                            break;
                        }
                    }
                }
            }
            int randomFobIndex = elements.size() <=1 ? 0 : new Random().nextInt(elements.size() - 1);
            randomFob = elements.get(randomFobIndex).getText();
            try {
                Clicks.hoverForSelection(elements.get(randomFobIndex));
            } catch (Exception e) {
                log.debug("Unable to hover over fob:" + fob+". Due to exception:"+e.getMessage());
            }
        } else {
            try {
                Clicks.hoverForSelection(Elements.findElement(By.linkText(fob)));
            } catch (Exception e) {
                log.debug("Unable to hover over fob:" + fob+". Due to exception:"+e.getMessage());
            }
        }
    }

    @Then("^I should see flyout menu for \"([^\"]*)\" fob$")
    public void iShouldSeeFlyoutMenuFor(String fob) throws Throwable {
        String fobName = fob.equals("random") ? randomFob : fob;
        List<WebElement> list = Elements.findElement(By.id("globalNavigation")).findElement(By.tagName("ul")).findElements(By.tagName("li")).stream().filter(i -> i.getAttribute("class").equals("selected")).collect(Collectors.toList());
        Assert.assertTrue(list.get(0).getText().equals(fobName));
    }

    @Then("^I should navigate to specific designer index page in \"([^\"]*)\" mode$")
    public void i_should_navigate_to_specific_designer_index_page(String shoppingMode) {
        String brandFOB = shoppingMode.equals("registry") ? "BRANDS" : "DESIGNERS";
        Clicks.click(Elements.findElement(By.linkText(brandFOB)));
        Clicks.click(Elements.findElement(By.linkText(selectedBrandChr)));
        Assert.assertTrue(!Elements.findElement(By.id("starting_with" + selectedBrandChr)).getAttribute("style").equals(""));
    }

    @Then("^I compare the details of global media in UI with XAPI response in \"([^\"]*)\" mode$")
    public void i_compare_the_details_of_ui_with_xapi_respone(String mode) {
        try {
            String width = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("width");
            String height = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("height");
            String file_name = Elements.findElement("header.nav_banner").findElement(By.tagName("img")).getAttribute("src").split("_pools/")[1];
            String mediaResponse = GeneralUtils.getXAPIResponse(mode);
            JSONObject jsonGlobalMedia = new JSONObject(mediaResponse);
            Assert.assertEquals(jsonGlobalMedia.getJSONObject("media").getJSONObject("HEADER_GNA").getJSONArray("items").getJSONObject(0).get("filePath"), file_name);
            Assert.assertEquals(jsonGlobalMedia.getJSONObject("media").getJSONObject("HEADER_GNA").getJSONArray("items").getJSONObject(0).get("width").toString(), width);
            Assert.assertEquals(jsonGlobalMedia.getJSONObject("media").getJSONObject("HEADER_GNA").getJSONArray("items").getJSONObject(0).get("height").toString(), height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @When("^I verify quick bag text in header for \"([^\"]*)\" user$")
    public void verify_brown_bag_text(String user_type) {
        switch (user_type) {
            case "guest":
                Assert.assertEquals("BROWN BAG: (0)", Elements.findElement("header.quickbag_hover").getText());
                break;
            case "registered":
                UserProfile customer = TestUsers.getCustomer(null);
                String firstName = customer.getUser().getProfileAddress().getFirstName();
                Assert.assertTrue(Elements.findElement("header.quickbag_hover").getText().contains(firstName));
                break;
        }

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

    @When("^I add (\\d+) random product to quick bag in \"([^\"]*)\" mode$")
    public void i_add_random_product_to_quick_bag(int productCount, String mode) throws Throwable {
//        Registry registry = new Registry();
        for (int i = 1; i <= productCount; i++) {
            CommonUtils.navigateToRandomProduct();
            ProductDisplay.selectRandomColor();
            ProductDisplay.selectRandomSize();
            GeneralUtils.clickAddToBagButtonInPDPPage();
            HashMap<String, String> productDetails = new HashMap<>();
            productDetails.put("Product Title", Elements.findElement(By.id("atb_productName")).getText());
            productDetails.put("Price", Elements.findElement(By.className("price")).getText());
            productDetails.put("Quantity", Elements.findElement(By.xpath("//*[@id=\"addToBagPanel\"]/div[1]/div/div/div/div[2]/div/div/div[2]/div[2]")).getText());
            bagContents.put(i, productDetails);
            Clicks.click("product_display.close_add_to_bag_dialog");
            Wait.untilElementNotPresent("product_display.add_to_bag_dialog");

//            if (mode.equals("registry"))
//                registry.I_navigate_to_registry_home_page();
//            else
//                i_navigate_to_home_page();
        }
    }

    @Then("^I should see the added products in quick bag$")
    public void i_should_see_the_added_products_in_quick_bag() {
        int i = 0;
        Utils.threadSleep(5000, "Waiting for quick bab contents");
        //Verify Bag Link in header & footer
        Assert.assertTrue(Elements.findElement(By.className("qb_myBagLink")).getAttribute("href").contains("/bag/index.ognc"));
        Assert.assertTrue(Elements.findElement(By.id("qb_quickBagBottomContent")).findElement(By.className("qb_myBagLink")).getAttribute("href").contains("/bag/index.ognc"));

        //Verify QuickBag Text
        String topBagCountText = Elements.findElement(By.className("qb_myBagLink")).getText();
        String bottomBagCountText = Elements.findElement(By.id("qb_quickBagBottomContent")).findElement(By.className("qb_myBagLink")).getText();
        Assert.assertTrue("ERROR - APP: 'MY BAG:X ITEMS' text is not displaying at top", topBagCountText.contains("MY BAG: ") && topBagCountText.contains(" ITEMS"));
        Assert.assertTrue("ERROR - APP: 'MY BAG:X ITEMS' text is not displaying at bottom", bottomBagCountText.contains("MY BAG: ") && bottomBagCountText.contains(" ITEMS"));

        //verify Checkout Button:
        Assert.assertTrue(Elements.findElement(By.id("qb_checkoutButton")).isDisplayed());
        int bagSize = bagContents.keySet().size();
        //Verify quickbag product details:
        List<WebElement> itemList = Elements.findElement(By.id("qb_quickBagItemsWrapper")).findElement(By.className("qb_quickBagItemsContainer")).findElements(By.className("quickBagItemContainer"));
        for (WebElement qb_product : itemList) {
            String actualPrice = null;
            try {
                if (qb_product.findElement(By.className("qb_priceHighlight")).isDisplayed()) {
                    actualPrice = qb_product.findElement(By.className("qb_priceHighlight")).getText().split(":")[1];
                }else{
                    actualPrice = qb_product.findElement(By.className("qb_price")).getText().split(":")[1];
                }
            } catch (Exception ee) {
                actualPrice = qb_product.findElement(By.className("qb_price")).getText().split(":")[1];
            }

            Assert.assertEquals(bagContents.get(bloomingdales() ? i+1 : bagSize).get("Price"), actualPrice.trim());
            Assert.assertEquals(bagContents.get(bloomingdales() ? i+1 : bagSize).get("Product Title"), qb_product.findElement(By.className("qb_itemImageContainer")).findElement(By.tagName("img")).getAttribute("title").trim());
            Assert.assertTrue(qb_product.findElement(By.className("qb_quickBagEditButton")).isDisplayed());
            Assert.assertTrue(qb_product.findElement(By.className("quickBagRemoveButton")).isDisplayed());
            i++;
            bagSize--;
        }


    }

    @Then("^I should see currency changed from \"([^\"]*)\" mode to other country in quick bag$")
    public void i_should_see_currency_change_from_one_to_another(String mode) {
        Wait.untilElementPresent(By.id("qb_quickBagItemsWrapper"));
        List<WebElement> itemList = Elements.findElement(By.id("qb_quickBagItemsWrapper")).findElement(By.className("qb_quickBagItemsContainer")).findElements(By.className("quickBagItemContainer"));
        for (WebElement qb_product : itemList) {
            String actualPrice = null;
            try {
                if (qb_product.findElement(By.className("qb_priceHighlight")).isDisplayed())
                    actualPrice = qb_product.findElement(By.className("qb_priceHighlight")).getText().split(":")[1];
                else
                    actualPrice = qb_product.findElement(By.className("qb_price")).getText().split(":")[1];
            } catch (Exception ee) {
                actualPrice = qb_product.findElement(By.className("qb_price")).getText().split(":")[1];
            }
            if (mode.equals("site"))
                Assert.assertTrue(actualPrice.contains("INR"));
            else
                Assert.assertTrue(actualPrice.contains("$"));
        }
    }

    @Then("^I verify that header global banner is not displayed$")
    public void     i_verify_that_header_global_banner_is_not_displayed() throws Throwable {

        Assert.assertTrue("ERROR - DATA: Header global banner is displayed in iship mode",
                (Elements.findElement("header.header_pool").findElements(By.tagName("a")).isEmpty()));
    }

    @And("^I verify that footer global banner is not displayed$")
    public void i_verify_that_footer_global_banner_is_not_displayed() throws Throwable {

        Assert.assertFalse("ERROR - DATA: Footer global banner is displayed in iship mode",
                (Elements.elementPresent("footer.footer_banner")));
    }

    @And("^I verify that \"([^\"]*)\" default message is displayed in new search box$")
    public void iShouldSeeDefaultMessageInnewSearchBox(String keyword) throws Throwable {
        try {
            Wait.forPageReady();
            String keywordPlaceHolder = Elements.findElement("home.search_field").getAttribute("placeholder").trim();
            Assert.assertTrue("Expected default message in search box is not displayed: " + keyword + " Actual displayed keyword place holder: " + keywordPlaceHolder,
                    keyword.equalsIgnoreCase(keywordPlaceHolder));
        } catch (Exception e) {
            Assert.fail("Expected keyword: " + keyword + " is not displayed" + e.getMessage());
        }
    }

    @And("^I should see carat symbols beside MANAGE and MY ACCOUNT Dropdowns$")
    public void i_should_see_caratsymbols_beside_manageand_myaccount_dropdowns() throws Throwable {
        try {
            WebElement managecarat_symbol = Elements.findElement("registry_home.manage_registry_carat_symbol");
            WebElement GiftsCarat_symbol = Elements.findElement("header.gifts_carat_symbol");
            if (isElementVisible(managecarat_symbol)) {
                Elements.elementPresent("header.manage_registry_carat_symbol");
            }
            if (isElementVisible(GiftsCarat_symbol)) {
                Elements.elementPresent("header.gifts_carat_symbol");
            }
            Elements.elementPresent("header.myaccount_carat_symbol");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed() ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    @And("^I click on the new bag icon$")
    public void I_click_on_the_new_bag_icon() throws Throwable {
        try {
            Clicks.click("header.shopping_bag_icon");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^I should see sign out link on the new header and footer$")
    public void i_should_see_signout_link_on_theNewheaderandfooter() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.goto_sign_out_link");
    }

    @Then("^I verify macys logo and minimal bag in minimal header$")
    public void I_verify_minimal_header() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.minimal_header_logo");
        Elements.elementPresent("header.minimal_header_bag");
    }

    @When("^I navigate to beauty box subscriptions page$")
    public void i_navigate_to_beauty_box_subscriptions_page() throws Throwable {
        Wait.forPageReady();
        String subscriptions_url = RunConfig.url + "/subscription/beauty-box";
        Navigate.visit(subscriptions_url);
    }

    @Then("^I verify red star appears in a white bag$")
    public void i_verify_red_star_appears_in_white_bag() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.red_star_in_shopping_bag");
    }

    @Then("^I verify red star disappears and red bag appears$")
    public void i_verify_red_star_disappears_and_red_bag_appears() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.red_shopping_bag");
    }

    @And("^I Should see welcome message on the right corner of the page on link rail$")
    public void i_should_see_usernameon_the_right_Corner_of_the_page() throws Throwable {
        Wait.forPageReady();
        String Username_profile = Elements.findElement("create_profile.verify_page").getAttribute("value");
        Assert.assertEquals(Elements.findElement(By.xpath("//div[@id='globalUserName']/div/span")).getText(), "HI, " + Username_profile);
    }

    @When("I click on \"([^\"]*)\" link in Link rail$")
    public void i_click_on_link_rail_elements(String linkrail_element) throws Throwable {
        Wait.forPageReady();
        switch (linkrail_element) {

            case "SIGN IN":
                Wait.secondsUntilElementPresent("header.goto_sign_in_link", 10);
                Clicks.click("header.goto_sign_in_link");
                break;
            case "SIGN OUT":
                Wait.secondsUntilElementPresent("header.goto_sign_out_link", 10);
                Clicks.click("header.goto_sign_out_link");
                break;

            case "My Account":
            case "MY ACCOUNT":
                Clicks.click("header.goto_my_account_link");
                break;

            case "MY MACY'S CREDIT CARD":
                GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
                Clicks.click("header.credit_card");
                break;

            case "MY ORDER HISTORY":
                GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
                Clicks.click("header.order_history");
                break;

            case "MY PROFILE":
                GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
                Clicks.click("header.goto_myprofile");
                break;

            case "MY WALLET":
                GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
                Clicks.click("header.goto_mywallet");
                break;

            case "MY PLENTI":
                GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
                Clicks.click("header.goto_myplenti");
                break;

            case "MY LISTS":
                GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
                Clicks.click("header.goto_wishlist");
                break;

            case "STORES":
                Clicks.click("header.goto_stores");
                break;

            case "WEDDING REGISTRY":
                Clicks.click("header.goto_wedding_registry");
                break;

            case "SHIPPING TO":
                Clicks.click("header.shipping_to_link");
                break;

            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + linkrail_element);
        }
    }

    @When("^I click on \"([^\"]*)\" link in Link rail for registry$")
    public void i_click_on_element_link_in_link_rail_for_registry(String registry_linkrail_element) throws Throwable {
        Wait.forPageReady();
        switch (registry_linkrail_element) {
            case "REGISTRY MANAGER":
                if (macys() && Elements.elementPresent("header.manage_registry_carat_symbol"))
                    Clicks.hoverForSelection("header.manage_registry_carat_symbol");
                Wait.untilElementPresent("header.goto_registry_manager");
                Elements.findElement("header.goto_registry_manager").click();
                Wait.forPageReady();
                break;

            case "VIEW REGISTRY":
                if (macys() && Elements.elementPresent("header.manage_registry_carat_symbol"))
                    Clicks.hoverForSelection("header.manage_registry_carat_symbol");
                Wait.untilElementPresent("header.goto_view_registry");
                Elements.findElement("header.goto_view_registry").click();
                Wait.forPageReady();
                break;

            case "FIND REGISTRY":
                Clicks.click("registry_home.goto_find_registry");
                Wait.forPageReady();
                break;

            case "CREATE REGISTRY":
                Clicks.click("registry_home.create_registry");
                Wait.forPageReady();
                break;

            case "BENEFITS":
                Wait.forPageReady();
                GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
                Wait.forPageReady();
                Clicks.click("registry_home.goto_benefits");
                break;

            case "HELP":
                Clicks.click("registry_home.help");
                Wait.forPageReady();
                break;

            case "STORES":
                Wait.forPageReady();
                Clicks.click("header.goto_stores");
                Wait.forPageReady();
                break;
            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + registry_linkrail_element);
        }
    }

    @When("^I navigate to \"([^\"]*)\" subsplash page from \"([^\"]*)\"$")
    public void I_navigate_to_the_browse_page_under(String subcategory, String category) throws Throwable {
        Wait.forPageReady();
        Home homePage = new Home();
        pausePageHangWatchDog();
        homePage.selectMainCategory(category);
        shouldBeOnPage("category_splash");
        homePage.selectSubCategory(subcategory);
        resumePageHangWatchDog();
        log.info("Navigated to sub category page from main Category");

    }

    @And("^I navigate to credit benefits page$")
    public void i_navigate_to_credit_benefits_page() throws Throwable {
        Wait.forPageReady();
        Clicks.click("navigation.goto_cardholder_benefits");
        log.info("Navigated to Credit Benifits page");
    }

    @And("^I navigate to Apply & Learn More page$")
    public void i_navigate_to_applyandlearnmorepages() throws Throwable {
        Wait.forPageReady();
        Clicks.click("navigation.goto_apply_and_learn_more");
        log.info("Navigated to Apply & Learn More page");
    }

    @Then("^I verify Media rail appears on top of the global header$")
    public void i_verify_mediarail_appears_ontop_ofthe_globalheader() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.media_rail");
    }

    @Then("^I verify advertisements appears on mediarail$")
    public void i_verify_two_adverstisements_are_displayed_on_top_of_theglobalheader() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.header_pool");
        Elements.elementPresent("header.header_pool_left");
    }

    @Then("^I verify the two ad pools on media rail$")
    public void i_verifytwoAddPools() throws Throwable {
        Clicks.hoverForSelection("header.find_out_more");
        Wait.forPageReady();
        String url = WebDriverManager.getCurrentUrl();
        int href_code_adpool = RESTOperations.doGET(url, null).getStatus();
        if (href_code_adpool != 200) {
            Assert.fail("Response code for href is not 200" + href_code_adpool);
        }
    }

    @And("^I verify link rail appears below the media rail$")
    public void i_verify_link_rail_appears_below_the_media_rail() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.link_rail");
    }

    @Then("^I should see \"([^\"]*)\" to the left of the link rail$")
    public void i_should_see_5_clickbale_elements_to_the_left_of_the_link_rail(String linkrail_domestic) throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Error - App : link rail element" + linkrail_domestic + "is not displayed", Elements.findElement(By.xpath(".//div[@class='redesign-header-wrapper']/section[@id='link-rail']/div/div[1]/nav/ul/li/a[contains(text(),'" + linkrail_domestic + "')]")).isDisplayed());
    }

    @Then("^I should see \"([^\"]*)\" to the left of the link rail for registry$")
    public void i_should_see_5_clickbale_elements_to_the_left_of_the_link_rail_for_registry(String linkrail_registry) throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Error - App : link rail element" + linkrail_registry + "is not displayed", Elements.findElement(By.xpath(".//div[@class='redesign-header-wrapper']/section[@id='link-rail']/div/div/div[1]/nav/ul/li/a[contains(text(),'" + linkrail_registry + "')]")).isDisplayed());
    }

    @When("^I hover on Gifts link")
    public void i_hover_on_gifts_link() throws Throwable {
        Wait.forPageReady();
        Clicks.hoverForSelection("header.gift_dropdown");
    }

    @Then("^I Should see dropdown with two clickable elements for Gifts$")
    public void i_should_see_dropdown_with_two_clickable_elements_for_gifts() throws Throwable {
        Wait.forPageReady();

    }

    @Then("^I should see \"([^\"]*)\" to the left of link rail$")
    public void i_should_see_3_clickable_elements_to_the_left_of_the_link_rail(String linkrail_iship) throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Error - App : link rail element" + linkrail_iship + "is not displayed", Elements.findElement(By.xpath(".//*[@id='link-rail']/div/div[1]/nav/ul/li/a[contains(text(),'" + linkrail_iship + "')]")).isDisplayed());
    }

    @And("I verify the \"([^\"]*)\" links pages are rendered properly in \"([^\"]*)\" mode$")
    public void i_verify_the_5_elements_pages_are_rendered_properly(String linkrail_dom_element, String selected_mode) throws Throwable {
        Wait.forPageReady();
        if (selected_mode.equalsIgnoreCase("Domestic")) {
            Assert.assertTrue("Error - App : link rail element" + linkrail_dom_element + "is not displayed", Elements.findElement(By.xpath(".//*[@id='link-rail']/div/div[1]/nav/ul/li/a[contains(text(),'" + linkrail_dom_element + "')]")).isDisplayed());
            switch (linkrail_dom_element) {
                case "STORES":
                    Clicks.click("header.goto_stores");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: Stores link did not opened'", title().contains("Our Stores"));
                    break;

                case "DEALS":
                    Clicks.click("header.goto_deals_promotions");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: Deals link did not opened'", title().contains("Deals and Promotions"));
                    break;

                case "LISTS":
                    Clicks.click("header.goto_wishlist");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: LISTS link did not opened'", title().contains("Macy's Lists"));
                    break;

                case "GIFTS":
                    Clicks.hoverForSelection("header.gift_dropdown");
                    Wait.forPageReady();
                    Elements.findElement(By.xpath(".//*[@id='link-rail']/div/div[1]/nav/ul/li[4]/ul/li/a[contains(text(),\"Father's Day Gift Guide\")]"));
                    Elements.findElement(By.xpath(".//ul[@class='dropdown-list dropdown-list-align-left']/li/a[contains(text(),'Gift Cards')]"));
                    Wait.forPageReady();
                    break;

                case "WEDDING REGISTRY":
                    Clicks.click("header.goto_wedding_registry");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: WEDDING REGISTRY link did not opened'", title().contains("Wedding Registry"));
                    break;

                default:
                    throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + linkrail_dom_element);
            }
        }
        if (selected_mode.equalsIgnoreCase("Registry")) {
            switch (linkrail_dom_element) {
                case "STORES":
                    Clicks.click("header.goto_stores");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: Stores link did not opened'", title().contains("Our Stores - Macy's"));
                    break;

                case "FIND REGISTRY":
                    Clicks.click("registry_home.goto_find_registry");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: Deals link did not opened'", title().contains("Macy's Registry"));
                    break;

                case "CREATE REGISTRY":
                    Clicks.click("registry_home.create_registry");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: LISTS link did not opened'", title().contains("Registry Manager"));
                    break;

                case "MANAGE":
                    Clicks.hoverForSelection(By.xpath("registry_home.manage_registry"));
                    Wait.forPageReady();
                    break;

                case "HELP":
                    Clicks.click("registry_home.help_link");
                    Wait.forPageReady();
                    break;

                default:
                    throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + linkrail_dom_element);
            }
        }
        if (selected_mode.equalsIgnoreCase("Iship")) {
            switch (linkrail_dom_element) {
                case "STORES":
                    Clicks.click("header.goto_stores");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: Stores link did not opened'", title().contains("Our Stores - Macy's"));
                    break;

                case "WEDDING REGISTRY":

                    break;

                case "SHIPPING TO":
                    Clicks.click("header.shipping_to_link");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: LISTS link did not opened'", title().contains("Macy's"));
                    break;

                default:
                    throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + linkrail_dom_element);
            }
        }
    }

    @And("^I hover on manage link rail element$")
    public void i_hover_on_manage_link_rail_element() throws Throwable {
        GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
        Wait.forPageReady();
    }

    @And("I should see 4 clickable elements from the dropdown$")
    public void i_should_see_4_links_from_manage_dropdown() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("registry_home.goto_registry_manager");
        Elements.elementPresent("registry_home.view_registry");
        Elements.elementPresent("header.goto_checklist");
        Elements.elementPresent("registry_home.goto_benefits");
    }

    @Then("I should see 2 clickable to the right of the link rail$")
    public void i_should_see_2_clickable_links_tothe_right_of_the_link_rails() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.goto_sign_in_link");
        Elements.elementPresent("header.goto_my_account_link");
    }

    @Then("I should not see any clickable elements on the right of the link rail$")
    public void i_should_not_see_any_clickable_elements_on_the_right_of_the_link_rails() throws Throwable {
        Wait.forPageReady();
        Assert.assertFalse("ERROR APP: sign in link should not be visible", Elements.elementPresent("header.goto_sign_in_link"));
        Assert.assertFalse("ERROR APP: sign in link should not be visible", Elements.elementPresent("header.goto_my_account_link"));
    }

    @Then("^I Should see Shop by Department dropdown menu on the left side$")
    public void iShouldSeeShopByDepartmentDropdownMenuOnTheLeftSide() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.shopbydepartment");
        Assert.assertTrue(Elements.findElement("header.shopbydepartment").getText().equalsIgnoreCase("SHOP BY DEPARTMENT"));
    }

    @Then("^I Should see Shop Registry dropdown menu on the left side$")
    public void iShouldSeeShopRegistryDropdownMenuOnTheLeftSide() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.shopbydepartment");
        Assert.assertTrue(Elements.findElement("header.shopbydepartment").getText().equalsIgnoreCase("SHOP REGISTRY"));
    }

    @And("^I Should see Search box to the right of the Search & Category Rail$")
    public void i_should_see_searchbox_in_between_the_macys_logo_and_bag() throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("home.search_field");
    }

    @And("^I Should see bag icon on top right corner of the search rail on header$")
    public void i_should_see_bag_icon_on_top_right_corner_of_the_search_rail() throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("quick_bag.quickbag_header");
    }

    @And("^I verify category rail is not displayed$")
    public void i_verify_category_rail_not_displayed() throws Throwable {
        Assert.assertFalse("ERROR - DATA: Category rail is still displayed", Elements.elementPresent("header.category-rail"));
    }

    @And("^I verify media rail is not displayed$")
    public void i_verify_media_rail_not_displayed() throws Throwable {
        Assert.assertFalse("ERROR - DATA: Media rail is still displayed", Elements.elementPresent("header.media_rail"));
    }

    @And("^I verify search rail is not displayed$")
    public void i_verify_search_rail_not_displayed() throws Throwable {
        Assert.assertFalse("ERROR - DATA: Search rail is still displayed", Elements.elementPresent("header.search_rail_header"));
    }

    @And("^I verify link rail is not displayed$")
    public void i_verify_link_rail_not_displayed() throws Throwable {
        Assert.assertFalse("ERROR - DATA: Link rail is still displayed", Elements.elementPresent("header.link_rail"));
    }

    @And("^I verify new search rail is displayed$")
    public void i_verify_new_serach_rail_is_displayed() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.search_rail_header");
    }

    @And("^I verify new media rail is displayed$")
    public void i_verify_new_media_rail_is_displayed() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.media_rail");
    }

    @And("^I verify new category rail is displayed$")
    public void i_verify_new_category_rail_is_displayed() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.category_rail");
    }

    @And("^I verify new link rail is displayed$")
    public void i_verify_new_link_rail_is_displayed() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.link_rail");
    }

    @And("^I Should see Help link on the link rail and click on it$")
    public void i_should_See_help_link_onthe_link_rail_on_registry_mode() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("registry_home.help", 25);
        Clicks.click("registry_home.help");
        Wait.forPageReady();
    }

    @Then("I Should see need help popout window$")
    public void i_should_see_need_help_popout_window() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.help_overlay");
    }

    @And("^I close the help overlay$")
    public void i_close_the_help_overlay() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("header.help_overlay_close_button");
        Clicks.click("header.help_overlay_close_button");
    }

    @When("^I hover on My Account element on link rail$")
    public void i_hover_on_my_account_element_on_link_rail() throws Throwable {
        Wait.forPageReady();
        GeneralUtils.hoverSelection(Elements.findElement("header.goto_my_account_link"));
    }

    @Then("I verify the My Account Pages \"([^\"]*)\" are rendered properly in \"([^\"]*)\" Mode$")
    public void i_verify_myaccount_pages_are_rendered_properly(String linkrail_right_element, String selectmode) throws Throwable {
        Wait.forPageReady();
        if (selectmode.equalsIgnoreCase("Domestic") || selectmode.equalsIgnoreCase("Registry")) {
            switch (linkrail_right_element) {
                case "MY MACY'S CREDIT CARD":
                    Clicks.click("header.goto_macys_credit_card");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: macys credit card did not opened'", title().contains("Credit Gateway"));
                    break;

                case "MY ORDER HISTORY":
                    Clicks.click("header.goto_order_history");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: Order history page did not displayed'", title().contains("My Order Status"));
                    break;

                case "MY PROFILE":
                    Clicks.click("header.goto_myprofile");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: My Profile page did not displayed'", title().contains("Profile"));
                    break;

                case "MY WALLET":
                    Clicks.click("header.goto_mywallet");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: My Wallet page did not displayed'", title().contains("My Wallet"));
                    break;

                case "MY PLENTI":
                    Clicks.click("header.goto_myplenti");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: My Plenti page didnot displayed'", title().contains("Plenti"));
                    break;

                case "MY LISTS":
                    Clicks.click("header.goto_mylist");
                    Wait.forPageReady();
                    Assert.assertTrue("'ERROR - APP: My Lists page did not displayed'", title().contains("Lists"));
                    break;

                default:
                    throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + linkrail_right_element);
            }
        }
    }

    @When("^I navigate to loyality enroll page$")
    public void i_navigate_to_loyality_enroll_page() throws Throwable {
        Wait.forPageReady();
        String loyality_enroll_url = RunConfig.url + "/loyalty/enroll";
        Navigate.visit(loyality_enroll_url);
    }

    @When("^I navigate to mobile app campaign page$")
    public void i_navigate_to_mobile_app_campaign_page() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("footer.goto_mobile_apps");
        Clicks.javascriptClick("footer.goto_mobile_apps");
    }

    @When("^I click on champion brand under Brands category$")
    public void i_click_on_champion_brand_under_Brands() throws Throwable {
        Wait.forPageReady();
        Clicks.click("header.brand_champion");
    }

    @When("I click on My Account \"([^\"]*)\" in \"([^\"]*)\" Mode$")
    public void i_verify_new_header_redesign_implementation_for_my_account_pages(String selectmode, String linkrail_right_element) throws Throwable {
        Wait.forPageReady();
        if (selectmode.equalsIgnoreCase("Domestic") || selectmode.equalsIgnoreCase("Registry")) {
            Wait.untilElementPresent("header.goto_my_account_link");
            switch (linkrail_right_element) {
                case "My Macy's Credit Card":
                    Clicks.click("header.credit_card");
                    Wait.forPageReady();
                    break;

                case "My Order History ":
                    Clicks.click("header.order_history");
                    Wait.forPageReady();
                    break;

                case "My Profile":
                    Clicks.click("header.profile");
                    Wait.forPageReady();
                    break;

                case "My Wallet":
                    Clicks.hoverForSelection("header.goto_mywallet");
                    Wait.forPageReady();
                    break;

                case "My Plenti":
                    Clicks.click("header.goto_myplenti");
                    Wait.forPageReady();
                    break;

                case "My Lists":
                    Clicks.click("header.goto_mylist");
                    Wait.forPageReady();
                    break;

                default:
                    throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + linkrail_right_element);
            }
        }
    }

    /**
     * Navigates to the registry home page from top nav
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to find registry page from top nav$")
    public void I_navigate_to_find_registry_page_from_top_nav() throws Throwable {
        Wait.forPageReady();
        Clicks.click("registry_home.goto_find_registry");
    }

    /**
     * Navigates to the registry home page from top nav
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to registry home page from top nav$")
    public void I_navigate_to_registry_home_page_from_top_nav() throws Throwable {
        Wait.forPageReady();
        Clicks.click("registry_home.goto_find_registry");
    }

    @Then("^I verify New Header Redesign implementation for clean in \"([^\"]*)\" mode$")
    public void i_verify_new_header_redesign_for_clean_implementation(String mode) throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("header.link_rail");
        Elements.elementShouldBePresent("header.search_rail");
        Elements.elementShouldBePresent("header.category_rail");
        switch (mode) {
            case "domestic":
                Elements.elementShouldBePresent("header.goto_stores");
                Elements.elementShouldBePresent("header.goto_deals_promotions");
                Elements.elementShouldBePresent("header.goto_wishlist");
                Elements.elementShouldBePresent("header.goto_wedding_registry");
                Elements.elementShouldBePresent("header.gift_dropdown");
                Elements.elementShouldBePresent("header.goto_sign_in_link");
                Elements.elementShouldBePresent("header.goto_my_account_link");
                Header.iverifyDynamicTopNavigationInDifferentModes(mode);
                break;
            case "registry":
                Elements.elementShouldBePresent("registry_home.goto_find_registry");
                Elements.elementShouldBePresent("registry_home.manage_registry_dropdown");
                Elements.elementShouldBePresent("registry_home.help");
                Elements.elementShouldBePresent("header.goto_sign_in_link");
                Elements.elementShouldBePresent("header.goto_my_account_link");
                Header.iverifyDynamicTopNavigationInDifferentModes(mode);
                break;
            case "iship":
                Elements.elementShouldBePresent("header.goto_stores");
                Elements.elementShouldBePresent("header.goto_wedding_registry");
                Elements.elementShouldBePresent("header.shipping_to_link");
                Header.iverifyDynamicTopNavigationInDifferentModes(mode);
                break;
            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + mode);
        }
    }

    @When("I visit the stores page$")
    public void i_visit_the_stores_page() throws Throwable {
        Wait.forPageReady();
        Clicks.click("header.goto_stores");
    }

    @When("I visit the wish list page$")
    public void i_visit_the_wish_list_page() throws Throwable {
        Wait.forPageReady();
        Clicks.click("header.goto_wishlist");
    }

    @And("^I navigate to the order history page$")
    public void i_navigate_to_the_order_history_page() throws Throwable {
        if (macys()) {
            Clicks.hoverForSelection("home.goto_my_account_link");
            Wait.untilElementPresent("home.goto_order_status");
            Elements.findElement("home.goto_order_status").click();
        } else {
            Clicks.click("search.order_history");
        }
    }

    @When("^I navigate to Registry Guide Pages$")
    public void i_navigate_to_registry_guide_page() throws Throwable {
        Clicks.hoverForSelection("registry_home.manage_registry");
        Wait.forPageReady();
        Clicks.click("header.goto_checklist");
    }

    @When("^I click on registry pages \"([^\"]*)\"$")
    public void i_verify_new_header_redesign_implementation_for_registry_pages(String registry_dropdown_item) throws Throwable {
        Wait.forPageReady();
        switch (registry_dropdown_item) {
            case "REGISTRY MANAGER":
                Clicks.click("registry_home.goto_registry_manager");
                Wait.forPageReady();
                break;

            case "VIEW REGISTRY":
                Clicks.click("registry_home.view_registry");
                Wait.forPageReady();
                break;

            case "REGISTRY GUIDE":
                Clicks.click("registry_home.goto_registryguide");
                Wait.forPageReady();
                break;

            case "plete ":
                Clicks.hoverForSelection("registry_home.goto_benefits");
            case "BENEFITS":
                Clicks.click("registry_home.goto_benefits");
                Wait.forPageReady();
                break;

            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid argument passed" + registry_dropdown_item);
        }
    }

    @And("^I verify New Header Redesign implementation for radical in current page$")
    public void i_verify_new_header_redesign_implementation_for_radical_in_radical() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.link_rail");
        Elements.elementPresent("header.search_category_rail");
    }

    @When("^I navigate to wedding registry by clicking registry link$")
    public void I_navigate_to_wedding_Registry_by_clickingonit() throws Throwable {
        Clicks.click("header.goto_wedding_registry");
        Wait.forPageReady();
    }

    @Then("^call content service to get global pool response for domestic mode$")
    public void callContentServiceToGetGlobalPoolResponseForDomesticMode(List<Map<String, String>> globalPool) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Map response = null;
        //String response = null;
        String poolName;
        for (Map<String, String> poolData : globalPool) {
            poolName = poolData.get("global_pool");
            response = GeneralUtils.getHeaderMediaResponse(poolName);

        }
    }

    @Then("^I verify the display of GNA$")
    public void i_verify_the_display_of_gna() throws Throwable {
        Wait.forPageReady();
        if (WebDriverManager.getCurrentUrl().contains("/shop/product")) {
            Navigate.scrollPage(0, 100);
            Navigate.scrollPage(0, -100);
            Wait.untilElementPresent("header.header_pool");
        }
        Assert.assertTrue("GNA banner not displayed", Elements.findElement("header.header_pool").isDisplayed());
    }

    @And("^I verify same GNA count on current page$")
    public void i_verify_same_GNA_count_on_home_page() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("GNA banner not displayed", Elements.findElement("home.header_pool").isDisplayed());
        List<WebElement> gna_current = Elements.findElement("home.header_pool").findElements(By.tagName("img"));
        int gna_count = gna_current.stream().map(ele -> ele.getAttribute("src")).collect(Collectors.toList()).size();
        log.info("gna_home_count: " + gna_count);
        Assert.assertEquals("GNA count mismatched in both pages", gna_home_count, gna_current.size());
    }

    @Then("^I should see Ajax call to navapp for \"([^\"]*)\" in \"([^\"]*)\" mode$")
    public void i_should_see_ajax_call_to_navapp_in_different_modes(String category, String mode) throws Throwable {
        Wait.forPageReady();
        switch (category) {
            case "flyout":
                switch (mode) {
                    case "site":
                        String current_url = WebDriverManager.getCurrentUrl();
                        int Http_href_code = RESTOperations.doGET(current_url, null).getStatus();
                        if (Http_href_code == 404) {
                            Assert.fail("Response code for href is not 200" + Http_href_code);
                        }
                    case "iship":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        String current_iship_url = WebDriverManager.getCurrentUrl();
                        int Http_href__iship_code = RESTOperations.doGET(current_iship_url, null).getStatus();
                        if (Http_href__iship_code == 404) {
                            Assert.fail("Response code for href is not 200" + Http_href__iship_code);
                        }
                        break;
                    case "registry":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        String current_registry_url = WebDriverManager.getCurrentUrl();
                        int Http_href__reg_code = RESTOperations.doGET(current_registry_url, null).getStatus();
                        if (Http_href__reg_code == 404) {
                            Assert.fail("Response code for href is not 200" + Http_href__reg_code);
                        }
                        break;
                }
                break;
            case "topnav":
                switch (mode) {
                    case "site":
                        String current_url = WebDriverManager.getCurrentUrl();
                        int Http_href_code = RESTOperations.doGET(current_url, null).getStatus();
                        if (Http_href_code == 404) {
                            Assert.fail("Response code for href is not 200" + Http_href_code);
                        }
                    case "iship":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        String current_iship_url = WebDriverManager.getCurrentUrl();
                        int Http_href__iship_code = RESTOperations.doGET(current_iship_url, null).getStatus();
                        if (Http_href__iship_code == 404) {
                            Assert.fail("Response code for href is not 200" + Http_href__iship_code);
                        }
                        break;
                    case "registry":
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        page.I_visit_web_site_as_a_guest_user_in_mode(mode);
                        String current_registry_url = WebDriverManager.getCurrentUrl();
                        int Http_href__reg_code = RESTOperations.doGET(current_registry_url, null).getStatus();
                        if (Http_href__reg_code == 404) {
                            Assert.fail("Response code for href is not 200" + Http_href__reg_code);
                        }
                        break;
                }
                break;
        }
    }

    @When("^I add a random product to my bag and checkout$")
    public void iAddARandomProductToBag() throws Throwable {
        CommonUtils.navigateToRandomProduct();
        boolean addedToBag = false;
        Assert.assertFalse("ERROR - DATA : Product ( " + (recentProduct == null ? "" : String.valueOf(recentProduct.id)) + " ) is unavailable on product display page!!", !Elements.elementPresent("product_display.add_to_bag_button") && Elements.elementPresent("product_display.availability_error"));
        try {
            Wait.forPageReady();
            int retries = 5;
            for (int count = 0; count < retries && !addedToBag; count++) {
                try {
                    ProductDisplay.selectRandomColor();
                    ProductDisplay.selectRandomSize();
                    Clicks.click("product_display.add_to_bag_button");
                    if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                        Clicks.clickIfPresent("product_display.add_to_bag_button");
                    }
                    if (MEW() && macys() && !onPage("add_to_bag")) {
                        Clicks.clickIfPresent("home.close_app_banner");
                        Clicks.click("home.my_bag");
                        return;
                    }
                    addedToBag = ProductDisplay.addedToBag();
                    if (RunConfig.debugMode) {
                        System.out.println("IsProductAddedToBag:" + addedToBag + ", Add to bag retry count:" + (count + 1));
                    }
                } catch (Exception e) {
                    System.err.println("Exception while adding product:" + e.getMessage());
                }
            }
            Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog");
            if (Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                Clicks.clickIfPresent("add_to_bag_dialog.add_to_bag_close_dialog");
            }
            if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                Clicks.clickIfPresent("product_display.technical_error");
            }
            if (isErrorPaneVisible()) {
                Clicks.click("home.popup_close");
            }
            Clicks.click("add_to_bag_dialog.add_to_bag_checkout");
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.err.println("Error while adding to bag: " + e);
        } finally {
            if (!addedToBag) {
                Wait.untilElementNotPresent("product_display.add_to_bag_button");
                if (macys()) {
                    Assert.assertFalse("ERROR - DATA : Given item is unavailable!!", Elements.elementPresent(By.className("css-tooltip")) && Elements.getText(By.className("css-tooltip")).contains("this item is unavailable"));
                }
                Assert.assertTrue("Unable to add product to bag", ProductDisplay.addedToBag());
            }

        }
    }

    @When("^I navigate to new stores page$")
    public void i_navigate_to_new_stores_page() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent(macys() ? "home.goto_our_stores" : "home.goto_stores");
        Clicks.click(macys() ? "home.goto_our_stores" : "home.goto_stores");
    }

    @When("^I navigate to ways to shop page$")
    public void i_navigate_to_ways_to_shop_page() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent(macys() ? "home.goto_our_stores" : "home.goto_ways_to_shop");
        Clicks.click(macys() ? "home.goto_our_stores" : "home.goto_ways_to_shop");
        if (!WebDriverManager.getCurrentUrl().contains(RunConfig.url)) {
            Navigate.visit(RunConfig.url + "/b/about-us/store-events-ways-to-shop/");
        }
    }

    @Then("^I should see respective category page$")
    public void iShouldSeeRespectiveCategoryPages() throws Throwable {
        Wait.forPageReady();
        List<WebElement> fobList = WebDriverManager.getCurrentUrl().contains("macys") ? Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.className("fob")) : Elements.findElement("header_and_footer.categories").findElement(By.tagName("ul")).findElements(By.tagName("li"))
                .stream()
                .filter(e -> !e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
        int count = 0;
        String current_category_url = null;
        current_category_url = WebDriverManager.getCurrentUrl();
        for (int i = 0; i < fobList.size(); i++) {
            if ((current_category_url.toLowerCase().contains(fobList.get(i).getText().toLowerCase()))
                    || (current_category_url.toLowerCase().contains(fobList.get(i).getText().toLowerCase().split(" ")[0]))) {
                count++;
                log.info("Successfully navigated to respective category page");
                break;
            }
        }
        if (current_category_url.toLowerCase().contains("shop/")) {
            log.info("Successfully navigated to respective category page");
        }
        if (count == 0) {
            Assert.fail("Doesn't navigate to respective category page");
        }
    }

    @Then("^I should see new header and footer elements section in \"([^\"]*)\"$")
    public void i_should_see_new_header_and_footer_elements_sections(String modes) throws Throwable {
        sitemode = modes;
        Wait.forPageReady();
        Elements.elementPresent("home.user_menu_container");
        Elements.elementPresent("home.search_menu_container");
        Elements.elementPresent("home.category_menu_container");
        Elements.elementPresent("home.footer_menu_section");
        if ((sitemode.contentEquals("Domestic")) || (sitemode.contentEquals("Registry"))) {
            Assert.assertTrue("Error -app : credit services link should be present", Elements.elementPresent("home.goto_credit_services"));
            Assert.assertTrue("Error -app : credit card bill pay link should be present", Elements.elementPresent("home.goto_credit_pay_bill_online"));
            Assert.assertTrue("Error -app : credit card holder benefits should be present", Elements.elementPresent("home.goto_credit_cardholder_benefits"));
            Assert.assertTrue("Error -app : credit card apply now should be present", Elements.elementPresent("home.home.goto_credit_apply_now"));
            if (macys()) {
                Assert.assertTrue("Error -app : Store locations should be present", Elements.elementPresent("home.goto_store_locations_and_hours"));
            }
            Assert.assertTrue("Error -app : Email sign up link should be present", Elements.elementPresent("home.goto_email_sign_up"));
        }
        if (sitemode.contentEquals("Iship")) {
            Elements.elementShouldBePresent("home.goto_frequently_asked_questions");
            Elements.elementShouldBePresent(macys() ? "home.goto_find_a_store" : "home.goto_our_stores");
            Elements.elementShouldBePresent("home.goto_visitors_services");
            Assert.assertFalse("Error -app : credit services link should not present", Elements.elementPresent("home.goto_credit_services"));
            Assert.assertFalse("Error -app : credit card bill pay link should not present", Elements.elementPresent("home.goto_credit_pay_bill_online"));
            Assert.assertFalse("Error -app : credit card apply now link not present", Elements.elementPresent("home.goto_credit_apply_now"));
            Assert.assertFalse("Error -app : My Account link should not be present", Elements.elementPresent("home.goto_my_account_link"));
            Assert.assertFalse("Error -app : sign link should not be present", Elements.elementPresent("home.goto_sign_in_link"));
        }

    }

    @And("^I verify GNA consistency$")
    public void i_verify_GNA_consistency() throws Throwable {
        Wait.forPageReady();
        if(bloomingdales() && HeaderActions.getSiteMode().equalsIgnoreCase("iship")){
            log.info("GNA media is not verified as BCOM ISHIP mode data won't be setup by SITEMERCH team");
        }else {
            List<WebElement> gnaMediaList = Elements.findElement("header.header_pool_media_section").findElements(By.tagName("a"));
            Assert.assertFalse("ERROR - DATA: GNA media Ads not available", gnaMediaList.isEmpty());
            for (int i = 0; i < gnaMediaList.size(); i++) {
                String gnaLinkURL = gnaMediaList.get(i).getAttribute("href");
                if (gnaLinkURL.contains("javascript:pop")) {
                    gnaLinkURL = gnaLinkURL.split("script:pop")[1].split("','")[0].replace("('", "");
                }
                if (!gnaLinkURL.contains("www") && !gnaLinkURL.contains("http")) {
                    gnaLinkURL = RunConfig.url + gnaLinkURL;
                }
                gnaMediaLinks.add(gnaLinkURL);
            }
            gnaMediaLinks.forEach(link -> {
                int responseCode = RESTOperations.doGET(link, null).getStatus();
                Assert.assertTrue("ERROR - APP: GNA media URL:" + link + " is returning response code:" + responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
            });
        }
    }

    @When("^I navigate to find registry on registry home page$")
    public void i_navigate_to_find_registry_page_from_reg_home_page() throws Throwable {
        Wait.forPageReady();
        if (macys()){
            Elements.findElement("registry_home.goto_find_registry").click();
        }else{
            String hostName = WebDriverManager.getCurrentUrl().split(".com")[0] + ".com";
            Navigate.visit(hostName+"/registry/wedding/registrysearch");
        }

    }

    @When("^I click on sub category link in flyout$")
    public void iclickonSubCategoryLink() throws Throwable {
        Home.selectRandomSubCategory();
        log.info("clicked on random subcategory");
    }

    @And("^I navigate to category browse page from \"([^\"]*)\"$")
    public void i_navigate_to_cat_browse_page_from_different_page(String category) throws Throwable {
        Wait.forPageReady();
        String text = null;
        if (!onPage("category_splash")) {
            selectRandomCategory();
        }

        while (!onPage("category_browse")) {
            WebElement selected = Elements.getRandomElement(Elements.element("category_splash.subcategory"),
                    el -> el.isDisplayed() && !el.getText().matches("(.*?)(Brands|Impulse|DESIGNERS|WEDDING REGISTRY)(.*?)"));
            text = selected.getText();
            Clicks.click(selected);

            // Safari is not waiting for page load after clicking on subcategory
            if (safari()) {
                Utils.threadSleep(1000, null);
                Wait.forPageReady();
            }

            if (!onPage("category_browse") && !onPage("category_splash")) {
                Navigate.browserBack();
            }
        }
        if (text == null) {
            text = Elements.getText("category_browse.current_category");
        }
        log.info("Navigated to category browse page");
    }

    @When("^I click on any Product Thumbnail in search results page$")
    public void i_click_on_any_product_thumb_nail_in_search_results_pages() throws Throwable {
        Wait.forPageReady();
        GeneralUtils.clickRandomProductThumbnailinSearchResultsPage("product_thumbnails.thumbnail_container");
    }

    @When("^I visit the registry \"(home|splash|browse|product)\" page$")
    public void i_visit_the_different_pages_in_registry_mode(String mode) throws Throwable {
        Wait.forPageReady();
        Clicks.click("home.goto_wedding_registry");
        switch (mode) {
            case "home":
                Wait.forPageReady();
                if (safari()) {
                    Wait.secondsUntilElementPresent("registry_home.goto_find_registry", 30);
                }
                CommonUtils.closeIECertError();
                shouldBeOnPage("registry_home");
                break;
            case "splash":
                Wait.forPageReady();
                page.I_navigate_to_random_category_splash_page();
                break;
            case "browse":
                Wait.forPageReady();
                Home.selectRandomSubCategory();
                log.info("Navigated to random category browse page");
                break;
            case "product":
                Wait.forPageReady();
                Home.selectRandomSubCategory();
                CommonUtils.selectRandomProduct(false, false);
                break;
        }
    }

    @When("I hover over on shop by department dropdown$")
    public void i_hover_over_on_shop_by_department_shop_registry_dropdown() throws Throwable {
        Wait.forPageReady();
        GeneralUtils.hoverSelection(Elements.findElement("header.shopbydepartment"));
    }

    @Then("^I verify that flyout menu is displayed for shop by department$")
    public void iVerifyThatFlyoutMenuIsDisplayedforshpobydepartment() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.open_flyout");
    }

    @And("I navigate to random subsplash page$")
    public void i_navigate_to_random_subsplash_pages() throws Throwable {
        Wait.forPageReady();
        Home.selectRandomSubCategory();
    }

    @And("^I note the GNA count on Home Page$")
    public void i_note_the_GNA_count_on_home_page() throws Throwable {
        Assert.assertTrue("GNA banner not displayed", Elements.findElement("home.header_pool").isDisplayed());
        List<WebElement> gna_home = Elements.findElement("home.header_pool").findElements(By.tagName("img"));
        gna_home_count = gna_home.stream().map(ele -> ele.getAttribute("src")).collect(Collectors.toList()).size();
        log.info("gna_home_count: " + gna_home_count);
    }



    @And("^I visit the \"([^\"]*)\" category page from the registry$")
    public void i_visit_the_category_page_from_the_Registry(String Cat_page) throws Throwable {
        Wait.forPageReady();
        new Home().selectMainCategory(Cat_page);
    }

    @Then("^I should verify all header links and images are functioning properly$")
    public void i_should_verify_all_header_links_and_images_are_functioning_properly() throws Throwable {
        Assert.assertTrue("Header not displayed", Elements.elementPresent("header.global_header"));
        Assert.assertTrue("Top Nav is not present on header", Elements.elementPresent("home.user_menu_container"));
        List<WebElement> toplinks = Elements.findElement("home.user_menu_container").findElements(By.tagName("a"));
        List<String> AllLinks_text_header = toplinks.stream().filter(ele -> ele.isDisplayed()).map(ele -> ele.getAttribute("href")).collect(Collectors.toList());
        //All Fob's Links
        Assert.assertTrue("Error: Category menu is not displayed", Elements.findElement("home.category").isDisplayed());
        List<WebElement> fob_links = Elements.findElement("home.category").findElements(By.tagName("a"));
        AllLinks_text_header = fob_links.stream().filter(ele -> !ele.getAttribute("href").isEmpty()).map(ele -> ele.getAttribute("href")).collect(Collectors.toList());
        System.out.println("All Links" + AllLinks_text_header);
        System.out.println("All Links count" + AllLinks_text_header.size());

        AllLinks_text_header.forEach(links -> {
            int Http_href_code = RESTOperations.doGET(links, null).getStatus();
            if (Http_href_code >= 400 && Http_href_code <= 499) {
                Assert.fail("Response code for href is not 200. Actual:" + Http_href_code);
            }

        });
    }

    @When("^I navigate to category browse page$")
    public void iNavigateToCategoryBrowsePage() throws Throwable {
        categorySplash.navigateToRandomCategoryBrowsePage(8);
        log.info("Navigated to random category browse page");
    }

    @Then("^I select a random product$")
    public void iNavigateToARandomProduct() throws Throwable {
        CommonUtils.navigateToRandomProduct();
    }

    @And("^I add items to bag after selecting upc of the product$")
    public void i_add_items_to_bag_after_selecting_upc_of_the_product() throws Throwable {
        boolean addedToBag = false;
        Assert.assertFalse("ERROR - DATA : Product ( " + (recentProduct == null ? "" : String.valueOf(recentProduct.id)) + " ) is unavailable on product display page!!", !Elements.elementPresent("product_display.add_to_bag_button") && Elements.elementPresent("product_display.availability_error"));
        try {
            Wait.forPageReady();
            int retries = 5;
            for (int count = 0; count < retries && !addedToBag; count++) {
                try {
                    ProductDisplay.selectRandomColor();
                    ProductDisplay.selectRandomSize();
                    Clicks.click("product_display.add_to_bag_button");
                    if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                        Clicks.clickIfPresent("product_display.add_to_bag_button");
                    }
                    if (MEW() && macys() && !onPage("add_to_bag")) {
                        Clicks.clickIfPresent("home.close_app_banner");
                        Clicks.click("home.my_bag");
                        return;
                    }
                    addedToBag = ProductDisplay.addedToBag();
                    if (RunConfig.debugMode) {
                        System.out.println("IsProductAddedToBag:" + addedToBag + ", Add to bag retry count:" + (count + 1));
                    }
                } catch (Exception e) {
                    System.err.println("Exception while adding product:" + e.getMessage());
                }
            }
            Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog");
            if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_dialog")) {
                Clicks.clickIfPresent("product_display.technical_error");
            }
            if (isErrorPaneVisible()) {
                Clicks.click("home.popup_close");
            }
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.err.println("Error while adding to bag: " + e);
        } finally {
            if (!addedToBag) {
                Wait.untilElementNotPresent("product_display.add_to_bag_button");
                if (macys()) {
                    Assert.assertFalse("ERROR - DATA : Given item is unavailable!!", Elements.elementPresent(By.className("css-tooltip")) && Elements.getText(By.className("css-tooltip")).contains("this item is unavailable"));
                }
                Assert.assertTrue("Unable to add product to bag", ProductDisplay.addedToBag());
            }

        }
    }

    @Then("^I verify the Easy Web browser link is rendered properly$")
    public void iVerifytheEasyBrowserLinkisRenderedProperly() throws Throwable {
        Assert.assertTrue("ERROR - APP: Visually impaired link is not displayed", Elements.findElement("home.goto_visually_impaired").isDisplayed());
        Clicks.click(Elements.findElement("home.goto_visually_impaired"));
        log.info("Visually impaired link tapped");
        Assert.assertTrue("'ERROR - APP: Easy web browser page not displayed'",WebDriverManager.getCurrentUrl().contains("ce/splash/accessibility/index") || title().contains("Introducing Easy Web Browsing"));
    }

    @Then("^I verify signup for emails functionality and navigate back to homepage$")
    public void i_verify_signup_for_emails_functionality_and_navigate_back_to_homepage() {
        Assert.assertTrue("Error - APP : sign up link not displayed", Elements.findElement("home.goto_email_sign_up").isDisplayed());
        Clicks.click(Elements.findElement("home.goto_email_sign_up"));
        // yet to implement sign up functionlity
    }

    @Then("^I verify all legal notice links in the Footer$")
    public void i_verify_all_legal_notice_links_in_the_footer() throws Throwable {
        if (WebDriverManager.getCurrentUrl().contains("macys")) {
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_legal_notice").isDisplayed());
            Assert.assertTrue("ERROR - APP: Pricing Policy link is not displayed", Elements.findElement("home.goto_pricing_policy").isDisplayed());
            Assert.assertTrue("ERROR - APP: Privacy Practices link is not displayed", Elements.findElement("home.goto_privacy_practices").isDisplayed());
            Assert.assertTrue("ERROR - APP: Interest Based ads link is not displayed", Elements.findElement("home.goto_interest_based_ads").isDisplayed());
            Assert.assertTrue("ERROR - APP: Customer bill of Rights link is not displayed", Elements.findElement("home.goto_customer_bill_of_rights").isDisplayed());
            Assert.assertTrue("ERROR - APP: California Privacy Rights link is not displayed", Elements.findElement("home.goto_california_privacy_rights").isDisplayed());
            Assert.assertTrue("ERROR - APP: CA Transparency in Supply Chains link is not displayed", Elements.findElement("home.goto_my_ca_transparency").isDisplayed());
            Assert.assertTrue("ERROR - APP: Product Recalls link is not displayed", Elements.findElement("home.goto_product_recalls").isDisplayed());
        } else {
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_terms_of_use").isDisplayed());
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_privacy").isDisplayed());
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_ca_privacy_rights").isDisplayed());
            Assert.assertTrue("ERROR - APP: Customer's Bill of Rights link is not displayed", Elements.findElement(By.linkText("Customers' Bill of Rights")).isDisplayed());
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_my_ca_transparency").isDisplayed());
        }
    }

    @Then("^I verify the Site Map page is displayed$")
    public void i_verify_the_sitemap_pageis_displayed() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Page not Successfully navigated to Sitemap page", title().contains("Site Index"));
    }

    @Then("^I verify the display of the category splash page$")
    public void iVerifyTheDisplayOftheCategorySplashPage() throws Throwable {
        List<WebElement> adRows = Elements.findElements("category_splash.rows");
        if (adRows.isEmpty())
            Assert.fail("Media missing on category splash page!");
        adRows.forEach(el -> {
            if (!el.isDisplayed())
                Assert.fail("A row on category splash page is not displayed");
        });
    }

    @Then("^I verify that Macys logo takes to Home Page$")
    public void i_verify_that_macys_logo_takes_to_home_page() throws Throwable {
        if (Elements.findElement("home.logo") != null) {
            Elements.elementShouldBePresent("home.logo");
            Clicks.click(Elements.element("home.logo"));
        } else {
            Elements.elementPresent("header.logo");
            Clicks.click(Elements.element("header.logo"));
        }
        Wait.forPageReady();
        String currentUrl = WebDriverManager.getCurrentUrl();
        Assert.assertTrue("ERROR - APP: Home page navigation failed with logo icon selection", macys() ? currentUrl.contains("macys_icon") : currentUrl.contains("BLOOMIES_ICON"));
    }

    /**
     * Searches for given text in the top search bar
     *
     * @param value text to search for
     * @throws Throwable if any exception occurs
     */
    @When("^I search for any category \"([^\"]*)\"$")
    public void I_search_for_any_category(String value) throws Throwable {
        ShopAndBrowse.I_search_for(value);
        Wait.forPageReady();
    }

    @When("^I select a random product from the search results page$")
    public void iSelectRandomProductFromsearchresultspage() throws Throwable {
        Wait.forPageReady();
        CommonUtils.selectRandomProduct(false, false);
    }

    @And("^I Should see Macys Logo on top left corner of the search rail on header$")
    public void i_should_see_macys_logo_on_top_left_corner_of_the_search_rail_on_header() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("header.logo");
    }

    @And("^I Should see Search box in between the macys logo and bag$")
    public void i_should_see_searchbox_in_between_macys_logo_and_bag() throws Throwable {
        Wait.forPageReady();
        Elements.elementPresent("home.search_field");
    }

    @And("^I should be landed on Home page$")
    public void i_should_be_landed_on_homepage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("ERROR APP: Page not navigated to macys home page", title().contains("Macy's - Shop Fashion Clothing & Accessories - Official Site - Macys.com"));
    }

    @And("^I should see \"([^\"]*)\" page is rendered$")
    public void i_should_see_my_account_page_is_rendered(String my_account_title) throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Error - App: My Account Page is not rendered", ((title().contains(my_account_title)) || (url().contains("myaccount"))));
    }

    @When("^I navigate to the my wish list page$")
    public void i_navigate_to_the_my_wish_list_page() throws Throwable {
        Wait.forPageReady();
        if (macys() && Elements.elementPresent("header.goto_my_account_link")) {
            Clicks.hoverForSelection("header.goto_my_account_link");
            Wait.untilElementPresent("header.myaccount_dropdown");
        }
        Clicks.click("header.goto_wishlist");
    }

    @And("^I should see the promotions page$")
    public void i_should_see_the_promotions_page() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue(WebDriverManager.getWebDriver().getTitle().contains("Macy's Coupons, Deals and Promotions"));
    }

    @Then("^I verify dynamic top navigation in \"(domestic|iship|registry|Iship|Registry|Domestic)\" mode$")
    public static void iverifyDynamicTopNavigationInDifferentModes(String mode) throws Throwable {
        Wait.forPageReady();
        Navigate.scrollPage(0, 1000);
        Navigate.scrollPage(0, -1000);
        String errorMessage = "Expected categories did not match visible categories.";
        Elements.elementShouldBePresent("category_menu.category");
        List<String> categories = Header.getnewheaderMainCategoryNames();
        List<String> expectedcategories_text = GeneralUtils.getExpectedMainCategoriesNames(mode);
        Collections.sort(categories);
        Collections.sort(expectedcategories_text);
        if (categories.contains("ACTIVE") || categories.contains("Active") ||
                categories.contains("EDITORIAL") || categories.contains("Editorial") || categories.contains("editorial")) {
            categories.remove("ACTIVE");
            categories.remove("Active");
            categories.remove("EDITORIAL");
            categories.remove("Editorial");
            categories.remove("editorial");
            Collections.sort(categories);
            Assert.assertTrue(errorMessage, categories.size() == expectedcategories_text.size());
            Assert.assertTrue(errorMessage + "Actual:" + categories + " and Expected:" + expectedcategories_text, expectedcategories_text.containsAll(categories));
        } else {
            Assert.assertTrue(errorMessage, categories.size() == expectedcategories_text.size());
            Assert.assertTrue(errorMessage + "Actual:" + categories + " and Expected:" + expectedcategories_text, expectedcategories_text.containsAll(categories));
        }
    }

    public static List<String> getnewheaderMainCategoryNames() {
        return Elements.findElements(Elements.element("category_menu.category")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @And("^I should see all (\\d+) clickable links are navigating to respective page for (guest|registered) user$")
    public void iShouldSeeAllClickableLinksAreNavigatingToRespectivePageForGuestUser(int arg0, String userType) throws Throwable {
        if (userType.equalsIgnoreCase("guest")) {
            GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
            Clicks.click("registry_home.goto_registry_manager");
            shouldBeOnPage("registry_sign_in");
            Navigate.browserBack();
            GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
            Wait.forPageReady();
            Clicks.click("registry_home.view_registry");
            shouldBeOnPage("new_registry_sign_in");
            Navigate.browserBack();
            GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
            Wait.forPageReady();
            /*Clicks.click("registry_home.goto_registryguide");
            shouldBeOnPage("registry_checklist");
            Navigate.browserBack();*/
            GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
            Clicks.click("registry_home.goto_benefits");
            Thread.sleep(3000);
            shouldBeOnPage("registry_benefits");
        }else {
            GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
            Clicks.click("registry_home.goto_registry_manager");
            shouldBeOnPage("registry_manager");
            Navigate.browserBack();
            GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
            Wait.forPageReady();
            Clicks.click("registry_home.view_registry");
            Assert.assertTrue("Not on View Registry page", WebDriverManager.getWebDriver().getCurrentUrl().contains("view_registry"));
            Navigate.browserBack();
            GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
            Wait.forPageReady();
            Clicks.click("registry_home.goto_registryguide");
            shouldBeOnPage("registry_checklist");
            Navigate.browserBack();
            GeneralUtils.hoverSelection(Elements.findElement("registry_home.manage_registry_dropdown"));
            Clicks.click("registry_home.goto_benefits");
            shouldBeOnPage("registry_benefits");
        }
        logInfo("Successfuly navigated to all clickable links and respective page for " + userType + "user");
    }

    @And("^I should see ISHIP Header or Footer with \"([^\"]*)\" flag$")
    public void iShouldSeeISHIPHeaderOrFooterWithFlag(String countryName) throws Throwable {
        String countryCode = HeaderActions.getCountryCode();
        Assert.assertTrue("Header flag is not matching with country", countryCode.equals(countryName));
    }

    @And("^I navigate registry home page in iship mode$")
    public void iNavigateRegistryHomePageInIshipMode() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("header.goto_wedding_registry", 15);
        Clicks.click("header.goto_wedding_registry");
        Wait.forPageReady();
    }
}
