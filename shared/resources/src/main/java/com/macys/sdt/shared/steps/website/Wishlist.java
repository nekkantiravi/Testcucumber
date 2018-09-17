package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.services.ProductService;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Wishlist extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(Wishlist.class);

    /**
     * Selects the wish list link in the header
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select wishlist link in header$")
    public void I_select_wishlist_link_in_header() throws Throwable {
        Wait.untilElementPresent("home.goto_wishlist");
        Clicks.click("home.goto_wishlist");
    }

    /**
     * Verifies browser is on wish list landing page as given user type
     *
     * @param user registered|guest
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see wishlist landing page as a (registered|guest) user$")
    public void I_should_see_wishlist_landing_page_as_a_user(String user) throws Throwable {
        if (!Wait.secondsUntilElementNotPresent("wish_list.loading_icon", 15))
            Navigate.browserRefresh();
        Wait.secondsUntilElementPresent("wish_list.wishlist_title", (safari() ? 20 : 10));
        shouldBeOnPage("wish_list");
        if (user.equals("registered")) {
            Clicks.click("wish_list.default_wishlist");
        }
    }

    /**
     * Navigates to a random product from the wish list
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I navigate to a random product PDP from wish list page$")
    public void I_navigate_to_a_random_product_PDP_from_wish_list_page() throws Throwable {
        Clicks.clickRandomElement("wish_list.item_links");
    }

    /**
     * Clicks the wishlist link from add to wishlist overlay on pdp
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select wishlist link on the wishlist overlay in PDP(?: page)?$")
    public void I_select_wishlist_link_on_the_wishlist_overlay_in_PDP_page() throws Throwable {
        Thread.sleep(5000);
        int height = Integer.parseInt(Elements.findElement(By.tagName("body")).getCssValue("height").split("\\.")[0]);
        if (macys()) {
            Navigate.scrollPage(0, height - 900);
        }
        Wait.secondsUntilElementPresentAndClick("home.goto_wishlist", 20);

    }

    /**
     * Deletes all wish lists the current user has from wish list page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I delete all lists in wishlist page$")
    public void I_delete_all_lists_in_wishlist_page() throws Throwable {
        Wait.secondsUntilElementPresentAndClick("home.goto_wishlist", 20);
        CreateProfile.closeSecurityAlertPopUp();
        Wait.untilElementPresent("wish_list.wishlist_title");
        String title = Elements.findElement("wish_list.wishlist_title").getText();
        while (!title.contains("My List (0)") && !(title.contains("My Temporary Wish List") || title.contains("My Wish List") || title.contains("Guest List") || title.contains("My List"))) {
            Wait.secondsUntilElementPresentAndClick("wish_list.manage_list", 20);
            Wait.secondsUntilElementPresentAndClick("manage_wish_list.select_delete_button", 10);
            Wait.secondsUntilElementPresentAndClick("manage_wish_list.delete_confirm_message", 10);
            Wait.secondsUntilElementPresentAndClick("manage_wish_list.yes_confirmation", 10);
            if (macys()) {
                Navigate.browserRefresh();
            }
            try {
                title = Elements.findElement("wish_list.wishlist_title").getText();
            } catch (Exception ex) {
                logger.error("I_delete_all_lists_in_wishlist_page:" + ex.getMessage());
                title = "";
            }
        }
    }

    /**
     * Creates a wish list with the given name on wish list page
     *
     * @param list_name name to give new list
     * @throws Throwable if any exception occurs
     */
    @And("^I create a list \"([^\"]*)\" from wishlist page$")
    public void I_create_a_list_from_wishlist_page(String list_name) throws Throwable {
        Wait.forPageReady("wish_list");
        Wait.secondsUntilElementPresent("wish_list.goto_create_list_link", 15);
        Clicks.javascriptClick("wish_list.goto_create_list_link");
        Wait.untilElementPresent("wish_list.list_name_text");
        if (!Elements.elementPresent("wish_list.list_name_text")) {
            Clicks.clickIfPresent("wish_list.goto_create_list_link");
        }
        TextBoxes.typeTextNEnter("wish_list.list_name_text", list_name);
        if (macys()) {
            Navigate.browserRefresh();
        }
        Wait.untilElementPresent("wish_list.wishlist_title");
    }

    /**
     * Adds the current product to wish list
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I add the product to wishlist")
    public void I_add_product_to_wishlist() throws Throwable {
        Wait.secondsUntilElementPresentAndClick("product_display.add_to_wishlist_image", 2);
        if (bloomingdales()) {
            Wait.secondsUntilElementPresentAndClick("quick_view.default_wishlist", 5);
        } else {
            Wait.untilElementPresent("product_display.wishlist_overlay");
        }
    }

    /**
     * Verifies that given product is present on the with list
     *
     * @param expectation should or should not
     * @throws Throwable if any exception occurs
     */
    @Then("^I (should|should not) see added product in product line items on wishlist page$")
    public void i_should_see_in_product_line_items_in_wishlist_page(String expectation) throws Throwable {
        boolean expectVisible = expectation.equals("should");
        if (!Wait.secondsUntilElementPresent("wish_list.item_links", 5)) {
            Navigate.browserRefresh();
            Wait.forPageReady();
        }
        List<WebElement> plist = Elements.findElements("wish_list.item_links");
        Boolean found;
        if (ShopAndBrowse.selectedProductName != null) {
            found = plist.stream().anyMatch(link -> link.getText().toLowerCase().contains(ShopAndBrowse.selectedProductName.toLowerCase()));
        } else {
            found = !plist.isEmpty();
        }
        if (expectVisible) {
            Assert.assertTrue("Product is not added to wishlist", found);
        } else {
            Assert.assertFalse("Unexpected product(s) on wishlist page", found);
        }
    }

    /**
     * Clicks the first product with a name matching the give one
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I select a added product on wishlist page$")
    public void i_select_a_product_on_wishlist_page() throws Throwable {
        List<WebElement> plist = Elements.findElements("wish_list.item_links");
        if (plist.stream().anyMatch(link -> link.getText().equalsIgnoreCase(ShopAndBrowse.selectedProductName))) {
            Clicks.click(plist.stream()
                    .filter(link -> link.getText().equalsIgnoreCase(ShopAndBrowse.selectedProductName))
                    .findFirst()
                    .orElse(null));
        }
    }

    /**
     * Adds a product to bag from wishlist and does the given action
     *
     * @param action continue shopping|checkout|close
     * @throws Throwable if any exception occurs
     */
    @When("^I add product to my bag from wishlist page and (continue shopping|checkout|close)$")
    public void I_add_product_to_my_bag_from_wishlist_page_and(String action) throws Throwable {
        Assert.assertTrue("ERROR-DATA: Unable to find available products in the wishlist", Elements.elementPresent("wish_list.add_to_bag_btn"));
        Clicks.clickRandomElement("wish_list.add_to_bag_btn");
        Wait.secondsUntilElementPresent("wish_list.add_to_bag_dialog", 20);
        Assert.assertTrue("ERROR - ENV : Add to bag Dialog is not presented", Elements.elementPresent("wish_list.add_to_bag_dialog"));
        action = action.equals("close") ? "overlay close" : action;
        action = action.replaceAll(" ", "_");
        Clicks.click("wish_list." + action);
    }

    /**
     * Verifies that a product is unavailable
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify product unavailability check at MST$")
    public void iVerifyProductUnavailabilityCheckAtMST() throws Throwable {
        ProductService.addUpcIdToBag("10855");
    }

    @Then("^I remove a random product from wishlist page$")
    public void iRemoveARandomProductFromWishlistPage() {
        if (!Wait.untilElementPresent("wish_list.item_links")) {
            logger.warn("No wishlist items found to remove");
            return;
        }
        Clicks.click("wish_list.delete_item");
        Wait.secondsUntilElementNotPresent("wish_list.item_links", 5);
    }

    @Then("^I should see the selected list name with \"([0-9]+)\" item count$")
    public void iShouldSeeTheSelectedListNameWithItemCount(int items) {
        if (!Wait.untilElementPresent("wish_list.item_count")) {
            throw new EnvException("Unable to find item count on wishlist page!");
        }
        String countText = Elements.getText("wish_list.item_count").replace("(", "").replace(")", "");
        int count = Integer.parseInt(countText);
        Assert.assertEquals("Expected " + items + " items in wish list, found " + count, items, count);
    }
}
