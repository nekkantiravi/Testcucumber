package com.macys.sdt.projects.Discovery.SearchBrowseComponentizationOldExp.utils.config;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.ScenarioHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.ProductThumbnail;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.CategoryBrowse;
import com.macys.sdt.shared.steps.website.PageNavigation;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import org.apache.commons.collections4.ListUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;
import net.lightbody.bmp.core.har.HarRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DiscoveryHelper extends StepUtils{

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryHelper.class);

    private static Random randomGenerator;
    private static int scenarioCount = 0;

    /*
        Return total thumbnail count displayed
        Will navigate to last page using pagination and will return the total product displayed on thumbnails.
        @return int total thumbnail count
     */
    public static int getTotalThumbnailCount() {
        int actTotalProductsCount = 0;
        try {
            //Declarations
            int productPerPage;
            int pageCount;
            int productCountInLastPage;
            productPerPage = getProductIds().size();
            if (paginationAvailable()) {
                pageCount = getTotalPageCount();
                String url = PageNavigation.url();
                logInfo("Page url before navigating to last page: "+ url);
                navigateToLastPage();
                productCountInLastPage = getProductIds().size();
                actTotalProductsCount = productPerPage * (pageCount - 1);
                actTotalProductsCount = actTotalProductsCount + productCountInLastPage;
                Navigate.visit(url);
            } else {
                actTotalProductsCount = productPerPage;
            }
        } catch (Exception e) {
            Assert.fail("Error fetching product count details in all pages.\r\n" + e.getMessage());
        }
        logInfo("Total product displayed on thumbnails: " + actTotalProductsCount);
        return actTotalProductsCount;
    }

    /*
       Returns total page number count displayed
       if no pagination is available by default will return 1
    */
    public static int getTotalPageCount() {
        int pageCount = 1;
        if (paginationAvailable()) {
            if (macys()) {
                List<WebElement> pageLinks = Elements.findElements("category_browse.goto_each_number_link");
                for (WebElement link : pageLinks) {
                    if (link.getText().matches("[0-9]+") && Integer.parseInt(link.getText()) > pageCount) {
                        pageCount = Integer.parseInt(link.getText());
                    }
                }
            } else {
                if(Elements.elementPresent("leftFacetNavigation_panel.currentPage"))
                    pageCount = Integer.parseInt(Elements.getText("leftFacetNavigation_panel.currentPage").split(" of ")[1]);
            }
        }
        logInfo("Total pages displayed on pagination: " + pageCount);
        return pageCount;
    }

    /*
        Returns true if pagination section is available else false
     */
    public static boolean paginationAvailable(){
        boolean flag;
        if (macys()){
            flag = Elements.elementPresent("category_browse.goto_current_page_number");
        }
        else{
            flag = Elements.elementPresent("leftFacetNavigation_panel.pageNavigator");
        }
        logInfo("Pagination available: " +  flag);
        return flag;
    }

    /*
        Navigate to given page number
     */
    public static void goToPageNumber(int pageNumber){
        if (paginationAvailable()) {
            CategoryBrowse categoryBrowse = new CategoryBrowse();
            if (macys()) {
                categoryBrowse.gotoPageNumber(pageNumber);
            } else {
                Clicks.click("leftFacetNavigation_panel.pageNavigator");
                WebElement element = Elements.findElement("leftFacetNavigation_panel.pageNavigator");
                List<WebElement> dropdown = element.findElements(By.tagName("li"));
                List<String> dropDownValues = element.findElements(By.tagName("li")).stream().map(WebElement::getText)
                        .collect(Collectors.toList());
                if (pageNumber==getCurrentPageNumber()) {
                    System.out.println("Already in given page number !");
                    logInfo("Already in the given page number !" + pageNumber);
                }
                else {
                    String currentPageFormat = pageNumber + " of " + getTotalPageCount();
                    int index = dropDownValues.indexOf(currentPageFormat);
                    Clicks.click(dropdown.get(index));
                }
            }
            logInfo("Navigated to the given page number: "+ pageNumber);
        }
        else
        {
            System.out.println("No pagination is available");
        }
    }

    /*
        Return the current page number
     */
    public static int getCurrentPageNumber(){
        int currentPageNumber = 1;
        if (paginationAvailable()) {
            if (macys()) {
                return Integer.parseInt(Elements.getText("category_browse.goto_current_page_number"));
            } else {
                return Integer.parseInt(Elements.getText("leftFacetNavigation_panel.currentPage").split(" of ")[0]);
            }
        }
        else return currentPageNumber;
    }

    /*
        Void method used to navigate last page
     */
    public static void navigateToLastPage() {
        CategoryBrowse categoryBrowse = new CategoryBrowse();
        int pageNumber = categoryBrowse.getPageCount();
        try {
            if (macys()) {
                WebElement element = Elements.findElement(By.xpath(".//*[contains(@class,'paginationSpacer') and contains(.,'" + pageNumber + "')]"));
                Clicks.click(element);
                Wait.untilElementPresent("category_browse.loading_mask");
                Wait.untilElementNotPresent("category_browse.loading_mask");
            }
            else{
                Clicks.click("leftFacetNavigation_panel.pageNavigator");
                WebElement element = Elements.findElement("leftFacetNavigation_panel.pageNavigator");
                List<WebElement> dropdown = element.findElements(By.tagName("li"));
                Clicks.click(dropdown.get(dropdown.size()-1));
            }

        } catch (Exception e) {
            Assert.fail("Error Navigating to Page" + pageNumber);
        }
        logInfo("Navigated to last page of the pagination: "+ pageNumber);
    }

    /*
        Return true if bread crumb is displayed for the given facet values
        @param List<String> facet values need to be verified.
     */
    public static Boolean breadCrumbVerification(List<String> facetValues) {
        //Declarations
        Boolean flag = false;
        List<String> breadCrumbsText;
        List<WebElement> breadcrumbsRemove;
        try {
            Wait.untilElementPresent("leftFacetNavigation_panel.facetBreadcrumbs");
            breadCrumbsText = Elements.findElements(Elements.element("leftFacetNavigation_panel.facetBreadcrumbs")).stream()
                    .map(WebElement::getText).collect(Collectors.toList());
            Collections.sort(breadCrumbsText);
            Collections.sort(facetValues);
            logInfo("Displayed breadcrumbs: " + breadCrumbsText);
            logInfo("Expected facet values: " + facetValues);
            flag = facetValues.equals(breadCrumbsText);
            breadcrumbsRemove = Elements.findElements(Elements.element("leftFacetNavigation_panel.facetRemoveBreadcrumb"));
            for (WebElement e: breadcrumbsRemove) flag = e.isDisplayed();
        } catch (Exception e) {
            Assert.fail("Error Fetching Breadcrumbs details " + "\r\n" + e.getMessage());
        }
        return flag;
    }

    /*
        Returns true if clear All button is visible else false
     */
    public Boolean displayClearAllButton() {
        //Declarations
        Boolean flag = false;
        try {
            if (Elements.elementPresent("leftFacetNavigation_panel.clearAllButton")) {
                flag = true;
            }
        } catch (Exception e) {
            Assert.fail("Error displaying Clear All Button " + "\r\n" + e.getMessage());
        }
        return flag;
    }

    /*
        Clicks clear All button
     */
    public void clickClearAllButton() {
        WebElement element;
        try {
            if (displayClearAllButton()) {
                element = Elements.findElement(Elements.element("leftFacetNavigation_panel.clearAllButton"));
                element.click();
                Wait.forPageReady();
            }
            else {
                Assert.fail("Clear All button is not displayed in the page");
            }
        } catch (Exception e) {
            Assert.fail("Error Clicking Clear All Button \r\n" + e.getMessage());
        }
    }

    /*
        Void method used to collapseAll Expanded Facets
     */
    public static void collapseAllExpandedFacets() {
        //Declarations
        List<WebElement> facetsElementsList;
        try {
            facetsElementsList = Elements.findElements(Elements.element("left_facet.facet_names"));
            for (WebElement ele: facetsElementsList){
                String facetName = ele.getText();
                if (facetName.equalsIgnoreCase("")){
                    if (ele.getAttribute("title").contains("Collapse")){
                        ele.click();
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail("Error collapsing expanded facets" + e.getMessage());
        }
        logInfo("Expanded all collapsed facets");
    }


    /*
       Returns Random Fact Names which is displayed.
       @Example:
            String faceName = DiscoveryHelper.getRandomFacetName();
            facetName #=> "Price"
       @return String contains facet name
    */
    public static String getRandomFacetName() {
        List<WebElement> facetNames;
        List<String> availableFacetNames = null;
        randomGenerator = new Random();
        int index;
        if (Elements.elementPresent(Elements.element("left_facet.facet_names"))) {
            facetNames = Elements.findElements(Elements.element("left_facet.facet_names"));
            facetNames = facetNames.stream().filter(el -> !el.getText().equalsIgnoreCase("")).collect(Collectors.toList());
            availableFacetNames = facetNames.stream().map(WebElement::getText).collect(Collectors.toList());
            logInfo("Available facet names on left nav" + availableFacetNames);
            availableFacetNames = availableFacetNames.stream().filter(name -> !name.contains("Size")).collect(Collectors.toList());
            availableFacetNames.remove("Pick Up In Store");
            availableFacetNames.remove("Brand");
            availableFacetNames.remove("Category");
            availableFacetNames = availableFacetNames.stream().filter(name -> !name.contains("Customer")).collect(Collectors.toList());
        }
        assert availableFacetNames != null;
        index = randomGenerator.nextInt(availableFacetNames.size());
        logInfo("Random Facet Name from available facet names on left nav: " + availableFacetNames.get(index));
        return availableFacetNames.get(index);
    }

    /*
        Returns list of all Facet Names displayed.
        @Example:
            List<String> facetNames = DiscoveryHelper.getAllFacetName()
                 facetNames   #=>    "Special Offers",
                                     "Size",
                                    "Brand",
                                    "Color",
                                    "Price",
                                    "Customers' Top Rated"
        @return List<String> a list of facet names displayed on the page.
     */
    public static List<String> getAllFacetName() {
        List<WebElement> facetNames;
        List<String> availableFacetNames = null;
        if (Elements.elementPresent(Elements.element("left_facet.facet_names"))) {
            facetNames = Elements.findElements(Elements.element("left_facet.facet_names"));
            facetNames = facetNames.stream().filter(el -> !el.getText().equalsIgnoreCase("")).collect(Collectors.toList());
            availableFacetNames = facetNames.stream().map(WebElement::getText).collect(Collectors.toList());
        }
        logInfo("Available all facet names on left nav" + availableFacetNames);
        return availableFacetNames;
    }

    /*
        Returns Items per page count
        This method is used only for MCOM since item per page section is not available for BCOM
        @Example:
                String itemsPerPage = DiscoveryHelper.getItemsPerPage()
                    itemsPerPage #=> 40
        @return String since All is displayed as String
     */
     public static String getItemsPerPage(){
        WebElement ele = ProductThumbnail.getproductThumbnailColumns() == 3 ?
                Elements.findElement("pagination.three_column_items_section") : Elements.findElement("pagination.four_column_items_section");
        WebElement selectedItemElement = ele.findElements(By.tagName("div")).stream()
                .filter(e->e.getAttribute("class").contains("selected")).findFirst().orElse(null);
        logInfo("Selected items per page count: "+ selectedItemElement.getText());
        return selectedItemElement.getText();
    }

    /*
        Returns single Facet value for the given Facet Name.
        @Example:
            facetValue = DiscoveryHelper.getRandomFacetValue("Color")
            facetValue => "Black"
        @return String facet value of the given Facet Name.
     */
    public static String getRandomFacetValue(String facetName) {
        List<WebElement> facetValues;
        List<String> availableFacetValues = null;
        randomGenerator = new Random();
        int index;
        try {
            LeftFacet.expandFacet(facetName);
            if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet(facetName);
            }
            WebElement ele = Elements.findElement(LeftFacet.getFacetDiv(facetName));
            ele = ele.findElement(Elements.element("leftFacetNavigation_panel.facet_item"));
            if (ele.isDisplayed()) {
                facetValues = Elements.findElement(LeftFacet.getFacetDiv(facetName)).findElements(By.tagName("a"));
                facetValues = facetValues.stream().filter(el->!el.getText().equals("")).collect(Collectors.toList());
                facetValues = facetValues.stream().filter(el -> !el.getAttribute("id").equals("bopsLocation")).collect(Collectors.toList());
                facetValues = facetValues.stream().filter(el -> !el.getAttribute("class").contains("selected")).collect(Collectors.toList());
                facetValues = facetValues.stream().filter(el -> !el.findElement(Elements.element("leftFacetNavigation_panel.facet_item")).getText().equalsIgnoreCase("")).collect(Collectors.toList());
                availableFacetValues = facetValues.stream().map(el -> el.findElement(Elements.element("leftFacetNavigation_panel.facet_item")).getText().split("\\[")[0].trim()).collect(Collectors.toList());
            }
            else {
                Assert.fail("No element with facet value found for facet name: "+ facetName);
            }
        }
        catch (Exception e){
            Assert.fail("Unable to get random facet value"+ e.getMessage());
        }

        assert availableFacetValues != null;
        logInfo("Available facet values under "+ facetName + " are " + availableFacetValues);
        index = randomGenerator.nextInt(availableFacetValues.size());
        logInfo("Random facet value for the given facet name "+ facetName + " facet is " + availableFacetValues.get(index));
        return availableFacetValues.get(index);
    }

    /*
       Returns All the product ids displayed in the page
       @Example :
            List<String> prodIds = DiscoveryHelper.getProductIds();
                        prodId #=> "12345",
                                    "76894874",
                                    "322323"
        @return List<String> of product ids on the page
    */
    public static List<String> getProductIds() {
        List<String> productIds;
        productIds = new ArrayList<>();
        try {
            Wait.untilElementPresent("productThumbnailPanel.product_thumbnails");
            List<WebElement> thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));
            productIds = thumbnails.stream().map(e->e.getAttribute("id")).collect(Collectors.toList());
        } catch (NoSuchElementException e) {
            Assert.fail("Product thumbnails are not displayed" + e);
        }
        logInfo("Displayed product ids: " + productIds);
        return productIds;
    }

    /*
       Returns All the brand facet Names from brand facet section including All Brands and Featured Brands
       @Example :
                List<String> allBrands = DiscoveryHelper.getAllBrandFacetName();
                            allBrands   #=> "Polo"
                                            "Nike"
       @return List<String> of facet Names from brand facet section
     */
    public static List<String> getAllBrandFacetName() {
        List<String> allBrands = null;
        try {
            //Declaration
            List<WebElement> featuredBrandsList;
            List<WebElement> allBrandsList;
            List<WebElement> brands = new ArrayList<>();
            LeftFacet.expandFacet("Brand");
            Elements.findElements("leftFacetNavigation_panel.expand_all_brand_facet").get(0).click();
            featuredBrandsList = Elements.findElement(Elements.element("leftFacetNavigation_panel.featured_brand_values")).findElements(By.tagName("li"));
            allBrandsList = Elements.findElement(Elements.element("leftFacetNavigation_panel.all_brand_list")).findElements(By.tagName("li"));
            brands.addAll(featuredBrandsList);
            brands.addAll(allBrandsList);
            allBrands = brands.stream().map(el -> el.findElement(By.className("item")).getText()).collect(Collectors.toList());
        } catch (Exception e) {
            Assert.fail("Error performing multiple facets from single facet \r\n" + e.getMessage());
        }
        logInfo("Displayed Facet values under All Brands section: "+ allBrands);
        return allBrands;
    }

    /*
        Returns list of all Brand Names displayed under brand facet under Featured Brand Section
        @Example :
                List<String> featuredBrands = DiscoveryHelper.getFeaturedBrandNames();
                            featuredBrands   #=> "Polo"
                                            "Nike"
       @return List<String> of facet Names from brand facet section
     */
    public static List<String> getFeaturedBrandNames(){
        List<String> featuredBrands = null;
        try {
            WebElement ele;
            List<WebElement> featuredBrandsList;
            ele = Elements.findElement(Elements.element("leftFacetNavigation_panel.featured_brand_values"));
            if (ele == null){
                featuredBrands = getAllFacetValues("Brand");
            }else {
                featuredBrandsList = ele.findElements(By.tagName("li"));
                featuredBrands = featuredBrandsList.stream().map(el -> el.findElement(By.className("item"))
                        .getText()).collect(Collectors.toList());
            }
            logInfo("Displayed Featured Brands: " + featuredBrands);
        }catch(Exception e){
            Assert.fail("Error in getting featured brand facet names \r\n" + e.getMessage());
        }
        return featuredBrands;
    }

    /*
       Returns list of all Brand Names displayed under brand facet under ALL Brand Section
       @Example :
               List<String> allBrands = DiscoveryHelper.getAllBrandNames();
                           allBrands   #=> "Polo"
                                           "Nike"
      @return List<String> of facet Names from brand facet section
    */
    public static List<String> getAllBrandNames(){
        List<String> allBrands = null;
        try {
            List<WebElement> allBrandsList;
            allBrandsList =  Elements.findElement(Elements.element("leftFacetNavigation_panel.all_brand_list")).findElements(By.tagName("li"));
            allBrands = allBrandsList.stream().map(el -> el.findElement(By.className("item"))
                    .getText()).collect(Collectors.toList());
            logInfo("Displayed All Brand Names: " + allBrands);
        }catch(Exception e){
            Assert.fail("Error in getting all brand facet names \r\n" + e.getMessage());
        }
        return allBrands;
    }

    /*
        Returns all facet values names for the given facet name
        @Example:
                List<WebElement> facetItems = DiscoveryHelper.getAllFacetValues("Color");
                            facetItems #=> "Blue",
                                            "Green",
                                            "Pink",
                                            "Multi"
        @return List<WebElement> of facet values for given facet name
     */
    public static List<String> getAllFacetValues(String facet) {
        List<String> facetsList = new ArrayList<>();
        List<WebElement> facetsListElements;
        WebElement facetsValuesList;
        try {
            facetsValuesList = Elements.findElement(LeftFacet.getFacetDiv(facet));
            if (!LeftFacet.isExpanded(facet)) {
                LeftFacet.expandFacet(facet);
            }
            facetsListElements = facetsValuesList.findElements(By.tagName("li")).stream()
                    .filter(e-> !e.getAttribute("class").equals(" disabled")).collect(Collectors.toList());
            //facetsListElements = facetsListElements.stream().filter(e-> !e.getAttribute("class").contains("selected")).collect(Collectors.toList());
            facetsList = facetsListElements.stream().map(e->e.getText().split("\\(")[0].split("\\[")[0].trim()).collect(Collectors.toList());
            facetsList = facetsList.stream().filter(e -> !e.equals("")).collect(Collectors.toList());
        } catch (Exception e) {
            Assert.fail("Unable to select facet item from "+ facet + e.getMessage());
        }
        logInfo("Available facet values under "+ facet + " facet are " + facetsList);
        return facetsList;
    }

    /*
        Select Quick view for the given product id
        @Example    DiscoveryHelper.selectQuickView("123")
     */
    public static void selectQuickView(String productId){
        try {
            WebElement divElement = getProductDiv(productId);
            if (macys()) {
                Clicks.hoverForSelection(divElement.findElement(By.className("thumbnailImage")));
                Wait.untilElementPresent(By.id("quickViewLauncher"));
                Clicks.click(divElement.findElement(By.id("quickViewLauncher")));
            }
            else {
                Clicks.click(divElement.findElement(By.className("quickPeekIcon")));
            }
        }
        catch (Exception e) {
            Assert.fail("Error Unable to select Quick view for the product id" + productId);
        }
        logInfo("Selected quick view for the product id: "+ productId);
    }

    /*
        Returns selected color for the given productId from quick view overlay
        If the product having color swatch will return the respective Color
        else the product having no color swatch selected or no color swatch is available will return null
        @Example:
                String selectedColorName = DiscoveryHelper.getSelectedColor("1234")
                            selectedColorName   #=> "Blue"
        @param String productId
     */
    public static String getSelectedColorFromQV(String productId){
        String selectedColor = null;
        List<WebElement> qvColorElements;
        try {
            Wait.untilElementPresent("quickViewOverlay.quickViewOverlay");
            WebElement qvElement = Elements.findElement("quickViewOverlay.quickViewOverlay");
            if (Elements.findElement("quickViewOverlay.quickViewOverlay").getAttribute("class").contains("masterProduct") && bloomingdales())
                selectedColor = null;
            if (qvElement.findElement(By.className("color-swatch")).isDisplayed()) {
                qvColorElements = qvElement.findElements(By.className("color-swatch"));
                for (WebElement ele : qvColorElements) {
                    if (ele.getAttribute("class").contains("selected")) {
                        selectedColor = ele.getAttribute("title");
                        break;
                    } else {
                        selectedColor = null;
                    }
                }
            }
        }
        catch (Exception e)
        {
            selectedColor = null;
            // By default if no color swatch available for the given product returns null
        }
        logInfo("Selected color of the product id: " + productId + " is "+ selectedColor);
        return selectedColor;
    }

    /*
        Returns selected color for the given productId from quick view overlay
        If the product having color swatch will return the respective Color
        else the product having no color swatch selected or no color swatch is available will return null
        @Example:
                String selectedColorName = DiscoveryHelper.getSelectedColor("1234")
                            selectedColorName   #=> "Blue"
        @param String productId
     */
    public static String getSelectedColorFromPdp(String productId){
        String selectedColor = null;
        List<WebElement> colorElements;
        try {
            Wait.untilElementPresent(By.className("colors"));
            WebElement element = Elements.findElement(By.className("colors"));
            if (element.findElement(By.className("colorSwatch")).isDisplayed()) {
                colorElements = element.findElements(By.tagName("li"));
                for (WebElement ele : colorElements) {
                    if (ele.getAttribute("class").contains("selected")) {
                        selectedColor = ele.getAttribute("title");
                        break;
                    } else {
                        selectedColor = null;
                    }
                }
            }
        }
        catch (Exception e)
        {
            selectedColor = null;
            // By default if no color swatch available for the given product returns null
        }
        logInfo("Selected color of the product id: " + productId + " is "+ selectedColor);
        return selectedColor;
    }

    /*
        Select the product thumbnail for the given product ID
     */
    public static void selectProductThumbnail(String prodId) {
        try {
            Wait.untilElementPresent("productThumbnailPanel.product_thumbnails");
            WebElement ele = getProductDiv(prodId);
            Clicks.click(ele.findElement(By.tagName("a")));
        }
        catch (Exception e){
            Assert.fail("Product thumbnails is not displayed" + e.getMessage());
        }
        logInfo("Selected thumbnail grid for the product id: " + prodId);
    }

    /*
         Returns Div element to select thumbnail for the given product ID
    */
    public static WebElement getProductDiv(String productId){
        WebElement productElement = null;
        if (Elements.elementPresent(By.className("productThumbnail"))){
            List<WebElement> thumbnails = Elements.findElements(By.className("productThumbnail"));
            for (WebElement ele : thumbnails)
            {
                if (ele.getAttribute("id").equalsIgnoreCase(productId)) {
                    productElement = ele;
                    break;
                }
                else
                {
                    productElement = null;
                }
            }
            if (productElement == null){
                Assert.fail("ERROR - APP: Unable to find product id " + productId + " on the page");
            }
        }
        else
        {
            Assert.fail("No product thumbnails is displayed on the page");
        }
        return productElement;
    }

    /*
        Select a random facet item other than the given facet item for the given facet Name
        and will return the selected facet item name
        @Example:
                String facetItem = DiscoveryHelper.selectAlternateFacet("Blue", "Color")
                    facetItem   #=> "Black"
        @return String selected facet item for the given facet name
     */
    public static String selectAlternateFacet(String ignoreFacetValue, String facetName) {
        String randomFacet = null;
        LeftFacet.expandFacet(facetName);
        Wait.untilElementPresent("leftFacetNavigation_panel.facet_item");
        for (int i = 0; i < 5; i++) {
            randomFacet = getRandomFacetValue(facetName);
            if (!ignoreFacetValue.equalsIgnoreCase(randomFacet)) {
                LeftFacet.selectItemFromFacet(randomFacet, facetName);
                break;
            }
        }
        if (randomFacet == null)
            Assert.fail("Error Unable to select multi facet for" + facetName + " facet");
        return randomFacet;
    }

    /*
        Returns selected color for the given productId
        If the product having color swatch will return the respective Color
        else the product having no color swatch selected or no color swatch is available will return null
        @Example:
                String selectedColorName = DiscoveryHelper.getSelectedColor("1234")
                            selectedColorName   #=> "Blue"
        @param String productId
     */
    public static String getSelectedColor(String productId){
        WebElement ele = getProductDiv(productId);
        String selectedColor = null;
        try {
            if (ele.findElement(By.className("color-swatch")).isDisplayed()) {
                List<WebElement> prod_ele = ele.findElements(By.className("color-swatch"));
                for (WebElement el: prod_ele){
                    if (el.getAttribute("class").contains("selected") || el.getAttribute("class").contains("Selected")){
                        selectedColor = el.getAttribute("title");
                        break;
                    }
                    else
                    {
                        selectedColor = null;
                    }
                }
            }
            else
                selectedColor = null;
        }
        catch (Exception e){
            selectedColor = null;
            // By default if no color swatch avail for the given product returns null
        }
        logInfo("Selected color in thumbnail grid for the product id: " + productId + " is " + selectedColor );
        return selectedColor;
    }


    /*
       Returns random store miles for bops facet
       @Example:
                String selectedMiles = DiscoveryHelper.selectRandomStoreMiles();
                        selectedMiles   #=> 10 miles
    */
    public static String selectRandomStoreMiles() {
        LeftFacet.expandFacet("Pick Up In Store");
        List<String> actual;
        String selectedMiles;
        if(bloomingdales()) {
            actual = Elements.findElement("left_facet.bops_store_search_radius").findElements(By.tagName("a")).stream().map(WebElement::getText).collect(Collectors.toList());
            selectedMiles = Elements.findElement("left_facet.bops_store_search_radius").findElements(By.tagName("a")).stream().filter(elm -> elm.getAttribute("class").contains("selected")).collect(Collectors.toList()).get(0).getText();
        }
        else {
            actual = DropDowns.getAllValues("left_facet.bops_store_search_radius");
            selectedMiles = DropDowns.getSelectedValue(By.id("bopsByMiles"));
        }
        int ignore_index = actual.indexOf(selectedMiles);
        int index;
        randomGenerator = new Random();
        do {
            index = randomGenerator.nextInt(actual.size() - 1);
        }
        while (index == ignore_index);
        if(bloomingdales()){
            Clicks.click(Elements.findElement("left_facet.bops_store_search_radius").findElements(By.tagName("a")).get(index));
        }
        else {
            DropDowns.selectByIndex("left_facet.bops_store_search_radius", index);
        }
        logInfo("Selected random mile "+ selectedMiles + " from bops store search radius");
        return selectedMiles;
    }


    /*
       Verify expand and collapse functionality against service for given mode, keyword and country code
       @Example:
                Boolean services = DiscoveryHelper.expandedCollapsedVerificationAgainstServiceAndUI("SITE","JEANS", "CA")
                        services #=> true
       @param String mode respective site ex: "REGISTRY"
       @param String keyword respective keyword ex: "JEANS"
       @param String ishipCountryCode respective countryCode ex: "CA" by default its "US"
    */
    public static Boolean expandedCollapsedVerificationAgainstServiceAndUI(String mode, String keyword, String ishipCountryCode) {
        Boolean flag = false;
        try {
            List<WebElement> facetsList;
            List<String> facetName = new ArrayList<>();
            List<Boolean> facetStatus = new ArrayList<>();
            HashMap<String, Boolean> UIFacetStatus = new HashMap<>();
            HashMap<String, Boolean> ServiceFacetStatus;
            Map serviceResponse;

            String shoppingMode = mode.equalsIgnoreCase("REGISTRY") ? "WEDDING_REGISTRY" : "SITE";
            String regionCode = mode.equalsIgnoreCase("ISHIP") ? ishipCountryCode : "US";
            serviceResponse = SearchService.getSearchService(keyword, shoppingMode, regionCode, false, null);

            logInfo("Service response for given keyword: " + keyword + " " + serviceResponse);
            facetsList = Elements.findElements(Elements.element("left_facet.facet_names"));
            facetsList.stream().filter(ele -> !ele.getText().equalsIgnoreCase("")).forEach(ele -> {
                if (ele.getAttribute("title").contains("Collapse")) {
                    facetStatus.add(false);
                } else {
                    facetStatus.add(true);
                }
            });
            collapseAllExpandedFacets();

            facetsList = Elements.findElements(Elements.element("left_facet.facet_names"));
            //facetName = facetsList.stream().filter(e->!e.getText().equalsIgnoreCase("")).collect(Collectors.toList());
            facetName.addAll(facetsList.stream().filter(ele -> !ele.getText().equalsIgnoreCase(""))
                    .map(WebElement::getText).collect(Collectors.toList()));

            for (int m = 0; m < facetName.size(); m++) {
                UIFacetStatus.put(facetName.get(m), facetStatus.get(m));
            }
            ServiceFacetStatus = SearchService.getFacetNameAndCollapsedStatusFromService(serviceResponse);
            logInfo("Service facet expand/collapse status: "+ ServiceFacetStatus);
            logInfo("UI facet expand/collapse status: " + UIFacetStatus);
            if (UIFacetStatus.equals(ServiceFacetStatus)) {
                flag = true;
            }
        } catch (Exception e) {
            Assert.fail("Error preforming validation between Service and UI \r\n" + e.getMessage());
        }
        return flag;
    }


    /*
       Returns values of the available brands in brand section
    */
    public static String availableBrand() {
        List<String> selectedBrandFacetValue;
        String item;
        String selectedFacetItem = null;
        try {
            LeftFacet.expandFacet("Brand");
            selectedBrandFacetValue = getAllBrandFacetName();
            Random random = new Random();
            int index = random.nextInt(selectedBrandFacetValue.size());
            item = selectedBrandFacetValue.get(index);
            selectedFacetItem = item;
            System.out.println("Item to select\t" + selectedFacetItem);
        } catch (Exception e) {
            Assert.fail("Unable to get the available brand facet names \r\n" + e.getMessage());
        }
        return selectedFacetItem;
    }

    /*
       Returns product count displayed in top of the page
    */
    public static int getProductCount(){
        int productCount = 0;
        try {
            Wait.untilElementPresent("left_facet.search_results_count");
            Wait.forLoading("left_facet.loading");
            Wait.forPageReady();
            if (bloomingdales()) {
                String prodCount = Elements.findElement(Elements.element("left_facet.search_results_count")).getText().split(" ")[0];
                productCount = Integer.parseInt(prodCount);
            }
            else
            {
                String prodCount = Elements.findElement(Elements.element("left_facet.search_results_count")).getText();
                productCount = Integer.parseInt(prodCount);
            }
        }
        catch (Exception e){
            Assert.fail("Product count is not displayed in the page"+ e.getMessage());
        }
        logInfo("Total product count displayed on top " + productCount);
        return productCount;
    }

    /*
        Returns true if multi level pricing like Was Orig is displayed under product thumbnail else return false
     */
    public static boolean verifyMultiLevelPricing() {
        Boolean flag = false;
        int count = 0;
        List<WebElement> thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));
        for (WebElement ele : thumbnails) {
            try {
                List<String> prices = Arrays.asList("Orig", "ORIG", "WAS", "Was", "Sale", "SALE", "REG", "Reg");
                String prodPrice = ele.findElement(By.className("prices")).getText();
                for (String price : prices) {
                    flag = prodPrice.contains(price);
                    if (flag) {
                        logInfo("Displayed Product price: " + prodPrice);
                        logInfo("Multi level pricing keywords: " + prices);
                        count = count + 1;
                        break;
                    }
                }
                if (flag && count >= 2){
                    flag = true;
                    break;
                }
                else flag = false;
            } catch (Exception e) {
                Assert.fail("Product price information is not displayed for product id: " + ele.getAttribute("id"));
            }
        }
        return flag;
    }


    /*
        Returns list of master product ids displayed in the page
     */
    public static List<String> getMasterProductIds(){
        ArrayList<String> prodIds = new ArrayList<>();
        List<WebElement> thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));
        for (WebElement ele : thumbnails)
        {
            try {
                if (ele.findElement(By.className("prices")).getText().contains("-")) {
                    String prodId = ele.getAttribute("id");
                    prodIds.add(prodId);
                }
            }
            catch (Exception e){
                Assert.fail("Product price information is not displayed for product id: "+ ele.getAttribute("id"));
            }
        }
        logInfo("Displayed master product ids: "+ prodIds);
        return prodIds;
    }

    /*
        Returns list of member product ids displayed in the page
     */
    public static List<String> getMemberProdIds(){
        List<String> prodIds= getProductIds();
        prodIds.removeAll(ListUtils.intersection(prodIds, getMasterProductIds()));
        logInfo("Displayed member product ids: " + prodIds);
        return prodIds;
    }

    /*
            Returns list of product ids which are having color swatch
     */
    public static List<String> getProductIdsWithColorSwatch() throws Exception {
        ArrayList<String> prodIds = new ArrayList<>();
        List<WebElement> thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));
        for (WebElement ele : thumbnails)
        {
            try {
                ele.findElement(By.className("color-swatch"));
                String prod_id = ele.getAttribute("id");
                prodIds.add(prod_id);
            }
            catch (Exception e){
                System.out.println("Product having no color swatch");
            }
        }
        logInfo("Product ids displayed with color swatches: "+ prodIds);
        return prodIds;
    }

    /*
           Returns list of product ids which are having color swatch with more link
           Works only for BCOM since color swatches with more link is displayed only for BCOM
    */
    public static List<String> getProductIdsWithMoreColorSwatch() throws Exception {
        ArrayList<String> prodIds = new ArrayList<>();
        List<WebElement> thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));
        for (WebElement ele : thumbnails)
        {
            try {
                ele.findElement(By.className("more-button"));
                String prod_id = ele.getAttribute("id");
                prodIds.add(prod_id);
            }
            catch (Exception e){
                System.out.println("Product having no color swatch with more link");
            }
        }
        logInfo("Product ids displayed with more color swatch link: "+ prodIds);
        return prodIds;
    }

    /*  Returns the color swatch family name for the given product id
            @param String product id which is having color swatch selected
            Works only for BCOM since the element is available for BCOM alone
    */
    public static String getSelectedColorFamilyName(String productId) {
        WebElement ele = getProductDiv(productId);
        String selectedColorFamilyName = null;
        List<WebElement> prod_ele = ele.findElements(By.className("color-swatch"));
        for (WebElement el: prod_ele){
            if (el.getAttribute("class").contains("selected")){
                selectedColorFamilyName = el.findElement(By.className("swatchColorFamily")).getAttribute("value");
                break;
            }
        }
        logInfo("Selected color family name on product thumbnail grid for the product id: "+ productId + " is "+ selectedColorFamilyName);
        return selectedColorFamilyName;
    }

    /*
        Return min and max price for the given facet
        If the facet is Under $50 minimum price will be returned as 0.00
        If the facet is Above & $500 maximum price will be returned as 0.00
        @Example  List<String, Double> facetPrice = DiscoveryHelper.getPriceFacetMinMax("Under $50")
                    facetPrice #=> "minPrice" => 0.00
                                    "maxPrice" => 50.00
                  List<String, Double> facetPrice = DiscoveryHelper.getPriceFacetMinMax("$200 - $300")
                    facetPrice #=> "minPrice" => 200.00
                                    "maxPrice" => 300.00
        @param String price, selected facet value
     */
    private static HashMap<String, Double> getPriceFacetMinMax(String price) {
        Double min, max;
        HashMap<String, Double> productPriceValue = new HashMap<>();
        if (price.contains("-")) {
            price = (price.replace("$", "")).replace(",", "").trim();
            min = Double.parseDouble(price.split("-")[0].trim());
            max = Double.parseDouble(price.split("-")[1].trim());
        } else if (price.contains("Above")) {
            min = Double.parseDouble(price.replaceAll("\\D+","").trim());
            max = 0.00;
        } else {
            min = 0.00;
            max = Double.parseDouble(price.replaceAll("\\D+","").trim());
        }
        productPriceValue.put("minPrice", min);
        productPriceValue.put("maxPrice", max);
        logInfo("Selected Min Price range from Price facet: "+ min);
        logInfo("Selected Max Price range from Price facet: "+ max);
        return productPriceValue;
    }

    /*
        Return min and max price for the given product price
        If the product member then minimum price will be returned as 0.00
        If the product is master or colorway  min & max will be returned
        @Example  List<String, Double> productPrice = DiscoveryHelper.getProductPrice("Reg 200\n Was 180\n Now 120")
                    productPrice #=> "minPrice" => 0.00
                                    "maxPrice" => 120.00
                  List<String, Double> productPrice = DiscoveryHelper.getProductPrice("Reg $200 - $300\n Was $180-$280\n Now $100-$150")
                    productPrice #=> "minPrice" => 100.00
                                    "maxPrice" => 150.00
        @param String price, selected facet value
     */
    public static HashMap<String, Double> getProductPrice(String price) {
        HashMap<String, Double> productDoublePrice = new HashMap<>();
        Double minPrice, maxPrice;
        String[] productPrice = price.split("\n");
        if (Arrays.toString(productPrice).contains("SELECT")) {
            price = productPrice[0].replaceAll("[a-z,A-Z]","").replaceAll("g.","").replaceAll("G.","").replaceAll("\\$","").trim();
        }
        else{
            price = productPrice[productPrice.length -1].replaceAll("g.","").replaceAll("G.","").replaceAll("[a-z,A-Z]","").replaceAll("\\$","").trim();
        }
        if (price.contains("-")){
            minPrice = Double.parseDouble(price.split("-")[0]);
            maxPrice = Double.parseDouble(price.split("-")[1]);
        }else
        {
            minPrice = 0.00;
            maxPrice = Double.parseDouble(price);
        }
        productDoublePrice.put("minPrice", minPrice);
        productDoublePrice.put("maxPrice", maxPrice);
        logInfo("Product Min Price: "+ minPrice);
        logInfo("Product Max Price: "+ maxPrice);
        return productDoublePrice;
    }

    /*
        Compares product price with price facet and returns true if product price falls between the selected price range
        else return false
        @Example   boolean facetVerification = DiscoveryHelper.priceVerification("1234", ["$100 - $200"], "$140")
                    facetVerification    #=> true

                    boolean facetVerification = DiscoveryHelper.priceVerification("1234", ["$100 - $200"], "$280")
                    facetVerification    #=> false
        @param String productId, for which we need to verify the price verification
        @param List<String> selectedFacetValues, selected price facets values
        @param String price, product price
     */
    public static boolean priceVerification(String productId, List<String> selectedFacetValues, String price){
        Double productMinPrice, productMaxPrice, facetMinPrice, facetMaxPrice;
        boolean flag = false;
        for (String facetItem : selectedFacetValues){
            productMinPrice = getProductPrice(price).get("minPrice");
            productMaxPrice = getProductPrice(price).get("maxPrice");
            facetMinPrice = getPriceFacetMinMax(facetItem).get("minPrice");
            facetMaxPrice = getPriceFacetMinMax(facetItem).get("maxPrice");
            if (facetMaxPrice == 0.00){
                if(productMaxPrice >= facetMinPrice) {
                    flag = true;
                    logInfo("Product Min Price: " + productMinPrice + " Product Max price: " + productMaxPrice + " for product id: " + productId);
                    logInfo("Facet Min Price: " + facetMinPrice + " Facet Max price: " + facetMaxPrice);
                    break;
                }
            }
            else{
                if (facetMinPrice <= productMinPrice || facetMaxPrice >= productMaxPrice){
                    flag = true;
                    logInfo("Product Min Price: "+ productMinPrice +" Product Max price: "+ productMaxPrice +" for product id: "+ productId);
                    logInfo("Facet Min Price: "+ facetMinPrice +" Facet Max price: "+ facetMaxPrice + " "+selectedFacetValues);
                    break;
                }
            }
        }
        return flag;
    }

    /*
        Returns all thumbnail details on the page like product id,name, price, price events, batch text, customer ratings
        If price events, batch text, customer ratings are not available for the product null will be returned
        @Example Map<String, HashMap> productDetails = DiscoveryHelper.getProductThumbnailsDetails();
                    productDetails #=> "12131" =>
                                                "elm_product_id"=> "12131"
                                                "elm_product_name" => "Product name"
                                                "elm_price_event" => "product price event"
                                                "elm_prices" => "$30.00"
                                                "elm_batch_text" => "product batch text"
                                                "elm_customer_ratings" => "product customer rating"
     */
    public static Map<String, HashMap> getProductThumbnailsDetails() {
        HashMap<String, HashMap> productThumbnails = new HashMap<>();
        boolean price_events;
        boolean batch_text;
        boolean customer_ratings;
        List<WebElement> thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));

        for (WebElement thumbnail : thumbnails) {
            HashMap<String, String> thumbnailWebElements = new HashMap<>();
            try {
                price_events = thumbnail.findElement(By.id("priceEventsDiv")).isDisplayed();
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Price events are not available for Product ID " + thumbnail.getAttribute("id"));
                price_events = false;
            }

            try {
                batch_text = thumbnail.findElement(By.className("badgeHeader")).isDisplayed();
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Batch Texts are not available for Product ID " + thumbnail.getAttribute("id"));
                batch_text = false;
            }

            try {
                customer_ratings = thumbnail.findElement(By.className("pdpreviews")).isDisplayed();
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Customer Ratings not available for Product ID " + thumbnail.getAttribute("id"));
                customer_ratings = false;
            }

            thumbnailWebElements.put("elm_product_id", thumbnail.getAttribute("id"));
            thumbnailWebElements.put("elm_product_name", thumbnail.findElement(By.className("shortDescription")).getText());
            thumbnailWebElements.put("elm_price_event", price_events ? thumbnail.findElement(By.id("priceEventsDiv")).getText() : null);
            thumbnailWebElements.put("elm_prices", thumbnail.findElement(By.className("prices")).getText().split("\\(")[0]);
            thumbnailWebElements.put("elm_batch_text", batch_text ? thumbnail.findElement(By.className("badgeHeader")).getText() : null);
            thumbnailWebElements.put("elm_customer_ratings", customer_ratings ? thumbnail.findElement(By.className("pdpreviews")).getText() : null);
            thumbnailWebElements.put("elm_customer_ratings_style", customer_ratings ? thumbnail.findElement(By.className("rating")).findElement(By.tagName("span")).getAttribute("style") : null);
            productThumbnails.put(thumbnail.getAttribute("id"), thumbnailWebElements);
        }

        return productThumbnails;
    }

    /*
        Returns thumbnail details for the given product on the page like product id,name, price, price events, batch text, customer ratings
        If price events, batch text, customer ratings are not available for the product null will be returned
        @Example Map<String, HashMap> productDetails = DiscoveryHelper.getProductThumbnailDetails();
                    productDetails #=> "12131" =>
                                                "elm_product_id"=> "12131"
                                                "elm_product_name" => "Product name"
                                                "elm_price_event" => "product price event"
                                                "elm_prices" => "$30.00"
                                                "elm_batch_text" => "product batch text"
                                                "elm_customer_ratings" => "product customer rating"
     */
    public static Map<String, HashMap> getProductThumbnailDetails(String productId) {
        HashMap<String, HashMap> productThumbnails = new HashMap<>();
        boolean price_events;
        boolean edv_text;
        boolean colorway_price;
        boolean batch_text;
        boolean customer_ratings;
        boolean edv_image;
        boolean first_range_priceSale;
        boolean second_range_priceSale;

        WebElement thumbnail = getProductDiv(productId);
        HashMap<String, WebElement> thumbnailWebElements = new HashMap<>();
        try {
            // EverydayValue Div
            edv_text = thumbnail.findElement(By.xpath(".//*[@id='edvDiv']/span")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Everyday Value Div are not available for Product ID " + thumbnail.getAttribute("id"));
            edv_text = false;
        }

        try {
            // EverydayValue Div Image
            edv_image = thumbnail.findElement(By.className("edvImage")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Everyday Value Div Image are not available for Product ID " + thumbnail.getAttribute("id"));
            edv_image = false;
        }

        try {
            price_events = thumbnail.findElement(By.className("priceEvents")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Price events are not available for Product ID " + thumbnail.getAttribute("id"));
            price_events = false;
        }

        try {
            colorway_price = thumbnail.findElement(By.className("colorway-price")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("colorway price are not available for Product ID " + thumbnail.getAttribute("id"));
            colorway_price = false;
        }

        try {
            batch_text = thumbnail.findElement(By.className("badgeHeader")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Batch Texts are not available for Product ID " + thumbnail.getAttribute("id"));
            batch_text = false;
        }

        try {
            customer_ratings = thumbnail.findElement(By.className("pdpreviews")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Customer Ratings not available for Product ID " + thumbnail.getAttribute("id"));
            customer_ratings = false;
        }

        try {
            first_range_priceSale = thumbnail.findElement(By.cssSelector("span[class='first-range priceSale']")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("first-range priceSale is not displayed for the Product " + thumbnail.getAttribute("id"));
            first_range_priceSale = false;
        }

        try {
            second_range_priceSale = thumbnail.findElement(By.cssSelector("span[class='second-range priceSale']")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("second-range priceSale is not displayed for the Product " + thumbnail.getAttribute("id"));
            second_range_priceSale = false;
        }

        thumbnailWebElements.put("elm_product_id", thumbnail);
        thumbnailWebElements.put("elm_product_name", thumbnail.findElement(By.className("shortDescription")));
        thumbnailWebElements.put("elm_colorway_price", colorway_price ? thumbnail.findElement(By.className("colorway-price")) : null);
        thumbnailWebElements.put("elm_everyDayValue", edv_text ? thumbnail.findElement(By.xpath(".//*[@id='edvDiv']/span")) : null);
        thumbnailWebElements.put("elm_everyDayImage", edv_image ? thumbnail.findElement(By.className("edvImage")) : null);
        thumbnailWebElements.put("elm_first_range_priceSale", first_range_priceSale ? thumbnail.findElement(By.cssSelector("span[class='first-range priceSale']")) : null);
        thumbnailWebElements.put("elm_second_range_priceSale", second_range_priceSale ? thumbnail.findElement(By.cssSelector("span[class='second-range priceSale']")) : null);
        thumbnailWebElements.put("elm_price_event", price_events ? thumbnail.findElement(By.className("priceEvents")) : null);
        thumbnailWebElements.put("elm_prices", thumbnail.findElement(By.className("prices")));
        thumbnailWebElements.put("elm_batch_text", batch_text ? thumbnail.findElement(By.className("badgeHeader")) : null);
        thumbnailWebElements.put("elm_customer_ratings", customer_ratings ? thumbnail.findElement(By.className("pdpreviews")) : null);
        productThumbnails.put(thumbnail.getAttribute("id"), thumbnailWebElements);

        return productThumbnails;
    }


    /*
        Return Map with String facet name and the respective HashMap with facet item and facet count
        @Example:
                Map<String, HashMap> facetDetail= DiscoveryHelper.getFacetDetail("Price");
                            facetDetail     #=> ("Price" =>
                                                  "Under $50" => "180")
        @param String facetName respective facetName ex: "Price"
     */
    public static Map<String, Integer> getFacetDetail(String facetName) {
        HashMap<String, Integer> facetDetail = new HashMap<>();
        if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
            LeftFacet.expandFacet(facetName);
        }
        List<WebElement> facetValues;
        List<String> availFacetValues = null;
        List<String> availFacetCount = null;
        WebElement ele = Elements.findElement(LeftFacet.getFacetDiv(facetName));
        ele = ele.findElement(Elements.element("leftFacetNavigation_panel.facet_item"));
        if (ele.isDisplayed()) {
            facetValues = Elements.findElement(LeftFacet.getFacetDiv(facetName)).findElements(By.tagName("a"));
            facetValues = facetValues.stream().filter(el->!el.getText().equals("")).collect(Collectors.toList());
            facetValues = facetValues.stream().filter(el -> !el.getAttribute("id").equals("bopsLocation")).collect(Collectors.toList());
            facetValues = facetValues.stream().filter(el -> !el.getAttribute("class").contains("selected")).collect(Collectors.toList());
            facetValues = facetValues.stream().filter(el -> !el.findElement(Elements.element("leftFacetNavigation_panel.facet_item")).getText().equalsIgnoreCase("")).collect(Collectors.toList());
            availFacetValues = facetValues.stream().map(el -> el.findElement(Elements.element("leftFacetNavigation_panel.facet_item")).getText().split("\\[")[0].split("\\(")[0].trim()).collect(Collectors.toList());
            availFacetCount = facetValues.stream().map(el -> el.findElement(Elements.element("leftFacetNavigation_panel.facet_item"))
                    .getAttribute("title").split("\\(")[1].replaceAll("\\W+","").replaceAll("\\D+","").trim()).collect(Collectors.toList());
        }
        assert availFacetValues != null;
        for (int i = 0; i < availFacetValues.size(); i++)
        {
            facetDetail.put(availFacetValues.get(i), Integer.parseInt(availFacetCount.get(i)));
        }
        return facetDetail;
    }

    /*
        Returns response code for the given href url
            int responseCode = DiscoveryHelper.getResponseCode("http://www.macys.com")
                    responseCode #=> 200
     */
    public static int getResponseCode(String href) throws IOException {
        int responseCode = 0;
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(href);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        }
        catch (Exception e){
            Assert.fail("Unable to get response for the url: "+href +"\t"+ e.getMessage());
        }
        logInfo("Response code " + responseCode + " is returned for the url: " + href);
        return responseCode;
    }

    private static Map<String, String> configureZipCodeLatLong(){
        HashMap<String, String> zipCodeLatLong = new HashMap<>();
        zipCodeLatLong.put("10021", "40.7700703,-73.958024599999993");
        zipCodeLatLong.put("10022", "40.7593941,-73.969779500000023");
        zipCodeLatLong.put("94102", "37.7786871,-122.421242399999983");
        zipCodeLatLong.put("11030", "40.7960005,-73.68305213");
        return zipCodeLatLong;
    }

    public static String getRandomZipCode(){
        randomGenerator = new Random();
        List<String> availZipcodes = new ArrayList<>();
        availZipcodes.add("10021");
        availZipcodes.add("10022");
        availZipcodes.add("94102");
        availZipcodes.add("11030");
        int index = randomGenerator.nextInt(availZipcodes.size());
        return  availZipcodes.get(index);
    }


    /*
        Create Bops cookie for the given zip code
        @Example:
                DiscoveryHelper.createBopsCookie("11030");
     */
    public static void createBopsCookie(String zipCode) {
        String cookieName = "MISCGCs";
        StringBuilder cookieValue;
        cookieValue = new StringBuilder();
        Map<String, String> zipcodeValues = configureZipCodeLatLong();
        try {
            for (Map.Entry<String, String> zip : zipcodeValues.entrySet()) {
                if (zip.getKey().equals(zipCode)) {
                    cookieValue.append("USERLL1_92_");
                    cookieValue.append(zip.getValue());
                    cookieValue.append("_87_USERPC1_92_");
                    cookieValue.append(zip.getKey());
                    if (bloomingdales()) {
                        cookieValue.append("3_87_BOPSPICKUPSTORE1_92_343");
                    }
                    break;
                }
            }
            logInfo("Creating " + cookieName + " cookie with " + cookieValue.toString() + " values");
            Cookies.addCookie(cookieName, cookieValue.toString());
            Navigate.browserRefresh();
        }catch (Exception e){
            Assert.fail("Unable to create BOPS cookie for the given zipcode "+zipCode + e.getMessage());
        }
    }

    /*
        Print info log on sdt-info.log file
        for the given log message
        @Example:
                DiscoveryHelper.logInfo("Verified BOPS facet is displayed on left nav")
        Output on sdt-info.log file #=> Info Log:  [Tue Dec 06 13:10:06 IST 2016] Verified BOPS facet is displayed on left nav
     */
    public static void logInfo(String logMessage) {
        String scenarioType = ScenarioHelper.getScenarioInfo().get("keyword").toString();
        String scenarioName = ScenarioHelper.getScenarioInfo().get("name").toString();
        if (scenarioCount == 0) {
            LOGGER.debug(scenarioType + ": " + scenarioName);
            scenarioCount = scenarioCount + 1;
        }
        LOGGER.debug(logMessage);
    }

    /*
        Print error log on sdt-error.log file
        for the given log message
        @Example:
                DiscoveryHelper.logErr("BOPS facet is not displayed on left nav")
        Output on sdt-error.log file #=> Error Log:  [Tue Dec 06 13:10:06 IST 2016] BOPS facet is not displayed on left nav
     */
    public static void logErr(String logMessage){
        LOGGER.error(logMessage);
    }

    /*
        Returns list of auto suggestions displayed under search box
        @Ex:
               List<String> suggestions = DiscoveryHelper.getAutoSuggestionText()
     */
    public static List<String> getAutoSuggestionList() {
        List<String> suggestions = new ArrayList<>();
        try {
            Wait.untilElementPresent("header.suggestions_list");
            List<WebElement> ele = Elements.findElements(Elements.element("header.suggestions_list"));
            for (WebElement e : ele) {
                suggestions.add(e.getText());
            }
            logInfo("Suggestions Displayed: " + suggestions);
        }catch (Exception e){
            Assert.fail("Unable to get autocomplete suggestions list");
        }
        return suggestions;
    }

    /*
        Return true if All brands section under Brand facet is collapsed
        else false.
     */
    public static boolean isAllBrandsCollapsed(){
        return Elements.findElement(Elements.element("leftFacetNavigation_panel.all_brand_list"))
                .getAttribute("class").contains("collapsed");
    }

    /*
       Return true if Featured brands section under Brand facet is collapsed
       else false.
    */
    public static boolean isFeaturedBrandsCollapsed(){
        return Elements.findElement(Elements.element("leftFacetNavigation_panel.featured_brand_values"))
                .getAttribute("class").contains("collapsed");
    }

    /*
       Collapse/Expand Featured brands section under Brand facet
    */
    public static void expandCollapseFeaturedBrand(){
        Elements.findElement(By.id("featured_brands")).click();
    }

    /*
      Collapse/Expand All brands section under Brand facet
   */
    public static void expandCollapseAllBrand(){
        Elements.findElement(By.id("all_brands")).click();
    }

    /*
        Returns list of Size facet headers under size facet
        Ex:
            List<String> facetHeaders = DiscoveryHelper.getAllSizeFacetHeaders();
                      facetHeaders # => {"Women's Regular",
                                         "Plus Sizes",
                                         "Men's Regular",
                                         "Women's Waist & Inseam",
                                         ...}
     */
    public static List<String> getAllSizeFacetHeaders(){
        List<String> sizeHeaders = new ArrayList<>();
        try {
            if (Elements.findElement(LeftFacet.getFacetDiv("Size")).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet("Size");
            }
            sizeHeaders = Elements.findElements("left_facet.expand_size_categories").stream()
                    .map(WebElement::getText).collect(Collectors.toList());
            sizeHeaders = sizeHeaders.stream().filter(e->!e.equalsIgnoreCase("")).collect(Collectors.toList());
        }catch (Exception e){
            Assert.fail("Unable to get size facet headers under size facet");
        }
        return sizeHeaders;
    }

    /*
        Expand/Collapse Size facet sub header for the given header name
     */
    public static void expandCollapseSizeFacetSubHeader(String headerName){
        try{
            if (Elements.findElement(LeftFacet.getFacetDiv("Size")).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet("Size");
            }
            WebElement ele = Elements.findElements("left_facet.expand_size_categories").stream()
                    .filter(e->e.getText().equalsIgnoreCase(headerName)).findFirst().orElse(null);
            ele.click();
        }catch (Exception e){
            Assert.fail("Unable to select size facet sub header under size facet for header: " + headerName + e.getMessage());
        }
    }

    /*
        Return true if the given size facet sub header is collapsed
        else false
     */
    public static boolean isSizeFacetSubHeaderCollapsed(String headerName){
        boolean state = false;
        try{
            if (Elements.findElement(LeftFacet.getFacetDiv("Size")).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet("Size");
            }
            WebElement ele = Elements.findElements("left_facet.expand_size_categories").stream()
                    .filter(e->e.getText().equalsIgnoreCase(headerName)).findFirst().orElse(null);
            String stateName = ele.getAttribute("title");
            state = stateName.contains("Expand");
        }catch (Exception e){
            Assert.fail("Unable to select size facet sub header under size facet for header: " + headerName + e.getMessage());
        }
        return  state;
    }

    /*
        Returns list of all size facet value under the given size facet header
     */
    public static List<String> getAllSizeFacetHeaderValue(String headerName){
        List<WebElement> facetsListElements;
        List<String> facetsList = null;
        try{
            if (Elements.findElement(LeftFacet.getFacetDiv("Size")).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet("Size");
            }
            if (isSizeFacetSubHeaderCollapsed(headerName)) expandCollapseSizeFacetSubHeader(headerName);
            WebElement ele = Elements.findElements("left_facet.expand_size_categories").stream()
                    .filter(e->e.getText().equalsIgnoreCase(headerName)).findFirst().orElse(null);
            ele = ele.findElement(By.xpath(".."));
            facetsListElements = ele.findElements(By.tagName("li")).stream()
                    .filter(e-> !e.getAttribute("class").equals(" disabled")).collect(Collectors.toList());
            facetsListElements = facetsListElements.stream().filter(e-> !e.getAttribute("class").contains("selected")).collect(Collectors.toList());
            facetsList = facetsListElements.stream().map(e->e.getText().split("\\(")[0].split("\\[")[0].trim()).collect(Collectors.toList());
            facetsList = facetsList.stream().filter(e -> !e.equals("")).collect(Collectors.toList());
        }catch (Exception e){
            Assert.fail("Unable to select size facet sub header under size facet for header: " + headerName + e.getMessage());
        }
        return facetsList;
    }

    /*
        Returns true if product rating falls with in the selected customer rating facet
        else return false
        Ex:
                boolean verification = DiscoveryHelper.ratingVerification(80, ["5 stars"])
                            verification #=> false
                boolean verification = DiscoveryHelper.ratingVerification(80, ["5 stars, 4 stars & up"])
                            verification #=> true
        @param int productRating, rating displayed for the product ex: 100 or 98
        @param List<String> selectedFacetValues, selected rating/ratings from customer rating facet ex: "5 start"
     */
    public static boolean ratingVerification(int productRating, List<String> selectedFacetValues){
        boolean ratingVerification = false;
        for (String selectedFacetValue : selectedFacetValues) {
            System.out.println("selected facet value : "+ selectedFacetValue);
            switch (selectedFacetValue){
                case "5 stars":
                    logInfo("Product rating " + productRating);
                    if (productRating == 100){
                        ratingVerification = true;
                    }
                    break;
                case "4 stars & up":
                    logInfo("Product rating " + productRating);
                    if (productRating >= 80){
                        ratingVerification = true;
                    }
                    break;
                case "3 stars & up":
                    logInfo("Product rating " + productRating);
                    if (productRating >= 60){
                        ratingVerification = true;
                    }
                    break;
                default:
                    System.out.println("Add case for the selected rating: "+ selectedFacetValue);
                    break;
            }
        }
        return ratingVerification;
    }

    /*
        Return true if size facet values are displayed under the given size facet sub header
        else false
     */
    public static boolean isSizeFacetValuesDisplayed(String headerName) {
        boolean facetValuesDisplayed = false;
        try {
            WebElement ele = Elements.findElements("left_facet.expand_size_categories").stream()
                    .filter(e->e.getText().equalsIgnoreCase(headerName)).findFirst().orElse(null);
            // Getting parent element for the given facet sub header
            ele = ele.findElement(By.xpath(".."));
            // if li elements are displayed then facet values are displayed else facet values are not displayed
            facetValuesDisplayed = ele.findElement(By.tagName("li")).isDisplayed();
        } catch (Exception e) {
            Assert.fail(headerName + " sub facet is not displayed");
        }
        return facetValuesDisplayed;
    }

    /*
        Return selected facet header "li" element id
        since size facet has so many sub headers this method is mainly used to get the selected sub header name
     */
    public static String getSelectedFacetName(String facetName) {
        String facetId = null;
        Wait.forLoading("left_facet.loading");
        if (facetName.equalsIgnoreCase("size")) {
            if (Elements.findElement(LeftFacet.getFacetDiv(facetName)).getAttribute("class").contains("collapsed")) {
                LeftFacet.expandFacet(facetName);
            }
            WebElement ele = Elements.findElement(LeftFacet.getFacetDiv(facetName));
            List<WebElement> facetDivs = ele.findElements(By.className("typbox"));
            for (WebElement facetDiv : facetDivs) {
                List<WebElement> liElements = facetDiv.findElements(By.tagName("li"));
                WebElement nextLevelSubHeader = liElements.stream()
                        .filter(e ->
                                e.getAttribute("class").contains("typbox"))
                        .findFirst()
                        .orElse(null);
                // Break if there is a next level sub header like Inseam, Waist or etc...
                // That element will be iterated in the next loop
                if (nextLevelSubHeader != null) {
                    facetId = null;
                } else {
                    WebElement selectedFacet = liElements.stream()
                            .filter(e ->
                                    e.getAttribute("class").contains("selected"))
                            .findFirst()
                            .orElse(null);
                    if (selectedFacet != null) {
                        facetId = facetDiv.getAttribute("id");
                        break;
                    }
                }
            }
        } else {
            WebElement ele = Elements.findElement(LeftFacet.getFacetDiv(facetName));
            facetId = ele.getAttribute("id");
        }
        return  facetId;
    }

    /*
      Returns list of product ids having badge text
   */
    public static List<String> getProductIdsHavingBadgeText() {
        List<String> productIds = getProductIds();
        List<String> productDisplayed = new ArrayList<>();
        for (String id : productIds) {
            Map<String, HashMap> details = getProductThumbnailDetails(id);
            WebElement batchText = (WebElement) details.get(id).get("elm_batch_text");
            if (batchText != null) {
                System.out.println("Displayed product batch text: " + batchText.getText());
                productDisplayed.add(id);
            }
        }
        logInfo("Product ids with badge text: " + productDisplayed);
        return productDisplayed;
    }

    /*
       Returns list of product ids having multi level pricing either two or three level pricing
    */
    public static List<String> getProductIdsHavingMultiLevelPricing() {
        List<String> productIds = getProductIds();
        List<String> productDisplayed = new ArrayList<>();
        for (String id : productIds) {
            Map<String, HashMap> details = getProductThumbnailDetails(id);
            WebElement price = (WebElement) details.get(id).get("elm_prices");
            int priceLevel = price.findElements(Elements.element("productThumbnailPanel.prices")).size();
            if (priceLevel >= 2) {
                System.out.println("Displayed product price: " + price.getText());
                productDisplayed.add(id);
            }
        }
        logInfo("Product ids with multi level pricing: " + productDisplayed);
        return productDisplayed;
    }

    public static String getServiceRespose(String serviceURL){

        // Service Response for Get for any given url
        String serviceResponse = " ";
        try {
            serviceResponse = Utils.httpGet(serviceURL, null);
            logInfo("Response from the service : "+serviceResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serviceResponse;
    }


    public static List<WebElement> getAllPriceWebElementsInList(Map<String, WebElement> thumbnailWebElements){

        // overAllPriceWebElements will have first-range,second-range,first-range priceSale, second-range priceSale
        // 0,1 positions first-range
        //2,3 positions second-range
        //4,5 positions second-range priceSale
        // if not found writes null respectively


        WebElement allPrices = thumbnailWebElements.get("elm_prices");
        List<WebElement> firstRangePriceElements = allPrices.findElements(By.cssSelector("span[class='first-range ']"));
        List<WebElement> secondRangePriceElements = allPrices.findElements(By.cssSelector("span[class='second-range ']"));
        WebElement firstRangeSalePriceElement = thumbnailWebElements.get("elm_first_range_priceSale");
        WebElement secondRangeSalePriceElement = thumbnailWebElements.get("elm_second_range_priceSale");

        List<WebElement> overAllPriceWebElements = new ArrayList<>();

        logInfo("First Range Size : " +firstRangePriceElements.size());

        for (int i=0;i<firstRangePriceElements.size();i++){
            if (firstRangePriceElements.get(i) != null) {
                overAllPriceWebElements.add(firstRangePriceElements.get(i));
            }
        }

        if( firstRangePriceElements.size() == 0){
            overAllPriceWebElements.add(null);
            overAllPriceWebElements.add(null);
            // sometimes only sale price is displayed
            logInfo("No price is displayed for this product");
        }else if (firstRangePriceElements.size() == 1){
            overAllPriceWebElements.add(null);
        }else {
            logInfo("More than one First Range price available");
        }

        logInfo("Second Range Size :" +secondRangePriceElements.size());

        for (int i=0;i<secondRangePriceElements.size();i++){
            if (secondRangePriceElements.get(i) != null) {
                overAllPriceWebElements.add(secondRangePriceElements.get(i));
            }
        }

        if (secondRangePriceElements.size() == 0){
            overAllPriceWebElements.add(null);
            overAllPriceWebElements.add(null);
        }else if (secondRangePriceElements.size() == 1){
            overAllPriceWebElements.add(null);
        }

        // has to be removed just for my understanding
        if (firstRangeSalePriceElement != null)
            System.out.println( "Value " + firstRangeSalePriceElement.getText()) ;
        if (secondRangeSalePriceElement != null)
            System.out.println( "Value " + secondRangeSalePriceElement.getText()) ;

        overAllPriceWebElements.add(firstRangeSalePriceElement);
        overAllPriceWebElements.add(secondRangeSalePriceElement);

        return overAllPriceWebElements;

    }

    public static List<String> getAllPriceTagsInList(List<WebElement> overAllPriceWebElements){

        // to get all the price tags available for a product

        int size = overAllPriceWebElements.size();
        List<String> overAllPriceTags = new ArrayList<>();

        for (int i=0;i<size;i++){
            if(overAllPriceWebElements.get(i)!=null){
                overAllPriceTags.add(overAllPriceWebElements.get(i).getText());
            }else {
                overAllPriceTags.add(null);
            }
        }
        // has to be removed for my understanding
        // Prices shown in UI
        for (int i=0;i<size;i++){
            if(overAllPriceTags.get(i)!=null){
                logInfo(" Price Value displayed from PriceTAGS " + i + " : "+ overAllPriceTags.get(i));
            }
        }
        return overAllPriceTags;

    }

    public static void VerifyPriceEventLabels(String priceType,Map<String, WebElement> thumbnailWebElements){
        // Verify priceLabels are correct and it retains color

        boolean isValid = false;
        WebElement priceEvents_TextElement = thumbnailWebElements.get("elm_price_event");

        if (priceEvents_TextElement != null) {
            switch (priceType) {
                case "Price Break Tiered":
                    // color math and font match - has to be modified exactly to match
                    isValid = (priceEvents_TextElement.getText()).equals("PRICE BREAK!") && (elementMatchColorCode(priceEvents_TextElement, "#3333"));
                    break;
                case "LPOS":
                case "LPOS Tiered":
                    // color math and font match - has to be modified exactly to match
                    isValid = (priceEvents_TextElement.getText()).startsWith("LOWEST PRICE OF THE ") && (priceEvents_TextElement.getText()).endsWith("SEASON!")&& (elementMatchColorCode(priceEvents_TextElement, "#3333"));
                    break;
                case "Special Savings Tiered":
                    // color math and font match - has to be modified exactly to match
                    isValid = (priceEvents_TextElement.getText()).equals("Special Savings") && (elementMatchColorCode(priceEvents_TextElement, "#3333"));
                    break;
                case "Additional Off Closeout":
                    isValid = (priceEvents_TextElement.getText()).equals("Closeout") && (elementMatchColorCode(priceEvents_TextElement, "#3333"));

                    break;
                default:
                    isValid = (priceEvents_TextElement.getText()).equalsIgnoreCase(priceType) && (elementMatchColorCode(priceEvents_TextElement,"#3333"));
            }
            logInfo("Price Lable Matched : " + isValid);
        }
        Assert.assertTrue("Price Labels Not displayed according to Type or Not with correct Color Code ",isValid);
    }

    private static boolean checkEveryDayValue(Map<String, WebElement> thumbnailWebElements) {
        // Checks for EveryDayValue image, label, color for Everyday and priceValue

        boolean isValid = false;
        boolean priceColorValid = false;
        boolean edvColorValid = false;
        String colorCode = "#cc00";
        List<WebElement> overAllPriceWebElements= getAllPriceWebElementsInList(thumbnailWebElements);
        List<String> overAllPriceTags = getAllPriceTagsInList(overAllPriceWebElements);
        WebElement edv_TextElement = thumbnailWebElements.get("elm_everyDayValue");
        WebElement edvImage = thumbnailWebElements.get("elm_everyDayImage");

        if (overAllPriceTags.get(0) !=null){
            priceColorValid = elementMatchColorCode(overAllPriceWebElements.get(0),colorCode);
            System.out.println(" EveryDay Price Validity :"+ priceColorValid);
        }else if (overAllPriceTags.get(0) != null && overAllPriceTags.get(2) != null ){
            priceColorValid = elementMatchColorCode(overAllPriceWebElements.get(0),colorCode) && elementMatchColorCode(overAllPriceWebElements.get(2),colorCode);
            System.out.println(" EveryDay Price Validity :"+ priceColorValid);
        }if (overAllPriceTags.get(4) != null) {
            // everyday price tag can have only one sale value
            priceColorValid = elementMatchColorCode(overAllPriceWebElements.get(4),colorCode);
        }else {
            logInfo(" Price Values not present  :");
        }

        String edvText = edv_TextElement.getText();
        logInfo(" Every Day Value Label Text : "+edvText);

        // edv image and Text color checking
        if (edvText.equals("Everyday Value") && (edvImage != null )){
            edvColorValid = elementMatchColorCode(edv_TextElement,colorCode);
            logInfo(" EDV Color Validity : "+ edvColorValid);
        }
        logInfo(" PriceValue is in Red ? : " + priceColorValid + " Everyday Value Text in Red ? : " +edvColorValid);
        isValid = priceColorValid && edvColorValid;

        // Fails here itself if something incorrect so no further run for this scenario
        Assert.assertTrue("Price Labels and EverydayValue labels are not correct in UI Thumbnail for the given PriceType ",isValid);
        return isValid;
    }


    public static void VerifyPriceTypes(int priceId,String priceType,Map<String, WebElement> thumbnailWebElements){

        List<WebElement> overAllPriceWebElements= getAllPriceWebElementsInList(thumbnailWebElements);
        List<String> overAllPriceTags = getAllPriceTagsInList(overAllPriceWebElements);
        boolean isValid  = AllPriceTypesValidation(priceId,priceType,overAllPriceWebElements,overAllPriceTags);

        // Check for EveryDayValue
        if (priceId == 13){
            // For Everyday Value only we have only one Sale price
            isValid = checkEveryDayValue(thumbnailWebElements) && isValid;
        }
        Assert.assertTrue("Price Types are not correct in UI Thumbnail for the given PriceType ",isValid);
    }


    public static boolean AllPriceTypesValidation(int priceId,String priceType,List<WebElement> overAllPriceWebElements,List<String> overAllPriceTags){
        // Check all available priceTags in the particular format

        boolean allValid = false;
        Map<Integer,List<String>> priceTagPatterns = new HashMap<>();

        // Load with each price Tag pattern
        List<String> firstTagPatterns = new ArrayList<>();
        firstTagPatterns.add("^[$|CAD]");
        firstTagPatterns.add("^[Orig. $|Orig. CAD]");
        firstTagPatterns.add("^[Reg. $|Reg. CAD]");
        priceTagPatterns.put(0,firstTagPatterns);

        Map<String,List<Integer>> priceIdPattern = new HashMap<>();

        List<Integer> firstTagType1 = new ArrayList<>();
        firstTagType1.addAll(Arrays.asList(0, 9, 11, 13));

        List<Integer> firstTagType2 = new ArrayList<>();
        firstTagType2.addAll(Arrays.asList(23, 24, 25, 6, 19, 7, 8, 16, 10));

        List<Integer> firstTagType3 = new ArrayList<>();
        firstTagType3.addAll(Arrays.asList(1, 3, 4, 5, 15, 17, 18, 20, 21, 22, 23, 24, 25, 26 ));

        List<String> FTpatterns = priceTagPatterns.get(0);
        priceIdPattern.put(FTpatterns.get(0),firstTagType1); // $/CAD  => 0,9,11,13
        priceIdPattern.put(FTpatterns.get(1),firstTagType2); // Orig   => 23,24,...
        priceIdPattern.put(FTpatterns.get(2),firstTagType3); // Reg => 1,3,4,...


        Integer priceIdObj = priceId;
        System.out.println(" What is PriceType "+ priceType);

        switch(priceType){
            case "Black Friday Special Type A":
            case "Limited-Time Special Type A":
            case "Deal of the Day Type A":
                boolean tag_valid = false;
                if (overAllPriceTags.get(0) != null) {
                    System.out.println(" The pattern checked is : " + firstTagPatterns.get(2));
                    logInfo(" The pattern checked is : " + firstTagPatterns.get(2));
                    tag_valid = priceIdPattern.get(firstTagPatterns.get(2)).contains(priceIdObj);
                }
                System.out.println(" Zeroth Tag matches with price Id : "+ tag_valid);
                allValid = tag_valid;
                break;
            case "Black Friday Special Type B":
            case "Limited-Time Special Type B":
            case "Deal of the Day Type B":
                boolean tag_valid2 = false;
                if (overAllPriceTags.get(0) != null) {
                    logInfo(" The pattern checked is " + firstTagPatterns.get(1));
                    tag_valid2 = priceIdPattern.get(firstTagPatterns.get(1)).contains(priceIdObj);
                }
                System.out.println(" Zeroth Tag matches with price Id : "+ tag_valid2);
                allValid = tag_valid2;
                break;
            default:
                if (overAllPriceTags.get(0) != null) {
                    boolean tag_valid1 = false;
                    for (String pattern :firstTagPatterns){
                        System.out.println(pattern);
                        tag_valid1 = priceIdPattern.get(pattern).contains(priceIdObj);
                        System.out.println(" Zeroth Tag matches with price Id : "+ tag_valid1);
                        if (tag_valid1 ) {
                            tag_valid1 = DiscoveryHelper.patternMatch(overAllPriceTags.get(0), pattern);
                            break;
                        }
                    }
                    allValid = tag_valid1;
                }

        }

        logInfo("Price Validity after zero validation  : " + allValid);
        List<String> secondTagPatterns = new ArrayList<>();
        secondTagPatterns.add("^[Was $|Was CAD]");
        priceTagPatterns.put(1,secondTagPatterns);

        List<Integer> secondTagType1 = new ArrayList<>();
        secondTagType1.addAll(Arrays.asList(5, 17, 18, 21, 22, 23, 24, 25, 26, 6, 19));

        List<String> STpatterns = priceTagPatterns.get(1);
        priceIdPattern.put(STpatterns.get(0),secondTagType1);

        if (overAllPriceTags.get(1) != null) {
            boolean tag_valid = false;
            for (String pattern :secondTagPatterns){
                logInfo(" The pattern checked is " +pattern);
                tag_valid = priceIdPattern.get(pattern).contains(priceIdObj);
                logInfo("First Tag matches with price Id : "+ tag_valid);
                if (tag_valid ) {
                    tag_valid = DiscoveryHelper.patternMatch(overAllPriceTags.get(1), pattern);
                    break;
                }
            }
            allValid = tag_valid && allValid;
        }else {
            // 5, 17, 18, 21, 22, 23, 24, 25, 26, 6, 19 priceIds must have PriceTag WAS
            // Otherwise it should fail
            if((secondTagType1.contains(priceIdObj))){
                System.out.println("PriceTag WAS Not Displayed");
                allValid = false;
            }
        }

        logInfo("Price Validity after first validation  : " + allValid);

        List<String> thirdTagPatterns = new ArrayList<>();
        thirdTagPatterns.add("^[-]");
        priceTagPatterns.put(2,thirdTagPatterns);

        List<Integer> third_fourthTagType1 = new ArrayList<>();
        third_fourthTagType1.addAll(Arrays.asList(0,9,11,13,1,3,4,5,17,15,18,20,21,22,23,24,25,26,6,19,7,8,16,10));

        List<String> TTpatterns = priceTagPatterns.get(2);
        priceIdPattern.put(TTpatterns.get(0),third_fourthTagType1);

        // For Ranges same pattern
        // 2,3 - no color
        for (int i=2;i<=3;i++){
            if (overAllPriceTags.get(i) != null) {
                boolean tag_valid = false;
                for (String pattern :thirdTagPatterns){
                    tag_valid = priceIdPattern.get(pattern).contains(priceIdObj);
                    logInfo( i+" th Tag matches with price Id : "+ tag_valid);
                    if (tag_valid )
                        tag_valid=DiscoveryHelper.patternMatch(overAllPriceTags.get(i), pattern);
                    break;
                }
                allValid = tag_valid && allValid;
            }
        }

        // (5) range pattern and check for color too
        if (overAllPriceTags.get(5) != null) {
            boolean tag_valid = false;
            for (String pattern :thirdTagPatterns){
                tag_valid = priceIdPattern.get(pattern).contains(priceIdObj);
                logInfo( 5 +" th Tag matches with price Id : "+ tag_valid);
                if (tag_valid )
                    tag_valid=DiscoveryHelper.patternMatch(overAllPriceTags.get(5), pattern) &&elementMatchColorCode(overAllPriceWebElements.get(5),"#cc00") && tag_valid;
                break;
            }
            allValid = tag_valid && allValid;
        }

        logInfo("Price Validity after 2,3,5 validation  : " + allValid);

        List<String> fifthTagPatterns = new ArrayList<>();
        fifthTagPatterns.add("^[$|CAD]");
        fifthTagPatterns.add("^[Now $|Now CAD]");
        fifthTagPatterns.add("^[Sale $|Sale CAD]");
        priceTagPatterns.put(4,fifthTagPatterns);

        //NOW
        List<Integer> fifthTagType2 = new ArrayList<>();
        fifthTagType2.addAll(Arrays.asList( 7, 8, 16, 10));

        //SALE
        List<Integer> fifthTagType3 = new ArrayList<>();
        fifthTagType3.addAll(Arrays.asList(1, 3, 4, 5, 6, 15, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 ));

        List<String> FITpatterns = priceTagPatterns.get(4);
        priceIdPattern.put(FITpatterns.get(0),firstTagType1);
        priceIdPattern.put(FITpatterns.get(1),fifthTagType2);
        priceIdPattern.put(FITpatterns.get(2),fifthTagType3);

        if (overAllPriceTags.get(4) != null) {
            boolean tag_valid = false;
            for (String pattern :fifthTagPatterns){
                logInfo(" The pattern checked is " + pattern);
                tag_valid = priceIdPattern.get(pattern).contains(priceIdObj);
                logInfo("Fourth Tag matches with price Id : "+ tag_valid);
                if (tag_valid ){
                    logInfo("Pattern Matches : "+ DiscoveryHelper.patternMatch(overAllPriceTags.get(4), pattern));
                    logInfo("Color Matches : "+ elementMatchColorCode(overAllPriceWebElements.get(4), "#cc00"));
                    tag_valid = DiscoveryHelper.patternMatch(overAllPriceTags.get(4), pattern) && elementMatchColorCode(overAllPriceWebElements.get(4), "#cc00");
                    break;
                }
            }

            if (priceId == 13){
                // For Everyday Value only we have only one Sale price
                allValid = tag_valid;
            }else {
                allValid = tag_valid && allValid;
            }
        }else {
            // Other than 0,13,11,9 priceIds all other must have PriceTag either NOW / Sale
            // Otherwise it should fail
            if(!(firstTagType1.contains(priceIdObj))){
                System.out.println("PriceTag NOW / SALE  Not Displayed");
                allValid = false;
            }
        }

        System.out.println("Price Validity after all validations of priceTags : " + allValid);
        logInfo("Price Validity after all validations of priceTags : " + allValid);
        return allValid;
    }


    public static boolean patternMatch(String givenString,String regex){
        // Checks whether given string matches the given regular expression
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(givenString);
        boolean match = m.find();
        return match;
    }

    public static boolean elementMatchColorCode(WebElement uiElement,String colorCode){
        // For any given UI Element will check whether it has given colorCode

        boolean colorMatching = false;
        String rgbColor = uiElement.getCssValue("color");
        String[] numbers = rgbColor.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        String hexValue = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
        logInfo("Hex Value for that color "+ hexValue);
        if (hexValue.contains(colorCode)) colorMatching = true;
        logInfo("Color Matches :" + colorMatching);
        return colorMatching;
    }

    //This method returns map => name and value pair from queryString of given tid and pi

    public static Map<String,String> configureSpecificActualTagsFromHar(String tidValue,String piValue){
        Map<String,String> specificQueryStringMap = new HashMap<>();

        for(HarEntry harEntry : harBuffer) {
           HarRequest request = harEntry.getRequest();
           if(request != null) {
               List<HarNameValuePair> queryString = request.getQueryString();
               if(queryString != null) {
                   boolean isTid = false;
                   boolean isPi = false;
                   for (HarNameValuePair valuePair : queryString) {
                       if ("tid".equalsIgnoreCase(valuePair.getName())) {
                           if (valuePair.getValue().equals(tidValue)) {
                               isTid = true;
                           }
                       }
                       if ("pi".equalsIgnoreCase(valuePair.getName())) {
                           if (valuePair.getValue().equals(piValue)) {
                               isPi = true;
                           }
                       }
                   }

                    if (isTid && isPi) {
                        for (HarNameValuePair valuePair : queryString) {
                            specificQueryStringMap.put(valuePair.getName(), valuePair.getValue());
                        }
                    }
               }
           }

       }
        return specificQueryStringMap;
    }

      //This method returns map => name and value pair from queryString

    public static Map<String,String> configureActualTagsFromHar(){

       Map<String,String> allQueryStringMap = new HashMap<>();

        for(HarEntry harEntry : harBuffer) {
            HarRequest request = harEntry.getRequest();
            if (request != null) {
                List<HarNameValuePair> queryString = request.getQueryString();
                if(queryString != null) {
                    for (HarNameValuePair valuePair : queryString) {
                        allQueryStringMap.put(valuePair.getName(), valuePair.getValue());
                    }
                }
            }
        }
        return allQueryStringMap;
    }

    // To print what is present in HarBuffer before flushing to gold folder after each step
    public static void printActualTags(){

        Map<String,String> actualPageViewTags = configureActualTagsFromHar();

        for (Map.Entry<String, String> entry : actualPageViewTags.entrySet()) {
            System.out.println(entry.getKey()+ "  :  " +entry.getValue());
        }

    }

    /*
      Lands on Dynamic Landing Page
    */
    public static void landingDynamicLandingPage(String modeName) {

        // coded for macys
        // DLP for BCOM not included now.
        String text = null;
        switch(modeName){
            case "domestic":
                text = "Abbyson Living";
                break;
            case "iship":
                text = "adidas";
                break;
            case "registry":
                text = "Aerobed";
                break;
        }

        try {

            List<WebElement> a_zBrandNames = Elements.findElements((By.cssSelector("div[class='brand-box'] ul li a")));
            WebElement selectedbrand = null;
            for (WebElement option : a_zBrandNames) {
                if (option.getText().equals(text)) {
                    selectedbrand = option;
                    break;
                }
            }
            Clicks.click(selectedbrand);
        } catch (Exception e) {
            Assert.fail("Error landing in Dynamic Landing Page");
        }
        logInfo("Landing on Dynamic landing Page");
    }

        public static void navigateToComponentizationURL(){
            String componentizationUrl;
            String currentURL = WebDriverManager.getCurrentUrl();
            if (currentURL.contains("EFCKEY"))
                componentizationUrl = currentURL.replaceAll("EFCKEY(.*)","EFCKEY=%7B%22EXPERIMENT%22%3A%5B2245%5D%7D");
            else if (currentURL.contains("\\?keyword")) {
                componentizationUrl = currentURL.concat("&EFCKEY=%7B%22EXPERIMENT%22%3A%5B2245%5D%7D");
            }
            else {
                componentizationUrl = currentURL.concat("?EFCKEY=%7B%22EXPERIMENT%22%3A%5B2245%5D%7D");
            }
            Navigate.visit(componentizationUrl);;
    }

}


