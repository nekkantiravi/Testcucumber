package com.macys.sdt.projects.Discovery.Regression.actions.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by YH03402 on 6/2/2017.
 */
public class DsvActions extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(DsvActions.class);


    public static void closePopupsifpresent() {
        if (bloomingdales()) {
            Clicks.clickIfPresent("navigation.closePopUp");
            if (Elements.elementPresent("product_display.foresee")) {
                Clicks.clickIfPresent("product_display.foresee_no_thanks");
            }
        }
    }

    /*return true if response code is equal to 200
    accept parameter as String value for the URL under test*/
    public static boolean responseCode(String source) {

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

    //checks response code for Image
    public static int getResponseCode(String href) throws IOException {
        int responseCode = 0;
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(href);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();

            int redirectCounter = 0;
            while (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                url = new URL(connection.getHeaderField("Location"));
                connection = (HttpURLConnection) url.openConnection();
                responseCode = connection.getResponseCode();
                redirectCounter++;
                if (redirectCounter > 5) {
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail("Unable to get response for the url: " + href + "\t" + e.getMessage());
        }
        return responseCode;
    }


    public static void verifySorting(String args) throws InterruptedException {
        Wait.isPageLoaded();
        List<Float> page_list = new LinkedList<>();
        List<WebElement> price_Webelement = new LinkedList<>();
        String delimiter = "\\$";
        int price_index = 1;
        if (macys())
            if (Elements.elementPresent("header_and_footer.header_country_flag")) {
                delimiter = " ";
                price_index = 2;
            } else {
                if (bloomingdales()) {
                    if (!Elements.elementPresent("header_and_footer.goto_guest_my_account")) {
                        delimiter = " ";
                        price_index = 2;
                    }
                }
            }


        if (args.equalsIgnoreCase("Price (high-low)") || args.equalsIgnoreCase("Price: High to Low")) {
            logger.info("High to low sorting verification");
            {
                price_Webelement = Elements.findElements("category_browse.browse_prices");
                int i = 0;
                for (WebElement ele : price_Webelement) {
                    i = page_list.size();
                    float price_fetched = 0;
                    logger.info("Webelement text are ::" + ele.getText() + ":::" + i);
                    if (elementPresent(ele, Elements.getValues("category_browse.price_sale_second_range").get(0))) {
                        WebElement innerEle = ele.findElement(By.xpath(Elements.getValues("category_browse.price_sale_second_range").get(0)));
                        price_fetched = Float.parseFloat(innerEle.getText().split("-")[1].replace(",", ""));
                        page_list.add(price_fetched);
                        logger.info("Sale Price displayed is::" + innerEle.getText() + "::Price Fetched::" + price_fetched + "Index::" + i);
                    } else if (elementPresent(ele, Elements.getValues("category_browse.price_second_range").get(0))) {
                        WebElement innerEle = ele.findElement(By.xpath(Elements.getValues("category_browse.price_second_range").get(0)));
                        if (macys()) {
                            price_fetched = Float.parseFloat(innerEle.getText().split("-")[1].replace(",", ""));
                        } else {
                            logger.info("inner text ::" + ele.getText());
                            if (!ele.getText().contains("SELECT")) {
                                price_fetched = Float.parseFloat(ele.getText().split(" ")[2].split(delimiter)[price_index].replace(",", ""));
                            } else {
                                price_fetched = Float.parseFloat(ele.getText().split("\n")[0].split(" ")[3].split(delimiter)[price_index].replace(",", ""));
                            }
                        }
                        page_list.add(price_fetched);
                        logger.info("Sale Price is::" + innerEle.getText() + "::Price Fetched::" + price_fetched + "Index::" + i);
                    } else if (elementPresent(ele, Elements.getValues("category_browse.price_sale_first_range").get(0))) {
                        WebElement innerEle = ele.findElement(By.xpath(Elements.getValues("category_browse.price_sale_first_range").get(0)));
                        if (macys()) {
                            price_fetched = Float.parseFloat(innerEle.getText().split(delimiter)[price_index].replace(",", ""));
                        } else {
                            logger.info("inner text ::" + innerEle.getText());
                            price_fetched = Float.parseFloat(innerEle.getText().split(" ")[1].split(delimiter)[price_index].replace(",", ""));
                        }
                        page_list.add(price_fetched);
                        logger.info("Sale Price is::" + innerEle.getText() + "::Price Fetched::" + price_fetched + "Index::" + i);
                    } else if (elementPresent(ele, Elements.getValues("category_browse.price_first_range").get(0))) {
                        WebElement innerEle = ele.findElement(By.xpath(Elements.getValues("category_browse.price_first_range").get(0)));
                        price_fetched = Float.parseFloat(innerEle.getText().split(delimiter)[1].replace(",", ""));
                        page_list.add(price_fetched);
                        logger.info("Simple price is::" + innerEle.getText() + "::Price Fetched::" + price_fetched + "Index::" + i);
                    }
                    if (page_list.size() > 5) {
                        break;
                    }
                }
            }
            logger.info("Price lists" + page_list.isEmpty());
            Assert.assertFalse("Fetched price list was empty", page_list.isEmpty());
            for (int index = 0; index < page_list.size() - 1; index++) {
                //  logger.info("Price list displayed for element::"+ i +"on page is::" + page_list.get(i));
                Assert.assertTrue("Price List is not in Descending order for element::" + index + "price::" + page_list.get(index), page_list.get(index) >= page_list.get(index + 1));
            }
            logger.info("List Ordered in Descending order i.e high to low");

        } else if (args.equalsIgnoreCase("Price (low-high)") || args.equalsIgnoreCase("Price: Low to High")) {
            logger.info("low to high sorting verification");

            {
                int i = 0;
                price_Webelement = Elements.findElements("category_browse.browse_prices");
                for (WebElement ele : price_Webelement) {
                    i = page_list.size();
                    float price_fetched = 0;
                    logger.info("Webelement text are ::" + ele.getText() + ":::" + i);
                    if (elementPresent(ele, Elements.getValues("category_browse.price_sale_first_range").get(0))) {
                        logger.info("Price Type::price_sales_first_range");
                        WebElement innerEle = ele.findElement(By.xpath(Elements.getValues("category_browse.price_sale_first_range").get(0)));
                        if (macys()) {
                            price_fetched = Float.parseFloat(innerEle.getText().split(delimiter)[price_index].replace(",", ""));
                        } else {
                            logger.info("BCOM-inner text ::" + innerEle.getText());
                            price_fetched = Float.parseFloat(innerEle.getText().split(" ")[1].split(delimiter)[price_index].replace(",", ""));
                        }
                        page_list.add(price_fetched);
                        logger.info("Sale Price is::" + innerEle.getText() + "::Price Fetched::" + price_fetched + "Index::" + i);
                    } else if (elementPresent(ele, Elements.getValues("category_browse.price_first_range").get(0))) {
                        logger.info("Price Type::price_first_range");
                        WebElement innerEle = ele.findElement(By.xpath(Elements.getValues("category_browse.price_first_range").get(0)));
                        if (macys()) {
                            price_fetched = Float.parseFloat(innerEle.getText().split(delimiter)[1].replace(",", ""));
                        } else {
                            logger.info("BCOM-inner text ::" + innerEle.getText());
                            price_fetched = Float.parseFloat(innerEle.getText().split(delimiter)[price_index].replace(",", ""));
                        }
                        page_list.add(price_fetched);
                        logger.info("Simple price is::" + innerEle.getText() + "::Price Fetched::" + price_fetched + "Index::" + i);
                    }
                    if (page_list.size() > 5) {
                        break;
                    }
                }
            }
            Assert.assertFalse("Fetched price list was empty", page_list.isEmpty());
            for (int index = 0; index < page_list.size() - 1; index++) {
                //  logger.info("Price list displayed for element::"+ i +"on page is::" + page_list.get(i));
                Assert.assertTrue("Price List is not in Ascending order for element::" + index + "Current price::" + page_list.get(index) + "Next Price::" + page_list.get(index + 1), page_list.get(index) <= page_list.get(index + 1));

            }
            logger.info("List Ordered in Ascending order i.e low to high");
        } else
            logger.info("Value::" + args + " Is not a price sorting.Skipping Sort validation");
        //int i = 0;

    }

    public static boolean elementPresent(WebElement parent, String child) {
        boolean status = false;
        try {
            String locator = child.replace(".", "");
            logger.info("Wait until locator::" + locator);
            Wait.secondsUntilElementPresent(By.xpath(locator), 10);
            status = parent.findElement(By.xpath(child)).isDisplayed();
            logger.info("Element status::" + status);
            return status;
        } catch (Exception e) {
            logger.info("Element status::" + status);
            return status;
        }
    }

    /*
    * Method Desc:  Verify Macys Browse/Search page's, Product are arranged in Ascending or descending order of their prices with least verifications
    * it Depend on Different modes
    * */

    public static void verifyMcomSorting(String mode, String sortyby) {
        Wait.isPageLoaded();
        List<Float> page_list = new LinkedList<>();
        List<WebElement> price_Webelement = new LinkedList<>();
        //Variables
        String delimiter = "\\$";
        int price_index = 1;
        //if their is a country flag in MCOM site then assuming that it is an Iship page
        //Else it will take the default values.
        if (mode.equalsIgnoreCase("registry"))//.elementPresent("header_and_footer.header_country_flag")) {
        {
            delimiter = " ";
            price_index = 2;
        }
        //fetching all the products from the page
        price_Webelement = Elements.findElements("category_browse.browse_prices");

        if (sortyby.equalsIgnoreCase("Price: High to Low")) {
            String current_text;
            logger.info("High to low sorting verification");
            int i = 0;
            for (WebElement ele : price_Webelement) {
                i++;
                float price_fetched = 0;
                current_text = ele.getText().replace("\n", " ").replace("\r", " ");
                logger.info("Webelement text are ::" + current_text + ":::" + i);
                //Fetching price of an Element which contains only the price_first_range elements ** for More type of listed price(ranges and sales) use above verifySorting method
                //Price type e.g. $54.50 Now $7.96
                if (current_text.contains("Now")) {
                    price_fetched = Float.parseFloat(current_text.split(" ")[2].replace("$","").replace(",", ""));
                    page_list.add(price_fetched);
                    logger.info("Simple price is::" + current_text+ "::Price Fetched::" + price_fetched + "Index::" + i);
                } else {
                    try {
                        price_fetched = Float.parseFloat(current_text.split(delimiter)[1].replace(",", ""));
                        page_list.add(price_fetched);
                        logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
                    } catch (Exception e) {
                        logger.info("Price type not listed");
                        i = (i != 0) ? i-- : 0;
                    }
                }
                if (i > 8) {
                    break;
                }
            }
            //Below lines verify that product their prices are arranged in Descending order or not
            verifyHighToLow(page_list);
        }
        //Verification for Low to High prices
        else if (sortyby.equalsIgnoreCase("Price: Low to High")) {
            String current_text;
            logger.info("low to high sorting verification");
            int i = 0;
            for (WebElement ele : price_Webelement) {
                i++;
                float price_fetched = 0;
                current_text = ele.getText().replace("\n", " ").replace("\r", " ");
                logger.info("Webelement text are ::" + current_text + ":::" + i);
                //low to high we need to fetch the price sales price first as it have both sales price and price first range
                if (current_text.contains("Now")) {
                    price_fetched = Float.parseFloat(current_text.split(" ")[2].replace("$","").replace(",", ""));
                    page_list.add(price_fetched);
                    logger.info("Simple price is::" + current_text+ "::Price Fetched::" + price_fetched + "Index::" + i);
                }
                //Fetching price of an Element which contains only the price_first_range elements ** for More type of listed price(ranges and sales) use above verifySorting method
                else {
                    try {
                        price_fetched = Float.parseFloat(current_text.split(delimiter)[1].replace(",", ""));
                        page_list.add(price_fetched);
                        logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
                    } catch (Exception e) {
                        logger.info("Price type not listed");
                        i = (i != 0) ? i-- : 0;
                    }
                }
                if (i > 8) {
                    break;
                }
            }
            verifyLowToHigh(page_list);

        } else
            logger.info("Value::" + sortyby + " Is not a price sorting.Skipping Sort validation");
    }

    /*******************************************************************************************************************************************************/
    /*Method Desc: Verify Bloomingdales Browse/Search page's, Product are arranged in Ascending or descending order of their prices with least verifications
    * Depends on Different mode i.e. domestic, iship and registry
    * */
    public static void verifyBcomSorting(String mode, String sortyby) {
        //get all products prices having similar
        if (!mode.equals("iship") && sortyby.equalsIgnoreCase("Price (high-low)")) {
            List<Float> page_list = new LinkedList<>();
            page_list = getBcomPriceDetails("high-low");
            logger.info("high to low sorting verification");
            verifyHighToLow(page_list);
        } else if (!mode.equals("iship") && sortyby.equalsIgnoreCase("Price (low-high)")) {
            List<Float> page_list = new LinkedList<>();
            page_list = getBcomPriceDetails("low-high");
            verifyLowToHigh(page_list);
        } else if (mode.equals("iship") && sortyby.equalsIgnoreCase("Price (high-low)")) {
            List<Float> page_list = new LinkedList<>();
            page_list = getBcomIshipPriceDetails("high-low");
            logger.info("high to low sorting verification");
            verifyHighToLow(page_list);
        } else if (mode.equals("iship") && sortyby.equalsIgnoreCase("Price (low-high)")) {
            List<Float> page_list = new LinkedList<>();
            page_list = getBcomIshipPriceDetails("low-high");
            logger.info("high to low sorting verification");
            verifyLowToHigh(page_list);
        } else {
            logger.info("Value::" + sortyby + " Is not a price sorting.Skipping Sort validation");
        }
    }

    private static void verifyHighToLow(List<Float> page_list) {
        logger.info("Price lists" + page_list);
        Assert.assertFalse("Fetched price list was empty", page_list.isEmpty());
        for (int index = 0; index < page_list.size() - 1; index++) {
            // verifying that each fetched price is lower or equal to the first price
            Assert.assertTrue("Price List is not in Descending order for element::" + index + "price::" + page_list.get(index) + "Next Price::" + page_list.get(index + 1), page_list.get(index) >= page_list.get(index + 1));
        }
        logger.info("List Ordered in Descending order i.e high to low");
    }

    private static void verifyLowToHigh(List<Float> page_list) {
        logger.info("Price lists" + page_list);
        Assert.assertFalse("Fetched price list was empty", page_list.isEmpty());
        for (int index = 0; index < page_list.size() - 1; index++) {
            //  logger.info("Price list displayed for element::"+ i +"on page is::" + page_list.get(i));
            Assert.assertTrue("Price List is not in Ascending order for element::" + index + "Current price::" + page_list.get(index) + "Next Price::" + page_list.get(index + 1), page_list.get(index) <= page_list.get(index + 1));

        }
        logger.info("List Ordered in Ascending order i.e low to high");

    }

    /*
    * Method Details: Fetches all bloomingdales prices on the Search/Browse pages*/
    private static List<Float> getBcomPriceDetails(String type) {
        String current_text;
        List<WebElement> price_Webelement = new LinkedList<>();
        String delimiter = "\\$";
        List<Float> page_list = new LinkedList<>();
        int i = 0;

        price_Webelement = Elements.findElements("category_browse.price_normal");
        for (WebElement ele : price_Webelement) {
            i++;
            float price_fetched = 0;
            current_text = ele.getText().replace("\n", " ").replace("\r", " ");
            logger.info("Webelement text are ::" + current_text + ":::" + i);
            if ((current_text.contains("REG") && current_text.contains("SALE")) || (current_text.contains("ORIG") && current_text.contains("NOW")) || (current_text.contains("ORIG") && current_text.contains("SALE"))) {
                price_fetched = Float.parseFloat(current_text.split(" ")[3].replace("$", "").replace(",", ""));
                page_list.add(price_fetched);
                logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
            } else if (!(current_text.contains("ORIG") || current_text.contains("NOW")) && current_text.contains("-")) {
              //e.g. $22,000.00 - $28,000.00
                if (type.equals("high-low")) {
               price_fetched = Float.parseFloat(current_text.split("-")[1].split("\\$")[1].replace(",", ""));
                } else if (type.equals("low-high")) {
               price_fetched = Float.parseFloat(current_text.split("-")[0].split("\\$")[1].replace(",", ""));
                }
                page_list.add(price_fetched);
                logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
            } else {
                try {
                    price_fetched = Float.parseFloat(current_text.split(delimiter)[1].replace(",", ""));
                    page_list.add(price_fetched);
                    logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
                } catch (Exception e) {
                    logger.info("Price type not listed");
                    i = (i != 0) ? i-- : 0;
                }
            }
            if (i > 8) {
                break;
            }
        }

        return page_list;
    }

    private static List<Float> getBcomIshipPriceDetails(String type) {
        String current_text;
        List<WebElement> price_Webelement = new LinkedList<>();
        String delimiter = Elements.findElement(By.id("iShipCurrency")).getText();
        List<Float> page_list = new LinkedList<>();
        //delimiter = "INR";
        int i = 0;
        logger.info("Delimiter for iship is::" + delimiter);
        price_Webelement = Elements.findElements("category_browse.price_normal");
        for (WebElement ele : price_Webelement) {
            i++;
            float price_fetched = 0;
            current_text = ele.getText().replace("\n", " ").replace("\r", " ");
            logger.info("Webelement text are ::" + current_text + ":::" + i);
            //Price type e.g. ORIG. INR 3,396.00 SALE INR 1,902.00 - INR 3,396.00
            if (current_text.contains("ORIG") && current_text.contains("SALE") && current_text.contains("-")) {
                if (type.equals("high-low")) {
                    price_fetched = Float.parseFloat(current_text.split(" ")[8].replace(",", ""));
                } else if (type.equals("low-high")) {
                    price_fetched = Float.parseFloat(current_text.split(" ")[5].replace(",", ""));
                }
                page_list.add(price_fetched);
                logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
            } else
                //Price type e.g. ORIG. INR 4,090.00 SALE INR 2,290.00
                if ((current_text.contains("REG") && current_text.contains("SALE")) || (current_text.contains("ORIG") && current_text.contains("NOW")) || (current_text.contains("ORIG") && current_text.contains("SALE"))) {
                    price_fetched = Float.parseFloat(current_text.split(" ")[5].replace(",", ""));
                    page_list.add(price_fetched);
                    logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
                }
                //Price type e.g. INR 1,902.00 - INR 3,396.00
                else if (!(current_text.contains("ORIG") || current_text.contains("NOW")) && current_text.contains("-")) {
                    if (type.equals("high-low")) {
                        price_fetched = Float.parseFloat(current_text.split(" ")[4].replace(",", ""));
                    } else if (type.equals("low-high")) {
                        price_fetched = Float.parseFloat(current_text.split(" ")[1].replace(",", ""));
                    }
                    page_list.add(price_fetched);
                    logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
                } else {
                    try {
                        //Price type e.g. - INR 690.00
                        price_fetched = Float.parseFloat(current_text.split(delimiter)[1].replace(",", ""));
                        page_list.add(price_fetched);
                        logger.info("Simple price is::" + current_text + "::Price Fetched::" + price_fetched + "Index::" + i);
                    } catch (Exception e) {
                        logger.info("Price type not listed");
                        i = (i != 0) ? i-- : 0;
                    }
                }
            if (i > 8) {
                break;
            }
        }
        return page_list;
    }
}




