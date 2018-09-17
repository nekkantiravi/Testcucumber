package com.macys.sdt.projects.Marketing.SEOLeftNav.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Marketing.SEOLeftNav.utils.LeftNavService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.macys.sdt.framework.utils.Utils.getFileDataInJson;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;


public class SEOLeftNav extends StepUtils {

    public String slpCategory;
    public List<String> linksData = new ArrayList<>();
    public List<String> mediaData = new ArrayList<>();
    public List<String> staticLinks = new ArrayList<>();
    public List<Map> categoryLinksinDB = new ArrayList<>();
    public String slectedStaticLink;
    public Object slectedCategoryLinkText;
    public Object slectedCategoryLinkID;


    private static final Logger logger = LoggerFactory.getLogger(SEOLeftNav.class);

    @When("^I navigate \"([^\"]*)\" SLP category which is under \"([^\"]*)\" parent category with COPY_BLOCK media in one zero one row$")
    public void iNavigateSLPCategoryWhichIsUnderParentCategoryWithCOPY_BLOCKMediaInOneZeroOneRow(String type, String parent) throws Throwable {
        JSONObject slpCategories = getFileDataInJson(getResourceFile("category.json"));
        slpCategory = slpCategories.getJSONObject("slp_category_list").getJSONObject("slp_parent_category_" + parent).getString("slp_child_category_" + type);
        Navigate.visit(RunConfig.url + "/shop/b?id=" + slpCategory);
        logger.info("Navigated to SLP category:" + slpCategory);
    }
    
    @Then("^I should not see multiple copy blocks on the page in UI$")
    public void iShouldNotSeeMultipleCopyBlocksOnThePageInUI() throws Throwable {
        Assert.assertFalse("ERROR APP: Copy block data is displaying on UI", Elements.elementPresent("flexible_template.copy_block"));
        logger.info("Multiple Copy block data is not displaying on UI");
    }

    @And("^I should not see copy block container in view page source$")
    public void iShouldNotSeeCopyBlockContainerInViewPageSource() throws Throwable {
        logger.info(WebDriverManager.getWebDriver().getPageSource());
        Assert.assertFalse("ERROR APP: Copy block data is displaying on View Source", WebDriverManager.getWebDriver().getPageSource().contains("id=\"copyBlockContainer\""));
        logger.info("Multiple Copy block data is not displaying on View Source");
    }

    @When("^I click Clear All link$")
    public void iClickClearAllLink() throws Throwable {
        Clicks.click("left_facet.clear_all_facets");
        logger.info("Cleared the all the facets");
    }

    @Then("^I should see multiple copy blocks displayed in UI$")
    public void iShouldSeeMultipleCopyBlocksDisplayedInUI() throws Throwable {
        Assert.assertTrue("ERROR APP: Copy block data is not displaying on UI", Elements.elementPresent("flexible_template.copy_block"));
        logger.info("Multiple Copy block data is displaying on UI");
    }

    @When("^I navigate the \"([^\"]*)\" SLP category which is under \"([^\"]*)\" parent category$")
    public void iNavigateTheSLPCategoryWhichIsUnderParentCategory(String type, String parent) throws Throwable {
        JSONObject slpCategories = getFileDataInJson(getResourceFile("category.json"));
        slpCategory = slpCategories.getJSONObject("slp_category_list").getJSONObject("slp_parent_category_" + parent).getString("slp_child_category_" + type);
        Navigate.visit(RunConfig.url + "/shop/b?id=" + slpCategory);
        Navigate.browserRefresh();
        logger.info("Navigated to SLP category:" + slpCategory);
    }

    @And("^I should see multiple copy blocks displayed higher up in the page view source same in the order of the UI$")
    public void iShouldSeeMultipleCopyBlocksDisplayedHigherUpInThePageViewSourceSameInTheOrderOfTheUI() throws Throwable {
        Wait.forPageReady();
        String er = WebDriverManager.getWebDriver().getPageSource();
        List<String> ss = Arrays.asList(er.split("\n"));
        String text = macys() ? "copyBlockContainer" : "copyBlockArea";
        int line = 0;
        if (ss.stream().anyMatch(e -> e.contains("<div id=\"" + text + "\">")))
            line = IntStream.range(0, ss.size()).filter(e -> ss.get(e).contains("<div id=\"" + text + "\">")).findFirst().getAsInt();
        else
            Assert.fail("ERROR - APP : Copy Block data is not displaying on View Source Page");
        Assert.assertTrue("ERROR - APP: Copy Block data is not displaying on View Source Page in given range", (line >= 1250 && line <= 1350));
        logger.info("Copy block data is displaying on View Source Page in given range");
    }

    @Then("^I should see the links ordered in which they were entered in Astra$")
    public void iShouldSeeTheLinksOrderedInWhichTheyWereEnteredInAstra() throws Throwable {
        mediaData = LeftNavService.getMediaGroupData(slpCategory);
        staticLinks = LeftNavService.getStaticLinksData(mediaData);
        staticLinks = Elements.findElements("left_navigation_category.seo_left_nav").stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR APP: Static links data is not matching in DB and UI", Arrays.equals(linksData.toArray(), staticLinks.toArray()));
        logger.info("Static links data is matching in DB and UI");
    }

    @Then("^I should see static links from ASTRA on left nav$")
    public void iShouldSeeStaticLinksFromASTRAOnLeftNav() throws Throwable {
        List<String> staticLinks = Elements.findElements("left_navigation_category.seo_left_nav").stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR APP: Copy block data is not displaying on UI", Arrays.equals(linksData.toArray(), staticLinks.toArray()));
        logger.info("Static links are displaying on left navigation");
    }

    @When("^I select static links$")
    public void iSelectStaticLinks() throws Throwable {
        Random randomtext = new Random();
        slectedStaticLink = staticLinks.get(randomtext.nextInt(staticLinks.size()));
        Clicks.clickElementByText("left_navigation_category.seo_left_nav", slectedStaticLink);
        logger.info("Selected static link name is:" + slectedStaticLink);
    }

    @Then("^I should navigate to appropriate static links$")
    public void iShouldNavigateToAppropriateStaticLinks() throws Throwable {
        List staticlinkdata = LeftNavService.getStaticLinksURL(slectedStaticLink);
        Map expectedURL = (HashMap) staticlinkdata.get(0);
        Assert.assertTrue("ERROR APP: Not navigated to appropriate static link", expectedURL.get("staticturl").toString().contains(WebDriverManager.getCurrentUrl()));
        logger.info("Navigated to appropriate static links");
    }

    @And("^I should see subcategory links from STELLA on left nav below the static links$")
    public void iShouldSeeSubcategoryLinksFromSTELLAOnLeftNavBelowTheStaticLinks() throws Throwable {
        Assert.assertTrue("ERROR APP: Static links are not displaying above the Subcategory link", Elements.elementPresent("left_facet.slp_caterory_before_category_links"));
        logger.info("Static links are displaying above the Subcategory link");
    }

    @And("^I should see the position of the static links above sub-category links$")
    public void iShouldSeeThePositionOfTheStaticLinksAboveSubCategoryLinks() throws Throwable {
        Assert.assertTrue("ERROR APP: Static links are not displaying above the Subcategory link", Elements.elementPresent("left_facet.slp_caterory_before_category_links"));
        logger.info("Static links are displaying above the Subcategory link");
    }

    @And("^I should see facets listed on left nav below the static links$")
    public void iShouldSeeFacetsListedOnLeftNavBelowTheStaticLinks() throws Throwable {
        Assert.assertTrue("ERROR APP: Static links are not displaying above facets", Elements.elementPresent("left_facet.slp_caterory_before_category_links"));
        logger.info("Static links are displaying above facets");
    }

    @And("^I should see the position of the static links above clone category links$")
    public void iShouldSeeThePositionOfTheStaticLinksAboveCloneCategoryLinks() throws Throwable {
        Assert.assertTrue("ERROR APP: Static links are not displaying above clone category", Elements.elementPresent("left_facet.slp_caterory_before_clone_category_links"));
        logger.info("Static links are displaying above clone category");
    }

    @And("^I should see the links ordered in which they were entered in Stella$")
    public void iShouldSeeTheLinksOrderedInWhichTheyWereEnteredInStella() throws Throwable {
        mediaData = LeftNavService.getMediaGroupData(slpCategory);
        categoryLinksinDB = LeftNavService.getCategoryLinksData(mediaData);
        logger.info("Selected category link name is:" + categoryLinksinDB);
        List categoryLinks = Elements.findElements("left_facet.slp_category_links").stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR APP: Category link data is not matching in UI and DB", Arrays.equals(linksData.toArray(), categoryLinks.toArray()));
        logger.info("Category link data is matching in UI and DB");
    }

    @And("^I should see the position of the sub-category links above facets$")
    public void iShouldSeeThePositionOfTheSubCategoryLinksAboveFacets() throws Throwable {
        Assert.assertTrue("ERROR APP: Category links are not displaying above facets", Elements.elementPresent("left_facet.category_link_before_facets"));
        logger.info("Category links are displaying above facets");
    }

    @Then("^I should see category links from STELLA on left nav$")
    public void iShouldSeeCategoryLinksFromSTELLAOnLeftNav() throws Throwable {
        List categoryLinks = Elements.findElements("left_facet.slp_category_links").stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR APP: Category links are not displaying on UI", Arrays.equals(linksData.toArray(), categoryLinks.toArray()));
        logger.info("Category links are displaying on UI");
    }

    @When("^I select subcategory links$")
    public void iSelectSubcategoryLinks() throws Throwable {
        Random random = new Random();
        int number = random.nextInt(categoryLinksinDB.size());
        slectedCategoryLinkText = categoryLinksinDB.get(number).get("text");
        slectedCategoryLinkID = categoryLinksinDB.get(number).get("categoryID");
        Clicks.clickElementByText("left_facet.slp_category_links", (String) slectedCategoryLinkID);
        logger.info("Selected category link is ::" + slectedCategoryLinkText);
    }

    @Then("^I should navigate to appropriate category links$")
    public void iShouldNavigateToAppropriateCategoryLinks() throws Throwable {
        Assert.assertTrue("ERROR APP: Not navigated to appropriate static link", slectedCategoryLinkID.toString().contains(WebDriverManager.getCurrentUrl()));
        logger.info("Navigate to appropriate category links");
    }


    @And("^I should see the position of the sub-category links above clone category$")
    public void iShouldSeeThePositionOfTheSubCategoryLinksAboveCloneCategory() throws Throwable {
        Assert.assertTrue("ERROR APP: Category links are not displaying above clone category", Elements.elementPresent("left_facet.caterory_link_before_leftnav_clone"));
        logger.info("Category links are displaying above clone category");
    }

    }