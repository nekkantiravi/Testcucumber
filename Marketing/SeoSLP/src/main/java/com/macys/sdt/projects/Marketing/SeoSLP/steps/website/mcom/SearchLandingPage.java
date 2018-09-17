package com.macys.sdt.projects.Marketing.SeoSLP.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Architecture.MSH.steps.website.MSHSmoke;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import com.macys.sdt.projects.Marketing.SeoSLP.utils.DBSeoSlp;
import com.macys.sdt.projects.Marketing.SeoSLP.utils.SeoSLP;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.Utils.getFileDataInJson;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;
import static java.net.URLDecoder.decode;


/**
 * Created by SEO SLP team on 3/20/2017.
 */
public class SearchLandingPage extends StepUtils {

    public List<WebElement> facetItems;
    public static List<WebElement> facetValues;
    public List<String> selectedFacets = new ArrayList<>();
    public static String facetName;
    public String selectedFacetToBeClear;
    public List<String> facetNames = new ArrayList<>();
    public List<String> allFacetItems;
    public String size;
    public static int productCount;
    public static HashMap<String, String> hm = new HashMap<String, String>();
    public static ArrayList<String> selectedFacetValues = new ArrayList<>();


    private static final Logger logger = LoggerFactory.getLogger(SeoSLP.class);


    @When("^I navigate to SLP browse category page$")
    public void I_navigate_to_SLP_browse_category_page() throws Throwable {
        Navigate.visit(RunConfig.url + "/shop/b?id=71407");
        //Navigate.visit("http://www.qa20codemacys.fds.com/shop/b?id=71407");
    }

    @Then("^I should see the SLP page$")
    public void I_should_see_the_SLP_page() throws Throwable {
        //Navigate.visit(MainRunner.url + "/shop/b?id=71407");
    }

    @Then("^I should see item count buttons in page$")
    public void iShouldSeeItemCountButtonsInPage() throws Throwable {
        Assert.assertTrue("ERROR - APP : Item count buttons are not displayed on the page",
                Elements.findElements("pagination.item_count_buttons").size() > 0);
        logger.info("Item count buttons are displayed on the page");
    }

    @And("^I select any product from thumbnail grid$")
    public void iSelectAnyProductFromThumbnailGrid() throws Throwable {
        Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
        Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
        Clicks.clickRandomElement("pagination.thumbnailgrid_products");
        logger.info("selected one product from thumbnail grid");
    }

    @Then("^I should be navigated to respective product display page$")
    public void iShouldBeNavigatedToRespectiveProductDisplayPage() throws Throwable {
        shouldBeOnPage("product_display");
        logger.info("Product page is displayed");
    }

    @When("^I select browser back button on pdp page$")
    public void iSelectBrowserBackButtonOnPDPPage() {
        Navigate.browserBack();
        logger.info("Clicked on browser back button");
    }

    @Then("^I should be navigated back to \"Browse\" page and same product location$")
    public void iShouldBeNavigatedBackToBrowsePageAndSameProductLocation() {
        shouldBeOnPage("category_browse");
        logger.info("Navigated back to same product location");
    }

    @When("^I select \"([^\"]*)\" item count option$")
    public void iSelectItemCountOPtion(String itemcount) {
        Clicks.click("pagination.item_count_buttons_" + itemcount);
        logger.info("Selected " + itemcount + " item count option");
    }

    @And("^I navigated to next page of thumbnail grid$")
    public void iNavigatedToNextPageOfThumbnailGrid() {
        Clicks.click("pagination.nextpage_thumbnailgrid");
        logger.info("Navigated to next page of thumbnail grid");
    }

    @And("^I should see check mark replaces the check box for the selected facet value$")
    public void iShouldSeeCheckMarkReplacesTheCheckBoxForTheSelectedFacetValue() throws Throwable {
        By facetItems = LeftFacet.getFacetItems(MSHSmoke.facetName);
        facetValues = Elements.findElements(facetItems);
        Assert.assertTrue("ERROR - APP : check box not selected",
                facetValues.stream().anyMatch(ele -> ele.findElement(By.xpath("..")).getAttribute("class").contains("selected") && ele.getText().equals(MSHSmoke.selectedFacetItem)));
        logger.info("check box is replaced with check mark");
    }

    @Then("^I should see clear all button displayed on facet panel$")
    public void iShouldSeeClearAllButtonDisplayedOnFacetpanel() throws Throwable {
        Elements.elementShouldBePresent("left_facet.clear_all_facets");
        logger.info("Clear All button is displayed in the facet panel");
    }

    @Then("^I should see facet values from all facets are removed$")
    public void iShouldSeeFacetValuesFromAllFacetsAreRemoved() {
        List<String> facetNames = MSHSmoke.getAllFacetName();
        facetNames.remove("Pick Up In Store");
        facetNames.remove("Free Pick Up In Store");
        facetNames.remove("Sales & Offers");
        facetNames.remove("Customer Ratings");
        int numberOfFacets = facetNames.size();
        int index = 0;
        pausePageHangWatchDog();
        while (index < numberOfFacets) {
            facetName = facetNames.get(index);
            //Navigate.browserRefresh();
            List<WebElement> facetItems = SeoSLP.getAllFacetItems(facetName.toUpperCase());
            Assert.assertTrue("ERROR - APP : Facet values are not removed from all facets",
                    facetItems.stream().anyMatch(ele -> !ele.findElement(By.xpath("..")).getAttribute("class").contains("selected")));
            index++;
        }
        resumePageHangWatchDog();
        logger.info("Facet values from all facets are removed");
    }

    @And("^I should see product assortment is updated after \"([^\"]*)\"$")
    public void iShouldSeeProductAssortmentIsUpdatedAfter(String operator) {
        switch (operator) {

            case "selected":
                Assert.assertTrue("ERROR - APP : product assortment is not updated", MSHSmoke.getProductCount() < MSHSmoke.productCount);
                break;
            case "deselectAll":
                Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
                Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
                Assert.assertTrue("ERROR - APP : Product count is not updated", MSHSmoke.productCount == MSHSmoke.getProductCount());
                break;
            case "deselectOne":
                Assert.assertTrue("ERROR - APP : Product count is not updated", MSHSmoke.productCount >= MSHSmoke.getProductCount());
                break;
        }
        logger.info("Product assortment got updated");

    }

    @And("^I should see clear all button is removed$")
    public void iShouldSeeClearAllButtonIsRemoved() {
        Wait.untilElementNotPresent("left_facet.clear_all_facets");
        Assert.assertFalse("ERROR - APP : Clear All button is removed", Elements.elementPresent("left_facet.clear_all_facets"));
        logger.info("Clear All button is removed");
    }

    @When("^I select two facet values in facet panel$")
    public void iSelectTwoFacetValuesInfacetPanel() {
        pausePageHangWatchDog();
        MSHSmoke.productCount = MSHSmoke.getProductCount();
        Navigate.browserRefresh();
        List<String> facetNames = MSHSmoke.getAllFacetName();
        int index = 0;
        int numberOfFacets = facetNames.size();
        while (index < numberOfFacets) {
            if (facetNames.get(index).equalsIgnoreCase("Gender") || facetNames.get(index).equalsIgnoreCase("Size")) {
                String facetName = facetNames.get(index);
                facetItems = SeoSLP.getAllFacetItems(facetName);
                Random ran = new Random();
                int itemIndex = ran.nextInt(facetItems.size());
                WebElement facetItem = facetItems.get(itemIndex);
                selectedFacets.add(facetItem.getText());
                Clicks.click(facetItem);
                Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
                Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
            }
            index++;
        }
        logger.info("Selected 2 facet values(Gender,Size) in facet panel ");
        resumePageHangWatchDog();
    }


    @And("^I should see \"COPY_BLOCK\" on the page in \"101,0\" rows$")
    public void iShouldSeeCopyBlockOnThePageInRows() {
        Wait.secondsUntilElementNotPresent("flexible_template.copy_block", 30);
        Assert.assertTrue("ERROR - APP : Copy block data is not displaying on the page", Elements.elementPresent("flexible_template.copy_block"));
        logger.info("Copy block data is displaying on UI");

    }

    @Then("^I should see Copy Block \"removed\"$")
    public void iShouldSeeCopyBlockRemoved() {
        Wait.secondsUntilElementNotPresent("flexible_template.copy_block", 30);
        Assert.assertFalse("ERROR - APP : Copy block data is displaying on the page", Elements.elementPresent("flexible_template.copy_block"));
        logger.info("Copy block data is not displaying on UI");
    }

    @And("^I should see Breadcrumbs of all selected facets$")
    public void iShouldSeeBreadcrumbsOfAllSelectedFacet() {
        selectedFacets.add(MSHSmoke.selectedFacetItem);
        Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
        Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
        Wait.until(() -> Elements.findElements("flexible_template.breadcrumbs").size() == 4, 20);

        Assert.assertTrue("ERROR - APP : Breadcrumbs of all selected facets are not displayed", selectedFacets.stream().map(txt -> txt.replace("\n", ", ")).collect(Collectors.toList())
                .containsAll(Elements.findElements("flexible_template.breadcrumbs").stream().map(WebElement::getText).collect(Collectors.toList())));
        logger.info("Breadcrumbs of all selected facets are displayed");
    }

    @And("^I click Clear link for any facet type$")
    public void iClickClearLinkForAnyFacetType() {
        pausePageHangWatchDog();
        List<WebElement> clearFacetValues = Elements.findElements("left_facet.clear_all_facet_selections").stream()
                .filter(ele -> !(ele.getAttribute("class").contains("hidden")) && ele.isDisplayed())
                .collect(Collectors.toList()); //contains clear elements with not hidden
        Random ran = new Random();
        int index = ran.nextInt(clearFacetValues.size());
        WebElement selectedClearFacet = clearFacetValues.get(index);
        String listOfValues[] = selectedClearFacet.getAttribute("title").split(" ");
        facetName = listOfValues[2];
        By facetItems = LeftFacet.getFacetItems(facetName);
        facetValues = Elements.findElements(facetItems);
        for (WebElement w : facetValues) {

            if (w.findElement(By.xpath("..")).getAttribute("class").contains("selected")) {
                selectedFacetToBeClear = w.getText();
            }

        }
        Clicks.click(selectedClearFacet);
        logger.info("Clicked on Clear link on facet type");
        resumePageHangWatchDog();
    }

    @Then("^I should see that facet values get unselected and removed from breadcrumb$")
    public void iShouldSeeThatFacetValuesGetUnselectedAndRemovedFromBreadcrumb() {
        Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
        Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
        if (!LeftFacet.isExpanded(facetName.toUpperCase())) {
            LeftFacet.expandFacet(facetName.toUpperCase());
        }
        pausePageHangWatchDog();
        By facetItems = LeftFacet.getFacetItems(facetName);
        facetValues = Elements.findElements(facetItems);
        List<String> breadcrumbsText = Elements.findElements("flexible_template.breadcrumbs").stream().map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP : Facet values are not unselected",
                facetValues.stream().anyMatch(ele -> !ele.findElement(By.xpath("..")).getAttribute("class").contains("selected")));

        Assert.assertFalse("ERROR - APP : cleared facet of breadcrumb not removed", breadcrumbsText.contains(selectedFacetToBeClear));
        logger.info("Facet values are unselected and cleared facet of breadcrumb is removed");
        resumePageHangWatchDog();
    }

    @When("^I close Breadcrumb of any selected facet$")
    public void iCloseBreadcrumbOfAnySelectedFacet() {
        Clicks.clickRandomElement("left_facet.remove_breadcrumbs");
        logger.info("I Closed the breadcrumb of any selected facet");
    }

    @Then("^I should see Breadcrumbs are removed from pagination section$")
    public void iShouldSeeBreadcrumbAreRemovedFromPaginationSection() {
        Assert.assertFalse("ERROR - APP : Breadcrumbs are not removed from paginationsection",
                Elements.elementPresent("flexible_template.breadcrumbs_pagination"));
        logger.info("Breadcrumbs are removed from pagination section");
    }

    @When("^I select multiple values of a single facet from the left nav$")
    public void iSelectMultipleValuesOfASingleFacetFromTheLeftNav() {
        List<String> facetItems = DiscoveryHelper.getAllFacetValues("Color");
        for (int i = 0; i < 2; i++) {
            Clicks.click(Elements.paramElement("left_facet.select_color_facet", facetItems.get(i)));
            Wait.secondsUntilElementPresent("category_browse.loading_mask", 10);
            Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
            selectedFacets.add(facetItems.get(i));
        }
    }

    @And("^I select one facet value in facet panel$")
    public void iSelectOneFacetValueInFacetPanel() {

        facetNames.add("Size");
        facetItems = SeoSLP.getAllFacetItems("Size");

        Random ran = new Random();
        int itemIndex = ran.nextInt(facetItems.size());
        WebElement facetItem = facetItems.get(itemIndex);
        String facet = facetItem.getText();
        selectedFacets.add(facet);
        String attr[] = facetItem.findElement(By.xpath("..")).getAttribute("href").split(",");
        String attr1[] = attr[1].split("/");
        size = attr1[0];
        Clicks.click(facetItem);
        Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
        Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);

    }

    @And("^I should see resulting url with all selected facet values$")
    public void iShouldSeeResultingUrlWithAllSelectedFacetValues() throws URISyntaxException {
        String actualUrl = null;
        String url = WebDriverManager.getCurrentUrl();

        Pattern pattern = Pattern.compile("(.*)");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            actualUrl = matcher.group();
        }
        for (String str : selectedFacets) {
            if (str.contains("/")) {
                String facetarr[] = str.split("/");
                for (int i = 0; i < facetarr.length; i++) {
                    facetarr[i].replace("/", "");
                    Assert.assertTrue("ERROR:selected facets are not displayed in the url", actualUrl.toLowerCase().contains(facetarr[i].toLowerCase()));
                }
            } else if (str.contains(" ")) {
                String facetarr[] = str.split(" ");
                for (int i = 0; i < facetarr.length; i++) {
                    Assert.assertTrue("ERROR:selected facets are not displayed in the url", actualUrl.toLowerCase().contains(facetarr[i].toLowerCase()));
                }
            } else {
                Assert.assertTrue("ERROR:selected facets are not displayed in the url", actualUrl.toLowerCase().contains(str.toLowerCase()));
            }
        }
        logger.info("Resulting url is displayed with all selected facets");
    }

    @And("^URL should be in \"([^\"]*)\" format after selecting facets$")
    public void UrlShouldBeInFormatAfterSelectingFacets(String expectedUrl) throws URISyntaxException {
        String actualUrl = null;
        String url = WebDriverManager.getCurrentUrl();

        Pattern pattern = Pattern.compile(".com[/A-Za-z0-9,%]+(.\\d)(,[a-z0-9]{2,})");
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            actualUrl = matcher.group();
        }

        for (int i = 0; i < selectedFacets.size(); i++) {

            expectedUrl = expectedUrl.replace("facetValue" + i, selectedFacets.get(i));
        }
        expectedUrl = expectedUrl.replace("sizefacetValue", size);
        String encodedUrl = new java.net.URI(null, expectedUrl, null).toASCIIString();

        for (String str : selectedFacets) {
            if (str.contains("/")) {
                String newStr = str.replace("/", "%2F");
                encodedUrl = encodedUrl.replace(str, newStr);
            }
        }
        Assert.assertTrue("ERROR:url is not as expected", actualUrl.equalsIgnoreCase(encodedUrl));

    }

    @And("^The canonical tag should have single facet value for each facet selected$")
    public void TheCanonicalTagShouldHaveSingleFacetValueForEachFacetSelected() {
        List<WebElement> linkEle = Elements.findElements(By.tagName("link"));
        List<String> url = linkEle.stream().filter(ele -> ele.getAttribute("rel").contains("canonical")).map(ele -> ele.getAttribute("href")).collect(Collectors.toList());
        String canonicalTag = url.get(0);
        for (String facet : facetNames) {
            Assert.assertTrue("ERROR:For each selected facet,Single facet not exist in canonical tag", canonicalTag.toLowerCase().contains(facet.toLowerCase()));
        }
        logger.info("The canonical tag is having single facet value for each facet selected");

    }

    @When("^I select one facet value in facet panel with crawlable facets$")
    public void iSelectOneFacetValueInfacetPanelWithCrawlablefacets() {
        facetItems = SeoSLP.getAllFacetItems("Color");
        Random ran = new Random();
        int ItemIndex = ran.nextInt(facetItems.size());
        WebElement facetItem = facetItems.get(ItemIndex);
        selectedFacets.add(facetItem.getText());
        Clicks.click(facetItem);
        Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
        Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);

    }

    @Then("^I should see selected facet value in canonical tag$")
    public void iShouldSeeSelectedfacetValueInCanonicalTag() {
        List<WebElement> linkEle = Elements.findElements(By.tagName("link"));
        List<String> url = linkEle.stream().filter(ele -> ele.getAttribute("rel").contains("canonical")).map(ele -> ele.getAttribute("href")).collect(Collectors.toList());
        for (String facet : selectedFacets) {
            Assert.assertTrue("ERROR:Selected facet value is not in canonical tag", url.get(0).contains(facet));
        }
        logger.info("Selected facet value is in canonical tag");
    }

    @Then("^I should see every facet value selected is included in the view source title tag$")
    public void iShouldSeeEveryFacetValueSelectedIsIncludedInTheViewSourceTitleTag() {
        String url = WebDriverManager.getCurrentTitle();
        for (String str : selectedFacets) {
            Assert.assertTrue("ERROR:Selected facet values are not include in the view source title tag", url.toLowerCase().contains(str.toLowerCase()));
        }
        logger.info("Every facet value selected is included in the view source title tag");
    }

    @And("^I should see rel tag with alternate in view page source$")
    public void iShouldSeeRelTagInViewPageSource() {
        try {
            String pageSource = WebDriverManager.getWebDriver().getPageSource();
            Assert.assertTrue("ERROR:Rel tag is not displayed in view page source ", pageSource.contains("alternate"));
            logger.info("Rel tag is displayed in view page source");
        } catch (Exception e) {
        }

    }

    @And("^I should see that facet values are removed from breadcrumb$")
    public void iShouldSeeThatFacetValuesAreRemovedFromBreadcrumb() throws Throwable {
        List<String> breadCrumbsText = Elements.findElements(Elements.element("left_facet.facetBreadcrumbs")).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        Assert.assertTrue("ERROR APP: Facets are not removed from the Breadcrum" + breadCrumbsText, breadCrumbsText.isEmpty());

    }

    @And("^I should see selected facets in breadcrumb$")
    public void iShouldSeeSelectedFacetsInBreadcrumb() throws Throwable {
        Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
        Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
        Wait.until(() -> Elements.findElements("flexible_template.breadcrumbs").size() == 4, 20);

        Assert.assertTrue("ERROR - APP : Breadcrumbs of all selected facets are not displayed", selectedFacets.stream().map(txt -> txt.replace("\n", ", ")).collect(Collectors.toList())
                .containsAll(Elements.findElements("flexible_template.breadcrumbs").stream().map(WebElement::getText).collect(Collectors.toList())));
        logger.info("Selected facets are displayed in breadcrumb");
    }

    @Then("^I should see page description as expected")
    public void iShouldSeePageDescription() throws Throwable {
        Assert.assertTrue("ERROR - APP : Page Description is not displaying ", Elements.getElementAttribute("left_navigation_category.category_description", "content").contains(DBSeoSlp.getMetaDescription(SeoSLP.getCategoryID())));
    }

    @Then("^I should see page title matches with Stella page title$")
    public void iShouldSeePageTitleMatchesWithStellaPageTitle() throws Throwable {
        Assert.assertTrue("ERROR - APP : Page Title is not matches with Stella page title", title().contains(DBSeoSlp.getPageTitle(SeoSLP.getCategoryID())));
    }

    @And("^I should see page header is matched the with \"([^\"]*)\" name$")
    public void iShouldSeePageHeaderIsMatchedTheWithName(String expectedHeader) throws Throwable {
        Assert.assertTrue("ERROR - APP : Page Header is not matched the with Category name", Elements.getText("left_navigation_category.category_header").equalsIgnoreCase(expectedHeader));
    }


    @Then("^I should see only one facet value in title tag according to alpha order$")
    public void iShouldSeeOnlyOneFacetValueInTitleTagAccordingToAlphaOrder() throws Throwable {
        Collections.sort(selectedFacetValues);
        Assert.assertTrue("ERROR - APP : Facet value in title tag is not displaying according to alpha order", title().contains(selectedFacetValues.get(0)));
        Assert.assertFalse("ERROR - APP : Facet value is displaying in title tag", title().contains(selectedFacetValues.get(1)));
    }

    @When("^I select (\\d+) facet values in facet panel with \"([^\"]*)\" facets$")
    public void iSelectFacetValuesInFacetPanelWithFacets(int j, String typeOfFacet) throws Throwable {
        List<String> availableFacetNames = Elements.findElements("left_facet.facet_names").stream().filter(WebElement::isDisplayed).map(WebElement::getText).collect(Collectors.toList());
        JSONObject jsonObject = Utils.getFileDataInJson(Utils.getResourceFile("facet.json"));
        JSONObject type_of_json = jsonObject.getJSONObject(typeOfFacet.equals("crawlable") ? "CRAWLABLE_FACETS" : "NON_CRAWLABLE_FACETS");
        ArrayList<Object> ri = new ArrayList<>(type_of_json.keySet());
        for (int i = 1; i <= j; i++) {
            String re = ri.get(new Random().nextInt(ri.size())).toString();
            ri.remove(re);
            if (availableFacetNames.contains(re)) {
                if (!LeftFacet.isExpanded(re))
                    LeftFacet.expandFacet(re);
                if (re.equals("Brand"))
                    Clicks.click(By.id("all_brands"));
                String facetValue = Elements.getRandomElement(LeftFacet.getFacetItems(re)).getText();
                hm.put(re, facetValue);
                LeftFacet.selectItemFromFacet(facetValue, re);
                LeftFacet.collapseFacet(re);
                Wait.secondsUntilElementNotPresent("left_facet.loading", 25);
            } else {
                Assert.fail("The facet in json data file is not available in UI");
            }
        }
    }

    @When("^I navigate the \"([^\"]*)\" category$")
    public void iNavigateTheCategory(String browseCategory) throws Throwable {
        JSONObject slpCategories = getFileDataInJson(getResourceFile("category.json"));
        JSONArray categories = slpCategories.getJSONArray(browseCategory);
        Random random = new Random();
        int number = random.nextInt(categories.length());
        String category = (String) categories.get(number);
        Navigate.visit(RunConfig.url + "/shop/category?id=" + category);
        logger.info("Navigated to category browse " + category);
    }

    @Then("^I should see only (\\d+) facet values based on facet types alphanumeric order in title tag$")
    public void iShouldSeeOnlyFacetValuesBasedOnFacetTypesAlphanumericOrderInTitleTag(int expecteFacetValues) throws Throwable {
        TreeMap<String, String> sorted = new TreeMap<>(hm);
        int i = 1;
        Set<Map.Entry<String, String>> mappings = sorted.entrySet();
        for (Map.Entry<String, String> mapping : mappings) {
            if (i <= expecteFacetValues) {
                Assert.assertTrue("ERROR - APP : Facet value in title tag is not displaying according to alpha order", title().contains(mapping.getValue()));
            } else {
                Assert.assertFalse("ERROR - APP : Facet value in title tag is not displaying according to alpha order", title().contains(mapping.getValue()));
            }
            i++;
        }
    }


    @Then("^I select (\\d+) facet values in facet panel from single \"([^\"]*)\" facet$")
    public void iSelectFacetValuesInFacetPanelFromSingleFacet(int j, String typeOfFacet) throws Throwable {
        selectedFacetValues.clear();
        List<String> selectedFacets = new ArrayList<>();
        String facetValue;
        List<String> availableFacetNames = Elements.findElements("left_facet.facet_names").stream().filter(WebElement::isDisplayed).map(WebElement::getText).collect(Collectors.toList());
        JSONObject jsonObject = Utils.getFileDataInJson(Utils.getResourceFile("facet.json"));
        JSONObject type_of_json = jsonObject.getJSONObject(typeOfFacet.equals("crawlable") ? "CRAWLABLE_FACETS" : "NON_CRAWLABLE_FACETS");
        ArrayList<Object> ri = new ArrayList<>(type_of_json.keySet());
        String re = ri.get(new Random().nextInt(ri.size())).toString();
        for (int i = 1; i <= j; i++) {
            if (availableFacetNames.contains(re)) {
                if (!LeftFacet.isExpanded(re))
                    LeftFacet.expandFacet(re);
                if (re.equals("Brand"))
                    if (Elements.elementPresent(By.id("all_brands"))) {
                        Clicks.click(By.id("all_brands"));
                    }
                if (selectedFacets.isEmpty()) {
                    facetValue = Elements.getRandomElement(LeftFacet.getFacetItems(re)).getText();
                    selectedFacets.add(facetValue);
                } else {
                    facetValue = Elements.getRandomElement(LeftFacet.getFacetItems(re)).getText();
                    if (selectedFacets.contains(facetValue)) {
                        facetValue = Elements.getRandomElement(LeftFacet.getFacetItems(re)).getText();
                    }
                }
                selectedFacetValues.add(facetValue);
                LeftFacet.selectItemFromFacet(facetValue, re);
                LeftFacet.collapseFacet(re);
                Wait.secondsUntilElementNotPresent("left_facet.loading", 25);
            } else {
                Assert.fail("The facet in json data file is not available in UI");
            }
        }
    }

    @Then("^I should not see non crawble facet values in title tag$")
    public void iShouldNotSeeNonCrawbleFacetValuesInTitleTag() throws Throwable {
        Assert.assertFalse("ERROR - APP : Non crawble Facet value is displaying in title tag", title().contains(selectedFacetValues.get(0)));
    }

    @Then("^I should see page header is matching with Category Name$")
    public void iShouldSeePageHeaderIsMatchingWithCategoryName() throws Throwable {
        Assert.assertTrue("ERROR - APP : Page Header is not matched the with Category name", Elements.getText("left_navigation_category.category_header").equalsIgnoreCase(DBSeoSlp.getCategoryName(SeoSLP.getCategoryID())));
    }


    @Then("^I should see selected facet value in header tag$")
    public void iShouldSeeSelectedFacetValueInHeaderTag() throws Throwable {
        Assert.assertTrue("ERROR - APP : Page Header is not matched the with Category name", Elements.getText("left_navigation_category.category_header").equalsIgnoreCase(selectedFacetValues.get(0) + " " + DBSeoSlp.getCategoryName(SeoSLP.getCategoryID())));
    }

    @Then("^I should see only one facet value in header tag according to alpha order$")
    public void iShouldSeeOnlyOneFacetValueInHeaderTagAccordingToAlphaOrder() throws Throwable {
        Collections.sort(selectedFacetValues);
        Assert.assertTrue("ERROR - APP : Page Header is not matched the with Category name", Elements.getText("left_navigation_category.category_header").equalsIgnoreCase(selectedFacetValues.get(0) + " " + DBSeoSlp.getCategoryName(SeoSLP.getCategoryID())));
        Assert.assertFalse("ERROR - APP : Page Header is matched the with Category name", Elements.getText("left_navigation_category.category_header").contains(selectedFacetValues.get(1)));
    }

    @Then("^I should see only (\\d+) facet values based on facet types alphanumeric order in page header$")
    public void iShouldSeeOnlyFacetValuesBasedOnFacetTypesAlphanumericOrderInPageHeader(int expecteFacetValues) throws Throwable {
        TreeMap<String, String> sorted = new TreeMap<>(hm);
        int i = 1;
        Set<Map.Entry<String, String>> mappings = sorted.entrySet();
        for (Map.Entry<String, String> mapping : mappings) {
            if (i <= expecteFacetValues) {
                Assert.assertTrue("ERROR - APP : Facet value in page header is not displaying according to alpha order", Elements.getText("left_navigation_category.category_header").contains(mapping.getValue()));
            } else {
                Assert.assertFalse("ERROR - APP : Facet value in page header is displaying", Elements.getText("left_navigation_category.category_header").contains(mapping.getValue()));
            }
            i++;
        }
        logger.info("Selected facet value is in page header");
    }

    @Then("^I should see selected facet value in canonical tag url$")
    public void iShouldSeeSelectedFacetValueInCanonicalTagUrl() throws Throwable {
        Collections.sort(selectedFacetValues);
        String afterTagDecode = decode(decode(Elements.findElement(By.cssSelector("[rel=\'canonical\']")).getAttribute("href"), "UTF-8"), "UTF-8").replace("|", "-");
        for (String facet : selectedFacetValues) {
            Assert.assertTrue("ERROR - APP : Selected facet value is not in canonical tag", afterTagDecode.contains(facet));
        }
        logger.info("Selected facet value is in canonical tag");
    }

    @Then("^I should not see selected facet value in canonical tag$")
    public void iShouldNotSeeSelectedFacetValueInCanonicalTag() throws Throwable {
        Collections.sort(selectedFacetValues);
        String afterTagDecode = decode(decode(Elements.findElement(By.cssSelector("[rel=\'canonical\']")).getAttribute("href"), "UTF-8"), "UTF-8").replace("|", "-");
        for (String facet : selectedFacetValues) {
            Assert.assertFalse("ERROR - APP : Selected facet value is in canonical tag", afterTagDecode.contains(facet));
        }
        logger.info("Selected facet value is in canonical tag");
    }

    @Then("^I should see selected facet values based on facet value alphanumeric order in canonical tag$")
    public void iShouldSeeSelectedFacetValuesBasedOnFacetValueAlphanumericOrderInCanonicalTag() throws Throwable {
        String afterTagDecode = decode(decode(Elements.findElement(By.cssSelector("[rel=\'canonical\']")).getAttribute("href"), "UTF-8"), "UTF-8").replace("|", "-");
        TreeMap<String, String> sorted = new TreeMap<>(hm);
        Set<Map.Entry<String, String>> mappings = sorted.entrySet();
        for (Map.Entry<String, String> mapping : mappings) {
            Assert.assertTrue("ERROR - APP : Selected facet value is not in canonical tag", afterTagDecode.contains(mapping.getValue()));
        }
    }

    @Then("^I should see selected facet value in metadescription$")
    public void iShouldSeeSelectedFacetValueInMetadescription() throws Throwable {
        String metaDescription = Elements.findElement("header.meta_description").getAttribute("content");
        for (String facetvalue : selectedFacetValues) {
            Assert.assertTrue("ERROR - APP :Selected facet value is not in metadescription", metaDescription.contains(facetvalue));
        }
        logger.info("Selected facet value is in metadescription");
    }

    @Then("^I should not see selected facet value in metadescription$")
    public void iShouldNotSeeSelectedFacetValueInMetadescription() throws Throwable {
        String metaDescription = Elements.findElement("header.meta_description").getAttribute("content");
        for (String facetvalue : selectedFacetValues) {
            Assert.assertFalse("ERROR - APP :Selected facet value is in canonical tag", metaDescription.contains(facetvalue));
        }
        logger.info("Selected facet value is not in metadescription");
    }

    @When("^I navigates DLP page through Brand$")
    public void iNavigatesDLPPageThroughBrand() throws Throwable {
        String dlpURL = macys() ? "/shop/featured/august-hats" : "/buy/converse%20kids%20sneakers";
        Navigate.visit(RunConfig.url + dlpURL);
        logger.info("Navigated to DLP page sucessfully");
    }

    @And("^I should see copy blocks displayed higher up in the page view source in desktop$")
    public void iShouldSeeCopyBlocksDisplayedHigherUpInThePageViewSourceInDesktop() throws Throwable {
        Wait.forPageReady();
        URL macysURL = new URL(WebDriverManager.getCurrentUrl());
        BufferedReader pageViewSource = new BufferedReader(
                new InputStreamReader(
                        macysURL.openStream()));
        String line;
        int lineNumber = 0;
        int lineCopyBlockNumber = 0;
        while ((line = pageViewSource.readLine()) != null) {
            lineNumber++;
            if (line.contains("class=\"catalogCopyBlock copy-block \"")) {
                lineCopyBlockNumber = lineNumber;
            }
        }
        Assert.assertTrue("ERROR - APP: Copy Block data is not displaying on View Source Page in given range", (lineCopyBlockNumber >= 300 && lineCopyBlockNumber <= 1200));
    }
}
