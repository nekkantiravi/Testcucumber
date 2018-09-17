package com.macys.sdt.projects.PurchaseAndDelivery.ResponsiveBag.steps.website.mcom;

import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.responsive_bag.ResponsiveBag;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.my_account.SignIn;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.Header;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static com.macys.sdt.framework.utils.StepUtils.onPage;
import static com.macys.sdt.framework.utils.StepUtils.shouldBeOnPage;

/**
 * Created by Eric L on 7/6/2017
 * Updated by Prashanth Kumar on 12/7/2017
 */
public class BagSteps {

    private static final Logger logger = LoggerFactory.getLogger(BagSteps.class);
    private int noOfItemsInBag = 0;
    private String USERID = "201306138";
    private String userId;
    private String sequenceNumber;

    /**
     * Navigates to the responsive empty bag pge
     */
    @Given("^I navigate to responsive empty bag page$")
    public void iNavigateToResponsiveEmptyBagPage() {
        Cookies.changeDomain(getUrlDomain());
        Cookies.deleteAllCookies();
        Navigate.to(ResponsiveBag.class);
    }

    /**
     * Navigates to the responsive bag pge
     */
    @Given("^I navigate to responsive bag page$")
    public void navigateToResponsiveBagPage() {
        Cookies.changeDomain(getUrlDomain());
        Cookies.deleteAllCookies();
        Cookies.setSingleSegment("2746");
        String segment = Cookies.getCookieValue("SEGMENT");

        if (!onPage("index")) {
            Navigate.visit("index");
        }
    }

    @Then("^I verify the bag icon is present on home page$")
    public void iVerifyBagIconPresence(){
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Assert.assertTrue("bag icon is present on home page", Wait
                .until(bag.bagIcon::isDisplayed, 10));
    }

    @And("^I clicked on Macys logo$")
    public void iClickedOnMacysLogo(){
        Navigate.get(Header.class).logo.click();
    }

    @Then("^I verify the checkout button is present on quick bag$")
    public void verifyCheckoutButton(){
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        WebDriver driver = WebDriverManager.getWebDriver();
        Actions actions = new Actions(driver);
        WebElement bagIcon = bag.bagIcon.getWrappedElement();
        Wait.until(bagIcon::isDisplayed,10);
        actions.moveToElement(bagIcon);
        actions.build().perform();
        WebElement bagCheckout = bag.checkoutFromBag.getWrappedElement();
        Assert.assertTrue("Checkout Button is present on quick bag", Wait
                .until(bag.checkoutFromBag::isDisplayed, 10));
    }

    /**
     * Verify the contents of the empty bag.
     * <p>
     * This includes empty bag message, sign in link, and continue shopping link.
     */
    @Then("^I verify that the empty bag is displayed.*$")
    public void verifyEmptyBagPage() {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Assert.assertTrue("Bag Page Wrapper not present on bag page", bag.bagPageWrapper.isDisplayed());
        // Verify empty bag message
        Assert.assertTrue("Empty Bag message is not displayed on responsive bag page", Wait
                .until(bag.emptyBag::isDisplayed, 10));
        // Verify signin link
        Assert.assertTrue("Empty Bag did not display sign in link", bag.signIn.isDisplayed());
        // Verify continue shopping link
        Assert.assertTrue("Empty Bag did not display continue shopping link", bag.continueShopping.isDisplayed());
    }

    @When("^I click the \"([^\"]*)\" button on the responsive bag page$")
    public void clickButtonOnResponsiveBag(String button) {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        switch (button) {
            case "signIn":
                bag.clickSignInButton();
                break;
            case "continueShopping":
                bag.clickContinueShoppingButton();
                break;
            case "dealsAndPromotions":
                bag.clickDealsAndPromotionButton();
                break;
            case "checkout":
                bag.clickCheckoutButton();
                break;
            case "bagIcon":
                bag.clickBagIcon();
                break;
            case "remove":
                sequenceNumber = bag.removeItem();
                break;
            case "remove_all":
                bag.removeItems();
                break;
            default:
                throw new IllegalArgumentException("No Button Present");
        }
    }

    @When("^I check the segment cookie is correct$")
    public void checkSegmentCookieIsCorrect() {
        String expectedValue = "2746";
        String segmentValue = Cookies.getCookieValue("SEGMENT");
        Assert.assertTrue("Segment cookie does not contain responsive bag experiment. Segment value: " + segmentValue
                                  + ",   Expected value: "
                                  + expectedValue, segmentValue
                                  .contains(expectedValue));
    }

    @Then("^I verify the page navigation for the \"([^\"]*)\" page$")
    public void iVerifyThePageNavigation(String name) {
        String selector;
        switch (name) {
            case "SignIn":
                SignIn signIn = Navigate.get(SignIn.class);
                Assert.assertTrue(signIn.isReady());
                break;
            case "dealsAndPromotions":
                shouldBeOnPage("deals_and_promotions1");
                break;
        }
    }

    /**
     * Navigates to the responsive bag page for the core bag.
     */
    @Given("^I navigate to core responsive bag page")
    public void iNavigateToCoreResponsiveBagPage() {
        userId = Cookies.getCookieValue("macys_online_uid");

        if (!onPage("index")) {
            Navigate.visit("index");
        }
        Cookies.changeDomain(getUrlDomain());
        Cookies.deleteAllCookies();
        Cookies.addCookie("macys_online_uid", userId);
        if (!onPage("index")) {
            Navigate.visit("index");
        }
        Navigate.browserRefresh();
    }


    @Given("^I navigate to responsive bag page")
    public void iNavigateToResponsiveBagPage() {
        Navigate.to(ResponsiveBag.class);
        shouldBeOnPage(Navigate.get(ResponsiveBag.class).pageAlias());
    }


    /**
     * Verify core bag is displayed
     */
    @Then("^I verify that the core bag is displayed.*$")
    public void verifyCoreBag() {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Assert.assertTrue("Bag Page Wrapper not present on bag page", bag.bagPageWrapper.isDisplayed());
        Wait.secondsUntilElementPresent("rbag.index.bagHeader", 10);
        Assert.assertTrue("Core Bag is not displayed with bag header", bag.bagHeader.isDisplayed());
        // Verify bag items
        Assert.assertTrue("Bag items are not displayed on responsive bag page", bag.bagItems.isDisplayed());
        int noOfItemsAfter = bag.bagItemContainer.size();
        Assert.assertTrue("There should be at least one bag item found", noOfItemsAfter > 0);
        // Verify order summary
        verifyOrderSummary();
    }

    @Then("^I verify the order summary$")
    public void verifyOrderSummarySection() {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Assert.assertTrue("Bag Page Wrapper not present on bag page", bag.bagPageWrapper.isDisplayed());
        // Verify order summary
        Assert.assertTrue("Order summary is not displayed on responsive bag page", bag.orderSummary.isDisplayed());
    }

    @Then("^I verify the order summary and checkout button$")
    public void verifyOrderSummary() {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Assert.assertTrue("Bag Page Wrapper not present on bag page", bag.bagPageWrapper.isDisplayed());
        // Verify order summary
        Assert.assertTrue("Order summary is not displayed on responsive bag page", bag.orderSummary.isDisplayed());
        // Verify checkout button
        Assert.assertTrue("checkout button is not displayed on responsive bag page", bag.checkout.isDisplayed());
    }

    @Then("^I verify that sticky order summary exists$")
    public void verifyStickyOrderSummary() {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Assert.assertTrue("Sticky order summary is not present on bag page", bag.stickyOrderSummary.isDisplayed());
        Assert.assertTrue("Sticky order summary is not present on bag page", bag.checkStickyOrderSummaryClass());
    }

    @Given("^I change the quantity dropdown to (\\d+) on bag page")
    public void iChangeQuantityDropdown(String quantity) {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Wait.until(bag.bagHeader::isDisplayed, 10);
        bag.jsChangeQuantity.selectByVisibleText(quantity);
    }

    @Then("^I verify the updated quantity is equal to (\\d+)$")
    public void verifyUpdatedQuantity(String expectedQty) {
        String currentQty = DropDowns.getSelectedValue(Elements.element("rbag.index.jsChangeQuantity"));
        Assert.assertTrue("Product quantity was not updated. Current value: " + currentQty + ",   Expected value: "
                                  + expectedQty, currentQty
                                  .contains(expectedQty));
    }

    @Then("^I verify the item is removed on responsive bag page$")
    public void verifyItemIsRemoved() {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        for (HtmlElement items : bag.getBagItemContainer()) {
            String sequenceNumberOfItem = items.getAttribute("sequenceNumber");
            Assert.assertNotEquals(sequenceNumber, sequenceNumberOfItem);
        }
    }

    @When("^I click the remove button link on bag page$")
    public void clickRemove() {
        //        Elements.findElements("rbag.index.bag-item-container").size();
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        if (Wait.until(bag.removeItem::isDisplayed)) {
            bag.removeItem.click();
        }
    }

    @Then("^I verify the item is removed$")
    public void verifyItemRemoved() {
        int noOfItemsAfter = Elements.findElements("rbag.index.bag-item-container").size();
        //Assert.assertNotEquals(no_of_items_after, noOfItemsInBag);
        Assert.assertEquals(noOfItemsAfter, 0);
    }

    @Then("I verify \"([^\"]*)\" button is available")
    public void verifyButton(String button) {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        switch (button) {
            case "checkout":
                Assert.assertTrue("Checkout button is not available", bag.getCheckout().exists());
                break;
            case "continue_shopping":
                Assert.assertTrue("Checkout button is not available", bag.getContinueShopping().exists());
                break;
            default:
                throw new IllegalArgumentException("No Button Present");
        }
    }

    @And("^I apply promo code \"([^\"]*)\" on responsive bag$")
    public void enterPromotion(String promoCode) {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        bag.getPromoInput().sendKeys(promoCode);
        bag.getPromoApply().click();

    }

    @Then("I verify promotion is applied")
    public void verifyPromotionApplied() {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Assert.assertTrue("Promo Applied description is not available", bag.getPromoAppliedDescription().exists());
    }

    @Then("I verify promotion \"([^\"]*)\" is visible")
    public void verifyPromotionThingAppear(String promotion) {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        switch (promotion) {
            case "pricing":
                Assert.assertTrue("promotion Applied description is not available", bag.getPromoAppliedDescription()
                                                                                       .exists());
                break;
            case "error message":
                Assert.assertTrue("Promo Error message is not available", bag.getNotificationError().exists());
                break;
            default:
                throw new IllegalArgumentException("No Button Present");
        }
    }

    @Then("I should go to signedIn page")
    public void navigatedToSignedInPage() {
        shouldBeOnPage(Navigate.get(SignIn.class).pageAlias());
    }

    @Then("^I verify header and footer on the bag$")
    public void verifyHeaderFooterDisplayed() {
        ResponsiveBag bag = Navigate.get(ResponsiveBag.class);
        Assert.assertTrue("Bag Icon not present on bag page", bag.bagIcon.isDisplayed());
        Assert.assertTrue("Shopping Bag header text is not displayed as expected", bag.headerText.getText()
                                                                                                 .contains("Shopping Bag"));
        Assert.assertTrue("Bag Id not present on bag page", bag.bagId.isDisplayed());
        Assert.assertTrue("Item Availability text is not displayed as expected", bag.availabilityText.getText()
                                                                                                     .contains("Item availability and pricing are not guaranteed."));
    }

    private String getUrlDomain() {
        String urlDomain = RunConfig.url.split("//")[1];
        return urlDomain;
    }

}

