package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategoryBrowse;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.ProductThumbnail;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.projects.Discovery.Regression.actions.website.FacetSelections.randomFacetValueSelection;
import static com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet.facetAvailable;

public class BrowseANDshop extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ShopAndBrowse.class);
    public static String brandSearch;
    public static String promotionalProdId;
    public static String promotionCode;
    public static Product recentProduct;
    public static String promoCode;
    public static String selectedProductName = null;
    public static String productId = "";
    private String selectedProdId;
    private CategoryBrowse categoryBrowse = new CategoryBrowse();
    private PreviewCategory previewCategory = new PreviewCategory();

    /**
     * Changes active country to given country
     *
     * @param /country full name of country as it is in the country selection drop down
     * @throws Throwable if any exception occurs
     */
   /* @When("^I change country to \"([^\"]*)\"$")
    public void I_change_the_country_to(String country) throws Throwable {
        boolean newDropDownEnabled;
        pausePageHangWatchDog();
        if (Wait.secondsUntilElementPresent("ishipping.country", (safari() ? 20 : 5))) {
            Assert.assertTrue("Not on international context page.", Wait.secondsUntilElementPresent("ishipping.country", (chrome() ? 20 : 5)));
            newDropDownEnabled = false;
        } else {
            Assert.assertTrue("Not on international context page.", Wait.secondsUntilElementPresent("ishipping.country_options", (chrome() ? 20 : 5)));
            newDropDownEnabled = true;
        }
        List<String> values;
        Random random = new Random();
        if (bloomingdales() && newDropDownEnabled) {
            values = DropDowns.getAllCustomValues("ishipping.country_options", "ishipping.country_list");
        } else {
            values = (edge() ? DropDowns.getAllValuesUsingJavaScript("ishipping.country") : DropDowns.getAllValues("ishipping.country"));
        }
        Wait.untilElementPresent("ishipping.country");
        String selectCountry = (country.equals("a random country") ? values.get(random.nextInt(values.size())) : country);
        if (newDropDownEnabled) {
            DropDowns.selectByText("ishipping.country", selectCountry);
        } else {
            DropDowns.selectCustomText("ishipping.country", "ishipping.country_list", selectCountry);
        }
        Clicks.click("ishipping.save_continue");
        Wait.forPageReady();
        resumePageHangWatchDog();
        Clicks.clickIfPresent("home.close_overlay_country");
        Navigate.visit("home");
        Wait.forPageReady();
        Clicks.clickIfPresent("home.close_welcome");
        closeBcomLoyaltyPromotionVideoOverlay();
    }
*/
    @Then("^I directly navigate to \"([^\"]*)\" page$")
    public void i_directly_navigate_to_page(String keyword) throws Throwable {
        if (keyword.equalsIgnoreCase("CUTLERY")) {
            Clicks.hoverForSelection(Elements.findElements("category_menu.category").stream().filter(ele -> ele.getText().equalsIgnoreCase("KITCHEN")).collect(Collectors.toList()).get(0));
            Elements.findElement("category_menu.flyout_menu").findElement(By.linkText("Bread Knives")).click();
        } else {
            Clicks.hoverForSelection(Elements.findElements("category_menu.category").stream().filter(ele -> ele.getText().equalsIgnoreCase("WOMEN")).collect(Collectors.toList()).get(0));
            Elements.findElement("category_menu.flyout_menu").findElement(By.linkText("Dresses")).click();
        }
    }

    @When("^I navigate to store and events$")
    public void i_navigate_to_store_and_events() {
        Wait.forPageReady();
        Clicks.click("ishipping.stores_events");
    }

    @Then("^I should see Customers Also Viewed and You Might Also Like panels$")
    public void i_should_see_Customers_Also_Viewed_and_You_Might_Also_Like_panels() {
        Wait.untilElementPresent("ishipping.CustomersAlsoViewed");
        Assert.assertTrue("Customers Also Viewed link is not present", Elements.elementPresent("ishipping.CustomersAlsoViewed"));
    }

    @When("^I find an available and orderable product \"([^\"]*)\" to my bag$")
    public void I_find_an_available_and_orderable_product_to_my_bag(String availableProductId) throws Throwable {
        CommonUtils.navigateDirectlyToProduct(availableProductId);
    }

    @And("^I add product from customer also viewed panel$")
    public void iAddProductFromCustomerAlsoViewedPanel() throws Throwable{
        Wait.forPageReady();
        Clicks.click("ishipping.addtobag_button");
        Wait.secondsUntilElementPresent("ishipping.checkout_Button",30);
        Wait.secondsUntilElementPresent("ishipping.prosRecommendation", 50);
        Wait.secondsUntilElementPresentAndClick("ishipping.select_recommendation_product",60);
        Navigate.browserRefresh();
    }

    @When("^I select an? \"(.*?)\" product to my bag(?: that is not(?: an?)? \"(.*?)\")?$")
    public void iSelectAProductToMyBag(String productTrue, String productFalse) throws Throwable {
        ShopAndBrowse shop = new ShopAndBrowse();
        shop.iNavigateToPdp(productTrue, productFalse);
    }

    @And("^I verify product selected from dyces available in shopping bag page$")
    public void iVerifyProductSelectedFromDycesAvailableInShoppingBagPage() throws Exception {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        String webId = WebDriverManager.getCurrentUrl().split("ID=")[1].split("&")[0];
        String webIDBulletText = Elements.findElement(Elements.element("ishipping.web_id")).getText();
        softly.assertThat(webIDBulletText).as("WEB ID Bullet Text").isEqualTo("Web ID: "+webId);
        Navigate.browserRefresh();
        Wait.secondsUntilElementPresentAndClick("ishipping.addtobag_button", 30);
        Wait.secondsUntilElementPresentAndClick("ishipping.checkout_Button", 30);
        Wait.forPageReady();
        Wait.secondsUntilElementPresent(By.xpath(Elements.getValues("ishipping.web_id_on_checkout").get(0).replace("$$$", webId)),30);
        Assert.assertTrue(Elements.elementPresent(By.xpath(Elements.getValues("ishipping.web_id_on_checkout").get(0).replace("$$$", webId))));
    }

    @Then("^I should see \"([^\"]*)\" listed in facets$")
    public void iShouldSeeListedInFacets(String facetName) throws Throwable {
        Wait.forPageReady();
        List<String> facetNames = LeftFacet.getAllFacetName();
        System.out.println(facetNames);
        Assert.assertTrue("ERROR- DATA:- Facet '" + facetName + "' is not available in facets. Please check BOPS data load completed successfully on this environment or not.", facetNames.contains(facetName));
        DiscoveryHelper.logInfo("Facet" + facetName + "is displaying in facets");
    }

    @And("^I navigate to \"([^\"]*)\" from Left Nav links$")
    public void iNavigateToFromLeftNavLinks(String leftNavCategory) throws Throwable {
        Home homePage = new Home();
        homePage.selectSubCategory(leftNavCategory);
        resumePageHangWatchDog();
    }

    @And("^I verify the title of browse page$")
    public void i_verify_the_title_of_browse_page() throws Throwable {
        try {
            if (macys()) {
                Wait.forPageReady();
                String ExpectedTitle = title();
                Assert.assertEquals(ExpectedTitle, "Dresses for Women - Shop the Latest Styles - Macy's");
            } else {
                Wait.forPageReady();
                String ExpectedTitle = title();
                Assert.assertEquals(ExpectedTitle, "Designer Dresses, Fashion Dresses & Designer Gowns - Bloomingdale's");
            }
        } catch (Exception e) {
            logger.info("Error message is: " + e);
        }
    }

    @And("^I verify the header text of browse page$")
    public void i_verify_the_header_text_of_browse_page() throws Throwable {
        try {
            if (macys()) {
                Assert.assertTrue(Elements.findElement("category_browse.verify_page_header_text_mcom").getText().contains("Dresses"));
            } else {
                Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("dresses"));
            }
        } catch (Exception e) {
            logger.info("Error message is: " + e);
        }
    }

    @And("^I verify product count on browse page$")
    public void i_verify_product_count_on_brwose_page() throws Throwable {
        try {
            if (macys()) {
                int product_count = Integer.parseInt(Elements.findElement("category_browse.product_count_mcom").getText());
                logger.info("product count=" + product_count);
                Assert.assertTrue("Search is not working", (product_count > 1000));
            } else {
                int product_count = Integer.parseInt(Elements.findElement("category_browse.product_count").getText());
                logger.info("product count=" + product_count);
                Assert.assertTrue("Search is not working", (product_count > 1000));
            }
        } catch (Exception e) {
            logger.info("Error message is: " + e);
        }
    }

    @And("^I verify filter by is displaying at left nav at browse page$")
    public void i_verify_filter_by_is_displaying_at_left_nav_at_browse_page() throws Throwable {
        try {
            if (macys()) {
                String exp_Text = Elements.findElement("category_browse.verify_filter_by_text").getText();
                Assert.assertEquals(exp_Text, "filter by");
            } else {
                Assert.assertTrue("facet list is getting displayed", Elements.findElement("category_browse.filter_location").isDisplayed());
            }
        } catch (Exception e) {
            logger.info("Error message is: " + e);
        }
    }

    /**
     * Checks all the red link is clickable at left nav for all catsplash page:
     *
     * @author: Ganesh Kumar
     * @date:14-June-2017
     */

    @When("^I navigate to all category pages and I verify left nav red link is clickable$")
    public void i_navigate_to_all_category_pages_and_I_verify_left_nav_red_link_is_clikable(DataTable dataTable) throws Throwable {
        List<String> categories = dataTable.asList(String.class);
        for (String cat : categories) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);
            Wait.forPageReady();
            if (cat.equalsIgnoreCase("Bed & Bath")) {
                Wait.forPageReady();
                String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                logger.info("Current page url is: " + Currentpageurl);
                WebElement elm = Elements.findElement("category_browse.red_link_At_Left_mcom");
                boolean el = isElementVisible(elm);
                if (el) {
                    int link_size = Elements.findElements("category_browse.red_link_At_Left_mcom").size();
                    for (int i = 0; i < link_size; i++) {
                        String redlinkTxt = Elements.findElements("category_browse.red_link_At_Left_mcom").get(i).getText();
                        String stringLastword = redlinkTxt.substring(redlinkTxt.lastIndexOf(" ") + 1);
                        logger.info("String at last postions is: " + stringLastword);
                        Clicks.click(Elements.findElements("category_browse.red_link_At_Left_mcom").get(i));
                        Wait.forPageReady();
                        Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                        String leftnavredlinkpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                        logger.info("Current page url is: " + leftnavredlinkpageurl);
                        Assert.assertNotEquals("Url should not match", Currentpageurl, leftnavredlinkpageurl);
                        String exvalue = Elements.findElement("category_browse.verify_page_header_text_mcom").getText().trim();
                        logger.info(exvalue);
                        if (exvalue.equalsIgnoreCase("Closeouts")) {
                            String replace = exvalue.replace(exvalue, "Clearance");
                            logger.info("Replce date is: " + replace);
                            Assert.assertTrue(replace.contains(stringLastword));
                        } else {
                            Assert.assertTrue(Elements.findElement("category_browse.verify_page_header_text_mcom").getText().trim().contains(stringLastword));
                        }
                        Navigate.browserBack();
                        Wait.forPageReady();
                        closePopup();
                    }
                } else {
                    logger.info("There is no red link at left nav at cat splash page");
                }
            }else{

                Wait.forPageReady();
                String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                logger.info("Current page url is: " + Currentpageurl);
                WebElement elm = Elements.findElement("category_browse.red_link_At_Left_mcom");
                boolean el = isElementVisible(elm);
                if (el) {
                    int link_size = Elements.findElements("category_browse.red_link_At_Left_mcom").size();
                    for (int i = 0; i < link_size; i++) {
                        String redlinkTxt = Elements.findElements("category_browse.red_link_At_Left_mcom").get(i).getText().toLowerCase().trim();
                        String[] titletxtlink = redlinkTxt.split(" ");
                        Clicks.click(Elements.findElements("category_browse.red_link_At_Left_mcom").get(i));
                        Wait.forPageReady();
                        Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                        String leftnavredlinkpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                        logger.info("Current page url is: " + leftnavredlinkpageurl);
/*                        boolean flag = false;
                        for (i = 0; i < titletxtlink.length; i++) {
                            if (leftnavredlinkpageurl.contains(titletxtlink[i])) {
                                logger.info("Navigated to red link lna page " + titletxtlink[i]);
                                flag = true;
                            }

                        }
                        Assert.assertTrue("not found the correct page", flag);*/
                        Assert.assertNotEquals("Url should not match", Currentpageurl, leftnavredlinkpageurl);
                        Navigate.browserBack();
                        Wait.forPageReady();
                        closePopup();
                    }
                } else {
                    logger.info("There is no red link at left nav at cat splash page");
                }
            }
        }

    }


    @And("^I verify featured category header$")
    public void i_verify_featured_category() throws Throwable {
        try {
            Wait.forPageReady();
            WebElement elm = Elements.findElement("preview_category.featured_categories_header_text");
            boolean el = isElementVisible(elm);
            if (el) {
/*               String elmnttext = Elements.findElement("preview_category.featured_categories_header_text").getText();
               logger.info(elmnttext);*/
                Assert.assertTrue(Elements.findElement("preview_category.featured_categories_header_text").getText().contains("Featured categories"));
            } else {
                logger.info("No Featured categories header is available for some category");
            }
        } catch (Exception e) {
            logger.info("Error message is: " + e);
        }
    }

    /**
     * Checks all the featured categories is clikable for all fob and validaion on landing page
     * Added validation for bcom preview as well
     * @author: Ganesh Kumar
     * @date:19-June-2017
     */

    @And("^I navigate to all features categories link and verified$")
    public void i_navigate_to_all_features_categories_link_and_verified() throws Throwable {
        if(macys()) {
            int link_size = Elements.findElements("preview_category.feature_categories_link").size();
            logger.info("Total number of feature categories link: " + link_size);
            for (int i = 0; i < link_size; i++) {
                Clicks.click(Elements.findElements("preview_category.feature_categories_link").get(i));
                Wait.forPageReady();
                if (WebDriverManager.getWebDriver().getTitle().contains("Macy's|Wedding Registry, Bridal Registry, wedding gift, anniversary registry")) {
                    logger.info("User is navigating to Wedding Registry page from featured category section");
                    Navigate.browserBack();
                    Wait.forPageReady();
                } else {
                    String featurecatpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                    logger.info("Current page url is: " + featurecatpageurl);
                    Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                    //                  Assert.assertTrue("verified featured category name in breadcruumb", Elements.elementPresent("preview_category.featured_category_breadcrumb_header"));

                    String expFeaturecaegoryPage = Elements.findElement("preview_category.featured_category_home_page").getText().toLowerCase().trim();
                    logger.info("Current featured category is: " + expFeaturecaegoryPage);
                    //  Assert.assertTrue("Selected featured category page is visible", Elements.elementPresent("preview_category.featured_category_home_page"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                }
            }
        }else{
            int link_size = Elements.findElements("preview_category.feature_categories_link").size();
            logger.info("Total number of feature categories link: " + link_size);
            for (int i = 0; i < link_size; i++) {
                String expFeatureCategoryText = Elements.findElements("preview_category.feature_category_link_text").get(i).getText().toLowerCase().trim();
                logger.info("Featured category link text is: " + expFeatureCategoryText);
                Clicks.click(Elements.findElements("preview_category.feature_categories_link").get(i));
                Wait.forPageReady();
                if (WebDriverManager.getWebDriver().getTitle().contains("Registry Home Page - Wedding Registry & Gift Registry at Bloomingdale's")) {
                    logger.info("User is navigating to Wedding Registry page from featured category section");
                    Navigate.browserBack();
                    Wait.forPageReady();
                } else {
                    String featurecatpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                    logger.info("Current page url is: " + featurecatpageurl);
                    Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                    Assert.assertTrue("verified featured category name in breadcruumb", Elements.elementPresent("preview_category.featured_category_breadcrumb_header"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                }
            }
        }
    }


    /**
     * Private Checks if element is visible Purpose:
     *
     * @author: Ganesh Kumar
     * @date:13-June-2017
     * @returnType: WebElement
     */
    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed() ? true : false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks all the essential featured categories home,Men and Women categories and validaion on landing page
     *
     * @author: Ganesh Kumar
     * @date:19-June-2017
     */

    @Given("^I navigate to \"([^\"]*)\" category page and I navigate to essentials category page and verified the featured categories for following categories$")
    public void i_navigate_to_category_page_and_I_navigate_to_home_essentials_category_page_and_verified_the_featured_categories_for_following_categories(String cate, DataTable dataTable) throws Throwable {
        new Home().selectMainCategory(cate);
        Wait.forPageReady();
        List<String> categories = dataTable.asList(String.class);
        for (String cat : categories) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectSubCategory(cat);
            Wait.forPageReady();
            int link_size = Elements.findElements("preview_category.feature_categories_link").size();
            logger.info("Total number of feature categories link: " + link_size);
            for (int i = 0; i < link_size; i++) {
                //  String featureCategorytext = Elements.findElements("category_browse.red_link_At_Left_mcom").get(i).getText().toLowerCase().trim();
                Clicks.click(Elements.findElements("preview_category.feature_categories_link").get(i));
                Wait.forPageReady();
                if (WebDriverManager.getWebDriver().getTitle().contains("Macy's|Wedding Registry, Bridal Registry, wedding gift, anniversary registry")) {
                    logger.info("User is navigating to Wedding Registry page from featured category section");
                    Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                    Navigate.browserBack();
                    Wait.forPageReady();
                } else {
                    String featurecatpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                    logger.info("Current page url is: " + featurecatpageurl);
                    Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                    String expFeaturecaegoryPage = Elements.findElement("preview_category.featured_category_home_page").getText().toLowerCase().trim();
                    logger.info("Current featured category is: " + expFeaturecaegoryPage);
                    Assert.assertTrue("Selected featured category page is visible", Elements.elementPresent("preview_category.featured_category_home_page"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                }
            }
            new Home().selectMainCategory(cate);
            Wait.forPageReady();
        }
    }

    /**
     * Checks all the red link is clickable at left nav for all catsplash page for bcom
     *
     * @author: Ganesh Kumar
     * @date:14-June-2017
     */

    @When("^I navigate to all category pages and I verify left nav red link is clikable for bcom$")
    public void i_navigate_to_all_category_pages_and_I_verify_left_nav_red_link_is_clikable_for_bcom(DataTable dataTable) throws Throwable {
        List<String> categories = dataTable.asList(String.class);
        for (String cat : categories) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);
            Wait.forPageReady();
            String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
            logger.info("Current page url is: " + Currentpageurl);
            String redColourlink = Elements.findElement("category_browse.red_left_link_bcom").getCssValue("color");
            String[] numbers = redColourlink.replace("rgba(", "").replace(")", "").split(",");
            int r = Integer.parseInt(numbers[0].trim());
            int g = Integer.parseInt(numbers[1].trim());
            int b = Integer.parseInt(numbers[2].trim());
            String hexValue = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
            logger.info("Hex Value for that color "+ hexValue);
            // boolean link = isElementVisible(redColourlink);
            boolean flag = false;
            if (hexValue.contains("#ea00")){
                String redlinktext = Elements.findElement("category_browse.red_left_link_bcom").getText().trim().toLowerCase();
                logger.info("lna text is :" + redlinktext);
                String[] linktext = redlinktext.split(" ");
                logger.info("lna text is :" + linktext[0]);
                Clicks.click(Elements.findElement("category_browse.red_left_link_bcom"));
                Wait.forPageReady();
                String redlinkCurrentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                Assert.assertTrue("Expected page is displaying", redlinkCurrentpageurl.contains(linktext[0]));
                Assert.assertTrue("Verify red lna page breadcrumb is visible" ,  Elements.elementPresent("category_browse.verify_lna_page"));
                flag=true;
            }
            Assert.assertTrue("No red link is visible at category for bcom : " , flag);
        }
    }

    @Then("^I select \"([^\"]*)\" for a random product$")
    public void iSelectForARandomProduct(String arg0) throws Throwable {
        Random randomGenerator;
        try {
            List<String> productIds = DiscoveryHelper.getProductIds();
            randomGenerator = new Random();
            int index = randomGenerator.nextInt(productIds.size() - 1);
            selectedProdId = productIds.get(index);
            DiscoveryHelper.selectQuickView(selectedProdId);
            Wait.ajaxDone();
            Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            Assert.assertTrue("ERROR - APP: Quick View overlay is not opened after click", Elements.elementPresent("quick_view.quick_view_close_dialog"));
        } catch (Exception e) {
            Assert.fail("ERROR - ENV: Unable to select the quick peek of random product" + e.getMessage());
        }
        DiscoveryHelper.logInfo("Selected the quick peek of random product id: " + selectedProdId);
    }

    @Then("^I should see price for the products$")
    public void iShouldSeePriceForTheProducts() throws Throwable {
        JSONArray productList = ProductThumbnail.getProductThumbnailDetails();
        productList.forEach(ele -> {
            JSONObject obj = (JSONObject) ele;
            String[] productPrice = (String[]) obj.get("product_prices");
            Assert.assertTrue("ERROR - APP: Prices are not displaying for product id " + obj.get("product_id"), productPrice != null && productPrice.length >= 1);
        });
        DiscoveryHelper.logInfo("All the products are displaying with price");
    }

    @Then("^I verify the basic attributes of COACH Brand browse Page$")
    public void iVerifyTheBasicAttributesOfCOACHBrandBrowsePage() throws Throwable {
        Wait.forPageReady();
        List<String> sortByOptions;
        List<String> productIds = DiscoveryHelper.getProductIds();
        List<String> facetNames = LeftFacet.getAllFacetName();
        int totalProductCount = DiscoveryHelper.getProductCount();
        if (facetNames == null || facetNames.size() == 0) {
            Assert.fail("ERROR - ENV: Facets are not displayed !!");
        }
        sortByOptions = macys() ? DropDowns.getAllValues("pagination.sort_by") : DropDowns.getAllCustomValues("pagination.sort_by", "pagination.sort_by_options");
        if (sortByOptions == null || sortByOptions.size() != (macys() ? 6 : 5)) {
            Assert.fail("ERROR - ENV: Sort by list options are not displayed correctly!!");
        }
        if (productIds != null && productIds.size() < 1) {
            Assert.fail("Products are not displayed!!");
        }
        if (bloomingdales()) {
            if (totalProductCount > 90) {
                Assert.assertTrue("ERROR - ENV: Pagination not displayed ", categoryBrowse.paginationAvailable());
            }
        } else {
            int selectedThumbnailColumn = 3;
            if (selectedThumbnailColumn == 3 && totalProductCount > 60) {
                Assert.assertTrue("ERROR - ENV: Pagination not displayed ", categoryBrowse.paginationAvailable());
            } else if (selectedThumbnailColumn == 4 && totalProductCount > 40) {
                Assert.assertTrue("ERROR - ENV: Pagination not displayed ", categoryBrowse.paginationAvailable());
            }
        }
        DiscoveryHelper.logInfo("Verified all the basic attributes of COACH Brand browse page");
    }


    @Then("^I should be taken directly to that product$")
    public void iShouldBeTakenDirectlyToThatProduct() throws Throwable {
        Wait.forPageReady();
        shouldBeOnPage("product_display");
        if (macys()) {
            WebElement prodid = Elements.findElement(By.className("productID"));
            Assert.assertTrue("ERROR - ENV: Not Navigated to searched productId", prodid.getText().contains("86800"));
        } else {
            WebElement prodid = Elements.findElement(By.id("productWebID"));
            Assert.assertTrue("ERROR - ENV: Not Navigated to searched productId", prodid.getText().contains("17979"));
        }
        DiscoveryHelper.logInfo("Navigated to searched productId product description page");
    }

    /**
     * Checks all the internal red link is clickable at left nav for WOMEN AND HOME  Category for Bcom
     * Validation on lna red link page
     * validation for 200 status code
     * @author: Ganesh Kumar
     * @date:21-June-2017
     */

    @And("^I navigate to \"([^\"]*)\" category page and I navigate to essentials \"([^\"]*)\" page and verified the red lna for following categories$")
    public void i_navigate_to_category_page_and_I_navigate_to_essentials_page_and_verified_the_red_lna_for_following_categories(String cat, String subcat) throws Throwable {
        new Home().selectMainCategory(cat);
        Wait.forPageReady();
        new Home().selectSubCategory(subcat);
        Wait.forPageReady();
        String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
        Assert.assertTrue("Expected page is displaying", Currentpageurl.contains(subcat));
        Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
        logger.info("Current page url is: " + Currentpageurl);
        String redColourlink = Elements.findElement("category_browse.internal_red_left_link_bcom").getCssValue("color");
        String[] numbers = redColourlink.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        String hexValue = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
        logger.info("Hex Value for that color "+ hexValue);
        // boolean link = isElementVisible(redColourlink);
        boolean flag = false;
        if (hexValue.contains("#ea00")){
            String redlinktext = Elements.findElement("category_browse.internal_red_left_link_bcom").getText().trim().toLowerCase();
            logger.info("lna text is :" + redlinktext);
            String[] linktext = redlinktext.split(" ");
            logger.info("lna text is :" + linktext[0]);
            Clicks.click(Elements.findElement("category_browse.internal_red_left_link_bcom"));
            Wait.forPageReady();
            Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
            String redlinkCurrentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
            Assert.assertTrue("Expected page is displaying", redlinkCurrentpageurl.contains(linktext[0]));
            Assert.assertTrue("Verify red lna page breadcrumb is visible" ,  Elements.elementPresent("category_browse.verify_lna_page"));
            flag=true;
        }
        Assert.assertTrue("No red link is visible at category for bcom : " , flag);
    }

    @Then("^All matching products should be returned$")
    public void allMatchingProductsShouldBeReturned() throws Throwable {
        Wait.forPageReady();
        List<String> productIds = DiscoveryHelper.getProductIds();
        int totalProductCount = DiscoveryHelper.getProductCount();
        Assert.assertTrue("All matching products had not returned", totalProductCount > 0 && productIds != null);
        DiscoveryHelper.logInfo("All matching products are products are returned for search keyword");
    }

    @Then("^I verify Left Nav section and links return (\\d+) OK$")
    public void iVerifyLeftNavSectionAndLinksReturnOK(int responsecode) throws Throwable {
        Wait.forPageReady();
        List<WebElement> leftNavLinks = Elements.findElements(By.className("gn_left_nav"));
        logger.info("All left Nav links count: " + leftNavLinks.size());
        pausePageHangWatchDog();
        for (WebElement link : leftNavLinks) {
            int responseCode = DiscoveryHelper.getResponseCode(link.getAttribute("href"));
            System.out.println(responseCode);
            logger.info("Response code for leftNavLink : " + link.getAttribute("href") + " = " + responseCode);
            Assert.assertTrue("ERROR - APP: Response code for left nav link href is not 200 and Actual Value is " + responseCode,
                    responseCode == responsecode || (responseCode >= 300 && responseCode <= 399));
        }
        resumePageHangWatchDog();
        logger.info("Verified: All left nav links work as expected");
    }


    /**
     * Checks all the internal featured caetgories link is clickable for WOMEN AND HOME  Category for Bcom
     * Validation on category landing page
     * validation for 200 status code on landing page
     * @author: Ganesh Kumar
     * @date:22-June-2017
     */

    @Given("^I navigate to \"([^\"]*)\" category page and I navigate to essentials \"([^\"]*)\" page and verified the internal featured categories for following categories$")
    public void i_navigate_to_category_page_and_I_navigate_to_essentials_page_and_verified_the_internal_featured_categories_for_following_categories(String cat, String subcat) throws Throwable {
        new Home().selectMainCategory(cat);
        Wait.forPageReady();
        new Home().selectSubCategory(subcat);
        Wait.forPageReady();
        if (subcat.contains("plus")) {
            String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
            Assert.assertTrue("Expected page is displaying", Currentpageurl.contains(subcat));
            Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
            int link_size = Elements.findElements("preview_category.feature_categories_link").size();
            logger.info("Total number of feature categories link: " + link_size);
            for (int i = 0; i < link_size; i++) {
/*               String expFeatureCategoryText = Elements.findElements("preview_category.feature_category_link_text").get(i).getAttribute("alt");
               logger.info("Featured category alt text is: " + expFeatureCategoryText);*/
                Clicks.click(Elements.findElements("preview_category.feature_categories_link").get(i));
                Wait.forPageReady();
                if (WebDriverManager.getWebDriver().getTitle().contains("Registry Home Page - Wedding Registry & Gift Registry at Bloomingdale's")) {
                    logger.info("User is navigating to Wedding Registry page from featured category section");
                    Navigate.browserBack();
                    Wait.forPageReady();
                } else {
                    String featurecatpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                    logger.info("Current page url is: " + featurecatpageurl);
                    Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                    Assert.assertTrue("verified featured category name in breadcruumb", Elements.elementPresent("preview_category.featured_category_breadcrumb_header"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                }
            }

        } else {
            String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
            Assert.assertTrue("Expected page is displaying", Currentpageurl.contains(subcat));
            Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
            int link_size = Elements.findElements("preview_category.feature_categories_link").size();
            logger.info("Total number of feature categories link: " + link_size);
            for (int i = 0; i < link_size; i++) {
                String expFeatureCategoryText = Elements.findElements("preview_category.feature_category_link_text").get(i).getText().toLowerCase().trim();
                logger.info("Featured category link text is: " + expFeatureCategoryText);
                Clicks.click(Elements.findElements("preview_category.feature_categories_link").get(i));
                Wait.forPageReady();
                if (WebDriverManager.getWebDriver().getTitle().contains("Registry Home Page - Wedding Registry & Gift Registry at Bloomingdale's")) {
                    logger.info("User is navigating to Wedding Registry page from featured category section");
                    Navigate.browserBack();
                    Wait.forPageReady();
                } else {
                    String featurecatpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                    logger.info("Current page url is: " + featurecatpageurl);
                    Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                    Assert.assertTrue("verified featured category name in breadcruumb", Elements.elementPresent("preview_category.featured_category_breadcrumb_header"));
                    Navigate.browserBack();
                    Wait.forPageReady();
                }
            }

        }
    }

    /**
     * Checks all the red lna link is for WOMEN Category is clickable for following sub categories: Plus,Petite and Lingerie
     * Validation on red lna landing page
     * validation for 200 status code on landing page
     * @author: Ganesh Kumar
     * @date:23-June-2017
     */

    @And("^I navigate to \"([^\"]*)\" category page and and I verify left nav red link is clikable for following subacategories:$")
    public void i_navigate_to_category_page_and_and_I_verify_left_nav_red_link_is_clikable_for_following_subacategories(String maincat, DataTable dataTable) throws Throwable {
        new Home().selectMainCategory(maincat);
        Wait.forPageReady();
        List<String> categories = dataTable.asList(String.class);
        logger.info("subcategories count is: " + categories);
        for (String cat : categories) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectSubCategory(cat);
            Wait.forPageReady();
            if (cat.equalsIgnoreCase("Bed & Bath")) {
                Wait.forPageReady();
                String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                logger.info("Current page url is: " + Currentpageurl);
                WebElement elm = Elements.findElement("category_browse.red_link_At_Left_mcom");
                boolean el = isElementVisible(elm);
                if (el) {
                    int link_size = Elements.findElements("category_browse.red_link_At_Left_mcom").size();
                    for (int i = 0; i < link_size; i++) {
                        String redlinkTxt = Elements.findElements("category_browse.red_link_At_Left_mcom").get(i).getText();
                        String stringLastword = redlinkTxt.substring(redlinkTxt.lastIndexOf(" ") + 1);
                        logger.info("String at last postions is: " + stringLastword);
                        Clicks.click(Elements.findElements("category_browse.red_link_At_Left_mcom").get(i));
                        Wait.forPageReady();
                        Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                        String leftnavredlinkpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                        logger.info("Current page url is: " + leftnavredlinkpageurl);
                        Assert.assertNotEquals("Url should not match", Currentpageurl, leftnavredlinkpageurl);
                        String exvalue = Elements.findElement("category_browse.verify_page_header_text_mcom").getText().trim();
                        logger.info(exvalue);
                        if (exvalue.equalsIgnoreCase("Closeouts")) {
                            String replace = exvalue.replace(exvalue, "Clearance");
                            logger.info("Replce date is: " + replace);
                            Assert.assertTrue(replace.contains(stringLastword));
                        } else {
                            Assert.assertTrue(Elements.findElement("category_browse.verify_page_header_text_mcom").getText().trim().contains(stringLastword));
                        }
                        Navigate.browserBack();
                        Wait.forPageReady();
                    }
                } else {
                    logger.info("There is no red link at left nav at cat splash page");
                }
                new Home().selectMainCategory(maincat);
                Wait.forPageReady();
            }else{

                String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                logger.info("Current page url is: " + Currentpageurl);
                WebElement elm = Elements.findElement("category_browse.red_link_At_Left_mcom");
                boolean el = isElementVisible(elm);
                if (el) {
                    int link_size = Elements.findElements("category_browse.red_link_At_Left_mcom").size();
                    for (int i = 0; i < link_size; i++) {
                        String redlinkTxt = Elements.findElements("category_browse.red_link_At_Left_mcom").get(i).getText().toLowerCase().trim();
                        logger.info(redlinkTxt);
                        Clicks.click(Elements.findElements("category_browse.red_link_At_Left_mcom").get(i));
                        Wait.forPageReady();
                        Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                        String leftnavredlinkpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                        logger.info("Current page url is: " + leftnavredlinkpageurl);
                        Assert.assertNotEquals("Url should not match", Currentpageurl, leftnavredlinkpageurl);
                        Assert.assertTrue("Verified red lna page header elemnt is displaying correctly on landing page", Elements.elementPresent("category_browse.verify_page_header_text_mcom"));
                        Navigate.browserBack();
                        Wait.forPageReady();
                    }
                } else {
                    logger.info("There is no red link at left nav at cat splash page");
                }
            }
            new Home().selectMainCategory(maincat);
            Wait.forPageReady();
        }

    }

    @When("^I select a random facet item from each of the facets$")
    public void iSelectARandomFacetItemFromEachOfTheFacets() throws Throwable {
        List<String> selectedFacetValues = new ArrayList<>();
        List<String> facets = LeftFacet.getAllFacetName();
        facets.remove("Pick Up In Store");
        facets.remove("Free Pick Up In Store");
        facets.remove("Category");
        if (macys()) { facets.remove("Size"); }
        for(String facet : facets){
            if (LeftFacet.getAllFacetName().contains(facet)) {
                String facetValue = randomFacetValueSelection(facet);
                selectedFacetValues.add(facetValue);
            }else{
              logger.info("Facet Name:"+facet+" is not available on page to verify facet value selection.");
            }
        }
        System.out.println("Selected factes : " + selectedFacetValues);
    }

    /**
     * Checks all the red lna link is for REGISTRY Page is clickable for following all categories:
     * Validation on red lna landing page
     * validation for 200 status code on landing page
     * @author: Ganesh Kumar
     * @date:23-June-2017
     */


    @When("^I navigate to following category pages and I verify left nav red link is clikable for registry page$")
    public void i_navigate_to_following_category_pages_and_I_verify_left_nav_red_link_is_clikable_for_registry_page(DataTable dataTable) throws Throwable {
        List<String> categories = dataTable.asList(String.class);
        logger.info("subcategories count is: " + categories);
        for (String cat : categories) {
            logger.info("opening category::" + cat);
            Wait.forPageReady();
            new Home().selectMainCategory(cat);
            Wait.forPageReady();
            String Currentpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
            Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
            logger.info("Current page url is: " + Currentpageurl);
            WebElement elm = Elements.findElement("category_browse.red_link_At_Left_mcom");
            boolean el = isElementVisible(elm);
            if (el) {
                int link_size = Elements.findElements("category_browse.red_link_At_Left_mcom").size();
                for (int i = 0; i < link_size; i++) {
                    String redlinkTxt = Elements.findElements("category_browse.red_link_At_Left_mcom").get(i).getText().toLowerCase().trim();
                    logger.info(redlinkTxt);
                    Clicks.click(Elements.findElements("category_browse.red_link_At_Left_mcom").get(i));
                    Wait.forPageReady();
                    Assert.assertTrue("Verifing the correct response code", previewCategory.i_verify_the_response_code_for_cat_browse_page());
                    String leftnavredlinkpageurl = WebDriverManager.getWebDriver().getCurrentUrl();
                    Assert.assertTrue("Current page url should contained registry events", leftnavredlinkpageurl.contains(redlinkTxt));
                    //logger.info("Current page url is: " + leftnavredlinkpageurl);
                    Assert.assertNotEquals("Url should not match", Currentpageurl, leftnavredlinkpageurl);
                    Navigate.browserBack();
                    Wait.forPageReady();
                }
            } else {
                logger.info("There is no red link at left nav at cat splash page");
            }
        }

    }


    @Then("^I should see a filtered list of displayed products$")
    public void iShouldSeeAFilteredListOfDisplayedProducts() throws Throwable {
        Wait.forPageReady();
        List<String> productIds = DiscoveryHelper.getProductIds();
        int totalProductCount = DiscoveryHelper.getProductCount();
        Assert.assertTrue("All matching products had not returned", totalProductCount > 0 && productIds != null);
        DiscoveryHelper.logInfo("All matching products are products are returned for search keyword");
        Assert.assertTrue("ERROR - APP: Breadcrumbs are not displaying", DiscoveryHelper.breadCrumbValues().size() > 0);
    }

    @When("^I select a random facet item from each of the facets and check the breadcrumbs$")
    public void iSelectARandomFacetItemFromEachOfTheFacetsAndCheckTheBreadcrumbs() throws Throwable {
        List<String> facets = LeftFacet.getAllFacetName();
        facets.remove("Pick Up In Store");
        facets.remove("Free Pick Up In Store");
        facets.remove("Category");
        facets.remove("Price");
        if (macys()) {
            facets.remove("Size");
        }
        for (String facet : facets) {
            if (!facet.contains("Customer") && facetAvailable(facet)) {
                String facetValue = randomFacetValueSelection(facet);
                List<String> breadcrumbs = DiscoveryHelper.breadCrumbValues();
                if(facetValue.toLowerCase().contains("gift with")){
                    breadcrumbs = breadcrumbs.stream().map(m -> m.toLowerCase()).collect(Collectors.toList());
                    Assert.assertTrue("ERROR - APP: Selected Facet Value:'"+facetValue.toLowerCase()+"' is not displaying in facet breadcrumb list. Actual:"+breadcrumbs, breadcrumbs.contains(facetValue.toLowerCase()));
                }else {
                    Assert.assertTrue("ERROR - APP: Selected Facet Value:'" + facetValue + "' is not displaying in facet breadcrumb list. Actual:" + breadcrumbs, breadcrumbs.contains(facetValue));
                }
            }
        }
    }

    @Then("^I verify the search results are sorted by \"([^\"]*)\" across multiple pages$")
    public void iVerifyTheSearchResultsAreSortedByAcrossMultiplePages(String sortOrder) throws Throwable {
        iVerifyProductDisplayOrderForOption(sortOrder);
        if (DiscoveryHelper.paginationAvailable()) {
            int pageCount = DiscoveryHelper.getTotalPageCount();
            if (pageCount > 2) {
                for (int i = 2; i < 5; i++) {
                    DiscoveryHelper.goToPageNumber(i);
                    iVerifyProductDisplayOrderForOption(sortOrder);
                }
            }
        }
    }

    @When("^I add ? \"(.*?)\" product to my bag(?: that is not(?: an?)? \"(.*?)\")?(?: and \"?(.*?)\"? ?checkout)?$")
    public void I_add_product_to_my_bag(String productTrue, String productFalse, String checkout) throws Throwable {
        ShopAndBrowse shop = new ShopAndBrowse();
        shop.iNavigateToPdp(productTrue, productFalse);
        if (productTrue.contains("virtual_gift_card")) {
            shop.addVirtualGiftCardToBag();
        } else {
            shop.I_add_product_to_my_bag_from_standard_PDP_Page();
        }
    }


    private void iVerifyProductDisplayOrderForOption(String sortOption) {
        JSONArray productList = ProductThumbnail.getProductThumbnailDetails();
        ArrayList productPricesAfterAction = new ArrayList();
        ArrayList productPricesBeforeAction = new ArrayList();
        ArrayList productSalePricesAfterAction = new ArrayList();
        productList.forEach(prod -> {
            JSONObject obj = (JSONObject) prod;
            String[] productPrice = (String[]) obj.get("product_prices");
            String salePrice = productPrice[productPrice.length - 1];
            productPricesAfterAction.add(salePrice);
            if (sortOption.equalsIgnoreCase("Price: High to Low") || sortOption.equalsIgnoreCase("Price (high-low)")) {
                salePrice = salePrice.contains("-") ? salePrice.split(" - ")[1].toLowerCase() : salePrice.toLowerCase();
            } else if (sortOption.equalsIgnoreCase("Price: Low to High") || sortOption.equalsIgnoreCase("Price (low-high)")) {
                salePrice = salePrice.contains("-") ? salePrice.split(" - ")[0].toLowerCase() : salePrice.toLowerCase();
            }
            salePrice = salePrice.replace("your choice", "").replace("now ", "").replace("sale ", "").replace(",", "");
            salePrice = (bloomingdales() && salePrice.contains("(")) ? salePrice.split(" \\(")[0] : salePrice;
            salePrice = salePrice.contains("$") ? salePrice.replace("$", "") : salePrice;
            salePrice = salePrice.contains(" ") ? salePrice.split(" ")[1] : salePrice;
            if(salePrice.matches("\\d+\\.\\d+")){
                productSalePricesAfterAction.add(Double.parseDouble(salePrice));
            }else{
                logger.info("Found invalid price string:'" +salePrice+"' for product:"+prod);
            }
        });
        ArrayList productSalePricesToSort = (ArrayList) productSalePricesAfterAction.clone();
        Collections.sort(productSalePricesToSort);
        if (sortOption.equalsIgnoreCase("Price: High to Low") || sortOption.equalsIgnoreCase("Price (high-low)")) {
            Collections.reverse(productSalePricesToSort);
        }
        ShopAndBrowse.productsListBeforeAction.forEach(prod -> {
            JSONObject obj = (JSONObject) prod;
            String[] productPrice = (String[]) obj.get("product_prices");
            productPricesBeforeAction.add(productPrice[productPrice.length - 1]);
        });
        logger.info("Actual Products with Sort:" + productSalePricesAfterAction.toString());
        logger.info("Expected Products with Sort:" + productSalePricesToSort.toString());
        Assert.assertFalse("ERROR - ENV: Products price order is not changed for sort option:" + sortOption, productPricesBeforeAction.toString().equals(productPricesAfterAction.toString()));
        Assert.assertTrue("ERROR - APP: Products are displaying as per selected sort option:" + sortOption, productSalePricesAfterAction.toString().equals(productSalePricesToSort.toString()));
        logger.info("Verified sortby: " +sortOption);
    }

    @When("^I close Breadcrumb of any selected facet$")
    public void iCloseBreadcrumbOfAnySelectedFacet() throws Throwable {
        List<String> breadcrumbValues = DiscoveryHelper.breadCrumbValues();
        breadcrumbValues.forEach(breadCrumbValue -> {
            List<WebElement> breadCrumbEles = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs")).stream().filter(f -> f.getText().equalsIgnoreCase(breadCrumbValue)).collect(Collectors.toList());
            Assert.assertFalse("ERROR - APP: Breadcrumb value:" + breadCrumbValue + " is not exists to de-select", breadCrumbEles.isEmpty());
            Clicks.click(breadCrumbEles.get(0));
            Wait.ajaxDone();
            Assert.assertFalse("ERROR - APP: Facet value:'" + breadCrumbValue + "' is not de-selected", DiscoveryHelper.breadCrumbValues().contains(breadCrumbValue));
        });
        DiscoveryHelper.logInfo("Removed the selected facets from the breadcrumb");
    }

    @Then("^I should see Breadcrumbs are removed from pagination section$")
    public void iShouldSeeBreadcrumbsAreRemovedFromPaginationSection() throws Throwable {
        Assert.assertTrue("ERROR - APP: Facet values are not de-selected", DiscoveryHelper.breadCrumbValues().size() == 0);
    }
}
