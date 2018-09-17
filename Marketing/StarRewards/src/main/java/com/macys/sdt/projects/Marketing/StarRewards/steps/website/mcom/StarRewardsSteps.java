package com.macys.sdt.projects.Marketing.StarRewards.steps.website.mcom;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.services.CreditCardService;
import com.macys.sdt.projects.Marketing.StarRewards.utils.StarRewardsUtils;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyWallet;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StarRewardsSteps extends StepUtils{

    private static final Logger logger = LoggerFactory.getLogger(StarRewardsSteps.class);

    @When("^I add a prop or co-brand card as default card in wallet having (PLATINUM|GOLD|SILVER) tier and star money" +
            " " +
            "(available|not available)$")
    public void iAddAPropOrCoBrandCardAsDefaultCardInWalletHavingPLATINUMTierAndStarMoneyAvailable(String tierName,
                                                                                                   String starMoney)
            throws Throwable {
        MyAccountSteps myAccountSteps = new MyAccountSteps();
        CreditCard card = StarRewardsUtils.getValidStarRewardCreditCardDetails(tierName, starMoney);

        try {
            CreditCardService.addCreditCardToWallet(card, true);
        } catch (EnvException e) {
            logger.info("Failed to add credit card from Service. Falling back on UI.");
            myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
            MyWallet.addCard(card);
            logger.info("Credit card successfully added to wallet from UI");
        }
    }

    @Then("^I (should|should not) see star rewards section on (payment|shipping & payment|order confirmation) page$")
    public void iShouldSeeStarRewardsSectionInShippingPaymentPage(String condition, String page) {
        String pageName = null;

        switch (page) {
            case "shipping & payment":
                pageName = "responsive_checkout_signed_in";
                break;
            case "payment":
                pageName = "responsive_checkout";
                break;
            case "order confirmation":
                pageName = "responsive_order_confirmation";
                break;
        }

        shouldBeOnPage(pageName);

        if (condition.equals("should")) {
            Assert.assertTrue("Star Rewards section is not displayed on " + page, Elements
                    .elementPresent(pageName + ".star_rewards_section"));
        } else {
            Assert.assertFalse("Star Rewards section is displayed on " + page, Elements
                    .elementPresent(pageName + ".star_rewards_section"));
        }
    }

    @Then("^I click on Star Rewards link in my account dropdown on below pages:$")
    public void iClickOnStarRewardsLinkInMyAccountDropdownOnBelowPages(List<Map<String, String>> all) throws Throwable {
        for (Map name : all) {
            if (Arrays.asList("gift_guide", "goto_gift_cards").contains(name.get("link"))) {
                Clicks.hoverForSelection(Elements.findElement("home.gifts_header"));
            }
            Wait.secondsUntilElementPresentAndClick(name.get("page_name") + "." + name.get("link"), 30);
            Wait.forPageReady((String) name.get("page"));
            Wait.untilElementPresent(name.get("page") + ".verify_page");
            StarRewardsUtils.clickStarRewardsLinkInMyAccountDropDown();
        }
    }

}