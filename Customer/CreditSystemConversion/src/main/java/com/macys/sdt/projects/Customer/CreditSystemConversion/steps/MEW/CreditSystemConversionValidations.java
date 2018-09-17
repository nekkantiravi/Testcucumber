package com.macys.sdt.projects.Customer.CreditSystemConversion.steps.MEW;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Customer.CreditSystemConversion.utils.CustomerUtils;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sgadiraju on 2/14/2017.
 */
public class CreditSystemConversionValidations extends StepUtils {

    @Given("^I sign to mobile website with user having \"([^\"]*)\" added in (wallet|profile)$")
    public void iSignToMEW(String accountType, String pagename) throws Throwable {

        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials = CustomerUtils.getCityUserCredentials(accountType);
        PageNavigation pageNavigationObj = new PageNavigation();
        pageNavigationObj.I_visit_the_mobile_web_site_as_a_guest_user_in_mode("guest");
        Clicks.click("home.goto_sign_in_link");
        TextBoxes.typeTextbox("sign_in.email", loginCredentials.get("email").toString());
        TextBoxes.typeTextbox("sign_in.password", loginCredentials.get("password").toString());
        Clicks.click("sign_in.verify_page");
        Wait.untilElementNotPresent("sign_in.verify_page");
        Wait.forPageReady();
    }

    @Then("^I should see CVV field is enabled$")
    public void iShouldSeeCVVFieldIsEnabled() throws Throwable {
        System.out.println("***" + Elements.elementPresent("responsive_checkout_signed_in.security_code"));
//        Assert.assertTrue("ERROR-APP: CVV filed is not displaying", Elements.elementPresent("responsive_checkout_signed_in.security_code"));
    }

    @Then("^I should see CVV field is disabled$")
    public void iShouldSeeCVVFieldIsDisabled() throws Throwable {
        Assert.assertFalse("ERROR-APP: CVV filed is not displaying", Elements.elementPresent("responsive_checkout_signed_in.security_code"));
    }

    @Then("^I (should not|should) see edit link on mobile wallet page$")
    public void iShouldVerifyEditLinkForTheNewCobrandCardOfAgeInMobileWebSiteWalletPage(String edit_display) throws Throwable {
        String page = macys() ? "oc_my_wallet" : "my_bwallet";
        Wait.secondsUntilElementPresentAndClick(page+".goto_edit_card", 5);
        if (edit_display.contains("not")) {
            Assert.assertFalse("EDIT link is NOT supressed in wallet page", Elements.elementPresent(page + ".edit_credit_card_heading"));
        } else{
            Assert.assertTrue("EDIT link is  supressed in wallet page", Elements.elementPresent(page + ".edit_credit_card_heading"));
        }
    }

    @And("^I click \"([^\"]*)\" on credit gateway page$")
    public void iClickOnCreditGatewayPage(String creditlink) throws Throwable {
        Clicks.click("credit_service_gateway_signedin."+creditlink);
    }

    @Then("^I should see below sections on gateway page$")
    public void iShouldSeeBelowSectionsOnGatewayPage(List<String> gateway_sections) throws Throwable {
        gateway_sections.forEach(gateway_section ->
                Assert.assertTrue(gateway_section +" is not displaying on Credit Gateway page",Elements.elementPresent("credit_service_gateway_signedin."+gateway_section))
        );
    }

    @When("^I navigate to credit service gateway page on MEW$")
    public void iNavigateToCreditServiceGatewayPageOnMEW() throws Throwable {
        PageNavigation pageNavigationObj = new PageNavigation();
        List<String> page = new ArrayList();
        String gatewayPage=macys() ? "Macy's Credit Card" : "MY CREDIT CARD";
      //Once Hamburger menu works will comment below two lines as static property to display credit card link is enabled
        if (macys()) {
            page.add("Wallet");
            page.add("Menu");
            page.add(gatewayPage);
            pageNavigationObj.I_navigate_the_global_navigation_menu_as_follows(page);
        }
        else{
            page.add(gatewayPage);
            pageNavigationObj.I_navigate_the_global_navigation_menu_as_follows(page);
        }
    }

}
