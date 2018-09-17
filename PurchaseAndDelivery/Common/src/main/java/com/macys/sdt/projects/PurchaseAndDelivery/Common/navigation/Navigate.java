package com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation;

import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by atepliashin on 11/3/16.
 */
public class Navigate extends com.macys.sdt.framework.interactions.Navigate {

    private static final Map<Class<? extends Page>, Page> pages = new HashMap<>();

    /**
     * Returns a constructed page. Waits when the page is ready, but doesn't open it.
     * Use it when the page is already open, but might be not ready (loaded/rendered) completely.
     *
     * @param <T> page class to construct
     * @return constructed page
     */
    public static <T extends Page> T get(Class<T> pageClass) {
        return get(pageClass, true);
    }

    /**
     * Returns a constructed page. Doesn't try to open, but can wait when the page is ready.
     * If <tt>waitForReady</tt> is true, will be waiting until the page is ready (behaviour can be adjusted by
     * overriding of method {@code waitForReady()} for the required page) or return it immediately otherwise.
     *
     * @param waitForReady defines should driver wait for page ready or not
     * @param <T>          page class to construct
     * @return constructed page
     */
    public static <T extends Page> T get(Class<T> pageClass, boolean waitForReady) {
        T page;
        if (pages.containsKey(pageClass) && !pages.get(pageClass).driver().toString().contains("null")) {
            // noinspection unchecked
            page = (T) pages.get(pageClass);
        } else {
            try {
                page = pageClass.newInstance();
                pages.put(pageClass, page);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Can't initialize instance of " + pageClass.getName(), e);
            }
        }
        if (waitForReady) {
            page.waitForReady();
        }
        return page;
    }

    /**
     * Tries to navigate to/open a page using format_url from page json description.
     * Target format_url can be formatted by given additional parameters
     *
     * @param args parameters that will be used to format format_url from the page json description
     * @param <T>  page class to construct
     * @return constructed page
     */
    public static <T extends Page> T to(Class<T> pageClass, Object... args) {
        T page = get(pageClass, false);
        visit(page.pageAlias(), args);
        page.waitForReady();
        return page;
    }
}
