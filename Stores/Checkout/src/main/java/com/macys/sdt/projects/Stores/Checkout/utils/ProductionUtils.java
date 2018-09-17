package com.macys.sdt.projects.Stores.Checkout.utils;

import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.*;

import static com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils.ClearLocal;
import static com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils.refreshPage;


public class ProductionUtils {

    //Integrated EnvironmentURLs
    public static String Prod572PA = "https://me572asssp01/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate";
    public static String ProdAssoc = "{\"associateNumber\":\"01001685\",\"associatePin\":\"1000\"}";
    public static String MacysProd572SalesTransBag = "https://me572asssp01/js/common.js";
    public static String MacysProdLandingDNS = "http://mstore.devops.fds.com/js/common.js";
    public static String MacysProdSearchDNS = "http://pdsc-lb-mstore18.sl.devops.fds.com/js/common.js";
    public static String BloomProdLandingDNS = "http://bstore.devops.fds.com/js/common.js";
    public static String BloomProdSearchDNS = "http://pdsc-lb-bstore18.sl.devops.fds.com/js/common.js";
    public static String TestAssoc = "{\"associateNumber\":\"71234561\",\"associatePin\":\"1000\"}";


    public static void GetandSetProdPDJWT(String StoreNum) throws Exception {
        Thread.sleep(1000);
        CheckoutUtils checkutil = new CheckoutUtils();
        String ProdJWT = checkutil.PinAuthCall("https://" + StoreNum + "asssp01/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate", ProdAssoc);

        //Removes The Placeholder ReplaceThisJWT in the MacysIntegratedEnv Json and replaces it with the String of Json
        FileInputStream fin = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/ProdJsonFiles/" + StoreNum + ".json"));
        InputStreamReader in = new InputStreamReader(fin);
        BufferedReader bufferedReader = new BufferedReader(in);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        String json = sb.toString();
        json = json.replace("ReplaceThisJWT", ProdJWT);
        WebDriver driver = WebDriverManager.getWebDriver();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
        if (StoreNum.contains("me")) {
            System.out.println("I ENTERED THE ME IF STATEMENT");
            //Sets Local Storage in the Bag Application
            Navigate.visit("https://" + StoreNum + "asssp01/js/common.js");
            Wait.forPageReady();
            ClearLocal();
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
            Thread.sleep(1000);
            refreshPage();

            //Sets Local Storage in the Landing Page
            Navigate.visit(MacysProdLandingDNS);
            Wait.forPageReady();
            ClearLocal();
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
            Thread.sleep(1000);
            refreshPage();

            //Sets Local Storage in the Search Page
            Navigate.visit(MacysProdSearchDNS);
            Wait.forPageReady();
            ClearLocal();
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
            Thread.sleep(1000);
            refreshPage();
        } else {
            System.out.println("The String did not contain me");

            //Sets Local Storage in the Bag Application
            Navigate.visit("https://" + StoreNum + "asssp01/js/common.js");
            Wait.forPageReady();
            ClearLocal();
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
            Thread.sleep(1000);
            refreshPage();

            //Sets Local Storage in the Landing Page
            Navigate.visit(BloomProdLandingDNS);
            Wait.forPageReady();
            ClearLocal();
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
            Thread.sleep(1000);
            refreshPage();

            //Sets Local Storage in the Search Page
            Navigate.visit(BloomProdSearchDNS);
            Wait.forPageReady();
            ClearLocal();
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
            Thread.sleep(1000);
            refreshPage();


        }

    }


    ////// This has the bad pin call
    public static void VerifyProdbadpin(String StoreNum) throws Exception {
        Thread.sleep(1000);
        CheckoutUtils checkutil = new CheckoutUtils();
        checkutil.BadPinAuthCall("https://" + StoreNum + "asssp01/pos/AssociateAuthenticateService/rest/V2/pinAuthenticate", TestAssoc);

        String json;
        try {
            FileInputStream finD = new FileInputStream(new File(System.getProperty("user.dir") + "/Stores/Checkout/resources/elements/responsive/other/pages/ProdJsonFiles/Practice.json"));
            InputStreamReader inD = new InputStreamReader(finD);
            BufferedReader bufferedReaderD = new BufferedReader(inD);
            StringBuilder sbD = new StringBuilder();

            String line;
            while ((line = bufferedReaderD.readLine()) != null) {
                sbD.append(line);
            }
            String jsonM = sbD.toString();
            json = jsonM;
        } catch (FileNotFoundException e) {
            System.out.println("I entered the catch setting file path");
            FileInputStream finD = new FileInputStream(new File("Stores/Checkout/src/main/java/com/macys/sdt/projects/Stores/Checkout/resources/elements/responsive/other/pages/ProdJsonFiles/Practice.json"));

            InputStreamReader inD = new InputStreamReader(finD);
            BufferedReader bufferedReaderM = new BufferedReader(inD);
            StringBuilder sbD = new StringBuilder();

            String line;
            while ((line = bufferedReaderM.readLine()) != null) {
                sbD.append(line);
            }
            String jsonx = sbD.toString();
            json = jsonx;
        }
        System.out.println("This is the practice json" + json);

            if (StoreNum.contains("ME")) {
                json = json.replace("DivNum", "71");

            } else {
                json = json.replace("DivNum", "72");
            }
            String storetrim = StoreNum.substring(2);
            System.out.println("This is t he trimmed store number" + storetrim);

            json = json.replace("StrNum", storetrim);
            json = json.replace("ispnum", "" + StoreNum + "asssp01");
            json = json.replace("sspnum", "" + StoreNum + "asssp01");
            System.out.println("This is the final ammended " + json);

            WebDriver driver = WebDriverManager.getWebDriver();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
            System.out.println("Current STore Number" + StoreNum);

            if (StoreNum.contains("ME")) {
                System.out.println("I ENTERED THE ME IF STATEMENT");
                //Sets Local Storage in the Bag Application
                Navigate.visit("https://" + StoreNum + "asssp01/js/common.js");
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(MacysProdLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(MacysProdSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();

                //Navigate back to landing page to start the transaction UI validation
                Thread.sleep(1000);
                Navigate.visit("http://mstore.devops.fds.com");
                Wait.forPageReady();
                Thread.sleep(1000);

            } else {
                System.out.println("The String did not contain me");

                //Sets Local Storage in the Bag Application
                Navigate.visit("https://" + StoreNum + "asssp01/js/common.js");
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Landing Page
                Navigate.visit(BloomProdLandingDNS);
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();

                //Sets Local Storage in the Search Page
                Navigate.visit(BloomProdSearchDNS);
                Wait.forPageReady();
                ClearLocal();
                executor.executeScript(String.format("window.localStorage.setItem('%s', JSON.stringify(%s));", "simulatorData", json));
                Thread.sleep(1000);
                refreshPage();

                //Navigate back to landing page to start the transaction UI validation
                Thread.sleep(1000);
                Navigate.visit("http://bstore.devops.fds.com");
                Wait.forPageReady();
                Thread.sleep(1000);

            }

        }
    }

















