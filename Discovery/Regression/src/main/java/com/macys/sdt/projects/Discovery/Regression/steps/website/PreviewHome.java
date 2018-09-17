package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import com.macys.sdt.projects.Discovery.Regression.actions.website.PreviewHomepage;
import com.macys.sdt.projects.Discovery.Regression.utils.ImageValidator;
import com.macys.sdt.projects.Discovery.Regression.utils.ReadPreviewExcelData;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.*;

/**
 * Created by yh03383 on 6/10/2017.
 */
public class PreviewHome extends StepUtils

{
    private static final Logger logger = LoggerFactory.getLogger(PreviewHome.class);
    private List<WebElement> popups;
    static String AdElement = null;

    @When("^I navigate to all category pages and I verify top banner is displayed on category page:$")
    public void i_navigate_to_all_category_pages_and_i_verify_top_banner_is_displayed_on_category_page(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        List<String> category = dataTable.asList(String.class);

        for (String cat : category) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);

            if (cat.equalsIgnoreCase("beauty")) {
                Assert.assertTrue("Top banner ad is not displayed for category: " + cat,
                        Elements.elementPresent("preview_category.top_banner_beauty"));
                Assert.assertTrue("Top banner image is not available for category:" + cat,
                        responseCode("preview_category.top_banner_beauty"));
            } else {
                Assert.assertTrue("Top banner is not displayed for category: " + cat,
                        Elements.elementPresent("preview_category.top_banner"));
                Assert.assertTrue("Top banner image is not available for category:" + cat,
                        responseCode("preview_category.top_banner"));
            }

        }
    }

    @When("^I navigate to all category pages and I verify main ad is displayed on category page:$")
    public void i_navigate_to_all_category_pages_and_i_verify_main_ad_is_displayed_on_category_page(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        List<String> categories = dataTable.asList(String.class);

        for (String cat : categories) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);

            //Beauty category UI is different from others category pages
            if (cat.equalsIgnoreCase("beauty")) {
                Assert.assertTrue("Main ad is not displayed for category: " + cat,
                        Elements.elementPresent("preview_category.main_ad_beauty"));
                Assert.assertTrue("Main ad is not available for category:" + cat,
                        responseCode("preview_category.main_ad_beauty"));
            } else {
                if (!(Elements.elementPresent("preview_category.main_ad"))) {
                    Assert.assertTrue("Main ad is not displayed for category: " + cat,
                            Integer.parseInt(Elements.getElementAttribute("preview_category.top_banner_main_ad", "height")) > 90);
                    Assert.assertTrue("Main ad is not available for category:" + cat,
                            responseCode("preview_category.top_banner_main_ad"));
                } else {
                    Assert.assertTrue("Main ad is not displayed for category: " + cat,
                            Integer.parseInt(Elements.getElementAttribute("preview_category.main_ad", "height")) > 90);
                    Assert.assertTrue("Main ad is not available for category:" + cat,
                            responseCode("preview_category.main_ad"));
                }
            }
        }
    }

    @Then("^I verify top banner is displayed on \"([^\"]*)\" page$")
    public void i_verify_top_banner_is_displayed_on_page(String category) throws Throwable {
        if (category.equalsIgnoreCase("beauty")) {
            Assert.assertTrue("Top banner ad is not displayed for category: " + category,
                    Elements.elementPresent("preview_category.top_banner_beauty"));
            Assert.assertTrue("Top banner image is not available for category:" + category,
                    responseCode("preview_category.top_banner_beauty"));
        } else {
            Assert.assertTrue("Top banner is not displayed for category: " + category,
                    Elements.elementPresent("preview_category.top_banner"));
            Assert.assertTrue("Top banner image is not available for category:" + category,
                    responseCode("preview_category.top_banner"));
        }

    }

    @Then("^I verify main ad is displayed on \"([^\"]*)\" page$")
    public void i_verify_main_ad_is_displayed_on_page(String category) throws Throwable {

        if (category.equalsIgnoreCase("beauty")) {
            Assert.assertTrue("Main ad is not displayed for category: " + category,
                    Elements.elementPresent("preview_category.main_ad_beauty"));
            Assert.assertTrue("Main ad is not available for category:" + category,
                    responseCode("preview_category.main_ad_beauty"));
        } else {
            if (!(Elements.elementPresent("preview_category.main_ad"))) {
                Assert.assertTrue("Main ad is not displayed for category: " + category,
                        Integer.parseInt(Elements.getElementAttribute("preview_category.top_banner_main_ad", "height")) > 90);
                Assert.assertTrue("Main ad is not available for category:" + category,
                        responseCode("preview_category.top_banner_main_ad"));
            } else {
                Assert.assertTrue("Main ad is not displayed for category: " + category,
                        Integer.parseInt(Elements.getElementAttribute("preview_category.main_ad", "height")) > 90);
                Assert.assertTrue("Main ad is not available for category:" + category,
                        responseCode("preview_category.main_ad"));
            }
        }
    }


    @When("^I navigate to all category pages and I verify top banner is displayed on registry category page:$")
    public void i_navigate_to_all_category_pages_and_I_verify_top_banner_is_displayed_on_registry_category_page(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        List<String> categories = dataTable.asList(String.class);
        for (String cat : categories) {
            logger.info("Selecting category: " + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);

            Assert.assertTrue("Top banner is not displayed for category:" + cat,
                    Integer.parseInt(Elements.getElementAttribute("preview_category.top_banner_registry", "height")) < 125);
            Assert.assertTrue("Top banner image is not available for category:" + cat,
                    responseCode("preview_category.top_banner_registry"));
        }
    }

    @When("^I navigate to all category pages and I verify main ad is displayed on registry category page:$")
    public void i_navigate_to_all_category_pages_and_I_verify_main_ad_is_displayed_on_registry_category_page(DataTable dataTable) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        List<String> categories = dataTable.asList(String.class);
        for (String cat : categories) {
            logger.info("Selecting category: " + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);
            Assert.assertTrue("Top banner is not displayed for category:" + cat, Integer.parseInt(Elements.getElementAttribute("preview_category.main_ad_registry", "height")) > 190);
            //responseCode("preview_category.main_ad_registry");
            Assert.assertTrue("Top banner image is not available for category:" + cat,
                    responseCode("preview_category.main_ad_registry"));
        }
    }

    private boolean responseCode(String selectorImg) {

        String src = Elements.getElementAttribute(selectorImg, "src");
        logger.info("SRC is ::" + src);
        int responseCode = RESTOperations.doGET(src, null).getStatus();
        if (responseCode != 200) {
            logger.info("Got Invalid Response Code:" + responseCode);
            return false;
        }
        logger.info("Got Valid responseCode: " + responseCode);
        return true;
    }

    @Then("^I should see new header and footer section in \"([^\"]*)\" mode$")
    public void i_should_see_new_header_and_footer_section_in_mode(String mode) throws Throwable {


        // Write code here that turns the phrase above into concrete actions
        if (!mode.equalsIgnoreCase("iship")) {
            Elements.elementShouldBePresent("header.my_account_container");
        }
        Elements.elementShouldBePresent("header.search_menu_container");
        Elements.elementShouldBePresent("header.category_menu_container");
        Elements.elementShouldBePresent("footer.global_footer");

    }

    @And("^I verify the footer is displayed$")
    public void i_verify_the_footer_is_displayed() throws Throwable {

        if (bloomingdales()) {
            Assert.assertTrue("footer section is not displayed : ",
                    Elements.elementPresent("footer.footer_menu_section"));
            // Write code here that turns the phrase above into concrete actions
        }
    }

    @Then("^I verify exclusion & details popup in main ad$")
    public void i_verify_exclusion_details_popup_in_main_ad() throws Throwable {
        List<WebElement> mainad_popups = Elements.findElements("preview_home.mainad_popup_links");
        if (mainad_popups.isEmpty()) {
            Assert.fail("No popup found in main ad");
        } else {
            logger.info("Number of popup found in main ads::" + mainad_popups.size());
        }
        for (WebElement popup : mainad_popups) {
            Clicks.click(popup);


            Navigate.switchWindow(1);
            Wait.forPageReady();
            Thread.sleep(20000);
            Assert.assertTrue("Macy's logo is not visible in popup",
                    Elements.elementPresent("preview_home.popup_logo"));
            logger.info("Macys logo is present in popup");

            Assert.assertTrue("Popup close button is not clickable",
                    Elements.elementPresent("preview_home.popup_close"));
            logger.info("Popup close button is present in popup");

            Assert.assertTrue("Popup is blank",
                    Elements.elementPresent("preview_home.popup_content") || WebDriverManager.getWebDriver().getCurrentUrl().equalsIgnoreCase(""));
            logger.info("Popup is not blank");

            Assert.assertTrue("Popup header/perecentage off detail is blank",
                    Elements.elementPresent("preview_home.popup_header"));
            logger.info("Popup header details are displaying");

            Assert.assertTrue("Popup sub header/promo detail is blank",
                    Elements.elementPresent("preview_home.popup_subheader"));
            logger.info(" Popup sub-header details are displaying");

            Assert.assertTrue("Popup disclaimer section is blank",
                    Elements.elementPresent("preview_home.popup_disclaimer"));
            logger.info("Popup disclaimer details are displaying");
            logger.info("Basic attributes of popup are verified successfully...!!!!");
            Navigate.switchWindowClose();
        }
    }

    @Then("^I verify GNA link is displayed on homepage and contains \"([^\"]*)\" text$")
    public void i_verify_GNA_links_are_displayed_on_homepage_and_contains_text(String text) throws Throwable {
        if (text.equalsIgnoreCase("Free Shipping")) {
            Assert.assertTrue("GNA free shipping banner is not displayed",
                    Elements.findElement("home.free_shipping_banner").isDisplayed());
            Assert.assertTrue("Free shipping text is missing from GNA",
                    Elements.findElement("home.free_shipping_text").getText().contains(text));
        } else if (text.equalsIgnoreCase("The Edit")) {
            Assert.assertTrue("GNA The Edit banner is not displayed",
                    Elements.findElement("home.Macys_present_text").isDisplayed());
            logger.info("text " + Elements.findElement("home.Macys_present_text").getText());
            Assert.assertTrue("The Edit text is missing from GNA",
                    Elements.findElement("home.Macys_present_text").getText().contains(text));
        }
    }

    @When("^I click on free shipping exclusion link$")
    public void i_click_on_free_shipping_exclusion_link() throws Throwable {
        try {
            Clicks.click("home.free_shipping_exclusion");
            Wait.forPageReady();
        } catch (Exception e) {
            Assert.fail("unable to click free shipping exclusion link " + e.getMessage());
        }
    }

    @Then("^I should be redirected to free ship popup$")
    public void i_should_be_redirected_to_free_ship_popup() throws Throwable {
        Navigate.switchWindow(1);
        Wait.forPageReady();
        shouldBeOnPage("free_shipping_exclusion");
        Assert.assertTrue("Not navigated to free shipping details window",
                WebDriverManager.getCurrentTitle().contains("Free Shipping & Free Returns"));
        DsvActions.responseCode(WebDriverManager.getCurrentUrl());
    }

    @When("^I click on check it out link$")
    public void i_click_on_check_it_out_link() throws Throwable {
        try {
            Clicks.click("home.Macys_present_check_it_out");
            Wait.forPageReady();
        } catch (Exception e) {
            Assert.fail("unable to click free shipping exclusion link " + e.getMessage());
        }
    }

    @Then("^I should be redirected to Macys The Edit page$")
    public void i_should_be_redirected_to_Macys_The_Edit_page() throws Throwable {
        Wait.forPageReady();
        shouldBeOnPage("the_edit");
        Assert.assertTrue("Not navigated to macys edit page",
                WebDriverManager.getCurrentTitle().contains("The Edit"));
        DsvActions.responseCode(WebDriverManager.getCurrentUrl());

    }

    @Then("^I verify all popup on \"([^\"]*)\" page opens without any errors$")
    public void i_verify_all_popup_on_page_opens_without_any_errors(String page) throws Throwable {
        List<WebElement> popups = Elements.findElements("popup_details.popup_links");
        String popup_url = null;
        String error_message = null;
        int popup_number = 1;
        if (popups.isEmpty())
            logger.info("No popup of found on page:: " + page);
        else {
            logger.info("Total number of popups found on " + page + "::" + popups.size());
            for (WebElement popup : popups) {
                try {
                    Clicks.javascriptClick(popup);
                    Navigate.switchWindow(1);
                    Wait.forPageReady();
                    Wait.secondsUntilElementPresent("popup_details.content", 10);
                    popup_url = WebDriverManager.getCurrentUrl();
                    if (popup_url.contains("popup")) {
                        Wait.secondsUntilElementPresent("popup_details.content", 10);
                        logger.info("inside 'offer details' popup");
                        logger.info("Popup URL::" + popup_url);
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number:: " + popup_number + " on " + page + "popup url::" + popup_url + "\n";
                            logger.info("Got Invalid Response code.");
                        }
                        logger.info("Response code verified sucessfully..!!");
                        if (!Elements.elementPresent("popup_details.content")) {
                            error_message += "\n" + "Content is missing for popup number " + popup_number +
                                    " on page " + page + ".Popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Popup verified successfully. Popup is not blank..!!");
                        Navigate.switchWindowClose();
                        popup_number++;
                    } else if (popup_url.contains("buy-online-pickup-in-store")) {
                        logger.info("inside 'buy online pickup in store' popup");
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number " + popup_number + "popup on " + page + "popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Popup verified successfully. Popup is not blank..!!");
                        shouldBeOnPage("buy_online_pickup_in_store");
                        logger.info("Popup verified successfully. Popup is not blank..!!");
                        Navigate.switchWindowClose();
                        popup_number++;
                    } else if (popup_url.contains("free-shipping")) {
                        logger.info("inside 'free shipping' popup");
                        logger.info("Checking response code only ");
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number " + popup_number + "popup on " + page + "popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Popup verified successfully. Popup is not blank..!!");
                        shouldBeOnPage("free_shipping_exclusion");
                        logger.info("Popup verified successfully. Popup is not blank..!!");
                        Navigate.switchWindowClose();
                        popup_number++;
                    } else if (popup_url.contains("/international-shipping/") || popup_url.contains("/international/context")) {
                        logger.info("inside 'international shipping' popup");
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number " + popup_number + "popup on " + page + "popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Popup verified successfully. Popup is not blank..!!");
                        logger.info("Response code checked..!!");
                        shouldBeOnPage("international_shipping");
                        logger.info("Popup verified successfully. Popup is not blank..!!");
                        Navigate.switchWindowClose();
                        popup_number++;
                    } else if (popup_url.contains("/about-us/")) {
                        logger.info("inside 'Macys INC-about us' popup");
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number " + popup_number + "popup on " + page + "popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Popup verified successfully. Popup is not blank..!!");
                        logger.info("Response code checked..!!");
                        shouldBeOnPage("macys_inc");
                        logger.info("Popup verified successfully. Popup is not blank..!!");
                        Navigate.switchWindowClose();
                        popup_number++;
                    } else if (popup_url.contains("itunes.apple.com") && bloomingdales()) {
                        logger.info("inside 'big brown bag app' popup");
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number " + popup_number + "popup on " + page + "popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Response code checked..!!");
                        Navigate.switchWindowClose();
                        popup_number++;
                    } else if (popup_url.contains("assets.bloomingdales.com") || popup_url.contains(".pdf") && bloomingdales()) {
                        logger.info("inside 'assets details pdf' popup");
                        logger.info("checking response code only");
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number " + popup_number + " popup on " + page + ".Popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Response code checked..!!");
                        Navigate.switchWindowClose();
                        popup_number++;
                    } else if (popup_url.contains("cheetahmail") && bloomingdales()) {
                        logger.info("inside 'sign up for bloomingdale's text alerts' popup");
                        //logger.info("checking response code only");
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number " + popup_number + " popup on " + page + ".Popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Response code checked..!!");

                        if (!Elements.elementPresent("popup_details.signup_text")) {
                            error_message += "\n" + "Content is missing for popup number " + popup_number +
                                    " on page " + page + ".Popup url::" + popup_url + "\n";
                            logger.info("Content validation failed for popup::" + popup_url);
                        } else
                            logger.info("Popup verified successfully. Popup is not blank..!!");
                        Navigate.switchWindowClose();
                        popup_number++;
                    } else {
                        logger.info("New type of popup opened");
                        if (!DsvActions.responseCode(WebDriverManager.getCurrentUrl())) {
                            error_message += "\n" + "Invalid response code for popup number " + popup_number + "popup on " + page + " .Popup url::" + popup_url + "\n";
                        } else
                            logger.info("Response code verified");
                        error_message += "\n" + "Popup number: " + popup_number + " on " + page + " is new type of popup. URL::" + popup_url + "\n";
                        Navigate.switchWindowClose();
                        popup_number++;
                    }

                } catch (Exception e) {
                    logger.info("validation failed for popup::" + popup_url + "Exception:" + e.getMessage());
                    error_message += "Popup url::" + popup_url + "Error" + e.getMessage() + "\n";
                    Navigate.switchWindowClose();
                    popup_number++;
                }
            }
            Assert.assertTrue("Popup validation failed for popup::" + error_message, error_message == null || error_message.isEmpty());
        }
    }


    @Then("^I verify \"([^\"]*)\" is displayed on Home Page$")
    public void i_verify_main_ad_is_displayed_on_Home_Page(String adType) throws Throwable {
        logger.info("Verify '" + adType + "' is preset on page");
        String sheetname = ReadPreviewExcelData.getFileNameWithDate();
        String image_name = PreviewHomepage.getImageNamefromExcel(adType, sheetname);
        if (!(StringUtils.containsIgnoreCase(image_name, ".png") || StringUtils.containsIgnoreCase(image_name, ".jpg")
                || StringUtils.containsIgnoreCase(image_name, ".gif"))) {
            logger.info("Image not provided in Excel::" + image_name);
            Assert.fail("Image not provided in Excel::" + image_name);
        }
        logger.info("Image name from Sheet+" + sheetname + "::" + image_name);
        List<WebElement> we = Elements.findElements("preview_home.image");
        logger.info("No. of elements::" + we.size());
        for (WebElement ele : we) {
            try {
                //logger.info("xpath" + ele);
                //logger.info(ele.getAttribute(adType.equalsIgnoreCase("category") ? "data-feo-orig-src" : "src"));
                String actual_src;
                //logger.info("SRC" + ele.getAttribute("src"));
                //logger.info("Data-feo_orig_src" + ele.getAttribute("data-feo-orig-src"));
                actual_src = ele.getAttribute("data-feo-orig-src") != null & ele.getAttribute("src") == null
                        ? ele.getAttribute("data-feo-orig-src") : ele.getAttribute("src");

                if (actual_src.contains(image_name)) {
                    logger.info("image name matched");
                    logger.info(ele.getAttribute("usemap"));
                    if (adType.equalsIgnoreCase("category")) {
                        logger.info("Inside CAtegory usemap value::" + ele.getAttribute("usemap"));
                        AdElement = "//div[contains(@id,'row_')]//img[@usemap='" + ele.getAttribute("usemap") + "']";
                        break;
                    } else {
                        AdElement = "//*[@id='row1_column1']//div/img[@usemap='" + ele.getAttribute("usemap") + "']";
                        break;
                    }
                }
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        logger.info(AdElement);
        Assert.assertFalse("ERROR: No element found with the image Name", AdElement == null);
        Assert.assertTrue("Ad not present", Elements.elementPresent(By.xpath(AdElement)));//"preview_home.main_ad"  Elements.elementPresent(By.xpath(xpathforImage)
    }

    @Then("^I verify the \"([^\"]*)\" image links with the sheet$")
    public void i_verify_main_ad_image_link_with_Hp_Doc(String adtype) throws Throwable {
        pausePageHangWatchDog();
        int error = 0;
        StringBuilder errorMessage = new StringBuilder("");
        /*fetching link data from WebSite - Main Ad*/
        HashMap<String, String> uiLinkMap = new HashMap<>();
        HashMap<String, String> sheetLinkMap = new HashMap<>();
        String sheetName = ReadPreviewExcelData.getFileNameWithDate();
        logger.info(sheetName);
        logger.info("NewXpath::" + AdElement + "/following-sibling::map/area[not(contains(@href,'javascript:pop'))]");
        List<WebElement> uiLinks = Elements.findElements(By.xpath(AdElement + "/following-sibling::map/area[not(contains(@href,'javascript:pop'))]"));//        List<WebElement> uiLinks = Elements.findElements();//

        for (WebElement ele : uiLinks) {
            logger.info("Alt tag is::" + ele.getAttribute("Alt").trim().toLowerCase());
            logger.info("Alt tag is::" + ele.getAttribute("href").trim().toLowerCase());
            uiLinkMap.put(ele.getAttribute("Alt"), ele.getAttribute("href"));
        }
        logger.info("Data from UI link::" + uiLinkMap);

       /*fetching data from Excel for Main add and processing it*/
        logger.info(sheetName);
        sheetLinkMap = PreviewHomepage.getPreviewLinksfromExcel(adtype, sheetName);

        logger.info(sheetLinkMap.toString());
        Assert.assertEquals("Count of links is not equal to Excel", uiLinkMap.size(), sheetLinkMap.size());

        for (Map.Entry<String, String> cat : sheetLinkMap.entrySet()) {
            logger.info("sheet data:: key::" + cat.getKey() + " value::" + cat.getValue());
            logger.info("Web data:: key::" + cat.getKey() + " value::" + uiLinkMap.get(cat.getKey().toString().trim().toLowerCase()));

            try {
                Assert.assertFalse("Link is Unavailable in UI for::" + cat.getKey() + " as on HP Doc", uiLinkMap.get(cat.getKey().toString().trim().toLowerCase()) == null || uiLinkMap.get(cat.getKey().toString().trim().toLowerCase()) == "");
                Assert.assertTrue("Category link for::" + cat.getKey() + " is not on Main ad", uiLinkMap.get(cat.getKey().toString().trim().toLowerCase()).contains("ID=" + cat.getValue())
                        || uiLinkMap.get(cat.getKey().toString().trim().toLowerCase()).contains("id=" + cat.getValue()) ||
                        uiLinkMap.get(cat.getKey().toString().trim().toLowerCase()).equalsIgnoreCase(cat.getValue()));
                Assert.assertTrue("Respose code not equal to 200", DsvActions.responseCode(uiLinkMap.get(cat.getKey().toString().trim().toLowerCase())));
            } catch (AssertionError ae) {
                error++;
                errorMessage.append(ae.getMessage());
                //  ae.printStackTrace();
            }
        }
        Assert.assertTrue("Test Failed ::" + errorMessage, error == 0);
        resumePageHangWatchDog();
    }

    // Verifies Popups exists on Homepage

    @Then("^I verify exclusion & details links for \"([^\"]*)\"$")
    public void i_verify_exclusion_details_links_for_banner(String fieldType) throws Throwable {
        popups = Elements.findElements(By.xpath(AdElement + "/following-sibling::map/area[contains(@href,'javascript:pop')]"));
        if (popups.isEmpty()) {
            Assert.fail("No popup found on Homepage");
        } else {
            logger.info("number of popups found on Homepage:: " + popups.size());
        }
        //Get no. of pop in excel
        HashMap<String, HashMap<String, String>> data = ReadPreviewExcelData.getPreviewExcelData(ReadPreviewExcelData.getFileNameWithDate());
        int excelcount = 0;
        Iterator<Map.Entry<String, HashMap<String, String>>> it;
        it = data.entrySet().iterator();
        //String fieldType = "Pop Up";
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, String>> rowData = it.next();
            //   logger.info(data.getKey() + "::" + data.getValue());
            if (rowData.getKey().contains(fieldType)) {//Main Ad
                excelcount++;
            }
        }
        logger.info("Pop up links in Excel" + excelcount);
        Assert.assertEquals("Popup links on Web and Excel did not match for " + fieldType, excelcount, popups.size());
    }

    /*Verifies popup details
    Take arguments as datatable from Feature for various attributes of popup
    Popup ID, Macys logo, Promo code name, offer details & promotion end date
    Matches the content on website with the content coming from excel sheet/HP doc*/
    @Then("^I verify \"([^\"]*)\" details as in below table:$")
    public void i_verify_popup_details_as_in_below_table(String fieldType, DataTable dataTable) throws Throwable {
        HashMap<String, HashMap<String, String>> data = ReadPreviewExcelData.getPreviewExcelData(ReadPreviewExcelData.getFileNameWithDate());
        Iterator<Map.Entry<String, HashMap<String, String>>> it;
        //String fieldType = "Pop Up";
        it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, String>> rowData = it.next();
            //logger.info(rowData.getKey() + "::" + rowData.getValue());
            if (rowData.getKey().contains(fieldType)) {//Main Ad
                HashMap<String, String> keyValues = rowData.getValue();
                logger.info(keyValues.toString());
                String popUpId = "popupID=" + keyValues.get("Links");
                List<WebElement> popUpLinks = Elements.findElements(By.xpath(AdElement + "/following-sibling::map/area[contains(@href,'javascript:pop')]"));//        List<WebElement> uiLinks = Elements.findElements();//
                logger.info("popup size::" + popUpLinks.size());
                String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
                for (WebElement ele : popUpLinks) {
                    try {
                        if (ele.getAttribute("href").contains(popUpId)) {
                            pausePageHangWatchDog();
                            if (!(fieldType.contains("Category"))) {
                                ele.click();
                            } else {
                                Clicks.javascriptClick(ele);
                            }
                            resumePageHangWatchDog();
                        } else
                            Assert.fail("Pop Up id ::" + popUpId + " is not available");
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                        Assert.fail("Pop Up is not clickable" + npe.getMessage());
                    }
                }

                String details = keyValues.get("Details");
                String popupId = keyValues.get("Links");
                logger.info("popup id::" + popupId);
                String promoCodeName = details.split(";")[0].trim();
                String offerDetails = details.split(";")[1].trim();
                String endDate = details.split(";")[2].trim();
                logger.info("details::" + details);
                logger.info("promo::" + promoCodeName);
                logger.info("offer details::" + offerDetails);
                logger.info("end date::" + endDate);
                Wait.forPageReady();
                Thread.sleep(4000);
                Set<String> windowHandles = WebDriverManager.getWebDriver().getWindowHandles();
                for (String windowHandle : windowHandles) {
                    if (!windowHandle.equals(parentWindow)) {
                        WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to popup window
                        List<String> attributes = dataTable.asList(String.class);
                        for (String attribute : attributes) {
                            if (attribute.equalsIgnoreCase("blank popup")) {
                                Wait.secondsUntilElementPresent("preview_home.popup_logo", 40);
                                logger.info(Elements.getText("preview_home.popup_content"));
                                Assert.assertFalse("Exclusion $ details link is navigating to blank Pop up",
                                        Elements.getText("preview_home.popup_content").isEmpty());

                            } else if (attribute.equalsIgnoreCase("Popup ID")) {

                                //Data check from HP doc
                                Assert.assertTrue("Popup is navigating to wrong ID as per HP doc",
                                        WebDriverManager.getCurrentUrl().contains(popupId));
                                logger.info("Popup redirection verified successfully..!!");

                            } else if (attribute.equalsIgnoreCase("macys logo")) {
                                Assert.assertTrue("Macy's logo is not visible in popup",
                                        Elements.elementPresent("preview_home.popup_logo"));
                                logger.info("Macys logo is present in popup");

                            } else if (attribute.equalsIgnoreCase("offer details")) {
                                logger.info("URL - " + WebDriverManager.getWebDriver().getCurrentUrl());
                                Assert.assertTrue("Popup perecentage off detail is blank",
                                        Elements.elementPresent("preview_home.popup_header"));
                                logger.info("Popup header details are displaying");
                                //Data check from HP doc
                                Assert.assertTrue("Offer details are wrong in popup",
                                        Elements.getText("preview_home.popup_header").contains(offerDetails));
                                logger.info("Offer details verified successfully..!!!");

                            } else if (attribute.equalsIgnoreCase("promo code name")) {
                                Assert.assertTrue("Popup promo detail is blank",
                                        Elements.elementPresent("preview_home.popup_subheader"));
                                logger.info(" Popup sub-header details are displaying");
                                //Data check from HP doc
                                Assert.assertTrue("Promo Code name is wrong in popup",
                                        Elements.getText("preview_home.popup_subheader").contains(promoCodeName));
                                logger.info("Promo Code name verified successfully..!!!");


                            } else if (attribute.equalsIgnoreCase("end date")) {
                                Assert.assertTrue("Popup disclaimer section is blank",
                                        Elements.elementPresent("preview_home.popup_disclaimer"));
                                logger.info("Popup disclaimer details are displaying");
                                //Data check from HP doc
                                Assert.assertTrue("Promo end date is wrong in popup",
                                        Elements.getText("preview_home.popup_disclaimer").contains(endDate));
                                logger.info("Promo end date verified successfully..!!!");

                            } else if (attribute.equalsIgnoreCase("close button")) {
                                Assert.assertTrue("Popup close button is not clickable",
                                        Elements.elementPresent("preview_home.popup_close"));
                                logger.info("Popup close button is present in popup");
                            }
                        }
                        WebDriverManager.getWebDriver().close();//closing popup window
                        WebDriverManager.getWebDriver().switchTo().window(parentWindow); //cntrl to parent window


                    }
                    Wait.forPageReady();
                }


            }
        }
    }


    @Then("^I verify the main-ad linked button are clickable$")
    public void i_verify_the_main_ad_linked_button_are_clickable() throws Throwable {
        List<WebElement> uiLinks = Elements.findElements(By.xpath(AdElement + "/following-sibling::map/area"));//        List<WebElement> uiLinks = Elements.findElements();//

        for (WebElement ele : uiLinks) {
            logger.info("Alt tag is::" + ele.getAttribute("Alt").trim().toLowerCase());
            String coord = ele.getAttribute("coords").trim().toLowerCase();
            logger.info("Coordinates tag is::" + coord);
            logger.info("Verify Coordinates");
            PreviewHomepage.iscooridatesValid(coord);
        }
    }

    @Then("^I verify the \"([^\"]*)\" image with the saved images$")
    public void i_verify_the_main_ad_image_with_the_saved_images(String fieldType) throws Throwable {
        String image_name = PreviewHomepage.getImageNamefromExcel(fieldType, ReadPreviewExcelData.getFileNameWithDate());
        String src = Elements.findElement(By.xpath(AdElement)).getAttribute("src");
        logger.info("SRC of Main Ad::" + src);
        WebDriverManager.getWebDriver().navigate().to(src);
        ImageValidator.verifyWebImagesValues(src, ReadPreviewExcelData.getPreivewDatafilePath("repo image", image_name));
    }

    @Then("^I verify the main-ad image with \"([^\"]*)\" images$")
    public void i_verify_the_main_with_images(String imageName) throws Throwable {
        String src = Elements.findElement(By.xpath(AdElement)).getAttribute("src");
        logger.info("SRC of Main Ad::" + src);
        logger.info("Test image name::" + imageName);
        WebDriverManager.getWebDriver().navigate().to(src);
        ImageValidator.verifyWebImagesValues(src, ReadPreviewExcelData.getPreivewDatafilePath("repo image", imageName));
    }

    @Given("^I verify popup details on category page top banner as in below table:$")
    public void i_verify_popup_details_on_category_page_top_banner_as_in_below_table(DataTable dataTable) throws Throwable {
        HashMap<String, HashMap<String, String>> data = ReadPreviewExcelData.getPreviewExcelData(ReadPreviewExcelData.getFileNameWithDate());
        Iterator<Map.Entry<String, HashMap<String, String>>> it;
        String fieldType = "Category-Pop Up";
        it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, String>> rowData = it.next();
            //   logger.info(data.getKey() + "::" + data.getValue());
            if (rowData.getKey().contains(fieldType)) {//Main Ad
                HashMap<String, String> keyValues = rowData.getValue();
                logger.info(keyValues.toString());
                String popUpId = "popupID=" + keyValues.get("Links");
                List<WebElement> popUpLinks = Elements.findElements(By.xpath(AdElement + "/preceding-sibling::map/area[contains(@href,'javascript:pop')]"));//        List<WebElement> uiLinks = Elements.findElements();//
                logger.info("popup size" + popUpLinks.size());
                String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
                for (WebElement ele : popUpLinks) {
                    try {
                        if (ele.getAttribute("href").contains(popUpId)) {
                            ele.click();
                        } else
                            Assert.fail("Pop Up id ::" + popUpId + " is not available");
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                        Assert.fail("Pop Up is not clickable" + npe.getMessage());
                    }
                }

                String details = keyValues.get("Details");
                String popupId = keyValues.get("Links");
                logger.info("popup id::" + popupId);
                String promoCodeName = details.split(";")[0].trim();
                String offerDetails = details.split(";")[1].trim();
                String endDate = details.split(";")[2].trim();
                logger.info("details::" + details);
                logger.info("promo::" + promoCodeName);
                logger.info("offer details::" + offerDetails);
                logger.info("end date::" + endDate);
                Wait.forPageReady();
                Thread.sleep(4000);
                Set<String> windowHandles = WebDriverManager.getWebDriver().getWindowHandles();
                for (String windowHandle : windowHandles) {
                    if (!windowHandle.equals(parentWindow)) {
                        WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to popup window
                        List<String> attributes = dataTable.asList(String.class);
                        for (String attribute : attributes) {
                            if (attribute.equalsIgnoreCase("blank popup")) {
                                Wait.secondsUntilElementPresent("preview_home.popup_logo", 40);
                                logger.info(Elements.getText("preview_home.popup_content"));
                                Assert.assertFalse("Exclusion $ details link is navigating to blank Pop up",
                                        Elements.getText("preview_home.popup_content").isEmpty());

                            } else if (attribute.equalsIgnoreCase("Popup ID")) {

                                //Data check from HP doc
                                Assert.assertTrue("Popup is navigating to wrong ID as per HP doc",
                                        WebDriverManager.getCurrentUrl().contains(popupId));
                                logger.info("Popup redirection verified successfully..!!");

                            } else if (attribute.equalsIgnoreCase("macys logo")) {
                                Assert.assertTrue("Macy's logo is not visible in popup",
                                        Elements.elementPresent("preview_home.popup_logo"));
                                logger.info("Macys logo is present in popup");

                            } else if (attribute.equalsIgnoreCase("offer details")) {
                                logger.info("URL - " + WebDriverManager.getWebDriver().getCurrentUrl());
                                Assert.assertTrue("Popup perecentage off detail is blank",
                                        Elements.elementPresent("preview_home.popup_header"));
                                logger.info("Popup header details are displaying");
                                //Data check from HP doc
                                Assert.assertTrue("Offer details are wrong in popup",
                                        Elements.getText("preview_home.popup_header").contains(offerDetails));
                                logger.info("Offer details verified successfully..!!!");

                            } else if (attribute.equalsIgnoreCase("promo code name")) {
                                Assert.assertTrue("Popup promo detail is blank",
                                        Elements.elementPresent("preview_home.popup_subheader"));
                                logger.info(" Popup sub-header details are displaying");
                                //Data check from HP doc
                                Assert.assertTrue("Promo Code name is wrong in popup",
                                        Elements.getText("preview_home.popup_subheader").contains(promoCodeName));
                                logger.info("Promo Code name verified successfully..!!!");


                            } else if (attribute.equalsIgnoreCase("end date")) {
                                Assert.assertTrue("Popup disclaimer section is blank",
                                        Elements.elementPresent("preview_home.popup_disclaimer"));
                                logger.info("Popup disclaimer details are displaying");
                                //Data check from HP doc
                                Assert.assertTrue("Promo end date is wrong in popup",
                                        Elements.getText("preview_home.popup_disclaimer").contains(endDate));
                                logger.info("Promo end date verifies successfully..!!!");

                            } else if (attribute.equalsIgnoreCase("close button")) {
                                Assert.assertTrue("Popup close button is not clickable",
                                        Elements.elementPresent("preview_home.popup_close"));
                                logger.info("Popup close button is present in popup");
                            }
                        }
                        WebDriverManager.getWebDriver().close();//closing popup window
                        WebDriverManager.getWebDriver().switchTo().window(parentWindow); //cntrl to parent window


                    }
                    Wait.forPageReady();
                }


            } else
                Assert.fail("Current" + fieldType + "Not present in Excel");
        }
    }


}
