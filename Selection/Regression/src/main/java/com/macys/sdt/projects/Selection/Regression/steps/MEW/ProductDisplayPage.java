package com.macys.sdt.projects.Selection.Regression.steps.MEW;


import com.macys.sdt.framework.interactions.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.macys.sdt.framework.utils.StepUtils.macys;


public class ProductDisplayPage {
    private static final Logger logger = LoggerFactory.getLogger(ProductDisplayPage.class);

    @Then("^I should see the default selected color$")
    public void iVerifyDefaultSelectedColor() throws Throwable {
        Wait.secondsUntilElementPresent("product_display.selected_swatch_color",20);
        Navigate.scrollPage(0, 650);
        String selectedColorValue = Elements.findElement("product_display.selected_swatch_color").
                getAttribute("aria-checked");
        String color = Elements.findElement("product_display.product_color_name").getText();
        Assert.assertEquals("Default color isn't selected", "true", selectedColorValue);
        logger.info("The default selected color is : " + color);

    }

    @Then("^I should see the selected color of a product$")
    public void iVerifySelectedColor() throws Throwable {
        Wait.untilElementPresent("product_display.select_default_color");
        int color_count = Elements.findElements("product_display.select_default_color").size();
        int number = (int) (Math.random() * color_count);
        Navigate.scrollPage(0, 650);
        Elements.findElements("product_display.select_default_color").get(number).click();
        String selectedColorValue = Elements.findElements("product_display.select_default_color")
                .get(number).getAttribute("aria-checked");
        String color = Elements.findElement("product_display.product_color_name").getText();
        Assert.assertEquals("color isn't selected", "true", selectedColorValue);
        logger.info("The selected color is : " + color);
    }

    @Then("^I should see the availability of sizes for selected color$")
    public void iVerifyAvailabilityOfSizeForSelectedColor() throws Throwable {
        String strColorAvailability = null;
        String strSelectedSize = null;
        Wait.untilElementPresent("product_display.select_default_color");
        int color_count = Elements.findElements("product_display.select_default_color").size();
        int number = (int) (Math.random() * color_count);
        Navigate.scrollPage(0, 650);
        Elements.findElements("product_display.select_default_color").get(number).click();
        if (Elements.elementPresent("product_display.select_size_dropdown")) {
            strSelectedSize = DropDowns.selectRandomValue("product_display.select_size_dropdown");
        } else {
            logger.info("No sizing options found");
        }
        strColorAvailability = Elements.findElements("product_display.select_default_color")
                .get(number).getAttribute("class");
        if (strSelectedSize.contains("not available")) {
            Assert.assertTrue("Size isn't available for selected color", strColorAvailability.contains("unavailable"));
            logger.info("Unavailable size for selected color is : " + strSelectedSize);
        } else {
            Assert.assertTrue("Size isn't available for selected color", strColorAvailability.contains("selected"));
            logger.info("Available size for selected color is : " + strSelectedSize);
        }
        logger.info("Sizes & colors of a product are verified successfully");
    }

    @Then("^I should see the availability of colors for selected size$")
    public void iVerifyAvailabilityOfColoForSelectedSize() throws Throwable {
        String strColorAvailability = null;
        String strSelectedSize = null;
        Navigate.scrollPage(0, 650);
        if (Elements.elementPresent("product_display.select_size_dropdown")) {
            DropDowns.selectRandomValue("product_display.select_size_dropdown");
        } else {
            logger.info("No sizing options found");
        }
        Wait.untilElementPresent("product_display.select_default_color");
        int color_count = Elements.findElements("product_display.select_default_color").size();
        int number = (int) (Math.random() * color_count);
        Elements.findElements("product_display.select_default_color").get(number).click();
        strColorAvailability = Elements.findElements("product_display.select_default_color")
                .get(number).getAttribute("class");
        String color = Elements.findElement("product_display.product_color_name").getText();
        strSelectedSize = DropDowns.getSelectedValue(Elements.element("product_display.select_size_dropdown"));
        if (strColorAvailability.contains("unavailable")) {
            Assert.assertTrue("Color isn't available for selected size", strSelectedSize.contains("not available"));
            logger.info("Unavailable color for selected size is : " + color);
        } else {
            Assert.assertTrue("Color isn't available for selected size", strSelectedSize.equalsIgnoreCase(strSelectedSize));
            logger.info("Available color for selected size is : " + color);
        }
        logger.info("colors & Sizes of a product are verified successfully");
    }

    @Then("^I should see the color quantity displayed$")
    public void iVerifyColorQuantityDisplayed() throws Throwable {
        Wait.untilElementPresent("product_display.select_default_color");
        int color_count = Elements.findElements("product_display.product_swatch_colors").size();
        String strColorQuantity = Elements.findElement("product_display.product_color_count").getText();
        int nColorQty = Integer.parseInt(strColorQuantity.split(" ")[0]);
        Assert.assertEquals("Color quantity isn't displayed correctly", color_count, nColorQty);
    }

    @When("^I select a random color swatch for the product$")
    public void iSelectRandomColorSwatch() throws Throwable {
        Wait.untilElementPresent("product_display.select_default_color");
        Navigate.scrollPage(0, 650);
        int color_count = Elements.findElements("product_display.select_default_color").size();
        int number = (int) (Math.random() * color_count);
        Elements.findElements("product_display.select_default_color").get(number).click();
    }

    @Then("^I should see unavailable sizes are inactive for the default color$")
    public void verifyUnavailableSizeInactive() throws Throwable {
        String strSelectedSize = null;
        String strColorAvailability = null;
        Wait.untilElementPresent("product_display.selected_swatch_color");
        Navigate.scrollPage(0, 650);
        String selectedColorValue = Elements.findElement("product_display.selected_swatch_color").
                getAttribute("aria-checked");
        String color = Elements.findElement("product_display.product_color_name").getText();
        Assert.assertEquals("Default color isn't selected", "true", selectedColorValue);
        logger.info("The default selected color is : " + color);
        if (Elements.elementPresent("product_display.select_size_dropdown")) {
            strSelectedSize = DropDowns.selectRandomValue("product_display.select_size_dropdown");
            strColorAvailability = Elements.findElement("product_display.selected_swatch_color").getAttribute("class");
            if (strColorAvailability.contains("unavailable")) {
                Assert.assertTrue("Color isn't available for selected size..", strSelectedSize.contains("not available"));
                logger.info("Unavailable color for selected size is : " + color);
            } else {
                Assert.assertTrue("Color isn't available for selected size..", strSelectedSize.equalsIgnoreCase(strSelectedSize));
                logger.info("Available color for selected size is : " + color);
            }

        } else {
            logger.info("No sizing options found");
        }
        logger.info("unavailable sizes are inactive for the default color");
    }

    @And("^I should see the relevant product image being displayed$")
    public void verifyRelevantProductImageDisplayed() throws Throwable {
        Wait.untilElementPresent("product_display.selected_swatch_color");
        boolean imageVisibility = Elements.findElement("product_display.product_image").isDisplayed();
        Assert.assertTrue("Relevant product image is not being displayed..", imageVisibility);
        logger.info("Relevant product image being displayed...");
    }

    @Then("^I should see size name \"Small\" as \"S\" on size swatch for member of master pdp$")
    public void verifySmallSizeSwatchForMember() throws Throwable {
        String strSelectedSize = null;
        Navigate.scrollPage(0, 650);
        if (Elements.elementPresent("product_display.select_size_dropdown")) {
            List<String> strListValues = DropDowns.getAllValues("product_display.select_size_dropdown");
            if (strListValues.contains("S")) {
                DropDowns.selectByText("product_display.select_size_dropdown", "S");
                strSelectedSize = DropDowns.getSelectedValue(Elements.element("product_display.select_size_dropdown"));
                Assert.assertEquals("Size name isn't displayed as 'S' on size swatch", "S", strSelectedSize);
                logger.info("Size is being displayed as 'S' on size Swatch.. ");
            } else {
                logger.info("'S' size not found");
            }
        } else {
            logger.info("No sizing options found");
        }
    }

    @Then("^I should see size name \"Large\" as \"L\" on size swatch for member of master pdp$")
    public void verifyLargeSizeSwatchForMember() throws Throwable {
        String strSelectedSize = null;
        if (Elements.elementPresent("product_display.select_size_dropdown")) {
            List<String> strListValues = DropDowns.getAllValues("product_display.select_size_dropdown");
            if (strListValues.contains("L")) {
                DropDowns.selectByText("product_display.select_size_dropdown", "L");
                strSelectedSize = DropDowns.getSelectedValue(Elements.element("product_display.select_size_dropdown"));
                Assert.assertEquals("Size name isn't displayed as 'L' on size swatch", "L", strSelectedSize);
                logger.info("Size is being displayed as 'L' on size Swatch.. ");
            } else {
                logger.info("'L' size not found");
            }
        } else {
            logger.info("No sizing options found");
        }
    }

    @When("^I select a random beauty color name from dropdown$")
    public void iSelectRandomBeautyColorName() throws Throwable {
        Navigate.scrollPage(0, 650);
        String strDropDownColorName = DropDowns.selectRandomValue("product_display.beauty_color_drop_down");
        logger.info("Random beauty color name is selected from drop down as:" + strDropDownColorName);
    }

    @Then("^I should see the default selected color name of beauty product$")
    public void verifyDefaultSelectedColorName() throws Throwable {
        String selectedColorValue = null;
        String strDropDownColorName = null;
        Wait.untilElementPresent("product_display.selected_swatch_color");
        selectedColorValue = Elements.findElement("product_display.selected_swatch_color").
                getAttribute("aria-checked");
        strDropDownColorName = DropDowns.getSelectedValue(Elements.element("product_display.beauty_color_drop_down"));
        Assert.assertEquals("Default color isn't selected", "true", selectedColorValue);
        logger.info("The default selected color is : " + strDropDownColorName);
    }

    @Then("^I should see the selected random color name display in dropdown$")
    public void verifyColorNameDisplayed() throws Throwable {
        boolean bColorNameDisplay = Elements.findElement("product_display.selected_swatch_color").isDisplayed();
        String strDropDownColorName = DropDowns.getSelectedValue(Elements.
                element("product_display.beauty_color_drop_down"));
        Assert.assertTrue("Random selected color name is not being displayed", bColorNameDisplay);
        logger.info("Random selected color name is being displayed as:" + strDropDownColorName);
    }

    @When("^I select a random color swatch for the product and verify color name accordingly$")
    public void iSelectRandomColorSwatchverify() throws Throwable {
        Wait.untilElementPresent("product_display.select_default_color");
        Navigate.scrollPage(0, 650);
        int color_count = Elements.findElements("product_display.select_default_color").size();
        Set<String> uniquecolorslist = new HashSet<>();
        for (int i = 0; i <= color_count; i++) {
            int number = (int) (Math.random() * color_count);
            Elements.findElements("product_display.select_default_color").get(number).click();
            uniquecolorslist.add(Elements.findElement("product_display.product_color_name").getText());
        }
        Assert.assertTrue("Color name changed accordingly when I change colors for a product",
                uniquecolorslist.size() > 1);
        logger.info("Color name changed accordingly when user change colors for a product");
    }

    @Then("^I should see maximum available quantity of product on pdp$")
    public void I_should_see_max_quantity_of_product_items() throws Throwable {
        if (macys()) {
            Wait.secondsUntilElementPresent("product_display.qty_value", 15);
            int quantity = Integer.parseInt(Elements.getText("product_display.qty_value"));
            Wait.secondsUntilElementPresent("addToBag.pdp_qty_increment_button", 20);
            while ((Elements.findElement("addToBag.pdp_qty_increment_button").isEnabled())) {
                Clicks.click(Elements.element("addToBag.pdp_qty_increment_button"));
                quantity++;
            }
            Assert.assertEquals("User is not able to select the maximum quantity of quantity", quantity, Integer.parseInt(Elements.getText("product_display.qty_value")));
        } else {
                Wait.secondsUntilElementPresent("product_display.quantity", 20);
                String dropdownSize =  Integer.toString(Elements.findElements("product_display.max_quantity").size());
                DropDowns.selectByValue("product_display.quantity", dropdownSize);
                String strLastDropDownValue = DropDowns.getSelectedValue(Elements.element("product_display.quantity"));
                Assert.assertEquals("Maximum quantity doesn't displayed on PDP..",dropdownSize,strLastDropDownValue);
                logger.info("Maximum quantity is being displayed on PDP");
            }
        }
}




