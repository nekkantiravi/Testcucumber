package com.macys.sdt.projects.Customer.MyAccountRedesign.steps.MEW.bcom;

import com.macys.sdt.projects.Customer.MyAccountRedesign.actions.MEW.bcom.OldMyAccount;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

/**
 * Created by uc06174 on 8/1/2017.
 */
public class MyAccountRedesignMobile {

    @Then("^I see 'My Preferences' link under 'My Address Book' link$")
    public void myPreferencesLinkAppears(){
        OldMyAccount.verifyMyPreferencesLinkExists();

    }

    @And("^I don't see 'Preferred Store' card$")
    public void iDonTSeePreferredStoreCard() {
        OldMyAccount.verifyMyPreferredStoreNotExists();
    }

    @When("^I tap on 'My Preferences' link on My Account page$")
    public void iTapOnMyPreferencesLinkOnMyAccountPage() {
        OldMyAccount.clickMyPreferencesLink();
    }

    @Then("^I'm redirected to /account/preferences/ page$")
    public void iMRedirectedToAccountPreferencesPage() {
        OldMyAccount.verifyMyPrefencesLinkRedirectsToCorrectUrl();
    }

    @Then("^I'm navigated to url which has “cm_sp=my_account-_-loyallist-_-my_preferences” coremetrics tag appended$")
    public void iMNavigatedToUrlWhichHasCm_spMy_account_Loyallist_My_preferencesCoremetricsTagAppended() {
        OldMyAccount.verifyCoremetricsTagIsAppendedToMyPreferencesUrl();
    }
}
