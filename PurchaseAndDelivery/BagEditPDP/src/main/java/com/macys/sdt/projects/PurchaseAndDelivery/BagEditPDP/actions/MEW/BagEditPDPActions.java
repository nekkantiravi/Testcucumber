package com.macys.sdt.projects.PurchaseAndDelivery.BagEditPDP.actions.MEW;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BagEditPDPActions extends StepUtils {

    public static List<WebElement> getColorSwatch() {

        return Elements.findElements("BagEditPDP.product_color_swatch");
    }


    public static String getSelectedColorName(){
        return Elements.getText("BagEditPDP.product_color_name");
    }


    public static void selectAnotherColorSwatch(){
        List<WebElement> colorSwatch = getColorSwatch();

        for(WebElement singleColor : colorSwatch){
            String selected = singleColor.getAttribute("aria-checked");
            if(selected != null && selected.equalsIgnoreCase("false")){
                Clicks.click(singleColor);
                break;
            }
        }
    }

    public static boolean isAnotherProductAdded(){
        List<WebElement> bag_itens = Elements.findElements("shopping_bag.item_title");

        if(bag_itens.size() > 1) {
            for (WebElement item : bag_itens) {
                bag_itens.remove(item);//remove first product from the list of products

                String product_name = item.getText();//get product name

                for (WebElement item2 : bag_itens){//search if on the list of products exists another item with the same name
                    if (item2.getText().equals(product_name))
                        return true;
                }
            }
        }
        return false;
    }

    public static String getProductName(){
        return Elements.getText("product_display.product_name");

    }

    public static boolean isProductInBag(String productName){
        List<WebElement> bag_itens;

        if (Elements.elementPresent("shopping_bag.item_title")) {
            bag_itens = Elements.findElements("shopping_bag.item_title");
        }else{
            bag_itens = Elements.findElements("shopping_bag.item_names");
        }

        if(bag_itens.isEmpty())
            return false;
        else{

            for (WebElement item : bag_itens) {

                if(isSameProductName(item.getText(), productName))
                    return true;
            }
            return false;
        }

    }

    /***
     * Some time we may have the same product but the name description in PDP is not the same as the one in Bag page.
     * Due to that, this function splits the name retrived from PDP and check if all words exists in the product name
     * from Bag page.
     * @param product_bag_name - the product name that is currently in the bag
     * @param product_name - the product name that we retrived from PDP
     * @return
     */
    public static boolean isSameProductName(String product_bag_name, String product_name){
        String [] splited_name = product_name.split(" ");

        for (int i = 0; i < splited_name.length; i++){
            if (!product_bag_name.contains(splited_name[i]))
                return false;
        }
        return true;
    }

}
