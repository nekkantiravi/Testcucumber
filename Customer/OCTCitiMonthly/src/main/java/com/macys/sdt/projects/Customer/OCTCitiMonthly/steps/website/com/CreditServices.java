package com.macys.sdt.projects.Customer.OCTCitiMonthly.steps.website.com;

import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.actions.website.citi.pages.credit_services.ActivateCard;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.actions.website.citi.pages.credit_services.AddCard;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.actions.website.citi.pages.credit_services.ApplyNow;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.actions.website.mcom.pages.my_account.CitiCreateProfile;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.model.CreditManageCard;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.utils.CreditServicesUtil;
import com.thoughtworks.selenium.webdriven.commands.Click;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.macys.sdt.framework.interactions.TextBoxes.typeTextbox;
import static com.macys.sdt.projects.Customer.OCTCitiMonthly.utils.CreditServicesUtil.*;


public class CreditServices extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CreditServices.class);

    /**
     * Clicks the given button on Credit Gateway page
     *
     * @param button element name (should match element ID in page JSON file
     * @param page   name of expected page
     * @throws Throwable if any exception occurs
     */
    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" page$")
    public void I_click_button_on_page(String button, String page) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("Do not Apply Card with Test Data on Production, Exiting");
        }

        switch (button) {
            case "apply now":
                Utils.threadSleep(1500, "Waiting for the page to Load");
                if (page.equals("gateway")) {
                    Wait.forPageReady();
                    if (!onPage("credit_service_gateway_signedin") || !onPage("credit_service_gateway_guest")) {
                        Wait.forPageReady();
                        Clicks.click("footer.goto_credit_services");
                    }
                    Wait.forPageReady();
                    Wait.untilElementPresent("credit_service_gateway_signedin.apply_now_button");
                    if (signedIn()) {
                        if (Elements.elementPresent("credit_service_gateway_signedin.apply_now_button")) {
                            Clicks.click("credit_service_gateway_signedin.apply_now_button");
                        } else {
                            Assert.fail("Apply Now button not present on gateway page, Exiting");
                        }
                    } else {
                        if (Elements.elementPresent("credit_service_gateway_guest.apply_now_button")) {
                            Clicks.click("credit_service_gateway_guest.apply_now_button");
                        } else {
                            Assert.fail("Apply Now button not present on gateway page, Exiting");
                        }
                    }
                } else if (page.equals("my account")) {
                    if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
                        Clicks.click("my_account.add_card_overlay_no_thanks_button");
                        Wait.untilElementNotPresent("my_account.one_time_add_card_overlay");
                    }
                    if (Elements.elementPresent("my_account.csr_apply_today_link")) {
                        Clicks.click("my_account.csr_apply_today_link");
                    } else {
                        Assert.fail("Apply Now button not present on my account page, Exiting");
                    }
                } else {
                    Clicks.click("my_account.apply_save");
                }
                break;
            case "activate card":
                if (!onPage("credit_service_gateway_guest") || !onPage("credit_service_gateway_signedin")) {
                    Clicks.click("footer.goto_credit_services");
                }
                Wait.forPageReady();
                if (Elements.elementPresent("credit_service_gateway_guest.activate_card")) {
                    Clicks.click("credit_service_gateway_guest.activate_card");
                } else {
                    Assert.fail("Activate Card button not present on gateway page, Exiting");
                }
                break;
            case "add card":
                Wait.forPageReady();
                if (page.equals("gateway")) {
                    if (onPage("my_account") && Elements.elementPresent("my_account.one_time_add_card_overlay")) {
                        Clicks.click("my_account.add_card_overlay_no_thanks_button");
                        Wait.untilElementNotPresent("my_account.one_time_add_card_overlay");
                    }
                    if (!onPage("credit_service_gateway_signedin")) {
                        Clicks.click("footer.goto_credit_services");
                    }
                    Utils.threadSleep(2000, "Waiting for page elemnts to load");
                    if (onPage("credit_service_gateway_signedin") && Elements.elementPresent("credit_service_gateway_signedin.add_card_button")) {
                        Clicks.click("credit_service_gateway_signedin.add_card_button");
                    } else if (onPage("credit_service_gateway_signedin") && Elements.elementPresent("credit_service_gateway_signedin.add_an_additional_card")) {
                        Clicks.click("credit_service_gateway_signedin.add_an_additional_card");
                    } else {
                        Assert.fail("Not on Credit gateway page or Add Card link not present, exiting");
                    }
                } else if (page.equals("my account")) {
                    Wait.forPageReady();
                    if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
                        Clicks.click("my_account.add_card_overlay_no_thanks_button");
                        Wait.untilElementNotPresent("my_account.one_time_add_card_overlay");
                    }
                    if (macys()) {
                        Wait.secondsUntilElementPresent("my_account.add_macys_card", 3);
                        if (Elements.elementPresent("my_account.add_macys_card")) {
                            Clicks.click("my_account.add_macys_card");
                        } else {
                            Assert.fail("Add card element not present, exiting");
                        }
                    } else if (bloomingdales()) {
                        Wait.secondsUntilElementPresent("my_account.csr_add_card_to_my_account_button", 3);
                        if (Elements.elementPresent("my_account.csr_add_card_to_my_account_button")) {
                            Clicks.click("my_account.csr_add_card_to_my_account_button");
                        } else {
                            Assert.fail("Add card element not present, exiting");
                        }
                    } else {
                        Assert.fail("Not on Macys/Bloomings page, exiting");
                    }
                } else {
                    Assert.fail("Not on Gateway/My account page, Exiting");
                }
                break;
            default:
                Clicks.click("credit_service_gateway_guest." + button.replace(" ", "_"));
        }
        Wait.forPageReady();
    }

    /**
     * Verifies the user is navigated to the correct page when clicking on the given credit services links
     *
     * @param credit_service_links links to click on (link text)
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to respective credit services page:$")
    public void I_should_be_navigated_to_respective_credit_services_page(List<HashMap<String, String>> credit_service_links) throws Throwable {
        ArrayList<String> failed_elements = new ArrayList<>();
        for (Map set : credit_service_links) {
            Clicks.click("home." + set.get("credit_link"));
            Utils.threadSleep(5000, "Waiting for page (without verify_page element) to load..");
            try {
                if (!onPage(set.get("landing_page").toString())) {
                    failed_elements.add(set.get("landing_page").toString());
                }
            } catch (NoSuchElementException e) {
                logger.info(set.get("landing_page") + " page not displayed!!");
            }
        }
        if (failed_elements.size() > 0) {
            Assert.fail("Following pages are not displayed:" + failed_elements.toString() + "!!");
        }
    }

    /**
     * Verifies the user is navigated to the correct page when clicking on the given credit services links
     *
     * @param page name of expected page
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to citi \"([^\"]*)\" page$")
    public void iShouldBeNavigatedToCitiPage(String page) throws Throwable {
        boolean expectedPage = false;
        Utils.threadSleep(3000, "Waiting for elements to load");
        Wait.secondsUntilElementNotPresent("credit_apply_now.pleaseWait", 30);

        switch (page) {
            case "apply now":
                Wait.secondsUntilElementPresent("credit_apply_now.verify_page", 8);
                expectedPage = onPage("credit_apply_now");
                break;
            case "security question":
                Wait.forPageReady();
                //Checking if landed security question page is from activation flow or add card flow
                if (onPage("credit_activate_security_question") || onPage("credit_request_code")) {
                    if (!onPage("credit_activate_security_question") && onPage("credit_request_code")) {
                        Wait.secondsUntilElementPresent("credit_request_code.answerSecureQues", 5);
                        ApplyNow.skipRequestCodePage();
                    }
                    Wait.secondsUntilElementPresent("credit_activate_security_question.submit", 5);
                    expectedPage = onPage("credit_activate_security_question");
                } else {
                    Wait.secondsUntilElementPresent("credit_add_card_security_question.verify_page", 8);
                    expectedPage = onPage("credit_add_card_security_question");
                }
                break;
            case "activate card":
                Wait.secondsUntilElementPresent("credit_activate_card.verify_page", 8);
                expectedPage = onPage("credit_activate_card");
                break;
            case "add card":
                Wait.secondsUntilElementPresent("credit_add_card.verify_page", 8);
                expectedPage = onPage("credit_add_card");
                break;
            case "card setting":
                Wait.secondsUntilElementPresent("credit_add_card_setting.verify_page", 8);
                expectedPage = onPage("credit_add_card_setting");
                break;
            case "card choice":
                Utils.threadSleep(5000, "Waiting for Page elements to load");
                if (Elements.elementPresent("credit_apply_card_choice.verify_page")) {
                    expectedPage = onPage("credit_apply_card_choice");
                } else if (Elements.elementPresent("credit_apply_add_additional_card.verify_page")) {
                    expectedPage = onPage("credit_apply_add_additional_card");
                } else {
                    Assert.fail("Not on card choice page, Exiting");
                }
                break;
            case "add authorized user":
                Utils.threadSleep(5000, "Waiting for Page elements to load");
                Wait.secondsUntilElementNotPresent("credit_add_auth_user.loading", 15);
                expectedPage = onPage("credit_add_auth_user");
                break;
            case "auth user added":
                Wait.secondsUntilElementNotPresent("credit_add_auth_user.loading", 15);
                expectedPage = onPage("credit_add_auth_user_added");
                break;
            default:
                Assert.fail("Not on Valid Citi Page, Exiting");
                break;
        }
        Assert.assertTrue("Did not navigate to " + page, expectedPage);
    }

    /**
     * Fill Apply Now details in CS Apply now page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I enter apply card details on page$")
    public void iEnterApplyCardDetailsOnPage() throws Throwable {
        if (!onPage("credit_apply_now")) {
            Assert.fail("Not on Apply Now page, cannot proceed with Test");
        }

        Wait.forPageReady();
        ApplyNow.fillApplyNowInfo();
    }

    /**
     * Fill Apply Now details in CS Apply now page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I scroll down to footer panel$")
    public void iScrollDownToFooterPanel() throws Throwable {
        Clicks.hoverForSelection("footer.footer_section");
    }

    /**
     * Select options for security questions during CS Apply now process
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select the options and submit security questions$")
    public void iSelectTheOptionsAndSubmitSecurityQuestions() throws Throwable {
        if (!onPage("credit_activate_security_question")) {
            Assert.fail("Not on security Question page, cannot proceed with Test");
        }

        Wait.secondsUntilElementPresent("credit_activate_security_question.submit", 5);
        ApplyNow.answerSecureQuestions();

    }

    /**
     * Clicks the given button on Credit Gateway page
     *
     * @param button element name (should match element ID in page JSON file)
     * @param page   name of expected page
     * @throws Throwable if any exception occurs
     */
    @And("^I click \"([^\"]*)\" button on citi \"([^\"]*)\" page$")
    public void iClickButtonOnCitiPage(String button, String page) throws Throwable {

        switch (button) {
            case "submit application":
                if (page.equals("apply now") && Elements.elementPresent("credit_apply_now.submit")) {
                    Clicks.click("credit_apply_now.submit");
                } else {
                    Assert.fail("Submit Application element not present");
                }
                break;
            case "return":
                if (page.equals("apply now") && Elements.elementPresent("credit_decision.decisionApproveReturn")) {
                    Clicks.click("credit_decision.decisionApproveReturn");

                } else {
                    Assert.fail("Return COM element not present");
                }
                break;
            default:
                Clicks.click("credit_service_gateway_guest." + button.replace(" ", "_"));
        }
    }

    /**
     * Checks whether page navigation to DOTCOM is successful
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to login page with layover popup$")
    public void iShouldBeNavigatedToLoginPageWithLayoverPopup() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }

        Utils.threadSleep(2000, "Waiting for page to load");
        Wait.forPageReady();
        if (!onPage("sign_in")) {
            Assert.fail("Not on Sign In page, Exiting");
        }
        if (Elements.elementPresent("signin_or_create_account.continueOverlay")) {
            Clicks.click("signin_or_create_account.continueOverlay");
        } else {
            Assert.fail("Overlay not present, Exiting");
        }

    }

    /**
     * Clicks the Continue button on Citi decision page speed bump
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click continue on citi page speed bump$")
    public void iClickContinueOnCitiDecisionPageSpeedBump() throws Throwable {

        if (Elements.elementPresent("credit_decision_approve.continueOverlay")) {
            Clicks.click("credit_decision_approve.continueOverlay");
        } else {
            Assert.fail("Continue button not present, Exiting");
        }
    }

    @And("^I click continue on layover popup$")
    public void iClickContinueOnLayoverPopup() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }
        logger.info("Landed on Sign in page");
    }

    /**
     * Clicks the given button on Credit Gateway page
     *
     * @param page   element name (should match element ID in page JSON file)
     * @param button button name of expected page
     * @throws Throwable if any exception occurs
     */
    @And("^I verify citi \"([^\"]*)\" page and click \"([^\"]*)\" button$")
    public void iVerifyCitiPageAndClickButtonOnPage(String page, String button) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }

        switch (page) {
            case "decision":
                Wait.secondsUntilElementNotPresent("credit_apply_now.pleaseWait", 25);
                Utils.threadSleep(3000, "Waiting for elements to load");
                Wait.forPageReady();
                ApplyNow.iVerifyCitiPageAndClickButton(page, button);
                break;
            case "activate now":
                shouldBeOnPage("credit_activate_now");
                if (Elements.elementPresent("credit_activate_now.activateNow")) {
                    Clicks.click("credit_activate_now.activateNow");
                }
                break;
            case "congratulations":
                shouldBeOnPage("credit_activate_congratulations");
                if (Elements.elementPresent("credit_activate_congratulations.return")) {
                    Clicks.click("credit_activate_congratulations.return");
                    Wait.secondsUntilElementPresent("credit_activate_congratulations.continueOverlay", 3);
                    Clicks.click("credit_activate_congratulations.continueOverlay");
                }
                Utils.threadSleep(4000, "Waiting for page to load");
                break;
            case "card added":
                shouldBeOnPage("credit_add_card_added");
                if (Elements.elementPresent("credit_add_card_added.return")) {
                    Clicks.click("credit_add_card_added.return");
                    Clicks.click("credit_add_card_added.continueOverlay");
                }
                Utils.threadSleep(4000, "Waiting for page to load");
                break;
        }
    }

    /**
     * Create profile for citi testing
     *
     * @param testCase test case number
     * @throws Throwable if any exception occurs
     */
/*    @And("^I create a profile for citi \"([^\"]*)\" test$")
    public void iCreateAProfileForCitiTest(String testCase) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }

        if (onPage("sign_in")) {
            Clicks.click("sign_in.create_profile");
        }

        Wait.forPageReady();
        CitiCreateProfile.fillCreateProfileInfo(testCase);
    }*/

    /**
     * Create profile for citi testing
     *
     * @param testCase test case number
     * @param cardType is credit card type
     * @param testType is test type. For ex, generic test or auth user test
     */
    @And("^I create a profile for citi \"([^\"]*)\" and \"([^\"]*)\" (test|auth user test)$")
    public void iCreateAProfileForCitiAndTest(String testCase, String cardType, String testType) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }
        if (onPage("sign_in")) {
            Clicks.click("sign_in.create_profile");
        }
        Wait.forPageReady();
        if (testType.equals("test")) {
            logger.info("This is a generic user profile creation");
            CitiCreateProfile.fillCreateProfileInfo(testCase, cardType, false);
        } else if (testType.equals("auth user test")) {
            logger.info("This is a auth user profile creation");
            CitiCreateProfile.fillCreateProfileInfo(testCase, cardType, true);
        } else {
            Assert.fail("Not on valid create profile page, Exiting");
        }
    }

    /**
     * Verify whether Add Card to Wallet is successful
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I verify \"([^\"]*)\" card in My wallet for \"([^\"]*)\" user$")
    public void iVerifyCardInMyWallet(String cardCase, String cardType) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }
        Wait.secondsUntilElementNotPresent("credit_apply_now.pleaseWait", 30);
        Wait.forPageReady();
        if (!onPage("oc_my_wallet")) {
            Clicks.hoverForSelection("header.goto_my_account_link");
            Clicks.click("header.my_wallet");
        }
        Utils.threadSleep(3000, "Waiting for page elements to load");
        Wait.forPageReady();
        String walletNum;
        if (macys()) {
            walletNum = Elements.getText("oc_my_wallet.wallCard");
        } else {
            walletNum = Elements.getText("my_bwallet.bWallCard");
        }
        walletNum = walletNum.replaceAll("\\D+", "");
        String cardNum;
        if (!walletNum.trim().isEmpty()) {
            switch (cardCase) {
                case "added":
                    CreditManageCard creditAddCard = getAddCardInfo(cardType, false);
                    cardNum = StringUtils.deleteWhitespace(creditAddCard.getCardNumber());
                    if (cardNum != null) {
                        cardNum = cardNum.substring(cardNum.length() - 4, cardNum.length());
                        logger.info("Card Number from Wallet:: " + walletNum + "\nAdded Card Number - " + cardNum);
                        if (walletNum.matches(cardNum)) {
                            logger.info("Cards matching, Add Card - Add card to wallet successful");
                        } else {
                            Assert.fail("Cards Not matching for Add Card, Exiting");
                        }
                    } else {
                        Assert.fail("Card number not set, Exiting");
                    }
                    break;
                case "activated":
                    CreditManageCard creditActivateCard = getActivationInfo(cardType);
                    cardNum = StringUtils.deleteWhitespace(creditActivateCard.getCardNumber());
                    if (cardNum != null) {
                        cardNum = cardNum.substring(cardNum.length() - 4, cardNum.length());
                        logger.info("Card Number from Wallet:: " + walletNum + "\nActivated Card Number - " + cardNum);
                        if (walletNum.matches(cardNum)) {
                            logger.info("Cards matching, Activation - Add card to wallet successful");
                        } else {
                            Assert.fail("Cards Not matching for Activation, Exiting");
                        }
                    } else {
                        Assert.fail("Card number not set, Exiting");
                    }
                    break;
                case "apply":
                    //ApplyNow.accNum is card number from citi decision approval page and cardNum are from JSON file
                    //logger.info("Card Number from Wallet:: " + walletNum + "\n Card Number from Citi Add/Activate/Apply Page:: \nApplyNow - " + ApplyNow.accNum + "\nCard Number - " + cardNum);
                    if (ApplyNow.accNum != null && walletNum.matches(ApplyNow.accNum)) {
                        logger.info("Cards matching, Apply Now - Add card to wallet successful");
                    } else {
                        Assert.fail("Cards Not matching for Acquisition, Exiting");
                    }
                default:
                    Assert.fail("Not a valid test, Exiting");
                    break;
            }
        } else if (walletNum.isEmpty() && AddCard.noAtw) {
            logger.info("Success! Card not added to wallet");
        } else {
            Assert.fail("Add card to/not to wallet check was not successful");
        }
    }

    /**
     * Navigate to Wallet page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I navigate to My Wallet page$")
    public void iNavigateToMyWalletPage() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }

        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            Clicks.click("my_account.add_card_overlay_no_thanks_button");
            Wait.untilElementNotPresent("my_account.one_time_add_card_overlay");
        }
        Clicks.click("my_account.wallet");
        Wait.forPageReady();
        if (onPage("oc_my_wallet")) {
            logger.info("Navigated to My wallet page");
        } else {
            Assert.fail("Not on My Wallet page, Exiting");
        }
    }

    /**
     * Navigate to Credit Gateway page
     *
     * @throws Throwable if any exception occurs
     */
    @Then("^I should be navigated to credit gateway page$")
    public void iShouldBeNavigatedToCreditGatewayPage() throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }
        Wait.forPageReady();
        if (!onPage("credit_service_gateway_signedin")) {
            Assert.fail("Not navigated to credit gateway page, Exiting");
        }
    }

    /**
     * Navigate to citi apply now page from one-time overlay
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click \"([^\"]*)\" on one time overlay$")
    public void iClickApplyNowOnOneTimeOverlay(String type) throws Throwable {
        if (prodEnv()) {
            throw new ProductionException("We are on Production instance, Exiting");
        }
        Wait.forPageReady();
        switch (type) {
            case "apply now":
                if (Elements.elementPresent("my_account.add_card_overlay_apply_today_link")) {
                    Clicks.click("my_account.add_card_overlay_apply_today_link");
                } else {
                    Assert.fail("Add card overlay not present, Exiting");
                }
            case "add card":
                if (Elements.elementPresent("my_account.add_card_overlay_add_card_button")) {
                    Clicks.click("my_account.add_card_overlay_add_card_button");
                } else {
                    Assert.fail("Add card overlay not present, Exiting");
                }
        }
    }

    /**
     * Fill Activate Card details in CS Activate Card page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I enter activate card details for \"([^\"]*)\" user and click verify$")
    public void iEnterActivateCardDetailsForFlowAndClickVerify(String user) throws Throwable {
        if (!onPage("credit_activate_card")) {
            Assert.fail("Not on Activate Card page, cannot proceed with Test");
        }

        Wait.forPageReady();
        ActivateCard.fillActivateCardInfo(user);
    }

    /**
     * Fill Add Card details in CS Add Card page
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I enter( authorized | )\"([^\"]*)\" card details with \"([^\"]*)\" option and click add card$")
    public void iEnterCardDetailsWithOptionAndClickAddCard(String authUserTest, String cardType, String addToWallet) throws Throwable {
        Wait.forPageReady();
        Utils.threadSleep(3000, "Waiting for add card page to load");
        Wait.secondsUntilElementNotPresent("credit_add_card.loading", 30);
        if (!onPage("credit_add_card") && onPage("credit_update_your_profile")) {
            logger.info("On update profile page, Add email");
            CreditServicesUtil.creditUpdateProfile(cardType);
        } else if (onPage("credit_add_card")) {
            Wait.secondsUntilElementPresent("credit_add_card.verify_page", 10);
            if (authUserTest.trim().equals("authorized")) {
                AddCard.fillAddCardInfo(cardType, addToWallet, true);
            } else {
                AddCard.fillAddCardInfo(cardType, addToWallet, false);
            }
        } else {
            Assert.fail("Not on Add Card page, cannot proceed with Test");
        }
    }

    /**
     * Add and submit security questions for added Card in CS Add Card page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I enter security question and submit$")
    public void iEnterSecurityQuestionAndSubmit() throws Throwable {
        if (!onPage("credit_add_card_security_question")) {
            Assert.fail("Not on Add Card security Question page, cannot proceed with Test");
        }

        Wait.secondsUntilElementPresent("credit_add_card_security_question.submit", 5);
        AddCard.addSecureQuestions();
    }

    /**
     * Select settings for added Card
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select the options on citi settings page and submit$")
    public void iSelectTheOptionsOnCitiSettingsPageAndSubmit() throws Throwable {
        if (!onPage("credit_add_card_setting")) {
            Assert.fail("Not on Activate Card page, cannot proceed with Test");
        }

        Wait.forPageReady();
        Clicks.javascriptClick("credit_add_card_setting.paperless");
        Clicks.click("credit_add_card_setting.submit");
    }

    /**
     * Click add Card on new my account page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click add card on my account page$")
    public void iClickAddCardOnMyAccountPage() throws Throwable {
        Wait.forPageReady();
        if (Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            Clicks.click("my_account.add_card_overlay_no_thanks_button");
            Wait.untilElementNotPresent("my_account.one_time_add_card_overlay");
        }
        if (macys()) {
            Wait.secondsUntilElementPresent("my_account.add_macys_card", 3);
            if (Elements.elementPresent("my_account.add_macys_card")) {
                Clicks.click("my_account.add_macys_card");
            } else {
                Assert.fail("Add card element not present, exiting");
            }
        } else if (bloomingdales()) {
            Wait.secondsUntilElementPresent("my_account.csr_add_card_to_my_account_button", 3);
            if (Elements.elementPresent("my_account.csr_add_card_to_my_account_button")) {
                Clicks.click("my_account.csr_add_card_to_my_account_button");
            } else {
                Assert.fail("Add card element not present, exiting");
            }
        } else {
            Assert.fail("Not on Macys/Bloomings page, exiting");
        }

    }

    /**
     * Click Card type on citi card choice page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I select a card type and submit$")
    public void iSelectACardTypeAndSubmit() throws Throwable {
        Wait.forPageReady();
        if (Elements.elementPresent("credit_apply_card_choice.verify_page")) {
            Clicks.click("credit_apply_card_choice.verify_page");
            //For now placed for prop card. Need to change in future for both type of cards on page
            Clicks.click("credit_apply_card_choice.propYesOverlay");
        } else if (Elements.elementPresent("credit_apply_add_additional_card.verify_page")) {
            Clicks.click("credit_apply_add_additional_card.verify_page");
        } else {
            Assert.fail("Card choice element not present, exiting");
        }
    }

    /**
     * Click Add Card on credit gateway page page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click add your card on gateway page$")
    public void iClickAddYourCardOnGatewayPage() throws Throwable {
        Wait.forPageReady();
        if (onPage("my_account") && Elements.elementPresent("my_account.one_time_add_card_overlay")) {
            Clicks.click("my_account.add_card_overlay_no_thanks_button");
            Wait.untilElementNotPresent("my_account.one_time_add_card_overlay");
        }
        if (!onPage("credit_service_gateway_signedin")) {
            Clicks.click("footer.goto_credit_services");
        }
        Utils.threadSleep(2000, "Waiting for page elemnts to load");
        if (onPage("credit_service_gateway_signedin") && Elements.elementPresent("credit_service_gateway_signedin.add_card_button")) {
            Clicks.click("credit_service_gateway_signedin.add_card_button");
        } else if (onPage("credit_service_gateway_signedin") && Elements.elementPresent("credit_service_gateway_signedin.add_an_additional_card")) {
            Clicks.click("credit_service_gateway_signedin.add_an_additional_card");
        } else {
            Assert.fail("Not on Credit gateway page or Add Card link not present, exiting");
        }
    }

    /**
     * Login with existing credentials
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I login using \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iLoginUsingAnd(String email, String pass) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("sign_in.verify_page", 2);
        typeTextbox("sign_in.email", email);
        typeTextbox("sign_in.password", pass);
        Clicks.click("sign_in.verify_page");
    }

    /**
     * Login with existing credentials
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I login into a profile with \"([^\"]*)\" added$")
    public void iLoginIntoAProfileWithAdded(String cardType) throws Throwable {
        Wait.forPageReady();
        if (onPage("sign_in")) {
            logger.info("Loggin into profile with " + cardType + " card already added");

            CreditManageCard email = getAddCardInfo(cardType, false);
            typeTextbox("sign_in.email", email.getEmail());
            typeTextbox("sign_in.password", "!Macys12345");
            Clicks.click("sign_in.verify_page");
            Utils.threadSleep(3000, "Waiting for popup to load");
            if (Elements.elementPresent("sign_in.complete_your_profile")) {
                Clicks.click("sign_in.complete_your_profile_close");
            } else if (Elements.elementPresent("sign_in.security_question")) {
                logger.info("Security Q&A setup overlay is displayed");
                UserProfile customer = TestUsers.getCustomer(null);
                DropDowns.selectByText("sign_in.security_question", customer.getUser().getUserPasswordHint().getQuestion());
                TextBoxes.typeTextbox("sign_in.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
                Clicks.click("sign_in.save_and_continue");
            } else {
                logger.info("Security Q&A setup overlay/Complete your profile is not displayed");
            }
        } else {
            Assert.fail("Not on Sign in page, cannot proceed with Test");
        }
    }

    /**
     * Click a specified link on page
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I click \"([^\"]*)\" link on \"([^\"]*)\" page$")
    public void iClickLinkOnPage(String link, String page) throws Throwable {
        Wait.forPageReady();
        if (!onPage("credit_service_gateway_signedin")) {
            Clicks.clickIfPresent("home.side_ad");
            Clicks.click("footer.goto_credit_services");
        }
        switch (link) {
            case "add authorized user":
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("credit_service_gateway_signedin.add_an_authorized_user", 15);
                if (Elements.elementPresent("credit_service_gateway_signedin.add_an_authorized_user")) {
                    Clicks.click("credit_service_gateway_signedin.add_an_authorized_user");
                } else {
                    Assert.fail("Add authorized user link not present, Exiting");
                }
                break;
            case "add additional card":
                if (onPage("credit_service_gateway_signedin") && Elements.elementPresent("credit_service_gateway_signedin.add_an_additional_card")) {
                    Clicks.click("credit_service_gateway_signedin.add_an_additional_card");
                } else {
                    Assert.fail("Add additional card link not present, Exiting");
                }
                break;
            default:
                Assert.fail("not on valid page, Exiting");
                break;
        }
    }

    /**
     * Enter auth user details on add auth user page
     */
    @And("^I enter authorized user details for \"([^\"]*)\" account and submit$")
    public void iEnterAuthorizedUserDetailsForAccountAndSubmit(String cardType) throws Throwable {
        if (!onPage("credit_add_auth_user")) {
            Assert.fail("Not on Add auth user page, Exiting");
        }
        AddCard.fillAuthUserInfo(cardType);
    }

    /**
     * Sign out from citi after adding auth user
     */
    @And("^I sign out from citi add authorized user page$")
    public void iSignOutFromCitiAddAuthorizedUserPage() throws Throwable {
        if (!onPage("credit_add_auth_user_added")) {
            Assert.fail("Not on auth user added page, Exiting");
        }
        Wait.secondsUntilElementPresent("credit_add_auth_user_added.sign_out", 5);
        if (Elements.elementPresent("credit_add_auth_user_added.sign_out")) {
            Clicks.click("credit_add_auth_user_added.sign_out");
            Wait.untilElementPresent("credit_add_auth_user_added.confirm_sign_off");
            Clicks.click("credit_add_auth_user_added.confirm_sign_off");
        } else {
            Assert.fail("Sign out link not present on auth user added page, Exiting");
        }
    }

    /**
     * Verify auth user after adding an authorized card to profile
     */
    @And("^I verify authrorized user indicator on gateway page$")
    public void iVerifyAuthrorizedUserIndicatorOnGatewayPage() throws Throwable {
        if (!onPage("credit_service_gateway_signedin")) {
            Clicks.click("header.credit_card");
        }
        Wait.forPageReady();
        Wait.untilElementPresent("credit_service_gateway_signedin.activate_card");
        Assert.assertTrue("Authorized User indicator not present", Elements.elementPresent("credit_service_gateway_signedin.authorized_indicator"));
    }
}
