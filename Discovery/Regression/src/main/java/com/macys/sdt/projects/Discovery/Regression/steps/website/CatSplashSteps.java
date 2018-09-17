package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategorySplash;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.FlexTemplatePanel;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YH03402 on 5/23/2017.
 */
public class CatSplashSteps extends StepUtils {
    CategorySplash splash = new CategorySplash();
    private static final Logger logger = LoggerFactory.getLogger(CatSplashSteps.class);

    @Then("^I verify that Sub Ads of \"([^\"]*)\" should be displayed on (?:cat splash|browse) page$")
    public void i_verify_that_Sub_Ads_displayed_on_page(String page) throws Throwable {
        FlexTemplatePanel ftPanel = new FlexTemplatePanel();
        HashMap catIconMedia = null;
        pausePageHangWatchDog();
        if(ftPanel.isMediaExistsOnPage("CATEGORY_ICON", "102")){
            catIconMedia = (HashMap)ftPanel.getMediaDetailsOnPage("CATEGORY_ICON", "102");
        }else if(ftPanel.isMediaExistsOnPage("CATEGORY_ICON", "103")){
            catIconMedia = (HashMap)ftPanel.getMediaDetailsOnPage("CATEGORY_ICON", "103");
        }else if(ftPanel.isMediaExistsOnPage("CATEGORY_ICON", "104")){
            catIconMedia = (HashMap)ftPanel.getMediaDetailsOnPage("CATEGORY_ICON", "104");
        }else if(ftPanel.isMediaExistsOnPage("CATEGORY_ICON", "105")){
            catIconMedia = (HashMap)ftPanel.getMediaDetailsOnPage("CATEGORY_ICON", "105");
        }else if(ftPanel.isMediaExistsOnPage("CATEGORY_ICON", "106")){
            catIconMedia = (HashMap)ftPanel.getMediaDetailsOnPage("CATEGORY_ICON", "106");
        }else{
            Assert.fail("ERROR - DATA: 'Sub Ads' media data not found on page url:"+WebDriverManager.getCurrentUrl());
        }
        List<String> linkUrls = (List)catIconMedia.get("linkUrls");
        linkUrls = linkUrls.stream().filter(f -> !f.equals("")).collect(Collectors.toList());
        List<String> imageSrcs = (List)catIconMedia.get("imageSrcs");
        Assert.assertFalse("ERROR - DATA: Sub Ads/Category Icons links are not available on category:"+page, linkUrls.isEmpty());
        Assert.assertFalse("ERROR - DATA: Sub Ads/Category Icons images are not available on category:"+page, imageSrcs.isEmpty());
        linkUrls.forEach(linkUrl -> {
            Assert.assertFalse("ERROR - DATA: Sub Ad link url is not available on page:"+page, linkUrl == null);
            logger.debug("Sub Ad Link URL:"+linkUrl);
            int responseCode = DSVPopularSearchesSteps.getResponseCode(linkUrl);
            Assert.assertTrue("ERROR - APP: Category Icon URL:"+linkUrl+" is returning response code:"+responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
        });
        imageSrcs.forEach(imageSrc -> {
            Assert.assertFalse("ERROR - DATA: Sub Ad Image source is not available on page:"+page, imageSrc == null);
            if(imageSrc.contains("asset")){
                logger.debug("Sub Ad Image src:"+imageSrc);
                int responseCode = DSVPopularSearchesSteps.getResponseCode(imageSrc);
                Assert.assertTrue("ERROR - APP: Category Icon URL:"+imageSrc+" is returning response code:"+responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
            }
        });
        resumePageHangWatchDog();
        logger.info("Verified sub ads on page:" + page);
    }

    @Then("^I should be navigated to \"([^\"]*)\" category page$")
    public void i_should_be_navigated_to_category_page(String category) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        String source=WebDriverManager.getCurrentUrl();
        logger.info(source);
        Assert.assertTrue("Not on category page:: "+category, source.contains(category));
        Assert.assertTrue("Got Invalid response code for category "+category , DsvActions.responseCode(source));
    }

    @Then("^I verify that Featured Categories of \"([^\"]*)\" should be displayed on (?:cat splash|browse) page$")
    public void iVerifyThatFeaturedCategoriesOfShouldBeDisplayedOnCatSplashPage(String page) throws Throwable {
        FlexTemplatePanel ftPanel = new FlexTemplatePanel();
        Assert.assertTrue("ERROR - DATA: TEXT media is not available to verify 'Featured Categories' on page"+page, ftPanel.isMediaExistsOnPage("TEXT", "101"));
        HashMap featuredCategoryMedia = (HashMap)ftPanel.getMediaDetailsOnPage("TEXT", "101");
        List<String> linkTexts = (List)featuredCategoryMedia.get("linkTexts");
        Assert.assertFalse("ERROR - DATA: Featured Category Link Text are not available on category:"+page, linkTexts.isEmpty());
        linkTexts.forEach(linkText -> {
            Assert.assertTrue("ERROR - APP: Featured Category text is not displaying on page:"+page, linkText != null && !linkText.equals(""));
        });
        i_verify_that_Sub_Ads_displayed_on_page(page);
        logger.info("Verified Featured Categories on page:"+page);
    }

    @Then("^I verify that Main Ads of \"([^\"]*)\" should be displayed on (?:cat splash|browse) page$")
    public void iVerifyThatMainAdsOfShouldBeDisplayedOnBrowsePage(String page) throws Throwable {
        FlexTemplatePanel ftPanel = new FlexTemplatePanel();
        HashMap mainAdMedia = null;
        pausePageHangWatchDog();
        if(ftPanel.isMediaExistsOnPage("AD", "0")){
            mainAdMedia = (HashMap)ftPanel.getMediaDetailsOnPage("AD", "0");
        }else if(ftPanel.isMediaExistsOnPage("IMAGE_MAP", "0")){
            mainAdMedia = (HashMap)ftPanel.getMediaDetailsOnPage("IMAGE_MAP", "0");
        }else if(ftPanel.isMediaExistsOnPage("AD", "101")){
            mainAdMedia = (HashMap)ftPanel.getMediaDetailsOnPage("AD", "101");
        }else if(ftPanel.isMediaExistsOnPage("IMAGE_MAP", "101")){
            mainAdMedia = (HashMap)ftPanel.getMediaDetailsOnPage("IMAGE_MAP", "101");
        }else {
            Assert.fail("ERROR - DATA: 'Main Ads' media data not found on page url:"+WebDriverManager.getCurrentUrl());
        }
        List<String> imageSrcs = (List)mainAdMedia.get("imageSrcs");
        Assert.assertFalse("ERROR - DATA: Main ads are not available on category:"+page, imageSrcs.isEmpty());
        imageSrcs.forEach(imageSrc -> {
            Assert.assertFalse("ERROR - DATA: Main Ad src location is not available on page:"+page, imageSrc == null);
            if (imageSrc.contains("asset")) {
                logger.debug("Main Ad Image src:"+imageSrc);
                int responseCode = DSVPopularSearchesSteps.getResponseCode(imageSrc);
                Assert.assertTrue("ERROR - APP: Main Ad src location URL:" + imageSrc + " is returning response code:" + responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
            }
        });
        resumePageHangWatchDog();
        logger.info("Verified Main Ads on page:"+page);
    }



    /**
     * validation for 200 status code on landing page for every categories
     * @author: Ganesh Kumar
     * @date:27-June-2017
     */

    @When("^I verify that following \"([^\"]*)\" Sub Ads are getting displayed on cat splash page$")
    public void i_verify_that_following_Sub_Ads_are_getting_displayed_on_cat_splash_page(String cat) throws Throwable {
        new Home().selectMainCategory(cat);
        Wait.forPageReady();
        List<WebElement> subAds = Elements.findElements("cat_splash.verify_subAdd_links_verify");
        if (!subAds.isEmpty()) {
            logger.info("Size is"+subAds.size());
            for (int i=0;i<subAds.size();i++) {
                String src = subAds.get(i).getAttribute("src");
                logger.info("SRC is ::" + src);
                int responseCode = RESTOperations.doGET(src, null).getStatus();
                Assert.assertEquals("Response code not 200", 200, responseCode);
            }
        } else {
            logger.info("SubAd not present on Pages::" + cat);
            Assert.assertFalse(true);
        }
    }

    @Then("^I verify main ad is displayed on \"([^\"]*)\" category page$")
    public void i_verify_main_ad_is_displayed_on_category_page(String cat) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        if (bloomingdales()) {


            if (Elements.elementPresent("preview_category.top_banner")) {
                logger.info("Found top banner in category::" + cat);
                logger.info("Checking top banner");
                String imgSourceTopBanner = Elements.getElementAttribute("preview_category.top_banner", "src");
                Assert.assertTrue("Top banner is navigating to unavailable Page ", DsvActions.responseCode(imgSourceTopBanner));

                logger.info("Checking Main ad now");
                Assert.assertTrue("Main ad is not present for category::" + cat,
                        Elements.findElement("preview_category.main_ad").isDisplayed());
                String imgSourceMainAd = Elements.getElementAttribute("preview_category.main_ad", "src");
                Assert.assertTrue("Main ad is navigating to unavailable Page", DsvActions.responseCode(imgSourceMainAd));


            } else {
                Assert.assertTrue("Main Ad is not present for category:", Elements.elementPresent("preview_category.main_ad"));
                String imgSourceMainAd = Elements.getElementAttribute("preview_category.main_ad", "src");
                Assert.assertTrue("Main Ad is navigating to unavailable Page ", DsvActions.responseCode(imgSourceMainAd));
                logger.info("Main Ad verified for cat::" + cat);

            }
        }

    }

    @Then("^I verify all gift guide links are not resulting error page$")
    public void iVerifyAllGiftGuideLinksAreNotResultingErrorPage() throws Throwable {
        List<String> all_Links = Elements.findElement(By.className("mainContainer")).findElements(By.tagName("a")).stream().map(f -> f.getAttribute("href")).collect(Collectors.toList());
        for (String url : all_Links) {
            int responseCode = DiscoveryHelper.getResponseCode(url);
            Assert.assertTrue("ERROR - APP: page link URL:" + url + " is returning response code:" + responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
        }
    }

    @Then("^I ensure to see the suppressed category message is displayed$")
    public void iEnsureToSeeTheSuppressedCategoryMessageIsDisplayed() throws Throwable {
        String expected_message = "The selected category cannot be shipped to\n" +
                "Australia.";
        Assert.assertTrue("Surppressed category message not displayed", Elements.getText("category_splash.surppressed_category_message").equalsIgnoreCase(expected_message));
    }
}
