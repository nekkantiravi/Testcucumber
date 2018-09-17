package com.macys.sdt.projects.Selection.PDP.steps.website.mcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.CurrentAddress;
import com.macys.sdt.framework.model.registry.Registry;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.framework.utils.rest.services.CreditCardService;
import com.macys.sdt.framework.utils.rest.services.RegistryService;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.Selection.PDP.steps.website.mcom.PIDsAndMethods;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.util.*;
import java.util.logging.Logger;


public class StepDefinitionsPDP extends StepUtils {

//    private static WebDriver wd = null; //new ChromeDriver();
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    SoftAssertions softly = new SoftAssertions();
    private static String productId = "";
    private static String productName = "";
    private static String productPrice = "";
    private static String emailAddress = "";
    private static String userPassword = "";
    public static String defaultColor;
    public static String colorSwatchSelected;
    public static WebElement selectedColor;
    public static WebElement selectedSize;


    @Given("^I select a \"([^\"]*)\" product and navigate to PDP in \"([^\"]*)\" mode$")
    public void I_select_a_product_and_navigate_to_PDP(String productType, String mode) throws Throwable {
        switch (mode) {
            case "site": {
                switch (productType) {
                    case "member": {
                        String[] products = PIDsAndMethods.memberProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "master": {
                        String[] products = PIDsAndMethods.masterProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "TrueFit": {
                        String[] products = PIDsAndMethods.trueFitProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (productType) {
                    case "member": {
                        String[] products = PIDsAndMethods.iShipMemberProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "master": {
                        String[] products = PIDsAndMethods.masterProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                }
                break;
            }
            case "registry": {
                List<WebElement> headerNavLinks = Elements.findElements(Elements.element("navigation.headerNavLinks"));
                logger.info("*** Number of headerNavLinks displayed: " + headerNavLinks.size());
                for(WebElement linkElement: headerNavLinks){
                    if((linkElement.getText()).equalsIgnoreCase("WEDDING REGISTRY")) {
                        logger.info(linkElement.getText() + " is clicked!");
                        Thread.sleep(2000);
                        Clicks.click(linkElement);
                        break;
                    }
                }
                Wait.secondsUntilElementPresent("navigation.registry_mode", 20);
                //softly.assertThat((Elements.findElement(Elements.element("navigation.registry_mode")).getText()).equalsIgnoreCase("Back to Macys.com")).as("Mode: Registry").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("navigation.registry_mode")).as("Mode: Registry").isEqualTo(true);
                softly.assertAll();
                logger.info( "User is now in Registry mode!");
                Thread.sleep(2000);
                switch (productType) {
                    case "member": {
                        String[] products = PIDsAndMethods.memberProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "master": {
                        String[] products = PIDsAndMethods.masterProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "video": {
                        String[] products = {"77589", "136737"};
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                }
                break;
            }
        }
        TextBoxes.typeTextNEnter("home.search_field", productId);
        Wait.forPageReady();
        Clicks.clickIfPresent("home.close_signup_for_email_popup");
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(productType + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!"));
            e.printStackTrace();
        }
    }

    @When("^I navigate directly to PDP with PID \"([^\"]*)\" in (site|iship|registry) mode$")
    public void navigating_directly_to_PDP_with_PID(String PID, String mode) throws Throwable {
        productId = PID;
        TextBoxes.typeTextNEnter("home.search_field", productId);
        try {
            Wait.forPageReady();
            Clicks.clickIfPresent("home.close_signup_for_email_popup");
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
            softly.assertThat(Elements.elementPresent("pdp.productTitle")).as("Landing Page: PDP page "+productId).isEqualTo(true);
        } catch (Exception e) {
            logger.warning(String.format("PDP not loading properly as productTitle is not displayed in " + mode + " mode!"));
            e.printStackTrace();
        }
        softly.assertAll();
        Thread.sleep(5000);
    }


    @When("^I navigate directly to \"([^\"]*)\" PDP and add a product to my bag in \"([^\"]*)\" mode$")
    public void I_navigate_directly_to_PDP_and_add_a_product_to_my_bag_in_mode(String page, String mode) throws Throwable {
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        String[] products = PIDsAndMethods.memberProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        TextBoxes.typeTextNEnter("home.search_field", productId);
                        try {
                            Wait.forPageReady();
                            Wait.secondsUntilElementPresent("pdp.add_to_bag", 20);
                            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                            Thread.sleep(1000);
                            Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
//                            scrollToLazyLoadElement("quick_bag.quickBagLink");
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        } catch (Exception e) {
                            logger.warning(String.format("AddToBag button not displayed on " + page + " PDP in " + mode + " mode!\n"));
                            e.printStackTrace();
                        }
                        Clicks.click(Elements.element("pdp.add_to_bag"));
                        Thread.sleep(10000);
                        Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                        Clicks.click(Elements.element("addToBag.keep_shopping_button"));
                        Thread.sleep(5000);
                        break;
                    }
                    case "master": {
                        String[] masters = PIDsAndMethods.masterProdID();;
                        int index = new Random().nextInt(masters.length);
                        productId = masters[index];
                        TextBoxes.typeTextNEnter("home.search_field", productId);
                        try {
                            Wait.forPageReady();
                            Wait.secondsUntilElementPresent("pdp.add_to_bag", 40);
                            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                            Thread.sleep(1000);
                            Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("masterProductMemberAddToBag button").isEqualTo(true);
                        } catch (Exception e) {
                            logger.warning(String.format("AddToBag button not displayed on " + page + " PDP in " + mode + " mode!\n"));
                            e.printStackTrace();
                        }
                        Clicks.click(Elements.element("pdp.add_to_bag"));
                        Thread.sleep(5000);
                        Wait.secondsUntilElementPresent("addToBag.masterKeepShoppingBtn", 20);
                        Clicks.click(Elements.element("addToBag.masterKeepShoppingBtn"));
                        Thread.sleep(5000);
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        String[] members = PIDsAndMethods.iShipMemberProdID();;
                        int index = new Random().nextInt(members.length);
                        productId = members[index];
                        TextBoxes.typeTextNEnter("home.search_field", productId);
                        try {
                            Wait.forPageReady();
                            Wait.secondsUntilElementPresent("pdp.add_to_bag", 20);
                            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
//                            scrollToLazyLoadElement("pdp.googleAdsense");
                            Thread.sleep(1000);
//                            scrollToLazyLoadElement("quick_bag.quickBagLink");
                            Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        } catch (Exception e) {
                            logger.warning(String.format("AddToBag button not displayed on " + page + " PDP in " + mode + " mode!\n"));
                            e.printStackTrace();
                        }
                        Clicks.click(Elements.element("pdp.add_to_bag"));
                        Thread.sleep(5000);
                        Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                        Clicks.click(Elements.element("addToBag.keep_shopping_button"));
                        Thread.sleep(5000);
                        break;
                    }
                    case "master": {
                        String[] masters = PIDsAndMethods.masterProdID();;
                        int index = new Random().nextInt(masters.length);
                        productId = masters[index];
                        TextBoxes.typeTextNEnter("home.search_field", productId);
                        try {
                            Wait.forPageReady();
                            Wait.secondsUntilElementPresent("pdp.add_to_bag", 20);
                            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                            Thread.sleep(1000);
                            Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        } catch (Exception e) {
                            logger.warning(String.format("AddToBag button not displayed on " + page + " PDP in " + mode + " mode!\n"));
                            e.printStackTrace();
                        }
                        Clicks.click(Elements.element("pdp.add_to_bag"));
                        Wait.secondsUntilElementPresent("addToBag.masterKeepShoppingBtn", 20);
                        Clicks.click(Elements.element("addToBag.masterKeepShoppingBtn"));
                        Thread.sleep(3000);
                        break;
                    }
                }
                break;
            }
            case "registry": {
                List<WebElement> headerNavLinks = Elements.findElements(Elements.element("navigation.headerNavLinks"));
                logger.info("*** Number of headerNavLinks displayed: " + headerNavLinks.size());
                for(WebElement linkElement: headerNavLinks){
                    if((linkElement.getText()).equalsIgnoreCase("WEDDING REGISTRY")) {
                        logger.info(linkElement.getText() + " is clicked!");
                        Thread.sleep(2000);
                        Clicks.click(linkElement);
                        break;
                    }
                }
                Wait.secondsUntilElementPresent("navigation.registry_mode", 20);
                //softly.assertThat((Elements.findElement(Elements.element("navigation.registry_mode")).getText()).equalsIgnoreCase("Back to Macys.com")).as("Mode: Registry").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("navigation.registry_mode")).as("Mode: Registry").isEqualTo(true);
                softly.assertAll();
                logger.info( "User is now in Registry mode!");
                Thread.sleep(2000);
                switch (page) {
                    case "member": {
                        String[] members = PIDsAndMethods.memberProdID();;
                        int index = new Random().nextInt(members.length);
                        productId = members[index];
                        TextBoxes.typeTextNEnter("home.search_field", productId);
                        try {
                            Wait.forPageReady();
                            Wait.secondsUntilElementPresent("pdp.add_to_bag", 20);
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        } catch (Exception e) {
                            logger.warning(String.format("AddToBag button not displayed on " + page + " PDP in " + mode + " mode!\n"));
                            e.printStackTrace();
                        }
                        Clicks.click(Elements.element("pdp.add_to_bag"));
                        Thread.sleep(5000);
                        Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                        Clicks.click(Elements.element("addToBag.keep_shopping_button"));
                        Thread.sleep(5000);
                        break;
                    }
                    case "master": {
                        String[] masters = PIDsAndMethods.masterProdID();;
                        int index = new Random().nextInt(masters.length);
                        productId = masters[index];
                        TextBoxes.typeTextNEnter("home.search_field", productId);
                        try {
                            Wait.forPageReady();
                            Wait.secondsUntilElementPresent("pdp.add_to_bag", 40);
                            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                            scrollToLazyLoadElement("pdp.googleAdsense");
                            Thread.sleep(1000);
                            scrollToLazyLoadElement("quick_bag.quickBagLink");
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("masterProductMemberAddToBag button").isEqualTo(true);
                        } catch (Exception e) {
                            logger.warning(String.format("AddToBag button not displayed on " + page + " PDP in " + mode + " mode!\n"));
                            e.printStackTrace();
                        }
                        Clicks.click(Elements.element("pdp.add_to_bag"));
                        Thread.sleep(5000);
                        Wait.secondsUntilElementPresent("addToBag.masterKeepShoppingBtn", 20);
                        Clicks.click(Elements.element("addToBag.masterKeepShoppingBtn"));
                        Thread.sleep(5000);
                        break;
                    }
                }
                break;
            }
        }
    }

    @When("^I select a \"([^\"]*)\" product for \"([^\"]*)\" and navigate to PDP")
    public void I_select_eligible_nonEligible_products_and_navigate_to_PDP(String arg, String feature) throws Throwable {
        Thread.sleep(5000);
        Wait.forPageReady();
        if(feature.equals("webCollage")) {
            switch (arg) {
                case "eligible": {
                    String[] product = {"77589","136737"};
                    int index = new Random().nextInt(product.length);
                    productId = product[index];
                    break;
                }
                case "non_eligible": {
                    String[] product = {"22804"};
                    int index = new Random().nextInt(product.length);
                    productId = product[index];
                    break;
                }
            }
        }
        else if(feature.equals("Olapic")) {
            switch (arg) {
                case "ZeroState": {
                    String[] product = {"22804"};
                    int index = new Random().nextInt(product.length);
                    productId = product[index];
                    break;
                }
                case "FullState": {
                    String[] product = {"77589"};
                    int index = new Random().nextInt(product.length);
                    productId = product[index];
                    break;
                }
            }
        }
        else if(feature.equals("BOPS")) {
            if (arg.equals("eligible")) {
                String[] product = {"2351355"};
                int index = new Random().nextInt(product.length);
                productId = product[index];
            } else {
                String[] product = {"1111111"};
                int index = new Random().nextInt(product.length);
                productId = product[index];
            }
        }
        else if(feature.equals("CollectionCTA") && arg.equals("member")) {
            String[] product = {"2711853", "183112", "1747260"};
            int index = new Random().nextInt(product.length);
            productId = product[index];
        }
        else if(feature.equals("BrandNameLink") && arg.equals("SpecialCharacter")) {
            String[] product = PIDsAndMethods.specialCharProdID();
            int index = new Random().nextInt(product.length);
            productId = product[index];
        }
        else if(feature.equals("SpecialCharacter") && arg.equals("master")) {
            String[] product = PIDsAndMethods.masterSpecialCharProdID();
            int index = new Random().nextInt(product.length);
            productId = product[index];
        }
        else if(feature.equals("SuppressedQ&A") && arg.equals("member")) {
            String[] product = PIDsAndMethods.suppressedQAProdID();
            int index = new Random().nextInt(product.length);
            productId = product[index];
        }
        else if(feature.equals("PROS")) {
            if(arg.equals("member")) {
                String [] member = {};
                //web id 22804 is not available in BCOM
                if(macys()) {
                     member = new String[]{"22804"};
                }
                else{
                     member = new String[]{"1253571"};
                }
                int index = new Random().nextInt(member.length);
                productId = member[index];
            }
            else {
                //String[] master = {"2179485"};
                String[] master = {"4635120"};
                int index = new Random().nextInt(master.length);
                productId = master[index];
            }
        }
        logger.info(productId);
        Wait.secondsUntilElementPresent("home.search_field", 20);
        TextBoxes.typeTextNEnter("home.search_field", productId);
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.add_to_bag", 20);
            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
        } catch (Exception e) {
            logger.warning(String.format("PDP not loaded properly!\n"));
            e.printStackTrace();
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" PDP \"([^\"]*)\" mode$")
    public void click_buttons_on_PDP(String button, String page, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(page + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!"));
            e.printStackTrace();
        }
        Thread.sleep(3000);
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        if(button.equals("A2B")) {
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                            String Id[] = Elements.findElement(Elements.element("pdp.ProductDetailsWebId")).getText().split(":", 0);
                            productId = Id[1].trim();
                            Clicks.click(Elements.element("pdp.add_to_bag"));
                            logger.warning(String.format("A2B button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("ContinueShopping")) {
                            Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.keep_shopping_button")).as("Keep Shopping button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.keep_shopping_button"));
                            logger.info(String.format("ContinueShopping button clicked; returning to " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("Checkout")) {
                            Wait.secondsUntilElementPresent("addToBag.checkout_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.checkout_button")).as("Checkout button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.checkout_button"));
                            logger.info(String.format("Checkout button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("A2L")) {
                            Wait.secondsUntilElementPresent("pdp.add_to_list", 20);
                            softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("A2L button").isEqualTo(true);
                            String Id[] = Elements.findElement(Elements.element("pdp.ProductDetailsWebId")).getText().split(":", 0);
                            productId = Id[1].trim();
                            Clicks.click(Elements.element("pdp.add_to_list"));
                            logger.info(String.format("Add2List button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("A2R")) {
                            Wait.secondsUntilElementPresent("pdp.add_to_registry", 20);
                            softly.assertThat(Elements.elementPresent("pdp.add_to_registry")).as("A2R button").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.add_to_registry"));
                            logger.info(String.format("Add2Registry button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("A2L Dropdown")) {
                            Wait.secondsUntilElementPresent("pdp.add_to_list", 20);
                            softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("A2L button").isEqualTo(true);
                            softly.assertThat(Elements.elementPresent("pdp.addToListDropdown")).as("A2L button").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.addToListDropdown"));
                            logger.info(String.format("Add2List Arrow button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        break;
                    }
                    case "master": {
                        if(button.equals("A2B")) {
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.add_to_bag"));
                            logger.info(String.format("A2B button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("ContinueShopping")) {
                            Wait.secondsUntilElementPresent("addToBag.masterKeepShoppingBtn", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.masterKeepShoppingBtn")).as("Keep Shopping button").isEqualTo(true);
                            softly.assertAll();
                            Clicks.click(Elements.element("addToBag.masterKeepShoppingBtn"));
                            logger.info(String.format("ContinueShopping button clicked; returning to " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("Checkout")) {
                            Wait.secondsUntilElementPresent("addToBag.masterCheckoutBtn", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.masterCheckoutBtn")).as("Checkout button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.masterCheckoutBtn"));
                            logger.info(String.format("Checkout button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        break;
                    }
                    case "e-gift": {
                        if(button.equals("A2B")) {
                            Clicks.clickIfPresent("home.close_signup_for_email_popup");
                            softly.assertThat(Elements.elementPresent("pdp.eGiftCardAddToBagBtn")).as("add_to_bag button").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.eGiftCardAddToBagBtn"));
                            logger.info(String.format("A2B button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("ContinueShopping")) {
                            Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.keep_shopping_button")).as("Keep Shopping button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.keep_shopping_button"));
                            logger.info(String.format("ContinueShopping button clicked; returning to " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("Checkout")) {
                            Wait.secondsUntilElementPresent("addToBag.checkout_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.checkout_button")).as("Checkout button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.checkout_button"));
                            logger.info(String.format("Checkout button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        break;
                    }
                    case "CHANEL": {
                        if(button.equals("A2B")) {
                            softly.assertThat(Elements.elementPresent("chanel.addToBagBtn")).as("add_to_bag button").isEqualTo(true);
                            Clicks.click(Elements.element("chanel.addToBagBtn"));
                            logger.info(String.format("A2B button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("ContinueShopping")) {
                            Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.keep_shopping_button")).as("Keep Shopping button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.keep_shopping_button"));
                            logger.info(String.format("ContinueShopping button clicked; returning to " + page + " PDP in " + mode + " mode!\n"));
                        }
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                       if(button.equals("A2B")) {
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                            String Id[] = Elements.findElement(Elements.element("pdp.ProductDetailsWebId")).getText().split(":", 0);
                            productId = Id[1].trim();
                            Clicks.click(Elements.element("pdp.add_to_bag"));
                            logger.warning(String.format("A2B button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("ContinueShopping")) {
                            Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.keep_shopping_button")).as("Keep Shopping button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.keep_shopping_button"));
                            logger.info(String.format("ContinueShopping button clicked; returning to " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("Checkout")) {
                            Wait.secondsUntilElementPresent("addToBag.checkout_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.checkout_button")).as("Checkout button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.checkout_button"));
                            logger.info(String.format("Checkout button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        break;
                    }
                    case "master": {
                        if(button.equals("A2B")) {
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.add_to_bag"));
                            logger.info(String.format("A2B button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("ContinueShopping")) {
                            Wait.secondsUntilElementPresent("addToBag.masterKeepShoppingBtn", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.masterKeepShoppingBtn")).as("Keep Shopping button").isEqualTo(true);
                            softly.assertAll();
                            Clicks.click(Elements.element("addToBag.masterKeepShoppingBtn"));
                            logger.info(String.format("ContinueShopping button clicked; returning to " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("Checkout")) {
                            Wait.secondsUntilElementPresent("addToBag.masterCheckoutBtn", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.masterCheckoutBtn")).as("Checkout button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.masterCheckoutBtn"));
                            logger.info(String.format("Checkout button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        break;
                    }
                }
                break;
            }
            case "registry": {
                 switch (page) {
                    case "member": {
                        if(button.equals("A2B")) {
                            softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                            String Id[] = Elements.findElement(Elements.element("pdp.ProductDetailsWebId")).getText().split(":", 0);
                            productId = Id[1].trim();
                            Clicks.click(Elements.element("pdp.add_to_bag"));
                            logger.warning(String.format("A2B button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("ContinueShopping")) {
                            Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.keep_shopping_button")).as("Keep Shopping button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.keep_shopping_button"));
                            logger.info(String.format("ContinueShopping button clicked; returning to " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("Checkout")) {
                            Wait.secondsUntilElementPresent("addToBag.checkout_button", 20);
                            softly.assertThat(Elements.elementPresent("addToBag.checkout_button")).as("Checkout button").isEqualTo(true);
                            Clicks.click(Elements.element("addToBag.checkout_button"));
                            logger.info(String.format("Checkout button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        else if(button.equals("A2R")) {
                            Wait.secondsUntilElementPresent("pdp.addToRegistryRegistryMode", 20);
                            softly.assertThat(Elements.elementPresent("pdp.addToRegistryRegistryMode")).as("A2R button").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.addToRegistryRegistryMode"));
                            logger.info(String.format("Add2Registry button clicked on " + page + " PDP in " + mode + " mode!\n"));
                        }
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I click \"([^\"]*)\" link or tab on \"([^\"]*)\" PDP \"([^\"]*)\" mode$")
    public void click_links_tabs_on_PDP(String link, String page, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(page + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!"));
            e.printStackTrace();
        }
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        if(link.equals("collectionCTA")) {
                            Wait.secondsUntilElementPresent("pdp.collectionCTA", 10);
                            softly.assertThat(Elements.elementPresent("pdp.collectionCTA")).as("collectionCTA Link").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.collectionCTA"));
                        }
                        else if(link.equals("productsCollectionLink")) {
                            Wait.secondsUntilElementPresent("pdp.productsCollectionLink", 20);
                            softly.assertThat(Elements.elementPresent("pdp.productsCollectionLink")).as("Master Collection Link").isEqualTo(true);
                            Thread.sleep(5000);
                            Clicks.click(Elements.element("pdp.productsCollectionLink"));
                        }
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "registry": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
    }

    @And("^I click \"([^\"]*)\" in bottom tabs section on (member|master) PDP (site|iship|registry) mode$")
    public void clicking_bottom_tab_links_on_PDP(String link, String page, String mode) throws Throwable {

        if(!(Wait.secondsUntilElementPresent("pdp.bottomTabList", 10))){
            logger.info("Scrolling to bottom tab list");
            scrollToLazyLoadElement("pdp.bottomTabList");
            Thread.sleep(5000);
        }
        softly.assertThat(Elements.elementPresent("pdp.bottomTabList")).as("bottomTabList").isEqualTo(true);

        if(link.equalsIgnoreCase("Ask a new question button")) {
            logger.info("Clicking on the " + link);
            Clicks.click(Elements.element("pdp.QA_Btn"));
        }
        else {
            List<WebElement> tabList = Elements.findElements(Elements.element("pdp.bottomTabList"));
            logger.info(tabList.size() + " tabs are displayed on bottom tabs section, and now clicking on the " + link + " tab/link!");
            for(WebElement linkElement: tabList){
                if(link.equalsIgnoreCase(linkElement.getText())) {
                    logger.info("Clicking on the " + linkElement.getText());
                    Clicks.click(linkElement);
                }
            }
        }
        logger.info(link + " clicked!");
        softly.assertAll();
        Thread.sleep(2000);
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "registry": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
    }

    @And("^I click \"([^\"]*)\" on \"([^\"]*)\" on (member|master) PDP (site|iship|registry) mode$")
    public void click_links_tabs_on_overlays_on_PDP(String link, String arg, String pg, String mode) throws Throwable {

        switch(mode) {
            case "site": {
                switch(pg) {
                    case "member": {
                        if(link.equalsIgnoreCase("Signin link")) {
                            if(!(arg.equalsIgnoreCase("A2L session expired popup"))){
                                Wait.secondsUntilElementPresent("pdp.addToListMsgPoppup", 15);
                                softly.assertThat(Elements.elementPresent("pdp.addToListMsgPoppup")).as("addToListMsgPoppup").isEqualTo(true);
                            }
                            softly.assertThat(Elements.elementPresent("pdp.addToListSigninLinkPoppup")).as("addToListSigninLinkPoppup").isEqualTo(true);
                            Thread.sleep(3000);
                            Clicks.click(Elements.element("pdp.addToListSigninLinkPoppup"));
                        }
                        else if(link.equalsIgnoreCase("List link")) {
                            Wait.secondsUntilElementPresent("pdp.addToListMsgPoppup", 15);
                            softly.assertThat(Elements.elementPresent("pdp.addToListMsgPoppup")).as("addToListMsgPoppup").isEqualTo(true);
                            softly.assertThat(Elements.elementPresent("pdp.addToListPopupListLink")).as("addToListPopupListLink").isEqualTo(true);
                            Thread.sleep(3000);
                            Clicks.click(Elements.element("pdp.addToListPopupListLink"));
                        }
                        else if(link.equalsIgnoreCase("DefaultList link")) {
                            Wait.secondsUntilElementPresent("pdp.addToDefaultList", 15);
                            softly.assertThat(Elements.elementPresent("pdp.addToDefaultList")).as("addToListPopupListLink").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.addToDefaultList"));
                            Wait.secondsUntilElementPresent("pdp.addToListPopupListLink", 15);
                            Thread.sleep(5000);
                            Clicks.click(Elements.element("pdp.addToListPopupListLink"));
                        }
                        else if(link.equalsIgnoreCase("CreateList link")) {
                            softly.assertThat(Elements.elementPresent("pdp.createNewListLink")).as("addToListPopupListLink").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.createNewListLink"));
                            Wait.secondsUntilElementPresent("pdp.createNewListName", 5);
                            Clicks.click(Elements.element("pdp.createNewListBtn"));
                            softly.assertThat(Elements.elementPresent("pdp.createNewListError")).as("createNewListError").isEqualTo(true);
                            softly.assertThat(((Elements.findElement(Elements.element("pdp.createNewListError")).getText())).equals("Please enter a name.")).isEqualTo(true);

                            Thread.sleep(3000);
                            TextBoxes.typeTextbox(Elements.element("pdp.createNewListName"), "MyList1");
                            Clicks.click(Elements.element("pdp.createNewListBtn"));

                            Wait.secondsUntilElementPresent("pdp.viewListLink", 5);
                            Clicks.click(Elements.element("pdp.viewListLink"));
                        }
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                break;
            }
            case "registry": {
                break;
            }
        }
        logger.info(String.format("***>> " + link + " clicked on " + arg + " on " + pg + " PDP " + mode + " mode!\n"));
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I click \"([^\"]*)\" icon on (member|master) PDP (site|iship|registry) mode$")
    public void click_icons_on_PDP(String icon, String page, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(page + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!"));
            e.printStackTrace();
        }
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        if(icon.equals("Pinterest")) {
                            Wait.secondsUntilElementPresent("pdp.pinterest_icon", 10);
                            softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.pinterest_icon"));
                            logger.info(String.format("Pinterest button clicked!\n"));
                            Wait.forPageReady();
                            Thread.sleep(3000);
                            Navigate.switchWindow(1);
                            Wait.secondsUntilElementPresent("pdp.pinterestOverlay", 20);
                            softly.assertThat(Elements.elementPresent("pdp.pinterestOverlay")).as("Pinterset Overlay").isEqualTo(true);
                            softly.assertThat(Elements.elementPresent("pdp.pinterestContinueBtn")).as("pinterestContinueBtn").isEqualTo(true);
                            softly.assertThat(Elements.elementPresent("pdp.pinterest_userEmail")).as("Pinterest userEmail").isEqualTo(true);
                            softly.assertThat(Elements.elementPresent("pdp.pinterest_userPwd")).as("Pinterest userPWD").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.pinterestContinueBtn"));
                        }
                        else if(icon.equals("Email")) {
                            Wait.secondsUntilElementPresent("pdp.email_icon", 10);
                            softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.email_icon"));
                            logger.info(String.format("Email button clicked!\n"));

                            }
                        break;
                    }
                    case "master": {
                        if(icon.equals("Pinterest")) {
                            Wait.secondsUntilElementPresent("pdp.pinterest_icon", 10);
                            softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("socialIconPinterest").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.pinterest_icon"));
                            logger.info(String.format("Pinterest button clicked!\n"));

                            }
                        else if(icon.equals("Email")) {
                            Wait.secondsUntilElementPresent("pdp.email_icon", 10);
                            softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("socialIconEmail").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.email_icon"));
                            logger.info(String.format("Email button clicked!\n"));

                        }
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        if(icon.equals("Pinterest")) {
                            Wait.secondsUntilElementPresent("pdp.pinterest_icon", 10);
                            softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.pinterest_icon"));
                            logger.info(String.format("Pinterest button clicked!\n"));

                        }
                        else if(icon.equals("Email")) {
                            Wait.secondsUntilElementPresent("pdp.email_icon", 10);
                            softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.email_icon"));
                            logger.info(String.format("Email button clicked!\n"));
                        }
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "registry": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I delete all cookies")
    public void deleting_all_cookies() throws Throwable {
        Cookies.deleteAllCookies();
        Navigate.browserRefresh();
    }

    @And("^I delete all server & client side cookies on \"([^\"]*)\" page in (site|iship|registry) mode")
    public void deleting_all_svr_client_cookies(String pg, String mode) throws Throwable {
        switch(pg) {
            case "PDP": {
                Cookies.deleteAllCookies();
                String url = WebDriverManager.getCurrentUrl();
                String[] vip = url.split("\\?",0);
                url = vip[0] + "?EFCKEY={}&" + vip[1];
                Navigate.visit(url);
                break;
            }
            case "SearchResult": {
                if(!mode.equalsIgnoreCase("iship")) {
                    Cookies.deleteAllCookies();
                }
                String url = WebDriverManager.getCurrentUrl();
                url = url + "?EFCKEY={}";
                Navigate.visit(url);
                break;
            }
            case "Browse": {
                if(!mode.equalsIgnoreCase("iship")) {
                    Cookies.deleteAllCookies();
                }
                String url = WebDriverManager.getCurrentUrl();
                url = url + "EFCKEY={}";
                Navigate.visit(url);
                break;
            }
        }

    }

    @And("^I force session expiration")
    public void deleting_cookies() throws Throwable {
        Cookies.deleteAllCookies();
    }

    @And("^I close & reopen the browser$")
    public void close_reopen_browser() throws Throwable {
        WebDriverManager.resetDriver(true);
        WebDriverManager.startWebDriver();
    }

    @And("^I resize the dimension on window screen$")
    public void resize_the_window_screen() throws Throwable {
        logger.info(String.format("Original window dimension (width, height):  " + WebDriverManager.getWebDriver().manage().window().getSize()));
        Dimension dimensions = WebDriverManager.getWebDriver().manage().window().getSize();
        WebElement html = WebDriverManager.getWebDriver().findElement(By.tagName("html"));
        int originalWidth = Integer.parseInt(html.getAttribute("clientWidth"));
        int originalHeight = Integer.parseInt(html.getAttribute("clientHeight"));

        WebDriverManager.getWebDriver().manage().window().setSize(new Dimension(
                (dimensions.width - originalWidth) + 1400,
                (dimensions.height - originalHeight) + 810
        ));
        logger.info(String.format("New window dimensions:  " + WebDriverManager.getWebDriver().manage().window().getSize()));
    }

    @And("^I signin as the previous registered user")
    public void I_signin_as_an_existing_registered_user() throws Throwable {
//        UserProfile customer = TestUsers.getCustomer(null);
//        TextBoxes.typeTextbox("sign_in.email", customer.getUser().getProfileAddress().getEmail());
//        TextBoxes.typeTextbox("sign_in.password", customer.getUser().getLoginCredentials().getPassword());
        TextBoxes.typeTextbox("sign_in.email", emailAddress);
        TextBoxes.typeTextbox("sign_in.password", userPassword);
        Clicks.click("sign_in.verify_page");
        Thread.sleep(10000);
    }

    @And("^I insert parameter to hit \"([^\"]*)\" PDP \"([^\"]*)\" mode$")
    public void I_insert_parameter_to_hit_PDP(String svr, String mode) throws Throwable {

        String current_url = WebDriverManager.getCurrentUrl();
        String[] url = current_url.split("\\?",0);
        if(mode.equals("site") || mode.equals("iship")) {
            if(svr.equals("heroku")) {
                String param = "?targetroute=pdpcomp&";
                Navigate.visit(url[0] + param + url[1]);
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("pdp.productTitle", 20);
                String PDPSource = WebDriverManager.getWebDriver().getPageSource();
                softly.assertThat(PDPSource.contains("<site>heroku</site>")).as("Hitting heroku?").isEqualTo(true);
                logger.info(String.format("** PDP loading successfully from: Heroku\n"));
                softly.assertAll();
            }
            else if(svr.equals("QA")) {
                String param = "?targetenv=legacy&";
                Navigate.visit(url[0] + param + url[1]);
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("pdp.productTitle", 20);
                String PDPSource = WebDriverManager.getWebDriver().getPageSource();
                PDPSource = PDPSource.split("<site>")[1].split("</site>")[0];
                softly.assertThat(PDPSource).as("Hitting legacy/QA?").isEqualTo("QA");
                logger.info(String.format("** PDP loading successfully from: " + PDPSource + "\n"));
                softly.assertAll();
            }
        }
    }

    @And("^I setup \"([^\"]*)\" cookies for \"([^\"]*)\" PDP \"([^\"]*)\" mode$")
    public void I_setup_cookies_for_PDP(String cookieType, String page, String mode) throws Throwable {
        switch(mode) {
            case "site": {
                switch(page) {
                    case "member": {
                        if(cookieType.equals("headerFooter")) {
                            Cookies.deleteAllCookies();
                            String url = WebDriverManager.getCurrentUrl();
                            url = url + "&EFCKEY={\"EXPERIMENT\":[\"2553\"]}";
                            Navigate.visit(url);
//                            CTRL: &EFCKEY={"EXPERIMENT":["2553"]}
//                            Hold: &EFCKEY={"EXPERIMENT":["2552"]}
//                            T1: &EFCKEY={"EXPERIMENT":["2554"]}
//                            T2: &EFCKEY={"EXPERIMENT":["2555"]}
                        }
                        else if(cookieType.equals("sizeColor")) {
                            Cookies.deleteAllCookies();
                            Cookies.addSegment("2499");
                            Navigate.browserRefresh();
                        }
                        break;
                    }
                    case "master": {
                        if(cookieType.equals("legacy")) {
                            Cookies.deleteAllCookies();
                            String url = WebDriverManager.getCurrentUrl();
                            url = url + "&EFCKEY={\"EXPERIMENT\":[\"2208\"]}";
                            Navigate.visit(url);
//                            CTRL: &EFCKEY={"EXPERIMENT":["2208"]}
//                            T1: &EFCKEY={"EXPERIMENT":["2209"]}
//                            T2: &EFCKEY={"EXPERIMENT":["2708"]}
                        }
                        break;
                    }
                }
            }
        }
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.productTitle", 20);
    }

    @And("^I setup \"([^\"]*)\" cookie for \"([^\"]*)\" on (member|master) PDP (site|iship|registry) mode$")
    public void I_setup_different_feature_cookies_on_PDP(String cookie, String feature, String pg, String mode) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        if(feature.equals("BigTicket")) {
            Cookies.deleteAllCookies();
            Navigate.browserRefresh();
            Cookies.addSegment(cookie);//2629 control w/ no A2B button & zipcode box
            Navigate.browserRefresh();
//                            String url = WebDriverManager.getCurrentUrl();
//                            url = url + "&" + cookie;//2629 control w/ no A2B button & zipcode box
//                            Navigate.visit(url);
        }
    }

    @And("^I scroll upDown for lazyLoad elements to load on PDP$")
    public void I_scroll_upDown_for_lazyLoad_elements_to_load_on_PDP() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/50)");
            Thread.sleep(1000);
            Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
   //        scrollToLazyLoadElement("pdp.productTitle");
            logger.info(String.format("** PDP loading successfully!\n"));
        } catch (Exception e) {
            logger.warning(String.format(" PDP not loading properly!"));
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    @And("^I scroll up&down for \"([^\"]*)\" element to load on PDP (site|iship|registry) mode$")
    public void scrolling_updown_for_elements_loading_on_PDP(String webEl, String mode) throws Throwable {
        Wait.forPageReady();
        Thread.sleep(3000);
        switch(webEl) {
            case "RVI": {
                scrollToLazyLoadElement("pdp.RVIpanel");
                Wait.secondsUntilElementPresent("pdp.RVIpanel", 10);
                logger.info("Successful scrolling down to \"" + webEl + "\" on PDP " + mode + " mode!");
                break;
            }
            case "Sponsored": {
                scrollToLazyLoadElement("pdp.sponsoredItemsSection");
                Wait.secondsUntilElementPresent("pdp.sponsoredItemsSection", 10);
                logger.info("Successful scrolling down to \"" + webEl + "\" on PDP " + mode + " mode!");
                break;
            }
        }
    }

    @And("^I select another \"([^\"]*)\" product to build product history$")
    public void I_select_another_product(String prodType) throws Throwable {
        if(prodType.equals("member")) {
            String[] products = PIDsAndMethods.otherMemberProdID();;
            int index = new Random().nextInt(products.length);
            productId = products[index];
        }
        else {
            String[] products = PIDsAndMethods.otherMasterProdID();
            int index = new Random().nextInt(products.length);
            productId = products[index];
        }
        TextBoxes.typeTextNEnter("pdp.globalSearchInputField", productId);
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(" PDP not loading properly!"));
            e.printStackTrace();
        }
    }

    @And("^I navigate to \"([^\"]*)\" in \"([^\"]*)\" mode$")
    public void navigating_to_pages(String page, String mode) throws Throwable {
        switch (mode) {
            case "site": {
                switch (page) {
                    case "MyAccount": {
                        Wait.secondsUntilElementPresent("account.myAccountLink", 20);
                        Clicks.click(Elements.element("account.myAccountLink"));
                        break;
                    }
                    case "GiftCard Page": {
                        Wait.secondsUntilElementPresent("giftCard.GiftsArrowLink", 20);
                        Clicks.click(Elements.element("giftCard.GiftsArrowLink"));
                        List<WebElement> giftLinks = Elements.findElements(Elements.element("giftCard.giftLinks"));
                        logger.info("*** Number of Gift Links displayed: " + giftLinks.size());
                        for(WebElement linkElement: giftLinks){
                            if((linkElement.getText()).equalsIgnoreCase("GIFT CARDS")) {
                                logger.info(linkElement.getText() + " is clicked!");
                                Clicks.click(linkElement);
                            }
                        }
                        try {
                            Wait.forPageReady();
                            Wait.secondsUntilElementPresent("giftCard.subCatHeaders", 20);
                        } catch (Exception e) {
                            logger.warning(String.format("Gift Card sub headers are not displayed!\n"));
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "Home Page": {
                        Wait.secondsUntilElementPresent("pdp.macysLogo", 20);
                        Clicks.click(Elements.element("pdp.macysLogo"));
                        break;
                    }
                }
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I select the \"([^\"]*)\" under \"([^\"]*)\" subCategory on Gift Card page$")
    public void select_the_subCategory_on_GiftCard_page(String cardType, String subCat) throws Throwable {

        List<WebElement> headerLinks = Elements.findElements(Elements.element("giftCard.giftCardLinks"));
        logger.info("*** Number of headerLinks displayed: " + headerLinks.size());
        List<WebElement> subCatLinks = Elements.findElements(Elements.element("giftCard.subCatList"));
        logger.info("*** Number of subCatLinks displayed: " + subCatLinks.size());

        for(WebElement linkElement: headerLinks){
            if((linkElement.getText()).equalsIgnoreCase(subCat)) {
                for(WebElement link: subCatLinks) {
                    if((link.getText()).equalsIgnoreCase(cardType)) {
                        logger.info("SubCategory \"" + link.getText() + "\" under \"" + linkElement.getText() + "\" is being clicked!");
                        Thread.sleep(2000);
                        if((linkElement.getText().equalsIgnoreCase("Gift Cards")) && (link.getText().equalsIgnoreCase(("All Occasions"))))
                            Clicks.click(By.xpath(".//*[@id='categoryTree']/ul/li[3]/ul/li[1]/a"));
                        else
                            Clicks.click(link);
                        break;
                    }
                }
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I select the subCategory link \"([^\"]*)\" under Category header \"([^\"]*)\"$")
    public void select_a_subCategory_under_category(String cat, String subCat) throws Throwable {

        List<WebElement> headerLinks = Elements.findElements(Elements.element("pdp.searchPageNavHeaders"));
        logger.info("*** Number of headerLinks displayed: " + headerLinks.size());
        List<WebElement> subCatLinks = Elements.findElements(Elements.element("pdp.searchPageNavLinks"));
        logger.info("*** Number of subCatLinks displayed: " + subCatLinks.size());

        for(WebElement linkElement: headerLinks){
            if((linkElement.getText()).equalsIgnoreCase(subCat)) {
                for(WebElement link: subCatLinks) {
                    if((link.getText()).equalsIgnoreCase(cat)) {
                        logger.info("SubCategory \"" + link.getText() + "\" under \"" + linkElement.getText() + "\" is being clicked!");
                        Thread.sleep(2000);
                        Clicks.click(link);
                        break;
                    }
                }
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I select a random \"([^\"]*)\" product$")
    public void selecting_a_random(String arg) throws Throwable {
        Wait.forPageReady();
        Random rand = new Random();

        switch (arg) {
            case "Gift Card": {
                Wait.secondsUntilElementPresent("giftCard.giftCards", 10);
                List<WebElement> giftCards = Elements.findElements(Elements.element("giftCard.giftCards"));
                logger.info("*** Number of Gift Cards displayed: " + giftCards.size());
                int giftCardOpt = rand.nextInt(giftCards.size());
                giftCards.get(giftCardOpt).click();
                break;
            }
            case "E-Gift Card": {
                Wait.secondsUntilElementPresent("giftCard.eGiftCards", 10);
                List<WebElement> eGiftCards = Elements.findElements(Elements.element("giftCard.eGiftCards"));
                logger.info("*** Number of E-Gift Cards displayed: " + eGiftCards.size());
                int eGiftCardOpt = rand.nextInt(eGiftCards.size());
                eGiftCards.get(eGiftCardOpt).click();
                break;
            }
            case "CHANEL": {
                Wait.secondsUntilElementPresent("chanel.chanelProductList", 10);
                List<WebElement> prodList = Elements.findElements(Elements.element("chanel.chanelProductList"));
                logger.info("*** Number of CHANEL products displayed: " + prodList.size());
                int prodOpt = rand.nextInt(prodList.size());
                Thread.sleep(3000);
                Clicks.click(prodList.get(prodOpt));
                break;
            }
            case "BigTicket": {
                Wait.secondsUntilElementPresent("pdp.productTitle", 10);
                List<WebElement> catList = Elements.findElements(Elements.element("pdp.bigTicketProducts"));
                logger.info("*** Number of Categories displayed: " + catList.size());
                int catOpt = rand.nextInt(catList.size());
                Clicks.click(catList.get(catOpt));

                Thread.sleep(5000);
                Wait.forPageReady();
                List<WebElement> prodList = Elements.findElements(By.xpath(".//*[@class='sortableGrid']/ul/li"));
                logger.info("*** Number of bigTicketProducts displayed: " + prodList.size());
                int prodOpt = rand.nextInt(prodList.size());
                Clicks.click(prodList.get(prodOpt));
                break;
            }
        }
        Thread.sleep(2000);
    }

    @And("^I select a random (member|master) product and save its (name|price) details on (Search|Browse) page$")
    public void selecting_a_random_product(String prodType, String arg, String pg) throws Throwable {
        Wait.forPageReady();
        if(pg.contains("Search")) {
            String PDPSource = WebDriverManager.getWebDriver().getPageSource();
            PDPSource = PDPSource.split("<type>")[1].split("</type>")[0];
            softly.assertThat(PDPSource.contains("searchResults")).as("searchResults PageSource").isEqualTo(true);
            logger.info(String.format("Search Page V/V."));
        }
        else if(pg.equalsIgnoreCase("Browse")) {
            String PDPSource = WebDriverManager.getWebDriver().getPageSource();
            PDPSource = PDPSource.split("<type>")[1].split("</type>")[0];
            softly.assertThat(PDPSource.equalsIgnoreCase("catalog - browse")).as("Browse PageSource").isEqualTo(true);
            logger.info(String.format("Browse Page V/V."));
        }
        Random rand = new Random();
        List<WebElement> items = Elements.findElements(Elements.element("pdp.browsePageThumbnails"));
        logger.info("Number of items displayed: " + items.size());
        switch(arg) {
            case "name": {
                int itemOpt = rand.nextInt(items.size());
                productName = items.get(itemOpt).getText();
                logger.info(String.format("Product Name Displayed as \"" + productName + "\" on " + pg + " page!\n"));
                items.get(itemOpt).click();
                break;
            }
            case "price": {
                int itemPriceOpt = rand.nextInt(items.size());
                productPrice = items.get(itemPriceOpt).getCssValue(".first-range");
                logger.info(String.format("Product Price Displayed as \"" + productPrice + "\" on " + pg + " page!\n"));
                items.get(itemPriceOpt).click();
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I hover over \"([^\"]*)\" FOB tab on PDP$")
    public void hovering_over_FOB_tab_on_PDP(String tab) throws Throwable {

        Random rand = new Random();
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.FOB_Tabs", 20);

        List<WebElement> FOBs = Elements.findElements(Elements.element("pdp.FOBs"));
        logger.info("*** Number of FOBs displayed: " + FOBs.size());
        if(tab.equals("Random")) {
            int FOB_Opt = rand.nextInt(FOBs.size());
            Clicks.hoverForSelection(FOBs.get(FOB_Opt));
            Thread.sleep(1000);
        }
        else {
            for(WebElement linkElement: FOBs){
                if((linkElement.getText()).equalsIgnoreCase(tab)) {
                    Thread.sleep(2000);
                    Clicks.hoverForSelection(linkElement);
                    logger.info(linkElement.getText() + " is hovered!");
                    break;
                }
            }
        }
        Thread.sleep(3000);
    }

    @And("^I select \"([^\"]*)\" link from FOB on PDP$")
    public void selecting_links_from_FOB_on_PDP(String link) throws Throwable {

        Wait.secondsUntilElementPresent("pdp.FOB_Links", 10);
        List<WebElement> FOBLinks = Elements.findElements(Elements.element("pdp.FOB_Links"));
        logger.info("*** Number of FOB_Links displayed: " + FOBLinks.size());
        for(WebElement linkElement: FOBLinks){
            if((linkElement.getText()).equalsIgnoreCase(link)) {
                logger.info(linkElement.getText() + " is clicked!");
                Thread.sleep(2000);
                Clicks.click(linkElement);
                break;
            }
        }
        Thread.sleep(1000);
    }

    @And("^I select \"([^\"]*)\" link from left nav sub categories$")
    public void selecting_links_from_leftNav_subCat(String link) throws Throwable {
        Wait.secondsUntilElementPresent("chanel.subCatLinks", 10);
        List<WebElement> links = Elements.findElements(Elements.element("chanel.subCatLinks"));
        logger.info("*** Number of subCatLinks displayed: " + links.size());
        for(WebElement linkElement: links){
            if((linkElement.getText()).equalsIgnoreCase(link)) {
                logger.info(linkElement.getText() + " is clicked!");
                Thread.sleep(2000);
                Clicks.click(linkElement);
                break;
            }
        }
        Wait.forPageReady();
        Thread.sleep(3000);
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/10)");
    }

    @And("^I select \"([^\"]*)\" (quantity|size|color) on (member|master) PDP in (site|iship|registry) mode$")
    public void select_a_QtyColorSize_on_PDP(String arg, String attr, String pg, String mode) throws Throwable {
        Random rand = new Random();
        switch (mode) {
            case "site": {
                if(pg.equals("member")) {
                    switch (attr) {
                        case "quantity": {
                            Wait.secondsUntilElementPresent("pdp.productQuantity", 10);
                            Clicks.click(Elements.element("pdp.productQuantity"));
                            List<WebElement> qtyOptions = Elements.findElements(Elements.element("pdp.quantityOptions"));
                            logger.info("*** Number of Quantity Options: " + qtyOptions.size());
                            int qtyOpt;

                            if(arg.equals("random")) {
                                qtyOpt = rand.nextInt(qtyOptions.size());
                                qtyOptions.get(qtyOpt).click();
                            }
                            else if(arg.equals("maximum"))
                                qtyOptions.get(qtyOptions.size() -1).click();
                            else {
                                qtyOptions.get((qtyOptions.size()) - (qtyOptions.size())).click();
                                logger.info("Default Quantity Selected!");
                            }
                            break;
                        }
                        case "color": {
                            Wait.secondsUntilElementPresent("pdp.memberProductColorOptions", 10);
                            List<WebElement> colors = Elements.findElements(Elements.element("pdp.memberProdColorsOptEnabled"));
                            logger.info("*** Number of Color Options: " + colors.size());
                            String colorSelected;
                            for(WebElement element: colors) {
                                colorSelected = element.getAttribute("class");
                                if(colorSelected.equalsIgnoreCase("swatch selected")) {
                                    defaultColor = element.getAttribute("data-title");
                                    logger.info("Default color \"" + defaultColor + "\" is the "  + colorSelected + "!");
                                }
                            }
                            if(Elements.elementPresent(Elements.element("pdp.memberViewMoreColors"))) {
                                String viewMoreColorLink = Elements.findElement(Elements.element("pdp.memberViewMoreColors")).getText();
                                String[] viewMoreColorTxt = viewMoreColorLink.split("\n", 0);
                                if (viewMoreColorTxt[1].equals("More")) {
                                    Clicks.click(Elements.findElement(Elements.element("pdp.memberViewMoreColors")));
                                    logger.info("*** >> View " + viewMoreColorTxt[1] + " << link displayed for colors!");
                                }
                                else
                                    logger.info("*** >> View Less << link displayed for colors!");
                            }
                            int colorOpt = rand.nextInt(colors.size());
                            Clicks.click(colors.get(colorOpt));

                            Thread.sleep(3000);
                            List<WebElement> colours = Elements.findElements(Elements.element("pdp.memberProdColorsOptEnabled"));
                            String colourSelected;
                            for(WebElement element: colours) {
                                colourSelected = element.getAttribute("class");
                                if(colourSelected.equalsIgnoreCase("swatch selected")) {
                                    colorSwatchSelected = element.getAttribute("data-title");
                                    logger.info("Selected color \"" + colorSwatchSelected + "\" is the "  + colourSelected + "!");
                                }
                            }
                            break;
                        }
                        case "size": {
                            Wait.secondsUntilElementPresent("pdp.memberProductSizeOptions", 10);
                            List<WebElement> sizes = Elements.findElements(Elements.element("pdp.memberProdSizeOptEnabled"));
                            logger.info("*** Number of Size Options: " + sizes.size());
                            String  viewMoreSizeLink= Elements.findElement(Elements.element("pdp.memberViewMoreSizes")).getText();
                            String[] viewMoreSizeTxt = viewMoreSizeLink.split("\n", 0);
                            if(viewMoreSizeTxt[1].equals("More")) {
                                Clicks.click(Elements.findElement(Elements.element("pdp.memberViewMoreSizes")));
                                logger.info("*** >> View " + viewMoreSizeTxt[1] + " << link displayed for sizes!");
                            }
                            else
                                logger.info("*** >> View Less << link displayed for sizes!");
                            int sizeOpt = rand.nextInt(sizes.size());
                            Clicks.click(sizes.get(sizeOpt));
                            break;
                        }
                    }
                }
                else if(pg.equals("master")) {

                }
                break;
            }
            case "iship": {
                break;
            }
            case "registry": {
                break;
            }
        }
        Thread.sleep(2000);
    }

    @And("^I select a random \"([^\"]*)\" color & size on (member|master) PDP in (site|iship|registry) mode$")
    public void select_a_random_avail_unavail_colorNsize_on_PDP(String arg, String pg, String mode) throws Throwable {
        Random rand = new Random();
        switch (mode) {
            case "site": {
                if(pg.equals("member")) {
                    Wait.secondsUntilElementPresent("pdp.memberProductColorOptions", 10);
                    switch (arg) {
                        case "available": {
                            List<WebElement> colors = Elements.findElements(Elements.element("pdp.memberProdColorsOptEnabled"));
                            logger.info("*** Number of Color Options: " + colors.size());
                            String  viewMoreColorLink= Elements.findElement(Elements.element("pdp.memberViewMoreColors")).getText();
                            String[] viewMoreColorTxt = viewMoreColorLink.split("\n", 0);
                            if(viewMoreColorTxt[1].equals("More")) {
                                Clicks.click(Elements.findElement(Elements.element("pdp.memberViewMoreColors")));
                                logger.info("*** >> View " + viewMoreColorTxt[1] + " << link displayed for colors!");
                            }
                            else
                                logger.info("*** >> View Less << link displayed for colors!");
                            int colorOpt = rand.nextInt(colors.size());
                            selectedColor = colors.get(colorOpt);
                            logger.info("*** >> selectedColor in selectColor method: " + selectedColor);
                            colors.get(colorOpt).click();

                            Thread.sleep(5000);

                            List<WebElement> sizes = Elements.findElements(Elements.element("pdp.memberProdSizeOptEnabled"));
                            logger.info("*** Number of Size Options: " + sizes.size());
                            String  viewMoreSizeLink= Elements.findElement(Elements.element("pdp.memberViewMoreSizes")).getText();
                            String[] viewMoreSizeTxt = viewMoreSizeLink.split("\n", 0);
                            if(viewMoreSizeTxt[1].equals("More")) {
                                Clicks.click(Elements.findElement(Elements.element("pdp.memberViewMoreSizes")));
                                logger.info("*** >> View " + viewMoreSizeTxt[1] + " << link displayed for sizes!");
                            }
                            else
                                logger.info("*** >> View Less << link displayed for sizes!");
                            int sizeOpt = rand.nextInt(sizes.size());
                            selectedSize = sizes.get(sizeOpt);
                            Clicks.click(sizes.get(sizeOpt));
                            break;
                        }
                        case "unavailable": {
                            List<WebElement> colors = Elements.findElements(Elements.element("pdp.memberProdColorsOptEnabled"));
                            logger.info("*** Number of Color Options: " + colors.size());
                            String  viewMoreColorLink= Elements.findElement(Elements.element("pdp.memberViewMoreColors")).getText();
                            String[] viewMoreColorTxt = viewMoreColorLink.split("\n", 0);
                            if(viewMoreColorTxt[1].equals("More")) {
                                Clicks.click(Elements.findElement(Elements.element("pdp.memberViewMoreColors")));
                                logger.info("*** >> View " + viewMoreColorTxt[1] + " << link displayed for colors!");
                            }
                            else
                                logger.info("*** >> View Less << link displayed for colors!");
                            int colorOpt = rand.nextInt(colors.size());
                            colors.get(colorOpt).click();

                            Thread.sleep(5000);

                            List<WebElement> sizes = Elements.findElements(Elements.element("pdp.memberProdSizeOptDisabled"));
                            logger.info("*** Number of Size Options: " + sizes.size());
                            String  viewMoreSizeLink= Elements.findElement(Elements.element("pdp.memberViewMoreSizes")).getText();
                            String[] viewMoreSizeTxt = viewMoreSizeLink.split("\n", 0);
                            if(viewMoreSizeTxt[1].equals("More")) {
                                Clicks.click(Elements.findElement(Elements.element("pdp.memberViewMoreSizes")));
                                logger.info("*** >> View " + viewMoreSizeTxt[1] + " << link displayed for sizes!");
                            }
                            else
                                logger.info("*** >> View Less << link displayed for sizes!");
                            int sizeOpt = rand.nextInt(sizes.size());
                            Clicks.click(sizes.get(sizeOpt));
                            break;
                        }
                    }
                }
                break;
            }
            case "iship": {
                break;
            }
            case "registry": {
                break;
            }
        }
        Thread.sleep(2000);
    }

    @And("^I select the exact same color & size on PDP$")
    public void select_the_same_colorNsize_on_PDP() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.memberProductColorOptions", 10);
        logger.info("*** >> selectedColor in Exact method: " + selectedColor);
        try {
            selectedColor.click();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String  viewMoreSizeLink= Elements.findElement(Elements.element("pdp.memberViewMoreSizes")).getText();
        String[] viewMoreSizeTxt = viewMoreSizeLink.split("\n", 0);
        if(viewMoreSizeTxt[1].equals("More")) {
            Clicks.click(Elements.findElement(Elements.element("pdp.memberViewMoreSizes")));
            logger.info("*** >> View " + viewMoreSizeTxt[1] + " << link displayed for sizes!");
        }
        else
            logger.info("*** >> View Less << link displayed for sizes!");
        try {
            Clicks.click(selectedSize);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    @And("^I click \"([^\"]*)\" on \"([^\"]*)\" page in (site|iship|registry) mode$")
    public void clicking_links_on_pages(String link, String page, String mode) throws Throwable {
        Wait.forPageReady();
        switch (mode) {
            case "site": {
                switch (page) {
                    case "MyAccount": {
                        if(link.equals("profile")) {
                            Wait.secondsUntilElementPresent("account.profileLink", 10);
                            Clicks.click(Elements.element("account.profileLink"));
                            Thread.sleep(5000);
                            String pgSource = WebDriverManager.getWebDriver().getPageSource();
                            emailAddress = pgSource.split("emailAddress\" value=\"")[1].split("@blackhole.macys.com")[0];
                            emailAddress = emailAddress + "@blackhole.macys.com";
                            userPassword = "Macys12345";
                            logger.info("*** User Email Address: " + emailAddress);
                            logger.info("*** User Password: " + userPassword);
                        }
                        break;
                    }
                    case "list": {
                        if(link.equalsIgnoreCase("product image")) {
                            Wait.forPageReady();
                            Wait.secondsUntilElementPresent("addToList.productImg", 20);
                            Clicks.click(Elements.element("addToList.productImg"));
                            Thread.sleep(2000);
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

    @And("^I fill out the required fields on \"([^\"]*)\" PDP by selecting \"([^\"]*)\"$")
    public void fill_out_giftCard_txtBoxes_on_PDP(String feature, String arg) throws Throwable {
        Random rand = new Random();
        Wait.forPageReady();
        switch (feature) {
            case "E-Gift Card": {
                Wait.secondsUntilElementPresent("pdp.eGiftCardPriceField", 10);
                Assert.assertTrue("Error - Unable to find the Price box on E-Gift Card PDP!",
                        Elements.elementPresent("pdp.eGiftCardPriceField"));
                if(arg.equals("Price Options")) {
                    List<WebElement> priceBtn = Elements.findElements(Elements.element("pdp.eGiftCardPriceBtnOpt"));
                    logger.info("*** Number of Price buttons displayed: " + priceBtn.size());
                    int priceOpt = rand.nextInt(priceBtn.size());
                    priceBtn.get(priceOpt).click();
                }
                else if(arg.contains("$")) {
                    String[] priceTxt = arg.split("\\$",0);
                    TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardPriceField"), priceTxt[1]);
                    logger.info("Value entered in price field: " + priceTxt[1]);
                }
                TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardRecipientEmail"), "macys_test@macys.com");
                TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardEmailTo"), "macys_automation@macys.com");
                TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardEmailFrom"), "macys_qa@macys.com");
                TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardMsg"), "Great Gift!");
                break;
            }
            case "Gift Card": {
                break;
            }
        }
    }

    @And("^I navigate to registry mode$")
    public void navigate_to_registry_mode() throws Throwable {
        List<WebElement> headerNavLinks = Elements.findElements(Elements.element("navigation.headerNavLinks"));
        logger.info("*** Number of headerNavLinks displayed: " + headerNavLinks.size());
        for(WebElement linkElement: headerNavLinks){
            if((linkElement.getText()).equalsIgnoreCase("WEDDING REGISTRY")) {
                logger.info(linkElement.getText() + " is clicked!");
                Thread.sleep(2000);
                Clicks.click(linkElement);
                break;
            }
        }
        Wait.secondsUntilElementPresent("navigation.registry_mode", 20);
        //softly.assertThat((Elements.findElement(Elements.element("navigation.registry_mode")).getText()).equalsIgnoreCase("Back to Macys.com")).as("Mode: Registry").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("navigation.registry_mode")).as("Mode: Registry").isEqualTo(true);
        softly.assertAll();
        logger.info( "User is now in Registry mode!");
        Thread.sleep(2000);
    }

    @And("^I select the first available size$")
    public void select_the_first_available_size() throws Throwable {

        Thread.sleep(2000);
        Clicks.click(Elements.element("pdp.memberFirstAvailSize"));
    }

    @And("^I select a random product from (vertical|horizontal) recommendation panel on (member|master) PDP (site|iship|registry) mode$")
    public void selecting_random_thumbnail_on_recommendation_panel_on_PDP(String panel, String pg, String mode) throws Throwable {

        Random rand = new Random();
        switch (panel.toLowerCase()) {
            case "vertical": {
                Thread.sleep(3000);
                softly.assertThat(Elements.elementPresent("pdp.prosVRPanel")).as("Pros VR panel on" + pg + " PDP " + mode + " mode!").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.prosVRThumbs")).as("Pros VR panel images on" + pg + " PDP " + mode + " mode!").isEqualTo(true);
                List<WebElement> VRThumbs = Elements.findElements(Elements.element("pdp.prosVRThumbs"));
                logger.info("*** Number of Pros VR Thumbnails: " + VRThumbs.size());
                int VRThumbsOpt = rand.nextInt(VRThumbs.size());
                productId = VRThumbs.get(VRThumbsOpt).getAttribute("id");
                Thread.sleep(5000);
                Clicks.javascriptClick(VRThumbs.get(VRThumbsOpt));
                break;
            }
            case "horizontal": {
                scrollToLazyLoadElement("pdp.prosHRPanel");
                Wait.secondsUntilElementPresent("pdp.prosHRPanel", 20);
                Thread.sleep(5000);
                softly.assertThat(Elements.elementPresent("pdp.prosHRPanel")).as("Pros HR panel on" + pg + " PDP " + mode + " mode!").isEqualTo(true);
                List<WebElement> HRThumbs = Elements.findElements(Elements.element("pdp.prosHRThumbs"));
                logger.info("*** Number of Pros HR Thumbnails: " + HRThumbs.size());
                int HRThumbsOpt = rand.nextInt(HRThumbs.size());
                productId = HRThumbs.get(HRThumbsOpt).getAttribute("id");
                Thread.sleep(2000);
                Clicks.javascriptClick(HRThumbs.get(HRThumbsOpt));
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I enter zipcode \"([^\"]*)\" for a BigTicket item and click the submit button on (member|master) PDP$")
    public void entering_zipcode_BigTicket_on_PDP(String zipcode, String pg) throws Throwable {

        softly.assertThat(Elements.elementPresent("pdp.bigTicketZipCode")).as("bigTicketZipCode").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.bigTicketSubmitBtn")).as("bigTicketZipCodeSubmitBtn").isEqualTo(true);
        TextBoxes.typeTextbox(Elements.element("pdp.bigTicketZipCode"), zipcode);
        Clicks.click(Elements.element("pdp.bigTicketSubmitBtn"));

        if(zipcode.equalsIgnoreCase("") || zipcode.equalsIgnoreCase("9999"))
            softly.assertThat((Elements.findElement(Elements.element("pdp.bigTicketZipcodeError")).getText()).equalsIgnoreCase("Please enter a valid zip code.")).isEqualTo(true);
        else if(zipcode.equalsIgnoreCase("99999")) {
            Thread.sleep(2000);
            if(pg.equalsIgnoreCase("member")) {
                softly.assertThat((Elements.findElement(Elements.element("pdp.bigTicketAvailMsg")).getText()).equalsIgnoreCase("To check availability, call 1-800-BUY-MACY (289-6229).")).as("Availability Msg").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.bigTicketAvailMsg")).as("bigTicketAvailMsg").isEqualTo(true);
            }
            else {
                softly.assertThat((Elements.findElement(Elements.element("pdp.masterBigTicketAvailMsg")).getText()).equalsIgnoreCase("To check availability, call 1-800-BUY-MACY (289-6229).")).as("Availability Msg").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.masterBigTicketAvailMsg")).as("masterBigTicketAvailMsg").isEqualTo(true);
            }
        }
        else {
            Thread.sleep(2000);
            if(pg.equalsIgnoreCase("member")) {
                softly.assertThat(Elements.elementPresent("pdp.bigTicketAvailMsg")).as("bigTicketAvailMsg").isEqualTo(true);
                softly.assertThat((Elements.findElement(Elements.element("pdp.bigTicketAvailMsg")).getText()).equalsIgnoreCase("In stock and available for delivery to 94587.")).as("Availability Msg").isEqualTo(true);
            }
            else {
                softly.assertThat(Elements.elementPresent("pdp.masterBigTicketAvailMsg")).as("masterBigTicketAvailMsg").isEqualTo(true);
                softly.assertThat((Elements.findElement(Elements.element("pdp.masterBigTicketAvailMsg")).getText()).equalsIgnoreCase("In stock and available for delivery to 94587.")).as("Availability Msg").isEqualTo(true);
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I select a random product from \"([^\"]*)\" on (member|master) PDP (site|iship|registry) mode$")
    public void selecting_random_product_from_sections_on_PDP(String arg, String pg, String mode) throws Throwable {

        Random rand = new Random();
        if(arg.equalsIgnoreCase("SponsoredItems")) {
            Wait.secondsUntilElementPresent("pdp.sponsoredItemsList", 10);
            List<WebElement> sponsItems = Elements.findElements(Elements.element("pdp.sponsoredItemsList"));
            logger.info("*** Number of Sponsored Items: " + sponsItems.size());
            int sponsItemsOpt = rand.nextInt(sponsItems.size());
            productId = sponsItems.get(sponsItemsOpt).getAttribute("data-hl-productid");
            Thread.sleep(2000);
            Clicks.click(sponsItems.get(sponsItemsOpt));
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @And("^I select a (Type|) on (member|master) PDP (site|iship|registry) mode$")
    public void selecting_features_on_PDP(String arg, String pg, String mode) throws Throwable {

        switch(arg) {
            case "Type": {
                if(Elements.elementPresent("pdp.productType")) {
                    Random rand = new Random();
                    Wait.secondsUntilElementPresent("pdp.productType", 10);
                    softly.assertThat(Elements.elementPresent("pdp.productType")).as("productType").isEqualTo(true);
                    List<WebElement> Types = Elements.findElements(Elements.element("pdp.productTypeList"));
                    logger.info("*** Number of Types displayed: " + Types.size());
                    int typeOpt = rand.nextInt(Types.size());
                    Types.get(typeOpt).click();
                }
                break;
            }
        }
        Thread.sleep(2000);
        softly.assertAll();
    }

    @Then("^I verify basic components of \"([^\"]*)\" PDP in \"([^\"]*)\" mode$")
    public void I_verify_basic_components_of_PDP(String productType, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(productType + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!\n"));
            e.printStackTrace();
        }
        String productTitle = Elements.findElement(Elements.element("pdp.productTitle")).getText();
        softly.assertThat(productTitle.isEmpty()).as("productTitle").isEqualTo(false);
        String webId = Elements.findElement(Elements.element("pdp.web_id")).getText();
        Clicks.hoverForSelection(Elements.element("pdp.web_id"));
        softly.assertThat(webId.contains(productId)).as("productId").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.productQuantity")).as("productQuantity").isEqualTo(true);
        switch (mode) {
            case "site": {
                switch (productType) {
                    case "member": {
                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("add_to_list button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_registry")).as("add_to_registry a").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);

                        scrollToLazyLoadElement("pdp.bottomTabs");
                        Thread.sleep(3000);
                        softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("bottomTabs").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);
                        logger.info(String.format("** " + productType + " PDP is loading as expected with all PDP components displayed in " + mode + " mode!\n"));
                        break;
                    }
                    case "master": {
                        softly.assertThat(Elements.elementPresent("quick_bag.quickbag_header")).as("QB Header").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsBtn")).as("Shop Collection Button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterProductMemberQuantity")).as("Quantity").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("masterProductMemberAddToBag button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("masterProductMemberAddToList button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_registry")).as("masterProductMemberAddToRegistry a").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("masterProductMainImage").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterProductZoomerButton")).as("masterProductZoomerButton").isEqualTo(false);
                        Clicks.hoverForSelection(Elements.element("pdp.product_main_image"));

                        Thread.sleep(2000);
                        Assert.assertFalse(Elements.elementPresent("pdp.socialIconFacebook"));
                        softly.assertThat(Elements.elementPresent("pdp.socialIconTweeter")).as("socialIconTweeter").isEqualTo(false);
                        softly.assertThat(Elements.elementPresent("pdp.socialIconGoogleshare")).as("socialIconGoogleshare").isEqualTo(false);

                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("socialIconPinterest").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("socialIconEmail").isEqualTo(true);

                        Clicks.click(Elements.element("pdp.bottomTabs"));
                        Wait.secondsUntilElementPresent("pdp.collectionItemsTab", 20);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsTab")).as("collectionItemsTab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsPanel")).as("collectionItemsPanel").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("bottomTabs").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterReviewsTab")).as("PDP Reviews Tab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterQATab")).as("PDP QA Tab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterShippingReturnTab")).as("Shipping & Return Tab").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.masterSpecialOfferTab")).as("masterSpecialOfferTab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);

                        logger.info(String.format("** " + productType + " PDP is loading as expected with all PDP components displayed in " + mode + " mode!\n"));
                        break;
                    }
                }
                break;
            }
            case "iship": {
                Assert.assertTrue(Elements.elementPresent("pdp.iShipCountryFlag"));
                switch (productType) {
                    case "member": {
//                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconFacebook")).as("socialIconFacebook").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconTweeter")).as("socialIconTweeter").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconPinterest")).as("socialIconPinterest").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconGoogleshare")).as("socialIconGoogleshare").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconEmail")).as("socialIconEmail").isEqualTo(true);
//
//                        Wait.secondsUntilElementPresent("pdp.prosHRPanel", 20);
//                        softly.assertThat(Elements.elementPresent("pdp.masterPDPTabs")).as("PDP Tabs").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.iShipMemberReviewTab")).as("PDP Reviews Tab").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.masterQATab")).as("PDP QA Tab").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.masterShippingReturnTab")).as("Shipping & Return Tab").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.prosVRPanel")).as("Pros VR Panel").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.prosHRPanel")).as("Pros HR Pane").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pdpAdsense")).as("Google Adsense").isEqualTo(true);
//                        logger.info(String.format("** " + productType + " PDP is loading as expected with all PDP components displayed in " + mode + " mode!\n"));

                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);

                        scrollToLazyLoadElement("pdp.bottomTabs");
                        Thread.sleep(3000);
                        softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("bottomTabs").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);
//                        scrollToLazyLoadElement("pdp.googleAdsense");
//                        softly.assertThat(Elements.elementPresent("pdp.googleAdsense")).as("googleAdsense").isEqualTo(true);
                        logger.info(String.format("** " + productType + " PDP is loading as expected with all PDP components displayed in " + mode + " mode!\n"));
                        break;
                    }
                    case "master": {
                        softly.assertThat(Elements.elementPresent("quick_bag.quickbag_header")).as("QB Header").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsBtn")).as("Shop Collection Button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterProductMemberQuantity")).as("Quantity").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("masterProductMemberAddToBag button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("masterProductMainImage").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterProductZoomerButton")).as("masterProductZoomerButton").isEqualTo(false);
                        Clicks.hoverForSelection(Elements.element("pdp.product_main_image"));

                        Thread.sleep(2000);
                        Assert.assertFalse(Elements.elementPresent("pdp.socialIconFacebook"));
                        softly.assertThat(Elements.elementPresent("pdp.socialIconTweeter")).as("socialIconTweeter").isEqualTo(false);
                        softly.assertThat(Elements.elementPresent("pdp.socialIconGoogleshare")).as("socialIconGoogleshare").isEqualTo(false);

                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("socialIconPinterest").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("socialIconEmail").isEqualTo(true);

                        Clicks.click(Elements.element("pdp.bottomTabs"));
                        Wait.secondsUntilElementPresent("pdp.collectionItemsTab", 20);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsTab")).as("collectionItemsTab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsPanel")).as("collectionItemsPanel").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("bottomTabs").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterReviewsTab")).as("PDP Reviews Tab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterQATab")).as("PDP QA Tab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterShippingReturnTab")).as("Shipping & Return Tab").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.masterSpecialOfferTab")).as("masterSpecialOfferTab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);

                        logger.info(String.format("** " + productType + " PDP is loading as expected with all PDP components displayed in " + mode + " mode!\n"));
                        break;
                    }
                }
                break;
            }
            case "registry": {
                switch (productType) {
                    case "member": {
                        softly.assertThat(Elements.elementPresent("pdp.addToRegistryRegistryMode")).as("addToRegistryRegistryMode").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("add_to_list button").isEqualTo(false);
                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);

                        scrollToLazyLoadElement("pdp.bottomTabs");
                        Thread.sleep(3000);
                        softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("bottomTabs").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);
//                        scrollToLazyLoadElement("pdp.googleAdsense");
//                        softly.assertThat(Elements.elementPresent("pdp.googleAdsense")).as("googleAdsense").isEqualTo(true);
                        logger.info(String.format("** " + productType + " PDP is loading as expected with all PDP components displayed in " + mode + " mode!\n"));
                        break;
                    }
                    case "master": {
//                        softly.assertThat(Elements.elementPresent("pdp.masterRegistryATBButton")).as("add_to_bag button").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.masterRegistryAddToRegistryButton")).as("add_to_registry button").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.masterRegistryAddToListButton")).as("add_to_list button").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.masterRegistryMainImage")).as("product_main_image").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconFacebook")).as("socialIconFacebook").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconTweeter")).as("socialIconTweeter").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconPinterest")).as("socialIconPinterest").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconGoogleshare")).as("socialIconGoogleshare").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.socialIconEmail")).as("email_icon").isEqualTo(true);
//                        scrollToLazyLoadElement("pdp.pdpAdsense");
//                        Thread.sleep(3000);
//                        softly.assertThat(Elements.elementPresent("pdp.pdpAdsense")).as("googleAdsense").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("quick_bag.quickbag_header")).as("QB Header").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsBtn")).as("Shop Collection Button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterProductMemberQuantity")).as("Quantity").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("masterProductMemberAddToBag button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("masterProductMemberAddToList button").isEqualTo(false);
                        softly.assertThat(Elements.elementPresent("pdp.addToRegistryRegistryMode")).as("masterProductMemberAddToRegistry a").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("masterProductMainImage").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterProductZoomerButton")).as("masterProductZoomerButton").isEqualTo(false);
                        Clicks.hoverForSelection(Elements.element("pdp.product_main_image"));

                        Thread.sleep(2000);
                        Assert.assertFalse(Elements.elementPresent("pdp.socialIconFacebook"));
                        softly.assertThat(Elements.elementPresent("pdp.socialIconTweeter")).as("socialIconTweeter").isEqualTo(false);
                        softly.assertThat(Elements.elementPresent("pdp.socialIconGoogleshare")).as("socialIconGoogleshare").isEqualTo(false);

                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("socialIconPinterest").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("socialIconEmail").isEqualTo(true);

                        Clicks.click(Elements.element("pdp.bottomTabs"));
                        Wait.secondsUntilElementPresent("pdp.collectionItemsTab", 20);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsTab")).as("collectionItemsTab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.collectionItemsPanel")).as("collectionItemsPanel").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("bottomTabs").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterReviewsTab")).as("PDP Reviews Tab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterQATab")).as("PDP QA Tab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.masterShippingReturnTab")).as("Shipping & Return Tab").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.masterSpecialOfferTab")).as("masterSpecialOfferTab").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);
                        logger.info(String.format("** " + productType + " PDP is loading as expected with all PDP components displayed in " + mode + " mode!\n"));
                        break;
                    }
                }
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the RVI panel on \"([^\"]*)\" PDP \"([^\"]*)\" mode$")
    public void I_verify_rvi_panel_on_PDP(String pgType, String mode) throws Throwable {
        Thread.sleep(7000);
        Wait.secondsUntilElementPresent("pdp.RVIpanel", 10);
        Assert.assertTrue(Elements.elementPresent("pdp.RVIheader"));
        Assert.assertTrue(Elements.elementPresent("pdp.RVIscroller"));
//        Assert.assertTrue(Elements.elementPresent("pdp.RVIthumbPad"));

        if(mode.equals("site")) {
            if(pgType.equals("member")) {
                Assert.assertTrue(Elements.elementPresent("pdp.RVIpanel"));
                Wait.secondsUntilElementPresent("pdp.memberRVIeditButton", 10);
                Assert.assertTrue(Elements.elementPresent("pdp.memberRVIeditButton"));
                Clicks.click("pdp.memberRVIeditButton");
                Wait.secondsUntilElementPresent("pdp.RVIremoveButton", 10);
                softly.assertThat(Elements.elementPresent("pdp.RVIremoveButton")).as("RVI Remove Button").isEqualTo(true);
                Clicks.click("pdp.RVIremoveButton");
                Clicks.click("pdp.memberRVIeditButton");
            }
            else {
                Wait.secondsUntilElementPresent("pdp.masterRVIeditButton", 10);
                Assert.assertTrue(Elements.elementPresent("pdp.masterRVIeditButton"));
                Clicks.click("pdp.masterRVIeditButton");
                Wait.secondsUntilElementPresent("pdp.RVIremoveButton", 10);
                softly.assertThat(Elements.elementPresent("pdp.RVIremoveButton")).as("RVI Remove Button").isEqualTo(true);
                Clicks.click("pdp.RVIremoveButton");
                Clicks.click("pdp.masterRVIeditButton");
            }
        }
        else if(mode.equals("iShip")) {
            Wait.secondsUntilElementPresent("pdp.iShipRVIeditButton", 10);
            Assert.assertTrue(Elements.elementPresent("pdp.iShipRVIeditButton"));
            Clicks.click("pdp.iShipRVIeditButton");
            Wait.secondsUntilElementPresent("pdp.RVIremoveButton", 10);
            softly.assertThat(Elements.elementPresent("pdp.RVIremoveButton")).as("RVI Remove Button").isEqualTo(true);
            Clicks.click("pdp.RVIremoveButton");
            Clicks.click("pdp.iShipRVIeditButton");
        }
        else if(mode.equals("Registry")) {
            Assert.assertTrue(Elements.elementPresent("pdp.registryRVIeditButton"));
            Clicks.click("pdp.registryRVIeditButton");
            Wait.secondsUntilElementPresent("pdp.RVIremoveButton", 10);
            softly.assertThat(Elements.elementPresent("pdp.registryRVIremoveButton")).as("RVI Remove Button").isEqualTo(true);
            Clicks.click("pdp.registryRVIremoveButton");
            Clicks.click("pdp.registryRVIeditButton");
        }
        logger.info(String.format("** RVI panel verified successfully!\n"));
        Thread.sleep(2000);
        softly.assertAll();
    }

    @Then("^I verify the navigation to BrandName page$")
    public void I_verify_the_navigation_to_BrandName_page() throws Throwable {
        Wait.secondsUntilElementPresent("pdp.brandNameLink", 5);
        softly.assertThat(Elements.elementPresent("pdp.brandNameLink")).as("brandNameLink").isEqualTo(true);
        String productTitle = Elements.findElement(Elements.element("pdp.productTitle")).getText();
        String brandName[] = productTitle.split("\n",0);
        logger.info(String.format("Brand Name: " + brandName[0]));
        Clicks.click("pdp.brandNameLink");
        Thread.sleep(3000);
        Navigate.switchWindow(1);
        String PageSource = WebDriverManager.getWebDriver().getPageSource();
        softly.assertThat((PageSource.contains(brandName[0])) && (PageSource.contains("<type>catalog - searchResults</type>")) ||
                (PageSource.contains("<type>catalog - subSplash</type>"))).as("PageSource contains required elements").isEqualTo(true);
        softly.assertAll();
    }

    @Then("^I verify hitting \"([^\"]*)\" PDP$")
    public void I_verify_hitting_PDP(String svr) throws Throwable {

        Thread.sleep(5000);
        if(svr.equals("QA")) {
            String PDPSource = WebDriverManager.getWebDriver().getPageSource();
            PDPSource = PDPSource.split("<site>")[1].split("</site>")[0];
            softly.assertThat(PDPSource).as("PDP Page Source").isEqualTo("QA");
            softly.assertAll();
        }
        else if(svr.equals("heroku")) {
            String PDPSource = WebDriverManager.getWebDriver().getPageSource();
            softly.assertThat(PDPSource.contains("<site>heroku</site>")).as("PDP Page Source").isEqualTo(true);
            softly.assertAll();
        }
    }

    @Then("^I verify the zoomer on member PDP$")
    public void I_verify_the_zoomer_on_member_PDP() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.product_main_image", 20);
        } catch (Exception e) {
            System.out.println("\nProduct main image is not displayed on member PDP!");
            logger.warning(String.format("Product main image is not displayed on member PDP!\n"));
            e.printStackTrace();
        }
        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
        Clicks.hoverForSelection(Elements.element("pdp.product_main_image"));
        Thread.sleep(3000);
        logger.info(String.format("** Product main image displayed and zoomer works as expected on member PDP!\n"));

        softly.assertAll();
    }

    @Then("^I verify the GIFTNOW on member PDP$")
    public void I_verify_the_giftnow_on_member_PDP() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.giftNowIcon", 20);
        } catch (Exception e) {
            System.out.println("\nGIFTNOW is not displayed on member PDP!");
            logger.warning(String.format("GIFTNOW is not displayed on member PDP!\n"));
            e.printStackTrace();
        }

        softly.assertThat(Elements.elementPresent("pdp.giftNowIcon")).as("giftNowIcon div").isEqualTo(true);
        Clicks.click(Elements.element("pdp.giftNowIcon"));
        Thread.sleep(3000);
        logger.info(String.format("** GIFTNOW displayed as expected on member PDP!\n"));

        softly.assertAll();
    }

    @Then("^I verify that \"([^\"]*)\" is \"([^\"]*)\" on PDP \"([^\"]*)\" mode")
    public void verify_webCollage_Videos_on_PDP(String feature, String arg, String mode) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        if(feature.equals("webCollage")) {
            scrollToLazyLoadElement("pdp.bottomTabs");
            Thread.sleep(3000);
            String txt = Elements.findElement(Elements.element("pdp.firstTab")).getText();
            softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("Tabs").isEqualTo(true);

            switch (arg) {
                case "displayed": {
                    softly.assertThat(txt.toLowerCase().equals("from the manufacturer")).as("WebCollage Tab").isEqualTo(true);
                    softly.assertThat(Elements.elementPresent("pdp.webCollageVideo")).as("webCollageVideo").isEqualTo(true);
//                logger.info(String.format("** Web Collage Video button clicked!\n"));
                    break;
                }
                case "not displayed": {
                    softly.assertThat(txt.toLowerCase().contains("from the manufacturer")).as("WebCollage Tab").isEqualTo(false);
                    softly.assertThat(txt.toLowerCase().equals("reviews")).as("Reviews Tab").isEqualTo(true);
                    break;
                }
            }
        }
        else if(feature.equals("Video")) {
            if(!(mode.equalsIgnoreCase("iship"))) {
                Assert.assertTrue(Elements.elementPresent("pdp.videoBtn"));
                Clicks.click(Elements.element("pdp.videoBtn"));
                Thread.sleep(5000);
            }
            logger.info(String.format("** Video button clicked on PDP " + mode + " mode!\n"));
        }
    }

    @Then("^I verify collection member products on master pdp$")
    public void verify_collection_member_products_on_master_pdp() throws Throwable {
        Wait.secondsUntilElementPresent("pdp.collectionItemsPanel", 30);
        softly.assertThat(Elements.elementPresent("pdp.collectionItemsPanel")).as("Collection Items").isEqualTo(true);
        softly.assertAll();
    }

    @Then("^I verify Q&A tab is not displayed on PDP$")
    public void verify_QA_tab_is_not_displayed_on_PDP() throws Throwable {
        scrollToLazyLoadElement("pdp.bottomTabs");
        Wait.secondsUntilElementPresent("pdp.QATab", 10);
        softly.assertThat(Elements.elementPresent("pdp.QAtabHeader")).as("Q&A Tab").isEqualTo(false);
        Assert.assertFalse(Elements.elementPresent("pdp.QATab"));
        softly.assertAll();
    }

    @Then("^I verify navigating to \"([^\"]*)\" PDP \"([^\"]*)\" mode$")
    public void verify_navigating_to_PDP(String page, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(page + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!\n"));
            e.printStackTrace();
        }
        Assert.assertNotNull(Elements.findElement(Elements.element("pdp.productTitle")).getText());
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        scrollToLazyLoadElement("pdp.bottomTabs");
                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("add_to_list button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.productQuantity")).as("product_main_image").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        scrollToLazyLoadElement("pdp.bottomTabs");
                        Assert.assertTrue(Elements.elementPresent("pdp.iShipCountryFlag"));
                        Assert.assertFalse(Elements.elementPresent("pdp.add_to_list"));
                        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.productQuantity")).as("product_main_image").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
        softly.assertAll();
    }

    @Then("^I verify that guest user is navigated to \"([^\"]*)\" signin page$")
    public void I_verify_that_guest_user_is_navigated_to_signin_page(String arg) throws Throwable {
        Wait.forPageReady();
        switch(arg) {
            case "Account": {
                String url = WebDriverManager.getCurrentUrl();
                Thread.sleep(5000);
                softly.assertThat(url.contains("/account/signin")).as("/account/signin").isEqualTo(true);
                break;
            }
            case "Registry": {
                String url = WebDriverManager.getCurrentUrl();
                Thread.sleep(5000);
                softly.assertThat(url.contains("/registry/wedding/regsignin?fromView=PDP")).as("registry/wedding/regsignin").isEqualTo(true);
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the \"([^\"]*)\" message on (member|master) PDP in (site|iship|registry) mode$")
    public void verify_various_messages_on_PDP(String arg, String page, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(page + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!\n"));
            e.printStackTrace();
        }
        Assert.assertNotNull(Elements.findElement(Elements.element("pdp.productTitle")).getText());
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        if(arg.equals("availibility")) {
                            Assert.assertFalse(Elements.elementPresent("pdp.availabilityIcon"));
                            Assert.assertFalse(Elements.elementPresent("pdp.inStockMsg"));

                            softly.assertThat(Elements.elementPresent("pdp.availabilityMsg")).as("availabilityMsg").isEqualTo(true);
                            String availMsg =  Elements.findElement(Elements.element("pdp.availabilityMsg")).getText();
                            Assert.assertTrue(availMsg.equals("Availability"));
                            String colorSizeMsg =  Elements.findElement(Elements.element("pdp.availMsgSizeNotSelected")).getText();
                            Assert.assertTrue(colorSizeMsg.equals("Select color & size above."));
                            Thread.sleep(2000);
                            softly.assertAll();
                        }
                        else if(arg.equals("in stock")) {
                            softly.assertThat(Elements.elementPresent("pdp.availabilityIcon")).as("availabilityIcon").isEqualTo(true);
                            String availMsg =  Elements.findElement(Elements.element("pdp.shipMsgHead")).getText();
                            Assert.assertTrue(availMsg.equals("Ship"));
                            String inStockMsg =  Elements.findElement(Elements.element("pdp.inStockMsg")).getText();
                            Assert.assertTrue(inStockMsg.equals("in stock"));
                            Thread.sleep(2000);
                            softly.assertAll();
                        }
                        else if(arg.equals("A2L session expired")) {
                            Thread.sleep(5000);
                            softly.assertThat(Elements.elementPresent("pdp.addToListSessionTimeoutPoppup")).as("addToListSessionTimeoutPoppup").isEqualTo(true);
                            softly.assertThat((Elements.findElement(Elements.element("pdp.addToListSessionTimeoutPoppup")).getText()).contains("Your session has expired. Please")).isEqualTo(true);
                            Thread.sleep(2000);
                            softly.assertAll();
                        }
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
    }

    @Then("^I verify \"([^\"]*)\" functionality on (member|master) PDP (site|iship|registry) mode$")
    public void verify_functionalities_on_PDP(String arg, String pg, String mode) throws Throwable {

        switch(mode) {
            case "site": {
                switch(pg) {
                    case "member": {
                        switch(arg) {
                            case "TrueFit": {
                                Wait.secondsUntilElementPresent("pdp.find_true_fit_link", 10);
                                softly.assertThat(Elements.elementPresent("pdp.find_true_fit_link")).as("true_fit link").isEqualTo(true);
                                Clicks.click(Elements.element("pdp.find_true_fit_link"));

                                Navigate.switchWindow(1);
                                Wait.secondsUntilElementPresent("pdp.find_true_fit_frame", 10);
                                softly.assertThat(Elements.elementPresent("pdp.find_true_fit_frame")).as("true_fit overlay").isEqualTo(true);
//                                softly.assertThat(Elements.elementPresent("pdp.find_true_fit_text_field")).as("find_true_fit_text_field").isEqualTo(true);
                                break;
                            }
                            case "Type": {
                                Random rand = new Random();
                                Wait.secondsUntilElementPresent("pdp.productType", 10);
                                softly.assertThat(Elements.elementPresent("pdp.productType")).as("productType").isEqualTo(true);
                                List<WebElement> Types = Elements.findElements(Elements.element("pdp.productTypeList"));
                                logger.info("*** Number of Types displayed: " + Types.size());
                                int typeOpt = rand.nextInt(Types.size());
                                Types.get(typeOpt).click();
                                Thread.sleep(1000);
                                typeOpt = rand.nextInt(Types.size());
                                Types.get(typeOpt).click();
                                Thread.sleep(1000);
                                typeOpt = rand.nextInt(Types.size());
                                Types.get(typeOpt).click();
                                break;
                            }
                        }
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                break;
            }
            case "registry": {
                break;
            }
        }
        Thread.sleep(2000);
        softly.assertAll();
    }

    @Then("^I verify the \"([^\"]*)\" FOB tab on PDP$")
    public void verify_the_FOB_tab_on_PDP(String arg) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.FOB_Tabs", 20);
        String url = WebDriverManager.getCurrentUrl();
        if(arg.equals("women"))
            softly.assertThat(url.contains("/shop/womens-clothing/womens-activewear")).as("CurrentPage URL is not as expected").isEqualTo(true);
        else if(arg.equals("men"))
            softly.assertThat(url.contains("/shop/mens-clothing/mens-activewear")).as("CurrentPage URL is not as expected").isEqualTo(true);
        else if(arg.equals("beauty"))
            softly.assertThat(url.contains("/shop/makeup-")).as("CurrentPage URL is not as expected").isEqualTo(true);
        else if(arg.equals("Home Decor"))
            softly.assertThat(url.contains("/shop/wedding-registry/home-decor/")).as("CurrentPage URL is not as expected").isEqualTo(true);
        else if(arg.equals("Dining"))
            softly.assertThat(url.contains("/shop/wedding-registry/dining-entertaining/")).as("CurrentPage URL is not as expected").isEqualTo(true);
        softly.assertAll();
    }

    @Then("^I verify the basic elements of the \"([^\"]*)\" PDP$")
    public void verify_the_basic_elements_of_the_PDP(String arg) throws Throwable {
        Wait.forPageReady();
        String url = WebDriverManager.getCurrentUrl();
        switch (arg) {
            case "CHANEL": {
                Wait.secondsUntilElementPresent("chanel.brandLogo", 20);
                softly.assertThat(url.contains("/chanel")).isEqualTo(true);
                softly.assertThat(Elements.elementPresent("chanel.brandLogo")).as("brandLogo").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("chanel.brandName")).as("brandName").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("chanel.productDescription")).as("productDescription").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("chanel.productQuantity")).as("productQuantity").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("chanel.addToBagBtn")).as("addToBagBtn").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("chanel.addToListBtn")).as("addToListBtn").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("chanel.zoomerBtn")).as("zoomerBtn").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("chanel.emailIcon")).as("emailIcon").isEqualTo(true);

                Clicks.click(Elements.element("chanel.zoomerBtn"));
                Thread.sleep(1000);
                Clicks.click(Elements.element("chanel.zoomerCloseBtn"));

                Thread.sleep(1000);
                Clicks.click(Elements.element("chanel.addToListBtn"));
                Wait.secondsUntilElementPresent("chanel.addToListOverlay", 10);
                Clicks.click(Elements.element("chanel.addToListOverlayClose"));

                Thread.sleep(1000);
                Clicks.click(Elements.element("chanel.emailIcon"));
                Wait.secondsUntilElementPresent("chanel.emailFriendsOverlay", 10);
                softly.assertThat(Elements.elementPresent("chanel.emailFriendsOverlay")).as("emailFriendsOverlay").isEqualTo(true);
                Thread.sleep(1000);
                Clicks.click(Elements.element("chanel.emailOverlayClose"));
                break;
            }
            case "E-Gift Card": {
                Wait.secondsUntilElementPresent("pdp.productTitle", 20);
                softly.assertThat(((Elements.findElement(Elements.element("pdp.productDescription")).getText())).contains("E-Gift Card")).isEqualTo(true);
                softly.assertThat(url.contains("e-gift")).isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.eGiftCardAddToBagBtn")).as("add_to_bag button").isEqualTo(true);
                Assert.assertTrue("Error - Unable to find the Price box on E-Gift Card PDP!",
                        Elements.elementPresent("pdp.eGiftCardPriceField"));
                Assert.assertTrue("Error - Unable to find the RecipientEmail box on E-Gift Card PDP!",
                        Elements.elementPresent("pdp.eGiftCardRecipientEmail"));
                Assert.assertTrue("Error - Unable to find the EmailTo box on E-Gift Card PDP!",
                        Elements.elementPresent("pdp.eGiftCardEmailTo"));
                Assert.assertTrue("Error - Unable to find the EmailFrom box on E-Gift Card PDP!",
                        Elements.elementPresent("pdp.eGiftCardEmailFrom"));
                Assert.assertTrue("Error - Unable to find the eGiftCardMsg box on E-Gift Card PDP!",
                        Elements.elementPresent("pdp.eGiftCardMsg"));
                softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("add_to_list button").isEqualTo(false);
                softly.assertThat(Elements.elementPresent("pdp.add_to_registry")).as("add_to_registry a").isEqualTo(false);
                softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);

                scrollToLazyLoadElement("pdp.shippingNreturnsHeader");
                softly.assertThat(Elements.elementPresent("pdp.shippingNreturnsExpand")).as("shippingNreturnsExpand").isEqualTo(true);
                Clicks.click(Elements.element("pdp.shippingNreturnsExpand"));
                Thread.sleep(3000);
                softly.assertThat(Elements.elementPresent("pdp.shippingNreturnsCollapsed")).as("shippingNreturnsCollapsed").isEqualTo(true);
                Clicks.click(Elements.element("pdp.shippingNreturnsCollapsed"));
                softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);
                scrollToLazyLoadElement("pdp.googleAdsense");
//                softly.assertThat(Elements.elementPresent("pdp.googleAdsense")).as("googleAdsense").isEqualTo(true);
                break;
            }
            case "Gift Card": {
                softly.assertThat(url.contains("/")).isEqualTo(true);
                break;
            }
            case "BigTicket": {
                softly.assertThat((Elements.findElement(Elements.element("pdp.productTitle")).getText()).isEmpty()).as("productTitle").isEqualTo(false);
                softly.assertThat(Elements.elementPresent("pdp.bigTicketZipCode")).as("bigTicketZipCode").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.bigTicketSubmitBtn")).as("bigTicketZipCodeSubmitBtn").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.add_to_bag")).as("add_to_bag button").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.add_to_list")).as("add_to_list button").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.add_to_registry")).as("add_to_registry a").isEqualTo(false);
//                softly.assertThat(Elements.elementPresent("pdp.giftNowIcon")).as("giftNowIcon").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.productAltImages")).as("productAltImages").isEqualTo(true);

                scrollToLazyLoadElement("pdp.bottomTabs");
                Thread.sleep(3000);
                softly.assertThat(Elements.elementPresent("pdp.bottomTabs")).as("bottomTabs").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.seoContainer")).as("seoContainer").isEqualTo(true);
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the layout of gift cards page$")
    public void verify_the_layout_of_gift_cards_page() throws Throwable {
        Wait.secondsUntilElementPresent("giftCard.leftNavCat", 20);
        Assert.assertTrue(Elements.elementPresent("giftCard.leftNavCat"));
        Assert.assertTrue(Elements.elementPresent("giftCard.navTitleHeader"));
        Assert.assertEquals((Elements.findElement(Elements.element("giftCard.navTitleHeader")).getText()),"Gift Cards");

        String expectedTxt[] = {"Giftnow","E-gift Cards","Gift Cards","Related Content"};
        List<WebElement> elementList = Elements.findElements(By.xpath(".//*[@id='firstNavSubCat']/li/span"));
        int index=0;
        for(WebElement webElementList: elementList) {
            logger.info(String.format(webElementList.getText()));
            softly.assertThat(webElementList.getText().toLowerCase().contains(expectedTxt[index].toLowerCase())).isEqualTo(true);
            index++;
        }
    }

    @Then("^I verify the \"([^\"]*)\" message under shipping & returns tab$")
    public void verify_the_message_under_shippingNreturns_tab(String msg) throws Throwable {
        String actualTxt;
        Wait.secondsUntilElementPresent("pdp.shippingNreturnsHeader", 20);
        scrollToLazyLoadElement("pdp.shippingNreturnsHeader");
        Clicks.click(Elements.element("pdp.shippingNreturnsHeader"));

        switch (msg) {
            case "This item qualifies for Free Shipping!": {
                actualTxt = Elements.findElement(Elements.element("pdp.shippingMsg")).getText();
                logger.info(String.format("Actual Message: " + actualTxt));
                softly.assertThat(actualTxt.contains(msg)).as("Free Sheeping message").isEqualTo(true);
                break;
            }
            case "This item qualifies for Free Shipping with minimum purchase!": {
                actualTxt = Elements.findElement(Elements.element("pdp.shippingMsg")).getText();
                logger.info(String.format("Actual Message: " + actualTxt));
                softly.assertThat(actualTxt.equals(msg)).as("Free Sheeping message w/ minimum purchase").isEqualTo(true);
                break;
            }
            case "This item is assigned a shipping surcharge fee of $4.00 based on size/weight and/or special shipping requirements": {
                actualTxt = Elements.findElement(Elements.element("pdp.shippingSurChargeMsg")).getText();
                logger.info(String.format("Actual Message: " + actualTxt));
                softly.assertThat(actualTxt.equals(msg)).as("Shipping surcharge message").isEqualTo(true);
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the links under shipping & returns tab on (member|master) PDP (site|iship|registry) mode$")
    public void verify_the_links_under_shippingNreturns_tab(String pg, String mode) throws Throwable {

        if(pg.equalsIgnoreCase("member")) {
            Wait.secondsUntilElementPresent("pdp.shippingNreturnsHeader", 20);
            scrollToLazyLoadElement("pdp.shippingNreturnsHeader");
            Clicks.click(Elements.element("pdp.shippingNreturnsHeader"));

            Wait.secondsUntilElementPresent("pdp.shippingNreturnsCollapsed", 5);
            Clicks.click(Elements.element("pdp.shippingNreturnsCollapsed"));

            Wait.secondsUntilElementPresent("pdp.shippingNreturnsExpand", 5);
            Clicks.click(Elements.element("pdp.shippingNreturnsExpand"));
        }

        Wait.secondsUntilElementPresent("pdp.shippingLink", 5);
        Clicks.click(Elements.element("pdp.shippingLink"));

        Navigate.switchWindow(1);
        Wait.secondsUntilElementPresent("pdp.shippingNreturnPolicy", 10);
        if(!(mode.equalsIgnoreCase("iship"))) {
            if(Elements.elementPresent(Elements.element("pdp.shippingNreturnPolicyReg")))
                softly.assertThat((Elements.findElement(Elements.element("pdp.shippingNreturnPolicyReg")).getText()).equalsIgnoreCase("What is Macy's shipping policy?")).as("What is Macy's shipping policy?").isEqualTo(true);
            else
                softly.assertThat((Elements.findElement(Elements.element("pdp.shippingNreturnPolicy")).getText()).equalsIgnoreCase("What is Macy's shipping policy?")).as("What is Macy's shipping policy?").isEqualTo(true);
        }
        else
            softly.assertThat((Elements.findElement(Elements.element("pdp.shippingNreturnPolicy")).getText()).equalsIgnoreCase("International Shipping")).as("International Shipping").isEqualTo(true);
        Navigate.switchWindowClose();

        Thread.sleep(1000);
        Clicks.click(Elements.element("pdp.returnLink"));

        Navigate.switchWindow(1);
        Wait.secondsUntilElementPresent("pdp.shippingNreturnPolicy", 10);
        if(!(mode.equalsIgnoreCase("iship"))) {
            if(Elements.elementPresent(Elements.element("pdp.shippingNreturnPolicyReg")))
                softly.assertThat((Elements.findElement(Elements.element("pdp.shippingNreturnPolicyReg")).getText()).equalsIgnoreCase("What is Macy's return policy?")).as("What is Macy's return policy?").isEqualTo(true);
            else
                softly.assertThat((Elements.findElement(Elements.element("pdp.shippingNreturnPolicy")).getText()).equalsIgnoreCase("What is Macy's return policy?")).as("What is Macy's return policy?").isEqualTo(true);
        }
        else
            softly.assertThat((Elements.findElement(Elements.element("pdp.shippingNreturnPolicy")).getText()).equalsIgnoreCase("International Returns")).as("International Returns").isEqualTo(true);
        Navigate.switchWindowClose();

        Thread.sleep(1000);
        if(!(mode.equalsIgnoreCase("iship"))) {
            Clicks.click(Elements.element("pdp.shippingTab"));
            Wait.forPageReady();
            softly.assertThat((WebDriverManager.getCurrentUrl()).contains("/free-shipping/index")).as("Exclusions & Details Link or page").isEqualTo(true);
        }
        else {
            Clicks.click(Elements.element("pdp.iShipExclusionsDetailsLink"));
            Wait.forPageReady();
            softly.assertThat((WebDriverManager.getCurrentUrl()).contains("/app/answers/detail/")).as("iShip Exclusions & Details Link or page").isEqualTo(true);
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify functionality of (eligible|non_eligible) BOPS product on (member|master) PDP$")
    public void verify_BOPS_functionality_on_PDP(String arg, String page) throws Throwable {
        Thread.sleep(3000);
        if(page.equals("member")) {
            switch (arg) {
                case "eligible": {
                    Thread.sleep(5000);
                    Wait.secondsUntilElementPresent("pdp.pickupInStoreIcon", 20);
                    softly.assertThat(Elements.elementPresent("pdp.pickupInStoreIcon")).as("BOPS Icon").isEqualTo(true);
                    softly.assertThat(Elements.elementPresent("pdp.pickupInStoreText")).as("BOPS Text").isEqualTo(true);
                    softly.assertThat((Elements.findElement(Elements.element("pdp.pickupInStoreText")).getText()).equals("Pick up in store")).isEqualTo(true);
                    softly.assertThat(Elements.elementPresent("pdp.pickupInStoreLink")).as("BOPS Link").isEqualTo(true);
                    Clicks.click(Elements.element("pdp.pickupInStoreLink"));

                    Wait.secondsUntilElementPresent("pdp.bopsOverlay", 20);
                    softly.assertThat(Elements.elementPresent("pdp.bopsOverlay")).as("BOPS Overlay").isEqualTo(true);
                    softly.assertThat(Elements.elementPresent("pdp.bopsOverlayHeader")).as("BOPS Overlay Header").isEqualTo(true);
                    softly.assertThat((Elements.findElement(Elements.element("pdp.bopsOverlayHeader")).getText()).equals("Select a store"));
                    softly.assertThat(Elements.elementPresent("pdp.bopsOverlayProdImg")).as("BOPS Product Image").isEqualTo(true);
                    softly.assertThat(Elements.elementPresent("pdp.bopsOverlayProdName")).as("BOPS Product Name").isEqualTo(true);
                    softly.assertThat(Elements.elementPresent("pdp.bopsOverlaySearchBtn")).as("BOPS Search Button").isEqualTo(true);
                    break;
                }
                case "non_eligible": {
                    break;
                }
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify sizes are displayed in alphanumeric orders on (member|master) PDP")
    public void verify_sizes_display_in_alphanumeric_orders(String pg) throws Throwable {
        switch (pg) {
            case "member": {
                Wait.secondsUntilElementPresent("pdp.memberProductSizeOptions", 10);
                List<WebElement> sizes = Elements.findElements(Elements.element("pdp.memberProductSizeOptions"));
                logger.info("*** Number of Size Options: " + sizes.size());

                if(Elements.elementPresent(Elements.element("pdp.memberViewMoreSizes"))) {
                    Clicks.click(Elements.findElement(Elements.element("pdp.memberViewMoreSizes")));
                    Thread.sleep(5000);
                }

                for(int index =0; index < sizes.size()-1; index++) {
                    if((sizes.get(index).getText()).compareTo(sizes.get(index+1).getText()) < 0) {
                        logger.info("*** Size" + (index+1) + ": " + sizes.get(index).getText());
                        softly.assertThat((sizes.get(index).getText()).compareTo(sizes.get(index+1).getText()) < 0).as("Sizes displayed in orders").isEqualTo(true);
                    }
                    else {
                        logger.info("*** Size" + (index+1) + ": " + sizes.get(index).getText() + " V/V CharByChar using ASCII values!");
                        String str1 = sizes.get(index).getText();
                        String str2 = sizes.get(index+1).getText();
                        logger.info("*======>>> " + str1 + " <= " + str2);
                        for(int i=0; i < str1.length() && i < str2.length(); i++) {
                            if(((int)str1.charAt(i) <= (int)str2.charAt(i)) || str1.length() <= str2.length()) {
                                softly.assertThat(((int) str1.charAt(i) <= (int) str2.charAt(i)) || str1.length() < str2.length()).as("Sizes displayed in orders; ASCII V/V").isEqualTo(true);
//                                logger.info("** ASCII Values: " + (int)str1.charAt(i) + " <= " + (int)str2.charAt(i));
                            }
                        }
                    }
                }
                break;
            }
            case "master": {
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the error message \"([^\"]*)\" on (member|master) PDP (site|iship|registry) mode$")
    public void verify_the_error_messages_on_PDP(String msg, String pg, String mode) throws Throwable {
        Thread.sleep(2000);
        switch(mode) {
            case "site": {
                switch(pg) {
                    case "member": {
                        switch (msg) {
                            case "Sorry, the selected color and size combination is unavailable.": {
                                Assert.assertTrue(Elements.elementPresent("pdp.memberProdA2BErrorPopup"));
                                softly.assertThat(((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())).equals(msg)).as("Correct Error Message Popup").isEqualTo(true);
                                logger.info("Error message popup: " + ((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())));
                                Clicks.click(Elements.element("pdp.memberProdA2BErrorPopupClose"));
                                break;
                            }
                            case "Please select a size.": {
                                Assert.assertTrue(Elements.elementPresent("pdp.memberProdA2BErrorPopup"));
                                softly.assertThat(((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())).equals(msg)).as("Correct Error Message Popup").isEqualTo(true);
                                logger.info("Error message popup: " + ((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())));
                                Clicks.click(Elements.element("pdp.memberProdA2BErrorPopupClose"));
                                break;
                            }
                            case "You've reached the limit for this item.": {
                                Assert.assertTrue(Elements.elementPresent("pdp.memberProdA2BErrorPopup"));
                                softly.assertThat(((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())).equals(msg)).as("Correct Error Message Popup").isEqualTo(true);
                                logger.info("Error message popup: " + ((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())));
                                Clicks.click(Elements.element("pdp.memberProdA2BErrorPopupClose"));
                                break;
                            }
                            case "You've reached the limit for this item. Please select another size/color.": {
                                Assert.assertTrue(Elements.elementPresent("pdp.memberProdA2BErrorPopup"));
                                softly.assertThat(((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())).equals(msg)).as("Correct Error Message Popup").isEqualTo(true);
                                logger.info("Error message popup: " + ((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())));
                                Clicks.click(Elements.element("pdp.memberProdA2BErrorPopupClose"));
                                break;
                            }
                            case "Item availability unknown. Please call.": {
                                Assert.assertTrue(Elements.elementPresent("pdp.bigTicketA2BErrorPopup"));
                                softly.assertThat(((Elements.findElement(Elements.element("pdp.bigTicketA2BErrorPopup")).getText())).equals(msg)).as("Correct Error Message Popup").isEqualTo(true);
                                logger.info("Error message popup: " + ((Elements.findElement(Elements.element("pdp.bigTicketA2BErrorPopup")).getText())));
                                Clicks.click(Elements.element("pdp.memberProdA2BErrorPopupClose"));
                                break;
                            }
                            case "Please enter a valid zip code": {
                                Assert.assertTrue(Elements.elementPresent("pdp.memberProdA2BErrorPopup"));
                                if(mode.equals("member"))
                                    softly.assertThat((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText()).equalsIgnoreCase(msg)).as("Correct Error Message Popup").isEqualTo(true);
                                else
                                    softly.assertThat(msg.contains(Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())).as("Correct Error Message Popup").isEqualTo(true);
                                logger.info("Error message popup: " + ((Elements.findElement(Elements.element("pdp.memberProdA2BErrorPopup")).getText())));
                                Clicks.click(Elements.element("pdp.memberProdA2BErrorPopupClose"));
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
            case "iship": {
                if(msg.equals("Product Unavailable")) {
                    Thread.sleep(5000);
                    softly.assertThat(Elements.elementPresent("pdp.iShipMemberItemUnavailableMsg")).as("Item Unavailable Message").isEqualTo(true);
                    String pdpMsg = Elements.findElement(Elements.element("pdp.iShipMemberItemUnavailableMsg")).getText();
                    logger.info(String.format("PDP Item Unavailable MSG ***>> " + pdpMsg));
                    Assert.assertFalse(pdpMsg.equals(""));
                    Assert.assertTrue(pdpMsg.equals("This product is currently unavailable"));
                }
                break;
            }
            case "registry": {
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify E-Gift Cards A2B error messages with invalid values for required fields$")
    public void verify_a2b_error_messages_on_eGiftCards_PDP() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.eGiftCardAddToBagBtn", 20);
        // A2B w/ empty required fields
        Clicks.click(Elements.element("pdp.eGiftCardAddToBagBtn"));
        Thread.sleep(3000);
        softly.assertThat(((Elements.findElement(Elements.element("pdp.eGiftCardPriceErrorMsg")).getText())).equals("Please use an amount from $10 to $1,000")).isEqualTo(true);
        softly.assertThat(((Elements.findElement(Elements.element("pdp.eGiftCardEmailErrorMsg")).getText())).equals("Email address must follow this format: jane@company.com.")).isEqualTo(true);
        logger.info("Price Field Error Message: " + (Elements.findElement(Elements.element("pdp.eGiftCardPriceErrorMsg")).getText()));
        logger.info("Email Field Error Message: " + (Elements.findElement(Elements.element("pdp.eGiftCardEmailErrorMsg")).getText()));
        Thread.sleep(3000);

        // A2B w/ invalid price & recipient's email fields
        TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardPriceField"), "1");
        TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardRecipientEmail"), "macys_test@macys");
        Clicks.click(Elements.element("pdp.eGiftCardAddToBagBtn"));
        softly.assertThat(((Elements.findElement(Elements.element("pdp.eGiftCardPriceErrorMsg")).getText())).equals("Please use an amount from $10 to $1,000")).isEqualTo(true);
        softly.assertThat(((Elements.findElement(Elements.element("pdp.eGiftCardEmailErrorMsg")).getText())).equals("Email address must follow this format: jane@company.com.")).isEqualTo(true);
        Thread.sleep(3000);

        // A2B w/ invalid price, but valid recipient's email fields
        TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardPriceField"), "1001");
        TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardRecipientEmail"), "macys_test@macys.com");
        Clicks.click(Elements.element("pdp.eGiftCardAddToBagBtn"));
        softly.assertThat(((Elements.findElement(Elements.element("pdp.eGiftCardPriceErrorMsg")).getText())).equals("Please use an amount from $10 to $1,000")).isEqualTo(true);
        Thread.sleep(3000);

        // A2B w/ valid price, but invalid recipient's email fields
        TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardPriceField"), "100");
        TextBoxes.typeTextbox(Elements.element("pdp.eGiftCardRecipientEmail"), "macys_test@macys");
        Clicks.click(Elements.element("pdp.eGiftCardAddToBagBtn"));
        softly.assertThat(((Elements.findElement(Elements.element("pdp.eGiftCardEmailErrorMsg")).getText())).equals("Email address must follow this format: jane@company.com.")).isEqualTo(true);

        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the (vertical|horizontal) recommendation panel on (member|master) PDP$")
    public void verify_recommendation_panel_on_PDP(String panel, String pg) throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        switch (panel.toLowerCase()) {
            case "vertical": {
                Thread.sleep(3000);
                softly.assertThat(Elements.elementPresent("pdp.prosVRPanel")).as("Pros VR Panel on" + pg + " PDP").isEqualTo(true);

                Wait.secondsUntilElementPresent("pdp.prosVRScrollDown", 5);
                Clicks.click(Elements.element("pdp.prosVRScrollDown"));
                Wait.secondsUntilElementPresent("pdp.prosVRScrollUp", 5);
                Thread.sleep(2000);
                Clicks.click(Elements.element("pdp.prosVRScrollUp"));
                break;
            }
            case "horizontal": {
                scrollToLazyLoadElement("pdp.prosHRPanel");
                Wait.secondsUntilElementPresent("pdp.prosHRPanel", 20);
                Thread.sleep(5000);
                softly.assertThat(Elements.elementPresent("pdp.prosHRPanel")).as("Pros HR Panel on" + pg + " PDP").isEqualTo(true);
                Clicks.javascriptClick(Elements.element("pdp.prosHRScrollRight"));
                Wait.secondsUntilElementPresent("pdp.prosHRScrollLeft", 10);
                Thread.sleep(2000);
                Clicks.javascriptClick(Elements.element("pdp.prosHRScrollLeft"));
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(3000);
    }

    @Then("^I verify the \"([^\"]*)\" on A2B page$")
    public void verify_on_A2B_page(String arg) throws Throwable {
        switch(arg) {
            case "GWP": {
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("addToBag.keep_shopping_button", 20);
                softly.assertThat((Elements.findElement(Elements.element("addToBag.A2B_GWP_SpecialOfferMsg")).getText()).toLowerCase().equals("You've qualified for a Bonus Gift!".toLowerCase())).as("A2B: GWP Msg").isEqualTo(true);
                softly.assertThat((Elements.findElement(Elements.element("addToBag.A2B_SpecialOfferReviewMsg")).getText()).toLowerCase().equals("We'll add it to your bag automatically".toLowerCase())).as("A2B: SpecialOffer Msg").isEqualTo(true);
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the \"([^\"]*)\" product added to bag on QB & Shoppingbag page$")
    public void verify_GWP_product_added_to_bag(String arg) throws Throwable {
        switch(arg) {
            case "GWP": {
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("pdp.productTitle", 10);
//                scrollToLazyLoadElement("quick_bag.quickBagLink");
                Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
                Wait.secondsUntilElementPresent("quick_bag.quickBagLink", 10);
                WebElement hoverQB = Elements.findElement("quick_bag.quickBagLink");
                try {
                    Clicks.hoverForSelection(hoverQB);
                }
                catch (Exception e) {
                    logger.warning(String.format("Hovering QB Failed!"));
                    e.printStackTrace();
                }
                Thread.sleep(2000);
                softly.assertThat((Elements.findElement(Elements.element("quick_bag.quickBag_GWP_Msg")).getText()).toLowerCase().equals("This Bonus has been added to your bag".toLowerCase())).as("QB: GWP Msg").isEqualTo(true);
                try {
                    Clicks.click("quick_bag.quickbag_checkout");
                }
                catch (Exception e) {
                    logger.warning(String.format("Click on quickbag_checkout failed due to hovering QB failure!"));
                    e.printStackTrace();
                }

                Wait.forPageReady();
                softly.assertThat((Elements.findElement(Elements.element("shopping_bag.shoppingbagGWP_Msg")).getText()).toLowerCase().equals("This Free Gift has been added to your bag".toLowerCase())).as("ShoppingBag: GWP Msg").isEqualTo(true);
                break;
            }
            case "Free Shipping": {
                Wait.forPageReady();
                softly.assertThat((Elements.findElement(Elements.element("shopping_bag.estimatedShippingTotal")).getText()).toLowerCase().equals("FREE".toLowerCase())).as("ShoppingBag: Estimated Shipping Total").isEqualTo(true);
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify \"([^\"]*)\" offer under SpecialOffers tab on (member|master) PDP (site|iship|registry) mode$")
    public void verify_SpecialOffers_on_PDP(String arg, String pg, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(pg + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!"));
            e.printStackTrace();
        }
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
        switch (pg) {
            case "member": {
                if(mode.equalsIgnoreCase("iship"))
                    Assert.assertTrue(Elements.elementPresent("pdp.iShipCountryFlag"));
                softly.assertThat(Elements.elementPresent("pdp.specialOffersDetailsLink")).as("specialOffersDetailsLink").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.specialOffersHeader")).as("specialOffersHeader").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.specialOffersText")).as("specialOffersFreeShipping HeaderBadges").isEqualTo(true);
                List<WebElement> specialOffersBadges = Elements.findElements(Elements.element("pdp.specialOffersText"));
                for(WebElement we: specialOffersBadges) {
                    if(we.getText().contains("FREE SHIPPING"))
                        softly.assertThat(we.getText().contains("FREE SHIPPING")).as("FREE SHIPPING & RETURNS").isEqualTo(true);
                    else if(we.getText().contains("FREE GIFT"))
                        softly.assertThat(we.getText().contains("FREE GIFT")).as("FREE GIFT Text").isEqualTo(true);
                    else if(we.getText().contains("_VG"))
                        softly.assertThat(we.getText().contains("_VG")).as("Other Offers Text").isEqualTo(true);
                    else if(we.getText().contains("BONUS VALUE"))
                        softly.assertThat(we.getText().contains("BONUS VALUE WORTH")).as("BONUS VALUE WORTH").isEqualTo(true);
                    else {
                        logger.info(String.format("OfferBadges displayed on PDP " + mode + " mode:  \"" + we.getText() + "\""));
                        Assert.fail("*** Unexpected SpecialOffers ***");
                    }
                    logger.info(String.format("OfferBadges displayed on PDP " + mode + " mode:  \"" + we.getText() + "\""));
                    Thread.sleep(1000);
                }
                Clicks.click(Elements.element("pdp.specialOffersDetailsLink"));
                Thread.sleep(2000);
                Clicks.click(Elements.element("pdp.specialOffersExpandCollapse"));
                Thread.sleep(3000);
                List<WebElement> specialOffers = Elements.findElements(Elements.element("pdp.specialOffers"));
                if(arg.equalsIgnoreCase("GWP")) {
                    for(WebElement el: specialOffers) {
                        if(el.getText().contains("FREE GIFT"))
                            softly.assertThat(el.getText().contains("FREE GIFT")).as("GWP under specialOffers Tab").isEqualTo(true);
                        break;
                    }
                }
                else if(arg.equalsIgnoreCase("Free Shipping")) {
                    for(WebElement el: specialOffers) {
                        if(el.getText().contains("FREE SHIP"))
                            softly.assertThat(el.getText().contains("FREE SHIP")).as("FREE SHIPPING under specialOffers Tab").isEqualTo(true);
                        break;
                    }
                }
                Clicks.click(Elements.element("pdp.specialOffersExpandCollapse"));
                Thread.sleep(2000);
                Clicks.click(Elements.element("pdp.specialOffersExpandCollapse"));
                break;
            }
            case "master": {
                break;
         }
    }
//    softly.assertAll();
//    Thread.sleep(2000);
    }

    @Then("^I verify social icons on (member|master|CHANEL) PDP (site|iship|registry) mode$")
    public void verify_social_icons_on_PDP(String pg, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(pg + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!"));
            e.printStackTrace();
        }
        Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
        switch (mode) {
            case "site": {
                switch (pg) {
                    case "member": {
                        Wait.secondsUntilElementPresent("pdp.pinterest_icon", 10);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);

                        Clicks.click(Elements.element("pdp.email_icon"));
                        logger.info(String.format("Email button clicked!\n"));
                        Thread.sleep(2000);
                        Navigate.switchWindow(1);
                        Wait.secondsUntilElementPresent("pdp.emailFriendsHeader", 20);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendsHeader")).as("emailFriendsHeader").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendsProdDesc")).as("emailFriendsProdDesc").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendSubmitBtn")).as("emailFriendSubmitBtn").isEqualTo(true);
                        Clicks.click(Elements.element("pdp.emailFriendsOverlayClose"));

                        Thread.sleep(1000);
                        Clicks.click(Elements.element("pdp.pinterest_icon"));
                        logger.info(String.format("Pinterest button clicked!\n"));
                        Navigate.switchWindow(1);
                        Wait.secondsUntilElementPresent("pdp.pinterestOverlay", 20);
                        softly.assertThat(Elements.elementPresent("pdp.pinterestOverlay")).as("Pinterset Overlay").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterestTitle")).as("Pinterest Title").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterestContinueBtn")).as("pinterestContinueBtn").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterest_userEmail")).as("Pinterest userEmail").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterest_userPwd")).as("Pinterest userPWD").isEqualTo(true);
                        Clicks.click(Elements.element("pdp.pinterestContinueBtn"));
                        break;
                    }
                    case "master": {
                        Wait.secondsUntilElementPresent("pdp.pinterest_icon", 10);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);

                        Clicks.click(Elements.element("pdp.email_icon"));
                        logger.info(String.format("Email button clicked!\n"));
                        Thread.sleep(2000);
                        Navigate.switchWindow(1);
                        Wait.secondsUntilElementPresent("pdp.emailFriendsHeader", 20);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendsHeader")).as("emailFriendsHeader").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendsProdDesc")).as("emailFriendsProdDesc").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendSubmitBtn")).as("emailFriendSubmitBtn").isEqualTo(true);
                        Clicks.click(Elements.element("pdp.emailFriendsOverlayClose"));

                        Thread.sleep(1000);
                        Clicks.click(Elements.element("pdp.pinterest_icon"));
                        logger.info(String.format("Pinterest button clicked!\n"));
                        Navigate.switchWindow(1);
                        Wait.secondsUntilElementPresent("pdp.pinterestOverlay", 20);
                        softly.assertThat(Elements.elementPresent("pdp.pinterestContinueBtn")).as("pinterestContinueBtn").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterest_userEmail")).as("Pinterest userEmail").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterest_userPwd")).as("Pinterest userPWD").isEqualTo(true);
                        Clicks.click(Elements.element("pdp.pinterestContinueBtn"));
                        break;
                    }
                    case "CHANEL": {
                        softly.assertThat(Elements.elementPresent("chanel.emailIcon")).as("emailIcon").isEqualTo(true);
                        Thread.sleep(1000);
                        Clicks.click(Elements.element("chanel.emailIcon"));
                        Wait.secondsUntilElementPresent("chanel.emailFriendsOverlay", 10);
                        softly.assertThat(Elements.elementPresent("chanel.emailFriendsOverlay")).as("emailFriendsOverlay").isEqualTo(true);
                        Thread.sleep(1000);
                        Clicks.click(Elements.element("chanel.emailOverlayClose"));
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (pg) {
                    case "member": {
                        Wait.secondsUntilElementPresent("pdp.pinterest_icon", 10);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);

                        Clicks.click(Elements.element("pdp.email_icon"));
                        logger.info(String.format("Email button clicked!\n"));
                        Thread.sleep(2000);
                        Navigate.switchWindow(1);
                        Wait.secondsUntilElementPresent("pdp.emailFriendsHeader", 20);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendsHeader")).as("emailFriendsHeader").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendsProdDesc")).as("emailFriendsProdDesc").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendSubmitBtn")).as("emailFriendSubmitBtn").isEqualTo(true);
                        Clicks.click(Elements.element("pdp.emailFriendsOverlayClose"));

                        Thread.sleep(1000);
                        Clicks.click(Elements.element("pdp.pinterest_icon"));
                        logger.info(String.format("Pinterest button clicked!\n"));
                        Navigate.switchWindow(1);
                        Wait.secondsUntilElementPresent("pdp.pinterestOverlay", 20);
                        softly.assertThat(Elements.elementPresent("pdp.pinterestOverlay")).as("Pinterset Overlay").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterestContinueBtn")).as("pinterestContinueBtn").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterest_userEmail")).as("Pinterest userEmail").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterest_userPwd")).as("Pinterest userPWD").isEqualTo(true);
                        Clicks.click(Elements.element("pdp.pinterestContinueBtn"));
                        break;
                    }
                    case "master": {
                        Wait.secondsUntilElementPresent("pdp.pinterest_icon", 10);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.email_icon")).as("email_icon").isEqualTo(true);

                        Clicks.click(Elements.element("pdp.email_icon"));
                        logger.info(String.format("Email button clicked!\n"));
                        Thread.sleep(2000);
                        Navigate.switchWindow(1);
                        Wait.secondsUntilElementPresent("pdp.emailFriendsHeader", 20);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendsHeader")).as("emailFriendsHeader").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendsProdDesc")).as("emailFriendsProdDesc").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.emailFriendSubmitBtn")).as("emailFriendSubmitBtn").isEqualTo(true);
                        Clicks.click(Elements.element("pdp.emailFriendsOverlayClose"));

                        Thread.sleep(1000);
                        Clicks.click(Elements.element("pdp.pinterest_icon"));
                        logger.info(String.format("Pinterest button clicked!\n"));
                        Navigate.switchWindow(1);
                        Wait.secondsUntilElementPresent("pdp.pinterestOverlay", 20);
                        softly.assertThat(Elements.elementPresent("pdp.pinterestContinueBtn")).as("pinterestContinueBtn").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterest_userEmail")).as("Pinterest userEmail").isEqualTo(true);
//                        softly.assertThat(Elements.elementPresent("pdp.pinterest_userPwd")).as("Pinterest userPWD").isEqualTo(true);
                        Clicks.click(Elements.element("pdp.pinterestContinueBtn"));
                        break;
                    }
                }
                break;
            }
            case "registry": {
                switch (pg) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify navigation to \"([^\"]*)\" page in (site|iship|registry) mode$")
    public void verify_navigation_to_pages(String arg, String mode) throws Throwable {
        Wait.forPageReady();
        switch(mode) {
            case "site": {
                if(arg.equalsIgnoreCase("list")) {
                    Wait.forPageReady();
                    String url = WebDriverManager.getCurrentUrl();
                    Thread.sleep(3000);
                    softly.assertThat(url.contains("/wishlist/mylist")).as("/wishlist/mylist").isEqualTo(true);
                }
                break;
            }
            case "iship": {
                break;
            }
            case "registry": {
                break;
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the availability message as \"([^\"]*)\" on (member|master) PDP in (site|iship|registry) mode$")
    public void verify_availability_messages_on_PDP(String msg, String page, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(page + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!\n"));
            e.printStackTrace();
        }
        Assert.assertNotNull(Elements.findElement(Elements.element("pdp.productTitle")).getText());
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        if(msg.equalsIgnoreCase("in stock : Direct from vendor, usually ships within x business days.")) {
                            Wait.secondsUntilElementPresent("pdp.availibilityMsg", 10);
                            String actualMsg = (Elements.findElement(Elements.element("pdp.availibilityMsg")).getText()).replace("\n"," ");
                            logger.info(String.format("Actual Message ===>>  "+ actualMsg));
                            String expectedMsg = actualMsg.replaceAll("[0-9]", "x");
                            logger.info(String.format("Expected Message ===>>  "+ expectedMsg));
                            softly.assertThat(msg.equalsIgnoreCase(expectedMsg)).as("Availibility Message").isEqualTo(true);
                            softly.assertAll();
                        }
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
        Thread.sleep(2000);
    }

    @Then("^I verify \"([^\"]*)\" in bottom tabs section on (member|master) PDP (site|iship|registry) mode$")
    public void verifying_bottom_tab_section_on_PDP(String link, String page, String mode) throws Throwable {
        if(link.equalsIgnoreCase("Q&A section")) {
            softly.assertThat(Elements.elementPresent("pdp.QA_Content")).as("QA_Content").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.QAtabHeader")).as("QAtabHeader").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.QA_Count")).as("QA_Count").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.QA_Btn")).as("QA_Btn").isEqualTo(true);
            logger.info(link + " verified!");
        }
        softly.assertAll();
        Thread.sleep(3000);
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "registry": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
    }

    @Then("^I verify \"([^\"]*)\" overlay on (member|master) PDP (site|iship|registry) mode$")
    public void verifying_overlays_on_PDP(String arg, String page, String mode) throws Throwable {
        switch (mode) {
            case "site": {
                switch (page) {
                    case "member": {
                        if(arg.equalsIgnoreCase("Q&A")) {
                            logger.info( "Verifying " + arg + " overlay!");
                            Thread.sleep(5000);
                            Navigate.switchWindow(1);
                            softly.assertThat(Elements.elementPresent("pdp.QA_OverlayHeader")).as("QA_OverlayHeader").isEqualTo(true);
                            softly.assertThat(Elements.elementPresent("pdp.QA_OverlayBody")).as("QA_OverlayBody").isEqualTo(true);
                        }
                        else if(arg.equalsIgnoreCase("Q&A Guest")) {
                            logger.info( "Verifying " + arg + " overlay!");
                            Thread.sleep(3000);
                            String url = WebDriverManager.getCurrentUrl();
                            softly.assertThat(url.contains("bazaarvoice.com/bvstaging/answers/submit/")).as("URL: bazaarvoice.com").isEqualTo(true);
                        }
                        else if(arg.equalsIgnoreCase("Pricing Policy")) {
                            logger.info( "Verifying " + arg + " overlay!");
                            Thread.sleep(2000);
                            softly.assertThat(Elements.elementPresent("pdp.pricePolicyLink")).as("pricePolicyLink").isEqualTo(true);
                            softly.assertThat((Elements.findElement(Elements.element("pdp.pricePolicyLink")).getText()).equalsIgnoreCase("Savings based on offering prices, not actual sales")).as("pricePolicy Link & Text").isEqualTo(true);
                            Clicks.click("pdp.pricePolicyLink");
                            Thread.sleep(2000);
                            Navigate.switchWindow(1);
                            softly.assertThat(Elements.elementPresent("pdp.pricePolicyOverlay")).as("pricePolicyOverlay").isEqualTo(true);
                            softly.assertThat(Elements.elementPresent("pdp.pricePolicyOverlayHeader")).as("pricePolicyOverlayHeader").isEqualTo(true);
                            softly.assertThat((Elements.findElement(Elements.element("pdp.pricePolicyOverlayHeader")).getText()).equalsIgnoreCase("Pricing Policy")).as("pricePolicy Overlay Header").isEqualTo(true);
                        }
                        logger.info(arg + " verified!");
                        softly.assertAll();
                        Thread.sleep(3000);
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
            case "registry": {
                switch (page) {
                    case "member": {
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }
        }
    }

    @Then("^I verify navigation to the corresponding PDP (site|iship|registry) mode$")
    public void verifying_navigation_to_the_corresponding_PDP(String mode) throws Throwable {

        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format("PDP not loading properly as productTitle is not displayed in " + mode + " mode!\n"));
            e.printStackTrace();
        }
        String productTitle = Elements.findElement(Elements.element("pdp.productTitle")).getText();
        softly.assertThat(productTitle.isEmpty()).as("productTitle").isEqualTo(false);
        String webId = Elements.findElement(Elements.element("pdp.web_id")).getText();
        Clicks.hoverForSelection(Elements.element("pdp.web_id"));
        softly.assertThat(webId.contains(productId)).as("productId").isEqualTo(true);
        logger.info("Corresponding PDP " + mode + " mode v/v.  ProductID: " + productId + " & " + webId);
    }

    @Then("^I verify the zoomer and altimages on (member|master) PDP (site|iship|registry) mode$")
    public void verifying_zoomer_altimages_on_PDP(String pg, String mode) throws Throwable {

        softly.assertThat(Elements.elementPresent("pdp.product_main_image")).as("product_main_image").isEqualTo(true);
        softly.assertThat(Elements.elementPresent("pdp.productAltImages")).as("productAltImages").isEqualTo(true);

        Thread.sleep(2000);
        Clicks.hoverForSelection(Elements.element("pdp.product_main_image"));
        Thread.sleep(2000);
        List<WebElement> altImgs = Elements.findElements(Elements.element("pdp.productAltImages"));
        Random rand = new Random();
        int imgOpt;

        if(altImgs.size() > 4 && Elements.elementPresent(Elements.element("pdp.altImgDownArrow"))) {
            Clicks.click(Elements.element("pdp.altImgDownArrow"));
            Thread.sleep(2000);
            Clicks.click(Elements.element("pdp.altImgUpArrow"));
        }
        logger.info("Number of AltImages displayed: " + altImgs.size());
        for(int i=1; i < altImgs.size()-1; i++) {
            if(altImgs.size() < 4)
                imgOpt = rand.nextInt(altImgs.size());
            else
                imgOpt = rand.nextInt(altImgs.size()/2);
            logger.info("Randomly Selected AltImage::  " + imgOpt);
            Thread.sleep(2000);
            altImgs.get(imgOpt).click();
            logger.info("Randomly Clicked AltImage::  " + imgOpt);
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the \"([^\"]*)\" section on (member|master) PDP (site|iship|registry) mode$")
    public void verifying_verious_sections_on_PDP(String arg, String pg, String mode) throws Throwable {

        Wait.forPageReady();
        if(arg.equalsIgnoreCase("SponsoredItems")) {
            Wait.secondsUntilElementPresent("pdp.sponsoredItemsContainer", 20);
            softly.assertThat(Elements.elementPresent("pdp.sponsoredItemsSection")).as("sponsoredItemsSection").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.sponsoredItemsHeader")).as("sponsoredItemsHeader").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp.sponsoredItemsContainer")).as("sponsoredItemsContainer").isEqualTo(true);
        }
    softly.assertAll();
    Thread.sleep(2000);
    }

    @Then("^I verify that color&quantity are reset to default values on member PDP (site|iship|registry) mode$")
    public void verifying_colorQuantity_reset_to_default_values_on_PDP(String mode) throws Throwable {

        Wait.forPageReady();
        Wait.secondsUntilElementPresent("pdp.memberProductColorOptions", 10);
        String PDPSource = WebDriverManager.getWebDriver().getPageSource();
        String[] quantityOptions = PDPSource.split("<option selected=\"\" value=\"", 0);
        String[] selectedQty = quantityOptions[1].split("\">", 0);
        logger.info("** Default Quantity: " + selectedQty[0]);
        softly.assertThat(Integer.parseInt(selectedQty[0])).as("Default Quantity Selected").isEqualTo(1);

        List<WebElement> colors = Elements.findElements(Elements.element("pdp.memberProdColorsOptEnabled"));
        String actualColorSelected;
        String colorElement;
        for(WebElement element: colors) {
            colorElement = element.getAttribute("class");
            if(colorElement.equalsIgnoreCase("swatch selected")) {
                actualColorSelected = element.getAttribute("data-title");
                softly.assertThat(actualColorSelected.equalsIgnoreCase(defaultColor));
                logger.info("Selected color in previous step: \"" + colorSwatchSelected + "\"");
                logger.info("Verified selected color \"" + actualColorSelected + "\", as the same as the default color \""  + defaultColor + "\"!");
                if(actualColorSelected.equalsIgnoreCase(colorSwatchSelected))
                    logger.info("** Default color was selected in the previous step: " + actualColorSelected + " & " + colorSwatchSelected);
            }
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Then("^I verify the productId being the same as added to bag$")
    public void verify_productId_as_added_to_bag() throws Throwable {
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("shopping_bag.shoppingbagWebId", 15);
        softly.assertThat(productId.equals(Elements.findElement(Elements.element("shopping_bag.shoppingbagWebId")).getText())).as("ProductId are the same").isEqualTo(true);
        logger.info("A2B Verified as ProductId on PDP & ShoppingBag: \"" + productId + "\" = \"" +
                Elements.findElement(Elements.element("shopping_bag.shoppingbagWebId")).getText() + "\"");
        softly.assertAll();
        Thread.sleep(2000);

    }

    @Then("^I verify that product (name|price) details are same on PDP (site|iship|registry) mode$")
    public void verify_name_price_details_on_PDP(String arg, String mode) throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/50)");
        } catch (Exception e) {
            logger.warning(String.format("PDP not loading properly as productTitle is not displayed in " + mode + " mode!\n"));
            e.printStackTrace();
        }
        if(mode.equalsIgnoreCase("iship")) {
            Wait.secondsUntilElementPresent("pdp.iShipCountryFlag", 5);
            Assert.assertTrue(Elements.elementPresent("pdp.iShipCountryFlag"));
        }
        else if(mode.equalsIgnoreCase("registry"))
            softly.assertThat((Elements.findElement(Elements.element("navigation.registry_mode")).getText()).equalsIgnoreCase("Back to Macys.com")).as("Mode: Registry").isEqualTo(true);

        if(arg.equalsIgnoreCase("name")) {
            softly.assertThat(productName.equalsIgnoreCase(Elements.findElement(Elements.element("pdp.productTitle")).getText().replace("\n"," ")))
                    .as("ProductName").isEqualTo(true);
            logger.info(String.format("Product Title Displayed as \"" + Elements.findElement(Elements.element("pdp.productTitle"))
                    .getText().replace("\n"," ")) + "\" on PDP " + mode + " mode!\n");
        }
        else if(arg.equalsIgnoreCase("price")) {
            logger.info("hmmm...");
        }
        softly.assertAll();
        Thread.sleep(2000);
    }

    @Given("^I visit the website as a (guest|registered|gvr|bvr) user in (site|iship|registry) mode$")
    public static void visitingTheWebsiteAsAUser(String userType, String mode) throws Throwable {
        Navigate.visit("sign_in");
        pausePageHangWatchDog();
        TestUsers.clearCustomer();
        HashMap<String, String> options = new HashMap<>();
        options.put("checkout_eligible", "true");
        options.put("paypal_eligible", "true");
        UserProfile profile = TestUsers.getCustomer(options);
        if (!userType.equalsIgnoreCase("guest")) {
            UserProfileService.createUserProfile(profile, true);
            if (!userType.equalsIgnoreCase("gvr")) {
                CreditCardService.addCreditCardToWallet(CreditCards.getValidCard("Visa"), true);
            }
        }
        if (userType.equalsIgnoreCase("registered")) {
            CommonUtils.signInToCreatedProfile();
        }
        if (userType.equalsIgnoreCase("gvr") || userType.equalsIgnoreCase("bvr")) {
            CurrentAddress address = new CurrentAddress();
            TestUsers.getRandomValidAddress(options, address);
            String secureToken = TestUsers.getCustomer(null).getUser().getTokenCredentials().getToken();
            int retryCount = 0;
            while (retryCount < 5) {
                try {
                    Registry registry = new Registry();
                    registry.addRandomData();
                    registry.getContactInfo().setCurrentAddress(address);
                    registry.setCurrentAddress(address);
                    registry = RegistryService.createRegistry(registry, secureToken);
                    if (registry.getId() != null) {
                        break;
                    }
                } catch (AssertionError e) {
                    System.out.println("Retrying again...");
                    retryCount++;
                }
            }
            Assert.assertNotNull("Failed to create registry", TestUsers.getCustomer(null).getRegistry().getId());
            if (userType.equalsIgnoreCase("bvr"))
                CommonUtils.signInToCreatedProfile();
        }
        resumePageHangWatchDog();
        logger.info("Visited website as " + userType + " user using rest services");
    }

    @Then("^I should see add to registry dialog on PDP$")
    public void shouldSeeAddToRegistryDialogOnPDP() throws Throwable {
        Assert.assertTrue("ERROR-APP: Add to Registry dailog is not visible",
                Wait.untilElementPresent("add_to_registry_dialog.add_to_bag_view_registry"));
        logger.info("Add to Registry dailog is displayed on PDP page");
    }

    @Then("^I should see Address line2 field value populated in Edit profile page$")
    public void Addressline2ValueInEditProfilePage() throws Throwable {

        UserProfile regUser = TestUsers.getCustomerInformation();
        String expectedAddressLine2 = regUser.getRegistry().getCurrentAddress().getAddressLine2();
        WebElement selectedElement1 = Elements.findElement("edit_registry.address_line_2");
        String actualAddressLine2 = selectedElement1.getAttribute("value");
        logger.info("Address Line2 value entered in create registry page" + expectedAddressLine2);
        logger.info("Address Line2 value in edit profile Page" + actualAddressLine2.toLowerCase());
        Assert.assertEquals("Registry address line2 displayed on the Edit Profile Page does not match with the address entered while creating registry", expectedAddressLine2, actualAddressLine2.toLowerCase());
    }

    @And("^I should see currency symbol is displayed on add to registry dialog$")
    public void iShouldSeeCurrencySymbolIsDisplayedOnAddToRegistryDialog() throws Throwable {
            Assert.assertTrue("Currency symbol is not displayed",
                    Elements.getText("add_to_registry_dialog.add_to_registry_dialog_price").contains("$"));
            logger.info("The currency symbol is displayed on add to registry dialog");
    }

    @Then("^I should see updated redirection URL link on PDP Page$")
    public void iShouldSeeUpdatedRedirectionURLLinkOnPDPPage() throws Throwable {
        String brand_URL = Elements.findElement("product_display.goto_brand_name").getAttribute("href");
        Assert.assertTrue("ERROR - APP: Url does not contains /shop/featured/",
                brand_URL.contains("/shop/featured/"));
        logger.info("Brand contains /shop/featured/ url");
    }

    @Then("^I should redirect to the updated URL from PDP page$")
    public void iShouldRedirectToTheUpdatedURLFromPDPPage() throws Throwable {
        WebDriverManager.getWebDriver().switchTo().window(WebDriverManager.getWebDriver().getWindowHandles().toArray()[1].toString());
        Assert.assertTrue("ERROR - APP: Current url does not contains /shop/featured/",
                WebDriverManager.getCurrentUrl().contains("/shop/featured/"));
        logger.info("Current url contains /shop/featured/");
    }

    @And("^I click on Request Warranty information bullet text link on PDP page$")
    public void iClickOnRequestWarrntyInformationBulletTextLinkOnPDPPage() throws Throwable {
        Assert.assertTrue("Request warranty information text is not displayed",
                Elements.elementPresent("product_display.warranty_request"));
        Clicks.clickLazyElement("product_display.warranty_request");
        logger.info("Request Warranty information bullet text with link is displayed");
    }

    @Then("^I should see below text in warranty overlay on PDP page$")
    public void iShouldSeeBelowTextInWarrantyOverlayOnPDPPage(DataTable table) throws Throwable {
        String expWarrantyInformationText = null;
        for (DataTableRow row : table.getGherkinRows())
            expWarrantyInformationText = row.getCells().get(0);
        String acutalWarrantyInformationText = Elements.findElement("product_display.warranty_text").getText();
        Assert.assertTrue("Warranty information text not displayed", acutalWarrantyInformationText.equals(expWarrantyInformationText));
        logger.info("Verified: Warranty Information text");
    }

    @When("^I click on contact us link displayed in warranty overlay$")
    public void iClickOnContactUsLink() throws Throwable {
        Assert.assertTrue("Contact us link is not displayed",
                Elements.elementPresent("product_display.contact_us_link"));
        Clicks.click("product_display.contact_us_link");
        logger.info("Contact us link is displayed on Warranty information overlay");
    }

    @Then("^I should verify the warranty overlay is closed$")
    public void iShouldVerifyTheWarrantyOverlayIsClosed() throws Throwable {
        Assert.assertTrue("Warranty Overlay is still displayed",
                !Elements.isElementInView(Elements.findElement("product_display.warranty_text")));
        logger.info("Warranty Overlay is closed successfully");
    }

    @And("^I navigates to customer service contact page$")
    public void iNavigatesToCustomerServiceContactPage() throws Throwable {
        Navigate.switchWindow(1);
        Assert.assertTrue("ERROR APP: Customer service URL is not correct",
                WebDriverManager.getCurrentUrl().contains("/app/ask"));
        Navigate.switchWindowClose();
        logger.info("Customer service URL is verified successfully");
    }
}