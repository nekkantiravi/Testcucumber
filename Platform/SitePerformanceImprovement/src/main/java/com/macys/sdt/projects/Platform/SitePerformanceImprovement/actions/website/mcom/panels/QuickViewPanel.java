package com.macys.sdt.projects.Platform.SitePerformanceImprovement.actions.website.mcom.panels;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

/**
 * Created by YC04026 on 2/21/2017.
 */
public class QuickViewPanel extends StepUtils {

    public static String prodDescription = null;
    public static String salePrice;
    public static String promotionsFromGrid;

    //Method to get product and its details
    public static WebElement selectRandomProduct(){

        WebElement randomProdId=null;
        try {
           randomProdId = Elements.getRandomElement(Elements.element("search_result.product_thumbnail_wrapper"));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("Unable to select a random product");
        }
        return  randomProdId;
    }
    //method to get the description of the randomly selected thumbnail from grid
    public static String getProdDescription(WebElement randomProduct){
        try {
            prodDescription = randomProduct.findElement(By.className("shortDescription")).getText();
            System.out.println("Description of the product ---:: " + prodDescription);
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail("Unable to get description of the product");
        }
        return prodDescription;
    }
    //method to get the Sale price of the randomly selected thumbnail from grid
    public static void getSalePrice(WebElement randomProduct) {
        String price = null;
        try {
            List<WebElement> allPrices = randomProduct.findElements(By.className("colorway-price"));
            for (WebElement ele : allPrices) {
                price = ele.getText();
                if ((price.contains("Sale")) || (price.contains("Now")) || (price.contains("Your Choice")) || (price.contains("")))
                    salePrice = ele.getText();
             }
        System.out.println("Sale price of the product ---:: " + salePrice);
    }catch( Exception e){
        e.printStackTrace();
        Assert.fail("Unable to get the price");
    }
    }
    //method to get the available promotion on the product
    public static void getPromotion(WebElement randomProduct){
        boolean badge_text;
        try {
            badge_text = randomProduct.findElement(By.className("badgeHeader")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("No promotion is available for Product ID " + randomProduct.getAttribute("id"));
            badge_text = false;

        }
        if(badge_text) {
                List<WebElement> promotions = randomProduct.findElements(By.className("badge"));
            for(WebElement ele:promotions) {
                promotionsFromGrid = ele.findElement(By.className("badgeHeader")).getText();
                    System.out.println("Available promotion on this product ::" + promotionsFromGrid);
               }

        }
    }

    //method to hover over a random thumbnail and click on "QUICKVIEW"
    public static void selectQuickView(WebElement randomProduct){
        try{
            Wait.forPageReady();
            Clicks.hoverForSelection(randomProduct.findElement(By.className("thumbnailImage")));
            Wait.untilElementPresent(By.id("quickViewLauncher"));
            Clicks.click(By.id("quickViewLauncher"));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("Unable to click on QuickView of product: "+prodDescription);
        }
    }

    //method to get item title from quick view
    public static String getItemTitleQV(){
        String qvProductTitle = null;
        qvProductTitle = Elements.findElement(By.className("productHeaderPDPLink")).getText();
        System.out.println("Product description from QV :: "+qvProductTitle);
        return  qvProductTitle;
    }

    //method to get item price from quick view
    public static String getItemPrice(){
        String priceQV = null;
        if(Elements.findElement(By.className("qvSalePrice")).isDisplayed()) {
            priceQV = Elements.findElement(By.className("qvSalePrice")).getText();
        }else if(Elements.findElement(By.className("qvPrice")).isDisplayed()){
            priceQV = Elements.findElement(By.className("qvPrice")).getText();
        }else  if(Elements.findElement(By.className("qvPriceSaleRange")).isDisplayed()){
            priceQV = Elements.findElement(By.className("qvPriceSaleRange")).getText();
        }
        System.out.println("Price from qv :: "+ priceQV);
        return  priceQV;
    }

        //Get QV promotion value

    public static String getQVpromotions(){
        String qvPromotion=null;
        boolean isPromoDisplaying = false;
        try {
             isPromoDisplaying = Elements.findElement(By.className("badgeHeader")).isDisplayed();
           } catch (Exception e) {
            isPromoDisplaying = false;
        }

        if(isPromoDisplaying) {
            qvPromotion = Elements.findElement(By.className("badgeHeader")).getText();
            System.out.println("Available promotion on this product ::" + qvPromotion);
            }
        return  qvPromotion;
        }

    //Getting QV details of Member products
    public static HashMap<String, Boolean> getMemberQVattributes(String mode){
        boolean prodName = false;
        boolean closeButton = false;
        boolean addToBag = false;
        boolean addToList = false;
        boolean mainImage = false;
        boolean priceDetails = false;
        boolean qvBottomPanel = false;
        boolean childDivider = false;
        boolean addToRegistry = false;

        HashMap<String, Boolean> qvDetails = new HashMap<>();

        try{
            prodName = Elements.findElement(By.className("productHeaderPDPLink")).isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        try{
            closeButton = Elements.findElement("quick_view.quick_view_close_dialog").isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        try{
            mainImage = Elements.findElement(By.className("main-view-holder")).isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        try{
            priceDetails = Elements.findElement(By.className("qvProductPrices")).isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }

        if(isMasterMember().equalsIgnoreCase("member")) {
            try {
                addToBag = Elements.findElement(By.id("qvAddToBag")).isDisplayed();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
            try{
                if(!mode.equalsIgnoreCase("iship")){
                    addToList = Elements.findElement(By.id("qvAddToList")).isDisplayed();
                    qvDetails.put("addToList", addToList);
                }
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
            qvDetails.put("addToBagButton", addToBag);
        }

        if(isMasterMember().equalsIgnoreCase("member")&&(mode.equalsIgnoreCase("registry"))){
            try{
                addToRegistry = Elements.findElement(By.className("qvAddToRegistry")).isDisplayed();
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
            qvDetails.put("addToRegsitryButton", addToRegistry);
        }

        if(isMasterMember().equalsIgnoreCase("master")){
            try{
                qvBottomPanel = Elements.findElement(By.className("qvBottomPanel")).isDisplayed();
                qvDetails.put("qvBottomSection", qvBottomPanel);
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
            try{
                childDivider = Elements.findElement(By.className("masterDivider")).isDisplayed();
                qvDetails.put("masterChildSection", childDivider);
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }


        }else {
            qvBottomPanel = true;
            qvDetails.put("masterChildSection", qvBottomPanel);
            childDivider = true;
            qvDetails.put("masterChildSection", childDivider);
        }


        qvDetails.put("prodName", prodName);
        qvDetails.put("closeButton", closeButton);
        qvDetails.put("mainImage", mainImage);
        qvDetails.put("priceDetails", priceDetails);

        return qvDetails;
    }

    //Checking QV for Member product
    public static String isMasterMember(){
        String productType = null;
        boolean masterMember = false;
        try{
            masterMember = Elements.findElement(By.className("fullProductDetailsLink")).isDisplayed();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(Elements.findElement(By.className("fullProductDetailsLink")).getText().contains("SEE COLLECTION DETAILS")){
            productType = "master";
        }
        else{
            Elements.findElement(By.className("fullProductDetailsLink")).getText().contains("SEE FULL PRODUCT DETAILS");
            productType = "member";
        }
        return productType;
    }

    //Getting height/ width of main image
    public static HashMap<String, String> getHightWidthQVimage() throws Throwable{
        HashMap<String, String> qvImagePxDetails = new HashMap<>();

        String imageHeightPxl = Elements.findElement(By.className("main-view")).getAttribute("style").split(";")[0].split(":")[1].trim();

        String imageWidthPxl = Elements.findElement(By.className("main-view")).getAttribute("style").split(";")[1].split(":")[1].trim();

        qvImagePxDetails.put("PixelHeight", imageHeightPxl);
        qvImagePxDetails.put("PixelWidth", imageWidthPxl);
        return qvImagePxDetails;
    }

    //method to select random quickview in Shopping bag page
    public static WebElement selectRandomProductFromProsPanel(){
        WebElement randomProductId = null;
        try{
            randomProductId = Elements.getRandomElement(Elements.element("home.shopping_page_vertical_panel"));
            System.out.println("--- found ---"+ randomProductId);
        }
        catch (Exception e){
            e.printStackTrace();
            Assert.fail("Unable to select a random product from the vertical panel");
        }
        return  randomProductId;
    }

    //method to hover over a random thumbnail and click on "QUICKVIEW" in shopping bag page
    public static void selectQuickViewInProsPanel(WebElement randomSelectedProduct){
        try{
            Navigate.scrollPage(0, 10);
            Wait.forPageReady();
            Clicks.hoverForSelection(randomSelectedProduct.findElement(By.className("thumbnailImage")));
            Wait.untilElementPresent(By.id("quickViewLauncher"));
            Clicks.click(By.id("quickViewLauncher"));
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail("Unable to click on QuickView of product: ");
        }
    }



}
