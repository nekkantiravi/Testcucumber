package com.macys.sdt.projects.Marketing.StarRewards.utils;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.StepUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarRewardsUtils extends StepUtils {

    /**
     * Gets a random Star Reward eligible credit card from "valid_cards.json"
     *
     * @param tierName  tierName of the credit card
     * @param starMoney available if star money available,  else not available for star reward eligible credit card
     * @return CreditCard of specified type
     */
    public static CreditCard getValidStarRewardCreditCardDetails(String tierName, String starMoney) {
        CreditCard card;
        List<String> cardTypes = new ArrayList<>();
        Random random = new Random();

        cardTypes.add("Macy's");
        cardTypes.add("Macy's American Express");
        cardTypes.add("Employee Card");

        String cardType = cardTypes.get(random.nextInt(cardTypes.size()));
        card = CreditCards.getValidStarRewardCard(cardType, tierName, starMoney);

        return card;
    }

    /**
     * Method to click on Star Rewards Link In My Account Drop Down
     */
    public static void clickStarRewardsLinkInMyAccountDropDown() {
        Clicks.hoverForSelection(Elements.findElement("header.goto_my_account_link"));
        Clicks.clickWhenPresent("header.goto_star_rewards");
        if (signedIn()) {
            shouldBeOnPage("star_rewards");
        } else {
            shouldBeOnPage("sign_in");
        }
    }

}