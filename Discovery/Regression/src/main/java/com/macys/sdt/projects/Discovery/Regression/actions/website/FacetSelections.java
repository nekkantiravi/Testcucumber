package com.macys.sdt.projects.Discovery.Regression.actions.website;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import org.openqa.selenium.By;

import java.util.*;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.bloomingdales;
import static com.macys.sdt.framework.utils.StepUtils.macys;
import static com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet.getFacetApply;

public class FacetSelections {

    public static void randomFacetSelectionBrowsePage() {
        List<String> facetNames = LeftFacet.getAllFacetName();
        facetNames.remove("Pick Up In Store");
        facetNames.remove("Free Pick Up In Store");
        facetNames.remove("Category");
        Random ran = new Random();
        int index = ran.nextInt(facetNames.size());
        String facetName = facetNames.get(index);
        System.out.println("Facet Name: " + facetName);
        Navigate.browserRefresh();
        if (!LeftFacet.isExpanded(facetName)) {
            LeftFacet.expandFacet(facetName);
        }
        Clicks.clickRandomElement(LeftFacet.getFacetItems(facetName));
    }

    public static String randomFacetValueSelection(String facetName) throws InterruptedException {
        System.out.println("Facet Name: " + facetName);
        if (!LeftFacet.isExpanded(facetName)) {
            LeftFacet.expandFacet(facetName);
        }
        if (!LeftFacet.isExpanded(facetName)) {
            Navigate.browserRefresh();
            Wait.forLoading("left_facet.loading");
            Wait.forPageReady();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LeftFacet.expandFacet(facetName);
        }
        HashMap facetValuesWithCount = (HashMap)DiscoveryHelper.getAllFacetValuesWithProductCount(facetName);
        List facetValues = (ArrayList)facetValuesWithCount.keySet().stream().map(m -> m).collect(Collectors.toList());
        Random random = new Random();
        int randomIndex = facetValues.size() == 1 ? 0 : random.nextInt(facetValues.size() - 1);
        if (macys() && facetName.equals("Brand") && facetValues.size() > 10) {
            Clicks.click(By.id("all_brands"));
        }
        String facetValue = (String)facetValues.get(randomIndex);
        LeftFacet.selectedFilter = facetValue;
        LeftFacet.filterCount = Integer.parseInt((String)facetValuesWithCount.get(facetValue));
        LeftFacet.selectItemFromFacet(facetValue, facetName);
        if (bloomingdales()) {
            Clicks.javascriptClick(getFacetApply(facetName));
        }
        Wait.forPageReady();
        Thread.sleep(15000);
        return facetValue;
    }

}
