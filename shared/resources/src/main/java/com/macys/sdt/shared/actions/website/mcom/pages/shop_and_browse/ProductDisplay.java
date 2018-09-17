package com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductDisplay extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ProductDisplay.class);

    public static boolean isMasterMemberPage() {
        return onPage("product_display") && Elements.elementPresent(Elements.element("product_display.choose_member_items"));
    }

    public static boolean isMasterMemberQuickViewDialog() {
        return Elements.elementPresent("quick_view.quick_view_product_add_to_bag");
    }

    public static void closeTechnicalErrorDialog() {
        // close technical popup error IF exists
        Clicks.clickIfPresent("product_display.technical_error");
    }

    public static void selectRandomColor() {
        By colorEl = Elements.element("product_display.select_default_color");
        if (Elements.elementPresent(colorEl)) {
            Clicks.click(Elements.getRandomElement(colorEl, WebElement::isDisplayed));
            closeTechnicalErrorDialog();
        } else {
            logger.info("No coloring options found");
        }
    }

    public static void selectSwatches() {
        if (Elements.elementPresent(By.className("swatch"))) {
            Clicks.click(Elements.getRandomElement(By.className("swatch")));
            closeTechnicalErrorDialog();
        } else {
            logger.info("No Type swatches are found");
        }
    }

    public static int numColorOptions() {
        List<WebElement> colorSelection = Elements.findElements("product_display.select_default_color");
        return colorSelection == null ? 0 : colorSelection.size();
    }

    public static void selectQuantity(String quantity) {
        if (macys()) {
            DropDowns.selectByValue("product_display.quantity", quantity);
        } else {
            DropDowns.selectCustomText("product_display.quantity", "product_display.quanity_list_options", quantity);
        }
    }

    public static int numSizeOptions() {
        List<WebElement> sizeSelection = Elements.findElements("product_display.select_default_size");
        return sizeSelection == null ? 0 : sizeSelection.size();
    }

    public static void selectRandomSize() {
        By sizeEl = Elements.element("product_display.select_default_size");
        if (Elements.elementPresent(sizeEl)) {
            if (bloomingdales()) {
                List<WebElement> sizes = Elements.findElement(sizeEl).findElements(By.tagName("option"));
                sizes.remove(0);
                Clicks.click(Elements.getRandomElement(sizeEl, WebElement::isDisplayed));
                sizes.get(new Random().nextInt(sizes.size())).click();
            } else {
                Clicks.click(Elements.getRandomElement(sizeEl, WebElement::isDisplayed));
            }
            closeTechnicalErrorDialog();
            for (int i = 0; i < 10; i++) {
                if (!Elements.elementPresent("product_display.validation_error") && !Elements.elementPresent("product_display.out_of_stock")) {
                    return;
                }
                Clicks.click(Elements.getRandomElement(sizeEl, WebElement::isDisplayed));
            }
            Assert.fail("Could not select valid size");
        } else if (macys() && Elements.elementPresent("product_display.select_size_dropdown")) {
            DropDowns.selectRandomValue("product_display.select_size_dropdown");
        } else if (bloomingdales() && (Elements.elementPresent("product_display.select_size_dropdown") || Elements.elementPresent("product_display.select_size_dropdown_options"))) {

            if (MEW()) {    // BCOM MEW
                // BCOM MEW has dropdown with select element hence dropdown
                if (Elements.elementPresent("product_display.select_size_dropdown")) {
                    logger.debug("select size drop down");
                    DropDowns.selectByIndex("product_display.select_size_dropdown", 1);
                } else {
                    logger.debug("select size dropdown options");
                    Wait.secondsUntilElementPresent("product_display.select_size_dropdown_options", 10);
                    Clicks.click("product_display.select_size_dropdown_options");
                    List<String> sizeValues = Elements.findElements("product_display.select_default_size")
                            .stream().filter(e -> e.findElement(By.xpath("..")).findElements(By.className("size-select-size-unavailable")).size() == 0)
                            .map(WebElement::getText).collect(Collectors.toList());
                    if (Elements.elementPresent("product_display.close_size_overlay")) {
                        Clicks.click("product_display.close_size_overlay");
                    } else {
                        Wait.secondsUntilElementPresent("product_display.select_size_dropdown_options",15);
                        Clicks.javascriptClick("product_display.select_size_dropdown_options");
                    }
                    int r = new Random().nextInt(sizeValues.size());
                    DropDowns.selectCustomText("product_display.select_size_dropdown_options", "product_display.select_default_size", sizeValues.get(r));
                }
            } else { // BCOM Desktop
                List<String> sizeValues = DropDowns.getAllCustomValues("product_display.select_size_dropdown", "product_display.select_size_dropdown_options");
                sizeValues.remove(0);
                int r = new Random().nextInt(sizeValues.size());
                DropDowns.selectCustomText("product_display.select_size_dropdown", "product_display.select_size_dropdown_options", sizeValues.get(r));
            }

        } else {
            logger.info("No sizing options found");
        }
    }

    public static List<WebElement> findRecommendedProducts(String panel_position) {
        try {
            switch (panel_position) {
                case "horizontal":
                    return Elements.findElements(Elements.element("recommendations.horizontal_recommendations"));
                case "vertical":
                    return Elements.findElements(Elements.element("recommendations.vertical_recommendations"));
                default:
                    Assert.fail("ERROR - APP: Invalid Recommendation panel: " + panel_position);
                    break;
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // ignore - will return null anyway
        }
        return null;
    }

    public static void addRandomMemberProductOnMasterPDP() {
        try {
            Clicks.clickRandomElement("product_display.add_to_bag_button");
        } catch (NoSuchElementException e) {
            Assert.fail("addRandomMemberProductOnMasterPDP(): Unable to select product");
        }
    }

    public static boolean bopsEligible() {
        String text = Elements.getText(Elements.element("product_display.bops_availability_message"));
        return text.matches("Available for pick up:") || text.matches("(.*)Available at (.*)");
    }

    public static boolean addedToBag() {
        Wait.forPageReady();
        return (onPage("add_to_bag")
                || Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_dialog")
                || Wait.untilElementPresent("add_to_bag.header")
                || Wait.untilElementPresent("add_to_bag_dialog.master_add_to_bag_dialog"))
                && !Elements.elementPresent("product_display.validation_error");
    }

    public static void verifyPdpAttributes() throws EnvException {
        Elements.elementShouldBePresent("product_display.product_title");
        Elements.elementShouldBePresent("product_display.add_to_bag_button");
        Elements.elementShouldBePresent("product_display.price_box");
        Elements.elementShouldBePresent("product_display.product_id_div");
        Elements.elementShouldBePresent("product_display.quantity");
        Elements.elementShouldBePresent("product_display.product_image");
        Elements.elementShouldBePresent("product_display.add_to_wishlist_image");
        Elements.elementShouldBePresent("product_display.product_description_block");
        if (onPage("radical_pdp")) {
            Elements.elementShouldBePresent("product_display.social_icon_pinterest");
        } else {
            Elements.elementShouldBePresent("product_display.social_icon_facebook");
            Elements.elementShouldBePresent("product_display.social_icon_twitter");
            if (macys()) {
                Elements.elementShouldBePresent("product_display.social_icon_google_plus");
                Clicks.click("product_display.expand_special_offers");
                Clicks.click("product_display.expand_shipping_and_returns");
            } else {
                String[] tabs = new String[]{"details", "reviews", "offers", "shipping & returns"};
                for (String tab : tabs) {
                    Clicks.click(By.linkText(tab));
                }
            }
        }
    }

    public static void selectBopsStore() {
        Clicks.click("radical_pdp.check_stores");
        Wait.untilElementPresent("change_pickup_store_dialog.search_distance");
        DropDowns.selectByText("change_pickup_store_dialog.search_distance", "100 miles");
        Wait.untilElementPresent("change_pickup_store_dialog.search_button");
        Clicks.click("change_pickup_store_dialog.search_button");
        if (!Wait.untilElementPresent("change_pickup_store_dialog.select_store_button")) {
            Clicks.click("change_pickup_store_dialog.close");
        } else {
            Clicks.clickRandomElement("change_pickup_store_dialog.select_store_button");
        }
    }

    public static void clickAddToBagButton() throws Exception {
        Clicks.click("product_display.add_to_bag_button");
    }
}
