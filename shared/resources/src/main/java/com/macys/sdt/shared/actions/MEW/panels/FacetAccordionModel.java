package com.macys.sdt.shared.actions.MEW.panels;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.interactions.Elements.elementInView;
import static com.macys.sdt.framework.utils.StepUtils.macys;

public class FacetAccordionModel {
    private static final Logger log = LoggerFactory.getLogger(FacetAccordionModel.class);
    private static Random randomGenerator = new Random();
    public static List<String> selectedFacetValues = new ArrayList<>();
    public static List<String> activeFacetValues = new ArrayList<>();
    public static String activeFacet;
    public static String selectedFacetValue;

    public static void apply() {
        if (macys()) {
            if (Wait.untilElementPresent("left_facet.apply")) {
                Navigate.execJavascript("window.scrollTo(document.body.scrollHeight,0)");
                Wait.untilElementPresent("left_facet.apply");
                Elements.findElement("left_facet.apply").click();
                Wait.untilElementNotPresent("search_result.loading_mask");
                log.info("facet values are applied");
            } else {
                Assert.fail("Unable to find apply button on facet accordion model");
            }
        } else {
            Clicks.click("search_result.apply_button");
            Wait.untilElementNotPresent("pagination.loading_mask");
        }
        log.info("Clicked on the apply button");
    }

    public static void backButton() {
        if (Wait.untilElementPresent("left_facet.back_button")) {
            Clicks.javascriptClick("left_facet.back_button");
        } else {
            Assert.fail("Unable to find back button on facet accordion model");
        }
    }

    public static void clearAll() {
        if (Wait.untilElementPresent("left_facet.clear_all")) {
            Clicks.javascriptClick("left_facet.clear_all");
            waitForLoading();
            Utils.threadSleep(2000, "Waiting for page to load");
            log.info("all facets are cleared");
        } else {
            Assert.fail("Unable to find clear all button on facet accordion model");
        }
    }

    public static void sortBy(String sortByValue) {
        if (Wait.untilElementPresent("left_facet.accordion_sort_by")) {
            DropDowns.selectByText("left_facet.accordion_sort_by", sortByValue);
        } else {
            Assert.fail("Unable to find clear all button on facet accordion model");
        }
    }

    public static List<String> getAllFacets() {
        if(macys())
            return Elements.findElements("left_facet.facet_accordion_header").stream().filter(WebElement::isDisplayed)
                    .map(WebElement::getText).collect(Collectors.toList());
        else
            return Elements.findElements(By.className("facet-list-item")).stream()
                    .map(WebElement::getText).collect(Collectors.toList());
    }

    public static boolean isFacetAvailable(String facetName) {
        return getAllFacets().contains(facetName);
    }

    public static void selectFacetByName(String facetName) {
        Wait.untilElementPresent("left_facet.facet_accordion_header");
        if (!Elements.getText("left_facet.expand_facet").contains(facetName)) {
            List<WebElement> facet_element = Elements.findElements("left_facet.facet_accordion_header").stream()
                    .filter(WebElement::isDisplayed)
                    .filter(el -> el.isDisplayed() && el.getText().equalsIgnoreCase(facetName))
                    .collect(Collectors.toList());
            if (facet_element == null) {
                Assert.fail("ERROR DATA: " + facetName + "facet is not available");
            } else {
                Navigate.execJavascript("window.scrollTo(0,document.body.scrollHeight)");
                Clicks.click(facet_element.get(0));
                waitForLoading();
            }
            if (Wait.secondsUntilElementNotPresent(By.className("m-expand-facet"), 5)) {
                Assert.fail("Facet header is not expanded in 5 seconds");
            }
            activeFacet = facetName;
            log.info("selected facet is " + activeFacet);
        }
    }

    public static void selectFacetValueByName(String facetValue) {
        List<WebElement> expandedFacetValues;
        List<WebElement> facet;
        activeFacetValues = new ArrayList<>();
        if (activeFacet.equals("Size")) {
            expandedFacetValues = Elements.findElement("left_facet.facet_size")
                    .findElements(By.tagName("li"));
            facet = expandedFacetValues.stream().filter(ele -> ele.isDisplayed() && ele.getText().contains(facetValue))
                    .collect(Collectors.toList());
            if (facet.isEmpty()) {
                Assert.fail("Error Data:- " + facetValue + " facet value not available");
            } else {
                Clicks.click(facet.get(0));
                List<WebElement> subSizes = Elements.findElement("left_facet.active_facet").findElements(By.tagName("li"));
                int randomSize = subSizes.size() > 1 ? randomGenerator.nextInt(subSizes.size() - 1) : 0;
                Clicks.click(subSizes.get(randomSize));
                if (!subSizes.get(randomSize).findElement(By.tagName("center")).findElement(By.tagName("input")).isSelected()) {
                    Assert.fail("ERROR - ENV: The facet value: " + facetValue + " did not become selected after being clicked");
                    selectedFacetValue = facetValue;
                }
                selectedFacetValue = Elements.findElement("left_facet.selected_size").getText();
                activeFacetValues.add(subSizes.get(randomSize).findElement(By.tagName("input")).getAttribute("value"));
            }
        } else {
            expandedFacetValues = Elements.findElement("left_facet.facet_accordions")
                    .findElements(By.tagName("li"));

            facet = activeFacet.equals("Customer Ratings") || activeFacet.equals("Customers' Top Rated") || activeFacet.equals("Customers Top Rated")
                    ? expandedFacetValues.stream().filter(ele -> ele.isDisplayed() && ele.findElement(By.tagName("input")).getAttribute("value").contains(facetValue))
                    .collect(Collectors.toList()) : expandedFacetValues.stream().filter(ele -> ele.isDisplayed() && ele.getText().contains(facetValue))
                    .collect(Collectors.toList());
            if (facet.isEmpty()) {
                Assert.fail("Error Data:- " + facetValue + " facet value not available");
            } else {

                if (!elementInView(facet.get(0)))
                    Navigate.execJavascript("window.scrollTo(document.body.scrollHeight," + facet.get(0).getLocation().getY() + ")");
                Clicks.click(facet.get(0));
                if (!facet.get(0).findElement(By.tagName("input")).isSelected()) {
                    Assert.fail("ERROR - ENV: The facet value: " + facetValue + " did not become selected after being clicked");
                }
            }
            activeFacetValues.add(activeFacet.equals("Price") ? facet.get(0).findElement(By.tagName("input")).getAttribute("value") :
                    facetValue);
            selectedFacetValue = facetValue;
        }
        log.info("single facet selected with facet " + selectedFacetValue);
    }

    public static void selectMultipleFacets() {
        int tries;
        WebElement facet;
        List<String> priviosulySelectedFacetValues = Elements.findElements(By.className("m-facet-accordion-selection")).stream().
                filter(WebElement::isSelected).map(webElement -> webElement.getAttribute("value")).collect(Collectors.toList());
        if (priviosulySelectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        activeFacetValues = new ArrayList<>();
        List<WebElement> expanded_facet_values;
        List<WebElement> unselected_facets;
        if (activeFacet.equals("Size")) {
            expanded_facet_values = Elements.findElement("left_facet.facet_size")
                    .findElements(By.tagName("li"));
            unselected_facets = expanded_facet_values.stream().filter(ele -> ele.isDisplayed() && !ele.getText().isEmpty()
                    && !ele.isSelected()).collect(Collectors.toList());
            if (unselected_facets.isEmpty())
                Assert.fail("Error Data:- facet value not available");
            tries = expanded_facet_values.size() > 5 ? 5 : expanded_facet_values.size() - 1;
            int randomSize;
            for (int i = 0; i < tries; i++) {
                unselected_facets.removeAll(expanded_facet_values.stream().filter(WebElement::isSelected).collect(Collectors.toList()));
                facet = unselected_facets.remove(randomGenerator.nextInt(unselected_facets.size() - 1));
                Navigate.execJavascript("window.scrollTo(document.body.scrollHeight,0)");
                facet.click();
                List<WebElement> subSizes = Elements.findElement("left_facet.active_facet").findElements(By.tagName("li"));
                randomSize = subSizes.size() > 1 ? randomGenerator.nextInt(subSizes.size() - 1) : 0;
                Clicks.click(subSizes.get(randomSize));
                activeFacetValues.add(subSizes.get(randomSize).findElement(By.tagName("input")).getAttribute("value"));
            }
            selectedFacetValues.addAll(Elements.findElements("left_facet.selected_size")
                    .stream().map(WebElement::getText).collect(Collectors.toList()));
        } else {
            boolean flag;
            expanded_facet_values = Elements.findElement("left_facet.facet_accordions")
                    .findElements(By.tagName("li"));
            unselected_facets = expanded_facet_values.stream().filter(ele -> ele.isDisplayed() && ele.isEnabled() && !ele.getText().isEmpty()
                    && !ele.isSelected() && !ele.findElement(By.tagName("input")).getAttribute("class").contains("facet-selection-unavailable")).collect(Collectors.toList());
            if (unselected_facets.isEmpty())
                Assert.fail("Error Data:- facet value not available");
            if (unselected_facets.size() == 1)
                tries = 1;
            else
                tries = unselected_facets.size() > 5 ? 5 : unselected_facets.size() - 1;
            flag = activeFacet.equals("Customer Ratings") || activeFacet.equals("Customers' Top Rated") || activeFacet.equals("Customers Top Rated");
            for (int i = 0; i < tries; i++) {
                unselected_facets.removeAll(expanded_facet_values.stream().filter(WebElement::isSelected).collect(Collectors.toList()));
                facet = unselected_facets.remove(randomGenerator.nextInt(unselected_facets.size() - 1));
                if (!Elements.elementInView(facet))
                    Navigate.execJavascript("window.scrollTo(document.body.scrollHeight," + facet.getLocation().getY() + ")");
                facet.click();
                if (flag)
                    selectedFacetValues.add(facet.findElement(By.tagName("input")).getAttribute("value"));
                else
                    selectedFacetValues.add(facet.getText());
                activeFacetValues.add(facet.findElement(By.tagName("input")).getAttribute("value"));
            }
            if (!flag)
                selectedFacetValues = selectedFacetValues.stream().map(value -> value.split("\\(")[0].trim()).collect(Collectors.toList());

        }
//        selectedFacetValues = selectedFacetValues.stream().distinct().collect(Collectors.toList());
        log.info("multiple facets selected with facet " + selectedFacetValues);

    }

    public static void removeFacets() throws Throwable {
        if (macys()) {
            List<WebElement> clearFacets = Elements.findElements("left_facet.facet_remove");
            for (int i = 0; i < clearFacets.size(); i++) {
                Wait.untilElementPresent("left_facet.facet_remove");
                Elements.findElement("left_facet.facet_remove").click();
                Utils.threadSleep(10000, "Waiting for page load after removing facet value from breadcrumb");
            }
        } else {
            List<WebElement> facets = Elements.findElements("search_result.chip");
            for (int i = 0; i < facets.size(); i++) {
                Wait.secondsUntilElementPresent("search_result.chip",15);
                Elements.findElement("search_result.chip").click();
                Utils.threadSleep(10000, "Waiting for page load after removing facet value from breadcrumb");
            }
        }
        log.info("facet values are cleared");
    }

    public static void removeFacet() throws Throwable {
        if (macys()) {
            Wait.secondsUntilElementPresent("left_facet.facet_remove", 10);
            WebElement removeFacet = Elements.findElements("left_facet.facet_remove").get(0);
            if (!(selectedFacetValues == null))
                selectedFacetValues.remove(removeFacet.getText());
            removeFacet.click();
        } else {
            Wait.secondsUntilElementPresent("search_result.breadcrumb_panel", 10);
            WebElement selectedFacet = Elements.findElements("search_result.chip").get(0);
            selectedFacet.click();
            Utils.threadSleep(5000,"ERROR - APP: Sleep timedout while removing facet");
        }
        log.info("single facet value removed");
    }

      /* method to select multiple facets for bcom */

    public static void multipleFacets(String facet_selection) throws Throwable {
        List<WebElement> unselected_facets;
        List<WebElement> priviosulySelectedFacetValues = Elements.findElements(By.className("facet-value")).stream().
                filter(webElement -> webElement.getAttribute("aria-checked").equals("true")).collect(Collectors.toList());
        if (priviosulySelectedFacetValues.isEmpty())
            selectedFacetValues = new ArrayList<>();
        if (facet_selection.equalsIgnoreCase("Size")) {
            List<WebElement> expanded_size_values = Elements.findElement("left_facet.size_type").findElements(By.tagName("li"));
            unselected_facets = expanded_size_values.stream().filter(ele -> ele.isDisplayed() && !ele.getText().isEmpty()
                    && !ele.isSelected()).collect(Collectors.toList());
            if (unselected_facets.isEmpty())
                Assert.fail("Error Data:- facet values not available");
            int size_count = expanded_size_values.size() > 5 ? 5 : expanded_size_values.size() - 1;
            for (int i = 0; i < size_count; i++) {
                unselected_facets.removeAll(expanded_size_values.stream().filter(element -> element.getAttribute("class")
                        .contains("facet-value-selected")).collect(Collectors.toList()));
                WebElement random_size_type = unselected_facets.get(new Random().nextInt(unselected_facets.size() - 1));
                selectedFacetValues.add(random_size_type.getText().replaceAll("\n", ", ").replaceAll("\\[\\d+\\]", "").trim());
                Clicks.click(random_size_type);
            }
        } else {
            List<WebElement> subFacet = Elements.findElement("search_result.sub_facets").findElements(By.tagName("li")).stream()
                    .filter(ele -> !ele.getAttribute("class").contains("facet-value-none")).collect(Collectors.toList());
            int size_count = subFacet.size() > 5 ? 5 : subFacet.size() - 1;
            for (int i = 0; i < size_count; i++) {
                unselected_facets = subFacet.stream().filter(ele -> ele.isDisplayed() && !ele.getText().isEmpty()
                        && !ele.isSelected()).collect(Collectors.toList());
                unselected_facets.removeAll(subFacet.stream().filter(element -> element.getAttribute("aria-checked").equals("true")).collect(Collectors.toList()));
                WebElement randomSubFacet = unselected_facets.get(new Random().nextInt(unselected_facets.size() - 1));
                selectedFacetValues.add(randomSubFacet.getText().replaceAll("\\[\\d+\\]", "").trim());
                Clicks.click(randomSubFacet);
            }
        }
        log.info("multiple facets selected with facet " + facet_selection);
    }


    public static boolean checkFacetValues() {
        List<String> activeValues = Elements.findElements(By.xpath("//input")).stream().
                filter(WebElement::isSelected).map(facet -> facet.getAttribute("value")).collect(Collectors.toList());
        return activeValues.containsAll(activeFacetValues);
    }


    public static void waitForLoading() {
        if (Wait.secondsUntilElementNotPresent("left_facet.loading_spinner", 20)) {
            log.info("Loading spinner Is not Visible now");
        } else {
            Assert.fail("loading spinner did not go away in 20s.");
        }
    }

    public static String selectRandomFacetValue() {
        activeFacetValues = new ArrayList<>();
        if (activeFacet.equals("Size")) {
            List<WebElement> expandedFacetValues = Elements.findElement("left_facet.facet_size")
                    .findElements(By.tagName("li"));
            WebElement element = expandedFacetValues.size() == 1 ? expandedFacetValues.get(0) : expandedFacetValues.get(randomGenerator.nextInt(expandedFacetValues.size() - 1));
            Clicks.click(element);
            List<WebElement> subSizes = Elements.findElement("left_facet.active_facet").findElements(By.tagName("li"));
            int randomSize = subSizes.size() > 1 ? randomGenerator.nextInt(subSizes.size() - 1) : 0;
            Clicks.click(subSizes.get(randomSize));
            selectedFacetValue = Elements.findElement("left_facet.selected_size").getText();
            activeFacetValues.add(subSizes.get(randomSize).findElement(By.tagName("input")).getAttribute("value"));
        } else {
            List<WebElement> expandedFacetValues = Elements.findElement("left_facet.facet_accordions")
                    .findElements(By.tagName("li")).stream().filter(ele -> ele.isDisplayed() && !ele.getText().isEmpty()
                            && !ele.findElement(By.tagName("input")).getAttribute("class").contains("facet-selection-unavailable")).collect(Collectors.toList());;
            activeFacetValues = new ArrayList<>();
            WebElement facetValue = expandedFacetValues.size() == 1 ? expandedFacetValues.get(0) : expandedFacetValues.get(randomGenerator.nextInt(expandedFacetValues.size() - 1));
            selectedFacetValue = activeFacet.equalsIgnoreCase("price") ? facetValue.getText().split(" \\(")[0] :
                    facetValue.findElement(By.tagName("input")).getAttribute("value");
            activeFacetValues.add(facetValue.findElement(By.tagName("input")).getAttribute("value"));
            Clicks.click(facetValue);
        }
        return selectedFacetValue;
    }

    public static Map<String, List<Map<String, Integer>>> getAllFacetsWithValues() {
        Map<String, List<Map<String, Integer>>> facetsWithValues = new HashMap<>();
        List<String> keys = getAllFacets();
        keys.remove("Size");
        keys.remove("Pick up in store");
        for (String facet : keys) {
            facetsWithValues.put(facet.split("\\n")[0], macys() ? getListforFacet(facet.split("\\n")[0].toLowerCase())
                    :getListforFacetBcom(facet.split("\\n")[0].toUpperCase()));
        }
        log.info("facets with values fetched successfully");
        return facetsWithValues;
    }

    private static List<Map<String,Integer>> getListforFacetBcom(String value) {
        List<Map<String, Integer>> facets = new ArrayList<>();
        List<WebElement> facetList = value.equals("DESIGNER")? Elements.findElement(By.id("b-j-facet-brand-list")).findElements(By.tagName("li")):
                value.equals("SALES & OFFERS")? Elements.findElement(By.className("facet-SPECIAL_OFFERS")).findElements(By.tagName("li")):
                        Elements.paramElement("left_facet.facet_list", value).findElement(WebDriverManager.getWebDriver()).findElements(By.tagName("li"));
        List<String> values = facetList.stream().map(ele -> ele.getAttribute("data-facet-value")).collect(Collectors.toList());
        List<Integer> counts = new ArrayList<>();
        for (WebElement valueElement : facetList) {
            counts.add(Integer.parseInt(valueElement.findElement(By.className("count-display-name")).getAttribute("innerText")
                    .split("\\[")[1].split("\\]")[0]));
        }

        for (int i = 0; i < values.size(); i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put(values.get(i), counts.get(i));
            facets.add(map);
        }

        Collections.sort(facets, mapComparator);
        log.info("facet values list obtained successfully");
        return facets;
    }

    private static List<Map<String, Integer>> getListforFacet(String value) {
        List<Map<String, Integer>> facets = new ArrayList<>();
        if (value.equals("customers' top rated"))
            value = value.split(" ")[1].trim();
        List<WebElement> facetList = Elements.paramElement("left_facet.facet_list", value).findElement(WebDriverManager.getWebDriver()).findElements(By.tagName("li"));
        List<String> values = (value.equals("price") && (PageNavigation.runMode.equals("domestic") || PageNavigation.runMode.equals("iship")))
                ? facetList.stream().map(ele -> ele.findElement(By.className("m-facet-accordion-values"))
                .getAttribute("innerText").split("\\(")[0].trim()).collect(Collectors.toList()) :
                facetList.stream().map(ele -> ele.findElement(By.tagName("input"))
                        .getAttribute("value")).collect(Collectors.toList());
        List<Integer> counts = new ArrayList<>();
        for (WebElement valueElement : facetList) {
            String type = (valueElement.findElement(By.tagName("input")).getAttribute("id").contains("COLOR")) ?
                    "color" : "product";
            counts.add(Integer.parseInt(valueElement.findElement(By.className("m-facet-accordion-" + type + "-count"))
                    .getAttribute("innerText").replaceAll("[\\(]|[ ]|[\\)]", "")));
        }

        for (int i = 0; i < values.size(); i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put(values.get(i), counts.get(i));
            facets.add(map);
        }

        Collections.sort(facets, mapComparator);
        log.info("facet values list obtained successfully");
        return facets;
    }

    public static Comparator<Map<String, Integer>> mapComparator = new Comparator<Map<String, Integer>>() {
        @Override
        public int compare(Map<String, Integer> map1, Map<String, Integer> map2) {
            String val1 = "";
            String val2 = "";
            for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                val1 = entry.getKey();
            }
            for (Map.Entry<String, Integer> entry : map2.entrySet()) {
                val2 = entry.getKey();
            }
            return val1.compareTo(val2);
        }

    };
}
