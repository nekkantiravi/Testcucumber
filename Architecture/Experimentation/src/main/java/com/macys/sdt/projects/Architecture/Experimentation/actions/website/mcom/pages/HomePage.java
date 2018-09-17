package com.macys.sdt.projects.Architecture.Experimentation.actions.website.mcom.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;

/**
 * Created by Admin on 5/15/2017.
 */
public class HomePage {

    public static boolean verifyHomePageAfterLogIn(){
        boolean isHomePage = false;
        try {
            isHomePage = Elements.elementPresent("homePage.Welcome_Text");
            return isHomePage;
        }catch(Exception e)
        {
            return isHomePage;
        }
    }

    public static void clickSignInLink(){
        String elementName = "home." + "goto_sign_in_link";
        Wait.untilElementPresent(elementName);
        Clicks.click(elementName);
    }
    public static void typeTextInSearchBox(String text){
        String elementName = "homePage." + "SearchBox";
        Wait.untilElementPresent(elementName);
        TextBoxes.typeTextNEnter(elementName, text);
    }

    public static String verifyQuantityAndSearchText(){
        String quantity = null;
        try {
            String elementName = "homePage." + "Search_Product_Count";
            Wait.untilElementPresent(elementName);
            quantity = Elements.findElement(elementName).getText();
            quantity += "&"; //seperator
            elementName = "homePage." + "Search_Search_Keyword";
            quantity += Elements.findElement(elementName).getText();
            return quantity;
        }catch(Exception e)
        {
            return quantity;
        }
    }

    public static boolean verifySubCategoryPage(String subcategoryPageName){
        boolean present = false;
        try{
            present = Elements.anyPresent(Elements.paramElement("home.Dresses_Sub_Category", subcategoryPageName));
            return present;
        } catch (Exception e) {
            return present;
        }
    }

    public static boolean verifyFobListElement(String fobElement ) {
        boolean present = false;
        try {
            present = Elements.anyPresent(Elements.paramElement("home.fobList", fobElement));
            return present;
        } catch (Exception e) {
            return present;
        }
    }
}
