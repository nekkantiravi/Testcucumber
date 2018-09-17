package com.macys.sdt.projects.Selection.Registry.steps.website.bcom;

import com.macys.sdt.framework.interactions.*;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class EmptyCategoryRecommendationSteps {

    private String product;

    @And("^I should (see|not see) Empty Category Recommendation displayed$")
    public void iShouldSeeEmptyCategoryRecommendationDisplayed(String option) throws Throwable{
        Wait.forPageReady();
        if(option.equals("see"))
            Assert.assertTrue("ERROR: Empty Category Recommendation section is not displayed", Elements.elementPresent("registry_bvr.empty_category_rec_section"));
        else
            Assert.assertFalse("ERROR: Empty Category Recommendation section is displayed", Elements.elementPresent("registry_bvr.empty_category_rec_section"));

    }

    @And("^I sort by \"(Price High to Low|Price Low to High|Category)\"$")
    public void iSortByPriceHighToLow(String option) throws Throwable {
        Wait.forPageReady();
        int index = 0; //option equals Category

        if(option.equals("Price High to Low"))
            index = 1;
        else if(option.equals("Price Low to High"))
            index=2;

        if (Elements.anyPresent("registry_bvr.sort_by_dropdown")) {
            DropDowns.selectByIndex("registry_bvr.sort_by_dropdown", index);
        }
    }


    private String categoryRecTitle;

    @And("^I add recommended product to registry$")
    public void iAddRecommendedProductToRegistry() throws Throwable{
        Wait.forPageReady();
        categoryRecTitle = Elements.getText("registry_bvr.category_recommendation_titles");
        Clicks.clickIfPresent("registry_bvr.add_to_registry_button");
    }

    @And("^I should see added product$")
    public void iShouldSeeAddedProduct() throws Throwable{
        Assert.assertTrue("ERROR: Product was not added to Registry", Elements.elementPresent("registry_bvr.green_check_product_added"));
    }

    @And("^I should (see|not see) the recommended items with same category as product recently added$")
    public void iShouldSeeTheRecommendedItemsWithSameCategoryAsProductRecentlyAdded(String option) throws Throwable{
        Wait.forPageReady();
        if(option.equals("see"))
            Assert.assertTrue("ERROR: Recommended Category not displayed: " + categoryRecTitle, categoryRecTitle.equals(Elements.getText("registry_bvr.category_recommendation_titles")));
        else
            Assert.assertFalse("ERROR: Recommended Category still being displayed: "+categoryRecTitle, categoryRecTitle.equals(Elements.getText("registry_bvr.category_recommendation_titles")));
    }

    @And("^I refresh the page$")
    public void iRefreshThePage() throws Throwable{
        Navigate.browserRefresh();
        Wait.forPageReady();
    }

    @And("^I click on product thumbnail from recommendation section$")
    public void iClickOnProductThumbnailFromRecommendationSection() throws Throwable{
        Clicks.clickIfPresent("registry_bvr.product_thumbnail");
    }

}
