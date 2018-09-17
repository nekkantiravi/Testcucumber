package com.macys.sdt.projects.Selection.NavAppComp.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

import static com.macys.sdt.framework.interactions.Navigate.visit;
import static com.macys.sdt.framework.interactions.TextBoxes.typeTextbox;
import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile.typeTextBoxIfPresent;

public class PDPStepDefinitions extends StepUtils {

    //private static final RestTemplate restTemplate = new RestTemplate();
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    public static String productId = "";
    private String productQuantity = "";
    private String newListName = "NavAppTestList";
    private String email = "";
    SoftAssertions softly = new SoftAssertions();
    String title = "";
    String amount = "";

    public String get_review_number() throws Throwable {
        Wait.forPageReady();
        Clicks.hoverForSelection(Elements.element("pdp.review_section"));
        Wait.secondsUntilElementPresent("pdp.review_count", 20);
        return Elements.findElement(Elements.element("pdp.review_count")).getText();
    }

    private void navigatetoPDPCompUrl() throws Throwable {
        Wait.forPageReady();
        String url = WebDriverManager.getCurrentUrl();
        int indx = url.indexOf("?");
        StringBuilder pdpcompUrl = new StringBuilder(url);
        pdpcompUrl = pdpcompUrl.insert(indx+1, "targetroute=pdpcomp&");
        url = pdpcompUrl.toString();
        Navigate.visit(url);
        Wait.forPageReady();
        boolean waitfornavappPage = false;
        boolean navappPageFound = false;
        int i = 0;
        do {
           logger.warning(String.format("looking for ... <appserver>web.1"));
           waitfornavappPage = WebDriverManager.getWebDriver().getPageSource().contains("<appserver>web.1");
           if(waitfornavappPage) {
             navappPageFound = true;
           }
           i = i + 1;
           if (i > 40) {
               Thread.sleep(2000);
             waitfornavappPage = true;
           }
        }while(!waitfornavappPage);
        if(!navappPageFound) {
            logger.warning(String.format("Navapp Page not found"));
        }
        softly.assertThat(WebDriverManager.getWebDriver().getPageSource().contains("<appserver>web.1")).as("Componentized PDP page loading is").isEqualTo(true);
        softly.assertAll();
    }

    private void navigatetoColorwayPriceProduct() throws Throwable {
        List<WebElement> thumbnails = new ArrayList<>();
        thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));

        List<WebElement> masterSpanElement = new ArrayList<>();
        List<WebElement> memberProductThumbnail = new ArrayList<>();

        for(WebElement thumbnail : thumbnails) {
            masterSpanElement = thumbnail.findElements(By.xpath(".//div[@class='innerWrapper']/div[@class='fullColorOverlayOff']/a/span[contains(@id,'_isMasterProduct')]"));
            if(!(masterSpanElement.size() > 0)) {
                memberProductThumbnail.add(thumbnail);
            }
        }
        int idx = new Random().nextInt(memberProductThumbnail.size());
        if(memberProductThumbnail.size() < 1) {
            logger.warning(String.format("Master product not found"));
        }
        Clicks.click(memberProductThumbnail.get(idx).findElement(By.xpath(".//div[@class='innerWrapper']/div[@class='textWrapper']/div[1]")));
        Wait.forPageReady();
        Thread.sleep(2000);
    }

    @Given("^I look at product with reviews$")
    public void I_look_at_product_with_reviews() throws Throwable {
        productId = "2380773";
        CommonUtils.navigateDirectlyToProduct(productId);
    }


    @Given("^I look at Mattress product with pinterest$")
    public void I_look_at_Floral_Print_Scuba_Fit_with_pinterest() throws Throwable {
        productId = "2682608";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
        //forPageReady();
        Wait.secondsUntilElementPresent("pdp.productDescription", 20);
        title = Elements.findElement(Elements.element("pdp.productDescription")).getText();

    }

    @Given("^I Navigate to a chanel product pdp page$")
    public void I_Navigate_to_a_chanel_product_pdp_page() throws Throwable {
        productId = "75909";
        CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Then("^I should see choose your items button$")
    public void I_should_see_choose_your_items_button() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(Elements.elementPresent("pdp.chanel_choose_your_items")).as("Chanel Choose your items").isEqualTo(true);
        softly.assertAll();
    }

    @Then("^I should not see product details special offers and shipping returns information$")
    public void I_should_not_see_product_details_special_offers_and_shipping_returns_information() throws Throwable {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(Elements.elementPresent("pdp.product_details_header")).as("Product details header").isEqualTo(false);
        softly.assertThat(Elements.elementPresent("pdp.product_shipping_returns_header")).as("Product shipping returns header").isEqualTo(false);
        softly.assertThat(Elements.elementPresent("pdp.specialOffersHeader")).as("Product special offers header").isEqualTo(false);

        softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("Product add to list").isEqualTo(false);
        softly.assertThat(Elements.elementPresent("pdp.orderByPhone")).as("Order by phone").isEqualTo(false);
        softly.assertThat(Elements.elementPresent("pdp.specialOffersTitle")).as("Special Offers title").isEqualTo(false);
        softly.assertThat(Elements.elementPresent("pdp.ProductDetailsWebId")).as("Web ID Under product details").isEqualTo(false);

        softly.assertAll();
    }

    @When("^I verify pinterest popup url$")
    public void I_login_with_an_existing_pinterest_account() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.pinterest_icon", 30);
        String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
        Clicks.hoverForSelection(Elements.element("pdp.pinterest_icon"));
        Clicks.javascriptClick(Elements.element("pdp.pinterest_icon"));
        Thread.sleep(4000);
        Set<String> handles =  WebDriverManager.getWebDriver().getWindowHandles();
        for(String windowHandle  : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to child window
                String url = WebDriverManager.getCurrentUrl();
                System.out.println("URL: " + url);
                softly.assertThat(url.contains("www.pinterest.com")).as("pin url").isEqualTo(true);
                System.out.println("INSIDE popup window");
                softly.assertAll();

            }
        }

        softly.assertAll();

    }

    @Then("^I should see pick a board pop up with product description$")
    public void I_should_see_pick_a_board_pop_up_panel() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.secondsUntilElementPresent("pdp.pinterest_pickaBoard", 40);
        softly.assertThat(Elements.elementPresent("pdp.pinterest_pickaBoard")).as("pinterest pick a board pop up").isEqualTo(true);
        String pinDescription = Elements.findElement(Elements.element("pdp.pinterest_description")).getText();
        softly.assertThat(pinDescription.contains(title)).as("pin description").isEqualTo(true);
        softly.assertAll();
        //MainRunner.getWebDriver().close(); //closing child window
    }


    @Then("^I verify common product info$")
    public void I_verify_common_product_info() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.productDescription", 40);
        String productDescription = Elements.findElement(Elements.element("pdp.productDescription")).getText();
        String webId = Elements.findElement(Elements.element("pdp.web_id")).getText();
        softly.assertThat(productDescription.isEmpty()).as("productDescription").isEqualTo(false);
        Clicks.hoverForSelection(Elements.element("pdp.web_id"));
        //softly.assertThat(webId.contains(productId)).as("productId").isEqualTo(true);
        if(Elements.elementPresent("pdp.orderByPhone")){
            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(false);
        }else{
            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
        }
        softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("add_to_list button").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("add_to_list product_main_image").isEqualTo(true);

        //BELOW Will verify that the page is served from Componentized PDP page
        String compPDPPageInfo = WebDriverManager.getWebDriver().getPageSource();
        softly.assertThat(compPDPPageInfo.contains("<site>heroku</site>")).as("Page Source").isEqualTo(true);

        //BELOW Will verify SEO Header flyout is present or not
        softly.assertThat(compPDPPageInfo.contains("flexLabelLinksContainer")).as("SEO flex lable links").isEqualTo(true);

        softly.assertAll();
    }



    @Given("^I verify reviews for iPhone6 Wristlet product$")
    public void I_verify_reviews_for_iPhone6_Wristlet_product() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();

        //String reviews = get_review_number();
        //softly.assertThat(Integer.parseInt(reviews)== 4).as("review_count").isEqualTo(true);

        String promo_text = Elements.findElement(Elements.element("pdp.review_promo_text")).getText();
        softly.assertThat(promo_text).as("promo_text").isEqualTo("Write a review for a chance to win a $1000 gift card!");
        softly.assertThat(Elements.elementPresent("pdp.review_promo_link")).as("review_promo_link").isEqualTo(true);

        String review_count_percent = Elements.findElement(Elements.element("pdp.review_count_percent")).getText();
        softly.assertThat(review_count_percent).as("review_count_percent").isEqualTo("of reviewers recommend this product.");

        softly.assertAll();
    }


    @Given("^I verify sort ascent reviews for iPhone6 Wristlet product$")
    public void I_verify_sort_ascent_reviews_for_iPhone6_Wristlet_product() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Clicks.hoverForSelection(Elements.element("pdp.review_section"));
        Wait.secondsUntilElementPresent("pdp.sort_review_list", 20);
        Clicks.hoverForSelection(Elements.element("pdp.sort_review_list"));
        WebElement s = Elements.findElement(Elements.element("pdp.sort_review_list"));
        //Select Newest
        Select dropdown = new Select(s);

        dropdown.selectByVisibleText("Oldest");
        Thread.sleep(2000);
        int number_of_reviews = Elements.findElements(Elements.element("pdp.review_date")).size();
        //convert to Date type
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date_first = format.parse(Elements.findElements(Elements.element("pdp.review_date")).get(0).getText());
        Date date_last = format.parse(Elements.findElements(Elements.element("pdp.review_date")).get(number_of_reviews - 1).getText());

        softly.assertThat(date_first.before(date_last)).as("review_date").isEqualTo(true);
        softly.assertAll();
    }


    @Given("^I verify sort desc reviews for iPhone6 Wristlet product$")
    public void I_verify_sort_desc_reviews_for_iPhone6_Wristlet_product() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Clicks.hoverForSelection(Elements.element("pdp.review_section"));
        Wait.secondsUntilElementPresent("pdp.sort_review_list", 20);
        Clicks.hoverForSelection(Elements.element("pdp.sort_review_list"));
        WebElement s = Elements.findElement(Elements.element("pdp.sort_review_list"));
        //Select Newest
        Select dropdown = new Select(s);
        dropdown.selectByVisibleText("Newest");
        Thread.sleep(2000);
        int number_of_reviews = Elements.findElements(Elements.element("pdp.review_date")).size();
        //convert to Date type
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date_first = format.parse(Elements.findElements(Elements.element("pdp.review_date")).get(0).getText());
        Date date_last = format.parse(Elements.findElements(Elements.element("pdp.review_date")).get(number_of_reviews-1).getText());

        softly.assertThat(date_first.after(date_last)).as("review_date").isEqualTo(true);
        softly.assertAll();
    }


    @Given("^I should see Reviews on the page$")
    public void I_should_see_Reviews_on_the_page() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();

        String reviews = get_review_number();
        softly.assertThat(Integer.parseInt(reviews)> 3).as("review_count").isEqualTo(true);
        Clicks.hoverForSelection(Elements.element("pdp.write_a_review_btn"));
        Wait.secondsUntilElementPresent("pdp.review_overall_title", 10);
        softly.assertThat(Elements.elementPresent("pdp.review_overall_title")).as("review_overall_title").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.review_poll_bar_1_star")).as("review_poll_bar_1_star").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.review_poll_bar_2_star")).as("review_poll_bar_2_star").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.review_poll_bar_3_star")).as("review_poll_bar_3_star").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.review_poll_bar_4_star")).as("review_poll_bar_4_star").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.review_poll_bar_5_star")).as("review_poll_bar_5_star").isEqualTo(true);

        String promo_text = Elements.findElement(Elements.element("pdp.review_promo_text")).getText();
        softly.assertThat(promo_text).as("promo_text").isEqualTo("Write a review for a chance to win a $1000 gift card!");
        softly.assertThat(Elements.elementPresent("pdp.review_promo_link")).as("review_promo_link").isEqualTo(true);

        String review_count_percent = Elements.findElement(Elements.element("pdp.review_count_percent")).getText();
        softly.assertThat(review_count_percent).as("review_count_percent").isEqualTo("of reviewers recommend this product.");

        softly.assertThat(Elements.elementPresent("pdp.RatingOverallSizeSlider")).as("RatingOverallSizeSlider").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.RatingLengthSlider")).as("RatingLengthSlider").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.RatingCoverageSlider")).as("RatingCoverageSlider").isEqualTo(true);
        softly.assertAll();
    }

    @Given("^I should see below title text Of Review$")
    public void I_should_see_below_title_text_Of_Review() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Clicks.hoverForSelection(Elements.element("pdp.customer_reviews"));

        List<WebElement> customerReviews_star = Elements.findElements(Elements.element("pdp.customer_rating_star_img"));
        softly.assertThat(customerReviews_star.size() > 0).as("customerReviews_star").isEqualTo(true);

        List<WebElement> customerReviews_nickname = Elements.findElements(Elements.element("pdp.customer_rating_nickname"));
        for(WebElement item : customerReviews_nickname){
            softly.assertThat(item.getText().isEmpty()).as("customerReviews_nickname").isEqualTo(false);
        }

        List<WebElement> customerReviews_text = Elements.findElements(Elements.element("pdp.customer_rating_review_text"));
        for(WebElement item : customerReviews_text){
            softly.assertThat(item.getText().isEmpty()).as("customerReviews_text").isEqualTo(false);
        }

        List<WebElement> customerReviews_title = Elements.findElements(Elements.element("pdp.customer_rating_review_title"));
        for(WebElement item : customerReviews_title){
            softly.assertThat(item.getText().isEmpty()).as("customerReviews_title").isEqualTo(false);
        }
        softly.assertAll();
    }

    @Then("^I verify links on PDP page$")
    public void I_verify_links_on_PDP_page() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();

        Clicks.hoverForSelection(Elements.element("pdp.review_section"));
        Wait.secondsUntilElementPresent("pdp.write_a_review_btn", 10);

        Clicks.click(Elements.element("pdp.RelevancySort_link"));
        //add wait
        softly.assertThat(Elements.elementPresent("pdp.RelevancySort_close_btn")).as("RelevancySort_close_btn").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.RelevancySort_text")).as("RelevancySort_text").isEqualTo(true);
        softly.assertAll();

        String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
        Clicks.click(Elements.element("pdp.review_promo_link"));
        Set<String> handles =  WebDriverManager.getWebDriver().getWindowHandles();
        for(String windowHandle  : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to child window
                Wait.secondsUntilElementPresent("pdp.review_promo_page_img", 20);
                softly.assertThat(Elements.elementPresent("pdp.review_promo_page_img")).as("review_promo_page_img").isEqualTo(true);
                WebDriverManager.getWebDriver().close(); //closing child window
                WebDriverManager.getWebDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        softly.assertAll();
    }

    @Then("^I verify Quantity drop down box$")
    public void I_verify_Quantity_drop_down_box() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.select_max_quantity", 40);
        softly.assertThat(Elements.elementPresent("pdp.select_max_quantity")).as("Select Max Quantity dropdown").isEqualTo(true);
        softly.assertAll();
    }

    @Then("^I should see flyout are SEO Header enabled$")
    public void I_should_see_flyout_are_SEO_Header_enabled() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.seo_enabled_flyout", 40);
        softly.assertThat(Elements.elementPresent("pdp.seo_enabled_flyout")).as("SEO Enabled flyout").isEqualTo(true);

        List<WebElement> flexLabelLinks = Elements.findElements(Elements.element("pdp.flexLabelLinksContainer"));
        List<WebElement> navPills = Elements.findElements(Elements.element("pdp.navPills"));
        softly.assertThat(navPills.size() == 1).as("UL NavPills available once").isEqualTo(true);
        softly.assertThat(flexLabelLinks.size() > 0).as("SEO flex lable links").isEqualTo(true);

        softly.assertAll();
    }

    @Then("^I should see Header flyout from FOB$")
    public void I_should_see_Header_flyout_from_FOB() throws Throwable {
        WebElement hoverFOB = null;
        WebElement hoverLogo = Elements.findElement("header.logo");
        Clicks.hoverForSelection(hoverLogo);
        try {
            Wait.forPageReady();
            hoverFOB = Elements.findElement("pdp.women_FOB");
        } catch (Exception e) {
            System.out.println("Unable to hover on quick bag header");
            e.printStackTrace();
        }
        SoftAssertions softly = new SoftAssertions();
        Clicks.hoverForSelection(hoverFOB);
        Wait.secondsUntilElementPresent("pdp.women_activewear", 40);
        WebElement fob_link = Elements.findElement(Elements.element("pdp.women_activewear"));
        softly.assertThat(fob_link.isDisplayed()).as("fob isDisplayed").isEqualTo(true);
        Clicks.click(Elements.element("pdp.women_activewear"));
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.women_FOB_selected", 40);
        softly.assertThat(Elements.elementPresent("pdp.women_FOB_selected")).as("Header flyout").isEqualTo(true);
        String selectedFOB = Elements.findElement(Elements.element("pdp.women_FOB_selected")).getText();
        softly.assertThat(selectedFOB).as("FOB Selected").isEqualToIgnoringCase("WOMEN");

        softly.assertAll();

    }

    @When("^I select random value from the Quantity list$")
    public void I_select_random_value_from_the_Quantity_list() throws Throwable {
        productQuantity = DropDowns.selectRandomValue(Elements.element("pdp.select_max_quantity"));
    }

    @Then("^I verify product Quantity gets updated$")
    public void I_verify_product_Quantity_gets_updated() throws Throwable {
        String pageQuantity = DropDowns.getSelectedValue(Elements.element("pdp.select_max_quantity"));
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(pageQuantity).as("Select Max Quantity dropdown").isEqualTo(productQuantity);
        softly.assertAll();
    }

    @Given("^I look at iPhone6 Wristlet product$")
    public void I_look_at_iPhone6_Wristlet_product() throws Throwable {
        productId = "711123";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Given("^I look at Pendant Necklace product$")
    public void I_look_at_Pendant_Necklace_product() throws Throwable {
        productId = "792565";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Given("^I look at colorway Dress product$")
    public void I_look_at_colorway_Dress_product() throws Throwable {
        productId = "2643306";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }


    @Then("^I verify write a review button$")
    public void I_verify_write_a_review_button() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Clicks.hoverForSelection(Elements.element("pdp.review_section"));
        Wait.secondsUntilElementPresent("pdp.sort_review_list", 20);
        Clicks.click(Elements.element("pdp.write_a_review_btn"));
        Wait.secondsUntilElementPresent("pdp.sign_in_button", 20);
        softly.assertThat(Elements.elementPresent("pdp.sign_in_button")).as("sign_in_button").isEqualTo(true);

        softly.assertAll();

    }

    @Then("^I verify Buy Online Pickup In Store$")
    public void I_verify_Buy_Online_Pickup_In_Store() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        List<WebElement> product_sizes = Elements.findElements(Elements.element("pdp.sizes_array"));
        if (Elements.elementPresent("pdp.sizes_array")) {
            Clicks.click(product_sizes.get(0));
        }

        Clicks.hoverForSelection(Elements.element("pdp.availability_title"));
        softly.assertThat(Elements.elementPresent("pdp.availability_title")).as("availability_title").isEqualTo(true);
        String availability_instock_text = Elements.findElement(Elements.element("pdp.availability_title")).getText();
        softly.assertThat(availability_instock_text).as("availability_instock_text").isEqualTo("in stock");
        String pickup_in_store_text = Elements.findElement(Elements.element("pdp.pickup_in_store_text")).getText();
        softly.assertThat(pickup_in_store_text).as("pickup_in_store_text").isEqualTo("Pick up in store");
        Wait.secondsUntilElementPresent("pdp.pickup_in_store_link", 10);
        if(Elements.elementPresent("pdp.pickup_in_store_link")) {
            Clicks.click(Elements.element("pdp.pickup_in_store_link"));
        }else{
            Clicks.click("pdp.pickup_in_other_store_link");
        }
        Wait.secondsUntilElementPresent("pdp.availability_check_zip", 10);
        softly.assertThat(Elements.elementPresent("pdp.availability_check_zip")).as("availability_check_zip").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.availability_check_search_btn")).as("availability_check_search_btn").isEqualTo(true);

        softly.assertAll();
        Clicks.click(Elements.element("pdp.availability_check_close_btn"));
    }

    @Then("^I verify price is present$")
    public void I_verify_price_is_present() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        if(Elements.elementPresent("pdp.singlePrice")) {
            String item = Elements.findElement(Elements.element("pdp.singlePrice")).getText();
            softly.assertThat(item.isEmpty()).as("singlePrice").isEqualTo(false);
            softly.assertThat(item.contains("$")).as("singlePrice").isEqualTo(true);
        }
        if(Elements.elementPresent("pdp.salePrice")) {
            String item = Elements.findElement(Elements.element("pdp.singlePrice")).getText();
            softly.assertThat(item.isEmpty()).as("salePrice").isEqualTo(false);
            softly.assertThat(item.contains("$")).as("singlePrice").isEqualTo(true);
        }



        softly.assertAll();
    }


    @Then("^I verify Add to List functionality$")
    public void I_verify_Add_to_List_functionality() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Clicks.hoverForSelection(Elements.element("pdp.add_to_list"));
        Wait.secondsUntilElementPresent("pdp.add_to_list", 10);
        Clicks.click(Elements.element("pdp.add_to_list"));
        Wait.secondsUntilElementPresent("pdp.add_to_list_signin_poppup", 10);
        softly.assertThat(Elements.elementPresent("pdp.add_to_list_signin_poppup")).as("add_to_list_signin_poppup").isEqualTo(true);
        Clicks.click(Elements.element("pdp.add_to_list_signin_poppup"));
        Wait.secondsUntilElementPresent("pdp.sign_in_button", 20);
        softly.assertThat(Elements.elementPresent("pdp.sign_in_button")).as("sign_in_button").isEqualTo(true);
        softly.assertAll();

        Navigate.browserBack();
    }

    @Then("^I verify Add to Bag functionality$")
    public void I_verify_Add_to_Bag_functionality() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.secondsUntilElementPresent("pdp.memberProductTitle", 30);
        String pdpDesc = Elements.findElement(Elements.element("pdp.memberProductTitle")).getText().replace("\n"," ");
        Clicks.hoverForSelection(Elements.element("pdp.add_to_bag"));
        Wait.secondsUntilElementPresent("pdp.add_to_bag", 5);
        List<WebElement> product_sizes = Elements.findElements(Elements.element("pdp.sizes_array"));
        if (Elements.elementPresent("pdp.sizes_array")) {
            Clicks.click(product_sizes.get(0));
        }
        Clicks.click(Elements.element("pdp.add_to_bag"));
        Wait.secondsUntilElementPresent("pdp.item_in_bag_link", 5);
        softly.assertThat(Elements.elementPresent("pdp.item_in_bag_link")).as("item_in_bag_link").isEqualTo(true);
        Wait.secondsUntilElementPresent("pdp.bag_product_description", 5);
        String item = Elements.findElement(Elements.element("pdp.bag_product_description")).getText();
        softly.assertThat(pdpDesc.toLowerCase().contains(item.toLowerCase())).as("bag_product_description").isEqualTo(true);
        softly.assertAll();
        Navigate.browserBack();

    }

    @Then("^I verify pinterest link$")
    public void I_verify_pinterest_link() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Clicks.hoverForSelection(Elements.element("pdp.pinterest_icon"));

        String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
        Clicks.click(Elements.element("pdp.pinterest_icon"));
        Set<String> handles =  WebDriverManager.getWebDriver().getWindowHandles();
        for(String windowHandle  : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to child window
                Wait.secondsUntilElementPresent("pdp.pinterest_login_popup", 20);
                softly.assertThat(Elements.elementPresent("pdp.pinterest_login_popup")).as("pinterest_login_popup").isEqualTo(true);
                WebDriverManager.getWebDriver().close(); //closing child window
                WebDriverManager.getWebDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        softly.assertAll();

    }
    
    @Then("^I verify Add to List functionality with registered user$")
    public void I_verify_Add_to_List_functionality_with_registered_user() throws Throwable {
        SoftAssertions softly = new SoftAssertions();

        Clicks.click(Elements.element("pdp.add_to_list"));
        Wait.secondsUntilElementPresent("pdp.added_to_list_msg", 40);
        WebElement list_msg = Elements.findElement(Elements.element("pdp.added_to_list_msg"));

        softly.assertThat(Elements.elementPresent("pdp.added_to_list_msg")).as("added_to_list_msg").isEqualTo(true);
        softly.assertThat(list_msg.isDisplayed()).as("added_to_list_msg_Displayed").isEqualTo(true);
        softly.assertAll();
    }


    @Then("^I verify Add to Bag functionality with registered user$")
    public void I_verify_Add_to_Bag_functionality_with_registered_user() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Clicks.click(Elements.element("pdp.add_to_bag"));
        Wait.secondsUntilElementPresent("addToBag.added_to_bag_confirm_msg", 40);
        String added_to_bag_msg = Elements.findElement(Elements.element("addToBag.added_to_bag_confirm_msg")).getText();
        String str_to_verify = "1 Item Added To Your Bag";
        softly.assertThat(added_to_bag_msg).as("cancelPriceAlert").isEqualToIgnoringCase(str_to_verify);
        softly.assertAll();
    }

    @Then ("^I verify price for colorway swatches \"([^\"]*)\"$")
    public void I_verify_price_for_colorway_swatches(String color) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        String tiered_price_css = "[aria-label='" + color + "']";
        Wait.secondsUntilElementPresent("pdp.colorway_tiered_price", 20);
        Clicks.click(Elements.findElement(By.cssSelector(tiered_price_css)));
        String colorway_xpath = "//li[@data-title='" + color + "']/../../../preceding-sibling::div[1]/div[contains(@class,'colorwayPrice')]";
        Wait.secondsUntilElementPresent(By.xpath(colorway_xpath), 5);
        //if price is different based on color
        if (Elements.findElements(By.xpath(colorway_xpath)).size() != 0) {
            String colorway_price = Elements.findElement(By.xpath(colorway_xpath)).getText();
            Thread.sleep(1000);
            String tiered_price = Elements.findElement(Elements.element("pdp.colorway_tiered_price")).getText();
            tiered_price = tiered_price.replace("Now", "").replace("Sale", "").replaceAll("\\s+", "");
            softly.assertThat(colorway_price).as("colorway_price").isEqualTo(tiered_price);
            softly.assertThat(colorway_price.contains("$")).as("colorway_price_contains_$").isEqualTo(true);
            softly.assertThat(tiered_price.contains("$")).as("tiered_price_contains_$").isEqualTo(true);
            //verify that price is digit
            softly.assertThat(tiered_price.replace("$", "").replace(".", "").matches("[0-9]+")).as("tiered_price_digit").isEqualTo(true);
            softly.assertThat(colorway_price.replace("$", "").replace(".", "").matches("[0-9]+")).as("tiered_price_digit").isEqualTo(true);
        }else{
            Boolean productColor = Elements.findElement(By.cssSelector(tiered_price_css)).isDisplayed();
            String productColorText = Elements.findElement(Elements.element("pdp.productSelectedColor")).getText();
            softly.assertThat(productColor).as("productColor").isEqualTo(true);
            softly.assertThat(productColorText).as("productColortext").isEqualTo(color);
        }
        softly.assertAll();
    }


    @Then("^I verify sizes section$")
    public void I_verify_sizes_section() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        softly.assertThat(Elements.elementPresent("pdp.product_size")|| Elements.elementPresent("pdp.product_size2")).as("product_size").isEqualTo(true);

        List<WebElement> product_sizes = Elements.findElements(Elements.element("pdp.sizes_array"));
        for(WebElement item : product_sizes){
            softly.assertThat(item.getText().isEmpty()).as("is_size_not_empty?").isEqualTo(false);
        }

        softly.assertAll();
    }

    @Then("^I verify email a friend icon")
    public void I_verify_email_a_friend_icon() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email a friend").isEqualTo(true);

        Clicks.click(Elements.element("pdp.email_icon"));
        Wait.secondsUntilElementPresent("pdp.email_captchaImage", 30);
        softly.assertThat(Elements.elementPresent("pdp.email_captchaImage")).as("Email Captcha image").isEqualTo(true);
        softly.assertAll();
        Clicks.click(Elements.element("pdp.email_closeicon"));
    }


    @Then("^I verify original price \"([^\"]*)\"$")
    public void I_verify_original_price(String original_price) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        String tiered_price = Elements.findElement(Elements.element("pdp.colorway_tiered_price")).getText();
        if (tiered_price.startsWith("$")) {
            softly.assertThat(Elements.elementPresent("pdp.colorway_original")).as("colorway_original").isEqualTo(false);
        } else{
            softly.assertThat(Elements.elementPresent("pdp.colorway_original")).as("colorway_original").isEqualTo(true);
            String item = Elements.findElement(Elements.element("pdp.colorway_original")).getText();
            softly.assertThat(original_price.isEmpty()).as("colorway_original").isEqualTo(false);
            softly.assertThat(item.contains(original_price)).as("colorway_original").isEqualTo(true);
        }

        softly.assertAll();
    }

    @Then("^I verify List functionality with registered user$")
    public void I_verify_List_functionality_with_registered_user() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        String randomNumber = getRandomNumber();
        String productDescription = Elements.findElement(Elements.element("pdp.productDescription")).getText();
        Wait.secondsUntilElementPresent(Elements.element("pdp.singlePrice"), 20);
        String singlePrice = Elements.findElement(Elements.element("pdp.singlePrice")).getText();
        List<WebElement> product_sizes = Elements.findElements(Elements.element("pdp.sizes_array"));
        if (Elements.elementPresent("pdp.sizes_array")) {
            Clicks.click(product_sizes.get(0));
        }

        createTestList(newListName + randomNumber);
        String listNameLink = "//a[contains(text(),'"+ newListName+randomNumber +"')]";
        Wait.secondsUntilElementPresent(By.xpath(listNameLink), 10);
        Clicks.click(Elements.findElement(By.xpath(listNameLink)));
        Wait.secondsUntilElementPresent(Elements.element("addToList.productNameLink"), 10);
        String productDescriptionList = Elements.findElement(Elements.element("addToList.productNameLink")).getText();

        String singlePriceList = "";
        if (Elements.elementPresent("addToList.productPriceNow")) {
            singlePriceList = Elements.findElement(Elements.element("addToList.productPriceNow")).getText();
        }else{
            singlePriceList = Elements.findElement(Elements.element("addToList.productPrice")).getText();
        }

        softly.assertThat(productDescriptionList.contains(productDescription)).as("productDescription").isEqualTo(true);
        softly.assertThat(singlePrice).as("singlePrice").isEqualTo(singlePriceList);
        deleteTestList(newListName);
        softly.assertAll();
    }

    @Then("^I should see Product Details and Shipping & Returns sections$")
    public void I_should_see_Product_Details_and_Shipping_Returns_sections() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        String url = WebDriverManager.getCurrentUrl();
        String webId = "";
        webId = url.split("ID=")[1].split("&")[0];
        String webIDBulletText = Elements.findElement(Elements.element("pdp.web_id_bulletText")).getText();
        Wait.secondsUntilElementPresent(Elements.element("pdp.product_details_header"), 40);
        softly.assertThat(Elements.elementPresent("pdp.product_details_header")).as("Product Details Header").isEqualTo(true);
        softly.assertThat(webIDBulletText).as("WEB ID Bullet Text").isEqualTo("Web ID: "+webId);

        softly.assertAll();
    }

    @Then("^The Product Details and shipping returns section should be in default state$")
    public void The_Product_Details_and_shipping_returns_section_should_be_in_default_state() throws Throwable {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(Elements.elementPresent("pdp.product_details_collapsed")).as("Product details collapsed").isEqualTo(false);
        softly.assertThat(Elements.elementPresent("pdp.product_shipping_returns_collapsed")).as("Shipping returns collapsed").isEqualTo(true);

        softly.assertAll();
    }

    @When("^I click on Product Details and shipping returns headers$")
    public void I_click_on_Product_Details_and_shipping_returns_headers() throws Throwable {
        Clicks.click(Elements.element("pdp.product_details_header"));
        Wait.secondsUntilElementPresent("pdp.product_details_collapsed", 10);
        Clicks.click(Elements.element("pdp.product_shipping_returns_header"));
    }

    @Then("^Product details and shipping returns section should change its default state$")
    public void Product_details_section_should_get_collapsed() throws Throwable {
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(Elements.elementPresent("pdp.product_details_collapsed")).as("Product details collapsed").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.product_shipping_returns_collapsed")).as("Shipping returns expand").isEqualTo(false);

        softly.assertAll();
    }

    public void searchProduct(String prodId) throws Throwable{
        Elements.findElement(Elements.element("pdp.globalSearchInputField")).clear();
        Elements.findElement(Elements.element("pdp.globalSearchInputField")).sendKeys(prodId);
        Wait.secondsUntilElementPresent("pdp.searchSubmit", 10);
        Clicks.click("home.searchSubmit");
    }


    public void createTestList(String listName) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Clicks.click(Elements.element("pdp.addToListArrow"));
        Wait.secondsUntilElementPresent("pdp.listDropDown", 40);
        Clicks.click(Elements.element("pdp.createNewListLink"));
        Clicks.click(Elements.element("pdp.createNewListBtn"));
        softly.assertThat(Elements.elementPresent("pdp.createNewListError")).as("createNewListError").isEqualTo(true);
        Wait.secondsUntilElementPresent("pdp.createNewListError", 10);
        String error_text = Elements.findElement(Elements.element("pdp.createNewListError")).getText();
        softly.assertThat(error_text).as("createNewListError").isEqualTo("Please enter a name.");
        Elements.findElement(Elements.element("pdp.createNewListName")).sendKeys(listName);
        Clicks.click(Elements.element("pdp.createNewListBtn"));
        Wait.secondsUntilElementPresent("pdp.newListLink", 10);
        Clicks.click(Elements.element("pdp.newListLink"));
        softly.assertAll();
    }

    public void deleteTestList(String listName) throws Throwable {
        String listNameLink = "//a[contains(text(),'" + listName + "')]";
        Wait.secondsUntilElementPresent(By.xpath(listNameLink), 10);
        while(Elements.findElements(By.xpath(listNameLink)).size() != 0) {
            Clicks.click(Elements.findElement(By.xpath(listNameLink)));
            Wait.secondsUntilElementPresent("addToList.wishListTitle", 10);
            Clicks.click(Elements.element("addToList.manageListArrow"));
            Wait.secondsUntilElementPresent("addToList.deleteListBtn", 10);
            Clicks.click(Elements.element("addToList.deleteListBtn"));
            Wait.secondsUntilElementPresent("addToList.confirmDeleteBtn", 10);
            Clicks.click(Elements.element("addToList.confirmDeleteBtn"));
        }
    }

    public String getRandomNumber()throws Throwable {
        LocalTime thisSec = LocalTime.now();
        return thisSec.toString().replace(":","").replace(".","");
    }

    @Then("^I verify Bag Elements were passed in correctly$")
    public void I_verify_Bag_Elements_were_passed_in_correctly() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        String item = Elements.findElement(Elements.element("pdp.singlePrice")).getText();
        item = item.replace("$","").replace("Now","").replace("Sale","").trim();
        double itemDouble = Double.parseDouble(item);
        int productQuantityInt = Integer.parseInt(productQuantity);
        double totalPrice = itemDouble * productQuantityInt;
        String productDescription = Elements.findElement(Elements.element("pdp.productDescription")).getText();
        String productColor = Elements.findElement(Elements.element("pdp.productSelectedColor")).getText();
        List<WebElement> product_sizes = Elements.findElements(Elements.element("pdp.sizes_array"));
        if (Elements.elementPresent("pdp.sizes_array")) {
            Clicks.click(product_sizes.get(0));
        }
        Clicks.click(Elements.element("pdp.add_to_bag"));
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("addToBag.productDescription", 10);
        String bagQuantity = Elements.findElement(Elements.element("addToBag.quantity")).getText();
        String bagTotalPrice = Elements.findElement(Elements.element("addToBag.totalPrice")).getText();
        String bagProductDescription = Elements.findElement(Elements.element("addToBag.productDescription")).getText();
        softly.assertThat(Elements.elementPresent("addToBag.productImage")).as("productImage").isEqualTo(true);
        String bagColor = Elements.findElement(Elements.element("addToBag.color")).getText();
        String bagPrice = Elements.findElement(Elements.element("addToBag.price")).getText();
        softly.assertThat(bagPrice.contains(item)).as("addToBag.price").isEqualTo(true);
        //if free gift with this product bag Quantity will be 1 item more
        softly.assertThat(Integer.parseInt(bagQuantity) >= Integer.parseInt(productQuantity)).as("bagQuantity").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("addToBag.totalPrice")).as("bagTotalPrice").isEqualTo(true);
        String totalPrice2 = Double.toString(totalPrice).replace("\\$", "").split("\\.")[0];
        bagTotalPrice = bagTotalPrice.replace(",", "").replace("\\$", "").split("\\.")[0];
        softly.assertThat(bagTotalPrice.contains(totalPrice2)).as("bagTotalPrice").isEqualTo(true);
        //get last 2 words from productDescription
        String[] parts = productDescription.split(" ");
        String lastWord = parts[parts.length - 1];
        String lastWord2 = parts[parts.length - 2];
        softly.assertThat(bagProductDescription.contains(lastWord2+" "+lastWord)).as("bagProductDescription").isEqualTo(true);
        softly.assertThat(bagColor).as("bagColor").isEqualTo(productColor);
        softly.assertAll();
    }


    @Then("^I look at Electronic Gift Card Product$")
    public void I_look_at_Electronic_Gift_Card_Product() throws Throwable {
        productId = "666269";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Then("^I look at Mattress product with big ticket function$")
    public void I_look_at_Mattress_product_with_big_ticket_function() throws Throwable {
        String[] big_ticket_products = {"2712389","4511654"};
        int idx = new Random().nextInt(big_ticket_products.length);
        productId = big_ticket_products[idx];
        searchProduct(productId);
        Wait.forPageReady();
        int i = 0;
        //will select other random product if product not found
        while(i < 5) {
            if(Elements.elementPresent("pdp.zero_productCount")) {
                idx = new Random().nextInt(big_ticket_products.length);
                productId = big_ticket_products[idx];
                searchProduct(productId);
                Wait.forPageReady();
            }
        i++;
        }
        navigatetoPDPCompUrl();
    }

    @Then("^I look at product with gift with purchase$")
    public void I_look_at_product_with_gift_with_purchase() throws Throwable {
        productId = "2676989";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Then("^I look at product with availability backorder$")
    public void I_look_at_product_with_availability_backorder() throws Throwable {
        productId = "609735";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }


    @Then("^I verify Add to Bag functionality for Electronic Gift Card Product$")
    public void I_verify_Add_to_Bag_functionality_for_Electronic_Gift_Card_Product() throws Throwable {
        email = "list@qa.com";
        title = "Macy's Red Star E-Gift Card";
        amount = "50";
        Wait.secondsUntilElementPresent("pdp.giftCardProductTitle", 40);
        String ProductTitle = Elements.findElement(Elements.element("pdp.giftCardProductTitle")).getText();
        softly.assertThat(ProductTitle).as("ProductTitle").isEqualTo(title);
        //verify required fields
        Clicks.click(Elements.element("pdp.giftCardAddToBagButton"));
        List<WebElement> error = Elements.findElements(Elements.element("pdp.giftCardRequiredFieldsError"));
        for(WebElement item : error){
            softly.assertThat(item.getText().contains("Enter Amount:")||item.getText().contains("Email Address:")).as("required_fields_error").isEqualTo(true);
        }
        Elements.findElement(Elements.element("pdp.giftCardAmountField")).sendKeys(amount);
        Elements.findElement(Elements.element("pdp.giftCardEmailField")).sendKeys(email);
        error = Elements.findElements(Elements.element("pdp.giftCardRequiredFieldsError"));
        softly.assertThat(error.size()==0).as("required_fields_error").isEqualTo(true);
//verify add to bag
        Clicks.click(Elements.element("pdp.giftCardAddToBagButton"));
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("addToBag.giftCardProductTitle", 10);
        String bagProductTitle = Elements.findElement(Elements.element("addToBag.giftCardProductTitle")).getText();
        String bagPrice = Elements.findElement(Elements.element("addToBag.giftCardAmount")).getText();
        String bagSubtotal = Elements.findElement(Elements.element("addToBag.giftCardSubtotal")).getText();

        softly.assertThat(bagProductTitle).as("BagProductTitle").isEqualTo(title);
        softly.assertThat(bagPrice.contains(amount)).as("amount").isEqualTo(true);
        softly.assertThat(bagSubtotal.contains(amount)).as("subtotal").isEqualTo(true);
        Navigate.browserBack();

        softly.assertAll();

    }


    @Then("^I verify quickMyBagLink contains Electronic Gift Card$")
    public void I_verify_quickMyBagLink_contains_Electronic_Gift_Card() throws Throwable {
        email = "list@qa.com";
        title = "Macy's Red Star E-Gift Card";
        amount = "50";
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.quickMyBagLink", 10);
        Clicks.hoverForSelection(Elements.element("pdp.quickMyBagLink"));
        Wait.secondsUntilElementPresent("pdp.quickMyBagTitle", 10);
        String quickBagTitle = Elements.findElement(Elements.element("pdp.quickMyBagTitle")).getText();
        String quickBagAmount = Elements.findElement(Elements.element("pdp.quickMyBagPrice")).getText();
        String quickBagEmail = Elements.findElement(Elements.element("pdp.quickMyBagEmail")).getText();
        softly.assertThat(quickBagAmount.contains(amount)).as("quickBagSubtotal").isEqualTo(true);
        softly.assertThat(quickBagTitle).as("quickBagProductTitle").isEqualTo(title);
        softly.assertThat(quickBagEmail).as("quickBagEmail").isEqualTo(email.toUpperCase());

        softly.assertAll();
    }


    @Then("^I verify email sample link$")
    public void I_verify_email_sample_link() throws Throwable {
        title = "Macy's Red Star E-Gift Card";
        Clicks.click(Elements.element("pdp.giftCardEmailSampleLink"));
        softly.assertThat(Elements.elementPresent("pdp.giftCardEmailSample")).as("email sample").isEqualTo(true);
        String giftCardEmailTitle = Elements.findElement(Elements.element("pdp.giftCardEmailTitle")).getText();
        softly.assertThat(giftCardEmailTitle).as("email giftCardEmailTitle").isEqualTo(title);
        Clicks.click(Elements.element("pdp.giftCardEmailSampleClose"));

        softly.assertAll();
    }



    @Then("^I verify pdp tab area$")
    public void I_verify_pdp_tab_area() throws Throwable {
        //ProductDetails Tab
        Wait.secondsUntilElementPresent("pdp.ProductDetailsTab", 10);
        Clicks.click(Elements.element("pdp.ProductDetailsTab"));
        String ProductDetailsWebId = Elements.findElement(Elements.element("pdp.ProductDetailsWebId")).getText();
        softly.assertThat(ProductDetailsWebId.contains(productId)).as("WebID").isEqualTo(true);
        //Q&A Tab
        softly.assertThat(Elements.elementPresent("pdp.QATab")).as("QATab").isEqualTo(true);
        softly.assertAll();

    }

    @Then("^I verify shipping link pdp tab area$")
    public void I_verify_shipping_pdp_tab_area() throws Throwable {
        //Shipping&Returns Tab
        Clicks.click(Elements.element("pdp.shippingTab"));
        //verify shipping popup
        String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
        Wait.secondsUntilElementPresent("pdp.shippingTabShippingLink", 10);
        Clicks.click(Elements.element("pdp.shippingTabShippingLink"));
        Set<String> handles =  WebDriverManager.getWebDriver().getWindowHandles();
        for(String windowHandle  : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to child window
                Wait.secondsUntilElementPresent("pdp.shippingPopupSearch", 20);
                softly.assertThat(Elements.elementPresent("pdp.shippingPopupSearch")).as("shippingPopupSearch").isEqualTo(true);
                WebDriverManager.getWebDriver().close(); //closing child window
                WebDriverManager.getWebDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        softly.assertAll();
    }

    @Then("^I verify return link pdp tab area$")
    public void I_verify_return_pdp_tab_area() throws Throwable {
        Wait.secondsUntilElementPresent("pdp.shippingTab", 10);
        Clicks.click(Elements.element("pdp.shippingTab"));
        Wait.secondsUntilElementPresent("pdp.shippingTabReturnLink", 10);
        //verify shipping popup
        String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
        Clicks.click(Elements.element("pdp.shippingTabReturnLink"));
        Set<String> handles =  WebDriverManager.getWebDriver().getWindowHandles();
        for(String windowHandle  : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to child window
                Wait.secondsUntilElementPresent("pdp.shippingPopupSearch", 20);
                softly.assertThat(Elements.elementPresent("pdp.shippingPopupSearch")).as("shippingPopupSearch").isEqualTo(true);
                WebDriverManager.getWebDriver().close(); //closing child window
                WebDriverManager.getWebDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        softly.assertAll();
    }

    @Then("^I verify click to call functionality$")
    public void I_verify_click_to_call_functionality() throws Throwable {
        String parentWindow = WebDriverManager.getWebDriver().getWindowHandle();
        Clicks.click(Elements.element("pdp.orderByPhone"));
        Thread.sleep(4000);
        Set<String> handles =  WebDriverManager.getWebDriver().getWindowHandles();
        for(String windowHandle  : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                SoftAssertions softlyAssert = new SoftAssertions();
                WebDriverManager.getWebDriver().switchTo().window(windowHandle);//switch to child window
                Wait.secondsUntilElementPresent("pdp.talkByPhonePopupBtn", 20);
                softlyAssert.assertThat(Elements.elementPresent("pdp.talkByPhonePopupBtn")).as("talkByPhonePopupBtn").isEqualTo(true);
                softlyAssert.assertAll();
                WebDriverManager.getWebDriver().close(); //closing child window
                WebDriverManager.getWebDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        String callMacys = Elements.findElement(Elements.element("pdp.callMacys")).getText();
        String availableHrs = Elements.findElement(Elements.element("pdp.availableHrs")).getText();
        softly.assertThat(callMacys).as("callMacys").isEqualTo("Call 1-800-BUY-MACY (289-6229)");
        //We're available 8am - 12am EST.  --------will ignore 12am since it changes constantly
        String message = "We're available 8am -([\\s*][\\d]{1,2}[a-z|A-Z]{2}[\\s*])EST.";
        softly.assertThat(availableHrs.matches(message)).as("availableHrs").isEqualTo(true);
        softly.assertAll();
    }


    @Then("^I verify Get alerts for this item$")
    public void I_verify_Get_alerts_for_this_item() throws Throwable {
        email = "list@qa.com";
        title = Elements.findElement(Elements.element("pdp.productDescription")).getText();
        //verify alert valid email error - no email
        Clicks.click(Elements.element("pdp.getPriceAlertsLink"));
        Wait.secondsUntilElementPresent("pdp.getPriceAlertsSubmit", 10);
        Clicks.click(Elements.element("pdp.getPriceAlertsSubmit"));
        Wait.secondsUntilElementPresent("pdp.getPriceAlertsError", 10);
        softly.assertThat(Elements.elementPresent("pdp.getPriceAlertsError")).as("getPriceAlertsError").isEqualTo(true);
        String PriceAlertError = Elements.findElement(Elements.element("pdp.getPriceAlertsError")).getText();
        softly.assertThat(PriceAlertError).as("PriceAlertError").isEqualTo("Please enter a valid email.");
        //verify alert valid email error - invalid email 1
        typeTextbox("pdp.getPriceAlertsEmail", "test");
        Clicks.click(Elements.element("pdp.getPriceAlertsSubmit"));
        PriceAlertError = Elements.findElement(Elements.element("pdp.getPriceAlertsError")).getText();
        softly.assertThat(PriceAlertError).as("PriceAlertError").isEqualTo("Email is invalid. Please try again.");
        //verify alert valid email error - invalid email 2
        typeTextbox("pdp.getPriceAlertsEmail", "test@test");
        Clicks.click(Elements.element("pdp.getPriceAlertsSubmit"));
        PriceAlertError = Elements.findElement(Elements.element("pdp.getPriceAlertsError")).getText();
        softly.assertThat(PriceAlertError).as("PriceAlertError").isEqualTo("Email is invalid. Please try again.");
        //set alert with valid email
        typeTextbox("pdp.getPriceAlertsEmail", email);
        Clicks.click(Elements.element("pdp.getPriceAlertsSubmit"));

        Wait.secondsUntilElementPresent("pdp.cancelPriceAlertsLink", 10);
        String cancelPriceAlert = Elements.findElement(Elements.element("pdp.cancelPriceAlertsLink")).getText();
        softly.assertThat(cancelPriceAlert).as("cancelPriceAlert").isEqualToIgnoringCase("Cancel Price Alerts");
        //cancel alert
        Thread.sleep(8000); //TBD remove hardcoded sleep
        Clicks.click(Elements.element("pdp.cancelPriceAlertsLink"));
        Wait.secondsUntilElementPresent("pdp.getPriceAlertsProduct", 10);
        String alertProductTitle = Elements.findElement(Elements.element("pdp.getPriceAlertsProduct")).getText();
        softly.assertThat(alertProductTitle).as("alertProductTitle").isEqualTo(title);
        Clicks.click(Elements.element("pdp.removePriceAlertsLink"));
        //verify alert canceled
        Wait.secondsUntilElementPresent("pdp.getPriceAlertsLink", 10);
        softly.assertThat(Elements.elementPresent("pdp.getPriceAlertsLink")).as("getPriceAlertsLink").isEqualTo(true);

        softly.assertAll();

    }


    @Then("^I verify gift with purchase text and links$")
    public void I_verify_gift_with_purchase_text_and_links() throws Throwable {
        //verify specialOffersText
        List <WebElement> specialOffersText = Elements.findElements(Elements.element("pdp.specialOffersText"));
        //String freeShipmentOffer = "FREE SHIPPING AND RETURNS";
        String freeGiftOffer = "FREE GIFT WITH PURCHASE";
        for(WebElement item : specialOffersText){
            softly.assertThat(item.getText().contains(freeGiftOffer) || !(item.getText().isEmpty())).as("specialOffersText").isEqualTo(true);
        }
        //verify specialOffersDetailsLink
        List <WebElement> specialOffersDetailsLink = Elements.findElements(Elements.element("pdp.specialOffersDetailsLink"));
        for(WebElement item : specialOffersDetailsLink){
            softly.assertThat(item.getText().contains("details")).as("specialOffersText").isEqualTo(true);
            softly.assertThat(item.getAttribute("href").contains("#product-offers-header")).as("specialOffersHREF").isEqualTo(true);
        }
        //verify specialOffersMoreOffersLink
        softly.assertThat(Elements.elementPresent("pdp.specialOffersMoreOffersLink")).as("specialOffersMoreOffersLink").isEqualTo(true);
        softly.assertThat(Elements.findElement(Elements.element("pdp.specialOffersMoreOffersLink")).getAttribute("href").contains("#product-offers-header")).as("specialOffersHREF").isEqualTo(true);
        //verify special offers title inside Special offers accordion
        Clicks.click(Elements.element("pdp.specialOffersMoreOffersLink"));
        Wait.secondsUntilElementPresent("pdp.specialOffersTitle", 20);
        List <WebElement> specialOffersTitle = Elements.findElements(Elements.element("pdp.specialOffersTitle"));
        for(WebElement item : specialOffersTitle){
            softly.assertThat(item.getText().contains(freeGiftOffer)).as("freeGiftOffer").isEqualTo(true);
        }
        softly.assertThat(specialOffersTitle.size() > 0).as("specialOffersTitle").isEqualTo(true);
        softly.assertAll();
    }

    @Then("^I verify backorder status appears on the page$")
    public void I_verify_backorder_text_appears_on_the_page() throws Throwable {
        Wait.secondsUntilElementPresent("pdp.availabilityRow", 20);
        String availabilityStatus = Elements.findElement(Elements.element("pdp.availabilityRow")).getText();
        softly.assertThat(availabilityStatus.contains("backorder") &&
                availabilityStatus.contains("usually ships within 38 business days")).as("availabilityStatus").isEqualTo(true);
    }

    @Then("^I should see \"([^\"]*)\" on quick bag overlay$")
    public void I_should_see_0_items_in_your_bag_Shop_great_deals_now_on_quick_bag_overlay(String quickBagText) throws Throwable {
        WebElement hoverQB = null;

        SoftAssertions softly = new SoftAssertions();
        Wait.secondsUntilElementPresent("pdp.quickMyBagLink", 10);
        Clicks.hoverForSelection(Elements.element("pdp.quickMyBagLink"));
        Wait.secondsUntilElementPresent("pdp.quickMyBagTitle", 10);

        Wait.secondsUntilElementPresent("quick_bag.quickbag_items_list", 10);
        String quickbag_Content = "";
        if(Elements.elementPresent("quick_bag.quickbag_items_list")) {
            quickbag_Content = Elements.findElement(Elements.element("quick_bag.quickbag_items_list")).getText();
        }else {
            quickbag_Content = Elements.findElement(Elements.element("quick_bag.quickbag_header")).getText();
        }
        softly.assertThat(quickbag_Content.toLowerCase().contains(quickBagText.toLowerCase())).as("QBag Overlay Text").isEqualTo(true);
        softly.assertAll();
    }

    @When("^I click on add to bag button")
    public void I_click_on_add_to_bag_button() throws Throwable {
        Wait.forPageReady();
        Clicks.click(Elements.element("pdp.add_to_bag"));
        Thread.sleep(2000);
        Wait.forPageReady();
        Navigate.browserBack();
        Wait.forPageReady();
        //Thread.sleep(5000);
    }

    @Then("^I should see Pendant Necklace product description hovering quick bag header$")
    public void I_should_see_Pendant_Necklace_product_description_hovering_quick_bag_header() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        WebElement hoverQB = null;
        Wait.forPageReady();
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("quick_bag.quickbag_header", 40);
            hoverQB = Elements.findElement("quick_bag.quickbag_header");
        } catch (Exception e) {
            System.out.println("Unable to hover on quick bag header");
            e.printStackTrace();
        }
        Clicks.hoverForSelection(hoverQB);

        Wait.secondsUntilElementPresent("quick_bag.quickbag_header", 20);
        String bag_item_description = Elements.findElement(Elements.element("quick_bag.quickbag_header")).getText();
        softly.assertThat(bag_item_description.contains("My Bag (1)")).as("QBag product description").isEqualTo(true);
        softly.assertAll();
    }

    public int get_qa_count() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.qaCount", 20);
        String qaFullText = Elements.findElement(Elements.element("pdp.qaCount")).getText();
        int  positionOfQ = qaFullText.indexOf('q');
        String qaCountValue = qaFullText.substring(0, positionOfQ);
        return Integer.parseInt(qaCountValue.trim());
    }

    @Given("^I verify QandA elements for iPhone6 Wristlet product$")
    public void I_verify_QandA_elements_for_iPhone6_Wristlet_product() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Clicks.hoverForSelection(Elements.element("pdp.qaSection"));
        Clicks.click(Elements.element("pdp.qaSection"));
        int qaCount = get_qa_count();
        softly.assertThat(qaCount > 0).as("qa_count").isEqualTo(true);
        Clicks.hoverForSelection(Elements.element("pdp.qaSearch"));

        softly.assertThat(Elements.elementPresent("pdp.qaSearch")).as("qa_search").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.qaExpandAllPlus")).as("qa_expand_all_plus").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.qaExpandAllLink")).as("qa_expand_all_link").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.qaQuestionPlus")).as("qa_question_plus").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.qaQuestionLink")).as("qa_question_link").isEqualTo(true);
        Clicks.click(Elements.element("pdp.qaQuestionLink"));
        Wait.secondsUntilElementPresent("pdp.qaAnswerButton", 20);
        softly.assertThat(Elements.elementPresent("pdp.qaAnswerButton")).as("qa_answer_button").isEqualTo(true);
        Clicks.click(Elements.element("pdp.qaAnswerButton"));
        Wait.secondsUntilElementPresent("pdp.sign_in_button", 20);
        softly.assertThat(Elements.elementPresent("pdp.sign_in_button")).as("sign_in_button").isEqualTo(true);

        softly.assertAll();
    }

    @Given("^I Navigate to COACH product pdp page$")
    public void I_Navigate_to_COACH_product_pdp_page() throws Throwable {
        productId = "2460457";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Given("^I look at DRESS product with ISHIP eligible$")
    public void I_look_at_DRESS_product_with_ISHIP_eligible() throws Throwable {
        String[] iship_eligible_products = {"2563967"};//"2911996"
        int idx = new Random().nextInt(iship_eligible_products.length);
        productId = iship_eligible_products[idx];
        typeTextBoxIfPresent("home.globalSearchInputField", productId);
        Clicks.click(Elements.element("home.searchSubmit"));
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Then("^I should see price with CAD Currency$")
    public void I_should_see_price_with_CAD_Currency() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.product_price_info", 20);
        String iship_Currency = Elements.findElement(Elements.element("pdp.product_price_info")).getText();
        softly.assertThat(iship_Currency.contains("CAD")).as("iship currency").isEqualTo(true);
        softly.assertAll();
    }


    @Then("^I verify common product info for CHANEL product$")
    public void I_verify_common_product_info_for_CHANEL_product() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.chanelBrandLogo", 20);
        softly.assertThat(Elements.elementPresent("pdp.chanelBrandLogo")).as("chanelBrandLogo").isEqualTo(true);
        String brand = Elements.findElement(Elements.element("pdp.chanelProductBrand")).getText();
        softly.assertThat(brand).as("chanelProductBrand").isEqualTo("CHANEL");
        //verify web ID
        String webId = Elements.findElement(Elements.element("pdp.chanelProductID")).getText();
        webId = webId.split("Web ID:")[1].trim();
        softly.assertThat(productId).as("webId").isEqualTo(webId);
        //verify price
        String price = Elements.findElement(Elements.element("pdp.chanelPriceInfo")).getText();
        softly.assertThat(price.contains("$")).as("chanelPriceInfo").isEqualTo(true);
        price = price.replace("$","").replace(".","");
        softly.assertThat(NumberUtils.isNumber(price)).as("chanelPriceInfo2").isEqualTo(true);
        //verify Quantity list
        softly.assertThat(Elements.elementPresent("pdp.chanelProductQuantity")).as("chanelProductQuantity").isEqualTo(true);
        List <WebElement> QuantityList = Elements.findElements(Elements.element("pdp.chanelProductQuantityOption"));
        softly.assertThat(QuantityList.isEmpty()).as("QuantityList not empty").isEqualTo(false);
        for(WebElement item : QuantityList){
            softly.assertThat(item.getText().isEmpty()).as("QuantityList option not empty").isEqualTo(false);
            softly.assertThat(NumberUtils.isNumber(item.getText())).as("QuantityList is number").isEqualTo(true);
        }
        softly.assertThat(Elements.elementPresent("pdp.chanelProductMainImg")).as("chanelProductMainImg").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.chanelAddToBagButton")).as("chanelAddToBagButton").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.chanelAddToRegistryButton")).as("chanelAddToRegistryButton").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.chanelAddToWishlistBtn")).as("chanelAddToWishlistBtn").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.chanelAvailabilityLink")).as("chanelAvailabilityLink").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.chanelEmailIcon")).as("chanelEmailIcon").isEqualTo(true);

        softly.assertAll();
    }



    @Then("^I verify Add to List functionality for chanel product with \"([^\"]*)\" user$")
    public void I_verify_Add_to_List_functionality_for_chanel_product(String user) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.forPageReady();
        if(user.equals("guest")) {
            Wait.secondsUntilElementPresent("pdp.chanelAddToWishlistBtn", 30);
            Clicks.click(Elements.element("pdp.chanelAddToWishlistBtn"));
            Wait.secondsUntilElementPresent("pdp.added_to_list_msg", 30);
            Clicks.click(Elements.element("pdp.added_to_list_msg"));
            Wait.forPageReady();
            //Wait.secondsUntilElementPresent("addTolist.wishListTitle", 30);
            //softly.assertThat(Elements.elementPresent("addTolist.wishListTitle")).as("wishListTitle").isEqualTo(true);
            softly.assertAll();
            Navigate.browserBack();
        } else if (user.equals("registred")) {
            Wait.secondsUntilElementPresent("pdp.chanelAddToWishlistDownArrow", 30);
            Clicks.click(Elements.element("pdp.chanelAddToWishlistDownArrow"));
            String randomNumber = getRandomNumber();
            Wait.secondsUntilElementPresent("pdp.createNewListLink", 20);
            Clicks.click(Elements.element("pdp.createNewListLink"));
            Elements.findElement(Elements.element("pdp.createNewListName")).sendKeys(newListName+randomNumber);
            Clicks.click(Elements.element("pdp.createNewListBtn"));
            Wait.secondsUntilElementPresent("pdp.newListLink", 30);
            Clicks.click(Elements.element("pdp.newListLink"));
            String listNameLink = "//a[contains(text(),'"+ newListName+randomNumber +"')]";
            Wait.secondsUntilElementPresent(By.xpath(listNameLink), 30);
            softly.assertThat(Elements.findElements(By.xpath(listNameLink)).size()).as("NewWishListLink").isEqualTo(1);
            Navigate.browserBack();
            softly.assertAll();
        } else {
            System.out.println("I verify Add to Registry functionality for chanel product with STEP wrong input -- " + user);
            softly.assertThat(true).as("NewWishListLink wrong input").isEqualTo(false);
            softly.assertAll();
        }

        softly.assertAll();
    }

    @Then("^I verify Add to Bag functionality for chanel product$")
    public void I_verify_Add_to_Bag_functionality_for_chanel_product() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.chanelAddToBagButton", 20);
        Clicks.click(Elements.element("pdp.chanelAddToBagButton"));

        Wait.forPageReady();
        Wait.secondsUntilElementPresent("addToBag.added_to_bag_confirm_msg", 10);
        softly.assertThat(Elements.elementPresent("addToBag.added_to_bag_confirm_msg")).as("added_to_bag_confirm_msg").isEqualTo(true);
        String quantity = Elements.findElement(Elements.element("addToBag.quantity")).getText();
        String productDescription = Elements.findElement(Elements.element("addToBag.productDescription")).getText();
        softly.assertThat(quantity.isEmpty()).as("Quantity").isEqualTo(false);
        softly.assertThat(productDescription.isEmpty()).as("productDescription").isEqualTo(false);
        Navigate.browserBack();

        softly.assertAll();
    }

    @Then("^I verify Add to Registry functionality for chanel product with \"([^\"]*)\" user$")
    public void I_verify_Add_to_Registry_functionality_for_chanel_product(String user) throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.chanelAddToRegistryButton", 20);
        Clicks.click(Elements.element("pdp.chanelAddToRegistryButton"));
        Wait.forPageReady();
        if(user.equals("guest")) {
            Wait.secondsUntilElementPresent("account.user_email", 40);
            softly.assertThat(Elements.elementPresent("account.user_email")).as("account.user_email").isEqualTo(true);
            softly.assertAll();
            Navigate.browserBack();
        } else if (user.equals("registred")) {
            Wait.secondsUntilElementPresent("pdp.accessRegistryBtn", 20);
            softly.assertThat(Elements.elementPresent("pdp.accessRegistryBtn")).as("accessRegistryBtn").isEqualTo(true);
            softly.assertAll();
        } else {
        System.out.println("I verify Add to Registry functionality for chanel product with STEP wrong input -- " + user);
        }


    }



    @Given("^I look at chanel product$")
    public void I_look_at_chanel_product() throws Throwable {
        visit("home");
        productId = "1669016";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Given("^I look at COACH Willow Floral Mercer product$")
    public void I_look_at_COACH_Willow_Floral_Mercer_product() throws Throwable {
        productId = "319737";
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        String url = WebDriverManager.getCurrentUrl();
        int indx = url.indexOf("?");
        StringBuilder pdpcompUrl = new StringBuilder(url);
        pdpcompUrl = pdpcompUrl.insert(indx+1, "targetroute=pdpcomp&");
        url = pdpcompUrl.toString();
        Navigate.visit(url);
        //CommonUtils.navigateDirectlyToProduct(productId);
    }


    @Then("^I search for \"([^\"]*)\" product$")
    public void I_search_for_nav_app_product(String keyword) throws Throwable {
        //typeTextbox("home.search_field", keyword);
        searchProduct(keyword);
    }


    @Given("^I verify suggestions are displayed$")
    public void I_verify_suggestions_are_displayed() throws Throwable {
        Wait.secondsUntilElementPresent("pdp.searchSuggestions", 20);
        WebElement searchSuggestions = Elements.findElement(Elements.element("pdp.searchSuggestions"));
        softly.assertThat(searchSuggestions.isDisplayed()).as("searchSuggestions").isEqualTo(true);
        softly.assertAll();
    }

    @Then("^I verify search results \"([^\"]*)\" are displayed$")
    public void I_verify_search_results(String keyword) throws Throwable {
        //TextBoxes.typeTextNEnter("home.search_field", keyword);
        //searchProduct(keyword);
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.searchResultKeyword", 40);
        String searchResultKeyword = Elements.findElement(Elements.element("pdp.searchResultKeyword")).getText();
        softly.assertThat(searchResultKeyword).as("searchResultKeyword").isEqualToIgnoringCase(keyword);
        softly.assertAll();
    }

    @Given("^I look at master product$")
    public void I_look_at_master_product() throws Throwable {
        String[] master_products = {"95946"};//"866649","2350695","2891327"
        int index = new Random().nextInt(master_products.length);
        productId = master_products[index];
        //TextBoxes.typeTextNEnter("home.search_field", productId);
        searchProduct(productId);
        Wait.forPageReady();
        //CommonUtils.navigateDirectlyToProduct(productId);
    }

    @When("^I navigate to PDP with random member product$")
    public void I_navigate_to_PDP_with_random_member_product() throws Throwable {
        List<WebElement> thumbnails = new ArrayList<>();
        thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));

        List<WebElement> masterSpanElement = new ArrayList<>();
        List<WebElement> memberProductThumbnail = new ArrayList<>();

        for(WebElement thumbnail : thumbnails) {
            masterSpanElement = thumbnail.findElements(By.xpath(".//div[@class='innerWrapper']/div[@class='fullColorOverlayOff']/a/span[contains(@id,'_isMasterProduct')]"));
            if(!(masterSpanElement.size() > 0)) {
                memberProductThumbnail.add(thumbnail);
            }
        }
        int idx = new Random().nextInt(memberProductThumbnail.size());
        if(memberProductThumbnail.size() < 1) {
            logger.warning(String.format("Member product not found"));
        }
        Clicks.click(memberProductThumbnail.get(idx).findElement(By.xpath(".//div[@class='innerWrapper']/div[@class='textWrapper']/div[1]")));
        //Clicks.click(memberProductThumbnail.get(idx).findElement(By.xpath("//div[@class='innerWrapper']/div[@class='shortDescription']/a")));
        navigatetoPDPCompUrl();
        if(Elements.elementPresent("pdp.masterProductViewMemberItems")) {
            logger.warning(String.format("Member product not found"));
        }

    }


    @When("^I navigate to PDP of the first master product$")
    public void iNavigateToPDPOfTheFirstmasterProduct() throws Throwable {
        List<WebElement> thumbnails = new ArrayList<>();
        thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));

        List<WebElement> masterSpanElement = new ArrayList<>();
        List<WebElement> masterProductThumbnail = new ArrayList<>();

        for(WebElement thumbnail : thumbnails) {
            masterSpanElement = thumbnail.findElements(By.xpath(".//div[@class='innerWrapper']/div[@class='fullColorOverlayOff']/a/span[contains(@id,'_isMasterProduct')]"));
            if(masterSpanElement.size() > 0) {
                masterProductThumbnail.add(thumbnail);
            }
        }
        int idx = new Random().nextInt(masterProductThumbnail.size());
        if(masterProductThumbnail.size() < 1) {
            logger.warning(String.format("Master product not found"));
        }
        //Clicks.click(masterProductThumbnail.get(idx).findElement(By.xpath(".//div[@class='innerWrapper']/div[@class='textWrapper']/div[1]")));
        Clicks.click(masterProductThumbnail.get(0).findElement(By.xpath(".//div[@class='innerWrapper']/div[@class='textWrapper']/div[1]")));
        navigatetoPDPCompUrl();
        if(!(Elements.elementPresent("pdp.masterProductViewMemberItems"))) {
            logger.warning(String.format("Master product not found"));
        }
    }


    @Then("^I verify common master product info$")
    public void I_verify_common_master_product_info() throws Throwable {
        //verify title is not empty
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.masterProductTitle", 40);
        if(Elements.elementPresent("pdp.toogleText")) {
            Clicks.click("pdp.toogleText");
        }
        String productTitle = Elements.findElement(Elements.element("pdp.masterProductTitle")).getText();
        softly.assertThat(productTitle.isEmpty()).as("searchResultKeyword").isEqualTo(false);
        //verify price is not empty
        Wait.secondsUntilElementPresent("pdp.masterProductPrice", 40);
        String masterProductPrice = Elements.findElement(Elements.element("pdp.masterProductPrice")).getText();
        softly.assertThat(masterProductPrice.startsWith("$")).as("masterProductPrice1").isEqualTo(true);
        softly.assertThat(masterProductPrice.replace("$", "").replace(".", "").replace("-","").replace(" ","").trim().matches("[0-9]+")).as("masterProductPrice2").isEqualTo(true);
        //masterProductViewMemberItems
        softly.assertThat(Elements.elementPresent("pdp.masterProductViewMemberItems")).as("masterProductViewMemberItems").isEqualTo(true);
        //web_id
        String web_id = Elements.findElement(Elements.element("pdp.web_id")).getText();
        softly.assertThat(web_id.startsWith("Web ID:")).as("web_id1").isEqualTo(true);
        //softly.assertThat(web_id.contains(productId)).as("web_id2").isEqualTo(true);

        //member product list masterProductMemberPriceColor
        List<WebElement> memberProducts = Elements.findElements(Elements.element("pdp.masterProductMemberPriceColor"));
        for(WebElement memberProduct : memberProducts) {
            softly.assertThat(Elements.elementPresent("pdp.masterProductViewMemberItems")).as("masterProductViewMemberItems").isEqualTo(true);
            if (memberProduct.getText().startsWith("Reg")) {
                String memberPrice = memberProduct.getText().split("Reg.")[1].trim();
                softly.assertThat(memberPrice.startsWith("$")).as("masterProductMemberPrice1").isEqualTo(true);
                softly.assertThat(memberPrice.replace("$", "").replace(".", "").matches("[0-9]+")).as("masterProductMemberPrice2").isEqualTo(true);
            }//end masterProductMemberPriceColor
        }

        //member product list masterProductMemberPriceNoColor
        List<WebElement> memberProductsNoColor = Elements.findElements(Elements.element("pdp.masterProductMemberPriceNoColor"));
        for(WebElement memberProductNoColor : memberProductsNoColor) {
            if (memberProductNoColor.getText().startsWith("Reg")) {
                String memberPrice = memberProductNoColor.getText().split("Reg.")[1].trim();
                softly.assertThat(memberPrice.startsWith("$")).as("masterProductMemberPrice1").isEqualTo(true);
                softly.assertThat(memberPrice.replace("$", "").replace(".", "").matches("[0-9]+")).as("masterProductMemberPrice2").isEqualTo(true);
            }//end masterProductMemberPriceNoColor
        }

        //member product list masterProductMember AddToBag, AddToList, AddToReg, Quantity
        List<WebElement> memberProductsAll = Elements.findElements(Elements.element("pdp.masterProductMemberView"));
        for(WebElement memberProductAll : memberProductsAll) {
            if(memberProductAll.isDisplayed()){
                softly.assertThat(Elements.elementPresent("pdp.masterProductViewMemberItems")).as("masterProductMemberAddToRegistry").isEqualTo(true);
                softly.assertThat(memberProductAll.findElement(Elements.element("pdp.masterProductMemberAddToList")).isDisplayed()).as("masterProductMemberAddToList").isEqualTo(true);
                softly.assertThat(memberProductAll.findElement(Elements.element("pdp.masterProductMemberQuantity")).isDisplayed()).as("masterProductMemberQuantity").isEqualTo(true);
                softly.assertThat(memberProductAll.findElement(Elements.element("pdp.masterProductMemberAddToBag")).isDisplayed()).as("masterProductMemberAddToBag").isEqualTo(true);
            }
        }


        softly.assertAll();

    }

    @When("^I navigate to product with BOPS available$")
    public void InavigatetoproductwithBOPSavailable() throws Throwable {
        searchProduct("shirts");
        boolean foundBOPS = false;
        int i = 0;
        do {
            I_navigate_to_PDP_with_random_member_product();
            List<WebElement> product_sizes = Elements.findElements(Elements.element("pdp.sizes_array"));
            for(WebElement item : product_sizes){
                Clicks.click(item);
                break;
            }

            Wait.secondsUntilElementPresent("pdp.pickup_in_store_link", 10);
            if(Elements.elementPresent("pdp.pickup_in_store_link")) {
                foundBOPS = true;
            }else{
                Navigate.browserBack();
           }

            if (i == 4) {
                logger.warning(String.format("BOPS Available product not found"));
                break;
            }

            i = i + 1;

        } while (foundBOPS);
    }


    @Given("^I verify select size message$")
    public void I_verify_select_size_message() throws Throwable {
        SoftAssertions softly = new SoftAssertions();

        Wait.secondsUntilElementPresent("pdp.add_to_bag", 5);
        Clicks.click(Elements.element("pdp.add_to_bag"));
        Wait.secondsUntilElementPresent("pdp.selectSizeMessage", 5);
        softly.assertThat(Elements.elementPresent("pdp.selectSizeMessage")).as("selectSizeMessage").isEqualTo(true);
        String selectSize = Elements.findElement(Elements.element("pdp.selectSizeMessage")).getText();
        softly.assertThat(selectSize.contains("Please select a size")).as("selectSizeMessage").isEqualTo(true);

        List<WebElement> disabledSizes = new ArrayList<>();
        disabledSizes = Elements.findElements(Elements.element("pdp.sizeDisabled"));
        if(disabledSizes.size()>0){
            System.out.println(Elements.elementPresent("pdp.viewMoreLink"));
            if(Elements.elementPresent("pdp.viewMoreLink")) {
                Clicks.click(Elements.element("pdp.viewMoreLink"));
            }
            int idx = new Random().nextInt(disabledSizes.size());
            Clicks.click(disabledSizes.get(idx));
            Clicks.click(Elements.element("pdp.add_to_bag"));
            Wait.secondsUntilElementPresent("pdp.selectSizeMessage", 5);
            selectSize = Elements.findElement(Elements.element("pdp.selectSizeMessage")).getText();
            softly.assertThat(selectSize.contains("Sorry, the selected color and size combination is unavailable")).as("selectSizeMessage").isEqualTo(true);
        }
        softly.assertAll();
    }


    @When("^I navigate to PDP with colorway price product$")
    public void InavigatetoPDPwithcolorwaypriceproduct() throws Throwable {
        //navigatetoColorwayPriceProduct();
        int counter = 0;
        boolean found = false;
        do  {
            navigatetoColorwayPriceProduct();
            counter = counter + 1;
            Wait.forPageReady();
            Thread.sleep(2000);
            if(Elements.elementPresent("pdp.colorwayPrice")) {
                found = true;
            } else {
                Navigate.browserBack();
                Wait.forPageReady();
             }
            if (counter >= 10) {
                logger.warning(String.format("Product with colorwayPrice not found"));
                break;
            }
            System.out.println("Elements.elementPresent(pdp.colorwayPrice): " + Elements.elementPresent("pdp.colorwayPrice"));

        } while(found);
        navigatetoPDPCompUrl();
    }


    @Then("^I should see colorway price with CAD Currency$")
    public void IshouldseecolorwaypricewithCADCurrency() throws Throwable {
        SoftAssertions softly = new SoftAssertions();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.colorwayPrice", 20);
        String iship_colorWay_Currency = Elements.findElement(Elements.element("pdp.colorwayPrice")).getText();
        softly.assertThat(iship_colorWay_Currency.contains("CAD")).as("iship currency").isEqualTo(true);
        softly.assertAll();
    }

    @When("^I navigate to colorway product PDP page$")
    public void I_navigate_to_colorway_product_PDP_page() throws Throwable {
        productId = "2351352";
        searchProduct(productId);
        Wait.forPageReady();
        navigatetoPDPCompUrl();
    }

    @Then("^I should not see add to registy button$")
    public void I_should_not_see_add_to_registy_button() throws Throwable {
        softly.assertThat(Elements.elementPresent("pdp.add_to_registry")).as("Add to registry").isEqualTo(false);
        softly.assertAll();
    }

    @Then("^I should not see add to list button$")
    public void I_should_not_see_add_to_list_button() throws Throwable {
        softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("Add to List").isEqualTo(false);
        softly.assertAll();
    }

    @Then("^I should not see egift link$")
    public void I_should_not_see_add_to_egift_link() throws Throwable {
        softly.assertThat(Elements.elementPresent("pdp.egift_this_item")).as("egift this item").isEqualTo(false);
        softly.assertAll();
    }

    @Then("^I should not see availability text$")
    public void I_should_not_see_availability_text() throws Throwable {
        softly.assertThat(Elements.elementPresent("pdp.availability_text")).as("Availability Text").isEqualTo(false);
        softly.assertAll();
    }

    @Then("^I should see pros price with CAD price info$")
    public void I_should_see_pros_price_with_CAD_price_info() throws Throwable {
        Wait.secondsUntilElementPresent("pdp.verticalPros_section", 30);
        String pros_price = Elements.findElement(Elements.element("pdp.veriticalPros_price")).getText();
        softly.assertThat(pros_price.contains("CAD ")).as("Pros price CAD").isEqualTo(true);
        softly.assertAll();
    }

    @Then("^I should see CAD price on quick bag overlay$")
    public void I_should_see_CAD_price_on_quick_bag_overlay() throws Throwable {
        WebElement hoverQB = null;
        try {
            Wait.forPageReady();
            hoverQB = Elements.findElement("quick_bag.quickbag_header");
        } catch (Exception e) {
            System.out.println("Unable to hover on quick bag header");
            e.printStackTrace();
        }
        SoftAssertions softly = new SoftAssertions();
        Clicks.hoverForSelection(hoverQB);
        Wait.secondsUntilElementPresent("quick_bag.quickbag_items_list", 30);
        String quickbag_Content = "";
        if(Elements.elementPresent("quick_bag.quickbag_items_list")) {
            quickbag_Content = Elements.findElement(Elements.element("quick_bag.quickbag_items_list")).getText();
        }else {
            quickbag_Content = Elements.findElement(Elements.element("quick_bag.quickbag_header")).getText();
        }
        softly.assertThat(quickbag_Content.contains("CAD ")).as("QBag Overlay Text price CAD").isEqualTo(true);
        softly.assertAll();
    }

    @When("^I navigate to special offer product PDP page$")
    public void I_navigate_to_special_offer_product_PDP_page() throws Throwable {
        String[] products = {"1965869","2379397","849581"};
        int idx = new Random().nextInt(products.length);
        productId = products[idx];
        searchProduct(productId);
        Wait.forPageReady();
        navigatetoPDPCompUrl();
    }

    @When("^I select special offer details link$")
    public void I_select_special_offer_details_link() throws Throwable {
        Wait.secondsUntilElementPresent("pdp.specialOffersDetailsLink", 30);
        Clicks.click(Elements.element("pdp.specialOffersDetailsLink"));
        Wait.forPageReady();
    }

    @Then("^I should see special offer section expands$")
    public void I_should_see_special_offer_section_expands() throws Throwable {
        String specialOfferText = Elements.findElement(Elements.element("pdp.specialOffersText")).getText();
        String specialOfferTitle = Elements.findElement(Elements.element("pdp.specialOffersTitle")).getText();
        softly.assertThat(specialOfferTitle.contains(specialOfferText)).as("Special Offer Title").isEqualTo(true);
        softly.assertAll();
        Clicks.click(Elements.element("pdp.specialOffersFullDetails"));
        String specialOfferDetails = Elements.findElement(Elements.element("pdp.specialOffersFullDetails")).getText();
        softly.assertThat(specialOfferDetails.toLowerCase().contains("hide full details")).as("Special Offer Details Link").isEqualTo(true);
        softly.assertAll();
    }


/*
    @Given("^I look at Pendant Necklace product mock$")
    public void setup_mock() throws Throwable {
        productId = "792565";
        String path = "/src/test/resources/data/";
        post_data_to_mock_service_for_product_id(productId, path);
        CommonUtils.navigateDirectlyToProduct(productId);
    }

    @Given("^I look at iPhone6 Wristlet product mock$")
    public void I_look_at_iPhone6_Wristlet_product_mock() throws Throwable {
        productId = "2380773";
        String path = "/src/test/resources/data/";
        post_data_to_mock_service_for_product_id(productId, path);
        CommonUtils.navigateDirectlyToProduct(productId);
    }


    public int post(final String host, final String path, final String spec) {
        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity(uri(host, path), spec, Object.class);
        return objectResponseEntity.getStatusCode().value();
    }

    public void delete(final String host, final String path) {
        restTemplate.delete(uri(host, path));
    }


    private URI uri(String host, String path) {
        try {
            return new URI(host + path);
        } catch (URISyntaxException e) {
            throw Throwables.propagate(e);
        }
    }


    public void post_data_to_mock_service_for_product_id(String productId, String path) throws IOException {
        final String dir = System.getProperty("user.dir").replace("\\", "/");
        String json = "";

        if(Boolean.valueOf(System.getenv("mock"))){
            try (FileInputStream inputStream = new FileInputStream(dir + path + productId + ".json")) {
                json = IOUtils.toString(inputStream);
            }
            MainRunner.url = System.getenv("PDP_UI_APP_URL");
            delete(System.getenv("PDP_SERVICE_ENDPOINT"), "/specs");
            post(System.getenv("PDP_SERVICE_ENDPOINT"), "/specs", json);

        }

    }
    */

}
