package com.macys.sdt.projects.Selection.ResponsivePDP.steps.mcom;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.projects.Selection.DigitalProductXapi.steps.PDPxAPISteps;
import com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom.Actions;
import com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom.Devices;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.CreateProfile;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class PDP {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PageNavigation.class);
    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static SoftAssertions softly = new SoftAssertions();
    private static String url = "https://www.rpdp-18a.tbe.zeus.fds.com";
    //    private static String url = "http://ma277mlvpdx011:8080";//Production URL
    private static boolean deviceRunStatus = false;
    private static boolean xApiFccValidationEnabled = RunConfig.booleanParam("xApiFccValidationEnabled");
    private static Random rand = new Random();
    private static String userProfileName = null;
    private static String productTitle = null;
    private static String PID;
    private static String webID;
    private static String defaultColor;
    private static String selectedColor = null;
    private static String selectedSize = null;
    private static int selectedQty = 1;
    private static String originalPrice;
    private static String salePrice;
    private static String bagId = "";


    @Given("^I visit the home page on (desktop|mobile|tablet) as a (guest|registered|registry) user$")
    public void visitHomePageOnDifferentDevices(String device, String user) throws Throwable {
        log.info("\nxApiFcc Validation Enabled::   "+xApiFccValidationEnabled+
                "\n===========================================================================\n");

        deviceRunStatus = false;
        if(Devices.runScenario(device)) {
            deviceRunStatus = true;
            Actions.changeDimension(device);
            Thread.sleep(1000);
            Actions.visitURL(url);
            deletingAllCookies(device);
            if(user.equalsIgnoreCase("guest"))
                userProfileName = "Guest User";
            else if (user.equalsIgnoreCase("registered")) {
                try {
                    UserProfileService.createRandomUserProfile();
                } catch (AssertionError e) {
                    log.warning("Service failed!");
                }
                CommonUtils.signInOrCreateAccount();
                CreateProfile.closeSecurityAlertPopUp();
                Thread.sleep(500);
                userProfileName = Elements.findElement(Elements.element("home.profileName")).getText().replace("Hi","").trim();
                Clicks.click("home.macysLogo");
                Thread.sleep(500);
                Actions.visitURL(url.replace("?cm_sp=navigation-_-top_nav-_-macys_icon",""));
                log.info("Verified "+ userProfileName + " as signed-in user, clicked on Macys logo to navigate to home page!");
            }
            else if (user.equalsIgnoreCase("registry")) {
            }
            log.info("Visiting the site as " + userProfileName);
        }
        Thread.sleep(1000);
    }

    @Given("^I navigate directly to \"([^\"]*)\" PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void navigatingDirectlyToPDP(String productType, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Configuration testData = new PropertiesConfiguration("Selection/ResponsivePDP/src/main/resources/testData.properties");
            PID = testData.getString(productType);
            String[] pId = PID.split(" ");
            int index = rand.nextInt(pId.length);
            PID = pId[index];
            log.info(userProfileName + " randomly selected PID:: "+PID+
                    "\n===========================================================================\n");

            String url = WebDriverManager.getCurrentUrl();
            if(!url.contains("shop/product/"))
                Actions.visitURL(url+"shop/product?ID="+PID+"&experience=responsive");
//                Actions.visitURL(url+"shop/product?ID="+PID);//Production URL
            else {
                String[] newUrl = WebDriverManager.getCurrentUrl().split("\\?ID=");
                Actions.visitURL(newUrl[0]+"\\?ID="+PID+"&experience=responsive");
//                Actions.visitURL(newUrl[0]+"?ID="+PID);//Production URL
            }

            //Temporarily solution for memberWithTypeColor url
            if(productType.equals("memberWithTypeColor")) {
                String[] productTypeUrl = WebDriverManager.getCurrentUrl().split("com/");
                Actions.visitURL(productTypeUrl[0]+"com/shop/product/radley-5-piece-fabric-chaise-sectional-sofa-created-for-macys?ID="+PID+"&experience=responsive");
//                String[] productTypeUrl = WebDriverManager.getCurrentUrl().split(":8080/");//Production URL
//                Actions.visitURL(productTypeUrl[0]+":8080/shop/product/radley-5-piece-fabric-chaise-sectional-sofa-created-for-macys?ID="+PID);//Production
            }

            try {
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("pdp.productTitle", 10);
                productTitle = Elements.findElement(Elements.element("pdp.productBrand")).getText() + " "
                        + Elements.findElement(Elements.element("pdp.productName")).getText();
                log.info(userProfileName + " randomly selected  \"" + productTitle + "\"");
            } catch (Exception e) {
                log.warning(productType + " PDP not loading correctly as productTitle is not displayed in " + mode + " mode!");
                e.printStackTrace();
            }
            if(!device.equalsIgnoreCase("desktop"))
                Actions.scroll("down", null);
        }
        Thread.sleep(1000);
    }

//    @Given("^I navigate directly to \"([^\"]*)\" PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
//    public void navigatingDirectlyToPDP(String productType, String mode, String device) throws Throwable {
//        if(deviceRunStatus) {
//            String[] url = WebDriverManager.getCurrentUrl().split("\\?");
//            PID = Products.ID(productType);
//            url[0] += "?ID=" + PID;
//            Actions.visitURL(url[0]);
//            log.info("PID::  " + PID);
//            try {
//                Wait.forPageReady();
//                Wait.secondsUntilElementPresent("pdp.productTitle", 10);
//            } catch (Exception e) {
//                log.warning(String.format(productType + " PDP loading improperly as productTitle is not displayed in " + mode + " mode!"));
//                e.printStackTrace();
//            }
//            if(device.equalsIgnoreCase("mobile"))
//                Actions.scroll("down", null);
//        }
//        Thread.sleep(1000);
//    }

    @Given("^I navigate to (master|member|eGiftCard|memberBigTicket) PDP (site|iship|registry) mode with PID \"([^\"]*)\" on (desktop|mobile|tablet)$")
    public void navigatingDirectlyToPDPWithPID(String productType, String mode, String pId, String device) throws Throwable {
        if(deviceRunStatus) {
            PID = pId;
            String url = WebDriverManager.getCurrentUrl();
            if(!url.contains("shop/product/"))
                Actions.visitURL(url+"shop/product/lenox-federal-platinum-5-piece-place-setting?ID="+PID+"&experience=responsive");
//            Actions.visitURL(url+"shop/product/lenox-federal-platinum-5-piece-place-setting?ID="+PID);//Production URL
            else {
                String[] newUrl = WebDriverManager.getCurrentUrl().split("\\?ID=");
                Actions.visitURL(newUrl[0]+"\\?ID="+PID+"&experience=responsive");
//                Actions.visitURL(newUrl[0]+"?ID="+PID);//Production URL
            }
            log.info(userProfileName + " randomly selected PID:: " + PID);
            try {
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("pdp.productTitle", 10);
                productTitle = Elements.findElement(Elements.element("pdp.productTitle")).getText();
            } catch (Exception e) {
                log.warning(productType + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!");
                e.printStackTrace();
            }
            if(productTitle.contains("\n"))
                productTitle = productTitle.replace("\n"," ");
            log.info(userProfileName + " randomly selected  \"" + productTitle + "\"");

            if(!device.equalsIgnoreCase("desktop"))
                Actions.scroll("down", null);
        }
        Thread.sleep(1000);
    }

    @When("^I search for a \"([^\"]*)\" product & navigate to PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void searchingProductAndNavigatingToPDP(String productType, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Configuration config = new PropertiesConfiguration("Selection/ResponsivePDP/src/main/resources/config.properties");
            PID = config.getString(productType);
            String[] pId = PID.split(" ");
            int index = rand.nextInt(pId.length);
            PID = pId[index];
            log.info(userProfileName + " searching product ID " + PID + " and navigating to PDP!");

            TextBoxes.typeTextNEnter("searchNbrowse.globalSearchBox", PID);
            String url = WebDriverManager.getCurrentUrl();
            Actions.visitURL(url+"&experience=responsive");
            try {
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("pdp.productTitle", 10);
                productTitle = Elements.findElement(Elements.element("pdp.productBrand")).getText() + " "
                        + Elements.findElement(Elements.element("pdp.productName")).getText();
                log.info(userProfileName + " searched for \"" + productTitle + "\"");
            } catch (Exception e) {
                log.warning(productType + " PDP not loading as productTitle not displayed in " + mode + " mode on " + device + "!");
                e.printStackTrace();
            }
        }
        Thread.sleep(1000);
    }

    @When("^I select a random (master|member) product on (desktop|mobile|tablet) and navigate to PDP in (site|iship|registry) mode$")
    public void selectingProductNavigatingToPDP(String pg, String device, String mode) throws Throwable {
        if(deviceRunStatus) {
        }
        Thread.sleep(1000);
    }

    @And("^I refresh the browser on (desktop|mobile|tablet)")
    public void refreshBrowser(String device) throws Throwable {
        if(deviceRunStatus) {
            Navigate.browserRefresh();
        }
        Thread.sleep(1000);
    }

    @And("^I close the browser on (desktop|mobile|tablet)")
    public void closingTheBrowser(String device) throws Throwable {
        if(deviceRunStatus) {
            Actions.closeBrowser();
            deviceRunStatus = false;
            log.info("Browser closed on " + device + "!");
        }
        Thread.sleep(1500);
    }

    @And("^I close & reopen the browser on (desktop|mobile|tablet)")
    public void closingAndReopeningTheBrowser(String device) throws Throwable {
        if(deviceRunStatus) {
            Actions.closeNopenBrowser(url);
        }
        Thread.sleep(1000);
    }

    @And("^I delete all cookies on (desktop|mobile|tablet)")
    public void deletingAllCookies(String device) throws Throwable {
        if(deviceRunStatus) {
            Cookies.deleteAllCookies();
            Navigate.browserRefresh();
        }
        Thread.sleep(1000);
    }

    @And("^I select (random|maximum|minimum|median|default) quantity on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void selectingQtyOnPDP(String qty, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            int max = Integer.parseInt(PDPxAPISteps.getFccXapiValidatedAttribute(PID, "maxQuantity"));
            int min = 1;
            int count = min;
            if(pg.equals("member")) {
                Wait.secondsUntilElementPresent("pdp.qtyIncrementBtn", 5);
                if(!device.equalsIgnoreCase("desktop"))
                    Actions.scroll("down", null);

                if(qty.equalsIgnoreCase("random")) {
                    int rn = rand.nextInt(max - min) +1;
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                    Assert.assertTrue(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")));
                    while(count < rn) {
                        Clicks.click(Elements.element("pdp.qtyIncrementBtn"));
                        count++;
                        if(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")))
                            break;
                    }
                    Thread.sleep(1000);
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.qtyValue")).getText()).equals(String.valueOf(count)));
                    log.info("Random Value: " + rn + "  Selected Quantity Value: " +
                            Elements.findElement(Elements.element("pdp.qtyValue")).getText());
                }
                else if(qty.equalsIgnoreCase("maximum")) {
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                    Assert.assertTrue(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")));
                    while(count < max) {
                        Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                        Clicks.click(Elements.element("pdp.qtyIncrementBtn"));
                        count++;
                    }
                    Assert.assertTrue(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")));
                    Assert.assertTrue(String.valueOf(count).equals(String.valueOf(max)));
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.qtyValue")).getText()).equals(String.valueOf(count)));
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.qtyValue")).getText()).equals(String.valueOf(max)));
                    log.info("Maximum Value: " + max + ", Number of Clicks: " + count + ",  Selected Quantity Value: " +
                            Elements.findElement(Elements.element("pdp.qtyValue")).getText());
                }
                else if(qty.equalsIgnoreCase("median")) {
                    max /= 2;
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                    Assert.assertTrue(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")));
                    while(count < max && !(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")))) {
                        Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                        Clicks.click(Elements.element("pdp.qtyIncrementBtn"));
                        count++;
                    }
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")));
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.qtyValue")).getText())
                            .equals(String.valueOf(count)));
                    log.info("Median Value: " + count + "  Selected Quantity Value: " +
                            Elements.findElement(Elements.element("pdp.qtyValue")).getText());
                }
                else if(qty.equalsIgnoreCase("minimum")) {
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                    Assert.assertTrue(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")));
                    while(count < max) {
                        Clicks.click(Elements.element("pdp.qtyIncrementBtn"));
                        count++;
                        if(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")))
                            break;
                    }
                    Assert.assertTrue(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")));
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.qtyValue")).getText()).equals(String.valueOf(count)));
                    Thread.sleep(1000);
                    count = max;
                    while(count > min) {
                        Clicks.click(Elements.element("pdp.qtyDecrementBtn"));
                        count--;
                        if(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")))
                            break;
                    }
                    Assert.assertFalse(Elements.elementPresent(By.xpath(".//*[@data-auto='increment-button'][@disabled='']")));
                    Assert.assertTrue(Elements.elementPresent(By.xpath(".//*[@data-auto='decrement-button'][@disabled='']")));
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.qtyValue")).getText()).equals(String.valueOf(min)));
                }
                else
                    log.info("Default Quantity Selected::  " + Elements.findElement(Elements.element("pdp.qtyValue")).getText());

                selectedQty = count;
            }
        }
        Thread.sleep(1000);
    }

    @And("^I call xAPI service for the product ID")
    public void callingXAPI() throws Throwable {
        if(deviceRunStatus) {
            PDPxAPISteps.I_call_pdp_xapi_service_for_product(PID);
        }
        Thread.sleep(1000);
    }

    @And("^I verify \"([^\"]*)\" with xAPI service")
    public void verifyingWithXAPI(String arg) throws Throwable {
        if(deviceRunStatus) {
            if(arg.equalsIgnoreCase("Product Not Found")) {
                if(PDPxAPISteps.I_verify_pdp_xapi_service_for_product_not_found())
                    log.info("Product Not Found Message Verified with xAPI");
                else
                    Assert.fail();
            }
        }
        Thread.sleep(1000);
    }

    @And("^I click the (A2B|A2L|A2R|Checkout|ContinueShopping|ContinueShoppingBack|Product Details|Price Details|Special Offers|Shipping & Returns) " +
            "button on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void clickingButtons(String btn, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Thread.sleep(1000);
            Wait.forPageReady();
            switch(btn) {
                case "A2B": {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.addToBagBtn")));
                    Clicks.click("pdp.addToBagBtn");
                    log.info(userProfileName + " Clicked A2B Button!");
                    break;
                }
                case "A2L": {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.addToListBtn")));
                    Clicks.click("pdp.addToListBtn");
                    log.info(userProfileName + " Clicked A2L Button!");
                    break;
                }
                case "A2R": {
                    if(device.equalsIgnoreCase("mobile"))
                        ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.addToRegistryLink")));
                    Clicks.click("pdp.addToRegistryLink");
                    log.info(userProfileName + " Clicked A2R Link!");
                    break;
                }
                case "Checkout": {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.checkoutBtn")));
                    Clicks.click("add2bag.checkoutBtn");
                    log.info(userProfileName + " Clicked Checkout Button on A2B Overlay!");
                    break;
                }
                case "ContinueShopping": {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.continueShoppingBtn")));
                    Clicks.click("add2bag.continueShoppingBtn");
                    log.info(userProfileName + " Clicked ContinueShopping Button!");
                    break;
                }
                case "ContinueShoppingBack": {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.continueShoppingBtn")));
                    Clicks.click("pdp.continueShoppingBtn");
                    log.info(userProfileName + " Clicked ContinueShoppingBack Button!");
                    Thread.sleep(5000);
                    Wait.secondsUntilElementPresent(("pdp.productTitle"),15);
                    Assert.assertFalse(webID.equals(PID));
                    Assert.assertTrue(PID.equals("1"));
                    log.info("WEB ID:: " + webID + " ..... PID:: " + PID);
                    break;
                }
                case "Product Details": {
                    ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
                    Clicks.click("pdp.productDetailsBtn");
                    Thread.sleep(1000);
                    Clicks.click("pdp.productDetailsBtn");
                    Thread.sleep(1000);
                    Clicks.click("pdp.productDetailsBtn");
                    break;
                }
                case "Price Details": {
                    ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    Clicks.click("pdp.priceDetailsBtn");
                    Thread.sleep(1000);
                    Clicks.click("pdp.priceDetailsBtn");
                    Thread.sleep(1000);
                    Clicks.click("pdp.priceDetailsBtn");
                    break;
                }
                case "Special Offers": {
                    if(!device.equalsIgnoreCase("mobile")) {
                        ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
                        Clicks.click("pdp.specialOffersBtn");
                        Thread.sleep(1000);
                        Clicks.click("pdp.specialOffersBtn");
                        Thread.sleep(1000);
                    }
                    break;
                }
                case "Shipping & Returns": {
                    ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
                    Thread.sleep(1000);
                    Clicks.click("pdp.shippingReturnsBtn");
                    Thread.sleep(1000);
                    Clicks.click("pdp.shippingReturnsBtn");
                    Thread.sleep(1000);
                    Clicks.click("pdp.shippingReturnsBtn");
                    break;
                }
            }
        }
        Thread.sleep(1000);
    }

    @And("^I click \"([^\"]*)\" link on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void clickingLinksOnPDP(String link, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            if(pg.equalsIgnoreCase("member")) {
                if(link.contains("Savings based on")) {
                    List<WebElement> elList = Elements.findElements(Elements.element("pdp.productDescriptionBullets"));
                    for(WebElement el: elList) {
                        if(el.getText().equalsIgnoreCase(link)) {
                            Clicks.click(el);
                        }
                    }
                }
                else
                    log.warning(link + " link not defined!");
            }
        }
        log.info(userProfileName + " clicked the link::  " + link);
        Thread.sleep(1000);
    }

    @And("^I click the (guest|registered) List link on AddToList popup on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void clickingListLinks(String link, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            if(pg.equalsIgnoreCase("member")) {
                if(link.equalsIgnoreCase("guest")) {
                    link = "Guest List";
                    Wait.secondsUntilElementPresent("pdp.A2LSuccessMsg", 10);
                    Thread.sleep(1000);
                    Clicks.click(Elements.element("pdp.guestListLink"));
                }
                else {
                    Wait.secondsUntilElementPresent("pdp.A2LSuccessMsg", 10);
                    Thread.sleep(1000);
                    Clicks.click(Elements.element("pdp.guestListLink"));
                    link = link.replace("registered",userProfileName+"'s List");
                }
            }
        }
        log.info(userProfileName + " clicked the link::  " + link);
        Thread.sleep(1000);
    }

    @And("^I randomly select an (available|unavailable) color on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void selectColorOnPDP(String arg, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Thread.sleep(1000);
            rand = new Random();
            List<String> xApiColorValues = null;
            List<WebElement> sizes;
            List<WebElement> colors;
            if(pg.equals("member")) {
                if(xApiFccValidationEnabled) {
                    xApiColorValues = PDPxAPISteps.getFccXapiValidatedAttributes(PID, "colorValues");
                    if((xApiColorValues.size() == 0) ) {
                        log.warning("xApiFcc Failed! Size of xApiColorValues::  " + xApiColorValues.size());
                        selectedColor = null;
                    }
                    else if((xApiColorValues.size() == 1) ) {
                        log.info("xApiFcc verified as product has only " + xApiColorValues.size() + " color that will not show on PDP!");
                        selectedColor = null;
                    }
                }
                Wait.secondsUntilElementPresent("pdp.colorSection", 10);
                if(Elements.elementPresent(Elements.element("pdp.viewMoreColors"))) {
                    Clicks.click(Elements.findElement(Elements.element("pdp.viewMoreColors")));
                    log.info("View More Colors Displayed & Clicked!");
                }
                if(arg.equalsIgnoreCase("available")) {
                    colors = Elements.findElements(Elements.element("pdp.availableColors"));
                    log.info("Number of available colors: " + colors.size());

                    if(xApiFccValidationEnabled) {
                        String[] pdpColorNames = new String[xApiColorValues.size()];
                        for(int i=0; i < xApiColorValues.size(); i++) {
//                                Clicks.click(colors.get(i));
//                                Wait.secondsUntilElementPresent(Elements.element("pdp.selectedColor"), 1);
//                                pdpColorNames[i] = Elements.findElement(Elements.element("pdp.selectedColor")).getText();
                            pdpColorNames[i] = colors.get(i).getAttribute("aria-label");
                        }
                        Arrays.sort(pdpColorNames);
                        Assert.assertTrue(pdpColorNames.length == xApiColorValues.size());
                        log.info("Verifying Colors in Fcc/xApi Vs PDP!");
                        for(int i=0; i < pdpColorNames.length; i++) {
                            Assert.assertTrue(xApiColorValues.get(i).equals(pdpColorNames[i]));
                            log.info("Fcc/xApi == PDP:: " + xApiColorValues.get(i) + " == " + pdpColorNames[i]);
                        }
                        logger.info("\n******* PDP Vs xApi verifications for \"Colors\" completed!" +
                                "\n===========================================================================\n");
                    }
                }
                else {
                    colors = Elements.findElements(Elements.element("pdp.unavailableColors"));
                    log.info("Number of unavailable colors: " + colors.size());
                    if(colors.size() == 0) {
                        int sizeOpt;
                        for(int i=0; i <= 3; i++) {
                            Thread.sleep(500);
                            sizes = Elements.findElements(Elements.element("pdp.unavailableSizes"));
                            sizeOpt = rand.nextInt(sizes.size());
                            Clicks.click(sizes.get(sizeOpt));
                            if(Elements.elementPresent(Elements.element("pdp.unavailableColors"))) {
                                colors = Elements.findElements(Elements.element("pdp.unavailableColors"));
                                log.info("Selected different size w/ number of unavailable colors: " + colors.size());
                                break;
                            }
                        }
                    }
                }
                String colorSelected;
                for(WebElement element: colors) {
                    colorSelected = element.getAttribute("class");
                    if(colorSelected.equalsIgnoreCase("color-swatch selected")) {
                        defaultColor = element.getAttribute("aria-label");
                        log.info("Default Color:: \"" + defaultColor + "\"");
                    }
                }
                int colorOpt = rand.nextInt(colors.size());
                Clicks.click(colors.get(colorOpt));

                Thread.sleep(3000);
                List<WebElement> colours = Elements.findElements(Elements.element("pdp.availableColors"));
                String colourSelected;
                for(WebElement element: colours) {
                    colourSelected = element.getAttribute("class");
                    if(colourSelected.equalsIgnoreCase("color-swatch selected")) {
                        selectedColor = element.getAttribute("aria-label");
                        log.info("Randomly Selected Color:: \"" + selectedColor + "\"");
                    }
                }
                if(selectedColor.equalsIgnoreCase(defaultColor))
                    log.info("Randomly Selected Default Color!");
            }
        }
        Thread.sleep(1000);
    }

    @And("^I randomly select an (available|unavailable) size on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void selectingSizeOnPDP(String arg, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Thread.sleep(1000);
            rand = new Random();
            List<String> xApiSizeValues = null;
            List<WebElement> sizes;
            List<WebElement> colors;
            if(pg.equals("member")) {
                int sizeOpt;
                if(xApiFccValidationEnabled) {
                    xApiSizeValues = PDPxAPISteps.getFccXapiValidatedAttributes(PID, "sizeValues");
                }
                if(Elements.elementPresent(Elements.element("pdp.viewMoreSizes"))) {
                    Clicks.click(Elements.findElement(Elements.element("pdp.viewMoreSizes")));
                    Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                    log.info("View More Sizes Displayed & Clicked!");
                }
                if(arg.equalsIgnoreCase("available")) {
                    sizes = Elements.findElements(Elements.element("pdp.allSizes"));
                    log.info("Number of all sizes displayed on PDP: " + sizes.size());

                    if(xApiFccValidationEnabled) {
                        Assert.assertTrue(sizes.size() == xApiSizeValues.size());
                        for(int i=0; i < xApiSizeValues.size(); i++) {
                            Assert.assertTrue(xApiSizeValues.get(i).equals(sizes.get(i).getText()));
                        }
                        logger.info("\n******* PDP Vs xApi verifications for \"Sizes\" completed!" +
                                "\n===========================================================================\n");
                    }
                    sizes = Elements.findElements(Elements.element("pdp.availableSizes"));
                    log.info("Number of available sizes displayed on PDP: " + sizes.size());
                }
                else {
                    sizes = Elements.findElements(Elements.element("pdp.unavailableSizes"));
                    log.info("Number of unavailable sizes: " + sizes.size());
                    if(sizes.size() == 0) {
                        int colorOpt;
                        for(int i=0; i <= 3; i++) {
                            Thread.sleep(500);
                            colors = Elements.findElements(Elements.element("pdp.availableColors"));
                            colorOpt = rand.nextInt(colors.size());
                            Clicks.click(colors.get(colorOpt));
                            if(Elements.elementPresent(Elements.element("pdp.unavailableSizes"))) {
                                sizes = Elements.findElements(Elements.element("pdp.unavailableSizes"));
                                log.info("Selected different color w/ number of unavailable sizes: " + sizes.size());
                                break;
                            }
                        }
                    }
                }
                sizeOpt = rand.nextInt(sizes.size());
                selectedSize = sizes.get(sizeOpt).getText();
                log.info("Randomly Selected Size:: " + selectedSize);
                Clicks.click(sizes.get(sizeOpt));
            }
        }
        Thread.sleep(1000);
    }

    @And("^I select a random size on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void selectRandomSizeOnPDP(String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Thread.sleep(1000);
            if(pg.equals("member")) {
                if(Elements.elementPresent(Elements.element("pdp.sizeSection"))) {
                    List<WebElement> sizes = Elements.findElements(Elements.element("pdp.availableSizes"));
                    if(sizes.size() > 1) {
                        rand = new Random();
                        int sizeOpt;
                        if(Elements.elementPresent(Elements.element("pdp.viewMoreSizes"))) {
                            Clicks.click(Elements.findElement(Elements.element("pdp.viewMoreSizes")));
                            Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
                            log.info("View More Sizes Displayed & Clicked!");
                        }
                        sizeOpt = rand.nextInt(sizes.size());
                        selectedSize = sizes.get(sizeOpt).getText();
                        log.info("Randomly Selected Size:: " + selectedSize);
                        Clicks.click(sizes.get(sizeOpt));
                    }
                }
            }
        }
        Thread.sleep(1000);
    }

    @And("^I randomly select an available type on member PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void selectTypeOnPDP(String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Thread.sleep(1000);
            rand = new Random();
            Wait.secondsUntilElementPresent("pdp.typeSection", 10);
            List<WebElement> Types = Elements.findElements(Elements.element("pdp.availableTypes"));
            log.info("Number of Types displayed: " + Types.size());
            int typeOpt = rand.nextInt(Types.size());
            Types.get(typeOpt).click();
            Thread.sleep(1000);
        }
    }

    @And("^I hover over \"([^\"]*)\" FOB tabs on PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void hoveringOverFOBsOnPDP(String tab, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            rand = new Random();
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.FOB_Tabs", 20);

            List<WebElement> FOBs = Elements.findElements(Elements.element("pdp.FOBs"));
            log.info("Number of FOBs displayed:: " + FOBs.size());
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
                        log.info("Hovered FOB tab \""+linkElement.getText()+ "\" on " + device + " PDP " + mode + " mode!");
                        break;
                    }
                }
            }
        }
        Thread.sleep(1000);
    }

    @And("^I select \"([^\"]*)\" link from FOB on PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void selectingLinksFromFOBsOnPDP(String link, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Wait.secondsUntilElementPresent("pdp.FOB_Links", 10);
            List<WebElement> FOBLinks = Elements.findElements(Elements.element("pdp.FOB_Links"));
            log.info("Number of FOB links displayed: " + FOBLinks.size());
            for(WebElement linkElement: FOBLinks){
                if((linkElement.getText()).equalsIgnoreCase(link)) {
                    log.info("The \""+linkElement.getText()+ "\" link under FOB is clicked on "+ device + " PDP " + mode + " mode!");
                    Thread.sleep(2000);
                    Clicks.click(linkElement);
                    break;
                }
            }
        }
        Thread.sleep(1000);
    }

    @And("^I capture the (webId) on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void captureElementData(String arg, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Wait.forPageReady();
            List<WebElement> elList = Elements.findElements(Elements.element("pdp.productDescriptionBullets"));
            String[] data;
            Wait.secondsUntilElementPresent("pdp.productTitle", 10);
            if(pg.equalsIgnoreCase("member")) {
                if(arg.equalsIgnoreCase("webId")) {
                    for(WebElement el: elList) {
                        if(el.getText().contains("Web ID:")) {
                            data = el.getText().split("Web ID:");
                            webID = data[1].trim();
                        }
                    }
                }
            }
        }
        Thread.sleep(1000);
    }

    @And("^I click the (Product Image) on list page in (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void clickingElementsOnListPage(String arg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Wait.forPageReady();
            if(arg.equalsIgnoreCase("Product Image")) {
//                Wait.forPageReady();
//                Wait.secondsUntilElementPresent("addToList.productImg", 10);
//                Clicks.click(Elements.element("addToList.productImg"));
                log.warning("DEFECT:: Link to WishList page not working!");
            }
            else
                log.warning(arg+ " page not defined!");
        }
        Thread.sleep(1000);
    }

    @And("^I click the (Checkout button|Remove button|Product Image|QuickBag Icon) on QuickBag overlay on (desktop|mobile|tablet)$")
    public void clickingElementsOnQuickBagOverlay(String arg, String device) throws Throwable {
        if(deviceRunStatus) {
            if (arg.equals("Checkout button")) {
                WebElement hoverQB = Elements.findElement("quickBag.qbLink");
                Wait.secondsUntilElementPresent("quickBag.qbLink", 10);
                Thread.sleep(3000);
                Clicks.hoverForSelection(hoverQB);
                Assert.assertTrue(Elements.elementPresent("quickBag.qbCheckoutBtn"));
                Thread.sleep(3000);
                Clicks.click("quickBag.qbCheckoutBtn");
            }
            else if (arg.equals("QuickBag Icon")) {
                Assert.assertTrue(Elements.elementPresent(Elements.element("quickBag.qbLink")));
                Clicks.click("quickBag.qbLink");
                log.info("Checkout Button Clicked on QuickBag overlay!");
            }
            else if (arg.equals("Remove button")) {
                WebElement hoverQB = Elements.findElement("quickBag.qbLink");
                Wait.secondsUntilElementPresent("quickBag.qbLink", 10);
                Clicks.hoverForSelection(hoverQB);
                Thread.sleep(3000);
                Clicks.hoverForSelection("quickBag.removeBtn");
                Thread.sleep(3000);
                Clicks.click("quickBag.removeBtn");
                selectedQty = 0;
            }
            else if (arg.equals("Product Image")) {
                WebElement hoverQB = Elements.findElement("quickBag.qbLink");
                Wait.secondsUntilElementPresent("quickBag.qbLink", 10);
                Clicks.hoverForSelection(hoverQB);
                Thread.sleep(3000);
                Clicks.hoverForSelection("quickBag.productImg");
                Thread.sleep(1000);
                Clicks.click("quickBag.productImg");
//                List<WebElement> productImages = Elements.findElements(Elements.element("quickBag.productImgs"));
//                for(WebElement link: productImages){
//                    if(!(link.getAttribute("href").contains("GIFTID"))) {
//                        log.info("Clicking on the " + link.getAttribute("href"));
//                        Clicks.click(link);
//                    }
//                }
            }
        }
        log.info(userProfileName + " clicked the \"" + arg + "\" on QuickBag overlay!");
        Thread.sleep(1000);
    }

    @And("^I click the (Remove button|Product Image) on ShoppingBag page (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void clickingElementsOnShoppingBagPage(String arg, String mode, String device) throws Throwable {
        if (deviceRunStatus) {
            if(!device.equalsIgnoreCase("desktop"))
                Actions.scroll("down", null);

            if(arg.equals("Product Image")) {
                Wait.secondsUntilElementPresent("shoppingBag.productImg", 10);
                Assert.assertTrue(Elements.elementPresent("shoppingBag.productImg"));
                Clicks.click("shoppingBag.productImg");
                log.info(userProfileName + " clicked the \"" + arg + "\" on ShoppingBag page!");
            }
            else if(arg.equals("Remove button")) {
                Wait.secondsUntilElementPresent("shoppingBag.removeBtn", 10);
                Assert.assertTrue(Elements.elementPresent("shoppingBag.removeBtn"));
                Clicks.click("shoppingBag.removeBtn");
                log.info(userProfileName + " clicked the \"" + arg + "\" on ShoppingBag page!");
                Thread.sleep(3000);
                Assert.assertTrue(Elements.elementPresent("shoppingBag.itemRemovedMsg"));
                Assert.assertTrue(Elements.findElement("shoppingBag.itemRemovedMsg").getText().equals("Removed from bag"));
                log.info("Items Removed Message::  " + Elements.findElement("shoppingBag.itemRemovedMsg").getText());
            }
        }
        Thread.sleep(1000);
    }

    @And("^I select the first available size on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void selectingFirstAvailableSize(String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Thread.sleep(2000);
            Clicks.click(Elements.element("pdp.memberFirstAvailSize"));
            log.info( "Selected first available size:: "+Elements.findElement("pdp.memberFirstAvailSize").getText()
                    +" on "+pg+" PDP "+mode+" mode on "+device);
        }
    }

    @Then("^I verify the basic elements on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingBasicElementsOfPDP(String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
//            List<String> dbAttrValues = DataBase.getAttributeValues(PID);
//            String dbAttrValue = DataBase.getAttributeValue(PID, "maxQuantity");
//            String attrValue = PDPxAPISteps.getFccXapiValidatedAttribute(PID, "BrandName");

            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productTitle")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productBrand")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productName")));
            if(xApiFccValidationEnabled) {
                List<String> attrValues = PDPxAPISteps.getFccXapiValidatedAttributes(PID, "Basic Elements");

                if(attrValues.get(0) != null) {
                    log.info( "BrandName Validated:: xApi: " + attrValues.get(0) + " == PDP/UI: "
                            + Elements.findElement(Elements.element("pdp.productBrand")).getText()+"\n");
                    Assert.assertTrue(Elements.findElement(Elements.element("pdp.productBrand")).getText().equals(attrValues.get(0)));
                }
                log.info( "ProductName Validated:: xApi: " + attrValues.get(1) + " == PDP/UI: "
                        + Elements.findElement(Elements.element("pdp.productName")).getText()+"\n");
                Assert.assertTrue(Elements.findElement(Elements.element("pdp.productName")).getText().trim().equals(attrValues.get(1).trim()));
                logger.info("\n******* PDP Vs xApi verifications for \"Basic Elements\" completed!" +
                        "\n===========================================================================\n");
            }
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productMainImg")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productDescription")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.quantity")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.qtyLabel")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.qtyValue")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.qtyIncrementBtn")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.qtyDecrementBtn")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.addToBagBtn")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.addToListBtn")));
            log.info("Verified the basic elements on " + pg + " PDP " + mode + " mode on " + device + "!" +
                    "\n===========================================================================\n");
        }
        Thread.sleep(1000);
    }

    @Then("^I verify product prices on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingPricesOnPDP(String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            String[] origPrice = Elements.findElement(Elements.element("pdp.priceDetails")).getText().split("\\$");
            originalPrice = origPrice[1];
            if(originalPrice.contains(","))
                originalPrice = originalPrice.replace(",","");
            log.info("Main Price on PDP::  "+originalPrice);

            if(Elements.elementPresent(Elements.element("pdp.salePrice"))) {
                String[] salPrice = Elements.findElement(Elements.element("pdp.salePrice")).getText().split("\\$");
                salePrice = salPrice[1];
                if (salePrice.contains(","))
                    salePrice = salePrice.replace(",", "");
                log.info("Sale Price on PDP::  "+salePrice);
            }
            if(xApiFccValidationEnabled) {
                List<String> attrValues = PDPxAPISteps.getFccXapiValidatedAttributes(PID, "prices");
                log.info("PDP receives from xApi & displays " + attrValues.size() + " tier price for the product::  " + PID);

                Assert.assertTrue(String.format("%.2f",Float.valueOf(originalPrice.trim())).equals(String.format("%.2f",Float.valueOf((attrValues.get(0).trim())))));
                log.info("Original Price Fcc/xApi == PDP:: " + String.format("%.2f",Float.valueOf((attrValues.get(0).trim()))) + " == " + String.format("%.2f",Float.valueOf(originalPrice.trim())));

                if(attrValues.size() > 1) {
                    Assert.assertTrue(String.format("%.2f",Float.valueOf(attrValues.get(1).trim())).equals(String.format("%.2f",Float.valueOf(salePrice.trim()))));
                    log.info("Sale Price Fcc/xApi == PDP:: " + String.format("%.2f",Float.valueOf(attrValues.get(1).trim())) + " == " + String.format("%.2f",Float.valueOf(salePrice.trim())));
                    log.info("Verified " + attrValues.size() + " tiered price displayed on PDP:: OriginalPrice = " + originalPrice + "  --->>  SalePrice = " + salePrice+"\nVerification completed for prices on PDP Vs xApiFcc!\n\n");
                }
                logger.info("\n******* PDP Vs xApi verifications for \"Price\" completed!" +
                        "\n===========================================================================\n");
            }
            else
                log.info("PDP verifications for \"Price\" completed!" +
                        "\n===========================================================================\n");
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the zoomer & altimages on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingZoomerAndAltImages( String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            List<String> pdpAltImgs = new ArrayList<>();
            List<WebElement> altImgs;
            String[] mainImgSrc;
            String[] actualWidth;
            String[] actualHeight;
            int expectedWidth;
            int expectedHeight;
            int size;
            String altImgSources;
            String[] altImgSrc1;
            String[] altImgSrc2;

            Thread.sleep(1000);
            Assert.assertTrue(Elements.elementPresent("pdp.productMainImg"));
            if(device.equalsIgnoreCase("desktop")) {
                expectedWidth = 500;
                expectedHeight = 613;
                mainImgSrc = Elements.findElement(Elements.element("pdp.productMainImg")).getAttribute("src").split("wid=");
                actualWidth = mainImgSrc[1].split("&");
                actualHeight = actualWidth[1].split("hei=");
                log.info("MainImage Size on " + device + ":: " + " ExpectedWidth=" + expectedWidth + " ExpectedHeight=" + expectedHeight
                        + " ... ActualWidth=" + actualWidth[0] + " ActualHeight=" + actualHeight[1]);
                Assert.assertTrue(expectedWidth == parseInt(actualWidth[0]));
                Assert.assertTrue(expectedHeight == parseInt(actualHeight[1]));
            }

            if(device.equalsIgnoreCase("mobile")) {
                altImgs = Elements.findElements(Elements.element("pdp.mobileAltImages"));
                altImgSources = altImgs.get(1).findElement(By.tagName("img")).getAttribute("src");
            }
            else {
                altImgs = Elements.findElements(Elements.element("pdp.productAltImages"));
                altImgSources = altImgs.get(1).getAttribute("src");
                Assert.assertTrue(Elements.elementPresent("pdp.productAltImages"));
            }
            if(xApiFccValidationEnabled) {
                List<String> attrValues = PDPxAPISteps.getFccXapiValidatedAttributes(PID, "altImages");
                altImgSrc1 = altImgSources.split("optimized/");
                for(int i=0; i < attrValues.size(); i++) {
                    altImgSrc2 = altImgSrc1[i+1].split("_");
                    pdpAltImgs.add(altImgSrc2[0]);
                }
                Assert.assertTrue(attrValues.size() == altImgs.size());
                Assert.assertTrue(attrValues.size() == pdpAltImgs.size());
                log.info("Number of AltImages verified on xApiFCC Vs AltImages displayed on PDP:: " + attrValues.size() + " == "+ pdpAltImgs.size());
                Collections.sort(pdpAltImgs);
                for(int i=0; i < attrValues.size(); i++) {
                    Assert.assertTrue(attrValues.get(i).equals(pdpAltImgs.get(i)));
                    log.info("xApiFcc ... PDP::  " + attrValues.get(i) + " ...  " + pdpAltImgs.get(i));
                }
                logger.info("\n******* PDP Vs xApi verifications for \"AltImages\" completed!" +
                        "\n===========================================================================\n");
            }
            if(altImgs.size() > 4) {
                if(device.equals("desktop")) {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.AltImagesDownArrow")));
                    Clicks.click(Elements.element("pdp.AltImagesDownArrow"));
                    Thread.sleep(500);
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.AltImagesUpArrow")));
                    Clicks.click(Elements.element("pdp.AltImagesUpArrow"));
                }
                size = altImgs.size()/2;
            }
            else
                size = altImgs.size();

            for(int i=0; i < size; i++) {
                Thread.sleep(1000);
                altImgs.get(i).click();
                Thread.sleep(3000);
                log.info("IndexId:: AltImage Clicked & AltImage Selected:: " + i + " == " + Integer.parseInt(Elements.findElement("pdp.selectedAltImg").getAttribute("data-index")));
                Assert.assertTrue(i == Integer.parseInt(Elements.findElement("pdp.selectedAltImg").getAttribute("data-index")));
                if(device.equalsIgnoreCase("desktop")) {
                    Clicks.hoverForSelection(Elements.element("pdp.mainImage"));
                    log.info("Main image hovered/zoomed on " + device + " " + pg + " PDP!");
                }
                else
                    log.warning("Zoomer should be manually verified on " + device);
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the error message \"([^\"]*)\" on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingErrorMessagesOnPDP(String msg, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Thread.sleep(1000);
            if(msg.equalsIgnoreCase("This color/size combination is not available.")) {
                Assert.assertTrue(Elements.elementPresent("pdp.errorMsg"));
                Assert.assertTrue(((Elements.findElement(Elements.element("pdp.errorMsg")).getText())).equals(msg));
            }
            else if(msg.equalsIgnoreCase( "Please select a size.")) {
                Assert.assertTrue(Elements.elementPresent("pdp.errorMsg"));
                Assert.assertTrue(((Elements.findElement(Elements.element("pdp.errorMsg")).getText())).equals(msg));
            }
            else if(msg.equalsIgnoreCase( "You've exceeded the quantity limit for this item.")) {
                Assert.assertTrue(Elements.elementPresent("pdp.errorMsg"));
                Assert.assertTrue(((Elements.findElement(Elements.element("pdp.errorMsg")).getText())).equals(msg));
            }
            else if(msg.equalsIgnoreCase( "You've reached the limit for this item. Please select another size/color.")) {
                Assert.assertTrue(Elements.elementPresent("pdp.errorMsg"));
                Assert.assertTrue(((Elements.findElement(Elements.element("pdp.errorMsg")).getText())).equals(msg));
            }
        }
        Clicks.click(Elements.element("pdp.errorMsgClose"));
        Thread.sleep(1000);
    }

    @Then("^I verify the \"([^\"]*)\" message on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingAvailabilityMessagesOnPDP(String msg, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            if(xApiFccValidationEnabled) {
                String available = PDPxAPISteps.getFccXapiValidatedAttribute(PID, "availability");
                if(available.equals("true"))
                    log.info("Item's Availability::  " + available);
                else
                    log.warning("Item is NOT available as xApi/Fcc returns availability::  " + available);
            }
            switch(msg) {
                case "Available.Usually ships within x business days.": {
                    Assert.assertTrue(Elements.findElement(Elements.element("pdp.shipItRadioBtn")).isEnabled());
                    Assert.assertTrue(Elements.findElement(Elements.element("pdp.shipItRadioBtn")).isSelected());
                    Assert.assertTrue(Elements.elementPresent("pdp.availabilitySection"));
                    Assert.assertTrue(Elements.elementPresent("pdp.shipItSection"));
                    Assert.assertTrue(Elements.elementPresent("pdp.shipItLabel"));
                    Assert.assertTrue(Elements.findElement(Elements.element("pdp.shipItLabel")).getText().equals("Ship It"));
                    Assert.assertTrue(Elements.elementPresent("pdp.availabilityMsg"));
                    Assert.assertTrue(Elements.elementPresent("pdp.shippingMsg"));
                    String actualMsg = Elements.findElement(Elements.element("pdp.availabilityMsg")).getText()
                            + Elements.findElement(Elements.element("pdp.shippingMsg")).getText();
                    log.info("Actual Message::  "+ actualMsg);
                    String expectedMsg = actualMsg.replaceAll("[0-9]", "x");
                    log.info("Expected Message::  "+ expectedMsg);
                    Assert.assertTrue(expectedMsg.equals(msg));

                    Clicks.click("pdp.shipItRadioBtn");
                    Thread.sleep(1000);
                    Assert.assertTrue(Elements.findElement(Elements.element("pdp.shipItRadioBtn")).isSelected());
                    break;
                }
                case "Select a color and size above in order to view availability.": {
                    Assert.assertTrue(Elements.elementPresent("pdp.availabilitySection"));
                    Assert.assertTrue(Elements.elementPresent("pdp.shipItSection"));
                    Assert.assertTrue(Elements.elementPresent("pdp.availability"));
                    log.info("Expected Msg:: Availability ... Actual Msg:: "+ Elements.findElement(Elements.element("pdp.availability")).getText());
                    Assert.assertTrue(Elements.findElement(Elements.element("pdp.availability")).getText().equals("Availability"));
                    Assert.assertTrue(Elements.findElement(Elements.element("pdp.shipItSection")).getText().equals("Availability\n"+msg));
                    log.info("Expected Msg::  " + ("Availability\n"+msg));
                    log.info(" ... Actual Msg::  " + Elements.findElement(Elements.element("pdp.shipItSection")).getText());
                    break;
                }
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify AddToList popup for the (guest|registered) user on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingAddToListFunctionalityOnPDP(String arg, String pg, String mode, String device) throws Throwable {
        if (deviceRunStatus) {
            Assert.assertTrue(Elements.elementPresent("pdp.A2LSuccessMsg"));
            Assert.assertTrue(Elements.elementPresent("pdp.guestListLink"));

            if(arg.equals("guest"))
                arg = "Added to Guest List.";
            else
                arg = "Added to "+userProfileName+"'s List.";

            Assert.assertTrue(Elements.findElement(Elements.element("pdp.A2LSuccessMsg")).getText().equals(arg));
            log.info("A2L Success Message::  " + Elements.findElement(Elements.element("pdp.A2LSuccessMsg")).getText());
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the \"([^\"]*)\" message under Shipping&Returns on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingMessagesUnderShippingReturns(String msg, String pg, String mode, String device) throws Throwable {
        if (deviceRunStatus) {
            switch (msg) {
                case "This item qualifies for Free Shipping with minimum purchase!": {
                    log.info("Free Shipping Msg::  \""+Elements.findElement(Elements.element("pdp.freeShippingMsg")).getText()+"\"");
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.freeShippingMsg")).getText()).equalsIgnoreCase(msg+" Exclusions & Details"));
                    break;
                }
                case "Shipping Surcharge Fee": {
                    msg = "This item is assigned a shipping surcharge fee of $4.00 based on size/weight and/or special shipping requirements";
                    log.info("Shipping Surcharge Fee:  \""+Elements.findElement(Elements.element("pdp.shippingReturnsBullets")).getText()+"\"");
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.shippingReturnsBullets")).getText()).equalsIgnoreCase(msg));
                    break;
                }
            }
        }
        log.info("Verified the message \"" + msg + "\" under Shipping & Returns section on " + pg + " PDP " + mode + " mode on " + device);
        Thread.sleep(1000);
    }

    @Then("^I verify BOPS functionality on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingBOPSOnPDP(String pg, String mode, String device) throws Throwable {
        if (deviceRunStatus) {
            log.warning("Implementation Pending:: BOPS functionality still pending!");
//            List<WebElement> radioBtns = Elements.findElements(Elements.element("pdp.NotAvailableBtnLabel"));
//            for(WebElement el: radioBtns)
//                Assert.assertTrue(el.isEnabled());
//
//            Assert.assertTrue(Elements.elementPresent("pdp.availabilitySection"));
//            Assert.assertTrue(Elements.findElement("pdp.pickUpInStoreLabel").getText().equals("Pick Up In Store"));
//            Assert.assertTrue(Elements.elementPresent("pdp.pickUpInStoreRadioBtn"));
//            Clicks.click("pdp.pickUpInStoreRadioBtn");
//            Thread.sleep(1000);
//            Assert.assertTrue(Elements.elementPresent("pdp.storeAvailabilityLink"));
//            Assert.assertTrue(Elements.findElement(Elements.element("pdp.pickUpInStoreRadioBtn")).isSelected());
        }
    }

    @Then("^I verify elements under Product Details section on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingElementsUnderProductDetails(String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productDetailsSection")));
//            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productDetailsHeader")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productDetailsBtn")));
            Clicks.click(Elements.element("pdp.productDetailsBtn"));
            Thread.sleep(1000);
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productDescription")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productDescriptionBullets")));
        }
        log.info("Verified Product Details section on " + pg + " PDP " + mode + " mode on " + device);
        Thread.sleep(1000);
    }

    @Then("^I verify elements under Price Details section on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingElementsUnderPriceDetails(String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.priceDetailsSection")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.priceDetailsHeader")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.priceDetailsBtn")));
            Thread.sleep(1000);
            log.info("Main Price under Price Section::  "+Elements.findElement(Elements.element("pdp.priceDetails")).getText());
            Assert.assertTrue(Elements.findElement(Elements.element("pdp.priceDetails")).getText().replace("$","").trim().equals(originalPrice));

            if(Elements.elementPresent(Elements.element("pdp.salePrice"))) {
                log.info("Sale Price under Price Section::  "+Elements.findElement(Elements.element("pdp.salePrice")).getText());
                Assert.assertTrue(Elements.findElement(Elements.element("pdp.salePrice")).getText().replace("Sale $","").trim().equals(salePrice));
            }
        }
        log.info("Verified Price Details section on " + pg + " PDP " + mode + " mode on " + device + " w/ PID::  "+ PID);
        Thread.sleep(1000);
    }

    @Then("^I verify the navigation of all links under Shipping&Returns on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingNavigationOfAllShippingReturnLinks(String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight/2)");

            Thread.sleep(1000);
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.shippingReturnsBullets")));

            // verifying shippingPolicyLink
            Assert.assertTrue((Elements.findElement(Elements.element("pdp.shippingPolicyLink")).getText()).contains("Shipping"));
            Clicks.click("pdp.shippingPolicyLink");
            Navigate.switchWindow(1);
            Wait.forPageReady();
            Assert.assertTrue((Elements.findElement(Elements.element("pdp.shippingNreturnPolicyReg")).getText()).equalsIgnoreCase("What is Macy's shipping policy?"));
            Navigate.switchWindowClose();
            Thread.sleep(1000);

            // verifying returnPolicyLink
            Assert.assertTrue((Elements.findElement(Elements.element("pdp.returnPolicyLink")).getText()).equals("Return"));
            Clicks.click("pdp.returnPolicyLink");
            Navigate.switchWindow(1);
            Wait.forPageReady();
            Assert.assertTrue((Elements.findElement(Elements.element("pdp.shippingNreturnPolicyReg")).getText()).equalsIgnoreCase("What is Macy's return policy?"));
            Navigate.switchWindowClose();
            Thread.sleep(1000);

            // verifying exclusionsDetailsLink
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.exclusionsDetailsLink")));
            Assert.assertTrue((Elements.findElement(Elements.element("pdp.exclusionsDetailsLink")).getText()).equals("Exclusions & Details"));
            Clicks.click("pdp.exclusionsDetailsLink");
            Wait.forPageReady();
            Assert.assertTrue((WebDriverManager.getCurrentUrl()).contains("/free-shipping/index"));
        }
        log.info("Verified navigation of all links under Shipping & Returns section on " + pg + " PDP " + mode + " mode on " + device);
        Thread.sleep(1000);
    }

    @Then("^I verify the navigation to (member PDP|master PDP) (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingNavigationToPages(String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Wait.forPageReady();
            String url = WebDriverManager.getCurrentUrl();
            if(pg.equalsIgnoreCase("member PDP")) {
                if(!url.contains("&experience=responsive"))
                    Actions.visitURL(url+"&experience=responsive");
                Wait.secondsUntilElementPresent(("pdp.productTitle"),15);
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productTitle")));
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productMainImg")));

                if(!device.equalsIgnoreCase("desktop"))
                    Actions.scroll("down", null);

                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.productDescription")));
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.quantity")));
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.qtyLabel")));
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.qtyValue")));
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.qtyIncrementBtn")));
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.qtyDecrementBtn")));
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.addToBagBtn")));
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the AddToBag drawer as the \"([^\"]*)\" product being added in (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingAddToBagDrawer(String arg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            log.info("Verifying A2B drawer on Member PDP " + mode + " mode on " + device.toUpperCase() + " w/ PID:: "+PID);
            Navigate.switchWindow(1);
            Thread.sleep(3000);
            Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.productTitle")));
            log.info("productTitle:: PDP: \"" + productTitle + "\"  A2B overlay: \"" + Elements.findElement(Elements.element("add2bag.productTitle")).getText() +"\"");
            Assert.assertTrue(Elements.findElement(Elements.element("add2bag.productTitle")).getText().equalsIgnoreCase(productTitle));
            Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.productMainImg")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.itemsInBag")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.subtotal")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.checkoutBtn")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.continueShoppingBtn")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.bagQty")));
            log.info("Selected Quantity:: PDP: " + selectedQty + "  A2B overlay: " + Elements.findElement
                    (Elements.element("add2bag.bagQty")).getText());
            Assert.assertTrue(Integer.parseInt(Elements.findElement(Elements.element("add2bag.bagQty")).getText()) >= (selectedQty));

            if(arg.contains("Color") && selectedColor != null) {
                Assert.assertTrue(Elements.findElement(Elements.element("add2bag.productColor")).getText().equalsIgnoreCase(selectedColor));
                log.info("Selected Color:: PDP: " + selectedColor + "  A2B overlay: " + Elements.findElement(Elements.element("add2bag.productColor")).getText());
            }

            if(arg.contains("Size")) {
                Assert.assertTrue(Elements.findElement(Elements.element("add2bag.productSize")).getText().equalsIgnoreCase(selectedSize));
                log.info("Selected Size:: PDP: " + selectedSize + "  AddToBag overlay: " + Elements.findElement(Elements.element("add2bag.productSize")).getText());
            }

            Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.productPrice")));
            String a2bOriginalPrice = Elements.findElement(Elements.element("add2bag.productPrice")).getText().replace("$","");
            if(a2bOriginalPrice.contains(","))
                a2bOriginalPrice = a2bOriginalPrice.replace(",","");

            Assert.assertTrue(a2bOriginalPrice.equals(originalPrice));
            log.info("Product Original Price:: PDP: " + originalPrice + "  A2B overlay: " + a2bOriginalPrice);

            String subTotal = Elements.findElement(Elements.element("add2bag.subtotal")).getText().replace("$","").trim();
            if(subTotal.contains(","))
                subTotal = subTotal.replace(",","");

            if(Elements.elementPresent(Elements.element("add2bag.productSalePrice"))) {
                Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.productSalePrice")));
                String[] a2bSalePrice = Elements.findElement(Elements.element("add2bag.productSalePrice")).getText().split("\\$",0);
                log.info("Product Sale Price:: PDP: " + salePrice + "  A2B overlay: " + a2bSalePrice[1]);
                if(a2bSalePrice[1].contains(","))
                    a2bSalePrice[1] = a2bSalePrice[1].replace(",","");

                Assert.assertTrue(a2bSalePrice[1].equalsIgnoreCase(salePrice));

                log.info("Product SubTotal on A2B overlay:: SalePrice= " + salePrice + " * Qty= " + selectedQty + " ===>> "
                        + String.format("%.2f",Float.valueOf(salePrice) * selectedQty) + "   SubTotal = " + subTotal);
                Assert.assertTrue(subTotal.equals(String.format("%.2f",Float.valueOf(salePrice) * selectedQty)));

                String youSaved = Elements.findElement(Elements.element("add2bag.youSaved")).getText().replace("$","").trim();
                if(youSaved.contains(","))
                    youSaved = youSaved.replace(",","");
                log.info("YouSaved amount on A2B overlay:: (originalPrice - salePrice) * Qty ===>> (" + originalPrice + " - " + salePrice + ") * " + selectedQty + " = "
                        + (String.format("%.2f",(Float.valueOf(originalPrice) - Float.valueOf(salePrice)) * selectedQty)) + " ===>> YouSaved = " + youSaved);
                Assert.assertTrue(youSaved.equals(String.format("%.2f",(Float.valueOf(originalPrice) - Float.valueOf(salePrice)) * selectedQty)));
            }
            else {
                log.info("Product SubTotal on A2B overlay:: originalPrice= " + originalPrice + " * Qty= " + selectedQty + " ===>> "
                        + String.format("%.2f",Float.valueOf(originalPrice) * selectedQty) + "   SubTotal = " + subTotal);
                Assert.assertTrue(subTotal.equals(String.format("%.2f",Float.valueOf(originalPrice) * selectedQty)));
            }
            log.info(" A2B drawer verification completed on Member PDP " + mode + " mode on " + device.toUpperCase() + " w/ PID:: "+PID);
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the ShoppingBag page as the \"([^\"]*)\" product being added in (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingShoppingBagPage(String arg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            log.info(" Verifying ShoppingBag page on Member PDP " + mode + " mode on " + device.toUpperCase() + " w/ PID:: "+PID);
            Wait.secondsUntilElementPresent("shoppingBag.productImg", 10);
            Assert.assertTrue(Elements.elementPresent("shoppingBag.productTitle"));
            log.info("productTitle:: PDP: \"" + productTitle + "\"  shoppingBag page: \"" + Elements.findElement(Elements.element("shoppingBag.productTitle")).getText() +"\"");
            Assert.assertTrue(Elements.findElement(Elements.element("shoppingBag.productTitle")).getText().equalsIgnoreCase(productTitle));
            Assert.assertTrue(Elements.elementPresent("shoppingBag.shoppingBagId"));
            Assert.assertNotNull(Elements.findElement(Elements.element("shoppingBag.shoppingBagId")).getText());
            bagId = Elements.findElement(Elements.element("shoppingBag.shoppingBagId")).getText();
            log.info(bagId.replace("Bag ID: ", "BagId::  "));

            Assert.assertTrue(Elements.elementPresent("shoppingBag.shoppingBagWebId"));
            log.info("PID/WebId::  " + Elements.findElement(Elements.element("shoppingBag.shoppingBagWebId")).getText());
            Assert.assertTrue(Elements.findElement(Elements.element("shoppingBag.shoppingBagWebId")).getText().equals(PID));

            log.info("Selected Quantity:: PDP: " + selectedQty + " ShoppingBag page: " + Elements.findElement
                    (Elements.element("shoppingBag.quantity")).getText());
            Assert.assertTrue(Integer.parseInt(Elements.findElement(Elements.element("shoppingBag.quantity")).getText()) == (selectedQty));

            if(arg.contains("Color") && selectedColor != null) {
                Assert.assertTrue(Elements.findElement(Elements.element("shoppingBag.productColor")).getText().equalsIgnoreCase(selectedColor));
                log.info("Selected Color:: PDP: " + selectedColor + "  shoppingBag page: " + Elements.findElement(Elements.element("shoppingBag.productColor")).getText());
            }
            else if(selectedColor == null) {
                Assert.assertTrue(Elements.findElement(Elements.element("shoppingBag.productColor")).getText().equalsIgnoreCase("No Color"));
                log.info("The item has no color displayed on PDP: " + selectedColor + " ... Color displayed on ShoppingBag page: "
                        + Elements.findElement(Elements.element("shoppingBag.productColor")).getText());
            }

            if(arg.contains("Size")) {
                Assert.assertTrue(Elements.findElement(Elements.element("shoppingBag.productSize")).getText().equalsIgnoreCase(selectedSize));
                log.info("Selected Size:: PDP: " + selectedSize + "  shoppingBag page: " + Elements.findElement(Elements.element("shoppingBag.productSize")).getText());
            }

            Assert.assertTrue(Elements.elementPresent(Elements.element("shoppingBag.originalPrice")));
            Assert.assertTrue(Elements.findElement(Elements.element("shoppingBag.originalPrice")).getText().replace("Reg. $","").trim().equalsIgnoreCase(originalPrice)
                    || Elements.findElement(Elements.element("shoppingBag.originalPrice")).getText().replace("$","").trim().equalsIgnoreCase(originalPrice));
            log.info("Product Original Price:: PDP: " + originalPrice + "  ShoppingBag page: " + Elements.findElement
                    (Elements.element("shoppingBag.originalPrice")).getText().replace("Reg. $",""));
            String totalPrice = Elements.findElement(Elements.element("shoppingBag.itemTotalPrice")).getText().replace("$","").trim();
            if(totalPrice.contains(","))
                totalPrice = totalPrice.replace(",","");

            if(Elements.elementPresent(Elements.element("shoppingBag.salePrice"))) {
                Assert.assertTrue(Elements.elementPresent(Elements.element("shoppingBag.salePrice")));
                Assert.assertTrue(Elements.findElement(Elements.element("shoppingBag.salePrice")).getText().replace("Sale $","").equalsIgnoreCase(salePrice));
                log.info("Product Sale Price:: PDP: " + salePrice + "  ShoppingBag page: " + Elements.findElement
                        (Elements.element("shoppingBag.salePrice")).getText().replace("Sale $",""));

                Assert.assertTrue(totalPrice.equals(String.format("%.2f",Float.valueOf(salePrice) * selectedQty)));
                log.info("Product Total Price on ShoppingBag page:: SalePrice= " + salePrice + " * Qty= " + selectedQty + " ===>> "
                        + String.format("%.2f",Float.valueOf(salePrice) * selectedQty) + "   ItemTotalPrice = " + totalPrice);
            }
            else {
                Assert.assertTrue(totalPrice.equals(String.format("%.2f",Float.valueOf(originalPrice) * selectedQty)));
                log.info("Product Total Price on ShoppingBag page:: OriginalPrice= " + originalPrice + " * Qty= " + selectedQty + " ===>> "
                        + String.format("%.2f",Float.valueOf(originalPrice) * selectedQty) + "   ItemTotalPrice = " + totalPrice);
            }
            log.info(" ShoppingBag page verification completed on Member PDP " + mode + " mode on " + device.toUpperCase() + " w/ PID:: "+PID);
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the ShoppingBag page in (site|iship|registry) mode for bag empty message & BagID removed on (desktop|mobile|tablet)")
    public void verifyingRemoveItemsOnShoppingBagPage(String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            log.info("Verifying items removed from bag on ShoppingBag page in "+mode+" mode on "+device+"!");
            Thread.sleep(1000);
            Assert.assertTrue(Elements.findElement("shoppingBag.shoppingBagId").getText().equals(""));
            log.info("BagID::  \"" + Elements.findElement("shoppingBag.shoppingBagId").getText() + "\"");
            Assert.assertTrue(Elements.elementPresent("shoppingBag.bagEmptyMsg"));
            Assert.assertTrue(Elements.findElement("shoppingBag.bagEmptyMsg").getText().equals("Your Current Shopping Bag is empty."));
            log.info("Bag Empty Message::  " + Elements.findElement("shoppingBag.bagEmptyMsg").getText());
            log.info("Verification completed on items removed from bag on ShoppingBag page in "+mode+" mode on "+device.toUpperCase()+" w/ PID::  "+PID);
        }
        Thread.sleep(1000);
    }

    @Then("^I verify special offers (should|should not) be displayed on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyOffersShouldBeDisplayed(String arg, String pg, String mode, String device) throws Throwable{
        if(deviceRunStatus) {
            if(arg.equalsIgnoreCase("should")) {
                if(device.equalsIgnoreCase("desktop")) {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.badgeHeader")));
                    List<WebElement> badgeHeaderElements = Elements.findElements(Elements.element("pdp.badgeHeader"));
                    log.info("Size of promo badges::" + badgeHeaderElements.size());
                    Assert.assertTrue(badgeHeaderElements.size() >= 1);
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.promoBadge")));
                    List<String> promoBadgeTextList = new ArrayList<>();
                    for (WebElement badgeHeader : badgeHeaderElements) {
                        log.info("Details Link Text::" + badgeHeader.getText());
                        promoBadgeTextList.add(badgeHeader.getText());
                    }
                    List<WebElement> detailsLink = Elements.findElements(Elements.element("pdp.promoBadge"));
                    for (WebElement details : detailsLink) {
                        details.click();
                        assertPromoBadges(promoBadgeTextList);
                    }
                }
                else {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.badgeHeader")));
                    List<WebElement> badgeHeaderElements = Elements.findElements(Elements.element("pdp.badgeHeader"));
                    log.info("Size of promo badges::" + badgeHeaderElements.size());
                    Assert.assertTrue(badgeHeaderElements.size() == 1);
                    List<String> promoBadgeTextList = new ArrayList<String>();
                    for (WebElement badgeHeader : badgeHeaderElements) {
                        log.info("Details Link Text::" + badgeHeader.getText());
                        promoBadgeTextList.add(badgeHeader.getText());
                    }
                    Elements.findElement(Elements.element("pdp.promoBadge")).click();
                    assertPromoBadges(promoBadgeTextList);
                }
            }
            else {
                Assert.assertFalse(Elements.elementPresent(Elements.element("pdp.badgeHeader")));
                Assert.assertFalse(Elements.elementPresent(Elements.element("pdp.promoBadge")));
                List<WebElement> badgeHeaderElements= Elements.findElements(Elements.element("pdp.badgeHeader"));
                Assert.assertTrue(CollectionUtils.isEmpty(badgeHeaderElements));
            }
        }
        log.info("Verified Special Offers " + arg + " be displayed on " + pg + " PDP " + mode + " mode on " + device);
        Thread.sleep(1000);
    }

    private void assertPromoBadges(List<String> promoBadgeTextList){
        Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.specialOfferSection")));
        List<WebElement> promoTitles = Elements.findElements(Elements.element(("pdp.badgeTitle")));
        for(WebElement promoTitle:promoTitles){
            Assert.assertTrue(promoBadgeTextList.contains(promoTitle.getText()));
        }
    }

    @Then("^I verify the \"([^\"]*)\" & \"([^\"]*)\" of the TopCategory links on (master|member|memberBigTicket|eGiftCard) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingTopCategoryLinksOnPDP(String rootCat, String homeCat, String productType, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            if(mode.equalsIgnoreCase("iship")) {
                Wait.forPageReady();
                Assert.assertTrue(Elements.elementPresent(Elements.element("home.backToUS")));
                Assert.assertTrue(Elements.findElement(Elements.element("home.backToUS")).getText().equalsIgnoreCase("Go to U.S. Site"));
            }
            else if(mode.equalsIgnoreCase("registry")) {
                Wait.forPageReady();
                String url = WebDriverManager.getCurrentUrl();
                Assert.assertTrue(url.contains("/registry/wedding/product/"));
            }
            List<WebElement> topCategoryLinks = Elements.findElements(("pdp.topCategoryLinks"));
            int cnt = 0;
            for(WebElement link: topCategoryLinks) {
                if(device.equalsIgnoreCase("desktop")) {
                    Assert.assertFalse(Elements.elementPresent(Elements.element("pdp.topCategoryLinks")));
                    break;
                }
                if(productType.equalsIgnoreCase("E-Gift Card")) {
                    Assert.assertTrue(topCategoryLinks.size() == 1);
                    Assert.assertTrue(rootCat.equalsIgnoreCase(link.getText()));
                    break;
                }
                if(cnt == 0) {
                    Assert.assertTrue(rootCat.equalsIgnoreCase(link.getText()));
                    log.info("Verified root-category \"" + link.getText() + "\" link on " + productType + " PDP " + mode + " mode on " + device + "!");
                }
                else {
                    Assert.assertTrue(homeCat.equalsIgnoreCase(link.getText()));
                    log.info("Verified home-category \"" + link.getText() + "\" link on " + productType + " PDP " + mode + " mode on " + device + "!");
                }
                cnt++;
                Thread.sleep(1000);
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify navigation of TopCategory \"([^\"]*)\" & \"([^\"]*)\" links on (master|member|memberBigTicket|eGiftCard) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingTopCategoryLinksLandingPages(String rootCat, String homeCat, String productType, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Wait.secondsUntilElementPresent(("pdp.topCategoryLinks"),10);
            List<WebElement> topCategoryLinks = Elements.findElements(("pdp.topCategoryLinks"));
            int cnt = 0;
            for(WebElement link: topCategoryLinks) {
                if(device.equalsIgnoreCase("desktop")) {
                    Assert.assertFalse(Elements.elementPresent(Elements.element("pdp.topCategoryLinks")));
                    log.info("Verified PDP:: No TopCategoryLinks Feature On " + device + "!");
                    break;
                }
                if(cnt == 0) {
                    Clicks.click(link);
                    Wait.secondsUntilElementPresent(Elements.element("searchNbrowse.catSplashCatName"),10);
                    if(mode.equalsIgnoreCase("iship")) {
                        Wait.forPageReady();
                        Assert.assertTrue(Elements.elementPresent(Elements.element("home.backToUS")));
                        Assert.assertTrue(Elements.findElement(Elements.element("home.backToUS")).getText().equalsIgnoreCase("Go to U.S. Site"));
                    }
                    else if(mode.equalsIgnoreCase("registry")) {
                        Wait.forPageReady();
                        String url = WebDriverManager.getCurrentUrl();
                        Assert.assertTrue(url.contains("/wedding-registry/"));
                    }

                    if(productType.equalsIgnoreCase("E-Gift Card")) {
                        Assert.assertTrue(topCategoryLinks.size() == 1);
                        Assert.assertTrue(Elements.findElement(Elements.element("eGiftcard.rootCategory")).getText().contains(rootCat));
                        log.info("RootCategory verified as:  \"" + Elements.findElement(Elements.element("eGiftcard.rootCategory")).getText() + "\" in " + mode + " mode!");
                        break;
                    }
                    else {
                        Assert.assertTrue(Elements.findElement(Elements.element("searchNbrowse.browseCatName")).getText().contains(rootCat));
                        log.info("RootCategory verified as::  \"" + Elements.findElement(Elements.element("searchNbrowse.browseCatName")).getText() + "\" in " + mode + " mode on " + device + "!");
                    }

                    if(topCategoryLinks.size() == 1 && homeCat.equalsIgnoreCase("None")) {
                        log.info("There is only \"" + rootCat + "\" BreadCrumbs Link displayed on PDP!");
                        break;
                    }
                    else
                        TextBoxes.typeTextNEnter("home.search_field", PID);
                }
                else {
                    if(!(mode.equalsIgnoreCase("iship")))
                        Cookies.deleteAllCookies();
                    String[] newUrl = WebDriverManager.getCurrentUrl().split("\\?ID=");
                    Actions.visitURL(newUrl[0]+"\\?ID="+PID+"&experience=responsive");
                    Navigate.browserRefresh();
                    Wait.secondsUntilElementPresent(("pdp.topCategoryLinks"),10);

//                    Clicks.click(link);// different DOM/linkElements as changed url above includes &experience=responsive; temporarily changing click-call
                    Clicks.click(Elements.findElement(By.xpath(".//*[@data-auto='breadcrumb-links']/a[2]")));

                    Wait.secondsUntilElementPresent(Elements.element("searchNbrowse.browseCatName"),10);
                    if(mode.equalsIgnoreCase("iship")) {
                        Wait.forPageReady();
                        Assert.assertTrue(Elements.elementPresent(("home.iShipFlagLink")));
                        Assert.assertTrue(Elements.findElement(("home.iShipFlagLink")).getText().equalsIgnoreCase("Go to U.S. Site"));
                    }
                    else if(mode.equalsIgnoreCase("registry")) {
                        Wait.forPageReady();
                        String url = WebDriverManager.getCurrentUrl();
                        Assert.assertTrue(url.contains("/wedding-registry/"));
                    }
                    Assert.assertTrue(Elements.findElement(Elements.element("searchNbrowse.browseCatName")).getText().contains(homeCat));
                    log.info("HomeCategory verified as::  \"" + Elements.findElement(Elements.element("searchNbrowse.browseCatName")).getText() + "\" in " + mode + " mode on " + device + "!");
                }
                cnt++;
                Thread.sleep(1000);
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify Type feature & functionality on member PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingTypeFeatureOnPDP(String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Thread.sleep(1000);
            Wait.secondsUntilElementPresent("pdp.typeSection", 10);
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.typeSection")));
            Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.typeHeader")));
            Assert.assertTrue(Elements.findElement(Elements.element("pdp.selectedType")).getText().equals("Please select"));
            List<WebElement> types = Elements.findElements(Elements.element("pdp.availableTypes"));
            log.info("Number of Types displayed: " + types.size());
            for(int i=0; i < types.size(); i++) {
                try {
                    types.get(i).click();
                    Thread.sleep(1000);
                    Assert.assertTrue(Elements.findElement(Elements.element("pdp.selectedType")).getText().equals(types.get(i).getText()));
                }
                catch(Exception e) {
                    e.getStackTrace();
                    log.warning("Verification Failed! Element missing in DOM...");
                }
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the \"([^\"]*)\" FOB link landing page from PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingTheFOBsLandingPageFromPDP(String arg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.FOB_Tabs", 15);
            String url = WebDriverManager.getCurrentUrl();
            if(arg.equals("women"))
                Assert.assertTrue(url.contains("/shop/womens-clothing/"));
            else if(arg.equals("men"))
                Assert.assertTrue(url.contains("/shop/mens-clothing/"));
            else if(arg.equals("beauty"))
                Assert.assertTrue(url.contains("/shop/makeup-"));
            else if(arg.equals("Home Decor"))
                Assert.assertTrue(url.contains("/shop/wedding-registry/home-decor/"));
            else if(arg.equals("Dining"))
                Assert.assertTrue(url.contains("/shop/wedding-registry/dining-entertaining/"));
            log.info("FOBs verification completed!");
        }
        Thread.sleep(1000);
    }

    @Then("^I verify (Pricing Policy|any) overlay on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingPricingPolicyOverlay(String arg, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            if(pg.equalsIgnoreCase("member")) {
                if(arg.equalsIgnoreCase("Pricing Policy")) {
                    Thread.sleep(2000);
                    Navigate.switchWindow(1);
                    Assert.assertTrue(Elements.elementPresent("pdp.pricePolicyOverlay"));
                    Assert.assertTrue(Elements.elementPresent("pdp.pricePolicyOverlayHeader"));
                    Assert.assertTrue((Elements.findElement(Elements.element("pdp.pricePolicyOverlayHeader")).getText()).equalsIgnoreCase("Pricing Policy"));
                }
            }
        }
        log.info("Verified " + arg + " on " + pg + " PDP " + mode + " mode on " + device + "!");
        Thread.sleep(1000);
    }

    @Then("^I verify the QB count is updating with (default|maximum|random|0) quantity items in bag on (desktop|mobile|tablet)")
    public void verifyingQuickBagCountUpdating(String arg, String device) throws Throwable {
        if(deviceRunStatus) {
            Wait.secondsUntilElementPresent("quickBag.qbLink", 10);
            WebElement hoverQB = Elements.findElement("quickBag.qbLink");

            Clicks.hoverForSelection(hoverQB);
            Wait.secondsUntilElementPresent("quickBag.qbCount", 10);
            Thread.sleep(3000);
            Assert.assertTrue(Elements.elementPresent(Elements.element("quickBag.qbCount")));
            String qbCount = Elements.findElement(Elements.element("quickBag.qbCount")).getAttribute("aria-label").replace("Shopping bag has ", "").replace(" items", "");
            if(qbCount.contains("item"))
                qbCount = qbCount.replace("item", "").trim();
            int qtyCount = Integer.parseInt(qbCount);
            if(arg.equals("0")) {
                Assert.assertTrue(qtyCount == 0 );
                Assert.assertTrue(qtyCount == Integer.parseInt(arg));
                String expected_msg = "0 items in your bag. Shop great deals now!";
                String quickbag_Content = Elements.findElement(Elements.element("quickBag.qbZeroItemCount")).getText();
                Assert.assertTrue(quickbag_Content.toLowerCase().contains(expected_msg.toLowerCase()));
                log.info("There is " + qtyCount + " item shown in QB!\nEmptyBag Message::  " + quickbag_Content + "\n");
            }
            else {
                Assert.assertTrue(qtyCount == selectedQty);
                log.info("Quantity selected:: PDP: " + selectedQty + " QuickBag overlay: " + qtyCount);
                log.info("Verification completed on QuickBag count update on "+device.toUpperCase()+" w/ PID::  "+PID);
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the elements on QuickBag overlay on (desktop|mobile|tablet)")
    public void verifyingQuickBagElements(String device) throws Throwable {
        if(deviceRunStatus) {
            log.info(" Verifying QuickBag overlay on Member PDP on " + device + " with PID:: "+PID);
            Wait.secondsUntilElementPresent("quickBag.qbLink", 10);
            WebElement hoverQB = Elements.findElement("quickBag.qbLink");

            Clicks.hoverForSelection(hoverQB);
            Wait.secondsUntilElementPresent("quickBag.qbCount", 10);
            Thread.sleep(3000);
            Assert.assertTrue(Elements.elementPresent(Elements.element("quickBag.qbCount")));
            String qbCount = Elements.findElement(Elements.element("quickBag.qbCount")).getAttribute("aria-label").replace("Shopping bag has ", "").replace(" items", "");
            if(qbCount.contains("item"))
                qbCount = qbCount.replace("item", "").trim();
            int qtyCount = Integer.parseInt(qbCount);
            Assert.assertTrue(qtyCount == selectedQty);
            if(selectedQty > 0) {
                log.info("Selected Quantity:: PDP: " + selectedQty + " QuickBag overlay: " + qtyCount);
                List<WebElement> qbSizeColor = Elements.findElements(Elements.element("quickBag.qbSizeColor"));
                if(selectedColor != null) {
                    Assert.assertTrue(qbSizeColor.get(0).getText().replace(" ,","").equals(selectedColor));
                    log.info("Selected Color:: PDP: " + selectedColor + " QuickBag overlay: " + qbSizeColor.get(0).getText().replace(" ,",""));
                }
                else {
                    log.info("The item has no color displayed on PDP: " + selectedColor + " ... Color displayed on QuickBag overlay: " + qbSizeColor.get(0).getText());
                    Assert.assertTrue(qbSizeColor.get(0).getText().replace(" ,","").equals("No Color"));
                }

                if(selectedSize != null) {
                    Assert.assertTrue(qbSizeColor.get(1).getText().equals(selectedSize));
                    log.info("Selected Size:: PDP: " + selectedSize + " QuickBag overlay: " + qbSizeColor.get(1).getText());
                }
                else
                    log.info("The item has no size as selectedSize on PDP is: " + selectedSize + "!");

                Assert.assertTrue(Elements.elementPresent("quickBag.productTitle"));
                log.info("productTitle:: PDP: \"" + productTitle + "\"  quickBag overlay: \"" + Elements.findElement(Elements.element("quickBag.productTitle")).getText() +"\"");
                Assert.assertTrue(Elements.findElement(Elements.element("quickBag.productTitle")).getText().equalsIgnoreCase(productTitle));
                Assert.assertTrue(Elements.elementPresent(Elements.element("quickBag.originalPrice")));
                Assert.assertTrue(Elements.findElement(Elements.element("quickBag.originalPrice")).getText().replace("REG: $","").trim().equalsIgnoreCase(originalPrice)
                        || Elements.findElement(Elements.element("quickBag.originalPrice")).getText().replace("$","").trim().equalsIgnoreCase(originalPrice));
                log.info("Product Original Price:: PDP: " + originalPrice + "  quickBag overlay: " + Elements.findElement
                        (Elements.element("quickBag.originalPrice")).getText().replace("REG: $",""));

                String totalPrice = Elements.findElement(Elements.element("quickBag.totalPrice")).getText().replace("Your Bag Total: $","").trim();
                if(totalPrice.contains(","))
                    totalPrice = totalPrice.replace(",","");

                if(Elements.elementPresent(Elements.element("quickBag.salePrice"))) {
                    Assert.assertTrue(Elements.elementPresent(Elements.element("quickBag.salePrice")));
                    String qbSalePrice = Elements.findElement(Elements.element("quickBag.salePrice")).getText().replace("SALE: $","");
                    log.info("Product Sale Price:: PDP: " + salePrice + "  QuickBag overlay: " + Elements.findElement
                            (Elements.element("quickBag.salePrice")).getText().replace("SALE: $",""));
                    Assert.assertTrue(qbSalePrice.equalsIgnoreCase(salePrice));

                    log.info("Product total on QuickBag overlay:: SalePrice= " + salePrice + " * Qty= " + selectedQty + " ===>> "
                            + String.format("%.2f",Float.valueOf(salePrice) * selectedQty) + "   totalPrice = " + totalPrice);
                    Assert.assertTrue(totalPrice.equals(String.format("%.2f",Float.valueOf(salePrice) * selectedQty)));

                    String youSaved = Elements.findElement(Elements.element("quickBag.youSaved")).getText().replace("You Saved: $","").trim();
                    log.info("YouSaved amount on QuickBag overlay:: (originalPrice - salePrice) * Qty ===>> (" + originalPrice + " - " + salePrice + ") * " + selectedQty + " = "
                            + (String.format("%.2f",(Float.valueOf(originalPrice) - Float.valueOf(salePrice)) * selectedQty)) + " ===>> YouSaved = " + youSaved);
                    Assert.assertTrue(youSaved.equals(String.format("%.2f",(Float.valueOf(originalPrice) - Float.valueOf(salePrice)) * selectedQty)));
                }
                else {
                    log.info("Product totalPrice on QuickBag overlay:: originalPrice= " + originalPrice + " * Qty= " + selectedQty + " ===>> "
                            + String.format("%.2f",Float.valueOf(originalPrice) * selectedQty) + "   totalPrice = " + totalPrice);
                    Assert.assertTrue(totalPrice.equals(String.format("%.2f",Float.valueOf(originalPrice) * selectedQty)));
                }
            }
            else {
                String expected_msg = "0 items in your bag. Shop great deals now!";
                String quickbag_Content = Elements.findElement(Elements.element("quickBag.qbZeroItemCount")).getText();
                Assert.assertTrue(quickbag_Content.toLowerCase().contains(expected_msg.toLowerCase()));
                log.info("There is " + qtyCount + " item shown in QuickBag!\nEmptyBag Message::  " + quickbag_Content + "\n");
            }
            log.info(" QuickBag overlay verification completed on Member PDP on " + device + " with PID:: "+PID);
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the (Pinterest|Email) social icon on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingSocialIconsOnPDP(String arg, String pg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            if(arg.equalsIgnoreCase("Pinterest")) {
                if(!device.equalsIgnoreCase("desktop"))
                    ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                Assert.assertTrue(Elements.elementPresent("pdp.pinterestIcon"));
                Thread.sleep(1000);
                Clicks.click(Elements.element("pdp.pinterestIcon"));
                log.info("Pinterest button clicked!\n");

                Navigate.switchWindow(1);
                Wait.secondsUntilElementPresent("pdp.pinterestOverlay", 15);
                Assert.assertTrue(Elements.elementPresent("pdp.pinterestOverlay"));
                Assert.assertTrue(Elements.elementPresent("pdp.pinterestUserEmail"));
                Assert.assertTrue(Elements.elementPresent("pdp.pinterestUserPwd"));
                Assert.assertTrue(Elements.elementPresent("pdp.pinterestContinueBtn"));
                Clicks.click("pdp.pinterestContinueBtn");
            }
            else {
                Assert.assertTrue(Elements.elementPresent("pdp.emailIcon"));
                Clicks.click(Elements.element("pdp.emailIcon"));
                log.info("Email button clicked!\n");
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify TrueFit functionality on member PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void verifyingTrueFitFunctionalityOnPDP(String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            log.info("Verifying TrueFit on Member PDP " + mode + " mode on " + device + " with PID:: "+PID);
            if(!device.equalsIgnoreCase("desktop"))
                Actions.scroll("down", null);

            Assert.assertTrue(Elements.elementPresent("pdp.trueFitSection"));
            Assert.assertTrue(Elements.elementPresent("pdp.trueFitLabel"));
            Assert.assertTrue((Elements.findElement(Elements.element("pdp.trueFitLabel")).getText())
                    .equals("Runs True to Size Find Your True Fit"));
            Assert.assertTrue(Elements.elementPresent("pdp.trueFitLink"));
            Assert.assertTrue((Elements.findElement(Elements.element("pdp.trueFitLink")).getText())
                    .equals("Find Your True Fit"));
            Clicks.click(Elements.element("pdp.trueFitLink"));
            log.info("TrueFit Link Clicked!");

//            Wait.secondsUntilElementPresent("pdp.trueFitFrame", 5);
//            Assert.assertTrue(Elements.elementPresent("pdp.trueFitFrame"));
            Thread.sleep(1000);
//            Assert.assertTrue(Elements.elementPresent("pdp.trueFitHeightFeet"));
//            TextBoxes.typeTextNEnter(Elements.element("pdp.trueFitHeightFeet"), "5");
//            Thread.sleep(1000);
//            TextBoxes.typeTextNEnter(Elements.element("pdp.trueFitHeightInch"), "7");
            log.info(" TrueFit verification completed on Member PDP " + mode + " mode on " + device + " with PID:: "+PID);
            log.warning(" TrueFit verification pending due to trueFit issues w/ Tealium!");
        }
        Thread.sleep(1000);
    }

    @Then("^I verify that the product is added to list in (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingAddToListFunctionality(String mode, String device) throws Throwable {
        if (deviceRunStatus) {
//            Wait.forPageReady();
//            Wait.secondsUntilElementPresent("addToList.productImg", 10);
//            Assert.assertTrue(Elements.elementPresent("addToList.productId"));
//            Assert.assertTrue(Elements.findElement("addToList.productId").getText().equals(PID));
            log.warning("DEFECT:: Link to WishList page not working!");
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the AddToRegistry drawer as the \"([^\"]*)\" product being added in (site|registry) mode on (desktop|mobile|tablet)")
    public void verifyingAddToRegistryDrawer(String arg, String mode, String device) throws Throwable {
        if(deviceRunStatus) {
            Navigate.switchWindow(1);
            Thread.sleep(2000);
            log.warning("Automation Pending!");
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the product category on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingProductCategoriesOnPDP(String pg, String mode, String device) throws Throwable {
        if (deviceRunStatus) {
            log.warning("Automation Pending!");
        }
        Thread.sleep(1000);
    }

    @Then("^I verify the reviews count and stars on (master|member) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void verifyingReviewsStarsCountOnPDP(String pg, String mode, String device) throws Throwable {
        if (deviceRunStatus) {
            Assert.assertTrue(Elements.elementPresent("pdp.reviewStars"));
            Assert.assertTrue(Elements.elementPresent("pdp.reviewCountLink"));
            if(xApiFccValidationEnabled) {
                List<String> attrValues = PDPxAPISteps.getFccXapiValidatedAttributes(PID, "reviews");
                Assert.assertTrue(Elements.findElement("pdp.ratingPercentage").getAttribute("style")
                        .replace("width: ","").replace("%;","").trim().equals(attrValues.get(0)));
                log.info("Rating Stars & Percentage on PDP Vs xApi::  "+Elements.findElement("pdp.ratingPercentage").getAttribute("style")
                        .replace("width: ","").replace("%;","").trim() +" == "+attrValues.get(0));

                Assert.assertTrue(Elements.findElement("pdp.reviewCountLink").getText().replace("Reviews","").trim()
                        .equals(attrValues.get(1)));
                log.info("Review Count on PDP Vs xApi::  " +Elements.findElement("pdp.reviewCountLink").getText()
                        .replace("Reviews","").trim() + " == " + attrValues.get(1));
                logger.info("\n******* PDP Vs xApi verifications for \"Reviews\" completed!" +
                        "\n===========================================================================\n");
            }
            else {
                log.info("Rating Stars & Percentage on PDP::  "+Elements.findElement("pdp.ratingPercentage").getAttribute("style")
                        .replace("width: ","").replace(";","").trim());
                log.info("Review Count on PDP::  " +Elements.findElement("pdp.reviewCountLink").getText()
                        .replace("Reviews","").trim());
            }
            Clicks.click("pdp.reviewCountLink");
            log.info("reviewCountLink is clicked!\n");
            log.warning("Implementation for Write Reviews pending!\n");
        }
        Thread.sleep(1000);
    }

}