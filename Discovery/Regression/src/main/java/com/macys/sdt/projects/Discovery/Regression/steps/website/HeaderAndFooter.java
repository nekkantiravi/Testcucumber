package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by YH03383 on 5/23/2017.
 */
public class HeaderAndFooter extends StepUtils {

    private static final Logger log = LoggerFactory.getLogger(HeaderAndFooter.class);

    //To verify 3 links under GFA domestic mode=
    @Then("^I verify the image links under GFA in (?:domestic|registry) mode$")
    public void i_verify_image_links_under_GFA_in_domestic_mode() throws Throwable {
        Wait.forPageReady();
        if (Elements.elementPresent("footer.goto_turn_new_leaf")) {
            verify_gfa("footer.goto_turn_new_leaf", "macysgreenliving", "green_living.our_commitment");
        } else {
            Assert.assertTrue("GFA links are missing", false);
        }
        if (Elements.elementPresent("footer.goto_give_back")) {
            Wait.forPageReady();
            //Clicks.click("footer.scrolling_ad");
            Clicks.clickIfPresent("footer.scrolling_ad");
            verify_gfa("footer.goto_give_back", "macys-gives", "magic_of_living.magic_of_living_text");
        } else {
            Assert.assertTrue("GFA links are missing", false);
        }
        if (Elements.elementPresent("footer.goto_macys_video_channel")) {
            Wait.forPageReady();
            Clicks.click("footer.scrolling_ad");
            verify_gfa("footer.goto_macys_video_channel", "video-channel", "macys_video_channel.macys_video");

        } else {
            Assert.assertTrue("GFA links are missing", false);
        }
    }

    private void verify_gfa(String footerElement, String urlText, String validationElement) {
        try {
            Clicks.click(footerElement);
        }catch (Exception e){
            log.info("Waiting for "+urlText+" page load failed");
        }
        if (urlText.equalsIgnoreCase("video-channel")) {
            Navigate.browserRefresh();
        }
        try{Thread.sleep(25000);}catch(Exception e){}
        Wait.forPageReady();
        String gfa_URL = WebDriverManager.getWebDriver().getCurrentUrl();
        log.info(gfa_URL);
        Assert.assertTrue("ERROR: GFA text:'"+urlText+"' not displayed Properly in URL:"+gfa_URL, gfa_URL.contains(urlText));
        Assert.assertTrue("ERROR: GFA is not displaying for element:"+validationElement, Elements.findElement(validationElement).isDisplayed());
        log.info(Elements.findElement(validationElement).getText());
        Navigate.browserBack();
        Wait.forPageReady();
    }

    @Then("^I verify header global banner appears and contains the text \"([^\"]*)\"$")
    public void i_verify_header_global_banner_errors_and_contains_the_text(String banner) throws Throwable {
        if (macys()) {
            Elements.findElement("home.banner").click();
        }else {
            Elements.findElement("header.nav_banner").click();
        }
        Set<String> allHandles = WebDriverManager.getWebDriver().getWindowHandles();
        allHandles.remove(allHandles.iterator().next());
        String lastHandle = allHandles.iterator().next();
        WebDriverManager.getWebDriver().switchTo().window(lastHandle);
        if(macys()){
            Elements.findElement(By.className("header-text")).getText().contains(banner);
        } else {
            Elements.findElement(By.className("bl_pop_header")).getText().contains(banner);
        }
    }

    @Given("^I verify footer global banner appears and contains the free shipping text$")
    public void i_verify_footer_global_banner_appears_and_contains_the_free_shipping_text() throws Throwable {
        if (macys()) {
            Assert.assertTrue(Elements.findElement(By.className("header-text")).getText().contains("FREE SHIPPING"));
        } else {
            Assert.assertTrue(Elements.findElement(By.id("footerBanner")).findElement(By.tagName("a")).getAttribute("href").contains("freeship"));
            log.info("Global banner is not available");
        }
    }

    @Then("^I verify footer banner ads are displayed$")
    public void i_verify_footer_banner_ads_are_displayed() throws Throwable {
        List<WebElement> footerBanners = Elements.findElements("home.footer_banner_ads");


        //HP dislays 3 footer banners ads
        for (int i = 0; i < footerBanners.size(); i++) {
            footerBanners = Elements.findElements("home.footer_banner_ads");
            String text = footerBanners.get(i).getAttribute("alt");

            log.info("size= "+footerBanners.size());
            //atleast 3 footer banner ads should be present
            Assert.assertTrue("Footer banner ads are missing", (footerBanners.size() >= 3));

            //checking if footer banner ads are displayed or not
            Assert.assertTrue("footer banner is not visible for: " + footerBanners.get(i).getAttribute("alt"), footerBanners.get(i).isDisplayed());
            log.info("clicked footer banner ad:" + footerBanners.get(i).getAttribute("alt"));


            //checking if footer banner links navigating to correct URL
            footerBanners.get(i).click();
            Wait.forPageReady();
            String url = WebDriverManager.getWebDriver().getCurrentUrl();
            log.info(url);
            log.info(text.replace(" ", "-"));
            Assert.assertTrue("Footer banner Pages are not loaded properly for :" + text
                    , url.contains(text.replace(" ", "-"))|| DsvActions.responseCode(WebDriverManager.getCurrentUrl()));

            Navigate.browserBack();
            Wait.forPageReady();
        }

    }

    @Given("^I verify that \"([^\"]*)\" social icons are displayed on homepage$")
    public void i_verify_that_social_icons_are_displayed_on_homepage(String socialLink) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


        switch (socialLink.toLowerCase()) {
            case "facebook":
                Assert.assertTrue("Social links not available for :" + socialLink,
                        Elements.findElement("home.goto_facebook").isDisplayed());
                log.info("Social link :" + socialLink + " is present");
                break;

            case "twitter":
                Assert.assertTrue("Social links not available for :" + socialLink,
                        Elements.findElement("home.goto_twitter").isDisplayed());
                log.info("Social link :" + socialLink + " is present");
                break;

            case "pinterest":
                Assert.assertTrue("Social links not available for :" + socialLink,
                        Elements.findElement("home.goto_pinterest").isDisplayed());
                log.info("Social link :" + socialLink + " is present");
                break;

            case "youtube":
                Assert.assertTrue("Social links not available for :" + socialLink,
                        Elements.findElement("home.goto_youtube").isDisplayed());
                log.info("Social link :" + socialLink + " is present");
                break;

            case "m blog":
                Assert.assertTrue("Social links not available for :" + socialLink,
                        Elements.findElement("home.goto_m_blog").isDisplayed());
                log.info("Social link :" + socialLink + " is present");
                break;

            case "mobile app":
                Assert.assertTrue("Social links not available for :" + socialLink,
                        Elements.findElement("home.goto_mobile_apps").isDisplayed());
                log.info("Social link :" + socialLink + " is present");
                break;

            case "sign-up":
                Assert.assertTrue("Social links not available for :" + socialLink,
                        Elements.findElement("home.goto_email_sign_up").isDisplayed());
                log.info("Social link :" + socialLink + " is present");
                break;

            default:
                Assert.fail("Invalid Social Link: " + socialLink);
                break;
        }
    }

    @And("^I navigate to all fob categories from (registry) home page$")
    public void iNavigateToAllFobCategoriesFromHomePageWithMode(String mode) throws Throwable {
        Home homePage = new Home();
        List<String> homePageFobs = homePage.getExpectedMainCategories(mode);
        homePageFobs.forEach(fob ->{
            Wait.forPageReady();
            homePage.selectMainCategory(fob);
            Wait.forPageReady();
            try {
                Thread.sleep(15000);
            } catch (Exception e) {
                log.info("Waiting completed for selected page load:"+fob);
            }
            if(fob.equalsIgnoreCase("WEDDING REGISTRY")){
                shouldBeOnPage("registry_home");
                Assert.assertTrue("ERROR - APP: Navigating to '"+fob+"' category is failed in '"+mode+"' mode", WebDriverManager.getCurrentUrl().contains("/registry/wedding/registryhome"));
            }else if(fob.equalsIgnoreCase("GETTING STARTED")){
                shouldBeOnPage("registry_sign_in");
                Assert.assertTrue("ERROR - APP: Navigating to '"+fob+"' category is failed in '"+mode+"' mode", WebDriverManager.getCurrentUrl().contains("/registry/wedding/registrycaptureemail"));
            }else if(fob.equalsIgnoreCase("BRANDS") || fob.equalsIgnoreCase("DESIGNERS")){
                shouldBeOnPage("designer_static");
                Assert.assertTrue("ERROR - APP: Navigating to '"+fob+"' category is failed in '"+mode+"' mode", WebDriverManager.getCurrentUrl().contains("/shop/wedding-registry/all-brands"));
            }else if(fob.equalsIgnoreCase("WEDDING SHOP")){
                shouldBeOnPage("wedding_shop");
                Assert.assertTrue("ERROR - APP: Navigating to '"+fob+"' category is failed in '"+mode+"' mode", WebDriverManager.getCurrentUrl().contains("/social/wedding-shop/"));
            }
            else{
                if(macys()){
                    shouldBeOnPage("category_splash");
                    Assert.assertTrue("ERROR - APP: Navigating to '"+fob+"' category is failed in '"+mode+"' mode", WebDriverManager.getCurrentUrl().contains("/shop/wedding-registry/"));
                }else{
                    shouldBeOnPage("category_browse");
                    Assert.assertTrue("ERROR - APP: Navigating to '"+fob+"' category is failed in '"+mode+"' mode", WebDriverManager.getCurrentUrl().contains("/shop/wedding-registry/"));
                }
            }
            Navigate.browserBack();
            log.info("Verified '"+fob+"' category navigation successfully");
        });
        log.info("Verified all fob categories navigation successfully in mode:"+mode);
    }

    @And("^I should see new header and footer elements section in registry mode$")
    public void iShouldSeeNewHeaderAndFooterElementsSectionInRegistryMode() throws Throwable {
        // macys links, exit if not on macys
        if (macys()) {
            Elements.elementShouldBePresent("header.search_menu_container");
            Elements.elementShouldBePresent("header.category_menu_container");

            // customer service
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
        }else{
            Elements.elementShouldBePresent("header.goto_back_to_bloomingdales");
            Elements.elementShouldBePresent("header.goto_my_account_link");
            Elements.elementShouldBePresent("header.search_menu_container");
            Elements.elementShouldBePresent("header.category_menu_container");

            Elements.elementShouldBePresent("footer.goto_customer_service");
            Elements.elementShouldBePresent("footer.goto_credit_pay_bill_online");
            Elements.elementShouldBePresent("footer.goto_credit_cardholder_benefits");
            Elements.elementShouldBePresent("footer.goto_credit_apply_now");
            Elements.elementShouldBePresent("footer.goto_shipping_and_delivery");
            Elements.elementShouldBePresent("footer.goto_returns");
            Elements.elementShouldBePresent("footer.goto_international");
            Elements.elementShouldBePresent("footer.goto_my_account");
            Elements.elementShouldBePresent("footer.goto_order_status");
            Elements.elementShouldBePresent("footer.goto_loyallist");
            Elements.elementShouldBePresent("footer.goto_my_profile");
            Elements.elementShouldBePresent("footer.goto_company");
            Elements.elementShouldBePresent("footer.goto_about_us");
            Elements.elementShouldBePresent("footer.goto_b_cause");
            Elements.elementShouldBePresent("footer.goto_careers");
            Elements.elementShouldBePresent("footer.goto_ways_to_shop");
            Elements.elementShouldBePresent("footer.goto_online_and_mobile");
            Elements.elementShouldBePresent("footer.goto_stores");
            Elements.elementShouldBePresent("footer.goto_terms_of_use");
            Elements.elementShouldBePresent("footer.goto_privacy");
            Elements.elementShouldBePresent("footer.goto_ca_privacy_rights");
            Elements.elementShouldBePresent("footer.goto_my_ca_transparency");
            Elements.elementShouldBePresent("footer.goto_email_sign_up");
            Elements.elementShouldBePresent("footer.goto_visually_impaired");
            Elements.elementShouldBePresent("footer.social_icons");
        }
        log.info("Verified new header and footer elements section in registry mode");
    }

    @Then("^I verify the id of the HTML should contain flex for FOBs$")
    public void iVerifyTheIdOfTheHTMLShouldContainFlexForFOBs() throws Throwable {
        List<String> fobElementIDs = Elements.findElements("category_menu.fob_menu_list").stream().map(m -> m.getAttribute("id")).collect(Collectors.toList());
        fobElementIDs.forEach(fobID -> {
            Assert.assertTrue("ERROR - APP: Fob ID:"+fobID+" is not pre-fixed with flex text", fobID.contains("flex"));
        });
        log.info("Verified flex text prefix for all FOB element id values");
    }

    @Then("^I verify that new dropdown link names are displayed$")
    public void iVerifyThatNewDropdownLinkNamesAreDisplayed(List<String> expected) {
        Clicks.hoverForSelection("header.goto_my_account_link");
        Wait.untilElementPresent("header.myaccount_dropdown");
        List<String> actual = Elements.findElements("header.my_account_drop_down_links").stream().map(m -> m.getText().toUpperCase()).collect(Collectors.toList());
        expected = expected.stream().map(m -> m.toUpperCase()).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP: Expected my account drop down option text is not listed", expected.containsAll(actual));
        log.info("Verified that expected drop down with below options: " + expected);
    }
}
