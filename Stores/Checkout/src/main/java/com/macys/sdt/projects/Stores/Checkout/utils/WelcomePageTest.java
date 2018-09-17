package com.macys.sdt.projects.Stores.Checkout.utils;

import com.galenframework.api.Galen;
import com.galenframework.reports.model.LayoutReport;
import com.galenframework.testng.GalenTestNgTestBase;
import com.galenframework.validation.ValidationResult;
import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.runner.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



public class WelcomePageTest extends GalenTestNgTestBase {

    private static final Logger log = LoggerFactory.getLogger(WelcomePageTest.class);

    @Override
    public WebDriver createDriver(Object[] args) {
        return WebDriverManager.startWebDriver();
    }

    @Override
    public WebDriver getDriver() {
        try {
            return WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            log.error("Driver was not initialized");
        }
        return null;
    }

    @Override
    public void checkLayout(String specPath, List<String> includedTags) throws IOException {
        LayoutReport layoutReport = Galen.checkLayout(this.getDriver(), specPath, includedTags);
        if (layoutReport.errors() > 0){
            throw new RuntimeException("Incorrect layout: " + layoutReport.getValidationErrorResults()
                    .stream().map(ValidationResult::getError).collect(Collectors.toList()));
        }
    }

    @Override
    public void load(String uri) {
        getDriver().get(uri);
    }

}