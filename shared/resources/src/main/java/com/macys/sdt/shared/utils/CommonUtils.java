package com.macys.sdt.shared.utils;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.exceptions.ProductionException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.CreditCard;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.model.addresses.Address;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.model.user.LoginCredentials;
import com.macys.sdt.framework.model.user.User;
import com.macys.sdt.framework.model.user.UserPasswordHint;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.AbbreviationHelper;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBConnection;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.shared.actions.MEW.pages.CreateProfileMEW;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAccount;
import javassist.bytecode.stackmap.BasicBlock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.interactions.Navigate.switchWindow;
import static com.macys.sdt.framework.utils.TestUsers.getRandomProduct;
import static com.macys.sdt.framework.utils.TestUsers.getValidVisaCreditCard;
import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public abstract class CommonUtils extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
    public static boolean isNewProfileCreated = false;

    public static void navigateDirectlyToProduct(String productID) {
        String product_url = RunConfig.url + "/shop/product/?ID=" + productID;
        Navigate.visit(product_url);
        closePopup();
    }

    public static void navigateDirectlyToProduct(int id) {
        navigateDirectlyToProduct("" + id);
    }

    public static void selectRandomProduct(boolean hasRating, boolean master) {
        boolean done = false;
        int count = 0;
        String error = null;
        while (!done && ++count < 3) {
            try {
                if (onPage("category_browse") || onPage("search_result")
                        || ((StepUtils.edge() || StepUtils.ie() || StepUtils.safari()) && WebDriverManager.getWebDriver().getCurrentUrl().contains("/shop/"))
                        || WebDriverManager.getWebDriver().getCurrentUrl().contains("/chanel-")) {
                    if (hasRating) {
                        selectMasterMemberProduct("search_result.product_thumbnail_with_review", master);
                    } else {
                        selectMasterMemberProduct("search_result.product_thumbnail_link", master);
                    }
                } else if (onPage("product_display")) {
                    if (!master) {
                        Clicks.clickRandomElement("product_display.member_product_link");
                    }
                } else {
                    Assert.fail("ERROR - DATA: not on a page with products to select");
                }
                done = true;
            } catch (Exception e) {
                //try again!
                logger.info("select random product - retrying: " + count);
                Utils.threadSleep(1000, null);
                error = e.toString();
            }
        }
        if (!done) {
            Assert.fail("ERROR - DATA: Could not select product - " + error);
        }
    }

    public static void quickViewRandomProduct(boolean hasRating, boolean master) {
        boolean clickable = false;
        int count = 0;
        String error = null;
        while (!clickable && ++count < 3) {
            try {
                if (onPage("category_browse") || onPage("search_result")) {
                    if (hasRating) {
                        quickViewMasterMemberProduct("search_result.product_thumbnail_with_review", master);
                    } else {
                        quickViewMasterMemberProduct("search_result.product_thumbnail_link", master);
                    }
                } else if (onPage("product_display")) {
                    quickViewMasterMemberProduct("recommendations.recommended_products", master);
                } else {
                    Assert.fail("not on a page with products to select");
                }
                clickable = true;
            } catch (Exception e) {
                //try again!
                Navigate.browserRefresh();
                logger.info("ShopAndBrowse.selectRandomProduct(): retrying:" + count);
                Utils.threadSleep(1000, null);
                error = e.toString();
            }
        }
        if (!clickable) {
            Assert.fail("Could not find clickable product - " + error);
        }
    }

    public static void quickViewMasterMemberProduct(String el, boolean master) {
        boolean found = false;
        List<WebElement> elements = Elements.findElements(el, element -> {
            WebElement price = element.findElement(By.xpath("../..")).findElement(By.cssSelector("div.prices"));
            return master && price.getText().contains(" - ");
        });
        WebElement selected = elements.get(new Random().nextInt(elements.size()));
        Clicks.hoverForSelection(selected);
        if (macys()) {
            if (Elements.elementPresent("search_result.product_thumbnail_quickview_tablet")) {
                Clicks.clickRandomElement("search_result.product_thumbnail_quickview_tablet");
            } else {
                WebElement thumbnail = Elements.getRandomElement("search_result.product_thumbnail");
                Clicks.hoverForSelection(thumbnail);
                if (!Wait.untilElementPresent("search_result.product_thumbnail_quickview")) {
                    Clicks.click(thumbnail);
                }
                Clicks.click("search_result.product_thumbnail_quickview");
                Clicks.clickIfPresent("quick_view.survey_close_button");
                Wait.untilElementPresent("quick_view.quick_view_product_dialog");
            }
            if (Elements.elementPresent("quick_view.product_error")) {
                Clicks.click("quick_view.quick_view_close_dialog");
            } else {
                found = true;
            }
        } else {
            Clicks.click("search_result.product_thumbnail_quickview");
            if (Elements.elementPresent("quick_view.product_error")) {
                Clicks.click("quick_view.quick_view_close_dialog");
            } else {
                found = true;
            }
        }
        if (!found) {
            Assert.fail("Unable to find " + (master ? "master" : "member") + " product on current page");
        }
    }

    public static void selectRandomProductMEW(boolean hasRating, boolean master) {
        boolean clickable = false;
        int count = 0;
        String error = null;
        boolean searchResults = Elements.elementPresent("search_result.search_results_thumbnail_wrapper");
        while (!clickable && count++ < 5) {
            try {
                selectMasterMemberProduct((searchResults ? "search_result" : "category_browse") // page name
                        + "." + "product_thumbnail_" + (hasRating ? "with_review" : "link"), master); // element name
                clickable = true;
                Navigate.browserRefresh();
                Wait.forPageReady();
            } catch (Exception e) {
                // check if it worked
                if (onPage("product_display", "registry_pdp")) {
                    return;
                }

                // try again!
                Navigate.browserRefresh();
                logger.info("ShopAndBrowse.selectRandomProductMEW(): retrying:" + count);
                if (searchResults) {
                    Wait.untilElementPresent("search_result.product_thumbnail_link");
                } else {
                    Wait.untilElementPresent("category_browse.product_thumbnail_link");
                }
                error = e.toString();
            }
        }
        if (!clickable) {
            Assert.fail("ERROR - DATA: Could not find clickable product - " + error);
        }
    }

    public static void selectMasterMemberProduct(String el, boolean master) {
        if (master) {
            Clicks.clickRandomElement(el, element -> {
                WebElement parent;
                WebElement price = null;
                if (MEW()) {
                    parent = element.findElement(By.xpath(".."));
                    try {
                        if (Elements.elementPresent("category_browse.browse_thumbnail_wrapper")) {
                            price = parent.findElement(Elements.element("category_browse.price"));
                        } else if (Elements.elementPresent("search_result.search_results_thumbnail_wrapper")) {
                            price = parent.findElement(Elements.element("search_result.price"));
                        }
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        return true;
                    }
                } else {
                    parent = element.findElement(By.xpath("../.."));
                    price = parent.findElement(By.cssSelector("div.prices"));
                }
                return price != null && price.getText().contains(" - ");
            });
        } else {
            try {
                pausePageHangWatchDog();
                String element = "product_thumbnails.thumbnail_wrapper";
                Wait.untilElementPresent(element);
                List<WebElement> productEles = Elements.findElements(element).stream().filter(f -> !f.getAttribute("class").contains("thumbnailGridMedia") && !f.getText().contains(" - ")).collect(Collectors.toList());
                if (productEles.isEmpty()) {
                    element = onPage("category_browse") ? "category_browse.browse_thumbnail_wrapper" : "search_result.search_results_thumbnail_wrapper";
                    productEles = Elements.findElements(element).stream().filter(f -> !f.getAttribute("class").contains("thumbnailGridMedia") && !f.getText().contains(" - ")).collect(Collectors.toList());
                }
                logger.info("Thumbnail Grid URL with product count(" + productEles.size() + "):" + WebDriverManager.getCurrentUrl());
                if (!productEles.isEmpty()) {
                    WebElement productEle = productEles.size() == 1 ? productEles.get(0) : (productEles.get(new Random().nextInt(productEles.size() - 1)));
                    productEle = productEle.findElement(By.tagName("a"));
                    if (safari()) {
                        Clicks.javascriptHover(productEle);
                        Clicks.javascriptClick(productEle);
                    } else {
                        Clicks.javascriptClick(productEle);
                        Wait.forPageReady("product_display");
                    }
                    logger.info("Selected PDP URL:" + WebDriverManager.getWebDriver().getCurrentUrl());
                }
                resumePageHangWatchDog();
            } catch (StaleElementReferenceException se) {
                Clicks.clickRandomElement(el, element -> MEW() && !(element.findElement(By.cssSelector("//*[@class=\"m-product-grid-list-item \"]/a/div[2]/div[5]/ul/li/span/span")).getText().contains(" - ")));
            } catch (org.openqa.selenium.interactions.MoveTargetOutOfBoundsException e) {
                // Sometimes thumbnail images are not clickable. Click product description to navigate to PDP.
                Clicks.clickRandomElement("product_thumbnails.product_short_description");
            } catch (Exception e) {
                Clicks.clickRandomElement(el, element -> MEW() || !(element.findElement(By.xpath("../..")).findElement(By.cssSelector("div.prices")).getText().contains(" - ")));
            }
            /*if (MEW()) {
                browserRefresh();
            }*/
        }
        // If we were looking for member but found master, navigate to one of the member products it contains.
        if (!master && isMasterProduct()) {
            Clicks.clickRandomElement("product_display.member_product_link");
            Clicks.click("quick_view.quick_view_close_dialog");
        }
    }

    /**
     * check if product is master or not
     *
     * @return true if product is master
     */
    public static boolean isMasterProduct() {
        return onPage("product_display", "registry_pdp") && Elements.elementPresent("product_display.member_product_list");
    }

    public static UserProfile signInOrCreateAccount() {
        return signInOrCreateAccount(false);
    }

    public static void signIn() throws Exception{
        customerSignIn();
        boolean flag = Elements.elementPresent("profile.session_expired");
        if(flag) {
            String notificationMessage =  Elements.findElement(Elements.element("profile.session_expired")).getText();
            if(notificationMessage.contains("Your session has expired")) {
                Wait.forPageReady();
                WebDriverManager.getWebDriver().navigate().refresh();
                Wait.forPageReady();
                customerSignIn();
            }

        }
    }

    private static void customerSignIn() throws Exception{
        UserProfile customer = TestUsers.getCustomer(null);
        TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        Clicks.click("sign_in.sign_in_button");
        Clicks.clickIfPresent("sign_in.close_overlay");
        Thread.sleep(2000);
    }


    /**
     * sign in to a non-registry account or create one if account doesn't exist
     *
     * @return UserProfile with details about the signed in user
     */
    public static UserProfile signInOrCreateAccount(boolean menuSignIn) {
        UserProfile customer = null;
        if (MEW()) {
            GlobalNav.openGlobalNav();
            if (menuSignIn) {
                GlobalNav.navigateOnGnByName("Sign In");
            } else {
                GlobalNav.navigateOnGnByName("My Account or SIGN IN / SIGN UP");
            }
            GlobalNav.closeGlobalNav();

        } else {
            String elementName = "home." + (macys() ? "goto_sign_in_link" : "goto_my_account_link");
            Wait.untilElementPresent(elementName);
            Clicks.click(elementName);
        }
        if (macys()) {
            closeIECertError();
        }
      /*  if (prodEnv()) {
            try {
                customer = TestUsers.getProdCustomer();
                TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
                TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
                Clicks.click("sign_in.verify_page");
            } catch (UserException e) {
                Assert.fail(e.getMessage());
            }
            Assert.assertFalse("Prod account not recognized", Elements.elementPresent("sign_in.error_message"));
        } else {*/
        if (!onPage("my_account") || !signedIn()) {
            customer = TestUsers.getCustomer(null);
            pausePageHangWatchDog();
            if (prodEnv()) {
                customer.getUser().getProfileAddress().setEmail("prodSanity_" + TestUsers.generateRandomEmail(16));
                logger.info("new email for production: " + customer.getUser().getProfileAddress().getEmail());
                if (macys() && Elements.elementPresent("sign_in.sign_up_popup")) {
                    Clicks.click("sign_in.sign_up_popup");
                }
            }
            TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
             Clicks.click("sign_in.sign_in_button");
//            Clicks.javascriptClick("sign_in.sign_in_button");
            Clicks.clickIfPresent("sign_in.close_overlay");
            resumePageHangWatchDog();
            // if this account works, we're good to go
            if (!Wait.secondsUntilElementPresent("sign_in.error_message", safari() || ie() ? 40 : 20)) {
                new MyAccount().setSecurityQuestion();
                if (!MEW()) {
                    Wait.secondsUntilElementPresent("header.goto_my_account_link", 20);
                    Clicks.click("header.goto_my_account_link");
                    //yc03673 2017-06-19 Fixed the issue with goto_my_account_second_link locator and below code works fine from all signed in pages now
                    //yc03673 2017-06-08 Added step to click the My Account link after expanding My Account list
                    if (macys()) {
                        Clicks.clickIfPresent("header.goto_my_account_second_link");
                    }
                }
                shouldBeOnPage("my_account");
                isNewProfileCreated = false;
                logger.info("No new profile created");
                return null;
            }
            if (MEW()) {
                CreateProfileMEW.createProfile(customer);
                Assert.assertTrue("New Profile is not created", Wait.until(() -> onPage("my_account"), 10));
                isNewProfileCreated = true;
                logger.info("New profile created in mew");
            } else {
                CreateProfile.createProfile(customer);
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("my_account.add_card_overlay_no_thanks_button", 20);
                Assert.assertTrue("New Profile could not be created", onPage("my_account") || onPage("my_profile"));
                Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
                isNewProfileCreated = true;
                logger.info("New profile created in desktop");
            }
            // }
        }
        return customer;
    }

    /**
     * navigate to random product using sub category
     */
    public static void navigateToRandomProduct() {
        Home.selectRandomSubCategory();
        selectRandomProduct(false, false);
    }

    /**
     * scroll down page (using js) when sidebar present
     */
    public static void scrollDownPageWhenSidebarPresent() {
        if (Elements.elementPresent("home.sidebar_iframe")) {
            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
        } else {
            logger.info("sidebar absent");
        }
    }

    /**
     * add credit card details to account
     *
     * @param creditCard credit card details
     * @param customer   customer details
     */
    public static void addCreditCardFromBWallet(CreditCard creditCard, UserProfile customer) {
        String cardType;
        if (creditCard == null) {
            creditCard = getValidVisaCreditCard();
        }
        if (customer == null) {
            customer = TestUsers.getCustomer(null);
        }
        // 2017-11-20 Ravi: Removed hardcoded month and year and replaced with the creditCard object values
//            int year = 5;
//            int month = 4;


        if (MEW()) {    //for mew
            Wait.untilElementPresent("oc_my_wallet.add_credit_card");
            try {
                Elements.elementInView("oc_my_wallet.add_credit_card");
                Clicks.click("oc_my_wallet.add_credit_card");
            } catch (Exception | Error e) {
                Navigate.browserRefresh();
                Utils.threadSleep(2000, null);
                Wait.untilElementPresent("oc_my_wallet.add_credit_card");
                Clicks.click("oc_my_wallet.add_credit_card");
            }
            Wait.untilElementPresent("credit_card.card_type");
            cardType = creditCard.getCardType().name.equals("Employee Card") ? "Macy's Employee Card" : creditCard.getCardType().name;
            DropDowns.selectByText("credit_card.card_type", cardType);
            TextBoxes.typeTextbox("credit_card.card_number", creditCard.getCardNumber());
            if (!(creditCard.getCardType().name.equalsIgnoreCase("Macy's") || creditCard.getCardType().name.equalsIgnoreCase("Employee Card"))) {
//                DropDowns.selectByIndex("credit_card.expiry_month", month);
//                DropDowns.selectByIndex("credit_card.expiry_year", year);
                DropDowns.selectByText("credit_card.expiry_month", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
                DropDowns.selectByText("credit_card.expiry_year", creditCard.getExpiryYear());
            }
            TextBoxes.typeTextbox("credit_card.first_name", customer.getUser().getProfileAddress().getFirstName());
            TextBoxes.typeTextbox("credit_card.last_name", customer.getUser().getProfileAddress().getLastName());
            TextBoxes.typeTextbox("credit_card.address_line_1", customer.getUser().getProfileAddress().getAddressLine1());
            TextBoxes.typeTextbox("credit_card.address_line_2", customer.getUser().getProfileAddress().getAddressLine2() == null ? "" : customer.getUser().getProfileAddress().getAddressLine2());
            TextBoxes.typeTextbox("credit_card.address_city", customer.getUser().getProfileAddress().getCity());
            DropDowns.selectByValue("credit_card.address_state", AbbreviationHelper.getStateAbbreviation(customer.getUser().getProfileAddress().getState()));
            TextBoxes.typeTextbox("credit_card.address_zip_code", String.valueOf(customer.getUser().getProfileAddress().getZipCode()));
            if (bloomingdales() || onPage("shipping_payment_signed_in")) {
                TextBoxes.typeTextbox("credit_card.card_phone_area_code", customer.getUser().getProfileAddress().getPhoneAreaCode());
                TextBoxes.typeTextbox("credit_card.card_phone_exchange", customer.getUser().getProfileAddress().getPhoneExchange());
                TextBoxes.typeTextbox("credit_card.card_phone_subscriber", customer.getUser().getProfileAddress().getPhoneSubscriber());
            } else {
                TextBoxes.typeTextbox("credit_card.phone_number", customer.getUser().getProfileAddress().getBestPhone());
            }
            TextBoxes.typeTextbox("credit_card.payment_email", customer.getUser().getProfileAddress().getEmail());
            Clicks.click("credit_card.save_card");
            Assert.assertTrue("ERROR-ENV: Unable to add a credit card successfully", Wait.untilElementPresent("oc_my_wallet.credit_cards"));
        } else {    // for desktop
            Clicks.click("my_bwallet.add_credit_card_btn");
            if (!Elements.elementPresent("credit_card_dialog.card_number")) {
                Assert.fail("Add credit card dialog did not appear");
            }

            if (macys()) {
                DropDowns.selectByText("credit_card_dialog.card_type", creditCard.getCardType().name);
//                DropDowns.selectByIndex("credit_card_dialog.expiry_month", month);
//                DropDowns.selectByIndex("credit_card_dialog.expiry_year", year);
                DropDowns.selectByText("credit_card.expiry_month", creditCard.getExpiryMonthIndex() + " - " + creditCard.getExpiryMonth());
                DropDowns.selectByText("credit_card.expiry_year", creditCard.getExpiryYear());
                DropDowns.selectByText("credit_card_dialog.address_state", customer.getUser().getProfileAddress().getState());
            } else {
                DropDowns.selectCustomText("credit_card_dialog.card_type", "credit_card_dialog.card_type_options", creditCard.getCardType().name);
                DropDowns.selectCustomText("credit_card_dialog.address_state", "credit_card_dialog.address_state_options", customer.getUser().getProfileAddress().getState());
//                DropDowns.selectCustomValue("credit_card_dialog.expiry_month", "credit_card_dialog.expiry_month_options", month);
                DropDowns.selectCustomValue("credit_card_dialog.expiry_month", "credit_card_dialog.expiry_month_options", Integer.parseInt(creditCard.getExpiryMonthIndex()));
                DropDowns.selectCustomText("credit_card_dialog.expiry_year", "credit_card_dialog.expiry_year_options", creditCard.getExpiryYear());
            }
            TextBoxes.typeTextbox("credit_card_dialog.card_number", creditCard.getCardNumber());
            TextBoxes.typeTextbox("credit_card_dialog.first_name", customer.getUser().getProfileAddress().getFirstName());
            TextBoxes.typeTextbox("credit_card_dialog.last_name", customer.getUser().getProfileAddress().getLastName());
            TextBoxes.typeTextbox("credit_card_dialog.address_line_1", customer.getUser().getProfileAddress().getAddressLine1());
            TextBoxes.typeTextbox("credit_card_dialog.address_city", customer.getUser().getProfileAddress().getCity());
            TextBoxes.typeTextbox("credit_card_dialog.address_zip_code", String.valueOf(customer.getUser().getProfileAddress().getZipCode()));
            TextBoxes.typeTextbox("credit_card_dialog.payment_email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_area_code", customer.getUser().getProfileAddress().getPhoneAreaCode());
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_exchange", customer.getUser().getProfileAddress().getPhoneExchange());
            TextBoxes.typeTextbox("credit_card_dialog.card_phone_subscriber", customer.getUser().getProfileAddress().getPhoneSubscriber());
            // click fails if it's not visible beforehand
            if (!Elements.elementPresent("credit_card_dialog.save_card")) {
                Clicks.hoverForSelection("credit_card_dialog.save_card");
            }
            Clicks.click("credit_card_dialog.save_card");
        }
    }

    /**
     * Checks if the test failed due to product unavailability or other checkout related environment issues
     */
    public static void checkProductUnavailability() {
        if (onPage("responsive_checkout")) {
            if ((Elements.elementPresent("responsive_checkout.error_container")) && (Elements.elementPresent("responsive_checkout.item_level_error"))) {
                Assert.fail("ERROR - DATA: Unable to checkout your added product due to Product Unavailability Issue. " + Elements.getText("responsive_checkout.error_container"));
            }
            if (Elements.elementPresent("responsive_checkout.error_container")) {
                Assert.fail("ERROR - ENV: Unable to process your checkout. This may be due to an environment issue. " + Elements.getText("responsive_checkout.error_container"));
            }
        } else if (onPage("responsive_checkout_signed_in")) {
            if (Elements.elementPresent("responsive_checkout_signed_in.error_container") && Elements.elementPresent("responsive_checkout_signed_in.item_level_error")) {
                Assert.fail("ERROR - DATA: Unable to checkout your added product due to Product Unavailability Issue. " + Elements.getText("responsive_checkout_signed_in.error_container"));
            }
            if (Elements.elementPresent("responsive_checkout_signed_in.error_container")) {
                Assert.fail("ERROR - ENV: Unable to process your checkout. This may be due to an environment issue. " + Elements.getText("responsive_checkout_signed_in.error_container"));
            }
        } else if (onPage("shipping_payment_signed_in")) {
            if (Elements.elementPresent("shipping_payment_signed_in.error_container") && Elements.elementPresent("shipping_payment_signed_in.item_level_error")) {
                Assert.fail("ERROR - DATA: Unable to checkout your added product due to Product Unavailability Issue");
            }
            if (Elements.elementPresent("shipping_payment_signed_in.error_container")) {
                Assert.fail("ERROR - ENV: Unable to process your checkout. This may be due to an environment issue");
            }
        }
    }

    public static void closeUpdateBrowserPopup() {
        Clicks.clickIfPresent("home.updatebrowser_popup_close");
    }

    public static void closeStylistPopup() {
        if (Elements.elementPresent("home.sidebar_iframe")) {
            switchToFrame("home.sidebar_iframe");
            Clicks.clickIfPresent("home.minimize_connect_popup");
            Clicks.clickIfPresent("home.close_connect_popup");
            switchToFrame("default");
        }
    }

    /**
     * Method to return all contextual media information
     *
     * @return Contextual Media information
     */
    public static JSONObject getContextualizeMedia() {

        File queries = getResourceFile("contextualize_media.json");
        JSONObject jsonObject = null;

        try {
            String jsonTxt = Utils.readTextFile(queries);
            jsonObject = new JSONObject(jsonTxt);
        } catch (IOException | JSONException e) {
            logger.warn("error in fetching contextualize media due to : " + e.getMessage());
        }

        return jsonObject;
    }

    /**
     * Pulls attributes from an "and" separated list into a HashMap
     *
     * @param productTrue  List of attributes separated by "and"
     * @param productFalse List of attributes separated by "and"
     * @return HashMap containing values from true/false strings
     */
    public static HashMap<String, Boolean> extractOptions(String productTrue, String productFalse) {
        HashMap<String, Boolean> optionMap = new HashMap<>();
        if (productTrue != null) {
            for (String s : productTrue.split(" ")) {
                if (!s.equalsIgnoreCase("and")) {
                    optionMap.put(s, true);
                }
            }
        }
        if (productFalse != null) {
            for (String s : productFalse.split(" ")) {
                if (!s.equalsIgnoreCase("and")) {
                    optionMap.put(s, false);
                }
            }
        }
        return optionMap;
    }

    /**
     * Navigates to a random product having attirbutes in productTrue but not productFalse
     *
     * @param productTrue  List of attributes separated by "and"
     * @param productFalse List of attributes separated by "and"
     * @return The Product that was selected
     * @throws EnvException if not navigated to product display page
     */
    public static Product navigateToRandomProduct(String productTrue, String productFalse) throws EnvException {
        HashMap<String, Object> opts = new HashMap<>();
        opts.putAll(CommonUtils.extractOptions(productTrue, productFalse));
        pausePageHangWatchDog();
        Product p = getRandomProduct(opts);
        resumePageHangWatchDog();
        Assert.assertNotNull("ERROR - DATA:  No " + productTrue + " product" + (productFalse != null ? " that is not " + productFalse : "") + " was found in products list.", p);
        CommonUtils.navigateDirectlyToProduct(p.id);
        Assert.assertFalse("ERROR - DATA: This product is no longer available!!", (macys() && Elements.elementPresent(By.className("prodNotavailableMsg"))));
        Wait.secondsUntilElementPresent("product_display.verify_page", 30);
        shouldBeOnPage("product_display");
        return p;
    }

    /**
     * Close IE certificate error for ie version 10 and 11
     */
    public static void closeIECertError() {
        if (ie() && WebDriverManager.getCurrentTitle().contains("Certificate Error:")) {
            Navigate.execJavascript("javascript:document.getElementById('overridelink').click();");
        }
    }

    /**
     * Runs an action until either the action is successful or maxTries is reached.
     *
     * @param action   action to be performed (lambda with no args and boolean return value)
     * @param maxTries maximum number of tries - 0 for default (5)
     * @param message  error message to print on failure
     */
    public static void retryAction(BooleanSupplier action, int maxTries, String message) {
        maxTries = maxTries <= 0 ? 5 : maxTries;
        int i;
        for (i = 0; i < maxTries; i++) {
            if (action.getAsBoolean()) {
                break;
            }
        }
        if (i == maxTries) {
            Assert.fail(message != null ? message : "");
        }
    }

    /**
     * Checks for sort by options similar to the option you're looking for
     * <p>
     * Example1: Feature file has "Price: Low to High", where as in Website, it is listed as "Price: (low to high)"
     * Example2: Feature file has "Price: Customer Top Rated", where as in Website, it is listed as "Customers' Top Rated"
     * This method is to find the match with matching regex and returns boolean value based on the match found
     * </p>
     *
     * @param sortByOptions List of all available options
     * @param listOption    Option you're looking for
     * @return true if a similar option exists in the list
     */
    public static boolean matchSimilarSortBy(List<String> sortByOptions, String listOption) {
        for (String sortOption : sortByOptions) {
            // So far seen four different combinations for price and customer options
            if (listOption.startsWith("Price")) {
                Pattern pricePattern = Pattern.compile("(?i)(.*Low(.*?)High*.)|(.*High(.*?)Low*.)");
                Matcher priceMatcher = pricePattern.matcher(sortOption);
                if (priceMatcher.find()) {
                    return true;
                }
            } else if (listOption.startsWith("Customer")) {
                Pattern customerPattern = Pattern.compile("(?i)(.*Customer(.*?)Rated*.)");
                Matcher customerMatcher = customerPattern.matcher(sortOption);
                if (customerMatcher.find()) {
                    return true;
                }
            } else {
                Pattern optionPattern = Pattern.compile(listOption, Pattern.CASE_INSENSITIVE);
                Matcher optionMatcher = optionPattern.matcher(sortOption);
                if (optionMatcher.find()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes special characters from strings and normalizes dashes into spaces
     *
     * @param s String to sanitize
     * @return sanitized version of given string
     */
    public static String sanitizeString(String s) {
        if (s == null) {
            return "";
        }
        return s.replace(",.+'&!", "").replace('-', ' ').toLowerCase();
    }


    /**
     * Goto signIn Page
     */
    public static void gotoSignInPage() {
        if (MEW()) {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("My Account");
            GlobalNav.closeGlobalNav();

        } else {
            String elementName = "home." + (macys() ? "goto_sign_in_link" : "goto_my_account_link");
            Wait.untilElementPresent(elementName);
            Clicks.click(elementName);
        }
        if (macys()) {
            closeIECertError();
        }
    }

    public static void signInToCreatedProfile() {
       /* if (prodEnv()) {
            try {
                UserProfile customer = TestUsers.getProdCustomer();
                TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
                TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
                Clicks.click("sign_in.verify_page");
            } catch (exceptions.UserException e) {
                Assert.fail(e.getMessage());
            }
            Assert.assertFalse("Prod account not recognized", Elements.elementPresent("sign_in.error_message"));
        } else {*/
        UserProfile customer = TestUsers.getCustomer(null);
        pausePageHangWatchDog();
        TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
        TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        Clicks.click("sign_in.verify_page");
        resumePageHangWatchDog();
        new MyAccount().setSecurityQuestion();

    }
    //}

    public static void signInWithExistingProfileToProdServer(String validationType) {

        JSONObject prodUserData = getProdUser(validationType);
        if (prodUserData == null) {
            logger.error("No correct user found in sanity_production.json");
        }
        pausePageHangWatchDog();
        logger.info("email: " + prodUserData.getString("email"));
        logger.info("password: " + prodUserData.getString("password"));
        TextBoxes.typeTextbox("sign_in.email", prodUserData.getString("email"));
        TextBoxes.typeTextbox("sign_in.password", prodUserData.getString("password"));
        Wait.secondsUntilElementPresent("sign_in.verify_page", 20);
        Clicks.javascriptClick("sign_in.verify_page");
        // Clicks.click("sign_in.verify_page");
        resumePageHangWatchDog();
        new MyAccount().setSecurityQuestion();
        if (prodEnv())
            Wait.secondsUntilElementNotPresent("sign_in.email", 60);
    }

    /**
     * Edit address details
     */
    public static void editAddressDetails() throws IOException {
        logger.info("Edit shipping address!!");
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("my_address_book.edit_first_address", 30);
        Clicks.click("my_address_book.edit_first_address");
        String address = null;
        try {
            JSONArray orders = null;
            File returnsFile = getResourceFile("sanity_production.json");
            String jsonTxt = Utils.readTextFile(returnsFile);
            JSONObject json = new JSONObject(jsonTxt);
            orders = (JSONArray) json.get("prod_user");
            for (int i = 0; i < orders.length(); i++) {
                JSONObject order = orders.getJSONObject(i);
                System.out.println(order);
                if (order.get("address_line_1") != null) {
                    address = order.get("address_line_1").toString();
                    break;
                }
            }
        } catch (IOException | JSONException e) {
            logger.warn("error in getting sanity production data : " + e.getMessage());
        }
        pausePageHangWatchDog();
        TextBoxes.typeTextbox("my_address_book.address_line_1", address);
        Clicks.click("my_address_book.update_address");
    }

    /**
     * make second address primary
     */
    public static void makeSecondAddressPrimary() {
        logger.info("Make second address as primary!!");
        Clicks.click("my_address_book.make_primary");
    }

    /**
     * remove first address
     */
    public static void deleteOneAddress() {
        Clicks.click("my_address_book.remove_first_address");
    }

    /**
     * Delete credit card for mcom/bcom
     */
    public static void deleteCreditCard() {
        if (macys()) {
            Clicks.click("oc_my_wallet.edit_credit_cards");
            Clicks.clickWhenPresent("oc_my_wallet.delete_card");
            Clicks.clickWhenPresent("oc_my_wallet.confirmation_yes_button");
        } else {
            Clicks.click("my_bwallet.remove_card_link");
            Clicks.clickWhenPresent("my_bwallet.yes_delete_card");
        }
    }

    /**
     * Verifies the required page elements for a given page
     *
     * @param pageName     name of page to verify
     * @param pageElements elements to check in the page
     */
    public static void verifyPageElements(String pageName, List<String> pageElements) {
        for (String element : pageElements) {
            Assert.assertTrue(element + " not present on " + pageName + " page", Elements.elementPresent(pageName + "." + element));
        }
    }

    /**
     * Verifies the required page elements for a given page
     *
     * @param pageName     name of page to verify
     * @param pageElements elements to check for a given text
     */
    public static void verifyPageElementsWithText(String pageName, List<HashMap<String, String>> pageElements) {
        ArrayList<String> failedElements = new ArrayList<String>();
        for (Map elements : pageElements) {
            try {
                if (!Elements.elementPresent(pageName + "." + elements.get("element"))) {
                    failedElements.add(pageName + "." + elements.get("element"));
                }
            } catch (NoSuchElementException e) {
                logger.info(elements.get("element").toString() + "element not present");
            }
        }
        if (failedElements.size() > 0) {
            Assert.fail("Following Elements are not displayed in the " + pageName + ":" + failedElements.toString());
        }
    }

    public static String getUserDetailsByEmail(String email_id) {
        JSONObject sql = Utils.getSqlQueries();
        Connection c = new DBConnection().createConnection();
        String userId = "";
        String selectsqlone =
                sql.getJSONObject("user_service").getString("user_id_by_email");
        try {
            PreparedStatement p1 = c.prepareStatement(selectsqlone.replace("?", email_id));
            ResultSet rs1 = p1.executeQuery();
            while (rs1.next()) {
                userId = rs1.getString("USER_ID");
            }
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return userId;
    }

    public static void switchToPrintShippingLabel() {
        Clicks.click("order_details.print_mailing_label");
        switchWindow(1);
        shouldBeOnPage("return_confirmation");
    }

    /**
     * Update Return Status in DB
     *
     * @param returnStatus       value to change return status to
     * @param returnOrderDetails JSONObject with return order details
     */
    public static void updateReturnStatus(String returnStatus, JSONObject returnOrderDetails) throws SQLException {
        JSONObject queries = Utils.getSqlQueries();
        Connection connection = new DBConnection().createConnection();
        PreparedStatement preparedStatement;
        try {
            switch (returnStatus) {
                case "incomplete":
                    Statement statement = connection.createStatement();
                    String query = queries.getJSONObject("returns").get("update_return_shipment_submitted_ts").toString().replace("?", returnOrderDetails.getString("order_number"));
                    logger.info("Query=" + query);
                    statement.executeUpdate(query);
                    connection.commit();
                    statement.close();
                    break;
                case "complete":
                    preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("update_return_shipment_status_cd").toString());
                    preparedStatement.setString(1, "C");
                    preparedStatement.setString(2, returnOrderDetails.getString("order_number"));
                    preparedStatement.executeUpdate();
                    break;
            }
        } catch (SQLException | JSONException e) {
            logger.error("Unable to update return status: " + e.getMessage());
        }
    }

    public static String getStatusUpdatedDate(String returnStatus, String orderNumber) {
        String date = null;
        Connection connection = new DBConnection().createConnection();
        Map<String, String> statusCode = new HashMap<>();
        statusCode.put("RETURN STATUS Complete", "C");
        statusCode.put("RETURN STATUS Incomplete", "I");
        statusCode.put("RETURN STATUS Submitted", "S");
        statusCode.put("RETURN STATUS Intransit", "T");
        statusCode.put("RETURN STATUS Received", "D");
        JSONObject queries = Utils.getSqlQueries();
        logger.info("Query details:" + queries.getJSONObject("returns").get("get_return_shipment_details_for_status").toString());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("get_return_shipment_details_for_status").toString());
            preparedStatement.setString(1, orderNumber);
            preparedStatement.setString(2, statusCode.get(returnStatus));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DateFormat mcomFormat = new SimpleDateFormat("MM/dd/YYYY");
                DateFormat bcomFormat = new SimpleDateFormat("MMMM dd, YYYY");
                date = ((StepUtils.macys()) ? (mcomFormat.format(java.sql.Date.valueOf(resultSet.getString("status_updated_ts").split(" ")[0]))) : (bcomFormat.format(java.sql.Date.valueOf(resultSet.getString("status_updated_ts").split(" ")[0]))));
            }
            if (date == null) {
                Assert.fail("ERROR - ENV Database returned nil for order number #{order_number}");
            }
        } catch (SQLException | JSONException e) {
            logger.error("Unable to get date of status update for order " + orderNumber);
        }
        return date;
    }

    public static void deleteReturnRecord(String returnStatus, JSONObject returnOrderDetails) {
        try {
            Connection connection = new DBConnection().createConnection();
            JSONObject queries = Utils.getSqlQueries();
            Map<String, String> statusCode = new HashMap<>();
            statusCode.put("complete", "C");
            statusCode.put("incomplete", "I");
            statusCode.put("intransit", "T");
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("return_shipment_using_order_with_status").toString());
            preparedStatement.setString(1, returnOrderDetails.getString("order_number"));
            preparedStatement.setString(2, statusCode.get(returnStatus));
            ResultSet rs = preparedStatement.executeQuery();
            String returnShipmentId;
            if (rs.next()) {
                returnShipmentId = rs.getString("RETURN_SHIPMENT_ID");
                preparedStatement.executeUpdate(queries.getJSONObject("returns").get("delete_return_lineitem").toString().replace("?", returnShipmentId));
                preparedStatement.executeUpdate(queries.getJSONObject("returns").get("delete_return_shipment_label").toString().replace("?", returnShipmentId));
                preparedStatement.executeUpdate(queries.getJSONObject("returns").get("delete_return_pickup_info").toString().replace("?", returnShipmentId));
                preparedStatement.executeUpdate(queries.getJSONObject("returns").get("delete_return_shipment").toString().replace("?", returnShipmentId));
            }
        } catch (SQLException | JSONException e) {
            Assert.fail("delete return record failed : " + e.getMessage());
        }
    }

    public static void updateTrackingDate(int days, JSONObject returnOrderDetails) {
        Connection connection = new DBConnection().createConnection();
        JSONObject queries = Utils.getSqlQueries();
        try {
            Statement statement = connection.createStatement();
            String query = queries.getJSONObject("returns").get("update_track_my_return").toString().replace("?", returnOrderDetails.getString("order_number")).replace("dd", String.valueOf(days));
            logger.info("Query is " + query);
            statement.executeUpdate(query);

        } catch (SQLException | JSONException e) {
            logger.error("Unable to update return status: " + e.getMessage());
        }
    }

    /**
     * Delete return record from order table
     *
     * @param orderNumber order number to delete
     */
    public static void deleteReturnRecord(String orderNumber) {
        try {
            Connection connection = new DBConnection().createConnection();
            JSONObject queries = Utils.getSqlQueries();
            Statement statement = connection.createStatement();
            Statement statement1 = connection.createStatement();
            ResultSet rs = statement.executeQuery(queries.getJSONObject("returns").get("return_shipment_using_order").toString().replace("?", orderNumber));
            String returnShipmentId;
            while (rs.next()) {
                returnShipmentId = rs.getString("RETURN_SHIPMENT_ID");
                statement1.executeUpdate(queries.getJSONObject("returns").get("delete_return_lineitem").toString().replace("?", returnShipmentId));
                statement1.executeUpdate(queries.getJSONObject("returns").get("delete_return_shipment_label").toString().replace("?", returnShipmentId));
                statement1.executeUpdate(queries.getJSONObject("returns").get("delete_return_pickup_info").toString().replace("?", returnShipmentId));
                statement1.executeUpdate(queries.getJSONObject("returns").get("delete_return_shipment").toString().replace("?", returnShipmentId));
            }
            statement.close();
            statement1.close();
        } catch (SQLException | JSONException e) {
            Assert.fail("delete return record failed : " + e.getMessage());
        }
    }

    /**
     * getARandomUserCredentials is to get the random user name with password
     *
     * @return a map object with a random user name with password
     */
    public static Map<String, String> getARandomUserCredentials() {
        Map<String, String> userCredentials = new HashMap();
        JSONObject sql = Utils.getSqlQueries();
        Connection c = new DBConnection().createConnection();
        String userId = "";
        String selectsqlone =
                sql.getJSONObject("user_service").getString("get_a_random_user");
        try {
            PreparedStatement p1 = c.prepareStatement(selectsqlone);
            ResultSet rs1 = p1.executeQuery();
            while (rs1.next()) {
                userCredentials.put("user_name", rs1.getString("USER_NAME"));
                userCredentials.put("password", Utils.decryptPassword(rs1.getString("PASSWORD")));
            }
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return userCredentials;
    }

    public static void getRandomValidAddress(Address addressTemp) {
        if (addressTemp == null) {
            logger.error("Address cannot be null");
            return;
        }
        JSONObject address = null;
        try {
            File addressFile = getResourceFile("valid_addresses.json");
            String jsonTxt = Utils.readTextFile(addressFile);
            JSONObject json = new JSONObject(jsonTxt);
            JSONArray addresses = (JSONArray) json.get("addresses");
            Random rand = new Random();
            address = (JSONObject) addresses.get(rand.nextInt(addresses.length()));
            while (!address.getString("country").equalsIgnoreCase("United States")) {
                address = (JSONObject) addresses.get(rand.nextInt(addresses.length()));
            }
            if (address == null) {
                throw new Exception("Unable to find address matching given options");
            }

        } catch (Exception e) {
            Assert.fail("Unable to parse JSON: " + e);
        }
        try {
            addressTemp.fillFromJson(address);
        } catch (JSONException e) {
            logger.error("Unable to get random address: " + e.getMessage());
        }
    }

    public static void validateAddressAssociatedToUserFromDb(String email) {
        JSONObject queries = Utils.getSqlQueries();
        Connection connection = new DBConnection().createConnection();
        int addresses = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queries.getJSONObject("user_service").get("get_address_added_to_user").toString().replace("?", email));
            while (rs.next()) {
                String addressLine1 = rs.getString("ADDRESS_LINE_1");
                logger.info(addressLine1);
                String addressLine2 = rs.getString("ADDRESS_LINE_2");
                logger.info(addressLine2);
                String addressLine3 = rs.getString("ADDRESS_LINE_3");
                logger.info(addressLine3);
                String city = rs.getString("CITY");
                String stateAbbr = rs.getString("STATE_ABBREV");
                String code = rs.getString("POSTAL_CODE");
                String newAddressLine = "";
                if (macys()) {
                    newAddressLine = city + " " + stateAbbr + " " + code;
                } else {
                    newAddressLine = city + " ,  " + stateAbbr + "   " + code;
                }
                logger.info(newAddressLine);
                List<WebElement> recordsOnUI = Elements.findElements(Elements.element("my_address_book.addresses"));
                int record = 0;
                for (; record < recordsOnUI.size(); record++) {
                    if ((recordsOnUI.get(record).getText().contains(addressLine1)) && (recordsOnUI.get(record).getText().contains(newAddressLine))) {
                        break;
                    }
                }
                if (record == recordsOnUI.size()) {
                    Assert.assertTrue("address associated to user on db does not matches with UI", false);
                }
                addresses += 1;
            }
            statement.close();
        } catch (SQLException | JSONException e) {
            logger.error("Unable to get return status: " + e.getMessage());
        }
        logger.info("number of records in db =" + addresses);
        List<WebElement> recordsOnUI = Elements.findElements(Elements.element("my_address_book.addresses"));
        Assert.assertEquals("number of address records in db is not equal to number of address records on ui", addresses, recordsOnUI.size());
    }

    public static String getLineItemDetails(String returnStatus, JSONObject returnOrderDetails) {
        try {
            Connection connection = new DBConnection().createConnection();
            JSONObject queries = Utils.getSqlQueries();
            Map<String, String> statusCode = new HashMap<>();
            statusCode.put("complete", "C");
            statusCode.put("incomplete", "I");
            statusCode.put("intransit", "T");
            statusCode.put("received", "D");
            statusCode.put("submitted", "S");
            logger.info("return status = " + statusCode.get(returnStatus));
            logger.info("order details=" + returnOrderDetails.getString("order_number"));
            Statement statement = connection.createStatement();
            String query = queries.getJSONObject("returns").get("get_line_items_details").toString().replace("?", returnOrderDetails.getString("order_number")).replace("dd", statusCode.get(returnStatus));
            logger.info("Query is " + query);
            ResultSet rs = statement.executeQuery(query);
            Date date = null;
            if (rs.next()) {
                date = rs.getDate("STATUS_UPDATED_TS");
            }
            logger.info("Date is " + date.toString());
            DateFormat bcomFormat = new SimpleDateFormat("MMMM dd, YYYY");
            String updatedDate = bcomFormat.format(date);
            logger.info("updated Date is " + updatedDate);
            statement.close();
            return updatedDate;
        } catch (SQLException | JSONException e) {
            Assert.fail("reterival of record failed : " + e.getMessage());
        }
        return null;
    }

    public static boolean verifyresponseCode(String source) {

        logger.info("SRC is ::" + source);
        int responseCode = RESTOperations.doGET(source, null).getStatus();
        if (responseCode == 200) {
            logger.info("Got correct Response code:: " + responseCode);
            return true;
        } else {
            logger.info("Response code is not 200:: " + responseCode);
            return false;
        }
    }

    /**
     * This method will create random user profile
     *
     * @return UserProfile that was created
     * @throws ProductionException if called while executing against production
     */
    public static UserProfile createNewUserProfile() throws ProductionException, EnvException {

        UserProfile customer = new UserProfile();
        User user = new User();
        HashMap<String, String> addressOpts = new HashMap<>();
        addressOpts.put("country", "United States");
        ProfileAddress profileAddress = new ProfileAddress();
        TestUsers.getRandomValidAddress(addressOpts, profileAddress);

        UserPasswordHint userPasswordHint = new UserPasswordHint();
        userPasswordHint.setId(1L);
        userPasswordHint.setAnswer(TestUsers.generateRandomSecurityAnswer());
        userPasswordHint.setQuestion("What was the first concert you attended?");

        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setPassword("Macys12345");

        user.setGender(TestUsers.generateRandomGender());
        user.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").format(TestUsers.generateRandomDate()));
        user.setLoginCredentials(loginCredentials);
        user.setProfileAddress(profileAddress);
        user.setUserPasswordHint(userPasswordHint);
        customer.setUser(user);
        TestUsers.currentEmail = customer.getUser().getProfileAddress().getEmail();
        TestUsers.currentPassword = customer.getUser().getLoginCredentials().getPassword();
        logger.info("Your New Email Address is: " + TestUsers.currentEmail);
        return customer;
    }

    public static ProfileAddress getReturnAddressFromDb(JSONObject returnOrderDetails) {
        try {
            ProfileAddress address = new ProfileAddress();
            Connection connection = new DBConnection().createConnection();
            JSONObject queries = Utils.getSqlQueries();
            logger.info("order details=" + returnOrderDetails.getString("order_number"));
            Statement statement = connection.createStatement();
            String query = queries.getJSONObject("returns").get("get_shipment_address").toString().replace("?", returnOrderDetails.getString("order_number"));
            logger.info("Query is " + query);
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                address.setAddressLine1(rs.getString("ADDRESS_LINE1"));
                address.setCity(rs.getString("CITY"));
                address.setState(rs.getString("STATE_ABBREV"));
                address.setZipCode(rs.getString("POSTAL_CODE"));
            }
            statement.close();
            return address;
        } catch (SQLException | JSONException e) {
            Assert.fail("reterival of record failed : " + e.getMessage());
        }
        return null;
    }

    public static JSONObject getProductionOrder(HashMap<String, String> options) {
        try {
            JSONArray orders;
            File returnsFile = getResourceFile("site_orders.json");
            String jsonTxt = Utils.readTextFile(returnsFile);
            JSONObject json = new JSONObject(jsonTxt);
            if (macys()) {
                orders = (JSONArray) json.get("macys");
            } else {
                orders = (JSONArray) json.get("bloomingdales");
            }

            for (int i = 0; i < orders.length(); i++) {
                JSONObject order = orders.getJSONObject(i);
                boolean found = true;
                for (String key : options.keySet()) {
                    try {
                        if (!options.get(key).equals(order.get(key))) {
                            found = false;
                            break;
                        }
                    } catch (JSONException e) {
                        Assert.fail("Data not found in return_orders for key: " + key);
                    }
                }
                if (found) {
                    return order;
                }
            }
            return null;
        } catch (Exception e) {
            Assert.fail("Unable to parse JSON: " + e);
            return null;
        }
    }

    public static JSONObject getProdUser(String userValidationType) {
        JSONObject prodUser = null;
        JSONObject prodUserData = null;
        JSONArray prodUserArray = null;
        try {
            String jsonText = Utils.readTextFile(getResourceFile("sanity_production.json"));
            prodUser = new JSONObject(jsonText);
        } catch (IOException | JSONException e) {
            logger.warn("error in getting sanity production data : " + e.getMessage());
        }

        prodUserArray = (JSONArray) prodUser.get("prod_user");
        logger.info("ProdUserArray:" + prodUserArray.toString());

        for (int i = 0; i < prodUserArray.length(); i++) {
            prodUserData = prodUserArray.getJSONObject(i);
            if (prodUserData.get("user_type").equals(userValidationType)) {
                return prodUserData;
            }
        }
        return prodUserData;
    }

    public static void setTrackingNumberinDB(JSONObject returnOrderDetails) throws SQLException {
        JSONObject queries = Utils.getSqlQueries();
        Connection connection = new DBConnection().createConnection();
        try {
            Statement statement = connection.createStatement();
            String query = queries.getJSONObject("returns").get("update_tracking_number").toString().replaceFirst("'\\?'", "'" + returnOrderDetails.getString("carrier_tracking_nbr") + "'").replaceFirst("'\\?'", "'" + returnOrderDetails.getString("order_number") + "'");
            logger.info("Query=" + query);
            statement.executeUpdate(query);
            connection.commit();
            statement.close();

        } catch (SQLException | JSONException e) {
            logger.error("Unable to set Tracking number in DB:" + e.getMessage());
        }
    }

    public static String getSubscriptionNameEnabledValue(String email_id, String columnName) {
        JSONObject queries = Utils.getSqlQueries();
        Connection connection = new DBConnection().createConnection();
        Map<String, String> userData = new HashMap<>();
        String userId = "";
        String opt_In = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queries.getJSONObject("user_service").get("user_id_by_email").toString().replace("?", email_id));
            while (rs.next()) {
                userId = rs.getString("USER_ID");
                userData.put("userId", userId);
            }
            rs = statement.executeQuery(queries.getJSONObject("user_service").get("user_subscription").toString().replace("?", userId));
            while (rs.next()) {
                userData.put(rs.getString("SUBSCRIPTION_ID"), rs.getString("OPT_IN"));
            }
            if (columnName.equals("Thisit Email Newsletter")) {
                opt_In = userData.get("1");
            } else if (columnName.equals("Email Frequency Preference")) {
                opt_In = userData.get("3");
            } else if (columnName.equals("Mobile Phone Marketing Preference")) {
                opt_In = userData.get("4");
            }
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return opt_In;
    }

    public static Map getUserFirstAndLastAndDOBByUserID(String userId) {
        JSONObject queries = Utils.getSqlQueries();
        Connection connection = new DBConnection().createConnection();
        Map<String, String> userData = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queries.getJSONObject("user_service").get("user_profile").toString().replace("?", userId));
            while (rs.next()) {
                userData.put("firstNameDB", rs.getString("FIRST_NAME"));
                userData.put("lastNameDB", rs.getString("LAST_NAME"));
                userData.put("DOBDB", rs.getString("DATE_OF_BIRTH"));
            }
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return userData;
    }

    public static void deletePasswordHintByUserID(String userId) {
        JSONObject queries = Utils.getSqlQueries();
        Connection connection = new DBConnection().createConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(queries.getJSONObject("user_service").get("delete_password_hint").toString().replace("?", userId));
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
    }

    public static Map getOrderTotal(String orderNumber) {
        JSONObject queries = Utils.getSqlQueries();
        Map<String, String> orderDetails = new HashMap<>();
        Connection connection = new DBConnection().createConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queries.getJSONObject("order_service").get("order_details").toString().replace("?", orderNumber));
            while (rs.next()) {
                orderDetails.put("orderTotal", rs.getString("order_total"));
            }
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return orderDetails;
    }

    public static void updateAllReturnStatus(String orderNumber){
           try {
            Connection connection = new DBConnection().createConnection();
            JSONObject queries = Utils.getSqlQueries();
            Statement statement = connection.createStatement();
            // Get Return_Shipment
               PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("return_shipment_last_shipment_using_order").toString());
               preparedStatement.setString(1, orderNumber);
               ResultSet resultSet = preparedStatement.executeQuery();
               Map returnShipment = resultSetToHashMap(resultSet);
               logger.info("ReturnShipment values :"+returnShipment.toString());


               preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("return_lineItem_for_shipmentId").toString());
               preparedStatement.setString(1, returnShipment.get("return_shipment_id").toString());
               resultSet = preparedStatement.executeQuery();
               Map returnLineItem = resultSetToHashMap(resultSet);
               logger.info("ReturnLineItem values :"+returnLineItem.toString());


               String[]  statusCodes = {"T", "D", "C", "I"};
               for( String status : statusCodes){
                   try{
                   preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("insert_into_return_shipment").toString());
                   preparedStatement.setString(1,returnShipment.get("carrier_nm").toString());
                   preparedStatement.setString(2,returnShipment.get("carrier_tracking_nbr").toString());
                   preparedStatement.setString(3,returnShipment.get("context_id").toString());
                   preparedStatement.setString(4,returnShipment.get("created").toString());
                   preparedStatement.setString(5,returnShipment.get("last_modified").toString());
                   preparedStatement.setString(6,returnShipment.get("order_date").toString());
                   preparedStatement.setString(7,returnShipment.get("order_nbr").toString());
                   preparedStatement.setString(8,returnShipment.get("order_sell_division_cd").toString());
                   preparedStatement.setString(9,returnShipment.get("order_sell_store_nbr").toString());
                   preparedStatement.setString(10,returnShipment.get("refund_method").toString());
                   preparedStatement.setString(11,returnShipment.get("refund_type").toString());
                   preparedStatement.setString(12,returnShipment.get("reservation_id").toString());
                   preparedStatement.setString(13,returnShipment.get("return_address_id").toString());
                   preparedStatement.setString(14,returnShipment.get("return_email_address").toString());
                   preparedStatement.setString(15,returnShipment.get("return_submitted_email_flg").toString());
                   preparedStatement.setString(16,returnShipment.get("return_submitted_ts").toString());
                   preparedStatement.setString(17,status.toString());
                   preparedStatement.setString(18,returnShipment.get("status_updated_ts").toString());
                   preparedStatement.executeUpdate();
                   }catch( SQLException | JSONException e ){
                       Assert.fail("Unable to add returnShipment entry");
                   }

                   try {
                       preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("insert_into_return_lineitem").toString());
                       preparedStatement.setString(1, returnLineItem.get("color_nbr").toString());
                       preparedStatement.setString(2, returnLineItem.get("created").toString());
                       preparedStatement.setString(3, returnLineItem.get("current_price").toString());
                       preparedStatement.setString(4, returnLineItem.get("fill_division_cd").toString());
                       preparedStatement.setString(5, returnLineItem.get("fill_store_nbr").toString());
                       preparedStatement.setString(6, returnLineItem.get("item_desc").toString());
                       preparedStatement.setString(7, returnLineItem.get("last_modified").toString());
                       preparedStatement.setString(8, returnLineItem.get("original_price").toString());
                       preparedStatement.setString(9, returnLineItem.get("quantity").toString());
                       preparedStatement.setString(10, returnLineItem.get("reason_cd").toString());
                       preparedStatement.setString(11, returnLineItem.get("return_shipment_id").toString());
                       preparedStatement.setString(12, returnLineItem.get("size_desc").toString());
                       preparedStatement.setString(13, returnLineItem.get("size_nbr").toString());
                       preparedStatement.setString(14, returnLineItem.get("status_cd").toString());
                       preparedStatement.setString(15, returnLineItem.get("status_updated_ts").toString());
                       preparedStatement.setString(16, returnLineItem.get("surcharge_fee").toString());
                       preparedStatement.setString(17, returnLineItem.get("tax").toString());
                       preparedStatement.setString(18, returnLineItem.get("upc").toString());
                       preparedStatement.setString(19, returnLineItem.get("vendornm").toString());
                       preparedStatement.executeUpdate();
                   }catch( Exception e ){
                       logger.info(e.toString() +" "+ e.getStackTrace() + " " +e.getMessage());
                   }
               }
            statement.close();
            // statement1.close();
        } catch (SQLException | JSONException e) {
            Assert.fail("delete return record failed : " + e.getMessage());
        }
    }

    public static HashMap  resultSetToHashMap(ResultSet rs) throws SQLException {

        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        HashMap row = new HashMap();
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                row.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
            }
        }
        return row;
    }

    public static HashMap getReturnInitiatedOrderInfo(JSONObject returnOrderDetails) {
        HashMap<String, String> returnItemFromDB = new HashMap<>();
        try {
            Connection connection = new DBConnection().createConnection();
            JSONObject queries = Utils.getSqlQueries();
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("maximum_return_shipment_id").toString().replace("?", returnOrderDetails.getString("order_number")));
            ResultSet rs = preparedStatement.executeQuery();
            String returnShipmentId = "";
            if (rs.next()) {
                returnShipmentId = rs.getString("RETURN_SHIPMENT_ID");
                if (returnShipmentId.equals(""))
                    Assert.fail("Could not find the Return Shipment ID in DB");
            }
            preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("with_return_line_item_shipment_id").toString().replace("?", returnShipmentId));
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                returnItemFromDB.put("upc", rs.getString("UPC"));
                returnItemFromDB.put("ITEM_DESC", rs.getString("ITEM_DESC"));
                returnItemFromDB.put("COLOR_DESC", rs.getString("COLOR_DESC"));
                returnItemFromDB.put("VENDOR_NM", rs.getString("VENDOR_NM"));
                returnItemFromDB.put("SIZE_DESC", rs.getString("SIZE_DESC"));
                returnItemFromDB.put("QUANTITY", rs.getString("QUANTITY"));
            }
        } catch (Exception e) {
            System.out.println(e + e.getMessage());
        }
        return returnItemFromDB;
    }

    public static Map<String, String> getAddressMap(String orderNumber, Map<String,String> defaultAddressMap){
        HashMap returnLineItem = null;
        try {
            Connection connection = new DBConnection().createConnection();
            JSONObject queries = Utils.getSqlQueries();
            Statement statement = connection.createStatement();
            // Get Return_Shipment
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("return_shipment_last_shipment_using_order").toString());
            preparedStatement.setString(1, orderNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            Map returnShipment = resultSetToHashMap(resultSet);
            logger.info("ReturnShipment values :"+returnShipment.toString());

            preparedStatement = connection.prepareStatement(queries.getJSONObject("returns").get("return_shipment_address").toString());
            preparedStatement.setString(1, returnShipment.get("return_address_id").toString());
            resultSet = preparedStatement.executeQuery();
            returnLineItem = resultSetToHashMap(resultSet);
            logger.info("Address values :"+returnLineItem.toString());



        } catch (Exception e) {
            System.out.println(e + e.getMessage());
        }

        return returnLineItem;
    }

    public static void signOut() {
        if (MEW()) {
            GlobalNav.openGlobalNav();
            GlobalNav.navigateOnGnByName("sign out");
            Wait.forPageReady();
            GlobalNav.closeGlobalNav();

        }
    }

    public static UserProfile signInOrCreateAccountMew() {
        UserProfile customer = null;
        if (!onPage("my_account") || !signedIn()) {
            customer = TestUsers.getCustomer(null);
            pausePageHangWatchDog();
            TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
            TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
            Clicks.click("sign_in.sign_in_button");
//            Clicks.javascriptClick("sign_in.sign_in_button");
            Clicks.clickIfPresent("sign_in.close_overlay");
            resumePageHangWatchDog();
            // if this account works, we're good to go
            if (!Wait.secondsUntilElementPresent("sign_in.error_message", safari() || ie() ? 40 : 20)) {
                new MyAccount().setSecurityQuestion();
                if (!MEW()) {
                    Wait.secondsUntilElementPresent("header.goto_my_account_link", 20);
                    Clicks.click("header.goto_my_account_link");
                    //yc03673 2017-06-19 Fixed the issue with goto_my_account_second_link locator and below code works fine from all signed in pages now
                    //yc03673 2017-06-08 Added step to click the My Account link after expanding My Account list
                    if (macys()) {
                        Clicks.clickIfPresent("header.goto_my_account_second_link");
                    }
                }
                shouldBeOnPage("my_account");
                isNewProfileCreated = false;
                logger.info("No new profile created");
                return null;
            }
            if (MEW()) {
                CreateProfileMEW.createProfile(customer);
                Assert.assertTrue("New Profile is not created", Wait.until(() -> onPage("my_account"), 10));
                isNewProfileCreated = true;
                logger.info("New profile created in mew");
            }
        }
        return customer;
    }
}

