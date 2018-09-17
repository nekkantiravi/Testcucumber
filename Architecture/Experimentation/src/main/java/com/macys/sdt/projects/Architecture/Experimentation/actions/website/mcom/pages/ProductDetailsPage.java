package com.macys.sdt.projects.Architecture.Experimentation.actions.website.mcom.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;


/**
 * Created by Admin on 5/17/2017.
 */
public class ProductDetailsPage {

    public static boolean verifyIsPdpPage() {
        boolean present = Elements.elementPresent("product_display.product_description_block");
        return present;
    }

    public static void verifyPdpProductName() {
        Elements.elementShouldBePresent("product_display.product_title");
    }

    public static void verifyPdpAddToBagButton() {
        Elements.elementShouldBePresent("product_display.add_to_bag_button");
    }

    public static void verifyPdpAddToListButton() {
        Elements.elementShouldBePresent("product_display.add_to_wishlist_image");
    }
//This method verify the Attributes present on Bag Page.
    public static void verifyBagPageAttribute() {
        Wait.untilElementPresent("ProductPage.Bag_Page");
        Wait.untilElementPresent("ProductPage.Checkout_Button");
        Elements.elementShouldBePresent("ProductPage.Bag_Page");
        Elements.elementShouldBePresent("ProductPage.Go_Button");
        Elements.elementShouldBePresent("ProductPage.Checkout_Button");
    }

    public static void clickOnGoBackButton() {
        Wait.untilElementPresent("ProductPage.Go_Button");
        Clicks.click("ProductPage.Go_Button");
    }

    public static void clickOnCheckOutButton() {
        Wait.untilElementPresent("ProductPage.Checkout_Button");
        Clicks.click("ProductPage.Checkout_Button");
    }

    //This method return the Product name shown in the PDP page else it returns null when product name is not found.
    public static String getProductName() {
        String name = null;
        try {
            Wait.untilElementPresent("ProductPage.Product_Name");
            name = Elements.getText("ProductPage.Product_Name");
            return name;
        } catch (Exception e) {
            return name;
        }
    }

    // This method return true if "PleaseeSelectSizeBox' is present on PDP page else it return false.
    public static boolean verifyPleaseSelectSizeBox() {
        boolean text = false;
        try {
            Wait.untilElementPresent("ProductPage.PleaseSelectSizeBox");
            text = Elements.elementPresent("ProductPage.PleaseSelectSizeBox");
            return text;
        } catch (Exception e) {
            return text;
        }
    }

    // This method return true if Image is zoomed on mouse hovering on PDP page else it return false.
    public static boolean verifyMouseHoverImage() {
        boolean isZoomed = false;
        try {
            Wait.untilElementPresent("ProductPage.PDP_Image");
            Clicks.hoverForSelection("ProductPage.PDP_Image");
            Clicks.click("ProductPage.PDP_Image");
            Wait.forPageReady();
            isZoomed = Elements.elementPresent("ProductPage.PDP_Image_Zoom");
            return isZoomed;
        } catch (Exception e) {
            return isZoomed;
        }
    }

    //This method return the image name present on PDP page else it will return null.
    public static String getImageName(){
        String image_Name=null;
        try{
            Wait.untilElementPresent("ProductPage.PDP_Image");
            image_Name  = Elements.getElementAttribute("ProductPage.PDP_Image", "src");
            return image_Name;
        }catch(Exception e){
            return image_Name;
        }
    }

    //This  method return finalCost present on PDP page else it will return null.
    public static String getFinalCost(){
        String final_Cost= null;
        try {
            Wait.untilElementPresent("ProductPage.Product_Name");
            final_Cost = Elements.getText("ProductPage.product_Final_Cost");
            return final_Cost;
        }catch(Exception e)
        {
            return final_Cost;
        }
    }

    //This  method return PromoCode present on PDP page else it will return null.
    public static String getPromoCode(){
        String promoCode= null;
        try {
            Wait.untilElementPresent("ProductPage.Product_Name");
            promoCode = Elements.getText("ProductPage.Promo_Code");
            return promoCode;
        }catch(Exception e)
        {
            return promoCode;
        }
    }

    //This method return the Sale price present on PDP page else it will return -1.0 as sale price.
    public static float getSalePrice(){
        float salePriceOnPDPPage= -1.0f;
        try {
            Wait.untilElementPresent("ProductPage.Sale_Price");
            String salePrice = Elements.getText("ProductPage.Sale_Price");
            String promocode_SaleArray[] = salePrice.split("\\$");
            salePriceOnPDPPage= Float.parseFloat(promocode_SaleArray[1]);
            return salePriceOnPDPPage;
        }catch(Exception e)
        {
            return salePriceOnPDPPage;
        }
    }
    //This method return the With Offer price present on PDP page else it will return null.
    public static float getOfferPrice(){
        float offerPriceOnPDPPage= -1.0f;
        try {
            Wait.untilElementPresent("ProductPage.With_Offer_Price");
            String offerPrice = Elements.getText("ProductPage.With_Offer_Price");
            String promocode_WithOfferArray[] = offerPrice.split("\\$");
            offerPriceOnPDPPage= Float.parseFloat(promocode_WithOfferArray[1]);
            return offerPriceOnPDPPage;
        }catch(Exception e)
        {
            return offerPriceOnPDPPage;
        }
    }

    //This method Clicks on Add to Bag button.
    public static void clickAddToBagButton() throws Exception
    {
           Wait.untilElementPresent("product_display.add_to_bag_button");
            Clicks.click("product_display.add_to_bag_button");

    }
}

