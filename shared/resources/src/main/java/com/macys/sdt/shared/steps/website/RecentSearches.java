package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.macys.sdt.shared.steps.website.ShopAndBrowse.I_search_for;

public class RecentSearches {
    private static final Logger logger = LoggerFactory.getLogger(RecentSearches.class);
    public static String randomLastSearch;

    @Then("^I should see search symbol infront of recently searched keyword$")
    public void i_should_see_search_symbol_infront_of_recently_searched_keyword() throws Throwable {
        Clicks.click("header.global_search_field");
        Assert.assertTrue("ERROR - APP:- Recent search panel was not displayed", Wait.untilElementPresent("header.recent_suggestions_list"));
        List<WebElement> actual_recent_search = Elements.findElements("header.last_searches");
        List<String> actual_recent_searches = actual_recent_search.stream().map(ele -> ele.getText()).collect(Collectors.toList());
        for (int i = 0; i <= actual_recent_searches.size() - 1; i++) {
            List<WebElement> recent_search_symbol = Elements.findElements("header.recent_searches_symbol");
            Assert.assertTrue("ERROR - APP:- Recent search symbol is not displaying before recent search term", recent_search_symbol.get(i).isDisplayed());
        }
        logger.info("Recent search symbol is displaying before recent search term");

    }

    @Then("^I (should|should not) see \"([^\"]*)\" text in recent search panel$")
    public void i_should_see_text_in_recent_search_panel(String condition, String recent_search_title) throws Throwable {
        Wait.forPageReady();
        if (condition.equalsIgnoreCase("should")) {
            Clicks.click("header.global_search_field");
            Assert.assertTrue("ERROR - APP:- Recent search panel was not displayed", Wait.untilElementPresent("header.recent_suggestions_list"));
            String actual_recent_search_title = Elements.findElement("header.recent_search_title").getText();
            Assert.assertTrue("ERROR - APP:- Recent search panel text was not matching with " + recent_search_title, actual_recent_search_title.equals(recent_search_title));
        } else {
            Wait.secondsUntilElementNotPresent("header.recent_search_title", 1000);
            Assert.assertFalse("ERROR - APP:- Recent search panel is displaying", Elements.elementPresent("header.recent_search_title"));
        }
        logger.info("Verified Recent search panel" + condition + "dislay text");
    }

    public static JSONArray getItemFromLocalStorage(String key) {
        String localValue = (String) Navigate.execJavascript(String.format("return window.localStorage.getItem('%s');", key));
        return new JSONArray(localValue);
    }

    @And("^I search with different keywords:$")
    public void iSearchWithDifferentKeywords(List<String> searchKeywords) throws Throwable {
        searchKeywords.forEach(searchKeyword -> {
            try {
                I_search_for(searchKeyword);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        logger.info("Searched with different keywords");
    }

    @Then("^I should see recent search panel with max (\\d+) last searches keywords$")
    public void i_should_see_recent_search_panel_with_max_last_searches_keywords(int recent_searches_size) throws Throwable {
        JSONArray expected_recent_searches = getItemFromLocalStorage("LastSearches");
        List<WebElement> actual_recent_search_elements = Elements.findElements("header.last_searches");
        List<String> actual_recent_searches = actual_recent_search_elements.stream().map(ele -> ele.getText()).collect(Collectors.toList());
        Assert.assertTrue("ERROR - APP:- Recent searches count are not matching", actual_recent_searches.size() <= recent_searches_size);
        Clicks.click("header.global_search_field");
        int keywordsCount = Elements.findElements("header.last_searches").size();
        Assert.assertTrue("ERROR - APP: - No recent searches are displaying in UI", keywordsCount != 0);
//        As we have www & www1 issue checking for max length of expected_recent_searches instead of actual_recent_searches.
        for (int i = 0; i <= keywordsCount - 1; i++) {
            String expected_recent_searches_value = (String) expected_recent_searches.get(i);
            Assert.assertTrue("ERROR - APP:- Recent searches are not matching", expected_recent_searches_value.equalsIgnoreCase(actual_recent_searches.get(i)));
        }
    }

    @When("^I click on any random recent search keyword$")
    public void i_click_on_any_random_recent_search_keyword() throws Throwable {
        Clicks.click("header.global_search_field");
        Assert.assertTrue("ERROR - APP:- Recent search panel was not displayed", Wait.untilElementPresent("header.recent_suggestions_list"));
        List<WebElement> last_searches = Elements.findElements("header.last_searches");
        int index = last_searches.size() == 1 ? 0 : new Random().nextInt(last_searches.size() - 1);
        randomLastSearch = last_searches.get(index).getText();
        Clicks.click(last_searches.get(index));
        logger.info("Selected Random recent search successfully");
    }

    @And("^I should see the selected autosuggestion keyword persist in search box on search results page$")
    public void iShouldSeeTheSelectedAutosuggestionKeywordPersistInSearchBoxOnSearchResultsPage() throws Throwable {
        String persistText = Elements.findElement("header.global_search_field").getAttribute("value");
        Assert.assertTrue("ERROR - APP:- Search persist keyword is not present or not matching in current search field", randomLastSearch.equalsIgnoreCase(persistText));
        logger.info("Search persist keyword is same");
    }

    @Then("^I should see the keyword \"([^\"]*)\" comes to top in recent searches$")
    public void iShouldSeeTheKeywordComesToTopInRecentSearches(String lastSearchKeyword) throws Throwable {
        JSONArray recent_keyword_searches = getItemFromLocalStorage("LastSearches");
        Assert.assertTrue("ERROR - APP:-" + lastSearchKeyword + "was not present in top of the recent search panel", recent_keyword_searches.get(0).equals(lastSearchKeyword));
    }
}
