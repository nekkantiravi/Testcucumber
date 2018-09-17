package com.macys.sdt.projects.Marketing.Plenti.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.model.user.UslInfo;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Marketing.OCWallet.steps.website.mcom.OCWallet;
import com.macys.sdt.projects.Marketing.Plenti.utils.Usl;
import com.macys.sdt.shared.actions.MEW.pages.PlentiEnroll;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.USLEnrollment;
import com.macys.sdt.shared.steps.website.CheckoutSteps;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

//import com.macys.sdt.projects.Marketing.OCWallet.actions.website.mcom.pages.Wallet;

public class PlentiSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(PlentiSteps.class);
    private static UslInfo uslToValidate;
    private static MyAccountSteps myAccountSteps = new MyAccountSteps();
    private static CommonSteps commonSteps = new CommonSteps();

    @Then("^I perform all valid Plenti lookups on the \"([^\"]*)\" page$")
    public void i_perform_all_valid_Plenti_lookups_on_the_page(String pageName) throws Throwable {

        logger.info("Verifying lookup based on ID");
        String plentiTypes[] = {"fully_enrolled", "pre_enrolled", "anonymous", "cancelled"};

        for (int i = 0; i < plentiTypes.length; i++) {
            UslInfo uslInfo = Usl.getEnrolledUslId(plentiTypes[i]);

            if (uslInfo == null) {
                Assert.fail("No plenti id found of type: " + plentiTypes[i]);
            }

            switch (pageName) {

                case "my_account":
                    if (plentiTypes[i].equals("fully_enrolled") || plentiTypes[i].equals("pre_enrolled")) {
                        Usl.plentiLookupByPhoneMyAccount(uslInfo);
                        Usl.verifyUslLookupMyAccount(uslInfo.getUslType().getName(), uslInfo);
                        Usl.removePlentiFromMyAccount();
                    }

                    Usl.plentiLookupByIdMyAccount(uslInfo);
                    Usl.verifyUslLookupMyAccount(uslInfo.getUslType().getName(), uslInfo);
                    if (!plentiTypes[i].equals("cancelled")) {
                        Usl.removePlentiFromMyAccount();
                    }
                    break;
                case "shopping_bag":
                    if (plentiTypes[i].equals("fully_enrolled") || plentiTypes[i].equals("pre_enrolled")) {
                        Usl.plentiLookupByPhoneShoppingBag(uslInfo);
                        Usl.verifyUslLookupShoppingBag(uslInfo.getUslType().getName(), uslInfo);
                        Usl.removePlentiFromShoppingBag();
                    }

                    Usl.plentiLookupByIdShoppingBag(uslInfo);
                    Usl.verifyUslLookupShoppingBag(uslInfo.getUslType().getName(), uslInfo);
                    if (!plentiTypes[i].equals("cancelled")) {
                        Usl.removePlentiFromShoppingBag();
                    }
                    break;
                case "payment":
                    if (plentiTypes[i].equals("fully_enrolled") || plentiTypes[i].equals("pre_enrolled")) {
                        Usl.plentiLookupByPhonePaymentPage(uslInfo);
                        Usl.verifyUslLookupPaymentPage(uslInfo.getUslType().getName(), uslInfo);
                        Usl.removePlentiFromPaymentPage();
                    }

                    Usl.plentiLookupByIdPaymentPage(uslInfo);
                    Usl.verifyUslLookupPaymentPage(uslInfo.getUslType().getName(), uslInfo);
                    if (!plentiTypes[i].equals("cancelled")) {
                        Usl.removePlentiFromPaymentPage();
                    }

                   /* if (!plentiTypes[i].equals("anonymous")) {
                        Usl.VerifyLookupByCCPaymentPage(pageName, uslInfo);
                        if (!plentiTypes[i].equals("cancelled")) {
                            Usl.removePlentiFromPaymentPage();
                        }
                    }*/
                    break;
            }
        }
    }

    @Given("^I visit the web site as a Plenti user with a \"([^\"]*)\" status$")
    public void iVisitTheWebSiteAsAPlentiUserWithAStatus(String plentiType) throws Throwable {
        new PageNavigation().I_visit_the_web_site_as_a_registered_user();
        UslInfo uslInfo = Usl.getEnrolledUslId(plentiType);
        uslToValidate = uslInfo;
        Usl.plentiLookupByPhoneMyAccount(uslInfo);
    }

    @Then("^I validate Plenti redeem functionality through checkout as a \"([^\"]*)\" user$")
    public void iValidatePlentiRedeemFunctionalityThroughCheckoutAsAUser(String userType) throws Throwable {
        String pageName = userType.equals("guest") ? "responsive_payment_guest_section" : "shipping & payment";
        new OCWallet().iCheckoutUntilIReachPageAsAUser(pageName, userType);
        Usl.redeemPlentiPoints();
        pageName = "order review";
        new CheckoutSteps().I_checkout_until_I_reach_the_page_as_a_user(pageName, userType, null, null);
        pageName = "order confirmation";
        new CheckoutSteps().I_checkout_until_I_reach_the_page_as_a_user(pageName, userType, null, null);
        Usl.verifyUslPointsInOrderConfirmationPage();
    }

    @And("^I add \"([^\"]*)\" id on shopping bag page$")
    public void iAddIdOnShoppingBagPage(String plentiType) throws Throwable {
        if (!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
        UslInfo uslInfo = Usl.getEnrolledUslId(plentiType);
        Usl.plentiLookupByPhoneShoppingBag(uslInfo);
    }


    @Then("^I validate Plenti functionality through checkout as a \"([^\"]*)\" user$")
    public void iValidatePlentiFunctionalityThroughCheckoutAsAUser(String userType) throws Throwable {

        if (!onPage("shopping_bag")) {
            Navigate.visit("shopping_bag");
        }
        String pageName = userType.equals("guest") ? "responsive_payment_guest_section" : "shipping & payment";
        new OCWallet().iCheckoutUntilIReachPageAsAUser(pageName, userType);
        pageName = "order review";
        new CheckoutSteps().I_checkout_until_I_reach_the_page_as_a_user(pageName, userType, null, null);
        pageName = "order confirmation";
        new CheckoutSteps().I_checkout_until_I_reach_the_page_as_a_user(pageName, userType, null, null);
        Usl.verifyUslPointsInOrderConfirmationPage();
    }

    @Given("^I visit the web site as a Plenti user$")
    public void iVisitTheWebSiteAsAPlentiUser() throws Throwable {

        Navigate.visit(RunConfig.url + "/account/signin?program=usl");
        Clicks.click("sign_in.create_profile");
        PlentiEnroll.doAuthentication();
        USLEnrollment.enroll(TestUsers.getuslCustomer(null));
        USLEnrollment.enrollStep1(TestUsers.getuslCustomer(null));
        USLEnrollment.completeEnrollment();
        USLEnrollment.linkCreditCardAndSetPreferences();
    }

    @When("^I lookup the USL ID manually specified in this step:$")
    public void iLookupTheUSLIDManuallySpecifiedInThisStep(DataTable plentiData) throws Throwable {

        List<List<String>> data = plentiData.raw();
        UslInfo uslInfo = new UslInfo(UslInfo.UslType.ANONYMOUS, data.get(1).get(0),
                data.get(1).get(2), "", UslInfo.CardType.COBRAND, "", "");
        USLEnrollment.plentiId = uslInfo.getPlentiId();
        USLEnrollment.activationCode = data.get(1).get(1);
        USLEnrollment.plentiPhone = uslInfo.getUslPhone();

        if (onPage("my_account")) {
            Usl.plentiLookupByIdMyAccount(uslInfo);
        }
        if (onPage("add_to_bag")) {
            Navigate.visit("shopping_bag");
            Usl.plentiLookupByIdShoppingBag(uslInfo);
        }
    }

    @Then("^I finalize enrollment for the Plenti ID$")
    public void iFinalizeEnrollmentForThePlentiID() throws Throwable {
        if (onPage("shopping_bag")) {
            if (!Elements.elementPresent("shopping_bag.plenti_finish_signup_link")) {
                Assert.fail("Finish sign up link not found");
            } else {
                Clicks.click("shopping_bag.plenti_finish_signup_link");
            }
        }

        if (onPage("my_account")) {
            if (!Elements.elementPresent("my_account.plenti_lookup_prompt_message")) {
                Assert.fail("Finish sign up link not found");
            } else {
                Clicks.click("my_account.plenti_lookup_prompt_message");
            }
        }

        if (!signedIn()) {
            Clicks.click("sign_in.create_profile");
        }
        USLEnrollment.enroll(TestUsers.getuslCustomer(null));
        USLEnrollment.enrollStep1(TestUsers.getuslCustomer(null));
        USLEnrollment.completeEnrollment();
        USLEnrollment.linkCreditCardAndSetPreferences();
    }

    @Then("^I should be able to add below USL ID on payment page:$")
    public void iShouldBeAbleToAddBelowUSLIDOnPaymentPage(DataTable plentiType) throws Throwable {

        for (Map<String, String> row : plentiType.asMaps(String.class, String.class)) {
            String type = row.get("usl_id_type");
            UslInfo uslInfo = Usl.getEnrolledUslId(type);

            if (uslInfo == null) {
                Assert.fail("No plenti id found of type: " + row.get("usl_id_type"));
            }

            Usl.plentiLookupByPhonePaymentPage(uslInfo);
            Usl.verifyUslLookupPaymentPage(uslInfo.getUslType().getName(), uslInfo);
            Usl.removePlentiFromPaymentPage();

            Usl.plentiLookupByIdPaymentPage(uslInfo);
            Usl.verifyUslLookupPaymentPage(uslInfo.getUslType().getName(), uslInfo);
            Usl.removePlentiFromPaymentPage();
        }
    }

    @Then("^I should see USL ID linked on payment page$")
    public void iShouldSeeUSLIDLinkedOnPaymentPage() throws Throwable {

        UslInfo uslInfo = PlentiSteps.uslToValidate;
        Usl.verifyUslLookupPaymentPage(uslInfo.getUslType().getName(), uslInfo);
        myAccountSteps.iNavigateToMyAccountPage();
        Usl.removePlentiFromMyAccount();
    }

    @Then("^I finalize enrollment for the PreEnrolled Plenti ID$")
    public void iFinalizeEnrollmentForThePlentiIDForPreEnrolled() throws Throwable {
        if (onPage("shopping_bag")) {
            if (!Elements.elementPresent("shopping_bag.plenti_finish_signup_link")) {
                Assert.fail("Finish sign up link not found");
            } else {
                Clicks.click("shopping_bag.plenti_finish_signup_link");
            }
        }

        if (onPage("my_account")) {
            if (!Elements.elementPresent("my_account.plenti_lookup_prompt_message")) {
                Assert.fail("Finish sign up link not found");
            } else {
                Clicks.click("my_account.plenti_lookup_prompt_message");
            }
        }

        if (!signedIn()) {
            Clicks.click("sign_in.create_profile");
        }
        USLEnrollment.enroll(TestUsers.getuslCustomer(null));
        USLEnrollment.PreenrollStep1And2(TestUsers.getuslCustomer(null));
        USLEnrollment.enrollSignup(TestUsers.getuslCustomer(null));
        USLEnrollment.completeEnrollment();
        USLEnrollment.linkCreditCardAndSetPreferences();
    }
}