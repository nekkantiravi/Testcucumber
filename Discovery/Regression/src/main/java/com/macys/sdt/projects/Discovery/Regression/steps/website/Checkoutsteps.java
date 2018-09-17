package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.SearchBrowseComponentization.steps.website.SearchBrowseFacetNavigation;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.LeftFacet;
import com.macys.sdt.shared.steps.website.CheckoutSteps;
import com.macys.sdt.shared.steps.website.Facets;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Admin on 6/12/2017.
 */
public class Checkoutsteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(Checkoutsteps.class);
    CheckoutSteps cs = new CheckoutSteps();
    SearchBrowseFacetNavigation sbfn=new SearchBrowseFacetNavigation();

    @Given("^I select pick up option for bops item after selecting a store$")
    public void i_select_pick_up_option_for_bops_item_after_selecting_a_store() throws Throwable {
        CheckoutSteps cs = new CheckoutSteps();
        cs.i_select_store_pickup_availability_link_on_bag_page();
        Facets.I_search_for_zipcode_in_bops_facet(macys() ? "94102" : "94102");
        cs.i_select_bops_store_button_in_change_pickup_store_dialog();
        ShopAndBrowse.I_save_close_the_bops_change_store_dialog();
        cs.I_select_pick_up_option_for_bops_item();
        logger.info("Selected pick up option for bops item after selecting available store");
    }

    @Then("^I select random bops facet value and select a random member product$")
    public void i_select_random_bops_facet_value_and_select_a_random_member_product() throws Throwable {
        //Select a random bops facet value. if bops stores are unavailable then change the store and apply facet
        // Feature text: Then I select random bops facet value and select a random member product


        SearchBrowseFacetNavigation sbfn=new SearchBrowseFacetNavigation();
        if (macys()) {
            Clicks.clickRandomElement(LeftFacet.getFacetItems("Free Pick Up In Store"));
        } else {
            LeftFacet.expandFacet("Pick Up In Store");
            if (Elements.elementPresent(Elements.paramElement("left_facet.facet_div", "Pick Up In Store"))) {
                Clicks.clickRandomElement(LeftFacet.getFacetItems("Pick Up In Store"));
            }
            else
            {
                Clicks.click(Elements.findElement("//a[@id='bopsLocation']"));
                Wait.forLoading("left_facet.loading");
                Wait.forPageReady();
                Facets.I_search_for_zipcode_in_bops_facet(macys() ? "94102" : "10022");
                cs.i_select_bops_store_button_in_change_pickup_store_dialog();
                ShopAndBrowse.I_save_close_the_bops_change_store_dialog();
            }
        }
        sbfn.iSelectApplyButtonInBopsFacet();
        CommonUtils.selectRandomProduct(false,false );
        resumePageHangWatchDog();


    }

    @Then("^I select and Apply random bops facet value$")
    public void i_select_and_Apply_random_bops_facet_value() throws Throwable {
      //Select and apply a random bops store to facets and vales
        //bloomingdales
        Wait.isPageLoaded();
        Wait.forPageReady();
        LeftFacet.expandFacet("Pick Up In Store");
        if (Elements.elementPresent(Elements.paramElement("left_facet.facet_div", "Pick Up In Store"))) {
            Clicks.clickRandomElement(LeftFacet.getFacetItems("Pick Up In Store"));
        }
        Clicks.click("left_facet.bops_location");
        Facets.I_search_for_zipcode_in_bops_facet(macys() ? "94102" : "10022");
        logger.info("Store location Entered::10022");
        cs.I_save_close_the_bops_change_store_dialog();
        logger.info("Selected a store after search");
        ShopAndBrowse.I_save_close_the_bops_change_store_dialog();
        sbfn.iSelectApplyButtonInBopsFacet();
    }
}
