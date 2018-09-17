package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;


/**
 * Created by statavarthy on 5/02/2017.
 */
public class FeaturedFacetValues {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FeaturedFacetValues.class);
    public static String selected_ffv;
    public static String ffv_title;
    public static List<WebElement> ffv_list;
    public static WebElement randomFfv;

    @When("^I select a random FFV$")
    public void iSelectARandomFFV() throws Throwable {
        Wait.untilElementPresent("search_result.ffv_display");
        ffv_list= Elements.findElement("search_result.ffv_display").findElements(By.tagName("button"));
        randomFfv = ffv_list.get(new Random().nextInt(ffv_list.size()-1));
        selected_ffv = randomFfv.getText();
        Clicks.click(randomFfv);
        LOGGER.info("Verified by selecting a random ffv");
    }

    @And("^I select Filter$")
    public void iSelectFilter() throws Throwable {
        Wait.untilElementPresent("search_result.sort_filter");
        Clicks.click("search_result.sort_filter");
        LOGGER.info("Verified by clicking on sort&filter");
    }

    @Then("^I should see FFV displayed as scrollable carousal$")
    public void iShouldSeeFFVDisplayedAsScrollableCarousal() throws Throwable {
        Wait.untilElementPresent("search_result.ffv_display");
        Assert.assertTrue("FeaturedFacetValues is displayed as s scrollable carousal",Elements.findElement("search_result.ffv_display").isDisplayed());
        LOGGER.info("Verified the display of ffv carousal");
    }

    @And("^I should see the See More button$")
    public void iShouldSeeTheSeeMoreButton() throws Throwable {
        Elements.elementShouldBePresent("search_result.see_more");
        LOGGER.info("Verified the display of See More button");
    }

    @And("^I click on See More button$")
    public void iClickOnSeeMoreButton() throws Throwable {
        Clicks.clickElementByText(("search_result.see_more"),"See More");
        LOGGER.info("Verified by clicking the See More button");
    }

    @Then("^I should see the accordion opened with the values exposed$")
    public void iShouldSeeTheAccordionOpenedWithTheValuesExposed() throws Throwable {
        String accordion = Elements.findElement("search_result.exposed_accordion").findElement(By.className("m-facet-accordion-header-text")).getText().toLowerCase();
        Assert.assertTrue(ffv_title.toLowerCase().contains(accordion));
        LOGGER.info("Verified the corresponding accordion open by default with ffv title ");
    }

    @Then("^I should see the chip displayed$")
    public void iShouldSeeTheChipDisplayed() throws Throwable {
        Wait.untilElementPresent("search_result.selected_breadcrumb");
        Assert.assertTrue("Chip is displayed for the selected ffv",Elements.findElement("search_result.selected_breadcrumb").getText().equals(selected_ffv));
        LOGGER.info("Verified the chip displayed");
    }

    @And("^I click on the accordion displayed with selected count$")
    public void iClickOnTheAccordionDisplayedWithSelectedCount() throws Throwable {
        Wait.untilElementPresent("search_result.selected_accordion");
        Clicks.clickElementByText("search_result.selected_accordion","1 Selected");
        LOGGER.info("Verified by clicking the accordion with slected count");
    }

    @And("^I should see “See More” button for (\\d+) or more FFVs$")
    public void iShouldSeeSeeMoreButtonForOrMoreFFVs(int ffv_count) throws Throwable {
        int ffv_count_ui = Elements.findElement("search_result.ffv_display").findElements(By.tagName("button")).size();
        if (ffv_count_ui >= ffv_count) {
            Elements.elementShouldBePresent("search_result.see_more");
            Assert.assertTrue("ERROR - APP: See More is not displayed for 7 or more FFVs", Elements.findElement("search_result.ffv_display").findElements(By.tagName("button")).get(ffv_count_ui - 1).getText().equals("See More"));
        } else {
            Assert.fail("ERROR - DATA: See More is not available for 7 or less FFVs");
        }
        LOGGER.info("Verified the display of See more button");
    }
    @Then("^I should see the FFV carousal based on the threshold product count$")
    public void iShouldSeeTheFFVCarousalBasedOnTheThresholdProductCount() throws Throwable {
        Wait.untilElementPresent("search_result.product_count");
        int product_count;
        if (Elements.elementPresent("search_result.product_count")) {
            product_count = Integer.parseInt(Elements.findElement("search_result.product_count").getText().replaceAll("\\D+", ""));
        } else  {
            product_count = Integer.parseInt(Elements.findElement("category_browse.verify_page").getText().replaceAll("\\D+", ""));
        }
        int threshold_product_count = 30;
        if (product_count >= threshold_product_count) {
            Elements.elementShouldBePresent("search_result.ffv_display");
        } else {
            System.out.print("FFV carousal is not displayed as the product count is below the threshold");
        }
        LOGGER.info("Verified the display of FFV carousal based on the product count threshold ");
    }

    @Then("^I should see the title updated dynamically$")
    public void iShouldSeeTheTitleUpdatedDynamically() throws Throwable {
       Wait.untilElementPresent("search_result.ffv_header");
        String updated_title = Elements.findElement("search_result.ffv_header").getText();
        if(updated_title!= ffv_title)
            System.out.println("FFV title update dynamically");
        else
            System.out.println("FFV title and carousel is not displayed as the product count is below the threshold");
    }

    @Then("^I should see the FFV title above the carousel$")
    public void iShouldSeeTheFFVTitleAboveTheCarousel() throws Throwable {
        Wait.forPageReady();
        Wait.untilElementPresent("search_result.ffv_header");
        ffv_title = Elements.findElement("search_result.ffv_header").findElement(By.tagName("h2")).getText();
        LOGGER.info("Verified the display of FFV title ");
    }

    @When("^I click on X mark next to chip$")
    public void iClickOnXMarkNextToChip() throws Throwable {
       Clicks.click("search_result.check_mark");
        LOGGER.info("Verified the chip removal by clicking on the X mark");
    }

    @Then("^I should not see the chips displayed$")
    public void iShouldNotSeeTheChipsDisplayed() throws Throwable {
        Wait.untilElementNotPresent("search_result.selected_breadcrumb");
        LOGGER.info("Verified the display of chip removal ");
    }

    @And("^I click on clear link in the accordion$")
    public void iClickOnClearLinkInTheAccordion() throws Throwable {
        Clicks.javascriptClick("search_result.clear_link");
        LOGGER.info("Verified by clicking on the clear link ");

    }

    @And("^I click on apply button$")
    public void iClickOnApplyButton() throws Throwable {
        Wait.forPageReady();
        Clicks.click(By.className("m-facet-accordion-apply"));
        LOGGER.info("Verified by clicking on the apply button");

    }
}
