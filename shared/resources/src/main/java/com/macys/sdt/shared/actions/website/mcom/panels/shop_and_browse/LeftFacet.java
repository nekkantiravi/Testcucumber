package com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeftFacet extends StepUtils {

    public static int filterCount;
    public static String selectedFilter;
    private static final Logger logger = LoggerFactory.getLogger(LeftFacet.class);
    public static String facetValue;
    public static String facetName;

    public static void selectItemFromFacet(String toSelect, String facet) {
        facetValue = toSelect;
        facetName = facet;
        pausePageHangWatchDog();
        Clicks.clickIfPresent("home.close_signup_for_email_popup");
        expandFacet(facet);
        if (!isExpanded(facet)) {
                Navigate.browserRefresh();
                Wait.forLoading("left_facet.loading");
                Wait.forPageReady();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                expandFacet(facet);
        }
        if(macys() && facet.equalsIgnoreCase("brand")){
            Wait.untilElementPresent("left_facet.expand_all_brand_facet");
            if(!Elements.findElements("left_facet.expand_all_brand_facet").isEmpty() && Elements.findElements("left_facet.expand_all_brand_facet").get(0).getAttribute("class").contains("collapsed")){
                Elements.findElements("left_facet.expand_all_brand_facet").get(0).click();
            }
        }
        Wait.secondsUntilElementPresent(getFacetDiv(facet),10);
        List<WebElement> facetValues = new ArrayList<>();
        WebElement facetSection = Elements.findElement(LeftFacet.getFacetDiv(facet));
        if(bloomingdales()){
            for(WebElement facetValueEle: facetSection.findElements(By.className("item_value"))){
                if(facetValueEle.isDisplayed() && facetValueEle.getText().equalsIgnoreCase(toSelect))
                    facetValues.add(facetValueEle);
            }
        }else{
            if (facet.toLowerCase().contains("store")) {
                for (WebElement facetValueEle : facetSection.findElements(By.className("item_value"))) {
                    if (facetValueEle.isDisplayed() && facetValueEle.getText().replaceAll("\\n", " ").equalsIgnoreCase(toSelect))
                        facetValues.add(facetValueEle);
                }
            }else if(facet.equalsIgnoreCase("size")) {
                for (WebElement facetValueEle : facetSection.findElements(By.tagName("a"))) {
                    if (facetValueEle.isDisplayed() && facetValueEle.getText().replaceAll("\\n", " ").equalsIgnoreCase(toSelect))
                        facetValues.add(facetValueEle);
                }
            }else if (facet.contains("\"")) {
                for (WebElement facetValueEle : facetSection.findElements(By.tagName("a"))) {
                    if (facetValueEle.isDisplayed() && facetValueEle.getAttribute("data-displayname").replace("\"", "").replaceAll("\\n", " ").replaceAll(",", "").equalsIgnoreCase(toSelect.replace("\"", "").replaceAll(",", "")))
                        facetValues.add(facetValueEle);
                }
            }else{
                for (WebElement facetValueEle : facetSection.findElements(By.className("item"))) {
                    try {
                        if (facetValueEle.isDisplayed() && facetValueEle.getAttribute("data-displayname").trim().replaceAll("\\n", " ").replaceAll(",", "").equalsIgnoreCase(toSelect.replaceAll(",", "")))
                            facetValues.add(facetValueEle);
                    }catch (NullPointerException nle){
                        if (facetValueEle.isDisplayed() && facetValueEle.getText().replaceAll("\\n", " ").replaceAll(",", "").equalsIgnoreCase(toSelect.replaceAll(",", "")))
                            facetValues.add(facetValueEle);
                    }
                }
            }
        }
        Assert.assertTrue("ERROR - DATA: Facet Value:'"+toSelect+"' found multiple times under facet:"+facet+". Actual returned facets are:"+facetValues, facetValues.size() > 0);
        Clicks.click(facetValues.get(0));
        if (bloomingdales()) {
            Clicks.javascriptClick(getFacetApply(facet));
        }
        Wait.forLoading("left_facet.loading");
        Wait.forPageReady();
        resumePageHangWatchDog();
    }

    /*
    Returns list of all Facet Names displayed.
    @Example:
        List<String> facetNames = LeftFacet.getAllFacetName()
             facetNames   #=>    "Special Offers",
                                 "Size",
                                "Brand",
                                "Color",
                                "Price",
                                "Customers' Top Rated"
    @return List<String> a list of facet names displayed on the page.
 */
    public static List<String> getAllFacetName() {
        pausePageHangWatchDog();
        List<String> availableFacetNames = new ArrayList<>();
        Wait.secondsUntilElementPresent("left_facet.facet_names", 15);
        if (Elements.findElement("left_facet.facet_names").isDisplayed()) {
            for(WebElement facetElement: Elements.findElements(Elements.element("left_facet.facet_names"))){
                if(!facetElement.getText().equalsIgnoreCase("")){
                    availableFacetNames.add(facetElement.getText());
                }
            }
        }
        logger.info("Available all facet names on left nav" + availableFacetNames);
        resumePageHangWatchDog();
        return availableFacetNames;
    }

   public static String getExactFacetName(String partialText) {
        pausePageHangWatchDog();
        Wait.secondsUntilElementPresent("left_facet.facet_names", 15);
        if (Elements.element("left_facet.facet_names") != null) {
            for(WebElement facetElement: Elements.findElements(Elements.element("left_facet.facet_names"))){
                if(facetElement.getText().toLowerCase().contains(partialText.toLowerCase())){
                    return facetElement.getText();
                }
            }
        }
        logger.info("Facet name with partial text:" + partialText);
        resumePageHangWatchDog();
        return null;
    }

    public static boolean isExpanded(String facet) {
        try{
            if (macys()) {
                return Elements.findElement(getFacetDiv(facet)).getAttribute("aria-expanded").equals("true");
            } else {
                return !Elements.findElement(getFacetDiv(facet)).getAttribute("class").contains("collapsed");
            }
        }catch (NullPointerException exe){
           Assert.fail("Facet Name:'"+facet+"' is not available to verify expanded status");
        }
        return false;
    }

    public static void expandFacet(String facet) {
        if (!isExpanded(facet)) {
            Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
            Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
            Clicks.click(getHeader(facet));
        }
        if (facet.equalsIgnoreCase("size") && !Elements.elementPresent(getFacetItems(facet))) {
            Elements.findElements("left_facet.expand_size_categories")
                    .stream().filter(f-> f.isDisplayed()).collect(Collectors.toList()).forEach(Clicks::click);
            Wait.secondsUntilElementPresent("category_browse.loading_mask", 5);
            Wait.secondsUntilElementNotPresent("category_browse.loading_mask", 20);
        }
    }

    public static void collapseFacet(String facet) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isExpanded(facet)) {
            Wait.untilElementPresent(getHeader(facet));
            Clicks.clickWhenPresent(getHeader(facet));
        }
    }

    public static By getHeader(String facet) {
        // sometimes the attribute we're checking is the exact header name
        // sometimes it's the header name in all caps with _ instead of space. Need to check.
        if (Elements.elementPresent(Elements.paramElement("left_facet.facet_header", facet.toUpperCase()))) {
            return Elements.paramElement("left_facet.facet_header", facet.toUpperCase());
        } else {
            return Elements.paramElement("left_facet.facet_header", fixIdentifier(facet));
        }
    }

    public static By getFacetItems(String facet) {
        if (Elements.elementPresent(Elements.paramElement("left_facet.facet_items", facet))) {
            return Elements.paramElement("left_facet.facet_items", facet);
        } else {
            return Elements.paramElement("left_facet.facet_items", fixIdentifier(facet));
        }
    }

    public static By getFacetDiv(String facet) {
        if (Elements.elementPresent(Elements.paramElement("left_facet.facet_div", facet.toUpperCase()))) {
            return Elements.paramElement("left_facet.facet_div", facet.toUpperCase());
        } else if (Elements.elementPresent(Elements.paramElement("left_facet.facet_div", facet))) {
            return Elements.paramElement("left_facet.facet_div", facet);
        } else {
            return Elements.paramElement("left_facet.facet_div", fixIdentifier(facet));
        }
    }

    public static boolean facetPresent(String facet) {
        try {
            getFacetDiv(facet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean selectClearAllForFacet(String facet){
        try {
            Elements.findElement(getFacetDiv(facet)).findElement(By.className("clearall")).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<WebElement> selectFacetValues(String facet){
        List<WebElement> selectedFacetValues = new ArrayList<>();
        try {
            Elements.findElement(getFacetDiv(facet)).findElements(By.tagName("a")).stream().map(m -> m.isDisplayed() && m.getAttribute("class").contains("selected")).collect(Collectors.toList());
            return selectedFacetValues;
        } catch (Exception e) {
            return selectedFacetValues;
        }
    }

    public static By getFacetApply(String facet) {
        if (Elements.elementPresent(Elements.paramElement("left_facet.facet_apply", facet))) {
            return Elements.paramElement("left_facet.facet_apply", facet);
        } else {
            return Elements.paramElement("left_facet.facet_apply", fixIdentifier(facet));
        }
    }

    public static boolean facetAvailable(String facet) {
        try {
            return Elements.elementPresent(By.id(fixIdentifier(facet)));
        } catch (Exception e) {
            return false;
        }
    }

    private static String fixIdentifier(String facet) {
        switch (facet.toLowerCase()) {
            case "gender":
                return "BEAUTY_GENDER";
            case "more ways to shop":
                return "SHOWONLY";
            case "in-store pickup":
            case "pick up in-store":
                return "UPC_BOPS_PURCHASABLE";
            case "apparel type":
                return "MENS_PRODUCT_TYPE";
            case "size":
                return "SIZE_TYPE";
            case "color":
                return "COLOR_NORMAL";
            case "customers' top rated":
                return "CUSTRATINGS";
            case "customer ratings":
                return "CUSTRATINGS";
            case "customers top rated":
                return "CUSTRATINGS";
            case "customer top rated":
                return "CUSTRATINGS";
            case "designer":
                return "BRAND";
            case "sales & offers":
                return "SPECIAL_OFFERS";
            case "department":
                return "FOB";
            case "style":
                return "DRESS_STYLE_M";
            case "age group":
                return "AGE_CATEGORY";
            case "women's regular":
                return "WOMEN_REGULAR_SIZE_T";
            case "plus sizes":
                return "WOMEN_PLUS_SIZE_T";
            case "petite":
                return "WOMEN_PETITE_SIZE_T";
            case "juniors":
                return "WOMEN_JUNIORS_SIZE_T";
            case "baby girl 0-24m":
                return "BABY_GIRL_0-24M_SIZE_T";
            case "girls":
                return "LITTLE_GIRL_2-6X_SIZE_T";
            case "dress occasion":
                return "SPECIAL_OCCASIONS";
            case "fit":
                return "JEAN_FIT_M";
            case "rise":
                return "RISE_M";
            case "maternity":
                return "MATERNITY_SIZE_ALL_T";
            case "regular":
                return "MATERNITY_SIZE_REG_T";
            case "plus":
                return "MATERNITY_SIZE_PLUS_T";
            case "skirt length":
                return "DRESS_LENGTH";
            case "item type":
                return "PRODUCT_DEPARTMENT";
            case "gifts":
                return "BEAUTY_GIFTING";
            case "heel height":
                return "HEEL";
            case "category":
                return "PRODUCT_DEPARTMENT";
            case "product size":
                return "BEAUTY_SIZE";
            case "the it list":
                return "TRENDS";
            case "boot style":
                return "BOOTS_TYPE";
            case "girls' plus":
                return "GIRL_PLUS_SIZE_T";
            case "toddler girls":
                return "TODDLER_GIRLS_T";
            case "little girls":
                return "LITTLE_GIRLS_T";
            case "big girls":
                return "BIG_GIRLS_T";
            case "material":
                return "FABRIC";
            case "fabric technology":
                return "FABRIC_PROPERTY";
            case "fragrance type":
                return "FRAGRANCE_PRODUCT_TYPE";
            case "fragrance notes":
                return "FRAGRANCE_SCENTS";
            case "performance features":
                return "FABRIC_PROPERTY";
            case "ring style":
                return "RINGS_STYLE";
            case "diamond carat":
                return "STONE_WEIGHT";
            case "Cookware Material":
                return "INTERIOR";
            case "Denim Style":
                return "DENIM_FIT";
            case "shaft height":
                return "BOOT_HEIGHT";
            case "wallet style":
                return "WALLET_TYPE";
            case "activity":
                return "SPORT";
            default:
                return facet.toUpperCase().replace(" ", "_");
        }
    }
}
