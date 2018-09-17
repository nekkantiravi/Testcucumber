package com.macys.sdt.shared.actions.website.mcom.pages.my_account;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.ChangePreferredStore;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

import static com.macys.sdt.framework.interactions.Elements.getText;

public class MyAccount extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(MyAccount.class);
    /**
     * Navigates to given page from my account pages using left navigation
     * <p>Page Options: <br>
     * "my account", "my profile", "my preferences", "my address book", "my wallet", "wish list", "order status", "furniture mattress status", "gift card balance"
     * </p>
     *
     * @param pageName target page name
     */
    public void navigateToLeftNavigationPage(String pageName) {
        String left_navigation_link = "navigation";
        switch (pageName.toLowerCase()) {
            case "my account":
                logger.info("Go to my account link");
                if(macys())
                    left_navigation_link += ".goto_my_account_head";
                else
                    left_navigation_link += ".goto_my_account";
                break;

            case "my profile":
                logger.info("Go to my profile link");
                left_navigation_link += ".goto_my_profile";
                break;
            case "my preferences":
                logger.info("Go to my preferences link");
                left_navigation_link += ".goto_my_preferences_link";
                break;
            case "my address book":
                logger.info("Go to my address book link");
                left_navigation_link += ".goto_my_address_book_link";
                break;
            case "my wallet":
                logger.info("Go to my wallet link");
                left_navigation_link += ".goto_my_wallet_link";
                break;
            case "wish list":
                logger.info("Go to my wish list link");
                left_navigation_link += ".goto_my_wish_list";
                break;
            case "order status":
                logger.info("Go to order status link");
                left_navigation_link += ".goto_order_status";
                break;
            case "furniture & mattress status":
                logger.info("Go to furniture & mattress status link");
                String closeWindow= left_navigation_link +".closePopUp";
                if (Elements.elementPresent(closeWindow))
                    Clicks.click(closeWindow);
                left_navigation_link += ".goto_furniture_mattress_status";
                break;
            case "gift card balance":
                logger.info("Go to gift card balance link");
                left_navigation_link += ".goto_gift_card_balance";
                break;
            case "my perks":
                left_navigation_link += ".goto_my_perks";
                break;
            case "my points":
                left_navigation_link += ".goto_my_points";
                break;
            case "faqs":
                left_navigation_link += ".goto_faq";
                break;
            case "my bloomingdale's credit card":
                left_navigation_link += ".goto_my_credit_card";
                break;
        }
        Wait.untilElementPresent(left_navigation_link);
        Clicks.click(left_navigation_link);
        Wait.forPageReady();
    }

    /**
     * Verifies browser is on given page
     * <p> Note: If  verify_page element is not defined for given page waits extra 5 seconds for page load </p>
     *
     * @param pageName Expected Page
     * @return true if on given page else false
     */
    public boolean navigatedToExpectedPage(String pageName) {
        String navigated_page_name;
        switch (pageName.toLowerCase()) {
            case "my wallet":
                navigated_page_name = macys() ? "oc_my_wallet" : "my_bwallet";
                break;
            case "furniture & mattress status":
                navigated_page_name = "furniture_mattress_status_page";
                break;
            case "create profile benefits":
                navigated_page_name = pageName.replaceAll(" ", "_");
                if(macys())
                Navigate.switchWindow(1);
                break;
            case "privacy practices":
                navigated_page_name = pageName.replaceAll(" ", "_");
                if(bloomingdales())
                    Navigate.switchWindow(1);
                break;
            case "my points":
                navigated_page_name = Elements.elementPresent("my_account.view_my_loyalllist_account") ?
                        "loyallist_account_summary" : "loyalty_association";
                break;
            case "my bloomingdale's credit card":
                navigated_page_name = "credit_service_gateway_signedin";
                break;
            case "my preferences":
                navigated_page_name = "my_preferences";
                break;
            case "faqs":
                logger.info("Go to FAQs link from left navigation");
                navigated_page_name = "faqs";
                break;
            default:
                navigated_page_name = pageName.replaceAll(" ", "_");
        }

            String verifyElementKey = navigated_page_name + ".verify_page";
            if (!Elements.getValues(verifyElementKey).isEmpty()) {
                Wait.secondsUntilElementPresent(navigated_page_name + ".verify_page", 30);
            } else {
                Utils.threadSleep(5000, "Navigated page is not loaded properly");
            }
            if(macys())
            {
                if(StringUtils.equalsIgnoreCase(navigated_page_name,"create_profile_benefits"))
                    Navigate.switchWindowClose();
                    return true;
            }else{
                if(StringUtils.equalsIgnoreCase(navigated_page_name,"privacy_practices")){
                    Navigate.switchWindowClose();
                    return true;
                }

            }

        return onPage(navigated_page_name.toLowerCase());
    }

    /**
     * Selects the security question and fills the answer
     */
    public void setSecurityQuestion() {
        Wait.forPageReady();
        try {
            Wait.untilElementPresent("sign_in.security_question");
        }catch(Exception e){
            System.out.println("Sign_in security page not shown");
        }
        if (Elements.elementPresent("sign_in.security_question")) {
            UserProfile customer = TestUsers.getCustomer(null);
            DropDowns.selectByText("sign_in.security_question", customer.getUser().getUserPasswordHint().getQuestion());
            TextBoxes.typeTextbox("sign_in.security_answer", customer.getUser().getUserPasswordHint().getAnswer());
            Clicks.click("sign_in.save_and_continue");
            Wait.forPageReady();
            logger.info("Set security question to user");
        }
    }

    /**
     * Sets the prefered store
     *
     * @param zipcode zip code location
     */
    public static void setPreferredStore(String zipcode) {
        Wait.untilElementPresent("my_account.change_locations");
        Clicks.click(Elements.element("my_account.change_locations"));
        ChangePreferredStore.setPreferredStore(zipcode);
    }

    /**
     * Navigates to given page from my account pages using left navigation
     * <p>Page Options: <br>
     * "Cardholder Benefits", "Learn More &amp; Apply", "My Macy's Credit Card", "my plenti"
     * </p>
     *
     * @param pageName target page name
     */
    public void navigateToOtherLeftNavigationPage(String pageName) {
        String left_navigation_link = "navigation";
        switch (pageName.toLowerCase()) {
             case "credit service gateway signedin":
                 logger.info("Go to My macys credit card link from left navigation");
                left_navigation_link += ".goto_my_credit_card";
                break;
            case "credit benefits":
                logger.info("Go to card holder benefits link from left navigation");
                left_navigation_link += ".goto_cardholder_benefits";
                break;
            case "credit service marketing":
                logger.info("Go to Learn more & Apply  link from left navigation");
                left_navigation_link += ".goto_apply_and_learn_more";
                break;
            case "my plenti":
                logger.info("Go to My Plenti link from left navigation");
                left_navigation_link += ".goto_my_plenti";
                break;
            case "loyalty association":
                logger.info("Go to My Points  link from left navigation");
                left_navigation_link += ".goto_my_loyallist";
                break;
            case "reward card balance":
                logger.info("Go to My Reward card balance link from left navigation");
                left_navigation_link += ".goto_reward_card_balance";
                break;
            case "my offers":
                logger.info("Go to Bonus offers link from left navigation");
                left_navigation_link += ".goto_bonus_offers";
                break;
            case "loyalty benefits":
                logger.info("Go to My Perks link from left navigation");
                left_navigation_link += ".goto_my_perks";
                break;
            case "faqs":
                logger.info("Go to FAQs link from left navigation");
                left_navigation_link += ".goto_faq";
                break;
        }
        logger.info(left_navigation_link);
        Wait.untilElementPresent(left_navigation_link);
        Clicks.click(left_navigation_link);
    }

    public static List<Product> tux_productDetailsODPage() {
        List<Product> productDetails = new ArrayList<>();
        Wait.forPageReady();
        List<WebElement> orderContainers = Elements.findElements(Elements.element("order_details.order_details_header"));
        Product p = null;
        for (int i = 0; i < orderContainers.size(); i++) {
            if (Elements.findElements("order_details.item_names").get(i).getText().contains("Tuxedo")) {
                p.reservation_id = Long.parseLong(Elements.findElement("order_details.tux_reservation_number").getText());
                p.quantity = Integer.parseInt(Elements.findElement("order_details.tux_quantity").getText());
            } else {
                Assert.fail("Tuxedo items are not displayed");
            }

        }

        return productDetails;
    }

    public void navigateToSignInPageLinks(String pageName) {
        String signIn_link = "sign_in";
        switch (pageName.toLowerCase()) {
            case "forgot password":
                logger.info("Go to forgot password link");
                signIn_link += ".forgot_password_link";
                break;
            case "looking for gift registry":
                logger.info("Go to looking for gift registry link");
                signIn_link += ".looking_for_gift_registry";
                break;
            case "create profile benefits":
                logger.info("Go to create profile benefits link");
                signIn_link += ".create_profile_benefits_new";
                break;
            case "privacy practices":
                logger.info("Go to Privacy Practices  link");
                signIn_link += ".privacy_policy";
                break;

        }
        Wait.untilElementPresent(signIn_link);
        Clicks.click(signIn_link);
    }
    /**
     * Gets a random valid credit card from "valid_cards.json"
     * This is overried method, it will return CC test data which is not depended on "3d_secure": "true" in "valid_cards.json"
     * Amol Ray
     * @return JSONObject containing credit card information
     */
    public static CreditCard getValidCreditCard(String cardType) {
        return CreditCards.getValidCards().stream().
                filter(card -> card.getCardType().name.equals(cardType) && !card.has3DSecure()).findFirst().
                orElseThrow(() -> new AssertionError("no valid cards of type "+cardType+" without 3Dsecure found"));
    }

    @Then("^I verify UI changes on account sign in page$")
    public void iVerifyUIChangesOnAccountSignInPages() {
        Assert.assertTrue("Sign in text is not present in Sign in UI page", getText("sign_in.Sign_in_text").equals("Sign in"));
        Assert.assertTrue("Have a Macy's account? text is not present in Sign in page", getText("sign_in.have_macys_account_text").equals("Have a Macy's account?"));
        Assert.assertTrue("If youStory B-79039  already created a Macy's account text is not present in Sign in page", getText("sign_in.have_macys_account_info_text").equals("If you already created a Macy's account, you can sign in here."));
        Assert.assertTrue("Email address label text is not present in Sign in page", getText("sign_in.email_address_label").equals("Email address"));
        Assert.assertTrue("Password label text is not present in Sign in page", getText("sign_in.password_label").equals("Password"));
        Assert.assertTrue("Password is case sensitive. text is not present in Sign in page", getText("sign_in.password_info_text").equals("Password is case sensitive."));
        Assert.assertTrue("dont have macys account? text is not present in Sign in page", getText("sign_in.dont_have_macys_account_text").equals("Don't have a Macy's account?"));
        Assert.assertTrue("Create an account now to take advantage text is not present in Sign in page", getText("sign_in.dont_have_macys_account_info_text").contains("Create an account now to take advantage of express checkout and other convenient features."));
        Assert.assertTrue("See All The Benefits Of Creating An Account text is not present in Sign in page", getText("sign_in.see_all_benefits_Link").equals("See All The Benefits Of Creating An Account"));
        Assert.assertTrue("Privacy Practices text is not present in Sign in page", getText("sign_in.privacy_practices").equals("Privacy Practices"));
        Assert.assertTrue("Keep in mind a few basic password rules: text is not present in Sign in page", getText("sign_in.keep_in_mind_text").equals("Keep in mind a few basic password rules:"));
        logger.info("UI changes verification success on account Sign in page");
    }

    @Then("^I should be able to visit all of the sections under My Account$")
    public void iShouldBeAbleToVisitAllOfTheSectionsUnderMyAccount() throws Throwable {
        Wait.forPageReady();
        new MyAccountSteps().iNavigateToMyWalletPageFromMyAccountPage();
        Wait.untilElementPresent("navigation.goto_my_address_book_link");
        Clicks.click("navigation.goto_my_address_book_link");
        Wait.untilElementPresent("navigation.goto_my_profile");
        Clicks.click("navigation.goto_my_profile");
        Wait.untilElementPresent("navigation.goto_my_wish_list");
        Clicks.click("navigation.goto_my_wish_list");
        Wait.forPageReady();
        Clicks.click("navigation.goto_my_account");
        Clicks.hoverForSelection("navigation.goto_my_account");
        Wait.untilElementPresent("navigation.goto_my_credit_card");
        Clicks.click("navigation.goto_my_credit_card");
        Wait.forPageReady();
        Wait.untilElementPresent("navigation.goto_orders");
        Clicks.click("navigation.goto_orders");
        if(macys())
         {
             Wait.untilElementPresent("navigation.goto_my_preferences_link");
             Clicks.click("navigation.goto_my_preferences_link");
             if (Elements.elementPresent("navigation.closePopUp"))
                 Clicks.click("navigation.closePopUp");
             Wait.untilElementPresent("navigation.goto_furniture_mattress_status");
             Clicks.click("navigation.goto_furniture_mattress_status");
             shouldBeOnPage("furniture_mattress_status_page");
         }
        else
        {
            Wait.untilElementPresent("my_account.view_my_loyalllist_account");
            Clicks.click("my_account.view_my_loyalllist_account");
            Wait.untilElementPresent("navigation.goto_my_profile");
            Clicks.click("navigation.goto_my_profile");
            shouldBeOnPage("my_profile");
        }
    }

}
