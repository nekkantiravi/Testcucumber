package com.macys.sdt.projects.Customer.CreditSystemConversion.steps.website;

import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.interactions.*; 
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Customer.CreditSystemConversion.utils.CreditAppService;
import com.macys.sdt.projects.Customer.CreditSystemConversion.utils.UserService;
import com.macys.sdt.projects.Customer.CreditSystemConversion.utils.CustomerUtils;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.framework.runner.WebDriverManager;
import cucumber.api.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;import org.openqa.selenium.WebElement;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.text.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.text.DateFormat;

import static com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile.selectCustomDropDownIfPresent;
import static com.sun.jna.platform.win32.COM.tlb.TlbImp.logInfo;

/**
 * Created by ndevanur on 11/22/2016.
 */
public class CreditSystemConversionValidations extends StepUtils {
    private String card_type;
    List<HashMap> apiCreditCardDetails = new ArrayList<>();
    HashMap<String, String> schedulePaymentDetails = new HashMap<>();
    String paymentAmount = null;
    String paymentDate = null;
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

//    @And("^I clicked on Add Card button$")
//    public void iClickOnAddCardButton() throws Throwable {
////        wait(10);
//        Navigate.browserRefresh();
//        Clicks.click("my_account.csr_add_card_to_my_account_button");
//    }

    @When("^I clicked on Add Card button$")
    public void i_clicked_on_Add_Card_button() throws Throwable {
        Navigate.browserRefresh();
        Clicks.click("my_account.csr_add_card_to_my_account_button");
    }

    @Then("^I should see citi fusion page$")
    public void iShouldSeeCitiFusionPage() throws Throwable {
        Wait.secondsUntilElementPresent("fusion_add_card.verify_page", 30);
        if (!onPage("fusion_add_card")) {
            Assert.fail("Not navigated to the Citi fusion add card page");
        }
    }

    @Given("^I sign in with user having \"([^\"]*)\" added in (wallet|profile)$")
    public void iSignInWithUser(String accountType, String pagename) throws Throwable {
        Map<String, String> loginCredentials = new HashMap<>();
        loginCredentials = CustomerUtils.getCityUserCredentials(accountType);
        PageNavigation pageNavigationObj = new PageNavigation();
        pageNavigationObj.I_visit_the_web_site_as_a_guest_user();
        MyAccountSteps myAccountObj = new MyAccountSteps();
        myAccountObj.iNavigateToMyAccountPage();
        TextBoxes.typeTextbox("sign_in.email", loginCredentials.get("email").toString());
        TextBoxes.typeTextbox("sign_in.password", loginCredentials.get("password").toString());
        Clicks.click("sign_in.verify_page");
        Clicks.clickIfPresent("sign_in.close_overlay");
        Wait.untilElementNotPresent("sign_in.verify_page");
//        Wait.forPageReady();
    }

    @Then("^I (should not|should) see edit link on wallet page$")
    public void iShouldVerifyEditLinkForTheNewCobrandCardOfAgeInMobileWebSiteWalletPage(String edit_display) throws Throwable {
        String page = macys() ? "oc_my_wallet" : "my_bwallet";
        if (edit_display.contains("not"))
            Assert.assertFalse("For Card with age : " + edit_display + " EDIT link is NOT supressed in wallet page", Elements.elementPresent(page + ".edit_credit_cards"));
        else
            Assert.assertTrue("For Card with age : " + edit_display + " EDIT link is  supressed in wallet page", Elements.elementPresent(page + ".edit_credit_cards"));
    }

    @When("^I select \"([^\"]*)\" link on apply and learn more page$")
    public void iSelectLinkOnApplyAndLearnMorePage(String link) throws Throwable {
        Clicks.click("credit_service_marketing." + link);
    }

    @Then("^I should redirect to \"([^\"]*)\" page$")
    public void iShouldRedirectToCitiSitePage(String page) throws Throwable {
        if (page.equals("apply now")) {
            Wait.secondsUntilElementPresent("fusion_apply_card.verify_page", 40);
            shouldBeOnPage("fusion_apply_card");
        } else {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent(page + ".verify_page", 60);
            shouldBeOnPage(page);
        }
    }

    @Then("^I should see disclosure copy of \"([^\"]*)\" on Learn more & apply page$")
    public void iShouldSeeDisclosureCopyOfOnLearnMoreApplyPage(String exclusion_links) {
        Assert.assertTrue("expected_disclosure_text is not displaying",
                CustomerUtils.getExpectedMessage(exclusion_links).equals(Elements.findElement("credit_service_marketing.disclosure_copy").getText()));
    }

    @Then("^I edit button should be (enabled|disabled) onclick change in credit card section$")
    public void editButtonShouldBeDisabledOnclickChangeInCreditCardSection(String state) throws Throwable {

        Wait.untilElementPresent("responsive_checkout_signed_in.change_credit_card_button");
        Clicks.click("responsive_checkout_signed_in.change_credit_card_button");
        Wait.untilElementPresent("responsive_checkout_signed_in.add_credit_card_button");

        boolean editEnabled = Elements.elementPresent("responsive_checkout_signed_in.edit_credit_card");
        if (state.equals("enabled")) {
            Assert.assertTrue("Edit button is disabled", editEnabled);
        } else {
            Assert.assertFalse("Edit button is enabled", editEnabled);
        }
    }


    @Then("^I should be navigated to gateway page$")
    public void iShouldBeNavigatedToGatewayPage() throws Throwable {
        shouldBeOnPage("credit_service_gateway_signedin");
    }

    @And("^I complete security questions and return to macys$")
    public void iCompleteSecurityQuestionsAndReturnToMacys() throws Throwable {
        selectCustomDropDownIfPresent("fusion_add_card.security_question_one", "fusion_add_card.select_q1", "security_ans_q1");
        selectCustomDropDownIfPresent("fusion_add_card.security_question_two", "fusion_add_card.select_q2", "security_ans_q2");
        selectCustomDropDownIfPresent("fusion_add_card.security_question_three", "fusion_add_card.select_q3", "security_ans_q3");
        Clicks.click("fusion_add_card.secq_next_button");
        Clicks.click("fusion_add_card.return_to_macys");
    }


    @Then("^I Enter \"([^\"]*)\" Information and add card$")
    public void iEnterInformationAndAddCard(String cardtype) throws Throwable {
        Map<String, String> cardDetails = new HashMap<>();
        cardDetails = CustomerUtils.getCardDetails(cardtype);
        TextBoxes.typeTextbox("fusion_add_card.card_number", cardDetails.get("card_number").toString());
        TextBoxes.typeTextbox("fusion_add_card.card_name", cardDetails.get("card_name").toString());
        TextBoxes.typeTextbox("fusion_add_card.cvv_code", cardDetails.get("security_code").toString());
        TextBoxes.typeTextbox("fusion_add_card.ssn_code", cardDetails.get("card_ssn").toString());
        Clicks.click("fusion_add_card.add_card_button");
    }

    @And("^I clicked on Apply Now button$")
    public void iClickedOnApplyNowButton() throws Throwable {
        Wait.secondsUntilElementPresent("credit_service_gateway_guest.apply_now_button", 40);
        Clicks.click("credit_service_gateway_guest.apply_now_button");
    }


    @Then("^I Enter Customer details \"([^\"]*)\" and apply for card in \"([^\"]*)\"$")
    public void iEnterCustomerDetailsAndApplyForCardIn(String userdetails, String pageName) throws Throwable {
        Map<String, String> applyCarddetails = new HashMap<>();
        applyCarddetails = CustomerUtils.getCityApplyCarddetials(userdetails);
        Navigate.browserRefresh();
        TextBoxes.typeTextbox(pageName + ".first_name", applyCarddetails.get("firstname").toString());
        TextBoxes.typeTextbox(pageName + ".last_name", applyCarddetails.get("lastname").toString());
        Clicks.click(pageName + ".emailAddress_btn");
        TextBoxes.typeTextbox(pageName + ".emailAddress_edit", applyCarddetails.get("email").toString());
        Clicks.click(pageName + ".address_btn");
        TextBoxes.typeTextbox(pageName + ".street_address", applyCarddetails.get("address").toString());
        TextBoxes.typeTextbox(pageName + ".city", applyCarddetails.get("city").toString());
        TextBoxes.typeTextbox(pageName + ".zipcode", applyCarddetails.get("ZIPcode").toString());
        DropDowns.selectByText(pageName + ".state_dropdown", applyCarddetails.get("state").toString());
        Clicks.click(pageName + ".phone_btn");
        TextBoxes.typeTextbox(pageName + ".phone", applyCarddetails.get("phone").toString());
        DropDowns.selectByText(pageName + ".phone_type", applyCarddetails.get("phonetype").toString());
        Clicks.click(pageName + ".financial_information");
        TextBoxes.typeTextbox(pageName + ".rent_payment", applyCarddetails.get("rent").toString());
        TextBoxes.typeTextbox(pageName + ".total_income", applyCarddetails.get("annualincome").toString());
        DropDowns.selectByText(pageName + ".residence_status_dropdown", applyCarddetails.get("residencestatus").toString());
        Clicks.click(pageName + ".identification_btn");
        TextBoxes.typeTextbox(pageName + ".ssn", applyCarddetails.get("SSN").toString());
        Clicks.click(pageName + ".dob_btn");
        TextBoxes.typeTextbox(pageName + ".dob", applyCarddetails.get("DOB").toString());
        Clicks.click(pageName + ".apply_checkbox");
        Clicks.click(pageName + ".submit_application");
        Wait.secondsUntilElementPresent(pageName + ".accept", 60);
        Clicks.click(pageName + ".accept");


    }

    @When("^I navigate to credit card page from footer$")
    public void iNavigateToCreditCardPageFromFooter() throws Throwable {

        if (Elements.elementPresent("home.goto_credit_services")) {
            Clicks.click("home.goto_credit_services");
        } else {
            Assert.fail("Credit service link is not found in the footer section");
        }

    }

    @Then("^I should see the added offers in credit gateway page$")
    public void iShouldSeeTheAddedOffersInCreditGatewayPage() throws Throwable {
        String expectedString = null;
        Wait.untilElementPresent("credit_service_gateway_signedin.verify_page");
        Assert.assertTrue("Offers message is NOT avalible in Credit gateway page", Elements.elementPresent("credit_service_gateway_signedin.credit_services_wallet_offer"));
        String onlineID = macys() ? "macys_online_uid" : "bloomies_online_uid";
        String cookieUserId = Cookies.getCookieValue(onlineID);
        int rowCount = new UserService().getRowCountWallet(cookieUserId);
        if (rowCount == 1) {
            expectedString = "Great news! There " + "is 1 offer   " + "in your wallet.See Them Now";
        } else {
            expectedString = "Great news! There " + "are " + rowCount + " offers " + "in your wallet.See Them Now";
        }
        Assert.assertTrue("Offer Message is NOT matching in UI", (Elements.getText("credit_service_gateway_signedin.credit_services_wallet_offer").replaceAll("\n+", "")).equals(expectedString));

    }

    @Then("^I should see the add card to wallet in credit gateway page$")
    public void iShouldSeeTheAddCardToWalletInCreditGatewayPage() throws Throwable {
        Wait.untilElementPresent("credit_service_gateway_signedin.verify_page");

        if (Elements.elementPresent("credit_service_gateway_signedin.credit_services_addtocard_wallet_text")) {
            Assert.assertTrue("Add card to Wallet Offers message is NOT avalible in Credit gateway page", Elements.elementPresent("credit_service_gateway_signedin.credit_services_addtocard_wallet_text"));
            Assert.assertTrue("Add card to Wallet Offers link is NOT avalible in Credit gateway page", Elements.elementPresent("credit_service_gateway_signedin.add_card_to_wallet"));
        }
        String UIMessage = CustomerUtils.getExpectedMessage("credit_services_addtocard_wallet_text").toString();
        Assert.assertTrue("Add card to Wallet Offers message is NOT matching ", UIMessage.equals(Elements.getText(("credit_service_gateway_signedin.credit_services_addtocard_wallet_text")).replaceAll("\n+", "")));

    }

    @Then("^I should see the add offers message in credit gateway page$")
    public void iShouldSeeTheAddOffersMessageInCreditGatewayPage() throws Throwable {
        Wait.untilElementPresent("credit_service_gateway_signedin.verify_page");

        if (Elements.elementPresent("credit_service_gateway_signedin.credit_services_addoffers_text")) {
            Assert.assertTrue("Add card to Wallet Offers message is NOT avalible in Credit gateway page", Elements.elementPresent("credit_service_gateway_signedin.credit_services_addoffers_text"));
            Assert.assertTrue("Add card to Wallet Offers link is NOT avalible in Credit gateway page", Elements.elementPresent("credit_service_gateway_signedin.credit_services_addoffers_link"));

        }
        String UIMessage = CustomerUtils.getExpectedMessage("credit_services_addoffers_text").toString();
        Assert.assertTrue("Add card to Wallet Offers message is NOT matching ", UIMessage.equals(Elements.getText(("credit_service_gateway_signedin.credit_services_addoffers_text")).replaceAll("\n+", "")));

    }

    @And("^I select \"([^\"]*)\" on new account discount banner$")
    public void iSelectOnNewAccountDiscountBanner(String link) throws Throwable {
        Wait.secondsUntilElementPresent("credit_service_gateway_guest." + link, 40);
        Clicks.click("credit_service_gateway_guest." + link);
    }

    @Then("^I should reach \"([^\"]*)\" page$")
    public void iShouldReachPage(String target_url) throws Throwable {
        shouldBeOnPage(target_url);
        Assert.fail("Expected url is not displaying");
    }

    @Then("^I should see below fields on new account discount banner:$")
    public void iShouldSeeBelowFieldsOnNewAccountDiscountBanner(List<String> info_links) throws Throwable {
        info_links.forEach(info_link ->
                Assert.assertTrue(info_link + " link is not displaying on banner", Elements.elementPresent("credit_service_gateway_guest." + info_link))
        );

    }

    @Then("^I (should not|should) see Edit or remove a card link$")
    public void iShouldSeeEditOrRemoveACardLink(String edit_display) throws Throwable {
        if (edit_display.contains("not"))
            Assert.assertFalse("For Card with age : " + edit_display + " EDIT link is NOT supressed in wallet page", Elements.elementPresent("credit_service_gateway_signedin.edit_or_remove_link"));
        else
            Assert.assertTrue("For Card with age : " + edit_display + " EDIT link is  supressed in wallet page", Elements.elementPresent("credit_service_gateway_signedin.edit_or_remove_link"));
    }

    @And("^I select 'Edit or remove a card' link$")
    public void iSelectEditOrRemoveACardLink() throws Throwable {
        Clicks.click("credit_service_gateway_signedin.edit_or_remove_link");
    }

    @And("^I click on suppress link on \"([^\"]*)\" page$")
    public void iClickOnIconOnCobrandMessage(String page) throws Throwable {
        String onlineID = macys() ? "macys_online_uid" : "bloomingdales_online_uid";
        String cookieUserId = Cookies.getCookieValue(onlineID);
        CustomerUtils.updateUserNotification(cookieUserId);
        Navigate.browserRefresh();
        Wait.forPageReady();

        if (page.contains("wallet"))
            Wait.secondsUntilElementPresent(page + ".add_credit_card", 30);
        if (page.contains("account"))
            iExpandNotificationMessage();

        Wait.secondsUntilElementPresent(page + ".cobrand_msg_close", 10);
        Clicks.javascriptClick(page + ".cobrand_msg_close");
    }

    @Then("^I (should not|should) see \"([^\"]*)\" on (wallet|credit gateway|my account) page$")
    public void iShouldSeeOnWalletPage(String msg_display, String message, String landing_page) throws Throwable {
        String page;
        if (landing_page.contains("wallet")) {
            page = macys() ? "oc_my_wallet" : "my_bwallet";
            String element = macys() ? "add_credit_card" : "add_credit_card_btn";
            Wait.secondsUntilElementPresent(page + "." + element, 30);
        } else
            page = landing_page.contains("account") ? "my_account" : "credit_service_gateway_signedin";

        boolean notification_exists = Elements.elementPresent(page + ".cobrand_message_notification");
        if (msg_display.contains("not")) {
            if (notification_exists)
                Assert.assertFalse("User is displaying with" + message + " on " + landing_page + "page",
                        (Elements.findElement(page + ".cobrand_message_notification").getText().replaceAll("’", "'")).contains(CustomerUtils.getExpectedMessage(message).replaceAll("’", "'")));
            else
                Assert.assertFalse("User is displaying with" + message + " on " + landing_page + "page", notification_exists);
        } else {
            enableCobrandMessage();
            if (landing_page.contains("account"))
                iExpandNotificationMessage();

            Wait.secondsUntilElementPresent((page + ".cobrand_message_notification"), 10);
            Assert.assertTrue("User is not displaying with" + message + " on " + landing_page + "page", Elements.elementPresent(page + ".cobrand_message_notification"));
            Assert.assertTrue(message + " content is not displaying as expected" + "on " + landing_page + "page",
                    (Elements.findElement(page + ".cobrand_message_notification").getText().replaceAll("’", "'")).contains(CustomerUtils.getExpectedMessage(message).replaceAll("’", "'")));
        }
    }

    @And("^I should see \"([^\"]*)\" process indicator$")
    public void iShouldSeeProcessIndicator(String process_indicator) throws Throwable {
        String current_url = WebDriverManager.getCurrentUrl();
        Assert.assertTrue("Process indicator is not displaying as expected in " + current_url, current_url.contains(process_indicator));
    }

    @Then("^I should reach on the \"([^\"]*)\" page$")
    public void iShouldReachOnThePage(String target_url) throws Throwable {
        Assert.assertTrue("User not redirected to expected page " + WebDriverManager.getCurrentUrl(), Wait.until(() -> WebDriverManager.getCurrentUrl().contains(target_url)));
    }

    @And("^I click on Find Out More link on \"([^\"]*)\" page$")
    public void iClickOnLearnMoreLinkOnPage(String page) throws Throwable {
        if (page.contains("wallet"))
            Wait.secondsUntilElementPresent(page + ".add_credit_card", 30);
        if (page.contains("account"))
            iExpandNotificationMessage();

        Wait.secondsUntilElementPresent(page + ".cobrand_msg_find_out_more_link", 10);
        Clicks.click(page + ".cobrand_msg_find_out_more_link");
    }

    @Given("^I open \"([^\"]*)\" through url$")
    public void iOpenThroughUrl(String deeplink_url) throws Throwable {
        Navigate.visit(RunConfig.url + deeplink_url);
    }

    @When("^I sign in with \"([^\"]*)\" user$")
    public void iSignInWithUser(String user_details) throws Throwable {
        if (user_details.contains("profile_nocard")) {
            UserProfile customer = TestUsers.getCustomer(null);
            pausePageHangWatchDog();
            TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        } else {
            Map<String, String> loginCredentials = new HashMap<>();
            loginCredentials = CustomerUtils.getCityUserCredentials(user_details);
            TextBoxes.typeTextbox("sign_in.email", loginCredentials.get("email").toString());
            TextBoxes.typeTextbox("sign_in.password", loginCredentials.get("password").toString());
        }
        Clicks.click("sign_in.verify_page");
        Wait.untilElementNotPresent("sign_in.verify_page");
    }

    @Then("^I should get a pop-up to add a card to profile$")
    public void iShouldGetAPopUpToAddACardToProfile() throws Throwable {
        Assert.assertTrue("add card overlay is not displayed", Elements.elementPresent("credit_service_gateway_signedin.add_card_on_overlay"));
    }

    @And("^I clicked on Add Card button on credit gate way page$")
    public void iClickedOnAddCardButtonOnCreditGateWayPage() throws Throwable {
        Clicks.click("credit_service_gateway_signedin.add_card_on_overlay");
    }

    @Then("^I should see notification message with replacement card header$")
    public void iShouldSeeCobrandMessageSectionInCollapsedState() throws Throwable {
        enableCobrandMessage();
        Wait.secondsUntilElementPresent("my_account.card_notification", 10);
        Assert.assertTrue("Cobrand message notification is not displaying",
                (Elements.findElement("my_account.card_notification").getText()).contains("Replacement Card"));
        Assert.assertFalse("Cobrand message notification is not in collapsed state", Elements.elementPresent("my_account.cobrand_msg_close"));
    }

    @And("^I expand notification message$")
    public void iExpandNotificationMessage() throws Throwable {
        Clicks.click("my_account.notification_expand");
    }

    @Then("^I should not see notification message with replacement card header$")
    public void iShouldNotSeeNotificationMessageWithReplacementCardHeader() throws Throwable {
        boolean notification_exists = Elements.elementPresent("my_account.card_notification");
        if (notification_exists)
            Assert.assertFalse("Cobrand message notification is not displaying",
                    (Elements.findElement("my_account.card_notification").getText()).contains("Replacement card"));
        else
            Assert.assertFalse("Cobrand message notification is still displaying", notification_exists);
    }

    @And("^I add ResponsiveMyAccount experiment cookie$")
    public void addExperimentCookie() throws Throwable {
        String experimentCookie = "2251";
        String controlCookie = "2252";
        String segmentCookie = Cookies.getCookieValue("SEGMENT");
        //If experiment cookie is not already added.
        if (!segmentCookie.contains(experimentCookie)) {
            Cookies.removeSegment(controlCookie);
            Cookies.addSegment(experimentCookie);
        }
    }

    @And("^I (should|should not) see (my account|credit gateway|my wallet) page with (LS_Indicator|auth_indicator) in credit card section$")
    public void iShouldSeeMyAccountPageWithLSIndicatorWithCard(String indicator_display, String notification_page, String indicator) throws Throwable {
        String page;
        if (notification_page.contains("wallet")) {
            page = macys() ? "oc_my_wallet" : "my_bwallet";
            String element = macys() ? "add_credit_card" : "add_credit_card_btn";
            Wait.secondsUntilElementPresent(page + "." + element, 30);
        } else
            page = notification_page.contains("account") ? "my_account" : "credit_service_gateway_signedin";
        if (indicator_display.contains("not"))
            Assert.assertFalse("User is displaying with indicator on " + page + "page", Elements.elementPresent(page + ".indicator"));
        else {
            String expected_indicator = CustomerUtils.getExpectedMessage(indicator);
            Assert.assertTrue(indicator + " is not displaying as expected" + "on " + notification_page + "page",
                    (Elements.findElement(page + ".indicator").getText()).contains(expected_indicator));
        }
    }

    @And("^I should see below fields on Learn More & Apply page$")
    public void iShouldSeeBelowFieldsOnLearnMoreApplyPage(List<String> elements) throws Throwable {
        elements.forEach(element ->
                Assert.assertTrue(element + " is not displayed on Responsive Learn & Apply Now page", Elements.elementPresent("credit_service_marketing." + element))
        );
    }

    @And("^I should see below credit card links in Responsive page left navigation:$")
    public void iShouldSeeBelowCreditCardLinksInResponsivePageLeftNavigation(List<HashMap<String, String>> left_nav_links) throws Throwable {
        ArrayList<String> failed_elements = links_verification(left_nav_links, "credit_service_marketing");
        if (failed_elements.size() > 0) {
            Assert.fail("Following Elements are not displayed in left navigation menu:" + failed_elements.toString() + "!!");
        }
    }

    @Then("^I should see below credit card links in footer section:$")
    public void iShouldSeeBelowCreditCardLinksInFooterSection(List<HashMap<String, String>> footer_links) throws Throwable {
        ArrayList<String> failed_elements = links_verification(footer_links, "my_account");
        if (failed_elements.size() > 0) {
            Assert.fail("Following Elements are not displayed in footer:" + failed_elements.toString() + "!!");
        }
    }

    @Then("^I should see Credit Agreements & Privacy Notice link is in collapsed state with expand symbol$")
    public void iShouldSeeCreditAgreementsAndPrivacyNoticeLinksInCollapsedState() throws Throwable {
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        Wait.secondsUntilElementPresent(page + ".expand_credit_agreement_and_privacy_notice", 25);
        Assert.assertTrue(Elements.elementPresent(page + ".credit_agreement_and_privacy_notice_header"));
        Assert.assertTrue(Elements.elementPresent(page + ".expand_credit_agreement_and_privacy_notice"));
    }

    @Then("^I should see below links under Credit Agreements & Privacy Notice link$")
    public void iShouldSeeBelowCreditCardLinksUnderCreditAgreementsPrivacyNoticelink(List<HashMap<String, String>> credit_agreement_links) throws Throwable {
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        ArrayList<String> failed_elements = links_verification(credit_agreement_links, page);
        if (failed_elements.size() > 0) {
            Assert.fail("Following Elements are not displayed under Credit Agreements & Privacy Notice link:" + failed_elements.toString() + "!!");
        }
    }

    @When("^I select on \"([^\"]*)\"$")
    public void iSelectOn(String btn) throws Throwable {
        Clicks.click("my_account." + btn);
    }

    @Then("^I should redirect to myAccount Page$")
    public void iShouldRedirectToMyAccountPage() throws Throwable {
        shouldBeOnPage("my_account");
    }


    @Then("^I should see below fields on my profile page$")
    public void iShouldSeeBelowFieldsOnMyProfilePage(List<String> info_links) throws Throwable {
        info_links.forEach(info_link ->
                Assert.assertTrue(info_link + " is displaying on my profile page", Elements.elementPresent("my_profile." + info_link))
        );
    }

    @Then("^I should not see below fields on my profile page$")
    public void iShouldNotSeeBelowFieldsOnMyProfilePage(List<String> info_links) throws Throwable {
        info_links.forEach(info_link ->
                Assert.assertFalse(info_link + " is displaying on my profile page", Elements.elementPresent("my_profile." + info_link))
        );
    }

    @Then("^I should see the card holder benefits page$")
    public void iShouldSeeTheCardHolderBenefitsPage() throws Throwable {
        Assert.assertTrue("Credit benefits page is not displayed", Elements.elementPresent("credit_benefits.verify_page"));
    }

    @Then("^I should see the learn more & apply page$")
    public void iShouldSeeTheLearnMoreApplyPage() throws Throwable {
        Assert.assertTrue("Learn more & apply page is not displayed", Elements.elementPresent("learn_more_and_apply_now.verify_page"));
    }

    @When("^I click on Apply Now button on \"([^\"]*)\" page$")
    public void iClickOnApplyNowButtonOnPage(String page) throws Throwable {
        if (page.equals("guest_gateway")) {
            Wait.secondsUntilElementPresent("credit_service_gateway_guest.apply_now_button", 40);
            Clicks.click("credit_service_gateway_guest.apply_now_button");
        } else if (page.equals("cardholder_benefits")) {
            Wait.secondsUntilElementPresent("credit_benefits.apply_now_button", 40);
            Clicks.click("credit_benefits.apply_now_button");
        } else if (page.equals("learn more & apply")) {
            Wait.secondsUntilElementPresent("credit_service_marketing.apply", 40);
            Clicks.click("credit_service_marketing.apply_now");
        } else {
            Wait.secondsUntilElementPresent("credit_service_marketing.apply_now_button", 40);
            Clicks.click("credit_service_marketing.apply_now_button");
        }
    }

    @When("^I select \"([^\"]*)\" link in ([^\"]*) page$")
    public void iSelectLinkInPage(String link, String page) throws Throwable {
        switch (page.toLowerCase()) {
            case "apply & learn more":
                Clicks.click("credit_service_marketing." + link);
                break;
            case "cardholder benefits":
                Clicks.click("credit_benefits." + link);
                if (bloomingdales())
                    Wait.forLoading("credit_benefits.cc_page");
                break;
            case "gateway sign":
                Clicks.click("credit_service_gateway_signedin." + link);
                break;
        }
    }

    @Then("^I should see updated text in card holder benefits page$")
    public void iShouldSeeUpdatedTextInCardHolderBenefitsPage() throws Throwable {
        Assert.assertTrue("updated Text is not displaying",
                (Elements.findElement("credit_benefits.earn_power_points").getText()).contains("Reward Account Balance"));

    }

    @Then("^I should see below information:$")
    public void iShouldSeeBelowInformation(List<String> elements) throws Throwable {
        String user = (signedIn() ? "signedin" : "guest");
        String page = "credit_service_gateway_" + user;
        elements.forEach(element ->
                Assert.assertTrue(element + " is not displayed on Responsive GateWay " + user + " page", Elements.elementPresent(page + "." + element))
        );
    }


    @And("^I tap on \"([^\"]*)\"$")
    public void iTapOn(String link) throws Throwable {
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        Wait.untilElementPresent(page + "." + link);
        Clicks.click(page + "." + link);
        Wait.forPageReady();
    }

    @Then("^I (should|should not) see other ways to pay overlay$")
    public void iShouldSeeOtherWaysToPayOverlay(String display) throws Throwable {
        if (!display.contains("not"))
            Assert.assertTrue(" Other Way To Pay Overlay is not displayed on Responsive GateWay Guest page", Elements.elementPresent("credit_service_gateway_guest.other_ways_to_pay_content"));
        else
            Assert.assertFalse(" Other Way To Pay Overlay is displayed on Responsive GateWay Guest page", Elements.elementPresent("credit_service_gateway_guest.other_ways_to_pay_content"));
    }


    @Then("^I should see AmexCard disclaimer text$")
    public void iShouldSeeAmexCardDisclaimerText() throws Throwable {
        String UIMessage = CustomerUtils.getExpectedMessage("AmexCard_disclaimer_text").toString();
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        Assert.assertTrue("AmexCard Disclaimer Text is NOT matching ", UIMessage.equals(Elements.getText(page + ".amex_card_disclaimer_text").replaceAll("\n+", "")));
    }

    @Then("^I should see below text in \"([^\"]*)\" section:$")
    public void iShouldSeeBelowElementsInOtherWaysToPaySection(String selector, List<String> text) throws Throwable {
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        text.forEach(info_link ->
                Assert.assertTrue(info_link + " is displaying on gateway  page",
                        Elements.findElement(page + "." + selector).getText().contains(info_link)));
    }

    @Then("^I should see \"([^\"]*)\" on gateway page$")
    public void iShouldSeeOnOverlay(String link) throws Throwable {
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        Assert.assertTrue("Other ways to pay is displayed", Elements.elementPresent(page + "." + link));
    }


    @Then("^I should see below elements in credit service gateway page:$")
    public void iShouldSeeBelowFieldsOnCreditServiceGatewayPage(List<String> elements) throws Throwable {
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        element_verification(elements, page);
    }

    @Then("^I should not see below elements in credit service gateway page:$")
    public void iShouldNotSeeBelowElementsInCreditServiceGatewayPage(List<String> elements) throws Throwable {
        String page = "credit_service_gateway_" + (signedIn() ? "signedin" : "guest");
        elements.forEach(element ->
                Assert.assertFalse(element + " is displayed on " + page + " page",
                        Elements.elementPresent(page + "." + element)));
    }

    @Then("^I should see notice text when user having card$")
    public void iShouldSeeNoticeTextWhenUserHavingCard() throws Throwable {
        String UIMessage = CustomerUtils.getExpectedMessage("notice_text").toString();
        Assert.assertTrue("Notice Text is NOT matching ", UIMessage.equals(Elements.getText("credit_service_gateway_signedin.notice_text").replaceAll("\n+", "")));
    }

    @And("^I should see below fields along with price on credit gateway page$")
    public void iShouldSeeBelowFieldsAlongWithPriceOnCreditGatewayPage(List<String> elements) throws Throwable {
        apiCreditCardDetails = CreditAppService.getRegisteredCardDetails(Cookies.getCookieValue(StepUtils.macys() ? "macys_online_uid" : "bloomingdales_online_uid"), Cookies.getCookieValue("secure_user_token"));
        CreditAppService.validateCreditCardInfo(apiCreditCardDetails, "credit_service_gateway_signedin", elements);
    }

    @And("^I should see \"([^\"]*)\" on card image$")
    public void iShouldSeeOnCardImage(String indicator) throws Throwable {
        Assert.assertTrue("Indicator is not displaying",
                CustomerUtils.getExpectedMessage(indicator).equals(Elements.findElement("credit_service_gateway_signedin." + indicator).getText()));
    }

    @And("^I should see next card using right carousel$")
    public void iShouldSeeNextCardUsingRightCarousel() throws Throwable {
        String card_before_navigation, card_after_navigation;
        Wait.untilElementPresent("credit_service_gateway_signedin.card_id");
        card_before_navigation = Elements.findElement("credit_service_gateway_signedin.card_id").getText();
        Clicks.click("credit_service_gateway_signedin.right_carousel");
        Wait.untilElementPresent("credit_service_gateway_signedin.card_id");
        card_after_navigation = Elements.findElement("credit_service_gateway_signedin.card_id").getText();
        Assert.assertFalse("Right carousel is not working", card_before_navigation.equals(card_after_navigation));
    }

    @And("^I should see previous card using left carousel$")
    public void iShouldSeePreviousCardUsingLeftCarousel() throws Throwable {
        String card_before_navigation, card_after_navigation;
        card_before_navigation = Elements.findElement("credit_service_gateway_signedin.card_id").getText();
        Clicks.click("credit_service_gateway_signedin.left_carousel");
        card_after_navigation = Elements.findElement("credit_service_gateway_signedin.card_id").getText();
        Assert.assertFalse("Left carousel is not working", card_before_navigation.equals(card_after_navigation));
    }

    @And("^I should see \"([^\"]*)\" (pre-populated|populated) with \"([^\"]*)\" as the date as defined by Eastern Time U.S.$")
    public void iShouldSeePaymentDate(String selector, String populated, String date) {
        String expected_date = "";
        String actual_date = Elements.getElementAttribute("one_time_payment."+"dp_"+selector.replaceAll(" ", "_").toLowerCase(), "value");
        if(date.contains("selected")) {
            expected_date = getCurrentDateAfter_n_Days(10, 0, 0, "MM/dd/yyyy");
        } else if(date.contains("Today")) {
            expected_date = new SimpleDateFormat("MM/dd/yyyy").format(new Date()).toString();
        }
        Assert.assertTrue("Dates are not matching", (expected_date.contentEquals(actual_date)));
    }

    @Then("^System should not accept the input and should display \"([^\"]*)\" date$")
    public void paymentDateShouldBeDisplayed(String date) {
        String paymentDate = Elements.getElementAttribute("one_time_payment.dp_payment_date_field", "value");
        String expectedDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date()).toString();
        Assert.assertTrue("Dates are not matching", (expectedDate.contentEquals(paymentDate)));
    }

    @When("^I tried to enter date into \"([^\"]*)\" using keyboard$")
    public void iTriedToEnterDateUsingKeyboard(String selector) {
        String input = getCurrentDateAfter_n_Days(10, 0, 0, "MM/dd/yyyy");
        try {
            TextBoxes.typeTextbox("one_time_payment.dp_"+selector.replaceAll(" ", "_").toLowerCase(), input);
        } catch(Exception e) {
            Assert.assertTrue(!(e.getMessage().isEmpty()));
        }
    }

    @And("^I should see the below fields in Date Picker Popup$")
    public void iShouldSeeBelowFieldsInDatePickerPopup(List<String> datePickerPopupFields) {
        Wait.secondsUntilElementPresent("otp_date_picker_popup.dp_calendar", 5);
        datePickerPopupFields.forEach(eachField ->
                Assert.assertTrue(eachField +" is not displayed on Date Picker Popup ",Elements.elementPresent("one_time_payment."+"dp_"+eachField.replaceAll(" ", "_").toLowerCase()))
        );
    }

    @And("^I should not see below fields in Date Picker Popup$")
    public void iShouldNotSeeBelowFieldsInDatePickerPopup(List<String> datePickerPopupFields) throws Throwable {
        Wait.secondsUntilElementPresent("otp_date_picker_popup.dp_calendar", 5);
        datePickerPopupFields.forEach(eachField ->
                Assert.assertFalse(eachField +" is not displayed on Date Picker Popup ",Elements.elementPresent("one_time_payment."+"dp_"+eachField.replaceAll(" ", "_").toLowerCase()))
        );
    }

    @Then("^Date picker popup should be displayed$")
    public void datePickerPopupShouldBeDisplayed() {
        Wait.secondsUntilElementPresent("one_time_payment.dp_calendar", 5);
        Assert.assertTrue("Date Picker popup is not displayed", Elements.anyPresent("one_time_payment.dp_calendar"));
    }

    @And("^I should see \"([^\"]*)\" date, as defined by Eastern Time U.S., is pre-selected$")
    public void dateShouldBePreselected(String date) {
        String preselectedDate = Elements.getText("one_time_payment.dp_todays_date");
        String expectedDate = new SimpleDateFormat("dd").format(new Date()).toString();
        Assert.assertTrue("Today's date "+expectedDate+" is not pre-selected ", expectedDate.equals(preselectedDate));
    }

    @When("^I navigate to My Preferences page from responsive gateway page$")
    public void iNavigateToMyPreferencesPageFromResponsiveGatewayPage() throws Throwable {
        Wait.secondsUntilElementPresent("navigation.goto_my_preferences_link", 60);
        Clicks.click("navigation.goto_my_preferences_link");
    }
	
	 @Then("^date picker popup should be closed$")
    public void datePickerPopupShouldBeClosed() {
        Assert.assertFalse("Date Picker popup is displayed", Elements.anyPresent("one_time_payment.dp_calendar"));
    }

    @But("^I should not see the following links in left navigation primary menu$")
    public void iShouldNotSeeTheFollowingLinksInLeftNavigationPrimaryMenu(List<String> elements) throws Throwable {
        elements.forEach(element ->
                Assert.assertFalse(element + " is not displayed on Responsive GateWay Guest page", Elements.elementPresent("credit_service_gateway_signedin." + element))
        );
    }

    @When("^I select \"([^\"]*)\" link$")
    public void iSelectLink(String link) throws Throwable {
        Clicks.click("navigation." + link);
    }

 @When("^I click on \"([^\"]*)\"$")
    public void iClickOnLocator(String selector) {
       Clicks.click("one_time_payment."+"dp_"+selector.replaceAll(" ", "_").toLowerCase());
    }

    @Then("^I should see \"([^\"]*)\" on schedule payment section$")
    public void iShouldSeeOnSchedulePaymentSection(String messge_type) throws Throwable {
        String schPaymentAmt = null, schPaymentDate = null;
        Wait.untilElementPresent("credit_service_gateway_signedin.card_id");
        String card_number = Elements.findElement("credit_service_gateway_signedin.card_id").getText();
        schedulePaymentDetails = CreditAppService.getSchedulePaymentDetails(Cookies.getCookieValue(StepUtils.macys() ? "macys_online_guid" : "bloomingdales_online_guid"), card_number);
        if (schedulePaymentDetails.containsKey("scheduledPaymentAmt"))
            schPaymentAmt = CustomerUtils.convertToCurrencyString(schedulePaymentDetails.get("scheduledPaymentAmt"));
        schPaymentDate = schedulePaymentDetails.get("scheduledPaymentDate");
        String expected_msg = CustomerUtils.getExpectedMessage(messge_type);
        if (schedulePaymentDetails.containsKey("scheduledPaymentAmt"))
            expected_msg = expected_msg.replace("$<sched_payment_amt>", schPaymentAmt);
        expected_msg = expected_msg.replace("<sched_payment_date>", schPaymentDate);
        Assert.assertTrue("ERROR - APP: Schedule payment message is not displaying as expected",
                Elements.findElement("credit_service_gateway_signedin.schedule_payment_message").getText().contains(expected_msg));
    }

    @And("^I should see below elements in (OTP1|OTP2) page:$")
    public void iShouldSeeBelowElementsInOTP1Page(String page, List<String> elements) throws Throwable {
        elements.forEach(element ->
                Assert.assertTrue(element + " is not displayed on " + page + "page", Elements.elementPresent("one_time_payment." + element))
        );

    }

    @And("^I should see below values in OTP1 page:$")
    public void iShouldSeeBelowValuesInOTP1Page(List<String> elements) throws Throwable {
        apiCreditCardDetails = CreditAppService.getRegisteredCardDetails(Cookies.getCookieValue(StepUtils.macys() ? "macys_online_uid" : "bloomingdales_online_uid"), Cookies.getCookieValue("secure_user_token"));
        CreditAppService.validateCreditCardInfo(apiCreditCardDetails, "OTP", elements);

    }

    @And("^I should see Payment Source on (OTP1|OTP2) page$")
    public void iShouldSeePaymentSource(String page) throws Throwable {
        String paymentSourceNbr = null;
        String card_text = Elements.findElement("one_time_payment.cardType").getText();
        String card_number = card_text.replaceAll("[^0-9]", "");
        schedulePaymentDetails = CreditAppService.getSchedulePaymentDetails(Cookies.getCookieValue(StepUtils.macys() ? "macys_online_guid" : "bloomingdales_online_guid"), card_number);
        paymentSourceNbr = schedulePaymentDetails.get("paymentSourceAccountNbr");
        Assert.assertTrue("ERROR - APP: OTP1: payment message is not displaying as expected",
                Elements.findElement("one_time_payment.payment_source_message_" + page).getText().contains("Account ending in " + paymentSourceNbr));

    }

    @And("^I should see \"([^\"]*)\" message$")
    public void iShouldSeeNotesSection(String messge_type) throws Throwable {
        String expected_msg = CustomerUtils.getExpectedMessage(messge_type);
        Assert.assertTrue("ERROR - APP:OTP1 disclaimer message is not displaying as expected",
                Elements.findElement("one_time_payment." + messge_type).getText().contains(expected_msg));
    }

    @And("^I tap \"([^\"]*)\" on \"([^\"]*)\" page$")
    public void iTapOnPage(String link, String page) throws Throwable {
        Wait.untilElementPresent(page + "." + link);
        Clicks.click(page + "." + link);
        Wait.forPageReady();
    }

    @And("^I should see below values in OTP2 page:$")
    public void iSeeBelowValuesOnOTP2Page(List<String> values) {
        for (String value : values) {
            switch (value) {
                case "payment_amount_value_otp2":
                    Assert.assertTrue("ERROR - APP: OTP2: payment amount is not displaying as expected",
                            paymentAmount.contains(Elements.findElement("one_time_payment.payment_amount_value_otp2").getText()));
                case "payment_date_value_otp2":
                    Assert.assertTrue("ERROR - APP: OTP2: payment date is not displaying as expected",
                            paymentDate.contains(Elements.findElement("one_time_payment.payment_date_value_otp2").getText()));
            }
        }
    }

    @When("^I continued to otp2 page with \"([^\"]*)\" as payment amount and \"([^\"]*)\" as payment date$")
    public void continueToOTP2Page(String paymentAmountOption, String paymentDateOption) throws Throwable {
        selectPaymentAmount(paymentAmountOption);
        selectPaymentDate(paymentDateOption);
        iTapOnPage("continue_button", "otp");

    }

    @When("^I selected \"([^\"]*)\" as payment amount in otp page$")
    public void selectPaymentAmount(String payAmount) throws Throwable {
        if (!Elements.findElement("one_time_payment." + payAmount + "_radio_button").isSelected()) {
            Elements.findElement("one_time_payment." + payAmount + "_radio_button").click();
        }
        if (payAmount.contains("other"))
            TextBoxes.typeTextbox("one_time_payment.other_amount_value", "60");
        paymentAmount = Elements.findElement("one_time_payment." + payAmount + "_value").getText();
    }

    @When("^I selected \"([^\"]*)\" as payment date in otp page$")
    public void selectPaymentDate(String payDate) throws Throwable {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        Date currentDatePlusOne = c.getTime();

        if (!Elements.findElement("one_time_payment." + payDate + "_radio_button").isSelected()) {
            Elements.findElement("one_time_payment." + payDate + "_radio_button").click();
        }
        if (payDate.contains("other")) {
            TextBoxes.typeTextbox("one_time_payment.other_date_value", dateFormat.format(currentDatePlusOne).toString());
        }
        if (payDate.contains("today"))
            paymentDate = dateFormat.format(date).toString();
        else
            paymentDate = Elements.findElement("one_time_payment." + payDate + "_value").getText();
    }


    public ArrayList<String> links_verification(List<HashMap<String, String>> left_nav_links, String page) {
        ArrayList<String> failed_elements = new ArrayList<>();
        for (Map link : left_nav_links) {
            String selector = page + "." + link.get("credit_link");
            if (!Elements.elementPresent(selector) || !(Elements.findElement(selector).getText()).contains((String) link.get("link_text")))
                failed_elements.add(page + "." + link.get("credit_link"));
        }
        return failed_elements;
    }

    public void element_verification(List<String> elements, String page) {
        elements.forEach(element ->
                Assert.assertTrue(element + " is not displayed on " + page + " page",
                        Elements.elementPresent(page + "." + element)));
    }

    public void enableCobrandMessage() {
        String onlineID = macys() ? "macys_online_uid" : "bloomingdales_online_uid";
        String cookieUserId = Cookies.getCookieValue(onlineID);
        CustomerUtils.updateUserNotification(cookieUserId);
        Navigate.browserRefresh();
        Wait.forPageReady();
    }

    @Then("^I should see 'Manage Autopay' in deeplinks$")
    public void iShouldSeeManageAutopayInDeeplinks() throws Throwable {
        Assert.assertTrue("Manage Auto pay link is not displayed", Elements.elementPresent("credit_service_gateway_signedin.manage_autopay_link"));
        logInfo("Successfully verified Manage Auto pay link on My Account page");
    }

    @Then("^I should see 'Enroll in Autopay' in deeplinks$")
    public void iShouldSeeEnrollInAutopayInDeeplinks() throws Throwable {
        Assert.assertTrue("Enroll In Auto pay link is not displayed", Elements.elementPresent("credit_service_gateway_signedin.enroll_in_autopay_link"));
        logInfo("Successfully verified Enroll In Auto pay link on My Account page");
    }

    @Then("^I should not see message is displaying on the UI for my account page$")
    public void iShouldNotSeeMessageIsDisplayingOnTheUIForMyAccountPage() {
        Assert.assertFalse("Schedule Payment message is displaying", Elements.elementPresent("credit_service_gateway_signedin.schedule_payment_message"));
        logInfo("Message is not displayed on My Account page");
    }

    @And("^I should see View details link as part of the scheduled payment message$")
    public void iShouldSeeViewDetailsLinkAsPartOfTheScheduledPaymentMessage() {
        Assert.assertTrue("View details link is displaying on My Account page", Elements.elementPresent("my_account.view_details_link"));
        logInfo("View Details link is  displayed on My Account page");
    }

    @When("^I click on View details link$")
    public void iClickOnViewDetailsLink() {
        Wait.forPageReady();
        Clicks.click("my_account.view_details_link");
    }
    @And("^I should not see view details link on the UI for my account page$")
    public void iShouldNotSeeViewDetailsLinkOnTheUIForMyAccountPage() {
        Assert.assertFalse("View details link is displaying on My Account page", Elements.elementPresent("my_account.view_details_link"));
        logInfo("View Details link is not displayed on My Account page");
    }

    @Then("^I should see \"([^\"]*)\" on schedule payment section on my account page$")
    public void iShouldSeeSchedulePaymentSectionOnMyAccountPage(String message_type) throws Throwable {
        String schPaymentAmt = null, schPaymentDate = null;
        Wait.untilElementPresent("my_account.card_id");
        String card_number = Elements.findElement("my_account.card_id").getText();
        schedulePaymentDetails = CreditAppService.getSchedulePaymentDetails(Cookies.getCookieValue(StepUtils.macys() ? "macys_online_guid" : "bloomingdales_online_guid"), card_number);
        if (schedulePaymentDetails.containsKey("scheduledPaymentAmt"))
            schPaymentAmt = CustomerUtils.convertToCurrencyString(schedulePaymentDetails.get("scheduledPaymentAmt"));
        schPaymentDate = schedulePaymentDetails.get("scheduledPaymentDate");
        String expected_msg = CustomerUtils.getExpectedMessage(message_type);
        if (schedulePaymentDetails.containsKey("scheduledPaymentAmt"))
            expected_msg = expected_msg.replace("$<sched_payment_amt>", schPaymentAmt);
        expected_msg = expected_msg.replace("<sched_payment_date>", schPaymentDate);
        Assert.assertTrue("ERROR - APP: Schedule payment message is not displaying as expected",
                Elements.findElement("my_account.schedule_payment_message").getText().contains(expected_msg));
    }

    @Then("^I should redirect to manage payments page$")
    public void iShouldRedirectToManagePaymentsPage() throws Throwable {
        for (String winHandle : WebDriverManager.getWebDriver().getWindowHandles()) {
            WebDriverManager.getWebDriver().switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
        String URL = WebDriverManager.getCurrentUrl();
        Assert.assertTrue(URL.contains("payments"));
    }

    @Then("^I should redirect to manage autopay page$")
    public void iShouldRedirectToManageautopayPage() throws Throwable {
        for (String winHandle : WebDriverManager.getWebDriver().getWindowHandles()) {
            WebDriverManager.getWebDriver().switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
        String URL = WebDriverManager.getCurrentUrl();
        Assert.assertTrue(URL.contains("autopay"));
    }
 public String getCurrentDateAfter_n_Days(int days, int months, int years, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        Date currentDate = new Date();
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusYears(years).plusMonths(months).plusDays(days);
        currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return format.format(currentDate).toString();
    }
    @Then("^I should see payment due date$")
    public void iShouldSeePaymentDueDate() throws Throwable {
        Assert.assertTrue("Payment due date is not displayed", Elements.elementPresent("one_time_payment.payment_due_date"));
        logInfo("Successfully verified Payment due date on OTP1 Page");
    }
	
	  @And("^I should see \"([^\"]*)\" on \"([^\"]*)\" popup$")
    public void iShouldSeeDatePickerPopupMessage(String expected_message, String page) throws Throwable {
        Wait.untilElementPresent("one_time_payment.dp_message");
        String actual_message = Elements.getText("one_time_payment.dp_message");
        expected_message = CustomerUtils.getExpectedMessage(expected_message);
        log.info("1041 :: "+actual_message);
        log.info("1042 :: "+expected_message);
        Assert.assertEquals(expected_message, actual_message);
    }

    @Then("^I should see tooltip on the right of Current Balance dollar amount$")
    public void iShouldSee() throws Throwable {
        Assert.assertTrue("Tooltip is not displayed", Elements.elementPresent("one_time_payment.tool_tip"));
        logInfo("Successfully verified tool tip");
    }

    @When("^I click on the tooltip$")
    public void iClickOnTooltip() throws Throwable {
        Wait.forPageReady();
        Clicks.click("one_time_payment.tool_tip");
    }

    @Then("^callout box should open with copy text$")
    public void callout() throws Throwable {
        Assert.assertTrue("call out box is not opened", Elements.elementPresent("one_time_payment.callout_box"));
        logInfo("successfully verified call out box");
    }

    @And("^I click on 'X' button on callout box$")
    public void iClickOnX() throws Throwable {
        Wait.forPageReady();
        Clicks.click("one_time_payment.close_button");
    }

    @Then("^I should not see callout box$")
    public void iShouldNotSeeCalloutBox() throws Throwable {
        Assert.assertFalse("Call out box is displayed ", Elements.elementPresent("one_time_payment.callout_box"));
        logInfo("call out box is not displayed");
    }

    @Then("^I should see disclosure paragraph above continue and cancel button$")
    public void iShouldSeeDisclosurePragraphAboveContinueAndCancelButton() throws Throwable {
        Assert.assertTrue("disclosure paragraph is not displayed", Elements.elementPresent("one_time_payment.disclosure_paragraph"));
        logInfo("Successfully verified disclosure paragraph");
    }

    @And("^I should see \" Terms & Conditions\" link in disclosure paragraph$")
    public void iShouldSeeTermsConditionsLink() throws Throwable {
        Assert.assertTrue("Terms & Conditions link is not displayed", Elements.elementPresent("one_time_payment.terms_conditions"));
        logInfo("successfully verified terms & conditions link");
    }

    @Then("^I should see the inline copy below the payment source$")
    public void iShouldSeeTheInlineCopyBelowThePaymentSource() throws Throwable {
        Assert.assertTrue("In line copy is not displayed", Elements.elementPresent("one_time_payment.inline_copy"));
        logInfo("successfully verified inline copy");
    }

    @And("^I should see \"More payment source options\" link$")
    public void iShouldSeeMorePaymentSourceOptions() throws Throwable {
        Assert.assertTrue("Make payment source options link is not displayed", Elements.elementPresent("one_time_payment.More_Payment_Source_Options"));
        logInfo("successfully verified More Payment Source Options link");

    }
    @And("^I click on other amount radio button$")
    public void iClickOnOtherAmountRadioButton() {
        String text = "other amount ";
        if (text.equals(Elements.findElement("one_time_payment.other-amount").getText())) {
            Clicks.click("one_time_payment.other_amount_radio_button");
        }
    }

    @When("^I enter alpha characters in the text box$")
    public void iEnterAlphaCharactersInTheTextBox(){
        Assert.assertTrue("Thank you for entering valid price value",Elements.findElement("one_time_payment.other_amount_text_box\"").getAttribute("value").matches("/^[A-z]+$/"));
        logInfo("Please enter a valid payment amount using only numbers and a decimal point");
    }

    @And("^I verify that text box is empty$")
    public void iVerifyThatTextBoxIsEmpty()
    {
        Assert.assertTrue("Text box beside other amount is not empty", Elements.findElement("one_time_payment.other_amount_text_box").getAttribute("value").isEmpty());
        logInfo("Text box beside other amount is empty");
    }

    @Then("^I should be navigated to otp1 page$")
    public void iShouldBeNavigatedToOtp1Page()
    {
        Assert.assertTrue("Not landed on otp1 page", Elements.elementPresent("one_time_payment.More_Payment_Source_Options"));
        logInfo("Landed on otp1 page");
    }

    @When("^I enter only numeric characters in the text box$")
    public void iEnterOnlyNumericCharactersInTheTextBox() {
        Assert.assertTrue("Please enter a valid payment amount using only numbers and a decimal point.", Elements.findElement("one_time_payment.other_amount_text_box").getAttribute("value").matches("(\\d)+\\.(\\d+)"));
        logInfo("Thank you for entering valid price value");
    }

    @And("^I click on the continue button$")
    public void iClickOnTheContinueButton() {
        Clicks.click("one_time_payment.continue");
    }

    @Then("^I should see error message below other amount box$")
    public void iShouldSeeErrorMessageBelowOtherAmountBox() {
        Assert.assertTrue("Error message is not displayed when we dont enter any value", Elements.elementPresent("one_time_payment.error_message"));
        logInfo("successfully verified error message");
    }

    @Then("^I should be navigated otp2 page$")
    public void iShouldBeNavigatedOtp2Page() {
        String currentURL = WebDriverManager.getCurrentUrl();
        Assert.assertTrue("Page is not navigated to otp2", currentURL.contains("authorize"));
        logInfo("Successfully navigated to otp2");
    }
    @And("^I should see all radio button choices are available$")
    public void iShouldSeeAllRadioButtonChoicesAreAvailable() {
        List<WebElement> rButton = Elements.findElements(By.name("amount"));
        for (WebElement allButtons : rButton) {
            Assert.assertTrue("Radio button is displayed", Elements.findElement(By.name("amount")).isEnabled());
        }
        logInfo("All radio button except Minimum Payment Due are enabled");
    }
    @And("^I should see all radio button choices are available except for Minimum Payment Due$")
    public void iShouldSeeAllRadioButtonChoicesAreAvailableExceptForMinimumPaymentDue() {
        List<WebElement> rButton = Elements.findElements(By.name("amount"));
        for (WebElement allButtons : rButton) {
            if (Elements.findElement(By.id("otp-form-min-payment-due")).isEnabled()) {
                logInfo("Minimum Payment Due radio button should not be enabled ");
                break;
            }
            Assert.assertTrue("Radio button is displayed", Elements.findElement(By.name("amount")).isEnabled());
        }
        logInfo("All radio button except Minimum Payment Due are enabled");
    }

    @And("^I should see Raido button for Minimum Payment Due is grayed out$")
    public void iShouldSeeRadioButtonForMinimumPaymentDueIsGrayedOut() {
        Assert.assertTrue("Minimum Payment Due Radio Button is not grayed out", Elements.findElement(By.id("otp-form-min-payment-due")).isEnabled());
        logInfo("Minimum Payment Due Radio Button is grayed out");
    }

    @And("^I should see Statement Balance is the default radio button selected$")
    public void iShouldSeeStatmentBalanceIsTheDefaultRadiButtonSelected() {
        Boolean is_Selected = Elements.findElement(By.id("otp-form-statement-balance")).isSelected();
        if (!is_Selected) {
            Clicks.click("one_time_payment.statement_balance");
        }
        logInfo("Statement balance is selected");
    }

    @And("^I should see Statement Balance is grayed out$")
    public void iShouldSeeStatementBalanceIsGrayedOut() {
        Assert.assertTrue("Statement Balance Radio Button is not grayed out", Elements.findElement(By.id("otp-form-statement-balance")).isEnabled());
        logInfo("Statement Balance Radio Button is grayed out");
    }

    @And("^I should see Minimum Payment Due is the default radio button selected$")
    public void iShouldSeeMinimumPaymentDueIsTheDefaultRadioButtonSelected() {
        Boolean is_Selected = Elements.findElement(By.id("otp-form-min-payment-due")).isSelected();
        if (!is_Selected) {
            Clicks.click("one_time_payment.minimum_payment_due");
        }
        logInfo("Minimum Payment Due radio button is selected");
    }

    @And("^I should see Current Balance is the default radio button selected$")
    public void iShouldSeeCurrentBalanceIsTheDefaultRadioButtonSelected() {
        Boolean is_Selected = Elements.findElement(By.id("otp-form-current-balance")).isSelected();
        if (!is_Selected) {
            Clicks.click("one_time_payment.current_balance");
            logInfo("Current Balance is the default radio button selected");
        }
    }

    @And("^I should see below radio buttons are available:$")
    public void iShouldSeeBelowRadioButtonsAreAvailable(List<String> rbuttons) {
        for (String value : rbuttons) {
            switch (value) {
                case "Current Balance":
                    Assert.assertTrue("Current Balance radio button is not available", Elements.findElement(By.id("otp-form-current-balance")).isEnabled());
                case " Other Amount ":
                    Assert.assertTrue("Other Amount radio button is not available", Elements.findElement(By.id("otp-form-other-amount")).isEnabled());

            }
        }

    }


}

