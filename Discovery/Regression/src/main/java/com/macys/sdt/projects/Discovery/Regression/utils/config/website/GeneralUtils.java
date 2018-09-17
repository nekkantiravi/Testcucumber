package com.macys.sdt.projects.Discovery.Regression.utils.config.website;

import com.google.gson.Gson;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.StepUtils.isErrorPaneVisible;
import static com.macys.sdt.framework.utils.StepUtils.macys;

public class GeneralUtils {

    private static final Logger log = LoggerFactory.getLogger(GeneralUtils.class);

    public static String getHeaderResponse(String appHost, String poolName) throws IOException {
        //Method to get the response
        URL ServiceURL = new URL("http://" + appHost + ":85/api/content/v2/globalpools/" + poolName + "?_expand=media");
        log.info("Service URL:" + ServiceURL);
        HttpURLConnection connection = (HttpURLConnection) ServiceURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("X-Macys-ClientId", "NavApp");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return br.readLine();
    }

    private static String getMSPHost() {
        String platform_vip = EnvironmentDetails.otherApp("f5_vip").ipAddress;
        String SDPPort = "85";
        URIBuilder builderVal = getURI(platform_vip, SDPPort);
        return builderVal.toString();
    }

    private static URIBuilder getURI(String localhost, String wireMockPort) {
        URIBuilder builder = new URIBuilder();
        String httpProtocol = "http";
        builder.setScheme(httpProtocol);
        builder.setHost(localhost);
        builder.setPort(Integer.parseInt(wireMockPort));
        System.out.println("SDP host" + builder);
        return builder;
    }

    public static String getXAPIResponse(String mode) throws IOException {
        String environment = RunConfig.getEnvOrExParam("website");
        environment = environment.contains("https")? environment : environment.replace("http", "https");
        String regionCode = "US";
        String shoppingMode = "SITE";
        if (mode.equals("registry"))
            shoppingMode = "WEDDING_REGISTRY";
        URL ServiceURL = new URL(environment + "/xapi/navigate/v1/header?_regionCode=" + regionCode + "&_shoppingMode=" + shoppingMode);
        HttpURLConnection connection = (HttpURLConnection) ServiceURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return br.readLine();
    }


    public static List<WebElement> getAllSubNavHeaders(String mode) {
        List<WebElement> subNavHeaderLinks = new ArrayList();
        List<WebElement> actualHeaderEle = Elements.findElement("header.link_rail").findElements(By.tagName("a")).stream().filter(f -> f.isDisplayed()).collect(Collectors.toList());
        for (WebElement headerEle : actualHeaderEle) {
            if (!headerEle.getText().equals("")) {
                subNavHeaderLinks.add(headerEle);
            }
        }
        return subNavHeaderLinks;
    }

    public static List<WebElement> getFOBFromRegistryHomePage() {
        List<WebElement> fobList;
        try {
            fobList = Elements.findElement(By.id("globalMastheadCategoryMenu")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        } catch (Exception ee) {
            fobList = Elements.findElement(By.id("mainNavigation")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
        }
        return fobList;
    }

    public static String selectRandomSize(String mode) {
        String size = null;
        if (mode.equalsIgnoreCase("iship") || mode.equalsIgnoreCase("registry")) {
            if (Elements.findElement("product_display.pdp_size") == null) {
                size = "No Size";
            } else {
                int size_count = Elements.findElement("product_display.pdp_size").findElements(By.className("size")).stream().filter(f -> !f.getAttribute("class").contains("disabled")).collect(Collectors.toList()).size();
                if (size_count == 0) {
                    if (Elements.findElement("product_display.size_section").findElement(By.className("swatchName")) != null) {
                        size = Elements.findElement("product_display.size_section").findElement(By.className("swatchName")).getText();
                    } else {
                        size = "No size";
                    }
                } else {
                    int number = (int) (Math.random() * size_count);
                    size = Elements.findElement("product_display.pdp_size").findElements(By.className("size")).stream().filter(f -> !f.getAttribute("class").contains("disabled")).collect(Collectors.toList()).get(number).getText();
                    Clicks.click(Elements.findElement("product_display.pdp_size").findElements(By.className("size")).stream().filter(f -> !f.getAttribute("class").contains("disabled")).collect(Collectors.toList()).get(number));
                }
            }
        } else {
            if (macys()) {
                if (Elements.findElement("product_display.size_section") == null)
                    size = "No size";
                else {
                    int size_count = Elements.findElement("product_display.size_section").findElements(By.className("swatch")).stream().filter(f -> !f.getAttribute("class").contains("disabled")).collect(Collectors.toList()).size();
                    if (size_count == 0) {
                        if (Elements.findElement("product_display.size_section").findElement(By.className("swatchName")) != null) {
                            size = Elements.findElement("product_display.size_section").findElement(By.className("swatchName")).getText();
                        } else {
                            size = "No size";
                        }
                    } else {
                        int number = (int) (Math.random() * size_count);
                        size = Elements.findElement("product_display.size_section").findElements(By.className("swatch")).stream().filter(f -> !f.getAttribute("class").contains("disabled")).collect(Collectors.toList()).get(number).getText();
                        Clicks.click(Elements.findElement("product_display.size_section").findElements(By.className("swatch")).stream().filter(f -> !f.getAttribute("class").contains("disabled")).collect(Collectors.toList()).get(number));
                    }
                }
            } else {
                if (Elements.findElement(By.className("newSizeSwatches")) == null) {
                    size = "No size";
                } else {
                    Elements.findElement(By.className("newSizeSwatches")).click();
                    Random rmd = new Random();
                    List<WebElement> sizeValues = Elements.findElement(By.className("pdpMemberSizes")).findElement(By.tagName("ul")).findElements(By.tagName("li"));
                    sizeValues.get(rmd.nextInt(sizeValues.size())).click();
                }
            }
        }
        return size;
    }

    public static String selectRandomColor(String mode) {
        String color = null;
        int color_count;
        int number;
        if (mode.equalsIgnoreCase("iship") || mode.equalsIgnoreCase("registry")) {
            if (Elements.findElement(By.className("productColor")) == null) {
                color = "No Color";
            } else {
                if (Elements.findElement(By.className("colorSwatch")) == null) {
                    color = Elements.findElement(By.className("productColor")).getText();
                } else {
                    color_count = Elements.findElements(By.className("colorSwatch")).size();
                    number = (int) (Math.random() * color_count);
                    color = Elements.findElements(By.className("colorSwatch")).get(number).getText();
                    Elements.findElements(By.className("colorSwatch")).get(number).click();
                }
            }
        } else {
            if (Elements.findElement("product_display.total_color_swatches") == null) {
                if (Elements.findElement(By.className("selectedColorName")) == null) {
                    color = "No Color";
                } else if (Elements.findElement(By.cssSelector(".colorHeader .swatchName")) == null) {
                    color = Elements.findElement(By.cssSelector(".colorHeader .swatchName")).getText();
                } else {
                    color = Elements.findElement(By.className("selectedColorName")).getText();
                }
            } else {
                color_count = Elements.findElements("product_display.select_default_color").size();
                number = (int) (Math.random() * color_count);
                color = Elements.findElements("product_display.select_default_color").get(number).getAttribute("aria-label");
                Elements.findElements("product_display.select_default_color").get(number).click();
            }
        }
        return color;
    }

    public static void clickAddToBagButtonInPDPPage() {
        try {
            try {
                Clicks.click("product_display.add_to_bag_button");
            } catch (Exception e) {
                System.out.println("'Add To Bag' button is not visible or available");
                e.printStackTrace();
            }
            Wait.untilElementPresent("add_to_bag_dialog.add_to_bag_summary_panel");
            if (!Elements.elementPresent("add_to_bag_dialog.add_to_bag_summary_panel"))
                Clicks.clickIfPresent("product_display.technical_error");
            if (isErrorPaneVisible())
                Clicks.click("home.popup_close");
        } catch (NoSuchElementException e) {
            System.out.println("Add to bag summary panel not visible or page not redirected correctly");
            e.printStackTrace();
        }
    }

    public static List<QuickbagProduct> getAllQuickbagProductDetails() {
        List<QuickbagProduct> productDetails = new ArrayList<>();
        int noOfProducts = Elements.findElements("quick_bag.product_title").size();
        for (int i = 0; i < noOfProducts; i++) {
            QuickbagProduct p = new QuickbagProduct(Integer.parseInt(String.valueOf(Elements.findElements("quick_bag.product_title").size())));
            p.name = Elements.findElements("quick_bag.product_title").get(i).getText().split("\n")[0];
            String price = Elements.findElements("quick_bag.price_details").get(i).getText();
            Elements.findElements(By.className("itemAction")).get(i).findElement(By.tagName("button"));
            if (Elements.findElements(By.className("giftCardEmail")).size() != 0)
                p.giftCardEmail = Elements.findElement(By.className("giftCardEmail")).getText();
            if (price.contains("FREE")) {
                p.gift_retail_price = "FREE";
                p.quantity = 1;
            } else if (price.contains("NOW") || price.contains("SALE")) {
                String qb_retail_price = Elements.findElements("quick_bag.price_details").get(i).findElement(By.className("salePrice")).getText().replaceAll("[a-zA-Z:$ ]", "");
                p.retail_price = Double.parseDouble(qb_retail_price.replaceAll(",", "").replace("$", ""));
            } else {
                String qb_retail_price = price.replaceAll("[a-zA-Z$: ]", "").replaceAll(",", "").replace("$", "");
                p.retail_price = Double.parseDouble(qb_retail_price);
            }
            p.color_size = Elements.findElements("quick_bag.product_title").get(i).getText().split("\n")[1];

            try {
                Clicks.hoverForSelection(By.id("itemInfo"));
                p.product_promoDesc = Elements.findElements("quick_bag.promo_desc").get(i).getText();
                System.out.println(p.product_promoDesc);
            } catch (Exception e) {
                p.product_promoDesc = "null";
            }
            try {
                Clicks.hoverForSelection(By.id("itemInfo"));
                p.badges = Elements.findElements("quick_bag.gift_badge").get(i).getText();
                System.out.println(p.badges);
            } catch (Exception e) {
                p.badges = "null";
            }
            if (price.contains("Qty"))
                p.quantity = Integer.parseInt(price.replace("x Qty. ", ""));
            else
                p.quantity = 1;
            String qb_bagTotal = Elements.findElement("quick_bag.bag_subtotal").getText();
            p.bagTotal = Double.parseDouble(qb_bagTotal.replaceAll("[a-zA-Z$: ]", "").replaceAll(",", "").replace("$", ""));
            productDetails.add(p);
            System.out.println(p);
        }
        return productDetails;
    }

    /**
     * Method to click on random product thumbnail in browse page
     *
     * @param selector
     */
    public static void clickRandomProductThumbnailinSearchResultsPage(String selector) {
        try {
            List<WebElement> thumbnail_container = Elements.findElement(selector).findElements(
                    By.xpath("//ul/li[@class='productThumbnail borderless']//a[@class='productThumbnailLink']"));
            Random r = new Random();
            if (thumbnail_container.size() != 0) {
                int randomValue = r.nextInt(thumbnail_container.size());
                thumbnail_container.get(randomValue).click();
            }
        } catch (Exception e) {
            System.out.println("Unable to click on random product thumbnail");
            e.printStackTrace();
        }
    }

    public static void hoverSelection(WebElement el) {
        try {
            Clicks.hoverForSelection(el);
        } catch (Exception e) {
            System.out.println("Unable to hover over on element:" + el);
            e.printStackTrace();
        }
    }

    public static void moveMousePosition() {
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        rb.mouseMove(100, 100);
    }

    public static List<String> getExpectedMainCategoriesNames(String mode) {
        if (macys()) {

            if (mode.equalsIgnoreCase("registry")) {
                return Arrays.asList("WEDDING REGISTRY", "STARTER IDEAS", "DINING", "KITCHEN", "BED & BATH", "HOME DECOR", "LUGGAGE", "CLEANING & ORGANIZING", "BRANDS", "WEDDING SHOP");
            } else if (mode.equalsIgnoreCase("iship")) {
                return Arrays.asList("WOMEN", "MEN", "HOME", "BED & BATH", "SHOES", "HANDBAGS & ACCESSORIES", "KIDS", "JUNIORS", "JEWELRY", "WATCHES", "BRANDS");
            } else {
                return Arrays.asList("HOME", "BED & BATH", "WOMEN", "MEN", "JUNIORS", "KIDS", "GIFTS", "BEAUTY", "SHOES", "HANDBAGS", "JEWELRY", "WATCHES", "BRANDS");
            }
        } else {
            if (mode.equalsIgnoreCase("registry")) {
                return Arrays.asList("GETTING STARTED", "DESIGNERS", "DINING & ENTERTAINING", "KITCHEN", "HOME DECOR", "BED & BATH", "LUGGAGE", "HOME CARE & TECH", "SALE");
            } else if (mode.equalsIgnoreCase("iship")) {
                return Arrays.asList("WOMEN", "SHOES", "HANDBAGS", "JEWELRY & ACCESSORIES", "MEN", "KIDS", "HOME", "SALE", "DESIGNERS", "WHAT'S NEW", "THE REGISTRY");
            } else {
                return Arrays.asList("WOMEN", "SHOES", "HANDBAGS", "JEWELRY & ACCESSORIES", "BEAUTY", "MEN", "KIDS", "HOME", "SALE", "DESIGNERS", "WHAT'S NEW", "GIFTS", "THE REGISTRY");
            }
        }
    }

    public static int getResponseCode(String href) throws IOException {
        int responseCode = 0;
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(href);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            Assert.fail("Unable to get response for the url: " + href + "\t" + e.getMessage());
        }
        return responseCode;
    }

    public static Map getHeaderMediaResponse(String global_pool) {
        Map serviceResponse = new HashMap<>();
        String globalPoolAPI = "/api/content/v2/globalpools/<media_type>?_expand=media&sdpGrid=primary";
        HttpClient client = new HttpClient();
        String retval = new String();
        HttpConnectionManager manager = client.getHttpConnectionManager();
        manager.getParams().setConnectionTimeout(30 * 1000);
        String value;
        try {
            globalPoolAPI = globalPoolAPI.replace("<media_type>", global_pool);
            value = getMSPHost() + globalPoolAPI;
            GetMethod method = new GetMethod(value);
            method.addRequestHeader("X-Macys-ClientId", "NavApp");
            method.addRequestHeader("accept", "application/json");
            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
            String readLine;
            while (((readLine = br.readLine()) != null)) {
                retval = retval.concat(readLine);
            }
            serviceResponse = new Gson().fromJson(retval, Map.class);
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return serviceResponse;
    }
}
