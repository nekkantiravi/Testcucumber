package com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.mcom.panels;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.interactions.Clicks.click;
import static com.macys.sdt.framework.interactions.Clicks.clickIfPresent;
import static com.macys.sdt.framework.interactions.Elements.*;


/**
 * Created by yc04026 on 10/6/2016.
 */
public class SpoRecentlyViewedPanel extends StepUtils{
    private static List<String> recentlyViewed;

    public static boolean isDisplayed() {
        scrollToLazyLoadElement("spoRecentlyViewed.thumbnail_wrapper");
        recentlyViewed = findElements("spoRecentlyViewed.thumbnail_wrapper").stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return elementPresent("spoRecentlyViewed.thumbnail_wrapper");
    }

    public static boolean isEditVisible() {
        return elementPresent("spoRecentlyViewed.edit_button");
    }

    public static List<String> getRecentlyViewed() {
        return findElements("spoRecentlyViewed.thumbnail_wrapper").stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

    }

    public static List<String> getOldRecentlyViewed() {
        return recentlyViewed;
    }

    public static void updateProducts() {
        recentlyViewed = getRecentlyViewed();
    }


    // This method is to return the count of product thumbnails present at a time in Rvi panel.
    public static long getRecentlyViewedCurrentCount() {
        long countCurrentSet =findElement("spoRecentlyViewed.rvi_currentset").findElements(By.className("thumb")).stream().filter(WebElement::isDisplayed).count();
        return  countCurrentSet;
    }

    // This method return the prod_ids of the items available in the Rvi container.
    public static List<String> getProductIds(){
        List<WebElement> rvi_details = findElements("spoRecentlyViewed.rvi_thumbnails_new");
        ArrayList prod_ids=new ArrayList();
        for( WebElement e : rvi_details){
            prod_ids.add(e.getAttribute("data-choiceid"));
        }
        return prod_ids;
    }

    // This method is to return the count of product thumbnails present at a time in Old Rvi panel.
    public static List<String> getProductIdsInRegistryRvi(){
        List<WebElement> rvi_details = findElements(By.className("rviProductThumbnail"));
        ArrayList prod_ids=new ArrayList();
        System.out.println(rvi_details);
        for( WebElement e : rvi_details){

            prod_ids.add(e.getAttribute("id").split("_")[1]);

        }
        return prod_ids;
    }

    // This method return the prod_ids of the items available in the Old Rvi container.
    public static List<String> getProductIdsInRegistryRviEntire(){
        ArrayList prod_ids=new ArrayList();
        clickIfPresent("spoRecentlyViewed.scroll_right");
        List<WebElement> rvi_details = findElements(By.className("rviProductThumbnail"));
        for( WebElement e : rvi_details){
            prod_ids.add(e.getAttribute("id").split("_")[1]);
        }
        return prod_ids;
    }

    // This method return the prod_id of the items which going to remove from Rvi panel.
    public static String itemToBeRemovedRegistry(){
        return getProductIdsInRegistryRvi().get(0);
    }


    // This method is to verify whether the navigations arrows are visible or not.
    public static boolean isNavigationArrowsVisible(){
        return elementPresent("spoRecentlyViewed.scroll_right");
    }

    // This method is to perform click on Edit and Done in Rvi panel.
    public static void clickEditDone(){
        clickIfPresent("spoRecentlyViewed.edit_button");
    }

    // This will return the item which going to remove from Rvi panel.
    public static String itemToBeRemoved(){
        return getProductIds().get(0);
    }

    // This method will perform click in remove button in Rvi panel
    public static void clickRemoveButton(){
        click(findElements("spoRecentlyViewed.remove_button").get(0));
    }

    public static List<String> getProductIdsWithAlternateImages(){
        ArrayList<String> prodIds = new ArrayList<>();
        List<WebElement> thumbnails = Elements.findElements(By.className("productThumbnail"));
        for (WebElement ele : thumbnails)
        {
            try {
                Clicks.hoverForSelection(ele);
                if (ele.findElement(By.className("fullColorOverlayOff")).findElements(By.tagName("span")).get(2).getAttribute("id").contains("productLevelAltImages")) {
                    String prodId = ele.getAttribute("id");
                    prodIds.add(prodId);
                    if(prodIds.size()==6)
                        break;
                }
            }
            catch (Exception e){
                Assert.fail("Product with alt images are not available : "+ ele.getAttribute("id"));
            }
        }
        return prodIds;
    }

    public static void selectProductThumbnail(String prodId) {
        try {
            //Wait.untilElementPresent("product_thumbnails.product_thumbnails");
            Wait.untilElementPresent(By.className("productThumbnail"));
            WebElement ele = getProductDiv(prodId);
            Clicks.click(ele.findElement(By.tagName("a")));
        }
        catch (Exception e){
            Assert.fail("Product thumbnails is not displayed" + e.getMessage());
        }
    }

    private static WebElement getProductDiv(String productId){
        WebElement productElement = null;
        if (Elements.elementPresent(By.className("productThumbnail"))){
            List<WebElement> thumbnails = Elements.findElements(By.className("productThumbnail"));
            for (WebElement ele : thumbnails)
            {
                if (ele.getAttribute("id").equalsIgnoreCase(productId)) {
                    productElement = ele;
                    break;
                }
                else
                {
                    productElement = null;
                }
            }
            if (productElement == null){
                Assert.fail("ERROR - APP: Unable to find product id " + productId + " on the page");
            }
        }
        else
        {
            Assert.fail("No product thumbnails is displayed on the page");
        }
        return productElement;
    }


    public static List<Boolean> isalternateImagesAvailable(){
        boolean altImages = false;
        List<Boolean> alt=new ArrayList<>();
        List<WebElement> altRvi = Elements.findElements(By.className("rviProductThumbnail"));
        for (WebElement sl : altRvi) {
            try{
                Clicks.hoverForSelection(sl);
                altImages=sl.findElements(By.tagName("span")).get(1).isDisplayed();
                alt.add(altImages);
            }catch (org.openqa.selenium.NoSuchElementException e){
                System.out.println(e.getMessage());
            }
        }
        return alt;
    }
}
