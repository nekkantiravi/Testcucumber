package com.macys.sdt.projects.Selection.List.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.*;
import cucumber.api.java.en.And;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.scrollToLazyLoadElement;

public class WishListPageSteps {
    private static final Logger logger = Logger.getLogger(WishListPageSteps.class.getName());
    private static WebElement selecteditem;
    private String expectedtooltiptext = "Item added to your wish List.";
    private static String expectedproductid, actualproductid, expectedsize;

    /*@When("^I select a random Wish List icon on browse page$")
    public void iSelectARandomwishListIconOnBrowsePage() throws Throwable {
        Wait.untilElementPresent("category_browse.wish_list_button");
        Clicks.randomJavascriptClick(Elements.element("category_browse.wish_list_button"));
        Clicks.click(Elements.getRandomElement("category_browse.wish_list_button"));
    }*/

    @Then("^I select a size$")
    public void iSelectASize() throws Throwable {
        String size;
        Wait.secondsUntilElementPresent("product_display.select_size_dropdown", 5);
        Assert.assertTrue(Elements.elementPresent("product_display.select_size_dropdown"));
        scrollToLazyLoadElement("product_display.select_size_dropdown");
        Wait.secondsUntilElementPresent("category_browse.wish_list_image", 10);
        WebElement dropDown = Elements.findElement("product_display.select_size_dropdown");
        Select select = new Select(dropDown);
        List<WebElement> elements = select.getOptions().stream().filter(e -> (!e.getText().contains("not available") && !e.getText().contains("Select A Size")&& !e.getText().contains("XXL") && !e.getText().contains("XS")&& !e.getText().contains("XL"))).collect(Collectors.toList());
        int randomIndex = new Random().nextInt(elements.size());
        size = elements.get(randomIndex).getText();
        DropDowns.selectByText("product_display.select_size_dropdown", size);
    }


    @And("^I add to the Wish List$")
    public void iAddToTheWishList() throws Throwable {
        Wait.secondsUntilElementPresent("category_browse.add_to_wish_list", 5);
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

    @Given("^I navigate to wish list page$")
    public void i_navigate_to_wish_list_page() throws Throwable {
        Wait.untilElementPresent("header.top_nav_wish_list_icon");
        Clicks.click("header.top_nav_wish_list_icon");
    }

    @Then("^I tap on Full Details button to Edit my product$")
    public void iTapOnFullDetailsButtonToEditMyProduct() throws Throwable {
        Wait.untilElementPresent("category_browse.full_details_button");
        Clicks.click("category_browse.full_details_button");
    }

    @Then("^On the wish list PDP I select a random size$")
    public void onTheWishListPDPISelectARandomSize() throws Throwable {
        Wait.secondsUntilElementPresent("header.header_image", 10);
        scrollToLazyLoadElement("product_display.select_size_dropdown");
        Wait.secondsUntilElementPresent("footer.become_guest_loyallist", 10);
        Assert.assertTrue(Elements.elementPresent("product_display.select_size_dropdown"));
        WebElement dropDown = Elements.findElement("product_display.select_size_dropdown");
        Select select = new Select(dropDown);
        List<WebElement> elements = select.getOptions().stream().filter(e -> (!e.getText().contains("not available") && !e.getText().contains("Select A Size")&& !e.getText().contains("XXL") && !e.getText().contains("XS")&& !e.getText().contains("XL"))).collect(Collectors.toList());
        int randomIndex = new Random().nextInt(elements.size());
        expectedsize = elements.get(randomIndex).getText();
        DropDowns.selectByText("product_display.select_size_dropdown", expectedsize);

    }

    @When("^I tap on \"([^\"]*)\" button$")
    public void iTapOnButton(String arg0) throws Throwable {
        Wait.untilElementPresent("product_display.add_to_wishlist");
        Clicks.click("product_display.add_to_wishlist");
    }

    @And("^I tap on \"([^\"]*)\" button on the confirmation overlay$")
    public void iTapOnButtonOnTheConfirmationOverlay(String arg0) throws Throwable {
        Wait.untilElementPresent("wish_list_overlay.view_list");
        Clicks.click("wish_list_overlay.view_list");
    }

    @Then("^on Wish list page I verify that the size selected displays$")
    public void onWishListPageIVerifyThatTheSizeSelectedDisplays() throws Throwable {
        Wait.untilElementPresent("category_browse.product_size_wishlist");
        WebElement size = Elements.findElement("category_browse.product_size_wishlist");
        System.out.print(expectedsize+" "+String.valueOf(size.getText().charAt(0)));
        Assert.assertTrue(expectedsize.contains(String.valueOf(size.getText().charAt(0))));
    }

   @When("^I choose a random Wish List icon on browse page$")
    public void i_choose_a_random_Wish_List_icon_on_browse_page() throws Throwable {

       Wait.untilElementPresent("category_browse.wish_list_button");
       List<WebElement> products = Elements.findElements("category_browse.product_name_without_brand_name").stream().filter(e -> e.findElements(By.className("b-j-add-to-wishlist-btn")).size() > 0).collect(Collectors.toList());
       System.out.println(products.size());
       WebElement selecteditem = products.get(new Random().nextInt(products.size())).findElement(By.className("b-j-add-to-wishlist-btn"));
       selecteditem.click();
    }


    @Then("^I remove the product from the wishlist$")
    public void iRemoveTheProductFromTheWishlist() throws Throwable {
        Wait.secondsUntilElementPresent("category_browse.wish_list_product_remove", 5);
        Wait.secondsUntilElementPresent("header.header_image", 5);
        Clicks.click("category_browse.wish_list_product_remove");
        Wait.secondsUntilElementNotPresent("category_browse.remove_button_modal", 5);
        Clicks.click("category_browse.remove_button_modal");
    }

    @Then("^I verify if the product is removed$")
    public void iVerifyIfTheProductIsRemoved() throws Throwable {
        Wait.secondsUntilElementNotPresent("footer.goto_sign_in_link", 5);
        Assert.assertTrue(!Elements.elementPresent("category_browse.add_to_bag_wish_list"));
    }

}
