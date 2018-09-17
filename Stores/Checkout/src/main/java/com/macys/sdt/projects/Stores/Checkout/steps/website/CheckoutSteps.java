package com.macys.sdt.projects.Stores.Checkout.steps.website;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;

import static com.macys.sdt.framework.interactions.Clicks.hoverForSelection;
import static com.macys.sdt.framework.interactions.Clicks.sendEnter;
import static com.macys.sdt.framework.interactions.Elements.elementPresent;
import static com.macys.sdt.framework.interactions.Elements.findElement;
import static com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CheckoutSteps extends StepUtils {


    public static final String TENDERING_TEXT = "Tendering";
    private static final String INVALID_UPC_ERROR_MESSAGE = "The barcode scanned is invalid";
    public static String change_price_title_text = "Change Price";
    public static String change_price_default_input_field = "$ ";
    public static int no_of_items_in_bag;
    public static BigDecimal subtotal_bag;
    public static String hold_currentPrice, hold_subTotal;

    //Macy's products

   // public static final String UPC_MACYS_DISCOUNT = "51153819872";
    public static final String UPC_MACYS_DISCOUNT = "20714001940";
    //Commented this out until fedfil fixes the issue associated with this UPC- replaced with a valid upc above $30
    //public static final String UPC_MACYS_DISCOUNT = "91709543745";
    public static final String UPC_MACYS_NO_CRL = "888602194910";
    public static final String UPC_MACYS_NO_COLOR_SIZE_WEBID = "12345000010";
    public static final String UPC_MACYS_NO_COLOR_SIZE = "490600010637";
    public static final String UPC_MACYS_LESS_THAN_30 = "91709352521";

    public static final String WEBID_MACYS_NO_COLOR_SIZE = "1234567890";

    public static boolean firstTime = true;

    //Bloomingdales products
    public static final String UPC_BLOOM_LESS_THAN_30 = "20714004866";

    private void goToHome() {
        Navigate.visit(RunConfig.url);
    }

    @Given("^I am on \"([^\"]*)\"$")
    public void i_am_on(String application) throws Throwable {
        switch (application) {
            case "Macy's Sales Trans":
                Navigate.visit("home");
                Wait.forPageReady();
                if (firstTime) {
                    System.out.println("I ENTERED THE FIRST TIME LOGIC");
                    Wait.forPageReady();
                    Wait.secondsUntilElementPresent("home.overlay_close", 5);
                    refreshPage();
                    Wait.secondsUntilElementPresent("home.error_overlay", 30);
                    Thread.sleep(500);
                    ClearLocal();
                    settingLocalstorage("MacysSSP20");
                    SettingFeatureFlags("FFlagSampleappY");
                    refreshPage();
                    Thread.sleep(2000);
                    refreshPage();
                    Thread.sleep(2000);
                    firstTime = false;
                    if (Elements.elementPresent("home.overlay_close")) {
                        Clicks.click("home.overlay_close");
                    }
                    refreshPage();
                }
                if (Elements.elementPresent("home.overlay_close")) {
                    Clicks.click("home.overlay_close");
                }

                if (Elements.elementPresent("home.error_overlay")) {
                    Thread.sleep(500);
                    refreshPage();
                    Thread.sleep(500);
                    System.out.println("The 3rd Refresh was needed... For Some reason we saw an adiitional error on startup");
                }

                if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                    Clicks.click("home.overlay_close");
                    Thread.sleep(500);

                    if (Elements.elementPresent("tendering.signature_view")) {
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt & there was an error overlay on to of the sig prompt. Please Fix.");
                    } else {
                        CancelTrans();
                        Thread.sleep(500);
                        Assert.fail("The previous transaction failed after a successful tender but prior to signature being complete");
                    }
                }

                if (Elements.elementPresent("tendering.page_content")) {
                    Wait.forPageReady();
                    Thread.sleep(2000);
                    if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                        Clicks.click("home.overlay_close");
                        Thread.sleep(1000);
                        CancelTrans();
                        Thread.sleep(1000);
                        Assert.fail("The transaction ended on tendering module with an error message");
                        iCanSeeTheSalesTransLandingPage();
                    }
                    Thread.sleep(1000);
                    CancelTrans();
                    Thread.sleep(1000);
                    Assert.fail("The transaction ended on tendering module.");
                    iCanSeeTheSalesTransLandingPage();


                }

                if (Elements.elementPresent("tendering.signature_view")) {
                    Thread.sleep(1000);
                    if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                        Clicks.click("home.overlay_close");
                        Thread.sleep(500);
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt & there was an error overlay on to of the sig prompt. Please Fix.");
                    } else {
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt. Please Fix.");
                    }
                }

                if (Elements.elementPresent("postTender.print_button")) {
                    Clicks.click("postTender.print_button");
                    Thread.sleep(500);
                    Assert.fail("The previous transaction Was stuck on the Print screen after completing authorization. Please fix");
                }

                //Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
                if (!Elements.elementPresent("bag_screen.emptyBag")) {
                    Wait.secondsUntilElementPresent("home.progressBar_bag", 15);
                    Clicks.click("home.progressBar_bag");
                    Thread.sleep(500);
                }

                if (Elements.elementPresent("bag_screen.item_details")) {
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction was not canceled or completed. At the Start of this transaction there was an item in the bag. Please fix");
                }

                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 10);
                Elements.elementShouldBePresent("bag_screen.emptyBag");
                Wait.secondsUntilElementPresent("home.progressBar_product", 15);
                Elements.elementShouldBePresent("home.progressBar_product");
                Clicks.click("home.progressBar_product");
                Wait.secondsUntilElementPresent("home.verify_page", 20);
//        Elements.elementShouldBePresent("home.verify_page");
                //
                Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 20);
//        Elements.elementShouldBePresent("bag_screen.upc_textbox");
                break;
            case "Bloomingdale's Sales Trans":
                Navigate.visit("home");
                Wait.forPageReady();
                if (firstTime) {
                    Wait.forPageReady();
                    Wait.secondsUntilElementPresent("home.overlay_close", 5);
                    refreshPage();
                    Wait.secondsUntilElementPresent("home.error_overlay", 30);
                    ClearLocal();
                    settingLocalstorage("BlmSSP22");
                    SettingFeatureFlags("FFlagSampleappY");
                    refreshPage();
                    Thread.sleep(2000);
                    refreshPage();
                    Thread.sleep(2000);
                    firstTime = false;
                    if (Elements.elementPresent("home.overlay_close")) {
                        Clicks.click("home.overlay_close");
                        refreshPage();
                    }
                    if (Elements.elementPresent("home.overlay_close")) {
                        Clicks.click("home.overlay_close");
                        refreshPage();
                    }
                }
                if (Elements.elementPresent("home.overlay_close")) {
                    Clicks.click("home.overlay_close");
                }
                if (Elements.elementPresent("home.error_overlay")) {
                    Thread.sleep(500);
                    refreshPage();
                    Thread.sleep(500);
                    System.out.println("The 3rd Refresh was needed... For Some reason we saw an adiitional error on startup");
                }

                if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                    Clicks.click("home.overlay_close");
                    Thread.sleep(500);

                    if (Elements.elementPresent("tendering.signature_view")) {
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt & there was an error overlay on to of the sig prompt. Please Fix.");
                    } else {
                        CancelTrans();
                        Thread.sleep(500);
                        Assert.fail("The previous transaction failed after a successful tender but prior to signature being complete");
                    }
                }

                if (Elements.elementPresent("tendering.page_content")) {
                    Wait.forPageReady();
                    Thread.sleep(2000);
                    if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                        Clicks.click("home.overlay_close");
                        Thread.sleep(1000);
                        CancelTrans();
                        Thread.sleep(1000);
                        Assert.fail("The transaction ended on tendering module with an error message");
                        iCanSeeTheSalesTransLandingPage();
                    }
                    Thread.sleep(1000);
                    CancelTrans();
                    Thread.sleep(1000);
                    Assert.fail("The transaction ended on tendering module.");
                    iCanSeeTheSalesTransLandingPage();


                }

                if (Elements.elementPresent("tendering.signature_view")) {
                    Thread.sleep(1000);
                    if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                        Clicks.click("home.overlay_close");
                        Thread.sleep(500);
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt & there was an error overlay on to of the sig prompt. Please Fix.");
                    } else {
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt. Please Fix.");
                    }
                }

                if (Elements.elementPresent("postTender.print_button")) {
                    Clicks.click("postTender.print_button");
                    Thread.sleep(500);
                    Assert.fail("The previous transaction Was stuck on the Print screen after completing authorization. Please fix");
                }

                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
                if (!Elements.elementPresent("bag_screen.emptyBag")) {
                    Wait.secondsUntilElementPresent("home.progressBar_bag", 15);
                    Clicks.click("home.progressBar_bag");
                    Thread.sleep(500);
                }

                if (Elements.elementPresent("bag_screen.item_details")) {
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction was not canceled or completed. At the Start of this transaction there was an item in the bag. Please fix");
                }

                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 10);
                Elements.elementShouldBePresent("bag_screen.emptyBag");
                Wait.secondsUntilElementPresent("home.progressBar_product", 15);
                Elements.elementShouldBePresent("home.progressBar_product");
                Clicks.click("home.progressBar_product");
                Wait.secondsUntilElementPresent("home.verify_page", 20);
                Elements.elementShouldBePresent("home.verify_page");
                Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 20);
                Elements.elementShouldBePresent("bag_screen.upc_textbox");
                break;
            case "Macy's Integrated Environment":
                Navigate.visit("pdp");
                Wait.forPageReady();
                if (firstTime) {
                    GetandSetPDJWT("Macys20Integrated", "FFlagIntegratedENV");
                    Navigate.visit("pdp");
                    firstTime = false;
                }
                if (Elements.elementPresent("home.error_overlay")) {
                    Thread.sleep(500);
                    refreshPage();
                    Thread.sleep(500);
                    System.out.println("The 3rd Refresh was needed... For Some reason we saw an adiitional error on startup");
                }
                if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                    Clicks.click("home.overlay_close");
                    Thread.sleep(500);
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction failed after a successful tender but prior to signature being complete");
                }

                if (Elements.elementPresent("bag_screen.emptyBag")) {
                    ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                    productDiscoveryStep.iClickOnHamburgerIcon();
                    productDiscoveryStep.iClickTheProductDiscoveryHomeMenuOption();

                }


                if (!Elements.findElement("home.bag_action").getText().isEmpty()) {
                    System.out.println("THERE WAS SOMETHING IN THE BAG");
                    ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                    productDiscoveryStep.iClickOnHamburgerIcon();
                    iSeeTheCancelTransactionButton();
                    iClickOnCancelTransationButton();
                    Thread.sleep(500);
                    Assert.fail("At the start of the transaction there was already an item within the bag");
                }

//
//
//                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
//                if (!Elements.elementPresent("bag_screen.emptyBag")) {
//                    Wait.secondsUntilElementPresent("home.progressBar_bag", 15);
//                    Clicks.click("home.progressBar_bag");
//                    Thread.sleep(500);
//                }

                if (Elements.elementPresent("bag_screen.item_details")) {
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction was not canceled or completed. Please fix");
                }

                Elements.elementShouldBePresent("pdp.verify_page");

                break;
            case "Bloomingdale's Integrated Environment":
                Navigate.visit("pdp");
                Wait.forPageReady();
                if (firstTime) {
                    GetandSetPDJWT("Bloom22Integrated", "FFlagIntegratedENV");
                    Navigate.visit("pdp");
                    firstTime = false;
                }
                if (Elements.elementPresent("home.error_overlay")) {
                    Thread.sleep(500);
                    refreshPage();
                    Thread.sleep(500);
                    System.out.println("The 3rd Refresh was needed... For Some reason we saw an adiitional error on startup");
                }
                if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                    Clicks.click("home.overlay_close");
                    Thread.sleep(500);
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction failed after a successful tender but prior to signature being complete");
                }

                if (Elements.elementPresent("bag_screen.emptyBag")) {
                    ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                    productDiscoveryStep.iClickOnHamburgerIcon();
                    productDiscoveryStep.iClickTheProductDiscoveryHomeMenuOption();

                }


                if (!Elements.findElement("home.bag_action").getText().isEmpty()) {
                    System.out.println("THERE WAS SOMETHING IN THE BAG");
                    ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                    productDiscoveryStep.iClickOnHamburgerIcon();
                    iSeeTheCancelTransactionButton();
                    iClickOnCancelTransationButton();
                    Thread.sleep(500);
                    Assert.fail("At the start of the transaction there was already an item within the bag");
                }

//
//
//                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
//                if (!Elements.elementPresent("bag_screen.emptyBag")) {
//                    Wait.secondsUntilElementPresent("home.progressBar_bag", 15);
//                    Clicks.click("home.progressBar_bag");
//                    Thread.sleep(500);
//                }

                if (Elements.elementPresent("bag_screen.item_details")) {
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction was not canceled or completed. Please fix");
                }

                Elements.elementShouldBePresent("pdp.verify_page");

                break;
            case "Macy's Sales Trans Checkout Disabled":
                Navigate.visit("pdp");
                Wait.forPageReady();
                if (firstTime) {
                    GetandSetPDJWT("Macys20Integrated", "FeatureFlagsCheckoutDisabled");
                    Navigate.visit("pdp");
                    firstTime = false;
                }
                if (Elements.elementPresent("home.error_overlay")) {
                    Thread.sleep(500);
                    refreshPage();
                    Thread.sleep(500);
                    System.out.println("The 3rd Refresh was needed... For Some reason we saw an additional error on startup");
                }
                if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                    Clicks.click("home.overlay_close");
                    Thread.sleep(500);
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction failed after a successful tender but prior to signature being complete");
                }

                if (Elements.elementPresent("bag_screen.emptyBag")) {
                    ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                    productDiscoveryStep.iClickOnHamburgerIcon();
                    productDiscoveryStep.iClickTheProductDiscoveryHomeMenuOption();

                }


                if (!Elements.findElement("home.bag_action").getText().isEmpty()) {
                    System.out.println("THERE WAS SOMETHING IN THE BAG");
                    ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                    productDiscoveryStep.iClickOnHamburgerIcon();
                    iSeeTheCancelTransactionButton();
                    iClickOnCancelTransationButton();
                    Thread.sleep(500);
                    Assert.fail("At the start of the transaction there was already an item within the bag");
                }

//
//
//                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
//                if (!Elements.elementPresent("bag_screen.emptyBag")) {
//                    Wait.secondsUntilElementPresent("home.progressBar_bag", 15);
//                    Clicks.click("home.progressBar_bag");
//                    Thread.sleep(500);
//                }

                if (Elements.elementPresent("bag_screen.item_details")) {
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction was not canceled or completed. Please fix");
                }

                Elements.elementShouldBePresent("pdp.verify_page");

                break;
            case "Macy's Desktop":
                Navigate.visit("home");
                Wait.forPageReady();
                if (firstTime) {
                    Wait.forPageReady();
                    setWindowToHalDesktopView();
                    Thread.sleep(6000);
                    Wait.secondsUntilElementPresent("home.error_overlay", 30);
                    Thread.sleep(500);
                    ClearLocal();
                    settingLocalstorage("MacysSSP24Desktop");
                    SettingFeatureFlags("FFflagallY");
                    refreshPage();
                    Thread.sleep(2000);
                    refreshPage();
                    Thread.sleep(2000);
                    firstTime = false;

                }
                if (Elements.elementPresent("home.error_overlay")) {
                    Thread.sleep(500);
                    refreshPage();
                    Thread.sleep(500);
                    System.out.println("The 3rd Refresh was needed... For Some reason we saw an adiitional error on startup");
                }

                if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                    Clicks.click("home.overlay_close");
                    Thread.sleep(500);

                    if (Elements.elementPresent("tendering.signature_view")) {
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt & there was an error overlay on to of the sig prompt. Please Fix.");
                    } else {
                        CancelTrans();
                        Thread.sleep(500);
                        Assert.fail("The previous transaction failed after a successful tender but prior to signature being complete");
                    }
                }

              // Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
                if (!Elements.elementPresent("bag_screen.emptyBag")) {
                    Wait.secondsUntilElementPresent("home.progressBar_bag", 15);
                    if (Elements.elementPresent("home.overlay_close")) {
                        Clicks.click("home.overlay_close");
                    }

                    if (Elements.elementPresent("home.progressBar_bag")) {
                        Clicks.click("home.progressBar_bag");
                        Thread.sleep(500);
                    } else {
                        refreshPage();
                    }
                }

                if (Elements.elementPresent("bag_screen.item_details")) {
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction was not canceled or completed. At the Start of this transaction there was an item in the bag. Please fix");
                }

                if (Elements.elementPresent("tendering.signature_view")) {
                    Thread.sleep(1000);
                    if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                        Clicks.click("home.overlay_close");
                        Thread.sleep(500);
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt & there was an error overlay on to of the sig prompt. Please Fix.");
                    } else {
                        TenderingSteps tendering = new TenderingSteps();
                        tendering.iInputMySignature();
                        tendering.iPressTheConfirmSignatureButton();
                        tendering.iClickPosttenderPrintButton();
                        iCanSeeTheSalesTransLandingPage();
                        Assert.fail("The Last Transaction Was stuck on the Signature Prompt. Please Fix.");
                    }
                }

                if (Elements.elementPresent("postTender.print_button")) {
                    Clicks.click("postTender.print_button");
                    Thread.sleep(500);
                    Assert.fail("The previous transaction Was stuck on the Print screen after completing authorization. Please fix");
                }

                if (Elements.elementPresent("home.overlay_close")) {
                    Clicks.click("home.overlay_close");
                }
                if (!Elements.elementPresent("bag_screen.emptyBag")&& (Elements.elementPresent("home.overlay_close"))) {
                    Clicks.click("home.overlay_close");
                }
                Wait.secondsUntilElementPresent("home.progressBar_product", 15);
                Elements.elementShouldBePresent("home.progressBar_product");
                Clicks.click("home.progressBar_product");
                Wait.secondsUntilElementPresent("home.verify_page", 20);
                Elements.elementShouldBePresent("home.verify_page");
                Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 20);
                Elements.elementShouldBePresent("bag_screen.upc_textbox");
                break;
            case "Macy's Production":
                Navigate.visit("pdp");
                Wait.forPageReady();
                if (firstTime) {
                    System.out.println("I entered the first time LOGIC");
                    GetandSetPDJWT("MacysProd572","NoFlags");
                    Navigate.visit("pdp");
                    firstTime = false;
                }
                if (Elements.elementPresent("home.error_overlay")) {
                    Thread.sleep(500);
                    refreshPage();
                    Thread.sleep(500);
                    System.out.println("The 3rd Refresh was needed... For Some reason we saw an adiitional error on startup");
                }
                if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                    Clicks.click("home.overlay_close");
                    Thread.sleep(500);
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction failed after a successful tender but prior to signature being complete");
                }

                if (Elements.elementPresent("bag_screen.emptyBag")) {
                    ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                    productDiscoveryStep.iClickOnHamburgerIcon();
                    productDiscoveryStep.iClickTheProductDiscoveryHomeMenuOption();

                }


                if (!Elements.findElement("home.bag_action").getText().isEmpty()) {
                    System.out.println("THERE WAS SOMETHING IN THE BAG");
                    ProductDiscoverySteps productDiscoveryStep = new ProductDiscoverySteps();
                    productDiscoveryStep.iClickOnHamburgerIcon();
                    iSeeTheCancelTransactionButton();
                    iClickOnCancelTransationButton();
                    Thread.sleep(500);
                    Assert.fail("At the start of the transaction there was already an item within the bag");
                }

//
//
//                Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
//                if (!Elements.elementPresent("bag_screen.emptyBag")) {
//                    Wait.secondsUntilElementPresent("home.progressBar_bag", 15);
//                    Clicks.click("home.progressBar_bag");
//                    Thread.sleep(500);
//                }

                if (Elements.elementPresent("bag_screen.item_details")) {
                    CancelTrans();
                    Thread.sleep(500);
                    Assert.fail("The previous transaction was not canceled or completed. Please fix");
                }

                Elements.elementShouldBePresent("pdp.verify_page");
                break;
        }

    }

    @When("^I access Checkout bag screen$")
    public void i_access_Checkout_bag_screen() throws Throwable {
        Navigate.visit(RunConfig.url + "/bag");

    }

    @Then("^I can see Checkout empty bag view$")
    public void I_can_see_Checkout_empty_bag_view() throws Throwable {
        Thread.sleep(2000);
        Wait.forPageReady();
        if (Elements.elementPresent("home.error_overlay")) {
            System.out.println("I entered the refresh JWT Ovelray");
            refreshPage();
        }

        if (Elements.elementPresent("home.overlay_close")) {
            Clicks.click("home.overlay_close");
        }
        Wait.secondsUntilElementPresent("bag_screen.emptyBag", 5);
        if (Elements.elementPresent("home.error_overlay")) {
            Thread.sleep(500);
            refreshPage();
            Thread.sleep(500);
            System.out.println("The JWT error Message Displayed");
        }
        Elements.elementShouldBePresent("bag_screen.emptyBag");
        if (!Elements.elementPresent("bag_screen.emptyBag")) {
            Assert.fail("Empty bag element not displayed");
        }

        if (!Elements.getText("bag_screen.emptyBag").contains("Bag is empty\nScan or search for items")) {
            Assert.fail("Empty bag message was incorrect ");

        }
    }

    @Then("^I can see the customer view$")
    public void i_can_see_the_customer_view() throws Throwable {
        shouldBeOnPage("customer");

        if (!Elements.elementPresent("customer.verify_page")) {
            Assert.fail("Customer element not displayed");
        }
    }

    @When("^I add an item to the Checkout bag$")
    public void i_add_an_item_to_the_Checkout_bag() throws Throwable {
        Wait.forPageReady();
        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 15);
            Elements.elementShouldBePresent("bag_screen.upc_textbox");
            TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_DISCOUNT);
            Clicks.click("bag_screen.add_to_bag_button");

        } else {
            if (Elements.elementPresent("home.overlay_close")) {
                Clicks.click("home.overlay_close");
            }
            Clicks.click("home.progressBar_product");
            Wait.untilElementPresent("bag_screen.upc_textbox");
            Elements.elementShouldBePresent("bag_screen.upc_textbox");
            TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_DISCOUNT);
            Clicks.click("bag_screen.add_to_bag_button");


        }
    }

    @When("^I add \"([^\"]*)\" item to the Checkout bag$")
    public void i_add_number_item_to_the_Checkout_bag(String upc) throws Throwable {
        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 12);
            Elements.elementShouldBePresent("bag_screen.upc_textbox");
            TextBoxes.typeTextNEnter("bag_screen.upc_textbox", upc);
            Clicks.click("bag_screen.add_to_bag_button");

        } else {

            TextBoxes.typeTextNEnter("bag_screen.upc_textbox", upc);
            Clicks.click("bag_screen.add_to_bag_button");

        }
    }

    @When("^I press the suspend button$")
    public void I_press_the_Suspend_button() throws Throwable {
        Thread.sleep(500);
        Wait.secondsUntilElementPresent("bag_screen.suspend_button", 5);
        while (!Elements.findElement("bag_screen.suspend_button").isEnabled()) {
            System.out.println("The Suspend button is DISABLEDDDDD");
            Thread.sleep(2000);
        }
        Clicks.click("bag_screen.suspend_button");
        Wait.untilElementPresent("bag_screen.overlay");
    }

    @Then("^I should see the customer name overlay$")
    public void I_should_see_the_customer_name_overlay() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.overlay_confirm_button", 5);
        Elements.elementShouldBePresent("bag_screen.overlay");
        Elements.elementShouldBePresent("bag_screen.overlay_cancel_button");
        Elements.elementShouldBePresent("bag_screen.overlay_confirm_button");
        Elements.elementShouldBePresent("bag_screen.overlay_header");
        Elements.elementShouldBePresent("bag_screen.overlay_message");
        Elements.elementShouldBePresent("bag_screen.overlay_textbox");
        Elements.elementShouldBePresent("home.overlay_close");

        String custnam_field = Elements.getElementAttribute("bag_screen.overlay_textbox", "placeholder");
        Assert.assertEquals("Enter Customer's Name", custnam_field);

        //The following code is a Galen check to verify the the Cancel and Continue buttons on the Customer Name overlay are side by side

        CheckoutUISteps checkoutUISteps = new CheckoutUISteps();
        checkoutUISteps.iSeeTheContinue_cancelButtonsAligned();
    }

    @When("^I input the customers name$")
    public void I_input_the_customers_name() throws Throwable {
        if (Elements.elementInView("bag_screen.overlay_textbox")) {
            TextBoxes.typeTextNEnter("bag_screen.overlay_textbox", "Michael");
        }
    }

    @And("^I click customer name overlay continue button$")
    public void I_click_customer_name_overlay_suspend_button() throws Throwable {
        Clicks.click("bag_screen.overlay_confirm_button");
        Thread.sleep(1000);
    }

    @Then("^I see the suspended bag confirmation overlay$")
    public void I_see_the_suspended_bag_confirmation_overlay() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.overlay_confirm_button", 30);
        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementNotPresent("bag_screen.spinner", 20);

            if (Elements.elementPresent("bag_screen.spinner")) {
                Assert.fail("There was a spinner when waiting for the customer overlay for over 50 seconds");
            }
            Elements.elementShouldBePresent("home.confirmation_overlay");
            Elements.elementShouldBePresent("bag_screen.confirmation_overlay_header");
            Elements.elementShouldBePresent("bag_screen.confirmation_overlay_message");
            Elements.elementShouldBePresent("home.overlay_close");
            Elements.elementShouldBePresent("bag_screen.overlay_confirm_button");
        }
        Elements.elementShouldBePresent("home.confirmation_overlay");
        Elements.elementShouldBePresent("bag_screen.confirmation_overlay_header");
        Elements.elementShouldBePresent("bag_screen.confirmation_overlay_message");
        Elements.elementShouldBePresent("home.overlay_close");
        Elements.elementShouldBePresent("bag_screen.overlay_confirm_button");
    }

    @When("^I press OK on the suspended confirmation overlay$")
    public void I_press_OK_on_the_suspend_confirmation_overlay() throws Throwable {
        Wait.untilElementPresent("bag_screen.overlay_confirm_button");
        Clicks.click("bag_screen.overlay_confirm_button");
    }

    @Then("^I am returned to New POS Sample webapp$")
    public void I_am_returned_to_New_POS_Sample_webapp() throws Throwable {
        shouldBeOnPage("home");
        Elements.elementShouldBePresent("home.verify_page");
    }

    @And("^I press the cancel button$")
    public void I_press_the_cancel_button() throws Throwable {
        Clicks.click("bag_screen.overlay_cancel_button");
    }

    @Then("^I can view the suspend button$")
    public void I_can_view_the_Suspend_button() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.suspend_button");
    }

    @Then("^I should be able to see totals information of all items added$")
    public void i_should_be_able_to_see_totals_information_of_all_items_added() throws Throwable {
        // Force sleep to get the real data after Totals is loaded
        Thread.sleep(3000);

        Assert.assertTrue("Total label is not displayed", Elements.findElement("tendering.tender_total_label").isDisplayed());
        Assert.assertTrue("Total amount is not displayed", Elements.elementPresent("tendering.tender_total_amount"));

        if (Elements.elementPresent("bag_screen.error_box")) {
            Assert.fail("Totals values not displayed - Error Box is present");
        }

        if (Elements.findElement("tendering.tender_total_amount").getText().equals("--")) {
            Assert.fail("Totals values not displayed - Total is empty '--'");
        }
    }

    @When("^I add (\\d+) items to bag$")
    public void i_add_items_to_Bag(int count) throws Throwable {
        if (!Elements.elementPresent("bag_screen.upc_textbox")) {
            goToHome();
        }
        CheckoutUtils.addItemsToBag(count);
    }

    @Then("^I should be able to see totals information is updated$")
    public void i_should_be_able_to_see_Totals_information_is_updated() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.details", 5);

        Wait.untilElementPresent("bag_screen.bag_item");
        int no_of_items = Elements.findElements("bag_screen.").size();
        float[] prices = new float[no_of_items];
        float sub_total = 0;
        float bag_amount = 0;

        // getting individual prices and totaling
        for (int i = 0; i < no_of_items; i++) {
            String currentPrice = Elements.findElements("bag_screen.item_currentprice").get(i).getText();
            currentPrice = currentPrice.substring(currentPrice.indexOf("$") + 1);
            prices[i] = Float.parseFloat(currentPrice);
            sub_total += prices[i];
        }

        // getting subtotal on screen
        String sub_total_screen = Elements.findElement("tendering.tender_subtotal_amount").getText();
        sub_total_screen = sub_total_screen.substring(sub_total_screen.indexOf("$") + 1);
        float sub_total_app = Float.parseFloat(sub_total_screen);

        // getting tax price on screen
        String tax = Elements.findElement("tendering.tender_tax_amount").getText();
        tax = tax.substring(tax.indexOf("$") + 1);
        float tax_amount = Float.parseFloat(tax);


        if (Elements.elementPresent("bag_screen.bag_fee_amount")) {
            //getting bag price on screen
            String bag = Elements.findElement("bag_screen.bag_fee_amount").getText();
            bag = bag.substring(bag.indexOf("$") + 1);
            bag_amount = Float.parseFloat(bag);
        }


        // totaling individual prices, tax amount and bag amount
        float total = sub_total + tax_amount + bag_amount;

        // getting total on screen
        String total_screen = Elements.findElement("tendering.tender_total_amount").getText();
        total_screen = total_screen.substring(total_screen.indexOf("$") + 1);
        float total_app = Float.parseFloat(total_screen);

        // assert totals
        Assert.assertEquals("Total on screen is not equal to calculated total", total, total_app, 0);

    }

    @Then("^I should be able to see totals information is updated on Tendering$")
    public void i_should_be_able_to_see_Totals_information_is_updated_on_Tendering() throws Throwable {
        Wait.secondsUntilElementPresent("tendering.tender_first_list_item_description", 5);

        // getting subtotal on screen and comparing to calculated subtotal
        String sub_total_screen = Elements.findElement("tendering.tender_subtotal_amount").getText();
        sub_total_screen = sub_total_screen.substring(sub_total_screen.indexOf("$") + 1);
        BigDecimal subtotal_amount = new BigDecimal(sub_total_screen);

        // getting tax price on screen
        String tax = Elements.findElement("tendering.tender_tax_amount").getText();
        tax = tax.substring(tax.indexOf("$") + 1);
        BigDecimal tax_amount = new BigDecimal(tax);

        // totaling displayed subtotal, tax amount and bag amount
        BigDecimal total = subtotal_amount.add(tax_amount);

        // getting total on screen
        String total_screen = Elements.findElement("tendering.tender_total_amount").getText();
        total_screen = total_screen.substring(total_screen.indexOf("$") + 1);
        BigDecimal total_app = new BigDecimal(total_screen);

        // assert totals
        Assert.assertTrue("Total on screen is not equal to calculated total", total.equals(total_app));

    }

    @When("^I should be able to view the Add To Bag button$")
    public void i_should_be_able_to_view_the_Add_To_Bag_button() throws Throwable {
        Wait.untilElementPresent("bag_screen.add_to_bag_button");
        Elements.elementShouldBePresent("bag_screen.add_to_bag_button");
    }

    @When("^I should be able to add \"([^\"]*)\" items to the bag$")
    public void i_should_be_able_to_add_items_to_the_bag(String count) throws Throwable {
        CheckoutUtils.addItemsToBag(Integer.parseInt(count));
    }

    @When("^I add (\\d+)st item into the bag$")
    public void i_add_st_item_into_the_bag(int count) throws Throwable {
        TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_DISCOUNT);
        if (Elements.elementPresent("bag_screen.add_to_bag_button")) {
            Clicks.click("bag_screen.add_to_bag_button");
        }
    }

    @Then("^I should see appropriate error message$")
    public void i_should_see_appropriate_error_message() throws Throwable {
        Wait.untilElementPresent("home.confirmation_overlay");
        String overlay_msg = Elements.findElement("bag_screen.confirmation_overlay_message").getText();
        Assert.assertEquals("Appropriate error message is not displayed.Unexpected error message:" + overlay_msg, "That is not a valid UPC", overlay_msg);
    }

    @Then("^I should view the item limit error message$")
    public void i_should_view_the_item_limit_error_message() throws Throwable {
        Wait.secondsUntilElementPresent("home.confirmation_overlay", 5);
        if (Elements.elementPresent("home.confirmation_overlay")) {
            Elements.elementShouldBePresent("home.confirmation_overlay");
            String overlay_msg = Elements.findElement("bag_screen.confirmation_overlay_message").getText();
            Assert.assertEquals("You can't add more than 50 items.", overlay_msg);
        }

        //PLEASE DELETE THIS SECOND IF STATEMENT ONCE THE CORRESPONDING PD TOAST MESSAGE DEFECT IS FIXED.
        if (Elements.elementPresent("bag_screen.toast_body")) {
            Elements.elementShouldBePresent("bag_screen.toast_message");
            String overlay_msg = Elements.findElement("bag_screen.toast_message").getText();
            Assert.assertEquals("You can't add more than 50 items.", overlay_msg);
        }
    }

    @Then("^I should be able to clear the error message$")
    public void i_should_be_able_to_clear_the_error_message() throws Throwable {
        closeAlert();
    }

    @Then("^I should return to the bag screen$")
    public void i_should_return_to_the_bag_screen() throws Throwable {
        Wait.untilElementPresent("bag_screen.add_to_bag_button");
        Elements.elementShouldBePresent("bag_screen.add_to_bag_button");
    }

    @Then("^I can see the view bag button$")
    public void i_can_see_the_view_bag_button() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.confirmviewbagbutton");
    }

    @When("^I press the view bag button$")
    public void i_press_the_view_bag_button() throws Throwable {
        Clicks.click("bag_screen.viewbagbutton");
    }

    @Then("^I can see the item information on Product Discovery$")
    public void i_can_see_the_Item_Information_on_Product_Discovery() throws Throwable {

        //verifyProductDetails();


        Wait.untilElementPresent("bag_screen.details");
        Elements.elementShouldBePresent("bag_screen.item_details");
//      Elements.elementShouldBePresent("bag_screen.item_description");
//      Elements.elementShouldBePresent("bag_screen.item_image");
//        Elements.elementShouldBePresent("bag_screen.itemcolor_title");
//        Elements.elementShouldBePresent("bag_screen.itemsize_title");
        Elements.elementShouldBePresent("pdp.uat_todays_price");
//        Elements.elementShouldBePresent("bag_screen.item_origprice");
        // Elements.elementShouldBePresent("bag_screen.item_separator");
        // Elements.elementShouldBePresent("bag_screen.item_upc");
//        Elements.elementShouldBePresent("bag_screen.shiptruck_image");
//        Elements.elementShouldBePresent("bag_screen.shipitem_verbiage");
//        Elements.elementShouldBePresent("bag_screen.webid_title");
//        Elements.elementShouldBePresent("bag_screen.web_id");
        //      Elements.elementShouldBePresent("bag_screen.item_color");
        //Elements.elementShouldBePresent("bag_screen.item_size");

//        String itemcolor_title = Elements.findElement("bag_screen.itemcolor_title").getText();
//        Assert.assertEquals("COLOR", itemcolor_title);
//
//        String itemsize_title = Elements.findElement("bag_screen.itemsize_title").getText();
//        Assert.assertEquals("SIZE", itemsize_title);
//
//        String item_upc = Elements.findElement("bag_screen.item_upc").getText();
//        Assert.assertEquals("UPC 490600010637", item_upc);
//
//        String webid_title = Elements.findElement("bag_screen.webid_title").getText();
//        Assert.assertEquals("Web ID 1234567890", webid_title);

    }

    @Then("^I can see the item information on Sales Trans$")
    public void i_can_see_the_Item_Information_on_Sales_Trans() throws Throwable {
        Thread.sleep(500);
        Wait.secondsUntilElementPresent("bag_screen.details", 5);
        Elements.elementShouldBePresent("bag_screen.item_details");
        Elements.elementShouldBePresent("bag_screen.item_currentprice");
        Elements.elementShouldBePresent("bag_screen.item_description");
        Elements.elementShouldBePresent("bag_screen.item_upc");

        //Verifies if an orig price displays it is struckthrough
        if (Elements.elementPresent("bag_screen.item_origprice")) {
            Assert.assertTrue("Original price does not have strikethrough", elementHasStrikeThrough(Elements.findElement("bag_screen.item_origprice")));

        }

        //Verifies if an Image does not display then the "product image unavailable displays"
        if (!Elements.elementPresent("bag_screen.item_image")) {
            Elements.elementShouldBePresent("home.no_image");

        }
        //Verifies that the number of items within the bag should match what is in the header bag icon
        int no_of_items_in_bag = Elements.findElements("bag_screen.item_currentprice").size();
        int no_of_items_in_header_icon = Integer.parseInt(Elements.findElement("home.bag_action").getText());
        Assert.assertEquals("The Number of Items within the bag did not match what was in the header bag icon", no_of_items_in_bag, no_of_items_in_header_icon);

        //Verifies the subtotal is displaying
        if (Elements.findElement("bag_screen.subtotal_amount").getText().contains("--") || (Elements.findElement("bag_screen.subtotal_amount").getText().contains("$0.00"))) {
            Assert.fail("Subtotal is not calculating within the bag was either -- or $0.00");
        }
    }


    @When("^I cancel the transaction$")
    public void I_cancel_the_transaction() throws Throwable {
        Wait.untilElementPresent("bag_screen.cancel_transaction_button");
        Clicks.click("bag_screen.cancel_transaction_button");
        Thread.sleep(5000);

    }

    @Then("^I see the cancelled transaction confirmation overlay$")
    public void I_see_the_cancelled_transaction_confirmation_overlay() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.overlay");
        Elements.elementShouldBePresent("bag_screen.overlay_message");
        Elements.elementShouldBePresent("bag_screen.overlay_ok_button");
    }

    @When("^I press OK on the cancelled transaction confirmation overlay$")
    public void I_press_OK_on_the_cancelled_transaction_confirmation_overlay() throws Throwable {
        Clicks.click("bag_screen.overlay_ok_button");
    }

    @When("^I remove all item from the Checkout bag$")
    public void i_remove_all_item_from_the_Checkout_bag() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(2000);
        hoverForSelection("bag_screen.item_details");

        try {

            List<WebElement> items = Elements.findElements("bag_screen.item_details");
            int totalsize = items.size();
            while (items.size() > 0) {
                iSwipeTheItemFromRightToLeft();
                iCanSeeTheSlideOutMenu();
                iCanSeeTheSlideOutMenuOptions();
                iClickTheRemoveButton();


//                Clicks.swipe("bag_screen.item_currentprice", "bag_screen.upc_label");
//                Wait.untilElementPresent("bag_screen.remove_button_bag");
//                Clicks.click("bag_screen.remove_button_bag");
                Thread.sleep(3000);
//Updates the decremented  values
                --totalsize;
                if (totalsize > 0)
//Just to avoid getting an Exception
                    items = Elements.findElements("bag_screen.item_details");
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
//Prints the Exception message
        }

    }

    @When("^I remove an item from the Checkout bag$")
    public void i_remove_an_item_from_the_Checkout_bag() throws Throwable {
        Thread.sleep(3000);
        Assert.assertTrue(Elements.findElement("bag_screen.item_details").isDisplayed());
        Clicks.swipe("bag_screen.item_details", "bag_screen.upc_label");
        Wait.untilElementPresent("bag_screen.remove_button_bag");
        Clicks.click("bag_screen.remove_button_bag");
        if (Elements.elementPresent("bag_screen.spinner")) {
            Thread.sleep(2000);

        }
    }

    @Then("^I can view that the item is removed from the bag$")
    public void i_can_view_that_the_item_is_removed_from_the_bag() throws Throwable {
        int no_of_items_after = Elements.findElements("bag_screen.details").size();
        Assert.assertNotEquals(no_of_items_after, no_of_items_in_bag);
    }

    @Then("^I see the item added to the bag$")
    public void i_see_the_item_added_to_the_bag() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.item_details");
    }

    @And("^I press ok on the add to bag confirmation layer$")
    public void I_press_ok_on_the_add_to_bag_confirmation_layer() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.add_item_ok");
        Clicks.click("bag_screen.add_item_ok");
    }

    @Then("^I remain on the bag screen$")
    public void I_remain_on_the_bag_screen() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.details");
    }

    @Then("^I can see bag information displayed below the navigation header bar$")
    public void i_can_see_bag_information_displayed_below_the_navigation_header_bar() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.header_info");
        String header_info = Elements.findElement("bag_screen.header_info").getText();
        Assert.assertTrue(header_info.contains("To send"));
    }

    @Then("^I can see the added to bag confirmation layer$")
    public void i_can_see_the_added_to_bag_confirmation_layer() throws Throwable {
        // Thread.sleep(2000);
        Wait.untilElementPresent("home.add_item_overlay");
        Elements.elementShouldBePresent("home.add_item_overlay");
        Elements.elementShouldBePresent("home.add_item_overlay_name");
        // Elements.elementShouldBePresent("home.add_item_overlay_bag_button");
    }

    @Then("^I can see the added to bag confirmation layer on Product Discovery$")
    public void i_can_see_the_added_to_bag_confirmation_layer_ProductDiscovery() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("home.add_item_overlay");
        Elements.elementShouldBePresent("home.add_item_overlay");
        Elements.elementShouldBePresent("home.add_item_overlay_name");
        Elements.elementShouldBePresent("home.uat_add_item_overlay_bag_button");
    }


    @Then("^I can see the added to bag toast message$")
    public void i_can_see_the_added_to_bag_toast_message() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("bag_screen.toast_body", 10);
        if (Elements.elementPresent("bag_screen.spinner") || Elements.elementPresent("pdp.pdp_spinner")) {
            Wait.secondsUntilElementPresent("bag_screen.toast_body", 15);
            Elements.elementShouldBePresent("bag_screen.toast_body");
            Wait.secondsUntilElementPresent("bag_screen.toast_close_button", 15);
            Elements.elementShouldBePresent("bag_screen.toast_close_button");

//            // Checks if toast messages are populated
//            try {
//                String toastFull = Elements.findElement("bag_screen.toast_text").getText();
//                String toastFirstLine = toastFull.substring(0, 12);
//                String toastSecondLine = toastFull.substring(toastFull.indexOf("Added to Bag") + 12);
//                Assert.assertEquals("Toast message does not start with Added to Bag", "Added to Bag", toastFirstLine);
//                Assert.assertTrue("Toast message product description is blank", toastSecondLine.length() > 0);
//            } catch (StaleElementReferenceException e) {
//                String toastFull = Elements.findElement("bag_screen.toast_text").getText();
//                String toastFirstLine = toastFull.substring(0, 12);
//                String toastSecondLine = toastFull.substring(toastFull.indexOf("Added to Bag") + 12);
//                Assert.assertEquals("Toast message does not start with Added to Bag", "Added to Bag", toastFirstLine);
//                Assert.assertTrue("Toast message product description is blank", toastSecondLine.length() > 0);
//            }

        } else {

            Wait.secondsUntilElementPresent("bag_screen.toast_body", 20);
            Elements.elementShouldBePresent("bag_screen.toast_body");
            Wait.secondsUntilElementPresent("bag_screen.toast_close_button", 15);
            Elements.elementShouldBePresent("bag_screen.toast_close_button");
//
////             Checks if toast messages are populated
//            try {
//                String toastFull = Elements.findElement("bag_screen.toast_text").getText();
//                String toastFirstLine = toastFull.substring(0, 12);
//                String toastSecondLine = toastFull.substring(toastFull.indexOf("Added to Bag") + 12);
//                Assert.assertEquals("Toast message does not start with Added to Bag", "Added to Bag", toastFirstLine);
//                Assert.assertTrue("Toast message product description is blank", toastSecondLine.length() > 0);
//            } catch (StaleElementReferenceException e) {
//                String toastFull = Elements.findElement("bag_screen.toast_text").getText();
//                String toastFirstLine = toastFull.substring(0, 12);
//                String toastSecondLine = toastFull.substring(toastFull.indexOf("Added to Bag") + 12);
//                Assert.assertEquals("Toast message does not start with Added to Bag", "Added to Bag", toastFirstLine);
//                Assert.assertTrue("Toast message product description is blank", toastSecondLine.length() > 0);
//            }
        }
    }

    @Then("^the toast message fades away after (\\d+) seconds$")
    public void the_toast_message_fades_away_after_seconds(int seconds) throws Throwable {

        Wait.secondsUntilElementNotPresent("bag_screen.toast_capsule", seconds);

    }


    @When("^I click the View Bag button and navigate to the bag$")
    public void i_click_the_View_Bag_button_and_navigate_to_the_bag() throws Throwable {
        Elements.elementShouldBePresent("home.uat_item_overlay_bag_button");
        Clicks.click("home.uat_item_overlay_bag_button");

    }

    @When("^I click the View Bag button and navigate to the bag on Sales Trans$")
    public void i_click_the_View_Bag_button_and_navigate_to_the_bag_on_Sales_Trans() throws Throwable {
        Elements.elementShouldBePresent("home.add_item_overlay_bag_button");
        Clicks.click("home.add_item_overlay_bag_button");

    }

    @Then("^I should be able to see the bag view$")
    public void i_should_be_able_to_see_the_bag_view() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("bag_screen.verify_page", 20);
        Elements.elementShouldBePresent("bag_screen.verify_page");
        Elements.elementPresent("bag_screen.verify_page");
        hold_currentPrice = "bag_screen.changed_price";
        hold_subTotal = "bag_screen.subtotal_amount";
    }

    @When("^I clear the confirmation layer manually$")
    public void i_clear_the_confirmation_layer_manually() throws Throwable {
        Wait.untilElementPresent("home.add_item_overlay_close_button");
        Clicks.click("home.add_item_overlay_close_button");
    }

    @When("^I do not take action for more than (\\d+) seconds$")
    public void i_do_not_take_action_for_seconds(int seconds) throws Throwable {
        Thread.sleep(seconds * 1000);
    }

    @When("^I add an item to the Checkout bag that has an associated fee$")
    public void i_add_an_item_to_the_Checkout_bag_that_has_an_associated_Fee() throws Throwable {

        Wait.untilElementPresent("bag_screen.upc_textbox");

        TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_NO_CRL);

        Wait.untilElementPresent("bag_screen.size_input");

        TextBoxes.typeTextNEnter("bag_screen.size_input", "Large");

        Wait.untilElementPresent("bag_screen.color_input");

        TextBoxes.typeTextNEnter("bag_screen.color_input", "Blue");

        Wait.untilElementPresent("bag_screen.webid_input");

        TextBoxes.typeTextNEnter("bag_screen.webid_input", WEBID_MACYS_NO_COLOR_SIZE);

        Clicks.click("bag_screen.add_to_bag_button");

    }

    @Then("^I can see the associated fee added to the bag$")
    public void i_can_see_the_associated_Fee_added_to_the_bag() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.fee_details");
        Elements.elementShouldBePresent("bag_screen.fee_name");
        Elements.elementShouldBePresent("bag_screen.fee_price");
        Elements.elementShouldBePresent("bag_screen.fee_upc");

        String fee_name = Elements.findElement("bag_screen.fee_name").getText();
        Assert.assertEquals("CDA FCLTY CHG", fee_name);

        String fee_price = Elements.findElement("bag_screen.fee_price").getText();
        Assert.assertEquals("$0.20", fee_price);

        String fee_upc = Elements.findElement("bag_screen.fee_upc").getText();
        Assert.assertEquals("34/2", fee_upc); // UPC 2880020000017

    }

    @Then("^I do not see the fee$")
    public void i_do_not_see_the_fee() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.fee_name");
        Wait.untilElementNotPresent("bag_screen.fee_price");

    }

    @Then("^I should see the fee associated in the bag$")
    public void i_should_see_the_fee_associated_in_the_bag() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.fee_details");
        Elements.elementShouldBePresent("bag_screen.fee_name");
    }

    @Then("^I can see that the fee associated is removed from the bag$")
    public void i_can_see_that_the_fee_assocaitd_is_removed_from_the_bag() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.fee_details");
        Assert.assertFalse(Elements.elementPresent("bag_screen.fee_details"));
        Wait.untilElementNotPresent("bag_screen.fee_name");
        Assert.assertFalse(Elements.elementPresent("bag_screen.fee_name"));
    }

    @Then("^I can view that the item and associated fee is removed from the bag$")
    public void i_can_view_that_the_item_and_associated_Fee_is_removed_from_the_Bag() throws Throwable {
        int no_of_items_after = Elements.findElements("bag_screen.details").size();
        Assert.assertNotEquals(no_of_items_after, no_of_items_in_bag);
        Wait.untilElementNotPresent("bag_screen.fee_name");
        Wait.untilElementNotPresent("bag_screen.fee_price");
        Wait.untilElementNotPresent("bag_screen.fee_upc");
        Wait.untilElementPresent("bag_screen.emptyBag");
    }

    @When("^I add a second item to the Checkout bag$")
    public void i_add_a_second_item_to_the_Checkout_bag() throws Throwable {
        Wait.secondsUntilElementNotPresent("home.add_item_overlay", 3);

        if (!Elements.elementPresent("bag_screen.upc_textbox")) {
            Wait.untilElementPresent("bag_screen.upc_textbox");
        }
        TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_NO_CRL);
        if (Elements.elementPresent("bag_screen.add_to_bag_button")) {
            Clicks.click("bag_screen.add_to_bag_button");
        }
    }

    @Then("^I can view the second item is still in the bag$")
    public void i_can_view_the_second_item_is_still_in_the_bag() throws Throwable {
        if (Elements.elementPresent("bag_screen.item_details")) {
            int no_of_items = Elements.findElements("bag_screen.item_details").size();
            Assert.assertEquals(2, no_of_items);
        }
    }

    @Then("^I can see the added to bag confirmation layer with default image$")
    public void i_can_see_the_added_to_bag_confirmation_layer_with_default_image() throws Throwable {
        Wait.secondsUntilElementPresent("home.add_item_overlay", 2);
        Elements.elementShouldBePresent("home.add_item_overlay");
        Elements.elementShouldBePresent("home.add_item_overlay_no_image");

    }

    @Then("^I see the bag view item with default image$")
    public void i_see_the_bag_view_item_with_default_image() throws Throwable {
        Elements.elementShouldBePresent("home.no_image");

    }

    @When("^I add an item to the Checkout bag that \"([^\"]*)\"$")
    public void iAddAnItemToTheCheckoutBagThat(String specialItem) throws Throwable {
        addSpecialUPC(specialItem);
    }

    @Then("^I can see the color size and WebID titles do not display$")
    public void i_can_see_the_color_size_and_WebID_titles_do_not_display() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.itemcolor_title");
        Wait.untilElementNotPresent("bag_screen.itemsize_title");
        Wait.untilElementNotPresent("bag_screen.webid_title");
    }

    @When("^I add an item to the Checkout bag that does not have a color$")
    public void i_add_an_item_to_the_Checkout_bag_that_does_not_have_a_color() throws Throwable {
        Wait.untilElementPresent("bag_screen.upc_textbox");

        TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_NO_COLOR_SIZE_WEBID);

        Wait.untilElementPresent("bag_screen.size_input");

        TextBoxes.typeTextNEnter("bag_screen.size_input", "Large");

        Wait.untilElementPresent("bag_screen.webid_input");

        TextBoxes.typeTextNEnter("bag_screen.webid_input", WEBID_MACYS_NO_COLOR_SIZE);

        Clicks.click("bag_screen.add_to_bag_button");

    }

    @Then("^I can see the color title does not display$")
    public void i_can_see_the_color_title_does_not_display() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.itemcolor_title");
        Assert.assertFalse(Elements.elementPresent("bag_screen.itemcolor_title"));
    }

    @When("^I add an item to the Checkout bag that does not have a size$")
    public void i_add_an_item_to_the_Checkout_bag_that_does_not_have_a_size() throws Throwable {

        Wait.untilElementPresent("bag_screen.upc_textbox");

        TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_NO_COLOR_SIZE);

        Wait.untilElementPresent("bag_screen.color_input");

        TextBoxes.typeTextNEnter("bag_screen.color_input", "Blue");

        Wait.untilElementPresent("bag_screen.webid_input");

        TextBoxes.typeTextNEnter("bag_screen.webid_input", WEBID_MACYS_NO_COLOR_SIZE);

        Clicks.click("bag_screen.add_to_bag_button");

    }

    @Then("^I can see the size title does not display$")
    public void i_can_see_the_size_title_does_not_display() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.itemsize_title");
        Assert.assertFalse(Elements.elementPresent("bag_screen.itemsize_title"));
    }

    @When("^I add an item to the Checkout bag that does not have a WebID$")
    public void i_add_an_item_to_the_Checkout_bag_that_does_not_have_a_WebID() throws Throwable {
        Wait.untilElementPresent("bag_screen.upc_textbox");

        TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_NO_COLOR_SIZE);

        Wait.untilElementPresent("bag_screen.size_input");

        TextBoxes.typeTextNEnter("bag_screen.size_input", "Large");

        Wait.untilElementPresent("bag_screen.color_input");

        TextBoxes.typeTextNEnter("bag_screen.color_input", "Blue");

        Clicks.click("bag_screen.add_to_bag_button");

    }

    @Then("^I can see the WebID title does not display$")
    public void i_can_see_the_WebID_title_does_not_display() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.webid_title");
        Assert.assertFalse(Elements.elementPresent("bag_screen.webid_title"));
    }

    @When("^I tap ellipsis menu$")
    public void i_tap_ellipsis_menu() throws Throwable {
        Wait.untilElementPresent("bag_screen.ellipsis_button");
        for (int i = 0; i < 2; i++) {
            try {
                Clicks.click("bag_screen.ellipsis_button");
            } catch (Exception e) {
                Clicks.click("bag_screen.ellipsis_button");
            }
        }

    }

    @Then("^I should see an option to Add another item$")
    public void i_should_see_an_option_to_Add_another_item() throws Throwable {
        Elements.elementPresent("bag_screen.add_another_button");
    }

    @Then("^I should see an option to Edit item$")
    public void i_should_see_an_option_to_Edit_item() throws Throwable {
        Elements.elementPresent("bag_screen.edit_item_button");
    }

    @When("^I tap Add another item button$")
    public void iTapAddAnotherItemButton() throws Throwable {
        Wait.untilElementPresent("bag_screen.add_another_button");
        Clicks.click("bag_screen.add_another_button");
        Thread.sleep(1000);
        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementNotPresent("bag_screen.spinner", 25);
            if (Elements.elementPresent("bag_screen.spinner")) {
                iCallCancel();
                Assert.fail("The add another to bag spinner remained on the screen for longer than 25 seconds");
            }
        }
    }

    @When("^I tap Edit item button$")
    public void iTapEditItemButton() throws Throwable {
        Wait.untilElementPresent("bag_screen.edit_item_button");
        try {
            Clicks.click("bag_screen.edit_item_button");
        } catch (Exception e) {
            Clicks.click("bag_screen.edit_item_button");
        }
    }

    @Then("^I verify Add another item call URL with UPC$")
    public void i_verify_Add_another_item_call_URL_with_UPC() throws Throwable {
        Wait.forPageReady();
        boolean hasPathToAdd = url().contains("/products/upcs/91709543745");
        Assert.assertTrue("Add another item call URL was " + url(), hasPathToAdd);
    }

    @Then("^I verify Edit item call URL with UPC$")
    public void i_verify_Edit_item_call_URL_with_UPC() throws Throwable {
        boolean hasPathToEdit = url().contains(UPC_MACYS_DISCOUNT);
        Assert.assertTrue("Edit item call URL was " + url(), hasPathToEdit);
    }


    @Then("^I can see the clear button$")
    public void i_can_see_the_clear_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("^I press the header clear button$")
    public void i_press_the_header_clear_button() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }


    @Then("^I see an icon above the verbiage indicating the bag is empty$")
    public void i_see_an_icon_above_the_verbiage_indicating_the_bag_is_empty() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.empty_bag_icon");
    }

    @When("^I press the remove item button$")
    public void i_press_the_remove_item_button() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.remove_button");
        Clicks.click("bag_screen.remove_button");
    }

    @When("^I see truck icon displayed in item details$")
    public void i_see_truck_icon_displayed_in_item_details() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.shiptruck_image");
    }

    @When("^I see the text \"([^\"]*)\" next to truck icon$")
    public void i_see_the_text_next_to_truck_icon(String text) throws Throwable {
        if (!Elements.findElement("bag_screen.shipitem_verbiage").getText().equals(text)) {
            Assert.fail("Verbiage mismatch");
        }
    }

    @And("^I verify verbiage as \"([^\"]*)\"$")
    public void i_verify_verbiage_as(String text) throws Throwable {
        if (!Elements.findElement("bag_screen.ellipsis_menu_add_another_item").getText().equals(text)) {
            Assert.fail("Verbiage mismatch");
        }
    }

    @When("^I scroll up and down inside the bag$")
    public void i_scroll_up_and_down_inside_the_bag() throws Throwable {
        Navigate.scrollPage(0, 0);
    }

    @Then("^I should see totals field is static at the bottom of the screen$")
    public void i_should_see_totals_field_is_static_at_the_bottom_of_the_screen() throws Throwable {
        String css = Elements.findElement("bag_screen.total").getCssValue("position");
        if (!css.equals("fixed")) {
            Assert.fail("Totals not fixed at the bottom");
        }
    }

    @Then("^I can see the checkout button$")
    public void i_can_see_the_checkout_button() throws Throwable {
        Wait.untilElementPresent("bag_screen.checkout_button");
        Elements.elementShouldBePresent("bag_screen.checkout_button");
    }

    @When("^I press the checkout button$")
    public void i_press_the_checkout_button() throws Throwable {
        Thread.sleep(2000);
        Wait.untilElementPresent("bag_screen.checkout_button");
        Elements.elementShouldBePresent("bag_screen.checkout_button");
        while (!Elements.findElement("bag_screen.checkout_button").isEnabled()) {
            Thread.sleep(1000);
        }
        Clicks.click("bag_screen.checkout_button");
    }

    @Then("^I do not see the Checkout or Suspend button$")
    public void i_do_not_see_the_Checkout_or_Suspend_button() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.checkout_button");
        Wait.untilElementNotPresent("bag_screen.suspend_button");
    }

    @Then("^I should see the wait spinner overlaying the totals area$")
    public void i_should_see_the_wait_spinner_overlaying_the_totals_area() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("^the wait spinner clears$")
    public void the_wait_spinner_clears() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^I should see the totals display area with the totals dashed out$")
    public void i_should_see_the_totals_display_area_with_the_totals_dashed_out() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("^the totals do not calculate after (\\d+) seconds$")
    public void the_totals_do_not_calculate_after_seconds(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^I should see the current error message in the totals area$")
    public void i_should_see_the_current_error_message_in_the_totals_area() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^I can see the No Image SVG$")
    public void i_can_see_the_No_Image_SVG() throws Throwable {
        Elements.elementShouldBePresent("home.no_image");
    }

    @Then("^I see the CRL Overlay$")
    public void i_see_the_CRL_Overlay() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.crl_overlay", 10);
        Elements.elementShouldBePresent("bag_screen.crl_overlay");
        Elements.elementShouldBePresent("home.overlay_close");
        Elements.elementShouldBePresent("bag_screen.crl_overlay_image");
        Wait.secondsUntilElementPresent("bag_screen.crl_overlay_productname", 10);
        Elements.elementShouldBePresent("bag_screen.crl_overlay_productname");
        Elements.elementShouldBePresent("bag_screen.crl_overlay_upclabel");

    }


    @Then("^I can see the CRL was added to the item in the bag$")
    public void i_can_see_the_CRL_was_added_to_the_item_in_the_bag() throws Throwable {
        Wait.untilElementPresent("bag_screen.item_details");
        Elements.elementShouldBePresent("bag_screen.crl_value");

        String crl_value = Elements.findElement("bag_screen.crl_value").getText();
        Assert.assertEquals("6789", crl_value);

    }

    @When("^I close the CRL Overlay$")
    public void i_close_the_CRL_Overlay() throws Throwable {
        Thread.sleep(500);

        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementPresent("home.overlay_close", 10);
            Elements.elementShouldBePresent("home.overlay_close");
            Clicks.click("home.overlay_close");
        } else {

            Wait.secondsUntilElementPresent("home.overlay_close", 10);
            Elements.elementShouldBePresent("home.overlay_close");
            Clicks.click("home.overlay_close");
        }
    }

    @Then("^I can see the CRL was NOT added to the item in the bag$")
    public void i_can_see_the_CRL_was_NOT_added_to_the_item_in_the_bag() throws Throwable {
        Wait.untilElementPresent("bag_screen.item_details");
        Wait.untilElementNotPresent("home.crl_missing");

    }


    @Then("^I can see the Invalid CRL error message$")
    public void i_can_see_the_Invalid_CRL_error_message() throws Throwable {
        Wait.untilElementPresent("bag_screen.crl_modal_error");
        Elements.elementShouldBePresent("bag_screen.crl_modal_error");

        String invalidcrl_error = Elements.findElement("bag_screen.crl_modal_error").getText();
        Assert.assertEquals("Invalid or duplicate CRL", invalidcrl_error);
    }


    @Then("^I see the duplicate CRL error message$")
    public void i_see_the_duplicate_CRL_error_message() throws Throwable {
        Wait.untilElementPresent("bag_screen.crl_modal_error");
        Elements.elementShouldBePresent("bag_screen.crl_modal_error");

        String Duplicatecrl_error = Elements.findElement("bag_screen.crl_modal_error").getText();
        Assert.assertEquals("Invalid or duplicate CRL", Duplicatecrl_error);

    }

    @When("^I Clear the field$")
    public void i_Clear_the_field() throws Throwable {
        WebElement element = Elements.findElement("bag_screen.crl_overlay_input");
        element.clear();

    }

    @Then("^I can see both CRLs in the bag view$")
    public void i_can_see_both_CRLs_in_the_bag_view() throws Throwable {
        Wait.untilElementPresent("bag_screen.item_details");
        Elements.elementShouldBePresent("bag_screen.crl_one");
        Elements.elementShouldBePresent("bag_screen.crl_two");

        String crl_one = Elements.findElement("bag_screen.crl_one").getText();
        Assert.assertEquals("CRL 123456789", crl_one);

        String crl_two = Elements.findElement("bag_screen.crl_two").getText();
        Assert.assertEquals("CRL 987654321", crl_two);


    }

    @Then("^I see the item is in a markdown status -the price of the item is lower than the original-$")
    public void iSeeTheItemIsInAMarkdownStatusThePriceOfTheItemIsLowerThanTheOriginal() throws Throwable {
        String original_price;
        String current_price;
        float numeric_current_price, numeric_original_price;

        Elements.elementShouldBePresent("pdp.uat_orig_price");
        Elements.elementShouldBePresent("pdp.uat_todays_price");

        original_price = Elements.getText("pdp.uat_orig_price");
        current_price = Elements.getText("pdp.uat_todays_price");


        original_price = original_price.replace("$", "");
        current_price = current_price.replace("$", "");

        numeric_original_price = Float.parseFloat(original_price);
        numeric_current_price = Float.parseFloat(current_price);

        Assert.assertTrue("Current price is higher than original price", numeric_current_price < numeric_original_price);


    }

    @Then("^I see the item is in a markdown status -the price of the item is lower than the original on Sales Trans$")
    public void iSeeTheItemIsInAMarkdownStatusThePriceOfTheItemIsLowerThanTheOriginalOnSalesTrans() throws Throwable {
        String original_price;
        String current_price;
        float numeric_current_price, numeric_original_price;

        Elements.elementShouldBePresent("bag_screen.item_currentprice");
        Elements.elementShouldBePresent("bag_screen.item_origprice");

        original_price = Elements.getText("bag_screen.item_origprice");
        current_price = Elements.getText("bag_screen.item_currentprice");


        original_price = original_price.replace("$", "");
        current_price = current_price.replace("$", "");

        numeric_original_price = Float.parseFloat(original_price);
        numeric_current_price = Float.parseFloat(current_price);

        Assert.assertTrue("Current price is higher than original price", numeric_current_price < numeric_original_price);


    }

    @And("^I see the original price of the item has a strikethrough$")
    public void iSeeTheOriginalPriceOfTheItemHasAStrikethrough() throws Throwable {
        Thread.sleep(500);
        Wait.secondsUntilElementPresent("bag_screen.item_origprice", 5);
        Assert.assertTrue("Original price does not have strikethrough", elementHasStrikeThrough(Elements.findElement("bag_screen.item_origprice")));
    }


    @And("^I see the original price of the item has a strikethrough on Sales Trans$")
    public void iSeeTheOriginalPriceOfTheItemHasAStrikethroughOnSalesTrans() throws Throwable {
        Assert.assertTrue("Original price does not have strikethrough", elementHasStrikeThrough(Elements.findElement("bag_screen.item_origprice")));
    }

    @And("^I see the actual price of the item also displays$")
    public void iSeeTheActualPriceOfTheItemAlsoDisplays() throws Throwable {
        Assert.assertTrue("Actual price is not displayed.", Elements.elementPresent("pdp.uat_todays_price"));
    }

    @And("^I see the actual price of the item also displays on Sales Trans$")
    public void iSeeTheActualPriceOfTheItemAlsoDisplaysOnSalesTrans() throws Throwable {
        Assert.assertTrue("Actual price is not displayed.", Elements.elementPresent("bag_screen.item_currentprice"));
    }


    @Given("^I go to URL path \"([^\"]*)\"$")
    public void iGoToURLPath(String arg0) throws Throwable {
        Navigate.visit("customer");
    }

    @When("^I add an item and then tab to another field$")
    public void iAddAnItemAndThenTabToAnotherField() throws Throwable {
        Wait.untilElementPresent("bag_screen.color_input");
        typeTextNTab("bag_screen.color_input", "Blue");
        Thread.sleep(3000);


    }

    @When("^I click on the bag icon$")
    public void iClickOnTheBagIcon() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(3000);
        if (Elements.elementPresent("bag_screen.spinner") || (Elements.elementPresent("bag_screen.toast_close_button"))) {
            Wait.secondsUntilElementNotPresent("bag_screen.toast_close_button", 5);
            Wait.untilElementPresent("home.progressBar_bag");
            Clicks.click("home.progressBar_bag");
        } else {
            Wait.secondsUntilElementNotPresent("bag_screen.toast_close_button", 5);
            Wait.untilElementPresent("home.progressBar_bag");
            Clicks.click("home.progressBar_bag");

        }
    }

    @When("^I add a product that is not place order$")
    public void iAddAProductThatIsNotPlaceOrder() throws Throwable {
        i_add_an_item_to_the_Checkout_bag();
    }

    @Then("^I am redirected to Tendering screen$")
    public void iAmRedirectedToTenderingScreen() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I click the item image$")
    public void iClickTheItemImage() throws Throwable {
        Wait.untilElementPresent("bag_screen.item_image");
        Thread.sleep(2000);
        Elements.elementShouldBePresent("bag_screen.item_image");
        Clicks.click("bag_screen.item_image");
        Thread.sleep(1000);
    }
//This step can be used if there is ever an input field on CRL again.
//    @When("^I input \"([^\"]*)\" as a CRL$")
//    public void iInputAsACRL(String crl) throws Throwable {
//        if (!Elements.elementPresent("bag_screen.crl_overlay_input")) {
//            goToHome();
//        }
//        TextBoxes.typeTextNEnter("bag_screen.crl_overlay_input", crl);
//        //Wait.untilElementPresent("bag_screen.crl_add_button");
//        //   Clicks.click("bag_screen.crl_add_button");
//
//        if (Elements.elementPresent("bag_screen.crl_add_button")) {
//            Clicks.click("bag_screen.crl_add_button");
//        }
//
//        if (Elements.elementPresent("bag_screen.crl_submit_button")) {
//            Clicks.click("bag_screen.crl_submit_button");
//        }
//    }

    @And("^I see the Cancel Transaction button$")
    public void iSeeTheCancelTransactionButton() throws Throwable {
        Wait.untilElementPresent("bag_screen.cancel_transaction_button");
        Elements.elementShouldBePresent("bag_screen.cancel_transaction_button");
        Thread.sleep(2000);
    }

    @Then("^I click on Cancel Transaction button$")
    public void iClickOnCancelTransationButton() throws Throwable {
        Thread.sleep(500);
        Wait.secondsUntilElementNotPresent("bag_screen.toast_close", 10);
        Elements.elementShouldBePresent("bag_screen.cancel_transaction_button");
        Elements.elementInView("bag_screen.cancel_transaction_button");
        Clicks.click("bag_screen.cancel_transaction_button");
        Thread.sleep(1000);
        if (Elements.elementPresent("tendering.authorize_overlay")) {
            Wait.secondsUntilElementNotPresent("tendering.authorize_overlay", 10);
        }

        if (Elements.elementPresent("pdp.search_icon")) {
            Wait.secondsUntilElementPresent("pdp.landing_message", 5);

        } else {
            Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 10);
        }
    }


    @And("^I can see the confirm cancellation overlay$")
    public void iCanSeeTheConfirmCancellationOverlay() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.overlay");
        Elements.elementShouldBePresent("home.overlay_close");
        Elements.elementShouldBePresent("bag_screen.overlay_header");
        Elements.elementShouldBePresent("bag_screen.overlay_message");
        Elements.elementShouldBePresent("bag_screen.cancel_cancel_button");
        Elements.elementShouldBePresent("bag_screen.cancel_confirm_button");

        String CancelTitle = Elements.findElement("bag_screen.overlay_header").getText();
        Assert.assertEquals("Cancel Transaction", CancelTitle);

        String CancelMsg = Elements.findElement("bag_screen.overlay_message").getText();
        Assert.assertEquals("Continuing will delete the entire bag", CancelMsg);
    }

    @Then("^I press cancel on the confirm cancellation overlay$")
    public void iPressNoOnTheConfirmCancellationOverlay() throws Throwable {
        Wait.untilElementPresent("bag_screen.cancel_cancel_button");
        Clicks.click("bag_screen.cancel_cancel_button");
    }


    @When("^I press Yes on the confirm cancellation overlay$")
    public void iPressYesOnTheConfirmCancellationOverlay() throws Throwable {
        Clicks.click("bag_screen.cancel_confirm_button");
    }

    @Then("^I should not see the suspend button$")
    public void iShouldNotSeeTheSuspendButton() throws Throwable {
        Assert.assertFalse("Suspend button is visible on page", Elements.elementPresent("bag_screen.suspend_button"));
    }

    @Then("^I should be able to see subtotals information is updated$")
    public void iShouldBeAbleToSeeSubtotalsInformationIsUpdated() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.details", 5);
        Wait.secondsUntilElementPresent("bag_screen.product_fee", 5);

        if (Elements.elementPresent("bag_screen.disabled")) {
            Wait.secondsUntilElementNotPresent("bag_screen.disabled", 10);
        }

        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementNotPresent("bag_screen.spinner", 10);
        }

        if (Elements.elementPresent("bag_screen.details")) {
            int no_of_items = Elements.findElements("bag_screen.bag_item").size();
            int no_of_taxes = Elements.findElements("bag_screen.product_fee").size();
            BigDecimal[] prices = new BigDecimal[no_of_items];
            BigDecimal[] taxes = new BigDecimal[no_of_taxes];
            BigDecimal sub_total = BigDecimal.ZERO;
            BigDecimal calculated_subtotals = BigDecimal.ZERO;
            BigDecimal total_taxes = BigDecimal.ZERO;

            // getting individual prices and totaling
            try {
                for (int i = 0; i < no_of_items; i++) {
                    String currentPrice = Elements.findElements("bag_screen.item_currentprice").get(i).getText();
                    currentPrice = currentPrice.substring(currentPrice.indexOf("$") + 1);
                    prices[i] = new BigDecimal(currentPrice);
                    sub_total = sub_total.add(prices[i]);
                }
            } catch (StaleElementReferenceException e) {
                for (int i = 0; i < no_of_items; i++) {
                    String currentPrice = Elements.findElements("bag_screen.item_currentprice").get(i).getText();
                    currentPrice = currentPrice.substring(currentPrice.indexOf("$") + 1);
                    prices[i] = new BigDecimal(currentPrice);
                    sub_total = sub_total.add(prices[i]);
                }
            }

            // getting product taxes and totaling
            try {
                for (int i = 0; i < no_of_taxes; i++) {
                    String currentTax = Elements.findElements("bag_screen.product_fee").get(i).getText();
                    currentTax = currentTax.substring(currentTax.indexOf("$") + 1);
                    taxes[i] = new BigDecimal(currentTax);
                    total_taxes = total_taxes.add(taxes[i]);
                }
            } catch (StaleElementReferenceException e) {
                for (int i = 0; i < no_of_taxes; i++) {
                    String currentTax = Elements.findElements("bag_screen.product_fee").get(i).getText();
                    currentTax = currentTax.substring(currentTax.indexOf("$") + 1);
                    taxes[i] = new BigDecimal(currentTax);
                    total_taxes = total_taxes.add(taxes[i]);
                }
            }

            //calculating subtotals
            Wait.secondsUntilElementPresent("bag_screen.bag_fee_amount", 5);
            if (Elements.elementPresent("bag_screen.bag_fee_label")) {
                //getting bag price on screen
                BigDecimal bag_amount = BigDecimal.ZERO;

                try {
                    String bag = Elements.findElement("bag_screen.bag_fee_amount").getText();
                    bag = bag.substring(bag.indexOf("$") + 1);
                    bag_amount = new BigDecimal(bag);
                    calculated_subtotals = sub_total.add(total_taxes).add(bag_amount);
                } catch (StaleElementReferenceException e) {
                    String bag = Elements.findElement("bag_screen.bag_fee_amount").getText();
                    bag = bag.substring(bag.indexOf("$") + 1);
                    bag_amount = new BigDecimal(bag);
                    calculated_subtotals = sub_total.add(total_taxes).add(bag_amount);
                }

            } else {
                calculated_subtotals = sub_total.add(total_taxes);
                subtotal_bag = calculated_subtotals;
            }

            // getting sub total on screen
            Wait.secondsUntilElementPresent("bag_screen.subtotal_amount", 10);
            String sub_total_screen = Elements.findElement("bag_screen.subtotal_amount").getText();

            //if subtotals show as $NaN because the app is slow to calculate,
            //we'll wait for the subtotal to be calculated before continuing
            int timer = 0;
            while (sub_total_screen.equals("$NaN")) {
                if (timer <= 20) {
                    Thread.sleep(2000);
                    timer++;
                    sub_total_screen = Elements.findElement("bag_screen.subtotal_amount").getText();
                }
            }

            sub_total_screen = sub_total_screen.substring(sub_total_screen.indexOf("$") + 1);
            BigDecimal sub_total_app = new BigDecimal(sub_total_screen);

            // assert sub totals
            Assert.assertTrue("Comparing sub total calculation with sub total on screen", calculated_subtotals.equals(sub_total_app));

        } else {
            Assert.fail("Unable to find any items");
        }
    }

    @Then("^I should be able to see subtotal for the added items$")
    public void iShouldBeAbleToSeeSubtotalForTheAddedItems() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("bag_screen.subtotal_amount", 15);
        Assert.assertTrue("Subtotal is not displayed.", Elements.elementPresent("bag_screen.subtotal_amount"));
    }

    @When("^I scroll down inside the bag$")
    public void iScrollDownInsideTheBag() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(1000);
        Navigate.scrollPage(155, 400);
        System.out.println("I SCROLLED");
    }

    @Then("^I should not see the totals$")
    public void iShouldNotSeeTheTotals() throws Throwable {
        Assert.assertFalse("Totals are visible on page", Elements.elementPresent("bag_screen.total"));
    }

    @And("^I should not see the estimated sales tax$")
    public void iShouldNotSeeTheEstimatedSalesTax() throws Throwable {
        Assert.assertFalse("Sales Taxes are visible on page", Elements.elementPresent("bag_screen.tax_amount"));

    }

    @Then("^I can see the \"([^\"]*)\" CRL in the bag$")
    public void i_can_see_the_CRL_in_the_bag(String CRL) throws Throwable {
        Wait.untilElementPresent("bag_screen.item_details");
        Elements.elementShouldBePresent("bag_screen.crl_label");
        Elements.elementShouldBePresent("bag_screen.crl_value");

        String first_crl_label = Elements.findElement("bag_screen.crl_label").getText();
        Assert.assertEquals("CRL", first_crl_label);

        String first_crl = Elements.findElement("bag_screen.crl_value").getText();
        Assert.assertEquals(CRL, first_crl);
    }


    @When("^I add an item to the Checkout bag for a Take Sale$")
    public void iAddAnItemToTheCheckoutBagForATakeSale() throws Throwable {
        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 15);
            Elements.elementShouldBePresent("bag_screen.upc_textbox");
            Clicks.click("bag_screen.taketransradio_button");
            TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_DISCOUNT);
            Clicks.click("bag_screen.add_to_bag_button");

        } else {
            if (Elements.elementPresent("home.overlay_close")) {
                Clicks.click("home.overlay_close");
            }
            Clicks.click("home.progressBar_product");
            Clicks.click("bag_screen.taketransradio_button");
            TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_DISCOUNT);
            Clicks.click("bag_screen.add_to_bag_button");
        }


    }

    @Then("^I can see the second bag icon removed$")
    public void i_can_see_the_second_bag_icon_removed() throws Throwable {
        Assert.assertFalse("Second bag icon is visible on page", Elements.elementPresent("home.show_bag"));
    }

    @Then("^I can see the Bag/Send icons display$")
    public void i_can_see_the_Bag_Send_icons_display() throws Throwable {
        Elements.elementShouldBePresent("home.bag_send_icon");
    }

    @Then("^I do not see the bag confirmation layer$")
    public void i_do_not_see_the_bag_confirmation_layer() throws Throwable {
        Assert.assertFalse("Confirmation layer is visible", Elements.elementPresent("home.add_item_overlay"));
    }


    @Then("^I can see UAT Checkout empty bag view$")
    public void iCanSeeUATCheckoutEmptyBagView() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.emptyBag", 20);
        Elements.elementShouldBePresent("bag_screen.emptyBag");

        if (!Elements.elementPresent("bag_screen.emptyBag")) {
            Assert.fail("Empty bag element not displayed");
        }

        if (!Elements.getText("bag_screen.emptyBag").equals("Bag is empty\nScan or search for items")) {
            Assert.fail("Empty bag message was incorrect");

        }
    }


    @And("^I can see the item price$")
    public void iCanSeeTheItemPrice() throws Throwable {
        Assert.assertTrue("Item price is not displayed", Elements.elementPresent("tendering.tender_first_list_item_price"));
    }

    @And("^I can see the subtotal$")
    public void iCanSeeTheSubtotal() throws Throwable {
        String subtotalLabel = Elements.findElement("tendering.tender_subtotal_label").getText();
        Assert.assertEquals("Subtotal was not found", "Subtotal", subtotalLabel);
        Assert.assertTrue("Subtotal amount is not displayed", Elements.elementPresent("tendering.tender_subtotal_amount"));
    }

    @And("^I can see the item list in a summary view$")
    public void iCanSeeTheItemListInASummaryView() throws Throwable {
        Wait.untilElementPresent("tendering.tender_first_list_item_description");
        Assert.assertTrue("Items are not displayed in list view", Elements.elementPresent("tendering.tender_first_list_item_description"));
        Assert.assertTrue("Items are not displayed in list view", Elements.elementPresent("tendering.tender_first_list_item_price"));
    }

    @And("^I can see the tax amount$")
    public void iCanSeeTheTaxAmount() throws Throwable {
        Assert.assertTrue("Tax is not visible on screen", Elements.elementPresent("tendering.tender_tax_amount"));
    }

    @And("^I can see the shipping fee$")
    public void iCanSeeTheShippingFee() throws Throwable {
        String shippingFeeLabel = Elements.findElement("tendering.tender_shipping_fee_label").getText();
        Assert.assertEquals("Shipping fee label was not found", "Shipping Fee", shippingFeeLabel);
        Assert.assertTrue("Shipping fee amount is not displayed", Elements.elementPresent("tendering.tender_shipping_fee_amount"));
    }

    @And("^The shipping fee is missing$")
    public void theShippingFeeIsMissing() throws Throwable {
        Assert.assertTrue("Shipping fee label is displayed, but shouldn't be", !Elements.elementPresent("tendering.tender_shipping_fee_label"));
        Assert.assertTrue("Shipping fee amount is displayed, but shouldn't be", !Elements.elementPresent("tendering.tender_shipping_fee_amount"));
    }


    @When("^I scan \"([^\"]*)\" in to the CRL Overlay$")
    public void iScanInToTheCRLOverlay(String UPC) throws Throwable {
        Thread.sleep(500);
        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementNotPresent("bag_screen.spinner", 5);
        }
        Wait.secondsUntilElementPresent("bag_screen.crl_overlay", 10);
        Elements.elementShouldBePresent("bag_screen.crl_overlay");
        ScanCrl(UPC);


        // Implementation for a random CRl entry
//        int ran_no = genrateRandomNumber(000000000, 999999999);
//        String random2 = Integer.toString(ran_no);
//        TextBoxes.typeTextNEnter("bag_screen.crl_overlay_input", random2);

    }

    @Then("^I can see the slide out menu$")
    public void iCanSeeTheSlideOutMenu() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.slide_out_menu");
    }

    @And("^I can see the slide out menu options$")
    public void iCanSeeTheSlideOutMenuOptions() throws Throwable {
        // More and remove icons are properly displayed
        Elements.elementShouldBePresent("bag_screen.more_button");
        Elements.elementShouldBePresent("bag_screen.remove_button_bag");
        Wait.untilElementPresent("bag_screen.more_icon_text");
        Wait.untilElementPresent("bag_screen.remove_icon_text");

        String moreIconText = Elements.findElement("bag_screen.more_icon_text").getText();
        String removeIconText = Elements.findElement("bag_screen.remove_icon_text").getText();

        Assert.assertTrue("More Icon text is not properly displayed as expected.", Elements.findElement("bag_screen.more_icon_text").isDisplayed());
        Assert.assertTrue("Remove Icon text is not properly displayed as expected.", Elements.findElement("bag_screen.remove_icon_text").isDisplayed());
        Assert.assertTrue("More Icon text is not as expected. Unexpected text:" + moreIconText, moreIconText.equals("More"));
        Assert.assertTrue("Remove Icon text is not as expected. Unexpected text:" + removeIconText, removeIconText.equals("Remove"));
    }


    @When("^I click on the more button$")
    public void iClickOnTheMoreButton() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.more_button");
        Actions click = new Actions(WebDriverManager.getWebDriver());
        WebElement ele = WebDriverManager.getWebDriver().findElement(By.className("button-more"));
        click.moveToElement(ele, 0, 0).click().build().perform();
    }

    @Then("^I can see the options 'Edit Item','Add Another' and 'Price Change'$")
    public void iCanSeeTheOptionsEditItemAndAddAnother() throws Throwable {
        Wait.untilElementPresent("bag_screen.edit_item_button");
        Assert.assertTrue("Edit button is not visible",
                Elements.elementInView("bag_screen.edit_item_button"));
        Assert.assertTrue("Add Another button is not visible",
                Elements.elementInView("bag_screen.add_another_button"));
        Assert.assertTrue("Change Price button is not visible",
                Elements.elementInView("bag_screen.change_price_button"));
    }

    @Then("^I can no longer see the options list$")
    public void iCanNoLongerSeeTheOptionsList() throws Throwable {
        Wait.secondsUntilElementNotPresent("bag_screen.edit_item_button", 3);
        Wait.secondsUntilElementNotPresent("bag_screen.add_another_button", 3);
        Wait.secondsUntilElementNotPresent("bag_screen.change_price_button", 3);

        Assert.assertFalse("Edit button is still visible",
                Elements.elementPresent("bag_screen.edit_item_button"));
        Assert.assertFalse("Add Another button is still visible",
                Elements.elementPresent("bag_screen.add_another_button"));
        Assert.assertFalse("Change Price button is still visible",
                Elements.elementPresent("bag_screen.change_price_button"));
    }

    @When("^I click the remove button$")
    public void iClickTheRemoveButton() throws Throwable {
        Thread.sleep(1000);
        Clicks.click("bag_screen.remove_button_bag");
        Thread.sleep(3000);
        if (elementPresent("bag_screen.cancel_overlay")) {
            Wait.secondsUntilElementNotPresent("bag_screen.cancel_overlay", 5);
        }
    }

    @Then("^I can no longer see the slide out menu$")
    public void iCanNoLongerSeeTheSlideOutMenu() throws Throwable {
        if (Elements.elementPresent("bag_screen.more_button")) {
            Assert.fail("Slide out menu (more button)is present");
        }
        if (Elements.elementPresent("bag_screen.remove_button_bag")) {
            Assert.fail("Slide out menu (remove button)is present");

        }
    }

    @When("^I select Take option$")
    public void iSelectTakeOption() throws Throwable {
        if (!Elements.elementPresent("bag_screen.upc_textbox")) {
            goToHome();
        }
        Wait.untilElementPresent("bag_screen.taketransradio_button");
        Clicks.click("bag_screen.taketransradio_button");

    }

    @When("^I click on the bag action button$")
    public void iClickOnTheBagActionButton() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(3000);
        Wait.secondsUntilElementPresent("home.bag_action", 20);
        Elements.elementPresent("home.bag_action");
        if (Elements.elementPresent("bag_screen.toast_body") || Elements.elementPresent("bag_screen.spinner")) {
            Thread.sleep(500);
            Wait.secondsUntilElementNotPresent("bag_screen.toast_body", 10);
            Wait.secondsUntilElementNotPresent("bag_screen.spinner", 10);
            Wait.secondsUntilElementNotPresent("pdp.pdp_spinner", 10);

        }

        Thread.sleep(1000);
        int no_of_items_in_header_icon = Integer.parseInt(Elements.findElement("home.bag_action").getText());
        if (no_of_items_in_header_icon >= 45) {
            WebDriver driver = null;
            try {
                driver = WebDriverManager.getWebDriver();
            } catch (DriverNotInitializedException e) {
                e.printStackTrace();
            }
            Clicks.click("home.bag_action");
            Thread.sleep(2000);
            Wait.forPageReady();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            Navigate.scrollPage(0, -100);
            hoverForSelection("bag_screen.suspend_button");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("bagButtonSuspend")));
        }
        else {
            Clicks.click("home.bag_action");
            Thread.sleep(1000);
            Wait.secondsUntilElementPresent("bag_screen.checkout_button", 5);
        }
    }


    @And("^I see the Device row$")
    public void iSeeTheDeviceRow() throws Throwable {
        Elements.elementShouldBePresent("home.device_row");
    }

    @And("^I click on Device row$")
    public void iClickOnDeviceRow() throws Throwable {
        Clicks.click("home.device_row");
    }

    @Then("^I can see Device information$")
    public void iCanSeeDeviceInformation() throws Throwable {
        Elements.elementShouldBePresent("home.store_number");
        Elements.elementShouldBePresent("home.register_number");
        Elements.elementShouldBePresent("home.machine_name");
        Elements.elementShouldBePresent("home.device_IP_address");
        Elements.elementShouldBePresent("home.device_gateway");
        Elements.elementShouldBePresent("home.device_netmask");
        Elements.elementShouldBePresent("home.ISP_IP_address");
        Elements.elementShouldBePresent("home.ISP_hostname");
        Elements.elementShouldBePresent("home.SSP_IP_address");
        Elements.elementShouldBePresent("home.SSP_hostname");
        Elements.elementShouldBePresent("home.hal_version");
    }

    @And("^I see the Scanner row$")
    public void iSeeTheScannerRow() throws Throwable {
        Elements.elementShouldBePresent("home.scanner_row");
    }

    @And("^I click on the Scanner row$")
    public void iClickOnTheScannerRow() throws Throwable {
        Clicks.click("home.scanner_row");
    }

    @Then("^I can see Scanner information$")
    public void iCanSeeScannerInformation() throws Throwable {
        Elements.elementShouldBePresent("home.scanner_status");
        Elements.elementShouldBePresent("home.scanner_version");
    }

    @Then("^I see the Other row$")
    public void iSeeTheOtherRow() throws Throwable {
        Elements.elementShouldBePresent("home.other_row");
    }

    @When("^I click on the Other row$")
    public void iClickOnTheOtherRow() throws Throwable {
        Clicks.click("home.other_row");
    }

    @Then("^I can see Other configurations$")
    public void iCanSeeOtherConfigurations() throws Throwable {
        Elements.elementShouldBePresent("home.other_information");
    }

    @And("^I can see the Input field and add button were removed$")
    public void iCanSeeTheInputFieldAndAddButtonWereRemoved() throws Throwable {
        if (!Elements.elementPresent("bag_screen.crl_overlay_input")) {

        } else {

            Assert.fail("Input Field Displayed");

        }
        if (!Elements.elementPresent("bag_screen.crl_submit_button")) {
        } else {
            Assert.fail("Add to bag button Displayed");
        }
    }


    @Then("^The CRL overlay closes$")
    public void theCRLOverlayCloses() throws Throwable {
        Wait.secondsUntilElementNotPresent("bag_screen.crl_overlay", 5);
        if (Elements.elementPresent("bag_screen.crl_overlay")) {
            Assert.fail("CRl Overlay did not close");
        }

    }

    @When("^I scan UPC \"([^\"]*)\" into the bag$")
    public void iScanUPCIntoTheBag(String UPC) throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.bag_screen_verify_page", 5);
        if (Elements.elementPresent("bag_screen.spinner")) {

            Wait.secondsUntilElementNotPresent("bag_screen.spinner", 10);
        }
        Assert.assertTrue("Spinner was still displayed.", !Elements.elementPresent("bag_screen.spinner"));
        ScanUPC(UPC);

    }

    @Then("^I can see the Recall error$")
    public void i_can_see_the_Recall_error() throws Throwable {
        Elements.elementShouldBePresent("home.confirmation_overlay");
        Elements.elementShouldBePresent("bag_screen.confirmation_overlay_header");
        Elements.elementShouldBePresent("bag_screen.confirmation_overlay_message");
        Elements.elementShouldBePresent("home.overlay_close");
        Elements.elementShouldBePresent("bag_screen.cancel_confirm_button");

        String recallHeader = Elements.findElement("bag_screen.confirmation_overlay_header").getText();
        Assert.assertEquals("Recalled Item.", recallHeader);
        String recallMessage = Elements.findElement("bag_screen.confirmation_overlay_message").getText();
        Assert.assertEquals("This item can not be sold due to a recall. Please remove it and any others from the selling floor.", recallMessage);
        String confirmButtonText = Elements.findElement("bag_screen.cancel_confirm_button").getText();
        Assert.assertEquals("OK", confirmButtonText);
    }

    @Then("^I close the Recall error$")
    public void i_close_the_Recall_error() throws Throwable {
        Wait.untilElementPresent("bag_screen.cancel_confirm_button");
        Clicks.click("bag_screen.cancel_confirm_button");
    }

    @Then("^I can see the Restricted Item error$")
    public void i_can_see_the_Restricted_Item_error() throws Throwable {
        Wait.secondsUntilElementPresent("home.confirmation_overlay", 10);
        Elements.elementShouldBePresent("home.confirmation_overlay");
        Elements.elementShouldBePresent("home.overlay_close");
        Elements.elementShouldBePresent("bag_screen.cancel_confirm_button");

        String restrictedMessage = Elements.findElement("home.confirmation_overlay").getText();
        Assert.assertEquals("This item cannot be purchased via this application. Complete this transaction at the full-functionality register.\nOK, I got it", restrictedMessage);
        String confirmButtonText = Elements.findElement("bag_screen.cancel_confirm_button").getText();
        Assert.assertEquals("OK, I got it", confirmButtonText);


    }

    @When("^I close the Restricted Item error$")
    public void i_close_the_Restricted_Item_error() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.cancel_confirm_button", 5);
        Elements.elementShouldBePresent("home.confirmation_overlay");
        Clicks.click("bag_screen.cancel_confirm_button");
        Wait.secondsUntilElementNotPresent("home.confirmation_overlay", 5);
    }


    @Then("^I can see the sales trans landing page$")
    public void iCanSeeTheSalesTransLandingPage() throws Throwable {
        Thread.sleep(2000);
        Wait.forPageReady();
        if (Elements.elementPresent("home.overlay_close")) {
            Clicks.click("home.overlay_close");
        }
        Wait.secondsUntilElementPresent("home.verify_page", 25);
        Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 20);
        Elements.elementShouldBePresent("bag_screen.upc_textbox");
        Elements.elementShouldBePresent("home.verify_page");
        String Salestranstitle = Elements.findElement("home.page_title").getText();
        Assert.assertEquals("Add Product", Salestranstitle);

    }


    @When("^I add an item to the Checkout bag that costs less than \"([^\"]*)\"$")
    public void iAddAnItemToTheCheckoutBagThatCostsLessThanDollars(String DivLessthan30) throws Throwable {
        Thread.sleep(1000);
        switch (DivLessthan30) {
            case "$30 for Macy's":
                TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_MACYS_LESS_THAN_30);
                if (Elements.elementPresent("bag_screen.add_to_bag_button")) {
                    Clicks.click("bag_screen.add_to_bag_button");
                }
                break;
            case "$30 for Bloomingdale's":
                TextBoxes.typeTextNEnter("bag_screen.upc_textbox", UPC_BLOOM_LESS_THAN_30);
                if (Elements.elementPresent("bag_screen.add_to_bag_button")) {
                    Clicks.click("bag_screen.add_to_bag_button");
                }
        }
    }


    @And("^I swipe the item from right to left$")
    public void iSwipeTheItemFromRightToLeft() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("bag_screen.item_currentprice", 5);
        Clicks.swipe("bag_screen.item_currentprice", "bag_screen.item_image");

    }


    @When("^I slide the item from left to right$")
    public void iSlideTheItemFromLeftToRight() throws Throwable {
        Thread.sleep(1000);
        Clicks.swipe("bag_screen.item_currentprice", "bag_screen.remove_button_bag");
    }

    @When("^I input \"([^\"]*)\" in the suspend text box$")
    public void iInputInTheSuspendTextBox(String textSuspendName) throws Throwable {
        typeText("bag_screen.overlay_textbox", textSuspendName);
    }


    @Then("^I do not see the red error rectangle$")
    public void iDoNotSeeTheRedErrorRectangle() throws Throwable {
        Assert.assertFalse("Red Error Rectangle is visible but shouldn't be", Elements.elementPresent("bag_screen.red_error_rectangle"));
    }

    @Then("^I see the red error rectangle$")
    public void iSeeTheRedErrorRectangle() throws Throwable {
        Assert.assertTrue("Red Error Rectangle is not visible but should be", Elements.elementPresent("bag_screen.red_error_rectangle"));
    }


    @And("^I can see \"([^\"]*)\" in the suspend text box$")
    public void iCanSeeInTheSuspendTextBox(String text) throws Throwable {
        Assert.assertEquals("Customer name in textbox is not" + text, Elements.findElement("bag_screen.overlay_textbox").getAttribute("value"), text);
    }

    @When("^I input \"([^\"]*)\" as a CRL$")
    public void iInputAsACRL(String CRL) throws Throwable {
        Wait.untilElementPresent("bag_screen.crl_overlay_input");
        TextBoxes.typeTextNEnter("bag_screen.crl_overlay_input", CRL);

        if (Elements.elementPresent("bag_screen.crl_add_button")) {
            Clicks.click("bag_screen.crl_add_button");
        }

    }


    @Then("^I do not see the bag fee overlay$")
    public void iDoNotSeeTheBagFeeOverlay() throws Throwable {
        Assert.assertFalse("Bag fee overlay is visible, but shouldn't be", Elements.elementPresent("bag_screen.bag_fee_overlay"));
    }

    @When("^I add \"([^\"]*)\" bags$")
    public void iAddBags(String bag_no) throws Throwable {
        if (!Elements.elementPresent("bag_screen.bag_fee_input")) {
            goToHome();
        }
        typeText("bag_screen.bag_fee_input", bag_no);
        Wait.untilElementPresent("bag_screen.bag_fee_enter_button");
        Elements.elementShouldBePresent("bag_screen.bag_fee_enter_button");
        Clicks.click("bag_screen.bag_fee_enter_button");
    }

    @When("^I type \"([^\"]*)\" bags$")
    public void TypedBags(String bag_no) throws Throwable {
        if (!Elements.elementPresent("bag_screen.bag_fee_input")) {
            goToHome();
        }
        typeText("bag_screen.bag_fee_input", bag_no);
        Wait.untilElementPresent("bag_screen.bag_fee_enter_button");
        Elements.elementShouldBePresent("bag_screen.bag_fee_enter_button");
    }

    @And("^I can see the Bag Fee$")
    public void iCanSeeTheBagFee() throws Throwable {
        Wait.secondsUntilElementPresent(By.cssSelector(".qe-bag-fee-label"), 5);
        Assert.assertTrue("Bag Fee label is not displayed", Elements.elementPresent("bag_screen.bag_fee_label"));
        Wait.secondsUntilElementPresent(By.cssSelector(".align-right.qe-bag-fee-amount"), 5);
        Assert.assertTrue("Bag Fee amount is not displayed", Elements.elementPresent("bag_screen.bag_fee_amount"));
    }


    @When("^I add an extra bag$")
    public void iAddAnExtraBag() throws Throwable {
        String current_bag_count = Elements.findElement("bag_screen.bag_fee_overlay_textbox").getAttribute("value");
        int bag_count = Integer.parseInt(current_bag_count);
        int new_bag_no = bag_count + 1;
        String bags = String.valueOf(new_bag_no);
        Elements.findElement("bag_screen.bag_fee_input").clear();
        TextBoxes.typeTextbox("bag_screen.bag_fee_input", bags);
        Wait.secondsUntilElementPresent("bag_screen.bag_fee_enter_button", 3);
        Clicks.click("bag_screen.bag_fee_enter_button");
    }

    @When("^I remove a bag$")
    public void iRemoveABag() throws Throwable {
        String current_bag_count = Elements.findElement("bag_screen.bag_fee_overlay_textbox").getAttribute("value");
        int bag_count = Integer.parseInt(current_bag_count);
        int new_bag_no = bag_count - 1;
        String bags = String.valueOf(new_bag_no);
        Elements.findElement("bag_screen.bag_fee_input").clear();
        TextBoxes.typeTextbox("bag_screen.bag_fee_input", bags);
        Wait.secondsUntilElementPresent("bag_screen.bag_fee_enter_button", 3);
        Clicks.click("bag_screen.bag_fee_enter_button");

    }

    @Then("^I can see the unathorized to ring error message$")
    public void iCanSeeTheUnathorizedToRingErrorMessage() throws Throwable {
        Elements.elementShouldBePresent("home.confirmation_overlay");
        Elements.elementShouldBePresent("bag_screen.overlay_confirm_button");

        String unauthtoring = Elements.findElement("bag_screen.confirmation_overlay_header").getText();
        Assert.assertEquals("Not Authorized", unauthtoring);

        String unauthmsg = Elements.findElement("bag_screen.confirmation_overlay_message").getText();
        Assert.assertEquals("You are not currently authorized to ring transactions. Talk with your manager if your permissions need changed.", unauthmsg);

    }

    @When("^I close the error message$")
    public void iCloseTheErrorMessage() throws Throwable {
        Wait.untilElementPresent("bag_screen.overlay_confirm_button");
        Elements.elementShouldBePresent("bag_screen.overlay_confirm_button");
        Clicks.click("bag_screen.overlay_confirm_button");

    }

    @When("^I set the unathorized manager level$")
    public void iSetTheUnathorizedManagerLevel() throws Throwable {
        ManagerLevel0();
        refreshPage();
        Wait.forPageReady();
        Thread.sleep(2000);
    }

    @And("^I see the disabled suspend and checkout buttons$")
    public void iSeeTheDisabledSuspendAndCheckoutButtons() throws Throwable {
        Thread.sleep(1000);
        Elements.elementShouldBePresent("bag_screen.checkout_button");
        Elements.elementShouldBePresent("bag_screen.suspend_button");

        Assert.assertFalse("Button is not disabled", Elements.findElement("bag_screen.checkout_button").isEnabled());
        Assert.assertFalse("Button is not disabled", Elements.findElement("bag_screen.suspend_button").isEnabled());
    }


    @Then("^I should not be able to see the cancel transaction button$")
    public void iShouldNotBeAbleToSeeTheCancelTransactionButton() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.cancel_transaction_button");
        if (Elements.elementPresent("bag_screen.cancel_transaction_button")) {
            Assert.fail("Cancel transaction button is present");
        }
    }


    @Then("^I close the Input Billing Information screen$")
    public void iCloseTheInputBillingInformationScreen() throws Throwable {
        Wait.untilElementPresent("bag_screen.billing_info_details");
        Clicks.click("sends.shipping_info_close");
    }

    @When("^I close the Order Review Overlay$")
    public void iCloseTheOrderReviewOverlay() throws Throwable {
        Wait.untilElementPresent("sends.order_review_overlay");
        Clicks.click("home.overlay_close");
    }

    @Then("^I select More menu option$")
    public void ISelectMoreMenuOption() throws Throwable {
        Wait.untilElementPresent("bag_screen.more_button");
        Assert.assertTrue("More option is not displayed on the screen.", Elements.findElement("bag_screen.more_button").isDisplayed());
        Clicks.click("bag_screen.more_button");
    }

    @Then("^The change price overlay displays properly$")
    public void thePriceChangeOverlayDisplays() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_title");
        Elements.elementShouldBePresent("bag_screen.change_price_title");
        Wait.untilElementPresent("bag_screen.update_price_button");
        Elements.elementShouldBePresent("bag_screen.update_price_button");
        Wait.untilElementPresent("bag_screen.change_price_close_button");
        Elements.elementShouldBePresent("bag_screen.change_price_close_button");
    }

    @And("^I can close the change price overlay$")
    public void ICanCloseTheChangePriceOverlay() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_close_button");
        Elements.elementShouldBePresent("bag_screen.change_price_close_button");
        Clicks.click("bag_screen.change_price_close_button");
        // TODO : replace the sleep
        Thread.sleep(5000);
    }

    @And("^Associate can return to the bag screen$")
    public void associateCanReturnToTheBagScreen() throws Throwable {
        Assert.assertTrue("Bag title is not displayed on the screen.", findElement("home.page_title").isDisplayed());
        Wait.untilElementPresent("bag_screen.suspend_button");
        Thread.sleep(3000);
        Assert.assertTrue("Suspend button is not displayed on the screen.", findElement("bag_screen.suspend_button").isDisplayed());
        Wait.untilElementPresent("bag_screen.checkout_button");
        Assert.assertTrue("Checkout button is not displayed on the screen.", findElement("bag_screen.checkout_button").isDisplayed());
    }

    @And("^Associate can return to the bag screen when a take transaction is completed$")
    public void associateCanReturnToTheBagScreenWhenATakeTransactionIsCompleted() throws Throwable {
        Assert.assertTrue("Bag title is not displayed on the screen.", findElement("home.page_title").isDisplayed());
        Wait.untilElementPresent("bag_screen.checkout_button");
        Assert.assertTrue("Checkout button is not displayed on the screen.", findElement("bag_screen.checkout_button").isDisplayed());
    }

    @And("^Check if change price title text is present and it's the expected one$")
    public void changePriceTextIsPresentAndItSTheExpectedOne() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_title");
        Elements.elementShouldBePresent("bag_screen.change_price_title");
        String changePriceTitle = findElement("bag_screen.change_price_title").getText();
        Assert.assertTrue("Change price title text is not the expected one and is not properly displayed.", changePriceTitle.equals(change_price_title_text));
    }


    @When("^Price of the item is updated in bag$")
    public void priceOfTheItemIsUpdatedInBag() throws Throwable {
        //Temporary solution using sleep
        Thread.sleep(3000);
        Wait.untilElementPresent("bag_screen.item_origprice");
        String changedPrice = Elements.findElement("bag_screen.item_origprice").getText();
        Thread.sleep(3000);
        Wait.untilElementPresent("bag_screen.item_currentprice");
        String oldOriginalPrice = Elements.findElement("bag_screen.item_currentprice").getText();
        Assert.assertNotEquals("Price of the item is not updated in the bag.", oldOriginalPrice, changedPrice);
    }


    @Then("^Subtotal reflects the new price of item$")
    public void subtotalReflectsTheNewPriceOfItem() throws Throwable {
        String changedPrice = Elements.findElement("bag_screen.changed_price").getText();
        Thread.sleep(1000);
        // Compare changed bag subtotal to original bag subtotal
        // hold_currentPrice is assigned a value in i_should_be_able_to_see_the_bag_view
        String subtotal = Elements.findElement("bag_screen.subtotal_amount").getText();
        Assert.assertEquals("Subtotal is not correctly displayed.", changedPrice, subtotal);
        // String prevsubtotal = Elements.findElement("bag_screen.subtotal_amount").getText();
        // Assert.assertEquals("Subtotal is not correctly displayed.", prevsubtotal, subtotal);
    }

    @When("^I complete price change with \"([^\"]*)\"$")
    public void associateCompletesPriceChangeWith(String invalid_input) throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_input_field");
        Assert.assertTrue("Change price input field is not displayed on the page.", Elements.findElement("bag_screen.change_price_input_field").isDisplayed());
        Elements.findElement("bag_screen.change_price_input_field").sendKeys(BACK_SPACE);
        Elements.findElement("bag_screen.change_price_input_field").sendKeys(invalid_input);
    }

    @When("^I add (\\d+) to quantity$")
    public void iAddToQuantity(String count) throws Throwable {
        if (!Elements.elementPresent("home.enter_quantity_textbox")) {
            goToHome();
        }
        typeText("home.enter_quantity_textbox", count);
    }

    @Then("^I should see (\\d+) items in the bag$")
    public void iShouldSeeItemsInTheBag(int count) throws Throwable {
        if (Elements.elementPresent("bag_screen.swipeable_container")) {
            int no_of_items = Elements.findElements("bag_screen.swipeable_container").size();
            Assert.assertEquals(count, no_of_items);
        }
    }

    @Then("^I should see the unable to add item popup$")
    public void iShouldSeeTheUnableToAddItemPopup() throws Throwable {
        Elements.elementShouldBePresent("home.confirmation_overlay");
    }

    @Then("^I see the add to bag spinner$")
    public void iSeeTheAddToBagSpinner() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.spinner");
    }

    @Then("^Check that Only one of the three options can be used$")
    public void checkThatOnlyOneOfTheThreeOptionsCanBeUsed() throws Throwable {
        Thread.sleep(5000);
        Assert.assertTrue("Price option is not displayed on the screen.", Elements.findElement("bag_screen.price_option").isDisplayed());
        Assert.assertTrue("Price option is not selected.", Elements.findElement("bag_screen.price_option").isEnabled());
    }

    @Then("^No input is displayed$")
    public void noInputIsDisplayed() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_input_field");
        Assert.assertTrue("Change price input field is not displayed on the page.", Elements.findElement("bag_screen.change_price_input_field").isDisplayed());
        String inputTextValue = Elements.findElement("bag_screen.change_price_input_field").getAttribute("value");

        // Input Field Text should not be displayed because invalid data
        // Issue When user types negative values it switches to positive values --> the test will fail because negative values should not be allowed
        Assert.assertTrue("Invalid data was accepted.Unexpected input : " + inputTextValue, inputTextValue.equals(change_price_default_input_field));
    }

    @When("^Associate selects price option$")
    public void associateSelectsPriceOption() throws Throwable {
        Assert.assertTrue("Price option is not displayed on the screen.", Elements.findElement("bag_screen.price_option").isDisplayed());
        Elements.findElement("bag_screen.price_option").click();
    }

    @Then("^Check if input text is the expected one$")
    public void checkIfInputTextIsTheExpectedOne() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_close_button");
        Elements.elementShouldBePresent("bag_screen.change_price_close_button");
        Clicks.click("bag_screen.change_price_close_button");
        Thread.sleep(5000);
    }

    @And("^I type a new price value$")
    public void ITypeANewPriceValue() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_input_field");
        Assert.assertTrue("Change price input field is not displayed on the page.", Elements.findElement("bag_screen.change_price_input_field").isDisplayed());
        Elements.findElement(Elements.element("bag_screen.change_price_input_field")).sendKeys("4545");
    }

    @Then("^Complete the price change and check default symbol$")
    public void compareThePriceChangeCheckDefaultSymbol() throws Throwable {
        String originalPrice = Elements.findElement("bag_screen.changed_price").getText();
        Assert.assertTrue("Input field is not displayed on the page.", Elements.findElement("bag_screen.change_price_input_field").isDisplayed());
        Elements.findElement("bag_screen.price_option").click();
        Elements.findElement("bag_screen.change_price_input_field").click();

        String inputTextDefaultValue = Elements.findElement("bag_screen.change_price_input_field").getAttribute("value");

        Assert.assertTrue("The default value is not as expected.", inputTextDefaultValue.contains(change_price_default_input_field));
        Wait.untilElementPresent("bag_screen.update_price_button");
        Assert.assertTrue("Update price button is not displayed on the screen.", Elements.findElement("bag_screen.update_price_button").isDisplayed());
        Elements.findElement("bag_screen.update_price_button").click();
    }

    @And("^Default value for change price option is set on price option$")
    public void defaultValueForChangePriceOptionIsSetOnPriceOption() throws Throwable {
        Assert.assertTrue("Price Option is not displayed on the screen ", Elements.findElement("bag_screen.price_option").isDisplayed());
    }

    @When("^I select change price from the More menu options$")
    public void IselectChangePriceActionFromTheMoreMenuOptions() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_action");
        Assert.assertTrue("Change price from More Menu options is not displayed.", Elements.findElement("bag_screen.change_price_action").isDisplayed());
        Clicks.click("bag_screen.change_price_action");
    }


    @When("^Page \"([^\"]*)\" is properly loaded$")
    public void pageIsProperlyLoaded(String screen) throws Throwable {
        Thread.sleep(2000);
        switch (screen) {
            case "Home":
                Assert.assertTrue("Home button is not displayed on the screen.", Elements.findElement("bag_screen.home_button").isDisplayed());

                break;

            case "Tendering":
                Wait.untilElementPresent("tendering.tendering_screen");
                Elements.elementShouldBePresent("tendering.tendering_screen");
                String tenderingScreenTitle = Elements.findElement("tendering.tendering_screen").getText();
                Assert.assertEquals("You're not on the tendering screen", "Tendering", tenderingScreenTitle);
                break;

            case "Payment":

                break;
        }


    }

    @Then("^I do not see the add item or edit item buttons$")
    public void iDoNotSeeTheAddItemOrEditItemButtons() throws Throwable {
        if (Elements.elementPresent("bag_screen.edit_item_button")) {
            Assert.fail("Add Item Displayed");
        }

        if (Elements.elementPresent("bag_screen.add_another_button")) {
            Assert.fail("Edit Item Displayed");
        }
    }

    @But("^I see the change price button$")
    public void iSeeTheChangePriceButton() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_button");
        Elements.elementShouldBePresent("bag_screen.change_price_button");

        if (!Elements.elementPresent("bag_screen.change_price_button")) {
            Assert.fail("Price Change did not display");

        }
    }

    @When("^I Scan in a random crl$")
    public void iGenerateARandomCrl() throws Throwable {
        int ran_no = GenerateRandomCRL();
        String randomCRL = Integer.toString(ran_no);

        ScanCrl(randomCRL);

    }

    @When("^I close the customer name overlay$")
    public void iCloseTheCustomerNameOverlay() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.overlay");
        Elements.elementShouldBePresent("bag_screen.overlay_cancel_button");
        Elements.elementShouldBePresent("bag_screen.overlay_confirm_button");
        Elements.elementShouldBePresent("bag_screen.overlay_header");
        Elements.elementShouldBePresent("bag_screen.overlay_message");
        Elements.elementShouldBePresent("bag_screen.overlay_textbox");
        Elements.elementShouldBePresent("home.overlay_close");
        Clicks.click("home.overlay_close");
    }

    @Then("^I can see the bag action menu$")
    public void iCanSeeTheBagActionMenu() throws Throwable {
        Elements.elementShouldBePresent("home.bag_action");
    }

    @And("^I can verify that I don't see the send arrow$")
    public void iCanVerifyThatIDonTSeeTheSendArrow() throws Throwable {
        Elements.elementShouldBePresent("home.bag_action");
        Wait.secondsUntilElementNotPresent("home.bag_send_icon", 5);
        if (Elements.elementPresent("home.bag_send_icon")) {
            Assert.fail("Send arrow is present");
        }
    }

    @Then("^I complete percent change and overlay closes$")
    public void iCompletePercentChangeAndOverlayCloses() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_input_field");
        Assert.assertTrue("Change price input field is not displayed on the page.", Elements.findElement("bag_screen.change_price_input_field").isDisplayed());
        Wait.untilElementPresent("bag_screen.percent_option");
        Elements.elementShouldBePresent("bag_screen.percent_option");
        Elements.findElement("bag_screen.percent_option").click();

        //Take 10% off price
        Elements.findElement(Elements.element("bag_screen.change_price_input_field")).sendKeys("10");
        Wait.untilElementPresent("bag_screen.update_price_button");
        Assert.assertTrue("Update price button is not displayed on the screen.", Elements.findElement("bag_screen.update_price_button").isDisplayed());
        Elements.findElement("bag_screen.update_price_button").click();
    }

    @Then("^I complete dollar change and overlay closes$")
    public void iCompleteDollarChangeAndOverlayCloses() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_input_field");
        Assert.assertTrue("Change price input field is not displayed on the page.", Elements.findElement("bag_screen.change_price_input_field").isDisplayed());
        Wait.untilElementPresent("bag_screen.dollar_off_option");
        Elements.elementShouldBePresent("bag_screen.dollar_off_option");
        Elements.findElement("bag_screen.dollar_off_option").click();
        //Take $25.00 off price
        Elements.findElement(Elements.element("bag_screen.change_price_input_field")).sendKeys("25.00");
        Wait.untilElementPresent("bag_screen.update_price_button");
        Assert.assertTrue("Update price button is not displayed on the screen.", Elements.findElement("bag_screen.update_price_button").isDisplayed());
        Elements.findElement("bag_screen.update_price_button").click();
    }

    @And("^I call Cancel$")
    public void iCallCancel() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(1000);
        CancelTrans();
        Thread.sleep(1000);
        Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 5);
        Wait.secondsUntilElementPresent("pdp.landing_message", 5);
        if (!Elements.elementPresent("bag_screen.upc_textbox") || !Elements.elementPresent("pdp.landing_message")) {

            Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 5);
            Wait.secondsUntilElementPresent("pdp.landing_message", 5);
        }
    }

    @And("^Items Counter is updated$")
    public void itemCounterIsUpdated() throws Throwable {
        // WIP - waiting for the story to be completed
        Assert.assertTrue("Items counter is not displayed.", Elements.findElement("home.bag_action").isDisplayed());
        Clicks.click("home.bag_action");
        //Assert.assertTrue("",Elements.findElement("pdp.uat_items_number").isDisplayed());
        int no_of_items = Elements.findElements("bag_screen.bag_item").size();
        int itemsCounter = Elements.findElements("home.bag_action").size();
        Assert.assertEquals("Unexpected number of items : " + no_of_items, itemsCounter, no_of_items);
    }

    @And("^No extra tendering text is displayed on the bottom page of Tendering screen$")
    public void noExtraTenderingTextIsDisplayedOnTheBottomPageOfTenderingScreen() throws Throwable {
        Wait.forPageReady();
        String extraTenderingText = Elements.findElement("tendering.page_content").getText();
        Assert.assertTrue("Extra tendering text is displayed on Tendering screen.", !extraTenderingText.contains(TENDERING_TEXT));
    }

    @When("^I click the bag menu$")
    public void iClickTheBagMenu() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(2000);
        Wait.secondsUntilElementPresent("home.bag_action", 20);
        Elements.elementPresent("home.bag_action");
        if (Elements.elementPresent("bag_screen.toast_body") || Elements.elementPresent("bag_screen.spinner")) {
            Thread.sleep(500);
            Wait.secondsUntilElementNotPresent("bag_screen.toast_body", 10);
            Wait.secondsUntilElementNotPresent("bag_screen.spinner", 10);
            Wait.secondsUntilElementNotPresent("pdp.pdp_spinner", 10);

        }

        Assert.assertTrue("Bag Menu is not displayed as expected.", Elements.findElement("home.bag_action").isDisplayed());
        Clicks.click("home.bag_action");

    }

    @Then("^I can see the product fees$")
    public void iCanSeeTheProductFees() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.product_fee", 5);
        Assert.assertTrue("Fees are not visible",
                Elements.elementPresent("bag_screen.product_fee"));
    }

    @Then("^I do not see the product fees$")
    public void iDoNotSeeTheProductFees() throws Throwable {
        Assert.assertTrue("Fees are visible, but shouldn't be",
                !Elements.elementPresent("bag_screen.product_fee"));
    }

    @When("^I select \"([^\"]*)\" from the More menu options$")
    public void iSelectFromTheMoreMenuOptions(String option) throws Throwable {
        switch (option) {
            case "Change Price":
                Wait.untilElementPresent("bag_screen.change_price_action");
                Assert.assertTrue("Change price from More Menu options is not displayed.",
                        Elements.findElement("bag_screen.change_price_action").isDisplayed());
                Clicks.click("bag_screen.change_price_action");
                break;

            case "Add Gift Receipt":
                Wait.untilElementPresent("bag_screen.add_gift_receipt_action");
                Assert.assertTrue("Add Gift Receipt from More Menu options is not displayed.",
                        Elements.findElement("bag_screen.add_gift_receipt_action").isDisplayed());
                Clicks.click("bag_screen.add_gift_receipt_action");
                break;

            case "Remove Gift Receipt":
                Wait.untilElementPresent("bag_screen.remove_gift_receipt_action");
                Assert.assertTrue("Remove Gift Receipt from More Menu options is not displayed.",
                        Elements.findElement("bag_screen.remove_gift_receipt_action").isDisplayed());
                Clicks.click("bag_screen.remove_gift_receipt_action");
                break;

        }
    }

    @When("^The update button should be disabled$")
    public void the_update_button_should_be_disabled() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.update_price_button");
        Assert.assertFalse("Button is not disabled", Elements.findElement("bag_screen.update_price_button").isEnabled());
    }

    @When("^I click the update button$")
    public void i_click_the_update_button() throws Throwable {
        Elements.elementShouldBePresent("bag_screen.update_price_button");

    }

    @Then("^an Invalid price value error message is displayed$")
    public void an_Invalid_price_value_error_message_is_displayed() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.toast_capsule");
        if (Elements.elementPresent("bag_screen.toast_capsule")) {
            Assert.fail("Invalid price value");
        }
    }

    @When("^I select dollar Off from the Change Price options$")
    public void iSelectDollarOffFromTheChangePriceOptions() throws Throwable {
        Wait.untilElementPresent("bag_screen.change_price_input_field");
        Assert.assertTrue("Change price input field is not displayed on the page.", Elements.findElement("bag_screen.change_price_input_field").isDisplayed());
        Wait.untilElementPresent("bag_screen.dollar_off_option");
        Elements.elementShouldBePresent("bag_screen.dollar_off_option");
        Elements.findElement("bag_screen.dollar_off_option").click();
        //Take $130.00 off price
        Elements.findElement(Elements.element("bag_screen.change_price_input_field")).sendKeys("130.00");
        Wait.untilElementPresent("bag_screen.update_price_button");
        Assert.assertTrue("Update price button is not displayed on the screen.", Elements.findElement("bag_screen.update_price_button").isDisplayed());
        Elements.findElement("bag_screen.update_price_button").click();
    }

    @Then("^an Invalid dollar off value error message is displayed$")
    public void an_Invalid_dollar_off_value_error_message_is_displayed() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.toast_capsule");
        if (Elements.elementPresent("bag_screen.toast_capsule")) {
            Assert.fail("Invalid dollar off value");
        }
    }

    @Then("^The gift indicator is visible in the product area$")
    public void theGiftIndicatorIsVisibleInTheProductArea() throws Throwable {
        Assert.assertTrue("The gift indicator is not visible in the product area",
                Elements.elementPresent("bag_screen.gift_receipt_label"));
    }

    @Then("^The gift indicator is not visible in the product area$")
    public void theGiftIndicatorIsNotVisibleInTheProductArea() throws Throwable {
        Assert.assertTrue("The gift indicator is  visible in the product area, but shouldn't be",
                !Elements.elementPresent("bag_screen.gift_receipt_label"));
    }

    @When("^I go back to the previous page$")
    public void iGoBackToThePreviousPage() throws Throwable {
        Wait.forPageReady();
        GoBacktoPreviousPage();
    }

    @When("^I click the product icon$")
    public void iClickTheProductIcon() throws Throwable {
        Elements.elementShouldBePresent("home.progressBar_product");
        Clicks.click("home.progressBar_product");
    }

    @Then("^The \"([^\"]*)\" option is not available$")
    public void theOptionIsNotAvailable(String step) throws Throwable {
        switch (step) {

            case "Add Gift Receipt":
                Assert.assertFalse("The " + step + " option is visible, but shouldn't be",
                        Elements.elementPresent("bag_screen.add_gift_receipt_action"));
                break;

        }
    }

    @And("^Plenti points error toast on bag screen is not displayed after after unsuccessful authorization$")
    public void plentiPointsErrorToastDisplayedOnTheBagScreen() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.toast_capsule");
        if (Elements.elementPresent("bag_screen.toast_capsule")) {
            Assert.fail("Plenti points error toast is still displaying");
        }
    }

    @And("^An error indicating that the card was not approved is displayed$")
    public void anErrorIndicatingThatTheCardWasNotApprovedIsDisplayed() throws Throwable {
        Wait.untilElementPresent("tendering.declined_card_confirm_button");
        Assert.assertTrue("An error indicating that the card was not approved is not displayed.", Elements.findElement("tendering.declined_card_confirm_button").isDisplayed());
    }

    @When("^I click on I Got this button$")
    public void iClickOnIGotThisButton() throws Throwable {
        Wait.untilElementPresent("tendering.declined_card_confirm_button");
        Elements.findElement("tendering.declined_card_confirm_button").click();
    }

    @And("^I can see the shipping fee displayed and set to \"([^\"]*)\"$")
    public void iCanSeeTheShippingFeeDisplayedAndSetTo(String amount) throws Throwable {
        iCanSeeTheShippingFee();
        String shippingFeeLabel = Elements.findElement("tendering.tender_shipping_fee_amount").getText();
        Assert.assertEquals("Shipping fee amount is not " + amount, amount, shippingFeeLabel);
    }

    @Then("^I see the correct value displayed \"([^\"]*)\"$")
    public void iSeeTheCorrect(String bag_no) throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.bag_fee_input", 5);
        String correct_bag_no = Elements.findElement("bag_screen.bag_fee_input").getAttribute("value");
        Assert.assertEquals("Unexpected Input displayed : " + correct_bag_no, correct_bag_no, bag_no);
    }

    @When("^I click Enter on bag overlay$")
    public void iClickEnterOnBagOverlay() throws Throwable {
        Wait.untilElementPresent("bag_screen.bag_fee_enter_button");
        Elements.elementShouldBePresent("bag_screen.bag_fee_enter_button");
        Clicks.click("bag_screen.bag_fee_enter_button");
    }

    @And("^I do not see a CRL in the bag$")
    public void iDoNotSeeACRLInTheBag() throws Throwable {
        if (Elements.elementPresent("bag_screen.crl_value")) {
            Assert.fail("There was a CRL in the bag");

        }
    }

    @And("^I can see that bag icon is bolded$")
    public void iCanSeeBagIconBolded() throws Throwable {
        Wait.untilElementPresent("bag_screen.bag_action");
        String pressedStatus = Elements.findElement("home.bag_action").getAttribute("class");
        Assert.assertTrue("Bag icon is not bolded.", pressedStatus.contains("selected"));
    }

    @Then("^I can see that bag icon is not bolded$")
    public void iCanSeeThatBagIconIsNotBolded() throws Throwable {
        Wait.untilElementPresent("bag_screen.bag_action");
        String pressedStatus = Elements.findElement("home.bag_action").getAttribute("class");
        Assert.assertTrue("Bag icon is boldeed.", !pressedStatus.contains("selected"));
    }

    @And("^I press the sales trans add to bag button$")
    public void iPressTheSalesTransAddToBagButton() throws Throwable {
        Clicks.click("bag_screen.add_to_bag_button");
    }

    @When("^I scroll down to see items listed$")
    public void iScrollDownToSeeItemsListed() throws Throwable {
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getWebDriver();
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Actions dragger = new Actions(WebDriverManager.getWebDriver());
        WebElement draggablePart = WebDriverManager.getWebDriver().findElement(By.cssSelector("#itemContainer3"));

        int numberOfPixelsToDragTheScrollbarDown = 50;
        for (int i = 10; i < 100; i = i + numberOfPixelsToDragTheScrollbarDown) {
            try {
                dragger.moveToElement(draggablePart).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release().perform();
                Thread.sleep(1000L);
            } catch (Exception e1) {
            }

        }
    }

    @When("^I scan Coupon \"([^\"]*)\" into the bag$")
    public void iScanCouponintothebag(String Coupon) throws Throwable {
        ScanCoupon(Coupon);
    }


    @When("^I scan something other than a UPC or coupon \"([^\"]*)\" into the bag$")
    public void iscansomethingotherthanaUPCorcouponintothebag(String Coupon) throws Throwable {
        ScanCoupon(Coupon);
    }

    @Then("^the coupon added message should be displayed$")
    public void the_coupon_added_message_should_be_displayed() throws Throwable {
        Wait.untilElementNotPresent("bag_screen.toast_capsule");
        if (Elements.elementPresent("bag_screen.toast_capsule")) {
            Assert.fail("Coupon Added");
        }
    }

    @Then("^I see the unable to scan toast message$")
    public void i_see_the_unable_to_scan_toast_message() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.toast_capsule", 5);
        if (!Elements.elementPresent("bag_screen.toast_capsule")) {
            Assert.fail("Unable to scan item");
        }
    }

    @Then("^the coupon number should be displayed$")
    public void the_coupon_number_should_be_displayed() throws Throwable {
        Wait.untilElementPresent("bag_screen.coupon_label");
        Assert.assertTrue(Elements.findElement("bag_screen.coupon_label").isDisplayed());
        Assert.assertTrue("The coupon number is not displayed as expected", Elements.findElement("bag_screen.coupon_label").isDisplayed());
    }

    @Then("^the coupon message should be static$")
    public void the_coupon_message_should_be_static() throws Throwable {
        String css = Elements.findElement("bag_screen.coupon_label").getCssValue("position");
        if (!css.equals("fixed")) {
            Assert.fail("Coupon message is static");
        }
    }

    @Then("^I cannot see the Cancel Transaction Button$")
    public void iCannotSeeTheCancelTransactionButton() throws Throwable {
        Wait.untilElementPresent("pdp.about_button");
        if (Elements.elementPresent("bag_screen.cancel_transaction_button")) {
            Assert.fail("Cancel Button is present");

        }
    }

    @And("^Progress bar is not displayed$")
    public void progressBarIsNotDisplayed() throws Throwable {
        Wait.untilElementPresent("home.progress_bar");
        Assert.assertTrue("Progress bar is still displayed.", !Elements.findElement("home.progress_bar").isDisplayed());
    }

    @When("^I refresh the page$")
    public void iRefreshThePage() throws Throwable {
        refreshPage();
        Wait.forPageReady();
    }

    @Then("^I hand key a \"([^\"]*)\"$")
    public void iHandKeyA(String UPC) throws Throwable {
        Wait.untilElementPresent("bag_screen.hand_key_input");
        Elements.findElement("bag_screen.hand_key_input").click();
        typeText("bag_screen.hand_key_input", UPC);
        sendEnter("bag_screen.hand_key_input");
    }

    @And("^I see the invalid UPC error message$")
    public void iSeeTheInvalidUPCErrorMessage() throws Throwable {
        String invalidUPCError = Elements.findElement("bag_screen.toast_body").getText();
        Assert.assertTrue("Invalid barcode error message is not displayed properly.Unexpected text: " + invalidUPCError, invalidUPCError.equals(INVALID_UPC_ERROR_MESSAGE));
    }
}