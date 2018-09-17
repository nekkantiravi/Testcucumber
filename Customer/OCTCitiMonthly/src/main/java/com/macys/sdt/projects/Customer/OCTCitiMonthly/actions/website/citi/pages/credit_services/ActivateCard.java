package com.macys.sdt.projects.Customer.OCTCitiMonthly.actions.website.citi.pages.credit_services;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.model.CreditManageCard;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.projects.Customer.OCTCitiMonthly.utils.CreditServicesUtil.getActivationInfo;

public class ActivateCard extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(ActivateCard.class);

    /**
     * To get Activation data and fill on CS Activate Card page
     */
    public static void fillActivateCardInfo(String user) {
        if (!onPage("credit_activate_card")) {
            Assert.fail("Not on Activate Card page, Can't proceed with Test");
        }

        CreditManageCard creditActivateCard = getActivationInfo(user);

        if (!safari() && Elements.elementPresent("credit_activate_card.cardNumber")) {
            stopPageLoad();

            if (Elements.elementPresent("credit_activate_card.cardNumber")) {
                TextBoxes.typeTextbox("credit_activate_card.cardNumber", creditActivateCard.getCardNumber());
            } else {
                Assert.fail("Card Number Field not present, exiting");
            }

            if (Elements.elementPresent("credit_activate_card.name")) {
                TextBoxes.typeTextbox("credit_activate_card.name", creditActivateCard.getName());
            } else {
                Assert.fail("Name Field not present, exiting");
            }

            if (Elements.elementPresent("credit_activate_card.cvv")) {
                TextBoxes.typeTextbox("credit_activate_card.cvv", creditActivateCard.getSecurityCode());
            } else {
                Assert.fail("CVV Field not present, exiting");
            }

            if (Elements.elementPresent("credit_activate_card.ssn")) {
                TextBoxes.typeTextbox("credit_activate_card.ssn", creditActivateCard.getSsn());
            } else {
                Assert.fail("SSN Field not present, exiting");
            }

            if (Elements.elementPresent("credit_activate_card.verify")) {
                Clicks.click("credit_activate_card.verify");
            } else {
                Assert.fail("Verify button not present, exiting");
            }
        } else {
            Assert.fail("Elements not present, exiting");
        }
    }
}
