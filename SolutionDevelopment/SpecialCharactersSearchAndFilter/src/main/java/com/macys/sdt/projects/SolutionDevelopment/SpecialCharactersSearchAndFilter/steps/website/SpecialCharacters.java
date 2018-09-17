package com.macys.sdt.projects.SolutionDevelopment.SpecialCharactersSearchAndFilter.steps.website;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.projects.SolutionDevelopment.SpecialCharactersSearchAndFilter.actions.DesignerIndex;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by u063689 on 10/23/2017.
 */
public class SpecialCharacters {

    private DesignerIndex designerIndex = new DesignerIndex();
    private static final Logger logger = LoggerFactory.getLogger(SpecialCharacters.class);

    @And("^I navigate to a designer index page$")
    public void iNavigateToADesignerIndexPage() {
        Navigate.visit("designer_static");
    }


    @When("^I select a brand with the special character and verify landing on designer landing page$")
    public void iClickOnBrandNameWithTheFollowingSpecialCharacter() {
        List<String> characters = getCharacters();
        List<String> brokenBrands = new ArrayList<>();
        HashMap<String, String> map = designerIndex.selectDesignerBySpecialCharacterOneValue(characters);
        for (String character : map.keySet()
                ) {
            logger.info("Currently iterating over character " + character);
            logger.info("The Brand name found corresponding to the character is " + map.get(character) +
                    " that has a length of " + map.get(character).length());
            if (map.get(character).length() == 0) {
                logger.info("There is no brand found with the character " + character + " in it. Moving on to next one.");
                map.keySet().iterator().next();
            } else {
                Clicks.click(Elements.findElement(By.linkText(map.get(character))));
                logger.info("Clicked on link: " + map.get(character));
                Wait.forPageReady();
                String brandNameOnBrowse = designerIndex.getRandomBrandName("designer_landing_page");
                if (!brandNameOnBrowse.contentEquals("none")) {
                    logger.info("Current URL is: " + MainRunner.currentURL);
                } else {
                    logger.info("We are on the empty search result page!!!!!!");
                    brokenBrands.add(map.get(character));
                }

                Navigate.visit("designer_static");
            }
            Wait.forPageReady();
        }
        if (brokenBrands.size() != 0) {
            Assert.fail("One or more brands resulted in zero search result page " + brokenBrands);
        } else {
            logger.info("All brands with special characters pass");
        }
    }

    @When("^I search for brand name with the following special character and verify landing on search page$")
    public void iSearchForBrandNameWithTheFollowingSpecialCharacter() {
        List<String> characters = getCharacters();
        List<String> brokenBrands = new ArrayList<>();
        Navigate.visit("designer_static");
        HashMap<String, String> map = designerIndex.selectDesignerBySpecialCharacterOneValue(characters);
        for (String character : map.keySet()
                ) {
            logger.info("Currently iterating over character " + character);
            logger.info("The Brand name found corresponding to the character is " + map.get(character) +
                    " that has a length of " + map.get(character).length());
            if (map.get(character).length() == 0) {
                logger.info("There is no brand found with the character " + character + " in it. Moving on to next one.");
                map.keySet().iterator().next();
            } else {
                designerIndex.searchFor(map.get(character));

                logger.info("Searched for brand: " + map.get(character));
                Wait.forPageReady();
                String brandNameOnBrowse = designerIndex.getRandomBrandName("search_result");
                if (!brandNameOnBrowse.contentEquals("none")) {
                    logger.info("Current URL is: " + MainRunner.currentURL);
                } else {
                    logger.info("We are on the empty search result page!!!!!!");
                    brokenBrands.add(map.get(character));
                }
                Navigate.browserBack();

            }
            if (isForseePopup()) {
                colseForseePopup();
            }
            if (brokenBrands.size() != 0) {
                Assert.fail("One or more brands resulted in zero search result page " + brokenBrands);
            } else {
                logger.info("All brands with special characters pass");
            }
        }
    }

    @And("^I search for the following attribute with special character:$")
    public void iSearchForTheFollowingAttributeWithSpecialCharacter(List<String> attribute) {
        List<String> emptyPage = new ArrayList<>();
        for (String attr : attribute) {
            designerIndex.searchFor(attr);
            logger.info("Searching for attribute " + attr);
            if (Elements.elementPresent("search_result.zero_results_header")) {
                emptyPage.add(attr);
                logger.info("Landed on the empty search result page");
                attribute.iterator().next();
            } else {
                logger.info("Test passes successfully");
            }
        }
        if (emptyPage.size() != 0) {
            Assert.fail("One or more brands resulted in zero search result page " + emptyPage);
        } else {
            logger.info("All brands with special characters pass");
        }
    }

    @Then("^I verify that products displayed have 100% in the product name$")
    public void iVerifyThatProductsDisplayedHaveInTheProductName() {
        logger.info("The random product selected from the page: " + designerIndex.getRandomProduct("search_result")
                .findElement(By.className("productDescription")).getText());
        Assert.assertTrue(designerIndex.getRandomProduct("search_result")
                .findElement(By.className("productDescription")).getText().contains("100%"));
    }


    @When("^I verify user lands on designer browse page when clicking on the designer name is present$")
    public void iClickOnTheFollowingDesignerNameIfPresent() throws ElementNotFoundException {
        List<String> desName = getDesigners();
        List<String> missingDesigners = new ArrayList<>();
        List<String> brands = Elements.findElements("designer_static.brands")
                .stream().map(c -> c.getText()).collect(Collectors.toList());
        try {
            for (String name : desName) {
                if (brands.contains(name.trim())) {
                    Clicks.click(Elements.findElement(By.linkText(name)));
                    logger.info("Clicked on the brand name: " + name);
                    Assert.assertTrue(designerIndex.getRandomBrandName("designer_landing_page").contains(name));
                    Navigate.browserBack();
                } else {
                    missingDesigners.add(name);
                    logger.info(name + " is missing from the designer index page");
                }
            }
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
        logger.info("the list of designer names that were not live on the site is: " + missingDesigners);
    }

    @And("^I validate all links with special characters by clicking on them and verifying they navigate to correct pages$")
    public void iValidateAllLinksWithSpecialCharatersByClickingOnThemAndVerifyingTheyNavigateToCorrectPages() throws Throwable {
        List<String> characters = getCharacters();
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> fobmap = getMyMap(characters);
        clickingURL(fobmap);
    }

    private void clickingURL(LinkedHashMap<String, LinkedHashMap<String, List<String>>> map) throws Throwable{
        Wait.isPageLoaded();
        List<String> incorrectLinks = new LinkedList<>();

        for (Map.Entry<String, LinkedHashMap<String, List<String>>> entry : map.entrySet()) {

            for (Map.Entry<String, List<String>> littleEntry : entry.getValue().entrySet()) {
                for (int i=0; i<littleEntry.getValue().size(); i++) {
                    WebElement category = Elements.findElement(By.linkText(entry.getKey()));
                    logger.info("Current category is " + category.getText());
                    if (littleEntry.getValue().isEmpty()) {
                        logger.info("no url with character " + littleEntry.getKey());
                        map.keySet().iterator().next();
                    } else {
                        Wait.forPageReady();
                        if (isForseePopup()) {
                            colseForseePopup();
                        }

                        if (category.getText().equalsIgnoreCase("designers")) {
                            hoverForSelectionWithRetry(category.getText());
                            logger.info("Chosen " + littleEntry.getValue().get(i) + " to click on");
                            WebElement link = Elements.findElement(By.linkText(littleEntry.getValue().get(i)));
                            Clicks.hoverForSelection(link);
                            Clicks.click(link);
                            logger.info("Clicked on the url " + littleEntry.getValue().get(i));
                            Assert.assertTrue(Elements.elementPresent("designer_static.verify_page"));
                        }
                        else{
                            hoverForSelectionWithRetry(category.getText());
                            logger.info("Chosen " + littleEntry.getValue().get(i) + " to click on");
                            WebElement link = Elements.findElement(By.linkText(littleEntry.getValue().get(i)));

                            Clicks.hoverForSelection(link);
                            if (!Elements.elementInView(link)){
                                for (int c=0; c<3; c++){
                                    try {
                                        Clicks.hoverForSelection(link);
                                        Clicks.click(link);
                                    }
                                    catch (Exception e) {e.printStackTrace();}
                                }
                            }
                            Clicks.click(link);
                            logger.info("Clicked on the url " + littleEntry.getValue().get(i));
                            if(MainRunner.currentURL.contains("/shop/fashion-lookbooks-videos-style-guide/explore-now")){
                                logger.info("Landed on a lookbooks & guides page Landing Page");
                            }
                            else if (MainRunner.currentURL.contains("/b/")) {
                                logger.info("Landed on a lookbook");
                            }
                            else if(!MainRunner.currentURL.contains("/shop/fashion-lookbooks-videos-style-guide/explore-now") ){
                                if(designerIndex.getRandomProduct("category_browse") == null){
                                    incorrectLinks.add(MainRunner.currentURL);
                                }
                            }
                        }
                        Navigate.browserBack();
                        Wait.forPageReady();
                        if (isForseePopup()) {
                            colseForseePopup();
                        }
                        map.keySet().iterator().next();
                    }
                }
            }
        }

    logger.info("List of URLs that broke: "+ incorrectLinks);
        if(incorrectLinks.size()!=0){
         Assert.fail("Scenario fails because some links lead to broken pages");
        }
    }

    private List<WebElement> getFOB() {
        return Elements.findElements("category_menu.category");
    }

    private LinkedHashMap<String, LinkedHashMap<String, List<String>>> getMyMap(List<String> characters) {
        List<WebElement> fob = getFOB();
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> fobmap = new LinkedHashMap<>();
        for (WebElement category : fob) {
            Clicks.hoverForSelection(category);
            String flyouts_xpath = "//div[@id='" + category.getAttribute("aria-controls") + "']/div/div/div/ul/li/a";
            LinkedHashMap<String, List<String>> map = designerIndex.selectLinksWithSpecialCharacters(characters, flyouts_xpath);
            fobmap.put(category.getText(), map);
        }
        return fobmap;
    }

    private void hoverForSelectionWithRetry(String category) throws Throwable{
        int attempts = 0;
        while (attempts < 5) {
            try {
                logger.info("Hovering over " + category);
                Clicks.hoverForSelection(Elements.findElement(By.linkText(category)));
                break;
            } catch (Exception e){
                logger.info(e.getMessage());
            }
            attempts++;
        }
    }

    private boolean isForseePopup(){
        return Elements.elementInView(Elements.findElement(By.id("acsMainInvite")));
    }

    private void colseForseePopup(){
        Elements.findElement(By.linkText("No, thanks")).click();
    }

    private List<String> getCharacters(){
        String charVar = RunConfig.getEnvOrExParam("charVar");
        if (charVar==null){
            charVar = "* , ., &, -, ?, +, ', !, /, \\, |, (, ), ^, $, #, @, ., ® , ô , ó, é, _, è, ë, ™, <, >, :, ;";
        }
        return Arrays.asList(charVar.replaceAll(" ", "").split(","));
    }

    private List<String> getDesigners(){
        String designers = RunConfig.getEnvOrExParam("designers");
        if (designers==null){
            designers = "M·A·C, C/MEO Collective, Nambé, b.tempt'd by Wacoal";
        }
        return Arrays.asList(designers.split(","));
    }


}