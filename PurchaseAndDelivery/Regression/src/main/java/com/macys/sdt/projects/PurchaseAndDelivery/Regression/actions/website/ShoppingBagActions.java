package com.macys.sdt.projects.PurchaseAndDelivery.Regression.actions.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.Promotion;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.ShoppingBag;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;
import java.text.DecimalFormat;


public class ShoppingBagActions {

    public static void checkLineItemMessages(Promotion promotion) {
        StepUtils.shouldBeOnPage("shopping_bag");
        for (int i = 0; i < promotion.products.size(); i++) {
            if (promotion.products.get(i).isOverThreshold) {
                validateOverThreshold(promotion, promotion.products.get(i));
            }
        }
    }

    private static void validateOverThresholdMessage(int position, Promotion promotion, Product product) {
        ShoppingBag shoppingBag = Navigate.get(ShoppingBag.class);
        // 1st: The Over Threshold message should be displayed:
        // in case there's no OT promo displayed:
        Assert.assertTrue(String.format("The product %1$s there's no OT promo displayed", product),
                          shoppingBag.overThresholdPromos.size() > position);
        // finding the OT container that corresponds to the product's position in the bag
        HtmlElement itemOverThresholdPromosContainer = shoppingBag.overThresholdPromos.get(position);

        // Now, we need to find all of the OT messages that are displayed for the particular product
        List<WebElement> itemOverThresholdPromos = itemOverThresholdPromosContainer
                .findElements(By.className("itemPromoPricingKey"));

        // Now, we need to make sure there's only one OT message displayed
        Assert.assertTrue(String.format("The product %1$s has more than one OT promotion", product),
                          itemOverThresholdPromos.size() == 1);

        // Now, let's check if the message is as expected, if offer name line 1 is empty, then the message should be
        // checkout description
        WebElement overThresholdPromo = itemOverThresholdPromos.get(0);
        String actualMessage = overThresholdPromo.getText().trim();
        String expectedMessage;
        if (promotion.name.isEmpty()) {
            expectedMessage = String
                    .format("%1$s %2$s", promotion.otCopy, promotion.description.trim());
        } else {
            expectedMessage = String.format("%1$s %2$s", promotion.otCopy, promotion.name.trim());
        }
        Assert.assertTrue(String.format("For the product %1$s the OT message is not as expected, expected: %2$s, " +
                                                "found: %3$s", product, expectedMessage, actualMessage),
                          actualMessage.equals(expectedMessage) && overThresholdPromo.isDisplayed());

        // Now, it's time to check if the tooltip is present, for OT, there should be no tooltip
        validateToolTip(itemOverThresholdPromosContainer, promotion, product);
    }

    private static void validateToolTip(HtmlElement itemPromosContainer, Promotion
            promotion, Product product) {
        ShoppingBag shoppingBag = Navigate.get(ShoppingBag.class);
        if (product.isOverThreshold) {
            Assert.assertTrue(String.format("For the product: %1$s, there are tooltips displayed", product),
                              itemPromosContainer
                                      .findElements(By.className("promo-tooltip")).size() == 0);
        } else {
            Assert.assertTrue(String.format("For the product: %1$s, there's no tooltip displayed", product),
                              itemPromosContainer
                                      .findElements(By.className("promo-tooltip")).size() > 0);
            WebElement toolTip = itemPromosContainer.findElements(By.className("promo-tooltip")).get(0);
            Assert.assertTrue(String.format("For the product: %1$s, the tooltip is not displayed", product), toolTip
                    .isDisplayed());
            Clicks.click(toolTip);
            shoppingBag.waitForReady();
            Wait.until(shoppingBag.promoTooltipContent::isDisplayed);
            String actualToolTipTitle = shoppingBag.promoTooltipContent.findElement(By.className("promo-title"))
                                                                       .getText(); //shoppingBag
            // .promoTooltipContentPromoTitle.getText();
            Assert.assertTrue(String.format("For the product: %1$s, the title of the tooltip displayed for the promo:" +
                                                    " " +
                                                    "%2$d, is not Offer Details, found: %3$s", product, promotion
                                                    .id, actualToolTipTitle),
                              actualToolTipTitle
                                      .equals("Offer Details"));
            String actualPromotionInfo = Elements.findElement(By.id("promo-tooltip-content"))
                                                 .findElement(By.className("promo-info")).getText().trim();
            String expectedPromotionInfo = promotion.legalDisclaimer.trim();
            Assert.assertTrue(String.format("For the product %1$s, the content of the tooltip displayed of the promo:" +
                                                    " " +
                                                    "%2$d is not as expected. Expected: %3$s, found: %4$s", product,
                                            promotion
                                                    .id, expectedPromotionInfo, actualPromotionInfo),
                              actualPromotionInfo
                                      .equals(expectedPromotionInfo));
            Elements.findElement(By.className("container-close")).click();
        }
    }


    private static void validateOverThreshold(Promotion promotion, Product product) {
        // first, we need to find the product in the bag and record its position
        ShoppingBag shoppingBag = Navigate.get(ShoppingBag.class);
        int position = shoppingBag.locateProductPosition(product);
        Assert.assertTrue(String.format("The product %1$s is not in the bag", product), position > -1);

        //after finding the position of the product in the bag, now it's time to start the validations
        //validate OT message
        validateOverThresholdMessage(position, promotion, product);

        //validade Price with Offers
        validateFinalTotal(position, product);

    }

    private static boolean isStrikedOut(HtmlElement element) {
        return element.getAttribute("class").contains("totalStrike");
    }

    private static void validateFinalTotal(int position, Product product) {
        ShoppingBag shoppingBag = Navigate.get(ShoppingBag.class);
        // the price should be crossed out
        HtmlElement actualItemPrice = shoppingBag.itemsTotals.get(position);
        DecimalFormat df = new DecimalFormat("#.00");
        String expectedItemPrice = "$" + df.format(product.individualPrice * product.quantity);
        Assert.assertTrue(String.format("For the product: %1$s, the regular price is not as expected. Expected: %2$s," +
                                                " found: %3$s", product, expectedItemPrice, actualItemPrice.getText()),
                          actualItemPrice.getText().equals(expectedItemPrice) && actualItemPrice.isDisplayed());
        Assert.assertTrue(String.format("For the product: %1$s, the price is not crossed out", product),
                          isStrikedOut
                                  (actualItemPrice));

        //validate the copy
        Assert.assertTrue(String.format("Fot the product: %1$s, there's no final cost displayed", product),
                          shoppingBag
                                  .itemsFinalTotalCopy
                                  .size() > position);
        HtmlElement actualTotalCopyElement = shoppingBag.itemsFinalTotalCopy.get(position);
        String actualTotalCopy = actualTotalCopyElement.getText();
        String expectedFinalTotalCopy = "Price with Offer(s)";
        Assert.assertTrue(String.format("For the product: %1$s, the final total copy is not as expected, expected: " +
                                                "%2$s, found: %3$s", product, expectedFinalTotalCopy,
                                        actualTotalCopy),
                          actualTotalCopy.equals(expectedFinalTotalCopy) && actualTotalCopyElement.isDisplayed());

        //validate the new price
        Assert.assertTrue(String.format("For the product: %1$s, there's no final price displayed", product),
                          shoppingBag
                                  .itemsFinalTotal
                                  .size() > position);
        String expectedFinalTotal = "$" + df.format(product.salePrice);
        HtmlElement actualFinalTotalElement = shoppingBag.itemsFinalTotal.get(position);
        String actualFinalTotal = actualFinalTotalElement.getText();
        Assert.assertTrue(String.format("For the product: %1$s, the final total is not as expected. Expected: %2$s " +
                                                "found: %3$s", product, expectedFinalTotal, actualFinalTotal),
                          actualFinalTotal.equals(expectedFinalTotal) && actualFinalTotalElement.isDisplayed());
        Assert.assertTrue(String.format("For the product: %1$s, the final total is striked out", product), !isStrikedOut
                (actualFinalTotalElement));
    }
}