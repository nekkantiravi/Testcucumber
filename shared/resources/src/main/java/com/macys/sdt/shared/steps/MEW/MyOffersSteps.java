package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.db.models.PromotionService;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyOffersSteps extends StepUtils {

    List<String> promoCodes = new ArrayList<>();
    List<String> walletPromoCodes = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(MyOffersSteps.class);
    /**
     * Adds all available offers to wallet from deals and promotions page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I add all wallet eligible offers from deals and promotions page using mobile website$")
    public void i_add_all_wallet_eligible_offers_from_deals_and_promotions_page_using_mobile_website() throws Throwable {
        shouldBeOnPage("deals_and_promotions");
        pausePageHangWatchDog();
        Wait.untilElementPresent("deals_and_promotions.offers_container");
        int count = Elements.findElements("deals_and_promotions.offers_container").size();
        for (int i = 0; i < count; i++) {
            Clicks.click(Elements.findElements("deals_and_promotions.offers_container").get(i));
            Wait.untilElementPresent("offer_details.offer_promocode");
            if (Elements.elementPresent("offer_details.add_to_wallet") && Elements.getElementAttribute("offer_details.add_to_wallet", "class").contains("add-to-wallet")) {
                promoCodes.add(Elements.findElement("offer_details.offer_promocode").getText().replace("Promo code: ", ""));
                Clicks.click("offer_details.add_to_wallet");
                Wait.untilElementPresent("offer_details.toast_message");
                if (Wait.untilElementPresent("offer_details.remove_from_wallet_button"))
                    logger.info("Promo code '"+ promoCodes.get(promoCodes.size() - 1) +"' is successfully added to wallet!!");
                else {
                    logger.info("Promo code '"+ promoCodes.get(promoCodes.size() - 1) +"' is not added to wallet!!");
                    promoCodes.remove(promoCodes.size() - 1);
                }
            }
            Wait.untilElementPresent("offer_details.back");
            Clicks.click("offer_details.back");
            Wait.untilElementNotPresent("offer_details.back");
            Wait.untilElementPresent("deals_and_promotions.offers_container");
        }
    }

    /**
     * Verifies that the offers that were added show up in my wallet
     * <p>
     * Uses step "I add all wallet eligible offers from deals and promotions page using mobile website" as basis for verification.
     * Requires that either this step be run, or another step updates the "promoCodes" variable with correct information
     * </p>
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see the added offers in my wallet page using mobile website$")
    public void i_should_see_the_added_offers_in_my_wallet_page() throws Throwable {
        shouldBeOnPage("oc_my_wallet");
        Clicks.clickIfPresent("oc_my_wallet.view_all_saved_offers");
        List<WebElement> offers = Elements.findElements("oc_my_wallet.offers_list");
        walletPromoCodes.addAll(offers.stream().map(offer -> offer.getAttribute("data-id")).collect(Collectors.toList()));
        for (String promoCode : promoCodes) {
            Assert.assertTrue(promoCode + "is not added to wallet", walletPromoCodes.contains(promoCode));
        }
    }

    /**
     * Adds a promo code from the promo service to wallet
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I add an offer to my wallet using mobile website$")
    public void iAddAnOfferToMyWalletUsingMobileWebsite() throws Throwable {
        String promoCode = new PromotionService().getWalletEligiblePromoCode();
        Assert.assertNotNull("ERROR - DATA: Wallet eligible promo code is not available in database!!", promoCode);
        String page = macys() ? "oc_my_wallet" : "my_bwallet";
        shouldBeOnPage(page);
        Clicks.click(page + ".add_offer_btn");
        Wait.untilElementPresent("add_offer_dialog.promo_code");
        TextBoxes.typeTextbox("add_offer_dialog.promo_code", promoCode);
        Clicks.click("add_offer_dialog.save_offer");
        Assert.assertTrue("ERROR - DATA: " + promoCode + " is not added to wallet",
                Wait.untilElementPresent(page + ".offers_container"));
    }
}