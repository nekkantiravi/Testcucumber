package com.macys.sdt.projects.Discovery.SearchResults.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class RecentSearches {

    @Then("^I should see \"([^\"]*)\" text in recent search panel$")
    public void i_should_see_text_in_recent_search_panel(String recent_search_title) throws Throwable {
        Clicks.click("search.search_field");
        Assert.assertTrue(Wait.untilElementPresent("search.autocomplete_flyout"));
        String actual_recent_search_title = Elements.findElement("search.recent_search_title").getText();
        Assert.assertTrue(actual_recent_search_title.equals(recent_search_title));
    }

    @When("^I type one character in search box$")
    public void i_type_one_character_in_search_box() throws Throwable {
        Random rnd = new Random();
        String keyword = randomAlphanumeric(10);
        String chr = String.valueOf(keyword.charAt(rnd.nextInt(keyword.length())));
        TextBoxes.typeTextbox("search.search_field", chr);
    }

    @When("^I type two characters \"([^\"]*)\" in search box$")
    public void i_type_two_characters_in_search_box(String input_string) throws Throwable {
        TextBoxes.typeTextbox("search.search_field", input_string);
    }

    @Then("^I should not see recent searches panel$")
    public void i_should_not_see_recent_searches_panel() throws Throwable {
        Clicks.click("search.search_field");
        Assert.assertFalse(Elements.elementPresent("search.last_searches"));
    }

    @Then("^I should see search keyword autocomplete suggestions$")
    public void i_should_see_search_keyword_autocomplete_suggestions() throws Throwable {
        Assert.assertTrue(Wait.untilElementPresent("search.autocomplete_suggestions"));
    }

    @Then("^I should not see duplicate keywords in recent searches panel$")
    public void i_should_not_see_duplicate_keywords_in_recent_searches_panel() throws Throwable {
        Clicks.click("search.search_field");
        Assert.assertTrue(Wait.untilElementPresent("search.autocomplete_flyout"));
        List<WebElement> actual_recent_search = Elements.findElements("search.last_searches");
        List<String> actual_recent_searches = actual_recent_search.stream().map(ele -> ele.getText()).collect(Collectors.toList());
        for (int i = 0; i < actual_recent_searches.size(); i++) {
            for (int j = i+1; j <actual_recent_searches.size();j++){
                Assert.assertNotEquals("message",actual_recent_searches.get(i),actual_recent_searches.get(i + 1));
            }
        }
    }

//    @And("^I navigate to order history page$")
//    public void i_navigate_to_order_history_page() throws Throwable {
//        if(macys()) {
//            Clicks.hoverForSelection("search.my_account");
//            Wait.untilElementPresent("search.myaccount_dropdown");
//            Elements.findElement("search.order_history").click();
//        }
//        else{
//            Clicks.click("search.order_history");
//        }
//    }
    
}
