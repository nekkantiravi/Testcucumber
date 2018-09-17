package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.db.models.PromotionService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.NoSuchElementException;

public class MyWalletSteps extends StepUtils {

    String promoCode = null;

    /**
     * Adds a promo code from the promo service to wallet if possible
     */
    @Then("^I saved omnichannel offer having more than one promo code in wallet$")
    public void I_saved_omnichannel_offer_having_more_than_one_promo_code_in_wallet() {
        PromotionService promotionService = new PromotionService();
        promoCode = promotionService.getWalletEligiblePromoCode();
        if (promoCode == null) {
            Assert.fail("ERROR - DATA: Wallet eligible promo code is not available in database!!");
        }
        Clicks.click((macys() ? "oc_my_wallet" : "my_bwallet") + ".add_offer_btn");
        TextBoxes.typeTextbox("add_offer_dialog.promo_code", promoCode);
        Clicks.clickIfPresent("add_offer_dialog.save_offer");
    }

    /**
     * Adds the given offer code to wallet from my wallet page
     *
     * @param offer offer code to add
     */
    @Then("^I add omnichannel offer \"([^\"]*)\" having more than one promo code in wallet$")
    public void I_add_omnichannel_offer_having_more_than_one_promo_code_in_wallet(String offer) {
        Clicks.click((macys() ? "oc_my_wallet" : "my_bwallet") + ".add_offer_btn");
        TextBoxes.typeTextbox("add_offer_dialog.promo_code", offer);
        Clicks.clickIfPresent("add_offer_dialog.save_offer");
    }

    /**
     * Verifies that promo code was added to wallet
     */
    @And("^I verify the promo code added to my wallet page$")
    public void I_verify_the_promo_code_added_to_my_wallet_page() {
        try {
            if (macys()) {
                Elements.elementPresent("oc_my_wallet.offers_container");
            } else {
                Elements.elementPresent("my_bwallet.offers_container");
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Promo code is not added to wallet page");
        }
    }
    @Then("^I should be able to see wallet icon and below wallet description$")
    public void iShouldBeAbleToSeeWalletIconAndBelowWalletDescription(List<String> message) throws Throwable {
        Assert.assertTrue("Wallet icon is not displayed", Elements.elementPresent("my_offers.wallet_img"));
        Assert.assertTrue("Wallet description is not displaying as expected",(Elements.findElement("my_offers.wallet_desc").getText()).equals(message.get(0)));
    }


    @And("^I should see \"([^\"]*)\" link on \"([^\"]*)\" page$")
    public void iShouldSeeLinkOnPage(String link, String page) throws Throwable {
        Assert.assertTrue("Link not found on page",Elements.elementPresent(page+"."+link));
    }

    @When("^I click on \"([^\"]*)\" link on \"([^\"]*)\" page$")
    public void iClickOnLink(String link, String page) throws Throwable {
        Clicks.click(page+"."+link);
    }

    @Then("^I should be navigated to wallet page$")
    public void iShouldBeNavigatedToWalletPage() throws Throwable {
        String current_url = WebDriverManager.getCurrentUrl();
        Assert.assertTrue("Couldn't navigate to wallet page", current_url.contains("wallet"));
    }

    @Then("^I should see the message as \"([^\"]*)\" in tool tip$")
    public void iShouldSeeTheMessageAsInToolTip(String message) throws Throwable {
        Wait.untilElementPresent("my_offers.add_to_wallet_tool_tip");
        Assert.assertTrue("tool tip text is not displaying as expected",
                (Elements.findElement("my_offers.add_to_wallet_tool_tip")
                        .getText().replace("\n", " ")).equals(message));
    }

    @And("^I should see added offer in my wallet page$")
    public void iShouldSeeAddedOfferInMyWalletPage() throws Throwable {
        Clicks.click("my_offers.get_started");
        Assert.assertTrue("offer is not added to wallet",
                Elements.elementPresent("my_offers.offers_container"));
    }

    @Then("^I should see added credit card in My Wallet page$")
    public void iShouldSeeAddedCreditCardInMyWalletPage() throws Throwable {
        Assert.assertTrue("Credit card is not added to wallet",
                Elements.elementPresent("oc_my_wallet.credit_cards_info"));
    }
}
