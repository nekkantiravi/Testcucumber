package com.macys.sdt.projects.PurchaseAndDelivery.Regression.steps.mew;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Created by yc05165 on 20/09/17.
 */
public class ShopAndBrowse extends StepUtils {

    @When("^I search \"([^\"]*)\" using mobile website$")
    public void iSearchUsingMobileWebsite(String value) {
        Assert.assertTrue("ERROR-ENV: Search text field is not visible", Wait.untilElementPresent("home.search_field"));
        TextBoxes.typeTextbox("home.search_field", value);
        Clicks.click("header.search_button");
        Wait.forPageReady();
        String page = (value.contains("brands") ? "brand_index" : "search_result");
        Wait.untilElementPresent(page + ".verify_page");
        shouldBeOnPage(page);
    }
}
