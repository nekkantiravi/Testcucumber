package com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom;


import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.logging.Logger;


public class Actions extends StepUtils {

    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static int screenWidth = 0;
    private static int screenHeight = 0;


    public static void visitURL(String url) throws Throwable {
        try {
            WebDriverManager.getWebDriver().get(url);
            Wait.forPageReady();
            WebDriverManager.closeAlert();
            log.info("Successfully launched browser visiting URL: " + url);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.warning("Error launching browser or visiting URL:  " + url);
        }
        Thread.sleep(2000);
    }

    public static void closeBrowser() throws Throwable {
        Cookies.deleteAllCookies();
        WebDriverManager.getWebDriver().close();
    }

    public static void closeNopenBrowser(String url) throws Throwable {
        WebDriverManager.getWebDriver().close();
        Thread.sleep(1000);
        log.info("Browser Closed!");
        try {
            WebDriverManager.getWebDriver().get(url);
            Wait.forPageReady();
            WebDriverManager.closeAlert();
            log.info("Successfully re-opened browser visiting URL: " + url);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.warning("Error re-opening browser or visiting URL:  " + url);
        }
        Thread.sleep(2000);
    }

    public static void changeDimension(String device) throws Throwable {
        log.info("Original dimension (width, height):  " + WebDriverManager.getWebDriver().manage().window().getSize());
        Dimension dimensions = WebDriverManager.getWebDriver().manage().window().getSize();
        WebElement html = WebDriverManager.getWebDriver().findElement(By.tagName("html"));
        int originalWidth = Integer.parseInt(html.getAttribute("clientWidth"));
        int originalHeight = Integer.parseInt(html.getAttribute("clientHeight"));
        switch (device) {
            case ("desktop"): {
                screenWidth = originalWidth;
                screenHeight = originalHeight;
                break;
            }
            case ("mobile"): {
                screenWidth = 400;
                screenHeight = 700;
                break;
            }
            case ("tablet"): {
                screenWidth = 870;
                screenHeight = 530;
                break;
            }
        }
        WebDriverManager.getWebDriver().manage().window().setSize(new Dimension(
                (dimensions.width - originalWidth) + screenWidth,
                (dimensions.height - originalHeight) + screenHeight
        ));
        html = WebDriverManager.getWebDriver().findElement(By.tagName("html"));
        Assert.assertTrue(Integer.parseInt(html.getAttribute("clientWidth")) <= screenWidth);
        Assert.assertTrue(Integer.parseInt(html.getAttribute("clientHeight")) <= screenHeight);
        log.info("Dimensions modified from width=" + originalWidth + " & height=" + originalHeight + " to width=" +
                Integer.parseInt(html.getAttribute("clientWidth")) + " & height=" + Integer.parseInt(html.getAttribute("clientHeight")));
        Thread.sleep(2000);
    }

    public static void scroll(String direction, String el) {
        if(el != null)
            scrollToLazyLoadElement(el);
        else if(direction != null) {
            switch(direction) {
                case "down": {
//                    Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight/3)");
                    ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight/4)");
                    break;
                }
                case "up": {
                    Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
//                    ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(document.body.scrollHeight), 0");
                    break;
                }
                case "right": {
                    ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollRight)");
                    break;
                }
                case "left": {
                    ((JavascriptExecutor) WebDriverManager.getWebDriver()).executeScript("window.scrollTo(0, document.body.scrollLeft)");
                    break;
                }
            }
        }
    }

    public static void setUserAgent(String device) {
        String chromeDriverLocation = "shared/resources/src/main/resources/framework/selenium_drivers/chromedriverMac";
        System.out.println("Chrome Driver: " + chromeDriverLocation );
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions options = new ChromeOptions();
        if(device.equalsIgnoreCase("desktop"))
            options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
        else if(device.equalsIgnoreCase("mobile"))
            options.addArguments("--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");
        else
            options.addArguments("--user-agent=Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
//        WebDriverManager.getWebDriver();
    }


}
