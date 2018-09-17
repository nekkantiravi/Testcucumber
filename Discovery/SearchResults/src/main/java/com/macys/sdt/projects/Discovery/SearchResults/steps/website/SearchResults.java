package com.macys.sdt.projects.Discovery.SearchResults.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.SearchResults.utils.SearchUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import javax.json.*;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
//import java.util.Set;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONArray;
//import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;

//import com.sun.tools.javac.util.Assert;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import javax.json.JsonBuilderFactory;
//import javax.json.JsonBuilderFactory.*;
//import com.sun.tools.javac.util.Assert;


public class SearchResults extends StepUtils {

    String searchType = null;
    List<JsonObject> listOfMaps = new ArrayList<JsonObject>();
    static List<String> actual_recent_searches ;

    @And("^sample test: I click the logo$")
    public void sample_test_I_click_the_logo() throws Throwable {
        Clicks.click("home.logo");
    }

    @And("^sample test: I navigate to sample page$")
    public void sample_test_I_navigate_to_sample_page() throws Throwable {
        Navigate.visit("search");
        Clicks.click("search.test_element");
    }


    @And("^I enter search keyword$")
    public void iEnterSearchKeyword(List<Map<String, String>> searchKeyword) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        String keyword = null;
        actual_recent_searches = new ArrayList<>();
        for (Map<String, String> set : searchKeyword) {
            searchType = set.get("Search_Type");
            keyword = set.get("Keyword");
            actual_recent_searches.add(keyword);
            TextBoxes.typeTextbox("search.search_field", keyword);
            Clicks.click("search.search_submit");
            Assert.assertTrue(Wait.untilElementPresent("search.search_result"));
            String productCount;
            productCount = Elements.findElement("search.product_count").getText();
            System.out.println(productCount);
            //Assert.assertTrue(Wait.untilElementNotPresent("search.pagination"));
            Boolean paginationDisplay = Elements.findElement("search.pagination").isDisplayed();
            //Need to print number of page "43"
            //List<WebElement> paginationCount = Elements.findElements("search.pagination_count");
            List<WebElement> facetNames = Elements.findElements("search.facet_name");
//            for (WebElement e : facetNames) {
//                System.out.println(e.getText());
//            }
            JsonObject data = Json.createObjectBuilder()
                    .add(searchType, Json.createObjectBuilder()
                        .add("name", keyword)
                        .add("facet", facetNames.size())
                        .add("items", productCount)
                        .build())
                    .build();

            int i = listOfMaps.size();
            if (i >= 0) {
                listOfMaps.add(i, data);
            }
        }
    }

    @And("^Geberate Report$")
    public void geberateReport() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //JsonObject personObject = null;
        JsonObject personObject = Json.createObjectBuilder()
                .add("data", listOfMaps.toString())
                .build();
        System.out.println(personObject);
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        //writer.writeObject(personObject);
        writer.writeObject(personObject);
        writer.close();
        System.out.print(stringWriter.getBuffer().toString());

        FileWriter file = new FileWriter("./Discovery/data.json");
        file.write(stringWriter.getBuffer().toString());
        file.flush();
        file.close();

    }
    @Then("^I call KWS service using keyword$")
    public void iCallKWSServiceUsingKeyword(List<Map<String, String>> searchKeyword) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //redirectType = redirectType.equals("URL") ? "ABSOLUTE_URL" : redirectType;
        // mode = mode.equalsIgnoreCase("site") ? "SITE" : "WEDDING_REGISTRY";
        String mode = "SITE";
        //String regionCode = mode.equalsIgnoreCase("iship") ? "CA" : "US";
        String regionCode = "US";
        String keyword = null;
        Map response = null;
        Map searchResponse = null;
        ArrayList productlist = null;
        ArrayList products = null;
        Map result = null;
        Integer counter;
        Integer greaterThanCounter;
        Integer lessThanCounter;

        //actual_recent_searches = new ArrayList<>();
        for (Map<String, String> set : searchKeyword) {
            keyword = set.get("keyword");
            response = SearchUtils.getSearchService(keyword, mode, regionCode, false, null);
            searchResponse = (Map) response.get("SearchResult");
            productlist = (ArrayList) searchResponse.get("searchResultGroups");
            result = (Map) productlist.get(0);
            products = (ArrayList) result.get("productIds");
            //System.out.print(products);
            //System.out.print("xxxxx" + products.getClass());
            //System.out.print("yyyy" + products.get(0).getClass());

            //Double d = Double.valueOf((Double) products.get(0));
//            Integer in = Integer.valueOf(Double.valueOf((Double) products.get(0)).intValue());
//            System.out.print("iiiiiii" + in);
           // System.out.print("zzzz" + ((Double) products.get(0)).intValue());


            Random generator = new Random();
            int randomIndex = generator.nextInt(products.size()-20);

            //System.out.print("Indexxxxxx" + randomIndex);
            counter = 0;
            greaterThanCounter = 0;
            lessThanCounter = 0;
            for (int i = randomIndex; i< randomIndex + 20 ; i++) {

                if ((Integer.valueOf(((Double) products.get(i + 1)).intValue()).equals(Integer.valueOf(((Double) products.get(i)).intValue()) + 1))) {
                    counter = counter + 1;
                }
                //Need to implement continuous greater than or continuous less than
                else if((Integer.valueOf(((Double) products.get(i + 1)).intValue()) > (Integer.valueOf(((Double) products.get(i)).intValue())))){
                    greaterThanCounter = greaterThanCounter + 1;
                }
                else if((Integer.valueOf(((Double) products.get(i + 1)).intValue()) < (Integer.valueOf(((Double) products.get(i)).intValue())))){
                    lessThanCounter = lessThanCounter+1;
                }
            }
            Assert.assertFalse("RDPP Sort By is not happening in KWS Service from Discovery side as all products are Incremental", counter>18);
            Assert.assertFalse("RDPP Sort By is not happening in KWS Service from Discovery side, as all products are Ascending by Default", greaterThanCounter > 18);
            Assert.assertFalse("RDPP Sort By is not happening in KWS Service from Discovery side,as all products are Ascending by Default", lessThanCounter > 18);


        }
        throw new PendingException();

    }



    @Then("^I should see minimum (\\d+) and maximum (\\d+)$")
    public void iShouldSeeMinimumAndMaximum(int min_prod_count, int max_prod_count) throws Throwable {
        // Write code here that turns the phrase above into concrete actions


        throw new PendingException();
    }

}