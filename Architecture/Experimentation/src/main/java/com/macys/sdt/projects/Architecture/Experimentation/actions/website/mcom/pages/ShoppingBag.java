package com.macys.sdt.projects.Architecture.Experimentation.actions.website.mcom.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;

/**
 * Created by Abhishek on 5/25/2017.
 */

public class ShoppingBag {

    //This method clicks on apply button present on Shopping bag page.
    public static void clickApplyPromoCodeButton() throws Exception{
            Wait.untilElementPresent("ShoppingBagPage.Bag_Shopping_Page_Apply_Button");
            Clicks.click("ShoppingBagPage.Bag_Shopping_Page_Apply_Button");
    }

    //This method clicks on apply button present on Shopping bag page.
    public static void enterPromoCode(String promoCode) throws Exception{
        Wait.untilElementPresent("ShoppingBagPage.Enter_PromoCode_TextBox");
        TextBoxes.typeTextbox("ShoppingBagPage.Enter_PromoCode_TextBox",promoCode);
    }

    //This method verify the Promocode message after user has applied.
    public static boolean verifyPromoCodeApplied(String expectedMessage) throws Exception {
        Wait.untilElementPresent("ShoppingBagPage.PromoCode_Message");
        String actualText = Elements.getText("ShoppingBagPage.PromoCode_Message");
        if (actualText.contains(expectedMessage))
            return true;
        else
            return false;
    }
//This method verify the shopping Bag page is appeared or not.
    public static boolean verifyIsShoppingBagPage() {
        Wait.untilElementPresent("ShoppingBagPage.Shopping_Bag_Page");
        boolean present = Elements.elementPresent("ShoppingBagPage.Shopping_Bag_Page");
        return present;
    }

    //This method verify the Image present on shopping Bag page.
    public static boolean verifyImageOnShoppingBagPage() throws Exception{
        Wait.untilElementPresent("ShoppingBagPage.Image_On_Shopping_Bag_Page");
        boolean present = Elements.elementPresent("ShoppingBagPage.Image_On_Shopping_Bag_Page");
        return present;
    }



}
