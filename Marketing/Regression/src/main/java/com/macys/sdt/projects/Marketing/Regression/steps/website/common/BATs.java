package com.macys.sdt.projects.Marketing.Regression.steps.website.common;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class BATs extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(BATs.class);

    @Then("^I verify that every link on the home page leads to a valid destination$")
    public void iVerifyThatEveryLinkOnTheHomePageLeadsToAValidDestination() throws Throwable {
        StringBuilder errors =  new StringBuilder();
        pausePageHangWatchDog();
        List<Integer> pageCodes = new ArrayList<>(Arrays.asList(200,302,301));
        int statusCode;
        List<WebElement> links = Elements.findElements("home.all_links").stream().filter(BATs::isVisible).collect(toList());
        if (bloomingdales()) {
            links.addAll(Elements.findElements("home.hp_images"));
            links.add(Elements.findElement("home.top_free_shipping_banner"));
            links.add(Elements.findElement("home.bottom_free_shipping_banner"));
        }
        List<String> linkHrefs = links.stream().map(BATs::getPopLink).collect(toList());
        for (String linkHref : linkHrefs) {
            try {
                statusCode = RESTOperations.doGET(linkHref, null).getStatus();
            } catch (Exception e) {
                logger.info("Error found: " + e);
                statusCode = 0;
            }
            String message = "ImageLink::" + linkHref + " returned " + statusCode + " on GET call";
            logger.info(message);
            if (!pageCodes.contains(statusCode)) {
                errors.append(message).append("\n");
            }
        }
        Assert.assertTrue(errors.toString(), errors.toString().equals(""));
        resumePageHangWatchDog();
    }

    private static boolean isVisible (WebElement link) {
        return (link.isDisplayed() && !link.getText().isEmpty());
    }

    private static String getPopLink (WebElement link) {
        Pattern popRegex = Pattern.compile("(?:javascript:pop\\(')([^,]*)'?");
        Matcher popMatch;
        String linkHref = link.getAttribute("href");
        if (linkHref == null) {
            return "";
        }
        popMatch = popRegex.matcher(linkHref);
        if (popMatch.find()) {
            linkHref = popMatch.group(1).replace("')","");
            logger.info("PopLink is ::" + linkHref);
        }
        if (!linkHref.contains("http")) {
            linkHref = RunConfig.url + linkHref;
        }
        return linkHref;
    }
}
