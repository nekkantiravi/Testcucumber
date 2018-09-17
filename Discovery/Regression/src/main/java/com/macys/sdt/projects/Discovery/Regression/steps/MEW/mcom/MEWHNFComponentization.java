package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

/**
 * Created by yc05s1d on 6/21/17.
 */
public class MEWHNFComponentization extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(MEWHNFComponentization.class);
    private static String search_keyword = null;

    @Then("^I click on open Navigation$")
    public void MEWHNFComponentization() throws Throwable {
        Clicks.click("mew_hnf.hamburger_menu");
        Elements.findElement("mew_hnf.navigation_menu").isDisplayed();
    }

    @Then("^I verify All elements of left navigation in Header Menu$")
    public void iVerifyAllElementsOfLeftNavigationInHeaderMenu(List<String> menuItems) throws Throwable {
        List<String> menuChild = Elements.findElement("mew_hnf.navigation_menu").findElements(By.tagName("li")).stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("Error - Env: Categories under GN are not displayed in the order " + menuItems, menuItems.equals(menuChild));
    }

    @Then("^I verify All elements of left navigation in registry Header Menu$")
    public void iVerifyAllElementsOfLeftNavigationInRegistryHeaderMenu(List<String> menuItems) throws Throwable {
        List<String> menuChild = Elements.findElement("mew_hnf.registry_home_navigation_menu").findElements(By.tagName("li")).stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("Error - Env: Categories under GN are not displayed in the order " + menuItems, menuItems.equals(menuChild));
    }

    @Then("^I should not see global navigation panel if I close global navigation$")
    public void iShouldNotSeeGlobalNavigationPanelIfICloseGlobalNavigation() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Clicks.click("mew_hnf.close_navigation_menu");
        Assert.assertTrue("I still see Global nav menu panel", Elements.findElement("mew_hnf.hamburger_menu").isDisplayed());
        //throw new PendingException();
    }

    @Then("^I verify data in footer section of the unified login page$")
    public void iVerifyDataFooterSectionOnUnifiedLoginPage(List<String> footerOptions) throws Throwable {
        Wait.forPageReady();
        String footerText = Elements.findElement("footer.unified_footer_links").getText();
        for (String footerOption : footerOptions) {
            Assert.assertTrue("ERROR - DATA: Diffrent data found on footer section", footerText.contains(footerOption));
        }
        logger.info("verified the footer section on unified login page");
    }

    @Then("^I verify the copy right section on footer shows \"([^\"]*)\"$")
    public void iVerifyDataFooterSectionOnUnifiedLoginPage(String copyRightSectionText) throws Throwable {
        Wait.forPageReady();
        String footerCRText = Elements.findElement("footer.unified_footer_copy_right").getText();
        Assert.assertTrue("ERROR - DATA: Unexpected copy right value found", footerCRText.equalsIgnoreCase(copyRightSectionText));
        logger.info("verified the footer copy right section on unified login page");
    }

    @When("^I Navigate to \"([^\"]*)\" social link in footer$")
    public void I_Navigate_to_footer_link(String Social_Link) throws DriverNotInitializedException {
        switch (Social_Link) {
            case "Facebook":
                if (!Clicks.clickIfPresent("home.goto_facebook")) {
                    Assert.fail(Social_Link + " is not Available");
                }
                break;
            case "Twitter":
                if (!Clicks.clickIfPresent("home.goto_twitter")) {
                    Assert.fail(Social_Link + " is not Available");
                }
                break;
            case "Pinterest":
                if (!Clicks.clickIfPresent("home.goto_pinterest")) {
                    Assert.fail(Social_Link + " is not Available");
                }
                break;
            case "Youtube":
                if (!Clicks.clickIfPresent("home.goto_youtube")) {
                    Assert.fail(Social_Link + " is not Available");
                }
                break;
            case "Instagram":
                if (!Clicks.clickIfPresent("home.goto_instagram")) {
                    Assert.fail(Social_Link + " is not Available");
                }
        }
    }

    @When("^I Navigate to \"([^\"]*)\" page in footer$")
    public void I_Navigate_to_all_footer_links(String page_link) throws DriverNotInitializedException {
        switch (page_link) {
            case "Sign in":
                if (!Clicks.clickIfPresent("home.goto_sign_in_link")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Change Country":
                if (!Clicks.clickIfPresent("home.goto_choose_country")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Find A Store":
                if (!Clicks.clickIfPresent("home.find_a_store")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Customer Service":
                if (!Clicks.clickIfPresent("home.goto_contact_us")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Privacy Practices":
                if (!Clicks.clickIfPresent("home.goto_privacy")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Internet based Ads":
                if (!Clicks.clickIfPresent("home.goto_interest_based_ads")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Legal Notice":
                if (!Clicks.clickIfPresent("home.goto_legal_notice")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Desktop Version":
            case "Full Site":
                if (!Clicks.clickIfPresent("home.goto_full_site")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Customers' Bill of Rights":
                if (!Clicks.clickIfPresent("home.goto_bill_rights")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Careers":
                if (!Clicks.clickIfPresent("home.goto_career")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            case "Way to Shop":
                if (!Clicks.clickIfPresent("home.goto_way_to_shop")) {
                    Assert.fail(page_link + " is not Available");
                }
                break;
            default:
                Assert.fail("Invalid argument!!!");
        }
    }

    @Then("^I verify the navigation for \"([^\"]*)\" page$")
    public void i_verify_the_navigation(String name) throws Throwable {

        switch (name) {
            case "Facebook":
                Wait.forPageReady();
                String OriginalWindow = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> windowHandels = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh : windowHandels) {
                    if (!wh.equals(OriginalWindow)) {
                        WebDriverManager.getWebDriver().switchTo().window(wh);
                        String pageUrl = WebDriverManager.getCurrentUrl();
                        Assert.assertTrue("Doesn't navigate to Facebook page", pageUrl.contains("facebook"));
                    }
                }
                break;

            case "Instagram":
                Wait.forPageReady();
                String OriginalWindow1 = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> windowHandels1 = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh1 : windowHandels1) {
                    if (!wh1.equals(OriginalWindow1)) {
                        WebDriverManager.getWebDriver().switchTo().window(wh1);
                        String pageUrl1 = WebDriverManager.getCurrentUrl();
                        Assert.assertTrue("Doesn't navigate to Instagram page", pageUrl1.contains("instagram"));
                    }
                }
                break;

            case "Pinterest":
                Wait.forPageReady();
                String OriginalWindow2 = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> windowHandels2 = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh2 : windowHandels2) {
                    if (!wh2.equals(OriginalWindow2)) {
                        WebDriverManager.getWebDriver().switchTo().window(wh2);
                        String pageUrl2 = WebDriverManager.getCurrentUrl();
                        Assert.assertTrue("Doesn't navigate to Pinterest page", pageUrl2.contains("pinterest"));
                    }
                }
                break;
            case "Twitter":
                Wait.forPageReady();
                String OriginalWindow3 = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> windowHandels3 = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh3 : windowHandels3) {
                    if (!wh3.equals(OriginalWindow3)) {
                        WebDriverManager.getWebDriver().switchTo().window(wh3);
                        String pageUrl3 = WebDriverManager.getCurrentUrl();
                        Assert.assertTrue("Doesn't navigate to Twitter page", pageUrl3.contains("twitter"));
                    }
                }
                break;
            case "Youtube":
                Wait.forPageReady();
                String OriginalWindow4 = WebDriverManager.getWebDriver().getWindowHandle();
                Set<String> windowHandels4 = WebDriverManager.getWebDriver().getWindowHandles();

                for (String wh4 : windowHandels4) {
                    if (!wh4.equals(OriginalWindow4)) {
                        WebDriverManager.getWebDriver().switchTo().window(wh4);
                        String pageUrl4 = WebDriverManager.getCurrentUrl();
                        Assert.assertTrue("Doesn't navigate to Youtube page", pageUrl4.contains("youtube"));
                    }
                }
                break;
            default:
                System.out.println("Argument doesn't match!!!");
        }
    }

    @Then("^I verify the page navigation for \"([^\"]*)\" page$")
    public void i_verify_the_page_navigation(String name) throws Throwable {
        if (WebDriverManager.getCurrentUrl().contains("macys")) {
            switch (name) {
                case "Sign in":
                    Wait.forPageReady();
                    Assert.assertTrue("ERROR: Email address field is not displayed", Elements.findElement("sign_in.email").isDisplayed());
                    break;
                case "Change Country":
                    Wait.forPageReady();
                    Assert.assertTrue("Not on international context page", WebDriverManager.getCurrentUrl().contains("/international/context?"));
                    break;
                case "Find A Store":
                    Wait.forPageReady();
                    Assert.assertTrue("Not on find a store page", WebDriverManager.getCurrentUrl().contains("l.macys.com/stores.html"));
                    break;
                case "Customer Service":
                    Wait.forPageReady();
                    String OriginalWindow = WebDriverManager.getWebDriver().getWindowHandle();
                    Set<String> windowHandels = WebDriverManager.getWebDriver().getWindowHandles();

                    for (String wh : windowHandels) {
                        if (!wh.equals(OriginalWindow)) {
                            WebDriverManager.getWebDriver().switchTo().window(wh);
                            String pageUrl = WebDriverManager.getCurrentUrl();
                            Assert.assertTrue("Doesn't navigate to Customer service page", pageUrl.contains("customer"));
                        }
                    }
                    break;
                case "Privacy Practices":
                    Wait.forPageReady();
                    String OriginalWindow1 = WebDriverManager.getWebDriver().getWindowHandle();
                    Set<String> windowHandels1 = WebDriverManager.getWebDriver().getWindowHandles();

                    for (String wh1 : windowHandels1) {
                        if (!wh1.equals(OriginalWindow1)) {
                            WebDriverManager.getWebDriver().switchTo().window(wh1);
                            String pageUrl = WebDriverManager.getCurrentUrl();
                            Assert.assertTrue("Doesn't navigate to Privacy Practices page", pageUrl.toLowerCase().contains("privacy"));
                        }
                    }
                    break;
                case "Internet based Ads":
                    Wait.forPageReady();
                    String OriginalWindow2 = WebDriverManager.getWebDriver().getWindowHandle();
                    Set<String> windowHandels2 = WebDriverManager.getWebDriver().getWindowHandles();

                    for (String wh2 : windowHandels2) {
                        if (!wh2.equals(OriginalWindow2)) {
                            WebDriverManager.getWebDriver().switchTo().window(wh2);
                            String pageUrl = WebDriverManager.getCurrentUrl();
                            Assert.assertTrue("Doesn't navigate to internet based ads page", pageUrl.toLowerCase().contains("interest_based_ads"));
                        }
                    }
                    break;
                case "Legal Notice":
                    Wait.forPageReady();
                    String OriginalWindow3 = WebDriverManager.getWebDriver().getWindowHandle();
                    Set<String> windowHandels3 = WebDriverManager.getWebDriver().getWindowHandles();

                    for (String wh3 : windowHandels3) {
                        if (!wh3.equals(OriginalWindow3)) {
                            WebDriverManager.getWebDriver().switchTo().window(wh3);
                            String pageUrl = WebDriverManager.getCurrentUrl();
                            Assert.assertTrue("Doesn't navigate to Legal Notice page", pageUrl.contains("legal_notice"));
                        }
                    }
                    break;
                case "Desktop Version":
                    Wait.forPageReady();
                    String pageUrl = WebDriverManager.getCurrentUrl();
                    Assert.assertTrue("Doesn't navigate to Desktop Version", pageUrl.contains("www." + EnvironmentDetails.getEnv(WebDriverManager.getCurrentUrl())));
                    break;
                case "Customers' Bill of Rights":
                    Wait.forPageReady();
                    Navigate.switchWindow(1);
                    Assert.assertTrue("Doesn't navigate to Customers' Bill of Rights page", WebDriverManager.getCurrentUrl().contains("customer-bill-of-rights"));
                    break;
                case "Careers":
                    Wait.forPageReady();
                    Navigate.switchWindow(1);
                    Assert.assertTrue("Doesn't navigate to Careers page", WebDriverManager.getCurrentUrl().contains("CAREERS"));
                    break;
                case "Way to Shop":
                    Wait.forPageReady();
                    Navigate.switchWindow(1);
                    Assert.assertTrue("Doesn't navigate to Way to Shop page", WebDriverManager.getCurrentUrl().contains("ways-to-shop"));
                    break;
                default:
                    System.out.println("Argument doesn't match!!!");
            }
        }
    }

    @And("^I should see text \"([^\"]*)\" at the bottom of the page$")
    public void i_verify_the_new_legal_notice_text_in_the_footer(String expectedLegalCopy) {
        try {
            Wait.forPageReady();
            String ActualLegalCopyMsg = Elements.findElement("home.trademark").getText();
            Assert.assertTrue("Expected Legal copy message in footer is not displayed: " + expectedLegalCopy + " Actual displayed Legal copy message: " + ActualLegalCopyMsg,
                    expectedLegalCopy.equalsIgnoreCase(ActualLegalCopyMsg));
        } catch (Exception e) {
            Assert.fail("Expected Legal Copy Message: " + expectedLegalCopy + " is not displayed" + e.getMessage());
        }
    }

    @Then("^I should see SIGN UP FOR EMAILS OR TEXTS button in footer$")
    public void i_should_see_signup_for_emails_button() {
        Wait.forPageReady();
        Elements.elementPresent("home.goto_email_text_signup");
    }

    @Then("^I should see signup for emails page opened in new tab$")
    public void i_should_see_signup_for_emails_opened_in_new_tab() throws DriverNotInitializedException {
        Wait.forPageReady();
        if (macys()) {
            Navigate.switchWindow(1);
            Assert.assertTrue("Doesn't navigate to signup for emails page in iship mode", WebDriverManager.getCurrentUrl().contains("emails.macys"));
        } else {
            Assert.assertTrue("Doesn't navigate to signup for emails page in iship mode", WebDriverManager.getCurrentUrl().contains("international-visitor-services"));
        }
    }

    @Then("^I verify all legal notice links in the mew Footer$")
    public void i_verify_all_legal_notice_links_in_the_footer() throws Throwable {
        if (macys()) {
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_legal_notice").isDisplayed());
            Assert.assertTrue("ERROR - APP: CA Privacy Rights link is not displayed", Elements.findElement("home.goto_ca_privacy_rights").isDisplayed());
            Assert.assertTrue("ERROR - APP: CA Transparency in Supply Chains link is not displayed", Elements.findElement("home.goto_ca_trasparency_Act").isDisplayed());
            Assert.assertTrue("ERROR - APP: Product Recalls link is not displayed", Elements.findElement("home.goto_customer_bill_of_rights").isDisplayed());
        } else {
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_terms_of_use").isDisplayed());
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_privacy").isDisplayed());
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_ca_privacy_rights").isDisplayed());
            Assert.assertTrue("ERROR - APP: Customer's Bill of Rights link is not displayed", Elements.findElement("Customers' Bill of Rights").isDisplayed());
            Assert.assertTrue("ERROR - APP: Legal Notice link is not displayed", Elements.findElement("home.goto_my_ca_transparency").isDisplayed());
        }
    }

    @When("^I scroll the page down$")
    public void i_Scroll_the_page_down() throws Throwable {
        scrollToLazyLoadElement("footer.footer_bottom_links");
        Wait.forPageReady();
    }

    @Then("^I verify the legal notice \"([^\"]*)\" for mew are rendered properly$")
    public void i_verify_legal_notice_pages(String footer_link) throws DriverNotInitializedException {
        switch (footer_link) {
            case "Request our corporate name & address by email":
                Clicks.click("footer.goto_corporatename_and_address_by_email");
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("-_-corporate_name_request"));
                Wait.forPageReady();
                break;
            case "CA Privacy Rights":

                Wait.forPageReady();
                Clicks.click("footer.goto_ca_privacy_rights");
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("-_-ca_privacy_rights"));
                break;
            case "Customers Bill of Rights":

                Wait.forPageReady();
                WebDriverManager.getWebDriver().get(Elements.findElement(By.linkText("Customer Bill of Rights")).getAttribute("href"));
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("-_-cust_bill_of_rights"));
                break;
            case "CA Transparency Supply Chain":
                Wait.forPageReady();
                WebDriverManager.getWebDriver().get(Elements.findElement(By.linkText("CA Transparency in Supply Chain")).getAttribute("href"));
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("-_-ca_transparency"));
                break;
        }
        Wait.untilElementPresent("header.logo");
    }

    @Then("^I verify SIGN UP FOR EMAILS OR TEXTS navigates to Sing in Page$")
    public void i_verify_signup_or_emails_navigation() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("ERROR: Email address field is not displayed", Elements.findElement("sign_in.email").isDisplayed());
    }

    @Then("^I verify SIGN UP FOR EMAILS OR TEXTS navigates to preferences page$")
    public void iVerifySIGNUPFOREMAILSORTEXTSNavigatesToPreferencesPage() throws Throwable {
        shouldBeOnPage("my_preferences");
    }

    @Then("^I should see the footer text \"([^\"]*)\" displayed on the footer$")
    public void i_should_see_the_footer_text_displayed_on_the_footer(String expected_footer_text) throws Throwable {
        try {
            Wait.forPageReady();
            String ActualLegalCopyMsg = Elements.findElement("mew_hnf.Footer_text").getText();
            Assert.assertTrue("Expected Legal copy message in footer is not displayed: " + expected_footer_text + " Actual displayed Legal copy message: " + ActualLegalCopyMsg,
                    expected_footer_text.equalsIgnoreCase(ActualLegalCopyMsg));

        } catch (Exception e) {
            Assert.fail("expected footer text Message: " + expected_footer_text + " is not displayed" + e.getMessage());
        }
    }

    @Then("^I Click on Shop$")
    public void iClickOnShop() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        if (Elements.findElement("mew_hnf.menu").isDisplayed()) {
            Elements.findElement("mew_hnf.shop").click();
            logger.info("Clicked on Shop menu");
        }
        //throw new PendingException();
    }

    @Then("^I should see the top level categories under the shop menu:$")
    public void iShouldSeeTheTopLevelCategoriesUnderTheShopMenu(List<String> mewFOBs) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        if (Elements.findElement("mew_hnf.shop").getClass().equals("current")) {
            int menuChildCount = Elements.findElements("mew_hnf.menu_child").size();
            List<String> menuChild = new ArrayList<>();
            for (int i = 0; i < menuChildCount; i++) {
                menuChild.add(i, Elements.findElements("mew_hnf.menu_child").get(i).getText());
            }
            Assert.assertTrue(mewFOBs.equals(menuChild));
        }
        //throw new PendingException();
    }

    @Then("^I verify below \"([^\"]*)\" left navigation expands$")
    public void iVerifyBelowLeftNavigationExpands(String mode) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        switch (mode) {
            case "domestic":
                Assert.assertTrue("Domestic Header GN is not showing", Elements.findElement("mew_hnf.expand_left_navigation_domestic_iship").isDisplayed());
                break;
            case "registry":
                Assert.assertTrue("Registry Header GN is not showing", Elements.findElement("mew_hnf.expand_left_navigation_reg").isDisplayed());
                break;
            case "iship":
                Assert.assertTrue("Iship Header GN is not showing", Elements.findElement("mew_hnf.expand_left_navigation_domestic_iship").isDisplayed());
                break;
        }
        //throw new PendingException();
    }

    @Then("^I verify Macys Logo is displayed in Header$")
    public void iVerifyMacysLogoIsDisplayedInHeader() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue("Macys logo is not displaying", Elements.findElement("mew_hnf.macys_logo").isDisplayed());
        //throw new PendingException();
    }

    @Then("^I verify New Header Redesign implementation in current page$")
    public void i_verify_new_header_redesign_for_clean_implementation() throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("mew_hnf.hamburger_menu");
        Elements.elementShouldBePresent("mew_hnf.macys_logo");
        Elements.elementShouldBePresent("mew_hnf.navigation_menu");
        Elements.elementShouldBePresent("mew_hnf.shopping_bag");
        Elements.elementShouldBePresent("mew_hnf.search_input_field");
    }

    @And("^I verify shopping bag icon is displayed in Header$")
    public void iVerifyQuickBagIconIsDisplayedInHeader() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue("Shopping bag icon is not displaying", Elements.findElement("mew_hnf.shopping_bag").isDisplayed());
        //throw new PendingException();
    }

    @When("^I click on Macys logo in header$")
    public void iClickOnMacysLogoInHeader() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Elements.findElement("mew_hnf.macys_logo").click();
        //throw new PendingException();
    }

    @Then("^I verify logo will redirect to the homepage$")
    public void iVerifyLogoWillRedirectToTheHomepage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue("Home page in not showing", Elements.findElement("mew_hnf.home_page").isDisplayed());
        //throw new PendingException();
    }

    @Then("^I verify default text in Search bar is \"([^\"]*)\"$")
    public void iVerifyDefaultTextInSearchBarIs(String searchText) throws Throwable {
        try {
            Wait.forPageReady();
            String keywordPlaceHolder = macys() ? Elements.findElement("mew_hnf.search_input_field").getAttribute("placeholder").trim() :
                    Elements.findElement(By.tagName("input")).getAttribute("placeholder");
            Assert.assertTrue("Expected default message in search box is not displayed: " + searchText + " Actual displayed keyword place holder: " + keywordPlaceHolder,
                    searchText.equalsIgnoreCase(keywordPlaceHolder));
        } catch (Exception e) {
            Assert.fail("Expected keyword: " + searchText + " is not displayed" + e.getMessage());
        }
    }

    @When("^I type \"([^\"]*)\" keyword in mew search box$")
    public void i_type_a_key_word_in_mew_search(String keyword) throws Throwable {
        search_keyword = keyword;
        TextBoxes.typeTextNEnter("home.search_field", search_keyword);
        Wait.forPageReady();
        logger.info("Entered the search keyword in the mew search box");
    }

    @And("^I type single character in mew search box$")
    public void i_Type_Single_Character_In_Mew_SearchBox() throws Throwable {
        Random rand = new Random();
        String keyword = randomAlphanumeric(10);
        String random_character = String.valueOf(keyword.charAt(rand.nextInt(keyword.length())));
        TextBoxes.typeTextbox("home.search_field", random_character);
        logger.info("Verified by entering a random text");
    }

    @Then("^I should see the \"([^\"]*)\" text in the panel$")
    public void iShould_See_The_Text_In_Panel(String recent_searches) throws Throwable {
        Clicks.click("search.search_field");
        Utils.threadSleep(2000, "ERROR - APP: Sleep timeout while waiting for recent searches");
        String selector = macys() ? "mew_hnf.recent_searchcontainer" : "search_result.recent_searchcontainer";
        String title = Elements.findElement(selector).getText().toLowerCase();
        Assert.assertTrue("Error - App: Recent searches title is displayed", title.contains(recent_searches));
        logger.info("Verified the recent searches title in recent search panel");
    }

    @And("^I should see recent searches in recent search panel$")
    public void i_Should_See_RecentSearches_In_RecentSearchPanel() throws Throwable {
        List<String> actualRecentSearch;
        String recentSearchText = macys() ? "LastSearches" : "recentSearches";
        String localValue = (String) Navigate.execJavascript(String.format("return window.localStorage.getItem('%s');", recentSearchText));
        JSONArray jsonArray = new JSONArray(localValue);
        List<String> expectedRecentSearches = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            expectedRecentSearches.add(jsonArray.getString(i).toLowerCase());
        }
        if (macys()) {
            actualRecentSearch = Elements.findElements("mew_hnf.lastSearches").stream()
                    .map(ele -> ele.getAttribute("data-value").toLowerCase()).collect(Collectors.toList());

        } else {
            actualRecentSearch = Elements.findElements("search_result.recent_search_list").stream()
                    .map(ele -> ele.getAttribute("data-kwrs").toLowerCase()).collect(Collectors.toList());
        }
        Assert.assertEquals("Error - App: Recent searches are not displayed in order", expectedRecentSearches, actualRecentSearch);
        logger.info("Verified by comparing the recent searches from local storage and with the search keyword");
    }

    @Then("^I verify user navigates to customer service page$")
    public void i_verify_user_navigates_to_customer_Service_page() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("'ERROR - APP: Page not navigated to customer service page'", title().contains("Macy's Customer Service Site"));
        logger.info("Verified customer service page");
    }

    @Then("^I verify user navigates to Gift Registry customer service page$")
    public void i_verify_user_navigates_to_gift_registry_customer_service_page() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("'ERROR - APP: Page not navigated to gift registry page'", Elements.getText(By.className("rn_Summary")).contains("Gift Registry"));
        logger.info("Verified gift registry customer service page");
    }

    @Then("^I verify GN is expanded till \"([^\"]*)\"$")
    public void i_verify_GN_expanded_till(String category) throws Throwable {
        switch (category) {
            case "Home":
                category = "For The Home";
                break;
            case "Juniors":
                category = "Juniors & Guys";
                break;
            case "Plus Sizes & Petites":
                category = "Plus & Petite";
                break;
            case "Handbags & Accessories":
                category = "Handbags & Sunglasses";
                break;
            case "Gift Cards":
                category = "Gift Guides & Gift Cards";
                break;
            case "Kids":
                category = "Kids & Baby";
                break;
            case "Luggage":
                category = "Luggage & Backpacks";
        }
        Wait.forPageReady();
        GlobalNav.openGlobalNav();
        Assert.assertTrue("Error - App: GN is not expanded till " + category, Elements.getText("home.gn_current_category").equalsIgnoreCase(category));
        logger.info("Verified GN is expanded");
    }

    @When("^I click on sales media banner in home page$")
    public void iClickOnSalesBanner() throws Throwable {
        if (Elements.findElement("mew_hnf.sale_banner").isDisplayed()) {
            Elements.findElement("mew_hnf.sale_banner").click();
            logger.info("Clicked on sales media banner");
        }
//        throw new PendingException();
    }

    @When("^I enter \"([^\"]*)\" keyword in search field for mew$")
    public void i_enter_keyword_in_search_field_for_mew(String keyword) throws Throwable {
        try {
            TextBoxes.typeTextbox("search.search_field", keyword);
            Wait.untilElementPresent("header.suggestions_list");
        } catch (Exception e) {
            Assert.fail("Error to enter keyword in search field");
        }
        logger.info("Entered " + keyword + " keyword in search field");
    }

    @Then("^I should see autocomplete suggestions in mew$")
    public void iShouldSeeAutocompleteSuggestionsinmew() throws Throwable {
        Wait.secondsUntilElementPresent("header.suggestions_list", 30);
        Assert.assertTrue("ERROR - APP: Autocomplete suggestions are not displayed",
                Elements.elementPresent("header.suggestions_list"));
        logger.info("autosuggestions are shown in mew");
    }

    @And("^I should see 'X' Icon on the right corner of the search box$")
    public void i_shouldseeXiconontherightcornerofthesearchbox() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("ERROR - APP: X icon is not displayed", Elements.elementPresent("search.clear"));
        logger.info("Cross mark is shown in mew search box");
    }

    @When("^I Click on Registry in IShip mode$")
    public void i_click_on_registry_in_ship_mode() throws Throwable {
        Wait.forPageReady();
        GlobalNav.openGlobalNav();
        Clicks.click("mew_hnf.registry_home");
        logger.info("Clicked on IShip registry");
    }

    @Then("^I Should not see Registry Home page in IShip mode$")
    public void i_should_not_See_registry_in_ishipmode() throws Throwable {
        Wait.forPageReady();
        Assert.assertFalse("Page navigated to wedding registry page in Iship mode", title().contains("Macy's|Wedding Registry"));
        logger.info("Page not navigated to wedding registry");
    }

    @And("^I click on star rewards link on my account page for mobile$")
    public void i_click_on_starrewards_link_on_myaccount_page_for_mobiles() throws Throwable {
        Wait.forPageReady();
        Clicks.click("mew_hnf.Starrewards_link");
    }

    @And("^I click on my preferences link on my account page for mobile$")
    public void i_click_on_mypreference_link_on_my_account_page_for_mobile() throws Throwable {
        Wait.forPageReady();
        scrollToLazyLoadElement("footer.footer_bottom_links");
        Clicks.click("my_account.my_preferences");
    }

    @Then("^I should not see autocomplete suggestions in mew$")
    public void iShouldNotSeeAutocompleteSuggestionsinmew() throws Throwable {
        Wait.secondsUntilElementPresent("header.suggestions_list", 30);
        Assert.assertFalse("ERROR - APP: Autocomplete suggestions are not displayed",
                Elements.elementPresent("header.suggestions_list"));
        logger.info("autosuggestions are shown in mew for registry");
    }

    @When("^I search with below keywords$")
    public void iSearchWithBelowKeywords(List<String> keywords) throws Throwable {
        for (String keyword : keywords) {
            i_type_a_key_word_in_mew_search(keyword);
        }
    }

    @Then("^I verify all discovery elements on \"([^\"]*)\" page$")
    public void iVerifyAllDiscoveryElementsOnPage(String page) throws Throwable {
        switch (page.toLowerCase()) {
            case "deals":
                shouldBeOnPage("deals_and_promotions");
                i_verify_GN_expanded_till(page);
                GlobalNav.closeGlobalNav();
                break;
            case "my account":
                shouldBeOnPage("my_account");
                break;
            case "macy's credit card":
                shouldBeOnPage("credit_service_gateway_signedin");
                break;
            case "wallet":
                shouldBeOnPage("oc_my_wallet");
                i_verify_GN_expanded_till(page);
                GlobalNav.closeGlobalNav();
                break;
            case "lists":
                shouldBeOnPage("wish_list");
                i_verify_GN_expanded_till(page);
                GlobalNav.closeGlobalNav();
                break;
            case "stores":
                shouldBeOnPage("stores");
                break;
            case "customer service":
            case "legal policies":
                Navigate.switchWindow(1);
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("customerservice"));
                Navigate.switchWindowClose();
                Clicks.clickWhenPresent("home.global_nav_button");
                Assert.assertTrue("Error - App: GN is not expanded till " + page, Elements.getText("home.gn_current_category").equalsIgnoreCase(page));
                GlobalNav.closeGlobalNav();
                break;
            case "order tracking":
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("service/contact/international/order"));
                Assert.assertTrue("Error - App: Not on '" + page + "' page", Wait.untilElementPresent(By.className("m-header-title")));
                Clicks.clickWhenPresent("home.global_nav_button");
                Assert.assertTrue("Error - App: GN is not expanded till " + page, Elements.getText("home.gn_current_category").equalsIgnoreCase(page));
                GlobalNav.closeGlobalNav();
                break;
            case "registry":
                Assert.assertTrue(WebDriverManager.getCurrentUrl().contains("www.customerservice-macys.com/app/answers/detail/a_id/5366?"));
                break;
            default:
                Assert.fail("Invalid option");
        }
        Assert.assertTrue("ERROR - ENV: Header is missing on " + page, Wait.secondsUntilElementPresent("header.header_container", 20));
        Assert.assertTrue("ERROR - ENV: Footer is missing on " + page, Wait.secondsUntilElementPresent(By.tagName("footer"), 20));
        logger.info("Verified all discovery elements on page " + page);
    }

    @Then("^I should not navigate to registry search page$")
    public void iShouldNotNavigateToRegistrySearchPage() throws Throwable {
        Assert.assertFalse("Error - App: Auto Suggestion selection in Domestic is taking into Registry Search Pages",
                WebDriverManager.getCurrentUrl().contains("/shop/registry/wedding/search"));
        logger.info("Verified Auto Suggestion selection in Domestic should not taking into Registry Search Pages");
    }

    @And("^I click on Menu in GN to navigate back to home page$")
    public void iClickOnMenuInGNToNavigateBackToHomePage() throws Throwable {
        GlobalNav.openGlobalNav();
        Clicks.click("mew_hnf.menu");
        shouldBeOnPage("home");
    }

    @When("^I click on Macys logo and navigate back to home page$")
    public void I_click_on_Macys_logo_and_navigate_to_home_page() throws Throwable {
        if (Elements.elementPresent("home.verify_page")) {
            Clicks.click("home.verify_page");
            shouldBeOnPage("home");
        } else if (Elements.elementPresent("mew_hnf.macys_logo")) {
            Clicks.click("mew_hnf.macys_logo");
            shouldBeOnPage("home");
        } else {
            Assert.fail("Unable to identify the Macys logo");
        }
        logger.info("Navigated to home page by tapping on macys logo");
    }

    @And("^I should see country logo in footer section$")
    public void iShouldSeeCountryLogoInFooterSection() throws Throwable {
        Assert.assertTrue("Error - Env: Country flag is not displaying in footer section", CommonUtils.verifyresponseCode(Elements.getElementAttribute("footer.flag", "src")));
        Assert.assertTrue("Error - Env: Wrong Country flag is displaying", Elements.getElementAttribute("footer.flag", "src").contains(Cookies.getCookieValue("shippingCountry")));
    }

    @When("^I tap on Wedding Registry link in left Nav$")
    public void iTapOnWeddingRegistryLinkInLeftNav() throws Throwable {
        GlobalNav.openGlobalNav();
        Clicks.clickElementByText(By.className("m-j-cm-element"), "Wedding Registry");
    }

    @Then("^I should be on the registry mobile home page$")
    public void iShouldLandOnTheRegistryMobileHomePage() throws Throwable {
        shouldBeOnPage("registry_home");
    }

    @Then("^I should be on the registry browse page$")
    public void iShouldBeOnTheRegistryBrowsePage() throws Throwable {
        shouldBeOnPage("category_browse");
        Assert.assertTrue("Error - Env: Not on registry browse page", WebDriverManager.getCurrentUrl().contains("shop/wedding-registry"));
    }

    @And("^I should see related brands in brand index page for For The Home FOB$")
    public void iShouldSeeRelatedBrandsInBrandIndexPageForForTheHomeFOB() throws Throwable {
        Assert.assertTrue("Error - App: All brands are showing up in brand index page for For The Home FOB", Elements.getText("brand_index.browse_cat_name").contains("Home"));
    }

    // BCOM Steps

    @Then("^I verify SIGN UP FOR EMAILS OR TEXTS page$")
    public void iVerifySIGNUPFOREMAILSORTEXTSPage() throws Throwable {
        Assert.assertTrue("", WebDriverManager.getCurrentUrl().contains("ebm.cheetahmail.com"));
        Assert.assertTrue("", Wait.untilElementPresent(By.className("contenttable")) &&
                Elements.getText(By.className("contenttable")).contains("Get exclusive offers, the latest trend reports, updates on new arrivals and so much more."));
    }
}
