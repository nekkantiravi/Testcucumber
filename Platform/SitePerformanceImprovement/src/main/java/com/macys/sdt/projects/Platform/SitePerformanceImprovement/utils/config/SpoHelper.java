package com.macys.sdt.projects.Platform.SitePerformanceImprovement.utils.config;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



/**
 * Created by yc04026 on 5/5/2017.
 */
public class SpoHelper extends StepUtils {

    private static int scenarioCount = 0;

    public static List<String> getProductIds() throws  Throwable{
        List<String> productIds = new ArrayList<>();
        try{
            Wait.untilElementPresent("productThumbnailPanel.product_thumbnails");
            List<WebElement> thumbnails = Elements.findElements(Elements.element("productThumbnailPanel.product_thumbnails"));
            productIds = thumbnails.stream().map(e ->e.getAttribute("id")).collect(Collectors.toList());
        } catch (NoSuchElementException e){
            Assert.fail("prodcut thumbnails are not displayed" + e);
        }
        return productIds;
    }

    public static void selectProductThumbnail(String prodId) throws Throwable{
                   try {
                Wait.untilElementPresent("productThumbnailPanel.product_thumbnails");
                WebElement ele = getProductDiv(prodId);
                Clicks.click(ele.findElement(By.tagName("a")));
            }
            catch (Exception e){
                Assert.fail("Product thumbnails is not displayed" + e.getMessage());
            }
    }


    public static WebElement getProductDiv(String productId) throws  Throwable{
        WebElement productElement = null;
        if(Elements.elementPresent(By.className("productThumbnail"))){
            List<WebElement> allThumbnails = Elements.findElements(By.className("productThumbnails"));
            for(WebElement ele:allThumbnails){
                if(ele.getAttribute("id").equalsIgnoreCase(productId)){
                    productElement = ele;
                    break;
                }
                else{
                    productElement= null;
                }
                if(productElement == null){
                    Assert.fail("ERROR - APP: Unable to find product id " + productId + " on the page");
                }
            }
        }
        else{
            Assert.fail("No product thumbnail is displayed on the page");
        }
        return productElement;
    }

}

