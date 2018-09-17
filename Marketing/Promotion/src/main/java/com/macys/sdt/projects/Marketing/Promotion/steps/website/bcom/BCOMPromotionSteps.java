package com.macys.sdt.projects.Marketing.Promotion.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.PromotionService;
import com.macys.sdt.projects.Marketing.Promotion.utils.PromoUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse.ProductDisplay;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class BCOMPromotionSteps extends StepUtils {

    private PageNavigation pageNavigation = new PageNavigation();
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();

    @And("^I verify the eligible offers section in shopping bag page$")
    public void iVerifyTheEligibleOffersSectionInShoppingBagPage() throws Throwable {

        Utils.threadSleep(4000, null);
        String promoCode = PromoUtils.promoData.getString("promo_code");
        Map<String, String> promoDetails = new PromotionService().getPromotionDetails(promoCode);
        String actValidityDates = Elements.findElement("shopping_bag.eligible_offer_expiry").getText().trim();
        String expValidityDates = "EXP. " + promoDetails.get("expirationDate");
        String expPromoHeading = promoDetails.get("promoHeading") + " " + promoDetails.get("promoSubHeading");
        String actPromoHeading = Elements.findElement(By.xpath("//label[@for='eligibleOffer_" + promoCode + "_']")).getText();

        assertTrue("ELIGIBLE OFFERS header could not be validated",
                Elements.findElement("shopping_bag.eligible_offers_header").getText().equals("ELIGIBLE OFFERS"));
        assertTrue("DO NOT USE ANY OFFERS text could not be validated",
                Elements.findElement("shopping_bag.no_use_offer_option").getText().equals("DO NOT USE ANY OFFERS"));
        assertTrue("Applicable promotion header does not match with expected", expPromoHeading.equals(actPromoHeading));
        assertTrue("Applicable promotion expiry date does not match with expected", actValidityDates.equals(expValidityDates));
        assertTrue("Applicable promotion amount not found in eligible offers", Elements.elementPresent("shopping_bag.eligible_offer_amount"));
        assertTrue("Eligible promotion exclusions link not found in eligible offers", Elements.elementPresent("shopping_bag.eligible_offer_exclusions"));

    }

    @And("^I select the eligible promotion from eligible offer section$")
    public void iSelectTheEligiblePromotionFromEligibleOfferSection() throws Throwable {

        String promoCode = PromoUtils.promoData.getString("promo_code");
        Wait.untilElementPresent("shopping_bag.selected_offer_rb");
        Clicks.click(Elements.findElement(By.cssSelector("span#eligibleOffer_" + promoCode + "__cover")));
    }

    @Then("^I should see promotion applied details in order summary setion$")
    public void iShouldSeePromotionAppliedDetailsInOrderSummarySetion() throws Throwable {

        Utils.threadSleep(3000, null);
        String promoCode = PromoUtils.promoData.getString("promo_code");
        assertTrue("Promo code applied header could not be validated in Order Summary",
                Elements.findElement("shopping_bag.Promo_code_applied_text").getText().equals("Promo Code Applied"));
        assertTrue("Applied promo code text could not be validated in Order Summary",
                Elements.findElement("shopping_bag.promo_code_label").getText().equals(promoCode));
        assertTrue("Remove promo code button missing", Elements.elementPresent("shopping_bag.remove_promo_button"));
        assertTrue("Applied promo amount missing", Elements.elementPresent("shopping_bag.applied_promo_amount"));
    }

    @And("^I select do not use any offer option$")
    public void iSelectDoNotUseAnyOfferOption() throws Throwable {

        Clicks.click(Elements.findElement(By.cssSelector("span#eligibleOffer_NONE_cover")));
    }

    @And("^I should not see the eligible offer option in eligible offer section$")
    public void iShouldNotSeeTheEligibleOfferOptionInEligibleOfferSection() throws Throwable {

        Utils.threadSleep(3000, null);
        String promoCode = PromoUtils.promoData.getString("promo_code");
        Wait.untilElementPresent("shopping_bag.selected_offer_rb");
        assertTrue("Eligible promotion option found in eligible offer section",
                !Elements.elementPresent(By.xpath("//label[@for='eligibleOffer_" + promoCode + "_']")));
    }

    @And("^I select options to trigger the promotion in pdp page$")
    public void iSelectOptionsToTriggerThePromotionInPdpPage() throws Throwable {

        Utils.threadSleep(3000, null);
        String qty = PromoUtils.promoData.getString("qty_trigger");
        ProductDisplay.selectRandomColor();
        PromoUtils.selectRandomSize();
        Clicks.click("product_display.quantity");
        Utils.threadSleep(1000, null);
        Clicks.click(By.xpath("//div[@id='pdpQty']/div/ul/li[text()='" + qty + "']"));
    }

    @And("^I should see no offer in wallet text in eligible offers section$")
    public void iShouldSeeNoOfferInWalletTextInEligibleOffersSection() throws Throwable {

        String actualText = "There are no valid offers available in your bWallet. Any offers that you saved previously may have expired.";
        Utils.threadSleep(3000, null);
        assertTrue("No offer in wallet text not found in shopping bag",
                Elements.findElement("shopping_bag.no_offer_in_wallet_text").getText().equals(actualText));
    }

    @Given("^I visit the web site as a registered user with no stored offers in wallet$")
    public void iVisitTheWebSiteAsARegisteredUserWithNoStoredOffersInWallet() throws Throwable {

        pageNavigation.I_navigate_to_create_profile_page();
        myAccountSteps.iCreateANewProfile();
    }
}
