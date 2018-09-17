package com.macys.sdt.projects.Marketing.GuestOffers.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.Marketing.GuestOffers.utils.OffersService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.macys.sdt.framework.utils.StepUtils;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by m661571 on 6/8/17.
 */
public class GuestOffers {
    private static final Logger logger = LoggerFactory.getLogger(GuestOffers.class);
    public int sub_total_before, saved_amount_before;


    @Then("^I should see (.*) text in Wallet section$")
    public void iShouldSeeExpected_textTextInWalletSection(String text) throws Throwable {

        logger.info("Expected::::::::" + text);
        logger.info("actual::::::::" + Elements.findElement("guest_offers.title_copy").getText());

        switch (text.toLowerCase()) {
            case "Eligible offers":
                assertTrue("Eligible offers Title is not being displayed",
                        Elements.findElement("guest_offers.eligible_offers_title").getText().contentEquals(text));
                break;

            case "Sign-In":
                assertTrue("Sign-In link is not being displayed",
                        Elements.findElement("guest_offers.Sign-In").getText().contentEquals(text));
                break;
            case "there might be better offers in your wallet. sign in to make sure you get the best one!":
                logger.info("Expected::::::::" + text);
                logger.info("actual::::::::" + Elements.findElement("guest_offers.title_copy").getText());
                assertTrue("eligible offers titile copy is not being displayed",
                        Elements.findElement("guest_offers.title_copy").getText().contentEquals(text));

                break;

            default:

                break;
        }
    }

    @Then("^I should see wallet icon$")
    public void iShouldBeAbleToSeeWalletIcon() throws Throwable {
        Wait.forPageReady();
        assertTrue("Wallet icon is not displayed", Elements.elementPresent("guest_offers.wallet_icon"));
        logger.info("Verified: Wallet icon is displayed.");

    }

    @And("^I should see \"([^\"]*)\" title$")
    public void iShouldSeeTitle(String arg0) throws Throwable {
        assertTrue(" Failed to display Eligible offers title .", Elements.elementPresent("guest_offers.eligible_offers_title"));
        logger.info("Verified: Eligible offers title is displayed.");
    }

    @And("^I should see the enter promo code text$")
    public void iShouldSeeTheEnterPromoCodeText(List<String> expectedMessage) throws Throwable {
        logger.info("Enter Promo Code Text is: " + Elements.getText("guest_offers.have_a_promo_code"));
        Assert.assertTrue("Ener Promo code Text is not displayed", Elements.getText("guest_offers.have_a_promo_code").equals(expectedMessage.get(0)));
        logger.info("Successfully verified the Enter promo code text above promo code text box");
    }

    @And("^I should see following best offer text in eligible offer section$")
    public void iShouldSeeFollowingBestOfferTextInEligibleOfferSection(List<String> expectedMessage) throws Throwable {
        logger.info("Best Offer Text is: " + Elements.getText("guest_offers.best_offer"));
        Assert.assertTrue("Best Offer Text is not displayed", Elements.getText("guest_offers.best_offer").equals(expectedMessage.get(0)));
        logger.info("Successfully verified the Best offer text above promo description");
    }

    @When("^I apply \"([^\"]*)\" promo code manually$")
    public void iApplyPromoCodeManually(String promo_code) throws Throwable {
        Wait.forPageReady();
        TextBoxes.typeTextNEnter("guest_offers.promo_code_text_box", promo_code);
        Clicks.click("guest_offers.apply_button_beside_textbox");
    }

    @And("^I save the bag subtotal$")
    public void iSaveTheBagSubtotal() throws Throwable {
        Wait.ajaxDone();
        String subtotal = Elements.findElement("guest_offers.sub_total").getText().replace("$", "");
        sub_total_before = Integer.parseInt(subtotal);
    }

    @When("^I apply promotion$")
    public void iApplyPromotion() throws Throwable {
        Clicks.click("guest_offers.apply_button_promodesc");
    }

    @Then("^I verify offer applied successfully$")
    public void iVerifyOfferAppliedSuccessfully() throws Throwable {
        float sub_total_after;
        String subTotal = Elements.findElement("guest_offers.sub_total").getText().replace("$", "");
        sub_total_after = Integer.parseInt(subTotal);
        String savedAmount = Elements.findElement("guest_offers.promo_discount_amount").getText().split("$")[1];
        saved_amount_before = Integer.parseInt(savedAmount);
        assertTrue("Offer is not applied successfully", sub_total_after == (sub_total_before - saved_amount_before));
        logger.info("Offer is applied successfully");
    }

    @And("^I verify applied offer details$")
    public void iVerifyAppliedOfferDetails() throws Throwable {
        String promoDesc;
        assertTrue("Offer applied title is not displayed", Elements.elementPresent("guest_offers.offer_applied_title"));
        logger.info("offer applied title is displayed ");

        if (Elements.elementPresent("guest_offers.more_less_link")) {
            Clicks.click("guest_offers.more_less_link");
            promoDesc = Elements.getText("guest_offers.offer_applied_promoDesc").replace(".Less", "");
        } else {
            promoDesc = Elements.getText("guest_offers.offer_applied_promoDesc");
        }
        String bagId = Elements.findElement(By.id("bagId")).getAttribute("value").replace("-", "");
        JSONArray offerdetails = OffersService.getOfferDetails(bagId, null);

        assertTrue("Promo Description is not displayed same as in service", promoDesc.equals(offerdetails.getJSONObject(0).get("desc")));
        logger.info("Verified: Expected Promo description is displayed on offer applied section on Shopping Bag page.");

        assertTrue("Saved amount is not displayed on offer applied section", Elements.elementPresent("guest_offers.offer_applied_promo_discount_amount"));
        logger.info("Saved amount is displayed on offer applied section");

        assertTrue("Remove link is not displayed successfully", Elements.elementPresent("guest_offers.remove_link"));
        logger.info("Remove link is displayed successfully");

    }

    @When("^I remove applied promotion$")
    public void iRemoveAppliedPromotion() throws Throwable {
        Clicks.click("guest_offers.remove_link");
    }

    @Then("^I verify offer removed successfully$")
    public void iVerifyOfferRemovedSuccessfully() throws Throwable {
        assertTrue("Offer is not removed", Elements.elementPresent("guest_offers.promo_code_text_box"));
        logger.info("Offer is removed successfully");
    }

    @And("^I verify eligible offer details$")
    public void iVerifyEligibleOfferDetails() throws Throwable {
        String promoDesc;
        if (Elements.elementPresent("guest_offers.more_less_link")) {
            Clicks.click("guest_offers.more_less_link");
            promoDesc = Elements.getText("guest_offers.promo_description").replace(".Less", "");
        } else {
            promoDesc = Elements.getText("guest_offers.promo_description");
        }

        String bagId = Elements.findElement(By.id("bagId")).getAttribute("value").replace("-", "");
        JSONArray offerdetails = OffersService.getOfferDetails(bagId, null);
        Assert.assertTrue("Promo Description is not displayed same as in service", promoDesc.equals(offerdetails.getJSONObject(0).get("desc")));

        assertTrue("", Elements.elementPresent("guest_offers.promo_discount_amount"));
        logger.info("Saved price is displayed on eligible offer section");

        assertTrue("", Elements.elementPresent("guest_offers.promo_validity"));
        logger.info("promo validity is displayed");

        assertTrue("", Elements.elementPresent("guest_offers.exclusions_details"));
        logger.info("Exclusioins & Details link is displayed");
    }

    @When("^I sign in from eligible offers section$")
    public void iSignInFromEligibleOffersSection() throws Throwable {
        Clicks.click("guest_offers.sign_in_link");
    }

    @And("^I sign in with existing profile$")
    public void iSignInWithExistingProfile() throws Throwable {
        StepUtils.shouldBeOnPage("sign_in");
        logger.info("Performed Action : Navigated to sign in page");
        Elements.findElement("guest_offers.email").sendKeys(TestUsers.currentEmail);
        Elements.findElement("guest_offers.password").sendKeys(TestUsers.currentPassword);
    }

    @And("^I should not see sign in link eligible offers section$")
    public void iShouldNotSeeSignInLinkEligibleOffersSection() throws Throwable {
        assertFalse("Sign in link is still displayed", Elements.elementPresent("guest_offers.sign_in_link"));
        logger.info("Sign in link is not displayed");
    }

    @And("^I add product to bag$")
    public void iAddProductToBag() throws Throwable {
        Wait.untilElementPresent("guest_offers.add_to_bag");
        Clicks.click("guest_offers.add_to_button");
        Wait.forPageReady();
        Wait.untilElementPresent("guest_offers.checkout_button");
        Clicks.click("guest_offers.checkout_button");
        Wait.forPageReady();
    }

    @When("^I enter \"([^\"]*)\" promocode manually$")
    public void iEnterPromocodeManually(String promo_code) throws Throwable {
        TextBoxes.typeTextNEnter("guest_offers.promo_code_text_box", promo_code);
        Clicks.click("guest_offers.apply_button");
    }

    @Then("^I should see following validation message$")
    public void iShouldSeeFollowingValidationMessage(List<String> expectedMessage) throws Throwable {
        assertTrue("Expected Error message is not displayed", Elements.findElement("guest_offers.error_message").equals(expectedMessage.get(0)));
        logger.info("Expected error message is displayed ");
    }

    @And("^I sign out$")
    public void iSignOut() throws Throwable {
        Wait.forPageReady();
        Clicks.click("guest_offers.sign_out");
    }

    @When("^I select see all offers$")
    public void iSelectSeeAllOffers() throws Throwable {
        Clicks.click("guest_offers.see_all_offers");
    }

    @And("^I select \"([^\"]*)\" quantity$")
    public void iSelectQuantity(String value) throws Throwable {
        DropDowns.selectByValue("guest_offers.product_quantity", value);
    }

    @And("^I should see following text for \"([^\"]*)\" section$")
    public void iShouldSeeFollowingTextForSection(String type, List<String> copy_text) throws Throwable {
        switch (type) {
            case "guest offers":
                Assert.assertTrue("Title copy text is not displayed", Elements.getText("guest_offers.promo_title_copy").trim().equals(copy_text.get(0)));
            case "no offers":
                Assert.assertTrue("Title copy text is not displayed", Elements.getText("guest_offers.promo_title_copy").trim().equals(copy_text.get(0)));
        }
    }

    @And("^I should see promo code field with apply button$")
    public void iShouldSeePromoCodeFieldWithApplyButton() throws Throwable {
        assertTrue("Promo code text box is not displayed", Elements.elementPresent("guest_offers.promo_code_text_box"));
        logger.info("Promo code text box is displayed");
        assertTrue("Apply button is not displayed", Elements.elementPresent("guest_offers.apply_button"));
        logger.info("Apply button is displayed");
    }

    @And("^I should not see eligible offers$")
    public void iShouldNotSeeEligibleOffers() throws Throwable {
        Assert.assertFalse("Eligible offers section is displayed", Elements.elementPresent("guest_offers.eligible_offer_panel"));
    }

    @Then("^I should see wallet icon$")
    public void iShouldSeeWalletIcon() throws Throwable {
        assertTrue("Wallet icon is not displayed", Elements.elementPresent("guest_offer.wallet_icon"));
        logger.info("Wallet icon is displayed successfully");
    }

    @And("^I verify see all offers link$")
    public void iVerifySeeAllOffersLink() throws Throwable {
        assertTrue("See All Offers link is not displayed", Elements.elementPresent("guest_offers.see_all_offers"));
        logger.info("See All Offers link is displayed successfully");
    }

    @Then("^I should see apply an offer overlay$")
    public void iShouldSeeApplyOffersOverlay() throws Throwable {
        assertTrue("Apply an offers overlay is not displayed", Elements.elementPresent("guest_offers.apply_offer_overlay"));
        logger.info("Apply an offer overlay is displayed successfully");
    }
}


