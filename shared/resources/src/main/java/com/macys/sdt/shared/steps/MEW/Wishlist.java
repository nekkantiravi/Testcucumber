package com.macys.sdt.shared.steps.MEW;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import cucumber.api.PendingException;
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

public class Wishlist extends StepUtils {

    private static final Logger log = LoggerFactory.getLogger(Wishlist.class);
    public List<WebElement> productList;
    public WebElement randomProduct;
    public WebElement member_product;

    /**
     * Deletes all wish lists the current user has from wish list page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I delete all lists in wishlist page using mobile website$")
    public void I_delete_all_lists_in_wishlist_page_using_mobile_website() throws Throwable {
        CreateProfile.closeSecurityAlertPopUp();
        if (Elements.elementPresent("my_account.my_list")) {
            Clicks.click("my_account.my_list");
            Wait.untilElementPresent("wish_list.list_item_count");
            String count = Elements.getText("wish_list.list_item_count");
            while (!count.equals("0")) {
                Clicks.click("wish_list.item_delete_link");
                String new_count = Elements.getText("wish_list.list_item_count");
                while (new_count.equals(count))
                    Navigate.browserRefresh();
                count = new_count;
            }
            return;
        }
        Wait.untilElementPresent("wish_list.wishlist_title");
        String title = Elements.findElement("wish_list.wishlist_title").getText();
        while (!title.contains("My List") && !(!(title.contains("Test's List")) || title.contains("My Wish List") || title.contains("My List"))) {
            Wait.secondsUntilElementPresentAndClick("wish_list.manage_list", 10);
            Wait.secondsUntilElementPresentAndClick("wish_list.udate_setting", 10);
            Wait.untilElementPresent("wish_list.select_delete_button");
            Wait.secondsUntilElementPresentAndClick("wish_list.select_delete_button", 10);
            Wait.secondsUntilElementPresentAndClick("wish_list.delete_confirm_button", 10);
            if (macys()) {
                Navigate.browserRefresh();
            }
            try {
                title = Elements.findElement(Elements.element("wish_list.wishlist_title")).getText();
            } catch (Exception ex) {
                log.error("I_delete_all_lists_in_wishlist_page:" + ex.getMessage());
                title = "";
            }
        }
    }

    /**
     * Adds a random product to bag from wishlist page and selects given button
     *
     * @param action "continue shopping", "checkout" or "close"
     * @throws Throwable if any exception occurs
     */
    @When("^I add product to my bag from wishlist page using mobile website and (continue shopping|checkout|close)$")
    public void I_add_product_to_my_bag_from_wishlist_page_using_mobile_website_and(String action) throws Throwable {
        Wait.forPageReady();
        pausePageHangWatchDog();
        Assert.assertTrue("ERROR-DATA: Unable to find available products in the wishlist", Wait.secondsUntilElementPresent("wish_list.add_to_bag_btn", 10));
        Clicks.clickIfPresent("wish_list.manage_wish_list_close");
        Clicks.clickRandomElement("wish_list.add_to_bag_btn");
        Wait.untilElementPresent("wish_list.add_to_bag_dialog");
        Assert.assertFalse("ERROR - ENV : Unable to add item to bag!. Error('" + Elements.getText("wish_list.error_tooltip") + "')", Elements.elementPresent("wish_list.error_tooltip"));
        Assert.assertTrue("ERROR - ENV : Add to bag Dialog is not presented", Elements.elementPresent("wish_list.add_to_bag_dialog"));
        switch (action.toLowerCase()) {
            case "continue shopping":
                Clicks.clickIfPresent("wish_list.continue_shopping");
                break;
            case "checkout":
                Clicks.clickIfPresent("wish_list.checkout");
                break;
            case "close":
                Clicks.clickIfPresent("wish_list.overlay_close");
                break;
            default:
                Assert.fail("Invalid option found");
                break;
        }
        resumePageHangWatchDog();
    }

    /**
     * Selects a random product from wish list page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I navigate to a random product PDP from wish list page using mobile website$")
    public void I_navigate_to_a_random_product_PDP_from_wish_list_page_using_mobile_website() throws Throwable {
        Wait.untilElementPresent("wish_list.item_links");
        Clicks.clickRandomElement("wish_list.item_links");
    }

    @And("^I should see the wishlist image wrapper for the products displayed$")
    public void iShouldSeeTheWishlistImageWrapperForTheProductsDisplayed() throws Throwable {
        productList = Elements.findElement("search_result.verify_page").findElements(By.className("mb-j-browse-image"));
        for (WebElement product : productList) {
            Thread.sleep(200);
            String product_name = product.getText().split("\n")[0];
            Assert.assertTrue("Wish list image wrapper for " + product_name + "displayed", product.findElement(By.className("b-j-plp-add-to-wishlist")).isDisplayed());
        }
        log.info("Verified the wish list image wrapper");
    }

    @When("^I click on the wishlist image wrapper for a random product$")
    public void iClickOnTheWishlistImageWrapperForARandomProduct() throws Throwable {
        randomProduct = productList.get(new Random().nextInt(productList.size() - 1));
        Clicks.click(randomProduct.findElement(By.xpath("//*[@class='mb-j-browse-image']/a/div[1]/div[2]/div/div/button")));
        log.info("Verified the wish list image wrapper for a " + randomProduct.getText());
    }

    @Then("^I should see Add to wish list overlay$")
    public void iShouldSeeAddToWishListOverlay() throws Throwable {
        Wait.untilElementPresent("wish_list.wishlist_modal");
        Assert.assertTrue("Add to wishlist button is displayed",
                Elements.findElement("wish_list.wishlist_button").isDisplayed());
        Thread.sleep(2000);
        log.info("Verified the display of add to wish list overlay");
    }

    @When("^I select a dropdown value$")
    public void iSelectADropdownValue() throws Throwable {
        DropDowns.selectRandomValue("wish_list.sizes");
        log.info("Selected a dropdown value from the list");
    }

    @And("^I click on Add to wish list button$")
    public void iClickOnAddToWishListButton() throws Throwable {
        Clicks.click("wish_list.wishlist_button");
        log.info("Added the item to wishlist");
    }

    @Then("^I should see the item added to wish list$")
    public void iShouldSeeTheItemAddedToWishList() throws Throwable {
        Assert.assertTrue("Item added to wishlist overlay displayed", Elements.findElement("wish_list.wishlist_overlay").isDisplayed());
        log.info("Verified the item added to wishlist");
    }
}
