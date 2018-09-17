package com.macys.sdt.projects.Stores.Checkout.utils;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.Stores.Checkout.steps.website.CheckoutSteps;
import com.macys.sdt.projects.Stores.Checkout.steps.website.ProductDiscoverySteps;

import static com.macys.sdt.framework.interactions.Clicks.hoverForSelection;

public class ProductDiscoveryUtils {
    // Put code here that doesn't necessarily apply to just one page or step.
    // You can also use it for more general utility things, like interacting
    // with a database or talking to a service.


    /**
     * Adds the given number of items to the shopping bag
     * via the product discovery page
     *
     * @param count number of items to add to bag
     */


    public static void addWebIdToBag(int count) throws Throwable {
        ProductDiscoverySteps productDiscoverySteps = new ProductDiscoverySteps();
        productDiscoverySteps.i_press_the_search_icon();
        productDiscoverySteps.i_can_see_the_search_overlay();
        productDiscoverySteps.i_uncheck_this_store_only();
        productDiscoverySteps.i_search_for_on_product_discovery("51153819872");
        hoverForSelection("pdp.send_radio");
        Clicks.click("pdp.send_radio");



        for (int i = 0; i < count; i++) {
            productDiscoverySteps.i_can_see_the_product_card();
            hoverForSelection("pdp.add_to_bag");
            productDiscoverySteps.i_press_the_Add_to_bag();
            Thread.sleep(1000);
            if(Elements.elementPresent("pdp.pdp_spinner")){
                Wait.secondsUntilElementNotPresent("pdp.pdp_spinner", 20);

            }
            Wait.secondsUntilElementNotPresent("bag_screen.toast_body", 10);


        }


    }

    public static void AddItemtotheBagOnProductDiscovery(String Item, String TransType) throws Throwable {
        switch (TransType) {
            case "Take":
                //Input an If statement verifying that the number in this store is greater than 0.. If not takes unavail
                ProductDiscoverySteps productDiscoverySteps = new ProductDiscoverySteps();
                productDiscoverySteps.i_press_the_search_icon();
                productDiscoverySteps.i_can_see_the_search_overlay();
                productDiscoverySteps.i_uncheck_this_store_only();
                productDiscoverySteps.i_search_for_on_product_discovery(Item);
                Wait.forPageReady();
                CheckoutSteps checkout = new CheckoutSteps();
                checkout.iScrollDownInsideTheBag();
                hoverForSelection("pdp.add_to_bag");
                if(Elements.elementPresent("pdp.pdp_spinner")){
                    Wait.secondsUntilElementNotPresent("pdp.pdp_spinner", 20);

                }
                hoverForSelection("pdp.add_to_bag");
                productDiscoverySteps.i_press_the_Add_to_bag();
                break;
            case "Send":
                //Input an If statement verifying that the number in this store is greater than 0.. If not takes unavail
                ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                productDiscoveryStep.i_press_the_search_icon();
                productDiscoveryStep.i_can_see_the_search_overlay();
                productDiscoveryStep.i_uncheck_this_store_only();
                productDiscoveryStep.i_search_for_on_product_discovery(Item);
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("pdp.add_to_bag", 20);
                CheckoutSteps checkout2 = new CheckoutSteps();
                checkout2.iScrollDownInsideTheBag();
                hoverForSelection("pdp.add_to_bag");
                Elements.elementShouldBePresent("pdp.send_radio");
                Clicks.click("pdp.send_radio");

                hoverForSelection("pdp.add_to_bag");
                if(Elements.elementPresent("pdp.pdp_spinner")){
                    Wait.secondsUntilElementNotPresent("pdp.pdp_spinner", 20);

                }
                productDiscoveryStep.i_press_the_Add_to_bag();
                break;
            case "DisabledSend":
                //Input an If statement verifying that the number in this store is greater than 0.. If not takes unavail
                ProductDiscoverySteps productDiscoveryStepdisab = new ProductDiscoverySteps();
                productDiscoveryStepdisab.i_press_the_search_icon();
                productDiscoveryStepdisab.i_can_see_the_search_overlay();
                productDiscoveryStepdisab.i_uncheck_this_store_only();
                productDiscoveryStepdisab.i_search_for_on_product_discovery(Item);
                Wait.forPageReady();
                CheckoutSteps checkout1 = new CheckoutSteps();
                checkout1.iScrollDownInsideTheBag();
                hoverForSelection("pdp.add_to_bag");
                Wait.secondsUntilElementPresent("pdp.send_radio", 20);

                hoverForSelection("pdp.add_to_bag");
                if(Elements.elementPresent("pdp.pdp_spinner")){
                    Wait.secondsUntilElementNotPresent("pdp.pdp_spinner", 20);

                }
                Wait.secondsUntilElementPresent("pdp.send_radio", 20);
                hoverForSelection("pdp.send_radio");
                Clicks.click("pdp.send_radio");
                hoverForSelection("pdp.add_to_bag");
                productDiscoveryStepdisab.i_press_the_Add_to_bag();
                break;

        }

        }

    }













