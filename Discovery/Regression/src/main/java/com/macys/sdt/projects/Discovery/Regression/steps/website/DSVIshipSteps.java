package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by gzanin on 6/7/17.
 */
public class DSVIshipSteps {

    private static final Logger logger = LoggerFactory.getLogger(GlobalNav.class);

    private PageNavigation pageNavigation = new PageNavigation();

    private String countryCode() {
        return Elements.getElementAttribute("header_and_footer.country_code_flag", "href");
    }

    @Then("^I verify the Iship FOBs in below table$")
    public void iVerifyTheRegistryFOBsInBelowTable(Map<String, String> list) throws Throwable {
        for (Map.Entry<String, String> entry : list.entrySet()) {
            String category = entry.getKey();
            String subcategory = entry.getValue();
            logger.info("Starting verification of:" + category + " catsplash");
            pageNavigation.I_navigate_to_category_page(category);
            verifyBasicAttributesOfIshipCatSplashPage();
            logger.info("Starting verification of:" + subcategory + " subcategory");
            navigateToSubCategoryFromCatSplashPage(subcategory);
            verifyBasicAttributesOfIshipBrowsePage();
        }
    }

    private void verifyBasicAttributesOfIshipBrowseOrCatSplashPage() {
        if (StepUtils.onPage("category_browse"))
            verifyBasicAttributesOfIshipBrowsePage();
        else
            verifyBasicAttributesOfIshipCatSplashPage();
    }

    private void navigateToSubCategoryFromCatSplashPage(String strSubcategory) {
        List<WebElement> subcategories = Elements.findElements("left_navigation_category.subcategory");
        for (WebElement subcategory : subcategories) {
            if (subcategory.getText().toLowerCase().equals(strSubcategory.toLowerCase())) {
                Clicks.click(subcategory);
                Wait.forPageReady();
                break;
            }
        }
    }

    private void clickOnDifferentSubCatgoryFromFlyoutMenuFor(String category, String subcategory) throws Throwable {
        pageNavigation.I_mouse_over_category_from_top_navigation(category);
        WebElement anotherSubcategory = Elements.findElement("header.open_flyout").findElements(By.tagName("a")).stream().filter(a -> !a.getText().equals(subcategory)).findFirst().get();
        logger.info("Starting verification of:" + anotherSubcategory.getText() + " subcategory");
        anotherSubcategory.click();
        Wait.forPageReady();
    }

    private void verifyBasicAttributesOfIshipBrowsePage() {
        Assert.assertFalse("my account link is displayed", Elements.elementPresent("header.goto_my_account_link"));
        Assert.assertTrue("Category menu is not displayed", Wait.untilElementPresent("header.category_menu"));
        Assert.assertTrue("Main footer is not displayed", Wait.untilElementPresent("footer.footer_menu_section"));
        Assert.assertTrue("Facet container is not displayed", Wait.untilElementPresent("facet_chart.facets_panel"));

        Integer productCount = Integer.parseInt(Elements.getText("pagination.product_count_span").replace(" items", ""));
        if (productCount > 180)
            Assert.assertTrue("Pagination option is not available with " + productCount + " products in the page", hasPagination());
        if (productCount > 90 && productCount <= 180) {
            Clicks.click("pagination.view_180");
            Wait.untilElementNotPresent("pagination_top.goto_next_page_via_arrow");
            Assert.assertTrue("Pagination option is available with " + productCount + " products in the page", hasPagination());
        }
        if (productCount <= 90)
            Assert.assertFalse("Pagination option is available with " + productCount + " products in the page", hasPagination());

        Assert.assertTrue("Sort by option is not available", Wait.untilElementPresent("pagination.sort_by"));
        Assert.assertFalse("Iship currency displayed as USD", Elements.getText("header.iship_currency").equals("USD"));
    }

    private boolean hasPagination() {
        return Elements.elementPresent("pagination_top.goto_next_page_via_arrow");
    }

    private void verifyBasicAttributesOfIshipCatSplashPage() {
        Assert.assertTrue("Left navigation container is not present in CatSplash page", Wait.untilElementPresent("left_facet.left_navigation_container"));
        Assert.assertFalse("Country code display as US", countryCode().contains("/US."));
        Assert.assertTrue("Category menu is not displayed", Wait.untilElementPresent("header.category_menu"));
        Assert.assertTrue("Main footer is not displayed", Wait.untilElementPresent("footer.footer_menu_section"));
        Assert.assertTrue("Left navigation container is not display", Wait.untilElementPresent("left_facet.left_navigation_container"));
        Assert.assertFalse("my account link is displayed", Elements.elementPresent("header.goto_my_account_link"));
    }


    @Then("^I verify the basic attributes of iship browse or cat splash page$")
    public void i_verify_the_basic_attributes_of_iship_browse_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        verifyBasicAttributesOfIshipBrowseOrCatSplashPage();
    }

}
