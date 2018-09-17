package com.macys.sdt.projects.Marketing.Promotion.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import com.macys.sdt.projects.Marketing.OCWallet.steps.website.mcom.OCWallet;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.PromotionService;
import com.macys.sdt.projects.Marketing.Promotion.utils.PromoUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class PromotionSteps extends StepUtils {

    private OCWallet ocwallet = new OCWallet();

    private PageNavigation pageNavigation = new PageNavigation();
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();

    @Given("^I visit the web site as a registered user with \"([^\"]*)\" promotion in wallet$")
    public void iVisitWebSiteAsARegisteredUserWithPromotionInWallet(String promoType) throws Throwable {

        pageNavigation.I_navigate_to_create_profile_page();
        myAccountSteps.iCreateANewProfile();
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        Wait.forPageReady();
        myAccountSteps.iClickOnAddOfferOnWalletPage();
        if (PromoUtils.promoData == null) {
            PromoUtils.promoData = PromoUtils.getPromoData(promoType);
        }
        Wallet.addValidOffer(PromoUtils.promoData.getString("promo_code"));
    }


    @And("^I verify the APPLY promotion details in shopping bag page$")
    public void iVerifyTheAPPLYPromotionDetailsInShoppingBagPage() throws Throwable {

        String promo = PromoUtils.promoData.getString("promo_code");
        assertTrue("Apply promotion option not found in shopping bag",
                Elements.elementPresent("shopping_bag.apply_promotion_option"));
        assertTrue("Best available offer text not found in shopping bag",
                Elements.elementPresent("shopping_bag.best_available_offer_text"));
        Map<String, String> promoCode = new PromotionService().getPromotionDetails(promo);

        String expPromoHeading = promoCode.get("promoHeading") + " " + promoCode.get("promoSubHeading");
        String actPromoHeading = Elements.findElement("shopping_bag.promo_heading_apply_option").getText();
        assertTrue("Promotion Heading on shopping bag not matching with DB. Expected Promotion Heading: " + expPromoHeading +
                ", Actual Promotion Name: " + actPromoHeading, actPromoHeading.equalsIgnoreCase(expPromoHeading));

        String actValidityDates = Elements.findElement("shopping_bag.promo_validity").getText().trim();
        String expValidityDates = "Valid " + promoCode.get("effectiveDate") + " - " + promoCode.get("expirationDate");
        assertTrue("Promotion Dates on shopping bag not matching with DB. Expected Promotion Dates: " +
                expValidityDates + " Actual Promotion Dates: " + actValidityDates, actValidityDates.equals(expValidityDates));
        assertTrue("Exclusions & Details link text not displayed for the apply promotion option",
                Elements.findElement("shopping_bag.exclusion_details").getText().trim().equalsIgnoreCase("exclusions & details"));
        assertTrue("Exclusions & Details text is not a link in shopping bag",
                Elements.findElement("shopping_bag.exclusion_details").getTagName().equals("a"));
        assertTrue("Save text in Apply promotion not displayed",
                Elements.elementPresent("shopping_bag.save_text"));

    }

    @And("^I click on APPLY button to apply the promotion$")
    public void iClickOnAPPLYButtonToApplyThePromotion() throws Throwable {

        Wait.untilElementPresent("shopping_bag.apply_promotion_option");
        Clicks.click("shopping_bag.apply_promotion_option");
    }

    @Then("^I should see promotion applied details in the item section$")
    public void iShouldSeePromotionAppliedDetailsInTheItemSection() throws Throwable {

        if (bloomingdales()) {
            PromoUtils.verifyPromotionInItemSection();
        } else {

            Utils.threadSleep(3000, null);
            String globalType = PromoUtils.promoData.getString("global");
            String actualItemPromotionDesc = Elements.findElement("shopping_bag.item_promo_desc").getText();
            String expItemPromotionDesc = null;
            String promoType = PromoUtils.promoData.getString("promo_type");

            if (globalType.equals("true")) {
                expItemPromotionDesc = PromoUtils.promoData.getString("promo_description");
            } else {
                expItemPromotionDesc = "Offer Applied: " + PromoUtils.promoData.getString("promo_code");
            }

            if (promoType.equals("GWP") || promoType.equals("PWP")) {
                if (promoType.equals("PWP")) {
                    PromoUtils.addPWPItemToBag();
                }

                Assert.assertTrue("Gift item could not be found in shopping bag page",
                        Elements.elementPresent("shopping_bag.gwp_pwp_container"));
            } else {
                assertTrue("Applied promotion desc does not match with expected",
                        actualItemPromotionDesc.equals(expItemPromotionDesc));
                assertTrue("Applied promotion amount not found in shopping bag item section",
                        Elements.elementPresent("shopping_bag.item_promotion_amount"));
            }
        }
    }

    @Then("^I should see promotion applied details in wallet section$")
    public void iShouldSeePromotionAppliedDetailsInWalletSection() throws Throwable {

        Utils.threadSleep(3000, null);
        String promoCode = PromoUtils.promoData.getString("promo_code");
        Wait.untilElementPresent("shopping_bag.promo_code_remove");
        if (Elements.elementPresent("shopping_bag.more_link")) {
            Clicks.click("shopping_bag.more_link");
        }

        Map<String, String> promo = new PromotionService().getPromotionDetails(promoCode);
        String expPromoDesc = promo.get("promoDesc");
        String actPromoDesc = Elements.findElement("shopping_bag.wallet_promo_desc").getText();
        String[] actPromoDescSplit = actPromoDesc.contains("More") ? actPromoDesc.split("More") : actPromoDesc.split("Less");
        actPromoDesc = actPromoDescSplit[0].trim();
        assertTrue("Promo desc could not be validated in shopping bag wallet section",
                actPromoDesc.equalsIgnoreCase(expPromoDesc));
        assertTrue("Remove link text not found in shopping bag wallet section",
                Elements.findElement("shopping_bag.remove_promotion").getText().equals("Remove"));
        assertTrue("Remove link text not found in shopping bag wallet section",
                Elements.findElement("shopping_bag.remove_promotion").getTagName().equals("a"));
      //  assertTrue("Applied promotion amount not found in shopping bag wallet section",
              //  Elements.elementPresent("shopping_bag.wallet_promotion_amount"));
    }

    @Then("^I should (not see|see) the applied promotion in checkout page$")
    public void iShouldSeeTheAppliedPromotionInCheckoutPage(String option) throws Throwable {

        String promo = PromoUtils.promoData.getString("promo_code");
        if (option.equals("see")) {
            Map<String, String> promoCode = new PromotionService().getPromotionDetails(promo);

            String expPromoDescription = promoCode.get("promoDesc");

            if (bloomingdales()) {
                expPromoDescription = "Promo Code: " + expPromoDescription + " remove";
            }
            String actualItemPromotionDesc = Elements.findElement("responsive_checkout_signed_in.promo_description")
                    .getText();
            assertTrue("Applied promotion amount not found in checkout page",
                    Elements.elementPresent("responsive_checkout_signed_in.promo_amount"));
        } else {
            assertTrue("Promotion details found in checkout page for global promotion",
                    !Elements.elementPresent("responsive_checkout_signed_in.promo_details"));
        }
    }

    @Then("^I should see \"([^\"]*)\" link$")
    public void iShouldSeeLink(String linkText) throws Throwable {

        assertTrue("See All Offers text could not be verified",
                Elements.findElement("shopping_bag.see_all_my_offers").getText().equals("See All Offers"));
    }

    @And("^I click on See All Offers link$")
    public void iClickOnSeeAllOffersLink() throws Throwable {
        Clicks.click("shopping_bag.see_all_my_offers");
    }

    @Then("^I should see the eligible promotion in the apply an offer window$")
    public void iShouldSeeTheEligiblePromotionInTheApplyAnOfferWindow() throws Throwable {

    }

    @Then("^I verify the details in apply an offer window$")
    public void iVerifyTheDetailsInApplyAnOfferWindow() throws Throwable {

        Utils.threadSleep(4000, null);
        String promo = PromoUtils.promoData.getString("promo_code");
        Map<String, String> promoCode = new PromotionService().getPromotionDetails(promo);
        String expPromoHeading = promoCode.get("promoHeading") + " " + promoCode.get("promoSubHeading");
        String expValidityDates = promoCode.get("expirationDate");
        String firstName = TestUsers.getCustomer(null).getUser().getProfileAddress().getFirstName();

        assertTrue("Wallet image not found in apply an offer window",
                Elements.elementPresent("shopping_bag.wallet_overlay_img"));
        assertTrue("Wallet header text could not be verified",
                Elements.findElement("shopping_bag.wallet_overlay_heading").getText().
                        equals(firstName + "'s" + " Wallet"));
        assertTrue("Wallet static text could not be verified",
                Elements.findElement("shopping_bag.wallet_overlay_static_text").getText()
                        .equals("We picked these offers based on what's in your bag—select one and apply it!"));
        assertTrue("Find different offer link text could not be validated",
                Elements.findElement("shopping_bag.find_different_offer").getText()
                        .equalsIgnoreCase("find a different offer"));
    }

    @Then("^I should see the eligible promotion details in apply an offer window$")
    public void iShouldSeeTheEligiblePromotionDetailsInApplyAnOfferWindow() throws Throwable {

        Wait.untilElementPresent("shopping_bag.wallet_overlay_img");
        String promo = PromoUtils.promoData.getString("promo_code");
        Map<String, String> promoCode = new PromotionService().getPromotionDetails(promo);
        String expPromoHeading = promoCode.get("promoHeading") + " " + promoCode.get("promoSubHeading");
        String expValidityDates = promoCode.get("expirationDate");

        assertTrue("Eligible promotion radio not found in apply offer overlay",
                Elements.elementPresent(By.id("promo" + promo)));
        assertTrue("Eligible promotion header does not match with expected",
                Elements.findElement(By.xpath("//label[@for='promoBUY2']")).getText().equalsIgnoreCase(expPromoHeading));
        String actualPromoApplied = Elements.findElement("shopping_bag.apply_offer_overlay_promo_code_1")
                .getText() + Elements.findElement("shopping_bag.apply_offer_overlay_promo_code_2").getText();
        assertTrue("Promo code in apply an offer overlay does not match with expected",
                actualPromoApplied.equals("Promo code:" + promo));
        assertTrue("Promo code expiry in apply an offer overlay does not match with expected",
                Elements.findElement("shopping_bag.apply_offer_overlay_expiry").getText().substring(17).trim().equals(expValidityDates));
        assertTrue("Promo code exclusions in apply an offer overlay does not match with expected",
                Elements.findElement("shopping_bag.apply_offer_overlay_exclusions").getText()
                        .equals("Exclusions & Details"));
        assertTrue("Promo code amount in apply an offer overlay is not present",
                Elements.elementPresent("shopping_bag.apply_offer_overlay_promo_amount"));

    }

    @Then("^I should (not see|see) APPLY promotion option$")
    public void iShouldNotSeeAPPLYPromotionOption(String option) throws Throwable {

        Utils.threadSleep(3000, null);
        if (option.equals("not see")) {
            assertTrue("Apply promotion option found in shopping bag",
                    !Elements.elementPresent("shopping_bag.apply_promotion_option"));
        } else {
            assertTrue("Apply promotion option not found in shopping bag",
                    Elements.elementPresent("shopping_bag.apply_promotion_option"));
        }
    }

    @And("^I should (not see|see) See All Offers link$")
    public void iShouldNotSeeSeeAllOffersLink(String option) throws Throwable {

        Wait.forPageReady();
        if (option.equals("not see")) {
            assertTrue("See All Offers link found",
                    !Elements.elementPresent("shopping_bag.see_all_my_offers"));
        } else {
            assertTrue("See All Offers link not found",
                    Elements.elementPresent("shopping_bag.see_all_my_offers"));
        }
    }

    @Then("^I cancel out of apply an offer overlay window$")
    public void iCancelOutOfApplyAnOfferOverlayWindow() throws Throwable {

        Wait.forPageReady();
        Clicks.click("shopping_bag.cancel_overlay_button");
    }

    @Then("^I select eligible promotion and click on apply$")
    public void iSelectEligiblePromotionAndClickOnApply() throws Throwable {

        Clicks.click("shopping_bag.apply_overlay_button");
    }

  /*  @Then("^I enter the promo code in promotion field and click on apply$")
    public void iEnterThePromoCodeInPromotionFieldAndClickOnApply() throws Throwable {

        Wait.forPageReady();
        TextBoxes.typeTextbox("shopping_bag.apply_promo_textfield", "BUY2");
        Clicks.click("shopping_bag.apply_promo_button");
    }*/

    @And("^I adjust qty to make the promotion (available|unavailable)$")
    public void iAdjustQtyToMakeThePromotionAvailable(String option) throws Throwable {

        String qty = PromoUtils.promoData.getString("qty_trigger");
        if (option.equals("available")) {
            DropDowns.selectByText("shopping_bag.quantities", qty);
            Wait.forPageReady();
        } else {
            Integer intQty = Integer.valueOf(qty) - 1;
            DropDowns.selectByText("shopping_bag.quantities", Integer.toString(intQty));
            Wait.forPageReady();
        }
    }

    @Then("^I enter the promo code in promotion field and click on apply$")
    public void iEnterThePromoCodeInPromotionFieldAndClickOnApply() throws Throwable {
        Wait.forPageReady();
        String promoCode = PromoUtils.promoData.getString("promo_code");
        TextBoxes.typeTextbox("shopping_bag.apply_promo_textfield", promoCode);
        Clicks.click("shopping_bag.apply_promo_button");
    }

    @Then("^I verify the promotion on the shopping bag page$")
    public void iVerifyThePromotionOnTheShoppingBagPage() throws Throwable {

        String globalType = PromoUtils.promoData.getString("global");
        if (!globalType.equals("true")) {
            iShouldSeePromotionAppliedDetailsInWalletSection();
        }
        iShouldSeePromotionAppliedDetailsInTheItemSection();
    }

    @Then("^I verify the promotion on the order details panel$")
    public void iVerifyThePromotionOnTheOrderDetailsPanel() throws Throwable {

        String globalType = PromoUtils.promoData.getString("global");
        if (globalType.equals("true")) {
            iShouldSeeTheAppliedPromotionInCheckoutPage("not see");
        }
    }

    @And("^I select options to trigger the promotion and add to bag$")
    public void iSelectOptionsToTriggerThePromotionAndAddToBag() throws Throwable {

        Wait.untilElementPresent("product_display.quantity");
        if (macys()) {
            DropDowns.selectByText("product_display.quantity", PromoUtils.promoData.getString("qty_trigger"));
        } else {
            Clicks.click("product_display.quantity");
            Clicks.click(By.xpath("//div[@class='custom dropdown itemQtyDropdown open']/ul/li[" + "]"));
        }
        ProductDisplay.selectRandomColor();
//        ProductDisplay.selectRandomSize();
        Clicks.click("product_display.add_to_bag_class");
    }

    @And("^I verify the offer description on the add to bag overlay$")
    public void iVerifyTheOfferDescriptionOnTheAddToBagOverlay() throws Throwable {
        Utils.threadSleep(4000, null);

    }

    @And("^I add \"([^\"]*)\" product to my bag$")
    public void iAddProductToMyBag(String promoType) throws Throwable {

        if (PromoUtils.promoData == null) {
            PromoUtils.getPromoData("promoType");
        }
        PromoUtils.promoData = PromoUtils.getPromoData(promoType);
        CommonUtils.navigateDirectlyToProduct(PromoUtils.promoData.getString("eligible_product"));
    }


    @And("^I verify the promotion on the PDP page$")
    public void iVerifyThePromotionOnThePDPPage() throws Throwable {

        PromoUtils.verifyBadgeTextDetails();
    }

    @And("^I navigate to shopping bag page from add to bag overlay$")
    public void iNavigateToShoppingBagPageFromAddToBagOverlay() throws Throwable {

        Wait.untilElementPresent("add_to_bag.checkout");
        Clicks.click("add_to_bag.checkout");
    }

    @Then("^I enter the \"([^\"]*)\" promotion in promotion field and click on apply$")
    public void iEnterThePromotionInPromotionFieldAndClickOnApply(String promoType) throws Throwable {

        Wait.forPageReady();
        if (PromoUtils.promoData == null) {
            PromoUtils.promoData = PromoUtils.getPromoData(promoType);
        }
        if (macys()) {
            TextBoxes.typeTextbox("shopping_bag.apply_promo_textfield", PromoUtils.promoData.getString("promo_code"));
            Clicks.click("shopping_bag.apply_promo_button");
        } else {
            TextBoxes.typeTextbox("shopping_bag.promo_code", PromoUtils.promoData.getString("promo_code"));
            Clicks.click("shopping_bag.apply_promo_code_button");
        }
    }

    @And("^I should see Go To Wallet link in wallet section$")
    public void iShouldSeeGoToWalletLinkInWalletSection() throws Throwable {

        Wait.forPageReady("shopping_bag");
        Assert.assertTrue("Go To Wallet link not found in shopping bag",
                Elements.elementPresent("shopping_bag.go_to_wallet_link"));
    }

    @Then("^I click on Remove button to remove applied promotion$")
    public void iClickOnRemoveButtonToRemoveAppliedPromotion() throws Throwable {

        Utils.threadSleep(3000, null);
        if (bloomingdales()) {
            Wait.untilElementPresent("shopping_bag.remove_promo_button");
            Clicks.click("shopping_bag.remove_promo_button");
        } else {
            Wait.untilElementPresent("shopping_bag.promo_code_remove");
            Clicks.click("shopping_bag.promo_code_remove");
        }
    }

    @Then("^I verify the applied promotion has been removed$")
    public void iVerifyTheAppliedPromotionHasBeenRemoved() throws Throwable {

        Utils.threadSleep(3000, null);
        if (bloomingdales()) {
            String promoCode = PromoUtils.promoData.getString("promo_code");
            assertTrue("Promo text found in Order Summary after removing promotion", !Elements.elementPresent("shopping_bag.promo_code_label"));
            assertTrue("Promo code button present after removing promotion", !Elements.elementPresent("shopping_bag.remove_promo_button"));
            assertTrue("Promo amount found after removing promotion", !Elements.elementPresent("shopping_bag.applied_promo_amount"));
            assertTrue("Promo amount found in item section after removing promotion", !Elements.elementPresent("shopping_bag.promo_code_applied_item_amount"));
            assertTrue("Price with offer text found in item section after removing promotion", !Elements.elementPresent("shopping_bag.price_with_offers_text"));
            assertTrue("Price with offer amount found in item section after removing promotion", !Elements.elementPresent("shopping_bag.price_with_offers_amount"));
        }  else {
            assertTrue("Promo desc found in shopping bag wallet section", !Elements.elementPresent("shopping_bag.wallet_promo_desc"));
            assertTrue("Remove link found in shopping bag wallet section", !Elements.elementPresent("shopping_bag.remove_promotion"));
            assertTrue("Applied promotion desc found in shopping bag item section", !Elements.elementPresent("shopping_bag.wallet_promo_desc"));
            assertTrue("Applied promotion amount found in shopping bag item section", !Elements.elementPresent("shopping_bag.item_promotion_amount"));
            assertTrue("You saved label still present in shopping bag", !Elements.elementPresent("shopping_bag.you_saved_label"));
            assertTrue("Saved amount still present in shopping bag", !Elements.elementPresent("shopping_bag.saved_amount"));
        }
    }
}
