package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.HeaderActions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.*;
import static java.util.stream.Collectors.toList;

public class Footer {

//    String url = "";
//    String title = "";
    private static final Logger log = LoggerFactory.getLogger(Header.class);
    public static String sitemode;

    @Then("^I verify all links for \"([^\"]*)\" in the Footer$")
    public void i_verify_all_links_for_customer_service_in_the_footer(String Footer_link) throws Throwable {
        String pageUrl = WebDriverManager.getCurrentUrl();
        switch (Footer_link) {
            case "Customer Service":
                Assert.assertTrue("ERROR - APP: Customer Service link is not displayed", Elements.findElement("home.goto_customer_service").isDisplayed());
                if (pageUrl.contains("macys")) {
                    Assert.assertTrue("ERROR - APP: Order tracking link is not displayed", Elements.findElement("home.goto_order_status").isDisplayed());
                    Assert.assertTrue("ERROR - APP: shipping and delivery link is not displayed", Elements.findElement("home.goto_shipping_and_delivery").isDisplayed());
                    Assert.assertTrue("ERROR - APP: Easy Returns link is not displayed", Elements.findElement("home.goto_easy_returns").isDisplayed());
                    Assert.assertTrue("ERROR - APP: Contact us link is not displayed", Elements.findElement("home.goto_contact_us").isDisplayed());
                    Assert.assertTrue("ERROR - APP: Para ayuda link is not displayed", Elements.findElement("home.goto_para_ayuda").isDisplayed());

                } else {
                    Assert.assertTrue("ERROR - APP: Shipping Policy link is not displayed", Elements.findElement("home.goto_shipping_and_delivery").isDisplayed());
                    Assert.assertTrue("ERROR - APP: Returns & Exchanges link is not displayed", Elements.findElement("home.goto_returns").isDisplayed());
                    Assert.assertTrue("ERROR - APP: International link is not displayed", Elements.findElement("home.goto_international").isDisplayed());
                }
                break;
            case "Credit Services":
                Assert.assertTrue("ERROR - APP: credit services link is not displayed", Elements.findElement("home.goto_credit_services").isDisplayed());
                Assert.assertTrue("ERROR - APP: credit pay bill is not displayed", Elements.findElement("home.goto_credit_pay_bill_online").isDisplayed());
                Assert.assertTrue("ERROR - APP: credit card holder link is not displayed", Elements.findElement("home.goto_credit_cardholder_benefits").isDisplayed());
                Assert.assertTrue("ERROR - APP: credit card apply link is not displayed", Elements.findElement("home.goto_credit_apply_now").isDisplayed());
                if (pageUrl.contains("macys"))
                    Assert.assertTrue("ERROR - APP: easy returns link is not displayed", Elements.findElement("home.goto_easy_returns").isDisplayed());
                break;

            case "Our Stores":
                Assert.assertTrue("ERROR - APP: Store locations and hours link is not displayed", Elements.findElement("home.goto_store_locations_and_hours").isDisplayed());
                Assert.assertTrue("ERROR - APP: store events link is not displayed", Elements.findElement("home.goto_store_events").isDisplayed());
                Assert.assertTrue("ERROR - APP: store catalogues link is not displayed", Elements.findElement("home.goto_store_catalogs").isDisplayed());
                Assert.assertTrue("ERROR - APP: tell us what you think link is not displayed", Elements.findElement("home.goto_tell_us_what_you_think").isDisplayed());
                break;

            case "MACY'S INC":
            case "MACY'S INC.":
                Assert.assertTrue("ERROR - APP: Macys jobs link is not displayed", Elements.findElement("home.goto_macys_jobs").isDisplayed());
                Assert.assertTrue("ERROR - APP: Press room link is not displayed", Elements.findElement("home.goto_press_room").isDisplayed());
                Assert.assertTrue("ERROR - APP: investors link is not displayed", Elements.findElement("home.goto_investors").isDisplayed());
                break;
            case "My Account":
                Assert.assertTrue("ERROR - APP: My Account link is not displayed", Elements.findElement("home.goto_my_account").isDisplayed());
                Assert.assertTrue("ERROR - APP: Order Status link is not displayed", Elements.findElement("home.goto_order_status").isDisplayed());
                Assert.assertTrue("ERROR - APP: Loyalist link is not displayed", Elements.findElement("home.goto_loyallist").isDisplayed());
                Assert.assertTrue("ERROR - APP: MyProfile link is not displayed", Elements.findElement("home.goto_my_profile").isDisplayed());
                break;
            case "Company":
                Assert.assertTrue("ERROR - APP: Go to Company link is not displayed", Elements.findElement("home.goto_company").isDisplayed());
                Assert.assertTrue("ERROR - APP: Abouts Us  link is not displayed", Elements.findElement("home.goto_about_us").isDisplayed());
                Assert.assertTrue("ERROR - APP: BCause link is not displayed", Elements.findElement("home.goto_b_cause").isDisplayed());
                Assert.assertTrue("ERROR - APP: Careers link is not displayed", Elements.findElement("home.goto_careers").isDisplayed());
                break;
            case "Ways to Shop":
                Assert.assertTrue("ERROR - APP: Ways to Shop link is not displayed", Elements.findElement("home.goto_ways_to_shop").isDisplayed());
                Assert.assertTrue("ERROR - APP: Mobile link is not displayed", Elements.findElement("home.goto_online_and_mobile").isDisplayed());
                Assert.assertTrue("ERROR - APP: Stores link is not displayed", Elements.findElement("home.goto_stores").isDisplayed());
                Assert.assertTrue("ERROR - APP: Pick Up In Store link is not displayed", Elements.findElement(By.linkText("Pick Up In Store")).isDisplayed());
                break;
            case "Social Media":
                Assert.assertTrue("ERROR - APP: Social Media icons are not displayed", Elements.findElement(By.className("socialLinks")).findElements(By.tagName("a")).size() == 7);
                break;

            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid Argument: " + Footer_link);
        }
    }

    @Given("^I verify footer global banner appears without errors and contains the free shipping text$")
    public void i_verify_footer_global_banner_appears_without_errors_and_contains_the_free_shipping_text() throws Throwable {
        Assert.assertTrue(Elements.findElement(By.id("footerBanner")).findElement(By.tagName("a")).getAttribute("href").contains("freeship"));
        log.info("Global banner is not available for ISHIP mode");
    }

    @When("^I navigate to \"([^\"]*)\" footer links$")
    public void I_Navigate_to_footer_links(String link) throws Throwable {
        scrollToLazyLoadElement("home.footer_menu_section");
        if (link.equals("MY ACCOUNT")) {
            Clicks.click(Elements.findElement(By.xpath("//*[@id=\"footerLinks\"]/ul/li[2]/h3/a")));
        } else {
            Clicks.click(By.linkText(link));
            if (link.equals("catalogs")) {
                Clicks.clickWhenPresent("home.verify_page");
            }
        }
    }

    @Then("^I verify the display of GFA$")
    public void i_verify_display_of_GFA() throws Throwable {
        Wait.forPageReady();
        if (WebDriverManager.getCurrentUrl().contains("/shop/product")) {
            Navigate.scrollPage(0, 2000);
        }
        Assert.assertTrue("GFA menu not displayed", Elements.findElement("footer.goto_footer_ad_pool").isDisplayed());
    }

    @Then("^I verify the image links under GFA$")
    public void i_verify_image_links_under_GFA() throws Throwable {
        Wait.forPageReady();
        if (Elements.elementPresent("footer.goto_turn_new_leaf")) {
            Clicks.click("footer.goto_turn_new_leaf");
        }
        Thread.sleep(25000);
        String Leaf_URL = WebDriverManager.getWebDriver().getCurrentUrl();
        System.out.println(Leaf_URL);
        Assert.assertTrue(Leaf_URL.contains("living"));
        Navigate.browserBack();
        Wait.forPageReady();
        if (Elements.elementPresent("footer.goto_give_back")) {
            Clicks.click("footer.goto_give_back");
        }
        Thread.sleep(25000);
        String Living_URL = WebDriverManager.getWebDriver().getCurrentUrl();
        System.out.println(Living_URL);
        Assert.assertTrue(Living_URL.contains("magic-of-giving") || Living_URL.contains("social/macys-gives"));
        Navigate.browserBack();
        Wait.forPageReady();
        if (Elements.elementPresent("footer.goto_macys_video_channel")) {
            Clicks.click("footer.goto_macys_video_channel");
        }
        Thread.sleep(25000);
        String macys_video_URL = WebDriverManager.getCurrentUrl();
        System.out.println(macys_video_URL);
        Assert.assertTrue(macys_video_URL.contains("video-channel"));
    }

    @Then("^I verify the display of GNA and GFA$")
    public void i_verify_display_of_GNA_and_GFA() throws Throwable {
        Wait.forPageReady();
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/50)");
        Assert.assertTrue("ERROR - DATA: GNA banner not displayed", Elements.findElement("header.header_pool").isDisplayed());
        if(macys()){
            Assert.assertTrue("ERROR - DATA: GFA menu not displayed", Elements.findElement("footer.goto_footer_ad_pool").isDisplayed());
        }else {
            Assert.assertTrue("ERROR - DATA: GFA menu not displayed", Elements.findElement("footer.goto_new_footer_ad_pool").isDisplayed());
        }
    }

    @And("^I verify GFA consistency$")
    public void i_verify_GFA_consistency() throws Throwable {
        Wait.forPageReady();
        if (bloomingdales() && HeaderActions.getSiteMode().equalsIgnoreCase("iship")) {
            log.info("GFA media is not verified as BCOM ISHIP mode data won't be setup by SITEMERCH team");
        } else {
            List<WebElement> footerlinks = Elements.findElement(By.id(macys()? "hnfGlobalBodyFooterAdPool": "footerBanner")).findElements(By.tagName(("img")));
            Assert.assertFalse("ERROR - DATA: GFA media Ads not available", footerlinks.isEmpty());
            List<String> AllLinks_text_footer = footerlinks.stream().filter(ele -> !ele.getAttribute("src").isEmpty()).map(ele -> ele.getAttribute("src")).collect(Collectors.toList());
            AllLinks_text_footer.forEach(link -> {
                int responseCode = RESTOperations.doGET(link, null).getStatus();
                Assert.assertTrue("ERROR - APP: GFA media URL:" + link + " is returning response code:" + responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
            });
        }
    }

    @Then("^I verify the legal notice pages \"([^\"]*)\" are rendered properly$")
    public void iVerifythlegalnoticepagesareRenderedProperly(String legalnoticelink) throws Throwable {
        log.info("Verification started");
        Wait.forPageReady();
        Assert.assertTrue("legal notice pages Link " + legalnoticelink + "is not displayed", Elements.findElement(By.linkText(legalnoticelink)).isDisplayed());
        Clicks.click(Elements.findElement(By.linkText(legalnoticelink)));
        Wait.forPageReady();
        switch (legalnoticelink) {
            case "Legal Notice":
                Assert.assertTrue("'ERROR - APP: Legal Notice- page not displayed'", WebDriverManager.getCurrentUrl().contains("/app/answers/detail/a_id/39/"));
                break;

            case "Pricing Policy":
                Assert.assertTrue("'ERROR - APP: Pricing Policy page not displayed'", WebDriverManager.getCurrentUrl().contains("app/answers/detail/a_id/14/"));
                break;

            case "Privacy Practices":
                Assert.assertTrue("'ERROR - APP: Privacy Practices page not displayed'", WebDriverManager.getCurrentUrl().contains("app/answers/detail/a_id/40/"));
                break;

            case "Interest Based Ads":
                Assert.assertTrue("'ERROR - APP: Interest Based Ads page not displayed'", WebDriverManager.getCurrentUrl().contains("app/answers/detail/a_id/595#adv"));
                break;

            case "Customer Bill of Rights":
                Assert.assertTrue("'ERROR - APP: Customer Bill of Rights page not displayed'", WebDriverManager.getCurrentUrl().contains("/social-responsibility/customer-bill-of-rights/default.aspx"));
                break;

            case "California Privacy Rights":
                Assert.assertTrue("'ERROR - APP: California Privacy Rights page not displayed'", WebDriverManager.getCurrentUrl().contains("app/answers/detail/a_id/595#california"));
                break;

            case "CA Transparency in Supply Chains Act":
                Assert.assertTrue("'ERROR - APP: Human Trafficking page not displayed'", WebDriverManager.getCurrentUrl().contains("/social-responsibility/human-trafficking/default.aspx"));
                break;

            case "Product Recalls":
                Assert.assertTrue("'ERROR - APP: Product Recalls page not displayed'", WebDriverManager.getCurrentUrl().contains("app/answers/detail/a_id/137"));
                break;

            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid legal notice link page" + legalnoticelink);

        }
        log.info("Basic attributes of page is verified");
    }

    @Then("^I verify the WHAT'S HAPPENING AT MACY'S link in the Footer$")
    public void i_verify_the_what_happenings_at_macys_link_n_the_footer() throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("footer.goto_whats_happening_at_macys");
    }

    @And("^I verify the  WHAT'S HAPPENING AT MACY'S page is rendered properly$")
    public void i_verify_the_whats_happening_at_macys_page_is_rendered_properly() throws Throwable {
        if(edge()){
            Clicks.javascriptClick("footer.goto_whats_happening_at_macys");
        }else{
            Clicks.click("footer.goto_whats_happening_at_macys");
        }
        Wait.forPageReady();
        Assert.assertTrue("'ERROR - APP: Whats happening at macys page not displayed'", title().contains("Macy's| Corporate Campaigns"));
    }

    @Then("^I verify all links for Stay Connected in the Footer$")
    public void i_verify_all_links_for_stay_connected_in_the_footer() throws Throwable {
        Wait.forPageReady();
        Elements.elementShouldBePresent("footer.stay_connected");
        Elements.elementShouldBePresent("footer.goto_facebook");
        Elements.elementShouldBePresent("footer.goto_pinterest");
        Elements.elementShouldBePresent("footer.goto_youtube");
        Elements.elementShouldBePresent("footer.goto_m_blog");
    }


    @Then("^I verify basic attributes in online catelogs page$")
    public void i_verify_basic_attributes_in_online_catelogs_pages() throws Throwable {
        Elements.elementShouldBePresent("home.goto_store_catalogs");
        Clicks.click("home.goto_store_catalogs");
        Thread.sleep(1000);
        Wait.forPageReady();
        Assert.assertTrue("Error - Online Catalogs Page is not loaded", title().contains("Macy's - Online Catalogs"));
    }

    @And("^I verify the stay connected pages are rendered properly$")
    public void i_verify_the_sta_connected_pages_are_rendered_properly() throws Throwable {
        Wait.forPageReady();
        Clicks.click("footer.goto_facebook");
        Thread.sleep(2000);
        Wait.forPageReady();
        Assert.assertTrue("Error - App: Macys Facebook page is not loaded", WebDriverManager.getCurrentUrl().contains("facebook"));
        Navigate.browserBack();
        Clicks.click("footer.goto_pinterest");
        Thread.sleep(3000);
        Wait.forPageReady();
        Assert.assertTrue("Error - App: Macys pinterest page is not loaded", title().contains("Pinterest"));
        Navigate.browserBack();
        Clicks.click("footer.goto_twitter");
        Thread.sleep(2000);
        Wait.forPageReady();
        Assert.assertTrue("Error - App: Macys Twitter page is not loaded", title().contains("Twitter"));
        Navigate.browserBack();
        Clicks.click("footer.goto_m_blog");
        Thread.sleep(2000);
        Wait.forPageReady();
        Assert.assertTrue("Error - App: Macys mBlog page is not loaded", title().contains("mBlog"));
        Navigate.browserBack();
        Clicks.click("footer.goto_youtube");
        Thread.sleep(2000);
        Wait.forPageReady();
        Assert.assertTrue("Error - App: Macys youtube page is not loaded", title().contains("YouTube"));
        Navigate.browserBack();
        Clicks.click("footer.stay_connected");
        Thread.sleep(2000);
        Wait.forPageReady();
        Assert.assertTrue("Error - App: Macys youtube page is not loaded", WebDriverManager.getCurrentUrl().contains("/ce/splash/macys-connect/index"));
    }

    @And("^I note the Footer Ad count on Home Page$")
    public void i_note_the_footer_add_count_on_home_page() throws Throwable {
        Assert.assertTrue("Footer Ad is not available!!", Elements.findElement("home.goto_footer_banner").isDisplayed());
        List<WebElement> footer_image_home = Elements.findElement("home.goto_footer_banner").findElements(By.tagName("img"));
        int footer_image_home_count = footer_image_home.stream().map(ele -> ele.getAttribute("src")).collect(Collectors.toList()).size();
        log.info("footer_image_home_count: " + footer_image_home_count);
        List<WebElement> footer_area_home = Elements.findElement("home.goto_footer_banner").findElements(By.tagName("area"));
        int footer_area_home_count = footer_area_home.stream().map(ele -> ele.getAttribute("alt")).collect(Collectors.toList()).size();
        log.info("footer_area_home_count: " + footer_area_home_count);
    }

    @And("^I verify Footer Ads are not present on current page$")
    public void i_verify_footer_ads_are_not_present_on_current_page() throws Throwable {
        Assert.assertFalse("ERROR - DATA: Footer Ad should not be visible", Elements.elementPresent("home.goto_footer_banner"));
    }

    @Then("^I should verify all footer links and images are functioning properly$")
    public void i_should_verify_all_footer_links_and_images_are_functioning_properly() {
        Assert.assertTrue("ERROR - ENV: Main footer is not displayed", Elements.findElement("home.footer_menu_section").isDisplayed());

        List<WebElement> toplinks = Elements.findElement("home.footer_menu_section").findElements(By.tagName("a"));
        List<String> AllLinks_text_footer = toplinks.stream().filter(ele -> ele.isDisplayed()).map(ele -> ele.getAttribute("href")).collect(Collectors.toList());

        //Verifying Sitemap link
        Assert.assertTrue("ERROR - ENV: Sitemap link not displayed", Elements.findElement("home.goto_site_map").isDisplayed());

        //All Footer images

        List<WebElement> Footer_images = Elements.findElement("home.category").findElements(By.tagName("img"));
        AllLinks_text_footer = Footer_images.stream().filter(ele -> ele.isDisplayed()).map(ele -> ele.getAttribute("src")).collect(toList());
        System.out.println("All Links" + AllLinks_text_footer);
        System.out.println("All Links count" + AllLinks_text_footer.size());

        AllLinks_text_footer.forEach(links -> {
            int Http_href_code = RESTOperations.doGET(links, null).getStatus();
            if (Http_href_code == 404) {
                Assert.fail("Response code for href is not 200:" + Http_href_code);
            }
        });
    }

    @When("^I select twitter icon in the footer$")
    public void i_select_twitter_icon_in_the_footer() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("ERROR - twitter element is not displaying !!", Elements.elementPresent("footer.goto_twitter"));
        Clicks.click(Elements.element("footer.goto_twitter"));
    }

    @Then("^I should be navigated to macys twitter page$")
    public void i_should_be_navigated_to_macys_twitter_page() throws Throwable {
        Thread.sleep(1000);
        Wait.forPageReady();
        Assert.assertTrue("Page not Successfully navigated to Macy's Twitter page", title().contains("Twitter"));
    }

    @Then("^I verify all links for (Customer Service|Credit Services|My Account|Company|Ways to Shop|Our Stores|MACY'S INC|MACY'S INC.) in the Footer$")
    public void i_verify_all_links_for_customer_services_in_the_footer(String Footer_link) throws Throwable {
        Navigate.scrollPage(0, 1000);
        Navigate.scrollPage(0, -1000);
        switch (Footer_link) {
            case "Customer Service":
                Assert.assertTrue("ERROR - APP: Customer Service link is not displayed", Elements.findElement("footer.goto_customer_service").isDisplayed());
                Assert.assertTrue("ERROR - APP: Order tracking link is not displayed", Elements.findElement("footer.goto_order_status").isDisplayed());
                Assert.assertTrue("ERROR - APP: shipping and delivery link is not displayed", Elements.findElement("footer.goto_shipping_and_delivery").isDisplayed());
                Assert.assertTrue("ERROR - APP: Easy Returns link is not displayed", Elements.findElement("footer.goto_easy_returns").isDisplayed());
                Assert.assertTrue("ERROR - APP: Contact us link is not displayed", Elements.findElement("footer.goto_contact_us").isDisplayed());
                Assert.assertTrue("ERROR - APP: Para ayuda link is not displayed", Elements.findElement("footer.goto_para_ayuda").isDisplayed());
                break;
            case "Credit Services":
                Assert.assertTrue("ERROR - APP: credit services link is not displayed", Elements.findElement("footer.goto_credit_services").isDisplayed());
                Assert.assertTrue("ERROR - APP: credit pay bill is not displayed", Elements.findElement("footer.goto_credit_pay_bill_online").isDisplayed());
                Assert.assertTrue("ERROR - APP: credit card holder link is not displayed", Elements.findElement("footer.goto_credit_cardholder_benefits").isDisplayed());
                Assert.assertTrue("ERROR - APP: easy returns link is not displayed", Elements.findElement("footer.goto_easy_returns").isDisplayed());
                Assert.assertTrue("ERROR - APP: credit card apply link is not displayed", Elements.findElement("footer.goto_credit_apply_now").isDisplayed());
                break;

            case "Our Stores":
                Assert.assertTrue("ERROR - APP: Store locations and hours link is not displayed", Elements.findElement("footer.goto_store_locations_and_hours").isDisplayed());
                Assert.assertTrue("ERROR - APP: store events link is not displayed", Elements.findElement("footer.goto_store_events").isDisplayed());
                Assert.assertTrue("ERROR - APP: store catalogues link is not displayed", Elements.findElement("footer.goto_store_catalogs").isDisplayed());
                Assert.assertTrue("ERROR - APP: tell us what you think link is not displayed", Elements.findElement("footer.goto_tell_us_what_you_think").isDisplayed());
                break;

            case "MACY'S INC.":
            case "MACY'S INC":
                Assert.assertTrue("ERROR - APP: Macys jobs link is not displayed", Elements.findElement("footer.goto_macys_jobs").isDisplayed());
                Assert.assertTrue("ERROR - APP: Press room link is not displayed", Elements.findElement("footer.goto_press_room").isDisplayed());
                Assert.assertTrue("ERROR - APP: investors link is not displayed", Elements.findElement("footer.goto_investors").isDisplayed());
                break;

            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid Argument: " + Footer_link);
        }
    }

    @Then("^I verify the basic attributes of (Credit Services|Customer Service|MACY'S INC|MACY'S INC.|Our Stores|order tracking|shipping & delivery|returns|contact us|para ayuda|pay bill|cardholder benefits|apply & learn more) links in the Footer$")
    public void i_verify_the_basic_attributes_of_services_links_in_the_Footer(String links_footer) throws Throwable {
        pausePageHangWatchDog();
        switch (links_footer) {
            case "Customer Service":
                scrollToLazyLoadElement("footer.goto_customer_service");
                Wait.forPageReady();
                Clicks.click("footer.goto_customer_service");
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Customer Service page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("/app?"));
                Navigate.browserBack();
                scrollToLazyLoadElement("footer.goto_order_status");
                Clicks.click("footer.goto_order_status");
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys Order Status page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("service/order-status?"));
                Navigate.browserBack();
                scrollToLazyLoadElement("footer.goto_shipping_and_delivery");
                Clicks.click("footer.goto_shipping_and_delivery");
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys Shipping and delivery page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("/app/answers/list/c/3"));
                Navigate.browserBack();
                scrollToLazyLoadElement("footer.goto_easy_returns");
                Clicks.click("footer.goto_easy_returns");
                Thread.sleep(1000);
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys Easy Returns page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("/app/answers/list/c/4"));
                Navigate.browserBack();
                scrollToLazyLoadElement("footer.goto_contact_us");
                Clicks.click("footer.goto_contact_us");
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys Contact us page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("/app/contact"));
                Navigate.browserBack();
                scrollToLazyLoadElement("footer.goto_para_ayuda");
                Clicks.click("footer.goto_para_ayuda");
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys Para ayuda page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("/app/answers/list/c/13"));
                Navigate.browserBack();
                break;

            case "Credit Services":
                Wait.forPageReady();
                scrollToLazyLoadElement("footer.goto_credit_services");
                closePopup();
                Clicks.click("footer.goto_credit_services");
                Wait.forPageReady();
                Thread.sleep(1000);
                Assert.assertTrue("Error - App: Macys Credit Gateway page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("/my-credit/gateway/guest"));
                Navigate.browserBack();
                Thread.sleep(2000);
                Wait.untilElementPresent("footer.goto_credit_pay_bill_online");
                scrollToLazyLoadElement("footer.goto_credit_pay_bill_online");
                closePopup();
                Clicks.click("footer.goto_credit_pay_bill_online");
                Thread.sleep(1000);
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys Store Credit pay bill page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("/account/signin?"));
                Navigate.browserBack();
                Thread.sleep(2000);
                try {
                    scrollToLazyLoadElement("footer.goto_credit_cardholder_benefits");
                    closePopup();
                    Clicks.click("footer.goto_credit_cardholder_benefits");
                } catch (Exception e) {
                    log.info("Exception occurred for Card Holder Benefits link selection from footer:" + e.getMessage());
                }
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys Credit benefit page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("ce/creditservice/marketing/benefits?"));
                Navigate.browserBack();
                break;

            case "Our Stores":
                scrollToLazyLoadElement("footer.goto_store_locations_and_hours");
                Wait.forPageReady();
                Clicks.click("footer.goto_store_locations_and_hours");
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys stores location & hours page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("shop/store/search?"));
                Navigate.browserBack();
                scrollToLazyLoadElement("footer.goto_store_events");
                Clicks.click("footer.goto_store_events");
                Wait.forPageReady();
                Assert.assertTrue("Error - App: Macys Store Events page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("social/events/"));
                Navigate.browserBack();
                scrollToLazyLoadElement("footer.goto_store_catalogs");
                Clicks.click("footer.goto_store_catalogs");
                Wait.forPageReady();
                Thread.sleep(2000);
                Assert.assertTrue("Error - App: Macys Online Catalogs page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("/flyers"));
                Navigate.browserBack();
                break;

            case "MACY'S INC.":
            case "MACY'S INC":
                Wait.forPageReady();
                scrollToLazyLoadElement("footer.goto_macys_jobs");
                Clicks.click("footer.goto_macys_jobs");
                Wait.forPageReady();
                Set<String> windowSet = WebDriverManager.getWebDriver().getWindowHandles();
                WebDriverManager.getWebDriver().switchTo().window((String) windowSet.toArray()[1]);
                Assert.assertTrue("Error - App: Macys Jobs page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("macysjobs.com"));
                WebDriverManager.getWebDriver().switchTo().window((String) windowSet.toArray()[0]);
                scrollToLazyLoadElement("footer.goto_press_room");
                Clicks.click("footer.goto_press_room");
                Wait.forPageReady();
                WebDriverManager.getWebDriver().switchTo().window((String) windowSet.toArray()[1]);
                Assert.assertTrue("Error - App: Macys press room page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("macysinc.com/press-room/"));
                WebDriverManager.getWebDriver().switchTo().window((String) windowSet.toArray()[0]);
                scrollToLazyLoadElement("footer.goto_investors");
                Clicks.click("footer.goto_investors");
                Wait.forPageReady();
                WebDriverManager.getWebDriver().switchTo().window((String) windowSet.toArray()[1]);
                Assert.assertTrue("Error - App: Macys Investors page is not loaded", WebDriverManager.getWebDriver().getCurrentUrl().contains("investors.macysinc.com/"));
                Navigate.browserBack();
                break;

            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid Argument: " + links_footer);
        }
        resumePageHangWatchDog();
    }

    @Then("^I verify the \"(.*?)\" are rendered properly$")
    public void iVerifytheAdspotLinksareRenderedProperly(String Footerlink) throws Throwable {
        log.info("Verification started");
        Wait.forPageReady();
        Assert.assertTrue("Ad Spot Link " + Footerlink + "is not displayed", Elements.findElement(By.xpath("//area[@alt='" + Footerlink + "']")).isDisplayed());
        if(edge()){
            Clicks.javascriptClick(Elements.findElement(By.xpath("//area[@alt='" + Footerlink + "']")));
        }else{
            Clicks.click(Elements.findElement(By.xpath("//area[@alt='" + Footerlink + "']")));
        }
        Wait.forPageReady();
        switch (Footerlink) {
            case "macys video channel":
                Assert.assertTrue("'ERROR - APP: Macys Video channel page not displayed'", title().contains("Video Channel"));
                break;

            case "green living":
                Assert.assertTrue("'ERROR - APP: Green Living page not displayed'", title().contains("Green Living"));
                break;

            case "the magic of giving":
                Assert.assertTrue("'ERROR - APP: Magic of Giving page not displayed'", title().contains("Magic of Giving"));
                break;

            default:
                throw new IllegalArgumentException("ERROR - DATA: Invalid Footer link page" + Footerlink);

        }
        log.info("Basic attributes of page is verified");
    }

    @When("^I navigate to \"Sitemap\" link in the footer panel$")
    public void i_navigate_to_links_in_the_footer_panel() throws Throwable {
        Assert.assertTrue("ERROR - ENV: Sitemap link not displayed", Elements.findElement("home.goto_site_map").isDisplayed());
        Clicks.click(Elements.findElement("home.goto_site_map"));
    }

    @Then("^I verify the footer links in \"([^\"]*)\" Mode$")
    public void i_verify_the_footer_links_in_domestic_mode(String mode) throws Throwable {
        Wait.forPageReady();
        sitemode = mode;
        if (sitemode.contentEquals("Domestic") || sitemode.contentEquals("Registry")) {
            Elements.elementPresent("home.goto_credit_services");
            Elements.elementPresent("home.goto_credit_pay_bill_online");
            Elements.elementPresent("home.goto_credit_cardholder_benefits");
            Elements.elementPresent("home.home.goto_credit_apply_now");
            Elements.elementPresent("home.goto_store_locations_and_hours");
            Elements.elementPresent("home.goto_email_sign_up");
        }
        if (sitemode.contentEquals("Iship")) {

            Elements.elementShouldBePresent("home.goto_frequently_asked_questions");
            Elements.elementShouldBePresent(macys() ? "home.goto_find_a_store" : "home.goto_our_stores");
            Elements.elementShouldBePresent("home.goto_visitors_services");

            Assert.assertFalse("Error -app : credit services link should not present", Elements.elementPresent("home.goto_credit_services"));
            Assert.assertFalse("Error -app : credit card bill pay link should not present", Elements.elementPresent("home.goto_credit_pay_bill_online"));
            //Assert.assertFalse("Error -app : My Account link should not be present", Elements.elementPresent("home.goto_credit_cardholder_benefits"));
            Assert.assertFalse("Error -app : credit card apply now link not present", Elements.elementPresent("home.goto_credit_apply_now"));
            Assert.assertFalse("Error -app : My Account link should not be present", Elements.elementPresent("home.goto_my_account_link"));
            Assert.assertFalse("Error -app : sign link should not be present", Elements.elementPresent("home.goto_sign_in_link"));
        }
    }

    @Then("^I should see header footer ad pools in \"(SITE|REGISTRY|ISHIP)\" mode$")
    public void i_should_see_header_footer_ad_pools_in_modes(String mode_selected) throws Throwable {
        if (macys()) {
            Navigate.scrollPage(0, 1000);
            Navigate.scrollPage(0, -1000);
            Elements.elementPresent("footer.goto_turn_new_leaf");
            Elements.elementPresent("footer.goto_give_back");
            Elements.elementPresent("footer.goto_back_stage");
            Elements.elementPresent("footer.goto_footer_ad_pool");
            Assert.assertTrue("GNA banner not displayed", Elements.elementPresent("header.header_pool"));
            if (macys() && Elements.elementPresent("header.gift_dropdown"))
                Clicks.hoverForSelection("header.gift_dropdown");
            Wait.untilElementPresent("header.goto_gift_cards");
            if (mode_selected.equalsIgnoreCase("REGISTRY") || mode_selected.equalsIgnoreCase("ISHIP"))
                Assert.assertNull("Error - APP: Mother guide is visible in mode:"+mode_selected, Elements.findElement("header.gift_guide"));
            else
                Assert.assertTrue("Error - APP: Mother guide is not visible", Elements.findElement("header.gift_guide").isDisplayed());
        } else {
            Elements.elementShouldBePresent("header.header_adpool");
            Elements.elementShouldBePresent("footer.footer_banner");
        }
    }

    @Then("^I verify navigated to \"([^\"]*)\" page$")
    public void iVerifyNavigatedToPage(String footerlinkText) throws Throwable {
        String error_message = "Not navigated to " + footerlinkText + " page";
        switch (footerlinkText) {
            case "eSSENTIAL Accessibility":
                Wait.forPageReady();
                Assert.assertTrue(error_message, WebDriverManager.getWebDriver().getCurrentUrl().contains("accessibility"));
                break;
            case "macy's backstage":
                Wait.forPageReady();
                Assert.assertTrue(error_message, WebDriverManager.getWebDriver().getCurrentUrl().contains("macysbackstage"));
                break;
            default:
                Assert.fail("Invalid Footer link");
                break;
        }
        log.info("Successfully navigated for" + footerlinkText + "page");
    }

    @And("^I verify alt text of the \"([^\"]*)\" icon$")
    public void iVerifyAltTextOfTheIcon(String text) throws Throwable {
        Assert.assertTrue("Alt text of the " + text + " icon is not correct", Elements.findElement("footer.goto_essential_accessible").findElement(By.tagName("a")).getAttribute("title").contains("This Web Accessibility icon serves as a link to download eSSENTIAL Accessibility assistive technology software for individuals with physical disabilities."));
        log.info("Successfully verified alt text for " + text + " link");
    }

}
