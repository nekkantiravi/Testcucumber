package com.macys.sdt.projects.Selection.Regression.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Selection.Regression.actions.website.DSVPDPActions;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;


public class DSVPDPSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(DSVPDPSteps.class);
    public String productHeading="";

    @When("^I navigate directly to Reviews PDP page$")
    public void i_navigate_directly_to_Reviews_PDP_page() throws Throwable {
        HashMap<String, Object> productHash = new HashMap<String, Object>()
        {{
            put("available", true);
            put("master_product", true);
            put("has_reviews", true);
            put("has_ratings", true);
        }};
        Product p = TestUsers.getRandomProduct(productHash);
        int productId = p.id;
        TextBoxes.typeTextNEnter("header.search_field", Integer.toString(productId));
        StepUtils.shouldBeOnPage("product_display");
    }

    @When("^I click on the \"([^\"]*)\" tab$")
    public void i_click_on_the_tab(String tabName) throws Throwable {
        DSVPDPActions.closePopUp();
        switch (tabName){
            case "Reviews":{
                Wait.untilElementPresent("pdp.master_reviews_tab");
                Clicks.click("pdp.master_reviews_tab");
                break;
            }
            default:{
                Assert.fail("Invalid tab name");
                break;
            }
        }
    }

    @Then("^I should see product ratings and reviews under the reviews tab on PDP Page$")
    public void iShouldSeeProductRatingsAndReviewsUnderTheReviewsTabOnPDPPage() throws Throwable {
        Assert.assertTrue("No reviews & ratings are shown", (Wait.untilElementPresent("pdp.review") &&  Wait.untilElementPresent("pdp.rating")));
    }

    @Then("^I \"([^\"]*)\" see \"([^\"]*)\" link$")
    public void i_see_link(String condition, String linkName) throws Throwable {
        linkName = linkName.toLowerCase();
        String selector = "";
        switch (linkName){
            case"write a comment":{
                selector = "product_display.write_a_review_button";
                break;
            }
            default:{
                Assert.fail("Invalid linkName");
                break;
            }
        }
        switch (condition){
            case "should": {
                Assert.assertTrue("ERROR - ENV: The link is not present" + linkName, Elements.elementPresent(selector));
                break;
            }
            case "should not":{
                Assert.assertFalse("ERROR - ENV: The link is present" + linkName, Elements.elementPresent(selector));
                break;
            }
            default:{
                Assert.fail("Invalid option");
                break;
            }
        }
    }

    @When("^I change the country to \"([^\"]*)\"$")
    public void I_change_country_to(String country) throws Throwable {
        DSVPDPActions.changeCountryTo(country);
    }

    @When("^I add a random E-Gift gift card to my bag$")
    public void iAddARandomEGiftGiftCardToMyBag() throws Throwable {
        DSVPDPActions.addRandomEGiftToBag();
    }

    @And("^I navigate to PDP from shopping bag$")
    public void iNavigateToPDPFromShoppingBag() throws Throwable {
        DSVPDPActions.NavigateToPDPFromShoppingBag();
    }

    @And("^I add another product to my bag$")
    public void iAddAnotherProductToMyBag() throws Throwable {
        DSVPDPActions.addAnotherProduct();
    }

    @And("^I continue to shopping bag$")
    public void iContinueToShoppingBag() throws Throwable {
        DSVPDPActions.continueToShoppingBag();
    }

    @And("^I verify the quantity in shopping bag equals (\\d+)$")
    public void iVerifyTheQuantityInShoppingBagEquals(int count) throws Throwable {
        Assert.assertEquals("The quantity of items doesn't match", count, DSVPDPActions.getItemsCount());
    }

    @When("^I navigate to (COACH|CHANEL|Gift Card) PDP page$")
    public void iNavigateToCOACHPDPPage(String name) throws Throwable {
        switch (name){
            case "COACH":{
                DSVPDPActions.navigateToCoachPDP();
                break;
            }
            case "CHANEL":{
                DSVPDPActions.navigateToChanelPDP();
                break;
            }
            case "Gift Card":{
                DSVPDPActions.navigateToEgiftPDP();
                break;
            }
            default:{
                Assert.fail("Invalid option");
            }
        }
    }

    @Then("^I (should|should not) see social media icons such as facebook, google plus, pinterest and twitter on PDP Page$")
    public void iShouldNotSeeSocialMediaIconsSuchAsFacebookGooglePlusPinterestAndTwitterOnPDPPage(String option) {
        ArrayList<String> socialMedias = new ArrayList<>();
        Clicks.clickIfPresent("product_display.share_icon");
        socialMedias.addAll(Arrays.asList("product_display.share_facebook", "product_display.share_pinterest", "product_display.share_twitter"));
        for (String socialMedia:socialMedias){
            switch (option){
                case "should not":{
                    Assert.assertFalse("ERROR - ENV: The " + socialMedia + " Icon is present", Elements.elementPresent(socialMedia));
                    break;
                }
                case "should":{
                    Assert.assertTrue("ERROR - ENV: The " + socialMedia + " Icon is not present", Elements.elementPresent(socialMedia));
                    break;
                }
                default:{
                    Assert.fail("Invalid option");
                    break;
                }
            }
        }
    }

    @Then("^I should see the item level error:$")
    public void iShouldSeeTheItemLevelError(Map<String, String> table) throws Throwable {
        String brand, expectedMessage;
        for (Map.Entry<String, String> entry:table.entrySet()){
            brand = entry.getKey();
            expectedMessage = entry.getValue();
            if (brand.toLowerCase().equals("bcom")){
                String errorMessage = Elements.getText("shopping_bag.item_level_error_message");
                Assert.assertTrue("ERROR - ENV: Error Message is not as expected", errorMessage.equals(expectedMessage));
            }
        }
    }

    @And("^I select a UPC of the product$")
    public void iSelectAUPCOfTheProduct() throws Throwable{
        if(StepUtils.bloomingdales()){
            ProductDisplay.selectRandomColor();
            ProductDisplay.selectRandomSize();
        }
    }

    private WebElement randomStoreDiv;

    @And("^I navigate to CHOOSE A STORE overlay in PDP$")
    public void iNavigateToCHOOSEASTOREOverlayInPDP() throws Throwable {
            Clicks.click("product_display.find_store_link");
            Wait.secondsUntilElementPresent("change_pickup_store_dialog.search_field", 50);
            Assert.assertTrue("ERROR ENV - Not on the 'change pickup store' dialog" , Wait.untilElementPresent("change_pickup_store_dialog.search_field"));
        }

    @And("^I select a BOPS available store from the ISA overlay$")
    public void iSelectABOPSAvailableStoreFromTheISAOverlay() throws Throwable {
        if(StepUtils.bloomingdales()){
            Wait.untilElementPresent("change_pickup_store_dialog.random_store");
            List<WebElement> stores = Elements.findElements(Elements.element("change_pickup_store_dialog.random_store"));
            randomStoreDiv = stores.get(new Random().nextInt(stores.size()));
            Clicks.click(randomStoreDiv.findElement(Elements.element("change_pickup_store_dialog.select_store_span")));
        }
    }

    @And("^I select a view map link for a \"([^\"]*)\" in ISA overlay$")
    public void iSelectAViewMapLinkForAInISAOverlay(String name) throws Throwable {
        if(StepUtils.bloomingdales()){
            if(name.toLowerCase().equals("bops_store")){
                Clicks.hoverForSelection(randomStoreDiv);
                Wait.untilElementPresent("change_pickup_store_dialog.toggle_map_view");
                Clicks.click(randomStoreDiv.findElement(Elements.element("change_pickup_store_dialog.toggle_map_view")));
            }
        }
    }

    @Then("^I should see google map displaying for the selected \"([^\"]*)\"$")
    public void iShouldSeeGoogleMapDisplayingForTheSelected(String name) throws Throwable {
        if(StepUtils.bloomingdales()){
            if(name.toLowerCase().equals("bops_store")){
                WebElement mapview = randomStoreDiv.findElement(Elements.element("change_pickup_store_dialog.map_view"));
                Clicks.hoverForSelection(mapview);
                Assert.assertTrue("ERROR - ENV: The map is not displayed", mapview.isDisplayed());
            }
        }
    }

    @Then("^I verify the title tag on PDP page$")
    public void i_verify_the_title_tag_on_PDP_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String expectedTitle = Elements.findElement("product_display.product_title").getText();
        logger.info("Product title::" + expectedTitle);
        logger.info("exp::"+expectedTitle.split("\n")[0]);
        String title= expectedTitle.split("\n")[0];
        String firstTitle=title.split(" ")[0];
        logger.info("main::"+firstTitle);


        Assert.assertTrue("ERROR - ENV: Browser title does not include product title found in PDP",
                WebDriverManager.getCurrentUrl().contains(firstTitle.toLowerCase()));

    }

    @Then("^I verify the product name in browse page continues to PDP$")
    public void i_verify_the_product_name_in_browse_page_continues_to_PDP() throws Throwable {

        String productTitleBrowse = null;
        if (bloomingdales()) {
            productTitleBrowse = Elements.findElement("category_browse.product_thumbnail").getAttribute("title");
            logger.info("Product title on Browse Page::" + productTitleBrowse);
        } else {
            logger.info(WebDriverManager.getCurrentUrl());
            productTitleBrowse = Elements.findElement("category_browse.product_thumbnail").getText();
            logger.info("Product title on Browse Page::" + productTitleBrowse);
        }
        Elements.findElement("category_browse.product_thumbnail").click();
        logger.info(WebDriverManager.getCurrentUrl());
        String productTitlePDP = Elements.findElement("product_display.product_title").getText();
        logger.info("Product title on PDP::" +productTitlePDP);
        logger.info("after trimming browse::" +productTitleBrowse.split("-")[0].trim());
        logger.info("after trimming pdp::" +productTitlePDP.replace("\n"," ").split("-")[0].trim());

        if(bloomingdales()){
            Assert.assertTrue("Product title found in category browse page is not found in PDP page",
                    productTitleBrowse.split("-")[0].trim().equalsIgnoreCase(productTitlePDP.split("\n")[0].trim()));
        }
        else
            Assert.assertTrue("Product title found in category browse page is not found in PDP page",
                productTitleBrowse.split("-")[0].trim().equalsIgnoreCase(productTitlePDP.replace("\n"," ").split("-")[0].trim()));
    }

    @Then("^I verify the zoom functionality$")
    public void i_verify_the_zoom_functionality() throws Throwable {
        Assert.assertTrue(DSVPDPActions.verifyMouseHoveronPDPImage());
    }

    @When("^I should see vertical recommendation section is displayed on PDP page$")
    public void i_should_see_vertical_recommendation_is_displayed_on_PDP_page() throws Throwable {
        Assert.assertTrue("Vertical recommendation section is not displayed on PDP Page",
                Elements.elementPresent("recommendations.vertical_recommendations"));
        logger.info("Vertical recommendation section is displayed on PDP");
    }

    @When("^I click on any one recommended products$")
    public void i_click_on_any_one_recommended_products() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }



    @Then("^I should be navigated to the corresponding PDP page from recommendations section$")
    public void i_should_be_navigated_to_the_corresponding_PDP_page() throws Throwable {

        List<WebElement> recommendations = Elements.findElements("pdp.recommendation_products");
        Random number= new Random();
        int randomNumber=number.nextInt(3);
        String webIdRecommdendation = null;
        if(bloomingdales()) {
            webIdRecommdendation = recommendations.get(randomNumber).getAttribute("data-webid");
        }
        else{
            webIdRecommdendation = recommendations.get(randomNumber).getAttribute("id");
        }
        logger.info("web id of product in recommendation section::" +webIdRecommdendation);
        recommendations.get(randomNumber).click();
        shouldBeOnPage("product_display");
        logger.info(WebDriverManager.getCurrentUrl());
        if(bloomingdales()) {
            Elements.findElement("pdp.show_more").click();
        }
        Wait.forPageReady();
        shouldBeOnPage("product_display");
        String webdIdOnPDP = Elements.getText("pdp.web_id");
        logger.info("web id of opened product::" +webdIdOnPDP);
        logger.info("web id::"+webdIdOnPDP.split(" ")[2]);

        Assert.assertTrue("ERROR: Recommended product id not matched",
               webIdRecommdendation.equalsIgnoreCase(webdIdOnPDP.split(" ")[2]) );
    }

    @When("^I navigate PDP page that has recommendations in \"([^\"]*)\" panel in \"([^\"]*)\" mode$")
    public void iNavigatePDPPageThatHasRecommendationsInPanelInMode(String panel, String mode) {
        switch (mode){
            case "site": CommonUtils.navigateToRandomProduct("orderable and available","master_product");
                break;
            case "registry": CommonUtils.navigateToRandomProduct("orderable and available and registrable","master_product");
                break;
            case "iship": CommonUtils.navigateToRandomProduct("orderable and available and iship_eligible","master_product");
                break;
        }
    }
    @Then("^I verify the product name in browse page continues to pdp$")
    public void iVerifyTheProductNameInBrowsePageContinuesToPDP() {
        WebElement randomProduct = Elements.getRandomElement("category_browse.product_names");
        String titleInBrowsePage = randomProduct.getText();
        Clicks.click(randomProduct);
        Wait.forPageReady();
        Assert.assertTrue("Title doesn't match",titleInBrowsePage.equals(Elements.findElement("product_display.product_name").getText()));
    }

    @Then("^I verify social icon links on PDP$")
    public void i_verify_social_icon_links_on_PDP() throws Throwable {
        if (bloomingdales()) {
            shouldBeOnPage("product_display");
            Wait.secondsUntilElementPresent("pdp.share_toggle", 20);
            try {
                Clicks.click("pdp.share_toggle");
            } catch (Exception e) {
                Assert.fail("Unable to to click share toggle link " + e.getMessage());
            }
            DSVPDPActions.facebookVerification();
            DSVPDPActions.pinterestVerification();
            DSVPDPActions.twitterVerification();
        } else {
            DSVPDPActions.pinterestVerification();
        }
    }

    @When("^I navigate to the PDP page from shopping bag page$")
    public void i_navigate_to_the_PDP_page_from_shopping_bag_page() throws Throwable {
        shouldBeOnPage("shopping_bag");
        Clicks.click("shopping_bag.product_image");
    }

    @Then("^I should see the item level error on PDP page:$")
    public void i_should_see_the_item_level_error_on_PDP_page(Map<String, String> table) throws Throwable {
        String brand, expectedMessage;
        for(Map.Entry<String, String> entry :table.entrySet()){
            brand=entry.getKey();
            expectedMessage=entry.getValue();
            if(brand.toLowerCase().equals("bcom")){
                String errorMessage= Elements.getText("pdp.item_error_message");
           Assert.assertEquals("ERROR - ENV: Error Message is not as expected on PDP page",
                        errorMessage,expectedMessage);
            }
        }
    }
    @Then("^I verify the display of Q&A in PDP$")
    public void i_verify_the_display_of_qa_in_pdp() throws Throwable {

        scrollToLazyLoadElement("olapic.bbolapic_carousel");
        Wait.secondsUntilElementPresent("pdp.qa_reg_tab", 10);
        Thread.sleep(5000);
        Assert.assertTrue("PDP Page does not contains QA Tab", Elements.elementPresent("pdp.qa_reg_tab"));
        Clicks.click("pdp.qa_reg_tab");
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("pdp.qa_reg_header", 10);
        Assert.assertTrue("PDP Page does not QA Header", Elements.elementPresent("pdp.qa_reg_header"));
        logger.info("QA section is displaying in Registry PDP");
    }

    @And("^I verify Q&A in page source$")
    public void i_verify_qa_in_page_source() throws Throwable {
        Assert.assertTrue("QA does not display in page source", WebDriverManager.getWebDriver().getPageSource().contains("Product Q&amp;A"));
        logger.info("QA section is displaying in Registry Page Source");

    }

    @And("^I should see scrolling in recommendation panel$")
    public void i_should_see_scrolling_in_recommendation_panel() throws Throwable {
        try {
            System.out.println("Size=" + Elements.findElements("recommendations.vertical_recommendations").size());
            if (Elements.findElements("recommendations.vertical_recommendations").size() > 2) {
                Clicks.click("recommendations.scroll_forward");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Then("^I should see recommendation products in vertical panel$")
    public void i_should_see_recommendation_products_in_vertical_panel() throws Throwable {
        Assert.assertFalse("Product list is Empty",
                Elements.findElements("pdp.vertical_recommendations").isEmpty());
    }

    @Then("^I verify currency in category and PDP page in Domestic Mode$")
    public void iVerifyCurrencyInCategoryAndPDPPageInDomesticMode() {
            String price = (Elements.findElement("pdp.price_browse")).getText();
            String currency = ShopAndBrowse.currency;
            Assert.assertTrue("ERROR - APP: Expected Currency:" + currency + " doesn't match in Browse Page price:" + price, price.contains("$"));
            Wait.secondsUntilElementPresent("product_thumbnails.product_thumbnails_image", 30);
            Clicks.clickRandomElement("product_thumbnails.product_thumbnails_image");
            Wait.forPageReady();
            String pdp_price = Elements.findElement("pdp.product_price_domestic").getText();
            Assert.assertTrue("ERROR - APP: Expected Currency:" + currency + " doesn't match in PDP price:" + price, pdp_price.contains("$"));
    }

    @Then("^I verify the basic attributes of Q&A TAB in PDP Page$")
    public void i_verify_the_basic_attribute_of_qa_tab_in_pdp_page() throws Throwable {
        Navigate.scrollPage(0,1200);
        Wait.secondsUntilElementPresent("pdp.qa_pdp_section",40);
        Assert.assertTrue("PDP Page does not QA Tab", Elements.elementPresent("pdp.qa_pdp_section"));
        Clicks.click("pdp.qa_pdp_section");
        Wait.secondsUntilElementPresent("pdp.qa_reg_header",40);
        Assert.assertTrue("PDP Page does not QA Header",Elements.elementPresent("pdp.qa_reg_header"));
        logger.info("The basic attributes of Q&A TAB in PDP Page is displayed!!");
    }
    @Then("^I verify the update order of Copy & Bullet points on PDP$")
    public void i_verify_the_update_order_of_copy_bullet_points_on_pdp() throws Throwable {
        Assert.assertTrue("ERROR - ENV: update order of Copy & Bullet points not displaying", Elements.elementPresent("product_display.pdp_bullets"));
        Assert.assertTrue("ERROR - ENV: Warranty request information is not displaying in bullet points",Elements.elementPresent("product_display.warranty_request"));
        logger.info("update order of Copy & Bullet points on PDP verified successfully");
    }

    @And("^I store product title$")
    public void iStoreProductTitle() throws Throwable {
        productHeading = Elements.findElement("pdp.productDescription").getText();

    }
    @And("^I should see products in scroller$")
    public void iShouldSeeProductsInScroller() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent( "pdp.you_might_like",20);
        WebElement we=Elements.findElement("pdp.you_might_like");
        List<WebElement> imagesList = we.findElements(By.tagName("img"));
        Assert.assertTrue("You might also like this is blank",
                imagesList.size()>1);
    }

    @And("^I select any recommendation product and navigate to bag to match products name$")
    public void iSelectAnyRecommendationProductAndNavigateToBagToMatchProductName() throws Throwable {
        Wait.secondsUntilElementPresent( "pdp.total_scroll_products",20);
        Clicks.click("pdp.total_scroll_products");
        Wait.secondsUntilElementPresent( "product_display.add_to_bag_button",20);
        String productTitle = Elements.findElement("pdp.productDescription").getText();
        Clicks.click("product_display.add_to_bag_button");
        Wait.forPageReady();
        logger.info("Product added to bag - "+productTitle);
        Wait.secondsUntilElementPresent( "add_to_bag_dialog.add_to_bag_checkout",20);
        Clicks.click("add_to_bag_dialog.add_to_bag_checkout");
        List<String> products = new ArrayList<>();
        for (WebElement element : Elements.findElements("pdp.product_list")) {
            products.add(element.getText());
        }
        Assert.assertFalse("Product title found in shopping bag is not found in PDP page",
                products.contains(productTitle));
        Assert.assertFalse("Product title found in shopping bag is not found in PDP page",
                products.contains(productHeading));
        logger.info("Product title in shopping bag macthing with recommended product");

    }

}