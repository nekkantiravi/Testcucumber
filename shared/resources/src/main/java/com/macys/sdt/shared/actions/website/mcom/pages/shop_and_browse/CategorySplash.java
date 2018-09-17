package com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.utils.CommonUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CategorySplash extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CategorySplash.class);
    private CategoryBrowse browse = new CategoryBrowse();

    public static By selectFeaturedCategory(String category) {
        return By.xpath("//img[@alt='" + category + "']");
    }

    public static void selectCustomerTopRatedProduct() {
        WebElement itemElement = Elements.findElement(Elements.element("category_splash.splash_reviews")).findElements(By.id("splashReview")).get(0);
        Clicks.click(itemElement.findElement(By.id("reviewProductImage")));
    }

    /**
     * Method to navigate to a random category browse page
     *
     * @param max_attempts number of retry attempts to make
     * @throws Exception thrown if any exception found
     */
    public void navigateToRandomCategoryBrowsePage(int max_attempts) throws Exception {
        for (int i = 0; i < max_attempts; i++) {
            try {
                Home.selectRandomSubCategory();
            } catch (Exception e) {
                //retry on exception
                if (RunConfig.debugMode) {
                    logger.info("Number of Attempts for Browse Page is" + i);
                }
            }
            if (onPage("category_browse") || browse.paginationAvailable() || browse.sortByAvailable()) {
                return;
            }
        }
        Assert.fail("ERROR - DATA : Unable to found Browse page in " + max_attempts + " attempts");
    }

    /**
     * Method to navigate to a random category browse page having SEO links
     *
     * @param max_attempts number of retry attempts to make
     * @throws Exception thrown if any exception found
     */
    public void navigateToRandomCategoryWithPopularSearchLink(int max_attempts) throws Exception {
        CommonUtils.retryAction(() -> {
            try {
                Home.selectRandomSubCategory();
            } catch (Exception e) {
                return false;
            }
            if (popularSearchLinksAvailable()) {
                selectOnePopularSearchLink();

                // Safari is not waiting for page load after clicking on PopularSearchLink
                if (safari()) {
                    Utils.threadSleep(1000, null);
                    Wait.forPageReady();
                }

                if (browse.sortByAvailable() && browse.paginationAvailable()) {
                    return true;
                }
            }
            return false;
        }, max_attempts, "ERROR - DATA: Popular search links page is not available in " + max_attempts + " attempts");
    }

    /**
     * Method to click a link from a list of available SEO links in category browse page
     *
     * @return link that was selected
     */
    public static String selectOnePopularSearchLink() {
        List<WebElement> seo_links = Elements.findElements("popular_searches.seo_tag_links");
        seo_links = seo_links.stream().filter(link -> link.getAttribute("href").contains((macys() ? "/featured/" : "/buy/"))).collect(Collectors.toList());
        Assert.assertFalse("ERROR - DATA: Popular search links are not displaying", seo_links.isEmpty());
        int index = seo_links.size() == 1 ? 0 : new Random().nextInt(seo_links.size() - 1);
        String linkText = seo_links.get(index).getText();
        Clicks.javascriptClick(seo_links.get(index));
        logger.info("Selected popular searches link text:"+linkText);
        return linkText;
    }

    /**
     * Method to check whether SEO links are present or not
     *
     * @return true if SEO links are present, else returns false
     */
    public static boolean popularSearchLinksAvailable(){
        scrollToLazyLoadElement("popular_searches.tag_cloud_link_section");
        Utils.threadSleep(1000, "ERROR - ENV: Waiting for populr searches section(tag cloud) load");
        List<WebElement> seo_links = Elements.findElements("popular_searches.seo_tag_links");
        for (WebElement element : seo_links) {
            if (element.getAttribute("href").contains((macys() ? "/featured/" : "buy"))) {
                return true;
            }
        }
        return false;
    }
}

