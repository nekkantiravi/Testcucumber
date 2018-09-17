package com.macys.sdt.projects.Stores.Checkout.utils;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.macys.sdt.framework.runner.RunConfig.appTest;



public class CheckoutUtils {

    //Integrated EnvironmentURLs
    public static String MacysLandingDNS= "http://mstore-uat.devops.fds.com/js/common.js";
    public static String MacysSearchDNS= "http://mstore-uat-pd.devops.fds.com/js/common.js";
    public static String MacysSalesTransSSP20 = "https://fs020asssp01/js/common.js";
    public static String MacysSalesTransSSP24 = "https://fs024asssp01/js/common.js";
    public static String MacysSalesTransSSP10 = "https://fs010asssp01/js/common.js";
    public static String BloomLandingDNS= "http://bstore-uat.devops.fds.com/js/common.js";
    public static String BloomSearchDNS= "http://bstore-uat-pd.devops.fds.com/js/common.js";
    public static String BloomSalesTransSSP22 = "https://fs022asssp01/js/common.js";
    public static String BloomSalesTransSSP12= "https://fs012asssp01/js/common.js";
    public static String BloomSalesTransSSP28= "https://fs028asssp01/js/common.js";
    public static String Prod572PA = "https://me572asssp01/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate";
    public static String Macys337PA = "https://ln001xsssp0010/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate";
    public static String Macys166PA = "https://ln001xsssp0020/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate";
    public static String Macys572PA = "https://ln001xsssp0024/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate";
    public static String Bloom22PA = "https://ln001xsssp0022/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate";
    public static String Bloom55PA = "https://ln001xsssp0012/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate";
    public static String Bloom01PA = "https://ln001xsssp0028/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate";
    public static String ProdAssoc = "{\"associateNumber\":\"01001685\",\"associatePin\":\"1000\"}";
    public static String TestAssoc = "{\"associateNumber\":\"71234561\",\"associatePin\":\"1000\"}";
    public static String MacysProd572SalesTransBag = "https://me572asssp01/js/common.js";
    public static String MacysProd572LandingDNS = "http://mstore.devops.fds.com/js/common.js";
    public static String MacysProd572SearchDNS = "http://pdsc-lb-mstore18.sl.devops.fds.com/js/common.js";


    // Put code here that doesn't necessarily apply to just one page or step.
    // You can also use it for more general utility things, like interacting
    // with a database or talking to a service.
    /**
     * this line searches for "s" child tag, which represent the striketrhough Css
     * If the element doesn not have a strikethrough, the search would return an exception
     *
     * @param element
     * @return True if the element has strikethrough, False if it doesn't
     */
    private static PrintStream errLog = null;

    public static boolean elementHasStrikeThrough(WebElement element) {

        try {
            element.findElement(By.xpath("s"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Adds the given number of items to the shopping bag
     * no_of_items_in_bag
     *
     * @param count number of items to add to bag
     */
    public static void addItemsToBag(int count) throws InterruptedException {
        for (int i = 0; i < count; i++) {
            TextBoxes.typeTextNEnter("bag_screen.upc_textbox", "20714001940");
            Elements.elementPresent("bag_screen.add_to_bag_button");
            Clicks.click("bag_screen.add_to_bag_button");

            if (Elements.elementPresent("bag_screen.toast_body") || Elements.elementPresent("bag_screen.spinner")) {
                System.out.println("The Toast is STILLLLL");
                Thread.sleep(1000);
                Wait.secondsUntilElementNotPresent("bag_screen.bag_screen.spinner", 10);
                Wait.secondsUntilElementNotPresent("bag_screen.toast_body", 5);
                Wait.secondsUntilElementPresent("bag_screen.add_to_bag_button", 5);

            }
        }

    }
    /**
     * Minimizes the browser window to iPhone view
     */

    public static void setWindowToHalDesktopView() {

        try {
            WebDriverManager.getWebDriver().manage().window().setPosition(new Point(0, 0));
            WebDriverManager.getWebDriver().manage().window().setSize(new Dimension(1366, 768));
        } catch (DriverNotInitializedException e) {
            Assert.fail("Driver not initialized");
        }
    }

    public static void settingLocalstorage(String environment) throws Exception {
        try {

            FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/"+environment+".json"));
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            WebDriver driver = WebDriverManager.getWebDriver();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));

        } catch (FileNotFoundException e) {
            FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/"+environment+".json"));
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();


            WebDriver driver = WebDriverManager.getWebDriver();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));


        }
        Thread.sleep(1000);
    }

    /**
     * public static void MakeTransactionTypeTake() throws Exception {
     * WebDriver driver = WebDriverManager.getWebDriver();
     * JavascriptExecutor executor = (JavascriptExecutor) driver;
     * <p>
     * String transType = "Stores::Trans";
     * String smth;
     * String smth2;
     * smth = (String) executor.executeScript(String.format("return window.localStorage.getItem('%s');", transType));
     * smth2 = smth.replace("send", "take");
     * <p>
     * executor.executeScript("window.localStorage.setItem('%s', JSON.stringify('%s'));", transType, smth2);
     * <p>
     * }
     */

    public static void ManagerLevel0() throws Exception {
        try {
            FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/manager_level_0.json"));
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();


            WebDriver driver = WebDriverManager.getWebDriver();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));


        } catch (FileNotFoundException e) {
            FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/manager_level_0.json"));
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();


            WebDriver driver = WebDriverManager.getWebDriver();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));


        }
    }

    /**
     * Types text into element and sends an enter key
     *
     * @param selector String selector in format "page_name.element_name"
     * @param text     text to type in
     */
    public static void typeTextNTab(String selector, String text) {
        typeTextNTab(Elements.element(selector), text);
        System.out.println(selector.split("\\.")[1] + ": " + text);
    }

    /**
     * Types text into element and sends an enter key
     *
     * @param selector By selector to use
     * @param text     text to type in
     */
    public static void typeTextNTab(By selector, String text) {
        Navigate.runBeforeNavigation();
        WebElement element = Elements.findElement(selector);
        if (element == null) {
            return;
        }

        if (appTest) {
            text += StepUtils.iOS() ? "" : "\n";
            element.clear();
            element.sendKeys(text);
            if (StepUtils.iOS()) {
                Clicks.click(By.id("Search"));
            }
        } else {
            element.click();
            element.clear();
            element.sendKeys(text);

            if (StepUtils.safari() || RunConfig.useAppium) {
                try {
                    element.submit();
                } catch (Exception e) {
                    element.sendKeys(Keys.TAB);
                }
            } else {
                element.sendKeys(Keys.TAB);
            }
        }
        Navigate.runAfterNavigation();
    }


    //Findxy coordinates

    public static void findXY(String selector) {
        findXYCoordinates(Elements.findElement(selector));
    }

    public static void findXYCoordinates(WebElement el) {
        Point point = el.getLocation();
        // System.out.println("Location: X position : " + point.x);
        // System.out.println("Location: Y position : " + point.y);
    }



    public static Map<String, HashMap> getProductDetails() {
        HashMap<String, HashMap> productThumbnails = new HashMap<>();
        boolean item_price;
        boolean batch_text;
        boolean customer_ratings;
        List<WebElement> thumbnails = Elements.findElements(Elements.element("bag_screen.details"));

        for (WebElement thumbnail : thumbnails) {
            HashMap<String, String> thumbnailWebElements = new HashMap<>();
            try {
                item_price = thumbnail.findElement(By.className("itemPrice")).isDisplayed();
            } catch (org.openqa.selenium.NoSuchElementException e) {
                System.out.println("Price details are not available ");
                item_price = false;
            }

            thumbnailWebElements.put("elm_product_id", thumbnail.getAttribute("id"));
            thumbnailWebElements.put("elm_product_name", thumbnail.findElement(By.className("itemDesc productName")).getText());
            thumbnailWebElements.put("elm_price_event", item_price ? thumbnail.findElement(By.className("priceInfo")).getText() : null);
            thumbnailWebElements.put("elm_prices", thumbnail.findElement(By.className("prices")).getText().split("\\(")[0]);

            productThumbnails.put(thumbnail.getAttribute("id"), thumbnailWebElements);
        }

        return productThumbnails;
    }

    public static void swipeitemrighttoleft() throws InterruptedException {
        Wait.forPageReady();
        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }


//        Thread.sleep(5000);
//        Actions action = new Actions(driver);
//        WebElement drag = driver.findElement(By.className("qe-item-currentPrice"));
//        WebElement drop = driver.findElement(By.className("qe-item-originalPrice"));
//        refreshPage();
//        Thread.sleep(7000);
//        action.dragAndDrop(drag, drop).perform();
////        action.clickAndHold(drag).perform();
////        action.pause(2000);
////        action.moveToElement(drop).release().perform();
//
//
  }


    public static void typeText(String selector, String text) {
        typeText(Elements.element(selector), text);
        System.out.println(selector.split("\\.")[1] + ": " + text);
    }


    public static void typeText(By selector, String text) {
        Navigate.runBeforeNavigation();
        WebElement element = Elements.findElement(selector);
        if (element == null) {
            return;
        }

        if (appTest) {
            text += StepUtils.iOS() ? "" : "\n";
            element.clear();
            element.sendKeys(text);
            if (StepUtils.iOS()) {
                Clicks.click(By.id("Search"));
            }
        } else {
            element.click();
            element.clear();
            element.sendKeys(text);

        }
        Navigate.runAfterNavigation();
    }

    public static void ScanCrl(String UPC) {

        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver).executeScript(
                "HAL.handlers['scanCallback'].resolve(\"" + UPC + "\");");

    }

     public static void ScanUPC(String UPC) {

        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver).executeScript(
                "HAL.handlers['scanCallback'].resolve(\"" + UPC + "\");");

    }

    public static void ScanCoupon(String Coupon) {

        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver).executeScript(
                "HAL.handlers['scanCallback'].resolve(\"" + Coupon + "\");");

    }

    public static void addSpecialUPC(String specialItem) {
        if (!Elements.elementPresent("bag_screen.upc_textbox")) {
            Wait.untilElementPresent("bag_screen.upc_textbox");
        }
        switch (specialItem) {
            case "does not have a color size WebID":
                TextBoxes.typeTextNEnter("bag_screen.upc_textbox", "12345000010");
                break;
            case "has a discount":
                TextBoxes.typeTextNEnter("bag_screen.upc_textbox", "91709543745");
                break;
            case "is recall item":
                TextBoxes.typeTextNEnter("bag_screen.upc_textbox", "0768549130595");
                break;
            case "does not have CRL":
                TextBoxes.typeTextNEnter("bag_screen.upc_textbox", "888602194910");
                break;
        }

        if (Elements.elementPresent("bag_screen.add_to_bag_button")) {
            Clicks.click("bag_screen.add_to_bag_button");
        }

    }

    public static void MsrSwipe(String Card) throws InterruptedException {
        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver).executeScript(
                "HAL.handlers['msrCallback'].resolve({\"keyVersion\":\"0\",\"encryptionType\":\"AES\",\"encryptionAlgorithm\":\"CBC\", \"data\":\"" + Card + "\"});");

    }

//
//    public static void LongPressBag() throws InterruptedException {
//        Wait.forPageReady();
//        WebDriver driver = null;
//        try {
//            driver = WebDriverManager.getWebDriver();
//        } catch (DriverNotInitializedException e) {
//            e.printStackTrace();
//        }
//
//        WebElement el = driver.findElement(By.id(("show-bag-menu")));
//        Actions action = new Actions(driver);
//        action.clickAndHold(el).perform();
//        action.pause(2000);
    //action.moveToElement(el).release();


    public static void refreshPage() {
        Wait.forPageReady();
        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }

        driver.navigate().refresh();
        Wait.forPageReady();
        if (Elements.elementPresent("home.overlay_close")) {
            Clicks.click("home.overlay_close");
        }
    }

        public static void TransHeaderVerifyOpenTransaction(String ClientID) throws ClassNotFoundException, SQLException, InterruptedException {
            //This is How we Generate Today's Gregorian Date
            DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
            formatter.setLenient(false);
            Date today = new Date();
            String TodayGregDate = (formatter.format(today));
            System.out.println("This is Todays Greg date" + TodayGregDate);

           DateFormat formatter1 = new SimpleDateFormat("dd-mm-yyyy");
            String Date = Elements.findElement("").getText();
           Date.matches(String.valueOf(formatter1));

//Running an SQL Query to verify the Device ID and the Open Transaction Display
            Thread.sleep(1000);
            Class.forName("com.mysql.jdbc.Driver");
            Thread.sleep(1000);
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@s94d1z1.federated.fds:1521/stpdev02", "PS_UCT", "psuct_stpdev01");
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery("SELECT * FROM TRANS_HDR WHERE GREG_DATE = \'" + TodayGregDate + "\' AND SELLING_STORE = '166' AND CLIENT_ID = \'"+ ClientID +"\' AND TRANS_CD = 8");
            if (rs.next()) {
                do {
                    String OpenTrans = rs.getString("TRANS_CD");
                    System.out.println("The Open Record came back as " + OpenTrans);

                    String DeviceID = rs.getString("CLIENT_ID");
                    System.out.println("The Device ID is " + DeviceID);

                    Assert.assertTrue("DeviceID was not collected", DeviceID.equals(ClientID));
                    Assert.assertTrue("Open Transaction was not collected", OpenTrans.equals("8"));
                    System.out.println("THE OPEN TRANSACTION VERIFIED WAS SUCCESSFUL");


                } while (rs.next()); {

                }
            }
            else {

                Assert.fail("The Database was empty");
            }
        }


    public static void TransHeaderVerifyCloseTransaction(String ClientID) throws ClassNotFoundException, SQLException {
        //This is How we Generate Today's Gregorian Date
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
        formatter.setLenient(false);
        Date today = new Date();
        String TodayGregDate = (formatter.format(today));
        System.out.println("This is Todays Greg date" + TodayGregDate);

        //This is How we Generate Yesterday's Gregorian Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date Yesterday = calendar.getTime();
        String YesterdaysGregDate = (formatter.format(Yesterday));
        System.out.println("This is Yesterdays Greg date" + YesterdaysGregDate);


//Running an SQL Query to verify the Device ID and the Close Transaction from the previous day Display
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@s94d1z1.federated.fds:1521/stpdev02", "PS_UCT", "psuct_stpdev01");
        Statement smt = con.createStatement();
        ResultSet rs = smt.executeQuery("SELECT * FROM TRANS_HDR WHERE GREG_DATE = \'" + YesterdaysGregDate + "\' AND SELLING_STORE = '166' AND CLIENT_ID = \'" + ClientID + "\'   AND TRANS_TYP_NBR = 10 AND TRANS_CD = 9");

        if(rs.next()) {


            do {
                String CloseRecord = rs.getString("TRANS_CD");
                System.out.println("The Close Record came back as " + CloseRecord);

                String DeviceID = rs.getString("CLIENT_ID");
                System.out.println("The Device ID is " + DeviceID);


                Assert.assertTrue("DeviceID was not collected", DeviceID.equals(ClientID));
                Assert.assertTrue("Close Transaction was not collected", CloseRecord.equals("9"));
                System.out.println("THE CLOSED TRANSACTION VERIFIED WAS SUCCESSFUL");
            } while (rs.next()); {

            }
        }
        else {

            Assert.fail("The Database was empty");
        }
    }


    public static void ClearLocal() throws DriverNotInitializedException, InterruptedException {
        Thread.sleep(1000);
        WebDriver driver = WebDriverManager.getWebDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("window.localStorage.clear();"));    }


    public static int GenerateRandomCRL()  {

        Random rnd = new Random();
        int random1 = rnd.nextInt(999999999);
        String random2 = Integer.toString(random1);
        return random1;
    }

    public static void CancelTrans() throws InterruptedException {
        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver).executeScript("Backbone.$('#show-menu').click();");
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("Backbone.$('#cancel-transaction-link').click();");
        Thread.sleep(500);
//        ((JavascriptExecutor) driver).executeScript("Backbone.Radio.channel( 'Bag' ).request( 'ClearSalesTrans' );\n");

    }


    public static void GoBacktoPreviousPage() throws InterruptedException {
        WebDriver driver = null;
        try {
            driver = WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            e.printStackTrace();
        }

        ((JavascriptExecutor) driver).executeScript("window.history.go(-1)");

    }

    public static void SettingFeatureFlags(String environment) throws Exception {
        try {
            FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + environment + ".json"));
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            json= json;


            WebDriver driver = WebDriverManager.getWebDriver();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "uPOSFeatureFlags",json));
        }
        catch(FileNotFoundException e) {
            System.out.println("I entered the Setting Feature Flag Catch");
            FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + environment + ".json"));
            InputStreamReader in = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            json = json;


            WebDriver driver = WebDriverManager.getWebDriver();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "uPOSFeatureFlags", json));
        }
    }

    public static void GetandSetPDJWT(String ssp, String FeatureFlags) throws Exception {
        Thread.sleep(2000);
        switch (ssp) {
            case "Macys20Integrated":
                String Macys20JWT = CheckoutUtils.PinAuthCall(Macys166PA, TestAssoc);
                String jsonfile20 = "Macys_IntegratedSSP20";
                String json;
                try {
                    FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile20 + ".json"));
                    InputStreamReader in = new InputStreamReader(fin);
                    BufferedReader bufferedReaderD = new BufferedReader(in);
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = bufferedReaderD.readLine()) != null) {
                        sb.append(line);
                    }
                    String jsonM = sb.toString();
                    json = jsonM.replace("ReplaceThisJWT", Macys20JWT);
                } catch (FileNotFoundException e) {
                    System.out.println("I entered the catch setting file path");
                    FileInputStream finD = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile20 + ".json"));

                    InputStreamReader inD = new InputStreamReader(finD);
                    BufferedReader bufferedReaderM = new BufferedReader(inD);
                    StringBuilder sbD = new StringBuilder();

                    String line;
                    while ((line = bufferedReaderM.readLine()) != null) {
                        sbD.append(line);
                    }
                    String jsonx = sbD.toString();
                    json = jsonx.replace("ReplaceThisJWT", Macys20JWT);
                }

                WebDriver driver = WebDriverManager.getWebDriver();
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                System.out.println("This is 20 integrated json" + json);

                //Sets Local Storage in the Bag Application
                Navigate.visit(MacysSalesTransSSP20);
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(MacysLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(MacysSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();
                break;
            case "Bloom22Integrated":
                String Bloom22JWT = CheckoutUtils.PinAuthCall(Bloom22PA, TestAssoc);
                String jsonfile22 = "Bloom_IntegratedSSP22";
                System.out.println("This is the JWT That I will Inject" + Bloom22JWT);
                String jsonB;

                try {
                    FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile22 + ".json"));
                    InputStreamReader in = new InputStreamReader(fin);
                    BufferedReader bufferedReaderD = new BufferedReader(in);
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = bufferedReaderD.readLine()) != null) {
                        sb.append(line);
                    }
                    String jsonM = sb.toString();
                    jsonB = jsonM.replace("ReplaceThisJWT", Bloom22JWT);
                } catch (FileNotFoundException e) {
                    System.out.println("I entered the catch setting file path");
                    FileInputStream finD = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile22 + ".json"));

                    InputStreamReader inD = new InputStreamReader(finD);
                    BufferedReader bufferedReaderM = new BufferedReader(inD);
                    StringBuilder sbD = new StringBuilder();

                    String line;
                    while ((line = bufferedReaderM.readLine()) != null) {
                        sbD.append(line);
                    }
                    String jsonx = sbD.toString();
                    jsonB = jsonx.replace("ReplaceThisJWT", Bloom22JWT);
                }
                System.out.println("This is the JSONB for 22" + jsonB);
                WebDriver driverB = WebDriverManager.getWebDriver();
                JavascriptExecutor executorB = (JavascriptExecutor) driverB;
                executorB.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", jsonB));


                //Sets Local Storage in the Bag Application
                Navigate.visit(BloomSalesTransSSP22);
                Wait.forPageReady();
                ClearLocal();
                executorB.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", jsonB));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(BloomLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executorB.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", jsonB));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(BloomSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executorB.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", jsonB));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();
                break;
            case "Macys10Integrated":
                String Macys10JWT = CheckoutUtils.PinAuthCall(Macys337PA, TestAssoc);
                String jsonfile10 = "Macys_IntegratedSSP10";
                try {
                    FileInputStream finD = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile10 + ".json"));
                    InputStreamReader inD = new InputStreamReader(finD);
                    BufferedReader bufferedReaderD = new BufferedReader(inD);
                    StringBuilder sbD = new StringBuilder();

                    String lineD;
                    while ((lineD = bufferedReaderD.readLine()) != null) {
                        sbD.append(lineD);
                    }
                    String jsonM = sbD.toString();
                    json = jsonM.replace("ReplaceThisJWT", Macys10JWT);
                } catch (FileNotFoundException e) {
                    System.out.println("I entered the catch setting file path");
                    FileInputStream finD = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile10 + ".json"));

                    InputStreamReader inD = new InputStreamReader(finD);
                    BufferedReader bufferedReaderM = new BufferedReader(inD);
                    StringBuilder sbD = new StringBuilder();

                    String lineD;
                    while ((lineD = bufferedReaderM.readLine()) != null) {
                        sbD.append(lineD);
                    }
                    String jsonx = sbD.toString();
                    json = jsonx.replace("ReplaceThisJWT", Macys10JWT);
                }

                WebDriver driverD = WebDriverManager.getWebDriver();
                JavascriptExecutor executorD = (JavascriptExecutor) driverD;
                executorD.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));


                //Sets Local Storage in the Bag Application
                Navigate.visit(MacysSalesTransSSP10);
                Wait.forPageReady();
                ClearLocal();
                executorD.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(MacysLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executorD.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(MacysSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executorD.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                break;
            case "MacysProd572":
                String ProdJWT = CheckoutUtils.PinAuthCall(Prod572PA, ProdAssoc);
                String jsonfile572 = "Macy's_Integrated_PROD572";

                //Removes The Placeholder ReplaceThisJWT in the MacysIntegratedEnv Json and replaces it with the String of Json
                FileInputStream finP = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile572 + ".json"));
                InputStreamReader inP = new InputStreamReader(finP);
                BufferedReader bufferedReaderP = new BufferedReader(inP);
                StringBuilder sbP = new StringBuilder();
                String lineP;
                while ((lineP = bufferedReaderP.readLine()) != null) {
                    sbP.append(lineP);
                }
                String jsonP = sbP.toString();
                json = jsonP.replace("ReplaceThisJWT", ProdJWT);

                System.out.println(json);

                WebDriver driverP = WebDriverManager.getWebDriver();
                JavascriptExecutor executorP = (JavascriptExecutor) driverP;
                executorP.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));


                //Sets Local Storage in the Bag Application
                Navigate.visit(MacysProd572SalesTransBag);
                Wait.forPageReady();
                ClearLocal();
                executorP.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(MacysProd572LandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executorP.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(MacysProd572SearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executorP.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();
                break;
            case "Macys20Disabled":
                String Macys20disabledJWT = CheckoutUtils.PinAuthCall(Macys166PA, TestAssoc);
                String jsonfile20D = "Macys_IntegratedSSP20";
                try {
                    FileInputStream findis = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile20D + ".json"));
                    InputStreamReader indis = new InputStreamReader(findis);
                    BufferedReader bufferedReaderM = new BufferedReader(indis);
                    StringBuilder sbdis = new StringBuilder();

                    String linedis;
                    while ((linedis = bufferedReaderM.readLine()) != null) {
                        sbdis.append(linedis);
                    }
                    String jsonM = sbdis.toString();
                    json = jsonM.replace("ReplaceThisJWT", Macys20disabledJWT);
                    System.out.println(json);
                } catch (FileNotFoundException e) {
                    System.out.println("I entered the catch setting file path");
                    FileInputStream findis = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile20D + ".json"));

                    InputStreamReader indis = new InputStreamReader(findis);
                    BufferedReader bufferedReaderM = new BufferedReader(indis);
                    StringBuilder sbdis = new StringBuilder();

                    String linedis;
                    while ((linedis = bufferedReaderM.readLine()) != null) {
                        sbdis.append(linedis);
                    }
                    String jsonx = sbdis.toString();
                    json = jsonx.replace("ReplaceThisJWT", Macys20disabledJWT);
                }

                WebDriver driverDis = WebDriverManager.getWebDriver();
                JavascriptExecutor executorDis = (JavascriptExecutor) driverDis;
                executorDis.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));

                //Sets Local Storage in the Bag Application
                Navigate.visit(MacysSalesTransSSP20);
                Wait.forPageReady();
                ClearLocal();
                executorDis.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(MacysLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executorDis.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(MacysSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executorDis.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();
                break;
            case "Macys10Disabled":
                String Macys10disabledJWT = CheckoutUtils.PinAuthCall(Macys337PA, TestAssoc);
                String jsonfile10D = "Macys_IntegratedSSP10";
                try {
                    FileInputStream findis1 = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile10D + ".json"));
                    InputStreamReader indis1 = new InputStreamReader(findis1);
                    BufferedReader bufferedReaderM = new BufferedReader(indis1);
                    StringBuilder sbdis1 = new StringBuilder();

                    String linedis1;
                    while ((linedis1 = bufferedReaderM.readLine()) != null) {
                        sbdis1.append(linedis1);
                    }
                    String jsonM1 = sbdis1.toString();
                    json = jsonM1.replace("ReplaceThisJWT", Macys10disabledJWT);
                    System.out.println(json);
                } catch (FileNotFoundException e) {
                    FileInputStream findis1 = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile10D + ".json"));

                    InputStreamReader indis1 = new InputStreamReader(findis1);
                    BufferedReader bufferedReaderM = new BufferedReader(indis1);
                    StringBuilder sbdis1 = new StringBuilder();

                    String linedis1;
                    while ((linedis1 = bufferedReaderM.readLine()) != null) {
                        sbdis1.append(linedis1);
                    }
                    String json1 = sbdis1.toString();
                    json = json1.replace("ReplaceThisJWT", Macys10disabledJWT);
                }

                WebDriver driverDis1 = WebDriverManager.getWebDriver();
                JavascriptExecutor executorDis1 = (JavascriptExecutor) driverDis1;
                executorDis1.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));

                //Sets Local Storage in the Bag Application
                Navigate.visit(MacysSalesTransSSP10);
                Wait.forPageReady();
                ClearLocal();
                executorDis1.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(MacysLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executorDis1.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(MacysSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executorDis1.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();
                break;
            case "Macys24Integrated":
                String Macys24JWT = CheckoutUtils.PinAuthCall(Macys572PA, TestAssoc);
                String jsonfile24 = "Macys_IntegratedSSP24";
                try {
                    FileInputStream finM = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile24 + ".json"));
                    InputStreamReader inM = new InputStreamReader(finM);
                    BufferedReader bufferedReaderM = new BufferedReader(inM);
                    StringBuilder sbM = new StringBuilder();
                    String lineM;
                    while ((lineM = bufferedReaderM.readLine()) != null) {
                        sbM.append(lineM);
                    }
                    String jsonM = sbM.toString();
                    json = jsonM.replace("ReplaceThisJWT", Macys24JWT);
                } catch (FileNotFoundException e) {
                    FileInputStream finM = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile24 + ".json"));
                    InputStreamReader inM = new InputStreamReader(finM);
                    BufferedReader bufferedReaderM = new BufferedReader(inM);
                    StringBuilder sbM = new StringBuilder();
                    String lineM;
                    while ((lineM = bufferedReaderM.readLine()) != null) {
                        sbM.append(lineM);
                    }
                    String jsonM = sbM.toString();
                    json = jsonM.replace("ReplaceThisJWT", Macys24JWT);
                }

                WebDriver driverM = WebDriverManager.getWebDriver();
                JavascriptExecutor executorM = (JavascriptExecutor) driverM;
                executorM.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));

                //Sets Local Storage in the Bag Application
                Navigate.visit(MacysSalesTransSSP24);
                Wait.forPageReady();
                ClearLocal();
                executorM.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(MacysLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executorM.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(MacysSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executorM.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();
                break;
            case "Bloom12Integrated":
                String Bloom12JWT = CheckoutUtils.PinAuthCall(Bloom55PA, TestAssoc);
                String jsonfile12= "Bloom_IntegratedSSP12";
                try {
                    FileInputStream finb = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile12 + ".json"));
                    InputStreamReader inb = new InputStreamReader(finb);
                    BufferedReader bufferedReaderb = new BufferedReader(inb);
                    StringBuilder sbb = new StringBuilder();

                    String lineb;
                    while ((lineb = bufferedReaderb.readLine()) != null) {
                        sbb.append(lineb);
                    }
                    String jsonb = sbb.toString();
                    json = jsonb.replace("ReplaceThisJWT", Bloom12JWT);
                    System.out.println(json);
                } catch (FileNotFoundException e) {
                    FileInputStream finb = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile12 + ".json"));
                    InputStreamReader inb = new InputStreamReader(finb);
                    BufferedReader bufferedReaderb = new BufferedReader(inb);
                    StringBuilder sbb = new StringBuilder();
                    String lineb;
                    while ((lineb = bufferedReaderb.readLine()) != null) {
                        sbb.append(lineb);
                    }
                    String jsonM = sbb.toString();
                    json = jsonM.replace("ReplaceThisJWT", Bloom12JWT);
                }

                WebDriver driverb = WebDriverManager.getWebDriver();
                JavascriptExecutor executorb = (JavascriptExecutor) driverb;
                executorb.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));

                //Sets Local Storage in the Bag Application
                Navigate.visit(BloomSalesTransSSP12);
                Wait.forPageReady();
                ClearLocal();
                executorb.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(BloomLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executorb.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(BloomSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executorb.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                SettingFeatureFlags(FeatureFlags);
                Thread.sleep(1000);
                refreshPage();
                break;
            case "Bloom28Integrated":
                String Bloom28JWT = CheckoutUtils.PinAuthCall(Bloom01PA, TestAssoc);
                String jsonfile28 = "Bloom_IntegratedSSP28.json";
                try {
                    FileInputStream finx = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile28 + ".json"));
                    InputStreamReader inx = new InputStreamReader(finx);
                    BufferedReader bufferedReaderM = new BufferedReader(inx);
                    StringBuilder sbx = new StringBuilder();

                    String lineM;
                    while ((lineM = bufferedReaderM.readLine()) != null) {
                        sbx.append(lineM);
                    }
                    String jsonM = sbx.toString();
                    json = jsonM.replace("ReplaceThisJWT", Bloom28JWT);
                } catch (FileNotFoundException e) {
                    FileInputStream finx = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/" + jsonfile28 + ".json"));
                    InputStreamReader inx = new InputStreamReader(finx);
                    BufferedReader bufferedReaderM = new BufferedReader(inx);
                    StringBuilder sbx = new StringBuilder();

                    String lineM;
                    while ((lineM = bufferedReaderM.readLine()) != null) {
                        sbx.append(lineM);
                    }
                    String jsonx = sbx.toString();
                    json = jsonx.replace("ReplaceThisJWT", Bloom28JWT);
                }
                    WebDriver driverx = WebDriverManager.getWebDriver();
                    JavascriptExecutor executorx = (JavascriptExecutor) driverx;
                    executorx.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));

                    //Sets Local Storage in the Bag Application
                    Navigate.visit(BloomSalesTransSSP28);
                    Wait.forPageReady();
                    ClearLocal();
                    executorx.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                    Thread.sleep(1000);
                    SettingFeatureFlags(FeatureFlags);
                    Thread.sleep(1000);
                    refreshPage();

                    //Sets Local Storage in the Landing Page
                    Navigate.visit(BloomLandingDNS);
                    Wait.forPageReady();
                    ClearLocal();
                    executorx.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                    Thread.sleep(1000);
                    SettingFeatureFlags(FeatureFlags);
                    Thread.sleep(1000);
                    refreshPage();

                    //Sets Local Storage in the Search Page
                    Navigate.visit(BloomSearchDNS);
                    Wait.forPageReady();
                    ClearLocal();
                    executorx.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                    Thread.sleep(1000);
                    SettingFeatureFlags(FeatureFlags);
                    Thread.sleep(1000);
                    refreshPage();
                    break;
        }
    }
    public static String PinAuthCall(String URL, String Body) throws Exception {
        String jwtJson = CheckoutUtils.HttpPostRequest(URL, Body);
        System.out.println("This is Alex's code" + jwtJson);
        String cs = jwtJson;

        cs = cs.replace("{\"jwt\":\"", " ");
        cs = cs.replace("\"}", " ");
        cs = cs.replaceAll("\".*", "");
        System.out.println("AMMENDED STRING" + cs);
        String JWT = cs;

        return JWT;
    }

        public static String HttpPostRequest(String url, String jsonBody) throws Exception
        {
            HttpClient client = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).setSslcontext(new SSLContextBuilder().loadTrustMaterial(null, (x509Certificates, s) -> true).build()).build();
            StringEntity requestEntity = new StringEntity(jsonBody ,ContentType.APPLICATION_JSON);

            HttpPost postMethod = new HttpPost(url);
            postMethod.setEntity(requestEntity);

            HttpResponse response = client.execute(postMethod);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }

    public static String BadPinAuthCall(String URL, String Body) throws Exception {
        String jwtJson = CheckoutUtils.HttpPostRequest(URL, Body);
        System.out.println("This is Alex's code" + jwtJson);
        String message = jwtJson;
        Assert.assertFalse("The Pin Authenticate Response came back as Service Unavailable", message.contains("Service Unavailable"));
        Assert.assertTrue("The pin auth error message contained Invalid Userid", message.contains("Invalid Userid"));

        return message;
    }

}















