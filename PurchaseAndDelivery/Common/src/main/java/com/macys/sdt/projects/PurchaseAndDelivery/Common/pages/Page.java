package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages;

import com.google.common.base.CaseFormat;
import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.loader.decorator.JsonHtmlElementLocatorFactory;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * Created by atepliashin on 10/7/16.
 */
abstract public class Page {
    protected WebDriver driver;

    public Page() {
        try {
            driver = WebDriverManager.getWebDriver();
            PageFactory.initElements(new HtmlElementDecorator(new JsonHtmlElementLocatorFactory(driver)), this);
        } catch (DriverNotInitializedException e) {
            Assert.fail("Driver not initialized");
        }
    }

    public WebDriver driver() {
        return driver;
    }

    public void waitForReady() {
        Wait.forPageReady(pageAlias());
    }

    public String pageAlias() {
        if (this.getClass().isAnnotationPresent(FindBy.class)) {
            return this.getClass().getAnnotation(FindBy.class).jsonPath();
        } else {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, this.getClass().getSimpleName());
        }
    }

    public boolean isReady() {
        return StepUtils.onPage(pageAlias());
    }
}
