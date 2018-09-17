package com.macys.sdt.projects.PurchaseAndDelivery.ItemAvailability.actions.website;


import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.SearchResult;
import com.macys.sdt.shared.steps.website.PageNavigation;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.projects.Discovery.SearchBrowseComponentization.utils.config.DiscoveryHelper.*;

public class IAGenericMethods {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IAGenericMethods.class);

    public static int getProductCountFromTab() {
        SearchResult searchResult = Navigate.get(SearchResult.class);
        return Integer.parseInt(searchResult.selectedTab.getText().replaceAll("[^\\d]", ""));
    }

    public static int getTotalThumbnailCount() {
        pausePageHangWatchDog();
        int actTotalProductsCount;
            int pageCount;
            int productPerPage = getProductIds().size();
            if (paginationAvailable()) {
                pageCount = getTotalPageCount();
                String url = PageNavigation.url();
                logger.info("Page url before navigating to last page: " + url);
                navigateToLastPage();
                Wait.forPageReady();
                actTotalProductsCount = productPerPage * (pageCount - 1) + getProductIds().size();
                Navigate.visit(url);
                Wait.forPageReady();
            } else {
                actTotalProductsCount = productPerPage;
            }
        logger.info("Total product displayed on thumbnails: " + actTotalProductsCount);
        resumePageHangWatchDog();
        return actTotalProductsCount;
    }
}