package com.macys.sdt.projects.Customer.OCTCitiMonthly.actions.website.citi.pages.credit_services;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.projects.Customer.OCTCitiMonthly.model.CreditManageCard;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.projects.Customer.OCTCitiMonthly.utils.CreditServicesUtil.*;

public class AddCard extends StepUtils {
    public static Boolean noAtw = false;
    private static final Logger logger = LoggerFactory.getLogger(AddCard.class);

    /**
     * To get Add Card data and fill on CS Add Card page
     */
    public static void fillAddCardInfo(String cardType, String addToWallet, boolean authUser) {
        if (!onPage("credit_add_card")) {
            Assert.fail("Not on Add Card page, Can't proceed with Test");
        }

        CreditManageCard creditAddCard = getAddCardInfo(cardType, authUser);

        if (!safari() && Elements.elementPresent("credit_add_card.cardNumber")) {
            stopPageLoad();

            if (Elements.elementPresent("credit_add_card.cardNumber")) {
                TextBoxes.typeTextbox("credit_add_card.cardNumber", creditAddCard.getCardNumber());
            } else {
                Assert.fail("Card Number Field not present, exiting");
            }

            if (Elements.elementPresent("credit_add_card.name")) {
                TextBoxes.typeTextbox("credit_add_card.name", creditAddCard.getName());
            } else {
                Assert.fail("Name Field not present, exiting");
            }

            if (Elements.elementPresent("credit_add_card.cvv")) {
                TextBoxes.typeTextbox("credit_add_card.cvv", creditAddCard.getSecurityCode());
            } else {
                Assert.fail("CVV Field not present, exiting");
            }

            if (Elements.elementPresent("credit_add_card.ssn")) {
                TextBoxes.typeTextbox("credit_add_card.ssn", creditAddCard.getSsn());
            } else {
                Assert.fail("SSN Field not present, exiting");
            }

            if (Elements.elementPresent("credit_add_card.addToWallet") && addToWallet.equals("no atw")) {
                Clicks.click("credit_add_card.addToWallet");
                noAtw = true;
            }
            logger.info("Not Add To Wallet:" + noAtw + addToWallet);
            if (Elements.elementPresent("credit_add_card.verify")) {
                Clicks.click("credit_add_card.verify");
            } else {
                Assert.fail("Verify button not present, exiting");
            }
        } else {
            Assert.fail("Elements not present, exiting");
        }
    }

    /**
     * To add security questions for added card in CS Add Card flow
     */
    public static void addSecureQuestions() {

        if (onPage("credit_add_card_security_question") && Elements.elementPresent("credit_add_card_security_question.submit")) {
            if (Elements.elementPresent("credit_add_card_security_question.select_que1")) {
                Clicks.click("credit_add_card_security_question.submit");
                Wait.untilElementPresent("credit_add_card_security_question.answer1");
                if (Elements.elementPresent("credit_add_card_security_question.answer1")) {
                    TextBoxes.typeTextbox("credit_add_card_security_question.answer1", "test01");
                } else {
                    Assert.fail("Answer1 Field not present, exiting");
                }

                if (Elements.elementPresent("credit_add_card_security_question.answer2")) {
                    TextBoxes.typeTextbox("credit_add_card_security_question.answer2", "test02");
                } else {
                    Assert.fail("Answer2 Field not present, exiting");
                }

                if (Elements.elementPresent("credit_add_card_security_question.answer3")) {
                    TextBoxes.typeTextbox("credit_add_card_security_question.answer3", "test03");
                } else {
                    Assert.fail("Answer3 Field not present, exiting");
                }
            } else {
                Assert.fail("Security question1 not present, exiting");
            }

            Clicks.click("credit_add_card_security_question.submit");
        } else {
            Assert.fail("Not on Security Questions page or Security Questions not present, exiting");
        }
    }

    /**
     * To add security questions for added card in CS Add Card flow
     */
    public static void fillAuthUserInfo(String cardType) {
        if (!onPage("credit_add_auth_user")) {
            Assert.fail("Not on Add Authorized user page, Exiting");
        }
        JSONObject authUserData = new JSONObject();
        User addAuthUserInfo = getAddAuthUserInfo(null);
        if (!safari() && Elements.elementPresent("credit_add_auth_user.first_name")) {
            if (Elements.elementPresent("credit_add_auth_user.first_name")) {
                String fName = addAuthUserInfo.getProfileAddress().getFirstName();
                TextBoxes.typeTextbox("credit_add_auth_user.first_name", fName);
                authUserData.put("firstName", fName);
            } else {
                Assert.fail("Auth User First Name Field not present, exiting");
            }
            if (Elements.elementPresent("credit_add_auth_user.last_name")) {
                String lName = addAuthUserInfo.getProfileAddress().getLastName();
                TextBoxes.typeTextbox("credit_add_auth_user.last_name", lName);
                authUserData.put("lastName", lName);
            } else {
                Assert.fail("Auth User Last Name Field not present, exiting");
            }
            if (Elements.elementPresent("credit_add_auth_user.dob")) {
                String dob = addAuthUserInfo.getDateOfBirth();
                TextBoxes.typeTextbox("credit_add_auth_user.dob", dob.replaceAll("[/]", ""));
                authUserData.put("dob", dob);
            } else {
                Assert.fail("Auth User DOB Field not present, exiting");
            }
            if (Elements.elementPresent("credit_add_auth_user.address")) {
                String address = addAuthUserInfo.getProfileAddress().getAddressLine1();
                TextBoxes.typeTextbox("credit_add_auth_user.address", address);
                authUserData.put("address", address);
            } else {
                Assert.fail("Auth User Address Field not present, exiting");
            }
            if (Elements.elementPresent("credit_add_auth_user.city")) {
                String city = addAuthUserInfo.getProfileAddress().getCity();
                TextBoxes.typeTextbox("credit_add_auth_user.city", city.replaceAll("[^a-zA-Z0-9 ]", ""));
                authUserData.put("city", city);
            } else {
                Assert.fail("Auth User City Field not present, exiting");
            }
            Clicks.clickIfPresent("credit_add_auth_user.state_options");
            if (Elements.elementPresent("credit_add_auth_user.state_options")) {
                String state = addAuthUserInfo.getProfileAddress().getState();
                DropDowns.selectByText("credit_add_auth_user.state", state);
                authUserData.put("state", state);
            } else {
                Assert.fail("Auth User State Field not present, exiting");
            }
            if (Elements.elementPresent("credit_add_auth_user.zip")) {
                String zip = addAuthUserInfo.getProfileAddress().getZipCode();
                TextBoxes.typeTextbox("credit_add_auth_user.zip", zip);
                authUserData.put("zip", zip);
            } else {
                Assert.fail("Auth User zipCode Field not present, exiting");
            }
            if (Elements.elementPresent("credit_add_auth_user.add_user")) {
                Clicks.click("credit_add_auth_user.add_user");
                appendUpdateCardDetails("authUser", cardType, authUserData);
            } else {
                Assert.fail("Add User button not present, exiting");
            }
            Utils.threadSleep(5000, "Waiting for identity check popup to show up");
            if (Elements.elementPresent("credit_add_auth_user.id_check_popup")) {
                Clicks.clickIfPresent("credit_add_auth_user.auth_que_1");
                TextBoxes.typeTextbox("credit_add_auth_user.auth_que_1", "test01");
                Clicks.clickIfPresent("credit_add_auth_user.auth_que_2");
                TextBoxes.typeTextbox("credit_add_auth_user.auth_que_2", "test02");
                Clicks.click("credit_add_auth_user.continue_popup");
            }
        }
    }
}
