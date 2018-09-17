package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by YH03402 on 6/2/2017.
 */
public class SearchSteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(SearchSteps.class);

    @Then("^I waited for Search Page to load$")
    public void i_waited_for_Search_Page_to_load() throws Throwable {
        Clicks.clickIfPresent("navigation.closePopUp");
        Wait.forPageReady();
        shouldBeOnPage("search_result");
        Wait.forLoading("search_result.loading_mask");
    }


    @When("^I navigate to (first|second|last) page from (top|bottom) pagination$")
    public void iNavigateToFirstPageFromtopPagination(String position, String type) throws Throwable {
        String paginationtype = "";
        Elements.elementShouldBePresent("pagination.pages");
        switch (type) {
            case "top":
                paginationtype = "pagination.top_pagination_section";
                break;
            case "bottom":
                paginationtype = "pagination.bottom_pagination_section";
                break;
            default:
                logger.info("wrong pagination type selected");
                break;
        }

        List<WebElement> pages = Elements.findElements(Elements.element(paginationtype));
        if (bloomingdales() && type.equalsIgnoreCase("top")) {
            Clicks.click("pagination.top_pagination_dropdown");
        } else if (bloomingdales() && type.equalsIgnoreCase("bottom")) {
            Clicks.click("pagination.bottom_pagination_dropdown");
        }
        if (position.equals("first")) {
            pages = pages.stream()
                    .filter(el -> !el.getText().equals("1"))
                    .collect(Collectors.toList());
            // if size is 0, we are already on the first page as the selector excludes the current page
            if (pages.size() != 0) {
                Clicks.click(pages.get(0));
            }
        } else if (position.equals("second")) {
            pages = pages.stream()
                    .filter(el -> !el.getText().equals("1"))
                    .collect(Collectors.toList());
            if (pages.size() != 0) {
                Clicks.click(pages.get(1));
            }
        } else {
            pages = pages.stream()
                    .filter(el -> !el.getText().isEmpty())
                    .collect(Collectors.toList());
            Clicks.click(pages.get(pages.size() - 1));
        }
        Wait.forPageReady();
        Wait.forLoading(By.id("loading_c"));
    }

    @Given("^I search a random product for mode (domestic|registry|iship)")
    public void i_search_a_random_product_for_mode(String mode) throws Throwable {
        String[] allSearchText;
        Random random = new Random();
        if (!mode.equalsIgnoreCase("registry")) {
            allSearchText = new String[]{"dresses", "boots", "jackets", "shirts"};
        } else {
            allSearchText = new String[]{"plates", "vacuum", "cutlery"};
        }
        String randomtext = allSearchText[random.nextInt(allSearchText.length)];
        ShopAndBrowse.I_search_for(randomtext);
        Wait.forPageReady();
    }

    @And("^I should see page navigated to \"([^\"]*)\" pattern url$")
    public void iShouldSeePageNavigatedToPatternUrl(String urlPattern) throws Throwable {
        Thread.sleep(25000);
        String currentURL = WebDriverManager.getWebDriver().getCurrentUrl();
        if(urlPattern.contains(" || ")){
            String[] urlPatterns = urlPattern.split(" || ");
            Assert.assertTrue("ERROR - APP: Expected url pattern"+urlPatterns+" is not matched for current page:"+currentURL, (currentURL.contains(urlPatterns[0]) || currentURL.contains(urlPatterns[1])));
        }else{
            Assert.assertTrue("ERROR - APP: Expected url pattern"+urlPattern+" is not matched for current page:"+currentURL, currentURL.contains(urlPattern));
        }

        logger.info("Verifying url pattern:'"+urlPattern+"' for current navigated page:"+currentURL);
    }

    @And("^I should see basic attributes of Search Results page$")
    public void iShouldSeeBasicAttributesOfSearchResultsPage() throws Throwable {
        int defaultProdCount = macys() ? 60 : 90;
        Assert.assertTrue("ERROR - ENV: Main header is not displayed",
                Elements.elementPresent("category_menu.category_menu_container"));
        Assert.assertTrue("ERROR - ENV: Main footer is not displayed",
                Elements.elementPresent("footer.footer_menu_section"));
        if (url().contains("all-designers")) {
            logger.info("All-designers pages do not have facets, sorting, or products.");
            return;
        }
        if (!url().contains("gift-cards")) {
            Assert.assertTrue("ERROR - ENV: facets panel is not displayed",
                    Elements.elementPresent("left_facet.facets_panel"));
        }
        Assert.assertTrue("Currency not displaying",
                Elements.findElements("product_thumbnails.price").get(0).getText().contains("$"));
        Assert.assertTrue("ERROR - ENV: Sort by option is not available",
                Elements.elementPresent("search_result.sort_by_select"));
        int productCount = Integer.parseInt(
                Elements.findElement("search_result.product_count").getText().split(" ")[0]);
        Assert.assertTrue("ERROR - ENV: Product count is not displayed above thumbnail grid", productCount > 0);
        if (productCount > defaultProdCount) {
            Assert.assertTrue("ERROR - ENV: Pagination option is not available", DiscoveryHelper.paginationAvailable());
        }
        Assert.assertTrue("ERROR - ENV: Product with review & ratings not found in product thumbnail",
                Elements.elementPresent("product_thumbnails.product_thumbnail_with_review"));
        logger.info("The basic attributes of Browse page are verified successfully!!");
    }

    @Then("^I should be landed on Zero Results Page$")
    public void iShouldBeLandedOnZeroResultsPage() throws Throwable {
        Wait.forPageReady();
        shouldBeOnPage("zero_search_result");
        Assert.assertTrue("ERROR - ENV: Zero Results Page is not displayed",
                onPage("zero_search_result"));
        logger.info("Verified Zero Results Pages landing.");
    }
}
