package com.macys.sdt.projects.Discovery.Regression.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.shared.actions.MEW.panels.Pagination;
import com.macys.sdt.projects.Discovery.Regression.utils.config.MEW.SearchAndBrowseActions;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.framework.utils.StepUtils.shouldBeOnPage;

public class DesignerIndexSteps {

    private static final Logger logger = LoggerFactory.getLogger(DesignerIndexSteps.class);
    public static int product_count;

    @And("^I should see products without applied selected facet values on DLP page$")
    public void iShouldSeeProductsWithoutAppliedSelectedFacetValuesOnDLPPage() throws Throwable {
        Assert.assertTrue("Product count is matched with the initial product count",product_count==SearchAndBrowseActions.getProductCount());
        logger.info("Verified the product count when applied facets cleared from DLP page");
    }

    @When("^I click \"([^\"]*)\" on the alphabet picker$")
    public void iClickOnTheAlphabetPicker(String value) {
        Wait.untilElementPresent("designer_index.alphabet_wrapper");
        WebElement alphabetWrapper = Elements.findElement("designer_index.alphabet_wrapper");
        WebElement selectedLetter = alphabetWrapper.findElement(By.cssSelector("div[data-menu-item='" + value + "']"));
        logger.info("Scrolling the page so the letter \"{}\" on the alphabet picker is in view and clickable", value);
        Navigate.execJavascript("arguments[0].scrollIntoView()", selectedLetter);
        Clicks.click(selectedLetter);
        logger.info("Clicked on \"{}\" on the alphabet picker", value);
    }

    @Then("^I should see the page scrolled to the selected \"([^\"]*)\"$")
    public void iShouldSeeThePageScrolledToTheSelected(String value) {
        // Faster to filter through the sections first instead of streaming through all the designer_links
        // WebElement designerThatShouldBeInView = Elements.findElements("designer_index.designer_links").stream().filter(e -> e.getText().startsWith(value)).findFirst().orElse(null);
        WebElement designerSection = Elements.findElements("designer_index.designers_sections")
                .stream()
                .filter(e -> e.findElement(By.cssSelector("h4")).getText().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
        Assert.assertNotNull("designerSection is null", designerSection);
        WebElement designerThatShouldBeInView = designerSection.findElements(By.className("b-j-designer-link")).get(0);
        Assert.assertTrue("The page did not scroll to the selected letter", Elements.isElementInView(designerThatShouldBeInView));
        logger.info("Verified that the page has scrolled to the selected letter: {}", value);
    }

    @Then("^I verify the functionality after clicking \"([^\"]*)\" on the alphabet picker$")
    public void iVerifyTheFunctionalityAfterClickingOnTheAlphabetPicker(String value) {
        iClickOnTheAlphabetPicker(value);
        iShouldSeeThePageScrolledToTheSelected(value);
    }

    @And("^I click on scroll to the top button$")
    public void iClickOnScrollToTheTopButton() {
        Clicks.click("designer_index.scroll_top_button");
        logger.info("Clicked on the scroll to the top button");
    }

    @When("^I navigate to \"([^\"]*)\" page$")
    public void i_navigate_to_all_designer_index_page(String page) throws Throwable {
        Navigate.execJavascript("arguments[0].scrollIntoView(true);", Elements.findElement(By.linkText(page)));
        Clicks.javascriptClick(Elements.findElement(By.linkText(page)));
        Wait.forPageReady();
        logger.info("Navigating to all designers page from home page");
        if (Elements.findElement("home.main_navigation_button").getAttribute("tabindex").equalsIgnoreCase("0")) {
            GlobalNav.closeGlobalNav();
            Thread.sleep(1000);
        }
    }

    @When("^I click on back button on PDP page and navigate to DLP page$")
    public void iClickOnBackButtonOnPDPPageAndNavigateToDLPPage() throws Throwable {
        WebDriverManager.getWebDriver().navigate().back();
        shouldBeOnPage("dynamic_landing");
        logger.info("Verified dynamic landing page after clicking back button on PDP page");
    }

    @And("^I select a \"([^\"]*)\" from the list$")
    public void iSelectAFromTheList(String designer) throws Throwable {
        shouldBeOnPage("brand_index");
        Navigate.execJavascript("arguments[0].scrollIntoView(true);", Elements.findElement(By.linkText(designer)));
        Clicks.click(Elements.findElement(By.linkText(designer)));
        Wait.forPageReady();
        Thread.sleep(3000);
        logger.info("Navigating to selected brand dlp page");
    }

    @Then("^I should be on DLP page$")
    public void iShouldBeOnDLPPage() throws Throwable {
        shouldBeOnPage("dynamic_landing");
        logger.info("Navigated to the DLP Page");
    }

    @And("^pagination should be displayed$")
    public void paginationDisplayedOnDlpPage() throws Throwable {
        Wait.forPageReady();
        Assert.assertTrue("Pagination is not displayed on dlp page ",
                Elements.findElement("pagination.select_page_no").isDisplayed());
        logger.info("Verified pagination is displayed on dlp page");
    }

    @And("^I should see all products displayed in DLP page$")
    public void iShouldSeeAllProductsDisplayedInDLPPage() throws Throwable {
        Elements.elementShouldBePresent("dynamic_landing.dlp_page_content");
        logger.info("Products displayed in selected DLP page");
    }

    @And("^I should see pagination displayed for more than (\\d+) products$")
    public void iShouldSeePaginationDisplayedForMoreThanProducts(int product_count) throws Throwable {
        String count_ui = Elements.findElement("dynamic_landing.product_count").getText().replaceAll("\\D+", "");
        int product_count_ui = Integer.parseInt(count_ui);
        if (product_count_ui > product_count) {
            Elements.elementShouldBePresent("pagination.select_page_no");
            logger.info("Pagination is displayed as the count is greater than 90");
        } else {
            logger.info("Pagination is not displayed as the count is less than 90");
        }
    }

    @When("^I navigate to next page on (BROWSE|SEARCH|DLP) page$")
    public void iNavigateToNextOnPage(String page) throws Throwable {
        Navigate.execJavascript("arguments[0].scrollIntoView(true);", Elements.findElement("pagination.next_page"));
        Wait.secondsUntilElementPresent("pagination.next_page", 5);
        Clicks.javascriptClick(Elements.findElement("pagination.next_page"));
        logger.info("Navigated to next page successfully");
        Wait.forPageReady();
    }

    @And("^I navigate to prev page on (BROWSE|SEARCH|DLP) page$")
    public void iNavigateToPrevOnPage(String page) throws Throwable {
        Navigate.execJavascript("arguments[0].scrollIntoView(true);", Elements.findElement("pagination.previous_page"));
        Wait.secondsUntilElementPresent("pagination.previous_page", 5);
        Clicks.javascriptClick(Elements.findElement("pagination.previous_page"));
        logger.info("Navigated to previous page successfully");
        Wait.forPageReady();
    }

    @When("^I select \"([^\"]*)\" page on page$")
    public void iNavigateOnPage(String nev) throws Throwable {
        WebDriverManager.getWebDriver().navigate().refresh();
        Pagination.selectPagination(nev);
        Wait.forPageReady();
        logger.info("Selected" + nev + "  button");
    }
}
