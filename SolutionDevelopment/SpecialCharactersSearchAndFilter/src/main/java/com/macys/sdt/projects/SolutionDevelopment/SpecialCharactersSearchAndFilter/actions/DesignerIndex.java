package com.macys.sdt.projects.SolutionDevelopment.SpecialCharactersSearchAndFilter.actions;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.SolutionDevelopment.SpecialCharactersSearchAndFilter.steps.website.SpecialCharacters;
import cucumber.api.java.hu.Ha;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.List;


/**
 * Created by u063689 on 10/23/2017.
 */
public class DesignerIndex {

        private List<String> Chars;
        private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SpecialCharacters.class);

    public LinkedHashMap<String, String> selectDesignerBySpecialCharacterOneValue(List<String> characters) {
        setCharacters(characters);
        List<WebElement> brands = Elements.findElements("designer_static.brands");
        Collections.shuffle(brands);
        List<WebElement> myEl = brands.stream().filter(ele -> hasCharacter(getCharacters(), ele))
                .peek(ele -> logger.info(ele.getText()))
                .collect(Collectors.toList());

        return characters
                .stream()
                .map(c ->
                        new AbstractMap.SimpleImmutableEntry<>(
                                c, myEl.stream()
                                .filter(ele -> ele.getText().contains(c))
                                .collect(Collectors.toList())
                        ))
                        .collect(LinkedHashMap::new, (k,v) -> k.put(v.getKey(),
                        v.getValue().isEmpty() ? "" : v.getValue().get(0).getText() ), LinkedHashMap::putAll);
    }

    public LinkedHashMap<String, List<String>> selectLinksWithSpecialCharacters(List<String> characters, String xpathFlyouts) {
        List<WebElement> allLinks = Elements.findElements(By.xpath(xpathFlyouts));
        List<WebElement> links = allLinks.stream().filter(ele -> linkWithCharacter(characters, ele))
                .collect(Collectors.toList());
        List<String> linksText = links.stream().map(e -> e.getText()).collect(Collectors.toList());
        return characters.stream().map(c->
                            new AbstractMap.SimpleImmutableEntry<>(
                                    c, linksText.stream()
                                            .filter(ele -> ele.contains(c))
                                            .collect(Collectors.toList())
                            ))
                .collect(LinkedHashMap::new, (k,v) -> k.put(v.getKey(), v.getValue() ), LinkedHashMap::putAll);
    }

    private boolean linkWithCharacter(List<String> characters, WebElement element){
        boolean value = false;
        String ele = element.getText();
        for (String ch: characters){
            if(ele.contains(ch)){
                value = true;
            }
        }
        return value;
    }


    private boolean hasCharacter(List<String> characters, WebElement element) {
        boolean value = false;
        if(characters.size()==0){
            return false;
        }
        String brand = element.getText();
        String newchars[] = new String[characters.size()];
        newchars = characters.toArray(newchars);
        for (String charac : characters
                ) {
                    if(brand.contains(charac)){
                        newchars = ArrayUtils.removeElement(newchars, charac);
                        value = true;
                    }
        }
        characters = Arrays.asList(newchars);
        setCharacters(characters);
        return value;
    }


    private List<String> getCharacters(){
            return Chars;
    }

    private void setCharacters(List<String> input) {
         this.Chars = input;
    }

    public WebElement getRandomProduct(String page){
        Wait.forPageReady(page);
        if(Elements.elementPresent(page + ".thumbnail_wrapper")){
            List<WebElement> products = Elements.findElements(page + ".thumbnail_wrapper");
            Random random = new Random();
            return products.get(random.nextInt(products.size()));
        }
        return null;
    }

    public String getRandomBrandName(String page){
        Wait.forPageReady(page);
        if (Elements.elementPresent(page + ".brand_name")){
            List<WebElement> names = Elements.findElements(page + ".brand_name");
            logger.info(names.size() + " products on the page");
            List<String> namesText = names.stream().map(c-> c.getText()).collect(Collectors.toList());
            Random randomizer = new Random();
            return namesText.get(randomizer.nextInt(namesText.size()));

        }
        else{
        return "none";
        }
    }

    public void searchFor(String attribute){
        TextBoxes.typeTextbox("header.search_field", attribute);
        Clicks.click("header.search_button");
    }


}