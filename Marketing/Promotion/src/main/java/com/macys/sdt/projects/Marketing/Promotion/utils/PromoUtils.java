package com.macys.sdt.projects.Marketing.Promotion.utils;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class PromoUtils extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(PromoUtils.class);
    public static JSONObject promoData = null;

    public static void verifyBadgeTextDetails() {

        List<WebElement> badgeElems = Elements.findElements("product_display.badgeTexts");
        String text = null;
        String badgeText = promoData.getString("badge_text");
        boolean offerFound = false;

        for (WebElement elem : badgeElems) {
            text = macys() ? elem.findElement(By.xpath("//span[@class='badgeText']")).getText() : elem.getText();
            if (text.equals(badgeText)) {
                Assert.assertTrue("Details link for the badge text could not be found",
                        macys() ? Elements.elementPresent(By.xpath("//a[@class='details'")) : Elements.elementPresent(By.xpath("//span[@class='see-more'")));
                offerFound = true;
                break;
            }
        }
       // Clicks.click("product_display.expand_special_offers");
        verifyOfferInSpecialOffers(badgeText);
    }

    public static void verifyOfferInSpecialOffers(String badgeText) {

        List<WebElement> badgeElems = Elements.findElements("product_display.special_offers");
        String text = null;
        boolean offerFound = false;

        for (WebElement elem : badgeElems) {
            text = elem.getText();
            if (text.equals(badgeText)) {
                Assert.assertTrue("Offer description could not be verified in special offer section",
                        Elements.elementPresent(By.xpath("//div[@class='otherOfferDescription']")));
                offerFound = true;
                break;
            }
        }
        Assert.assertTrue("Offer detail header in special offers section not verified, " +
                "Expected: " + badgeText + " , "  + "Actual: " + text, offerFound);
    }

    public static void addPWPItemToBag() {

        List<WebElement> pwpOffers = Elements.findElements("shopping_bag.pwp_offers");

        for (WebElement elem : pwpOffers) {
            if (elem.findElement(By.xpath("//child::select[contains(@id,'color')]")).isDisplayed()) {
                DropDowns.selectByIndex(By.xpath("//div[@class='offersContainer ']//child::select[contains(@id,'color')]"), 1);
            }
            Clicks.click(elem.findElement(By.xpath("//div[@class='offersAtb']/a")));
            Utils.threadSleep(3000, null);
            break;
        }
    }

    public static JSONObject getPromoData(String promoType) {

        JSONObject jsonObject = null;
        ArrayList<String> list = new ArrayList<String>();
        JSONArray array = null;
        try {
            File file = getResourceFile("valid_promo_codes.json");
            String jsonText = Utils.readTextFile(file);
            jsonObject = new JSONObject(jsonText);
            if (macys()) {
                array = jsonObject.getJSONArray("macys");
            } else {
                array = jsonObject.getJSONArray("bcom");
            }
            for (int i = 0; i < array.length(); i++) {
                promoData = array.getJSONObject(i);
                if (promoData.get("promo_type").equals(promoType)) {
                    return promoData;
                }
            }

        } catch (JSONException | IOException ex) {
            Assert.fail("Failed to extract JSON objects from categories.json");
        }
        return promoData;
    }

    public static void verifyPromotionInItemSection() {

        Utils.threadSleep(3000, null);
        String globalType = PromoUtils.promoData.getString("global");
      //  String actualItemPromotionDesc = Elements.findElement("shopping_bag.item_promo_desc").getText();
        String expItemPromotionDesc = null;
        String promoType = PromoUtils.promoData.getString("promo_type");
        String promoCode = PromoUtils.promoData.getString("promo_code");

        if (globalType.equals("true")) {
            expItemPromotionDesc = PromoUtils.promoData.getString("promo_description");
        } else {
            expItemPromotionDesc = "Promo code " + promoCode + " applied.";
        }

        if (promoType.equals("GWP") || promoType.equals("PWP")) {
            if (promoType.equals("PWP")) {
                PromoUtils.addPWPItemToBag();
            }
            if (Elements.elementPresent("shopping_bag.select.attrChoice")) {
                DropDowns.selectByIndex("shopping_bag.select.attrChoice", 2);
            }

            Assert.assertTrue("Gift item could not be found in shopping bag page",
                    Elements.elementPresent("shopping_bag.gwp_container"));
        } else {
            assertTrue("Applied promo code could not be validated in item section", Elements.findElement("shopping_bag.promo_code_applied_item_section").getText().equals(expItemPromotionDesc));
            assertTrue("Applied promo code amount not found in item section", Elements.elementPresent("shopping_bag.promo_code_applied_item_amount"));
            assertTrue("Price with offer text not found in item section", Elements.findElement("shopping_bag.price_with_offers_text").getText().equals("Price with Offer(s)"));
            assertTrue("Price with offer amount not found in item section", Elements.elementPresent("shopping_bag.price_with_offers_amount"));
        }
    }

    public static void selectRandomSize() {

        if (Elements.elementPresent("product_display.size_list")) {
            List<WebElement> sizeList = Elements.findElements("product_display.size_list");
            Clicks.click("product_display.size_dropdown");
            Clicks.click(sizeList.get(1));
        }
    }
}
