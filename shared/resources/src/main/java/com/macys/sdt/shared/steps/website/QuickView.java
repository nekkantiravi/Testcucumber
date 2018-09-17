package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

import static com.macys.sdt.shared.utils.CommonUtils.quickViewRandomProduct;

public class QuickView extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(QuickView.class);

    /**
     * Selects a random quick view dialog on browse page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select a random product in a quickview dialog$")
    public void I_select_a_random_product_in_a_quickview_dialog() throws Throwable {
        if (macys()) {
            // tablet resolution
            if (Elements.elementPresent("search_result.product_thumbnail_quickview_tablet")) {
                Clicks.clickRandomElement("search_result.product_thumbnail_quickview_tablet");
            } else {
                pausePageHangWatchDog();
                WebElement thumbnail = Elements.getRandomElement("search_result.product_thumbnail");
                Clicks.hoverForSelection(thumbnail);
                resumePageHangWatchDog();
                if (Wait.untilElementPresent("search_result.product_thumbnail_quickview")) {
                    Clicks.click("search_result.product_thumbnail_quickview");
                    return;
                }

                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            }
        } else {
            Clicks.randomJavascriptClick("search_result.product_thumbnail_quickview");
            Assert.assertTrue("ERROR-ENV: Quick view Dialog is not present", Wait.untilElementPresent("quick_view.quick_view_product_dialog"));
        }
    }

    /**
     * Opens a quick view dialog of a product matching the given conditions
     *
     * @param prod_type "member" or "master" or "member_alternate_image" or "master_alternate_image"
     * @param hasRating include for a product with customer ratings
     * @throws Throwable if any exception occurs
     */
    @When("^I quick view a random (member|master|member_alternate_image|master_alternate_image) product(?: with (customer ratings))?$")
    public void I_quick_view_a_random_product(String prod_type, String hasRating) throws Throwable {
        boolean found = false;
        int i = 0, max = 5;

        while (!found && i++ < max) {
            quickViewRandomProduct(hasRating != null, prod_type.toLowerCase().contains("master"));
            switch (prod_type.toLowerCase()) {
                case "member":
                    found = ProductDisplay.isMasterMemberQuickViewDialog();
                    break;
                case "master":
                    found = !ProductDisplay.isMasterMemberQuickViewDialog();
                    break;
                case "member_alternate_image":
                    found = Elements.elementPresent("quick_view.quick_view_alt_images")
                            && ProductDisplay.isMasterMemberQuickViewDialog();
                    break;
                case "master_alternate_image":
                    found = Elements.elementPresent("quick_view.quick_view_alt_images")
                            && !ProductDisplay.isMasterMemberQuickViewDialog();
                    break;
            }
            if (!found) {
                Clicks.clickIfPresent("quick_view.quick_view_close_dialog");
            }
        }
        if (!found) {
            Assert.fail("Failed to find " + prod_type + " product after " + max + " tries.");
        }
    }

    /**
     * Selects "see full product details" from a quick view dialog
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I select 'see full product details' link from the quickview dialog$")
    public void I_select_see_full_product_details_link_from_the_quickview_dialog() throws Throwable {
        Thread.sleep(25000);
        Assert.assertTrue("ERROR - ENV: Unable to find see full product info link", Elements.findElement("quick_view.quick_view_see_full_details").isDisplayed());
        Clicks.click("quick_view.quick_view_see_full_details");
        new PageNavigation().I_should_be_redirected_to_PDP_page();
    }

    /**
     * Closes a visible quick view dialog
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I close the quickview dialog$")
    public void I_close_the_quickview_dialog() throws Throwable {
        Wait.secondsUntilElementPresentAndClick("quick_view.quick_view_close_dialog", 2);
    }

    /**
     * Adds a currently visible quick view product to bag
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add the item to the bag from quick view$")
    public void I_add_the_item_to_the_bag_from_quick_view() throws Throwable {
        if (bloomingdales()) {
            Wait.secondsUntilElementPresent("quick_view.quick_view_product_size_list", 50);
            if (Elements.elementPresent("quick_view.quick_view_product_size_list")) {
                List<WebElement> sizes = Elements.findElement("quick_view.quick_view_product_size_list")
                        .findElements(By.xpath("li[not(contains(@class, \"disabled\"))]"));
                WebElement prodSize = sizes.get(new Random().nextInt(sizes.size()));
                Clicks.click(prodSize.findElement(By.xpath("span")));
            }
        } else {
            if (Elements.elementPresent("quick_view.quick_view_product_size")) {
                DropDowns.selectByIndex("quick_view.quick_view_size_dropdown", 1);
            }
        }
        Clicks.javascriptClick("quick_view.quick_view_product_add_to_bag");
        Wait.untilElementNotPresent("quick_view.quick_view_product_add_to_bag");
    }

    /**
     * Adds a currently visible quick view product to wish list
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add the item to wishlist from QV$")
    public void I_add_the_product_to_wishlist_from_QV() throws Throwable {
        if (macys()) {
            Wait.secondsUntilElementPresent("quick_view.quick_view_size_dropdown", 5);
            DropDowns.selectByIndex("quick_view.quick_view_size_dropdown", 1);
            if (Elements.elementPresent("quick_view.selected_color")) {
                WebElement color = Elements.findElement("quick_view.selected_color");
                while (color.getAttribute("class").contains("disabledOption")) {
                    Clicks.clickRandomElement("quick_view.colorway_swatch");
                    color = Elements.findElement("quick_view.selected_color");
                }
            }
        } else {
            if (Elements.findElement("quick_view.quick_view_product_size_list").isDisplayed()) {
                Clicks.clickRandomElement("quick_view.quick_view_product_size");
            }
        }

        Clicks.click("quick_view.quick_view_product_add_to_wishlist");
        if (bloomingdales() && Elements.elementPresent("quick_view.default_wishlist")) {
            Clicks.click("quick_view.default_wishlist");
        }

        if (bloomingdales()) {
            Wait.untilElementPresent("quick_view.quick_view_add_to_wishlist_overlay");
            if (Elements.findElement("quick_view.quick_view_add_to_wishlist_overlay").getText().contains("Sorry")) {
                Clicks.click("quick_view.quick_view_product_add_to_wishlist");
            }
        }
    }

    /**
     * Navigates to shopping bag page from quick view dialog
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to shopping bag page from quick view dialog$")
    public void I_navigate_to_shopping_bag_page_from_quick_view_dialog() throws Throwable {
        Clicks.click("quick_view.checkout_now");
        if (onPage("add_to_bag")) {
            Clicks.click("add_to_bag.checkout");
        }
        shouldBeOnPage("shopping_bag");
    }

    @And("^I add a random item to bag from quick view$")
    public void iAddARandomItemToBagFromQuickView() {
        pausePageHangWatchDog();
        try {
            Wait.secondsUntilElementPresent("search_result.product_thumbnail_wrapper", 10);
            Clicks.hoverForSelection(Elements.getRandomElement("search_result.product_thumbnail_wrapper"));
            Clicks.randomJavascriptClick("search_result.product_thumbnail_quickview");
            Wait.secondsUntilElementPresent("quick_view.quick_view_product_add_to_bag", 5);
            if (Elements.elementPresent(By.className("qvLoadingErrorMessage"))) {
                logger.debug("Failed to load quick view. Retrying again...");
                Clicks.click("quick_view.quick_view_close_dialog");
                Clicks.hoverForSelection(Elements.getRandomElement("search_result.product_thumbnail_wrapper"));
                Clicks.randomJavascriptClick("search_result.product_thumbnail_quickview");
                Wait.secondsUntilElementPresent("quick_view.quick_view_product_add_to_bag", 5);
            }
            if (Elements.elementPresent("quick_view.quick_view_size_dropdown")) {
                DropDowns.selectByText("quick_view.quick_view_size_dropdown",
                        DropDowns.getAllValues("quick_view.quick_view_size_dropdown").get(1));
            }
            Clicks.click("quick_view.quick_view_product_add_to_bag");
            Wait.untilElementNotPresent("quick_view.quick_view_product_add_to_bag");
        } catch (Exception e) {
            if (Elements.elementPresent(By.className("qvLoadingErrorMessage"))) {
                Assert.fail("Failed to load quick view: " + Elements.getText(By.className("qvLoadingErrorMessage")));
            }
            e.printStackTrace();
            Assert.fail("Failed due to: " + e.getMessage());
        }
        resumePageHangWatchDog();
    }

    @Then("^I click \"([^\"]*)\" button on QuickView page$")
    public void i_click_button_on_QuickView_page(String selectedButtonName) throws Throwable {

        Thread.sleep(1000);
        if (bloomingdales()) {
            if (Elements.elementPresent("quick_view.quick_view_product_size_list")) {
                List<WebElement> sizes = Elements.findElement("quick_view.quick_view_product_size_list")
                        .findElements(By.xpath("li[not(contains(@class, \"disabled\"))]"));
                WebElement prodSize = sizes.get(new Random().nextInt(sizes.size()));
                Clicks.click(prodSize.findElement(By.xpath("span")));
            }
        } else {
            if (Elements.elementPresent("quick_view.quick_view_product_size")) {
                Thread.sleep(1000);
                DropDowns.selectByIndex("quick_view.quick_view_size_dropdown", 1);
            }
        }

        if(selectedButtonName.equals("ADD TO REGISTRY")){
            String eleToClick = macys() ? "quick_view.add_to_registry" : "quick_view.quick_view_add_to_registry";
            Wait.secondsUntilElementPresent(eleToClick, 10);
            if (Elements.elementPresent(eleToClick)) {
                Clicks.click(eleToClick);
            } else {
                Assert.fail("Add To Registry is not displayed");
            }
        }else  if(selectedButtonName.equals("ADD TO LIST")) {
            String eleToClick = macys() ? "quick_view.add_to_list" : "quick_view.quick_view_product_add_to_wishlist";
            Wait.secondsUntilElementPresent(eleToClick, 10);
            if (Elements.elementPresent(eleToClick)) {
                Clicks.click(eleToClick);
            } else {
                Assert.fail("Add To List is not displayed");
            }
        }
        logger.info("Successfully click on " + selectedButtonName + " button");
    }
}
