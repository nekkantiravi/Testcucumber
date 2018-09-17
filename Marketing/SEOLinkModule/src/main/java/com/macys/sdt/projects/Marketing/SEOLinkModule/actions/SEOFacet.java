package com.macys.sdt.projects.Marketing.SEOLinkModule.actions;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.Utils.getFileDataInJson;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

/**
 * Created by heqbal on 7/24/2017.
 */
public class SEOFacet extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(SEOFacet.class);

    public static HashMap<String, ArrayList<String>> getFacetNameAndValues() {
        HashMap<String, ArrayList<String>> facetNamesFacetvalues = new HashMap<>();
        for (WebElement e : Elements.findElements("left_facet.available_facet_names")) {
            if (macys()) {
                String fName = e.getAttribute("name").replace("[]", "");
                String fValue = e.getAttribute("value");
                ArrayList<String> newList;
                newList = facetNamesFacetvalues.containsKey(fName) ? facetNamesFacetvalues.get(fName) : new ArrayList<>();
                newList.add(fValue);
                facetNamesFacetvalues.put(fName, newList);
            } else {
                String fName = e.getAttribute("data-facet-name");
                ArrayList<String> newList = new ArrayList<>();
                for (WebElement ee : e.findElements(By.tagName("li"))) {
                    String fValue = ee.getAttribute("data-facet-value");
                    newList.add(fValue);
                    facetNamesFacetvalues.put(fName, newList);
                }
            }
        }
        logger.info("Anchor tags are displaying for crawable facets"+facetNamesFacetvalues);
        return facetNamesFacetvalues;
    }

    public static List getFacetValuesAndLinks() {
        return Elements.findElements("left_facet.anchors_tags_for_facets").stream()
                .map(ele -> {
                    Map<String, String> txt = new HashMap<>();
                    txt.put("name", ele.getAttribute("innerHTML"));
                    txt.put("url", ele.getAttribute("href"));
                    return txt;
                }).collect(Collectors.toList());
    }

    public static JSONArray getFacetData(String facetType) {
        JSONObject facets = getFileDataInJson(getResourceFile("facets.json"));
        return facets.getJSONArray(facetType);
    }

    public static void facetsLinkVerifactions(JSONArray crawableFacets, HashMap<String, ArrayList<String>> facetNameFacetvalues, List facetNamesFacetLinks) {
        for (String b : facetNameFacetvalues.keySet()) {
            if (Collections.singletonList(crawableFacets).toString().contains(b)) {
                crawableLinkVerification(facetNameFacetvalues.get(b), facetNamesFacetLinks);
            } else {
                nonCrawbleLinkVerification(facetNameFacetvalues.get(b), facetNamesFacetLinks);

            }
        }

    }

    public static HashMap<String, ArrayList<String>> getSizeFacetNameAndValues() {
        HashMap<String, ArrayList<String>> sizeFacetNamesAndFacetvalues = new HashMap<>();
        String[] fName;
        for (WebElement e : Elements.findElements("left_facet.size_facets")) {
            fName = e.getAttribute("value").split("#");
            ArrayList<String> temp = new ArrayList<>();
            temp.add(fName[1].replace(",", ""));
            if (sizeFacetNamesAndFacetvalues.containsKey(fName[0]))
                temp.addAll(sizeFacetNamesAndFacetvalues.get(fName[0]));
            sizeFacetNamesAndFacetvalues.put(fName[0], temp);
        }
        return sizeFacetNamesAndFacetvalues;
    }

    public static void facetSizeLinkVerifactions(JSONArray sizeFacetes, HashMap<String, ArrayList<String>> sizeFacetNamesAndFacetvalues, List facetNamesFacetLinks) {
        for (String b : sizeFacetNamesAndFacetvalues.keySet()) {
            if (Arrays.asList(sizeFacetes.toString()).contains(b)) {
                nonCrawbleLinkVerification(sizeFacetNamesAndFacetvalues.get(b), facetNamesFacetLinks);

            } else {
                crawableLinkVerification(sizeFacetNamesAndFacetvalues.get(b), facetNamesFacetLinks);

            }
        }
    }

    public static void crawableLinkVerification(ArrayList<String> facetNameFacetvalue, List facetNamesFacetLinks) {
        for (int i = 0; i < facetNameFacetvalue.size(); i++) {
            ArrayList matchedDetails = new ArrayList();
            Iterator popularSearchIterator = facetNamesFacetLinks.iterator();
            while (popularSearchIterator.hasNext()) {
                HashMap nameWithURL = (HashMap) popularSearchIterator.next();
                String actualFacetName = (String) nameWithURL.get("name");
                String expectedFacetName = (String) facetNameFacetvalue.get(i);
                if (actualFacetName.replace("&amp;", "&").trim().equalsIgnoreCase(expectedFacetName.replace("&amp;", "&").trim())) {
                    matchedDetails.add(nameWithURL);
                }
            }
            matchedDetails.forEach(f -> {
                HashMap nameWithURL = (HashMap) f;
                Assert.assertFalse("ERROR - APP: Found empty URL for Facet Value:" + nameWithURL.get("name"), nameWithURL.get("url").equals(""));
                Assert.assertFalse("ERROR - APP: Other parameters are displaying in URL" + nameWithURL.get("url"), nameWithURL.get("url").toString().contains("&edge=hybrid&EFCKEY"));
                logger.info("URL'S" + nameWithURL.get("url"));
            });
        }
    }

    public static void nonCrawbleLinkVerification(ArrayList<String> facetNameFacetvalue, List facetNamesFacetLinks){
        for (int i = 0; i < facetNameFacetvalue.size(); i++) {
            ArrayList matchedDetails = new ArrayList();
            Iterator popularSearchIterator = facetNamesFacetLinks.iterator();
            while (popularSearchIterator.hasNext()) {
                HashMap nameWithURL = (HashMap) popularSearchIterator.next();
                String actualFacetName = (String) nameWithURL.get("name");
                String exectedFacetName = (String) facetNameFacetvalue.get(i);
                if (actualFacetName.replace("&amp;", "&").trim().equalsIgnoreCase(exectedFacetName.replace("&amp;", "&").trim())) {
                    matchedDetails.add(nameWithURL);
                }
            }
            Assert.assertTrue("ERROR - APP: URL is displaying for black listed Facet Value" + matchedDetails.toString(), matchedDetails.isEmpty());
        }
    }

    public static void goToFacetsFilters() {
        String facetFilter = macys()? "left_facet.sort_filter" : "left_facet.refine_results";
        Wait.secondsUntilElementPresent(facetFilter, 10);
        Clicks.click(facetFilter);
    }

    public static void pdpStrutureDataForBrand(JSONObject jsonObject){
        List<String> brandElements = Arrays.asList("@type", "name");
        JSONObject brandResult = jsonObject.getJSONObject("brand");
        for (String f : brandElements) {
            Assert.assertTrue("ERROR - APP : PDP Structure" + f + "is not displaying or not available for Brand", brandResult.keySet().contains(f));
        }
    }

    public static void pdpStrutureDataForOffers(JSONObject jsonObject){
        List<String> offerElements = Arrays.asList("priceCurrency", "itemOffered", "@type", "price", "availability");
        Object offerResult = jsonObject.get("offers");
        JSONObject offerResults;
        offerResults = (offerResult instanceof JSONObject) ? (JSONObject) offerResult : ((JSONArray) offerResult).getJSONObject(0);
        for (String g : offerElements) {
            Assert.assertTrue("ERROR - APP : PDP Structure" + g + "is not displaying or not available for Offers", offerResults.keySet().contains(g));
        }
    }

    public static void pdpStrutureDataForAggregateRating(JSONObject jsonObject){
        List<String> aggregateRatingElements = Arrays.asList("@type", "ratingValue", "ratingCount", "bestRating", "itemReviewed", "@type");
        JSONObject aggregateRatingResult = jsonObject.getJSONObject("aggregateRating");
        for (String h : aggregateRatingElements) {
            Assert.assertTrue("ERROR - APP : PDP Structure" + h + "is not displaying or not available for Aggregate Rating", aggregateRatingResult.keySet().contains(h));

        }
    }

    public static void pdpStrutureDataForReviews(JSONObject jsonObject){
        List<String> reviewElements = Arrays.asList("@type", "datePublished", "description", "name", "itemReviewed", "@type");
        Object offerReview = jsonObject.get("reviews");
        JSONObject offerReviews;
        offerReviews = (offerReview instanceof JSONObject) ? (JSONObject) offerReview : ((JSONArray) offerReview).getJSONObject(0);
        for (String i : reviewElements) {
            Assert.assertTrue("ERROR - APP : PDP Structure" + i + "is not displaying or not available for Aggregate Rating", offerReviews.keySet().contains(i));
        }
    }
}
