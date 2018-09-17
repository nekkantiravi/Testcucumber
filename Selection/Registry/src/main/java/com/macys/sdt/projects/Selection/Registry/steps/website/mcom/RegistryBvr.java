package com.macys.sdt.projects.Selection.Registry.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.steps.website.Registry;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.*;

public class RegistryBvr {

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);

    @Then("^I should (see|not see) NGF message on BVR$")
    public void iShouldSeeNGFMessageOnBVR(String option ,List<String> expected_message) throws Throwable{
        Wait.forPageReady();
        if(option.equals("see")) {
            Assert.assertTrue(Elements.elementPresent("registry_bvr.ngf_message"));
            Assert.assertTrue("This item will be discontinued soon. is verified", Elements.getText("registry_bvr.ngf_message").trim().contains(expected_message.get(0)));
            Assert.assertTrue("See Similar Items is verified.", Elements.getText("registry_bvr.similar_items_link").trim().contains(expected_message.get(1)));
            logger.info("Expected NGF message is verified successfully");
        } else {
            Assert.assertFalse(Elements.elementPresent("registry_bvr.ngf_message"));
            Assert.assertFalse("This item will be discontinued soon. See Similar Items", Elements.getText("registry_bvr.ngf_message").trim().contains(expected_message.get(0))? true : false);
            logger.info("NGF message not seen is verified successfully.");
        }
    }

    @When("^I click on similar items link from NGF message$")
    public void iClickOnSimilarItemsLinkFromNGFMessage() throws Throwable {
        Wait.untilElementPresent("registry_bvr.similar_items_link");
        Clicks.click("registry_bvr.similar_items_link");
        logger.info("Successfully navigate to category sub splash page.");
    }

    @Then("^I should be navigated to sub category page in registry mode$")
    public void iShouldBeNavigatedToSubCategoryPageInRegistryMode() throws Throwable {
        pausePageHangWatchDog();
        try {
            shouldBeOnPage("category_sub_splash");
            if(WebDriverManager.getCurrentUrl().contains("luggage")? false : true){
                boolean registry_mode = (WebDriverManager.getCurrentUrl().contains("wedding-registry"))? true : false;
                Assert.assertTrue("Category sub splash page is in registry mode",registry_mode);
            }
        } catch (Exception e) {
            Assert.fail("Category sub splash page is not in registry mode " + e.getMessage());
        }
        logger.info("Successfully verified the page is category sub splash and  Url in registry mode.");
        resumePageHangWatchDog();
    }


    @And("^I add registry pre event ngf date product to BVR page from standard PDP$")
    public void iAddRegistryPreEventNgfDateProductToBVRPageFromStandardPDP() throws Throwable {
        int retries = 5;
        for (int count = 0; count < retries && !Elements.elementPresent("add_to_registry_dialog.add_to_bag_view_registry"); count++) {
            if (ProductDisplay.isMasterMemberPage()) {
                Clicks.clickRandomElement("product_display.add_to_registry");
            } else {
                Clicks.click("product_display.add_to_registry");
            }
            Wait.secondsUntilElementPresent("add_to_registry_dialog.add_to_bag_view_registry", 5);
            Clicks.clickIfPresent("product_display.technical_error");
            if (isErrorPaneVisible()) {
                Clicks.click("home.popup_close");
            }
            if (macys() && Elements.anyPresent(By.className("close-grey-tiny"))) {
                Assert.assertTrue("ERROR - DATA : Product is not eligible to add to registry !!", Elements.findElement(By.className("close-grey-tiny")).getText().contains("This item is not registrable"));
            }
        }
        try {
            Wait.forPageReady();
            Wait.untilElementPresent("add_to_registry_dialog.add_to_bag_view_registry");
            Clicks.click("add_to_registry_dialog.add_to_bag_view_registry");
        } catch (NoSuchElementException e) {
            Assert.fail("Unable to add product to registry" + e);
        }
    }

}


