package com.macys.sdt.projects.Marketing.InternationalHomePage.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;



public class InternationalHomePageSteps extends StepUtils {

    @Then("^I should see the seasonal promo in the header$")
    public void i_should_see_the_seasonal_promo_in_the_header() throws Throwable {
        Assert.assertTrue("The seasonal promotion is not present", Elements.elementPresent("header.seasonal_action_off"));
        Assert.assertFalse("The seasonal promo is expanded", isSeasonalPromoExpanded());
    }

    @When("^I click on the seasonal promo in the header$")
    public void i_click_on_the_seasonal_promo_in_the_header() throws Throwable {
        Elements.findElement("header.seasonal_action_off").click();
    }

    public boolean isSeasonalPromoExpanded(){
        return Wait.untilElementPresent("header.seasonal_action_on");
    }

    @Then("^the seasonal promo should be expanded$")
    public void theSeasonalPromoShouldBeExpanded() throws Throwable {
        Assert.assertTrue("The seasonal promo is not expanded", isSeasonalPromoExpanded());
    }

    @Then("^I should see the correct iship currency for Canada in the header$")
    public void iShouldSeeTheCorrectIshipCurrencyForCanadaInTheHeader() throws Throwable {
        String expectedCurrency = "CAD";
        String actualCurrency = Elements.getText("header.iship_currency").toUpperCase();
        Assert.assertTrue("The found iship currency ( " + actualCurrency + " ) differs from the expected one ( " + expectedCurrency + " )", expectedCurrency.equals(actualCurrency));
    }

    @And("^I should see the correct country flag for Canada$")
    public void iShouldSeeTheCorrectCountryFlagForCanada() throws Throwable {
        Assert.assertTrue("The flag is not displayed", Elements.elementPresent("header_and_footer.country_code_flag"));
        String src = Elements.findElement("header_and_footer.country_code_flag").getAttribute("src");
        String expectedSrc =  "/CA.";
        Assert.assertTrue("The found flag is not the expected one. Src expected to contain: " + expectedSrc + "found: " + src, src.contains(expectedSrc));
    }
}
