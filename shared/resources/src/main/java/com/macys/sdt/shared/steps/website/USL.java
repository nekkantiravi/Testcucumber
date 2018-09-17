package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.user.UslInfo;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.mcom.pages.checkout.Checkout;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

//import com.macys.sdt.projects.Marketing.Regression.utils.Usl;

public class USL extends StepUtils {

    /**
     * Looks up a plenti ID on given page
     *
     * @param page payment|shopping bag|my account
     * @throws Throwable if any exception occurs
     */
    @And("^I lookup plenti id using valid usl (plenti id|phone number) on (payment|shopping bag|my account) page$")
    public void I_lookup_plenti_id_using_valid_usl_phone_number(String plentiMethod, String page) throws Throwable {
        String pageName = null;
        UslInfo usl_info;
        switch (page) {
            case "payment":
                pageName = "shipping_payment_signed_in";
                break;
            case "shopping bag":
                pageName = "shopping_bag";
                break;
        }
        // String phone_no = CommonUtils.getEnrolledUslId().getString("usl_phone");
        usl_info = TestUsers.getUSLInformation();
        Wait.untilElementPresent(pageName + ".lookup_link");
        Elements.elementInView(pageName + ".lookup_link");
        Clicks.click(pageName + ".lookup_link");
        switch (plentiMethod) {
            case "phone number":
                String phone_no = usl_info.getUslPhone();
                TextBoxes.typeTextbox(pageName + ".usl_phone_number", phone_no);
                break;
            case "plenti id":
                String plentiId = usl_info.getPlentiId();
                Wait.untilElementPresent(pageName + ".usl_id_checkbox");
                Clicks.click(pageName + ".usl_id_checkbox");
                TextBoxes.typeTextbox(pageName + ".usl_phone_number", plentiId);
                break;
        }

        Wait.forPageReady();
        Clicks.click(pageName + ".usl_search_phone");
        Wait.untilElementPresent(pageName + ".usl_added_result");
        Wait.secondsUntilElementPresent(pageName + ".added_usl_id", 5);

    }

    @And("^I add usl as payment on (payment|shopping bag|my account) page$")
    public void iAddUslAsPayment(String page) throws Throwable {
        String pageName;
        switch (page) {
            case "payment":
                pageName = "shipping_payment_signed_in";
                break;
            case "shopping bag":
                pageName = "shopping_bag";
                break;
            case "my account":
                pageName = "my_account";
                break;
            default:
                pageName = page;
        }
        Checkout.addUslAsPayment();
        Wait.secondsUntilElementPresent(pageName + ".plenti_points_text", 5);
    }

    @And("^I expand plenti panel on \"([^\"]*)\" page$")
    public void iExpandPlentiPanelOnPage(String page) throws Throwable {
        Wait.untilElementPresent("responsive_checkout_signed_in.change_shipping_address");
        Clicks.click(page + ".lookup_link");
    }


    @Then("^I validate Plenti functionality in checkout page$")
    public void iValidatePlentiFunctionalityInCheckoutPage() throws Throwable {
        Assert.assertTrue("Plenti Logo is not getting displayed", Elements.elementPresent("responsive_checkout_signed_in.plenti_logo"));
        Assert.assertTrue("Plenti Remove link is not getting displayed", Elements.elementPresent("responsive_checkout_signed_in.plenti_remove_link"));
        Assert.assertTrue("Plenti id is not masked", Elements.elementPresent("responsive_checkout_signed_in.masked_plenti_id"));
        Assert.assertTrue("Plenti points are getting displayed", Elements.elementPresent("responsive_checkout_signed_in.plenti_points_info"));

    }
}
