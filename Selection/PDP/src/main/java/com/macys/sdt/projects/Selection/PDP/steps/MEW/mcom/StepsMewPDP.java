package com.macys.sdt.projects.Selection.PDP.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class StepsMewPDP {
    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private SoftAssertions softly = new SoftAssertions();
    private String productId = "";
    private String category = "";
    private String subCategory = "";
    private String webId = "";


    @Given("^I visit mobile site as a (guest|registered) user in (site|iship|registry) mode$")
    public void visitingMobileSite(String user, String mode) throws Throwable{
        switch(user) {
            case "guest": {
                if(mode.equalsIgnoreCase("registry")) {
                    new PageNavigation().I_visit_the_mobile_web_site_as_a_registered_user("guest");
                    GlobalNav.openGlobalNav();
                    GlobalNav.navigateOnGnByName("WEDDING REGISTRY");
                    Wait.forPageReady();
                    softly.assertThat(Elements.findElement(By.xpath(".//*[@id='mb-j-main-content-container']//h1")).getText()
                            .equalsIgnoreCase("Macy's wedding registry")).as("Registry HomePage");
                    log.info(" Guest user is on Mobile Registry Mode/HomePage!");
                }
                break;
            }
            case "registered": {
                if(mode.equalsIgnoreCase("registry")) {
                    new PageNavigation().I_visit_the_mobile_web_site_as_a_registered_user("registered");
                    GlobalNav.openGlobalNav();
                    GlobalNav.navigateOnGnByName("WEDDING REGISTRY");
                    Wait.forPageReady();
                    softly.assertThat(Elements.findElement(By.xpath(".//*[@id='mb-j-main-content-container']//h1")).getText()
                            .equalsIgnoreCase("Macy's wedding registry")).as("Registry HomePage");
                    log.info(" Registered user is on Mobile Registry Mode/HomePage!");
                }
                break;
            }
        }
    }


    @When("^I navigate to PDP with PID \"([^\"]*)\" in (site|iship|registry) mode$")
    public void navigatingToPDPwithPID(String PID, String mode) throws Throwable {
        productId = PID;
        TextBoxes.typeTextNEnter("home.search_field", productId);
        Wait.forPageReady();
        if(!(mode.equalsIgnoreCase("iship")))
            Cookies.deleteAllCookies();
        Navigate.browserRefresh();
        Wait.secondsUntilElementPresent(By.cssSelector(".m-j-product-main-image"),10);
    }

    @And("^I save the (productId|productName|priceDetails) on (PDP|browse|search) page$")
    public void savingProductDetails(String arg, String pg) throws Throwable {

        switch(arg) {
            case "productId": {
                if(pg.equalsIgnoreCase("PDP")) {
                    Wait.forPageReady();
                    Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/4)");
                    log.info(" \"" + Elements.findElement(Elements.element("pdp_mew.webId")).getText() + "\"");
                    String Id[] = Elements.findElement(Elements.element("pdp_mew.webId")).getText().split(":", 0);
                    productId = Id[1].trim();
                }
                break;
            }
            case "productName": {
                break;
            }
            case "priceDetails": {
                break;
            }
        }
    }

    @And("^I save the category & subCategory of the BreadCrumbs on (member|master) PDP (site|iship|registry) mode$")
    public void savingBreadCrumbsLinksOnPDP(String pg, String mode) throws Throwable {
        List<WebElement> breadcrumbsLinks = Elements.findElements(("pdp_mew.breadCrumbs"));
        int cnt = 0;
        for(WebElement el: breadcrumbsLinks) {
            if(cnt == 0)
                category = el.getText();
            else
                subCategory = el.getText();
            cnt++;
            Thread.sleep(1000);
        }
    }

    @And("^I select any random (member|master) product on (search|browse) page$")
    public void selectingAnyRandomProduct(String prodType, String pg) throws Throwable {
        Random rand = new Random();
        Wait.forPageReady();
        Thread.sleep(3000);
        switch(pg) {
            case "browse": {
                String PDPSource = WebDriverManager.getWebDriver().getPageSource();
                softly.assertThat(PDPSource.contains("m-browse-container")).as("Browse Page").isEqualTo(true);
                log.info(" Browse Page Verified!");
                if(prodType.equalsIgnoreCase("member")) {
                    Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                    Thread.sleep(1000);
                    Wait.secondsUntilElementPresent(("pdp_mew.productListBrowsePg"),10);
                    Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
                    List<WebElement> items = Elements.findElements(("pdp_mew.productListBrowsePg"));
                    int randItem = rand.nextInt(items.size());
                    Clicks.click(items.get(randItem));
                    log.info("Clicked: "+randItem);
                }
                break;
            }
            case "search": {
                String PDPSource = WebDriverManager.getWebDriver().getPageSource();
                softly.assertThat(PDPSource.contains("m-search-container")).as("Browse Page").isEqualTo(true);
                log.info(" Search Page Verified!");
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify BreadCrumbs links on (member|master) PDP (site|iship|registry) mode$")
    public void verifingBreadCrumbsLinksOnPDP(String pg, String mode) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent(("pdp_mew.breadCrumbs"),10);
        List<WebElement> breadcrumbsLinks = Elements.findElements(("pdp_mew.breadCrumbs"));
        int cnt = 0;
        for(WebElement el: breadcrumbsLinks) {
            if(cnt == 0) {
                Clicks.click(el);
                Wait.secondsUntilElementPresent(By.cssSelector("#m-catsplash-category-name"),10);
                softly.assertThat(Elements.findElement(By.cssSelector("#m-catsplash-category-name")).getText()
                        .contains(category)).as("Category").isEqualTo(true);
                log.info("Category verified as:  \"" + Elements.findElement(By.cssSelector("#m-catsplash-category-name")).getText() + "\"");
                if(breadcrumbsLinks.size() == 1) {
                    log.info("There is only 1 BreadCrumbs Link displayed on PDP, which verified as:  \"" + Elements.findElement(By.cssSelector("#m-catsplash-category-name")).getText() + "\"");
                    break;
                }
                else
                    TextBoxes.typeTextNEnter("home.search_field", productId);
            }
            else {
                Cookies.deleteAllCookies();
                Navigate.browserRefresh();
                Wait.secondsUntilElementPresent(("pdp_mew.breadCrumbs"),10);
//                Clicks.click(el);
                Clicks.click(Elements.findElement(By.xpath(".//*[@id='m-product-info-column']/div[1]/div[1]/a[2]")));
                Wait.secondsUntilElementPresent(By.cssSelector(".m-browse-catname"),10);
                softly.assertThat(Elements.findElement(By.cssSelector(".m-browse-catname")).getText()
                        .contains(subCategory)).as("SubCategory").isEqualTo(true);
                log.info("SubCategory verified as:  \"" + Elements.findElement(By.cssSelector(".m-browse-catname")).getText() + "\"");
            }
            cnt++;
            Thread.sleep(2000);
        }
    }

    @Then("^I verify the \"([^\"]*)\" and \"([^\"]*)\" of the BreadCrumbs on \"([^\"]*)\" PDP (site|iship|registry) mode$")
    public void verifyingBreadCrumbsLinksOnPDP(String rootCat, String homeCat, String productType, String mode) throws Throwable {
        if(mode.equalsIgnoreCase("iship")) {
            Wait.forPageReady();
            softly.assertThat(Elements.elementPresent(("pdp_mew.iShipLink")));
            softly.assertThat(Elements.findElement(("pdp_mew.iShipLink")).getText().equalsIgnoreCase("Go to U.S. Site"))
                    .as("Go to U.S. Site on PDP").isEqualTo(true);
        }
        else if(mode.equalsIgnoreCase("registry")) {
            Wait.forPageReady();
            String url = WebDriverManager.getCurrentUrl();
            softly.assertThat(url.contains("/registry/wedding/product/")).as("URL:/registry/wedding/product/ on PDP").isEqualTo(true);
        }
        List<WebElement> breadcrumbsLinks = Elements.findElements(("pdp_mew.breadCrumbs"));
        int cnt = 0;
        for(WebElement el: breadcrumbsLinks) {
            if(productType.equalsIgnoreCase("E-Gift Card")) {
                Assert.assertTrue(breadcrumbsLinks.size() == 1);
                softly.assertThat(rootCat.equalsIgnoreCase(el.getText()));
                break;
            }
            if(cnt == 0)
                softly.assertThat(rootCat.equalsIgnoreCase(el.getText()));
            else
                softly.assertThat(homeCat.equalsIgnoreCase(el.getText()));
            cnt++;
            Thread.sleep(1000);
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify navigation of BreadCrumbs \"([^\"]*)\" and \"([^\"]*)\" links on \"([^\"]*)\" PDP (site|iship|registry) mode$")
    public void verifingBreadCrumbsCategorySubCategoryLandingPages(String rootCat, String homeCat, String productType, String mode) throws Throwable {
        Wait.secondsUntilElementPresent(("pdp_mew.breadCrumbs"),10);
        List<WebElement> breadcrumbsLinks = Elements.findElements(("pdp_mew.breadCrumbs"));
        int cnt = 0;
        for(WebElement el: breadcrumbsLinks) {
            if(cnt == 0) {
                Clicks.click(el);
                Wait.secondsUntilElementPresent(By.cssSelector("#m-catsplash-category-name"),10);
                if(mode.equalsIgnoreCase("iship")) {
                    Wait.forPageReady();
                    softly.assertThat(Elements.elementPresent(("pdp_mew.iShipLink")));
                    softly.assertThat(Elements.findElement(("pdp_mew.iShipLink")).getText().equalsIgnoreCase("Go to U.S. Site"))
                            .as("Go to U.S. Site on " + rootCat + ", the rootCategory page").isEqualTo(true);
                }
                else if(mode.equalsIgnoreCase("registry")) {
                    Wait.forPageReady();
                    String url = WebDriverManager.getCurrentUrl();
                    softly.assertThat(url.contains("/wedding-registry/")).as("URL: /wedding-registry/ on " + rootCat + ", the rootCategory page").isEqualTo(true);
                }

                if(productType.equalsIgnoreCase("E-Gift Card")) {
                    Assert.assertTrue(breadcrumbsLinks.size() == 1);
                    softly.assertThat(Elements.findElement(By.cssSelector(".row.m-header.gift-header")).getText()
                            .contains(rootCat)).as("RootCategory").isEqualTo(true);
                    log.info("RootCategory verified as:  \"" + Elements.findElement(By.cssSelector(".row.m-header.gift-header")).getText() + "\" in " + mode + " mode!");
                    break;
                }
                else if(Elements.elementPresent(By.cssSelector(".m-browse-catname"))) {
                    softly.assertThat(Elements.elementPresent(By.cssSelector(".m-browse-catname"))).isEqualTo(true);
                    softly.assertThat(Elements.findElement(By.cssSelector(".m-browse-catname")).getText()
                            .contains("Dinnerware")).as("RootCategory").isEqualTo(true);
                    log.info("RootCategory verified as:  \"" + Elements.findElement(By.cssSelector(".m-browse-catname")).getText() + "\" in " + mode + " mode!");
                }
                else {
                    softly.assertThat(Elements.findElement(By.cssSelector("#m-catsplash-category-name")).getText()
                            .contains(rootCat)).as("RootCategory").isEqualTo(true);
                    log.info("RootCategory verified as:  \"" + Elements.findElement(By.cssSelector("#m-catsplash-category-name")).getText() + "\" in " + mode + " mode!");
                }

                if(breadcrumbsLinks.size() == 1 && homeCat.equalsIgnoreCase("None")) {
                    log.info("There is only \"" + rootCat + "\" BreadCrumbs Link displayed on PDP!");
                    break;
                }
                else
                    TextBoxes.typeTextNEnter("home.search_field", productId);
            }
            else {
                if(!(mode.equalsIgnoreCase("iship")))
                    Cookies.deleteAllCookies();
                Navigate.browserRefresh();
                Wait.secondsUntilElementPresent(("pdp_mew.breadCrumbs"),10);
//                Clicks.click(el);
                Clicks.click(Elements.findElement(By.xpath(".//*[@id='m-product-info-column']/div[1]/div[1]/a[2]")));
                Wait.secondsUntilElementPresent(By.cssSelector(".m-browse-catname"),10);
                if(mode.equalsIgnoreCase("iship")) {
                    Wait.forPageReady();
                    softly.assertThat(Elements.elementPresent(("pdp_mew.iShipLink")));
                    softly.assertThat(Elements.findElement(("pdp_mew.iShipLink")).getText().equalsIgnoreCase("Go to U.S. Site"))
                            .as("Go to U.S. Site on " + homeCat + ", the homeCategory page").isEqualTo(true);
                }
                else if(mode.equalsIgnoreCase("registry")) {
                    Wait.forPageReady();
                    String url = WebDriverManager.getCurrentUrl();
                    softly.assertThat(url.contains("/wedding-registry/")).as("URL: /wedding-registry/ on " + homeCat + ", the homeCategory page").isEqualTo(true);
                }
                softly.assertThat(Elements.findElement(By.cssSelector(".m-browse-catname")).getText()
                        .contains(homeCat)).as("HomeCategory").isEqualTo(true);
                log.info("HomeCategory verified as:  \"" + Elements.findElement(By.cssSelector(".m-browse-catname")).getText() + "\" in " + mode + " mode!");
            }
            cnt++;
            Thread.sleep(1000);
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I click on search go button at search field$")
    public void I_click_on_GoButton() throws Throwable {
        Wait.untilElementPresent("home.go");
        Clicks.click("home.go");
        log.info("Verified that Search go button is clicked");
    }

    @When("^I search for \"([^\"]*)\" global Search Input Field$")
    public void I_search_for_global_Search_Input_Field(String search_str) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp_mew.header_search_field", 20);
        Elements.findElement(Elements.element("pdp_mew.header_search_field")).sendKeys(search_str);
        Clicks.click(Elements.element("pdp_mew.header_search_btn"));
    }

    @When("^I navigate to member PDP page that has multiple color/size combination$")
    public void I_navigate_to_member_PDP_page_that_has_multiple_color_size_combination() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp_mew.search_result_list", 20);
        List<WebElement> searchProductList = Elements.findElements(Elements.element("pdp_mew.search_result_list"));
        System.out.println("---------------Storing product WEB ID");
        webId = searchProductList.get(0).getAttribute("data-product_id");
        Elements.findElement(By.cssSelector("li[data-product_id='"+webId+"']>a>div.mb-j-search-image")).click();
    }

    @When("^I should see the write a review button and I click on it$")
    public void I_should_see_the_write_a_review_button_and_click_on_it() throws Throwable {
        Wait.secondsUntilElementPresent("pdp_mew.pdp_write_review_btn", 20);
        Elements.findElement(By.cssSelector("a[href*='myReviews']")).click();
    }

    @When("^I write a review on the review modal for the product$")
    public void I_write_a_review_on_the_review_modal_for_the_product() throws Throwable {
        Wait.secondsUntilElementPresent("pdp_mew.write_review_star", 20);
        Elements.findElement(By.cssSelector("div#mb-write-review-rating-" + webId)).click();
        Elements.findElement(By.cssSelector("input#reviewHeadline-" + webId)).sendKeys("This is test review title");
        Elements.findElement(By.cssSelector("textarea#myReviewsText-" + webId)).sendKeys("This is test review body This is test review body This is test review body This is test review body");
        Elements.findElement(By.cssSelector("input#myReviewsNickname-" + webId)).sendKeys("Reviewer");
        Elements.findElement(By.cssSelector("input#isrecommended-yes-" + webId)).click();
        Elements.findElement(By.xpath("//input[@id='reviewHeadline-" + webId + "']/../..//button")).click();


      // div.toggleHideSubmitted.showOneTitle
       // Thanks for your review of Levi's® 511 Slim Fit Jeans
       // write_review_confirm


    }

    @Then("^the write review submission is complete$")
    public void the_write_review_submission_is_complete() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp_mew.write_review_confirm", 20);

        String confirm = Elements.findElement(("pdp_mew.write_review_confirm")).getText();
        String expected = "Thanks for your review of";
        String expected2 = "Please note that your review may take up to 48 hours to appear.";
        softly.assertThat(confirm.contains(expected));
        softly.assertThat(confirm.contains(expected2));

        softly.assertAll();
        log.info("Verified that the rating and reviews are present on PDP");
    }

    @Then("^I should see rating and reviews on pdp$")
    public void I_should_see_ratings_and_reviews_on_pdp() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp_mew.rating_pdp", 20);
        Wait.secondsUntilElementPresent("pdp_mew.rating_count", 20);

        softly.assertThat(Elements.elementPresent("pdp_mew.rating_pdp"));
        softly.assertThat(Elements.elementPresent("pdp_mew.rating_count"));

        softly.assertAll();
        log.info("Verified that the rating and reviews are present on PDP");
    }

    @Then("^I click on reviews number next to stars$")
    public void I_click_on_reviews_number_next_to_stars() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp_mew.rating_count", 20);
        Clicks.click("pdp_mew.rating_count");
        Wait.secondsUntilElementPresent("pdp_mew.review_articles", 40);
    }

    @Then("^I verify that reviews are displayed$")
    public void I_verify_that_reviews_are_displayed() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp_mew.review_articles", 40);
        softly.assertThat(Elements.elementPresent("pdp_mew.review_articles"));
        String article = Elements.findElement("pdp_mew.review_articles").getText();
        softly.assertThat(!article.isEmpty());
        softly.assertAll();
        log.info("Verified that reviews articles are present and not empty");
    }

    @Then("^verify media player$")
    public void verify_media_player() throws Throwable {
//refresh page
        Wait.forPageReady();
        WebDriverManager.getWebDriver().navigate().refresh();
        Wait.forPageReady();
//click on Watch Video link
        Wait.secondsUntilElementPresent("pdp_mew.media_player_link", 40);
        Clicks.click("pdp_mew.media_player_link");
//click on Play button
        Wait.secondsUntilElementPresent("pdp_mew.media_player_play", 40);
        Clicks.click("pdp_mew.media_player_play");
//verify video property
        Wait.secondsUntilElementPresent("pdp_mew.media_track_display", 40);
        softly.assertThat(Elements.elementPresent("pdp_mew.media_track_display"));
//let video play for 5 seconds
        Thread.sleep(5000);
//pause the video
        Navigate.execJavascript("document.querySelector('video').pause()");
//get current_time from player
        String current_time = (String) Navigate.execJavascript("return document.querySelector('div.vjs-current-time-display').textContent");
        int result = Integer.parseInt(current_time.split(":")[1]);
//assert current_time more then 0
        softly.assertThat(result > 0);

    }


}
