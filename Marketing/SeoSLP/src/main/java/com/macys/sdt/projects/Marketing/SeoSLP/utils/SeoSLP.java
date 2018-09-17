package com.macys.sdt.projects.Marketing.SeoSLP.utils;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.projects.Marketing.SeoSLP.steps.website.mcom.SearchLandingPage;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.url;


/**
 * Created by dbodige on 8/11/2017.
 */
public class SeoSLP {

    public static List<WebElement> getAllFacetItems(String facetName){
        if(Elements.elementPresent(Elements.element("left_facet.facet_names"))){

            if (!LeftFacet.isExpanded(facetName)){
                LeftFacet.expandFacet(facetName);
            }
            By facetItems = LeftFacet.getFacetItems(facetName);
            SearchLandingPage.facetValues = Elements.findElements(facetItems);
        }
        else {
            Assert.fail("ERROR - ENV: Facets are not displayed on left navigation");
        }
        return SearchLandingPage.facetValues;
    }

    public static String getCategoryID() {
        String[] splitedURL = url().split("id=");
        String category = splitedURL[1];
        if (splitedURL[1].contains("&")) {
            splitedURL = splitedURL[1].split("&");
            category = splitedURL[0];
        }
        return category;
    }


}
