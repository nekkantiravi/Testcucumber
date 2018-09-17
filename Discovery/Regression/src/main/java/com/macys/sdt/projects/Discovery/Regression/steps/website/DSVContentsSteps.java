package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.steps.website.SearchBrowseFacetNavigation;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DSVContentsSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(DSVContentsSteps.class);

    @Then("^I verify the basic attributes of cat splash page$")
    public void iVerifyTheBasicAttributesOfCatSplashPage() {
        Wait.secondsUntilElementPresent("category_menu.category_menu_container",15);
        Assert.assertTrue("ERROR - ENV: Main header is not displayed",
                Elements.elementPresent("category_menu.category_menu_container"));
        Assert.assertTrue("ERROR - ENV: Main footer is not displayed",
                Elements.elementPresent("footer.footer_menu_section"));
        Assert.assertTrue("ERROR - ENV: Left navigation container is not displayed",
                Elements.elementPresent("left_navigation_category.left_navigation_container"));
        Assert.assertTrue("ERROR - ENV: Left navigation container is not displayed",
                Elements.elementPresent("flexible_template.row_one_point_one") || Elements.elementPresent("flexible_template.row_one_point_zero"));
        logger.info("The basic attributes of Category splash page are verified successfully!!");
    }

    @Then("^I verify the basic attributes of Browse page$")
    public void iVerifyTheBasicAttributesOfBrowsePage() {
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
            Wait.secondsUntilElementPresent("left_facet.facets_panel",15);
            Assert.assertTrue("ERROR - ENV: facets panel is not displayed",
                    Elements.elementPresent("left_facet.facets_panel"));
        }
        Assert.assertTrue("Currency not displaying",
                Elements.findElements("product_thumbnails.price").get(0).getText().contains("$"));
        Assert.assertTrue("ERROR - ENV: Sort by option is not available",
                Elements.elementPresent("category_browse.verify_page"));
        int productCount = Integer.parseInt(
                Elements.findElement("category_browse.product_count").getText().split(" ")[0]);
        Assert.assertTrue("ERROR - ENV: Product thumbnail is not displayed", productCount > 0);
        if (bloomingdales()) {
            if (productCount > defaultProdCount) {
                Assert.assertTrue("ERROR - ENV: Pagination option is not available",
                        Elements.elementPresent("category_browse.pagination"));
            }
        }
        logger.info("The basic attributes of Browse page are verified successfully!!");
    }

    @When("^I click on sub category link in flyout from \"([^\"]*)\"$")
    public void iClickOnSubCategoryLinkInFlyoutFrom(String fob) {
        Clicks.hoverForSelection(By.linkText(fob));
        List<WebElement> subCategoryUnderFlyoutLinks = Elements.findElements(
                By.xpath("//*[@class='subnav flyout-on']//a[contains(@href,'shop') and not(contains(@href,'FEATURED'))]"));
        Assert.assertFalse("ERROR - DATA: Sub Category links are not found for :"+fob, subCategoryUnderFlyoutLinks.isEmpty());
        if(fob.equalsIgnoreCase("WHAT'S NEW")){
            for(int i = 0; i < subCategoryUnderFlyoutLinks.size(); i++){
                logger.info("Navigating to sub category "
                        + subCategoryUnderFlyoutLinks.get(i).getText()
                        + " through flyout link.");
                Clicks.click(subCategoryUnderFlyoutLinks.get(i));
                if(Elements.elementPresent("product_thumbnails.thumbnail_wrapper")){
                    break;
                }else{
                    Navigate.browserBack();
                    Clicks.hoverForSelection(By.linkText(fob));
                    subCategoryUnderFlyoutLinks = Elements.findElements(
                            By.xpath("//*[@class='subnav flyout-on']//a[contains(@href,'shop') and not(contains(@href,'FEATURED'))]"));
                }
            }
        }else{
            int randomSubCategoryIndex = subCategoryUnderFlyoutLinks.size() == 0 ? 1 : new Random().nextInt(subCategoryUnderFlyoutLinks.size() - 1);
            logger.info("Navigating to sub category "
                    + subCategoryUnderFlyoutLinks.get(randomSubCategoryIndex).getText()
                    + " through flyout link.");
            Clicks.click(subCategoryUnderFlyoutLinks.get(randomSubCategoryIndex));
        }

    }

    @Then("^I verify the basic attributes of Browse page or Cat Sub Splash page$")
    public void iVerifyTheBasicAttributesOfBrowsePageOrCatSubSplashPage() {
        String categoryPageType = Navigate.execJavascript("return pageSignature").toString();
        if (categoryPageType.contains("catSplash")) {
            logger.info("Verifying attributes of a cat splash page.");
            iVerifyTheBasicAttributesOfCatSplashPage();
        } else {
            logger.info("Verifying attributes of a browse page.");
            iVerifyTheBasicAttributesOfBrowsePage();
        }
    }

    @Then("^I should verify both product counts in tooltip, SDP and Site are same for selected color facet$")
    public void iShouldVerifyBothProductCountsInTooltipSDPAndSiteAreSameForSelectedColorFacet() throws Throwable {
        String selectedFacet = SearchBrowseFacetNavigation.selectedFacetValues.get(0);
        HashMap facetValuesWithProductCount = (HashMap)DiscoveryHelper.getAllFacetValuesWithProductCount("Color");
        String selectedFacetProdCount = (String)facetValuesWithProductCount.get(selectedFacet);
        int uiProductCount = Integer.parseInt(Elements.findElement("pagination.product_count_breadcrumb").getText().split(" ")[0]);
        Assert.assertTrue("ERROR - APP: Selected facet value tooltip count:"+selectedFacetProdCount+" is not matching with displayed product count",
                Integer.parseInt(selectedFacetProdCount) == uiProductCount);
        logger.info("Verifying selected facet value tooltip count:"+selectedFacetProdCount+" with displayed product count.");
//        if(!prodEnv()){
//            String catID = WebDriverManager.getCurrentUrl().split("id=")[1].split("&")[0];
//            TODO :: Servie call is failing. Need to look into it.
//            int serviceProdCount = BrowseService.getProductCountForFacet(catID, "Color", selectedFacet, "SITE", "US");
//            Assert.assertTrue("ERROR - APP: Service product count:"+serviceProdCount+" & selected facet product count:"+selectedFacetProdCount+" values are not matching",
//                    Integer.parseInt(selectedFacetProdCount) == serviceProdCount);
//            Assert.assertTrue("ERROR - APP: Service product count:"+serviceProdCount+" & UI facet product count:"+uiProductCount+" values are not matching",
//                    uiProductCount == serviceProdCount);
//        }
    }
}
