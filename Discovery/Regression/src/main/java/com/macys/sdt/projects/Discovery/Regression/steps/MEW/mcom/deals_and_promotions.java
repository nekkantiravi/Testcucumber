package com.macys.sdt.projects.Discovery.Regression.steps.MEW.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cucumber.api.java.en.When;

/**
 * Created by Admin on 05/07/17.
 */
public class deals_and_promotions extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(deals_and_promotions.class);

        @When("I click \"([^\"]*)\" on \"([^\"]*)\" (page|panel)$")
        public void iClickLinkOnPage (String link, String page, String pageOrPanel){
        if (pageOrPanel.equals("page")) {
            shouldBeOnPage(page);
        }
            pausePageHangWatchDog();
            Elements.elementShouldBePresent(page + "." + link);
            Wait.secondsUntilElementPresent("deals_and_promotions.offer_code",30);
            Clicks.click("deals_and_promotions.offer_code");
    }
}