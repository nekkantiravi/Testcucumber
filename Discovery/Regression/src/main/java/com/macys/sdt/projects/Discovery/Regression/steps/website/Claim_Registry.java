package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Then;
import org.junit.Assert;

/**
 * Created by yh03402 on 22/05/17.
 */
public class Claim_Registry extends StepUtils {

    /**
     * Navigates to the registry home page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should see promocode appears on browse page$")
    public void i_should_see_promocode_appears_on_browse_page() throws Throwable {
        Assert.assertTrue("PromoCode text is not displaying", Elements.elementPresent("home.test_promo"));
    }

}
