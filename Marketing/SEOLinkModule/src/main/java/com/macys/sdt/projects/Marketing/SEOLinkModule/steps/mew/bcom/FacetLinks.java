package com.macys.sdt.projects.Marketing.SEOLinkModule.steps.mew.bcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.Marketing.SEOLinkModule.actions.SEOFacet;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by heqbal on 7/3/2017.
 */
public class FacetLinks {
    private static final Logger logger = LoggerFactory.getLogger(FacetLinks.class);

    @Then("^I should see the anchor tags created for all facet type/value combinations in DOM$")
    public void iShouldSeeTheAnchorTagsCreatedForAllFacetTypeValueCombinationsInDOM() throws Throwable {
        SEOFacet.goToFacetsFilters();
        HashMap<String, ArrayList<String>> facetNamesFacetvalues = SEOFacet.getFacetNameAndValues();
        List facetvaluesFacetLinks = SEOFacet.getFacetValuesAndLinks();
        Assert.assertFalse("ERROR - APP: Facet links data is not displaying or kill switch SEO_CREATE_FACETS_LINKS is off", facetvaluesFacetLinks.isEmpty());
        JSONArray crawableFacets = SEOFacet.getFacetData("CRAWABLE_FACETS");
        SEOFacet.facetsLinkVerifactions(crawableFacets, facetNamesFacetvalues, facetvaluesFacetLinks);
        logger.info("Anchor tags are displaying for crawable facets");
    }

    @Then("^I should not see the anchor tags for noncrawable facet type/value combinations in DOM$")
    public void iShouldNotSeeTheAnchorTagsForNoncrawableFacetTypeValueCombinationsInDOM() throws Throwable {
        SEOFacet.goToFacetsFilters();
        HashMap<String, ArrayList<String>> sizeFacetNamesAndValues = SEOFacet.getSizeFacetNameAndValues();
        List facetNamesFacetLinks = SEOFacet.getFacetValuesAndLinks();
        logger.info("Anchor tag facet names and values" + facetNamesFacetLinks);
        Assert.assertFalse("ERROR - APP: Facet links data is not displaying or kill switch SEO_CREATE_FACETS_LINKS is off", facetNamesFacetLinks.isEmpty());
        JSONArray sizeFacetes = SEOFacet.getFacetData("NON_CRAWABLE_SIZE_FACETS");
        SEOFacet.facetSizeLinkVerifactions(sizeFacetes, sizeFacetNamesAndValues, facetNamesFacetLinks);
        logger.info("Anchor tags are not displaying for noncrawable facets");
    }

    @Then("^I should not see the facet links URLS in DOM$")
    public void iShouldNotSeeTheFacetLinksURLSInDOM() throws Throwable {
        Assert.assertFalse("ERROR - APP: Facet links are displaying in anchor tag", Elements.elementPresent("left_facet.anchors_tags_for_facets"));
        logger.info("Facet links are not displaying in anchor tag");
    }

    @Then("^I should see the facet links URLS in DOM$")
    public void iShouldSeeTheFacetLinksURLSInDOM() throws Throwable {
        Wait.secondsUntilElementPresent("left_facet.anchors_tags_for_facets", 20);
        Assert.assertTrue("ERROR - APP: Facet links are not displaying in anchor tag", Elements.elementPresent("left_facet.anchors_tags_for_facets"));
        logger.info("Facet links are displaying in anchor tag");
    }

    @Then("^I should see the anchor tags created for all facet type/value combinations in DOM of bcom$")
    public void iShouldSeeTheAnchorTagsCreatedForAllFacetTypeValueCombinationsInDOMOfBcom() throws Throwable {
        SEOFacet.goToFacetsFilters();
        HashMap<String, ArrayList<String>> facetNamesAndFacetValues = SEOFacet.getFacetNameAndValues();
        List faceNamesFacetLinks = SEOFacet.getFacetValuesAndLinks();
        JSONArray crawableFacets = SEOFacet.getFacetData("CRAWABLE_SIZE_FACETS");
        facetNamesAndFacetValues.remove("SIZE_TYPE");
        for (String b : facetNamesAndFacetValues.keySet()) {
            if (Arrays.asList(crawableFacets).toString().contains(b)) {
                SEOFacet.crawableLinkVerification(facetNamesAndFacetValues.get(b), faceNamesFacetLinks);

            } else {
                SEOFacet.nonCrawbleLinkVerification(facetNamesAndFacetValues.get(b), faceNamesFacetLinks);
            }
        }
    }

    @Then("^I should not see the anchor tags for noncrawable facet type/value combinations in DOM of bcom$")
    public void iShouldNotSeeTheAnchorTagsForNoncrawableFacetTypeValueCombinationsInDOMOfBcom() throws Throwable {
        SEOFacet.goToFacetsFilters();
        HashMap<String, ArrayList<String>> facetNamesAndFacetValues = SEOFacet.getFacetNameAndValues();
        List faceNamesFacetLinks = SEOFacet.getFacetValuesAndLinks();
        JSONArray crawableFacets = SEOFacet.getFacetData("NON_CRAWABLE_SIZE_FACETS");
        for (String b : facetNamesAndFacetValues.keySet()) {
            if (Arrays.asList(crawableFacets).toString().contains(b)) {
                ArrayList facetValues = facetNamesAndFacetValues.get(b);
                for (int i = 0; i < facetValues.size(); i++) {
                    ArrayList matchedDetails = new ArrayList();
                    Iterator popularSearchIterator = faceNamesFacetLinks.iterator();
                    while (popularSearchIterator.hasNext()) {
                        HashMap nameWithURL = (HashMap) popularSearchIterator.next();
                        String actualFacetName = (String) nameWithURL.get("name");
                        String[] fName;
                        fName = facetValues.get(i).toString().split("#");
                        String exectedFacetName = fName[1].replace(",", "");
                        if (actualFacetName.replace("&amp;", "&").trim().equalsIgnoreCase(exectedFacetName.replace("&amp;", "&").trim())) {
                            matchedDetails.add(nameWithURL);
                        }
                    }
                    Assert.assertTrue("ERROR - APP: URL is displaying for black listed Facet Value" + matchedDetails.toString(), matchedDetails.isEmpty());
                }
            } else {
                SEOFacet.crawableLinkVerification(facetNamesAndFacetValues.get(b), faceNamesFacetLinks);
            }
        }
    }


}