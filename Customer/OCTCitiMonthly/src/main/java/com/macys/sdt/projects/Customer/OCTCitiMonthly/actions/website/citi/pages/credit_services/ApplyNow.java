package com.macys.sdt.projects.Customer.OCTCitiMonthly.actions.website.citi.pages.credit_services;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.model.CreditApplyNow;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.projects.Customer.OCTCitiMonthly.utils.CreditServicesUtil.getApplyNowInfo;

public class ApplyNow extends StepUtils {
    public static String accNum;
    private static final Logger logger = LoggerFactory.getLogger(ApplyNow.class);

    /**
     * To get Apply Now data and fill on CS Apply Now page
     */
    public static void fillApplyNowInfo() {
        if (!onPage("credit_apply_now")) {
            Assert.fail("Not on Apply Now page, Can't proceed with Test");
        }

        CreditApplyNow creditApplyNow = getApplyNowInfo();

        pausePageHangWatchDog();
        if (!safari() && Elements.elementPresent("credit_apply_now.firstName")) {
            stopPageLoad();

            Clicks.click("credit_apply_now.firstName");
            if (Elements.elementPresent("credit_apply_now.firstName")) {
                TextBoxes.typeTextbox("credit_apply_now.firstName", creditApplyNow.getFirstName());
            } else {
                Assert.fail("First Name Field not present, exiting");
            }

            Clicks.click("credit_apply_now.lastName");
            if (Elements.elementPresent("credit_apply_now.lastName")) {
                TextBoxes.typeTextbox("credit_apply_now.lastName", creditApplyNow.getLastName());
            } else {
                Assert.fail("Last Name Field not present, exiting");
            }

            Clicks.click("credit_apply_now.emailFld");
            if (Elements.elementPresent("credit_apply_now.email")) {
                TextBoxes.typeTextbox("credit_apply_now.email", creditApplyNow.getEmail());
            } else {
                Assert.fail("Email Field not present, exiting");
            }

            Clicks.click("credit_apply_now.addressFld");
            if (Elements.elementPresent("credit_apply_now.address")) {
                TextBoxes.typeTextbox("credit_apply_now.address", creditApplyNow.getAddress());
                TextBoxes.typeTextbox("credit_apply_now.city", creditApplyNow.getCity());
                DropDowns.selectByText("credit_apply_now.state", creditApplyNow.getState());
                TextBoxes.typeTextbox("credit_apply_now.zip", creditApplyNow.getZipCode());
            } else {
                Assert.fail("Address Field not present, exiting");
            }

            Clicks.click("credit_apply_now.phoneFld");
            if (Elements.elementPresent("credit_apply_now.phone")) {
                TextBoxes.typeTextbox("credit_apply_now.phone", creditApplyNow.getPhone());
                DropDowns.selectByText("credit_apply_now.phoneType", creditApplyNow.getPhoneType());
            } else {
                Assert.fail("Phone Field not present, exiting");
            }

            Utils.threadSleep(500, null);
            Clicks.click("credit_apply_now.financeFld");
            if (Elements.elementPresent("credit_apply_now.mortgage")) {
                TextBoxes.typeTextbox("credit_apply_now.mortgage", creditApplyNow.getMortgage());
                DropDowns.selectByText("credit_apply_now.residenceStatus", creditApplyNow.getResStatus());
                TextBoxes.typeTextbox("credit_apply_now.income", creditApplyNow.getIncome());
            } else {
                Assert.fail("Mortgage field not present, exiting");
            }

            Clicks.click("credit_apply_now.ssnFld");
            if (Elements.elementPresent("credit_apply_now.ssn")) {
                TextBoxes.typeTextbox("credit_apply_now.ssn", creditApplyNow.getSsn());
            } else {
                Assert.fail("SSN field not present, exiting");
            }

            Clicks.click("credit_apply_now.dobFld");
            if (Elements.elementPresent("credit_apply_now.dob")) {
                TextBoxes.typeTextbox("credit_apply_now.dob", creditApplyNow.getDob());
            } else {
                Assert.fail("DOB field not present, exiting");
            }

            Clicks.javascriptClick("credit_apply_now.tnc");

            resumePageHangWatchDog();
        } else {
            Assert.fail("Elements not present, exiting");
        }

    }

    /**
     * To skip request code page in CS Apply Now flow
     */
    public static void skipRequestCodePage() {
        if (!onPage("credit_request_code")) {
            Assert.fail("Not on Request Code page, Can't proceed with Test");
        }

        if (Elements.elementPresent("credit_request_code.answerSecureQues")) {
            Clicks.click("credit_request_code.answerSecureQues");
        } else {
            Assert.fail("Answer Security Questions links not present, exiting");
        }

    }

    /**
     * To select options for security questions page and submit in CS Apply Now flow
     */
    public static void answerSecureQuestions() {

        if (onPage("credit_activate_security_question") && Elements.elementPresent("credit_activate_security_question.submit")) {
            Clicks.javascriptClick("credit_activate_security_question.que1");
            Clicks.javascriptClick("credit_activate_security_question.que2");
            Clicks.javascriptClick("credit_activate_security_question.que3");
            Clicks.click("credit_activate_security_question.submit");
        } else {
            Assert.fail("Security Questions not present, exiting");
        }

    }

    /**
     * To return to COM from citi decision Approve/Pending page
     *
     * @param page   name of expected page
     * @param button if any exception occurs
     */
    public static void iVerifyCitiPageAndClickButton(String page, String button) {

        Wait.forPageReady();
        switch (button) {
            case "return":
                if (page.equals("decision") && onPage("credit_decision_approve")) {
                    Wait.secondsUntilElementPresent("credit_decision_approve.addCardToWallet", 5);
                    if (Elements.elementPresent("credit_decision_approve.addCardToWallet")) {
                        accNum = StringUtils.deleteWhitespace(Elements.getText("credit_decision_approve.accNumber"));
                        if (accNum != null) {
                            accNum = accNum.substring(accNum.length() - 4, accNum.length());
                        } else {
                            Assert.fail("Card number not present on the page, Exiting");
                        }
                        logger.info("New Account Number is:: " + accNum);
                        Clicks.click("credit_decision_approve.decisionApproveReturn");
                        Wait.secondsUntilElementPresent("credit_decision_approve.continueOverlay", 3);
                        Clicks.click("credit_decision_approve.continueOverlay");
                    } else {
                        Assert.fail("Add Card to Wallet option not present, exiting");
                    }
                } else if (page.equals("decision") && onPage("credit_decision_pending")) {
                    Wait.secondsUntilElementPresent("credit_decision_pending.descisionPending", 5);
                    if (Elements.elementPresent("credit_decision_pending.descisionPending")) {
                        Clicks.click("credit_decision_pending.descisionPending");
                    } else {
                        Assert.fail("No elements present to return to DOTCOM, exiting");
                    }
                } else if (page.equals("decision") && onPage("credit_decision_decline")) {
                    Wait.secondsUntilElementPresent("credit_decision_decline.verify_page", 5);
                    if (Elements.elementPresent("credit_decision_decline.verify_page")) {
                        Clicks.click("credit_decision_decline.verify_page");
                        Clicks.click("credit_decision_decline.continueOverlay");
                    } else {
                        Assert.fail("No elements present to return to DOTCOM, exiting");
                    }
                } else {
                    Assert.fail("Not on citi decision page, exiting");
                }
                break;
            default:
                Assert.fail("No button inputs passed, exiting");
        }
    }
}