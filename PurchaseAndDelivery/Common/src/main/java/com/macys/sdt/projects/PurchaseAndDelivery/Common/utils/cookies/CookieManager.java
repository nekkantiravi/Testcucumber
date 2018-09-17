package com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.cookies;

import com.macys.sdt.framework.exceptions.DriverNotInitializedException;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Created by atepliashin on 1/16/17.
 */
public class CookieManager {

    public Experiment experiment() {
        return new Experiment(this);
    }

    public MacysCookieMap macysCookieMap(CookieName cookieName) {
        return new MacysCookieMap(cookieName, this);
    }

    public void addCookie(Cookie cookie) {
        driver().manage().addCookie(cookie);
    }

    public Cookie getCookieNamed(CookieName cookieName) {
        return getCookieNamed(cookieName.toString());
    }

    public Cookie getCookieNamed(String cookieName) {
        return driver().manage().getCookieNamed(cookieName);
    }

    public Set<Cookie> getCookies() {
        return driver().manage().getCookies();
    }

    public void deleteCookieNamed(CookieName cookieName) {
        deleteCookieNamed(cookieName.toString());
    }

    public void deleteCookieNamed(String cookieName) {
        driver().manage().deleteCookieNamed(cookieName);
    }

    public String domain() {
        try {
            URL url = new URL(RunConfig.url);
            String host = url.getHost();
            return host.replaceAll("^www1?\\.", "");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Cannot create URL object from " + RunConfig.url, e);
        }
    }

    private WebDriver driver() {
        try {
            return WebDriverManager.getWebDriver();
        } catch (DriverNotInitializedException e) {
            return null;
        }
    }
}
