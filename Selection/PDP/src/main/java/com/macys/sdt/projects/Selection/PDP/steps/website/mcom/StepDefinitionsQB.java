package com.macys.sdt.projects.Selection.PDP.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;


public class StepDefinitionsQB extends StepUtils {

    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    SoftAssertions softly = new SoftAssertions();


    @And("^I click the \"([^\"]*)\" on quick bag$")
    public void click_the_button_on_quick_bag(String arg) throws Throwable {
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/20)");
        Thread.sleep(2000);
        Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
        Thread.sleep(5000);

        if (arg.equals("checkout")) {
            WebElement hoverQB = Elements.findElement("quick_bag.quickBagLink");
            Wait.secondsUntilElementPresent("quick_bag.quickBagLink", 10);
            Thread.sleep(3000);
            Clicks.hoverForSelection(hoverQB);
            softly.assertThat(Elements.elementPresent("quick_bag.quickbag_checkout")).as("quickbag_checkout").isEqualTo(true);
            Thread.sleep(3000);
            Clicks.click("quick_bag.quickbag_checkout");
        }
        else if (arg.equals("QBlink")) {
            Wait.secondsUntilElementPresent("quick_bag.quickBagLink", 10);
            Clicks.click("quick_bag.quickbag_checkoutLink");
        }
        else if (arg.equals("remove")) {
            WebElement hoverQB = Elements.findElement("quick_bag.quickBagLink");
            Wait.secondsUntilElementPresent("quick_bag.quickBagLink", 10);
            Clicks.hoverForSelection(hoverQB);
            Thread.sleep(3000);
            Clicks.hoverForSelection("quick_bag.remove_first_item");
            Thread.sleep(3000);
            Clicks.click("quick_bag.remove_first_item");
        }
        else if (arg.equals("product image")) {
            WebElement hoverQB = Elements.findElement("quick_bag.quickBagLink");
            Wait.secondsUntilElementPresent("quick_bag.quickBagLink", 20);
            Thread.sleep(3000);
            Clicks.hoverForSelection(hoverQB);
            softly.assertThat(Elements.elementPresent("quick_bag.quickbag_product_images")).as("product_image").isEqualTo(true);
            Thread.sleep(3000);
            List<WebElement> productImages = Elements.findElements(Elements.element("quick_bag.productImages"));
            for(WebElement link: productImages){
                if(!(link.getAttribute("href").contains("GIFTID"))) {
                    logger.info("Clicking on the " + link.getAttribute("href"));
                    Clicks.click(link);
                }
            }
        }
    }

    @Then("^I verify quickbag flyout on PDP")
    public void I_verify_quickbag_flyout_on_PDP() throws Throwable {

        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/20)");
//        scrollToLazyLoadElement("pdp.seoContainer");
        Thread.sleep(2000);
        Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");

        WebElement hoverQB = Elements.findElement("quick_bag.quickBagLink");
        Wait.secondsUntilElementPresent("quick_bag.quickBagLink", 10);
        Clicks.hoverForSelection(hoverQB);
        Wait.secondsUntilElementPresent("quick_bag.quick_bag_item_description", 10);
        Wait.secondsUntilElementPresent("quick_bag.qbFirstProdcutImg", 10);
        softly.assertThat(Elements.elementPresent("quick_bag.quickbag_individual_prices")).as("QuickBag Prices").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("quick_bag.price_bag_total")).as("QuickBag Prices").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("quick_bag.quickbag_checkout")).as("QuickBag Prices").isEqualTo(true);
        Thread.sleep(2000);
        softly.assertThat(Elements.elementPresent("quick_bag.remove_first_item")).as("remove button").isEqualTo(true);
        Clicks.hoverForSelection("quick_bag.remove_first_item");
        Clicks.click("quick_bag.remove_first_item");
        Thread.sleep(2000);
    }

    @Then("^I verify quickbag count is updating with \"([^\"]*)\" items in bag")
    public void I_verify_quickbag_count_is_updating(int arg) throws Throwable {

        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/20)");
//        scrollToLazyLoadElement("pdp.seoContainer");
        Thread.sleep(2000);
        Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");

        WebElement hoverQB = Elements.findElement("quick_bag.quickBagLink");
        Wait.secondsUntilElementPresent("quick_bag.quickBagLink", 10);

        Clicks.hoverForSelection(hoverQB);
        Wait.secondsUntilElementPresent("quick_bag.qbCount", 10);
        Thread.sleep(3000);
        Assert.assertTrue(Elements.elementPresent(Elements.element("quick_bag.qbCount")));
        String qbCount = Elements.findElement(Elements.element("quick_bag.qbCount")).getAttribute("aria-label").replace("Shopping bag has ", "").replace(" items", "");
        if(qbCount.contains("item"))
            qbCount = qbCount.replace("item", "").trim();
        int qtyCount = Integer.parseInt(qbCount);
        Assert.assertTrue(qtyCount >= arg);
        if(arg == 0) {
            Assert.assertTrue(qtyCount == arg);
            String expected_msg = "0 items in your bag. Shop great deals now!";
            String quickbag_Content = Elements.findElement(Elements.element("quick_bag.qbZeroItemCountMsg")).getText();
            softly.assertThat(quickbag_Content.toLowerCase().contains(expected_msg.toLowerCase())).as("QB: Empty Bag Msg").isEqualTo(true);
            logger.info(String.format("** There is " + qtyCount + " item shown in QB!\n** EmptyBag Msg:  " + quickbag_Content + "\n"));
        }
        else if(arg == 1) {
            logger.info(String.format("** There is " + qtyCount + " item shown in QB!\n"));
        }
        else {
            logger.info(String.format("** There are " + qtyCount + " items shown in QB!\n"));
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

}
