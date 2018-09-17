package com.macys.sdt.projects.Marketing.Plenti.utils;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.user.UslInfo;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.ObjectMapperProvider;
import com.macys.sdt.framework.utils.Utils;
import org.junit.Assert;

import java.io.File;
import java.util.List;

import static com.macys.sdt.framework.utils.StepUtils.onPage;
import static com.macys.sdt.framework.utils.StepUtils.signedIn;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;
import static org.junit.Assert.assertTrue;

public class Usl {

    /**
     * Gets a random valid USL id from "enrolled_usl_id.json"
     *
     * @param type type of plenti to be retrieved
     * @return JSONObject containing USL information
     */
    public static UslInfo getEnrolledUslId(String type) {

        try {
            File uslFile = getResourceFile("enrolled_usl_id.json");
            String jsonTxt = Utils.readTextFile(uslFile);
            //JSON from file to Object
            List<UslInfo> uslInfoList = ObjectMapperProvider.getJsonMapper().readValue(jsonTxt,
                    TypeFactory.defaultInstance().constructCollectionType(List.class,
                            UslInfo.class));
            for (UslInfo elem : uslInfoList) {
                if (elem.getUslType().getName().equals(type)) {
                    return elem;
                }
            }

        } catch (Exception e) {
            Assert.fail("Unable to parse JSON: " + e);
            return null;
        }
        return null;
    }

    public static void verifyUslLookupMyAccount(String type, UslInfo uslInfo) {

        Wait.forPageReady();
        if (!uslInfo.getUslType().getName().equals("cancelled")) {
            Wait.untilElementNotPresent("my_account.plenti_lookup_button");
            if (Elements.elementPresent("my_account.plenti_lookup_button")) {
                Assert.fail("PlentiSteps could not be added");
            }
        } else {
            Wait.untilElementPresent("shopping_bag.plenti_signup_link");
        }

        switch (type) {

            case "fully_enrolled":
                assertFullyEnrolledMyAccount(type);
                break;

            case "pre_enrolled":
                assertPreEnrolledMyAccount(type);
                break;

            case "cancelled":
                assertCancelledMyAccount(type);
                break;

            case "anonymous":
                assertNotEnrolledMyAccount(type);
                break;
        }
    }

    public static void verifyUslLookupShoppingBag(String type, UslInfo uslInfo) {

        Wait.forPageReady();
        if (!uslInfo.getUslType().getName().equals("cancelled")) {
            Wait.untilElementPresent("shopping_bag.added_usl_id");
            if (!Elements.elementPresent("shopping_bag.added_usl_id")) {
                Assert.fail("PlentiSteps could not be added");
            }
        } else {
            Wait.untilElementPresent("shopping_bag.plenti_signup_link");
        }

        switch (type) {

            case "fully_enrolled":
                assertFullyEnrolledShoppingBag(type, uslInfo);
                break;

            case "pre_enrolled":
                assertPreEnrolledShoppingBag(type, uslInfo);
                break;

            case "cancelled":
                assertCancelledShoppingBag(type);
                break;

            case "anonymous":
                assertNotEnrolledShoppingBag(type, uslInfo);
                break;
        }
    }

    public static void verifyUslLookupPaymentPage(String type, UslInfo uslInfo) {

        Wait.forPageReady();
        String jsonElementPage = getJsonElementPage();
        if (!uslInfo.getUslType().getName().equals("cancelled")) {
            Wait.untilElementPresent(jsonElementPage + ".masked_plenti_id");
            if (!Elements.elementPresent(jsonElementPage + ".masked_plenti_id")) {
                Assert.fail("PlentiSteps could not be added");
            }
        }

        switch (type) {

            case "fully_enrolled":
                assertFullyEnrolledPaymentPage(type, uslInfo);
                break;

            case "pre_enrolled":
                assertPreEnrolledPaymentPage(type, uslInfo);
                break;

            case "cancelled":
                assertCancelledPaymentPage(type, uslInfo);
                break;

            case "anonymous":
                assertNotEnrolledPaymentPage(type, uslInfo);
                break;
        }
    }

    public static void removePlentiFromMyAccount() {
        onPage("my_account");
        Clicks.click("my_account.plenti_link");
        Wait.untilElementPresent("usl.remove_plenti_link");
        if (Elements.elementPresent("usl.remove_plenti_link")) {
            Clicks.click("usl.remove_plenti_link");
        } else {
            Assert.fail(" PlentiSteps remove link not found");
        }
        Clicks.click("usl.remove_plenti_warning_yes");
    }

    public static void removePlentiFromShoppingBag() {
        onPage("shopping_bag");
        Clicks.click("shopping_bag.plenti_remove_link");
        Wait.untilElementPresent("shopping_bag.usl_search_phone");
        if (Elements.elementPresent("shopping_bag.added_usl_id")) {
            Assert.fail("plenti could not be removed");
        }
    }

    public static void removePlentiFromPaymentPage() throws DriverNotInitializedException {

        String jsonElementPage = getJsonElementPage();
        Clicks.click(jsonElementPage + ".plenti_remove_link");
        Navigate.browserRefresh();
        Wait.untilElementPresent(jsonElementPage + ".expand_plenti_section");
        if (!Elements.elementPresent(jsonElementPage + ".expand_plenti_section")) {
            Assert.fail("plenti could not be removed");
        }
    }

    public static void plentiLookupByPhoneMyAccount(UslInfo uslInfo) {

        Wait.untilElementPresent("my_account.plenti_phone_lookup_tab");
        Clicks.click("my_account.plenti_phone_lookup_tab");
        TextBoxes.typeTextbox("my_account.plenti_lookup_textfield", uslInfo.getUslPhone());
        Clicks.click("my_account.plenti_lookup_button");

        if (!uslInfo.getUslType().getName().equals("cancelled")) {
            Wait.untilElementPresent("my_account.plenti_link");
            if (!Elements.elementPresent("my_account.plenti_link")) {
                Assert.fail("PlentiSteps could not be added");
            }
        }
    }

    public static void plentiLookupByIdMyAccount(UslInfo uslInfo) throws Throwable {

        Wait.untilElementPresent("my_account.plenti_lookup_tab");
        Thread.sleep(1000);
        Clicks.click("my_account.plenti_lookup_tab");
        TextBoxes.typeTextbox("my_account.plenti_lookup_textfield", uslInfo.getPlentiId());
        Clicks.click("my_account.plenti_lookup_button");

        if (!uslInfo.getUslType().getName().equals("cancelled")) {
            Wait.untilElementPresent("my_account.plenti_link");
            if (!Elements.elementPresent("my_account.plenti_link")) {
                Assert.fail("PlentiSteps could not be added");
            }
        }
    }

    public static void plentiLookupByPhoneShoppingBag(UslInfo uslInfo) {

        if (!Elements.elementPresent("shopping_bag.usl_phone_number")) {
            Clicks.click("shopping_bag.lookup_link");
        }
        TextBoxes.typeTextbox("shopping_bag.usl_phone_number", uslInfo.getUslPhone());
        Clicks.click("shopping_bag.usl_search_phone");

        if (!uslInfo.getUslType().getName().equals("cancelled")) {
            Wait.untilElementPresent("shopping_bag.added_usl_id");
            if (!Elements.elementPresent("shopping_bag.added_usl_id")) {
                Assert.fail("PlentiSteps could not be added");
            }
        }
    }

    public static void plentiLookupByIdShoppingBag(UslInfo uslInfo) {

        if (!Elements.elementPresent("shopping_bag.usl_phone_number")) {
            Clicks.click("shopping_bag.lookup_link");
        }
        TextBoxes.typeTextbox("shopping_bag.usl_id_field", uslInfo.getPlentiId());
        Clicks.click("shopping_bag.lookup_add_button");

        if (!uslInfo.getUslType().getName().equals("cancelled")) {
            Wait.untilElementPresent("shopping_bag.added_usl_id");
            if (!Elements.elementPresent("shopping_bag.added_usl_id")) {
                Assert.fail("PlentiSteps could not be added");
            }
        }
    }

    public static void plentiLookupByPhonePaymentPage(UslInfo uslInfo) {

        Navigate.browserRefresh();
        String jsonElementPage = getJsonElementPage();
        if (Elements.elementPresent(jsonElementPage + ".plenti_cancel")) {
            Clicks.click(jsonElementPage + ".plenti_cancel");
        }
        Wait.untilElementPresent(jsonElementPage + ".expand_plenti_section");
        Clicks.click(jsonElementPage + ".expand_plenti_section");
        TextBoxes.typeTextbox(jsonElementPage + ".plenti_text_field", uslInfo.getUslPhone());
        Clicks.click(jsonElementPage + ".plenti_search_button");

    }

    public static void plentiLookupByIdPaymentPage(UslInfo uslInfo) {

        Navigate.browserRefresh();
        String jsonElementPage = getJsonElementPage();
        if (Elements.elementPresent(jsonElementPage + ".plenti_cancel")) {
            Clicks.click(jsonElementPage + ".plenti_cancel");
        }
        Wait.untilElementPresent(jsonElementPage + ".expand_plenti_section");
        Clicks.click(jsonElementPage + ".expand_plenti_section");
        Clicks.click(jsonElementPage + ".plenti_lookup_byId_radiobutton");
        TextBoxes.typeTextbox(jsonElementPage + ".plenti_text_field", uslInfo.getPlentiId());
        Clicks.click(jsonElementPage + ".plenti_search_button");

    }

    public static void VerifyLookupByCCPaymentPage(String pageName, UslInfo uslInfo) {

        Navigate.browserRefresh();
        String jsonElementPage = getJsonElementPage();
        if (Elements.elementPresent(jsonElementPage + ".plenti_cancel")) {
            Clicks.click(jsonElementPage + ".plenti_cancel");
        }
        Wait.untilElementPresent(jsonElementPage + ".expand_plenti_section");
        Clicks.click(jsonElementPage + ".expand_plenti_section");
        Clicks.click(jsonElementPage + ".plenti_lookup_byCC_radiobutton");
        TextBoxes.typeTextbox(jsonElementPage + ".plenti_text_field", uslInfo.getUslLinkedCard());
        Clicks.click(jsonElementPage + ".plenti_search_button");

        if (uslInfo.getUslType().getName().equals("cancelled")) {
            Usl.verifyUslLookupPaymentPage(uslInfo.getUslType().getName(), uslInfo);
        } else {
            Wait.untilElementPresent(jsonElementPage + ".masked_plenti_id");
            if (Elements.elementPresent(jsonElementPage + ".masked_plenti_id")) {
                Usl.verifyUslLookupPaymentPage(uslInfo.getUslType().getName(), uslInfo);
            } else {
                Assert.fail("PlentiSteps could not be added");
            }
        }
    }

    public static void assertFullyEnrolledMyAccount(String type) {

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent("my_account.plenti_lookup_error"));

        assertTrue("PlentiSteps link not found for fully enrolled",
                Elements.elementPresent("my_account.plenti_link"));

        assertTrue("PlentiSteps points not found for fully enrolled",
                Elements.elementPresent("my_account.plenti_points"));

//        for 0 points this message will not come
//        assertTrue("PlentiSteps points message not found for fully enrolled",
//                Elements.elementPresent("my_account.plenti_points_message"));

        assertTrue("Prompt message found for fully enrolled",
                !Elements.elementPresent("my_account.plenti_lookup_prompt_message"));

        assertTrue("Brand logo not found for fully enrolled",
                Elements.elementPresent("my_account.plenti_brand"));

    }

    public static void assertPreEnrolledMyAccount(String type) {

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent("my_account.plenti_lookup_error"));

        assertTrue("PlentiSteps link not found for pre-enrolled",
                Elements.elementPresent("my_account.plenti_link"));

        assertTrue("PlentiSteps points found for pre-enrolled",
                !Elements.elementPresent("my_account.plenti_points"));

        assertTrue("PlentiSteps points message found for pre-enrolled",
                !Elements.elementPresent("my_account.plenti_points_message"));

        assertTrue("Prompt message not found for pre-enrolled",
                Elements.elementPresent("my_account.plenti_lookup_prompt_message"));

        assertTrue("Prompt message not correct for pre-enrolled",
                Elements.findElement("my_account.plenti_lookup_prompt_message").getText()
                        .equals("Finish Signing Up For Plenti"));

        assertTrue("Prompt link incorrect for pre-enrolled",
                Elements.findElement("my_account.plenti_lookup_prompt_message").getAttribute("href")
                        .contains("/loyalty/enroll?uslStatus=PREENROLLED&origin=MYACCOUNT"));

        assertTrue("Brand logo not found for pre-enrolled",
                Elements.elementPresent("my_account.plenti_brand"));
    }

    public static void assertNotEnrolledMyAccount(String type) {

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent("my_account.plenti_lookup_error"));

        assertTrue("PlentiSteps link not found for not-enrolled",
                Elements.elementPresent("my_account.plenti_link"));

        assertTrue("PlentiSteps points found for not-enrolled",
                !Elements.elementPresent("my_account.plenti_points"));

        assertTrue("PlentiSteps points message found for not-enrolled",
                !Elements.elementPresent("my_account.plenti_points_message"));

        assertTrue("Prompt message not found for not-enrolled",
                Elements.elementPresent("my_account.plenti_lookup_prompt_message"));

        assertTrue("Prompt message not correct for not-enrolled",
                Elements.findElement("my_account.plenti_lookup_prompt_message").getText()
                        .equals("Finish Signing Up For Plenti"));

        assertTrue("Prompt link incorrect for not-enrolled",
                Elements.findElement("my_account.plenti_lookup_prompt_message").getAttribute("href")
                        .contains("/loyalty/enroll?uslStatus=NOT_ENROLLED&origin=MYACCOUNT"));

        assertTrue("Brand logo not found for not-enrolled",
                Elements.elementPresent("my_account.plenti_brand"));
    }

    public static void assertCancelledMyAccount(String type) {

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                Elements.elementPresent("my_account.plenti_lookup_error"));

        assertTrue("PlentiSteps link found for cancelled",
                !Elements.elementPresent("my_account.plenti_link"));

        assertTrue("PlentiSteps points found for cancelled",
                !Elements.elementPresent("my_account.plenti_points"));

        assertTrue("PlentiSteps points message found for cancelled",
                !Elements.elementPresent("my_account.plenti_points_message"));

        assertTrue("Prompt message found for cancelled",
                !Elements.elementPresent("my_account.plenti_lookup_prompt_message"));

        assertTrue("Prompt message not correct for cancelled",
                Elements.findElement("my_account.plenti_signup_link").getText()
                        .equals("Sign Up For Plenti."));

        assertTrue("Prompt link incorrect for cancelled",
                Elements.findElement("my_account.plenti_signup_link").getAttribute("href")
                        .contains("/loyalty/enroll?uslStatus=CANCELLED&origin=MYACCOUNT"));

        assertTrue("Brand logo not found for cancelled",
                Elements.elementPresent("my_account.plenti_brand"));
    }

    public static void assertFullyEnrolledShoppingBag(String type, UslInfo uslInfo) {

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent("shopping_bag.plenti_error_message"));

        if(signedIn()) {
            assertTrue("Save to profile checkbox not found for fully-enrolled",
                    Elements.elementPresent("shopping_bag.plenti_save_to_profile_chkbox"));
            assertTrue("Save to profile label not found for fully-enrolled",
                    Elements.elementPresent("shopping_bag.plenti_save_to_profile_label"));
        } else {
            assertTrue("Save to profile checkbox found for fully-enrolled for guest",
                    !Elements.elementPresent("shopping_bag.plenti_save_to_profile_chkbox"));
            assertTrue("Save to profile label not found for fully-enrolled for guest",
                    !Elements.elementPresent("shopping_bag.plenti_save_to_profile_label"));
        }

        assertTrue("PlentiSteps remove link not found for fully-enrolled",
                Elements.elementPresent("shopping_bag.plenti_remove_link"));

        assertTrue("PlentiSteps banner not found for fully-enrolled",
                Elements.elementPresent("shopping_bag.plenti_banner"));

        assertTrue("Prompt message found for for fully-enrolled",
                !Elements.elementPresent("shopping_bag.plenti_prompt_message"));

        assertTrue("Finish signup link found for fully-enrolled",
                !Elements.elementPresent("shopping_bag.plenti_finish_signup_link"));

        assertTrue("Cancelled prompt found for fully-enrolled",
                !Elements.elementPresent("shopping_bag.plenti_cancelled_prompt"));

        assertTrue("Wrong plenti id details for fully-enrolled",
                Elements.findElement("shopping_bag.added_usl_id").getText().substring(10,26)
                        .equals("************" + uslInfo.getPlentiId().substring(12, 16)));

    }

    public static void assertPreEnrolledShoppingBag(String type, UslInfo uslInfo) {

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent("shopping_bag.plenti_error_message"));

        if(signedIn()) {
            assertTrue("Save to profile checkbox not found for pre-enrolled",
                    Elements.elementPresent("shopping_bag.plenti_save_to_profile_chkbox"));
            assertTrue("Save to profile label not found for pre-enrolled",
                    Elements.elementPresent("shopping_bag.plenti_save_to_profile_label"));
        } else {
            assertTrue("Save to profile checkbox found for pre-enrolled for guest",
                    !Elements.elementPresent("shopping_bag.plenti_save_to_profile_chkbox"));
            assertTrue("Save to profile label not found for pre-enrolled for guest",
                    !Elements.elementPresent("shopping_bag.plenti_save_to_profile_label"));
        }

        assertTrue("PlentiSteps remove link not found for pre-enrolled",
                Elements.elementPresent("shopping_bag.plenti_remove_link"));

        assertTrue("PlentiSteps banner not found for pre-enrolled",
                Elements.elementPresent("shopping_bag.plenti_banner"));

        assertTrue("Prompt message not found for pre-enrolled",
                Elements.elementPresent("shopping_bag.plenti_prompt_message"));

        assertTrue("Finish signup link found for pre-enrolled",
                !Elements.elementPresent("shopping_bag.plenti_finish_signup_link"));

        assertTrue("Cancelled prompt found for pre-enrolled",
                !Elements.elementPresent("shopping_bag.plenti_cancelled_prompt"));

        assertTrue("Wrong plenti id details for pre-enrolled",
                Elements.findElement("shopping_bag.added_usl_id").getText().substring(10,26)
                        .equals("************" + uslInfo.getPlentiId().substring(12, 16)));

    }

    public static void assertCancelledShoppingBag(String type) {

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent("shopping_bag.plenti_error_message"));

        assertTrue("Save to profile checkbox found for cancelled",
                !Elements.elementPresent("shopping_bag.plenti_save_to_profile_chkbox"));

        assertTrue("Save to profile label found for cancelled",
                !Elements.elementPresent("shopping_bag.plenti_save_to_profile_label"));

        assertTrue("PlentiSteps remove link found for cancelled",
                !Elements.elementPresent("shopping_bag.plenti_remove_link"));

        assertTrue("PlentiSteps banner not found for cancelled",
                Elements.elementPresent("shopping_bag.plenti_banner"));

        assertTrue("Prompt message not found for cancelled",
                Elements.elementPresent("shopping_bag.plenti_cancelled_prompt_msg"));

        assertTrue("Finish signup link found for cancelled",
                !Elements.elementPresent("shopping_bag.plenti_finish_signup_link"));

        assertTrue("Prompt link incorrect for cancelled",
                Elements.findElement("shopping_bag.plenti_signup_link").getAttribute("href")
                        .contains("/loyalty/enroll?program=usl&origin=bag&uslStatus=CANCELLED"));

        assertTrue("Cancelled prompt not found for cancelled",
                Elements.elementPresent("shopping_bag.plenti_cancelled_prompt"));

        assertTrue("PlentiSteps id found for cancelled",
                !Elements.elementPresent("shopping_bag.added_usl_id"));

    }

    public static void assertNotEnrolledShoppingBag(String type, UslInfo uslInfo) {

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent("shopping_bag.plenti_error_message"));

        if(signedIn()) {
            assertTrue("Save to profile checkbox not found for not-enrolled",
                    Elements.elementPresent("shopping_bag.plenti_save_to_profile_chkbox"));
            assertTrue("Save to profile label not found for not-enrolled",
                    Elements.elementPresent("shopping_bag.plenti_save_to_profile_label"));
        } else {
            assertTrue("Save to profile checkbox found for not-enrolled for guest",
                    !Elements.elementPresent("shopping_bag.plenti_save_to_profile_chkbox"));
            assertTrue("Save to profile label not found for not-enrolled for guest",
                    !Elements.elementPresent("shopping_bag.plenti_save_to_profile_label"));
        }

        assertTrue("PlentiSteps remove link not found for not-enrolled",
                Elements.elementPresent("shopping_bag.plenti_remove_link"));

        assertTrue("PlentiSteps banner not found for not-enrolled",
                Elements.elementPresent("shopping_bag.plenti_banner"));

        assertTrue("Prompt message not found for not-enrolled",
                Elements.elementPresent("shopping_bag.plenti_prompt_message"));

        assertTrue("Finish signup link not found for not-enrolled",
                Elements.elementPresent("shopping_bag.plenti_finish_signup_link"));

        assertTrue("Prompt link incorrect for pre-enrolled",
                Elements.findElement("shopping_bag.plenti_finish_signup_link").getAttribute("href")
                        .contains("/loyalty/enroll?program=usl&origin=bag&uslStatus=NOT_ENROLLED"));

        assertTrue("Cancelled prompt found for not-enrolled",
                !Elements.elementPresent("shopping_bag.plenti_cancelled_prompt"));

        assertTrue("Wrong plenti id details for not-enrolled",
                Elements.findElement("shopping_bag.added_usl_id").getText().substring(10,26)
                        .equals("************" + uslInfo.getPlentiId().substring(12, 16)));

    }

    public static void assertFullyEnrolledPaymentPage(String type, UslInfo uslInfo) {

        String jsonElementPage = getJsonElementPage();

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent(jsonElementPage + ".plenti_lookup_error"));

        assertTrue("PlentiSteps remove link not found for fully-enrolled",
                Elements.elementPresent(jsonElementPage + ".plenti_remove_link"));

        assertTrue("PlentiSteps banner not found for fully-enrolled",
                Elements.elementPresent(jsonElementPage + ".plenti_label"));

        assertTrue("Finish signup message found for fully-enrolled",
                !Elements.elementPresent(jsonElementPage + ".plenti_finish_signup_msg"));

        assertTrue("Wrong plenti id details for fully-enrolled",
                Elements.findElement(jsonElementPage + ".masked_plenti_id").getText()
                        .equals("************" + uslInfo.getPlentiId().substring(12, 16)));
    }

    public static void assertPreEnrolledPaymentPage(String type, UslInfo uslInfo) {

        String jsonElementPage = getJsonElementPage();

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent(jsonElementPage + ".plenti_lookup_error"));

        assertTrue("PlentiSteps remove link not found for pre-enrolled",
                Elements.elementPresent(jsonElementPage + ".plenti_remove_link"));

        assertTrue("PlentiSteps banner not found for pre-enrolled",
                Elements.elementPresent(jsonElementPage + ".plenti_label"));

        assertTrue("Finish signup message not found for pre-enrolled",
                Elements.elementPresent(jsonElementPage + ".plenti_finish_signup_msg"));

        assertTrue("Wrong plenti id details for pre-enrolled",
                Elements.findElement(jsonElementPage + ".masked_plenti_id").getText()
                        .equals("************" + uslInfo.getPlentiId().substring(12, 16)));
    }

    public static void assertCancelledPaymentPage(String type, UslInfo uslInfo) {

        String jsonElementPage = getJsonElementPage();

        assertTrue("PlentiSteps Lookup error msg for cancelled PlentiSteps not found ",
                Elements.elementPresent(jsonElementPage + ".plenti_lookup_error"));

        assertTrue("PlentiSteps remove link found for cancelled",
                !Elements.elementPresent(jsonElementPage + ".plenti_remove_link"));

       // assertTrue("PlentiSteps banner not found for cancelled",
             //   Elements.elementPresent("responsive_checkout_signed_in.plenti_label"));

        assertTrue("Finish signup message found for cancelled",
                !Elements.elementPresent(jsonElementPage + ".plenti_finish_signup_msg"));

        assertTrue("PlentiSteps id found for cancelled",
                !Elements.elementPresent(jsonElementPage + ".masked_plenti_id"));
    }

    public static void assertNotEnrolledPaymentPage(String type, UslInfo uslInfo) {

        String jsonElementPage = getJsonElementPage();

        assertTrue("PlentiSteps Lookup failed with the following error message displayed: ",
                !Elements.elementPresent(jsonElementPage + ".plenti_lookup_error"));

        assertTrue("PlentiSteps remove link not found for not-enrolled",
                Elements.elementPresent(jsonElementPage + ".plenti_remove_link"));

        assertTrue("PlentiSteps banner not found for not-enrolled",
                Elements.elementPresent(jsonElementPage + ".plenti_label"));

        assertTrue("Finish signup message not found for not-enrolled",
                Elements.elementPresent(jsonElementPage + ".plenti_finish_signup_msg"));

        assertTrue("Wrong plenti id details for not-enrolled",
                Elements.findElement(jsonElementPage + ".masked_plenti_id").getText()
                        .equals("************" + uslInfo.getPlentiId().substring(12, 16)));
    }

    public static String getJsonElementPage() {
        String url = WebDriverManager.getCurrentUrl();
        String jsonPage = null;
        if (url.contains("chkout/rcsignedin")) {
            jsonPage = "responsive_checkout_signed_in";
        } else {
            jsonPage = "responsive_checkout";
        }
        return jsonPage;
    }

    public static void redeemPlentiPoints() {

        String jsonElementPage = getJsonElementPage();
        if (Elements.elementPresent(jsonElementPage + ".plenti_redeem_button")) {
            Clicks.click(jsonElementPage + ".plenti_redeem_button");
        }
        TextBoxes.typeTextbox(jsonElementPage + ".redeem_amount_field", "2.00");
        TextBoxes.typeTextbox(jsonElementPage + ".plenti_redeem_pin", "1234");
        Clicks.click(jsonElementPage + ".plenti_redeem_apply_button");
        if (jsonElementPage.contains("responsive_checkout_signed_in")) {
            if (!Elements.elementPresent(jsonElementPage + ".plenti_points_remove")) {
                Assert.fail("PlentiSteps points cound not be redeemed");
            }
        } else if (jsonElementPage.contains("responsive_checkout")) {
            if (!Elements.elementPresent(jsonElementPage + ".plenti_edit_button")) {
                Assert.fail("PlentiSteps points cound not be redeemed");
            }
        }
    }

    public static void verifyUslPointsInOrderConfirmationPage() {

        assertTrue("PlentiSteps section not found in Order Confirmation page: ",
                Elements.elementPresent("responsive_order_confirmation.plenti_section"));

        assertTrue("PlentiSteps pending points not found in Order Confirmation page: ",
                Elements.elementPresent("responsive_order_confirmation.plenti_points_balance"));

        assertTrue("PlentiSteps redeemed points found in Order Confirmation page: ",
                Elements.elementPresent("responsive_order_confirmation.plenti_points_redeemed"));;

        assertTrue("PlentiSteps available points not found in Order Confirmation page: ",
                Elements.elementPresent("responsive_order_confirmation.plenti_points_available"));

       /* String availablePoints = Elements.findElement("responsive_order_confirmation.plenti_points_available")
                .getText();
        if (points - Integer.getInteger(availablePoints) != 2) {
            Assert.fail("Redeemed points could not be validated");
        }*/
    }
}
