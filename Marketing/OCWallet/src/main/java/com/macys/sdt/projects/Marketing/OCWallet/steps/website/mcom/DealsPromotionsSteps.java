package com.macys.sdt.projects.Marketing.OCWallet.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DealsPromotionsSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(DealsPromotionsSteps.class);

    @And("^I click on 'add to wallet' button for any valid offer in deals and promotions page$")
    public void iClickOnAddToWalletButtonForAnyValidOfferInDealsAndPromotionsPage() throws Throwable {

        List<WebElement> elems = Elements.findElements("my_offers.add_to_wallet");
        Assert.assertTrue("No offers present that can be added to wallet",
                elems.size() != 0);

        for (WebElement elem : elems) {
            if (elem.getAttribute("id").equals("atwBtn-BLOOM")) {
                continue;
            }
            OCWallet.promoCode = elem.getAttribute("id").substring(7);
            Clicks.click(elem);
            if (Elements.elementPresent("deals_and_promotions.error_add_to_wallet")) {
                continue;
            }
            break;
        }
    }
}
