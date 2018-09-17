package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.Regression.actions.website.DsvActions;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.ProductThumbnail;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by YH03402 on 5/30/2017.
 */
public class ShopAndBrowse extends StepUtils {
    public static final Logger logger = LoggerFactory.getLogger(ShopAndBrowse.class);
    List<WebElement> sortbyList;
    List<String> actualSortbyList = new LinkedList<>();

    @When("^I select different sort options from dropdown in (domestic|iship|registry) mode and Verify (Search|Browse) Page is served from \"([^\"]*)\":$")
    public void i_select_different_sort_options_from_dropdown_and_Verify_Page_is_served_from_Dallas(String mode,String page, String cookieType, DataTable data) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        String page_var = null;
        if (page.equalsIgnoreCase("browse"))
            page_var = "category_browse";
        else if (page.equalsIgnoreCase("search"))
            page_var = "search_result";
        else
            logger.info("Page not specified");
        VerifyDalSteps vds = new VerifyDalSteps();
        List<String> sortType = data.asList(String.class);
        for (String type : sortType) {
            logger.info("Text to select::" + type);
            Wait.forPageReady();

            if (macys()) {
                DropDowns.selectByText(page_var + ".sort_by_select", type);
            } else {
                Wait.forPageReady();
                Clicks.clickIfPresent("navigation.closePopUp");
                if (Elements.elementPresent("product_display.foresee")) {
                    Clicks.clickIfPresent("product_display.foresee_no_thanks");
                }
                Clicks.clickWhenPresent(page_var + ".sort_by_select");
                Wait.untilElementPresent(page_var + ".sort_by_option");
                Clicks.clickElementByText(page_var + ".sort_by_option", type);
            }
            Wait.forLoading(page_var + ".loading_mask");
            shouldBeOnPage(page_var);
            if (macys())
                DsvActions.verifyMcomSorting(mode,type);
            else DsvActions.verifyBcomSorting(mode,type);
            vds.iVerifyThatPageIsServedFromServer(cookieType);
        }
    }

    @Then("^I should be in Category Browse page$")
    public void iShouldBeInCategoryBrowsePage() throws Throwable {
        shouldBeOnPage("category_browse");
    }

    @When("^I select clear all button for '(Color|Price|Brand|Customer Ratings)' facet$")
    public void iSelectClearAllButtonForPriceFacet(String facetName) throws Throwable {
        LeftFacet.selectClearAllForFacet(facetName);
        Wait.forLoading("left_facet.loading");
        Wait.forPageReady();
        logger.info("Selected clear all button for facet: " + facetName);
    }

    @Then("^I should not see selected facets from '(Color|Price|Brand|Customer Ratings)' facet$")
    public void iShouldNotSeeSelectedFacetsFromPriceFacet(String facetName) throws Throwable {
        Assert.assertTrue("ERROR - APP: Facet values are selected in '" + facetName + "' facet", LeftFacet.selectFacetValues(facetName).isEmpty());
        logger.info("Verified all facet values are in de-selected mode for facet: " + facetName);
    }

    @Then("^I should see \"([^\"]*)\" currency for all products in thumbnail grid$")
    public void iShouldSeeAUDCurrencyForAllProductsInThumbnailGrid(String currencyCode) throws
            Throwable {
        JSONArray productList = ProductThumbnail.getProductThumbnailDetails();
        productList.forEach(ele -> {
            JSONObject obj = (JSONObject) ele;
            String[] productPrice = (String[]) obj.get("product_prices");
            Assert.assertTrue("Error App:- Prices are not displaying for product id " + obj.get("product_id"), productPrice != null && productPrice.length >= 1);
            Assert.assertTrue("ERROR - APP: Products are not displaying with iship mode currency:" + currencyCode + " for price value:" + productPrice[0] + " under product id:" + obj.get("product_id"), productPrice[0].contains(currencyCode));
            if (productPrice.length > 1) {
                Assert.assertTrue("ERROR - APP: Products are not displaying with iship mode currency:" + currencyCode + " for Sale price:" + productPrice[1] + " under product id:" + obj.get("product_id"), productPrice[1].contains(currencyCode));
            }
        });
        logger.info("Verified product price currency for all products in thumbnail grid");
    }

    @Then("^I verify the currency \"([^\"]*)\" on product display page$")
    public void iVerifyTheCurrencyOnProductDisplayPage(String currencyCode) throws Throwable {
        Assert.assertTrue("ERROR - APP: ISHIP mode Currency: '" + currencyCode + "", Elements.findElement("product_display.price_box").getText().contains(currencyCode));
        logger.info("Verified product iship currency:'" + currencyCode + "' on PDP page");
    }

    @And("^I select any random facet in facet panel and verify the count with filtered products$")
    public void iSelectAnyRandomFacetInFacetPanel() throws Throwable {
        StepUtils.pausePageHangWatchDog();
        Wait.forPageReady();
        String title;
        String[] facetArray;
        String selectedFacetName = null;
        int leftNavProductCount = 0;
        int pageProductCount = 0;
        Navigate.browserRefresh();
        List<String> facetNames = getAllFacetName();
        facetNames.remove("Pick Up In Store");
        facetNames.remove("Free Pick Up In Store");
        facetNames.remove("Category");
        facetNames = facetNames.stream()
                .filter(e -> !e.contains("Customer"))
                .collect(Collectors.toList());
        Random ran = new Random();
        int index = ran.nextInt(facetNames.size());
        String facetName = facetNames.get(index);
        System.out.println("Facet Name: " + facetName);
       // Navigate.browserRefresh();
        if (!LeftFacet.isExpanded(facetName)) {
            LeftFacet.expandFacet(facetName);
        }
       // logger.info(Elements.paramElement("left_facet.facet_items_list", facetName).toString());
        Wait.isPageLoaded();
        WebElement webElement = null;
        int retry=0;
        while(leftNavProductCount==0 & retry<5) {
            logger.info("Try No::"+retry);
            webElement = Elements.getRandomElement(Elements.paramElement("left_facet.title_facet_items", facetName));
            title = webElement.getAttribute("title");
            logger.info("Title is::" + title);
            facetArray = title.split("\\(");
            selectedFacetName = facetArray[0];
            logger.info("Selected Facet Name::" + selectedFacetName);
            leftNavProductCount = Integer.parseInt(facetArray[1].replace(")", ""));
            logger.info("Left Nav product count::" + leftNavProductCount);
            retry++;
        }webElement.click();
        if (bloomingdales()) {
            Clicks.click(Elements.paramElement("left_facet.title_facet_Apply", facetName));
        }
        Wait.isPageLoaded();
        Wait.forLoading("search_result.loading_mask");
        Wait.untilElementPresent("left_facet.facetBreadcrumbs");
        if (macys()) {
            pageProductCount = Integer.parseInt(Elements.getText("category_browse.product_count"));
        } else {
            pageProductCount = Integer.parseInt(Elements.getText("category_browse.product_count").split(" ")[0]);
        }
        logger.info("Page count after page load::" + pageProductCount);
        Assert.assertEquals("Product count is not same as on facet", pageProductCount, leftNavProductCount);
        String breadCrumbstext = Elements.getText("left_facet.facetBreadcrumbs");
        logger.info("facet_breadcrumbs is::" + breadCrumbstext);
        Assert.assertEquals("Facet Bread Crumbs are not same as selected", selectedFacetName.toLowerCase().trim(), breadCrumbstext.toLowerCase().trim());
        StepUtils.resumePageHangWatchDog();
    }

    private static List<String> getAllFacetName() {
        List<WebElement> facetNames;
        List<String> availableFacetNames = null;
        if (Elements.elementPresent(Elements.element("left_facet.facet_names"))) {
            facetNames = Elements.findElements(Elements.element("left_facet.facet_names"));
            facetNames = facetNames.stream()
                    .filter(el -> !el.getText()
                            .equalsIgnoreCase(""))
                    .collect(Collectors.toList());
            availableFacetNames = facetNames.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } else {
            Assert.fail("ERROR - ENV: Facets are not displayed on left navigation");
        }
        return availableFacetNames;
    }

    @Then("^I should see \"([^\"]*)\" is displayed for the \"([^\"]*)\" brand$")
    public void iShouldSeeIsDisplayedForTheBrand(String condition, String inPage) throws Throwable {
        if(condition.equalsIgnoreCase("customer ratings")){
            Assert.assertTrue("ERROR - DATA: '" + condition + "' does not exist in the " + inPage + " page", Elements.findElement("product_thumbnails.product_thumbnail_with_review").isDisplayed());
        }else{
            Assert.assertTrue("ERROR - DATA: '" + condition + "' does not exist in the " + inPage + " page", Elements.findElement("product_thumbnails.badgeHeader").isDisplayed());
        }
        logger.info("Successfully verified that the " + condition + " is displayed for the " + inPage + " brand");
    }


    @Then("^I \"([^\"]*)\" see the following options in the Sort By list$")
    public void i_see_the_following_options_in_the_Sort_By_list(String arg1, DataTable arg2) throws Throwable {
        String error_message = "ERROR - ENV: #{choice} be able to see #{option} in sort by list";
      /*  List<WebElement> sortbyList;
        List<String> actualSortbyList = null;*/
        Clicks.clickIfPresent("category_browse.sort_by_select");
        if (Elements.findElement(By.xpath("//a[contains(@class,'sortByItens')]")).isDisplayed()) {
            sortbyList = Elements.findElements(By.xpath("//a[contains(@class,'sortByItens')]"));
            actualSortbyList.add(Elements.findElement(By.xpath("//button[@id='sortBy']")).getText());
            for (WebElement ele : sortbyList) {
                String text = ele.getText();
                logger.info("Element::" + text);
                if (!(text.isEmpty())) actualSortbyList.add(text);
            }
            logger.info("ActualSortedList is::" + actualSortbyList);
            Clicks.clickIfPresent("category_browse.sort_by_select");

        } else {
            Assert.fail("ERROR - ENV: List not displayed");
        }
        for (String option : arg2.asList(String.class)) {
            if (arg1.equals("should not")) {
                Assert.assertFalse("Current values are preset in Sorty by list", actualSortbyList.contains(option));
            } else {
                Assert.assertTrue("Current values are not preset in Sorty by list", actualSortbyList.contains(option));
            }
            logger.info("Verified : " + arg1 + " be able to see " + option + " in sort by list");
        }
    }

    @Then("^I verify display order of sort by drop down$")
    public void i_verify_display_order_of_sort_by_drop_down() throws Throwable {
        String[] order = {"Our Top Picks", "New Arrivals", "Best Sellers", "Customer Top Rated", "Price (high-low)", "Price (low-high)"};
        Assert.assertTrue("Sort by option counts are not same", order.length == actualSortbyList.size());
        for (int index = 0; index < order.length; index++) {
            logger.info("Value at index::" + index + "is ::" + order[index] + " and is matched with ::" + actualSortbyList.get(index));
            Assert.assertTrue("Value at index::" + index + "is ::" + order[index] + "and is unmatched with ::" + actualSortbyList.get(index), order[index].equalsIgnoreCase(actualSortbyList.get(index)));
        }
    }

    @Then("^I verify each sort by option on (page|DAL Server)?$")
   // @Then("^I verify each sort by option on DAL Server$")
    public void i_verify_each_sort_by_option(String pagetype,DataTable arg1) throws Throwable {
        selectSortbyDropDown(pagetype,"browse", arg1);
    }


    public void selectSortbyDropDown(String pagetype,String page, DataTable data) throws Throwable {
        VerifyDalSteps vds = new VerifyDalSteps();
        String page_var = null;
        if (page.equalsIgnoreCase("browse"))
            page_var = "category_browse";
        else if (page.equalsIgnoreCase("search"))
            page_var = "search_result";
        else
            logger.info("Page not specified");
        List<String> sortType = data.asList(String.class);
        for (String type : sortType) {
            List<String> old_product_list = new ArrayList<>();
            List<String> new_product_list = new ArrayList<>();
            Wait.forPageReady();
            old_product_list=fetchproductlist();
            logger.info("old top ten product list"+old_product_list);
            logger.info("Text to select::" + type);
            Wait.forPageReady();
            if (macys()) {
                DropDowns.selectByText(page_var + ".sort_by_select", type);
            } else {
                Wait.forPageReady();
                Clicks.clickIfPresent("navigation.closePopUp");
                if (Elements.elementPresent("product_display.foresee")) {
                    Clicks.clickIfPresent("product_display.foresee_no_thanks");
                }
                Clicks.clickWhenPresent(page_var + ".sort_by_select");
                Wait.untilElementPresent(page_var + ".sort_by_option");
                Clicks.clickElementByText(page_var + ".sort_by_option", type);
            }
            Wait.forPageReady();
            Wait.forLoading(page_var + ".loading_mask");
            new_product_list=fetchproductlist();
            logger.info("New top ten product list"+new_product_list);
            shouldBeOnPage(page_var);
            if(pagetype.equalsIgnoreCase("dal server")){
                vds.iVerifyThatPageIsServedFromServer("DAL");
            }
            Assert.assertNotEquals("List of top ten elements not updated",old_product_list,new_product_list);
        }
    }

    public List<String> fetchproductlist() {
        List<String> product_list = new ArrayList<>();
        Wait.forLoading("left_facet.loading");
        int count = 0;
        List<WebElement> ele =  (Elements.elementPresent(By.className("productThumbnail"))) ? Elements.findElements(By.className("productThumbnail"))
                : Elements.findElements(By.xpath("//li[@class='thumbnailItem']/div"));
        for (WebElement product : ele) {
            count++;
            if (!(product.getAttribute("id")).isEmpty()) {
                product_list.add(product.getAttribute("id"));
                if (count == 10) break;
            }
        }
        return product_list;
    }

    @Then("^I verify all media links and popups are not resulting error page$")
    public void iVerifyAllMediaLinksAndPopupsAreNotResultingErrorPage() throws Throwable {
        pausePageHangWatchDog();
        List<String> imageSources = new ArrayList<>();
        List<String> linkHrefs = new ArrayList<>();
        List<String> areaHrefs = new ArrayList<>();
        String hostName = WebDriverManager.getCurrentUrl().split(".com")[0] + ".com";
        List<WebElement> mediaComponents = Elements.findElements("flexible_template.media_component");
        Iterator iterator = mediaComponents.iterator();
        while (iterator.hasNext()){
            WebElement mediaComponent = (WebElement)iterator.next();
            List<WebElement> images = mediaComponent.findElements(By.tagName("img"));
            if(!images.isEmpty())
                imageSources.addAll(images.stream().map(m -> m.getAttribute("src")).collect(Collectors.toList()));
            List<String> links = mediaComponent.findElements(By.tagName("a")).stream().map(m -> m.getAttribute("href")).collect(Collectors.toList());
            if(!links.isEmpty())
                linkHrefs.addAll(links);
            List<String> areaLinks = mediaComponent.findElements(By.tagName("area")).stream().filter(f -> f.getAttribute("href") != null && !f.getAttribute("href").equals("")).map(m -> m.getAttribute("href")).collect(Collectors.toList());
            if(!areaLinks.isEmpty())
                areaHrefs.addAll(areaLinks);
        }
        linkHrefs.forEach(linkUrl -> {
            if(!linkUrl.contains("http") && !linkUrl.contains("javascript:pop"))
                linkUrl = hostName + linkUrl;
            if(linkUrl.contains("javascript:pop"))
                linkUrl = hostName + linkUrl.split("'")[1];
            logger.debug("Media Link URL:"+linkUrl);
            int responseCode = DSVPopularSearchesSteps.getResponseCode(linkUrl);
            Assert.assertTrue("ERROR - APP: Media link URL:"+linkUrl+" is returning response code:"+responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
        });
        imageSources.forEach(imageSrc -> {
            if(imageSrc.contains("asset")){
                logger.debug("Media image src:"+imageSrc);
                int responseCode = DSVPopularSearchesSteps.getResponseCode(imageSrc);
                Assert.assertTrue("ERROR - APP: Media Image Source URL:"+imageSrc+" is returning response code:"+responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
            }else {
                logger.debug("Not verified Media image src:"+imageSrc);
            }
        });
        areaHrefs.forEach(areaHref -> {
            if(!areaHref.contains("http") && !areaHref.contains("javascript:pop"))
                areaHref = hostName + areaHref;
            if(areaHref.contains("javascript:pop"))
                areaHref = hostName + areaHref.split("'")[1];
            logger.debug("Area link URL:" + areaHref);
            int responseCode = DSVPopularSearchesSteps.getResponseCode(areaHref);
            Assert.assertTrue("ERROR - APP: Area link URL:" + areaHref + " is returning response code:" + responseCode, responseCode == 200 || (responseCode >= 300 && responseCode <= 399));
        });
        resumePageHangWatchDog();
        logger.info("Verified all images, links and popups are not resulting any error page");
    }

    @Then("^I verify the facets on Coach left nav$")
    public void iVerifyTheFacetsOnCoachLeftNav() throws Throwable {
        List<String> displayingFacets = LeftFacet.getAllFacetName();
        List<String> expectedFacets = macys() ? Arrays.asList("Special Offers", "Handbag Style", "Color", "Handbag Material", "Handbag Size", "Accessories Type", "Price", "Wallet Style", "Customer Ratings") : Arrays.asList("Collection", "Item Type", "Item Style", "Color", "Price", "Material", "Size", "Sales & Offers");
        Assert.assertTrue("ERROR - DATA: Left Nav Facets not properly ordered on COACH page", displayingFacets.containsAll(expectedFacets));
        logger.info("Facets dispalying order on page is " + displayingFacets + "and expected order is" + expectedFacets + ".Both are matching verified successfully");
    }

    @And("^I verify the subcategories on Coach left nav$")
    public void iVerifyTheSubcategoriesOnCoachLeftNav() throws Throwable {
        List<String> letNavSubCategories;
        List<String> expectedSubCategories = Arrays.asList("Coach Handbags", "Coach Accessories", "Coach Shoes", "Coach Watches", "Coach Sunwear", "Coach Fragrance", "Coach Gifts", "Coach Special Offers");
        Wait.secondsUntilElementPresent("left_facet.categoryHeader", 15);
        letNavSubCategories = Elements.findElements(By.className("subCategory")).stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - ENV: Subcategories in left nav are not properly ordered", letNavSubCategories.equals(expectedSubCategories));
        logger.info("Subcategories dispalying order on page is " + letNavSubCategories + "and expected order is" + expectedSubCategories + ".Both are matching verified successfully");
    }

    @Then("^I verify that item text on Coach Browse Page are displayed$")
    public void iVerifyThatItemTextOnCoachBrowsePageAreDisplayed() throws Throwable {
        String itemText = Elements.findElement("pagination.search_message").getText();
        int productCount = DiscoveryHelper.getProductCount();
        Assert.assertTrue("ERROR - ENV: Text is incorrect", itemText.contains("items in"));
        Assert.assertTrue("ERROR - ENV: Item count is not displayed", productCount != 0);
        logger.info("Item text field is displayed and count is" + productCount);
    }

    @And("^I navigate to COACH \"([^\"]*)\"$")
    public void iNavigateToCOACH(String subCategory) throws Throwable {
        Clicks.javascriptClick(Elements.findElement(By.linkText(subCategory)));
        logger.info("Navigated to subcategory page" + subCategory + "successfully");
    }

    @Then("^I verify the Coach Product thumbnail$")
    public void iVerifyTheCoachProductThumbnail() throws Throwable {
        Elements.findElements("product_thumbnails.product_thumbnails").forEach(product -> {
            Assert.assertTrue("ERROR - ENV: Product image not displayed", product.findElement(By.tagName("img")).isDisplayed());
            Assert.assertTrue("ERROR - ENV : Product title not displayed", product.findElement(By.className("productDescription")).isDisplayed());
            Assert.assertTrue("ERROR - ENV: Product price not displayed", product.findElement(By.className("priceInfo")).isDisplayed());
        });
        logger.info("Verified the Coach Product thumbnail successfully");
    }

    @And("^I verify that selected sort by \"([^\"]*)\" option persist$")
    public void iVerifyThatSelectedSortByOptionPersist(String sortByValue) throws Throwable {
        Assert.assertTrue("Selected sort by option persist!!", Elements.findElement("search_result.sort_by_select").getText().contains(sortByValue));
        logger.info("Verified the selected sort by option persist!!");
    }

    @Then("^I verify non-shop pattern url links are not resulting error page$")
    public void iVerifyNonShopPatternUrlLinksAreNotResultingErrorPage() throws Throwable {
        List<WebElement> left_nav_links = Elements.findElements("left_navigation_category.subcategory");
        for (WebElement element : left_nav_links) {
            if (!element.getAttribute("href").contains("/shop")) {
                int responseCode = DsvActions.getResponseCode(element.getAttribute("href"));
                logger.info("Response code for leftNavLink : " + left_nav_links + " = " + responseCode);
                Assert.assertTrue("ERROR - APP: Response code for left nav link href is not 200 and Actual Value is " + responseCode, responseCode == 200);
            }
        }
        logger.info("Verified: All non-shop pattern url links are not resulting error page");
    }

    @And("^I verify that custom price facet is removed from price facet section$")
    public void iVerifyThatCustomPriceFacetIsRemovedFromPriceFacetSection() throws Throwable {
        LeftFacet.expandFacet("Price");
        Assert.assertTrue("Custom price facet is removed from price facet section", Elements.findElement("left_facet.customPriceFrom").getText().equalsIgnoreCase("") && Elements.findElement("left_facet.customPriceTo").getText().equalsIgnoreCase(""));
        logger.info("verified that custom price facet is removed from price facet section");
    }

    @And("^I should see results message with H1 element$")
    public void iShouldSeeResultsMessageWithHElement() throws Throwable {
        Assert.assertTrue("ERROR - APP: Results message with H1 element is not displaying text: 'We found '",
                Elements.findElement("pagination.search_message").getText()
                        .contains("We found "));
        Assert.assertTrue("ERROR - APP: Results message with H1 element is not displaying text: ' results for '",
                Elements.findElement("pagination.search_message").getText()
                        .contains(" results for "));
        logger.info("Verified results message with H1 element");
    }
}


