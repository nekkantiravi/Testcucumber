package com.macys.sdt.projects.Stores.Omniclient.utils;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.runner.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OmniclientUtils {
    // Put code here that doesn't necessarily apply to just one page or step.
    // You can also use it for more general utility things, like interacting
    // with a database or talking to a service.

    public static void setWindowToiPhoneView() {
        try {
            WebDriverManager.getWebDriver().manage().window().setPosition(new Point(0, 0));
            WebDriverManager.getWebDriver().manage().window().setSize(new Dimension(375, 667));
        } catch (DriverNotInitializedException e) {
            Assert.fail("Driver not initialized");
        }
    }

    public static void waitForAngularLoad() {
        WebDriver jsWaitDriver =  WebDriverManager.getWebDriver();
        WebDriverWait wait = new WebDriverWait(jsWaitDriver, 15);
        JavascriptExecutor jsExec = (JavascriptExecutor) jsWaitDriver;

        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) jsWaitDriver)
                .executeScript(angularReadyScript).toString());

        //Get Angular is Ready
        boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            System.out.println("ANGULAR is NOT Ready!");
            //Wait for Angular to load
            wait.until(angularLoad);
        } else {
            System.out.println("ANGULAR is Ready!");
        }
    }
    public static boolean isDateValid(String date, DateTimeFormatter dateTimeFormatter) {
        try {
            LocalDate.parse(date, dateTimeFormatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
