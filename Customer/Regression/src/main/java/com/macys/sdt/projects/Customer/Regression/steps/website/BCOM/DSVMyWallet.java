package com.macys.sdt.projects.Customer.Regression.steps.website.BCOM;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.macys.sdt.framework.utils.TestUsers.getValidVisaCreditCard;

/**
 * Created by Pawan on 7/20/2017.
 */
public class DSVMyWallet extends CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(DSVMyWallet.class);

    @And("^I click on add a credit card on wallet page$")
    public void i_click_on_add_a_credit_card_on_wallet_page() throws Throwable {

        Clicks.click("my_bwallet.add_credit_card_btn");
        Assert.assertTrue("Add credit card dialog did not appear",
                Elements.elementPresent("credit_card_dialog.card_number"));
    }

    @Given("^I add a credit card from my bwallet page with Zip code starting with \"([^\"]*)\"$")
    public void i_add_a_credit_card_from_my_bwallet_page_with_Zip_code_starting_with(String arg1) throws Throwable {

        int month = 4;
        CreditCard creditCard = getValidVisaCreditCard();
        UserProfile customer = TestUsers.getCustomer(null);

        DropDowns.selectCustomText("credit_card_dialog.card_type", "credit_card_dialog.card_type_options", creditCard.getCardType().name);
        DropDowns.selectCustomValue("credit_card_dialog.expiry_month", "credit_card_dialog.expiry_month_options", month);
        DropDowns.selectCustomText("credit_card_dialog.expiry_year", "credit_card_dialog.expiry_year_options", creditCard.getExpiryYear());
        DropDowns.selectCustomText("credit_card_dialog.card_type", "credit_card_dialog.card_type_options", creditCard.getCardType().name);
        DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options",
                Elements.getValues("special_address.state").get(0));
        DropDowns.selectCustomValue("credit_card_dialog.expiry_month", "credit_card_dialog.expiry_month_options", month);
        DropDowns.selectCustomText("credit_card_dialog.expiry_year", "credit_card_dialog.expiry_year_options", creditCard.getExpiryYear());
        TextBoxes.typeTextbox("credit_card_dialog.card_number", creditCard.getCardNumber());
        TextBoxes.typeTextbox("credit_card_dialog.first_name", customer.getUser().getProfileAddress().getFirstName());
        TextBoxes.typeTextbox("credit_card_dialog.last_name", customer.getUser().getProfileAddress().getLastName());

        //special address validations
        logger.info("state::" + Elements.getValues("special_address.state").get(0));
        logger.info("address::" + Elements.getValues("special_address.address").get(0));
        logger.info("city::" + Elements.getValues("special_address.city").get(0));
        logger.info("pin code::" + Elements.getValues("special_address.pin_code").get(0));
        TextBoxes.typeTextbox("credit_card_dialog.address_line_1", Elements.getValues("special_address.address").get(0));
        TextBoxes.typeTextbox("credit_card_dialog.address_city", Elements.getValues("special_address.city").get(0));
        TextBoxes.typeTextbox("credit_card_dialog.address_zip_code", Elements.getValues("special_address.pin_code").get(0));
        TextBoxes.typeTextbox("credit_card_dialog.payment_email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_area_code", customer.getUser().getProfileAddress().getPhoneAreaCode());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_exchange", customer.getUser().getProfileAddress().getPhoneExchange());
        TextBoxes.typeTextbox("credit_card_dialog.card_phone_subscriber", customer.getUser().getProfileAddress().getPhoneSubscriber());

        if (!Elements.elementPresent("credit_card_dialog.save_card")) {
            Clicks.hoverForSelection("credit_card_dialog.save_card");
        }
        Clicks.click("credit_card_dialog.save_card");
    }

    @When("^I click on edit or remove a card link$")
    public void i_click_on_edit_or_remove_a_card_link() throws Throwable {

        Clicks.click("my_wallet.edit_card");
        Assert.assertTrue("Edit credit card dialog did not appear",
                Elements.elementPresent("credit_card_dialog.card_number"));

    }

    @Then("^I should not see zip code trimmed$")
    public void i_should_not_see_zip_code_trimmed() throws Throwable {

        logger.info("Found zip code::" + Elements.findElement("credit_card_dialog.address_zip_code").getAttribute("value"));
        Assert.assertTrue("Zip code is trimmed, found Zip code::" + Elements.getText("credit_card_dialog.address_zip_code"),
                Elements.findElement("credit_card_dialog.address_zip_code").getAttribute("value").equalsIgnoreCase(Elements.getValues("special_address.pin_code").get(0)));


    }

}



