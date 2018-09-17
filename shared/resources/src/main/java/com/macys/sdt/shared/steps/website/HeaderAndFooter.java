package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.interactions.Elements.getText;

public class HeaderAndFooter extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(HeaderAndFooter.class);


    public static void serverSideEFCKeyExperimentation(String efcKey) throws Throwable{
        String hnfRedesignExperimentURL;
        String currentURL = WebDriverManager.getCurrentUrl();
        //currentURL = currentURL + "?EFCKEY=" + "{%22EXPERIMENT%22:[%222529%22]}" + "#fn=" + "sp%3D1%26spc%3D1011%26ruleId%3D78|BS|BA%26slotId%3D2%26kws%3Ddresses%20-%20plus%20size%26searchType%3Dac%26ackws%3Ddres";
        if (currentURL.contains("#fn")){
            String[] s = currentURL.split("#fn");
            currentURL = s[0];
        }
        String efcKeyValue=null;
        String updatedEFCKeyValue=null;
        if (currentURL.contains("EFCKEY")) {

            currentURL = currentURL.replace("{", URLEncoder.encode("{","UTF-8"));
            currentURL = currentURL.replace("}",URLEncoder.encode("}","UTF-8"));
            currentURL = currentURL.replace("[",URLEncoder.encode("[","UTF-8"));
            currentURL = currentURL.replace("]",URLEncoder.encode("]","UTF-8"));
            currentURL = currentURL.replace(":",URLEncoder.encode(":","UTF-8"));
            URIBuilder currentURI = new URIBuilder(currentURL);

            List params = currentURI.getQueryParams();
            for (int i=0; i<params.size(); i++){
                if (((String) ((BasicNameValuePair) params.get(i)).getName()).contentEquals("EFCKEY")){
                    efcKeyValue = (String) ((BasicNameValuePair) params.get(i)).getValue();
                }
            }
            efcKeyValue = URLEncoder.encode(efcKeyValue,"UTF-8");
            if (efcKeyValue.contains("2525")){
                efcKeyValue = efcKeyValue.replace("%222525%22%2C","");
            }
            if (efcKeyValue.contains("2522")){
                efcKeyValue = efcKeyValue.replace("%222522%22%2C","");
            }
            if (efcKeyValue.contains("2523")){
                efcKeyValue = efcKeyValue.replace("%222523%22%2C","");
            }
            if (efcKeyValue.contains("2524")){
                efcKeyValue = efcKeyValue.replace("%222524%22%2C","");
            }
            String[] partialkey = efcKeyValue.split("%5D");
            updatedEFCKeyValue = partialkey[0] + "%2C" + "%22" + efcKey + "%22" + "%5D%7D";
            hnfRedesignExperimentURL = currentURL.replaceAll("EFCKEY(.*)", "EFCKEY=" + updatedEFCKeyValue);
            hnfRedesignExperimentURL = hnfRedesignExperimentURL + "&segment=" + efcKey;
            hnfRedesignExperimentURL = URLDecoder.decode(hnfRedesignExperimentURL,"UTF-8");
        }
        else if (currentURL.contains("?")) {
            updatedEFCKeyValue = "%7B%22EXPERIMENT%22%3A%5B%22" + efcKey + "%22%5D%7D";
            //updatedURI = currentURI.setParameter("EFCKEY",updatedEFCKeyValue);
            //hnfRedesignExperimentURL=updatedURI.toString();
            hnfRedesignExperimentURL = currentURL.concat("&EFCKEY=" + updatedEFCKeyValue + "&segment=" + efcKey);
        }
        else {
            updatedEFCKeyValue = "%7B%22EXPERIMENT%22%3A%5B%22" + efcKey + "%22%5D%7D";
            hnfRedesignExperimentURL = currentURL.concat("?EFCKEY=" + updatedEFCKeyValue + "&segment=" + efcKey);
        }
        Navigate.visit(hnfRedesignExperimentURL);

    }

    @And("^Navigate to \"([^\"]*)\" viewtype in new header footer experience$")
    public static void iNavigateToViewtypeInNewHeaderFooterExperience(String experimentType) throws Throwable {
        String hnfExp = RunConfig.getEnvOrExParam("hnf_exp");
        if (hnfExp == null || hnfExp.equalsIgnoreCase("true")) {
            // Write code here that turns the phrase above into concrete actions
            String efcExperimentationKey = null;
            if (macys()) {
                switch (experimentType) {
                    case "HnF Clean":
                        efcExperimentationKey = "2523";
                        break;
                    case "HnF Radical":
                        efcExperimentationKey = "2524";
                        break;
                    case "HnF Control":
                        efcExperimentationKey = "2522";
                        break;
                    case "HnF Holdout":
                        efcExperimentationKey = "2525";
                        break;
                    default:
                        throw new IllegalArgumentException("ERROR - DATA: No Experimentation for Header Footer passed" + efcExperimentationKey);
                }
                serverSideEFCKeyExperimentation(efcExperimentationKey);
            }
        }
        //throw new PendingException();
    }
    @Then("^I should see flyout menu$")
    public void iShouldSeeFlyoutMenu() throws Throwable {
       // Elements.elementShouldBePresent("home.open_flyout");
        iVerifyThatFlyoutMenuIsDisplayed();
    }

    @When("^I navigate to gift card category splash page$")
    public void iNavigateToGiftCardCategorySplashPage() throws Throwable {
        if(macys() && Elements.elementPresent("header.gift_dropdown"))
            Clicks.hoverForSelection("header.gift_dropdown");
        Wait.untilElementPresent("header.goto_gift_cards");
        Clicks.click("home.goto_gift_cards");
    }

    @And("^I should see new header and footer elements section$")
    public void iShouldSeeNewHeaderAndFooterElementsSection() throws Throwable {
        Elements.elementShouldBePresent("header.my_account_container");
        Elements.elementShouldBePresent("header.search_menu_container");
        Elements.elementShouldBePresent("header.category_menu_container");

        Elements.elementShouldBePresent("footer.global_footer");

        // macys links, exit if not on macys
        if (!macys()) {
            return;
        }
        // customer service
        Elements.elementShouldBePresent("footer.goto_us_site");
        Elements.elementShouldBePresent("footer.goto_order_status");
        Elements.elementShouldBePresent("footer.goto_shipping_and_delivery");
        Elements.elementShouldBePresent("footer.goto_returns");
        Elements.elementShouldBePresent("footer.goto_contact_us");
        Elements.elementShouldBePresent("footer.goto_para_ayuda");
        // credit services
        Elements.elementShouldBePresent("footer.goto_credit_services");
        Elements.elementShouldBePresent("footer.goto_credit_pay_bill_online");
        Elements.elementShouldBePresent("footer.goto_credit_cardholder_benefits");
        Elements.elementShouldBePresent("footer.goto_credit_apply_now");
        // our stores
        Elements.elementShouldBePresent("footer.goto_store_locations_and_hours");
        Elements.elementShouldBePresent("footer.goto_store_events");
        Elements.elementShouldBePresent("footer.goto_store_catalogs");
        Elements.elementShouldBePresent("footer.goto_tell_us_what_you_think");
        // macy's inc
        Elements.elementShouldBePresent("footer.goto_macys_jobs");
        Elements.elementShouldBePresent("footer.goto_press_room");
        Elements.elementShouldBePresent("footer.goto_investors");
        // social icons
        Elements.elementShouldBePresent("footer.goto_twitter");
        Elements.elementShouldBePresent("footer.goto_facebook");
        Elements.elementShouldBePresent("footer.goto_pinterest");
        Elements.elementShouldBePresent("footer.goto_youtube");
        Elements.elementShouldBePresent("footer.goto_m_blog");
        Elements.elementShouldBePresent("footer.goto_mobile_apps");
        Elements.elementShouldBePresent("footer.goto_email_sign_up");
        // legal
        Elements.elementShouldBePresent("footer.goto_legal_notice");
        Elements.elementShouldBePresent("footer.goto_pricing_policy");
        Elements.elementShouldBePresent("footer.goto_privacy_practices");
        Elements.elementShouldBePresent("footer.goto_interest_based_ads");
        Elements.elementShouldBePresent("footer.goto_customer_bill_of_rights");
        Elements.elementShouldBePresent("footer.goto_california_privacy_rights");
        Elements.elementShouldBePresent("footer.goto_my_ca_transparency");
    }

    @And("^I hover over on the below \"([^\"]*)\" fob's$")
    public void iHoverOverOnTheBelowFobS(String fob) throws Throwable {
        Clicks.hoverForSelection(Elements.paramElement("header.flyout_category", fob.toUpperCase()));
        logger.info("Performed hover on the " + fob + " fob's");
    }

    @Then("^I verify that flyout menu is displayed$")
    public void iVerifyThatFlyoutMenuIsDisplayed() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(5000);
        Assert.assertFalse("Flyout menu is not displayed",
                Elements.findElements("header.open_flyout").stream().filter(f -> f.isDisplayed()).collect(Collectors.toList()).isEmpty());
        logger.info("Verified that flyout menu is displayed");
    }

    @And("^I verify that \"([^\"]*)\" default message is displayed in search box$")
    public void iShouldSeeDefaultMessageInSearchBox(String keyword) throws Throwable {
        try {
            Wait.forPageReady();
            String displayedKeyword = Elements.findElement("home.search_field").getAttribute("value").trim();
            String keywordPlaceHolder = Elements.findElement("home.search_field").getAttribute("placeholder").trim();
            Assert.assertTrue("Expected default message in search box placeholder: " + keyword + " Actual displayed keyword place holder: " + keywordPlaceHolder,
                    keywordPlaceHolder.toLowerCase().contains(keyword.toLowerCase()));
            Assert.assertTrue("Expected default message in search box is not displayed,  Actual displayed keyword: " + displayedKeyword,
                    displayedKeyword.equals(""));
        } catch (Exception e) {
            Assert.fail("Expected keyword: " + keyword + " is not displayed" + e.getMessage());
        }
    }

    @Then("^I verify the (non secure|secure) page category flyouts for all categories$")
    public void iVerifyThePageCategoryFlyoutsForAllCategories(String page) throws Throwable {
        List<WebElement> fobs = Elements.findElements("home.category").stream().filter(a -> a.getAttribute("href") != null).collect(Collectors.toList());
        PageNavigation pageNavigation = new PageNavigation();
        List<WebElement> flext_elements = null;
        for (int i = 0; i < fobs.size(); i++) {
            String fobName = fobs.get(i).getText();
            Clicks.hoverForSelection(fobs.get(i));
            Assert.assertNotNull("ERROR - Flyout menu is not appearing for FOB:'"+fobName+"' on '"+page+"' pages", Elements.findElement(By.className("flexLabelLinksContainer")));
            flext_elements = Elements.findElement(By.className("flexLabelLinksContainer")).findElements(By.tagName("a"));
            WebElement random = flext_elements.get( new Random().nextInt(flext_elements.size()));
            String categoryName = random.getText();
            pageNavigation.iSelectSubcategoryFromFlyoutMenu(random.getText());
            Wait.forPageReady();
            logger.info("Clicked on sub category:'"+categoryName+"' link from the flyout menu for FOB:" +fobName);
            Navigate.browserBack();
            Wait.forPageReady();
            Thread.sleep(5000);
            fobs = Elements.findElements("home.category").stream().filter(a -> a.getAttribute("href") != null).collect(Collectors.toList());
        }
    }

    @Given("^I verify GNA for \"([^\"]*)\" mode$")
    public void i_verify_GNA_for_mode(String mode) throws Throwable {
        WebElement navBannerEle = Elements.findElement("header.nav_banner");
        List<WebElement> navAds = navBannerEle.findElements(By.tagName("img"));
        //FS $150 banner is not valid for Iship mode in BCOM
        //Banner should not display. Added validation for same
        if (mode.equalsIgnoreCase("iship") && bloomingdales()) {
            logger.info("On iship: GNA should not be displayed in BCOM iship mode");
            Assert.assertTrue("ERROR - DATA: GNA media data is available in mode:" + mode, navAds.size() == 0);
        } else {
            Assert.assertTrue("ERROR - DATA: GNA media data not available in mode:" + mode, navAds.size() > 0);
            logger.info("GNA verified successfully for mode: " + mode);
        }
    }

    @When("^I select '(customer service)' link from header$")
    public void iSelectCustomerServiceLinkFromHeader(String linkName) throws Throwable {
        Clicks.click(Elements.element("header.goto_customer_service"));
        Wait.forPageReady();
        logger.info("Selected '" + linkName+"' link from header panel");
    }

    @And("^I select \"([^\"]*)\" from sub nav menu$")
    public void iSelectFromSubNavMenu(String linkName) throws Throwable {
        Wait.secondsUntilElementPresent("header.goto_wedding_registry", 15);
        Clicks.javascriptClick("header.goto_wedding_registry");
        Wait.forPageReady();
        if (safari()) {
            Wait.secondsUntilElementPresent("registry_home.goto_find_registry", 30);
        }
        logger.info("Selected '" + linkName+"' link from header sub nav panel");
    }

    @Then("^I should be navigated to customer service page$")
    public void iShouldBeNavigatedToCustomerServicePage() throws Throwable {
        Assert.assertTrue("ERROR - APP: Customer service page is not displaying", title().equalsIgnoreCase("Macy's Customer Service Site") ||
                title().equalsIgnoreCase("International Gift Registry"));
        logger.info("Verify navition to customer service page");
    }

    @And("^I verify the new legal notice text in the footer:$")
    public void i_verify_the_new_legal_notice_text_in_the_footer(List<String> expectedLegalCopy) {
        logger.info("Footer is : " + getText("footer.legal_disclaimer_text"));
        expectedLegalCopy.forEach(legalCopy ->
                Assert.assertTrue("ERROR - APP: Legal notice copy is not updated in footer. Actual Footer text is: " + getText("footer.legal_disclaimer_text"), getText("footer.legal_disclaimer_text").contains(legalCopy)));
        logger.info("Legal notice copy is updated in footer");
    }

    @When("^I select random autocomplete suggestion$")
    public void iSelectRandomAutocompleteSuggestion() throws Throwable {
        try {
            Assert.assertFalse("ERROR - APP: Auto suggestion list is not populated", Elements.findElements(Elements.element("header.suggestions")).isEmpty());
            if (safari()) {
                Clicks.clickRandomElement("header.suggestions");
            } else {
                Wait.secondsUntilElementPresent("header.suggestions", 15);
                Clicks.click(Elements.findElements(Elements.element("header.suggestions")).stream().filter(el -> el.isDisplayed() && el.getText() != null).findAny().get());
            }
        } catch (Exception e) {
            Assert.fail("Element not present " + e);
        }
    }

    @When("^I navigate to \"([^\"]*)\" pattern url link from footer$")
    public void iNavigateToPatternUrlLinkFromFooter(String urlPattern) throws Throwable {
        pausePageHangWatchDog();
        List<WebElement> linksWithExpectedPattern = Elements.findElement("footer.global_footer").findElements(By.tagName("a"))
                .stream().filter(f -> f.isDisplayed() && f.getAttribute("href").toLowerCase().contains(urlPattern.toLowerCase())).collect(Collectors.toList());
        int index = linksWithExpectedPattern.size() <= 1 ? 0 : new Random().nextInt(linksWithExpectedPattern.size() - 1);
        logger.info("Selecting link from footer is: "+linksWithExpectedPattern.get(index).getText());
        Clicks.click(linksWithExpectedPattern.get(index));
        String currentUrl = WebDriverManager.getCurrentUrl();
        if (!currentUrl.contains(".fds")) {
            String navigateToURL = currentUrl.split(".com")[1];
            Navigate.visit(RunConfig.url + navigateToURL);
        }
        resumePageHangWatchDog();
    }

    @Then("^I hover over the quick bag$")
    public void i_hover_over_the_quick_bag() throws Throwable {
        String id = macys() ? "header.goto_shopping_bag" : "header.quickbag_hover";
        try {
            Clicks.hoverForSelection(Elements.findElement(id));
        } catch (Exception e) {
            logger.debug("Unable to hover over quickbag: "+e.getMessage());
        }
    }

    /**
     * Verifies that autocomplete suggestions are displayed
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see autocomplete suggestions$")
    public void iShouldSeeAutocompleteSuggestions() throws Throwable {
        Wait.secondsUntilElementPresent("header.suggestions_list", 30);
        Assert.assertTrue("ERROR - APP: Autocomplete suggestions are not displayed",
                Elements.elementPresent("header.suggestions_list"));
    }

    @And("^I verify the footer contains role contentinfo$")
    public void iVerifyTheFooterContainsRoleContentinfo() throws Throwable {
        Assert.assertTrue("Error - App:footer does not contain role - contentinfo",
                Elements.findElement("footer.global_footer").getAttribute("role").equals("contentinfo"));
    }

    @And("^I verify the country flag is hidden for website$")
    public void iVerifyTheCountryFlagIsHidden() throws Throwable {
        Assert.assertTrue("Error - App: aria-hidden attribute not present",
                Elements.findElement("footer.flag").getAttribute("aria-hidden").equals("true"));
        Assert.assertTrue("Error - App: tabindex attribute not present",
                Elements.findElement("footer.flag").getAttribute("tabindex").equals("-1"));
    }

    @And("^I verify the mobile app image is hidden$")
    public void iVerifyTheMobileAppImageIsHidden() throws Throwable {
        Assert.assertTrue("Error - App: aria-hidden attribute not present",
                Elements.findElement("footer.goto_mobile_apps").getAttribute("aria-hidden").equals("true"));
        Assert.assertTrue("Error - App: tabindex attribute not present",
                Elements.findElement("footer.goto_mobile_apps").getAttribute("tabindex").equals("-1"));
    }

    @Then("^I verify the font size is (\\d+) px$")
    public void iVerifyTheFontSizeIsAtLeastPx(int size) throws Throwable {
        Assert.assertTrue("Error - App: font size is not 11 px",
                Elements.findElement("footer.goto_credit_pay_bill_online").getCssValue("font-size").equals("11px"));
    }

    @And("^I verify the font is Helvetica, Arial,sans-serif$")
    public void iVerifyTheFontIsHelvetica() throws Throwable {
        Assert.assertTrue("Error - App: font family is not Helvetica, Arial,sans-serif",
                Elements.findElement("footer.goto_credit_pay_bill_online").getCssValue("font-family").equals("Helvetica"));
    }

    @And("^I verify the type color is #(\\d+)$")
    public void iVerifyTheTypeColorIs(int color) throws Throwable {
        Assert.assertTrue("Error - App: color is as per UI standard",
                Elements.findElement("footer.goto_credit_pay_bill_online").getCssValue("color").equals("#333333"));
    }

    @And("^I verify the whats happening at macys link does not contain label tag$")
    public void iVerifyTheWhatSHappeningAtMacysLinkDoesNotContainLabelTag() throws Throwable {
        Assert.assertFalse("what's happening link has label tag",
                Elements.findElement("footer.goto_whats_happening_at_macys").getTagName().equals("label"));
    }
}
