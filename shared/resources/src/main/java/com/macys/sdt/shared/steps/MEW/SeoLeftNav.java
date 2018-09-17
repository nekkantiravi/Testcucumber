package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.shared.actions.MEW.pages.Browse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static com.macys.sdt.framework.utils.StepUtils.macys;
import static com.macys.sdt.framework.utils.Utils.getFileDataInJson;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;


public class SeoLeftNav {

    private static final Logger logger = LoggerFactory.getLogger(SeoLeftNav.class);

    @When("^I navigates DLP page through Brand in mobile site$")
    public void iNavigatesDLPPageThroughBrandInMobileSite() throws Throwable {
        String dlpURL = macys() ? "/shop/featured/august-hats" : "/buy/converse%20kids%20sneakers";
        Navigate.visit(RunConfig.url + dlpURL);
        logger.info("Navigated to DLP page sucessfully");
    }

    @Then("^I should see copyblock displayed in UI$")
    public void iShouldSeeCopyblockDisplayedInUI() throws Throwable {
        Wait.secondsUntilElementPresent("flexible_template.copy_block", 60);
        Assert.assertTrue("ERROR - APP : Copy block data is not displaying on Mobile UI", Elements.elementPresent("flexible_template.copy_block"));
        logger.info("Copy block data is displaying on Mobile UI");
    }

    @Then("^I should see copy blocks displayed higher up in the page view source$")
    public void iShouldSeeCopyBlocksDisplayedHigherUpInThePageViewSource() throws Throwable {
        Assert.assertTrue("ERROR - APP: Copy block is not displayed higher up in the page view source", Elements.elementPresent("seo_tag_cloud.copyblock_higher_up"));
    }

    @And("^I should see more option in the copyblock media$")
    public void iShouldSeeMoreOptionInTheCopyblockMedia() throws Throwable {
        Wait.secondsUntilElementNotPresent("flexible_template.copy_block_more_link", 30);
        Assert.assertTrue("ERROR - APP : More option is not displaying on Copy Block", Elements.elementPresent("flexible_template.copy_block_more_link"));
        logger.info("More option is displaying on Copy Block");
    }

    @And("^I click on more option$")
    public void iClickOnMoreOption() throws Throwable {
        Clicks.click("flexible_template.copy_block_more_link");
        logger.info("More option is selected");
    }

    @Then("^I should see the copy block expanded with less option$")
    public void iShouldSeeTheCopyBlockExpandedWithLessOption() throws Throwable {
        Assert.assertTrue("ERROR - APP : Less option is not displaying on Copy Block", Elements.elementPresent("flexible_template.copy_block_less_link"));
        logger.info("Less option is displaying on Copy Block");
    }

    @And("^I click on less option$")
    public void iClickOnLessOption() throws Throwable {
        Clicks.click("flexible_template.copy_block_less_link");
        logger.info("Less option is selected");
    }

    @When("^I navigate to category browse page that has copy block$")
    public void iNavigateToCategoryBrowsePageThatHasCopyBlock() throws Throwable {
        JSONObject categoriesList = getFileDataInJson(getResourceFile("category.json"));
        JSONArray categories = categoriesList.getJSONArray("browse_category_list");
        Random random = new Random();
        int number = random.nextInt(categories.length());
        String category = (String) categories.get(number);
        Navigate.visit(RunConfig.url + "/shop/?id=" + category);
        logger.info("Navigated to category browse " + category);
    }

    @And("^I should see copy block is expanded by default$")
    public void iShouldSeeCopyBlockIsExpandedByDefault() throws Throwable {
        Assert.assertTrue("ERROR - APP : Copy block is not expanded by default", Elements.elementPresent("flexible_template.copy_block_less_link"));
        logger.info("Copy block is expanded by default");
    }

    @When("^I navigate the \"([^\"]*)\" SLP category$")
    public void iNavigateTheSLPCategory(String copy_block_data) throws Throwable {
        JSONObject slpCategories = getFileDataInJson(getResourceFile("category.json"));
        JSONArray categories = slpCategories.getJSONArray(copy_block_data);
        Random random = new Random();
        int number = random.nextInt(categories.length());
        String slpcategory = (String) categories.get(number);
        Navigate.visit(RunConfig.url + "/shop/b?id=" + slpcategory);
        logger.info("Navigated to SLP category browse " + slpcategory);
    }

    @Then("^I should see each copy block is collapsed by default$")
    public void iShouldSeeEachCopyBlockIsCollapsedByDefault() throws Throwable {
        Assert.assertTrue("ERROR - APP : Copy block is not collapsed by default", Elements.elementPresent("flexible_template.copy_block_more_link"));
        logger.info("Copy block is collapsed by default");
    }

    @Then("^I should not see copy block on the page$")
    public void iShouldNotSeeCopyBlockOnThePage() throws Throwable {
        Wait.secondsUntilElementNotPresent("flexible_template.copy_block", 30);
        Assert.assertFalse("ERROR - APP : Copy block data is displaying on Mobile UI", Elements.elementPresent("flexible_template.copy_block"));
        logger.info("Copy block data is not displaying on Mobile UI");
    }

    @And("^I deselect all facet values in facet panel$")
    public void iDeselectAllFacetValuesInFacetPanel() throws Throwable {
        Clicks.click("left_facet.clear_all_facets");
        Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
        Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
        logger.info("Cleared facet value");
    }

    @When("^I click second page in pagination$")
    public void iClickSecondPageInPagination() throws Throwable {
        Wait.secondsUntilElementPresent("pagination.goto_current_page_number", 10);
        int maxPageNumber = Integer.parseInt(Elements.getText("pagination.last_page_number_in_pagination"));
        Assert.assertFalse("ERROR - DATA : Pagination is not available!!", maxPageNumber <= 0);
        Random rand = new Random();
        // First two page numbers are displaying in UI in all cases.
        int randNumber = 1 + rand.nextInt(2);
        if (randNumber == 1) {
            randNumber = randNumber + 1;
        }
        if (macys()) {
            Browse browse = new Browse();
            Clicks.clickElementByText("pagination.page_number_links", String.valueOf(randNumber));
            Wait.secondsUntilElementPresent("pagination.page_number_links", 30);
            Assert.assertEquals("Pagination functionality is not working properly!!", browse.getCurrentPageNumber(), (randNumber));
        } else {
            DropDowns.selectByIndex("pagination.pages", randNumber);
            Wait.secondsUntilElementPresent("pagination.pages", 60);
//            Assert.assertEquals("Pagination functionality is not working properly!!", Integer.parseInt(Elements.getText("pagination.goto_current_page_number").trim()), (randNumber));
        }
        logger.info("Navigated page number is " + randNumber);
    }

    @When("^I click first page in pagination$")
    public void iClickFirstPageInPagination() throws Throwable {
        if (macys()) {
            Browse browse = new Browse();
            Clicks.clickElementByText("pagination.page_number_links", "1");
            Wait.secondsUntilElementPresent("pagination.page_number_links", 60);
            Assert.assertEquals("Pagination functionality is not working properly!!", browse.getCurrentPageNumber(), (1));
        } else {
            DropDowns.selectByIndex("pagination.pages", 1);
            Wait.secondsUntilElementPresent("pagination.pages", 60);
//            Assert.assertEquals("Pagination functionality is not working properly!!", Integer.parseInt(Elements.getText("pagination.goto_current_page_number").trim()), (1));
        }
        logger.info("Navigated to first page in pagination");

    }
}