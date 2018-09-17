package com.macys.sdt.projects.Selection.List.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.And;
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

public class WishListPage {
    private static String firstImage, expectedColor, wish_list_image;
    private static final Logger logger = Logger.getLogger(WishListPage.class.getName());
    private static String wishListColor;

    @When("^I select a random Wish List icon on browse page$")
    public void iSelectARandomwishListIconOnBrowsePage() throws Throwable {

        Wait.untilElementPresent("category_browse.wish_list_button");
        List<WebElement> products = Elements.findElements("category_browse.product_name_without_brand_name").stream().filter(e -> (e.findElements(By.className("b-more-colors")).size()) > 0 && e.findElement(By.className("b-price-regular")).getText().contains("-") && e.findElements(By.className("b-j-add-to-wishlist-btn")).size() > 0).collect(Collectors.toList());
        System.out.println(products.size());
        WebElement selecteditem = products.get(new Random().nextInt(products.size())).findElement(By.className("b-j-add-to-wishlist-btn"));
        selecteditem.click();
        Wait.untilElementPresent("category_browse.image_wishlist");
        firstImage = Elements.findElement("category_browse.image_wishlist").getAttribute("src");
    }


    @And("^I select a random color swatch from the Wish List modal$")
    public void iSelectARandomColorSwatchFromTheWishListModal() throws Throwable {
        Wait.untilElementPresent("category_browse.wish_list_all_colors");
        if (Elements.elementPresent("category_browse.wish_list_modal_color_drop_down")) {
            WebElement dropDown = Elements.findElement("category_browse.wish_list_modal_color_drop_down");
            Select select = new Select(dropDown);
            List<WebElement> elements = select.getOptions().stream().filter(e -> (!e.getText().contains("Select A Color"))).collect(Collectors.toList());
            int randomIndex = new Random().nextInt(elements.size());
            DropDowns.selectByText("category_browse.wish_list_modal_color_drop_down", elements.get(randomIndex).getText());
        }
    }

    @And("^I verify that the image updates to the color selected$")
    public void iVerifyThatTheImageUpdatesToTheColorSelected() throws Throwable {
        Wait.untilElementPresent("category_browse.image");
        String secondImage = Elements.findElement("category_browse.image_wishlist").getAttribute("src");
        System.out.println("image is:" + secondImage);
        if (firstImage.equalsIgnoreCase(secondImage)) {
            System.out.println("Both are same");
            logger.info("Image is not updating ");
        } else {
            System.out.println("Both images are different");
            logger.info("Image is updated ");
        }
    }

    @And("^I add product to my bag from Wish List page$")
    public void iAddProductToMyBagFromWishlistPage() throws Throwable {
        Wait.untilElementPresent("category_browse.wish_list_ATB");
        Clicks.click("category_browse.wish_list_ATB");
        Wait.untilElementPresent("add_to_bag.checkout");
        Clicks.click("add_to_bag.checkout");
        logger.info("Product was added to bag");
    }

    @When("^I am on Wish List page I verify image's color name$")
    public void iAmOnWishListPageIVerifyImageSColorName() throws Throwable {
            Wait.untilElementPresent("category_browse.wish_list_color_text");
            WebElement wishListColor = Elements.findElement("category_browse.wish_list_color_text");
            System.out.println("Color name is" + wishListColor.getText());
            expectedColor = wishListColor.getText();

        }

    @And("^I navigate my wish list page$")
    public void iNavigateMyWishListPage() throws Throwable {
       Wait.untilElementPresent("header.top_nav_wish_list_icon");
               Clicks.click("header.top_nav_wish_list_icon");

    }

    @Then("^I verify product's color in shopping bag is the same as color selected on Wish List page$")
    public void iVerifyProductSColorInShoppIngBagIsTheSameAsColorSelectedOnWishListPage() throws Throwable {
       Wait.untilElementPresent("category_browse.bag_title");
       Wait.untilElementPresent("category_browse.color_container");
       WebElement bag_color_container = Elements.findElement("category_browse.color_container");
       WebElement bag_color = bag_color_container.findElement(By.className("b-bag-item-detail"));
       System.out.println("bag color is "+bag_color.getText());
       Assert.assertTrue(bag_color.getText().contains(expectedColor));
    }

    @And("^I navigate to PDP from Wish List$")
    public void iNavigateToPDPFromWishList() throws Throwable {
        Wait.untilElementPresent("category_browse.full_details_button");
        Clicks.click("category_browse.full_details_button");
    }

    @Then("^I verify that the color matches the color on Wish List$")
    public void iVerifyThatTheColorMatchesTheColorOnWishList() throws Throwable {
        Wait.untilElementPresent("category_browse.wish_list_modal_color_drop_down");
        Select dropdown = new Select(Elements.findElement("category_browse.wish_list_modal_color_drop_down"));
        System.out.println(expectedColor);
        String actualColor = dropdown.getFirstSelectedOption().getText();
        Assert.assertTrue(expectedColor.equalsIgnoreCase(actualColor));
    }

    @Then("^I verify that the image displayed matches the image on Wish List$")
    public void iVerifyThatTheImageDisplayedMatchesTheImageOnWishList() throws Throwable {
        String image;
        Wait.untilElementPresent("category_browse.pdp_image");
        if(Elements.elementPresent("category_browse.pdp_color_swatch")){
            image = Elements.findElement("category_browse.pdp_color_swatch").getAttribute("src");
            System.out.println("its color swatch:"+image);
        }
        else {
            image = Elements.findElement("category_browse.pdp_main_image").getAttribute("src");
            System.out.println("its main image:"+image);
        }
        Assert.assertTrue(wish_list_image.equalsIgnoreCase(image));
    }

    @And("^I check the image link$")
    public void iCheckTheImageLink() throws Throwable {
        //String image;
        Wait.untilElementPresent("category_browse.pdp_color_swatch");
        scrollToLazyLoadElement("category_browse.pdp_main_image");
        if(Elements.elementPresent("category_browse.pdp_color_swatch")){
            wish_list_image = Elements.findElement("category_browse.pdp_color_swatch").getAttribute("src");
            System.out.println("its color swatch:******:"+wish_list_image);
        }
        else {
            wish_list_image = Elements.findElement("category_browse.pdp_main_image").getAttribute("src");
            System.out.println("its main image:******:"+wish_list_image);
        }
    }
}

