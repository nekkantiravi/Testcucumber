package com.macys.sdt.projects.PurchaseAndDelivery.ICanWait.steps.website;

import com.macys.sdt.framework.exceptions.DataException;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.OCMyWallet;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.PaymentGuestSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.SignedInGiftCardsSection;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.model.GiftCard;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.ShoppingBag;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.GiftCardUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by atepliashin on 12/7/16.
 */
public class GiftCardsSteps {

    @Given("^current user has (.*) in their wallet$")
    public void currentUserHasCard(String cardType) throws DataException {
        Long userId = Long.valueOf(Cookies.getCookieValue("macys_online_uid"));
        Stream<GiftCard> cardsStream;
        switch (cardType) {
            case "icw mmoney card":
                cardsStream = GiftCardUtils.getUserICWCards().stream().filter(c -> !c.isExpired());
                break;
            case "expired icw mmoney card":
                cardsStream = GiftCardUtils.getUserICWCards().stream().filter(GiftCard::isExpired);
                break;
            default:
                throw new IllegalArgumentException("Unexpected card type given: " + cardType);
        }
        GiftCardUtils.DB.addUserRewardCard(cardsStream.findFirst().
                orElseThrow(() -> new DataException("No suitable card found")).setUserId(userId));
    }

    @When("^I apply (\\d+)(?:st|nd|rd|th) gift card from the list$")
    public void applyPresentRewardCard(int humanCardIndex) {
        Navigate.get(SignedInCheckout.class).giftCardsSection().expand().applyRewardCard(--humanCardIndex);
    }

    @When("^I manually apply (expired)? icw mmoney card as a (guest|registered) user$")
    public void applyGiftCard(String expired, String user) throws DataException {
        Predicate<GiftCard> cardFilter = card -> {
            if (expired != null) {
                return card.isExpired();
            } else {
                return !card.isExpired();
            }
        };

        GiftCard card = GiftCardUtils.getUserICWCards().stream().
                filter(cardFilter).findFirst().
                orElseThrow(() -> new DataException("No suitable card found"));

        if (user.equals("guest")) {
            Navigate.get(PaymentGuestSection.class).applyGiftCard(card);
        } else {
            Navigate.get(SignedInGiftCardsSection.class).expand().applyGiftCard(card);
        }
    }

    @Then("^there's (\\d+) mmoney cards? applied$")
    public void mMoneyCardsApplied(int cardsCount) {
        SignedInGiftCardsSection giftCardsSection = Navigate.get(SignedInGiftCardsSection.class);
        assertThat(giftCardsSection.appliedGiftCard.size(), equalTo(cardsCount));
        assertThat(giftCardsSection.appliedGiftCardValue.size(), equalTo(cardsCount));
        giftCardsSection.appliedGiftCard.forEach(card -> assertTrue(card.getText().contains("Macy's Money")));
    }

    @Then("^I should have Macy's Money to spend$")
    public void mMoneyMessagePresent() {
        ShoppingBag bag = Navigate.get(ShoppingBag.class);
        assertThat(bag.mmoneyRedeemLogo.isDisplayed(), is(true));
        String rewardText = bag.walletRewardMessage.getText();
        String rewardRegex = "^You have \\$\\d+\\.\\d{2} in Macy's Money—Apply it at checkout in the Gift/Rewards card section\\.$";
        assertTrue(rewardText + " doesn't match " + rewardRegex, rewardText.matches(rewardRegex));
        // tooltip was removed in scope of the story B-58613
        assertThat(bag.mmoneyHintIcon.exists(), is(false));
    }

    @Then("^there's (\\d+) mmoney cards? in my wallet$")
    public void mMoneyCardsInWallet(int cardsCount) {
        OCMyWallet wallet = Navigate.get(OCMyWallet.class);
        List<List<?>> cardElementsLists = new ArrayList<>();
        cardElementsLists.add(wallet.mmoneyRewardCardLogos);
        cardElementsLists.add(wallet.mbmoneyRewardCardBalance);
        cardElementsLists.add(wallet.mbmoneyRewardCardNumbers);
        cardElementsLists.add(wallet.mbmoneyRewardCardExpiryDates);
        cardElementsLists.add(wallet.printCards);
        cardElementsLists.forEach(elementsList -> assertThat(elementsList.size(), equalTo(cardsCount)));
    }

    @Then("^macys's money copy present$")
    public void mMoneySectionCopyShown() {
        OCMyWallet wallet = Navigate.get(OCMyWallet.class);
        assertThat(wallet.mmoneyEvent.getText(), equalTo(
                "Use your Macy’s money online and in stores—even with promo codes, offers, " +
                        "Plenti points and on top of sale prices. Dates of use appear below. Exclusions & Details."));
        assertThat(wallet.mmoneyEventLink.getReference(), equalTo(
                "https://www.customerservice-macys.com/app/answers/detail/a_id/4761/~/" +
                        "what-is-macys-money-and-how-do-i-redeem-it%3F"));
    }

}
