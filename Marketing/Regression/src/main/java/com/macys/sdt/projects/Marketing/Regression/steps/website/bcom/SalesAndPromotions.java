package com.macys.sdt.projects.Marketing.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SalesAndPromotions {

    private static final Logger logger = LoggerFactory.getLogger(SalesAndPromotions.class);
    String linkHref = null;
    List<Integer> pageCodes = new ArrayList<>(Arrays.asList(200,302,301));

    @Then("^I should validate Sales and Promotions links in flyouts navigate to valid destination$")
    public void iShouldBeAbleToNavigateToValidDestination() {
        List<WebElement> salesAndPromotionsLinks = Elements.findElements("category_menu.sales_and_promotions_links");
        for (WebElement link : salesAndPromotionsLinks) {
            if(link.isDisplayed()) {
                linkHref = link.getAttribute("href");
                if (!linkHref.contains("http")) {
                    linkHref = RunConfig.url + linkHref;
                }
                if(link.getText().contains("%") && !linkHref.contains("%25")){
                    linkHref = linkHref.replace("%","%25");
                }
                int statusCode = RESTOperations.doGET(linkHref, null).getStatus();
                logger.info("Sales and Promotions Link::" + linkHref + ":: Response Code ::" + statusCode);
                Assert.assertTrue("Sales and Promotions Link::" + linkHref + " linkText::" + link.getText() + " returned " + statusCode + " on GET call",
                        pageCodes.contains(statusCode));
            }
        }
    }

    @Then("^All sale links should be navigated to valid destination$")
    public void allSaleLinksShouldBeNavigatedToValidDestination() {
        List<WebElement> salesAndPromotionsLinks = Elements.findElements("deals_and_promotions.shop_now");
        for (WebElement link : salesAndPromotionsLinks) {
                linkHref = link.getAttribute("href");
                if (!linkHref.contains("http")) {
                    linkHref = RunConfig.url + linkHref;
                }
                int statusCode = RESTOperations.doGET(linkHref, null).getStatus();
                logger.info("Shop Now Link::" + linkHref + ":: Response Code ::" + statusCode);
                Assert.assertTrue("Shop Now Link::" + linkHref + " linkText::" + link.getText() + " returned " + statusCode + " on GET call",
                        pageCodes.contains(statusCode));
        }
    }
}

