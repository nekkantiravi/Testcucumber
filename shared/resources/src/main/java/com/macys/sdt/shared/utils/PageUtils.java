package com.macys.sdt.shared.utils;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageUtils extends CommonUtils {

    private static PageUtils ourInstance = new PageUtils();
    private static final Logger LOGGER = LoggerFactory.getLogger(PageUtils.class);

    public static PageUtils getInstance() {
        return ourInstance;
    }

    private PageUtils() {
    }

    public void shouldBeOnPage(String pageName) throws Exception {

        String elementTimeout = StringUtils.trimToEmpty(com.macys.sdt.framework.runner.RunConfig.getExParam(RunConfig.Params.ElementTimeout.toString()));

        if(elementTimeout.matches("\\d+")) {
            shouldBeOnPage(pageName, Integer.valueOf(elementTimeout));
        } else {
            super.shouldBeOnPage(pageName);
        }
    }

    public void shouldBeOnPage(String pageName, int timeout) throws Exception {

        LOGGER.debug(String.format(">>>>>>>>> page name=%s;timeout=%d", pageName, timeout));

        Wait.forPageReady();
        Wait.secondsUntilElementPresent(pageName + ".verify_page", timeout);

        if (onPage(pageName)) {
            return;
        }

        throw new Exception("Not on page(s): " + pageName + " Current URL: " + WebDriverManager.getCurrentUrl());
    }
}
