package com.macys.sdt.projects.Marketing.HomePage.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.macys.sdt.framework.utils.StepUtils.macys;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class HomepageMCOMSteps {

    private static ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
    private ArrayList<String> auto_suggestion_1 = new ArrayList<>();
    private ArrayList<String> auto_suggestion_2 = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(HomepageMCOMSteps.class);

    @Then("^I verify logo is displayed and returns a (\\d+) OK$")
    public void iVerifyLogoIsDisplayedAndReturnsAOK(int httpCode) throws Throwable {

        String link = null;
        String FOBName = null;

        link = Elements.findElement("home.verify_page").getAttribute("href");
        if (!(link == null)) {
            if (!link.contains("http")) {
                link = RunConfig.url + link;
            }

            Map<String, String> headers = new HashMap<>();

            Response response = RESTOperations.doGET(link, headers);
            int statusCode = response.getStatus();
            Assert.assertTrue(FOBName + " FOB did not return 200 on GET call",
                    statusCode == httpCode);
        }
    }

    @Then("^I hover over on the MINI BAG$")
    public void iHoverOverOnTheMINIBAG() throws Throwable {

        Wait.forPageReady("my_account");
        Clicks.hoverForSelection("home.my_bag");
        Utils.threadSleep(3000, null);
    }

    @And("^I verify an overlay is displayed with \"([^\"]*)\" message$")
    public void iVerifyAnOverlayIsDisplayedWithMessage(String text) throws Throwable {

       // Wait.untilElementPresent("home.mini_bag_text");
        if (macys())
        Assert.assertTrue("My Bag overlay text could not be verified",
                Elements.findElement("home.my_bag").getText().equals("0 items in your bag. Shop great deals now!"));
        else {
            Assert.assertTrue("My Bag overlay text could not be verified",
                    Elements.findElement("home.mini_bag_text").getText().equals("Your brown bag is empty."));
        }
    }

    @And("^I verify Search Box is displayed with search icon$")
    public void iVerifySearchBoxIsDisplayedWithSearchIcon() throws Throwable {

        Wait.forPageReady("home");
        Assert.assertTrue("Global search field could not be found",
                Elements.elementPresent("home.search_field"));

        if (Elements.elementPresent("home.iship_flag_img")) {
            Assert.assertTrue("Global search icon could not be found",
                    Elements.elementPresent("home.iship_search_submit"));
        } else {
            Assert.assertTrue("Global search icon could not be found",
                    Elements.elementPresent("home.search_submit"));
        }
    }

    @When("^I search \"([^\"]*)\" for auto-completion$")
    public void iSearchForAutoCompletion(String text) throws Throwable {

        shopAndBrowse.I_type_in_search_box(text);
    }

    @Then("^I see auto-suggestions for \"([^\"]*)\"$")
    public void iSeeAutoSuggestionsFor(String text) throws Throwable {

        Utils.threadSleep(2000, null);
        List<WebElement> elems = Elements.findElements("home.auto_search_elements");
        if (elems.size() == 0) {
            Assert.fail("No auto search results found");
        }
    }

    @When("^I click the search icon$")
    public void iClickTheSearchIcon() throws Throwable {

        Clicks.click("home.search_submit");
    }

    @Then("^I verify I land on Search Results Page for \"([^\"]*)\"$")
    public void iVerifyILandOnSearchResultsPageFor(String searchText) throws Throwable {

        Wait.forPageReady("search_result");
        if (macys()) {
            Assert.assertTrue("Static search message not found",
                    Elements.findElement("pagination.search_message").getText()
                            .matches("We found " + "\\d+ " + "results for " + searchText));
            String result_count = Elements.findElement("pagination.search_message").getText()
                    .substring(9, 11);
            if (Integer.parseInt(result_count) == 0) {
                Assert.fail("Search contains zero results");
            }
        } else {
            Assert.assertTrue("Static search message not found",
                    Elements.findElement("pagination.search_message").getText()
                            .matches("Search results for\\n" + searchText));
          //  String result_count = Elements.findElement("pagination.selected_tab").getText();
        }
    }

    @When("^I type first two characters \"([^\"]*)\" in search box$")
    public void iTypeFirstTwoCharactersInSearchBox(String text) throws Throwable {

        shopAndBrowse.I_type_in_search_box(text);
    }

  /*  @Then("^I should see autocomplete suggestions$")
    public void iShouldSeeAutocompleteSuggestions() throws Throwable {

        List<WebElement> elems = Elements.findElements("home.auto_search_elements");
        if (elems.size() == 0) {
            Assert.fail("No auto search results found");
        }
        if (auto_suggestion_1.isEmpty()) {
            auto_suggestion_1 = extractAutoSuggestionList(elems);
        } else {
            auto_suggestion_2 = extractAutoSuggestionList(elems);
        }
    } */

    @And("^I should see \"([^\"]*)\" words or phrases Pertinent to the characters typed$")
    public void iShouldSeeWordsOrPhrasesPertinentToTheCharactersTyped(String arg0) throws Throwable {

        List<WebElement> elems = Elements.findElements("home.auto_search_elements");
        if (elems.size() != 10) {
            Assert.fail("Auto search result not equal to 10");
        }
    }

    @When("^I clear the search text area$")
    public void iClearTheSearchTextArea() throws Throwable {

        Elements.findElement("home.search_field").clear();
    }

    @And("^The autocomplete suggestions should display the letters that do not match the user typed term in bold$")
    public void theAutocompleteSuggestionsShouldDisplayTheLettersThatDoNotMatchTheUserTypedTermInBold() throws Throwable {

        List<WebElement> elems = Elements.findElements("home.auto_suggest_bold_text");

        for (WebElement elem : elems) {
            if (!elem.getText().isEmpty()) {
                Assert.assertTrue("Text not containing search keyword is not in bold",
                        !elem.getText().contains(shopAndBrowse.searchKeyword));
            }
        }
    }

    public static void verifyBold(String text, String keyword) {

        String[] arr = text.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].contains(keyword)) {
                arr[i] = arr[i].replace(keyword, "");
            }
        }
    }

    @And("^I should see the characters of suggession not more than \"([^\"]*)\" characters$")
    public void iShouldSeeTheCharactersOfSuggessionNotMoreThanCharacters(String num) throws Throwable {

        List<WebElement> elems = Elements.findElements("home.auto_search_elements");
        if (elems.size() == 0) {
            Assert.fail("No auto search results found");
        }

        for (WebElement elem : elems) {
            Assert.assertTrue("Characters suggestion is more than 72",
                    elem.getText().length() <= 72);
        }
    }

    @And("^the first letter of each word in autocomplete suggestions should be capitalized$")
    public void theFirstLetterOfEachWordInAutocompleteSuggestionsShouldBeCapitalized() throws Throwable {

        List<WebElement> elems = Elements.findElements("home.auto_search_elements");
        if (elems.size() == 0) {
            Assert.fail("No auto search results found");
        }

        for (WebElement elem : elems) {
            Assert.assertTrue("First letter in one of the word in auto suggestion <<" + elem.getText()
                    + ">> is not Capital", verifyFirstLetterCapital(elem.getText()));
        }
    }

    public static boolean verifyFirstLetterCapital(String text) {

        boolean isFirstLetterCapital = false;
        String[] arr = text.split(" ");

        for (int i = 0; i < arr.length; i++) {
            if (Character.isLowerCase(arr[i].charAt(0))) {
                return isFirstLetterCapital;
            }
        }
        return isFirstLetterCapital = true;
    }

    public ArrayList<String> extractAutoSuggestionList(List<WebElement> elems) {

        ArrayList<String> suggestionList = new ArrayList<String>();

        for (WebElement elem : elems) {
            suggestionList.add(elem.getText());
        }
        return suggestionList;
    }

    @And("^I should see both autocomplete suggestions as same$")
    public void iShouldSeeBothAutocompleteSuggestionsAsSame() throws Throwable {

        for (int i = 0; i < auto_suggestion_1.size(); i++) {

            Assert.assertTrue("Auto suggest does not match",
                    auto_suggestion_1.get(i).equals(auto_suggestion_2.get(i)));
        }
    }

    @And("^the autocomplete suggestions should display the part of query in bold$")
    public void theAutocompleteSuggestionsShouldDisplayThePartOfQueryInBold() throws Throwable {
        theAutocompleteSuggestionsShouldDisplayTheLettersThatDoNotMatchTheUserTypedTermInBold();
    }

    @And("^I should not see suggestions displayed jibberish$")
    public void iShouldNotSeeSuggestionsDisplayedJibberish() throws Throwable {

        Pattern pattern = Pattern.compile("[^A-Za-z&\\s]");
        List<WebElement> elems = Elements.findElements("home.auto_search_elements");

        for (WebElement elem : elems) {
            Assert.assertTrue("Auto suggestion contains gibberish characters",
                    !pattern.matcher(elem.getText()).find());
        }
    }

    @Then("^I verify TOPNAV elements are displayed and return a (\\d+) OK$")
    public void iVerifyTOPNAVElementsAreDisplayedAndReturnAOK(int httpCode, DataTable table) throws Throwable {

        String url = null;
        String TopNavName = null;
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {

            switch (row.get("TOPNAV")) {
                case "sign in":
                    url = Elements.findElement("home.goto_sign_in_link").getAttribute("href");
                    TopNavName = Elements.findElement("home.goto_sign_in_link").getText();
                    break;
                case "my account":
                    url = Elements.findElement("home.goto_guest_my_account").getAttribute("href");
                    TopNavName = Elements.findElement("home.goto_guest_my_account").getText();
                    break;
                case "stores":
                    url = Elements.findElement("home.stores_header").getAttribute("href");
                    TopNavName = Elements.findElement("home.stores_header").getText();
                    break;
                case "deals":
                    url = Elements.findElement("home.deals_header").getAttribute("href");
                    TopNavName = Elements.findElement("home.deals_header").getText();
                    break;
                case "lists":
                    url = Elements.findElement("home.lists_header").getAttribute("href");
                    TopNavName = Elements.findElement("home.lists_header").getText();
                    break;
                case "gifts":
                    Assert.assertTrue("Gifts top nav could not be found",
                            Elements.elementPresent("home.gifts_header"));
                    break;
                case "wedding registry":
                    url = Elements.findElement("home.goto_wedding_registry").getAttribute("href");
                    TopNavName = Elements.findElement("home.goto_wedding_registry").getText();
                    break;
                case "shipping to":
                    url = Elements.findElement("home.shipping_to_header").getAttribute("href");
                    TopNavName = Elements.findElement("home.shipping_to_header").getText();
                    break;
                case "IN flag":
                    url = Elements.findElement("home.shipping_to_header").getAttribute("href");
                    Assert.assertTrue("Flag image could not be found",
                            Elements.elementPresent("home.flag_image"));
            }
            Map<String, String> headers = new HashMap<>();

            if (!TopNavName.equals("GIFTS")) {
                if (!url.contains("http")) {
                    url = RunConfig.url + url;
                }
                Response response = RESTOperations.doGET(url, headers);
                int statusCode = response.getStatus();
                Assert.assertTrue(TopNavName + " Top NAV did not return 200 or 302 on GET call",
                        statusCode == httpCode || statusCode == 302);
            }
        }
    }

    private void verifyFobCategories(String catSelector, String headerSelector, String fob) {

        List<WebElement> categoryHeaderElems = Elements.findElements(headerSelector);
        ArrayList<String> headerElemList = new ArrayList<String>();

        for (WebElement elem : categoryHeaderElems) {
            headerElemList.add(elem.getText());
        }
        List<WebElement> categoryElems = Elements.findElements(catSelector);
        ArrayList<String> categoryElemList = new ArrayList<String>();

        for (WebElement elem : categoryElems) {
            categoryElemList.add(elem.getText());
        }
        ArrayList<String> headerCategoryList = getHeaderCategoryListFromJson(fob);
        ArrayList<String> categoryList = getCategoryListFromJson(fob);
        boolean categoryNotFound = true;
        Assert.assertTrue("No elements found for " + fob + " FOB", categoryHeaderElems.size() != 0);
        logger.info(fob + " FOB Header categories count not correct, Expected: "
                        + headerCategoryList.size() + ", Actual: " + categoryHeaderElems.size(),
                categoryHeaderElems.size() == headerCategoryList.size());
       /* for (int i = 0; i < headerElemList.size(); i++) {
            for (int j = 0; i < headerCategoryList.size(); i++) {
                if (headerElemList.get(i).equals(headerCategoryList.get(j))) {
                    categoryNotFound = false;
                    break;
                }
            }
            if (categoryNotFound) {
                Assert.fail(headerElemList.get(i) + "Header category could not be found");
            }
        } */
    }

    private void printFobCategories(String catSelector, String headerSelector) {

        List<WebElement> categoryHeaderElems = Elements.findElements(headerSelector);
        ArrayList<String> headerElemList = new ArrayList<String>();

        for (WebElement elem : categoryHeaderElems) {
            headerElemList.add(elem.getText());
        }
        List<WebElement> categoryElems = Elements.findElements(catSelector);
        ArrayList<String> categoryElemList = new ArrayList<String>();

        for (WebElement elem : categoryElems) {
            categoryElemList.add(elem.getText());
        }
        //  ArrayList<String> headerCategoryList = getHeaderCategoryListFromJson("women");
        // ArrayList<String> categoryList = getCategoryListFromJson("women");

        System.out.println("<<<<<<Headers>>>>>>");
        for (int i = 0; i < headerElemList.size(); i++) {
            System.out.println(headerElemList.get(i));
        }

        System.out.println("<<<<<<Categories>>>>>>");
        for (int i = 0; i < categoryElemList.size(); i++) {
            System.out.println(categoryElemList.get(i));

        }
    }

    private ArrayList<String> getHeaderCategoryListFromJson(String fob) {

        JSONObject jsonObject = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            File file = getResourceFile("categories.json");
            String jsonText = Utils.readTextFile(file);
            jsonObject = new JSONObject(jsonText);
            JSONArray array = jsonObject.getJSONObject(getMacysContext())
                    .getJSONObject(fob).getJSONArray("header_categories");
            for (int i = 0; i < array.length(); i++) {
                String elem = array.getString(i);
                list.add(elem);
            }

        } catch (JSONException | IOException ex) {
            Assert.fail("Failed to extract JSON objects from categories.json");
        }
        return list;
    }

    private ArrayList<String> getCategoryListFromJson(String fob) {

        JSONObject jsonObject = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            File file = getResourceFile("categories.json");
            String jsonText = Utils.readTextFile(file);
            jsonObject = new JSONObject(jsonText);
            JSONArray array = jsonObject.getJSONObject(getMacysContext())
                    .getJSONObject(fob).getJSONArray("categories");
            for (int i = 0; i < array.length(); i++) {
                String elem = array.getString(i);
                list.add(elem);
            }

        } catch (JSONException | IOException ex) {
            Assert.fail("Failed to extract JSON objects from categories.json");
        }
        return list;
    }

    private String getMacysContext() {

        String context = null;
        if (Elements.elementPresent("home.shipping_to_header")) {
            return context = "iship";
        } else if (MainRunner.currentURL.contains("registry")) {
            return context = "registry";
        } else {
            return context = "domestic";
        }
    }

    @And("^I mouse over on the below fob's and validate flyout menu$")
    public void iMouseOverOnTheBelowFobSAndValidateFlyoutMenu(DataTable table) throws Throwable {

        Wait.forPageReady();
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {

            switch (row.get("fob")) {
                case "HOME":
                    Clicks.hoverForSelection("home.home_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.home_categories", "home.home_headers", "home");
                    break;
                case "BED & BATH":
                    Clicks.hoverForSelection("home.bedbath_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.bedbath_categories", "home.bedbath_headers", "bed & bath");
                    // printFobCategories("home.bedbath_categories", "home.bedbath_headers");
                    break;
                case "WOMEN":
                    Clicks.hoverForSelection("home.women_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.women_categories", "home.women_headers", "women");
                    break;
                case "MEN":
                    Clicks.hoverForSelection("home.men_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.men_categories", "home.men_headers", "men");
                    break;
                case "JUNIORS":
                    Clicks.hoverForSelection("home.juniors_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.juniors_categories", "home.juniors_headers", "juniors");
                    break;
                case "KIDS":
                    Clicks.hoverForSelection("home.kids_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.kids_categories", "home.kids_headers", "kids");
                    // printFobCategories("home.kids_categories", "home.kids_headers");
                    break;
                case "BEAUTY":
                    Clicks.hoverForSelection("home.beauty_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.beauty_categories", "home.beauty_headers", "beauty");
                    break;
                case "SHOES":
                    Clicks.hoverForSelection("home.shoes_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.shoes_categories", "home.shoes_headers", "shoes");
                    break;
                case "HANDBAGS":
                    Clicks.hoverForSelection("home.handbags_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.handbags_categories", "home.handbags_headers", "handbags");
                    break;
                case "HANDBAGS & ACCESSORIES":
                    Clicks.hoverForSelection("home.handbagsaccessories_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.handbagsaccessories_categories", "home.handbagsaccessories_headers", "handbags & accessories");
                    // printFobCategories("home.handbagsaccessories_categories", "home.handbagsaccessories_headers");
                    break;
                case "JEWELRY":
                    Clicks.hoverForSelection("home.jewelry_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.jewelry_categories", "home.jewelry_headers", "jewelry");
                    break;
                case "WATCHES":
                    Clicks.hoverForSelection("home.watches_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.watches_categories", "home.watches_headers", "watches");
                    break;
                case "BRANDS":
                    Clicks.hoverForSelection("home.brands_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.brands_categories", "home.brands_headers", "brands");
                    // printFobCategories("home.brands_categories", "home.brands_headers");
                    break;
                case "WEDDING REGISTRY":
                    Clicks.hoverForSelection("home.weddingregistry_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.weddingregistry_categories", "home.weddingregistry_headers", "wedding registry");
                    // printFobCategories("home.weddingregistry_categories", "home.weddingregistry_headers");
                    break;
                case "DINING":
                    Clicks.hoverForSelection("home.dining_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.dining_categories", "home.dining_headers", "dining");
                    // printFobCategories("home.dining_categories", "home.dining_headers");
                    break;
                case "HOME DECOR":
                    Clicks.hoverForSelection("home.homedecor_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.homedecor_categories", "home.homedecor_headers", "home decor");
                    //  printFobCategories("home.homedecor_categories", "home.homedecor_headers");
                    break;
                case "LUGGAGE":
                    Clicks.hoverForSelection("home.luggage_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.luggage_categories", "home.luggage_headers", "luggage");
                    // printFobCategories("home.luggage_categories", "home.luggage_headers");
                    break;
                case "CLEANING & ORGANIZING":
                    Clicks.hoverForSelection("home.cleaning_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.cleaning_categories", "home.cleaning_headers", "cleaning & organizing");
                    //  printFobCategories("home.cleaning_categories", "home.cleaning_headers");
                    break;
                case "WEDDING DAY":
                    Clicks.hoverForSelection("home.weddingday_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.weddingday_categories", "home.weddingday_headers", "wedding day");
                    // printFobCategories("home.weddingday_categories", "home.weddingday_headers");
                    break;
                case "KITCHEN":
                    Clicks.hoverForSelection("home.kitchen_fob");
                    Utils.threadSleep(3000, null);
                    verifyFobCategories("home.kitchen_categories", "home.kitchen_headers", "kitchen");
                    //  printFobCategories("home.kitchen_categories", "home.kitchen_headers");
                    break;
            }

        }
    }

    @Then("^I should see and validate flyout menu$")
    public void iShouldSeeAndValidateFlyoutMenu() throws Throwable {

        Wait.forPageReady();
        Clicks.hoverForSelection("home.kids_fob");
        Utils.threadSleep(3000, null);
        verifyFobCategories("home.kids_categories", "home.kids_headers", "kids");
    }
}
