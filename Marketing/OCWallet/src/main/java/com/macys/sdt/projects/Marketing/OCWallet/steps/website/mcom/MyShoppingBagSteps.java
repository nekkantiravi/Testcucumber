package com.macys.sdt.projects.Marketing.OCWallet.steps.website.mcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;
import com.macys.sdt.projects.Marketing.OCWallet.utils.db.PromotionService;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by YC03673 on 7/3/2017.
 */
public class MyShoppingBagSteps {

    List<Map<String, String>> promoCodes;
    private static PromotionService promotionService = new PromotionService();
    private PageNavigation pageNavigation = new PageNavigation();
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();
    @Given("^I visit the web site as a registered user with (\\d+) stored future offers$")
    public void iVisitTheWebSiteAsARegistereduserWithStoredFutureOffers(int numOffers) throws Throwable{
        pageNavigation.I_visit_the_web_site_as_a_registered_user();
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
//        ArrayList<String> promoCodes = promotionService.getWalletEligiblePromoCodes();
        promoCodes = promotionService.getWalletEligiblePromoCodesByCount(numOffers, false);

        for (int i = 0; i < numOffers; i++) {
            Wait.forPageReady();
            myAccountSteps.iClickOnAddOfferOnWalletPage();
            Wallet.addValidOffer(promoCodes.get(i).get("promoCode"));
        }
    }

    @And("^I verify message \"You've got great offers in your wallet, but they don't apply to the items in your bag\" in offer widget section$")
    public void iVerifyMessageYouveGotGreatOffersInYourWallet() {
        assertTrue("",Elements.findElement("shopping_bag.offers_dont_apply_message").getText().equals("Sorry, but the offers in your wallet don't apply to these items."));
    }
}
