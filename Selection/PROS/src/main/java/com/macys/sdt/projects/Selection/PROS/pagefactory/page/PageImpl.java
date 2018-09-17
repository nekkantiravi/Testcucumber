package com.macys.sdt.projects.Selection.PROS.pagefactory.page;

import com.google.common.base.CaseFormat;
import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Selection.PROS.exception.UnknownPageException;
import com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy;
import com.macys.sdt.projects.Selection.PROS.pagefactory.loader.decorator.JsonHtmlElementLocatorFactory;
import com.macys.sdt.shared.steps.website.PageNavigation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

import java.net.MalformedURLException;

/**
 * credit: atepliashin
 */
public abstract class PageImpl implements Page {
    protected WebDriver driver;
    protected String pageName = null;
    protected String idSelector = null;
    private static Logger logger = LoggerFactory.getLogger(PageImpl.class);

    protected PageImpl() {
        try {
            driver = WebDriverManager.getWebDriver();
            PageFactory.initElements(new HtmlElementDecorator(new JsonHtmlElementLocatorFactory(driver)), this);
        } catch (DriverNotInitializedException e) {
            Assert.fail("Driver not initialized");
        }
    }

    public PageImpl(String _pageName, String _idSelector) {
        this();
        pageName = _pageName;
        idSelector = _idSelector;
    }


//    public WebDriver driver() {
//        return driver;
//    }

//    public void waitForReady() {
//        Wait.forPageReady(pageAlias());
//    }

    public String pageAlias() {

        if (getClass().isAnnotationPresent(FindBy.class)) {
            return getClass().getAnnotation(FindBy.class).jsonPath();
        } else {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, getClass().getSimpleName());
        }
    }

    @Override
    public abstract void navigate(PageNavigation.NavigateMode _navigateMode) throws MalformedURLException;

    @Override
    public void validate() throws UnknownPageException {

        if (!StepUtils.onPage(pageAlias())) {

            throw new UnknownPageException("Page unknown by %s".format(pageAlias()));
        }
    }
}
