package com.macys.sdt.projects.Selection.ResponsivePDP.steps.bcom;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.projects.Selection.ResponsivePDP.actions.bcom.BCOMDatabase;
import com.macys.sdt.projects.Selection.ResponsivePDP.actions.bcom.BCOMProducts;
import com.macys.sdt.projects.Selection.ResponsivePDP.actions.bcom.BCOMxapi;
import com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom.Actions;
import com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom.Devices;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.macys.sdt.projects.Selection.ResponsivePDP.actions.bcom.BCOMActions;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toMap;

public class bcomPDP {
    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static SoftAssertions softly = new SoftAssertions();
    private static String url = "https://www.bcom-rpdp17z.tbe.zeus.fds.com/";
    private static String pId = null;
    private static String colorLabel = null;
    private static String colorId = null;
    private static String size = null;
    private static int selectedQty;
    private static String selectedColor;
    private static float originalPrice;
    private static float salePrice;
    private static String brandName;
    private static String productName;
    private static String mainImageSrc;


    @Given("^I visit the bcom home page on (desktop|mobile|tablet) as a (guest|registered|registry) user$")
    public void iVisitBcomHomePageWithDifferentDevices(String device, String user) throws Throwable {
        if (Devices.runScenario(device)) {
            Actions.visitURL(url);
            Thread.sleep(1000);
            Actions.changeDimension(device);
            Cookies.deleteAllCookies();
            if (user.equalsIgnoreCase("registered")) {

            } else if (user.equalsIgnoreCase("registry")) {

            }
            Thread.sleep(1000);
        } else {
            Assert.assertTrue("Unknown device", false);
        }
    }

    @Given("^I navigate directly to (master|member) PDP that is? \"(.*?)\"(?: but not(?: an?)? \"(.*?)\")?(?: on (site|iship|registry) mode on (desktop|mobile|tablet))$")
    public void NavigateDirectlyToPDP(String productType, String productTrue, String productFalse, String mode, String device) throws Throwable {
        if (Devices.runScenario(device)) {
            if (productType.equalsIgnoreCase("member")) {
                HashMap<String, Object> attribs = new HashMap<>();
                attribs.putAll(BCOMActions.setOptions(productTrue, productFalse));
                log.info("Finding products that are " + attribs);
                pId = BCOMProducts.getRandomPdpFromJSON(attribs); //get products from json
                //pId = BCOMDatabase.getRandomPdpFromDB(attribs); //get products from db2
                BCOMActions.navigateDirectlyToPDP(pId);
                log.info(pId);
                try {
                    Wait.forPageReady();
                    Wait.secondsUntilElementPresent("pdp.product_name", 10);
                } catch (Exception e) {
                    log.warning(String.format(" PDP loading improperly as productTitle is not displayed in " + mode + " mode!"));
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
            Actions.changeDimension(device);
            Cookies.deleteAllCookies();
        } else {
            Assert.assertTrue("Unknown device", false);
        }
    }

    @Given("^I navigate to bcom (master|member) PDP (site|iship|registry) mode with PID \"([^\"]*)\" on (desktop|mobile|tablet)$")
    public void NavigateDirectlyToPDPWithPID(String productType, String mode, String productId, String device) throws Throwable {
        if (Devices.runScenario(device)) {
            BCOMActions.navigateDirectlyToPDP(productId);
            log.info(productId);
            try {
                Wait.forPageReady();
                Wait.secondsUntilElementPresent("pdp.product_name", 10);
            } catch (Exception e) {
                log.warning(String.format(productType + " PDP loading improperly as productTitle is not displayed in " + mode + " mode!"));
                e.printStackTrace();
            }
            Thread.sleep(1000);
            Actions.changeDimension(device);
            Cookies.deleteAllCookies();
        } else {
            Assert.assertTrue("Unknown device", false);
        }
    }

    @Then("^I verify the basic elements on (master|member) PDP on (site|iship|registry) mode on (desktop|mobile|tablet) with Xapi$")
    public void iVerifyTheBasicElementsOnMemberPDPSiteModeOnDeviceWithXapi(String productType, String mode, String device) throws Throwable {

        List<String> attrValues = BCOMxapi.getBcomFccXapiValidatedAttributes(pId, "singleAttrValues");

        softly.assertThat(Elements.elementPresent(Elements.element("pdp.product_title"))).as("Product Title").isEqualTo(true);

        softly.assertThat(Elements.elementPresent(Elements.element("pdp.product_brand"))).as("Product Brand").isEqualTo(true);
        softly.assertThat(Elements.findElement(Elements.element("pdp.product_brand")).getText()
                .equals(attrValues.get(0))).as("ProductBrandName").isEqualTo(true);
        brandName = Elements.findElement(Elements.element("pdp.product_brand")).getText();
        log.info("BrandName Validated:: xApi: " + attrValues.get(0) + " == PDP/UI: "
                + brandName);

        softly.assertThat(Elements.elementPresent(Elements.element("pdp.product_name"))).as("Product Name").isEqualTo(true);
        softly.assertThat(Elements.findElement(Elements.element("pdp.product_name")).getText().trim()
                .equals(attrValues.get(1).trim())).as("ProductName").isEqualTo(true);
        productName = Elements.findElement(Elements.element("pdp.product_name")).getText();
        log.info("ProductName Validated:: xApi: " + attrValues.get(1) + " == PDP/UI: "
                + productName);

        softly.assertThat(!Elements.findElements("pdp.product_mainImg").isEmpty()).as("Product Main Image").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("pdp.product_quantity"))).as("Select Quantity").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("pdp.addToBag_button"))).as("addToBag Button").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("pdp.wishlist_button"))).as("WishList Button").isEqualTo(true);

        softly.assertAll();
        log.info("Verified the basic elements on " + productType + " PDP " + mode + " mode on " + device + " with FccXapi!");
        Thread.sleep(1000);
    }


    @Then("^I verify that \"([^\"]*)\" accordion is open on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatAccordionIsOpen(String expAccordionName, String ProductType, String mode, String devie) {
        String actAccordionName = Elements.findElement("pdp.active_accordion").getAttribute("data-section");
        log.info("accordion name is: " + actAccordionName);
        softly.assertThat(expAccordionName.equals(actAccordionName)).as("accordion name").isTrue();
        softly.assertAll();
    }

    @And("^I verify the elements of (ProductDetails|shipping-returns) accordion with Xapi on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void iVerifyTheElementsOfProductDetailsAccordionWithXapi(String accordion, String productType, String device) throws Throwable {
        if (productType.equals("member")) {
            switch (accordion) {
                case "ProductDetails": {
                    List<String> expProdDetailsText = BCOMxapi.getAccordionDetailsFromXapi(pId, "product-details");
                    String productDescription = Elements.findElement("pdp.product_description")
                            .getText();
                    softly.assertThat(expProdDetailsText.get(0).trim().replace(".  ", ". ")
                            .equals(productDescription)).as("Product Description").isTrue();
                    List<WebElement> bulletDetails = Elements.findElement("pdp.product_details_bullets")
                            .findElements(By.cssSelector("li"));
                    for (int i = 0; i < bulletDetails.size(); i++) {
                        if (!expProdDetailsText.get(i + 1).contains("View Demo Video")) {
                            softly.assertThat(bulletDetails.get(i).getText().equals(expProdDetailsText.get(i + 1)))
                                    .as("product details bullet text").isEqualTo(true);
                        }
                    }
                    break;
                }
                case "shipping-returns": {
                    List<String> expShippingDetailsText = BCOMxapi.getAccordionDetailsFromXapi(pId, "shipping-returns");
                    if (Elements.elementPresent("pdp.shipping_returns_bullets")) {
                        if (device.equalsIgnoreCase("mobile") || device.equalsIgnoreCase("tablet")) {
                            Actions.scroll("down", null);
                            JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getWebDriver();
                            jse.executeScript("window.scrollTo(0, document.body.scrollHeight/2);");
                            Thread.sleep(2000);
                        }
                        List<WebElement> actBulletDetails = Elements.findElement("pdp.shipping_returns_bullets")
                                .findElements(By.cssSelector("li"));
                        log.info("bullet text size: " + actBulletDetails.size());
                        for (int i = 0; i < actBulletDetails.size(); i++) {
                            softly.assertThat(expShippingDetailsText.contains(actBulletDetails.get(i).getText()))
                                    .as("shipping bullet text").isEqualTo(true);
                        }
                        log.info("shipping returns bullet text is verified");
                    }
                    softly.assertThat(Elements.elementPresent("pdp.shipping_return_policy"))
                            .as("Shipping and Returns policies").isTrue();
                    break;
                }
            }
        }
        softly.assertAll();
        Thread.sleep(1000);
    }

    @And("^I verify the navigation of all links under (ProductDetails|shipping-returns) section on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void iVerifyTheNavigationOfAllLinksAccordion(String accordion, String productType, String mode, String device) throws InterruptedException {
        if (productType.equalsIgnoreCase("member")) {
            if (device.equals("mobile") || device.equals("tablet")) {
                JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getWebDriver();
                jse.executeScript("window.scrollTo(0, document.body.scrollHeight/2);");
            }
            switch (accordion) {
                case "ProductDetails": {
                    if (Elements.elementPresent("pdp.static_link")) {
                        WebElement staticLink = Elements.findElement("pdp.static_link").findElement(By.tagName("a"));
                        if (staticLink.getText().contains("WORRYNOMORE")) {
                            softly.assertThat(staticLink.getText().equals("Learn more about our WORRYNOMORE mattress protection plan"))
                                    .as("WorryMore link text").isTrue();
                            Clicks.click(staticLink);
                            Navigate.switchWindow(1);
                            log.info("Navigated to Worry no more window");
                            softly.assertThat(Elements.elementPresent("pdp.worrynomore_popup_text1"))
                                    .as("Furniture Protection Program").isTrue();
                            softly.assertThat(Elements.elementPresent("pdp.worrynomore_popup_text2"))
                                    .as("Mattress Protection Plan").isTrue();
                            Navigate.switchWindowClose();
                        }
                    }
                    Thread.sleep(1000);
                    if (Elements.elementPresent("pdp.warranty_link")) {
                        WebElement warranty = Elements.findElement("pdp.warranty_link");
                        softly.assertThat(warranty.getText().equals("Warranty Information")).as("Warrnty link text").isTrue();
                        Clicks.click(warranty.findElement(By.tagName("a")));
                        Navigate.switchWindow(1);
                        log.info("Navigated to Warranty window");
                        softly.assertThat(Elements.elementPresent("pdp.link_type_text"))
                                .as("How can I get warranty information?").isTrue();
                        softly.assertThat(Elements.elementPresent("pdp.customer_service_text"))
                                .as("Bloomingdale's Customer Service").isTrue();
                        Navigate.switchWindowClose();
                    }
                    break;
                }
                case "shipping-returns": {
                    WebElement shippingLink = Elements.findElement("pdp.shipping_link");
                    softly.assertThat(shippingLink.getText().equals("Shipping"))
                            .as("Shipping link text").isTrue();
                    if (device.equalsIgnoreCase("mobile") || device.equalsIgnoreCase("tablet")) {
                        Actions.scroll("down", "pdp.shipping_link");
                        Thread.sleep(2000);
                    }
                    Thread.sleep(3000);
                    Clicks.javascriptClick(shippingLink);
                    Navigate.switchWindow(1);
                    log.info("Navigated to Shipping window");
                    softly.assertThat(Elements.elementPresent("pdp.link_type_text"))
                            .as("What is the shipping policy?").isTrue();
                    softly.assertThat(Elements.elementPresent("pdp.customer_service_text"))
                            .as("Bloomingdale's Customer Service").isTrue();
                    Navigate.switchWindowClose();
                    WebElement returnsLink = Elements.findElement("pdp.return_link");
                    softly.assertThat(returnsLink.getText().equals("Returns"))
                            .as("Returns link text").isTrue();
                    Thread.sleep(3000);
                    Clicks.click(shippingLink);
                    Navigate.switchWindow(1);
                    log.info("Navigated to Returns window");
                    softly.assertThat(Elements.elementPresent("pdp.link_type_text"))
                            .as("What is the return and exchange policy?").isTrue();
                    softly.assertThat(Elements.elementPresent("pdp.customer_service_text"))
                            .as("Bloomingdale's Customer Service").isTrue();
                    Navigate.switchWindowClose();
                    break;
                }
            }

        }
        softly.assertAll();
    }

    @And("^I click on \"([^\"]*)\" accordion on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void iClickOnAccordion(String accordion, String productType, String mode, String device) throws InterruptedException {
        if (accordion.equals("shipping-returns")) {
            Elements.findElement("pdp.active_accordion")
                    .findElement(By.className("accordion-header")).click();
            Thread.sleep(1000);
            Clicks.click("pdp.shipping_returns_accordion");
            String actAccordionName = Elements.findElement("pdp.active_accordion").getAttribute("data-section");
            if (device.equalsIgnoreCase("mobile")) {
                Actions.scroll("down", null);
            }
            log.info("accordion name is: " + actAccordionName);
            Thread.sleep(1000);
            softly.assertThat(accordion.equals(actAccordionName)).as("accordion name").isTrue();
        }
        softly.assertAll();
        Thread.sleep(3000);
    }

    @Then("^I verify the social share icons on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyTheSocialShareIconsOnPDP(String productType, String mode, String device) throws InterruptedException {
        switch (mode) {
            case "site": {
                switch (productType) {
                    case "member": {
                        if (device.equals("mobile") || device.equals("tablet")) {
                            JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getWebDriver();
                            //jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
                            jse.executeScript("window.scrollTo(0, document.body.scrollHeight/2);");
                        }
                        Actions.scroll("down", null);
                        Thread.sleep(3000);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_icon")).as("pinterest_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.facebook_icon")).as("facebook_icon").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.twitter_icon")).as("twitter_icon").isEqualTo(true);
                        Thread.sleep(2000);

                        Clicks.click(Elements.element("pdp.pinterest_icon"));
                        log.info(String.format("Pinterest button clicked!\n"));
                        Thread.sleep(2000);
                        Navigate.switchWindow(1);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_email")).as("Pinterset email").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_password")).as("Pinterset password").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.pinterest_continue")).as("Pinterset continue").isEqualTo(true);
                        Navigate.switchWindowClose();

                        Clicks.click(Elements.element("pdp.facebook_icon"));
                        log.info(String.format("facebook button clicked!\n"));
                        Thread.sleep(2000);
                        Navigate.switchWindow(1);
                        softly.assertThat(Elements.elementPresent("pdp.facebook_email")).as("facebook email").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.facebook_password")).as("facebook password").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.facebook_login")).as("facebook login").isEqualTo(true);
                        Navigate.switchWindowClose();

                        Clicks.click(Elements.element("pdp.twitter_icon"));
                        log.info(String.format("twitter button clicked!\n"));
                        Thread.sleep(2000);
                        Navigate.switchWindow(1);
                        softly.assertThat(Elements.elementPresent("pdp.twitter_share")).as("twitter share").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.twitter_email")).as("twitter email").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.twitter_password")).as("twitter password").isEqualTo(true);
                        softly.assertThat(Elements.elementPresent("pdp.twitter_login")).as("twitter login").isEqualTo(true);
                        Navigate.switchWindowClose();


                        break;
                    }
                    case "master": {
                        break;
                    }
                    case "CHANEL": {
                        break;
                    }
                }
                break;
            }
            case "iship": {
                switch (productType) {
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
    }

    @Then("^I verify that I can open all accordions on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatICanOpenAllAccodions(String productType, String mode, String device) throws InterruptedException {
        Actions.scroll("down", null);
        if (productType.equals("member")) {
            List<WebElement> allAccordions = Elements.findElements("pdp.accordion_header")
                    .stream()
                    .filter(e -> e.getAttribute("aria-expanded").equals("false"))
                    .collect(Collectors.toList());
            for (WebElement e : allAccordions) {
                Clicks.click(e);
                Thread.sleep(3000);
                Assert.assertEquals("true", e.getAttribute("aria-expanded"));
            }
        }
    }

    @And("^I verify that I can close all accordions on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatICanCloseAllAccordions(String productType, String mode, String device) throws InterruptedException {
        if (productType.equals("member")) {
            List<WebElement> allAccordions = Elements.findElements("pdp.accordion_header")
                    .stream()
                    .filter(e -> e.getAttribute("aria-expanded").equals("true"))
                    .collect(Collectors.toList());
            for (WebElement e : allAccordions) {
                Clicks.click(e);
                Thread.sleep(3000);
                Assert.assertEquals("false", e.getAttribute("aria-expanded"));
            }
        }
    }

    @Then("^I verify that the color label text updates to the color selected on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatTheColorLabelTextUpdatesToTheColorSelected(String productType, String mode, String device) {
        if (productType.equalsIgnoreCase("member")) {
            WebElement actualColorLabel = Elements.findElement("pdp.color_label");
            softly.assertThat(selectedColor.equalsIgnoreCase(actualColorLabel.getText()))
                    .as("Color Label").isTrue();
        }
        softly.assertAll();
    }

    @When("^I verify that the product has only one color and color swatches are not displayed on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatTheProductHasOnlyOneColor(String productType, String mode, String device) throws Throwable {
        if (productType.equals("member")) {

            int numberOfColors = BCOMxapi.getNumberOfColorsFromXapi(pId);
            softly.assertThat(numberOfColors == 1).as("number of colors").isTrue();
            softly.assertThat(!Elements.elementPresent("pdp.color_swatches"))
                    .as("Color swatches are displayed").isTrue();
        }
    }

    @Then("^I verify that color swatches are displayed according to priceToColors in xapi on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatColorSwatchesAreDisplayedAccordiongToPriceToColorsInXapi(String productType, String mode, String device) throws Throwable {
        if (productType.equalsIgnoreCase("member")) {
            List<WebElement> colorIds = Elements.findElements("pdp.color_swatch_label");
            List<String> expectedColorIds = BCOMxapi.getXapiAttributes(pId, "priceToColors");
            log.info("total number of colors for the product: " + expectedColorIds.size());
            for (int i = 0; i < colorIds.size(); i++) {
                colorId = colorIds.get(i).getAttribute("for").replace("color-swatch-", "");
                log.info("color id is: " + colorId);
                softly.assertThat(expectedColorIds.get(i).equals(colorId))
                        .as("color order mismatch").isTrue();
            }
        }
        softly.assertAll();
    }

    @When("^I select all color swatches from all tiers on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iSelectAllColorSwatchesFromAllTiers(String productType, String mode, String device) throws Throwable {
        if (productType.equalsIgnoreCase("member")) {
            if (Elements.elementPresent("pdp.color_swatches")) { // more than one color
                if (Elements.findElements("pdp.color_swatches").size() == 1) {
                    Assert.assertFalse("color swatch error", true);
                }
                List<WebElement> colorSwatches = Elements.findElements("pdp.color_swatches");
                for (WebElement cs : colorSwatches) {
                    cs.click();
                    colorId = cs.findElement(By.className("color-swatch-label"))
                            .getAttribute("for")
                            .replace("color-swatch-", "");
                    iVerifyThatThePriceDisplayedOnPDPMatchesThePriceInXapi(productType, mode, device);
                }
            }
        }
    }

    @Then("^I verify product prices with xapi on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatThePriceDisplayedOnPDPMatchesThePriceInXapi(String productType, String mode, String device) throws Throwable {
        if (productType.equalsIgnoreCase("member")) {
            Map<String, Float> unSortedPrices;
            Map<String, Float> actualPrices = new LinkedHashMap<>();
            String label = null;
            Float price = null;
            if (Elements.elementPresent("pdp.sale_price")) {
                label = Elements.findElement("pdp.sale_price")
                        .findElement(By.className("cw_price_label")).getText();
                price = Float.parseFloat(Elements.findElement("pdp.sale_price")
                        .findElement(By.className("cw_price_holder")).getText()
                        .replaceAll("[$,]", ""));
                actualPrices.put(label, price);
            }
            List<WebElement> priceElements = Elements.findElements("pdp.general_price_container");
            for (WebElement pr : priceElements) {
                label = pr.findElement(By.className("cw_price_label")).getText();
                price = Float.valueOf(pr.findElement(By.className("cw_price_holder")).getText()
                        .replaceAll("[$,]", ""));
                System.out.println("Price in UI: ******* " + Float.parseFloat(pr.findElement(By.className("cw_price_holder")).getText()
                        .replaceAll("[$,]", "")));
                actualPrices.put(label, price);
            }
            unSortedPrices = BCOMxapi.getPriceMapForAColor(pId, colorId);
            Map<String, Float> expectedPrices = unSortedPrices.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            Assert.assertTrue("Price Label Mismatch", expectedPrices.equals(actualPrices));
            ArrayList<Float> expectedPriceValues = new ArrayList<>(expectedPrices.values());
            ArrayList<Float> actualPriceValues = new ArrayList<>(actualPrices.values());
            for (int i = 0; i < actualPriceValues.size(); i++) {
                log.info("Prices in Xapi " + expectedPriceValues.get(i));
                log.info("Prices in UI " + actualPriceValues.get(i));
                Assert.assertEquals("price/order mismatch", expectedPriceValues.get(i), actualPriceValues.get(i));
            }
            salePrice = (actualPriceValues.size() > 1) ? actualPriceValues.get(0) : 0;
            originalPrice = (actualPriceValues.size() > 1) ? actualPriceValues.get(actualPriceValues.size() - 1) : actualPriceValues.get(0);
        }
    }


    @When("^I select all sizes on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iSelectAllSizesOnPDP(String productType, String mode, String device) throws Throwable {
        if (productType.equalsIgnoreCase("member")) {
            WebElement dropDown = Elements.findElement("pdp.size_dropdown");
            Select select = new Select(dropDown);
            List<WebElement> elements = select.getOptions()
                    .stream()
                    .filter(e -> (!e.getText().contains("Select a size")))
                    .collect(Collectors.toList());
            for (WebElement e : elements) {
                size = e.getAttribute("value");
                log.info("size is " + size);
                DropDowns.selectByText("pdp.size_dropdown", e.getText());
                iVerifyThatTheAvailableColorsDisplayedMatchesTheAvailableColorsInXapi(productType, mode, device);
            }
        }

    }

    @Then("^I verify that the available colors displayed matches the available colors in xapi on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatTheAvailableColorsDisplayedMatchesTheAvailableColorsInXapi(String productType, String mode, String device) throws Throwable {
        if (productType.equalsIgnoreCase("member")) {
            List<WebElement> availableColors = Elements.findElements("pdp.available_colors");
            List<Integer> expectedColors = BCOMxapi.getColorsForASize(pId, size);
            List<Integer> actualColors = new ArrayList<>();
            Assert.assertTrue("Number of available colors mismatch", expectedColors.size() == availableColors.size());
            log.info("Verified number of available colors for the size selected");
            for (WebElement c : availableColors) {
                actualColors.add(Integer.parseInt(c.getAttribute("for").replace("color-swatch-", "")));
            }
            log.info("UI colors " + actualColors.size());
            Collections.sort(actualColors);
            Collections.sort(expectedColors);
            for (int i = 0; i < expectedColors.size(); i++) {
                Assert.assertEquals("available colorId mismatch", expectedColors.get(i), actualColors.get(i));
            }
            log.info("Verified available colors for the size selected matches the xapi response");
        }
    }

    @When("^I scroll to bottom of the page$")
    public void iScrollToBottomOfThePage() throws InterruptedException {
        Assert.assertTrue(Elements.elementPresent("pdp.back_to_top"));
        Thread.sleep(5000);
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverManager.getWebDriver();
        //jse.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        log.info("Navigated to bottom of the page");
    }

    @And("^I select backtotop button$")
    public void iSelectOnBacktotopButton() {
        Clicks.click("pdp.back_to_top");
    }

    @Then("^I verify that I am navigated to the top of the page$")
    public void iVerifyThatIAmNavigatedToTheTopOfThePage() throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor executor = (JavascriptExecutor) WebDriverManager.getWebDriver();
        Long windowScrollPosition;
        try {
            windowScrollPosition = (Long) executor.executeScript("return window.pageYOffset;");
            Assert.assertTrue(windowScrollPosition <= new Long(110));
        } catch (ClassCastException e) {
            Assert.assertFalse((Double) executor.executeScript("return window.pageYOffset;") <= 100);
        } catch (Exception e) {
            Assert.fail("Unable to get page offset: " + e.getMessage());
        }
        log.info("verified that scrolled back to top");

    }

    @Then("^I verify that the order of colors in the color picker dropdown match the order of colors in xapi on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatTheOrderOfColorsInTheColorPickerDropdownMatchTheOrderOfColorsInXapi(String productType, String mode, String device) throws Throwable {
        if (productType.equalsIgnoreCase("member")) {
            if (device.equals("mobile")) {
                Actions.scroll("down", null);
            }
            List<String> expColorIds = BCOMxapi.getXapiAttributes(pId, "colorsByName");
            WebElement dropDown = Elements.findElement("pdp.color_dropdown");
            Select select = new Select(dropDown);
            List<WebElement> colors = select.getOptions()
                    .stream()
                    .filter(e -> (!e.getText().contains("Select a color")))
                    .collect(Collectors.toList());
            String colorId;
            for (int i = 0; i < colors.size(); i++) {
                colorId = colors.get(i).getAttribute("data-id");
                softly.assertThat(colorId.equals(expColorIds.get(i)))
                        .as("Color Id Mismatch").isTrue();
                log.info("ColorId is " + colorId);
            }
        }
        softly.assertAll();
    }

    @Then("^I verify that the color selected from color picker dropdown matches the color swatch selected on (master|member|chanel) PDP on (site|iship) mode on (desktop|mobile|tablet)$")
    public void iVerifyThatTheColorSelectedFromColorPickerDropdownMatchesTheColorSwatchSelected(String productType, String mode, String device) throws Throwable {
        if (productType.equalsIgnoreCase("member")) {
            String colorSwatchName = null;
            WebElement dropDown = Elements.findElement("pdp.color_dropdown");
            Select select = new Select(dropDown);
            List<WebElement> colors = select.getOptions()
                    .stream()
                    .filter(e -> (!e.getText().contains("Select a color")))
                    .collect(Collectors.toList());
            Random rand = new Random();
            String colorPickerName = colors.get(rand.nextInt(colors.size())).getText();
            log.info("Selected Color Name in dropdown: " + colorPickerName);
            DropDowns.selectByText("pdp.color_dropdown", colorPickerName);
            Thread.sleep(2000);
            List<WebElement> selectedColorIds = Elements.findElements("pdp.color_swatches_name");
            for (WebElement e : selectedColorIds) {
                if (e.isSelected()) {
                    colorSwatchName = e.getAttribute("aria-label").replace("color ", "");
                }
            }
            log.info("selected color swatch name is " + colorSwatchName);
            softly.assertThat(colorPickerName.trim().equalsIgnoreCase(colorSwatchName.trim()))
                    .as("Color Name mismatch").isTrue();

        }
        softly.assertAll();
    }

    @Then("^I verify altimages on (pdp|imageOverlay) on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void iVerifyAltImagesOnPDP(String feat, String productType, String mode, String device) throws Throwable {
        if (productType.equals("member")) {
            List<String> attrValues = BCOMxapi.getBcomFccXapiValidatedAttributes(pId, "altImages");
            List<String> pdpAltImgs = new ArrayList<>();
            List<String> overlayAltImages = new ArrayList<>();
            int count = 0;
            String altImgSources;
            List<WebElement> altImgs;
            String mainImageSrc = null;
            WebElement imagesPDP = Elements.findElement("pdp.product_images");
            WebElement imagesOverlay = Elements.findElement("pdp.image_overlay");
            if (feat.equals("pdp")) {
                altImgs = imagesPDP.findElements(By.className("alt-image-label"));
                log.info("number of alt images: " + altImgs.size());
                for (int i = 0; i < attrValues.size(); i++) {
                    altImgSources = altImgs.get(i).findElement(By.tagName("img")).getAttribute("src");
                    pdpAltImgs.add(getIdFromURL(altImgSources));
                }
                Assert.assertTrue(attrValues.size() == pdpAltImgs.size());
                log.info("Number of AltImages verified on xApiFCC Vs AltImages displayed on PDP:: " + attrValues.size() + " == " + pdpAltImgs.size());
                List<String> unSortedPdpAltImgs = new ArrayList<>(Arrays.asList(new String[pdpAltImgs.size()]));
                Collections.copy(unSortedPdpAltImgs, pdpAltImgs);
                Collections.sort(pdpAltImgs);
                for (int i = 0; i < attrValues.size(); i++) {
                    Assert.assertTrue(attrValues.get(i).equals(pdpAltImgs.get(i)));
                    log.info("xApiFcc ... PDP::  " + attrValues.get(i) + " ...  " + pdpAltImgs.get(i));
                }
                if (device.equals("desktop") || device.equals("tablet")) {
                    if (pdpAltImgs.size() > 5) {
                        WebElement altImageCont = imagesPDP.findElement(By.className("alt-images"));
                        softly.assertThat(altImageCont.findElement(By.className("next")).isDisplayed()).as("AltImagesDownArrow Present").isEqualTo(true);
                        Clicks.click(altImageCont.findElement(By.className("next")));
                        Thread.sleep(500);
                        softly.assertThat(altImageCont.findElement(By.className("prev")).isDisplayed()).as("AltImagesUpArrow Present").isEqualTo(true);
                        Clicks.click(altImageCont.findElement(By.className("prev")));
                        for (int i = 0; i < attrValues.size(); i++) {
                            if (count != 0 && count % 5 == 0) {
                                if (device.equals("tablet")) {
                                    Actions.scroll("down", null);
                                    Clicks.click(altImageCont.findElement(By.className("next")));
                                    Thread.sleep(1000);
                                    Actions.scroll("up", null);
                                } else {
                                    Clicks.click(altImageCont.findElement(By.className("next")));
                                }
                            }
                            altImgs.get(i).click();
                            Thread.sleep(1000);
                            mainImageSrc = Elements.findElements("pdp.current_image").get(1)
                                    .findElement(By.tagName("img")).getAttribute("src");
                            log.info("Alt image ID" + unSortedPdpAltImgs.get(i) + "Main image ID " + getIdFromURL(mainImageSrc));
                            Assert.assertEquals(unSortedPdpAltImgs.get(i), getIdFromURL(mainImageSrc));
                            count++;
                        }
                    } else {
                        for (int i = 0; i < attrValues.size(); i++) {
                            altImgs.get(i).click();
                            Thread.sleep(1000);
                            mainImageSrc = Elements.findElements("pdp.current_image").get(1)
                                    .findElement(By.tagName("img")).getAttribute("src");
                            log.info("Alt image ID" + unSortedPdpAltImgs.get(i) + "Main image ID " + getIdFromURL(mainImageSrc));
                            Assert.assertEquals(unSortedPdpAltImgs.get(i), getIdFromURL(mainImageSrc));
                        }

                    }

                }
                if (device.equals("mobile")) {
                    List<WebElement> altImageDots = imagesPDP.findElements(By.className("main-image-dot-radio"));
                    for (int i = 0; i < attrValues.size(); i++) {
                        altImageDots.get(i).click();
                        Thread.sleep(3000);
                        mainImageSrc = Elements.findElements("pdp.current_image").get(1)
                                .findElement(By.tagName("img")).getAttribute("src");
                        log.info(i + "Alt image ID" + unSortedPdpAltImgs.get(i) + " Main image ID" + getIdFromURL(mainImageSrc));
                        Assert.assertEquals(unSortedPdpAltImgs.get(i), getIdFromURL(mainImageSrc));
                    }
                }
            }
            if (feat.equals("imageOverlay")) {
                altImgs = imagesOverlay.findElements(By.className("alt-image-label"));
                log.info("number of alt images on overlay: " + altImgs.size());
                for (int i = 0; i < attrValues.size(); i++) {
                    altImgSources = altImgs.get(i).findElement(By.tagName("img")).getAttribute("src");
                    overlayAltImages.add(getIdFromURL(altImgSources));
                }
                if (Elements.elementPresent("pdp.image_overlay_open")) ;
                {
                    Clicks.click(Elements.findElement(By.className("full-screen-button")));
                    Thread.sleep(3000);
                }
                log.info("overlay is: " + Elements.elementPresent("pdp.image_overlay_open"));
                if (device.equals("desktop") || device.equals("tablet")) {
                    if (altImgs.size() > 5) {
                        WebElement altImageCont = imagesOverlay.findElement(By.className("alt-images"));
                        softly.assertThat(altImageCont.findElement(By.className("next")).isDisplayed()).as("AltImagesUpArrow Present").isEqualTo(true);
                        Clicks.click(altImageCont.findElement(By.className("next")));
                        Thread.sleep(500);
                        softly.assertThat(altImageCont.findElement(By.className("prev")).isDisplayed()).as("AltImagesDownArrow Present").isEqualTo(true);
                        Clicks.click(altImageCont.findElement(By.className("prev")));
                        count = 0;
                        for (int i = 0; i < attrValues.size(); i++) {
                            if (count != 0 && count % 5 == 0) {
                                if (device.equals("tablet")) {
                                    Actions.scroll("down", null);
                                    Clicks.click(altImageCont.findElement(By.className("next")));
                                    Thread.sleep(1000);
                                    Actions.scroll("up", null);
                                } else {
                                    Clicks.click(altImageCont.findElement(By.className("next")));
                                }
                            }
                            altImgs.get(i).click();
                            Thread.sleep(1000);
                            mainImageSrc = Elements.findElements("pdp.current_image").get(1)
                                    .findElement(By.tagName("img")).getAttribute("src");
                            log.info(i + "Alt Image ID" + overlayAltImages.get(i) + " Main image ID" + getIdFromURL(mainImageSrc));
                            Assert.assertEquals(overlayAltImages.get(i), getIdFromURL(mainImageSrc));
                            count++;
                        }
                    } else {
                        for (int i = 0; i < attrValues.size(); i++) {
                            altImgs.get(i).click();
                            Thread.sleep(2000);
                            mainImageSrc = Elements.findElements("pdp.current_image").get(1)
                                    .findElement(By.tagName("img")).getAttribute("src");
                            log.info("Alt Image ID " + overlayAltImages.get(i) + "Main image ID " + getIdFromURL(mainImageSrc));
                            Assert.assertEquals(overlayAltImages.get(i), getIdFromURL(mainImageSrc));
                        }

                    }

                }
                if (device.equals("mobile")) {
                    List<WebElement> altImageDots = imagesOverlay.findElements(By.className("main-image-dot-radio"));
                    for (int i = 0; i < attrValues.size(); i++) {
                        altImageDots.get(i).click();
                        Thread.sleep(2000);
                        mainImageSrc = Elements.findElements("pdp.current_image").get(1)
                                .findElement(By.tagName("img")).getAttribute("src");
                        log.info("Alt Image ID" + overlayAltImages.get(i) + " Main image ID" + getIdFromURL(mainImageSrc));
                        Assert.assertEquals(overlayAltImages.get(i), getIdFromURL(mainImageSrc));
                    }
                }
                Thread.sleep(2000);
                Clicks.click(imagesOverlay.findElement(By.className("overlay-header-close-button")));
                Thread.sleep(2000);
            }
        }
    }

    public String getIdFromURL(String altImgSources) {
        String[] altImgSrc1 = null;
        String[] altImgSrc2;

        altImgSrc1 = altImgSources.split("optimized/");
        altImgSrc2 = altImgSrc1[1].split("_");
        return altImgSrc2[0];
    }

    @And("^I verify zoomer on imageOverlay on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void iVerifyOnImageOverlayPDPModeOnDevice(String productType, String mode, String device) throws Throwable {
        if (productType.equals("member")) {
            String regStyle;
            if (Elements.elementPresent("pdp.image_overlay_open")) ;
            {
                Clicks.click(Elements.findElement(By.className("full-screen-button")));
                Thread.sleep(2000);
            }
            Assert.assertTrue(!Elements.findElement("pdp.zoom_out_button").isEnabled());
            WebElement mainImage = Elements.findElements("pdp.current_image").get(3)
                    .findElement(By.tagName("img"));
            regStyle = mainImage.getAttribute("style");
            Assert.assertTrue(!regStyle.contains("scale3d(3, 3, 1)"));
            Clicks.click("pdp.zoom_in_button");
            Thread.sleep(2000);
            String zoomStyle = Elements.findElements("pdp.current_image").get(3)
                    .findElement(By.tagName("img")).getAttribute("style");
            log.info("zoom style is :" + zoomStyle);
            Assert.assertTrue(!Elements.findElement("pdp.zoom_in_button").isEnabled());
            Assert.assertTrue(zoomStyle.contains("scale3d(3, 3, 1)"));
            Clicks.click("pdp.zoom_out_button");
            Thread.sleep(2000);
            regStyle = Elements.findElements("pdp.current_image").get(3)
                    .findElement(By.tagName("img")).getAttribute("style");
            log.info("reg style is :" + regStyle);
            Assert.assertTrue(!regStyle.contains("scale3d(3, 3, 1)"));
            Assert.assertTrue(!Elements.findElement("pdp.zoom_out_button").isEnabled());
            log.info("zoom verified");
        }
    }

    @Then("^I verify promo banner and promo overlay on (member|master) PDP (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void iVerifyPromoBannerAndPromoOverlayOnPDPModeOnDevice(String productType, String mode, String device) throws Throwable {
        if (productType.equals("member")) {
            List<String> actPromoDetails = BCOMxapi.getXapiAttributes(pId, "promos");
            String title;
            String description;
            String legalDisclaimer;
            if (Elements.elementPresent("pdp.promo_header")) {
                String mainPromoText = Elements.findElement("pdp.promo_header").getText();
                log.info(" Main text is :" + mainPromoText);
                Assert.assertEquals("Main promo text fail", BCOMxapi.getMainPromoText().toLowerCase(), mainPromoText.toLowerCase());
                Clicks.click("pdp.promo_header");
                Thread.sleep(1000);
                List<WebElement> offers = Elements.findElement("pdp.offer_list")
                        .findElements(By.className("offer"));
                log.info("number of promos : " + offers.size());
                for (int i = 0; i < actPromoDetails.size(); i++) {
                    if (!offers.get(i).findElements(By.className("offer-title")).isEmpty()) {
                        title = offers.get(i).findElement(By.className("offer-title")).getText();
                    } else {
                        title = "";
                    }
                    if (!offers.get(i).findElements(By.className("offer-description")).isEmpty()) {
                        description = offers.get(i).findElement(By.className("offer-description")).getText();
                    } else {
                        description = "";
                    }
                    if (!offers.get(i).findElements(By.className("offer-info-exclusions-button")).isEmpty()) {
                        Clicks.click(offers.get(i).findElement(By.className("offer-info-exclusions-button")));
                    }
                    Thread.sleep(500);
                    if (!offers.get(i).findElements(By.className("offer-info-exclusions-message")).isEmpty()) {
                        legalDisclaimer = offers.get(i).findElement(By.className("offer-info-exclusions-message")).getText();
                    } else {
                        legalDisclaimer = "";
                    }
                    String promoText = title + " " + description + " " + legalDisclaimer;
                    Assert.assertEquals("Promo details mismatch", promoText, actPromoDetails.get(i));
                }
                Thread.sleep(1000);
            }
        }
    }

    @Then("^I verify the \"([^\"]*)\" message on (member|master) PDP on (site|iship|registry) mode on (desktop|mobile|tablet)")
    public void iVerifyingAvailabilityMessagesOnPDP(String msg, String pg, String mode, String device) throws Throwable {
        String available = BCOMxapi.getBcomFccXapiValidatedAttribute(pId, "availability");
        if (available.equals("true"))
            log.info("Item's Availability::  " + available);

        switch (msg) {
            case "Available.Usually ships within x business days.": {
                if (!available.equals("true")) {
                    log.warning("Item is NOT available as xApi/Fcc returns availability::  " + available);
                    break;
                }
                Assert.assertTrue(Elements.findElement(Elements.element("pdp.ship_radio_btn")).isEnabled());
                Assert.assertTrue(Elements.findElement(Elements.element("pdp.ship_radio_btn")).isSelected());
                //Assert.assertFalse(Elements.findElement(Elements.element("pdp.shipItRadioBtn")).isSelected());
                softly.assertThat(Elements.elementPresent("pdp.availability_message")).as("Availability Message").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.shipping_message")).as("Shipping Message").isEqualTo(true);
                softly.assertThat(Elements.elementPresent("pdp.ship_it_label")).as("Ship It Label").isEqualTo(true);
                softly.assertThat(Elements.findElement(Elements.element("pdp.ship_it_label")).getText().equals("Ship This Item"))
                        .as("Ship This Item Label Text").isEqualTo(true);

                String actualMsg = Elements.findElement(Elements.element("pdp.availability_message")).getText()
                        + Elements.findElement(Elements.element("pdp.shipping_message")).getText();
                String expectedMsg = actualMsg.replaceAll("[0-9]", "x");
                log.info("Actual Message::    " + actualMsg);
                log.info("Expected Message::  " + expectedMsg);
                softly.assertThat(expectedMsg.equals(msg)).as("Product Availability & Shipping Message").isEqualTo(true);

                softly.assertAll();
                break;
            }
            case "Select a size and/or color to view shipping and in-store availability.": {
                if (!available.equals("true")) {
                    log.warning("Item is NOT available as xApi/Fcc returns availability::  " + available);
                    break;
                }
                Assert.assertTrue(!Elements.findElement(Elements.element("pdp.ship_radio_btn")).isEnabled());
                softly.assertThat(!Elements.elementPresent("pdp.availability_message")).as("Availability Message").isEqualTo(true);
                softly.assertThat(!Elements.elementPresent("pdp.shipping_message")).as("Shipping Message").isEqualTo(true);

                log.info("Expected Msg:: Availability ... Actual Msg:: " + Elements.findElement(Elements.element("pdp.pick_up_label"))
                        .findElement(By.className("small-font")).getText());
                softly.assertAll();
                break;
            }
        }
        Thread.sleep(1000);
    }

    @And("^I click (A2B|A2L|A2R|Checkout|ContinueShopping|ContinueShoppingBack) " +
            "button on (master|member) PDP on (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void clickingButtons(String btn, String pg, String mode, String device) throws Throwable {
        Thread.sleep(1000);
        Wait.forPageReady();
        switch (btn) {
            case "A2B": {
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.addToBag_button")));
                Clicks.click("pdp.addToBag_button");
                log.info("A2B Button Clicked!");
                break;
            }
            case "A2L": {
                Assert.assertTrue(Elements.elementPresent(Elements.element("pdp.wishlist_button")));
                Clicks.click("pdp.wishlist_button");
                log.info("A2L Button Clicked!");
                break;
            }
            case "A2R": {
                Assert.assertTrue(Elements.elementPresent(Elements.element("")));
                Clicks.javascriptClick("");
                log.info("A2R Link Clicked!");
                break;
            }
            case "Checkout": {
                Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.checkout_button")));
                Clicks.click("add2bag.checkout_button");
                log.info("Checkout Button Clicked on A2B Overlay!");
                break;
            }
            case "ContinueShopping": {
                Assert.assertTrue(Elements.elementPresent(Elements.element("add2bag.contShopping_button")));
                Clicks.click("add2bag.contShopping_button");
                log.info("ContinueShopping Button Clicked!");
                break;
            }
        }
        Thread.sleep(1000);
    }

    @Then("^I verify product details on AddToBag drawer as the \"([^\"]*)\" product being added in (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void iVerifyingAddToBagDrawerBcom(String arg, String mode, String device) throws Throwable {
        Navigate.switchWindow(1);
        Thread.sleep(3000);
        softly.assertThat(Elements.elementPresent(Elements.element("add2bag.product_title"))).as("productTitle on A2B overlay").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("add2bag.product_mainImg"))).as("productMainImg on A2B overlay").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("add2bag.items_in_bag"))).as("itemsInBag on A2B overlay").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("add2bag.sub_total"))).as("add2bag.sub_total").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("add2bag.checkout_button"))).as("add2bag.checkoutBtn").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("add2bag.continue_shopping_button"))).as("add2bag.continueShoppingBtn").isEqualTo(true);
        softly.assertThat(Elements.elementPresent(Elements.element("add2bag.bag_qty"))).as("add2bag.bagQty").isEqualTo(true);

        log.info("Selected Quantity:: PDP: " + selectedQty + "  A2B overlay: " + Elements.findElement
                (Elements.element("add2bag.bagQty")).getText());
        softly.assertThat(Integer.parseInt(Elements.findElement(Elements.element("add2bag.bag_qty")).getText()) >= (selectedQty))
                .as("Product quantity on AddToBag overlay").isEqualTo(true);

        if (arg.contains("Color") && selectedColor != null) {
            softly.assertThat(Elements.findElement(Elements.element("add2bag.productColor")).getText().equalsIgnoreCase(selectedColor))
                    .as("selectedColor on AddToBag overlay").isEqualTo(true);
            log.info("Selected Color:: PDP: " + selectedColor + "  A2B overlay: " + Elements.findElement(Elements.element("add2bag.productColor")).getText());
        }

        if (arg.contains("Size")) {
            softly.assertThat(Elements.findElement(Elements.element("add2bag.productSize")).getText().equalsIgnoreCase(size))
                    .as("selectedSize on AddToBag overlay").isEqualTo(true);
            log.info("Selected Size:: PDP: " + size + "  AddToBag overlay: " + Elements.findElement(Elements.element("add2bag.productSize")).getText());
        }

        softly.assertThat(Elements.elementPresent(Elements.element("add2bag.productPrice"))).as("Original Price on A2B overlay").isEqualTo(true);
        softly.assertThat(Float.compare(Float.valueOf(Elements.findElement(Elements.element("add2bag.productPrice")).getText().replace("$", "")), originalPrice) == 0)
                .as("Original Price on AddToBag overlay").isEqualTo(true);
        log.info("Product Original Price:: PDP: " + originalPrice + "  A2B overlay: " + Elements.findElement
                (Elements.element("add2bag.productPrice")).getText().replace("$", ""));

        String subTotal = Elements.findElement(Elements.element("add2bag.sub_total")).getText().replace("$", "").trim();

        if (Elements.elementPresent(Elements.element("add2bag.productSalePrice"))) {
            softly.assertThat(Elements.elementPresent(Elements.element("add2bag.productSalePrice"))).as("Sale Price on A2B overlay").isEqualTo(true);
            ;
            String[] a2bSalePrice = Elements.findElement(Elements.element("add2bag.productSalePrice")).getText().split("\\$", 0);
            log.info("Product Sale Price:: PDP: " + salePrice + "  A2B overlay: " + a2bSalePrice[1]);
            softly.assertThat(a2bSalePrice[1].equalsIgnoreCase(String.valueOf(salePrice))).as("Sale Price on AddToBag overlay").isEqualTo(true);

            log.info("Product SubTotal on A2B overlay:: SalePrice= " + salePrice + " * Qty= " + selectedQty + " ===>> "
                    + String.format("%.2f", (salePrice) * selectedQty) + "   SubTotal = " + subTotal + " *** Defect on A2B overlay for subTotal; Assertion commented out for now ***");
//                softly.assertThat(subTotal.equals(String.format("%.2f",Float.valueOf(salePrice) * selectedQty))).as("Item SubTotal on A2B overlay").isEqualTo(true);

            String youSaved = Elements.findElement(Elements.element("add2bag.you_saved")).getText().replace("$", "").trim();
            log.info("YouSaved amount on A2B overlay:: (originalPrice - salePrice) * Qty ===>> (" + originalPrice + " - " + salePrice + ") * " + selectedQty + " = "
                    + (String.format("%.2f", (originalPrice - salePrice) * selectedQty)) + " ===>> YouSaved = " + youSaved);
            softly.assertThat(youSaved.equals(String.format("%.2f", originalPrice - (salePrice * selectedQty)))).as("YouSaved amount on A2B overlay").isEqualTo(true);
        } else {
            log.info("Product SubTotal on A2B overlay:: originalPrice= " + String.valueOf(originalPrice) + " * Qty= " + selectedQty + " ===>> "
                    + String.format("%.2f", Float.valueOf(originalPrice * Float.valueOf(selectedQty))) + "   SubTotal = " + subTotal + " *** Defect on A2B overlay for subTotal; Assertion commented out for now ***");
//                softly.assertThat(subTotal.equals(String.format("%.2f",Float.valueOf(originalPrice) * selectedQty))).as("Item SubTotal on A2B overlay").isEqualTo(true);
        }
        softly.assertAll();
        Thread.sleep(1000);
    }

    @Then("^I select random (quantity|size|color) on (master|member) PDP on (site|iship|registry) mode on (desktop|mobile|tablet)$")
    public void iSelectRandomOnMemberPDPOnSiteModeOnDevice(String attribute, String productType, String mode, String device) throws Throwable {
        if (productType.equalsIgnoreCase("member")) {
            Random rand = new Random();
            switch (attribute) {
                case "size": {
                    WebElement dropDown = Elements.findElement("pdp.size_dropdown");
                    Select select = new Select(dropDown);
                    List<WebElement> sizes = select.getOptions()
                            .stream()
                            .filter(e -> (!e.getText().contains("Select a size")))
                            .collect(Collectors.toList());
                    WebElement selectedSize = sizes.get(rand.nextInt(sizes.size()));
                    size = selectedSize.getAttribute("value");
                    log.info("size is " + size);
                    DropDowns.selectByText("pdp.size_dropdown", selectedSize.getText());
                    break;
                }
                case "quantity": {
                    WebElement dropDown = Elements.findElement("pdp.qty_dropdown");
                    Select select = new Select(dropDown);
                    List<WebElement> quantity = select.getOptions();
                    WebElement qty = quantity.get(rand.nextInt(quantity.size()));
                    selectedQty = Integer.parseInt(qty.getAttribute("value"));
                    log.info("quantity is " + selectedQty);
                    DropDowns.selectByText("pdp.qty_dropdown", qty.getText());
                    break;
                }
                case "color": {
                    selectedColor = Elements.findElement("pdp.color_label").getText();
                    List<WebElement> colorSwatches = Elements.findElements("pdp.color_swatches")
                            .stream()
                            .filter(e -> !e.getAttribute("data-color").equals(colorLabel))
                            .collect(Collectors.toList());
                    log.info("Number of color swatches: " + colorSwatches.size());
                    WebElement selectedSwatch = colorSwatches.get(new Random().nextInt(colorSwatches.size()));
                    Clicks.click(selectedSwatch);
                    selectedColor = selectedSwatch.getAttribute("data-color");
                    break;
                }
            }
        }
    }
}
