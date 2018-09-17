package com.macys.sdt.projects.Platform.SitePerformanceImprovement.steps.website.bcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.bcom.panels.HeaderFooterActions;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.macys.sdt.framework.interactions.Clicks.click;
import static com.macys.sdt.framework.interactions.Navigate.switchWindow;
import static com.macys.sdt.framework.interactions.Navigate.switchWindowClose;
import static com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.bcom.panels.HeaderFooterActions.clickSocialIcon;

/**
 * Created by yc04026 on 10/19/2016.
 */
public class HeaderFooterSteps extends StepUtils {
    // Validation of Bloomingdales logo
    @Then("^I verify the bloomingdales logo available in \"([^\"]*)\" homepage$")
    public void I_verify_the_bloomingdales_logo_available(String mode) throws Throwable {
        Assert.assertTrue("Bloomingdales logo is not available", HeaderFooterActions.validateLogoAvailable(mode));
    }

    //Click on Bloomingdales logo
    @When("^I click on bloomingdales logo in \"([^\"]*)\" mode$")
    public void I_click_on_bloomingdales_logo(String mode) throws Throwable {
        try{
            HeaderFooterActions.clickBloomingdalesIcon(mode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Click on GNA in the Header section
    @When("^I click on INFO and EXCLUSIONS link on top of search field$")
    public void I_click_on_INFO_and_EXCLUSIONS_link_on_top_of_search_field() throws Throwable {
        click("home.nav_banner");
    }

    //Verify the INFO and EXLUSIONS window and close it
    @Then("^I verify the new window and close it$")
    public void I_verify_the_new_window_and_close_it() throws Throwable {
        switchWindow(1);
        switchWindowClose();
    }

    //Verifying that user is on Home page
    @Then("^I verify navigated to \"([^\"]*)\" homepage$")
    public void I_verify_navigated_to_homepage(String mode) throws Throwable {
        Assert.assertTrue("Not navigated to " + mode + "homePage", onPage("home"));
    }

    //verifying the Fobs' are displayed
    @Then("^I verify the Fob's displayed$")
    public void I_verify_the_FOBs_displayed(List<String> Fobs) throws Throwable {
        List<String> getCategories = HeaderFooterActions.getAllMainCategoryNames();
        List<String> allCategories = new ArrayList<>();

        for (String a : Fobs) {
            if (!getCategories.contains(a.toUpperCase())) {
                Assert.fail("Does not have FOB : " + a + " category");
                allCategories.add(a);
            }
        }
        if (!allCategories.isEmpty()) {
            Assert.fail("Does not have FOB " + allCategories + " categories");
        }
    }


    //verifying the flyout menu
    @Then("^I verify flyout menu is displayed$")
    public void iVerifyThatFlyoutMenuIsDisplayed() throws Throwable {
        Elements.elementShouldBePresent("home.open_flyout");
    }

    //click on the seasonal action wrapper
    @When("^I click on the seasonal action wrapper$")
    public void I_click_on_the_seasonal_action_wrapper() throws Throwable {
        HeaderFooterActions.clickSeasonalWrapper();
    }

    //I verify the seasonal ad in topnav section
    @Then("^I verify the seasonal ad in topnav section$")
    public void I_verify_the_seasonal_ad_in_topnav_section() throws Throwable {
        Assert.assertTrue("Seasonal ad is not displaying", Elements.elementPresent("header.seasonal_action_on"));
    }

    //I should see "<topnav_links>" in topnav section
    @Then("^I should see \"([^\"]*)\" in topnav section$")
    public void I_should_see_topNav_section(String value) throws Throwable {
        switch (value) {
            case "Stores & Events":
                Assert.assertTrue("Unavailable", Elements.elementPresent("header.goto_stores"));

                break;
            case "Country change":
                Assert.assertTrue("Unavailable", Elements.elementPresent("header_and_footer.header_international_container"));
                break;
            case "My account":
                Assert.assertTrue("Unavailable", Elements.elementPresent("header.goto_my_account_link"));
                break;
            case "Wishlist":
                Assert.assertTrue("Unavailable", Elements.elementPresent("header.goto_wishlist"));
                break;
            case "Brown bag":
                Assert.assertTrue("Unavailable", Elements.elementPresent("header.quickbag_hover"));
                break;
            default:
                Assert.fail("Invalid Topnav category");
                break;

        }
        System.out.println("Validated topNav Link " + value);

    }

    //I navigate to "<topnav_links>" page from topnav section
    @Then("^I navigate to \"([^\"]*)\" page from topnav section$")
    public void I_navigate_to_page_from_topnav_section(String topNavLink) throws Throwable {

        switch (topNavLink) {
            case "Stores & Events":
                HeaderFooterActions.storesAndEvents();
                break;
            case "Country change":
                HeaderFooterActions.countryChange();
                break;
            case "My account":
                HeaderFooterActions.myAccount();
                break;
            case "Wishlist":
                HeaderFooterActions.wishList();
                break;
            case "Brown bag":
                HeaderFooterActions.brownBag();
                break;
            default:
                Assert.fail("invalid TopNav Category");
                break;
        }
    }

    //I should see the page navigates to "<topnav_links>" page
    @Then("^I should see the page navigates to \"([^\"]*)\" page$")
    public void I_should_see_the_page_navigates_to_page(String value) throws Throwable {
        String error_message = "Unavailable :: not on correct page " + value;
        switch (value) {
            case "Stores & Events":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.stores_and_events_page_verify"));
                break;
            case "Wishlist":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.wishList_page_verify"));
                break;
            case "Brown bag":
                onPage("shopping_bag");
                break;
            case "Country change":
                Assert.assertTrue(error_message, Elements.elementPresent("international_shipping.save_continue"));
                break;
            case "My account":
                onPage("my_account");
                break;
            default:
                Assert.fail("Invalid topNav link");
                break;

        }
    }

    //I verify search box is displayed
    @Then("^I verify search box is displayed$")
    public void I_verify_search_box_is_displayed() {
        Assert.assertTrue("Search field is unavailable", Elements.elementPresent("header.search_field"));
        Assert.assertTrue("Search icon is unavailable", Elements.elementPresent("header.search_button"));
    }

    //When I search for "<Search_char>"
    @When("^I entered \"([^\"]*)\" keyword in search field$")
    public void I_enter_keywork_in_search_field(String value) {
        TextBoxes.typeTextbox("header.search_field", value);
    }

    @Then("^I should see autocomplete suggestions list is populated$")
    public void I_should_see_autocomplete_results() throws Throwable {
        Wait.untilElementPresent("header.suggestions_list");
        Assert.assertTrue("autocomplete should be displaying", Elements.elementPresent("header.suggestions_list"));
    }

    //I hover over "<categories>" fobs
    @When("^I hover over \"([^\"]*)\" fobs$")
    public void i_hover_over_fobs(String cat) throws Throwable {
        Clicks.hoverForSelection(By.linkText(cat.toUpperCase()));

    }

    //Then I verify navigated to "<links>" page
    @Then("^I verify navigated to \"([^\"]*)\" page$")
    public void I_verify_navigated_to_footerLink_pages(String footerLink) throws Throwable {
        String error_message = "Not navigated to " + footerLink + " page";
        switch (footerLink) {
            case "CUSTOMER SERVICE":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_pages_verify"));
                break;
            case "Shipping Policy":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_pages_verify"));
                break;
            case "Returns & Exchanges":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_pages_verify"));
                break;
            case "International":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_pages_verify"));
                break;
            case "MY ACCOUNT":
                if (!onPage("sign_in")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                }
                break;
            case "Order Status":
                if (!onPage("order_status")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                }
                break;
            case "My Loyallist":
                if (!onPage("loyalty_home")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                }
                break;
            case "My Profile":
                if (!onPage("my_profile")) {
                    Assert.fail("User is not navigated to " + footerLink + "page!!");
                }
                break;
            case "MY CREDIT CARD":
                if (!onPage("credit_service_gateway_guest")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                }
                break;
            case "Pay Bill":
                if (!onPage("sign_in")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                }
                break;
            case "Cardholder Benefits":
                if (!onPage("credit_benefits")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                }
                break;
            case "Apply & Learn More":
                if (!onPage("credit_service_marketing")) {
                    Assert.fail("User is not navigated to " + footerLink + " page!!");
                }
                break;
            case "COMPANY":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.company_page_verify"));
                break;
            case "About Us":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.company_page_verify"));
                break;
            case "b.cause":
                Assert.assertTrue(error_message, Elements.getText("bcom_pages.b_cause_page_verify").contains("b.cause"));
                break;
            case "Careers":
                String homeWindow = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> allWindowHandles = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh : allWindowHandles) {
                    if (!wh.equals(homeWindow)) {
                        Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.careers_page_verify"));
                    }
                }
                break;
            case "WAYS TO SHOP":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.ways_to_shop_page_verify"));
                break;
            case "Online & Mobile":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.online_and_mobile_page_verify"));
                break;
            case "Stores":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.stores_and_events_page_verify"));
                break;
            case "Outlets":
                Assert.assertTrue(error_message, Elements.getText("bcom_pages.outlet_store_page_verify").toLowerCase().contains("outlet"));
                break;
            case "Terms of Use":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_page_verify"));
                break;
            case "Privacy":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_page_verify"));
                break;
            case "CA Privacy Rights":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_page_verify"));
                break;
            case "CA Transparency in Supply Chains Act":
                String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> allWindowHandels = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh : allWindowHandels) {
                    if (!wh.equals(parentWindow)) {
                        WebDriverManager.getWebDriver().switchTo().window(wh);
                        Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_page_verify"));
                    }
                }
                break;
            case "Customers' Bill of Rights":
                String OriginalWindow = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> windowHandels = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh : windowHandels) {
                    if (!wh.equals(OriginalWindow)) {
                        WebDriverManager.getWebDriver().switchTo().window(wh);
                        Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_rights_page_verify"));
                    }
                }
                break;
            case "Product Recall":
                String origWindow = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> allWinHandels = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh : allWinHandels) {
                    if (!origWindow.equals(wh)) {
                        WebDriverManager.getWebDriver().switchTo().window(wh);
                        Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_page_verify"));
                    }
                }
                break;
            case "Visually Impaired Customers":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.visually_impaired_page_verify"));
                break;
            case "Essential Accessibility":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.customer_service_page_verify"));
                break;
            default:
                Assert.fail("Invalid Footer link");
                break;
        }
    }

    //I verify navigated to iship "<links>" page
    @Then("^I verify navigated to iship \"([^\"]*)\" page$")
    public void i_verify_navigated_iship_link(String link) throws Throwable{
        String error_message = "Not navigated to " + link + " page";

        switch (link.toLowerCase()){
            case "your order":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.iship_verify_your_order"));
                break;
            case "order status":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.iship_verify_order_status"));
                break;
            case "shipping policy":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.iship_verify_shipping_policy"));
                break;
            case "returns & exchanges":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.iship_verify_return_exchange"));
                break;
            case "customer service":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.iship_verify_customer_service"));
                break;
            case "faqs":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.iship_verify_faqs"));
                break;
            case "visitor services":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.iship_verify_visitor_services"));
                break;
            case "domestic":
                Assert.assertTrue(error_message, Elements.elementPresent("bcom_pages.iship_verify_domestic"));
                break;
            default:
                Assert.fail("Invalid iship footer link");
                break;

        }
    }

    //I navigate and verify "<links>" social footer links & page
    @When("^I navigate and verify \"([^\"]*)\" social footer links & page$")
    public void I_navigate_and_verify_social_footer_links(String socialLink) throws Throwable {
        String page_error_message = "Not navigated to social link " + socialLink + "page";
        String icon_error_message = "link unavailable on "+socialLink+ " page";

        switch (socialLink.toLowerCase()) {
            case "facebook":

                clickSocialIcon(socialLink);
                Navigate.switchWindow(1);

                Assert.assertTrue(page_error_message, WebDriverManager.getWebDriver().getTitle().endsWith("Facebook"));
                Assert.assertTrue(icon_error_message, Elements.elementPresent("bcom_pages.face_book_page_verify"));
                break;
            case "twitter":
                clickSocialIcon(socialLink);
                Navigate.switchWindow(1);

                Assert.assertTrue(page_error_message, WebDriverManager.getWebDriver().getTitle().endsWith("Twitter"));
                Assert.assertTrue(icon_error_message, Elements.elementPresent("bcom_pages.twitter_page_verify"));
                break;
            case "pinterest":
                clickSocialIcon(socialLink);
                Navigate.switchWindow(1);

                Assert.assertTrue(page_error_message, WebDriverManager.getWebDriver().getTitle().endsWith("Pinterest"));
                Assert.assertTrue(icon_error_message, Elements.elementPresent("bcom_pages.pinterest_page_verify"));
                break;
            case "instagram":
                clickSocialIcon(socialLink);
                Navigate.switchWindow(1);

                Assert.assertTrue(page_error_message, WebDriverManager.getWebDriver().getTitle().endsWith("Instagram photos and videos"));
                Assert.assertTrue(icon_error_message, Elements.elementPresent("bcom_pages.instagram_page_verify"));
                break;
            case "mobile":
                clickSocialIcon(socialLink);
                Navigate.switchWindow(1);

                Assert.assertTrue(page_error_message, WebDriverManager.getWebDriver().getTitle().endsWith("Ways To Shop | Bloomingdale's"));
                Assert.assertTrue(icon_error_message, Elements.elementPresent("bcom_pages.mobile_page_verify"));
                break;
            case "sign-up":
                Elements.findElement("bcom_pages.sign_up_link").click();

                Assert.assertTrue(page_error_message, WebDriverManager.getWebDriver().getTitle().endsWith("Bloomingdale's"));
                Assert.assertTrue(icon_error_message, Elements.elementPresent("bcom_pages.sign_up_page_verify"));
                break;
            default:
                Assert.fail("Invalid Social Link");
                break;
        }

    }

    //closing the pop-up in home page in FireFox browser
    @Then("^I close popUp on home page$")
    public void I_close_popUp_on_home_page() {
        if(Elements.anyPresent("bcom_pages.close_popup")) {
            Clicks.click("bcom_pages.close_popup");
        }else
            System.out.println("pop_up is not displaying");

    }
}