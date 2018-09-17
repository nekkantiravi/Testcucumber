package com.macys.sdt.shared.actions.MEW.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;

public class LoyallistAssociation extends StepUtils {

    /**
     * Associate loyalty account to user profile
     *
     * @param loyalty_customer instance of LoyalistDetails model, loyalist details for association
     */
    public static void loyaltyAssociation(LoyalistDetails loyalty_customer) {
        Wait.forPageReady();
        TextBoxes.typeTextbox("loyalty_association.loyallist_number", loyalty_customer.getLoyaltyId());
        TextBoxes.typeTextbox("loyalty_association.loyallist_last_name", loyalty_customer.getLastName());
        TextBoxes.typeTextbox("loyalty_association.loyallist_zip_code", loyalty_customer.getZipCode());
        Clicks.click("loyalty_association.submit_id");
        Wait.forPageReady();
        Assert.assertFalse("ERROR - DATA: " + Elements.getText("loyalty_association.lty_error"), Elements.elementPresent("loyalty_association.lty_error"));
        Assert.assertFalse("ERROR - DATA: " + Elements.getText("loyalty_association.association_restriction_error"), Elements.elementPresent("loyalty_association.association_restriction_error"));
    }
}
