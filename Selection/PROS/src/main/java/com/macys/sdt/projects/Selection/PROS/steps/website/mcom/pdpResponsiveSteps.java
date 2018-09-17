package com.macys.sdt.projects.Selection.PROS.steps.website.mcom;

import com.gargoylesoftware.htmlunit.javascript.configuration.WebBrowser;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.utils.BrowsePageServices;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.macys.sdt.projects.Selection.DigitalProductXapi.steps.PDPxAPISteps.getJsonPDPXAIService;

//import com.macys.sdt.projects.Selection.pros.actions.website.mcom.DSVPDPActions;

public class pdpResponsiveSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(PdpSteps.class);
    private static SoftAssertions softly = new SoftAssertions();
    static JSONObject rootJson = null;


    @When("^I navigate to product \"([^\"]*)\" on (desktop|mobile|tablet)$")
    public void i_navigate_to_product(String product, String device) throws Throwable {
        switch (device) {
            case "desktop":
                Navigate.visit("https://www.qa20codemacys.fds.com/shop/product/lenox-eternal-gold-3-piece-serving-set?ID="+product+"&CategoryID=7919&cm_kws=22804");
            case "mobile":
                Navigate.visit("https://m.qa20codemacys.fds.com/shop/product/lenox-eternal-gold-3-piece-serving-set?ID="+product+"&CategoryID=7919&cm_kws=22804");
        }


    }

    @When("^I verify \"([^\"]*)\" recommendation panel on (desktop|mobile|tablet)$")
    public void i_verify_recommendation_panel_on(String panel, String device) throws Throwable {
        //Navigate.visit("https://www.qa17codemacys.fds.com/shop/product/lenox-eternal-gold-3-piece-serving-set?ID=22804&CategoryID=7919&cm_kws=22804");
        //Navigate.visit("https://www.qa20codemacys.fds.com/shop/product/lenox-eternal-gold-3-piece-serving-set?ID=22804&CategoryID=7919&cm_kws=22804");
        Navigate.visit("http://localhost:8081/test/product/seo-friendly-name?ID=1853846");

        Wait.forPageReady();

        switch (device) {
            case "desktop":
                switch (panel){
                    case "vertical":
                        Wait.secondsUntilElementPresent("pros_pdp.carouselVertical", 20);
                        softly.assertThat(Elements.elementPresent(Elements.element("pros_pdp.carouselVertical")));
                        softly.assertAll();
                        break;
                    case "horizontal":
                        Wait.forPageReady();
                        scrollToLazyLoadElement("pros_pdp.carouselHorizontal");
                        Wait.secondsUntilElementPresent("pros_pdp.carouselHorizontal", 20);
                        softly.assertThat(Elements.elementPresent(Elements.element("pros_pdp.carouselHorizontal")));
                        softly.assertAll();
                        break;
                }
            break;

            case "mobile":
                switch (panel){
                    case "vertical":
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVertical")).as("carouselVertical").isEqualTo(false);
                        softly.assertAll();
                        break;

                    case "horizontal":
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselHorizontal")).as("carouselHorizontal").isEqualTo(true);
                        softly.assertAll();
                        break;
                }
            break;

            case "tablet":

            break;

        }
    }


    @When("^I should see recommendation products in \"([^\"]*)\" panel on (desktop|mobile|tablet)$")
    public void i_should_see_recommendation_products_on(String panel, String device) throws Throwable {
        switch (device) {
            case "desktop":
                switch (panel){
                    case "vertical":
                        Wait.secondsUntilElementPresent("pros_pdp.carouselVertical", 20);
                        int pros_products_count = 0;
                        pros_products_count = Elements.findElements(Elements.element("pros_pdp.carouselVerticalProducts")).size();
                        List<WebElement> pros_product_ids = Elements.findElements(Elements.element("pros_pdp.carouselVerticalProductInView"));

                        for(WebElement item : pros_product_ids){
                            String product_id = item.getAttribute("id");

                            verifyProsProductReview("vertical", product_id);
                            verifyProsProductImage("vertical", product_id);
                            verifyProsProductPrice("vertical", product_id);

                        }

                        softly.assertThat(pros_products_count>0).isEqualTo(true);
                        softly.assertAll();
                        break;


                    case "horizontal":
                        //add code to verify horizontal panel
                        scrollToLazyLoadElement("pros_pdp.carouselHorizontal");
                        Wait.secondsUntilElementPresent("pros_pdp.carouselHorizontal", 20);

                        int pros_products_count_horizontal = 0;
                        pros_products_count_horizontal = Elements.findElements(Elements.element("pros_pdp.carouselHorizontalProducts")).size();

                        List<WebElement> pros_product_id_horizontal = Elements.findElements(Elements.element("pros_pdp.carouselHorizontalProductsId"));
                        for(WebElement item : pros_product_id_horizontal) {
                            String product_id = item.getAttribute("id");
                            verifyProsProductReview("horizontal", product_id);
                            verifyProsProductImage("horizontal", product_id);
                            verifyProsProductPrice("horizontal", product_id);
                        }
                        softly.assertThat(pros_products_count_horizontal>0).isEqualTo(true);
                        softly.assertAll();
                        break;
                }
            break;

            case "mobile":
                switch (panel){
                    case "vertical":
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVertical")).as("carouselVertical").isEqualTo(false);
                        softly.assertAll();
                        break;
                    case "horizontal":
                        int pros_products_count_horizontal = 0;
                        pros_products_count_horizontal = Elements.findElements(Elements.element("pros_pdp.carouselHorizontalProducts")).size();

                        List<WebElement> pros_product_id_horizontal = Elements.findElements(Elements.element("pros_pdp.carouselHorizontalProductsId"));
                        for(WebElement item : pros_product_id_horizontal) {
                            String product_id = item.getAttribute("id");
                            verifyProsProductReview("horizontal", product_id);
                            verifyProsProductImage("horizontal", product_id);
                            verifyProsProductPrice("horizontal", product_id);
                        }
                        softly.assertThat(pros_products_count_horizontal>0).isEqualTo(true);
                        softly.assertAll();
                        break;
                }
            break;

            case "tablet":

            break;


        }

    }

    //panel = vertical/horizontal
    private void verifyProsProductReview(String panel, String prodId) {
        String review_count = "";

        //verify review
        //check if product has reviews using xapi
        try {
            rootJson = getJsonPDPXAIService(prodId);
            review_count = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("reviewStatistics").getJSONObject("aggregate").get("count").toString());
        } catch (Exception e) {
            System.out.println("No review found");
        }


        //find same product in pros list and verify review
        if (!(review_count == "")){
            List<WebElement> prosList = Elements.findElements(By.cssSelector("div#pros-"+panel+" li[aria-hidden='false']"));

            for(WebElement item : prosList){
                String item_id = item.findElement(By.cssSelector("div.productThumbnail")).getAttribute("id");
                if(item_id.equals(prodId)){
                    softly.assertThat(item.findElement(By.cssSelector("div.pdpreviews")).isDisplayed()).as("prosReviewLink").isEqualTo(true);
                }

            }

        }else{
            //should be uncomment when xapi env and current env are the same
            //softly.assertThat(Elements.findElements(By.xpath(review_xpath)).size()).as("prosReviewLink").isEqualTo(0);
        }
        softly.assertAll();
    }

    //panel = vertical/horizontal
    private void verifyProsProductPrice(String panel, String prodId){
        //verify pros price
        List<WebElement> prosList = Elements.findElements(By.cssSelector("div#pros-"+panel+" li[aria-hidden='false']"));

        for(WebElement item : prosList){
            String item_id = item.findElement(By.cssSelector("div.productThumbnail")).getAttribute("id");
            if(item_id.equals(prodId)){
                String price = item.findElement(By.cssSelector("div.priceInfo")).getText();

                softly.assertThat(price.replace("$", "").
                                        replace(".", "").
                                        replace("-","").matches("[0-9]+")).as("pros price").isEqualTo(true);
            }

        }
        softly.assertAll();
    }

    //panel = vertical/horizontal
    private void verifyProsProductImage(String panel, String prodId){
           //verify pros image
        List<WebElement> prosList = Elements.findElements(By.cssSelector("div#pros-"+panel+" li[aria-hidden='false']"));

        for(WebElement item : prosList){
            String item_id = item.findElement(By.cssSelector("div.productThumbnail")).getAttribute("id");
            if(item_id.equals(prodId)){
                softly.assertThat(item.findElement(By.cssSelector("img.thumbnailImage")).isDisplayed()).as("thumbnailImage").isEqualTo(true);
            }

        }
        softly.assertAll();
    }




    @When("^I should see scrolling for \"([^\"]*)\" recommendation panel on (desktop|mobile|tablet)$")
    public void i_should_see_scrolling_products_on(String panel, String device) throws Throwable {
        switch (device) {
            case "desktop":
                switch (panel){
                    case "vertical":
                        //verify PrevArrow not appears and NextArrow appears
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVerticalPrevArrowEnable")).as("carouselVerticalPrevArrowEnableFalse").isEqualTo(false);
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVerticalNextArrow")).as("carouselVerticalNextArrow").isEqualTo(true);

                        List<WebElement> pros_product_ids = Elements.findElements(Elements.element("pros_pdp.carouselVerticalProductInView"));

                        softly.assertThat(pros_product_ids.size() == 3).as("defaultProsNumberOfProducts").isEqualTo(true);

                        Clicks.click(Elements.element("pros_pdp.carouselVerticalNextArrow"));
                        Thread.sleep(2000);
                        List<WebElement> pros_product_ids_next = Elements.findElements(Elements.element("pros_pdp.carouselVerticalProductInView"));

                        softly.assertThat(pros_product_ids_next.size() == 3).as("ProsNumberOfProductsAfterNext").isEqualTo(true);
                        //verify both PrevArrow and NextArrow appears
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVerticalPrevArrowEnable")).as("carouselVerticalPrevArrowEnableTrue").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVerticalNextArrow")).as("carouselVerticalNextArrow").isEqualTo(true);

                        Clicks.click(Elements.element("pros_pdp.carouselVerticalNextArrow"));
                        Thread.sleep(2000);
                        Clicks.click(Elements.element("pros_pdp.carouselVerticalNextArrow"));
                        Thread.sleep(2000);
                        Clicks.click(Elements.element("pros_pdp.carouselVerticalNextArrow"));
                        Thread.sleep(2000);
                        //verify NextArrow not appears
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVerticalNextArrowEnable")).as("carouselVerticalNextArrowEnableFalse").isEqualTo(false);

                        softly.assertAll();
                        break;



                    case "horizontal":
                        scrollToLazyLoadElement("pros_pdp.carouselHorizontal");
                        Wait.secondsUntilElementPresent("pros_pdp.carouselHorizontal", 20);
                        //verify both PrevArrow and NextArrow appears
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselHorizontalRightArrow")).as("carouselHorizontalRightArrow").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselHorizontalLeftArrowDisabled")).as("carouselHorizontalLeftArrowDisabled").isEqualTo(false);


                        Clicks.click(Elements.element("pros_pdp.carouselHorizontalRightArrow"));
                        Thread.sleep(2000);
                        Clicks.click(Elements.element("pros_pdp.carouselHorizontalRightArrow"));
                        Thread.sleep(2000);
                        Clicks.click(Elements.element("pros_pdp.carouselHorizontalRightArrow"));
                        Thread.sleep(2000);

                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselHorizontalRightArrowDisabled")).as("carouselHorizontalRightArrowDisabled").isEqualTo(false);
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselHorizontalLeftArrow")).as("carouselHorizontalLeftArrow").isEqualTo(true);

                        softly.assertAll();
                        break;
                }

                break;

            case "mobile":
                switch (panel){
                    case "vertical":
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVertical")).as("carouselVertical").isEqualTo(false);
                        softly.assertAll();
                        break;

                    case "horizontal":
                        //add code to verify horizontal panel
                        //should be same as desktop
                        break;
                }
            break;

            case "tablet":

            break;


        }

    }

    @When("^I verify navigation to the corresponding PDP from \"([^\"]*)\" panel on (desktop|mobile|tablet)$")
    public void i_verify_navigation_to_the_corresponding_PDP(String panel, String device) throws Throwable {
        switch (device) {
            case "desktop":
                switch (panel){
                    case "vertical":
                        Navigate.browserRefresh();
                        Wait.forPageReady();
                        Wait.secondsUntilElementPresent("pros_pdp.carouselVerticalProductInView", 20);
                        List<WebElement> pros_product_ids = Elements.findElements(Elements.element("pros_pdp.carouselVerticalProductInView"));
                        String product_id = pros_product_ids.get(0).getAttribute("id");
                        rootJson = getJsonPDPXAIService(product_id);

                        String product_description = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("name").toString();
                        List<WebElement> prosList = Elements.findElements(By.cssSelector("div#pros-"+panel+" li[aria-hidden='false']"));

                        for(WebElement item : prosList){

                            try {
                            Wait.secondsUntilElementPresent(By.cssSelector("div.productThumbnail"), 20);
                            String item_id = item.findElement(By.cssSelector("div.productThumbnail")).getAttribute("id");
                                if(item_id.equals(product_id)){
                                    item.click();
                                }
                            } catch (Exception e) {
                                System.out.println("No pros product found");
                            }

                                Wait.forPageReady();

                        }

                        String product_title = Elements.findElement(Elements.element("pros_pdp.pdpProductTitle")).getText();
                        //softly.assertThat(product_description).as("productTitle").isEqualTo(product_title);
                        softly.assertThat(product_title.contains(product_description)).as("productTitle").isEqualTo(true);
                        softly.assertAll();
                    break;



                    case "horizontal":
                        List<WebElement> pros_product_list = Elements.findElements(Elements.element("pros_pdp.carouselHorizontalProductsList"));
                        String h_product_id = pros_product_list.get(0).getAttribute("id");
                        rootJson = getJsonPDPXAIService(h_product_id);
                        String xapi_product_description = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("name").toString();

                        Clicks.click(Elements.element("pros_pdp.carouselHorizontalLeftArrow"));
                        Thread.sleep(2000);
                        Clicks.click(Elements.element("pros_pdp.carouselHorizontalLeftArrow"));
                        Thread.sleep(2000);
                        Clicks.click(Elements.element("pros_pdp.carouselHorizontalLeftArrow"));
                        Thread.sleep(2000);

                        Clicks.click(Elements.findElement(By.cssSelector("div#horizontalProsCarousel a[id='"+ h_product_id +"']")));
                        Wait.forPageReady();

                        String h_product_title = Elements.findElement(Elements.element("pros_pdp.pdpProductTitle")).getText();
                        softly.assertThat(xapi_product_description).as("productTitle").isEqualTo(h_product_title);
                        softly.assertAll();
                        break;


                }
                break;

            case "mobile":
                switch (panel){
                    case "vertical":
                        softly.assertThat(Elements.elementPresent("pros_pdp.carouselVertical")).as("carouselVertical").isEqualTo(false);
                        softly.assertAll();
                        break;

                    case "horizontal":
                        //add code to verify horizontal panel
                        //should be same as desktop
                        break;
                }
                break;

            case "tablet":

            break;

        }

    }


    @When("^I verify recommendation panel for zsr on (desktop|mobile|tablet)$")
    public void i_verify_recommendation_panel_for_zsr(String device) throws Throwable {
        switch (device) {
            case "desktop":
              //verify on zsr page
                String search_result_msg = Elements.findElement("pros_pdp.searchResultStr").getText();
                String expected_search_result_msg = "We found 0 results for zzz";
                softly.assertThat(search_result_msg).as("search_result_msg").isEqualTo(expected_search_result_msg);

                //verify pros bar contains products
                int pros_products_count_zsr = 0;
                Wait.secondsUntilElementPresent("pros_pdp.carouselHorizontalProductsListZSR", 20);
                pros_products_count_zsr = Elements.findElements(Elements.element("pros_pdp.carouselHorizontalProductsListZSR")).size();
                softly.assertThat(pros_products_count_zsr>0).isEqualTo(true);

                softly.assertAll();

            case "mobile":
                //should be same as desktop



            case "tablet":


        }

    }


    @When("^I verify grid recommendation panel on (desktop|mobile|tablet)$")
    public void i_verify_grid_recommendation_panel(String device) throws Throwable {
        switch (device) {
            case "desktop":

                //verify grid is not appears
                scrollToLazyLoadElement("pros_pdp.gridProducts");
                Wait.secondsUntilElementPresent("pros_pdp.gridProducts", 10);
                softly.assertThat(Elements.elementPresent("pros_pdp.gridProducts")).as("gridProducts").isEqualTo(false);

                softly.assertAll();

            case "mobile":
                //verify grid is appears and show 4 products
                String grid_prod_selector = "div#mb-j-product-recommendations-container-grid div.m-product-recommended-product";
                int grid_product_list = Elements.findElements(By.cssSelector(grid_prod_selector)).size();
                softly.assertThat(grid_product_list==4).isEqualTo(true);
                softly.assertThat(Elements.elementPresent(By.cssSelector(grid_prod_selector))).as("grid_prod_selector").isEqualTo(true);
                softly.assertAll();

            case "tablet":

        }

    }

    @When("^I verify navigation to the corresponding PDP from grid panel on (desktop|mobile|tablet)$")
    public void I_verify_navigation_to_the_corresponding_PDP_from_grid_panel(String device) throws Throwable {
        switch (device) {
            case "desktop":

                //do nothing: grid should not appears

            case "mobile":
                //verify grid is appears and show 4 products
                String grid_prod_selector = "div#mb-j-product-recommendations-container-grid div.m-product-recommended-product";
                String gridProdId = Elements.findElements(By.cssSelector(grid_prod_selector)).get(0).getAttribute("data-product-id");

                Elements.findElements(By.cssSelector(grid_prod_selector)).get(0).click();
                Wait.forPageReady();
                String url = WebDriverManager.getCurrentUrl();
                softly.assertThat(url.contains("ID="+gridProdId)).isEqualTo(true);
                Elements.findElement(By.cssSelector(".m-product-details-more.m-link")).click();
                softly.assertThat(Elements.elementPresent(By.xpath("//li[contains(text(), '"+gridProdId+"')]"))).as("productID").isEqualTo(true);
                softly.assertAll();

            case "tablet":

        }

    }



}
