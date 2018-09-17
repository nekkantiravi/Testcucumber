package com.macys.sdt.projects.Discovery.WishListBrowseAndSearch.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import cucumber.api.java.en.And;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class WishListStepsOne {
    private static final Logger logger = Logger.getLogger(WishListStepsOne.class.getName());
    private static WebElement selecteditem;
    private String expectedtooltiptext = "Item added to your Wish List.";
    private static String expectedproductid, actualproductid;

    @When("^I select a random Wish List icon on browse page$")
    public void iSelectARandomwishListIconOnBrowsePage() throws Throwable {
        Wait.untilElementPresent("category_browse.wish_list_button");
        List<WebElement> items = Elements.findElements("category_browse.wish_list_button");
        int randomIndex = new Random().nextInt(items.size());
        selecteditem = items.get(randomIndex);
        selecteditem.click();

    }
    @Then("^I select a size$")
    public void iSelectASize() throws Throwable {
        Wait.secondsUntilElementPresent("product_display.select_size_dropdown", 3);
        if (Elements.elementPresent("product_display.select_size_dropdown")) {
            WebElement dropDown = Elements.findElement("product_display.select_size_dropdown");
            Select select = new Select(dropDown);
            List<WebElement> elements = select.getOptions().stream().filter(e -> (!e.getText().contains("not available") && !e.getText().contains("Select A Size"))).collect(Collectors.toList());
            int randomIndex = new Random().nextInt(elements.size());
            DropDowns.selectByText("product_display.select_size_dropdown", elements.get(randomIndex).getText());
        }
    }

    @And("^I add to the Wish List$")
    public void iAddToTheWishList() throws Throwable {
        Wait.untilElementPresent("category_browse.add_to_wish_list");
        Elements.findElement("category_browse.add_to_wish_list").click();
    }

    @Then("^I verify that Wish List icons are filled$")
    public void iVerifyThatWishListIconsAreFilled() throws Throwable {
        Wait.untilElementPresent("header.wish_list_tool_tip");
        WebElement tooltip = Elements.findElement("header.wish_list_tool_tip");

        if(Elements.elementPresent("header.wish_list_tool_tip")) {
            Assert.assertEquals(tooltip.getText(), expectedtooltiptext);
            WebElement wishlisticon = Elements.findElement("header.top_nav_wish_list_icon");
            String class_name = (String) wishlisticon.getAttribute("class");
            String expectedclassname = "b-wishlist-added";
            Assert.assertTrue(class_name.equalsIgnoreCase(expectedclassname));
            logger.info("Verified that wish list icons are filled");
            Wait.secondsUntilElementNotPresent("category_browse.verify_page", 3);
        }
    }
    @And("^I verify Wish List has product recently added$")
    public void iVerifyWishListHasProductRecentlyAdded() throws Throwable {
        Clicks.click("header.top_nav_wish_list_icon");
    }

    @When("^I select a random item from the results$")
    public void iSelectARandomWishList() throws Throwable {
        Wait.untilElementPresent("wish_list.wishlist_title");
        List<WebElement> items = Elements.findElements("category_browse.product_name_without_brand_name");
        int randomIndex = new Random().nextInt(items.size());
        WebElement selecteditem = items.get(randomIndex);
        expectedproductid = selecteditem.getAttribute("data-product_id");
        selecteditem.findElement(By.className("b-j-add-to-wishlist-btn")).click();
    }
    @Then("^I verify that item is added$")
    public void i_verify_that_item_is_added() throws Throwable {
        Wait.untilElementPresent("header.wish_list_tool_tip");
        WebElement tooltip = Elements.findElement("header.wish_list_tool_tip");
        String expectedtooltiptext = "Item added to your wish List.";
        if (Elements.elementPresent("header.wish_list_tool_tip")) {
            Assert.assertTrue(tooltip.getText().equalsIgnoreCase(expectedtooltiptext));
        }
    }

    @Then("^Wish List header icon should contain the product just added$")
    public void headerStarShouldUpdate() throws Throwable {
        Wait.untilElementPresent("header.top_nav_wish_list_icon");
        Clicks.click("header.top_nav_wish_list_icon");
        Wait.untilElementPresent("category_browse.wish_list_title");
        List<WebElement> wishListItems = Elements.findElements("category_browse.wish_list_items_container");
        for (WebElement e: wishListItems){
            if(expectedproductid.equalsIgnoreCase(e.getAttribute("data-product-id"))) {
                actualproductid = e.getAttribute("data-product-id");
                logger.info(expectedproductid);
            }
        }
        Assert.assertEquals(expectedproductid, actualproductid);
        logger.info("Verified that the product is added to wishlist");
    }

    @When("^I tap outside of the modal$")
    public void iTapOutsideOfTheModal() throws Throwable {
        WebDriver driver = WebDriverManager.getWebDriver();
        WebElement mask = Elements.findElement("category_browse.mask");
        Actions action = new Actions(driver);
        action.moveToElement(mask, 11, 20).click().perform();

    }
    @Then("^Wish List modal should close$")
    public void WishListModalShouldClose() throws Throwable {
        Wait.untilElementPresent("header.header_image");
        Assert.assertTrue(Elements.elementPresent("header.header_image"));
        logger.info("Verified that we are out of Wish List PDP");

    }
    @And("^I tap on the X$")
    public void iTapOnTheX() throws Throwable {
        Wait.untilElementPresent("category_browse.wish_list_pdp_close_button");
        Elements.findElement("category_browse.wish_list_pdp_close_button").click();
    }

    @Then("^the star on the header should display a tool tip for (\\d+) seconds$")
    public void theStarOnTheHeaderShouldDisplayAToolTipForSeconds(int arg0) throws Throwable {
        Wait.untilElementPresent("header.wish_list_tool_tip");
        Assert.assertTrue(Elements.elementPresent("header.wish_list_tool_tip"));
        logger.info("Verified that the message is displayed ");
    }
}
