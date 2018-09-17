package com.macys.sdt.shared.actions.MEW.pages;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.db.models.PromotionService;
import org.json.JSONException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyOffers extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(MyOffers.class);
    public static String promoCode = null;

    /**
     * Delete all offers from OC Wallet page
     */
    public static void deleteOffers() {
        Navigate.browserRefresh();
        if (Wait.untilElementPresent("oc_my_wallet.available_offers")) {
            int offer_size = Elements.findElements("oc_my_wallet.available_offers").size();
            for (int i = 0; i < offer_size; i++) {
                if (Elements.findElements("oc_my_wallet.available_offers").size() == 1) {
                    Clicks.click("oc_my_wallet.available_offers");
                } else {
                    Clicks.click(Elements.findElements("oc_my_wallet.available_offers").get(i));
                }
                Wait.untilElementPresent("oc_my_wallet.container");
                Assert.assertTrue("Unable to click remove offer code button", Clicks.clickWhenPresent("oc_my_wallet.remove_offer_code_button"));
                Clicks.click("oc_my_wallet.remove_offer_confirmation_ok");
                Clicks.click("oc_my_wallet.back_btn");
            }
        } else {
            logger.info("No Offers found");
        }
    }

    /**
     * Add given wallet eligible promo code to OC Wallet page
     *
     * @param code Wallet eligible promo code to add
     */
    public static void addValidOffers(String code) {
        //  Navigate.browserRefresh();
        TextBoxes.typeTextbox("oc_my_wallet.input_offer_code", code);
        Clicks.javascriptClick("oc_my_wallet.add_offer_code_button");
        By el = Elements.element("oc_my_wallet.add_offer_error_msg");
        if (Elements.elementPresent(el)) {
            if (Elements.findElement(el).getText().contains("This offer is already in your wallet")) {
                logger.info("Same offer is already added to you wallet. Please use it for checkout");
                Navigate.browserBack();
                Navigate.browserRefresh();
                if (!Wait.untilElementPresent("oc_my_wallet.available_offers")) {
                    Assert.fail("Add offers are not displaying in the My Wallet Page");
                }
            } else if (Elements.findElement(el).getText().contains("Sorry, but we don't recognize the promo code you entered")) {
                Assert.fail("ERROR-DATA: Added offer is not valid. Please use a valid offer");
            }
        }
    }

    /**
     * Get wallet eligible promo code from DB and add to Wallet page
     */
    public static void addOffers() {
        try {
            if (prodEnv()) {
                promoCode = TestUsers.getValidPromotion().getString("promo_code");
            } else {
                PromotionService promotionService = new PromotionService();
                promoCode = promotionService.getWalletEligiblePromoCode();
                logger.info("promocode : " + promoCode);
                if (promoCode == null) {
                    Assert.fail("ERROR - DATA : Wallet eligible promo code is not available in database!!");
                }
            }
            TextBoxes.typeTextbox("oc_my_wallet.input_offer_code", promoCode);
            Clicks.javascriptClick("oc_my_wallet.add_offer_code_button");
            By el = Elements.element("oc_my_wallet.add_offer_error_msg");
            if (Elements.elementPresent(el)) {
                if (Elements.findElement(el).getText().contains("This offer is already in your wallet")) {
                    logger.info("Same offer is already added to you wallet. Please use it for checkout");
                    Navigate.browserBack();
                    Navigate.browserRefresh();
                    if (!Wait.untilElementPresent("oc_my_wallet.available_offers")) {
                        Assert.fail("Add offers are not displaying in the My Wallet Page");
                    }
                } else if (Elements.findElement(el).getText().contains("Sorry, but we don't recognize the promo code you entered")) {
                    Assert.fail("ERROR-DATA: Added offer is not valid. Please use a valid offer");
                }
            } else {
                Wait.untilElementPresent("oc_my_wallet.add_offer_confirmation");
                Clicks.javascriptClick("oc_my_wallet.add_offer_confirmation_ok");
                Wait.untilElementPresent("oc_my_wallet.available_offers");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Assert.fail("Not returned any valid promo-codes");
        }
    }
}
