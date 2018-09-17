package com.macys.sdt.projects.Customer.ReturnManagement.steps;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.ReturnsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

import static com.macys.sdt.framework.interactions.Navigate.switchWindow;


public class ReturnManagementSteps extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(ReturnManagementSteps.class);
    public JSONObject returnOrderDetails;
    String orderNumber = null;

    @When("^I navigate to submit page using \"([^\"]*)\" order as a \"([^\"]*)\" user$")
    public void iNavigateToSubmitPageUsingOrderAsAUser(String orderType, String userType) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        orderNumber = returnOrderDetails.getString("order_number");
        returnsPage.navigateToSelectionPage(orderType, userType.equals("guest"));
        returnsPage.selectItemsAndContinueToSubmitPage();
    }

    @Then("^I should see the overlay text$")
    public void iShouldSeeTheOverlayText(List<String> overlayText) throws Throwable {
        Clicks.click("return_submit.select_return_cancel");
        String actualCancelText = Elements.getText("return_submit.cancel_overlay_text");
        logger.info("Actual Cancel Order text is : " + actualCancelText);
        String expectedOverlayText = overlayText.get(0);
        expectedOverlayText = expectedOverlayText.replaceFirst("<order_number>", orderNumber);
        Assert.assertTrue("Error message on cancel return overlay is not correct. Actual: " + actualCancelText + ", Expected: " + expectedOverlayText, actualCancelText.equals(expectedOverlayText));
    }

    @And("^I select \"([^\"]*)\" in return cancel overlay$")
    public void iSelectInReturnCancelOverlay(String selectOption) throws Throwable {
        Clicks.click("return_submit.select_return_cancel");
        Wait.untilElementPresent(Elements.element("return_submit.select_return_cancel_yes"));
        switch (selectOption) {
            case "Yes":
                Clicks.click("return_submit.select_return_cancel_yes");
                logger.info("Selected Yes button in cancel overlay");
                break;
            case "No":
                Clicks.click("return_submit.select_return_cancel_no");
                logger.info("Selected no button in cancel overlay");
                break;
        }
    }

    @And("^I set cookie value$")
    public void iSetCookieValue() throws Throwable {
        String cookieValue = Cookies.getCookieValue("SNSGCs");
        logger.info("cookie value is : " + cookieValue);
        String cookieLastAccessTokenValue = cookieValue.split("_")[10];
        logger.info("cookie value is : " + cookieLastAccessTokenValue);
        String modifiedCookieLastAccessTokenValue = cookieValue.split("_")[10].replaceAll("1", "2");
        logger.info("cookie value is : " + modifiedCookieLastAccessTokenValue);
        String updatedCookieValue = cookieValue.replaceFirst(cookieLastAccessTokenValue, modifiedCookieLastAccessTokenValue);
        logger.info("cookie value is : " + updatedCookieValue);
        Cookies.deleteCookie("SNSGCs");
        Cookies.addCookie("SNSGCs", updatedCookieValue);
    }

    @Then("^I navigate to return confirmation page after the session expires$")
    public void iNavigateToReturnConfirmationPageAfterTheSessionExpires() throws Throwable {
        Wait.secondsUntilElementPresent("return_submit.submit_return", 15);
        Clicks.click("return_submit.submit_return");
    }

    @And("^I navigate to selection page using \"([^\"]*)\" order as a \"([^\"]*)\" user$")
    public void iNavigateToSelectionPageUsingOrderAsAUser(String orderType, String userType) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        HashMap<String, String> order = new HashMap<>();
        order.put("return_order", orderType);
        returnOrderDetails = Utils.getReturnOrders(order);
        orderNumber = returnOrderDetails.getString("order_number");
        returnsPage.navigateToSelectionPage(orderType, userType.equals("guest"));
    }

    @When("^I select \"([^\"]*)\" line item reason as \"([^\"]*)\" in reason for return$")
    public void iSelectLineItemReasonAsInReasonForReturn(String lineItemCount, String reason) throws Throwable {
        ReturnsPage returnsPage = new ReturnsPage();
        returnsPage.selectItemsWithSizeDidNotFitReasonAndContinueToSubmitPage(lineItemCount, reason, returnOrderDetails);
    }

    @And("^I navigate to confirmation page by \"([^\"]*)\" pickup details$")
    public void iNavigateToConfirmationPageByPickupDetails(String arg0) throws Throwable {
        Wait.secondsUntilElementPresent("return_submit.submit_return", 15);
        Clicks.click("return_submit.submit_return");
    }

    @And("^I select \"([^\"]*)\" from the \"([^\"]*)\" page$")
    public void iSelectFromThePage(String printMailingLabel, String page) throws Throwable {
        switch (page) {
            case "order details":
                Wait.forPageReady();
                Clicks.click("order_details.print_mailing_label");
                switchWindow(1);
                Wait.forPageReady();
                Wait.untilElementPresent("return_confirmation.print_shipping_label_top");
                Clicks.click("return_confirmation.print_shipping_label_top");
                break;
            case "confirmation":
                Wait.forPageReady();
                Wait.untilElementPresent("return_confirmation.print_shipping_label_top");
                Clicks.click("return_confirmation.print_shipping_label_top");
                Wait.forPageReady();
                break;
        }
    }

    @Then("^I refresh the page$")
    public void iRefreshThePage() throws Throwable {
        Wait.forPageReady();
        WebDriverManager.getWebDriver().navigate().refresh();
        Wait.forPageReady();
    }

    @Given("^I should (see|not see) image displayed for each line item$")
    public void iShouldSeeImageDisplayedForEachLineItem(String display_element) throws Throwable {
        Wait.untilElementPresent("orde_details.product_images");
        switch (display_element) {
            case "see": {
                List<WebElement> imageSource = Elements.findElements("return_selection.product_image_for_all_items");
                for (int i = 1; i <= imageSource.size(); i++) {
                    String srcValue = Elements.getElementAttribute(Elements.paramElement("return_selection.product_image_for_all_items_src", i + ""), "src");
                    if (macys()) {
                        Assert.assertTrue("Image is available", !srcValue.contains("imageUnavailable"));
                    }
                    if (bloomingdales()) {
                        Assert.assertTrue("Image is available", !srcValue.contains("image_not_available_thumbnail"));
                    }
                }
                break;
            }
            case "not see": {
                List<WebElement> imageSource = Elements.findElements("return_selection.product_image_for_all_items");
                for (int i = 1; i <= imageSource.size(); i++) {
                    String srcValue = Elements.getElementAttribute(Elements.paramElement("return_selection.product_image_for_all_items_src", i + ""), "src");
                    if (macys()) {
                        Assert.assertTrue("Image is not available", srcValue.contains("imageUnavailable"));
                    }
                    if (bloomingdales()) {
                        Assert.assertTrue("Image is not available", srcValue.contains("image_not_available_thumbnail"));
                    }
                }
                break;
            }
        }
    }

    @Given("^I select (Yes|No) in cancel return overlay$")
    public void iSelectInCancelReturnOverlay(String select_option) throws Throwable {
        Wait.forPageReady();
        Clicks.click("return_selection.cancel_order_button");
        Wait.forPageReady();
        switch (select_option) {
            case "Yes": {
                Clicks.click("return_selection.order_cancel_yes_button");
                logger.info("selected yes button in cancel overlay");
                break;
            }
            case "No": {
                Clicks.click("return_selection.order_cancel_no_button");
                logger.info("selected no button in cancel overlay");
                break;
            }
        }
    }

    @Then("^I should navigate to order details page$")
    public void iShouldNavigateToOrderDetailsPage() throws Throwable {
        Utils.threadSleep(6000, "Waiting for page load");
        Assert.assertTrue("Not Navigate to Order Details Page", onPage("order_details"));
    }

    @Then("^I should see error message above the order details header$")
    public void iShouldSeeErrorMessageAboveTheOrderDetailsHeader(List<String> messaage) throws Throwable {
        if (macys())
            Assert.assertTrue("", Elements.getText("return_selection.error_message").equals(messaage.get(0)));
        else {
            String firstErrorMessage = Elements.getText("return_selection.error_message");
            firstErrorMessage = firstErrorMessage.replaceAll("\\n", " ");
            Assert.assertTrue("", firstErrorMessage.equals(messaage.get(0)));
        }
    }

    @Given("^I attempt to continue in return selection page$")
    public void iAttemptoContinueInReturnSelectionPage() throws Throwable {
        Wait.forPageReady();
        Clicks.click(Elements.element("return_selection.submit_return"));
        Wait.forPageReady();
        logger.info("Clicked on submit return button in return selection page.");
    }

    @When("^I select (orignal_payment|giftcard_payment|any_refund) option from refund section$")
    public void iSelectOptionFromRefundSection(String option) throws Throwable {
        switch (option) {
            case "orignal_payment":
                Clicks.click("return_selection.credit_refund");
                break;
            case "giftcard_payment":
                Clicks.click("return_selection.gift_card_refund");
                break;
            default:
                Clicks.click("return_selection.refund_method");
        }
    }
    //The following method will work only for Chrome browser. For migration we are catering for Chrome browser as of now. If required in future then we will modity this method for other browser.
    @Then("^I should see call to action as find the right size option under reason for return$")
    public void iShouldSeeCallToActionAsFindTheRightSizeOptionUnderReasonForReturn() throws Throwable {
        //Applying this 10 sec hard wait to cater the print window. In future we will try to remove this wait.
        Thread.sleep(10000);
        if(WebDriverManager.getWebDriver().getWindowHandles().size()>=3) {
            WebDriverManager.getWebDriver().switchTo().window(WebDriverManager.getWebDriver().getWindowHandles().toArray()[2].toString());
            JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getWebDriver();
            executor.executeScript("document.getElementsByClassName('cancel')[0].click();");
            WebDriverManager.getWebDriver().switchTo().window(WebDriverManager.getWebDriver().getWindowHandles().toArray()[1].toString());
        }
        else
        {
            WebDriverManager.getWebDriver().switchTo().window(WebDriverManager.getWebDriver().getWindowHandles().toArray()[1].toString());
            JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getWebDriver();
            executor.executeScript("document.getElementsByClassName('cancel')[0].click();");
            WebDriverManager.getWebDriver().switchTo().window(WebDriverManager.getWebDriver().getWindowHandles().toArray()[0].toString());
        }
        Assert.assertTrue("Find the right size link should be present", Elements.elementPresent("return_confirmation.find_right_size"));
        logger.info("Call to Action is visible for line items");
    }

    @And("^I should be able to add items to bag on header$")
    public void iShouldBeAbleToAddItemsToBagOnHeader() throws Throwable {
        Wait.untilElementPresent("return_confirmation.find_right_size");
        Clicks.click("return_confirmation.find_right_size");
        //Applying this 7 sec hard wait to cater the print window. In future we will try to remove this wait.
        Thread.sleep(7000);
        Wait.untilElementPresent("return_confirmation.add_to_bag");
        Clicks.click("return_confirmation.add_to_bag");
        Wait.forPageReady();
        Wait.untilElementPresent("return_confirmation.item_added_to_bag");
        Assert.assertTrue("Item should be Added to bag Succesfully", Elements.elementPresent("return_confirmation.item_added_to_bag"));
        logger.info("Item Added to bag Succesfully");
    }
}