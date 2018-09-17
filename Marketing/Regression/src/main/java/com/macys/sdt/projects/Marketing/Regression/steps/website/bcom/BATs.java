package com.macys.sdt.projects.Marketing.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import static com.macys.sdt.framework.utils.StepUtils.*;

public class BATs {

    MyAccountSteps myAccountSteps = new MyAccountSteps();

    @Then("^I should see my first name on dashboard$")
    public void iShouldSeeMyFirstNameOnDashboard() throws Throwable {

        UserProfile customer = TestUsers.getCustomer(null);
        String name = "WELCOME, " + customer.getUser().getProfileAddress().getFirstName() + "!";
        Assert.assertTrue("Incorrect name found in My Account page",
                Elements.findElement("my_account.my_profile_welcome_message_my_account")
                        .getText().equals(name));
    }

    @And("^I should see \"([^\"]*)\" link on dashboard$")
    public void iShouldSeeLinkOnDashboard(String element) throws Throwable {

        onPage("my_account");
        switch (element) {
            case "Sign out":
                Clicks.hoverForSelection("header.goto_my_account_link");
                Assert.assertTrue("Sign Out Link could not be found",
                        Wait.untilElementPresent("header.goto_sign_out_link"));
                break;
            case "My Account":
                Clicks.hoverForSelection("header.goto_my_account_link");
                Assert.assertTrue("My Account Link could not be found",
                        Wait.untilElementPresent("header.goto_my_account_link"));
                break;
            case "Order Status & History":
                Clicks.hoverForSelection("header.goto_my_account_link");
                Assert.assertTrue("Order Status & History dropdown could not be found",
                        Wait.untilElementPresent("header.order_status_history_dropdown"));
                break;
            case "Pay My Bill":
                Clicks.hoverForSelection("header.goto_my_account_link");
                Assert.assertTrue("Pay My Bill dropdown could not be found",
                        Wait.untilElementPresent("header.pay_my_bill_dropdown"));
                break;
        }

    }
/*
   @And("^I navigate to Loyallist Account Association page from My Account$")
    public void iNavigateToLoyallistAccountAssociationPageFromMyAccount() throws Throwable {

       if (!onPage("my_account")) {
           Clicks.click("header.goto_my_account_link");
       }
       Assert.assertTrue("Could not find Add my Loyallist Account link",
               Wait.untilElementPresent("my_account.add_loyalty_number"));

       Clicks.click("my_account.add_loyalty_number");
       Assert.assertTrue("Could not navigate to Loyallist association page",
               Wait.untilElementPresent("loyalty_association.verify_page"));
   }*/

//    @And("^I can associate my account by loyallist number using \"([^\"]*)\"$")
//    public void iCanAssociateMyAccountByLoyallistNumberUsing(String lty_type) throws Throwable {
//
//        myAccountSteps.iShouldBeAbleToAssociateMyAccountByLoyallistNumberUsingDetails(lty_type);
//    }

   /* @Then("^I should see title as my bWallet$")
    public void iShouldSeeTitleAsMyBWallet() throws Throwable {

        Assert.assertTrue("MY bWallet header not found in My Account page",
                Wait.untilElementPresent("my_account.myaccount_wallet_section_header"));
    }

    @And("^I should see the number of offers in my bWallet$")
    public void iShouldSeeTheNumberOfOffersInMyBWallet() throws Throwable {

        String text = Elements.findElement("my_account.myaccount_saved_offers_text").getText();
        Assert.assertTrue("Num Offers in My Account page do not match with actual offers added",
                OCWallet.totalOffers == Integer.parseInt(text.substring(0,1)));
    }

    @And("^I should see link 'View my bWallet' that takes me to bWallet page$")
    public void iShouldSeeLinkViewMyBWalletThatTakesMeToBWalletPage() throws Throwable {

        Assert.assertTrue("View my bWallet link not found in My Account page",
                Wait.untilElementPresent("my_account.myaccount_wallet_link"));
    }*/

    @When("^I enroll into the Loyalty program by creating a new profile$")
    public void iEnrollIntoTheLoyaltyProgramByCreatingANewProfile() throws Throwable {

        myAccountSteps.iShouldBeAbleToEnrollInToTheLoyaltyProgramAsAUser("guest");
    }

    @And("^I navigate to the loyalty account summary page$")
    public void iNavigateToTheLoyaltyAccountSummaryPage() throws Throwable {

        Clicks.click("header.footer_loyallist");
    }

    @Then("^I verify the account summary page for the loyallist$")
    public void iVerifyTheAccountSummaryPageForTheLoyallist() throws Throwable {

        Assert.assertTrue("Loyallist header image could not be verified",
                Elements.elementPresent("loyallist_account_summary.loyalty_header_img"));

        Assert.assertTrue("Remove button could not be verified",
                Elements.elementPresent("loyallist_account_summary.remove_button"));

        Assert.assertTrue("Edit button could not be verified",
                Elements.elementPresent("loyallist_account_summary.edit"));

        Assert.assertTrue("Dog image could not be verified",
                Elements.elementPresent("loyallist_account_summary.loyalty_dog"));

        Assert.assertTrue("Loyalty badge could not be verified",
                Elements.elementPresent("loyallist_account_summary.loyalty_badge"));

    }

    @When("^I select \"([^\"]*)\" link from dashboard$")
    public void iSelectLinkFromDashboard(String link) throws Throwable {

        switch (link) {

            case "My Account":
                Clicks.hoverForSelection("header.goto_my_account_link");
                Wait.untilElementPresent("header.goto_my_account_link");
                Clicks.click("header.goto_my_account_link");
                break;
            case "Order Status & History":
                Wait.ajaxDone();
                Clicks.hoverForSelection("header.goto_my_account_link");
                Wait.untilElementPresent("header.order_status_history_dropdown");
                Clicks.click("header.order_status_history_dropdown");
                break;
            case "Pay My Bill":
                Clicks.hoverForSelection("header.goto_my_account_link");
                Wait.untilElementPresent("header.pay_my_bill_dropdown");
                Clicks.click("header.pay_my_bill_dropdown");
                break;
        }
    }

   /* @Then("^I verify that the \"([^\"]*)\" page is rendered$")
    public void iVerifyThatThePageIsRendered(String page) throws Throwable {

        switch (page) {

            case "My Account":
                Assert.assertTrue("Could not navigate to My Account page",onPage("my_account"));
                Assert.assertTrue("My Account title could not be verified",
                        WebDriverManager.getCurrentTitle().equals("My Account - Bloomingdale's"));
                break;
            case "Order Status & History":
                Assert.assertTrue("Could not navigate to Order Status & History",onPage("order_status"));
                Assert.assertTrue("My Account title could not be verified",
                        WebDriverManager.getCurrentTitle().equals("My Account - Bloomingdale's"));
                break;
            case "Pay My Bill":
                Assert.assertTrue("Could not navigate to Pay My Bill page",onPage("my_account"));
                Assert.assertTrue("My Account title could not be verified",
                        WebDriverManager.getCurrentTitle().equals("My Account - Bloomingdale's"));
                break;
        }

    }*/


    /*@Given("^I visit the web site as a Plenti user with a \"([^\"]*)\" status$")
    public void iVisitTheWebSiteAsAPlentiUserWithAStatus(String arg0) throws Throwable {

        new PageNavigation().I_visit_the_web_site_as_a_registered_user();

    } */

}
