package com.macys.sdt.projects.Stores.STAT.utils;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static com.macys.sdt.framework.runner.RunConfig.appTest;

public class STATUtils {
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






}





















