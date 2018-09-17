package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.steps.website.SearchBrowseFacetNavigation;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategoryBrowse;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.ProductThumbnail;
import com.macys.sdt.shared.steps.website.Facets;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class BotEngineSteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(BotEngineSteps.class);

    private CategoryBrowse categoryBrowse = new CategoryBrowse();
    private SearchBrowseFacetNavigation searchBrowseFacetNavigation =  new SearchBrowseFacetNavigation();
    private ProductThumbnail productThumbnail = new ProductThumbnail();
    private String userAgent = RunConfig.getEnvOrExParam("user_agent");
    private Facets facets = new Facets();

    @Then("^I verify product crawl for pagination action$")
    public void iVerifyProductCrawlForPaginationAction() throws Throwable {
        Thread.sleep(15000);
        int pageCount = categoryBrowse.getPageCount();
        if (pageCount > 1) {
            JSONArray oldProductList = productThumbnail.getProductThumbnailDetails();
            int currentPageNumber = categoryBrowse.getCurrentPageNumber();
            categoryBrowse.gotoPageNumber(currentPageNumber + 1);
            if (categoryBrowse.getCurrentPageNumber() != (currentPageNumber + 1)) {
                Assert.fail("Pagination functionality is not working properly!!");
            }
            Assert.assertTrue("ERROR - APP: Products are not crawled for 'Pagination' action through user_agent:"+userAgent, Navigate.execJavascript("return navigator.userAgent").equals(userAgent));
            Thread.sleep(10000);
            JSONArray newProductList = productThumbnail.getProductThumbnailDetails();
            Assert.assertFalse("ERROR - APP: Products are not updated for pagination with user-agent:"+userAgent, newProductList.toString().equals(oldProductList.toString()));
            currentPageNumber = categoryBrowse.getCurrentPageNumber();
            categoryBrowse.gotoPageNumber(currentPageNumber - 1);
            if (categoryBrowse.getCurrentPageNumber() != (currentPageNumber - 1)) {
                Assert.fail("Pagination functionality is not working properly!!");
            }
            Assert.assertTrue("ERROR - APP: Products are not crawled for 'Pagination' action through user_agent:"+userAgent, Navigate.execJavascript("return navigator.userAgent").equals(userAgent));
            Thread.sleep(10000);
            newProductList = productThumbnail.getProductThumbnailDetails();
            Assert.assertTrue("ERROR - APP: Products are not updated for pagination with user-agent:"+userAgent, newProductList.toString().equals(oldProductList.toString()));
        }else{
            logger.info("Pagination is not available. Hence products crawl functionality not verified with user-agent:"+userAgent);
        }
    }

    @And("^I verify product crawl for below sort options:$")
    public void iVerifyProductCrawlForBelowSortOptions(List<String> sortByList) throws Throwable {
        Navigate.browserRefresh();
        Wait.forPageReady();
        Thread.sleep(15000);
        List<String> sortByOptions;
        if (safari()) {
            Wait.secondsUntilElementPresent("pagination.sort_by", 15);
        }
        if (bloomingdales()) {
            String defaultText = Elements.getText("pagination.sort_by");
            sortByOptions = DropDowns.getAllCustomValues("pagination.sort_by", "pagination.sort_by_options");
            sortByOptions.add(0, defaultText);
        } else {
            Wait.untilElementPresent(Elements.element("pagination.sort_by"));
            sortByOptions = DropDowns.getAllValues("pagination.sort_by");
        }
        if (sortByOptions == null || !(sortByOptions.size() == sortByList.size())) {
            Assert.fail("sort by list options are not displayed correctly!!");
        }

        boolean foundMatch;
        for (String option : sortByList) {
            //Observed that, In website, Sort by options values are varying for products and thus failing assertions
            //Example: Feature file has "Price: Low to High", where as in Website, it is listed as "Price: (low to high)"
            foundMatch = CommonUtils.matchSimilarSortBy(sortByOptions, option);
            if (!foundMatch) {
                Assert.fail("sort by (" + option + ") option is not displayed in page!!");
            }
        }
        if (bloomingdales()) {
            sortByOptions.remove(0);
        }
        JSONArray oldProductList = productThumbnail.getProductThumbnailDetails();
        categoryBrowse.sortByValue(sortByOptions.get(new Random().nextInt(sortByOptions.size())));
        Thread.sleep(10000);
        Assert.assertTrue("ERROR - APP: Products are not crawled for 'Sort By' action through user_agent:"+userAgent, Navigate.execJavascript("return navigator.userAgent").equals(userAgent));
        JSONArray newProductList = productThumbnail.getProductThumbnailDetails();
        Assert.assertFalse("ERROR - APP: Products are not updated for 'Sort By' action with user-agent:"+userAgent, newProductList.toString().equals(oldProductList.toString()));
    }

    @And("^I verify product crawl for \"([^\"]*)\" item from \"([^\"]*)\" facet on left nav$")
    public void iVerifyProductCrawlForItemFromFacetOnLeftNav(String selected_item, String facet) throws Throwable {
        JSONArray oldProductList = null;
        if (facet.equalsIgnoreCase("BOPS")){
            Thread.sleep(20000);
            oldProductList = productThumbnail.getProductThumbnailDetails();
            facets.I_select_any_bops_facet_value();
        }else{
            oldProductList = productThumbnail.getProductThumbnailDetails();
            searchBrowseFacetNavigation.iSelectTheFirstColorInTheColorFacet("1", null, facet);
        }
        pausePageHangWatchDog();
        Assert.assertTrue("ERROR - APP: Products are not crawled for '"+facet+"' action through user_agent:"+userAgent, Navigate.execJavascript("return navigator.userAgent").equals(userAgent));
        Thread.sleep(10000);
        JSONArray newProductList = productThumbnail.getProductThumbnailDetails();
        Assert.assertFalse("ERROR - APP: Products are not updated for '"+facet+"' action with user-agent:"+userAgent, newProductList.toString().equals(oldProductList.toString()));
    }

    @Then("^I verify RE API - EnvironmentDetails$")
    public void iVerifyREAPIEnvironmentDetails() throws Throwable {
        Assert.assertTrue("ERROR - APP: HF_NodeJS IP is not returning from RE App service", EnvironmentDetails.otherApp("hf_nodejs").ipAddress != null);
        Assert.assertTrue("ERROR - APP: HF_xAPI IP is not returning from RE App service", EnvironmentDetails.otherApp("hf_xapi").ipAddress != null);
        Assert.assertTrue("ERROR - APP: MSPDiscovery IP is not returning from RE App service", EnvironmentDetails.otherApp("mspdiscovery").ipAddress != null);
        Assert.assertTrue("ERROR - APP: NAVAPP_JBOSS IP is not returning from RE App service", EnvironmentDetails.otherApp("navapp_jboss").ipAddress != null);
        Assert.assertTrue("ERROR - APP: APOLLO IP is not returning from RE App service", EnvironmentDetails.otherApp("apollo").ipAddress != null);
        Assert.assertTrue("ERROR - APP: MSPCONTENT IP is not returning from RE App service", EnvironmentDetails.otherApp("mspcontent").ipAddress != null);
        Assert.assertTrue("ERROR - APP: DiscoveryPagesUI IP is not returning from RE App service", EnvironmentDetails.otherApp("discoverypagesui").ipAddress != null);
        Assert.assertTrue("ERROR - APP: DiscoveryPagesXAPI IP is not returning from RE App service", EnvironmentDetails.otherApp("discoverypagesxapi").ipAddress != null);
        logger.info("Verified RE API infor for discovery domain components");
    }
}